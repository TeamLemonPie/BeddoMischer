package de.lemonpie.beddomischer.network.admin.command.read;

import com.google.gson.JsonPrimitive;
import de.lemonpie.beddocommon.model.seat.Seat;
import de.lemonpie.beddocommon.network.CommandData;
import de.lemonpie.beddocommon.network.CommandName;
import de.lemonpie.beddocommon.network.Scope;
import de.lemonpie.beddocommon.network.server.CommandExecutor;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.model.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class SeatReadCommandTest
{
	private Seat seatA;
	private Seat seatB;

	private Player playerA;
	private Player playerB;

	@Before
	public void brefore()
	{
		seatA = BeddoMischerMain.getSeatList().add(new Seat(0));
		seatB = BeddoMischerMain.getSeatList().add(new Seat(1));

		playerA = BeddoMischerMain.getPlayers().add(0);
		playerB = BeddoMischerMain.getPlayers().add(1);

		seatB.setPlayerId(playerB.getId());
	}

	@Test
	public void seatForPlayerChangeShouldReturnNormal()
	{
		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.SEAT_PLAYER_ID, seatA.getId(), new JsonPrimitive(playerA.getId()));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(seatA.getPlayerId()).isEqualTo(playerA.getId());
	}

	@Test
	public void seatForPlayerWithOldPlayerChangeShouldReturnNormal()
	{
		CommandData commandData = new CommandData(Scope.ADMIN, CommandName.SEAT_PLAYER_ID, seatB.getId(), new JsonPrimitive(playerA.getId()));
		CommandExecutor.getInstance().execute(commandData);

		assertThat(seatB.getPlayerId()).isEqualTo(playerA.getId());
	}
}
