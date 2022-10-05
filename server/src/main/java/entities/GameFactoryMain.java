package entities;

import java.util.HashMap;

public class GameFactoryMain implements GameFactory {
    public Game create (HashMap<String, Integer> settings) {
        return new GameRegular();
    }
}
