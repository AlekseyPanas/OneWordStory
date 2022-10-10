package drivers.views.consoleview.commands;

import java.util.HashMap;

public class CommandFactory {
    /**
     * Maps command names in the console to corresponding command classes
     * @param input full user console input
     * @return command instance of corresponding command
     */
    public static Command createCommand (String input) {
        ArgumentParser parser = new ArgumentParser(input);
        String commandName = parser.getCommandName();
        String[] commandArgs = parser.getCommandArgs();

        return switch (commandName) {
            case "quit" -> new QuitCommand(commandName, commandArgs);
            default -> new InvalidCommand(commandName, commandArgs);
        };
    }
}
