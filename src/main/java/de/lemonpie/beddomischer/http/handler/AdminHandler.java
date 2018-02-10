package de.lemonpie.beddomischer.http.handler;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;

public class AdminHandler implements TemplateViewRoute {

	public AdminHandler() {
	}

	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
		return new ModelAndView(new HashMap<>(), "Admin.ftl");
	}
}