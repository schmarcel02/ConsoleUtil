package ch.schmarcel.ConsoleUtil.CommandListener;

import ch.schmarcel.ConsoleUtil.ParameterCompiler.ParameterParser;

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
        command.setListener(this);
        commands.put(name.toLowerCase(), command);
    }

    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.setName("CommandListener");
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
                execute(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute(String s) {
        String[] input = s.split(" ");
        if (input.length >= 1 && commands.containsKey(input[0].toLowerCase())) {
            Command c = commands.get(input[0].toLowerCase());
            ArgumentList arguments = new ArgumentList(parser.parse(input));
            c.execute(arguments);
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
