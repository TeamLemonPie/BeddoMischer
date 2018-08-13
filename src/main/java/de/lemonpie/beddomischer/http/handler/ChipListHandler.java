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

public class ChipListHandler implements TemplateViewRoute
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

		playerModel.sort((p1, p2) -> {
			final Integer c2 = (Integer) p2.get("chips");
			final Integer c1 = (Integer) p1.get("chips");

			final Long t2 = (Long) p2.get("timestampDeactivate");
			final Long t1 = (Long) p1.get("timestampDeactivate");

			if(c2 == 0 && c1 == 0)
			{
				return Long.compare(t2, t1);
			}
			else
			{
				return Integer.compare(c2, c1);
			}
		});

		model.put("players", playerModel);
		return new ModelAndView(model, "Chips.ftl");
	}
}
