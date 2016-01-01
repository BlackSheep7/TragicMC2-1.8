package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayNatureDrain extends Doomsday implements IExtendedDoomsday {

	public DoomsdayNatureDrain(int id) {
		super(id, EnumDoomType.WORLDSHAPER);
	}

	@Override
	public void doInitialEffects(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		super.doInitialEffects(effect, doom, player, crucMoment);

		double radius = crucMoment ? 10.0D : 5.0D;
		List<BlockPos> list = WorldHelper.getBlocksInSphericalRange(player.worldObj, radius, player.posX, player.posY, player.posZ);

		boolean griefCheck = TragicConfig.griefConfig[0];
		float plantCount = 0.0F;
		BlockPos coords;

		for (int i = 0; i < list.size(); i++)
		{
			coords = (BlockPos) list.get(i);
			Block block = player.worldObj.getBlockState(coords).getBlock();

			if (block instanceof BlockReed || block instanceof BlockSapling || block instanceof BlockFlower
					|| block instanceof BlockDoublePlant || block instanceof BlockMushroom || block instanceof BlockCrops
					|| block instanceof BlockStem || block instanceof BlockLeaves || block instanceof BlockTallGrass)
			{
				if (griefCheck)
				{
					if (block instanceof BlockLeaves)
					{
						player.worldObj.setBlockState(coords, Blocks.gravel.getDefaultState());
					}
					else
					{
						player.worldObj.setBlockToAir(coords);
					}
				}
				plantCount += 0.2;
			}
			else if (block instanceof BlockGrass)
			{
				if (griefCheck)
				{
					player.worldObj.setBlockState(coords, Blocks.dirt.getDefaultState());
				}
				plantCount += 0.1;
			}

			if (block instanceof BlockFarmland)
			{
				if (griefCheck)
				{
					player.worldObj.setBlockState(coords, Blocks.dirt.getDefaultState());
				}
				plantCount += 0.15;
			}
		}

		if (crucMoment) plantCount *= 1.5F;
		if (plantCount > 40.0F) plantCount = 40.0F;
		effect.utilityInt = MathHelper.ceiling_double_int(plantCount);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{
		if (effect.utilityInt > 0)
		{
			float i = rand.nextInt((effect.utilityInt / 2) + 1) + 0.5F;

			if (crucMoment)
			{
				i *= 1.5;
				addCrucialMessage(player);
			}

			if (i > effect.utilityInt) i = effect.utilityInt;
			effect.utilityInt -= i;
			player.heal(i);
			player.worldObj.playSoundAtEntity(player, "mob.blaze.breath", 0.4F, 1.6F);
		}
		else
		{
			double radius = crucMoment ? 10.0D : 5.0D;
			List<BlockPos> list = WorldHelper.getBlocksInSphericalRange(player.worldObj, radius, player.posX, player.posY, player.posZ);

			boolean griefCheck = TragicConfig.griefConfig[0];
			float plantCount = 0.0F;
			BlockPos coords;

			for (int i = 0; i < list.size(); i++)
			{
				coords = (BlockPos) list.get(i);
				Block block = player.worldObj.getBlockState(coords).getBlock();

				if (block instanceof BlockReed || block instanceof BlockSapling || block instanceof BlockFlower
						|| block instanceof BlockDoublePlant || block instanceof BlockMushroom || block instanceof BlockCrops
						|| block instanceof BlockStem || block instanceof BlockLeaves || block instanceof BlockTallGrass)
				{
					if (griefCheck)
					{
						if (block instanceof BlockLeaves)
						{
							player.worldObj.setBlockState(coords, Blocks.gravel.getDefaultState());
						}
						else
						{
							player.worldObj.setBlockToAir(coords);
						}
					}
					plantCount += 0.2;
				}
				else if (block instanceof BlockGrass)
				{
					if (griefCheck)
					{
						player.worldObj.setBlockState(coords, Blocks.dirt.getDefaultState());
					}
					plantCount += 0.1;
				}

				if (block instanceof BlockFarmland)
				{
					if (griefCheck)
					{
						player.worldObj.setBlockState(coords, Blocks.dirt.getDefaultState());
					}
					plantCount += 0.15;
				}
			}

			if (crucMoment) plantCount *= 1.5F;
			if (plantCount > 40.0F) plantCount = 40.0F;
			effect.utilityInt = MathHelper.ceiling_double_int(plantCount);
			if (plantCount == 0) player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No plantlife in range to drain!"));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		float f = rand.nextFloat() * 10.0F;
		player.addExhaustion(f);
	}

	@Override
	public Doomsday getCombination() {
		return Doomsday.RealityAlter;
	}

	@Override
	public int getWaitTime() {
		return 20;
	}

	@Override
	public int getMaxIterations() {
		return 30;
	}
}
