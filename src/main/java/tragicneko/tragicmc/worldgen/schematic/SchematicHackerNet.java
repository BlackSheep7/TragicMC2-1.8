package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicHackerNet extends Schematic {

	public SchematicHackerNet(BlockPos pos, Structure str) {
		super(pos, str, 8, 14, 14);
	}

	@Override
	public Schematic generateStructure(int variant, World world, Random rand, int x, int y, int z) {

		final int iterations = 400 + rand.nextInt(200);
		this.setBlockToAir(world, x, y, z);

		for (int i = -14; i < 15; i++)
		{
			for (int j = -14; j < 15; j++)
			{
				for (int k = -14; k < 15; k++)
				{
					this.setBlockToAir(world, x + i, y + j, z + k);
				}
			}
		}

		for (int i = 0; i < iterations; i++)
		{
			this.setBlock(world, x + rand.nextInt(14) - rand.nextInt(14), y + rand.nextInt(14) - rand.nextInt(14), z + rand.nextInt(14) - rand.nextInt(14), TragicBlocks.DigitalSea, 0, 2);
		}

		for (int i = 0; i < 20; i++)
		{
			if (rand.nextBoolean())
			{
				int xr = x + rand.nextInt(14) - rand.nextInt(14);
				int yr = y + rand.nextInt(14) - rand.nextInt(14);
				int zr = z + rand.nextInt(14) - rand.nextInt(14);
				this.setBlock(world, xr, yr, zr, Blocks.mob_spawner, 0, 2, TragicConfig.getBoolean("allowHunter") ? "TragicMC.Hunter" : "Ghast");
			}
		}
		
		for (int i = 0; i < 10; i++)
		{
			if (rand.nextInt(4) == 0)
			{
				int xr = x + rand.nextInt(14) - rand.nextInt(14);
				int yr = y + rand.nextInt(14) - rand.nextInt(14);
				int zr = z + rand.nextInt(14) - rand.nextInt(14);
				this.setBlock(world, xr, yr, zr, Blocks.chest, 0, 2, TragicItems.NetherStructureHook);
			}
		}
		return this;
	}

}
