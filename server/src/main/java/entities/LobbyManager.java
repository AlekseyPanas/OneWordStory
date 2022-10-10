package entities;

import entities.codegenerators.CodeGenerator;
import entities.factories.LobbyFactory;
import entities.lobbies.Lobby;
import entities.lobbies.PrivateLobby;
import entities.lobbies.PublicLobby;

import java.util.ArrayList;

public class LobbyManager {
    private final ArrayList<Lobby> lobbies;
    private final CodeGenerator generator;
    private final LobbyFactory lobbyFactory;

    /**
     * @param generator An instance of a class that implements CodeGenerator
     */
    public LobbyManager (CodeGenerator generator, LobbyFactory lobbyFactory) {
        this.generator = generator;
        this.lobbyFactory = new LobbyFactory();
        lobbies = new ArrayList<>();
    }

    /**
     * Create and add a new lobby to the list
     * @param isPublic is lobby public
     * @return the lobby code
     */
    public String createLobby (boolean isPublic) {
        Lobby newLobby = lobbyFactory.create(isPublic, generateCode());
        lobbies.add(newLobby);
        return newLobby.getLobbyCode();
    }

    // TODO: Move this to a separate POOL implementation
    /**
     * Block thread until a lobby is available to join, once player
     * is added to the lobby, then return. Return false if something
     * goes wrong
     */
    public boolean sortPlayer (Player player) {
        // TODO: implement this
        return true;
    }

    /**
     * @return all lobbies
     */
    public Lobby[] getLobbies () {
        Lobby[] l = new Lobby[lobbies.size()];
        return lobbies.toArray(l);
    }

    /**
     * @return all lobbies with isPublic = true
     */
    public Lobby[] getPublicLobbies () {
        // TODO: Implement this
        return null;
    }

    /**
     * @return all lobbies with isPublic = false
     */
    public Lobby[] getPrivateLobbies () {
        // TODO: Implement this
        return null;
    }

    /**
     * @param lobbyCode String code of the lobby you are looking for
     * @return the lobby with the provided code, or null if no such lobby is found
     */
    public Lobby getLobbyByCode (String lobbyCode) {
        // TODO: Implement this
        return null;
    }

    /**
     * @return a unique code which has not been generated previously
     */
    public String generateCode () {
        return generator.generateUniqueCode();
    }
}
