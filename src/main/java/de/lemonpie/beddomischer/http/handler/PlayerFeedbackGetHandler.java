package de.lemonpie.beddomischer.http.handler;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerList;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerFeedbackGetHandler implements TemplateViewRoute {
	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
		PlayerList players = BeddoMischerMain.getPlayers();

		Map<String, Object> model = new HashMap<>();
		List<Map<String, Object>> playerModel = new ArrayList<>();

		for (Player player : players) {
			playerModel.add(player.toMap());
		}

		model.put("players", playerModel);
		return new ModelAndView(model, "PlayerFeedback.ftl");
	}
}