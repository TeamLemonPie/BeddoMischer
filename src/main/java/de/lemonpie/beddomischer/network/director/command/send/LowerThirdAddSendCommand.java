package de.lemonpie.beddomischer.network.director.command.send;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.model.lowerthird.LowerThird;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;

public class LowerThirdAddSendCommand extends CommandData
{
	public LowerThirdAddSendCommand(LowerThird lowerThird)
	{
		super(Scope.DIRECTOR, CommandName.LOWER_THIRD_ADD, lowerThird.getId(), new JsonPrimitive(lowerThird.getName()));
	}
}
