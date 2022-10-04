package adapters.servers;

import adapters.ViewModel;
import adapters.servers.ds.ConnectReturnData;
import adapters.servers.ds.ServerReturnData;
import usecases.ServerResponse;
import usecases.submit_name.snServerGateway;

// Layer: INTERFACE ADAPTERS

public abstract class ServerAdapter implements snServerGateway {

    private final ViewModel viewM;
    public ServerAdapter (ViewModel viewM) {
        this.viewM = viewM;
    }

    /**
     * Try to establish a connection with the server
     * @return object with success bool, message string, and unique ID given to this client
     * on the server side
     */
    protected abstract ConnectReturnData connect ();

    /**
     * Send a string of data to the server
     * @param line string without \n \r indicating what you want to send
     * @return was the line sent successfully
     */
    protected abstract boolean sendLine (String line);

    /**
     * Returns if the connection is actively open, meaning, that a
     * connection is established and has not been closed. In other words,
     * data is being received and data can be sent to the server
     * @return if the connection is open
     */
    protected abstract boolean isConnectionOpen ();

    /**
     * Gracefully close the connection with the server
     * @return whether the connection is now closed
     */
    protected abstract boolean closeConnection ();

    /**
     * Send display name to server in agreed format, wait for response and return it
     */
    protected abstract ServerReturnData setDisplayName (String displayName);

    @Override
    public ServerResponse setName(String displayName) {
        // Try to connect
        ConnectReturnData res = connect();

        if (res.isSuccess()) {
            // Try to send in display name
            ServerReturnData serverReturnData = setDisplayName(displayName);

            // Return if display name was received by server
            return new ServerResponse(serverReturnData.isSuccess() ?
                    ServerResponse.ResponseCode.SUCCESS : ServerResponse.ResponseCode.FAIL,
                    serverReturnData.getMessage());
        }

        // Failed to connect to server
        return new ServerResponse(ServerResponse.ResponseCode.FAIL, res.getMessage());
    }
}
