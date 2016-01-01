package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdaySeptics extends Doomsday implements IExtendedDoomsday {

	public DoomsdaySeptics(int id) {
		super(id, EnumDoomType.WORLDSHAPER);
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		super.doInitialEffects(effect, doom, player, crucMoment);
		if (TragicConfig.allowImmunity) player.addPotionEffect(new PotionEffect(TragicPotion.Immunity.id, 600, 0));
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		double radius = crucMoment ? 6.0D : 4.0D;
		List<BlockPos> list = WorldHelper.getBlocksInSphericalRange(player.worldObj, radius, player.posX, player.posY, player.posZ);

		Block block;
		BlockPos coords;

		if (crucMoment)
		{
			List list2 = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(radius, radius, radius));
			for (int i = 0; i < list2.size(); i++)
			{
				if (list2.get(i) instanceof EntityMob) ((EntityLivingBase) list2.get(i)).addPotionEffect(new PotionEffect(Potion.poison.id, 120, 2));
			}
		}

		for (int i = 0; i < list.size(); i++)
		{
			coords = (BlockPos) list.get(i);
			block = player.worldObj.getBlockState(coords).getBlock();

			if (block.isAir(player.worldObj, coords) && World.doesBlockHaveSolidTopSurface(player.worldObj, coords.down()))
			{
				player.worldObj.setBlockState(coords, TragicBlocks.SepticGas.getDefaultState());
			}
		}

		if (crucMoment) addCrucialMessage(player);
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.poison.id, 120, 1));
	}

	@Override
	public int getWaitTime() {
		return 5;
	}

	@Override
	public int getMaxIterations() {
		return 60;
	}
}
