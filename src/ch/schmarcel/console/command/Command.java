package ch.schmarcel.console.command;

public class Command {
    private CommandEvent event;
    private ArgumentConstraints argumentConstraints;

    public ArgumentConstraints getArgumentConstraints() {
        return argumentConstraints;
    }

    public Command(ArgumentConstraints argumentConstraints, CommandEvent event) {
        this.event = event;
        this.argumentConstraints = argumentConstraints;
    }

    public Command(CommandEvent event) {
        this.event = event;
        this.argumentConstraints = new ArgumentConstraints();
    }

    public void execute(ArgumentList args) {
        event.event(args);
    }
}
