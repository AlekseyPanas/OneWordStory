package entities.profanity;

public interface ProfanityFilter {
    /**
     * Checks if the given word is profane and returns the censored
     * version of that word. E.g "dumb" -> "****", "shit" -> "crap"
     *
     * @param word A string word that may or may not be profanity
     * @return the new version of the word, whether it be the same word if
     * the word was not profane, or an alternate 'safer' version based
     * on filter's implementation
     */
    String filterWord (String word);
}
