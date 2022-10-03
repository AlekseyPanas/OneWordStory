package adapters.servers.ds;

// Layer: INTERFACE ADAPTERS

public class ConnectReturnData {
    public static final int CLIENT_ID_WHEN_FAIL = -1;

    private final boolean success;
    private final String message;
    private final int clientId;

    public ConnectReturnData (boolean success, String message, int clientId) {
        this.success = success;
        this.message = message;
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
