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

public class SchematicLightSpire extends Schematic {

	public SchematicLightSpire(BlockPos pos, Structure str) {
		super(pos, str, 24, 6, 6);
	}

	@Override
	public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {

		ArrayList<BlockPos> list;
		final int height = rand.nextInt(30) + 36;

		for (int y1 = 0; y1 < height && y + y1 < 256; y1++)
		{
			list = WorldHelper.getBlocksInCircularRange(world, 4.25, x + rand.nextInt(3) - rand.nextInt(3), y + y1, z + rand.nextInt(3) - rand.nextInt(3));
			for (BlockPos coords : list)
			{
				this.setBlock(world, coords.getX(), coords.getY(), coords.getZ(), TragicBlocks.StringLight, 0, 2);
			}

			list = WorldHelper.getBlocksInCircularRange(world, 3.25, x + rand.nextInt(3) - rand.nextInt(3), y + y1, z + rand.nextInt(3) - rand.nextInt(3));
			for (BlockPos coords : list)
			{
				this.setBlockToAir(world, coords.getX(), coords.getY(), coords.getZ());
			}

			if (rand.nextInt(32) == 0)
			{
				int xr = x + rand.nextInt(3) - rand.nextInt(3);
				int zr = z + rand.nextInt(3) - rand.nextInt(3);
				this.setBlock(world, xr, y + y1, zr, Blocks.chest, 0, 2, TragicItems.NetherStructureHook);
			}
			else if (rand.nextInt(16) == 0)
			{
				int xr = x + rand.nextInt(3) - rand.nextInt(3);
				int zr = z + rand.nextInt(3) - rand.nextInt(3);
				this.setBlock(world, xr, y + y1, zr, Blocks.mob_spawner, 0, 2, TragicConfig.getBoolean("allowArchangel") ? "TragicMC.Archangel" : "Ghast");
			}
		}

		return true;
	}

}
