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

    public ArgumentConstraints addArgument(String name, Type type, boolean required) {
        args.add(new ArgumentConstraint(name, type, required));
        return this;
    }

    public ArgumentConstraints addOrderedArgument(String name, Type type) {
        orderedArgs.add(new ArgumentConstraint(name, type, true));
        return this;
    }

    public ArgumentConstraints addArgument(String name, boolean required) {
        args.add(new ArgumentConstraint(name, required));
        return this;
    }

    public ArgumentConstraints addOrderedArgument(String name) {
        orderedArgs.add(new ArgumentConstraint(name, true));
        return this;
    }
}
