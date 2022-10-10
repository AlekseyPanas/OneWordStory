package entities;

import entities.profanity.ProfanityFilter;
import entities.word_validity.ValidityChecker;

public class Word {
    private final String word;
    private final Player author;
    private final ValidityChecker validityChecker;

    /**
     * Takes care of TRIMMING the spaces around the provided word string before
     * it gets saved to the class
     */
    public Word (String word, Player author, ValidityChecker validityChecker) {
        this.word = word.trim();
        this.author = author;
        this.validityChecker = validityChecker;
    }

    public boolean isValid () { return validityChecker.isValid(word); }

    /**
     * @param p A profanity filter instance, cannot be null
     * @return the word string after it passes through the filter method
     */
    public String getWord(ProfanityFilter p) { return p.filterWord(word); }

    /**
     * @return the word string
     */
    public String getWord() { return word; }

    public Player getAuthor() {
        return author;
    }
}
