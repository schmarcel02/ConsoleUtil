package ch.schmarcel.console.command;

import ch.schmarcel.console.parameter.ArgumentList;

public interface CommandEvent {
    void event(ArgumentList args);
}
