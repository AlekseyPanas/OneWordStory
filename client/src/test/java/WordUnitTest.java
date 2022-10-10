import org.junit.jupiter.api.Test;
import entities.Word;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordUnitTest {

    @Test
    void testIsWordValid_trailingAndLeadingSpaces () {
        String text = "  Hello ";
        Word word = new Word(text, 0);

        assertTrue(word.isWordValid());
    }

    @Test
    void testIsWordValid_singleWordNoSpaces () {
        String text = "Word";
        Word word = new Word(text, 0);

        assertTrue(word.isWordValid());
    }

    @Test
    void testIsWordValid_spacesBetween () {
        String text = " This is not a word";
        Word word = new Word(text, 0);

        assertFalse(word.isWordValid());
    }
}
