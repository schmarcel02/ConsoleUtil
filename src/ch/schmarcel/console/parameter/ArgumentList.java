package ch.schmarcel.console.parameter;

import java.util.HashMap;

public class ArgumentList {
    private HashMap<String, String> arguments;

    public ArgumentList(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }

    public void addArgument(String name, String value) {
        arguments.put(name, value);
    }

    public String getString(String key, String defaultValue) {
        return hasArgument(key) ? arguments.get(key) : defaultValue;
    }

    public byte getByte(String key, byte defaultValue) {
        return hasArgument(key) ? Byte.valueOf(arguments.get(key)) : defaultValue;
    }

    public short getShort(String key, short defaultValue) {
        return hasArgument(key) ? Short.valueOf(arguments.get(key)) : defaultValue;
    }

    public int getInt(String key, int defaultValue) {
        return hasArgument(key) ? Integer.valueOf(arguments.get(key)) : defaultValue;
    }

    public long getLong(String key, long defaultValue) {
        return hasArgument(key) ? Long.valueOf(arguments.get(key)) : defaultValue;
    }

    public float getFloat(String key, float defaultValue) {
        return hasArgument(key) ? Float.valueOf(arguments.get(key)) : defaultValue;
    }

    public double getDouble(String key, double defaultValue) {
        return hasArgument(key) ? Double.valueOf(arguments.get(key)) : defaultValue;
    }

    public boolean geBoolean(String key, boolean defaultValue) {
        return hasArgument(key) ? Boolean.valueOf(arguments.get(key)) : defaultValue;
    }

    public String getString(String key) {
        return arguments.get(key);
    }

    public byte getByte(String key) {
        return Byte.valueOf(arguments.get(key));
    }

    public short getShort(String key) {
        return Short.valueOf(arguments.get(key));
    }

    public int getInt(String key) {
        return Integer.valueOf(arguments.get(key));
    }

    public long getLong(String key) {
        return Long.valueOf(arguments.get(key));
    }

    public float getFloat(String key) {
        return Float.valueOf(arguments.get(key));
    }

    public double getDouble(String key) {
        return Double.valueOf(arguments.get(key));
    }

    public boolean geBoolean(String key) {
        return Boolean.valueOf(arguments.get(key));
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
