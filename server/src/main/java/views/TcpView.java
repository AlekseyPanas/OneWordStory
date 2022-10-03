package views;

import views.tcp_util.PlayerConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;

public class TcpView extends View {

    private ServerSocket serverSocket;
    // Clients that have been connected and those that have terminated
    private ArrayList<PlayerConnection> clients;
    private ArrayList<PlayerConnection> closedClients;
    // Used locally to move clients to closedClients list
    ArrayList<PlayerConnection> moveToClosed;
    // Connection currently open waiting someone to join
    private PlayerConnection awaitingClient;

    // TODO: Remove this test code
    private BufferedReader userInputReader;

    /**
     *
     * @param port
     * @throws IOException if opening the server socket fails for some reason
     */
    public TcpView (int port) throws IOException {
        serverSocket = new ServerSocket(port);

        clients = new ArrayList<>();
        closedClients = new ArrayList<>();
        moveToClosed = new ArrayList<>();
        // Opens connection to await a client
        awaitingClient = new PlayerConnection(serverSocket);

        // TODO: Remove this test code
        userInputReader = new BufferedReader(new InputStreamReader(System.in));
    }
    @Override
    protected void start() {}


    @Override
    protected void runLoop() {
        if (awaitingClient.isConnectionComplete() &&
                !awaitingClient.isClosed()) {

            // Add current client to clients
            clients.add(awaitingClient);
            // Open connection to accept next client
            awaitingClient = new PlayerConnection(serverSocket);
        }

        String userInput;
        try {
            userInput = userInputReader.readLine();
        } catch (IOException e) {
            userInput = "";
        }

        for (PlayerConnection client: clients) {
            // Sets closed client to be transferred
            if (client.isClosed()) {
                moveToClosed.add(client);
            } else {
                if (!userInput.equals("")) {
                    client.sendLine(userInput);
                }
            }
        }

        // Transfers closed clients and clears the list
        for (PlayerConnection client: moveToClosed) {
            clients.remove(client);
            closedClients.add(client);
        }
        moveToClosed.clear();
    }

    @Override
    protected void end() {

    }
}
