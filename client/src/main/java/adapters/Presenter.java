package adapters;

import usecases.submit_name.snOutputBoundary;
import usecases.submit_name.snOutputData;

// Layer: INTERFACE ADAPTERS

public class Presenter implements snOutputBoundary {

    private final ViewModel viewM;

    public Presenter (ViewModel viewM) {
        this.viewM = viewM;
    }

    @Override
    public void setNameReact(snOutputData outputData) {
        // TODO: change something in the view model
    }
}
