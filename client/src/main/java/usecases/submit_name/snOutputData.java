package usecases.submit_name;

import usecases.ServerResponse;

// Layer: USE CASE

public class snOutputData {
    private ServerResponse res;

    public snOutputData (ServerResponse res) {
        this.res = res;
    }

    public ServerResponse getResponse() {
        return res;
    }

    public void setResponse(ServerResponse res) {
        this.res = res;
    }
}
