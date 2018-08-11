package de.lemonpie.beddomischer.http.handler;

import de.lemonpie.beddomischer.model.CountdownHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class CountdownGetHandler implements TemplateViewRoute
{

	private boolean transparent;

	public CountdownGetHandler(boolean transparent)
	{
		this.transparent = transparent;
	}

	@Override
	public ModelAndView handle(Request request, Response response)
	{
		Map<String, Object> model = new HashMap<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

		long countdownEndTime = CountdownHandler.getInstance().getPauseEndTime();
		long currentTime = System.currentTimeMillis();

		long difference = countdownEndTime - currentTime;
		difference /= 1000;

		model.put("time", countdownEndTime);
		model.put("timeDifference", difference);
		model.put("endTime", dateFormat.format(countdownEndTime));

		if(!transparent)
		{
			return new ModelAndView(model, "Countdown.ftl");
		}
		else
		{
			return new ModelAndView(model, "CountdownTransparent.ftl");
		}
	}
}
