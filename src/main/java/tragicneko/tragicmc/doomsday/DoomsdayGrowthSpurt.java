package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayGrowthSpurt extends Doomsday {

	public DoomsdayGrowthSpurt(int id) {
		super(id, EnumDoomType.COMBINATION);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {

		float plantCount = 0.0F;
		BlockPos coords;

		double radius = crucMoment ? 12.0D : 7.0D;
		List<BlockPos> list =  WorldHelper.getBlocksInSphericalRange(player.worldObj, radius, player.posX, player.posY, player.posZ);

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			Block block = player.worldObj.getBlockState(coords).getBlock();

			if (block == Blocks.gravel)
			{
				player.worldObj.setBlockState(coords, Blocks.farmland.getDefaultState());
				plantCount += 0.5;
			}
			else if (block == Blocks.dirt)
			{
				player.worldObj.setBlockState(coords, Blocks.grass.getDefaultState());
				plantCount += 0.5;
			}
			else if (block == Blocks.cobblestone)
			{
				player.worldObj.setBlockState(coords, Blocks.mossy_cobblestone.getDefaultState());
				plantCount += 0.5;
			}
			else if (block == Blocks.sand)
			{
				player.worldObj.setBlockState(coords, Blocks.dirt.getDefaultState());
				plantCount += 0.5;
			}
			else if (block == Blocks.air && rand.nextInt(4) == 0)
			{
				Block block2;
				if (World.doesBlockHaveSolidTopSurface(player.worldObj, coords.down()))
				{
					block2 = player.worldObj.getBlockState(coords.down()).getBlock();
					if (block2 == Blocks.grass)
					{
						if (rand.nextBoolean())
						{
							if (rand.nextBoolean())
							{
								if (rand.nextBoolean())
								{
									player.worldObj.setBlockState(coords, Blocks.tallgrass.getStateFromMeta(rand.nextInt(4)), 3);
								}
								else
								{
									player.worldObj.setBlockState(coords, TragicBlocks.TragicFlower.getStateFromMeta(rand.nextInt(16)), 3);
								}
							}
							else
							{
								Block block3 = rand.nextBoolean() ? Blocks.red_flower : Blocks.yellow_flower;
								player.worldObj.setBlockState(coords, block3.getStateFromMeta(block3 == Blocks.red_flower ? rand.nextInt(8) : 0), 3);
							}
						}
						else
						{
							int meta = rand.nextInt(6);
							player.worldObj.setBlockState(coords, Blocks.double_plant.getStateFromMeta(meta), 2);
							player.worldObj.setBlockState(coords.up(), Blocks.double_plant.getStateFromMeta(8), 2);
						}
					}
					else if (block2 == Blocks.mycelium)
					{
						player.worldObj.setBlockState(coords, rand.nextBoolean() ? Blocks.red_mushroom.getDefaultState() : Blocks.brown_mushroom.getDefaultState());
					}
					else if (block2 instanceof BlockLog || block2 instanceof BlockLeaves)
					{
						player.worldObj.setBlockState(coords, Blocks.vine.getDefaultState());
					}
				}
			}
		}

		if (plantCount > 40.0F) plantCount = 40.0F;

		if (plantCount > 0.0F)
		{
			player.heal(plantCount);
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Nowhere to grow plants!"));
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}

}
