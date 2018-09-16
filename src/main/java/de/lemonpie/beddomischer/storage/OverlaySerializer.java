package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.model.Overlay;
import de.tobias.logger.Logger;

import java.sql.SQLException;
import java.util.List;

public class OverlaySerializer
{
	private static final String fileName = BeddoMischerMain.BASE_PATH + "/Overlay.json";

	public static synchronized void saveOverlay(Overlay overlay)
	{
		try
		{
			BeddoMischerMain.getOverlayDao().update(overlay);
		}
		catch(SQLException e)
		{
			Logger.error(e);
		}
	}

	public static Overlay loadOverlay()
	{
		try
		{
			final List<Overlay> overlays = BeddoMischerMain.getOverlayDao().queryForAll();
			if(overlays.isEmpty())
			{
				return createOverlay();
			}
			return overlays.get(0);
		}
		catch(SQLException e)
		{
			return createOverlay();
		}
	}


	private static Overlay createOverlay()
	{
		Overlay overlay = new Overlay();
		try
		{
			BeddoMischerMain.getOverlayDao().create(overlay);
			return overlay;
		}
		catch(SQLException e1)
		{
			return null;
		}
	}
}
