package entities.lobbies;

import entities.games.Game;
import entities.Player;
import entities.factories.GameFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Lobby {
    public static final int LOBBY_CHECK_READY_EVERY_MS = 100;

    private final String lobbyId;
    private final boolean isPublic;

    private HashMap<String, Integer> settings;

    private ArrayList<Player> playerList;
    private Game game;
    private GameFactory gameFactory;

    private final Timer lobbyReadyChecker;

    public Lobby (String lobbyId, boolean isPublic) {
        this.lobbyId = lobbyId;
        this.isPublic = isPublic;

        // Checks if the lobby ready condition is satisfied and starts the game if so
        lobbyReadyChecker = new Timer();
        lobbyReadyChecker.scheduleAtFixedRate(new TimerTask() {
            // Using dem awesome ANONYMOUS CLASSES amirite?
            @Override
            public void run() {
                // Start game if ready
                if (isReady()) {
                    // TODO: Start game
                }
            }
        }, 0, LOBBY_CHECK_READY_EVERY_MS);
    }

    /**
     * Condition to determine if the lobby is ready, called by
     * internal timer.
     * @return is this lobby ready to start the game
     */
    public abstract boolean isReady ();

    public String getLobbyCode () { return lobbyId; }

    public Player[] getPlayers () {
        Player[] p = new Player[playerList.size()];
        return playerList.toArray(p);
    }

    public void setSetting (String settingName, int settingValue) {
        settings.put(settingName, settingValue);
    }
}
