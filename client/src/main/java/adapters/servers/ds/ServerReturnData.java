package adapters.servers.ds;

public class ServerReturnData {
    private final boolean success;
    private final String message;
    private final int responseCode;

    /**
     *
     * @param success a general boolean of whether the desired task was successful
     * @param message a string message of the response
     * @param responseCode is an arbitrary code whose significance can be defined by the class extending
     *                     the ServerAdapter
     */
    public ServerReturnData (boolean success, String message, int responseCode) {
        this.success = success;
        this.message = message;
        this.responseCode = responseCode;
    }

    public String getMessage() { return message; }

    public boolean isSuccess() { return success; }

    public int getResponseCode() { return responseCode; }
}
