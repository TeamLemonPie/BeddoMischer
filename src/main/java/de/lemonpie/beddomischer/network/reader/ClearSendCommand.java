package de.lemonpie.beddomischer.network.reader;

import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;

public class ClearSendCommand extends CommandData
{
	public ClearSendCommand(int key)
	{
		super(Scope.READER, CommandName.CLEAR, key, null);
	}
}
