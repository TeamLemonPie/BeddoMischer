package de.lemonpie.beddomischer.network.director.command.read;

import com.google.gson.JsonObject;
import de.lemonpie.beddocommon.model.lowerthird.LowerThird;
import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class LowerThirdAddReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.LOWER_THIRD_ADD;
	}

	@Override
	public void execute(CommandData command)
	{
		final JsonObject payload = command.getValue().getAsJsonObject();
		String name = payload.getAsJsonPrimitive("name").getAsString();
		String data = payload.getAsJsonPrimitive("data").getAsString();

		final LowerThird lowerThird = BeddoMischerMain.getLowerThirds().add(name, data);


	}
}
