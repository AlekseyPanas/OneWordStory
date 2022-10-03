import adapters.Controller;
import adapters.Presenter;
import adapters.ViewModel;
import adapters.servers.TCPServer;
import drivers.views.ConsoleView;
import usecases.submit_name.snInteractor;

import java.io.IOException;

//Layer: ORCHESTRATOR

public class Main {
    public static void main (String[] args) throws IOException {
        ViewModel viewM = new ViewModel();

        // Injected into all use cases.
        Presenter pres = new Presenter(viewM);
        TCPServer serv = new TCPServer(viewM);

        // Instances of use case interactors.
        snInteractor snI = new snInteractor(pres, serv);

        // Controller created with interactors.
        Controller controller = new Controller(snI);

        ConsoleView view = new ConsoleView(viewM, controller);
        view.runView();
    }
}
