package entities;

import entities.profanity.ProfanityChecker;

public class Word {
    private final String word;
    private final Player author;

    public Word (String word, Player author) {
        this.word = word;
        this.author = author;
    }

    public String getWord() {
        return word;
    }

    public Player getAuthor() {
        return author;
    }
}
