package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.CommandName;
import de.lemonpie.beddomischer.socket.Command;
import de.lemonpie.beddomischer.socket.CommandData;

public class AnteReadCommand implements Command
{

	@Override
	public CommandName name()
	{
		return CommandName.ANTE;
	}

	@Override
	public void execute(CommandData command)
	{
		BeddoMischerMain.getBoard().setAnte(command.getValue().getAsInt());
	}
}
