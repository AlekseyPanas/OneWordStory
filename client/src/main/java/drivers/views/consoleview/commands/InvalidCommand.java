package drivers.views.consoleview.commands;

import drivers.views.ConsoleView;

public class InvalidCommand extends Command {
    public InvalidCommand (String commandName, String[] args) {
        super(commandName, args);
    }

    @Override
    public void runCommand(ConsoleView view) {
        System.out.println(getCommandName() + " is and invalid command!");
    }
}
