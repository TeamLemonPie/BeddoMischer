package de.lemonpie.beddomischer.network.director.command.send;

import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;

public class LowerThirdFailedSendCommand extends CommandData
{
	public LowerThirdFailedSendCommand(int id)
	{
		super(Scope.DIRECTOR, CommandName.LOWER_THIRD_FAILED, id);
	}
}
