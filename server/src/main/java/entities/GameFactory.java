package entities;

import java.util.HashMap;

public interface GameFactory {
    /**
     * Given some settings, create the appropriate game instance
     * @param settings A map of string to integer settings
     * @return
     */
    Game create (HashMap<String, Integer> settings);
}
