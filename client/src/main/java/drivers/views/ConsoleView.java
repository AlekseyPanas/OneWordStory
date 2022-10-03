package drivers.views;

import adapters.Controller;
import adapters.ViewModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Layer: DRIVERS & FRAMEWORKS

public class ConsoleView {
    private final ViewModel viewM;
    private final Controller controller;

    public ConsoleView(ViewModel viewM, Controller controller) {
        this.viewM = viewM;
        this.controller = controller;
    }

    public void runView () throws IOException {
        // Reads console input.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // Debug statement to confirm loop is running.
            System.out.println("View is running");

            // Await a line of input and print it.
            String input = reader.readLine();
            System.out.println(input);

            // If input was non-empty and started with q, quit program.
            if (input.length() > 0 && input.trim().toLowerCase().charAt(0) == 'q') {
                break;
            }
        }

        terminateProgram();
    }

    /**
     * <p> Performs proper termination of all threads </p>
     */
    private void terminateProgram () {
        // TODO: Implement this method
    }
}
