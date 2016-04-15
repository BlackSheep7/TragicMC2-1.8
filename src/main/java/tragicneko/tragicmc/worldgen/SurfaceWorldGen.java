package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.util.WorldHelper;

public class SurfaceWorldGen implements IWorldGen {

	public final double radius;
	public final double variation;
	public final boolean usesAltGen;
	public final byte iterations;
	public final Block block;
	public final byte meta;
	public final Block toReplace;
	public final boolean doesAirCheck;
	public final boolean randPerIteration;

	public SurfaceWorldGen(double radius, double var, boolean flag, byte relays, Block block, byte meta, Block toReplace, boolean flag2, boolean flag3)
	{
		this.radius = radius;
		this.variation = var;
		this.usesAltGen = flag;
		this.iterations = relays;
		this.block = block;
		this.meta = meta;
		this.toReplace = toReplace;
		this.doesAirCheck = flag2;
		this.randPerIteration = flag3;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world) {

		if (!TragicConfig.getBoolean("allowScatteredSurfaceGen")) return;
		
		int x = (chunkX * 16) + random.nextInt(16);
		int z = (chunkZ * 16) + random.nextInt(16);
		int y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
		double radius = (this.variation * random.nextDouble()) + this.radius;
		ArrayList<BlockPos> list;
		BlockPos coords = new BlockPos(x, y, z);
		Block block;

		for (byte y1 = -1; y1 < 2; y1++)
		{
			if (this.randPerIteration)
			{
				x += random.nextInt(4) - random.nextInt(4);
				z += random.nextInt(4) - random.nextInt(4);
			}

			list = WorldHelper.getBlocksInCircularRange(world, radius, x, y + y1, z);

			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				block = world.getBlockState(coords).getBlock();

				if (this.doesAirCheck && world.getBlockState(coords.up()).getBlock().getMaterial() != Material.air) continue;
				if (block == this.toReplace) world.setBlockState(coords, this.block.getStateFromMeta(meta), 2);
			}
		}

		for (byte k = 0; k < this.iterations && this.usesAltGen; k++)
		{
			block = world.getBlockState(coords).getBlock();
			list = WorldHelper.getBlocksAdjacent(coords);

			for (BlockPos cand2 : list)
			{
				block = world.getBlockState(cand2).getBlock();
				if (this.doesAirCheck && world.getBlockState(cand2.up()).getBlock().getMaterial() != Material.air) continue;
				if (block == this.toReplace && random.nextBoolean()) world.setBlockState(cand2, this.block.getStateFromMeta(meta), 2);
			}

			coords = list.get(random.nextInt(list.size()));
		}
	}

}
