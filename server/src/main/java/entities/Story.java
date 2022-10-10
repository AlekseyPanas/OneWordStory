package entities;

import entities.factories.WordFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class Story {
    private ArrayList<Word> words;
    private final WordFactory wordFactory;

    public Story (WordFactory wordFactory) {
        words = new ArrayList<>();
        this.wordFactory = wordFactory;
    }

    public Story (WordFactory wordFactory, Word[] words) {
        // Calls other constructor
        this(wordFactory);
        // Adds given words
        this.words.addAll(Arrays.asList(words));
    }

    /**
     * Create a word via the provided factory instance and add it to the story
     * only if it is valid
     * @return did the word get added (aka is the word valid)
     */
    public boolean addWord (String word, Player author) {
        Word w = wordFactory.create(word, author);
        if (w.isValid()) {
            words.add(w);
            return true;
        } return false;
    };
}
