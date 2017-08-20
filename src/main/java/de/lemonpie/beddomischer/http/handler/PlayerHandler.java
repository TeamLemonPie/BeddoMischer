package de.lemonpie.beddomischer.http.handler;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayerHandler implements TemplateViewRoute {
	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("player_name", "Test");
		model.put("player_list", Arrays.asList("Peter", "Hans"));
		return new ModelAndView(model, "Player.ftl");
	}
}
