package entities.games;

import entities.Player;
import entities.Story;
import entities.factories.StoryFactory;
import entities.factories.WordFactory;
import entities.lobbies.Lobby;
import entities.word_validity.NoSpacesValidityChecker;

import java.util.LinkedList;
import java.util.Queue;

public class GameRegular extends Game {

    private final Story story;
    private Player currentTurnPlayer;
    private int turnTimeLeftSeconds;

    private final Queue<Player> playerQueue = new LinkedList<>();

    public GameRegular (Lobby lobby, int secondsPerTurn, Player[] initialPlayers) {
        super(lobby, secondsPerTurn, initialPlayers);

        // Creates words
        WordFactory wordFactory = new WordFactory(new NoSpacesValidityChecker());

        // Creates story with the given word factory
        story = new StoryFactory().create(wordFactory);

        // Sets first player in queue to be their turn
        currentTurnPlayer = playerQueue.peek();

        // TODO: Initiate internal timer to run passive changes in the game (such as turn timer countdown)
    }

    @Override
    public Story getStory() { return story; }

    @Override
    public boolean removePlayer(Player player) { return playerQueue.remove(player); }

    @Override
    public void addPlayer(Player player) { playerQueue.add(player); }

    @Override
    public void switchTurn() {
        // Move player to back of queue
        playerQueue.add(playerQueue.remove());
        // Set current player to be the new first player in the queue
        currentTurnPlayer = playerQueue.peek();
    }

    @Override
    public int getTurnTimeLeftSeconds() {
        return this.turnTimeLeftSeconds;
    }

    @Override
    public Player getCurrentTurnPlayer() {
        return this.currentTurnPlayer;
    }

    @Override
    public boolean isGameOver() {
        // TODO: Fix this to end game once less than 2 players
        return false;
    }
}
