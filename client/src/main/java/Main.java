import adapters.Controller;
import adapters.Presenter;
import adapters.ViewModel;
import adapters.servers.TCPServer;
import drivers.views.ConsoleView;
import drivers.views.View;
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

        View view = new ConsoleView(viewM, controller);
        view.runView();
//
//        ViewModel viewM = new ViewModel();
//        TCPServer serv = new TCPServer(viewM);
//
//        ConnectReturnData res = serv.connect();
//        System.out.println(res.getMessage());
//
//        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
//
//        while (true) {
//            String dat = inputReader.readLine();
//
//            if (dat.length() > 0) {
//                // 'q' to quit the app
//                if (dat.charAt(0) == 'q') {
//                    break;
//                }
//                // 's' to close the connection
//                else if (dat.charAt(0) == 's') {
//                    serv.closeConnection();
//                }
//                // 'c' to re-connect
//                else if (dat.charAt(0) == 'c') {
//                    serv.connect();
//                } else {
//                    boolean resp = serv.sendLine(dat);
//                    System.out.println("Send Success? " + resp);
//                }
//            }
//        }
//
//        serv.closeConnection();
    }
}
