package ch.schmarcel.console.command;

public class InputValidator {
    private ArgumentConstraints argumentConstraints;

    public InputValidator(ArgumentConstraints argumentConstraints) {
        this.argumentConstraints = argumentConstraints;
    }

    public String validate(ArgumentList argumentList) {
        for (ArgumentConstraint argument : argumentConstraints.getOrderedArgs()) {
            if (!argumentList.hasArgument(argument.name))
                return argument.name;
        }

        for (ArgumentConstraint argument : argumentConstraints.getArgs()) {
            if (argument.required && !argumentList.hasArgument(argument.name))
                return argument.name;
        }
        return null;
    }
}
