package adapters.servers;

import adapters.ViewModel;
import adapters.servers.ds.ConnectReturnData;
import java.net.*;
import java.io.*;

// Layer: DRIVERS & FRAMEWORKS

public class TCPServer extends ServerAdapter {
    // TODO: Change this to pull from a configuration file.
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 42069;
    private static final int CONNECTION_TIMEOUT = 1000;

    private final Socket serverConnection;

    public TCPServer (ViewModel viewM) {
        super(viewM);

        // Create blank socket instance.
        serverConnection = new Socket();
    }

    @Override
    protected ConnectReturnData connect() {
        try {
            InetSocketAddress addr = new InetSocketAddress(SERVER_IP, SERVER_PORT);
            serverConnection.connect(addr, CONNECTION_TIMEOUT);
        }

        // Connection did not go through
        catch (IOException e) {
            return new ConnectReturnData(false, "Connection failed",
                    ConnectReturnData.CLIENT_ID_WHEN_FAIL);
        }

        // TODO: Retrieve client ID from server and return it below (replace 0).

        return new ConnectReturnData(true, "Connected successfully", 0);
    }
}
