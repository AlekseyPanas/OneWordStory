package views.tcp_util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputThread implements Runnable {
    private final BufferedReader userInputReader;
    public String currentInput;

    public UserInputThread () {
        userInputReader = new BufferedReader(new InputStreamReader(System.in));
        currentInput = "";
    }

    public void closeReader () throws IOException {
        userInputReader.close();
        System.out.println("Closed user input reader");
    }

    @Override
    public void run() {
        while (true) {
            try {
                currentInput = userInputReader.readLine();
                if (currentInput.length() > 0 && currentInput.charAt(0) == 'q') {
                    closeReader();
                }
                System.out.println("New input set: " + currentInput);
            } catch (IOException e) {
                System.out.println("User input thread terminated");
                break;
            }
        }
    }
}
