package views;

import views.tcp_util.PlayerConnection;
import views.tcp_util.UserInputThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.ArrayList;

public class TcpView extends View {

    private final ServerSocket serverSocket;

    // Clients that have been connected and those that have terminated
    private final ArrayList<PlayerConnection> clients;
    private final ArrayList<PlayerConnection> closedClients;
    // Used locally to move clients to closedClients list
    private final ArrayList<PlayerConnection> moveToClosed;

    // Connection currently open waiting someone to join
    private PlayerConnection awaitingClient;

    // TODO: Remove this test code
    UserInputThread userInputThread;

    /**
     * @param port Port to open the server on
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
        userInputThread = new UserInputThread();
        (new Thread(userInputThread)).start();
    }

    @Override
    protected void start() {}

    @Override
    protected void runLoop() {
        // If the accept() stage has passed for this connection.
        if (awaitingClient.isConnectionComplete()) {
            if (!awaitingClient.isClosed()) {
                // Add current client to clients
                clients.add(awaitingClient);
            }

            // Open connection to accept next client
            awaitingClient = new PlayerConnection(serverSocket);
        }

        for (PlayerConnection client: clients) {
            // Sets closed client to be transferred
            if (client.isClosed()) {
                moveToClosed.add(client);
            }

            // TODO: Remove test code:
            else {
                if (!userInputThread.currentInput.equals("")) {
                    boolean hasLineSent = client.sendLine(userInputThread.currentInput);
                    System.out.println("Line " + (hasLineSent ? "has" : "has NOT") + " been sent");
                    userInputThread.currentInput = "";
                }
            } // TODO: --------------
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
