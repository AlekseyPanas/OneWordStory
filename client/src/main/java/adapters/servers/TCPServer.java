package adapters.servers;

import adapters.ViewModel;
import adapters.servers.ds.ConnectReturnData;
import java.net.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

// Layer: DRIVERS & FRAMEWORKS

public class TCPServer extends ServerAdapter {
    // TODO: Change this to pull from a configuration file.
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 42069;
    private static final int CONNECTION_TIMEOUT = 1000;

    private Socket serverConnection;

    // Ability to send and receive data to/from server
    private final PrintWriter serverWriter;
    private final BufferedReader serverReader;

    // Stores string chunks of received data
    private final LinkedList<String> receivedData;
    // Lock queue when changes being made (thread safety)
    private final ReentrantLock qLock;

    public TCPServer (ViewModel viewM) {
        super(viewM);

        qLock = new ReentrantLock();
        receivedData = new LinkedList<>();
    }

    public boolean isConnectionOpen () {
        return serverConnection.isConnected() !&& serverConnection.isClosed();
    }

    @Override
    protected boolean sendLine(String line) {
        if (isConnectionOpen()) {

        } return false;
    }

    @Override
    protected ConnectReturnData connect() {
        try {
            // Close existing connection. If this crashes, internet might be off so the catch block
            // will return a fail.
            serverConnection.close();

            // Create blank socket instance.
            serverConnection = new Socket();

            // Bind an address and port for the server you are connecting to and try to connect.
            InetSocketAddress addr = new InetSocketAddress(SERVER_IP, SERVER_PORT);
            // Will crash if times out
            serverConnection.connect(addr, CONNECTION_TIMEOUT);

            // This means connection has been established
            // TODO: Move on to create output and input streamd & receive thread
            serverConnection.getOutputStream();

            // TODO: Retrieve client ID from server and return it below (replace 0).

            return new ConnectReturnData(true, "Connected successfully", 0);
        }

        // Connection did not go through
        catch (IOException e) {
            return new ConnectReturnData(false, "Connection failed",
                    ConnectReturnData.CLIENT_ID_WHEN_FAIL);
        }
    }
}
