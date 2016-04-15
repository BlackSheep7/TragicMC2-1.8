package tragicneko.tragicmc.worldgen;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;

public class SynapseVariantGen implements IWorldGen {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world) {
		if (Math.abs(chunkX) < 2 && Math.abs(chunkZ) < 2) return;
		Chunk chk = world.getChunkFromChunkCoords(chunkX, chunkZ);
		boolean flag = random.nextBoolean();
		int x = chunkX * 16;
		int z = chunkZ * 16;

		if (flag)
		{			
			for (byte y1 = 16; y1 < 112; y1++)
			{
				for (byte x1 = 0; x1 < 16; x1++)
				{
					for (byte z1 = 0; z1 < 16; z1++)
					{
						if (y1 % 8 > 3)
						{
							if (random.nextBoolean()) world.setBlockState(new BlockPos(x + x1, y1, z + z1), TragicBlocks.CircuitBlock.getStateFromMeta(4), 2);
						}
						else
						{
							world.setBlockToAir(new BlockPos(x + x1, y1, z + z1));
						}
					}
				}
			}
		}
		else
		{
			for (byte y1 = 16; y1 < 112; y1++)
			{
				for (byte x1 = 0; x1 < 16; x1++)
				{
					for (byte z1 = 0; z1 < 16; z1++)
					{
						if (chk.getBlock(x1, y1, z1) == Blocks.air && random.nextInt(8) == 0) world.setBlockState(new BlockPos(x + x1, y1, z + z1), TragicBlocks.DigitalSea.getDefaultState());
						else if (chk.getBlockState(new BlockPos(x1, y1, z1)) == TragicBlocks.CircuitBlock.getDefaultState()) chk.setBlockState(new BlockPos(x1, y1, z1), TragicBlocks.CircuitBlock.getStateFromMeta(random.nextInt(4) + 1));
					}
				}
			}
		}

		byte[] ary = new byte[256];
		final byte toFill = (byte) (flag ? TragicConfig.getInt("synapseCorruptID") : TragicConfig.getInt("synapseDeadID"));
		Arrays.fill(ary, toFill);
		chk.setBiomeArray(ary);
	}
}
