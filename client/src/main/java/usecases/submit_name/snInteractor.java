package usecases.submit_name;

import usecases.ServerResponse;

public class snInteractor implements snInputBoundary {
    private final snOutputBoundary presenter;
    private final snServerGateway serv;

    public snInteractor (snOutputBoundary presenter, snServerGateway serv) {
        this.presenter = presenter;
        this.serv = serv;
    }

    @Override
    public void connectToGame (snInputData inputData) {
        ServerResponse res = serv.setName(inputData.getDisplayName());
        snOutputData out = new snOutputData(res);
        presenter.setNameReact(out);
    }
}
