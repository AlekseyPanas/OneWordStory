package entities.word_validity;

import entities.Word;

public interface ValidityChecker {
    /**
     * @param word a word object
     * @return if this word is valid and can be added to the story
     */
    boolean isValid (String word);
}
