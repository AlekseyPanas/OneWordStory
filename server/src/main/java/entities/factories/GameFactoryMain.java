package entities.factories;

import entities.Player;
import entities.games.Game;
import entities.games.GameRegular;
import entities.lobbies.Lobby;

import java.util.HashMap;
import java.util.List;

public class GameFactoryMain implements GameFactory {
    public Game create (HashMap<String, Integer> settings, Lobby lobby, List<Player> initialPlayers) {
        return new GameRegular(lobby, 15, initialPlayers);
    }
}
