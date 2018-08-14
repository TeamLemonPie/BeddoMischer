package de.lemonpie.beddomischer.network.admin.command.send;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;

public class PlayerOpSendCommand extends CommandData
{

	public PlayerOpSendCommand(int playerId)
	{
		super(Scope.ADMIN, CommandName.PLAYER_OP, playerId, new JsonPrimitive("add"));
	}
}
