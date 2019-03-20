package ch.schmarcel.console.command;

import java.util.HashMap;

public class CommandList {
    private HashMap<String, Command> commands;

    public CommandList() {
        commands = new HashMap<>();
    }

    public CommandList(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    public CommandList(CommandList commandList) {
        this.commands = commandList.commands;
    }

    public boolean hasCommand(String name) {
        return commands.containsKey(name);
    }

    public Command get(String name) {
        return commands.get(name);
    }

    public void add(String name, Command command) {
        commands.put(name.toLowerCase(), command);
    }

    public void remove(String name) {
        commands.remove(name);
    }
}
