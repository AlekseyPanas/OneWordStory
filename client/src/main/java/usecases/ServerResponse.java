package usecases;

// Layer: USE CASE

public class ServerResponse {
    public enum ResponseCode {
        FAIL,
        SUCCESS
    }

    private final ResponseCode code;
    private final String message;

    public ServerResponse (ResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseCode getResponseCode () {
        return code;
    }

    public String getMessage () {
        return message;
    }
}
