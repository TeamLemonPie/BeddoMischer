package de.lemonpie.beddomischer.network.director.command.read;

import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class LowerThirdDeleteReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.LOWER_THIRD_DELETE;
	}

	@Override
	public void execute(CommandData command)
	{
		int id = command.getKey();
		BeddoMischerMain.getLowerThirds().getObject(id).ifPresent(lowerThird ->
				BeddoMischerMain.getLowerThirds().remove(lowerThird));
		// Send back to Director will be implemented in listener
	}
}
