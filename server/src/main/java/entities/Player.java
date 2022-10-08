package entities;

public class Player {
    private final String displayName;
    private final int playerId;
    private boolean isReady;

    public Player (String displayName, int playerId) {
        this.displayName = displayName;
        this.playerId = playerId;
        isReady = false;
    }

    public void setReady () { isReady = true; }

    public void setUnready () { isReady = false; }

    public boolean isReady () { return isReady; }

    public String getDisplayName() {
        return displayName;
    }

    public int getPlayerId() {
        return playerId;
    }
}
