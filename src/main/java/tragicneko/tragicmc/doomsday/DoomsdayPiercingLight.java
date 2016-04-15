package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayPiercingLight extends Doomsday {

	public DoomsdayPiercingLight(int id) {
		super(id, EnumDoomType.CRISIS);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{
		float crisis = this.getCrisis(player);
		List<Entity> list = new ArrayList();

		if (player.worldObj.getLightFor(EnumSkyBlock.BLOCK, player.getPosition()) < 8)
		{
			double d0 = 16.0;

			if (crisis >= 0.25F)
			{
				d0 = 12.0;
			}

			if (crisis >= 0.5F)
			{
				d0 = 8.0;
			}

			if (crisis >= 0.75F)
			{
				d0 = 4.0;
			}

			list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(d0, d0, d0));

			if (effect.utilityList.size() == 0) addNoEntityMessage(player);
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "It's not dark enough here to use that!"));
			if (!list.isEmpty()) list.clear();
		}

		for (int x = 0; x < list.size(); x++)
		{
			if (list.get(x) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(x);
				if (entity instanceof EntityPlayer && !TragicConfig.getBoolean("allowPvP")) continue;

				int f = entity.worldObj.getLightFor(EnumSkyBlock.BLOCK, WorldHelper.getBlockPos(entity));

				if (f <= 8)
				{
					if (f < 1) f = 1;
					entity.setFire(10 + rand.nextInt(16));
					int j = crucMoment ? 40 : 20;
					entity.attackEntityFrom(DamageSource.magic, (j / f + crisis));
				}
			}
		}
	}



	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.setFire(8 + rand.nextInt(16));
	}

}
