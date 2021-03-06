package tragicneko.tragicmc.worldgen.schematic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicObsidianCavern extends Schematic {

	private static Block obs = Blocks.obsidian;
	private static Block bedrock = Blocks.bedrock;
	private static Block ladder = Blocks.ladder;
	private static Block summon = TragicBlocks.SummonBlock;
	private static Block chest = Blocks.chest;
	private static Block glowstone = Blocks.glowstone;
	private static Block spawner = Blocks.mob_spawner;
	
	private int variant = 0;
	private int variant2 = 0;

	public SchematicObsidianCavern(BlockPos pos, Structure str, World world) {
		super(pos, str, world, 8, 12, 12);
		variant = world.rand.nextInt(7);
		variant2 = world.rand.nextInt(10);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x,	int y, int z) {
		generateTube(world, rand, x, y, z);
		generateCavernOfferings(world, rand, x, y, z);
		generateCaveOpening(world, rand, x, y, z);
		return this;
	}

	public void generateTube(World world, Random rand, int x, int y, int z) {

		int starty = y;
		y = 0;

		for (int wah = 0; wah < 19; wah++) //To ensure the floor of the cavern is full bedrock
		{
			for (int wuh = 0; wuh < 19; wuh++)
			{
				this.setBlock(world, x + wah - 9, y, z + wuh - 9, bedrock);
			}
		}

		y = 1;

		for (int who = 0; who < 7; who++)
		{
			for (int wah = 0; wah < 19; wah++)
			{
				for (int wuh = 0; wuh < 19; wuh++) //To make sure the cavern is completely empty before generation
				{
					this.setBlockToAir(world, x + wah - 9, y + who, z + wuh - 9);
				}
			}

			for (int x1 = 0; x1 < 19; x1++) //To generate the bedrock walls around the cavern
			{
				this.setBlock(world, x + x1 - 9, y + who, z - 9, bedrock);
				this.setBlock(world, x - 9, y + who, z + x1 - 9, bedrock);
				this.setBlock(world, x + 18 - 9, y + who, z + x1 - 9, bedrock);
				this.setBlock(world, x + x1 - 9, y + who, z + 18 - 9, bedrock);
			}
		}

		y = 7;

		for (int who = 0; who < 5; who++)
		{
			for (int wah = 0; wah < 11; wah++)
			{
				for (int wuh = 0; wuh < 11; wuh++) //To make sure the cavern is completely empty before generation
				{
					this.setBlockToAir(world, x + wah - 5, y + who, z + wuh - 5);
				}
			}

			for (int x1 = 0; x1 < 13; x1++) //To generate the bedrock walls around the cavern
			{
				this.setBlock(world, x + x1 - 6, y + who, z - 6, bedrock);
				this.setBlock(world, x - 6, y + who, z + x1 - 6, bedrock);
				this.setBlock(world, x + 12 - 6, y + who, z + x1 - 6, bedrock);
				this.setBlock(world, x + x1 - 6, y + who, z + 12 - 6, bedrock);
			}

			for (int x1 = 0; x1 < 15; x1++) //To generate the bedrock walls around the cavern, 2nd outer layer
			{
				this.setBlock(world, x + x1 - 7, y + who, z - 7, bedrock);
				this.setBlock(world, x - 7, y + who, z + x1 - 7, bedrock);
				this.setBlock(world, x + 14 - 7, y + who, z + x1 - 7, bedrock);
				this.setBlock(world, x + x1 - 7, y + who, z + 14 - 7, bedrock);
			}

			for (int x1 = 0; x1 < 17; x1++) //To generate the bedrock walls around the cavern, 3rd outer layer
			{
				this.setBlock(world, x + x1 - 8, y + who, z - 8, bedrock);
				this.setBlock(world, x - 8, y + who, z + x1 - 8, bedrock);
				this.setBlock(world, x + 16 - 8, y + who, z + x1 - 8, bedrock);
				this.setBlock(world, x + x1 - 8, y + who, z + 16 - 8, bedrock);
			}
		}

		y = 12;

		for (int who = 0; who < 2; who++)
		{
			for (int wah = 0; wah < 7; wah++)
			{
				for (int wuh = 0; wuh < 7; wuh++) //To make sure the cavern is completely empty before generation
				{
					this.setBlockToAir(world, x + wah - 3, y + who, z + wuh - 3);
				}
			}

			for (int x1 = 0; x1 < 9; x1++) //To generate the bedrock walls around the cavern
			{
				this.setBlock(world, x + x1 - 4, y + who, z - 4, bedrock);
				this.setBlock(world, x - 4, y + who, z + x1 - 4, bedrock);
				this.setBlock(world, x + 8 - 4, y + who, z + x1 - 4, bedrock);
				this.setBlock(world, x + x1 - 4, y + who, z + 8 - 4, bedrock);
			}

			for (int x1 = 0; x1 < 11; x1++) //To generate the bedrock walls around the cavern, outer layer
			{
				this.setBlock(world, x + x1 - 5, y + who, z - 5, bedrock);
				this.setBlock(world, x - 5, y + who, z + x1 - 5, bedrock);
				this.setBlock(world, x + 10 - 5, y + who, z + x1 - 5, bedrock);
				this.setBlock(world, x + x1 - 5, y + who, z + 10 - 5, bedrock);
			}
		}

		y = 13;

		for (int wah = 0; wah < 5; wah++)
		{
			for (int wuh = 0; wuh < 5; wuh++) //To make sure the cavern is completely empty before generation
			{
				this.setBlockToAir(world, x + wah - 2, y, z + wuh - 2);
			}
		}

		for (int x1 = 0; x1 < 7; x1++) //To generate the bedrock walls around the cavern
		{
			this.setBlock(world, x + x1 - 3, y, z - 3, bedrock);
			this.setBlock(world, x - 3, y, z + x1 - 3, bedrock);
			this.setBlock(world, x + 6 - 3, y, z + x1 - 3, bedrock);
			this.setBlock(world, x + x1 - 3, y, z + 6 - 3, bedrock);
		}

		y = 14;

		for (int who = 0; who < 2; who++)
		{
			for (int wah = 0; wah < 3; wah++)
			{
				for (int wuh = 0; wuh < 3; wuh++) //To make sure the cavern is completely empty before generation
				{
					this.setBlockToAir(world, x + wah - 1, y + who, z + wuh - 1);
				}
			}

			for (int x1 = 0; x1 < 5; x1++) //To generate the bedrock walls around the cavern
			{
				this.setBlock(world, x + x1 - 2, y + who, z - 2, bedrock);
				this.setBlock(world, x - 2, y + who, z + x1 - 2, bedrock);
				this.setBlock(world, x + 4 - 2, y + who, z + x1 - 2, bedrock);
				this.setBlock(world, x + x1 - 2, y + who, z + 4 - 2, bedrock);
			}
		}

		y = 16;

		for (int who = 0; y + who <= starty; who++)
		{
			for (int wah = 0; wah < 3; wah++)
			{
				for (int wuh = 0; wuh < 3; wuh++) //To make sure the tube is completely air before generating obsidian
				{
					this.setBlockToAir(world, x + wah - 1, y + who, z + wuh - 1);
				}
			}

			for (int x1 = 0; x1 < 3; x1++) //To generate the obsidian tube
			{
				
				this.setBlock(world, x + x1 - 1, y + who, z - 1, obs);
				this.setBlock(world, x - 1, y + who, z + x1 - 1, obs);
				this.setBlock(world, x + 2 - 1, y + who, z + x1 - 1, obs);
				this.setBlock(world, x + x1 - 1, y + who, z + 2 - 1, obs);

				if ((y + who) % 12 == 0 && y + who != starty)
				{
					final int i = rand.nextInt(3);
					this.setBlock(world, x, y + who, z - 1, TragicBlocks.ObsidianVariant, i, 3);
					this.setBlock(world, x - 1, y + who, z, TragicBlocks.ObsidianVariant, i, 3);
					this.setBlock(world, x + 1, y + who, z, TragicBlocks.ObsidianVariant, i, 3);
					this.setBlock(world, x, y + who, z + 1, TragicBlocks.ObsidianVariant, i, 3);
				}
			}
		}

		for (int i = 0; y + i <= starty; i++)
		{
			this.setBlock(world, x, y + i, z, ladder, 3, 2);
		}

		//Glowstone in the large caverns to allow some visibility if the player doesn't h4ck their g4mma or have night vision on
		this.setBlock(world, x + 8, 6, z + 8, glowstone);
		this.setBlock(world, x + 8, 6, z - 8, glowstone);
		this.setBlock(world, x - 8, 6, z + 8, glowstone);
		this.setBlock(world, x - 8, 6, z - 8, glowstone);

		this.setBlock(world, x + 5, 11, z + 5, glowstone);
		this.setBlock(world, x + 5, 11, z - 5, glowstone);
		this.setBlock(world, x - 5, 11, z + 5, glowstone);
		this.setBlock(world, x - 5, 11, z - 5, glowstone);
	}

	public void generateCaveOpening(World world, Random rand, int x, int y, int z) {

		Block luxury = SchematicDesertTower.luxuryBlocks[rand.nextInt(SchematicDesertTower.luxuryBlocks.length)];
		int meta = 0;

		if (luxury == TragicBlocks.CompactOre)
		{
			meta = rand.nextInt(5);
		}

		switch(variant)
		{
		case 0:
			for (int y1 = 0; y1 < 4; y1++)
			{
				for (int x1 = -1; x1 < 2; x1++)
				{
					for (int z1 = -1; z1 < 2; z1++)
					{
						this.setBlock(world, x + x1, y + y1, z + z1, obs);
					}
				}
			}

			for (int y1 = 1; y1 < 3; y1++)
			{
				this.setBlockToAir(world, x + 1, y + y1, z);
				this.setBlockToAir(world, x - 1, y + y1, z);
				this.setBlockToAir(world, x, y + y1, z + 1);
				this.setBlockToAir(world, x, y + y1, z - 1);
				this.setBlockToAir(world, x, y + y1, z);
			}

			for (int x1 = -1; x1 < 2; x1++)
			{
				this.setBlock(world, x + x1, y + 4, z, obs);
				this.setBlock(world, x, y + 4, z + x1, obs);

				this.setBlock(world, x + 2, y, z + x1, obs);
				this.setBlock(world, x - 2, y, z + x1, obs);
				this.setBlock(world, x + x1, y, z - 2, obs);
				this.setBlock(world, x + x1, y, z + 2, obs);
			}

			this.setBlock(world, x, y + 4, z, luxury, meta, 2);
			this.setBlock(world, x, y + 5, z, obs);
			this.setBlock(world, x, y + 6, z, obs);
			break;
		case 1:
			for (int y1 = 0; y1 < 8; y1++) //Creates first block of obsidian for the middle of the head
			{
				for (int x1 = -1; x1 < 1; x1++)
				{
					for (int z1 = -1; z1 < 2; z1++)
					{
						this.setBlock(world, x + x1, y + y1, z + z1, obs);
					}
				}
			}

			this.setBlockToAir(world, x, y + 4, z);
			this.setBlockToAir(world, x, y + 5, z + 1);
			this.setBlockToAir(world, x, y + 5, z - 1);
			this.setBlock(world, x - 1, y + 5, z + 1, luxury, meta, 2);
			this.setBlock(world, x - 1, y + 5, z - 1, luxury, meta, 2); //Creates the eye sockets and places the luxury blocks for the eyes

			for (int x1 = -1; x1 < 2; x1++) //Creates the inner "teeth" next to the tube opening
			{
				this.setBlock(world, x + x1, y, z - 2, obs);
				this.setBlock(world, x + x1, y, z + 2, obs);

				if (x1 == 0)
				{
					this.setBlock(world, x + x1, y + 1, z - 2, obs);
					this.setBlock(world, x + x1, y + 1, z + 2, obs);
				}

				if (x1 == -1)
				{
					this.setBlock(world, x + x1, y + 2, z - 2, obs);
					this.setBlock(world, x + x1, y + 2, z + 2, obs);
					this.setBlock(world, x + x1, y + 1, z - 2, obs);
					this.setBlock(world, x + x1, y + 1, z + 2, obs);
				}
			}

			for (int y1 = 3; y1 < 7; y1++)
			{
				for (int x1 = -1; x1 < 1; x1++)
				{
					this.setBlock(world, x + x1, y + y1, z - 2, obs);
					this.setBlock(world, x + x1, y + y1, z + 2, obs);
				}
			}

			for (int y1 = 0; y1 < 8; y1++) //Creates the back layer of the head
			{
				this.setBlock(world, x - 2, y + y1, z + 1, obs);
				this.setBlock(world, x - 2, y + y1, z, obs);
				this.setBlock(world, x - 2, y + y1, z - 1, obs);
			}

			this.setBlock(world, x - 2, y + 5, z - 2, obs); //sets extra blocks on the back of the head
			this.setBlock(world, x - 2, y + 5, z + 2, obs);
			this.setBlockToAir(world, x - 2, y + 7, z - 1);
			this.setBlockToAir(world, x - 2, y + 7, z + 1);
			this.setBlock(world, x - 2, y + 7, z, obs);

			this.setBlockToAir(world, x, y + 1, z); //Creates the open 2x3x2 space to enter the tube
			this.setBlockToAir(world, x, y + 1, z - 1);
			this.setBlockToAir(world, x, y + 1, z + 1);
			this.setBlockToAir(world, x, y + 2, z);
			this.setBlockToAir(world, x, y + 2, z - 1);
			this.setBlockToAir(world, x, y + 2, z + 1);
			this.setBlockToAir(world, x, y + 3, z + 2);
			this.setBlockToAir(world, x, y + 3, z - 2);

			for (int y1 = 0; y1 < 3; y1++) //Creates the outer "teeth"
			{
				for (int z1 = -2; z1 < 3; z1++)
				{
					if (y1 != 0)
					{
						if (z1 != 0)
						{
							this.setBlock(world, x + 2, y + y1, z + z1, obs);
						}
					}
					else
					{
						this.setBlock(world, x + 2, y + y1, z + z1, obs);
					}
				}
			}
			break;
		case 2:
			for (int y1 = 0; y1 < 6; y1++)
			{
				for (int x1 = -1; x1 < 1; x1++)
				{
					for (int z1 = -1; z1 < 2; z1++)
					{
						this.setBlock(world, x + x1, y + y1, z + z1, obs); //Generates the basic 2 x 3 x 5 slab to carve out of
					}
				}
			}

			this.setBlockToAir(world, x, y + 1, z);
			this.setBlockToAir(world, x, y + 2, z); //Clears out the space to enter the tube, sets the eye
			this.setBlock(world, x, y + 4, z, luxury, meta, 2);

			this.setBlock(world, x + 1, y + 3, z, obs); //sets the obsidian blocks that are in front of the eye (the "goggles")
			this.setBlock(world, x + 1, y + 4, z - 1, obs);
			this.setBlock(world, x + 1, y + 4, z + 1, obs);
			this.setBlock(world, x + 1, y + 5, z, obs);

			this.setBlock(world, x, y + 4, z - 2, obs); //sets the obsidian blocks on the side of the head that represent the goggles
			this.setBlock(world, x, y + 4, z + 2, obs);
			this.setBlock(world, x - 1, y + 4, z - 2, obs);
			this.setBlock(world, x - 1, y + 4, z + 2, obs);

			this.setBlock(world, x, y + 6, z, obs); //Sets the blocks on the top of the head that represent the goggles
			this.setBlock(world, x - 1, y + 6, z, obs);

			for (int y1 = 2; y1 < 6; y1++)
			{
				this.setBlock(world, x - 2, y + y1, z, obs); //sets the blocks on the back of the head for the goggles
			}
			this.setBlock(world, x - 2, y + 4, z - 1, obs);
			this.setBlock(world, x - 2, y + 4, z + 1, obs);

			this.setBlock(world, x + 1, y + 1, z - 1, obs); //sets the two obsidian blocks near the tube opening (duh)
			this.setBlock(world, x + 1, y + 1, z + 1, obs);
			break;
		case 3:
			for (int z1 = -1; z1 < 2; z1++)
			{
				this.setBlock(world, x - 1, y, z + z1, obs); //Sets the blocks in front of and behind the starting block, the lower mouth
				this.setBlock(world, x + 1, y, z + z1, obs);
				this.setBlock(world, x + 2, y, z + z1, obs);

				for (int x1 = -2; x1 < 5; x1++)
				{
					this.setBlock(world, x + x1, y + 3, z + z1, obs); //sets the main blocks that make up the upper part of the mouth and middle part of the head
				}
			}

			for (int y1 = 0; y1 < 5; y1++)
			{
				for (int z1 = -1; z1 < 2; z1++) //sets the blocks that make up the center of the head
				{
					if (y1 > 0 && z1 != 0)
					{
						this.setBlock(world, x, y + y1, z + z1, obs);
					}

					this.setBlock(world, x - 1, y + y1, z + z1, obs);
				}
			}

			this.setBlock(world, x + 2, y + 1, z + 1, obs); //Teeth
			this.setBlock(world, x + 2, y + 1, z - 1, obs);
			this.setBlock(world, x + 4, y + 2, z + 1, obs);
			this.setBlock(world, x + 4, y + 2, z - 1, obs);

			for (int x1 = -3; x1 < 5; x1++) //Upper snout
			{
				this.setBlock(world, x + x1, y + 4, z, obs);
			}

			this.setBlock(world, x - 3, y + 5, z, obs); //Tip of middle spike on back
			this.setBlock(world, x, y + 6, z, obs); //Tip of top spike on head

			this.setBlock(world, x + 4, y + 4, z + 1, obs); //tip of the nose
			this.setBlock(world, x + 4, y + 4, z - 1, obs);

			this.setBlock(world, x + 1, y + 4, z + 1, luxury, meta, 2); //eyes
			this.setBlock(world, x + 1, y + 4, z - 1, luxury, meta, 2);

			this.setBlock(world, x + 1, y + 4, z - 2, obs); //blocks around the eyes
			this.setBlock(world, x + 1, y + 4, z + 2, obs);
			this.setBlock(world, x + 1, y + 5, z + 1, obs);
			this.setBlock(world, x + 1, y + 5, z - 1, obs);

			for (int x1 = -1; x1 < 1; x1++) //Blocks behind the eyes
			{
				this.setBlock(world, x + x1, y + 4, z + 1, obs);
				this.setBlock(world, x + x1, y + 4, z - 1, obs);
			}

			for (int x1 = -1; x1 < 3; x1++) //Middle spike on the top of the head
			{
				this.setBlock(world, x + x1, y + 5, z, obs);

				if (x1 < 2)
				{
					this.setBlock(world, x + x1, y + 3, z - 2, obs); //The sides of the mouth on the head
					this.setBlock(world, x + x1, y + 3, z + 2, obs);
				}
			}

			for (int x1 = -4; x1 < -1; x1++) //The lower spike on the back
			{
				this.setBlock(world, x + x1, y + 1, z, obs);
				if (x1 > -4)
				{
					this.setBlock(world, x + x1, y + 2, z, obs);
					this.setBlock(world, x + x1, y , z, obs);
				}
			}
			break;
		case 4:
			for (int y1 = 1; y1 < 6; y1++)
			{
				for (int z1 = -1; z1 < 2; z1++)
				{
					this.setBlock(world, x - 1, y + y1, z + z1, obs); //central blocks in the head

					if (z1 != 0)
					{
						if (y1 == 1 || y1 == 2)
						{
							this.setBlock(world, x + 1, y + y1, z + z1, obs);
						}

						if (y1 == 1)
						{
							this.setBlock(world, x + 2, y + y1, z + z1, obs);
						}
					}

					if (y1 > 3)
					{
						this.setBlock(world, x, y + y1, z + z1, obs);
					}
					else if (z1 != 0)
					{
						this.setBlock(world, x, y + y1, z + z1, obs);
					}

					if (y1 == 3)
					{
						for (int x1 = 0; x1 < 4; x1++) //upper mouth or snout
						{
							this.setBlock(world, x + x1, y + y1, z + z1, obs);
						}
					}

					if (y1 == 4 && z1 == 0)
					{
						for (int x1 = 0; x1 < 3; x1++)
						{
							this.setBlock(world, x + x1, y + y1, z, obs); //top snout
						}
					}
				}
			}

			this.setBlock(world, x, y + 4, z -1, luxury, meta, 2); //The eyes
			this.setBlock(world, x, y + 4, z + 1, luxury, meta, 2);

			this.setBlock(world, x, y + 4, z - 2, obs); //blocks around the eyes and on the side of the head
			this.setBlock(world, x, y + 4, z + 2, obs);
			this.setBlock(world, x - 1, y + 4, z - 2, obs);
			this.setBlock(world, x + 1, y + 4, z - 2, obs);
			this.setBlock(world, x - 1, y + 4, z + 2, obs);
			this.setBlock(world, x + 1, y + 4, z + 2, obs);
			this.setBlock(world, x, y + 5, z - 2, obs);
			this.setBlock(world, x, y + 5, z + 2, obs);
			this.setBlock(world, x, y + 3, z - 2, obs);
			this.setBlock(world, x, y + 3, z + 2, obs);

			for (int z1 = -1; z1 < 2; z1++) //forehead and lower jaw
			{
				this.setBlock(world, x + 1, y + 5, z + z1, obs);
				this.setBlock(world, x + 2, y, z + z1, obs);
			}

			this.setBlockToAir(world, x - 1, y + 5, z);

			this.setBlock(world, x - 1, y + 5, z - 2, obs);
			this.setBlock(world, x - 1, y + 5, z + 2, obs);
			this.setBlock(world, x - 1, y + 6, z - 2, obs);
			this.setBlock(world, x - 1, y + 6, z + 2, obs);

			for (int y1 = 5; y1 < 8; y1++)
			{
				this.setBlock(world, x - 1, y + y1, z - 3, obs);
				this.setBlock(world, x - 1, y + y1, z + 3, obs);
			}

			this.setBlock(world, x - 2, y + 6, z - 3, obs);
			this.setBlock(world, x - 2, y + 6, z + 3, obs);

			this.setBlock(world, x - 2, y + 7, z - 3, obs);
			this.setBlock(world, x - 2, y + 7, z + 3, obs);
			this.setBlock(world, x - 2, y + 7, z - 4, obs);
			this.setBlock(world, x - 2, y + 7, z + 4, obs);

			this.setBlock(world, x - 2, y + 8, z + 4, obs);
			this.setBlock(world, x - 2, y + 8, z - 4, obs);
			this.setBlock(world, x - 3, y + 8, z + 4, obs);
			this.setBlock(world, x - 3, y + 8, z - 4, obs);
			break;
		default:
			for (int y1 = 0; y1 < 3; y1++)
			{
				switch(y1)
				{
				case 0:
					for (int x1 = -3; x1 < 4; x1++)
					{
						for (int z1 = -3; z1 < 4; z1++)
						{
							this.setBlock(world, x + x1, y + y1, z + z1, obs);
						}
					}
					break;
				case 1:
					for (int x1 = -2; x1 < 3; x1++)
					{
						for (int z1 = -2; z1 < 3; z1++)
						{
							this.setBlock(world, x + x1, y + y1, z + z1, obs);
						}
					}
					break;
				case 2:
					for (int x1 = -1; x1 < 2; x1++)
					{
						for (int z1 = -1; z1 < 2; z1++)
						{
							if (x1 != 0 && z1 != 0)
							{
								this.setBlock(world, x + x1, y + y1, z + z1, obs);
							}
						}
					}
					break;
				}
			}

			for (int y1 = 0; y1 < 3; y1++)
			{
				this.setBlock(world, x + 1, y + y1, z, obs);
				this.setBlock(world, x - 1, y + y1, z, obs);
				this.setBlock(world, x, y + y1, z + 1, obs);
				this.setBlock(world, x, y + y1, z - 1, obs);
				this.setBlock(world, x, y + y1, z, ladder, 3, 2);
			}
			break;
		}

		this.setBlockToAir(world, x, y, z);
	}

	public void generateCavernOfferings(World world, Random rand, int x, int y, int z) {
		switch(variant2)
		{
		case 0:
			ArrayList<BlockPos> list = WorldHelper.getBlocksInCircularRange(world, 3.0D, x, 0, z);
			BlockPos coords;
			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				this.setBlockToAir(world, coords.getX(), coords.getY(), coords.getZ());
			}
			this.setBlock(world, x, 0, z, bedrock);
			this.setBlock(world, x, 1, z, chest, 0, 2, this.getChestHooks(1));
			break;
		case 1:
			this.setBlock(world, x, 1, z, bedrock);
			this.setBlock(world, x + 1, 1, z, chest, 0, 2, this.getChestHooks(0));
			this.setBlock(world, x - 1, 1, z, chest, 0, 2, this.getChestHooks(0));
			this.setBlock(world, x, 1, z + 1, chest, 0, 2, this.getChestHooks(0));
			this.setBlock(world, x, 1, z - 1, chest, 0, 2, this.getChestHooks(0));
			break;
		case 2:
			this.setBlock(world, x, 1, z, bedrock);

			int wubwub = rand.nextInt(4) + 1;
			int xerces = rand.nextInt(10);

			if (xerces == 1)
			{
				xerces = 0;
			}

			if (xerces >= 7)
			{
				xerces = 6;
			}

			this.setBlock(world, x + 5, 1, z + 5, summon, xerces, 2);
			this.setBlock(world, x + 1, 1, z, chest, 0, 2, this.getChestHooks(2));

			if (wubwub > 1)
			{
				xerces = rand.nextInt(10);

				if (xerces == 1)
				{
					xerces = 0;
				}

				if (xerces >= 7)
				{
					xerces = 6;
				}

				this.setBlock(world, x - 5, 1, z + 5, summon, xerces, 2);
				this.setBlock(world, x - 1, 1, z, chest, 0, 2, this.getChestHooks(2));

				if (wubwub > 2)
				{
					xerces = rand.nextInt(10);

					if (xerces == 1)
					{
						xerces = 0;
					}

					if (xerces >= 7)
					{
						xerces = 6;
					}

					this.setBlock(world, x - 5, 1, z - 5, summon, xerces, 2);
					this.setBlock(world, x, 1, z + 1, chest, 0, 2, this.getChestHooks(2));

					if (wubwub > 3)
					{
						xerces = rand.nextInt(10);

						if (xerces == 1)
						{
							xerces = 0;
						}

						if (xerces >= 7)
						{
							xerces = 6;
						}

						this.setBlock(world, x + 5, 1, z - 5, summon, xerces, 2);
						this.setBlock(world, x, 1, z - 1, chest, 0, 2, this.getChestHooks(2));
					}
				}
			}
			break;
		case 3:
			int mow = rand.nextInt(10);

			if (mow == 1)
			{
				mow = 0;
			}

			if (mow >= 7)
			{
				mow = 6;
			}

			this.setBlock(world, x, 1, z, summon, mow, 2);
			break;
		case 4:
			this.setBlock(world, x, 1, z, spawner, 0, 2, this.getRandomEntityNameForSpawner(rand.nextInt(10)));
			break;
		case 5:
			this.setBlock(world, x, 1, z, bedrock);

			int zera = rand.nextInt(4) + 1;
			int oppa = rand.nextInt(10);

			this.setBlock(world, x + 5, 1, z + 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
			this.setBlock(world, x + 1, 1, z, chest, 0, 2, this.getChestHooks(0));
			oppa = rand.nextInt(10);

			if (zera > 1)
			{

				this.setBlock(world, x - 5, 1, z + 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
				this.setBlock(world, x - 1, 1, z, chest, 0, 2, this.getChestHooks(0));
				oppa = rand.nextInt(10);

				if (zera > 2)
				{

					this.setBlock(world, x - 5, 1, z - 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
					this.setBlock(world, x, 1, z + 1, chest, 0, 2, this.getChestHooks(0));
					oppa = rand.nextInt(10);

					if (zera > 3)
					{
						this.setBlock(world, x + 5, 1, z - 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
						this.setBlock(world, x, 1, z - 1, chest, 0, 2, this.getChestHooks(0));
					}
				}
			}
			break;
		case 6:
			this.setBlock(world, x, 1, z, chest, 0, 2, this.getChestHooks(1));
			this.setBlock(world, x, 2, z, spawner, 0, 2, this.getRandomEntityNameForSpawner(rand.nextInt(10)));
			break;
		case 7:
			int mrow = rand.nextInt(10);

			if (mrow == 1)
			{
				mrow = 0;
			}

			if (mrow >= 7)
			{
				mrow = 6;
			}

			this.setBlock(world, x, 1, z, chest, 0, 2, this.getChestHooks(0));
			this.setBlock(world, x, 2, z, summon, mrow, 2);

			zera = rand.nextInt(4) + 1;
			oppa = rand.nextInt(10);

			this.setBlock(world, x + 5, 1, z + 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
			oppa = rand.nextInt(10);

			if (zera > 1)
			{
				this.setBlock(world, x - 5, 1, z + 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
				oppa = rand.nextInt(10);

				if (zera > 2)
				{
					this.setBlock(world, x - 5, 1, z - 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
					oppa = rand.nextInt(10);

					if (zera > 3)
					{
						this.setBlock(world, x + 5, 1, z - 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
					}
				}
			}
			break;
		case 8:
			mrow = rand.nextInt(10);

			if (mrow == 1)
			{
				mrow = 0;
			}

			if (mrow >= 7)
			{
				mrow = 6;
			}

			this.setBlock(world, x, 1, z, chest, 0, 2, this.getChestHooks(2));

			Block luxury = SchematicDesertTower.luxuryBlocks[rand.nextInt(SchematicDesertTower.luxuryBlocks.length)];
			int meta = 0;

			if (luxury == TragicBlocks.CompactOre)
			{
				meta = rand.nextInt(5);
			}

			this.setBlock(world, x, 2, z, luxury, meta, 2);
			this.setBlock(world, x, 3, z, summon, mrow, 2);

			zera = rand.nextInt(4) + 1;
			oppa = rand.nextInt(10);

			this.setBlock(world, x + 5, 1, z + 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
			oppa = rand.nextInt(10);

			if (zera > 1)
			{
				this.setBlock(world, x - 5, 1, z + 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
				oppa = rand.nextInt(10);

				if (zera > 2)
				{

					this.setBlock(world, x - 5, 1, z - 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
					oppa = rand.nextInt(10);

					if (zera > 3)
					{
						this.setBlock(world, x + 5, 1, z - 5, spawner, 0, 2, this.getRandomEntityNameForSpawner(oppa));
					}
				}
			}
			break;
		default:
			this.setBlock(world, x, 1, z, chest, 0, 2, this.getChestHooks(1));
			break;
		}
	}

	public ChestGenHooks getChestHooks(int flag) {
		ChestGenHooks hook = ChestHooks.commonHook;
		if (flag == 0)
		{
			hook = ChestHooks.rareHook;
		}
		else if (flag == 1)
		{
			hook = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
		}
		else if (flag == 2)
		{
			hook = ChestHooks.epicHook;
		}
		return hook;
	}

	public String getRandomEntityNameForSpawner(int i)
	{
		String s = TragicConfig.getBoolean("allowPlague") ? "TragicMC.Plague" : "Blaze";

		switch(i)
		{
		case 0:
			s = TragicConfig.getBoolean("allowMinotaur") ? "TragicMC.Minotaur" : this.getRandomVanillaEntityNameForSpawner(i);
			break;
		case 1:
			s = TragicConfig.getBoolean("allowInkling") ? "TragicMC.Inkling" : this.getRandomVanillaEntityNameForSpawner(i);
			break;
		case 2:
			s = TragicConfig.getBoolean("allowJabba") ? "TragicMC.Jabba" : this.getRandomVanillaEntityNameForSpawner(i);
			break;
		case 3:
			s = TragicConfig.getBoolean("allowNorVox") ? "TragicMC.NorVox" : this.getRandomVanillaEntityNameForSpawner(i);
			break;
		case 4:
			s = TragicConfig.getBoolean("allowRagr") ? "TragicMC.Ragr" : this.getRandomVanillaEntityNameForSpawner(i);
			break;
		case 5:
			s = TragicConfig.getBoolean("allowTox") ? "TragicMC.Tox" : this.getRandomVanillaEntityNameForSpawner(i);
			break;
		case 6:
			s = TragicConfig.getBoolean("allowGragul") ? "TragicMC.Gragul" : this.getRandomVanillaEntityNameForSpawner(i);
			break;
		case 7:
			s = TragicConfig.getBoolean("allowJarra") ? "TragicMC.Jarra" : this.getRandomVanillaEntityNameForSpawner(i);
			break;
		case 8:
			s = TragicConfig.getBoolean("allowKragul") ? "TragicMC.Kragul" : this.getRandomVanillaEntityNameForSpawner(i);
			break;
		default:
			break;
		}
		return s;
	}

	public String getRandomVanillaEntityNameForSpawner(int i)
	{
		String s = "Skeleton";

		switch(i)
		{
		case 0:
			s = "Zombie";
			break;
		case 1:
			s = "Enderman";
			break;
		case 2:
			s = "Slime";
			break;
		case 3:
			s = "Blaze";
			break;
		case 4:
			s = "Spider";
			break;
		case 5:
			s = "Witch";
			break;
		default:
			break;
		}
		return s;
	}

	public String getRandomBossNameForSpawner(int i)
	{
		String s = TragicConfig.getBoolean("allowApis") ? "TragicMC.Apis" : "Enderman";
		switch(i)
		{
		case 0:
			s = TragicConfig.getBoolean("allowEmpariah") ? "TragicMC.Yeti" : "Enderman";
			break;
		case 1:
			s = TragicConfig.getBoolean("allowKitsunakuma") ? "TragicMC.Kitsune" : "Enderman";
			break;
		case 2:
			s = TragicConfig.getBoolean("allowPolaris") ? "TragicMC.Polaris" : "Enderman";
			break;
		default:
			break;
		}
		return s;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("variant", this.variant);
		tag.setInteger("variant2", this.variant2);
		return tag;
	}

	@Override
	public Schematic readFromNBT(NBTTagCompound tag) {
		this.variant = tag.getInteger("variant");
		this.variant2 = tag.getInteger("variant2");
		return this;
	}
}
