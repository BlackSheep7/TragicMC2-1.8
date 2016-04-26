package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicKitsuneDen extends Schematic {

	private static Block fox = TragicBlocks.SmoothNetherrack; //1 is chiseled, 2 is beveled, 3 is sculpted, 5 is molten
	private static Block chest = Blocks.chest;
	private static Block spawner = Blocks.mob_spawner;
	
	public SchematicKitsuneDen(BlockPos pos, Structure str) {
		super(pos, str, 12, 9, 9);
	}

	@Override
	public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {
		int dens = MathHelper.clamp_int(variant, 0, 4);

		for (int y1 = 0; y1 < 8; y1++)
		{
			for (int x1 = -5; x1 < 6; x1++)
			{
				for (int z1 = -5; z1 < 6; z1++)
				{
					if (y1 == 0)
					{
						this.setBlock(world, x + x1, y + y1, z + z1, fox, 1, 2);
					}
					else
					{
						this.setBlockToAir(world, x + x1, y + y1, z + z1);
					}
				}
			}
		}

		for (int y1 = -1; y1 < 2; y1++) //sets the middle inset layer of sculpted blocks for the spawner or summon block
		{
			for (int x1 = -2; x1 < 3; x1++)
			{
				for (int z1 = -2; z1 < 3; z1++)
				{
					if (y1 == -1)
					{
						this.setBlock(world, x + x1, y + y1, z + z1, fox, 3, 2);
					}
					else
					{
						this.setBlockToAir(world, x + x1, y + y1, z + z1);
					}
				}
			}
		}

		this.setBlock(world, x + 2, y, z + 2, fox, 3, 2); //sets the blocks on the lower layer to round it out
		this.setBlock(world, x - 2, y, z + 2, fox, 3, 2);
		this.setBlock(world, x + 2, y, z - 2, fox, 3, 2);
		this.setBlock(world, x - 2, y, z - 2, fox, 3, 2);

		this.setBlock(world, x, y, z, chest, 0, 2, TragicItems.NetherStructureHook);
		this.setBlock(world, x, y + 1, z, spawner, 0, 2, TragicConfig.getBoolean("allowKitsunakuma") ? "TragicMC.Kitsune" : "Blaze");

		for (int z1 = -1; z1 < 2; z1++) //sets the extra sculpted blocks on the 0 layer
		{
			this.setBlock(world, x + 3, y, z + z1, fox, 3, 2);
			this.setBlock(world, x - 3, y, z + z1, fox, 3, 2);
			this.setBlock(world, x + z1, y, z + 3, fox, 3, 2);
			this.setBlock(world, x + z1, y, z - 3, fox, 3, 2);
		}

		for (int z1 = -5; z1 < 6; z1++) //Sets the outer blocks as beveled
		{
			this.setBlock(world, x + 5, y, z + z1, fox, 2, 2);
			this.setBlock(world, x - 5, y, z + z1, fox, 2, 2);
			this.setBlock(world, x + z1, y, z + 5, fox, 2, 2);
			this.setBlock(world, x + z1, y, z - 5, fox, 2, 2);
		}

		this.setBlock(world, x + 4, y, z + 3, fox, 2, 2); //finishes setting the outer beveled netherrack
		this.setBlock(world, x + 4, y, z - 3, fox, 2, 2);
		this.setBlock(world, x - 4, y, z + 3, fox, 2, 2);
		this.setBlock(world, x - 4, y, z - 3, fox, 2, 2);
		this.setBlock(world, x + 3, y, z + 4, fox, 2, 2);
		this.setBlock(world, x + 3, y, z - 4, fox, 2, 2);
		this.setBlock(world, x - 3, y, z + 4, fox, 2, 2);
		this.setBlock(world, x - 3, y, z - 4, fox, 2, 2);


		for (int z1 = -5; z1 < -2; z1++) //Removes the corners
		{
			this.setBlockToAir(world, x + 5, y, z + z1);
			this.setBlockToAir(world, x - 5, y, z + z1);
			this.setBlockToAir(world, x + z1, y, z + 5);
			this.setBlockToAir(world, x + z1, y, z - 5);
		}

		for (int z1 = 3; z1 < 6; z1++) //Removes the opposite corners
		{
			this.setBlockToAir(world, x + 5, y, z + z1);
			this.setBlockToAir(world, x - 5, y, z + z1);
			this.setBlockToAir(world, x + z1, y, z + 5);
			this.setBlockToAir(world, x + z1, y, z - 5);
		}

		this.setBlockToAir(world, x + 4, y, z + 4); //finishes rounding out the corners
		this.setBlockToAir(world, x - 4, y, z + 4);
		this.setBlockToAir(world, x + 4, y, z - 4);
		this.setBlockToAir(world, x - 4, y, z - 4);

		for (int y1 = 1; y1 < 5; y1++)
		{
			this.setBlock(world, x + 5, y + y1, z + 3, fox, 2, 2); //sets the outer beveled edges
			this.setBlock(world, x - 5, y + y1, z + 3, fox, 2, 2);
			this.setBlock(world, x + 5, y + y1, z - 3, fox, 2, 2);
			this.setBlock(world, x - 5, y + y1, z - 3, fox, 2, 2);
			this.setBlock(world, x + 3, y + y1, z + 5, fox, 2, 2);
			this.setBlock(world, x - 3, y + y1, z + 5, fox, 2, 2);
			this.setBlock(world, x + 3, y + y1, z - 5, fox, 2, 2);
			this.setBlock(world, x - 3, y + y1, z - 5, fox, 2, 2);

			for (int z1 = -2; z1 < 3; z1++) //sets the large outer smooth netherrack
			{
				this.setBlock(world, x + 6, y + y1, z + z1, fox);
				this.setBlock(world, x - 6, y + y1, z + z1, fox);
				this.setBlock(world, x + z1, y + y1, z + 6, fox);
				this.setBlock(world, x + z1, y + y1, z - 6, fox);
			}

			this.setBlock(world, x + 4, y + y1, z + 4, fox); //these are for the spaces in between the columns
			this.setBlock(world, x + 4, y + y1, z - 4, fox);
			this.setBlock(world, x - 4, y + y1, z + 4, fox);
			this.setBlock(world, x - 4, y + y1, z - 4, fox);
		}

		for (int z1 = -2; z1 < 3; z1++) //sets the upper layer of beveled netherrack
		{
			this.setBlock(world, x + 5, y + 5, z + z1, fox, 2, 2);
			this.setBlock(world, x - 5, y + 5, z + z1, fox, 2, 2);
			this.setBlock(world, x + z1, y + 5, z + 5, fox, 2, 2);
			this.setBlock(world, x + z1, y + 5, z - 5, fox, 2, 2);
		}

		this.setBlock(world, x + 4, y + 5, z + 3, fox, 2, 2); //finishes setting the upper outer beveled netherrack
		this.setBlock(world, x + 4, y + 5, z - 3, fox, 2, 2);
		this.setBlock(world, x - 4, y + 5, z + 3, fox, 2, 2);
		this.setBlock(world, x - 4, y + 5, z - 3, fox, 2, 2);
		this.setBlock(world, x + 3, y + 5, z + 4, fox, 2, 2);
		this.setBlock(world, x + 3, y + 5, z - 4, fox, 2, 2);
		this.setBlock(world, x - 3, y + 5, z + 4, fox, 2, 2);
		this.setBlock(world, x - 3, y + 5, z - 4, fox, 2, 2);


		for (int x1 = -2; x1 < 3; x1++) //Sets all of the blocks for the top layer, to be replaced
		{
			for (int z1 = -4; z1 < 5; z1++)
			{
				this.setBlock(world, x + x1, y + 5, z + z1, fox, 1, 2);
			}
		}

		for (int x1 = -4; x1 < 5; x1++)
		{
			for (int z1 = -2; z1 < 3; z1++)
			{
				this.setBlock(world, x + x1, y + 5, z + z1, fox, 1, 2);
			}
		}

		this.setBlock(world, x + 3, y + 5, z + 3, fox, 1, 2); //Fills in the last four blocks to finish the top
		this.setBlock(world, x - 3, y + 5, z + 3, fox, 1, 2);
		this.setBlock(world, x + 3, y + 5, z - 3, fox, 1, 2);
		this.setBlock(world, x - 3, y + 5, z - 3, fox, 1, 2);

		for (int y1 = 5; y1 < 7; y1++)
		{
			for (int x1 = -2; x1 < 3; x1++)
			{
				for (int z1 = -2; z1 < 3; z1++)
				{
					if (y1 == 5)
					{
						this.setBlockToAir(world, x + x1, y + y1, z + z1);
					}
					else
					{
						this.setBlock(world, x + x1, y + y1, z + z1, fox, 3, 2);
					}
				}
			}
		}

		for (int z1 = -1; z1 < 2; z1++) //sets the extra sculpted blocks on the upper layer
		{
			this.setBlock(world, x + 3, y + 5, z + z1, fox, 3, 2);
			this.setBlock(world, x - 3, y + 5, z + z1, fox, 3, 2);
			this.setBlock(world, x + z1, y + 5, z + 3, fox, 3, 2);
			this.setBlock(world, x + z1, y + 5, z - 3, fox, 3, 2);
		}

		this.setBlock(world, x + 2, y + 5, z + 2, fox, 3, 2); //sets the blocks on the upper layer to round it out
		this.setBlock(world, x - 2, y + 5, z + 2, fox, 3, 2);
		this.setBlock(world, x + 2, y + 5, z - 2, fox, 3, 2);
		this.setBlock(world, x - 2, y + 5, z - 2, fox, 3, 2);

		this.setBlock(world, x, y + 6, z, fox, 5, 2); //sets the one light block in the middle on top, lights it up just enough to see but not enough to prevent spawning

		if (dens > 0)
		{
			this.generateDen(world, rand, x + 11, y, z);

			for (int y1 = 1; y1 < 3; y1++) //generates the doorway to the extra den
			{
				for (int z1 = -1; z1 < 2; z1++)
				{
					this.setBlockToAir(world, x + 6, y + y1, z + z1);
					this.setBlock(world, x + 6, y, z + z1, fox, 2, 2);
				}
			}
			this.setBlockToAir(world, x + 6, y + 3, z);

			if (dens > 1)
			{
				this.generateDen(world, rand, x - 11, y, z);

				for (int y1 = 1; y1 < 3; y1++) //generates the doorway to the extra den
				{
					for (int z1 = -1; z1 < 2; z1++)
					{
						this.setBlockToAir(world, x - 6, y + y1, z + z1);
						this.setBlock(world, x - 6, y, z + z1, fox, 2, 2);
					}
				}
				this.setBlockToAir(world, x - 6, y + 3, z);

				if (dens > 2)
				{
					this.generateDen(world, rand, x, y, z + 11);

					for (int y1 = 1; y1 < 3; y1++) //generates the doorway to the extra den
					{
						for (int z1 = -1; z1 < 2; z1++)
						{
							this.setBlockToAir(world, x + z1, y + y1, z + 6);
							this.setBlock(world, x + z1, y, z + 6, fox, 2, 2);
						}
					}
					this.setBlockToAir(world, x, y + 3, z + 6);

					if (dens > 3)
					{
						this.generateDen(world, rand, x, y, z - 11);

						for (int y1 = 1; y1 < 3; y1++) //generates the doorway to the extra den
						{
							for (int z1 = -1; z1 < 2; z1++)
							{
								this.setBlockToAir(world, x + z1, y + y1, z - 6);
								this.setBlock(world, x + z1, y, z - 6, fox, 2, 2);
							}
						}
						this.setBlockToAir(world, x, y + 3, z - 6);
					}
				}
			}
		}
		return true;
	}

	public void generateDen(World world, Random rand, int x, int y, int z) {

		for (int y1 = 0; y1 < 6; y1++)
		{
			for (int x1 = -4; x1 < 5; x1++)
			{
				for (int z1 = -4; z1 < 5; z1++)
				{
					if (y1 == 0)
					{
						this.setBlock(world, x + x1, y + y1, z + z1, fox, 1, 2);
					}
					else
					{
						this.setBlockToAir(world, x + x1, y + y1, z + z1);
					}
				}
			}
		}

		for (int y1 = -1; y1 < 2; y1++) //sets the middle inset layer of sculpted blocks for the spawner or summon block
		{
			for (int x1 = -1; x1 < 2; x1++)
			{
				for (int z1 = -1; z1 < 2; z1++)
				{
					if (y1 == -1)
					{
						this.setBlock(world, x + x1, y + y1, z + z1, fox, 3, 2);
					}
					else
					{
						this.setBlockToAir(world, x + x1, y + y1, z + z1);
					}
				}
			}
		}

		this.setBlock(world, x + 1, y, z + 1, fox, 3, 2); //sets the blocks on the lower layer to round it out
		this.setBlock(world, x - 1, y, z + 1, fox, 3, 2);
		this.setBlock(world, x + 1, y, z - 1, fox, 3, 2);
		this.setBlock(world, x - 1, y, z - 1, fox, 3, 2);

		this.setBlock(world, x, y, z, chest, 0, 2, TragicItems.NetherStructureHook);
		this.setBlock(world, x, y + 1, z, spawner, 0, 2, TragicConfig.getBoolean("allowJabba") ? "TragicMC.Jabba" : "Blaze");

		this.setBlock(world, x + 1, y, z, fox, 3, 2);
		this.setBlock(world, x - 1, y, z, fox, 3, 2);
		this.setBlock(world, x, y, z + 1, fox, 3, 2);
		this.setBlock(world, x, y, z - 1, fox, 3, 2);

		this.setBlock(world, x + 1, y, z + 1, fox, 3, 2);
		this.setBlock(world, x - 1, y, z + 1, fox, 3, 2);
		this.setBlock(world, x + 1, y, z + 1, fox, 3, 2);
		this.setBlock(world, x + 1, y, z - 1, fox, 3, 2);


		for (int z1 = -1; z1 < 2; z1++) //Sets the outer blocks as beveled
		{
			this.setBlock(world, x + 4, y, z + z1, fox, 2, 2);
			this.setBlock(world, x - 4, y, z + z1, fox, 2, 2);
			this.setBlock(world, x + z1, y, z + 4, fox, 2, 2);
			this.setBlock(world, x + z1, y, z - 4, fox, 2, 2);
		}


		this.setBlock(world, x + 3, y, z + 2, fox, 2, 2); //finishes setting the outer beveled netherrack
		this.setBlock(world, x + 3, y, z - 2, fox, 2, 2);
		this.setBlock(world, x - 3, y, z + 2, fox, 2, 2);
		this.setBlock(world, x - 3, y, z - 2, fox, 2, 2);
		this.setBlock(world, x + 2, y, z + 3, fox, 2, 2);
		this.setBlock(world, x + 2, y, z - 3, fox, 2, 2);
		this.setBlock(world, x - 2, y, z + 3, fox, 2, 2);
		this.setBlock(world, x - 2, y, z - 3, fox, 2, 2);

		for (int y1 = 1; y1 < 5; y1++) //sets up the walls
		{
			this.setBlock(world, x + 4, y + y1, z + 2, fox, 2, 2); //sets the outer beveled edges
			this.setBlock(world, x - 4, y + y1, z + 2, fox, 2, 2);
			this.setBlock(world, x + 4, y + y1, z - 2, fox, 2, 2);
			this.setBlock(world, x - 4, y + y1, z - 2, fox, 2, 2);
			this.setBlock(world, x + 2, y + y1, z + 4, fox, 2, 2);
			this.setBlock(world, x - 2, y + y1, z + 4, fox, 2, 2);
			this.setBlock(world, x + 2, y + y1, z - 4, fox, 2, 2);
			this.setBlock(world, x - 2, y + y1, z - 4, fox, 2, 2);

			for (int z1 = -1; z1 < 2; z1++) //sets the large outer smooth netherrack
			{
				this.setBlock(world, x + 5, y + y1, z + z1, fox);
				this.setBlock(world, x - 5, y + y1, z + z1, fox);
				this.setBlock(world, x + z1, y + y1, z + 5, fox);
				this.setBlock(world, x + z1, y + y1, z - 5, fox);
			}

			this.setBlock(world, x + 3, y + y1, z + 3, fox); //these are for the spaces in between the columns
			this.setBlock(world, x + 3, y + y1, z - 3, fox);
			this.setBlock(world, x - 3, y + y1, z + 3, fox);
			this.setBlock(world, x - 3, y + y1, z - 3, fox);
		}

		for (int z1 = -1; z1 < 2; z1++) //sets the upper layer of beveled netherrack
		{
			this.setBlock(world, x + 4, y + 5, z + z1, fox, 2, 2);
			this.setBlock(world, x - 4, y + 5, z + z1, fox, 2, 2);
			this.setBlock(world, x + z1, y + 5, z + 4, fox, 2, 2);
			this.setBlock(world, x + z1, y + 5, z - 4, fox, 2, 2);
		}

		this.setBlock(world, x + 3, y + 5, z + 2, fox, 2, 2); //finishes setting the upper outer beveled netherrack
		this.setBlock(world, x + 3, y + 5, z - 2, fox, 2, 2);
		this.setBlock(world, x - 3, y + 5, z + 2, fox, 2, 2);
		this.setBlock(world, x - 3, y + 5, z - 2, fox, 2, 2);
		this.setBlock(world, x + 2, y + 5, z + 3, fox, 2, 2);
		this.setBlock(world, x + 2, y + 5, z - 3, fox, 2, 2);
		this.setBlock(world, x - 2, y + 5, z + 3, fox, 2, 2);
		this.setBlock(world, x - 2, y + 5, z - 3, fox, 2, 2);


		for (int x1 = -1; x1 < 2; x1++) //Sets all of the blocks for the top layer, to be replaced
		{
			for (int z1 = -3; z1 < 4; z1++)
			{
				this.setBlock(world, x + x1, y + 5, z + z1, fox, 1, 2);
			}
		}

		for (int x1 = -3; x1 < 4; x1++)
		{
			for (int z1 = -1; z1 < 2; z1++)
			{
				this.setBlock(world, x + x1, y + 5, z + z1, fox, 1, 2);
			}
		}

		this.setBlock(world, x + 2, y + 5, z + 2, fox, 1, 2); //Fills in the last four blocks to finish the top
		this.setBlock(world, x - 2, y + 5, z + 2, fox, 1, 2);
		this.setBlock(world, x + 2, y + 5, z - 2, fox, 1, 2);
		this.setBlock(world, x - 2, y + 5, z - 2, fox, 1, 2);

		for (int y1 = 5; y1 < 7; y1++)
		{
			for (int x1 = -1; x1 < 2; x1++)
			{
				for (int z1 = -1; z1 < 2; z1++)
				{
					if (y1 == 5)
					{
						this.setBlockToAir(world, x + x1, y + y1, z + z1);
					}
					else
					{
						this.setBlock(world, x + x1, y + y1, z + z1, fox, 3, 2);
					}
				}
			}
		}

		this.setBlock(world, x + 2, y + 5, z, fox, 3, 2);
		this.setBlock(world, x - 2, y + 5, z, fox, 3, 2);
		this.setBlock(world, x, y + 5, z + 2, fox, 3, 2);
		this.setBlock(world, x, y + 5, z - 2, fox, 3, 2);

		this.setBlock(world, x + 2, y + 5, z + 1, fox, 3, 2);
		this.setBlock(world, x - 2, y + 5, z + 1, fox, 3, 2);
		this.setBlock(world, x + 1, y + 5, z + 2, fox, 3, 2);
		this.setBlock(world, x + 1, y + 5, z - 2, fox, 3, 2);


		this.setBlock(world, x + 1, y + 5, z + 1, fox, 3, 2); //sets the blocks on the upper layer to round it out
		this.setBlock(world, x - 1, y + 5, z + 1, fox, 3, 2);
		this.setBlock(world, x + 1, y + 5, z - 1, fox, 3, 2);
		this.setBlock(world, x - 1, y + 5, z - 1, fox, 3, 2);

		this.setBlock(world, x, y + 6, z, fox, 5, 2); //sets the one light block in the middle on top, lights it up just enough to see but not enough to prevent spawning
	}
}
