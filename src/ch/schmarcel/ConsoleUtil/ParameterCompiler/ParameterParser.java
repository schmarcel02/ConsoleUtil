package ch.schmarcel.ConsoleUtil.ParameterCompiler;

import java.util.HashMap;
import java.util.List;

public class ParameterParser {
    private char parameterChar;

    public ParameterParser(char parameterChar) {
        this.parameterChar = parameterChar;
    }

    public HashMap<String, String> parse(String[] args) {
        HashMap<String, String> parameters = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == parameterChar) {
                parameters.put(args[i].substring(1), args.length > i+1 ? args[i + 1] : "");
            }
        }
        return parameters;
    }

    public HashMap<String, String> parse(String string) {
        return parse(string.split(" "));
    }

    public HashMap<String, String> parse(List<String> args) {
        return parse(args.toArray(new String[0]));
    }
}
