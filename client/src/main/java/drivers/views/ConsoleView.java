package drivers.views;

import adapters.Controller;
import adapters.ViewModel;
import drivers.views.consoleview.commands.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.locks.ReentrantLock;

// Layer: DRIVERS & FRAMEWORKS

public class ConsoleView implements View, Runnable {
    private final ViewModel viewM;
    private final Controller controller;
    private final BufferedReader reader;
    private volatile String currentInput;
    private ReentrantLock currentInputLock;
    private boolean stopped;

    public ConsoleView(ViewModel viewM, Controller controller) {
        this.viewM = viewM;
        this.controller = controller;

        // Reads console input.
        reader = new BufferedReader(new InputStreamReader(System.in));
        currentInput = "";
        // Starts reader thread
        (new Thread(this)).start();
    }

    /**
     * Stop the GUI while loop
     */
    public void stop () {
        stopped = true;
    }

    /**
     * Run the input thread
     */
    @Override
    public void run() {
        String line;
        while (!stopped) {
            try {
                line = reader.readLine();
                currentInput = line;
            } catch (IOException ignored) {
                System.out.println("Readline be throwin errors fam");
            }
        }
        System.out.println("Reader thread ended");
    }

    @Override
    public void runView () {
        String inp;

        while (!stopped) {

            // Captures input, runs command, clears input
            inp = currentInput;
            if (inp.length() > 0) {
                CommandFactory.createCommand(inp).runCommand(this);
                currentInput = "";
            }

            // Sleeps for a bit
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}

            // Display stuff
            for (int i = 0; i < 100; i++) { System.out.println(); }
            System.out.println("Hello");
        }

        teardown();
    }

    /**
     * <p> Performs proper termination of all threads </p>
     */
    private void teardown () {
        System.out.println("Closing resources...");
        try {
            System.in.close();
            reader.close();
            // TODO: Close other resources such as server
            System.out.println("Closed all resources");
        } catch (IOException e) {
            System.out.println("Failed to close all resources properly!");
        }
        System.out.println("Teardown finished");
    }
}
