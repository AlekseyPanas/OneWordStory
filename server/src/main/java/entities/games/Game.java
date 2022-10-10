package entities.games;

import entities.Player;
import entities.Story;
import entities.lobbies.Lobby;
import entities.lobbies.PublicLobby;

public abstract class Game {

    private final Lobby lobby;
    private final int secondsPerTurn;

    /**
     * @param lobby to which this game belongs
     * @param secondsPerTurn seconds per player's turn
     * @param initialPlayers an array of AT LEAST 2 PLAYERS
     */
    public Game (Lobby lobby,  int secondsPerTurn, Player[] initialPlayers) {
        // TODO: Throw error if 2 players not given

        for (Player p : initialPlayers) {
            this.addPlayer(p);
        }

        this.lobby = lobby;
        this.secondsPerTurn = secondsPerTurn;
    }

    /**
     * @return The lobby to which this game belongs
     */
    public Lobby getLobby () { return this.lobby; };

    /**
     * @return the current story in this game
     */
    public abstract Story getStory ();

    /**
     * Remove a player from the game without question
     * and adjust the game state accordingly. Return if the player
     * was in the game and was removed
     */
    public abstract boolean removePlayer (Player player);

    /**
     * Add a new player to this game without question
     * and adjust the game state accordingly
     */
    public abstract void addPlayer (Player player);

    /**
     * Change the turn to the next player without question (guaranteed to happen)
     */
    public abstract void switchTurn ();

    /**
     * @return Number of seconds left in the current turn
     */
    public abstract int getTurnTimeLeftSeconds ();

    /**
     * @return the player whose turn it is
     */
    public abstract Player getCurrentTurnPlayer ();

    /**
     * @return if the game is over by some custom condition
     */
    public abstract boolean isGameOver ();
}
