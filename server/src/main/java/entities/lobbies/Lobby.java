package entities.lobbies;

import entities.games.Game;
import entities.Player;
import entities.factories.GameFactory;

import java.util.*;

public abstract class Lobby {
    public static final int LOBBY_CHECK_READY_EVERY_MS = 100;

    private final String lobbyId;
    private final boolean isPublic;

    private HashMap<String, Integer> settings;

    private List<Player> playerList;
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
                    Lobby.this.startGame();
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

    public List<Player> getPlayers () {
        return playerList;
    }

    /**
     * @param p player to add
     * @return if the player was successfully added
     */
    public boolean addPlayer (Player p) {
        // Add player to game if started
        if (isGameStarted()) {
            game.addPlayer(p);
        }

        playerList.add(p);
        return true;
    }

    /**
     * @param p player to remove
     * @return if the player was successfully removed
     */
    public boolean removePlayer (Player p) {
        // Remove player from game if game is started
        if (isGameStarted()) {
            game.removePlayer(p);
        }
        return playerList.remove(p);
    }

    private void startGame () { this.game = gameFactory.create(settings, this, getPlayers()); }

    public boolean isGameStarted () { return this.game != null; }

    public void setSetting (String settingName, int settingValue) {
        settings.put(settingName, settingValue);
    }
}
