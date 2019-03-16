package ch.schmarcel.console.command;

import java.io.InputStream;
import java.util.*;

public class CommandListener implements Runnable{
    private Scanner scanner;
    private ParameterParser parser;

    private HashMap<String, Command> commands = new HashMap<>();

    private boolean running = false;

    public CommandListener(InputStream stream, char parameterChar) {
        scanner = new Scanner(stream);
        this.parser = new ParameterParser(parameterChar);
    }

    public void addCommand(String name, Command command) {
        commands.put(name.toLowerCase(), command);
    }

    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.setName("command");
        thread.start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            listen();
        }
    }

    private void listen() {
        try {
            String s = scanner.nextLine();
            if (s != null)
                interpret(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void interpret(String s) {
        String[] input = s.split(" ");

        if (input.length < 1)
            return;

        String commandName = input[0].toLowerCase();

        if (!commands.containsKey(commandName)) {
            unknownCommand(commandName);
            return;
        }

        Command c = commands.get(commandName);

        List<String> argStrings = new ArrayList<>(Arrays.asList(input));
        argStrings.remove(0);

        ArgumentList argumentList = parser.parse(argStrings, c.getArgumentConstraints());
        parser.parse(argumentList, argStrings, c.getArgumentConstraints());

        String missing = new InputValidator(c.getArgumentConstraints()).validate(argumentList);
        if (missing != null) {
            missingParameter(missing);
            return;
        }

        c.execute(argumentList);
    }

    public boolean isRunning() {
        return running;
    }

    public void unknownCommand(String command) {

    }

    public void missingParameter(String parameter) {

    }
}
