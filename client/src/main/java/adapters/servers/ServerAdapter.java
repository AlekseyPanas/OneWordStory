package adapters.servers;

import adapters.ViewModel;
import adapters.servers.ds.ConnectReturnData;
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

    @Override
    public ServerResponse setName(String displayName) {
        ConnectReturnData res = connect();
        if (res.isSuccess()) {


            return new ServerResponse(ServerResponse.ResponseCode.SUCCESS, res.getMessage();
        }

        return new ServerResponse(ServerResponse.ResponseCode.FAIL, res.getMessage());
    }
}
