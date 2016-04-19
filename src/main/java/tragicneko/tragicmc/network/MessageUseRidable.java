package tragicneko.tragicmc.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageUseRidable implements IMessage {

	public byte attackType;

	public MessageUseRidable() {}

	public MessageUseRidable(byte b)
	{
		this.attackType = b;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.attackType = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(this.attackType);
	}
}
