package adapters;

import usecases.submit_name.snInputBoundary;
import usecases.submit_name.snInputData;

// Layer: INTERFACE ADAPTERS

public class Controller {
    private final snInputBoundary snI;

    public Controller (snInputBoundary snI) {
        this.snI = snI;
    }

    public void connectToGame (String displayName) {
        snInputData data = new snInputData(displayName);
        snI.connectToGame(data);
    }
}
