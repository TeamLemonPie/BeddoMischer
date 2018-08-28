package de.lemonpie.beddomischer.network.admin.command.send;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;

public class PlayerHighlightSendCommand extends CommandData
{
	public PlayerHighlightSendCommand(int playerId, boolean highlight)
	{
		super(Scope.ADMIN, CommandName.PLAYER_HIGHLIGHT, playerId, new JsonPrimitive(highlight));
	}
}
