package ch.schmarcel.console.command;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class ArgumentList {
    private HashMap<String, Argument> arguments;

    public ArgumentList() {
        this.arguments = new HashMap<>();
    }

    public void addArgument(String name, String value) {
        arguments.put(name, new Argument(name, value));
    }

    public void removeArgument(String name) {
        arguments.remove(name);
    }

    public String getString(String name) {
        return arguments.get(name).value;
    }

    public byte getByte(String name) {
        return Byte.parseByte(getString(name));
    }

    public short getShort(String name) {
        return Short.parseShort(getString(name));
    }

    public int getInteger(String name) {
        return Integer.parseInt(getString(name));
    }

    public long getLong(String name) {
        return Long.parseLong(getString(name));
    }

    public float getFloat(String name) {
        return Float.parseFloat(getString(name));
    }

    public double getDouble(String name) {
        return Double.parseDouble(getString(name));
    }

    public boolean getBoolean(String name) {
        return Boolean.parseBoolean(getString(name));
    }

    public char getChar(String name) {
        return getString(name).charAt(0);
    }

    public boolean hasArgument(String name) {
        return arguments.containsKey(name);
    }

    public void forEach(BiConsumer<String, Argument> action) {
        arguments.forEach(action);
    }
}
