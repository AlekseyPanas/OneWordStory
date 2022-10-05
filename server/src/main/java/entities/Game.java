package entities;

public abstract class Game {
    private final Story story;
    private Player currentTurnPlayer;
    private int turnTimeLeftSeconds;

    public Game (StoryFactory storyFactory, WordFactory wordFactory) {
        story = storyFactory.create(wordFactory);
    }
}
