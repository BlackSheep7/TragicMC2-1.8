package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayRecall extends Doomsday{

	public DoomsdayRecall(int id) {
		super(id);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		int dim = player.worldObj.provider.getDimensionId();
		BlockPos cc = player.getBedLocation(dim);

		if (cc != null)
		{
			player.setPositionAndUpdate(cc.getX(), cc.getY(), cc.getZ());
		}
		else
		{
			cc = player.worldObj.getSpawnPoint();
			player.setPositionAndUpdate(cc.getX(), cc.getY(), cc.getZ());
		}

		player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + player.getName() + " was teleported to " + cc.getX() + ", " + cc.getY() + ", " + cc.getZ()));
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}

}
