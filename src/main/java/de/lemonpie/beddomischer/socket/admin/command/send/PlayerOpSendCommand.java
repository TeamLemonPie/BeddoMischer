package de.lemonpie.beddomischer.socket.admin.command.send;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandData;

public class PlayerOpSendCommand extends CommandData
{

	public PlayerOpSendCommand(int playerId)
	{
		super(Scope.ADMIN, CommandName.PLAYER_OP, playerId, new JsonPrimitive("add"));
	}
}
