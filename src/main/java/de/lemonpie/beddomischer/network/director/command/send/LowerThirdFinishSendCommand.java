package de.lemonpie.beddomischer.network.director.command.send;

import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;

public class LowerThirdFinishSendCommand extends CommandData
{
	public LowerThirdFinishSendCommand(int id)
	{
		super(Scope.DIRECTOR, CommandName.LOWER_THIRD_FINISH, id);
	}
}
