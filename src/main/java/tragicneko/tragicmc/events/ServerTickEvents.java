package tragicneko.tragicmc.events;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityIre;

public class ServerTickEvents {

	@SubscribeEvent
	public void onServerTick(ServerTickEvent event)
	{
		if (event.phase == Phase.START)
		{
			if (TragicConfig.allowIre)
			{
				EntityIre.ireTick++;
				if (EntityIre.ireTick >= 40)
				{
					EntityIre.ireTick = 0;
					EntityIre.ireNetSize = 0;
				}
			}	
		}
	}
}
