package entities.profanity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TempProfanityFilter implements ProfanityFilter {
    private final List<String> profanity = Arrays.asList("darn", "dumb", "stupid");

    /**
     * Checks profanity from a hard coded list variable containing all kinds of foul terms.
     * @param word word to filter for profanity
     * @return asterisks equal to the length of the given word if the word is profane,
     * otherwise return the same word
     */
    @Override
    public String filterWord(String word) {
        return profanity.contains(word) ?
        "*".repeat(word.length()) : word;
    }
}
