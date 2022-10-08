package entities.games;

import entities.Player;
import entities.Story;
import entities.factories.StoryFactory;
import entities.factories.WordFactory;
import entities.lobbies.Lobby;
import entities.word_validity.NoSpacesValidityChecker;

public class GameRegular extends Game {

    private final Lobby lobby;

    private final Story story;
    private Player currentTurnPlayer;
    private int turnTimeLeftSeconds;

    public GameRegular (Lobby lobby) {
        super(lobby);

        // Creates words
        WordFactory wordFactory = new WordFactory(new NoSpacesValidityChecker());

        // Creates story with the given word factory
        story = new StoryFactory().create(wordFactory);

        // Sets lobby
        this.lobby = lobby;
    }

    public Lobby getLobby () {
        return lobby;
    }

    @Override
    public Story getStory() {
        return story;
    }

    @Override
    public boolean removePlayer(Player player) { return false; }

    @Override
    public void addPlayer(Player player) {

    }

    @Override
    public Player[] getPlayers() {
        return new Player[0];
    }

    @Override
    public void switchTurn() {

    }

    @Override
    public int getTurnTimeLeftSeconds() {
        return 0;
    }

    @Override
    public void addToTurnTimer(int seconds) {

    }

    @Override
    public Player getCurrentTurnPlayer() {
        return null;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
