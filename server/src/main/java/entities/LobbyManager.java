package entities;

import entities.codegenerators.CodeGenerator;
import entities.lobbies.Lobby;

import java.util.ArrayList;

public class LobbyManager {
    private final ArrayList<Lobby> lobbies;
    private final CodeGenerator generator;

    /**
     * @param generator An instance of a class that implements CodeGenerator
     */
    public LobbyManager (CodeGenerator generator) {
        this.generator = generator;
        lobbies = new ArrayList<>();
    }

    /**
     * @return all lobbies
     */
    public Lobby[] getLobbies () {
        // TODO: Implement this
        return null;
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
