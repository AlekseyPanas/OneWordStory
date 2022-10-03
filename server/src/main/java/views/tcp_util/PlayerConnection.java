package views.tcp_util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PlayerConnection implements Runnable {

    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter outgoingDataWriter;
    private Reception receiver;
    private boolean connectionComplete;
    private boolean closed;

    /**
     * @param serverSocket Socket associated with this running server
     */
    public PlayerConnection(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        connectionComplete = false;

        // Starts the accepting run method
        (new Thread(this)).start();
    }

    /**
     * <p>
     *     When the thread is started, it waits to accept a connection. This step may
     *     fail in which case the connection will never complete and this connection instance
     *     is useless. Once a connection has been accepted, a reception thread is started which
     *     will constantly receive data and queue it. Once both of those complete, we have
     *     completed the connection and this method will return True
     *
     * </p>
     * @return is connection complete
     */
    public boolean isConnectionComplete() {
        return connectionComplete;
    }

    /**
     * If the connection was never properly established, this connection is considered
     * closed. Otherwise, if the reception thread is no longer receiving because it crashed,
     * connection is also closed.
     * @return if this connection is closed
     */
    public boolean isClosed() {
        return closed || !receiver.isReceiving();
    }

    /**
     * @return the next chunk, or null if connection is closed
     */
    public String getNextChunk () {
        return isClosed() ? null : receiver.getNextChunk();
    }

    /**
     * Attempt to close the connection. If connection hasn't been started yet or
     * if connection is already closed, then nothing changed so return false
     * @return did the connection go from a state of being open to being closed
     */
    public boolean closeConnection () throws IOException {
        if (connectionComplete || isClosed()) {
            clientSocket.close();
            closed = true;

            System.out.println("Connection with " + clientSocket.getInetAddress() + " has been closed");
        } return false;
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
        } catch (IOException e) {
            clientSocket = null;
        }

        if (clientSocket != null) {
            try {
                OutputStream s = clientSocket.getOutputStream();

                // PrintWriter puts given chunks of sized strings to a stream
                this.outgoingDataWriter = new PrintWriter(
                        s, true
                );

                receiver = new Reception(clientSocket);

                // Sets connection as complete
                connectionComplete = true;

                System.out.println("Connection with " + clientSocket.getInetAddress() + " has been established.");
            }
            // Occurs if output stream cannot be acquired (socket closed, etc)
            catch (IOException ignored) {
                closed = true;

                System.out.println("Connection with " + clientSocket.getInetAddress() + " failed to establish.");
            }
        }
    }
}
