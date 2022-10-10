package views.tcp_util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputThread implements Runnable {
    private final BufferedReader userInputReader;
    // Prevents caching so that when the input thread changes this var, java knows to check
    private volatile String currentInput;
    private boolean isStarted;
    private boolean isClosed;

    public UserInputThread () {
        userInputReader = new BufferedReader(new InputStreamReader(System.in));
        currentInput = "";
    }

    /**
     * Starts reader thread
     */
    public void startReader () {
        if (!isStarted) {
            (new Thread(this)).start();
        }
    }

    /**
     *
     * @return if the reader thread was started. Will return true even if the
     * reader is currently closed but was started at some point
     */
    public boolean isStarted () {
        return isStarted;
    }

    /**
     * @return if the reader has been closed
     */
    public boolean isClosed () {
        return isClosed;
    }

    public void closeReader () throws IOException {
        // Closes System.in stream so the readLine method in the other thread doesn't block
        // the subsequent close() call.
        System.in.close(); // This stroke of genius was the product of an hour of pain
        userInputReader.close();
        isClosed = true;
        System.out.println("Closed user input reader");
    }

    /**
     * When a user enters a line into System.in, that is recorded as the
     * current input. Any new input will override the old. Gets the current input
     * and clears the input string
     * @return the current input
     */
    public String getCurrentInput() {
        String t = currentInput;
        currentInput = "";
        return t;
    }

    /**
     * @return if the current input is non-empty
     */
    public boolean hasInput () { return currentInput.length() > 0; }

    @Override
    public void run() {
        String line;
        while (true) {
            try {
                line = userInputReader.readLine();
                currentInput = line;
                System.out.println("New input set: " + currentInput);
            } catch (IOException e) {
                System.out.println("User input thread terminated");
                break;
            }
        }
    }
}
