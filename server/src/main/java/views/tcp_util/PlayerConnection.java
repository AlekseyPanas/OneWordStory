package views.tcp_util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class PlayerConnection implements Runnable {

    private final ServerSocket serverSocket;
    private Socket clientSocket;

    // Reader and Writer of data to the connected client
    private PrintWriter outgoingDataWriter;
    private BufferedReader incomingDataReader;

    private final LinkedList<String> receivedData;
    // prevent data retrieval when the reception thread is assigning a new value.
    // For thread safety.
    private final ReentrantLock lockQ;

    private boolean connectionComplete;

    /** Start the connection thread to accept and then receive data from a connection
     * @param serverSocket Socket associated with this running server
     */
    public PlayerConnection(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        connectionComplete = false;

        lockQ = new ReentrantLock();
        receivedData = new LinkedList<>();

        // Starts the accepting run method
        (new Thread(this)).start();
    }

    /**
     * <p>
     *     When the thread is started, it waits to accept a connection. This step may
     *     fail in which case the connection will never complete and this connection instance
     *     is useless. Return true in that event.
     * </p>
     * <p>
     *     As soon as a connection is successfully accepted, this method also returns true
     * </p>
     * @return is connection complete
     */
    public boolean isConnectionComplete() { return connectionComplete; }

    /**
     * If accept() failed, socket will be null and this method returns true.
     * If connection was established but subsequently the socket was called to close(),
     * this method also returns true.
     * @return if this connection is closed or was not yet connected
     */
    public boolean isClosed() { return clientSocket == null || clientSocket.isClosed(); }

    /**
     * Attempt to close the client socket, the writer, and the reader. Return success
     */
    public boolean closeConnection () {
        try {
            clientSocket.close();
            outgoingDataWriter.close();
            incomingDataReader.close();

            System.out.println("closeConnection() with " + clientSocket.getInetAddress() + " succeeded");

            return true;
        }

        catch (IOException e) {
            System.out.println("closeConnection() with " + clientSocket.getInetAddress() + " failed");

            return false;
        }
    }

    /**
     * Check if the data queue is empty in a thread safe manner
     * @return if any data has been received
     */
    public boolean hasReceivedData () {
        boolean empty;

        // Waits until lock is free, then locks it to make edit
        lockQ.lock();
        try {
            // Add newly received chunk to the queue
            empty = receivedData.isEmpty();
        } finally {
            lockQ.unlock();
        }

        return empty;
    }

    /**
     * Pop from the queue in a thread-safe manner.
     * @return the next chunk, or null if connection is closed
     */
    public String getNextChunk () {
        if (hasReceivedData()) {
            String dataChunk;

            // Waits until lock is free, then locks it to make edit
            lockQ.lock();
            try {
                // Pop the next Datachunk in queeu
                dataChunk = receivedData.remove();
            } finally {
                lockQ.unlock();
            }

            return dataChunk;

        } return "";
    }

    /**
     * Add next string of data to the receivedData queue in a thread safe manner
     */
    private void addNewChunk (String chunk) {
        // Waits until lock is free, then locks it to make edit
        lockQ.lock();
        try {
            // Add newly received chunk to the queue
            receivedData.add(chunk);
        } finally {
            lockQ.unlock();
        }
    }

    /**
     * Send a line of data to the client. Exclude the \n or \r at the end as that
     * will be added for you in this method.
     * @param line A single line of data, without \r or \n at the end
     * @return true if the data was sent, or false if an error occurred or the socket was closed
     */
    public boolean sendLine (String line) {
        if (!isClosed()) {
            outgoingDataWriter.println(line);
            return true;
        } return false;
    }

    @Override
    public void run () {
        try {
            System.out.println("Awaiting a new connection...");
            clientSocket = serverSocket.accept();

            System.out.println("Connection with " + clientSocket.getInetAddress() + " has been established.");
        } catch (IOException e) {
            clientSocket = null;
            System.out.println("Failed to accept a connection");
        } finally {
            // Regardless of outcome, this accept stage has passed. This flag will notify
            // that this connection instance is no longer waiting to accept.
            connectionComplete = true;
        }

        if (clientSocket != null) {

            try {
                // PrintWriter puts given chunks of sized strings to a stream
                outgoingDataWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                // Buffered reader allows you to grab data from the stream in defined sized chunks
                incomingDataReader = new BufferedReader(
                        // Reads byte stream and turns it to a character stream
                        new InputStreamReader(
                                // Byte stream of incoming data
                                this.clientSocket.getInputStream()));
            }
            // Occurs if getInputStream or getOutputStream throw IOException
            // Can happen if: Socket is closed (impossible), socket is not connected (impossible),
            // or, some random IO error (don't see where it would come from)
            catch (IOException e) {
                System.out.println("1) Could not get input/output stream for " + clientSocket.getInetAddress());

                boolean hasClosed = closeConnection();
                if (!hasClosed) {
                    System.out.println("2) Tried to close connection but failed");
                }
            }

            while (true) {
                try {
                    // Read until \n or \r character and add chunk to received data.
                    String dataChunk = incomingDataReader.readLine();

                    // Null means that the stream of the connected client is closed (aka client closed)
                    if (dataChunk == null) {
                        System.out.println("Closing, received NULL from " + clientSocket.getInetAddress());
                        break;
                    }

                    // Add and print the received string
                    addNewChunk(dataChunk);
                    System.out.println("R: " + clientSocket.getInetAddress() + " has sent: " + dataChunk);

                }
                // Occurs if readline causes IO Error. This should only occur if the reader was closed
                // due to the impossible preceding error. (Or from other mysterious causes)
                catch (IOException e) {
                    System.out.println("An IO exception occurred in Reception from " + clientSocket.getInetAddress());
                    break;
                }
            }
            // Close all thingies
            closeConnection();
        }
    }
}
