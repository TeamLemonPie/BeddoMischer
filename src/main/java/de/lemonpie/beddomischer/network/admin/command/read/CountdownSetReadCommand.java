package de.lemonpie.beddomischer.network.admin.command.read;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
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
