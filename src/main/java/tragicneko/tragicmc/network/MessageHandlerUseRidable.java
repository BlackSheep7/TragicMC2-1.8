package tragicneko.tragicmc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityRidable;

public class MessageHandlerUseRidable implements IMessageHandler<MessageUseRidable, MessageUseRidable> {

	@Override
	public MessageUseRidable onMessage(MessageUseRidable message, MessageContext ctx) {
		EntityPlayer player = MinecraftServer.getServer().isDedicatedServer() ? TragicMC.proxy.getPlayerFromMessageCtx(ctx) : ctx.getServerHandler().playerEntity;
		if (player == null || player.ridingEntity == null) return null;
		if (player.ridingEntity instanceof EntityRidable)
		{
			EntityRidable er = (EntityRidable) player.ridingEntity;
			if (er.canAttack()) er.useAttack(message.attackType);
		}
		return null;
	}

}
