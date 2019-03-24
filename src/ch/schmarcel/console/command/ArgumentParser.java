package ch.schmarcel.console.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgumentParser {
    private static final char ESCAPE_CHAR = '\\', QUOTATION_CHAR = '"';
    private char identifier;

    public ArgumentParser(char identifier) {
        this.identifier = identifier;
    }

    public ArgumentList parse(ArgumentList target, List<String> argStrings, ArgumentConstraints argumentConstraints) {
        List<ArgumentConstraint> orderedConstraints = argumentConstraints.getOrderedArgs();
        List<String> args = combineQuotedArgs(argStrings);

        int index = 0;

        for (int i = 0; i < args.size() && i < orderedConstraints.size(); i++) {
            target.addArgument(orderedConstraints.get(i).name, args.get(i));
            index++;
        }

        for (int i = index; i < args.size(); i++) {
            String argString = args.get(i);
            if (argString.length() > 1 && argString.charAt(0) == identifier)
                target.addArgument(argString.substring(1), args.size() > i+1  ? args.get(i + 1) : "");
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

    private List<String> combineQuotedArgs(List<String> argStrings) {
        List<String> ret = new ArrayList<>();

        for (int i = 0; i < argStrings.size(); i++) {
            String first = argStrings.get(i);
            if (first.charAt(0) != QUOTATION_CHAR) {
                ret.add(first);
                continue;
            }

            StringBuilder combined = new StringBuilder();
            boolean closed = false;
            for (int k = i; k < argStrings.size(); k++) {
                String s = argStrings.get(k);
                combined.append(s).append(" ");
                if (s.charAt(s.length()-1) == QUOTATION_CHAR && !charIsEscaped(s, s.length()-1)) {
                    closed = true;
                    i = k;
                    break;
                }
            }

            if (closed)
                ret.add(combined.substring(0, combined.length()-1));
            else
                ret.add(first);
        }

        for (int i = 0; i < ret.size(); i++) {
            String s = ret.get(i);
            s = stripQuotationChars(s);
            s = stripEscapeChars(s);
            ret.set(i, s);
        }

        return ret;
    }

    private String stripEscapeChars(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ESCAPE_CHAR && !(i > 0 && string.charAt(i-1) == ESCAPE_CHAR))
                continue;
            sb.append(string.charAt(i));
        }
        return sb.toString();
    }

    private String stripQuotationChars(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != QUOTATION_CHAR || charIsEscaped(string, i))
                sb.append(string.charAt(i));
        }
        return sb.toString();
    }

    private boolean charIsEscaped(String s, int pos) {
        if (s.length() <= pos)
            throw new IndexOutOfBoundsException("" + pos);

        return pos >= 1 && s.charAt(pos-1) == ESCAPE_CHAR && !(pos >= 2 && s.charAt(pos-2) == ESCAPE_CHAR);
    }
}
