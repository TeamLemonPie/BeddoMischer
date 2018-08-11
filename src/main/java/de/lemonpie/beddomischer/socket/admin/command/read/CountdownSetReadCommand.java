package de.lemonpie.beddomischer.socket.admin.command.read;

import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.server.Command;
import de.lemonpie.beddocommon.network.server.CommandData;
import de.lemonpie.beddomischer.model.CountdownHandler;

public class CountdownSetReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.PAUSE;
	}

	@Override
	public void execute(CommandData command)
	{
		int minutes = command.getValue().getAsInt();
		if(command.getKey() == 0)
		{
			CountdownHandler.getInstance().setPauseEndTime(System.currentTimeMillis() + 1000 * 60 * minutes);
		}
		else
		{
			CountdownHandler.getInstance().setPauseStartTime(System.currentTimeMillis() + 1000 * 60 * minutes);
		}
	}
}
