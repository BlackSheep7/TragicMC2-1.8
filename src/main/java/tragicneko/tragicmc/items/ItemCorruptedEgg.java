package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import tragicneko.tragicmc.TragicMC;

public class ItemCorruptedEgg extends Item {

	public ItemCorruptedEgg()
	{
		super();
		this.setCreativeTab(TragicMC.Survival);
		this.setUnlocalizedName("tragicmc.corruptedEgg");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
		{
			return true;
		}
		else if (!playerIn.canPlayerEdit(pos.offset(side), side, stack))
		{
			return false;
		}
		else
		{
			IBlockState iblockstate = worldIn.getBlockState(pos);

			pos = pos.offset(side);
			double d0 = 0.0D;

			if (side == EnumFacing.UP && iblockstate instanceof BlockFence)
			{
				d0 = 0.5D;
			}

			Entity entity = spawnCreature(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY() + d0, (double)pos.getZ() + 0.5D);

			if (entity != null)
			{
				if (entity instanceof EntityLivingBase && stack.hasDisplayName())
				{
					entity.setCustomNameTag(stack.getDisplayName());
				}

				if (!playerIn.capabilities.isCreativeMode)
				{
					--stack.stackSize;
				}
			}

			return true;
		}
	}

	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
	{
		if (worldIn.isRemote)
		{
			return itemStackIn;
		}
		else
		{
			MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, true);

			if (movingobjectposition == null)
			{
				return itemStackIn;
			}
			else
			{
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
				{
					BlockPos blockpos = movingobjectposition.getBlockPos();

					if (!worldIn.isBlockModifiable(playerIn, blockpos))
					{
						return itemStackIn;
					}

					if (!playerIn.canPlayerEdit(blockpos, movingobjectposition.sideHit, itemStackIn))
					{
						return itemStackIn;
					}

					if (worldIn.getBlockState(blockpos).getBlock() instanceof BlockLiquid)
					{
						Entity entity = spawnCreature(worldIn, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.5D, (double)blockpos.getZ() + 0.5D);

						if (entity != null)
						{
							if (entity instanceof EntityLivingBase && itemStackIn.hasDisplayName())
							{
								((EntityLiving)entity).setCustomNameTag(itemStackIn.getDisplayName());
							}

							if (!playerIn.capabilities.isCreativeMode)
							{
								--itemStackIn.stackSize;
							}

							playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
						}
					}
				}

				return itemStackIn;
			}
		}
	}

	public static Entity spawnCreature(World worldIn, double x, double y, double z)
	{
		List list = worldIn.getBiomeGenForCoords(new BlockPos(x, y, z)).getSpawnableList(EnumCreatureType.MONSTER);

		if (list.isEmpty())
		{
			return null;
		}
		else
		{
			Entity entity = null;
			BiomeGenBase.SpawnListEntry entry = (SpawnListEntry) WeightedRandom.getRandomItem(itemRand, list);
			try
			{
				Class oclass = entry.entityClass;

				if (oclass != null)
				{
					entity = (Entity)oclass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {worldIn});
				}
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}

			if (entity instanceof EntityLivingBase)
			{
				EntityLiving entityliving = (EntityLiving)entity;
				entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(worldIn.rand.nextFloat() * 360.0F), 0.0F);
				entityliving.rotationYawHead = entityliving.rotationYaw;
				entityliving.renderYawOffset = entityliving.rotationYaw;
				entityliving.func_180482_a(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), (IEntityLivingData)null);
				worldIn.spawnEntityInWorld(entity);
				entityliving.playLivingSound();
			}

			return entity;
		}
	}
}
