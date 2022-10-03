package usecases.submit_name;

// Layer: USE CASE

import usecases.ServerResponse;

public interface snServerGateway {
    /**
     * <p> Attempt to connect to the server and supply the given display name </p>
     * @param displayName Display name to be associated with
     *                    this client once connected to the server.
     * @return Result of attempting to connect to the server
     */
    ServerResponse setName (String displayName);
}
