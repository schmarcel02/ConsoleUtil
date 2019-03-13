package ch.schmarcel.console.parameter;

import java.util.HashMap;
import java.util.List;

public class ParameterParser {
    private char parameterChar;

    public ParameterParser(char parameterChar) {
        this.parameterChar = parameterChar;
    }

    public ArgumentList parse(String[] args) {
        HashMap<String, String> arguments = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == parameterChar) {
                arguments.put(args[i].substring(1), args.length > i+1 ? args[i + 1] : "");
            }
        }
        return new ArgumentList(arguments);
    }

    public ArgumentList parse(String string) {
        return parse(string.split(" "));
    }

    public ArgumentList parse(List<String> args) {
        return parse(args.toArray(new String[0]));
    }
}
