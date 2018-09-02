package de.lemonpie.beddomischer.listener;

import de.lemonpie.beddomischer.model.Overlay;

public interface OverlayListener
{
	void hidePlayerDidChange(Overlay overlay);

	void hideBoardDidChange(Overlay overlay);
}
