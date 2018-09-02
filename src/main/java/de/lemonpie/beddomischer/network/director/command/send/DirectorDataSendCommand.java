package de.lemonpie.beddomischer.network.director.command.send;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddomischer.BeddoMischerMain;

public class DirectorDataSendCommand extends CommandData
{

	public DirectorDataSendCommand()
	{
		super(Scope.DIRECTOR, CommandName.DATA, 0);
		Gson gson = new Gson();
		final JsonElement value = gson.toJsonTree(BeddoMischerMain.getOverlay());
		setValue(value);
	}
}
