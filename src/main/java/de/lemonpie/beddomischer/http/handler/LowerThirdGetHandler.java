package de.lemonpie.beddomischer.http.handler;

import de.lemonpie.beddocommon.model.lowerthird.LowerThird;
import de.lemonpie.beddomischer.BeddoMischerMain;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Optional;

public class LowerThirdGetHandler implements Route
{
	@Override
	public Object handle(Request request, Response response) throws Exception
	{
		final String idVal = request.params(":id");
		if(idVal != null)
		{
			int id = Integer.valueOf(idVal);
			final Optional<LowerThird> object = BeddoMischerMain.getLowerThirds().getObject(id);
			if(object.isPresent())
			{
				return object.get().getData();
			}
		}
		return null;
	}
}
