package de.lemonpie.beddomischer.network.admin.command.read;

import com.google.gson.JsonObject;
import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;

/**
 * Configure Reader.
 * <code>key = reader id</code>
 * <code>value = {type, index}</code>
 */
public class ReaderReadCommand implements Command
{
	@Override
	public CommandName name()
	{
		return CommandName.READER;
	}

	@Override
	public void execute(CommandData command)
	{
		int readerId = command.getKey();

		// Remove old reader id
		removeOldReaderMapping(readerId);

		if(command.getValue() instanceof JsonObject)
		{
			JsonObject value = (JsonObject) command.getValue();
			int type = value.get("type").getAsInt();

			if(type == 0)
			{ // PLAYER
				int playerId = value.get("playerId").getAsInt();
				if(readerId != BeddoMischerMain.READER_NULL_ID)
				{
					BeddoMischerMain.getPlayers().getObject(playerId).ifPresent(player -> player.setReaderId(readerId));
				}
			}
			else if(type == 1)
			{ // BOARD
				int oldReaderId = value.get("oldReaderId").getAsInt();
				BeddoMischerMain.getBoard().removeReaderId(oldReaderId);
				if(readerId != BeddoMischerMain.READER_NULL_ID)
				{
					BeddoMischerMain.getBoard().addReaderId(readerId);
				}
			}
		}
	}

	private void removeOldReaderMapping(int readerId)
	{
		BeddoMischerMain.getPlayers().forEach(player -> {
			if(player.getReaderId() == readerId)
			{
				player.setReaderId(BeddoMischerMain.READER_NULL_ID);
			}
		});

		BeddoMischerMain.getBoard().removeReaderId(readerId);
	}
}
