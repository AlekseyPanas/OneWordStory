package entities;

import java.util.HashMap;

public class Lobby {
    private String lobbyId;
    private boolean isPublic;

    private HashMap<String, Integer> settings;
    private Player[] playerList;
    private Game game;
    private GameFactory gameFactory;

    public Lobby () {
        
    }
}
