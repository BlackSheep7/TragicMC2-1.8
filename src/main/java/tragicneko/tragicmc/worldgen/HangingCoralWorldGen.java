package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;

public class HangingCoralWorldGen extends WorldGenerator {

	public final int width;
	public final int height;
	public final int iterations;
	public final Block block;
	public final byte meta;

	public HangingCoralWorldGen(int width, int height, int iterations, Block block, byte meta)
	{
		this.width = width;
		this.height = height;
		this.iterations = iterations;
		this.block = block;
		this.meta = meta;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		if (!world.isAirBlock(pos))
		{
			return false;
		}
		else if (world.getBlockState(pos.up(1)).getBlock() != TragicBlocks.CircuitBlock)
		{
			return false;
		}
		else
		{
			world.setBlockState(pos, block.getStateFromMeta(meta), 2);

			for (int l = 0; l < this.iterations; ++l)
			{
				int i1 = pos.getX() + rand.nextInt(width) - rand.nextInt(width); // 4
				int j1 = pos.getY() - rand.nextInt(height); //32
				int k1 = pos.getZ() + rand.nextInt(width) - rand.nextInt(width);

				if (world.getBlockState(new BlockPos(i1, j1, k1)).getBlock().getMaterial() == Material.air)
				{
					byte b = 0;
					ArrayList<int[]> list = WorldHelper.getBlocksAdjacent(new int[] {i1, j1, k1});
					for (int[] coords : list)
					{
						if (world.getBlockState(new BlockPos(coords[0], coords[1], coords[2])).getBlock() == block) b++;
					}

					if (b == 1) world.setBlockState(new BlockPos(i1, j1, k1), block.getStateFromMeta(meta), 2);
				}
			}

			return true;
		}
	}
}
