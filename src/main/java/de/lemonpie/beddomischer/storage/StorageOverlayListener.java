package de.lemonpie.beddomischer.storage;

import de.lemonpie.beddomischer.listener.OverlayListener;
import de.lemonpie.beddomischer.model.Overlay;

public class StorageOverlayListener implements OverlayListener
{
	@Override
	public void hidePlayerDidChange(Overlay overlay)
	{
		OverlaySerializer.saveOverlay(overlay);
	}

	@Override
	public void hideBoardDidChange(Overlay overlay)
	{
		OverlaySerializer.saveOverlay(overlay);
	}
}