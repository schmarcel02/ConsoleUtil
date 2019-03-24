package ch.schmarcel.console.command;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ArgumentValidator {
    private ArgumentConstraints argumentConstraints;
    private Consumer<String> onMissingArgument;
    private BiConsumer<String, Type> onWrongType;

    public ArgumentValidator(ArgumentConstraints argumentConstraints, Consumer<String> onMissingArgument, BiConsumer<String, Type> onWrongType) {
        this.argumentConstraints = argumentConstraints;
        this.onMissingArgument = onMissingArgument;
        this.onWrongType = onWrongType;
    }

    public ArgumentValidator(ArgumentConstraints argumentConstraints, Consumer<String> onMissingArgument) {
        this(argumentConstraints, onMissingArgument, (name, type) -> System.out.println("Argument '" + name + "' is not of type: " + type.name()));
    }

    public ArgumentValidator(ArgumentConstraints argumentConstraints, BiConsumer<String, Type> onWrongType) {
        this(argumentConstraints, (name) -> System.out.println("Missing argument: " + name), onWrongType);
    }

    public ArgumentValidator(ArgumentConstraints argumentConstraints) {
        this(argumentConstraints, (name) -> System.out.println("Missing argument: " + name), (name, type) -> System.out.println("Argument '" + name + "' is not of type: " + type.name()));
    }

    public boolean validate(ArgumentList argumentList) {
        for (ArgumentConstraint argument : argumentConstraints.getOrderedArgs()) {
            if (!argumentList.hasArgument(argument.name)) {
                onMissingArgument.accept(argument.name);
                return false;
            }

            if (!validateType(argumentList.getString(argument.name), argument)) {
                onWrongType.accept(argument.name, argument.type);
                return false;
            }
        }

        for (ArgumentConstraint argument : argumentConstraints.getArgs()) {
            if (argument.required && !argumentList.hasArgument(argument.name)) {
                onMissingArgument.accept(argument.name);
                return false;
            }

            if (argumentList.hasArgument(argument.name) && !validateType(argumentList.getString(argument.name), argument)) {
                onWrongType.accept(argument.name, argument.type);
                return false;
            }
        }

        return true;
    }

    private boolean validateType(String value, ArgumentConstraint constraint) {
        StringValidator validator = new StringValidator();

        switch (constraint.type) {
            case BYTE:
                return validator.isByte(value);
            case SHORT:
                return validator.isShort(value);
            case INTEGER:
                return validator.isInteger(value);
            case LONG:
                return validator.isLong(value);
            case FLOAT:
                return validator.isFloat(value);
            case DOUBLE:
                return validator.isDouble(value);
            case STRING:
                return true;
            case CHAR:
                return validator.isChar(value);
            case BOOLEAN:
                return validator.isBoolean(value);
            case EMPTY:
                return true;
            default:
                return false;
        }
    }
}
