package ch.schmarcel.ConsoleUtil.ParameterCompiler;

import java.util.HashMap;
import java.util.List;

public class ParameterCompiler {
    private HashMap<String, String> parameters;

    public ParameterCompiler(String[] args, char parameterChar) {
        parameters = new ParameterParser(parameterChar).parse(args);
    }

    public ParameterCompiler(List<String> args, char parameterChar) {
        parameters = new ParameterParser(parameterChar).parse(args);
    }

    public String getString(String key, String defaultValue) {
        return hasParameter(key) ? parameters.get(key) : defaultValue;
    }

    public byte getByte(String key, byte defaultValue) {
        return hasParameter(key) ? Byte.valueOf(parameters.get(key)) : defaultValue;
    }

    public short getShort(String key, short defaultValue) {
        return hasParameter(key) ? Short.valueOf(parameters.get(key)) : defaultValue;
    }

    public int getInt(String key, int defaultValue) {
        return hasParameter(key) ? Integer.valueOf(parameters.get(key)) : defaultValue;
    }

    public long getLong(String key, long defaultValue) {
        return hasParameter(key) ? Long.valueOf(parameters.get(key)) : defaultValue;
    }

    public float getFloat(String key, float defaultValue) {
        return hasParameter(key) ? Float.valueOf(parameters.get(key)) : defaultValue;
    }

    public double getDouble(String key, double defaultValue) {
        return hasParameter(key) ? Double.valueOf(parameters.get(key)) : defaultValue;
    }

    public boolean geBoolean(String key, boolean defaultValue) {
        return hasParameter(key) ? Boolean.valueOf(parameters.get(key)) : defaultValue;
    }

    public boolean hasParameter(String key) {
        return parameters.containsKey(key);
    }
}
