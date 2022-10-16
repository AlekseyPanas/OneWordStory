package entities.lobbies;

import entities.Player;

public class PrivateLobby extends Lobby {

    public PrivateLobby (String lobbyId) {
        super(lobbyId, false);
    }

    /**
     * Lobby is ready when all players in the lobby are ready and there
     * are at least two players
     * @return is lobby ready to start game
     */
    @Override
    public boolean isReady() {
        // If less than 2 players are in the lobby, return false right away
        if (getPlayers().size() < 2) {
            return false;
        }

        // Return if all players are ready
        for (Player p: getPlayers()) {
            if (!p.isReady()) {
                return false;
            }
        } return true;
    }
}
