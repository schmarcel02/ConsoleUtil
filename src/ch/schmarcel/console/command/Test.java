package ch.schmarcel.console.command;

public class Test {
    private static CommandListener listener;

    public static void main(String[] args) {
        ArgumentConstraints progArgConstraints = new ArgumentConstraints()
                .addOrderedArgument("ip")
                .addOrderedArgument("port", ArgumentConstraint.Type.INTEGER);

        ArgumentParser parser = new ArgumentParser('-');
        ArgumentList argumentList = parser.parse(args, progArgConstraints);

        ArgumentValidator validator = new ArgumentValidator(progArgConstraints, name -> System.out.println("Missing argument: " + name));

        if (!validator.validate(argumentList))
            return;

        System.out.println("IP: " + argumentList.getArgument("ip"));
        System.out.println("Port: " + argumentList.getArgument("port"));
        if (argumentList.hasArgument("name"))
            System.out.println("Name: " + argumentList.getArgument("name"));

        listener = new CommandListener(System.in, createCommands(), '/') {
            @Override
            public void unknownCommand(String command) {
                System.out.println("Unknown Command: " + command);
            }

            @Override
            public void missingParameter(String parameter) {
                System.out.println("Missing Parameter: " + parameter);
            }
        };

        listener.start();
    }

    private static CommandList createCommands() {
        CommandList commandList = new CommandList();

        ArgumentConstraints constraints = new ArgumentConstraints()
                .addOrderedArgument("name")
                .addOrderedArgument("age", ArgumentConstraint.Type.INTEGER)
                .addArgument("city", false)
                .addArgument("gender", false);

        Command command = new Command(constraints, argList -> {
            String b1 = argList.hasArgument("city") || argList.hasArgument("gender") ? ", " : " and ";
            String b2 = argList.hasArgument("city") && argList.hasArgument("gender") ? ", " : " and ";

            String p1 = "My name is " + argList.getArgument("name") + b1 + "i am " + argList.getArgument("age") + " years old";
            String p2 = argList.hasArgument("city") ? b2 + "i live in " + argList.getArgument("city") : "";
            String p3 = argList.hasArgument("gender") ? " and i am of the " + argList.getArgument("gender") + " gender" : "";

            System.out.println(p1 + p2 + p3);
        });

        Command stopCommand = new Command(new ArgumentConstraints(), argList -> {
            listener.stop();
        });

        commandList.add("print", command);
        commandList.add("stop", stopCommand);

        return commandList;
    }
}
