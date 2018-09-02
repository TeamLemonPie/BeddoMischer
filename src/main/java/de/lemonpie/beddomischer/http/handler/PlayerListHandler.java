package de.lemonpie.beddomischer.http.handler;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.player.Player;
import de.lemonpie.beddomischer.model.player.PlayerList;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerListHandler implements TemplateViewRoute
{
	@Override
	public ModelAndView handle(Request request, Response response)
	{
		PlayerList players = BeddoMischerMain.getPlayers();

		Map<String, Object> model = new HashMap<>();
		List<Map<String, Object>> playerModel = new ArrayList<>();

		for(Player player : players)
		{
			playerModel.add(player.toMap());
		}

		model.put("players", playerModel);
		model.put("hide", BeddoMischerMain.getOverlay().isHidePlayer());

		return new ModelAndView(model, "Player.ftl");
	}
}
