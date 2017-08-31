package de.lemonpie.beddomischer.socket;

import de.lemonpie.beddomischer.CommandName;

public interface Command {
    CommandName name();

    void execute(CommandData command);
}
