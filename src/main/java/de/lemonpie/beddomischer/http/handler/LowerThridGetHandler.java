package de.lemonpie.beddomischer.http.handler;

import spark.Request;
import spark.Response;
import spark.Route;

public class LowerThridGetHandler implements Route
{
	@Override
	public Object handle(Request request, Response response) throws Exception
	{
		final String idVal = request.params(":id");
		if(idVal != null)
		{
			int id = Integer.valueOf(idVal);

		}
		return null;
	}
}
