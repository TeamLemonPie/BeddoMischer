package de.lemonpie.beddomischer.http.handler;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.Player;
import de.lemonpie.beddomischer.model.PlayerList;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChipGetHandler implements TemplateViewRoute
{
	@Override
	public ModelAndView handle(Request request, Response response)
	{
		int id = Integer.valueOf(request.params(":id"));
		PlayerList players = BeddoMischerMain.getPlayers();

		Map<String, Object> model = new HashMap<>();

		Optional<Player> playerOptional = players.getPlayer(id);
		if(playerOptional.isPresent())
		{
			model.put("player", playerOptional.get().toMap());
			return new ModelAndView(model, "ChipItem.ftl");
		}
		return null;
	}
}
