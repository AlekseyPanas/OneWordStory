package entities;

// Layer: ENTITIES

public class Story {
    private String story;
    private int latestWordIndex;

    public Story(String story, int latestWordIndex) {
        this.story = story;
        this.latestWordIndex = latestWordIndex;
    }

    public int getLatestWordIndex() {
        return latestWordIndex;
    }

    public String getStory() {
        return story;
    }

    public void setLatestWordIndex(int latestWordIndex) {
        this.latestWordIndex = latestWordIndex;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
