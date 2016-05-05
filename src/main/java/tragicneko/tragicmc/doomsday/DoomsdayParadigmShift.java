package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayParadigmShift extends Doomsday {

	public DoomsdayParadigmShift(int id) {
		super(id, EnumDoomType.CRISIS);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		boolean flag = (int) ((1 / this.getCrisis(player)) * 10) >= 15 || effect.isCommandActivated;
		if (crucMoment)
		{
			Doomsday dday = getRandomDoomsday();

			while (dday == null && dday != this)
			{
				dday = getRandomDoomsday();
			}

			if (!flag)
			{
				dday.activateDoomsday(doom);
			}
			else
			{
				DoomsdayEffect effect2 = new DoomsdayEffect(dday.getDoomId(), doom, flag);
				DoomsdayManager.registerDoomsdayEffect(player.getUniqueID(), effect2);
			}
		}

		Doomsday dday = getRandomDoomsday();

		while (dday == null && dday != this)
		{
			dday = getRandomDoomsday();
		}

		if (!flag)
		{
			dday.activateDoomsday(doom);
		}
		else
		{
			DoomsdayEffect effect2 = new DoomsdayEffect(dday.getDoomId(), doom, flag);
			DoomsdayManager.registerDoomsdayEffect(player.getUniqueID(), effect2);
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		if (TragicConfig.getBoolean("allowCooldown")) doom.increaseCooldown(200);
	}

}
