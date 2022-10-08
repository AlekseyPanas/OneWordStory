package entities.factories;

import entities.games.Game;
import entities.games.GameRegular;
import entities.lobbies.Lobby;

import java.util.HashMap;

public class GameFactoryMain implements GameFactory {
    public Game create (HashMap<String, Integer> settings, Lobby lobby) {
        return new GameRegular(lobby);
    }
}
