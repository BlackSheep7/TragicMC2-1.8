package tragicneko.tragicmc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tragicneko.tragicmc.TragicMC;

public class MessageHandlerSpawnParticle implements IMessageHandler<MessageParticle, IMessage>{

	@Override
	public IMessage onMessage(MessageParticle message, MessageContext ctx) {
		EntityPlayer player = TragicMC.proxy.getPlayerFromMessageCtx(ctx);
		if (player == null || player.worldObj == null || message.nameTag == null) return null;
		NBTTagCompound tag = message.nameTag;
		player.worldObj.spawnParticle(tag.hasKey("particleID") ? EnumParticleTypes.values()[tag.getInteger("particleID")] : EnumParticleTypes.CLOUD,
				tag.hasKey("xPos") ? tag.getDouble("xPos") : 0.0,
						tag.hasKey("yPos") ? tag.getDouble("yPos") : 0.0,
								tag.hasKey("zPos") ? tag.getDouble("zPos") : 0.0,
										tag.hasKey("xAccel") ? tag.getDouble("xAccel") : 0.0,
												tag.hasKey("yAccel") ? tag.getDouble("yAccel") : 0.0,
														tag.hasKey("zAccel") ? tag.getDouble("zAccel") : 0.0);
		return null;
	}
}
