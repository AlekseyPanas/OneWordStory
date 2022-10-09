package entities.factories;

import entities.lobbies.Lobby;
import entities.lobbies.PrivateLobby;
import entities.lobbies.PublicLobby;

public class LobbyFactory {
    public Lobby create (boolean isPublic, String code) {
        return isPublic ? new PublicLobby(code) :
                new PrivateLobby(code);
    };
}
