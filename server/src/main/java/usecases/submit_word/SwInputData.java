package usecases.submit_word;

public class SwInputData {
    private String word;

    private int playerId;

    public SwInputData (String word, int playerId) {
        this.word = word;
        this.playerId = playerId;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getPlayerId() {return this.playerId;}

    public void setPlayerId(int playerId) {this.playerId = playerId;}
}
