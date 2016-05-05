package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicDeathCircle extends Schematic {

	private static Block fire = Blocks.fire;
	private static Block exCob = TragicBlocks.DarkCobblestone;
	private static Block darkCob = TragicBlocks.DarkCobblestone;
	private static Block summon = TragicBlocks.SummonBlock;
	private static Block chest = Blocks.chest;

	public SchematicDeathCircle(BlockPos pos, Structure str) {
		super(pos, str, 6, 11, 11);
	}

	@Override
	public Schematic generateStructure(int variant, World world, Random rand, int x, int y, int z)
	{
		for (int y1 = 0; y1 < 6; y1++)
		{
			for (int x1 = 0; x1 < 11;x1++)
			{
				for (int z1 = 0; z1 < 11; z1++)
				{
					this.setBlockToAir(world, x + x1 - 5, y + y1, z - 5 + z1);
				}
			}
		}

		//First layer

		//First row
		this.setBlock(world, x - 1, y, z + 8, darkCob);
		this.setBlock(world, x + 1, y, z + 8, darkCob);
		this.setBlock(world, x + 2, y, z + 8, darkCob);

		//Second row
		this.setBlock(world, x - 5, y, z + 7, darkCob);
		this.setBlock(world, x - 4, y, z + 7, darkCob);
		this.setBlock(world, x - 2, y, z + 7, darkCob);
		this.setBlock(world, x - 1, y, z + 7, darkCob);
		this.setBlock(world, x - 1, y, z + 7, darkCob);
		this.setBlock(world, x, y, z + 7, darkCob);
		this.setBlock(world, x + 1, y, z + 7, darkCob);
		this.setBlock(world, x + 2, y, z + 7, darkCob);
		this.setBlock(world, x + 3, y, z + 7, darkCob);

		//Third row
		this.setBlock(world, x - 4, y, z + 6, darkCob);
		this.setBlock(world, x - 3, y, z + 6, darkCob);
		this.setBlock(world, x - 2, y, z + 6, exCob, 1, 2);
		this.setBlock(world, x - 1, y, z + 6, darkCob);
		this.setBlock(world, x, y, z + 6, darkCob);
		this.setBlock(world, x + 1, y, z + 6, darkCob);
		this.setBlock(world, x + 2, y, z + 6, darkCob);
		this.setBlock(world, x + 3, y, z + 6, darkCob);

		//Fourth row
		this.setBlock(world, x - 5, y, z + 5, darkCob);
		this.setBlock(world, x - 4, y, z + 5, darkCob);
		this.setBlock(world, x - 3, y, z + 5, darkCob);
		this.setBlock(world, x - 2, y, z + 5, darkCob);
		this.setBlock(world, x - 1, y, z + 5, exCob, 1, 2);
		this.setBlock(world, x, y, z + 5, darkCob);
		this.setBlock(world, x + 1, y, z + 5, darkCob);
		this.setBlock(world, x + 2, y, z + 5, darkCob);

		//Fifth row
		this.setBlock(world, x - 10, y, z + 4, darkCob);
		this.setBlock(world, x - 9, y, z + 4, darkCob);
		this.setBlock(world, x - 6, y, z + 4, darkCob);
		this.setBlock(world, x - 5, y, z + 4, darkCob);
		this.setBlock(world, x - 4, y, z + 4, exCob, 1, 2);
		this.setBlock(world, x - 3, y, z + 4, exCob, 1, 2);
		this.setBlock(world, x - 2, y, z + 4, darkCob);
		this.setBlock(world, x - 1, y, z + 4, exCob, 1, 2);
		this.setBlock(world, x, y, z + 4, exCob, 1, 2);
		this.setBlock(world, x + 1, y, z + 4, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z + 4, exCob, 1, 2);
		this.setBlock(world, x + 3, y, z + 4, darkCob);
		this.setBlock(world, x + 4, y, z + 4, darkCob);
		this.setBlock(world, x + 5, y, z + 4, darkCob);
		this.setBlock(world, x + 6, y, z + 4, darkCob);

		//Sixth row
		this.setBlock(world, x - 8, y, z + 3, darkCob);
		this.setBlock(world, x - 7, y, z + 3, darkCob);
		this.setBlock(world, x - 6, y, z + 3, darkCob);
		this.setBlock(world, x - 5, y, z + 3, exCob, 1, 2);
		this.setBlock(world, x - 4, y, z + 3, darkCob);
		this.setBlock(world, x - 3, y, z + 3, darkCob);
		this.setBlock(world, x - 2, y, z + 3, exCob, 1, 2);
		this.setBlock(world, x - 1, y, z + 3, exCob, 1, 2);
		this.setBlock(world, x, y, z + 3, exCob, 1, 2);
		this.setBlock(world, x + 1, y, z + 3, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z + 3, exCob, 1, 2);
		this.setBlock(world, x + 3, y, z + 3, darkCob);
		this.setBlock(world, x + 4, y, z + 3, exCob, 1, 2);
		this.setBlock(world, x + 5, y, z + 3, darkCob);
		this.setBlock(world, x + 5, y, z + 3, darkCob);

		//Seventh row
		this.setBlock(world, x - 7, y, z + 2, darkCob);
		this.setBlock(world, x - 6, y, z + 2, darkCob);
		this.setBlock(world, x - 5, y, z + 2, darkCob);
		this.setBlock(world, x - 4, y, z + 2, darkCob);
		this.setBlock(world, x - 3, y, z + 2, exCob, 1, 2);
		this.setBlock(world, x - 2, y, z + 2, exCob, 1, 2);
		this.setBlock(world, x - 1, y, z + 2, exCob, 1, 2);
		this.setBlock(world, x, y, z + 2, exCob, 1, 2);
		this.setBlock(world, x + 1, y, z + 2, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z + 2, exCob, 1, 2);
		this.setBlock(world, x + 3, y, z + 2, exCob, 1, 2);
		this.setBlock(world, x + 4, y, z + 2, darkCob);
		this.setBlock(world, x + 5, y, z + 2, exCob, 1, 2);
		this.setBlock(world, x + 6, y, z + 2, darkCob);
		this.setBlock(world, x + 7, y, z + 2, darkCob);
		this.setBlock(world, x + 8, y, z + 2, darkCob);
		this.setBlock(world, x + 9, y, z + 2, darkCob);

		//Eighth row
		this.setBlock(world, x - 7, y, z + 1, darkCob);
		this.setBlock(world, x - 6, y, z + 1, darkCob);
		this.setBlock(world, x - 5, y, z + 1, darkCob);
		this.setBlock(world, x - 4, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x - 3, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x - 2, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x + 3, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x + 4, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x + 5, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x + 6, y, z + 1, darkCob);
		this.setBlock(world, x + 7, y, z + 1, darkCob);
		this.setBlock(world, x + 8, y, z + 1, darkCob);

		//Ninth row
		this.setBlock(world, x - 7, y, z, darkCob);
		this.setBlock(world, x - 6, y, z, darkCob);
		this.setBlock(world, x - 5, y, z, darkCob);
		this.setBlock(world, x - 4, y, z, exCob, 1, 2);
		this.setBlock(world, x - 3, y, z, exCob, 1, 2);
		this.setBlock(world, x - 2, y, z, exCob, 1, 2);
		this.setBlock(world, x - 1, y, z, exCob, 1, 2);
		this.setBlock(world, x, y, z, exCob, 1, 2);
		this.setBlock(world, x + 1, y, z, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z, exCob, 1, 2);
		this.setBlock(world, x + 3, y, z, exCob, 1, 2);
		this.setBlock(world, x + 4, y, z, exCob, 1, 2);
		this.setBlock(world, x + 5, y, z, darkCob);
		this.setBlock(world, x + 6, y, z, exCob, 1, 2);
		this.setBlock(world, x + 7, y, z, darkCob);
		this.setBlock(world, x + 8, y, z, darkCob);

		//Tenth row
		this.setBlock(world, x - 8, y, z - 1, darkCob);
		this.setBlock(world, x - 7, y, z - 1, darkCob);
		this.setBlock(world, x - 6, y, z - 1, darkCob);
		this.setBlock(world, x - 5, y, z - 1, exCob, 1, 2);
		this.setBlock(world, x - 4, y, z - 1, exCob, 1, 2);
		this.setBlock(world, x - 3, y, z - 1, exCob, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, exCob, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, exCob, 1, 2);
		this.setBlock(world, x, y, z - 1, exCob, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, exCob, 1, 2);
		this.setBlock(world, x + 3, y, z - 1, exCob, 1, 2);
		this.setBlock(world, x + 4, y, z - 1, exCob, 1, 2);
		this.setBlock(world, x + 5, y, z - 1, darkCob);
		this.setBlock(world, x + 6, y, z - 1, darkCob);
		this.setBlock(world, x + 7, y, z - 1, darkCob);

		//Eleventh row
		this.setBlock(world, x - 7, y, z - 2, darkCob);
		this.setBlock(world, x - 6, y, z - 2, darkCob);
		this.setBlock(world, x - 5, y, z - 2, darkCob);
		this.setBlock(world, x - 4, y, z - 2, darkCob);
		this.setBlock(world, x - 3, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x - 2, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x - 1, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x + 1, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x + 3, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x + 4, y, z - 2, darkCob);
		this.setBlock(world, x + 5, y, z - 2, darkCob);
		this.setBlock(world, x + 6, y, z - 2, darkCob);
		this.setBlock(world, x + 7, y, z - 2, darkCob);

		//Twelfth row
		this.setBlock(world, x - 6, y, z - 3, darkCob);
		this.setBlock(world, x - 5, y, z - 3, darkCob);
		this.setBlock(world, x - 4, y, z - 3, darkCob);
		this.setBlock(world, x - 3, y, z - 3, darkCob);
		this.setBlock(world, x - 2, y, z - 3, exCob, 1, 2);
		this.setBlock(world, x - 1, y, z - 3, exCob, 1, 2);
		this.setBlock(world, x, y, z - 3, exCob, 1, 2);
		this.setBlock(world, x + 1, y, z - 3, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z - 3, exCob, 1, 2);
		this.setBlock(world, x + 3, y, z - 3, darkCob);
		this.setBlock(world, x + 4, y, z - 3, darkCob);
		this.setBlock(world, x + 5, y, z - 3, darkCob);
		this.setBlock(world, x + 6, y, z - 3, darkCob);
		this.setBlock(world, x + 7, y, z - 3, darkCob);

		//Thirteenth row
		this.setBlock(world, x - 6, y, z - 4, darkCob);
		this.setBlock(world, x - 5, y, z - 4, darkCob);
		this.setBlock(world, x - 4, y, z - 4, darkCob);
		this.setBlock(world, x - 3, y, z - 4, exCob, 1, 2);
		this.setBlock(world, x - 2, y, z - 4, darkCob);
		this.setBlock(world, x - 1, y, z - 4, exCob, 1, 2);
		this.setBlock(world, x, y, z - 4, exCob, 1, 2);
		this.setBlock(world, x + 1, y, z - 4, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z - 4, darkCob);
		this.setBlock(world, x + 3, y, z - 4, exCob, 1, 2);
		this.setBlock(world, x + 4, y, z - 4, exCob, 1, 2);
		this.setBlock(world, x + 5, y, z - 4, darkCob);
		this.setBlock(world, x + 6, y, z - 4, darkCob);

		//Fourteenth row
		this.setBlock(world, x - 7, y, z - 5, darkCob);
		this.setBlock(world, x - 6, y, z - 5, darkCob);
		this.setBlock(world, x - 5, y, z - 5, darkCob);
		this.setBlock(world, x - 4, y, z - 5, darkCob);
		this.setBlock(world, x - 3, y, z - 5, darkCob);
		this.setBlock(world, x - 2, y, z - 5, darkCob);
		this.setBlock(world, x - 1, y, z - 5, exCob, 1, 2);
		this.setBlock(world, x, y, z - 5, exCob, 1, 2);
		this.setBlock(world, x + 1, y, z - 5, darkCob);
		this.setBlock(world, x + 2, y, z - 5, darkCob);
		this.setBlock(world, x + 3, y, z - 5, darkCob);
		this.setBlock(world, x + 4, y, z - 5, darkCob);
		this.setBlock(world, x + 5, y, z - 5, darkCob);
		this.setBlock(world, x + 6, y, z - 5, darkCob);
		this.setBlock(world, x + 7, y, z - 5, darkCob);

		//Fifteenth row
		this.setBlock(world, x - 8, y, z - 6, darkCob);
		this.setBlock(world, x - 5, y, z - 6, darkCob);
		this.setBlock(world, x - 4, y, z - 6, darkCob);
		this.setBlock(world, x - 3, y, z - 6, darkCob);
		this.setBlock(world, x - 2, y, z - 6, darkCob);
		this.setBlock(world, x - 1, y, z - 6, exCob, 1, 2);
		this.setBlock(world, x, y, z - 6, darkCob);
		this.setBlock(world, x + 1, y, z - 6, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z - 6, darkCob);
		this.setBlock(world, x + 3, y, z - 6, darkCob);
		this.setBlock(world, x + 4, y, z - 6, darkCob);
		this.setBlock(world, x + 8, y, z - 6, darkCob);

		//Sixteenth row
		this.setBlock(world, x - 3, y, z - 7, darkCob);
		this.setBlock(world, x - 2, y, z - 7, darkCob);
		this.setBlock(world, x - 1, y, z - 7, darkCob);
		this.setBlock(world, x, y, z - 7, darkCob);
		this.setBlock(world, x + 1, y, z - 7, darkCob);
		this.setBlock(world, x + 2, y, z - 7, darkCob);

		//Seventeeth row
		this.setBlock(world, x - 3, y, z - 8, darkCob);
		this.setBlock(world, x - 1, y, z - 8, darkCob);
		this.setBlock(world, x, y, z - 8, darkCob);
		this.setBlock(world, x + 1, y, z - 8, darkCob);

		//Eighteenth row
		this.setBlock(world, x - 4, y, z - 9, darkCob);
		this.setBlock(world, x + 2, y, z - 9, darkCob);

		//Second layer
		y++;

		this.setBlock(world, x + 1, y, z + 6, darkCob);

		this.setBlock(world, x - 1, y, z + 5, exCob, 1, 2);

		this.setBlock(world, x - 3, y, z + 4, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z + 4, exCob, 1, 2);

		this.setBlock(world, x - 1, y, z + 2, exCob, 1, 2);
		this.setBlock(world, x + 1, y, z + 2, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z + 2, exCob, 1, 2);
		this.setBlock(world, x + 5, y, z + 2, exCob, 1, 2);

		this.setBlock(world, x - 7, y, z + 1, darkCob);
		this.setBlock(world, x - 2, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, exCob, 1, 2);

		this.setBlock(world, x - 8, y, z, darkCob);
		this.setBlock(world, x, y, z, chest, 0, 2, ChestHooks.uncommonHook);

		this.setBlock(world, x - 4, y, z - 1, exCob, 1, 2);

		this.setBlock(world, x - 2, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x + 5, y, z - 2, darkCob);

		this.setBlock(world, x - 5, y, z - 3, darkCob);
		this.setBlock(world, x + 1, y, z - 3, exCob, 1, 2);
		this.setBlock(world, x + 5, y, z - 3, darkCob);
		this.setBlock(world, x + 6, y, z - 3, darkCob);

		this.setBlock(world, x - 6, y, z - 4, darkCob);

		this.setBlock(world, x - 1, y, z - 5, exCob, 1, 2);
		this.setBlock(world, x + 3, y, z - 5, darkCob);

		this.setBlock(world, x, y, z - 7, darkCob);

		this.setBlock(world, x + 1, y, z - 8, darkCob);

		//Third layer
		y++;

		this.setBlock(world, x - 1, y, z + 5, exCob, 1, 2);

		this.setBlock(world, x - 3, y, z + 4, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z + 4, exCob, 1, 2);

		this.setBlock(world, x - 1, y, z + 2, fire);
		this.setBlock(world, x + 1, y, z + 2, exCob, 1, 2);

		this.setBlock(world, x - 2, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, exCob, 1, 2);

		this.setBlock(world, x - 8, y, z, darkCob);
		this.setBlock(world, x, y, z, summon, 3, 2);

		this.setBlock(world, x - 2, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x + 5, y, z - 2, darkCob);

		this.setBlock(world, x - 5, y, z - 3, darkCob);
		this.setBlock(world, x + 6, y, z - 3, darkCob);

		this.setBlock(world, x - 6, y, z - 4, darkCob);

		this.setBlock(world, x - 1, y, z - 5, exCob, 1, 2);
		this.setBlock(world, x + 3, y, z - 5, fire);

		this.setBlock(world, x, y, z - 7, darkCob);

		this.setBlock(world, x + 1, y, z - 8, darkCob);

		//Fourth layer
		y++;

		this.setBlock(world, x - 3, y, z + 4, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z + 4, fire);

		this.setBlock(world, x + 1, y, z + 2, exCob, 1, 2);

		this.setBlock(world, x - 2, y, z + 1, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, exCob, 1, 2);

		this.setBlock(world, x - 8, y, z, darkCob);

		this.setBlock(world, x - 2, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x + 2, y, z - 2, exCob, 1, 2);
		this.setBlock(world, x + 5, y, z - 2, darkCob);

		this.setBlock(world, x - 5, y, z - 3, darkCob);

		this.setBlock(world, x - 1, y, z - 5, fire);

		this.setBlock(world, x + 1, y, z - 8, darkCob);

		//Fifth layer
		y++;

		this.setBlock(world, x - 3, y, z + 4, exCob, 1, 2);

		this.setBlock(world, x + 2, y, z + 1, exCob, 1, 2);

		this.setBlock(world, x - 8, y, z, fire);

		this.setBlock(world, x - 2, y, z - 2, fire);
		this.setBlock(world, x + 2, y, z - 2, fire);
		this.setBlock(world, x + 5, y, z - 2, darkCob);

		this.setBlock(world, x - 5, y, z - 3, darkCob);

		this.setBlock(world, x + 1, y, z - 8, darkCob);

		//Sixth layer
		y++;

		this.setBlock(world, x - 3, y, z + 4, fire);

		this.setBlock(world, x + 2, y, z + 1, exCob, 1, 2);

		this.setBlock(world, x + 5, y, z - 2, fire);

		this.setBlock(world, x - 5, y, z - 3, fire);

		this.setBlock(world, x + 1, y, z - 8, fire);

		//Seventh layer
		y++;

		this.setBlock(world, x + 2, y, z + 1, fire);

		return this;
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z)
	{
		return generateStructure(0, world, rand, x, y, z);
	}

	@Override
	public Schematic generateWithRandomVariant(int variantSize, World world, Random rand, int x, int y, int z) {
		return generateStructure(world, rand, x, y, z);
	}
}
