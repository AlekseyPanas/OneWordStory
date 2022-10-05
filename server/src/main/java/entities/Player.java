package entities;

public class Player {
    private final String displayName;
    private final int playerId;

    public Player (String displayName, int playerId) {
        this.displayName = displayName;
        this.playerId = playerId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getPlayerId() {
        return playerId;
    }
}
