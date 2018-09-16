package de.lemonpie.beddomischer.network.admin.command.read;

import com.google.gson.JsonObject;
import de.lemonpie.beddocommon.network.Command;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddomischer.BeddoMischerMain;

import static de.lemonpie.beddocommon.model.seat.Seat.READER_NULL_ID;

/**
 * Configure Reader.
 * <code>key = reader id</code>
 * <code>value = {type, index}</code>
 */
public class ReaderMappingReadCommand implements Command
{
	public enum ReaderType
	{
		SEAT, BOARD
	}

	@Override
	public CommandName name()
	{
		return CommandName.READER;
	}

	@Override
	public void execute(CommandData command)
	{
		byte readerId = (byte) command.getKey();

		// Remove old reader id
		removeOldReaderMapping(readerId);

		if(command.getValue() instanceof JsonObject)
		{
			JsonObject value = (JsonObject) command.getValue();
			int type = value.get("type").getAsInt();

			if(type == ReaderType.SEAT.ordinal())
			{ // SEAT
				int seatId = value.get("seatId").getAsInt();
				if(readerId != READER_NULL_ID)
				{
					BeddoMischerMain.getSeatList().getObject(seatId).ifPresent(seat -> seat.setReaderId(readerId));
				}
			}
			else if(type == ReaderType.BOARD.ordinal())
			{ // BOARD
				byte oldReaderId = value.get("oldReaderId").getAsByte();
				BeddoMischerMain.getBoard().removeReaderId(oldReaderId);
				if(readerId != READER_NULL_ID)
				{
					BeddoMischerMain.getBoard().addReaderId(readerId);
				}
			}
		}
	}

	private void removeOldReaderMapping(byte readerId)
	{
		BeddoMischerMain.getSeatList().getSeatByReader(readerId).ifPresent(seat -> {
			seat.setReaderId(READER_NULL_ID);
		});

		BeddoMischerMain.getBoard().removeReaderId(readerId);
	}
}
