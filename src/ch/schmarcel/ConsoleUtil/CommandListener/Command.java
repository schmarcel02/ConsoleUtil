package ch.schmarcel.ConsoleUtil.CommandListener;

import java.util.Arrays;
import java.util.List;

public class Command {
    private CommandListener listener;
    private CommandEvent event;
    private List<String> requiredArgs, optionalArgs;

    public Command(String[] requiredArgs, String[] optionalArgs, CommandEvent event) {
        this.event = event;
        this.requiredArgs = Arrays.asList(requiredArgs);
        this.optionalArgs = Arrays.asList(optionalArgs);
    }

    public Command( List<String> requiredArgs, List<String> optionalArgs, CommandEvent event) {
        this.event = event;
        this.requiredArgs = requiredArgs;
        this.optionalArgs = optionalArgs;
    }

    public void execute(ArgumentList args) {
        for (String name : requiredArgs) {
            if (!args.hasArgument(name)) {
                listener.missingParameter(name);
                return;
            }
        }
        for (String name : args.getNamesAsArray()) {
            if (!(requiredArgs.contains(name) || optionalArgs.contains(name))) {
                listener.unknownParameter(name);
            }
        }
        event.event(args);
    }

    void setListener(CommandListener listener) {
        this.listener = listener;
    }
}
