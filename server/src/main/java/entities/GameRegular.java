package entities;

public class GameRegular extends Game {
    public GameRegular() {
        super(new StoryFactory(), new WordFactory());
    }
}
