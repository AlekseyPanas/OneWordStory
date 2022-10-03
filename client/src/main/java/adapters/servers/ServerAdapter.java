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

    protected abstract ConnectReturnData connect ();

    @Override
    public ServerResponse setName(String displayName) {
        // TODO: Implement this method
        return null;
    }
}
