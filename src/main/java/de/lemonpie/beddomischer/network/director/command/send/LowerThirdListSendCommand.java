package de.lemonpie.beddomischer.network.director.command.send;

import com.google.gson.Gson;
import de.lemonpie.beddocommon.model.lowerthird.LowerThirdList;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class LowerThirdListSendCommand extends CommandData
{
	private static final Gson gson = new Gson();

	public LowerThirdListSendCommand()
	{
		super(Scope.DIRECTOR, CommandName.LOWER_THIRD_LIST, 0);

		final LowerThirdList lowerThirds = BeddoMischerMain.getLowerThirds();
		setValue(gson.toJsonTree(lowerThirds.getData()));
	}
}
