package de.lemonpie.beddomischer.network.director;

import de.lemonpie.beddocommon.model.ObservableListListener;
import de.lemonpie.beddocommon.model.lowerthird.LowerThird;
import de.lemonpie.beddomischer.BeddoMischerMain;
import de.lemonpie.beddomischer.network.director.command.send.LowerThirdAddSendCommand;

public class NetworkLowerThirdListListener implements ObservableListListener<LowerThird>
{
	@Override
	public void addObjectToList(LowerThird lowerThird)
	{
		BeddoMischerMain.getDirectorServerSocket().writeAll(new LowerThirdAddSendCommand(lowerThird));
	}

	@Override
	public void removeObjectFromList(LowerThird player)
	{
	}
}
