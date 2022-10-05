package entities;

import entities.Player;
import entities.Word;

public class WordFactory {
    public Word create (String word, Player author) {
        return new Word(word, author);
    }
}
