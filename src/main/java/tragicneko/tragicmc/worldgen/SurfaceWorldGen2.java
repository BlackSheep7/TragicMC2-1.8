package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SurfaceWorldGen2 implements IWorldGen {

	public final byte iterations;
	public final Block block;
	public final byte meta;
	public final byte width;
	public final byte height;

	public SurfaceWorldGen2(byte iterations, Block block, byte meta, byte width, byte height)
	{
		this.iterations = iterations;
		this.block = block;
		this.meta = meta;
		this.width = width;
		this.height = height;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world) {

		if (!TragicConfig.getBoolean("allowScatteredSurfaceGen")) return;
		
		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(new BlockPos(Xcoord, 0, Zcoord)).getY();

		Block block;
		for (byte i = 0; i < iterations; i++)
		{
			Xcoord += random.nextInt(width) - random.nextInt(width);
			Zcoord += random.nextInt(width) - random.nextInt(width);
			Ycoord += random.nextInt(height) - random.nextInt(height);

			if (Ycoord < 0 || Ycoord > 256) break;
			block = world.getBlockState(new BlockPos(Xcoord, Ycoord, Zcoord)).getBlock();

			if (block.canBeReplacedByLeaves(world, new BlockPos(Xcoord, Ycoord, Zcoord)) || block.isAir(world, new BlockPos(Xcoord, Ycoord, Zcoord)))
			{
				if (World.doesBlockHaveSolidTopSurface(world, new BlockPos(Xcoord, Ycoord - 1, Zcoord)) && !block.getMaterial().isLiquid()) world.setBlockState(new BlockPos(Xcoord, Ycoord, Zcoord), this.block.getStateFromMeta(meta), 2);
			}
		}
	}

}
