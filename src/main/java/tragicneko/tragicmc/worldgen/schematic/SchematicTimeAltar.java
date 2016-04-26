package tragicneko.tragicmc.worldgen.schematic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicTimeAltar extends Schematic {

	private static Block quartz = Blocks.quartz_block;
	private static Block crystal = TragicBlocks.StarCrystal;
	private static Block summon = TragicBlocks.SummonBlock;
	private static Block chest = Blocks.chest;

	public SchematicTimeAltar(BlockPos pos, Structure str) {
		super(pos, str, 5, 10, 10);
	}

	@Override
	public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {
		for (int y1 = 0; y1 < 6; y1++)
		{
			for (int x1 = -7; x1 < 8; x1++)
			{
				for (int z1 = -7; z1 < 8; z1++)
				{
					this.setBlockToAir(world, x + x1, y + y1, z + z1);
				}
			}
		}

		ArrayList<BlockPos> list;
		BlockPos coords;
		Block block;

		list = WorldHelper.getBlocksInCircularRange(world, 8.0D, x, y, z);

		for (int i = 0; i < list.size(); i++) //creates a giant circle of quartz with star crystal inside of it sparingly
		{
			coords = list.get(i);
			this.setBlock(world, coords.getX(), coords.getY(), coords.getZ(), quartz, 0, 2);
		}

		list = WorldHelper.getBlocksInCircularRange(world, 4.446D, x, y, z);

		for (int i = 0; i < list.size(); i++) //creates a smaller hemisphere of star crystal to provide lighting
		{
			coords = list.get(i);
			block = world.getBlockState(coords).getBlock();
			if (block == quartz) this.setBlock(world, coords.getX(), coords.getY(), coords.getZ(), crystal, variant, 2);
		}

		//Creates a structure in the middle that you can use to create another time controller, it comes with a summon block on top though
		this.setBlock(world, x, y + 1, z, quartz);
		this.setBlock(world, x, y + 2, z, quartz);
		this.setBlock(world, x, y + 2, z + 1, quartz);
		this.setBlock(world, x, y + 2, z - 1, quartz);
		this.setBlock(world, x + 1, y + 2, z, quartz);
		this.setBlock(world, x - 1, y + 2, z, quartz);

		this.setBlock(world, x, y + 3, z, summon, 7, 2);

		this.setBlock(world, x, y, z, chest, 0, 2, TragicItems.AwesomeChestHook);

		this.setBlock(world, x + 1, y, z, quartz); //blocks to ensure the chest is concealed
		this.setBlock(world, x - 1, y, z, quartz);
		this.setBlock(world, x, y, z + 1, quartz);
		this.setBlock(world, x, y, z - 1, quartz);

		return true;
	}
}
