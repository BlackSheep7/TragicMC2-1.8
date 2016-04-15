package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SurfacePlantWorldGen implements IWorldGen {

	public final byte iterations;
	public final Block block;
	public final byte meta;
	public final byte width;
	public final byte height;
	public final byte plantHeight;

	public SurfacePlantWorldGen(byte iterations, Block block, byte meta, byte width, byte height, byte plantHeight)
	{
		this.iterations = iterations;
		this.block = block;
		this.meta = meta;
		this.width = width;
		this.height = height;
		this.plantHeight = plantHeight;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world) {

		if (!TragicConfig.getBoolean("allowFruitGen")) return;
		
		int Xcoord = (chunkX * 16) + random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(new BlockPos(Xcoord, 0, Zcoord)).getY();

		Block block;
		for (byte i = 0; i < iterations; i++)
		{
			Xcoord += random.nextInt(width) - random.nextInt(width);
			Zcoord += random.nextInt(width) - random.nextInt(width);
			Ycoord += random.nextInt(height) - random.nextInt(height);
			
			byte pl = (byte) (random.nextInt(this.plantHeight)+ 1); 

			for (byte y1 = 0; y1 < pl; y1++)
			{
				if (Ycoord + y1 < 0 || Ycoord + y1 > world.getActualHeight()) break;
				block = world.getBlockState(new BlockPos(Xcoord, Ycoord + y1, Zcoord)).getBlock();

				if (block.canBeReplacedByLeaves(world, new BlockPos(Xcoord, Ycoord + y1, Zcoord)) || block.isAir(world, new BlockPos(Xcoord, Ycoord + y1, Zcoord)))
				{
					if (this.block.canPlaceBlockAt(world, new BlockPos(Xcoord, Ycoord + y1, Zcoord))) world.setBlockState(new BlockPos(Xcoord, Ycoord + y1, Zcoord), this.block.getStateFromMeta(meta), 2);
				}
			}
		}
	}

}
