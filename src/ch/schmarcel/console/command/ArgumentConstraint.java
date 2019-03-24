package ch.schmarcel.console.command;

public class ArgumentConstraint {
    public String name;
    public boolean required;
    public Type type;

    public ArgumentConstraint(String name, Type type, boolean required) {
        this.name = name;
        this.required = required;
        this.type = type;
    }

    public ArgumentConstraint(String name, boolean required) {
        this(name, Type.STRING, required);
    }

    public enum Type {
        BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, STRING, CHAR, BOOLEAN, EMPTY
    }
}
