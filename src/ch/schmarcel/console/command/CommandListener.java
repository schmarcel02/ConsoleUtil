package ch.schmarcel.console.command;

import ch.schmarcel.console.parameter.ArgumentList;
import ch.schmarcel.console.parameter.ParameterParser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

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
        if (input.length >= 1 && commands.containsKey(input[0].toLowerCase())) {
            Command c = commands.get(input[0].toLowerCase());

            String[] argStrings = new String[input.length - 1];
            System.arraycopy(input, 1, argStrings, 0, argStrings.length);

            ArgumentList args = parser.parse(argStrings);

            for (String name : c.getRequiredArgs()) {
                if (!args.hasArgument(name)) {
                    missingParameter(name);
                    return;
                }
            }

            for (String name : args.getNamesAsArray()) {
                if (!(c.getRequiredArgs().contains(name) || c.getOptionalArgs().contains(name))) {
                    unknownParameter(name);
                }
            }

            c.execute(args);
        } else {
            unknownCommand(input[0].toLowerCase());
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void unknownCommand(String command) {

    }

    public void missingParameter(String parameter) {

    }

    public void unknownParameter(String parameter) {

    }
}
