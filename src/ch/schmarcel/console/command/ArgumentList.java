package ch.schmarcel.console.command;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class ArgumentList {
    private HashMap<String, Argument> arguments;

    public HashMap<String, Argument> getArguments() {
        return arguments;
    }

    public ArgumentList() {
        this.arguments = new HashMap<>();
    }

    public void addArgument(String name, String value) {
        arguments.put(name, new Argument(name, value));
    }

    public Argument getArgument(String name) {
        return arguments.get(name);
    }

    public String getValue(String name) {
        if (!hasArgument(name))
            return null;
        return arguments.get(name).value;
    }

    public boolean hasArgument(String name) {
        return arguments.containsKey(name);
    }

    public void forEach(BiConsumer<String, Argument> action) {
        arguments.forEach(action);
    }
}
