package drivers.views.consoleview.commands;

public class ArgumentParser {

    private final String commandName;
    private final String[] commandArgs;

    public ArgumentParser (String input) {
        String[] args = parseArgs(input);

        this.commandName = args[0];
        this.commandArgs = computeRest(args);
    }

    /**
     * @param input a string with a command and potential arguments, such as
     *              "send -m 'hello'"
     * @return input split by spaces into individual arguments, e.g {"send", "-m", "'hello'"}
     */
    private String[] parseArgs (String input) {
        String[] args = input.trim().toLowerCase().split(" ");
        for (int i = 0; i < args.length; i++) { args[i] = args[i].trim(); }
        return args;
    }

    /**
     * @return array of args excluding the command name at the start
     */
    private String[] computeRest (String[] args) {
        String[] rest = new String[args.length - 1];
        System.arraycopy(args, 1, rest, 0, args.length - 1);
        return rest;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getCommandArgs() {
        return commandArgs;
    }
}
