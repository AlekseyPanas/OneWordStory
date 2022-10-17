package usecases.submit_word;

import entities.LobbyManager;

public class SwInteractor implements SwInputBoundary{

    private SwOutputBoundary presenter;

    private LobbyManager lobbyManager;

    public SwInteractor (SwOutputBoundary presenter, LobbyManager lobbyManager) {
        this.presenter = presenter;
        this.lobbyManager = lobbyManager;
    }

    @Override
    public void submitWord (SwInputData inputData) {



    }
}
