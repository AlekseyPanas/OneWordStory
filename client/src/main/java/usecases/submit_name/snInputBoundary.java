package usecases.submit_name;

public interface snInputBoundary {
    /**
     * <p>Call server to connect to the game with the display name provided in inputData</p>
     * @param inputData provides displayName
     */
    void connectToGame (snInputData inputData);
}
