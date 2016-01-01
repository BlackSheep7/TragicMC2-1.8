package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayMoonlightSonata extends Doomsday {

	public DoomsdayMoonlightSonata(int id) {
		super(id);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		if (!player.worldObj.isDaytime() && player.worldObj.canBlockSeeSky(WorldHelper.getBlockPos(player)))
		{
			doom.fillDoom();
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Not the proper time to use that..."));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}

	@Override
	public Doomsday getCombination()
	{
		return Doomsday.FlightOfTheValkyries;
	}
}
