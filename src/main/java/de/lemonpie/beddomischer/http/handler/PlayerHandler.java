package de.lemonpie.beddomischer.http.handler;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

public class PlayerHandler implements TemplateViewRoute {
	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("player_name", "Test");
		return new ModelAndView(model, "Player.ftl");
	}
}
