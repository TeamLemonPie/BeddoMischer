package de.lemonpie.beddomischer.network.director.command.send;

import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;

public class LowerThirdDeleteSendCommand extends CommandData
{
	public LowerThirdDeleteSendCommand(int id)
	{
		super(Scope.DIRECTOR, CommandName.LOWER_THIRD_DELETE, id);
	}
}
