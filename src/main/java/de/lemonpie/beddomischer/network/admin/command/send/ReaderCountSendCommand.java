package de.lemonpie.beddomischer.network.admin.command.send;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandData;

public class ReaderCountSendCommand extends CommandData
{

	public enum Type
	{
		ADD, REMOVE
	}

	public ReaderCountSendCommand(Type type)
	{
		super(Scope.ADMIN, CommandName.READER_COUNT, -1, new JsonPrimitive(type.ordinal()));
	}
}
