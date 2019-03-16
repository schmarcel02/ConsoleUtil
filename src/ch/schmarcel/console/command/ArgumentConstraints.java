package ch.schmarcel.console.command;

import java.util.ArrayList;
import java.util.List;

public class ArgumentConstraints {
    private List<ArgumentConstraint> args = new ArrayList<>();
    private List<ArgumentConstraint> orderedArgs = new ArrayList<>();

    public List<ArgumentConstraint> getArgs() {
        return args;
    }

    public List<ArgumentConstraint> getOrderedArgs() {
        return orderedArgs;
    }

    public void addArgument(String name, boolean required) {
        args.add(new ArgumentConstraint(name, required));
    }

    public void addRequiredArguments(String... names) {
        for (String name : names)
            addArgument(name, true);
    }

    public void addOptionalArguments(String... names) {
        for (String name : names)
            addArgument(name, false);
    }

    public void addOrderedArgument(String name) {
        orderedArgs.add(new ArgumentConstraint(name, true));
    }

    public void addOrderedArguments(String... names) {
        for (String name : names)
            addOrderedArgument(name);
    }
}
