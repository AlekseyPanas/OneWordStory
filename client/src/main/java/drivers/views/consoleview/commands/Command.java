package drivers.views.consoleview.commands;

import drivers.views.ConsoleView;

public abstract class Command {
    private final String[] args;
    private final String commandName;

    public Command (String commandName, String[] args) {
        this.commandName = commandName;
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public String getCommandName() {
        return commandName;
    }

    /**
     * Run the command given some arguments and access to the parent view object
     * @param args a list of string arguments
     */
    public abstract void runCommand (ConsoleView view);
}
