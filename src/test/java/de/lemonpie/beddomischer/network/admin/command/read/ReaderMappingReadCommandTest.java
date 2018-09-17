package de.lemonpie.beddomischer.network.admin.command.read;

import com.google.gson.JsonObject;
import de.lemonpie.beddocommon.model.seat.Seat;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandExecutor;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class ReaderMappingReadCommandTest
{
	@Test
	public void setOneSeatReaderShouldReturnNormal()
	{
		Seat seat = BeddoMischerMain.getSeatList().add(new Seat());

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 0); // Player Type
		jsonObject.addProperty("seatId", seat.getId());
		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		assertThat(seat.getReaderId()).isEqualTo(3);
	}

	@Test
	public void setOneSeatReaderWithPreviousPlayerReaderShouldReturnNormal()
	{
		Seat seat = BeddoMischerMain.getSeatList().add(new Seat());
		seat.setReaderId(2);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 0); // Player Type
		jsonObject.addProperty("seatId", seat.getId());
		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		assertThat(seat.getReaderId()).isEqualTo(3);
	}

	@Test
	public void setOneSeatReaderWithPreviousBoardReaderShouldReturnNormal()
	{
		Seat seat = BeddoMischerMain.getSeatList().add(new Seat());
		BeddoMischerMain.getBoard().addReaderId((byte) 3);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 0); // Player Type
		jsonObject.addProperty("seatId", seat.getId());
		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		assertThat(BeddoMischerMain.getBoard().getReaderIds().contains(3)).isFalse();
		assertThat(seat.getReaderId()).isEqualTo(3);
	}

	@Test
	public void setOneBoardReaderShouldReturnNormal()
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 1); // Board Type
		jsonObject.addProperty("oldReaderId", -3);

		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		assertThat(BeddoMischerMain.getBoard().getReaderIds().contains((byte) 3)).isTrue();
	}

	@Test
	public void setOneBoardReaderWithPreviousSeatReaderShouldReturnNormal()
	{
		Seat seat = BeddoMischerMain.getSeatList().add(new Seat());
		seat.setReaderId(3);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", 1); // Board Type
		jsonObject.addProperty("oldReaderId", -3);

		CommandData data = new CommandData(Scope.ADMIN, CommandName.READER, 3, jsonObject); // Key = Reader ID

		CommandExecutor.getInstance().execute(data);

		assertThat(seat.getReaderId()).isEqualTo(-3);
		assertThat(BeddoMischerMain.getBoard().isBoardReader((byte) 3)).isTrue();
	}
}
