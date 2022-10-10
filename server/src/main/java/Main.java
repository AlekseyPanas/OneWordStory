import adapters.ViewModel;
import entities.LobbyManager;
import entities.codegenerators.NumericCodeGenerator;
import entities.factories.LobbyFactory;
import views.LocalConsoleTestView;
import views.TcpView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // View mode
        ViewModel viewM = new ViewModel();

        // Lobby manager with numeric lobby codes of length 5
        LobbyManager manager = new LobbyManager(new NumericCodeGenerator(5), new LobbyFactory());

        LocalConsoleTestView view = new LocalConsoleTestView(viewM);
        //TcpView server = new TcpView(42069);
        //server.runView();
    }
}
