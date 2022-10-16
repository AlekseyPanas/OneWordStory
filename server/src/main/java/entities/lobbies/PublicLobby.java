package entities.lobbies;

public class PublicLobby extends Lobby {
    public PublicLobby (String lobbyId) {
        super(lobbyId, true);
    }

    /**
     * Lobby is ready to start as soon as 2 players join
     * @return is lobby ready to start game
     */
    @Override
    public boolean isReady() {
        return getPlayers().size() >= 2;
    }
}
