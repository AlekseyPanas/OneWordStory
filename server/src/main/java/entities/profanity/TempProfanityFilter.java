package entities.profanity;

import java.util.Arrays;

public class TempProfanityFilter implements ProfanityFilter {
    private final String[] profanity = {"darn", "dumb", "stupid"};

    /**
     * Checks profanity from a hard coded list variable containing all kinds of foul terms.
     * @param word word to filter for profanity
     * @return asterisks equal to the length of the given word if the word is profane,
     * otherwise return the same word
     */
    @Override
    public String filterWord(String word) {
        return Arrays.asList(profanity).contains(word) ?
        "*".repeat(word.length()) : word;
    }
}
