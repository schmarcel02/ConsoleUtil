package ch.schmarcel.console.command;

import java.util.function.Consumer;

public class ArgumentValidator {
    private ArgumentConstraints argumentConstraints;
    private Consumer<String> onMissingArgument;

    public ArgumentValidator(ArgumentConstraints argumentConstraints, Consumer<String> onMissingArgument) {
        this.argumentConstraints = argumentConstraints;
        this.onMissingArgument = onMissingArgument;
    }

    public boolean validate(ArgumentList argumentList) {
        for (ArgumentConstraint argument : argumentConstraints.getOrderedArgs()) {
            if (!argumentList.hasArgument(argument.name)) {
                onMissingArgument.accept(argument.name);
                return false;
            }
        }

        for (ArgumentConstraint argument : argumentConstraints.getArgs()) {
            if (argument.required && !argumentList.hasArgument(argument.name)) {
                onMissingArgument.accept(argument.name);
                return false;
            }
        }

        return true;
    }
}
