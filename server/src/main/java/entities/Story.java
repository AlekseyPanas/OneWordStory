package entities;

import java.util.ArrayList;
import java.util.Arrays;

public class Story {
    ArrayList<Word> words;
    WordFactory wordFactory;

    public Story (WordFactory wordFactory) {
        this.wordFactory = wordFactory;
        words = new ArrayList<>();
    }

    public Story (WordFactory wordFactory, Word[] words) {
        this.wordFactory = wordFactory;
        this.words = new ArrayList<>();
        this.words.addAll(Arrays.asList(words));
    }

    /**
     * Add a word to the story
     * @return success
     */
    public boolean addWord (String word, Player author) {
        words.add(wordFactory.create(word, author));
        return true;
    };
}
