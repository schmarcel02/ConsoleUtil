package ch.schmarcel.console.command;

public class Test {
    public static void main(String[] args) {
        ArgumentConstraints progArgConstraints = new ArgumentConstraints();
        progArgConstraints.addOrderedArguments("ip", "port");

        ParameterParser parser = new ParameterParser('-');
        ArgumentList argumentList = parser.parse(args, progArgConstraints);

        System.out.println("Has IP: " + argumentList.hasArgument("ip") + " Has Port: " + argumentList.hasArgument("port"));

        InputValidator validator = new InputValidator(progArgConstraints);
        String missing = validator.validate(argumentList);
        if (missing != null) {
            System.out.println("missing argument " + missing);
        }

        CommandListener listener1 = new CommandListener(System.in, '/') {
            @Override
            public void unknownCommand(String command) {
                System.out.println("Unknown Command: " + command);
            }

            @Override
            public void missingParameter(String parameter) {
                System.out.println("Missing Parameter: " + parameter);
            }
        };

        ArgumentConstraints constraints = new ArgumentConstraints();
        constraints.addOrderedArguments("name", "age");
        constraints.addOptionalArguments("city", "gender");
        constraints.addRequiredArguments("lol");

        Command command = new Command(constraints, argList -> {
            String b1 = argList.hasArgument("city") || argList.hasArgument("gender") ? ", " : " and ";
            String b2 = argList.hasArgument("city") && argList.hasArgument("gender") ? ", " : " and ";

            String v1 = argList.hasArgument("gender") ? argList.getValue("gender").toLowerCase().equals("male") ? "superior male" : "inferior female" : "";

            String p1 = "My name is " + argList.getValue("name") + b1 + "i am " + argList.getValue("age") + " years old";
            String p2 = argList.hasArgument("city") ? b2 + "i live in " + argList.getValue("city") : "";
            String p3 = argList.hasArgument("gender") ? " and i am of the " + v1 + " gender" : "";

            System.out.println(p1 + p2 + p3);
        });

        listener1.addCommand("print", command);

        while (listener1.isRunning()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        listener1.start();
    }
}
