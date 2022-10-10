package views;

import adapters.ViewModel;
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

    // Takes System.in input from another thread.
    UserInputThread userInputThread;

    /**
     * @param port Port to open the server on
     * @throws IOException if opening the server socket fails for some reason
     */
    public TcpView (int port, ViewModel viewM) throws IOException {
        super(viewM);

        serverSocket = new ServerSocket(port);

        clients = new ArrayList<>();
        closedClients = new ArrayList<>();
        moveToClosed = new ArrayList<>();

        // Opens connection to await a client
        awaitingClient = new PlayerConnection(serverSocket);

        userInputThread = new UserInputThread();
        userInputThread.startReader();
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

        // Reads user input
        if (userInputThread.hasInput()) {
            String input = userInputThread.getCurrentInput().trim().toLowerCase();
            System.out.println(input);
            // 'q' command stops the server
            if (input.equals("q")) {
                stop();
            }
        }

        for (PlayerConnection client: clients) {
            // Sets closed client to be transferred
            if (client.isClosed()) {
                moveToClosed.add(client);
            }

            else {}
        }

        // Transfers closed clients and clears the list
        for (PlayerConnection closedClient: moveToClosed) {
            clients.remove(closedClient);
            closedClients.add(closedClient);
        }
        moveToClosed.clear();
    }

    /**
     * In charge of closing all resources.
     */
    @Override
    protected void end() {
        System.out.println("Closing program...");
        try {
            serverSocket.close();
            for (PlayerConnection conn : clients) {
                if (!conn.closeConnection()) {
                    throw new IOException();
                }
            }
            userInputThread.closeReader();
        } catch (IOException e) {
            System.out.println("Some resources failed to close");
        } finally {
            System.out.println("Termination finished");
        }
    }
}
