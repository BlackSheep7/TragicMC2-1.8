package tragicneko.tragicmc.worldgen.schematic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicMemoryCache extends Schematic {

	public SchematicMemoryCache(BlockPos pos, Structure str) {
		super(pos, str, 12, 12, 12);
	}

	@Override
	public Schematic generateStructure(int variant, World world, Random rand, int x, int y, int z) {
		double size = rand.nextDouble() * 4.45D + 5.55D;
		ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(world, size, x, y, z);

		for (BlockPos coords : list)
		{
			this.setBlock(world, coords.getX(), coords.getY(), coords.getZ(), TragicBlocks.Conduit, 0, 2);
		}

		list = WorldHelper.getBlocksInSphericalRange(world, size - 1.0D, x, y, z);

		for (BlockPos coords : list)
		{
			this.setBlockToAir(world, coords.getX(), coords.getY(), coords.getZ());
		}

		for (int x1 = -1; x1 < 2 && TragicConfig.getBoolean("allowNanoSwarm"); x1++)
		{
			for (int z1 = -1; z1 < 2; z1++)
			{
				for (int y1 = -1; y1 < 2; y1++)
				{
					this.setBlock(world, x + x1, y + y1, z + z1, Blocks.mob_spawner, 0, 2, "TragicMC.NanoSwarm");
				}
			}
		}

		this.setBlock(world, x, y, z, Blocks.chest, 0, 2, TragicItems.NetherStructureHook);

		return this;
	}

}
