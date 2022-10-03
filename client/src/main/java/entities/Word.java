package entities;

// Layer: ENTITIES

public class Word {
    private String word;
    private int index;

    public Word (String word, int index) {
        this.word = word.trim();
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getWord() {
        return word;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return If the word is a single word
     * */
    public boolean isWordValid() {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                return false;
            }
        } return true;
    }
}
