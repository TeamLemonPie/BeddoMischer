package de.lemonpie.beddomischer.http.handler;

import de.lemonpie.beddocommon.model.seat.Seat;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.Board;
import de.lemonpie.beddomischer.model.CountdownHandler;
import de.lemonpie.beddomischer.model.player.Player;
import de.lemonpie.beddomischer.model.player.PlayerList;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerFeedbackGetHandler implements TemplateViewRoute
{
	@Override
	public ModelAndView handle(Request request, Response response)
	{
		PlayerList players = BeddoMischerMain.getPlayers();
		Board board = BeddoMischerMain.getBoard();

		players.getData().sort(Comparator.comparingInt(Player::getId));

		Map<String, Object> model = new HashMap<>();
		List<Map<String, Object>> playerModel = new ArrayList<>();

		for(Player player : players)
		{
			playerModel.add(player.toMap());
		}

		long countdownEndTime = CountdownHandler.getInstance().getPauseStartTime();
		model.put("time", countdownEndTime);
		model.put("small_blind", board.getSmallBlind());
		model.put("big_blind", board.getBigBlind());
		model.put("ante", board.getAnte());
		model.put("players", playerModel);

		List<Integer> playerIds = BeddoMischerMain.getSeatList().getData().stream().sorted(Comparator.comparingInt(Seat::getId)).map(seat -> seat.getPlayerId() - 1).collect(Collectors.toList());
		model.put("seats", playerIds);

		return new ModelAndView(model, "PlayerFeedback.ftl");
	}
}
