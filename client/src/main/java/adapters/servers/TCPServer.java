package adapters.servers;

import adapters.ViewModel;
import adapters.servers.ds.ConnectReturnData;
import adapters.servers.ds.ServerReturnData;

import java.net.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

// Layer: DRIVERS & FRAMEWORKS

public class TCPServer extends ServerAdapter implements Runnable {
    // TODO: Change this to pull from a configuration file.
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 42069;
    private static final int CONNECTION_TIMEOUT = 1000;

    private Socket serverConnection;

    // Ability to send and receive data to/from server
    private PrintWriter serverWriter;
    private BufferedReader serverReader;

    // Stores string chunks of received data
    private final LinkedList<String> receivedData;
    // Lock queue when changes being made (thread safety)
    private final ReentrantLock qLock;

    /**
     * Initialize lock and data list and pass view model to parent ServerAdapter constructor
     * @param viewM Server can manipulate view model if needed to give view new
     *              data from server
     */
    public TCPServer (ViewModel viewM) {
        super(viewM);

        qLock = new ReentrantLock();
        receivedData = new LinkedList<>();
    }

    /**
     * Try to connect to the server and return output
     * @return success of connection and server-side clientId associated with this client
     */
    @Override
    protected ConnectReturnData connect() {
        try {
            // Close existing connection if there is one.
            // Assume all components are closed after this line.
            if (serverConnection != null) { closeConnection(); }

            // Create blank socket instance.
            serverConnection = new Socket();

            // Bind an address and port for the server you are connecting to
            InetSocketAddress addr = new InetSocketAddress(SERVER_IP, SERVER_PORT);
            // Try to connect. Will throw error if timed out, or an error occurs during connection
            serverConnection.connect(addr, CONNECTION_TIMEOUT);

            // If code following this comment runs, it means connection has been established

            // Create reader and writer.
            // Technically, getting the streams could cause an error, resulting in a connected
            // socket but the method will return failed connection. (I believe this scenario isn't
            // possible)
            serverReader = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
            serverWriter = new PrintWriter(serverConnection.getOutputStream(), true);

            // Start reception thread
            (new Thread(this)).start();

            // TODO: Retrieve client ID from server and return it below (replace 0).

            return new ConnectReturnData(true, "Connected successfully",
                    0, 0);
        }

        // Connection did not go through
        catch (IOException e) {
            return new ConnectReturnData(false, "Connection failed", 0);
        }
    }

    /**
     * Return is the server socket is connected, and that it has not been closed. This
     * ensures that true is returned iff the socket is currently still connected
     * @return is the connection open
     */
    @Override
    protected boolean isConnectionOpen () { return serverConnection.isConnected() && !serverConnection.isClosed(); }

    /**
     * Close server socket, reader, and writer. Catch failure. Note that there
     * is a rare (might be impossible) event where only some components
     * will be closed while others will remain open (depends on which one crashes).
     * @return if ALL components closed successfully.
     */
    @Override
    protected boolean closeConnection() {
        try {
            // Connection gracefully closes
            serverConnection.close();
            serverWriter.close();
            serverReader.close();

            System.out.println("Connection closed successfully");
            return true;
        } catch (IOException e) {
            // If internet disconnected, connection failed to close and might still be open
            System.out.println("Connection failed to close");
            return false;
        }
    }

    /**
     * Get and remove the next chunk in receivedData queue
     * @return the removed chunk, or empty string if queue is empty
     */
    public String getNextChunk () {
        if (hasReceivedData()) {
            String dataChunk;

            // Waits until lock is free, then locks it to make edit
            qLock.lock();
            try {
                // Pop the next Datachunk in queeu
                dataChunk = receivedData.remove();
            } finally {
                qLock.unlock();
            }

            return dataChunk;

        } return "";
    }

    /**
     *
     * @return if there is any data received waiting to be read
     */
    public boolean hasReceivedData () {
        boolean empty;

        // Waits until lock is free, then locks it to make edit
        qLock.lock();
        try {
            // Add newly received chunk to the queue
            empty = receivedData.isEmpty();
        } finally {
            qLock.unlock();
        }

        return empty;
    }

    /**
     * Add next string of data to the receivedData queue in a thread safe manner
     */
    private void addNewChunk (String chunk) {
        // Waits until lock is free, then locks it to make edit
        qLock.lock();
        try {
            // Add newly received chunk to the queue
            receivedData.add(chunk);
        } finally {
            qLock.unlock();
        }
    }

    /**
     * Send a line of data to the server as long as the connection is open
     * @param line string without \n \r indicating what you want to send
     * @return true if connection is open and message was sent, or false if connection
     * was closed
     */
    @Override
    protected boolean sendLine(String line) {
        if (isConnectionOpen()) {
            serverWriter.println(line);
            return true;
        } return false;
    }

    @Override
    protected ServerReturnData setDisplayName(String displayName) {
        boolean res = sendLine("displayName:" + displayName);
        if (res) {
            // TODO: Implement this part to scan for reception of confirmation
            return new ServerReturnData(true, "Successfully sent name", 1);
        } return new ServerReturnData(false, "Connection not open", 0);
    }

    /**
     * Runs reception thread. This thread runs during an open connection. If socket is closed
     * on either end, stream will end and readline will get null, causing definite closure.
     */
    @Override
    public void run() {
        System.out.println("Reception from server has started");

        while (true) {
            // Waits to receive a line of text
            try {
                String dataChunk = serverReader.readLine();
                // Stream closed (aka connection with server closed)
                if (dataChunk == null) {
                    System.out.println("Closing, received NULL from server");
                    break;
                }

                // Add data to linked list queue
                addNewChunk(dataChunk);
                System.out.println("R: Server has sent: " + dataChunk);
            }
            // Occurs if closeConnection() is called on this end. readline() will then throw IOException
            catch (IOException e) {
                System.out.println("An IO exception occurred in reception from sever");
                break;
            }
        }

        // This code executes if reception fails, which usually means that the
        // connection was closed somehow (whether intentionally or abruptly)
        System.out.println("Reception from server has closed");

        // Close all components on this end
        boolean closedSuccess = closeConnection();
        System.out.println("All components " + (closedSuccess ? "have" : "have NOT") + " been closed.");
    }
}
