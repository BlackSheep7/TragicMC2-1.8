package tragicneko.tragicmc.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.properties.PropertyAmulets;

public class MessageHandlerAmulet implements IMessageHandler<MessageAmulet, IMessage> {

	@Override
	public MessageAmulet onMessage(MessageAmulet message, MessageContext ctx) {
		PropertyAmulets amulets = PropertyAmulets.get(TragicMC.proxy.getPlayerFromMessageCtx(ctx));
		if (amulets != null && TragicConfig.getBoolean("allowAmulets")) amulets.loadNBTData(message.tag);

		return null;
	}

}