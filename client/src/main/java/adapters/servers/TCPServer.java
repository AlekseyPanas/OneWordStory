package adapters.servers;

import adapters.ViewModel;
import adapters.servers.ds.ConnectReturnData;
import java.net.*;

// Layer: DRIVERS & FRAMEWORKS

public class TCPServer extends ServerAdapter {
    // TODO: Change this to pull from a configuration file.
    private final String SERVER_IP = "localhost";
    private final String SERVER_PORT = "42069";

    public TCPServer (ViewModel viewM) {
        super(viewM);
    }

    @Override
    protected ConnectReturnData connect() {
        // TODO: Implement this method
        return new ConnectReturnData(false, "Under construction",
                ConnectReturnData.CLIENT_ID_WHEN_FAIL);
    }
}
