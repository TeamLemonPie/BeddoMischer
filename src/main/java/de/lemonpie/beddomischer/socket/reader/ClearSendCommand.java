package de.lemonpie.beddomischer.socket.reader;

import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandData;

public class ClearSendCommand extends CommandData
{
	public ClearSendCommand(int key)
	{
		super(Scope.READER, CommandName.CLEAR, key, null);
	}
}
