package tragicneko.tragicmc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;

public class MessageHandlerFlight implements IMessageHandler<MessageFlight, IMessage>{

	@Override
	public IMessage onMessage(MessageFlight message, MessageContext ctx) {
		TragicConfig.overrideObject("allowFlight", message.flightEnabled == 1);
		if (!TragicConfig.getBoolean("allowFlight"))
		{
			EntityPlayer player = TragicMC.proxy.getPlayerFromMessageCtx(ctx);
			if (player != null) player.addChatMessage(new ChatComponentText("Flight potion effect is disabled due to the server not allowing it. Change the option in your server.properties file if you want it enabled."));
		}
		return null;
	}

}
