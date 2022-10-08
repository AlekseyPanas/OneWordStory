package entities.word_validity;

import java.util.Arrays;

/**
 * Simple validity checker to see if the given word text has any spaces in between
 * and that the length doesn't exceed a certain constant value
 */
public class NoSpacesValidityChecker implements ValidityChecker {
    public static final int MAX_WORD_LENGTH = 15;

    @Override
    public boolean isValid(String word) {
        return word.contains(" ");
    }
}
