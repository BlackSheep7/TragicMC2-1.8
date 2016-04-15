package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.util.WorldHelper;

public class PitWorldGen implements IWorldGen {

	public final Block block;
	public final byte meta;
	public final byte depth;
	public final byte depthVar;
	public final double radius;
	public final double variation;

	public PitWorldGen(Block block, byte meta, byte depth, byte depthVar, double radius, double variation)
	{
		this.block = block;
		this.meta = meta;
		this.depth = depth;
		this.depthVar = depthVar;
		this.radius = radius;
		this.variation = variation;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world) {

		if (!TragicConfig.getBoolean("allowVoidPitGen")) return;
		
		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(new BlockPos(Xcoord, 0, Zcoord)).down(random.nextInt(10)).getY();
		int depth = Ycoord - this.depth - random.nextInt(this.depthVar + 1);

		double size;
		ArrayList<BlockPos> list;
		ArrayList<BlockPos> cands = new ArrayList<BlockPos>();

		size = this.variation * random.nextDouble() + this.radius;

		for (byte pow = 0; pow + Ycoord >= depth && pow + Ycoord >= 0 && pow + Ycoord <= 256; --pow)
		{
			if (size >= 5.5D)
			{
				list = WorldHelper.getBlocksInCircularRange(world, size * 0.31773D, Xcoord, Ycoord + pow, Zcoord); //makes sure the middle of the pit is clear

				for (BlockPos coords : list)
				{
					if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
				}
			}

			list = WorldHelper.getBlocksInCircularRange(world, size * 0.64773D, Xcoord, Ycoord + pow, Zcoord); //gives the pit more of a gradual feel

			for (BlockPos coords : list)
			{
				if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
			}

			list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + pow, Zcoord); //outer part that has the most scattered blocks

			for (BlockPos coords : list)
			{
				if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
			}

			if (size >= 3.0D && random.nextInt(4) == 0) size *= 0.987425D; //reduces size of the void pit randomly, similarly to spikes, but this is to reduce lag
		}

		for (BlockPos coords2 : cands)
		{
			if (coords2.getY() > depth + 1 || random.nextBoolean()) world.setBlockToAir(coords2);
			else world.setBlockState(coords2, this.block.getStateFromMeta(meta), 2);
		}
	}
}
