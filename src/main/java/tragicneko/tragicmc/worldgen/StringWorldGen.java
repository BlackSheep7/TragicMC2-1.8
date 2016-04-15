package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class StringWorldGen implements IWorldGen {

	public final Block block;
	public final byte meta;
	public final byte iterations;

	public StringWorldGen(Block block, byte meta, byte iterations)
	{
		this.block = block;
		this.meta = meta;
		this.iterations = iterations;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world) {

		if (!TragicConfig.getBoolean("allowStringLightGen")) return;
		
		int Xcoord = (chunkX * 16);
		int Zcoord = (chunkZ * 16);
		int Ycoord = world.getTopSolidOrLiquidBlock(new BlockPos(Xcoord, 0, Zcoord)).up(random.nextInt(8) + 4).getY();

		Block ablock;
		ArrayList<int[]> cands = new ArrayList<int[]>();
		int[] cand;

		for (byte i = 0; i < this.iterations; i++)
		{
			Xcoord += random.nextInt(2) - random.nextInt(2);
			Zcoord += random.nextInt(2) - random.nextInt(2);
			Ycoord += random.nextInt(2);
			cand = new int[] {Xcoord, Ycoord, Zcoord};
			if (cands.contains(cand)) continue;
			ablock = world.getBlockState(new BlockPos(Xcoord, Ycoord, Zcoord)).getBlock();

			if (ablock.canBeReplacedByLeaves(world, new BlockPos(Xcoord, Ycoord, Zcoord)) || ablock.isAir(world, new BlockPos(Xcoord, Ycoord, Zcoord))) cands.add(cand);
		}

		for (int[] coords : cands)
		{
			world.setBlockState(new BlockPos(coords[0], coords[1], coords[2]), this.block.getStateFromMeta(meta), 2);
		}
	}

}
