package de.lemonpie.beddomischer.socket;

public interface Command {
	String name();
	void execute(CommandData command);
}
