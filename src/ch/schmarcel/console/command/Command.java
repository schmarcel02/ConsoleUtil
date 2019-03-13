package ch.schmarcel.console.command;

import ch.schmarcel.console.parameter.ArgumentList;

import java.util.Arrays;
import java.util.List;

public class Command {
    private CommandEvent event;
    private List<String> requiredArgs, optionalArgs;

    public List<String> getRequiredArgs() {
        return requiredArgs;
    }

    public List<String> getOptionalArgs() {
        return optionalArgs;
    }

    public Command(String[] requiredArgs, String[] optionalArgs, CommandEvent event) {
        this.event = event;
        this.requiredArgs = Arrays.asList(requiredArgs);
        this.optionalArgs = Arrays.asList(optionalArgs);
    }

    public Command(List<String> requiredArgs, List<String> optionalArgs, CommandEvent event) {
        this.event = event;
        this.requiredArgs = requiredArgs;
        this.optionalArgs = optionalArgs;
    }

    public void execute(ArgumentList args) {
        event.event(args);
    }
}
