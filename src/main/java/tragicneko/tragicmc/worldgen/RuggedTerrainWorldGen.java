package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.blocks.BlockGenericTallGrass;
import tragicneko.tragicmc.util.WorldHelper;

public class RuggedTerrainWorldGen implements IWorldGen {

	public final Block block;
	public final byte meta;
	public final byte iterations;
	public final double radius;
	public final double variation;
	public final boolean replacesAir;
	public final Block toReplace;
	public final byte width;

	public RuggedTerrainWorldGen(Block block, byte meta, Block toReplace, byte iterations, double radius, double var, boolean flag, byte width)
	{
		this.block = block;
		this.meta = meta; //1 for dirt, 2 for eroded stone
		this.toReplace = toReplace;
		this.iterations = iterations; //4
		this.radius = radius; //4.0
		this.variation = var; // 2.0
		this.replacesAir = flag; //tainted only
		this.width = width; //8
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world) {

		if (!TragicConfig.getBoolean("allowScatteredSurfaceGen")) return;
		
		int x = (chunkX * 16) + random.nextInt(16);
		int z = (chunkZ * 16) + random.nextInt(16);
		int y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();

		double radius;
		ArrayList<BlockPos> list;
		Block block;

		for (byte pow = 0; pow < this.iterations; pow++)
		{
			x += random.nextInt(width) - random.nextInt(width);
			z += random.nextInt(width) - random.nextInt(width);
			radius = (variation * random.nextDouble()) + this.radius;

			for (byte y1 = -1; y1 < 2; y1++)
			{
				list = WorldHelper.getBlocksInCircularRange(world, radius, x, y + y1, z);

				for (BlockPos coords : list)
				{
					if (random.nextInt(16) != 0) continue;

					block = world.getBlockState(coords).getBlock();
					if (!world.getBlockState(coords.down()).getBlock().isOpaqueCube()) continue;

					if (block == this.toReplace || block.getMaterial() == Material.air && this.replacesAir || block instanceof BlockGenericTallGrass)
					{
						world.setBlockState(coords, this.block.getStateFromMeta(meta), 2);
					}
				}
			}

		}
	}

}
