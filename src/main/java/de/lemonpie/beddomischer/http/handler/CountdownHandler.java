package de.lemonpie.beddomischer.http.handler;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

public class CountdownHandler implements TemplateViewRoute {

	private boolean transparent;

	public CountdownHandler(boolean transparent) {
		this.transparent = transparent;
	}

	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("time", "19:24");

		if (!transparent) {
			return new ModelAndView(model, "Countdown.ftl");
		} else {
			return new ModelAndView(model, "CountdownTransparent.ftl");
		}
	}
}
