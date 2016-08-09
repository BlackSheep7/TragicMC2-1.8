package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayPoisonBreak extends Doomsday {

	public DoomsdayPoisonBreak(int id) {
		super(id);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{
		Vec3 vec = WorldHelper.getVecFromEntity(player, 15.0);
		if (vec == null) return;

		for (byte i = 0; i < 4; i ++)
		{
			double d0 = vec.xCoord - player.posX + (rand.nextDouble() - rand.nextDouble()) * 2.35;
			double d1 = vec.yCoord - player.posY - player.getEyeHeight() + (rand.nextDouble() - rand.nextDouble()) * 1.135;
			double d2 = vec.zCoord - player.posZ + (rand.nextDouble() - rand.nextDouble()) * 2.35;

			EntityPoisonBarb fireball = new EntityPoisonBarb(player.worldObj, player, d0, d1, d2);
			fireball.setPosition(player.posX, player.posY + player.getEyeHeight(), player.posZ);
			player.worldObj.spawnEntityInWorld(fireball);
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == TragicItems.MercuryDagger) player.destroyCurrentEquippedItem();
	}

}
