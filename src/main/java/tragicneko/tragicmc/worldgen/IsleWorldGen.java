package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class IsleWorldGen implements IWorldGen {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world) {

		if (!TragicConfig.getBoolean("allowIsleGen")) return;
		
		int Xcoord = (chunkX * 16) + random.nextInt(16) - random.nextInt(16);
		int Zcoord = (chunkZ * 16) + random.nextInt(16) - random.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(new BlockPos(Xcoord, 0, Zcoord)).getY();

		ArrayList<BlockPos> list;
		byte relays = (byte) (1 + random.nextInt(3));
		Block block;
		double regression = 0.86977745D;
		double cutoff = 0.48943755D;
		double size;
		ArrayList<BlockPos> cands = new ArrayList<BlockPos>();
		int yMax;

		for (byte buzza = 0; buzza < relays; buzza++)
		{
			size = random.nextDouble() * 4.5D + 2.5D;
			Xcoord += random.nextInt(8) - random.nextInt(8);
			Zcoord += random.nextInt(8) - random.nextInt(8);
			yMax = Ycoord;
			byte r = (byte) (16 + random.nextInt(16) - random.nextInt(8));
			Ycoord += r;
			yMax += r;

			for (byte y1 = 0; y1 > -32; y1--)
			{
				if (size < cutoff) break;
				size *= regression;

				if (random.nextInt(12) == 0)
				{
					Xcoord += random.nextInt(2) - random.nextInt(2);
					Zcoord += random.nextInt(2) - random.nextInt(2);
				}

				list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + y1, Zcoord);

				for (BlockPos coords2 : list)
				{
					block = world.getBlockState(coords2).getBlock();
					if (!cands.contains(coords2)) cands.add(coords2);
				}
			}

			for (BlockPos coords2 : cands)
			{
				if (coords2.getY() >= yMax)
				{
					world.setBlockState(coords2, TragicBlocks.ErodedStone.getStateFromMeta(0), 2);
				}
				else
				{
					world.setBlockState(coords2, TragicBlocks.DarkStone.getStateFromMeta(0), 2);
				}
			}
			Ycoord = world.getTopSolidOrLiquidBlock(new BlockPos(Xcoord, 0, Zcoord)).getY();
		}
	}

}
