package ch.schmarcel.console.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParameterParser {
    private char parameterChar;

    public ParameterParser(char parameterChar) {
        this.parameterChar = parameterChar;
    }

    public ArgumentList parse(ArgumentList target, List<String> argStrings, ArgumentConstraints argumentConstraints) {
        List<ArgumentConstraint> orderedArgs = argumentConstraints.getOrderedArgs();

        for (int i = 0; !argStrings.isEmpty() && i < orderedArgs.size(); i++) {
            target.addArgument(orderedArgs.get(i).name, argStrings.get(0));
            argStrings.remove(0);
        }

        for (int i = argStrings.size() - 1; i >= 0; i--) {
            String argString = argStrings.get(i);
            if (argString.charAt(0) == parameterChar && argString.length() > 1) {
                target.addArgument(argString.substring(1), argStrings.size() > i+1 ? argStrings.get(i + 1) : "");
                argStrings.remove(i+1);
                argStrings.remove(i);
            }
        }

        return target;
    }

    public ArgumentList parse(List<String> argStrings, ArgumentConstraints argumentConstraints) {
        return parse(new ArgumentList(), argStrings, argumentConstraints);
    }

    public ArgumentList parse(ArgumentList target, String[] argStrings, ArgumentConstraints argumentConstraints) {
        return parse(target, new ArrayList<>(Arrays.asList(argStrings)), argumentConstraints);
    }

    public ArgumentList parse(String[] argStrings, ArgumentConstraints argumentConstraints) {
        return parse(new ArgumentList(), new ArrayList<>(Arrays.asList(argStrings)), argumentConstraints);
    }
}
