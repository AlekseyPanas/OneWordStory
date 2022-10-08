package entities.factories;

import entities.Player;
import entities.Word;
import entities.word_validity.ValidityChecker;

public class WordFactory {
    private final ValidityChecker v;

    public WordFactory (ValidityChecker v) {
        this.v = v;
    }

    public Word create (String word, Player author) {
        return new Word(word, author, this.v);
    }
}
