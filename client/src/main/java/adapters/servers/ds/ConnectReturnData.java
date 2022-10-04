package adapters.servers.ds;

// Layer: INTERFACE ADAPTERS

public class ConnectReturnData extends ServerReturnData {
    public static final int CLIENT_ID_WHEN_FAIL = -1;

    private final int clientId;

    public ConnectReturnData (boolean success, String message,
                              int responseCode, int clientId) {
        super(success, message, responseCode);
        this.clientId = clientId;
    }

    public ConnectReturnData (boolean success, String message, int responseCode) {
        super(success, message, responseCode);
        this.clientId = ConnectReturnData.CLIENT_ID_WHEN_FAIL;
    }

    public int getClientId() {
        return clientId;
    }
}
