package entities.profanity;

import java.util.Arrays;

public class ListProfanityChecker implements ProfanityChecker {
    private final String[] profanity = {"fuck", "shit", "ass"};

    /**
     * Checks profanity from a hard coded list variable containing all kinds of foul terms
     * @param word word to check profane
     * @return if the word is profane
     */
    @Override
    public boolean isProfane(String word) {
        return Arrays.asList(profanity).contains(word);
    }
}
