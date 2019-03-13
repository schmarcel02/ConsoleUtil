package ch.schmarcel.ConsoleUtil.CommandListener;

import java.util.HashMap;

public class ArgumentList {
    private HashMap<String, String> arguments;

    public ArgumentList(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }

    public void addArgument(String name, String value) {
        arguments.put(name, value);
    }

    public String getArgument(String name) {
        return arguments.get(name);
    }

    public boolean hasArgument(String name) {
        return arguments.containsKey(name);
    }

    public String[] getNamesAsArray() {
        return arguments.keySet().toArray(new String[0]);
    }

    public String[] getValuesAsArray() {
        return arguments.values().toArray(new String[0]);
    }
}
