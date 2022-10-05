package drivers.views.consoleview.commands;

import drivers.views.ConsoleView;

public class QuitCommand extends Command {
    public QuitCommand (String commandName, String[] args) {
        super(commandName, args);
    }

    @Override
    public void runCommand(ConsoleView view) {
        view.stop();
    }
}
