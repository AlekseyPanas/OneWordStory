package entities.factories;

import entities.Player;
import entities.games.Game;
import entities.lobbies.Lobby;

import java.util.HashMap;
import java.util.List;

public interface GameFactory {
    /**
     * Given some settings, create the appropriate game instance
     * @param settings A map of string to integer settings
     * @return the game instance
     */
    Game create (HashMap<String, Integer> settings, Lobby lobby, List<Player> initialPlayers);
}
