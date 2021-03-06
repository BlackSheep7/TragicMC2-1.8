package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicDarkHut extends Schematic {

	public SchematicDarkHut(BlockPos pos, Structure str, World world) {
		super(pos, str, world, 13, 5, 7);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {

		for (byte y1 = 0; y1 < 13; y1++)
		{
			for (byte x1 = -4; x1 < 5 && y1 > 2; x1++)
			{
				for (byte z1 = -4; z1 < 5; z1++)
				{
					this.setBlockToAir(world, x + x1, y + y1, z + z1);
				}
			}

			this.setBlock(world, x + 2, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
			this.setBlock(world, x - 3, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
			this.setBlock(world, x + 2, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
			this.setBlock(world, x - 3, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);

			if (y1 == 4)
			{
				for (byte x1 = -2; x1 < 3; x1++)
				{
					this.setBlock(world, x + x1, y + y1, z + 3, TragicBlocks.Darkwood, 4, 2);
					this.setBlock(world, x + x1, y + y1, z - 3, TragicBlocks.Darkwood, 4, 2);
				}

				for (byte x1 = -2; x1 < 3; x1++)
				{
					this.setBlock(world, x + 2, y + y1, z + x1, TragicBlocks.Darkwood, 0, 2);
					this.setBlock(world, x - 3, y + y1, z + x1, TragicBlocks.Darkwood, 0, 2);
				}

				this.setBlock(world, x + 2, y + y1, z + 4, TragicBlocks.Darkwood, 8, 2);
				this.setBlock(world, x - 3, y + y1, z + 4, TragicBlocks.Darkwood, 8, 2);
				this.setBlock(world, x + 2, y + y1, z - 4, TragicBlocks.Darkwood, 8, 2);
				this.setBlock(world, x - 3, y + y1, z - 4, TragicBlocks.Darkwood, 8, 2);

				this.setBlock(world, x + 3, y + y1, z + 3, TragicBlocks.Darkwood, 4, 2);
				this.setBlock(world, x - 4, y + y1, z + 3, TragicBlocks.Darkwood, 4, 2);
				this.setBlock(world, x + 3, y + y1, z - 3, TragicBlocks.Darkwood, 4, 2);
				this.setBlock(world, x - 4, y + y1, z - 3, TragicBlocks.Darkwood, 4, 2);

				this.setBlock(world, x + 3, y + y1, z + 1, TragicBlocks.Darkwood, 4, 2);
				this.setBlock(world, x - 4, y + y1, z + 1, TragicBlocks.Darkwood, 4, 2);
				this.setBlock(world, x + 3, y + y1, z - 1, TragicBlocks.Darkwood, 4, 2);
				this.setBlock(world, x - 4, y + y1, z - 1, TragicBlocks.Darkwood, 4, 2);

				this.setBlock(world, x, y + y1, z - 4, TragicBlocks.Darkwood, 8, 2);
				this.setBlock(world, x - 1, y + y1, z - 4, TragicBlocks.Darkwood, 8, 2);
				this.setBlock(world, x, y + y1, z - 5, TragicBlocks.Darkwood, 8, 2);
				this.setBlock(world, x - 1, y + y1, z - 5, TragicBlocks.Darkwood, 8, 2);

				this.setBlock(world, x, y + y1, z + 4, TragicBlocks.Darkwood, 8, 2);
				this.setBlock(world, x - 1, y + y1, z + 4, TragicBlocks.Darkwood, 8, 2);
			}
			else if (y1 == 5)
			{
				for (byte x1 = -2; x1 < 3; x1++)
				{
					this.setBlock(world, x + x1, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
					this.setBlock(world, x + x1, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
					this.setBlock(world, x + 2, y + y1, z + x1, TragicBlocks.Darkwood, 0, 2);
					this.setBlock(world, x - 3, y + y1, z + x1, TragicBlocks.Darkwood, 0, 2);
				}

				this.setBlock(world, x + 3, y + y1, z + 1, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x - 4, y + y1, z + 1, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x + 3, y + y1, z - 1, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x - 4, y + y1, z - 1, TragicBlocks.Darkwood, 0, 2);

				this.setBlock(world, x, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x - 1, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);

				this.setBlock(world, x + 1, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x - 2, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);

				for (byte x1 = -2; x1 < 2; x1++)
				{
					for (byte z1 = -2; z1 < 3; z1++)
					{
						this.setBlock(world, x + x1, y + y1, z + z1, TragicBlocks.DarkwoodPlanks, 0, 2);
					}
				}
			}
			else if (y1 == 6 || y1 == 8)
			{
				for (byte x1 = -2; x1 < 3; x1++)
				{
					this.setBlock(world, x + x1, y + y1, z + 3, TragicBlocks.DarkwoodPlanks, 0, 2);
					this.setBlock(world, x + 2, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
					this.setBlock(world, x - 3, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
				}

				this.setBlock(world, x - 2, y + y1, z - 3, TragicBlocks.DarkwoodPlanks, 0, 2);
				this.setBlock(world, x + 1, y + y1, z - 3, TragicBlocks.DarkwoodPlanks, 0, 2);

				if (y1 == 6)
				{
					this.setBlock(world, x - 2, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);
					this.setBlock(world, x + 1, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);

					this.setBlock(world, x + 1, y + y1, z - 2, Blocks.anvil, 2, 2);
					this.setBlock(world, x - 2, y + y1, z - 2, Blocks.trapped_chest, 0, 2, ChestHooks.commonHook);
					this.setBlock(world, x - 2, y + y1, z - 1, Blocks.trapped_chest, 0, 2, ChestHooks.commonHook);

					this.setBlock(world, x + 1, y + y1, z + 2, Blocks.crafting_table, 0, 2);

					this.setBlock(world, x, y + y1, z + 2, Blocks.bed, 8, 2);
					this.setBlock(world, x, y + y1, z + 1, Blocks.bed, 0, 2);
					this.setBlock(world, x - 1, y + y1, z + 2, Blocks.bed, 8, 2);
					this.setBlock(world, x - 1, y + y1, z + 1, Blocks.bed, 0, 2);

					this.setBlock(world, x - 2, y + y1, z + 2, Blocks.ender_chest, 3, 2);
				}
				else
				{
					for (byte x1 = -2; x1 < 2; x1++)
						this.setBlock(world, x + x1, y + y1, z - 3, TragicBlocks.DarkwoodPlanks, 0, 2);
				}
			}
			else if (y1 == 7)
			{
				this.setBlock(world, x - 2, y + y1, z + 3, TragicBlocks.DarkwoodPlanks, 0, 2);
				this.setBlock(world, x + 1, y + y1, z + 3, TragicBlocks.DarkwoodPlanks, 0, 2);

				this.setBlock(world, x - 2, y + y1, z - 3, TragicBlocks.DarkwoodPlanks, 0, 2);
				this.setBlock(world, x + 1, y + y1, z - 3, TragicBlocks.DarkwoodPlanks, 0, 2);

				this.setBlock(world, x - 3, y + y1, z + 2, TragicBlocks.DarkwoodPlanks, 0, 2);
				this.setBlock(world, x + 2, y + y1, z - 2, TragicBlocks.DarkwoodPlanks, 0, 2);

				this.setBlock(world, x - 3, y + y1, z - 2, TragicBlocks.DarkwoodPlanks, 0, 2);
				this.setBlock(world, x + 2, y + y1, z + 2, TragicBlocks.DarkwoodPlanks, 0, 2);
			}
			else if (y1 == 9)
			{
				for (byte x1 = -2; x1 < 3; x1++)
				{
					this.setBlock(world, x + x1, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
					this.setBlock(world, x + x1, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
				}

				for (byte x1 = -4; x1 < 4; x1++)
				{
					this.setBlock(world, x + x1, y + y1, z + 4, TragicBlocks.Darkwood, 4, 2);
					this.setBlock(world, x + x1, y + y1, z - 4, TragicBlocks.Darkwood, 4, 2);
				}

				for (byte x1 = -2; x1 < 3; x1++)
				{
					this.setBlock(world, x + 2, y + y1, z + x1, TragicBlocks.Darkwood, 8, 2);
					this.setBlock(world, x - 3, y + y1, z + x1, TragicBlocks.Darkwood, 8, 2);
				}

				this.setBlock(world, x + 2, y + y1, z + 5, TragicBlocks.Darkwood, 8, 2);
				this.setBlock(world, x - 3, y + y1, z + 5, TragicBlocks.Darkwood, 8, 2);
				this.setBlock(world, x + 2, y + y1, z - 5, TragicBlocks.Darkwood, 8, 2);
				this.setBlock(world, x - 3, y + y1, z - 5, TragicBlocks.Darkwood, 8, 2);

				this.setBlock(world, x + 3, y + y1, z + 1, TragicBlocks.Darkwood, 4, 2);
				this.setBlock(world, x - 4, y + y1, z + 1, TragicBlocks.Darkwood, 4, 2);
				this.setBlock(world, x + 3, y + y1, z - 1, TragicBlocks.Darkwood, 4, 2);
				this.setBlock(world, x - 4, y + y1, z - 1, TragicBlocks.Darkwood, 4, 2);
				
				for (byte x1 = -2; x1 < 2; x1++)
				{
					this.setBlock(world, x + x1, y + y1, z - 1, TragicBlocks.Darkwood, 4, 2);
					this.setBlock(world, x + x1, y + y1, z + 1, TragicBlocks.Darkwood, 4, 2);
				}
			}
			else if (y1 == 10)
			{
				for (byte x1 = -2; x1 < 3; x1++)
				{
					this.setBlock(world, x + 1, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
					this.setBlock(world, x - 2, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
				}

				for (byte x1 = -2; x1 < 2; x1++)
				{
					this.setBlock(world, x + x1, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
					this.setBlock(world, x + x1, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
				}

				this.setBlock(world, x + 3, y + y1, z + 1, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x - 4, y + y1, z + 1, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x + 3, y + y1, z - 1, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x - 4, y + y1, z - 1, TragicBlocks.Darkwood, 0, 2);

				this.setBlock(world, x - 1, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x, y + y1, z - 4, TragicBlocks.Darkwood, 0, 2);

				this.setBlock(world, x - 1, y + y1, z + 4, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x, y + y1, z + 4, TragicBlocks.Darkwood, 0, 2);
				
				this.setBlock(world, x - 1, y + y1, z + 1, Blocks.mob_spawner, 0, 2, "Witch");
				this.setBlock(world, x, y + y1, z + 1, Blocks.mob_spawner, 0, 2, "Witch");
				this.setBlock(world, x - 1, y + y1, z - 1, Blocks.mob_spawner, 0, 2, "Witch");
				this.setBlock(world, x, y + y1, z - 1, Blocks.mob_spawner, 0, 2, "Witch");
			}
			else if (y1 == 11)
			{
				for (byte x1 = -2; x1 < 3; x1++)
				{
					this.setBlock(world, x, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
					this.setBlock(world, x - 1, y + y1, z + x1, TragicBlocks.DarkwoodPlanks, 0, 2);
				}

				for (byte x1 = -2; x1 < 2; x1++)
				{
					this.setBlock(world, x + x1, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
					this.setBlock(world, x + x1, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
				}
			}
			else if (y1 == 12)
			{
				this.setBlock(world, x - 1, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x, y + y1, z - 3, TragicBlocks.Darkwood, 0, 2);

				this.setBlock(world, x - 1, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
				this.setBlock(world, x, y + y1, z + 3, TragicBlocks.Darkwood, 0, 2);
			}
		}

		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		return null;
	}

	@Override
	public Schematic readFromNBT(NBTTagCompound tag) {
		return null;
	}

}
