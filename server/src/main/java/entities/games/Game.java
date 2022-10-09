package entities.games;

import entities.Player;
import entities.Story;
import entities.lobbies.Lobby;
import entities.lobbies.PublicLobby;

public abstract class Game {

    private final Lobby lobby;
    private final Story story;
    private final int secondsPerTurn;

    /**
     * @param lobby to which this game belongs
     * @param story an instance of the story that will be added to in this game
     * @param secondsPerTurn seconds per player's turn
     */
    public Game (Lobby lobby, Story story, int secondsPerTurn) {
        //this.lobby = lobby;
        this.story = story;
        this.secondsPerTurn = secondsPerTurn;
    }

    /**
     * @return The lobby to which this game belongs
     */
    public Lobby getLobby () { return this.lobby; };

    /**
     * @return the current story in this game
     */
    public Story getStory () { return this.story; };
    /**
     * Remove a player from the game without question
     * and adjust the game state accordingly. Return if the player
     * was in the game and was removed
     */
    public boolean removePlayer (Player player) {};

    /**
     * Add a new player to this game without question
     * and adjust the game state accordingly
     */
    public void addPlayer (Player player) {};

    /**
     * @return all the players in this game currently
     */
    public Player[] getPlayers () {};

    /**
     * Change the turn to the next player without question (guaranteed to happen)
     */
    public void switchTurn () {};

    /**
     * @return Number of seconds left in the current turn
     */
    public int getTurnTimeLeftSeconds () {};

    /**
     * Add time to the number of seconds left in the current turn
     * @param seconds number of seconds to add. Provide negative number to subtract
     */
    public void addToTurnTimer (int seconds) {};

    /**
     * @return the player whose turn it is
     */
    public Player getCurrentTurnPlayer () {};

    /**
     * @return if the game is over by some custom condition
     */
    public boolean isGameOver () {};
}
