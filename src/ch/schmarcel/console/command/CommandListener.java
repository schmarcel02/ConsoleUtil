package ch.schmarcel.console.command;

import java.io.InputStream;
import java.util.*;

public class CommandListener implements Runnable{
    private Scanner scanner;
    private ParameterParser parser;

    public CommandList commands;

    private boolean running = false;

    public CommandListener(InputStream stream, CommandList commands, char parameterChar) {
        this.commands = commands;
        scanner = new Scanner(stream);
        parser = new ParameterParser(parameterChar);
    }

    public CommandListener(InputStream stream, CommandList commands) {
        this(stream, commands, '-');
    }

    public CommandListener(InputStream stream, char parameterChar) {
        this(stream, new CommandList(), parameterChar);
    }

    public CommandListener(InputStream stream) {
        this(stream, new CommandList(), '-');
    }

    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.setName("commandListener");
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

        if (!commands.hasCommand(commandName)) {
            unknownCommand(commandName);
            return;
        }

        Command c = commands.get(commandName);

        List<String> argStrings = new ArrayList<>(Arrays.asList(input));
        argStrings.remove(0);

        ArgumentList argumentList = parser.parse(argStrings, c.getArgumentConstraints());
        parser.parse(argumentList, argStrings, c.getArgumentConstraints());

        String missing = new ArgumentValidator(c.getArgumentConstraints()).validate(argumentList);
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
