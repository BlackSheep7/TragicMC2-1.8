package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayRealityAlter extends Doomsday {

	private Block[] randomBlocks = new Block[] {Blocks.wheat, Blocks.glowstone, Blocks.cactus, Blocks.deadbush, Blocks.carrots, TragicBlocks.CarrotBlock, TragicBlocks.PotatoBlock,
			TragicBlocks.Light, Blocks.red_flower, Blocks.yellow_flower, Blocks.double_plant, Blocks.wooden_slab, Blocks.cake, Blocks.bed, Blocks.hay_block, Blocks.hardened_clay,
			Blocks.red_mushroom_block, Blocks.brown_mushroom_block, Blocks.water, Blocks.lava, Blocks.activator_rail, Blocks.tnt};

	public DoomsdayRealityAlter(int id) {
		super(id, EnumDoomType.WORLDSHAPER);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom,	EntityPlayer player, boolean crucMoment)
	{
		double radius = crucMoment ? 24.0D : 12.0D;
		List<BlockPos> list = WorldHelper.getBlocksInSphericalRange(player.worldObj, radius, player.posX, player.posY, player.posZ);
		List list2 = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(radius, radius, radius));

		for (int i = 0; i < list2.size(); i++)
		{
			if (list2.get(i) instanceof EntityAnimal && rand.nextInt(8) == 0)
			{
				if (!((EntityLiving) list2.get(i)).hasCustomName())
				{
					if (rand.nextFloat() > 0.35F)
					{
						((Entity) list2.get(i)).getDataWatcher().updateObject(10, "Dinnerbone");
					}
					else
					{
						((Entity) list2.get(i)).getDataWatcher().updateObject(10, "Grumm");
					}
				}
			}
			if (list2.get(i) instanceof EntityMob && TragicConfig.allowStun) ((EntityLivingBase) list2.get(i)).addPotionEffect(new PotionEffect(TragicPotion.Stun.id, 60));
		}

		Block block;
		BlockPos coords;

		for (int i = 0; i < list.size(); i++)
		{
			coords = (BlockPos) list.get(i);
			block = player.worldObj.getBlockState(coords).getBlock();

			if (!(block instanceof BlockAir) && rand.nextInt(48) == 0)
			{
				player.worldObj.setBlockToAir(coords);
				block = Blocks.air;
			}

			if (block == Blocks.air && rand.nextInt(256) == 0)
			{
				player.worldObj.setBlockState(coords, randomBlocks[rand.nextInt(randomBlocks.length)].getDefaultState());
			}
			else if (rand.nextInt(8) != 0)
			{
				if (block == Blocks.lava)
				{
					player.worldObj.setBlockState(coords, Blocks.ice.getDefaultState());
				}
				else if (block == Blocks.water)
				{
					player.worldObj.setBlockState(coords, Blocks.sand.getDefaultState());
				}
				else if (block == Blocks.stone || block == Blocks.cobblestone)
				{
					player.worldObj.setBlockState(coords, Blocks.gravel.getDefaultState());
				}
				else if (block == Blocks.gravel)
				{
					player.worldObj.setBlockState(coords, Blocks.dirt.getDefaultState());
				}
				else if (block == Blocks.netherrack)
				{
					player.worldObj.setBlockState(coords, Blocks.lava.getDefaultState());
				}
				else if (block == Blocks.ice)
				{
					player.worldObj.setBlockState(coords, Blocks.fire.getDefaultState());
				}
				else if (block == Blocks.sand)
				{
					player.worldObj.setBlockState(coords, Blocks.water.getDefaultState());
				}
				else if (block == Blocks.grass)
				{
					player.worldObj.setBlockState(coords, TragicBlocks.Quicksand.getStateFromMeta(1), 2);
				}
				else if (block == Blocks.clay)
				{
					player.worldObj.setBlockState(coords, Blocks.stone.getDefaultState());
				}
				else if (block == Blocks.dirt)
				{
					player.worldObj.setBlockState(coords, Blocks.cobblestone.getDefaultState());
				}
				else if (block == Blocks.sandstone)
				{
					player.worldObj.setBlockState(coords, TragicBlocks.Quicksand.getDefaultState());
				}
				else if (block == Blocks.leaves)
				{
					player.worldObj.setBlockState(coords, Blocks.web.getDefaultState());
				}
				else if (block == Blocks.web)
				{
					player.worldObj.setBlockState(coords, Blocks.hay_block.getDefaultState());
				}
				else if (block == Blocks.cactus)
				{
					player.worldObj.setBlockState(coords, Blocks.red_mushroom_block.getDefaultState());
				}
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		if (TragicConfig.allowDisorientation) player.addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, 120, 0));
	}

	@Override
	public Doomsday getCombination() {
		return Doomsday.GrowthSpurt;
	}
}
