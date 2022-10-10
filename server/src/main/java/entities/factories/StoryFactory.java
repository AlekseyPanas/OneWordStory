package entities.factories;

import entities.Story;
import entities.Word;

public class StoryFactory {
    public Story create (WordFactory wordFactory) {
        return new Story(wordFactory);
    }

    public Story create (WordFactory wordFactory, Word[] words) { return new Story(wordFactory, words); }
}
