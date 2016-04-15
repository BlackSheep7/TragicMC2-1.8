package tragicneko.tragicmc.dimension;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;

public class TragicTeleporter extends Teleporter {

	protected final WorldServer worldServerInstance;
	protected final Random random;

	public TragicTeleporter(WorldServer par1WorldServer)
	{
		super(par1WorldServer);
		this.worldServerInstance = par1WorldServer;
		this.random = new Random(par1WorldServer.getSeed());
	}

	@Override
	public void placeInPortal(Entity par1Entity, float rotationYaw)
	{
		if (this.worldServerInstance.provider.getDimensionId() != 0)
		{
			int i = this.worldServerInstance.getSpawnPoint().getX();
			int k = this.worldServerInstance.getSpawnPoint().getZ();
			int j = this.worldServerInstance.provider instanceof TragicWorldProvider ? this.worldServerInstance.getTopSolidOrLiquidBlock(new BlockPos(i, 0, k)).getY() : this.worldServerInstance.getSpawnPoint().getY();
			byte b0 = 1;
			byte b1 = 0;

			boolean endFlag = this.worldServerInstance.provider.getDimensionId() == 1 || this.worldServerInstance.provider.getDimensionId() == TragicConfig.getInt("synapseID");

			if (endFlag)
			{
				i = this.worldServerInstance.provider.getSpawnCoordinate().getX();
				j = this.worldServerInstance.provider.getSpawnCoordinate().getY();
				k = this.worldServerInstance.provider.getSpawnCoordinate().getZ();
			}
			else if (par1Entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) par1Entity;
				BlockPos cc = player.getBedLocation(this.worldServerInstance.provider.getDimensionId());

				if (cc != null)
				{
					i = cc.getX();
					j = cc.getY();
					k = cc.getZ();
				}
			}

			Block spawnBlock = this.worldServerInstance.getBlockState(new BlockPos(i, j - 1, k)).getBlock();
			boolean lavaFlag = spawnBlock.getMaterial() == Material.lava || spawnBlock.getMaterial() == Material.lava;

			if (worldServerInstance.provider.getDimensionId() == TragicConfig.getInt("collisionID") || lavaFlag || worldServerInstance.provider.getDimensionId() == TragicConfig.getInt("synapseID"))
			{
				for (int l = -2; l <= 2; ++l)
				{
					for (int i1 = -2; i1 <= 2; ++i1)
					{
						for (int j1 = -1; j1 < 3; ++j1)
						{
							int k1 = i + i1 * b0 + l * b1;
							int l1 = j + j1;
							int i2 = k + i1 * b1 - l * b0;
							boolean flag = j1 < 0;
							this.worldServerInstance.setBlockState(new BlockPos(k1, l1, i2), flag ? (endFlag ? Blocks.obsidian.getDefaultState() : TragicBlocks.DeadDirt.getDefaultState()) : Blocks.air.getDefaultState());
						}
					}
				}

				if (lavaFlag)
				{
					for (int x = -1; x < 2; x++)
					{
						this.worldServerInstance.setBlockState(new BlockPos(i + x, j, k - 3), endFlag ? Blocks.obsidian.getDefaultState() : TragicBlocks.DeadDirt.getDefaultState());
						this.worldServerInstance.setBlockState(new BlockPos(i + x, j, k + 3), endFlag ? Blocks.obsidian.getDefaultState() : TragicBlocks.DeadDirt.getDefaultState());
						this.worldServerInstance.setBlockState(new BlockPos(i + 3, j, k + x), endFlag ? Blocks.obsidian.getDefaultState() : TragicBlocks.DeadDirt.getDefaultState());
						this.worldServerInstance.setBlockState(new BlockPos(i - 3, j, k + x), endFlag ? Blocks.obsidian.getDefaultState() : TragicBlocks.DeadDirt.getDefaultState());
					}
				}
			}

			par1Entity.setLocationAndAngles(i, (double)j + 1, k, par1Entity.rotationYaw, par1Entity.rotationPitch);
			par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
			par1Entity.fallDistance = 0.0F;
		}
		else
		{
			int i = worldServerInstance.getSpawnPoint().getX();
			int k = worldServerInstance.getSpawnPoint().getZ();
			int j = worldServerInstance.getTopSolidOrLiquidBlock(new BlockPos(i, 0, k)).getY();

			if (par1Entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) par1Entity;
				BlockPos cc = player.getBedLocation(0);

				if (cc != null)
				{
					player.setLocationAndAngles(cc.getX(), cc.getY() + 1.5D, cc.getZ(), player.rotationYaw, player.rotationPitch);
				}
				else
				{
					player.setLocationAndAngles(i, j, k, player.rotationYaw, player.rotationPitch);
				}

				player.motionX = player.motionY = player.motionZ = 0.0D;
				player.fallDistance = 0.0F;
			}
			else
			{
				par1Entity.setLocationAndAngles(i, j, k, par1Entity.rotationYaw, par1Entity.rotationPitch);
				par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
				par1Entity.fallDistance = 0.0F;
			}
		}

	}

}
