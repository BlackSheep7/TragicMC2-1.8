package tragicneko.tragicmc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tragicneko.tragicmc.TragicMC;

public class MessageHandlerGui implements IMessageHandler<MessageGui, IMessage>{

	@Override
	public IMessage onMessage(MessageGui message, MessageContext ctx) {
		EntityPlayer player = MinecraftServer.getServer().isDedicatedServer() ? TragicMC.proxy.getPlayerFromMessageCtx(ctx) : ctx.getServerHandler().playerEntity;
		player.openGui(TragicMC.getInstance(), message.id, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
		return null;
	}

}
