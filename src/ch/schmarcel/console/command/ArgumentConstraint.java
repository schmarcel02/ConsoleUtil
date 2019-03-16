package ch.schmarcel.console.command;

public class ArgumentConstraint {
    public String name;
    public boolean required;

    public ArgumentConstraint(String name, boolean required) {
        this.name = name;
        this.required = required;
    }
}
