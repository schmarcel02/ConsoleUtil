package ch.schmarcel.console.command;

public class Test {
    public static void main(String[] args) {
        CommandListener listener1 = new CommandListener(System.in, '/') {
            @Override
            public void unknownCommand(String command) {
                System.out.println("Unknown Command: " + command);
            }

            @Override
            public void unknownParameter(String parameter) {
                System.out.println("Unknown Parameter: " + parameter);
            }

            @Override
            public void missingParameter(String parameter) {
                System.out.println("Missing Parameter: " + parameter);
            }
        };
        listener1.addCommand("start", new Command(new String[] {"p", "t"}, new String[] {"e"},
                cArgs -> {
                    System.out.println("Executed 'Start' with Parameters: p=" + cArgs.getString("p") +
                    " t=" + cArgs.getString("t") + (cArgs.hasArgument("e")?" e="+cArgs.getString("e"):""));
                }));
        listener1.start();
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
