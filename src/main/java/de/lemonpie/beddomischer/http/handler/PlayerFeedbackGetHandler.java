package de.lemonpie.beddomischer.http.handler;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerList;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.*;

public class PlayerFeedbackGetHandler implements TemplateViewRoute {
	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
		PlayerList players = BeddoMischerMain.getPlayers();

		players.getData().sort(Comparator.comparingInt(Player::getId));

		Map<String, Object> model = new HashMap<>();
		List<Map<String, Object>> playerModel = new ArrayList<>();

		for (Player player : players) {
			playerModel.add(player.toMap());
		}

		long countdownEndTime = BeddoMischerMain.getPauseStartTime();
		model.put("time", countdownEndTime);
		model.put("players", playerModel);
		return new ModelAndView(model, "PlayerFeedback.ftl");
	}
}
