package de.lemonpie.beddomischer.http.handler;

import de.lemonpie.beddocommon.model.card.Card;
import de.lemonpie.beddomischer.BeddoMischerMain;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardHandler implements TemplateViewRoute
{

	@Override
	public ModelAndView handle(Request request, Response response)
	{
		Map<String, Object> model = new HashMap<>();
		List<String> cards = new ArrayList<>();

		for(Card card : BeddoMischerMain.getBoard().getCards())
		{
			cards.add(card != null ? card.getName() : "back");
		}
		model.put("board", cards);
		return new ModelAndView(model, "Board.ftl");
	}
}
