package tragicneko.tragicmc.worldgen.schematic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicCelestialTemple extends Schematic {

	private static Block quartz = TragicBlocks.DarkenedQuartz;
	private static Block obs = TragicBlocks.DarkStone;
	private static Block glow = TragicBlocks.StarCrystal;
	private static Block chest = Blocks.chest;
	private static Block stone = TragicBlocks.DarkStone;
	private static Block summon = TragicBlocks.SummonBlock;
	
	private int variant = 0;

	public SchematicCelestialTemple(BlockPos origin, Structure str, World world) {
		super(origin, str, world, 12, 25, 25);
		this.variant = world.rand.nextInt(4);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z)
	{
		ArrayList<BlockPos> list;
		byte relays = (byte) (6 + (rand.nextInt(variant + 1) + 1) * (rand.nextInt(variant + 1) + 1));
		Block block;
		double regression = 0.88977745D;
		double cutoff = 0.48943755D;
		double size;
		ArrayList<BlockPos> cands = new ArrayList<BlockPos>();
		int yMax;
		int Xcoord;
		int Zcoord;
		int Ycoord;

		for (byte buzza = 0; buzza < relays; buzza++)
		{
			if (buzza == 0)
			{
				size = 15.0D;
				Xcoord = x + 4;
				Zcoord = z + 4;
				Ycoord = y;
				cutoff = 0.6278D;
				regression = 0.8876D;
			}
			else
			{
				size = rand.nextDouble() * 5.5D + 4.5D;
				Xcoord = x + 4 + rand.nextInt(10) - rand.nextInt(10);
				Zcoord = z + 4 + rand.nextInt(10) - rand.nextInt(10);
				Ycoord = y - rand.nextInt(2);
				regression = 0.88977745D;
				cutoff = 0.68943755D;
			}
			yMax = Ycoord;

			for (byte y1 = 0; y1 > -32; y1--)
			{
				if (size < cutoff) break;
				size *= regression;

				if (rand.nextInt(12) == 0)
				{
					Xcoord += rand.nextInt(2) - rand.nextInt(2);
					Zcoord += rand.nextInt(2) - rand.nextInt(2);
				}

				list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + y1, Zcoord);

				for (BlockPos coords2 : list)
				{
					if (!cands.contains(coords2))
					{
						if (yMax < coords2.getY()) yMax = coords2.getY();
						cands.add(coords2);
					}
				}
			}

			byte rnd = (byte) (rand.nextInt(3) + 1);

			for (BlockPos coords2 : cands)
			{
				if (coords2.getY() >= yMax)
				{
					this.setBlock(world, coords2.getX(), coords2.getY(), coords2.getZ(), TragicBlocks.AshenGrass, 0, 2);
				}
				else if (coords2.getY() >= yMax - rnd - rand.nextInt(2))
				{
					this.setBlock(world, coords2.getX(), coords2.getY(), coords2.getZ(), TragicBlocks.DeadDirt, 0, 2);
				}
				else
				{
					this.setBlock(world, coords2.getX(), coords2.getY(), coords2.getZ(), TragicBlocks.DarkStone, 0, 2);
				}
			}
		}

		for (byte y1 = 0; y1 < 10; y1++)
		{
			for (byte x1 = -8; x1 < 17; x1++)
			{
				for (byte z1 = -6; z1 < 15; z1++)
				{
					this.setBlockToAir(world, x + x1, y + y1 + 1, z + z1);
				}
			}
		}
		//First layer

		//Top row

		for (int i = 2; i < 8; i++)
		{
			this.setBlock(world, x - 7, y, z + i, quartz, 0, 2);
		}

		//Second row
		for (int i = 1; i < 9; i++)
		{
			this.setBlock(world, x - 6, y, z + i, quartz, 0, 2);
		}

		//Third row
		for (int i = -1; i < 11; i++)
		{
			this.setBlock(world, x - 5, y, z + i, quartz, 0, 2);
		}

		//Fourth row
		for (int i = -2; i < 12; i++)
		{
			this.setBlock(world, x - 4, y, z + i, quartz, 0, 2);
		}

		//Fifth row
		for (int i = -2; i < 12; i++)
		{
			this.setBlock(world, x - 3, y, z + i, quartz, 0, 2);
		}

		//Sixth row
		for (int i = -3; i < 13; i++)
		{
			this.setBlock(world, x - 2, y, z + i, quartz, 0, 2);
		}

		//Seventh row
		for (int i = -4; i < 14; i++)
		{
			this.setBlock(world, x - 1, y, z + i, quartz, 0, 2);
		}

		this.setBlock(world, x - 1, y, z, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 9, quartz, 1, 2);

		//Eighth row
		for (int i = -5; i < 15; i++)
		{
			this.setBlock(world, x, y, z + i, quartz, 0, 2);
		}

		this.setBlock(world, x, y, z + 4, obs, 0, 2);
		this.setBlock(world, x, y, z + 5, obs, 0, 2);

		//Ninth row
		for (int i = -5; i < 15; i++)
		{
			this.setBlock(world, x + 1, y, z + i, quartz, 0, 2);
		}

		this.setBlock(world, x + 1, y, z, quartz, 1, 2);
		this.setBlock(world, x + 1, y, z + 3, obs, 0, 2);
		this.setBlock(world, x + 1, y, z + 4, quartz, 1, 2);
		this.setBlock(world, x + 1, y, z + 5, quartz, 1, 2);
		this.setBlock(world, x + 1, y, z + 6, obs, 0, 2);
		this.setBlock(world, x + 1, y, z + 9, quartz, 1, 2);

		//Tenth row
		for (int i = -5; i < 15; i++)
		{
			this.setBlock(world, x + 2, y, z + i, quartz, 0, 2);
		}

		this.setBlock(world, x + 2, y, z, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 2, obs, 0, 2);
		this.setBlock(world, x + 2, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 4, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 5, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 7, obs, 0, 2);
		this.setBlock(world, x + 2, y, z + 9, quartz, 1, 2);

		//Eleventh row
		for (int i = -5; i < 15; i++)
		{
			this.setBlock(world, x + 3, y, z + i, quartz, 0, 2);
		}

		this.setBlock(world, x + 3, y, z, quartz, 1, 2);
		this.setBlock(world, x + 3, y, z + 2, obs, 0, 2);
		this.setBlock(world, x + 3, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x + 3, y, z + 4, obs, 0, 2);
		this.setBlock(world, x + 3, y, z + 5, obs, 0, 2);
		this.setBlock(world, x + 3, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x + 3, y, z + 7, obs, 0, 2);
		this.setBlock(world, x + 3, y, z + 9, quartz, 1, 2);

		//Twelfth row - This is the middle row
		for (int i = -5; i < 15; i++)
		{
			this.setBlock(world, x + 4, y, z + i, quartz, 0, 2);
		}

		this.setBlock(world, x + 4, y, z + 1, obs, 0, 2);
		this.setBlock(world, x + 4, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x + 4, y, z + 3, glow, 0, 2);
		this.setBlock(world, x + 4, y, z + 4, chest, 0, 2, ChestHooks.rareHook);
		this.setBlock(world, x + 4, y, z + 5, chest, 0, 2, ChestHooks.rareHook);
		this.setBlock(world, x + 4, y, z + 6, glow, 0, 2);
		this.setBlock(world, x + 4, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x + 4, y, z + 8, obs, 0, 2);

		//Thirteenth row
		for (int i = -5; i < 15; i++)
		{
			this.setBlock(world, x + 5, y, z + i, quartz, 0, 2);
		}

		this.setBlock(world, x + 5, y, z, quartz, 1, 2);
		this.setBlock(world, x + 5, y, z + 2, obs, 0, 2);
		this.setBlock(world, x + 5, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x + 5, y, z + 4, obs, 0, 2);
		this.setBlock(world, x + 5, y, z + 5, obs, 0, 2);
		this.setBlock(world, x + 5, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x + 5, y, z + 7, obs, 0, 2);
		this.setBlock(world, x + 5, y, z + 9, quartz, 1, 2);

		//Fourteenth row
		for (int i = -5; i < 15; i++)
		{
			this.setBlock(world, x + 6, y, z + i, quartz, 0, 2);
		}

		this.setBlock(world, x + 6, y, z, quartz, 1, 2);
		this.setBlock(world, x + 6, y, z + 2, obs, 0, 2);
		this.setBlock(world, x + 6, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x + 6, y, z + 4, quartz, 1, 2);
		this.setBlock(world, x + 6, y, z + 5, quartz, 1, 2);
		this.setBlock(world, x + 6, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x + 6, y, z + 7, obs, 0, 2);
		this.setBlock(world, x + 6, y, z + 9, quartz, 1, 2);

		//Fifteenth row
		for (int i = -5; i < 15; i++)
		{
			this.setBlock(world, x + 7, y, z + i, quartz, 0, 2);
		}

		this.setBlock(world, x + 7, y, z, quartz, 1, 2);
		this.setBlock(world, x + 7, y, z + 3, obs, 0, 2);
		this.setBlock(world, x + 7, y, z + 4, quartz, 1, 2);
		this.setBlock(world, x + 7, y, z + 5, quartz, 1, 2);
		this.setBlock(world, x + 7, y, z + 6, obs, 0, 2);
		this.setBlock(world, x + 7, y, z + 9, quartz, 1, 2);

		//Sixteenth row
		for (int i = -5; i < 15; i++)
		{
			this.setBlock(world, x + 8, y, z + i, quartz, 0, 2);
		}

		this.setBlock(world, x + 8, y, z + 4, obs, 0, 2);
		this.setBlock(world, x + 8, y, z + 5, obs, 0, 2);

		//Seventeenth row
		for (int i = -4; i < 14; i++)
		{
			this.setBlock(world, x + 9, y, z + i, quartz, 0, 2);
		}

		this.setBlock(world, x + 9, y, z, quartz, 1, 2);
		this.setBlock(world, x + 9, y, z + 9, quartz, 1, 2);

		//Eighteenth row
		for (int i = -3; i < 13; i++)
		{
			this.setBlock(world, x + 10, y, z + i, quartz, 0, 2);
		}

		//Nineteenth row
		for (int i = -2; i < 12; i++)
		{
			this.setBlock(world, x + 11, y, z + i, quartz, 0, 2);
		}

		//Twentieth row
		for (int i = -2; i < 12; i++)
		{
			this.setBlock(world, x + 12, y, z + i, quartz, 0, 2);
		}

		//Twenty-first row
		for (int i = -1; i < 11; i++)
		{
			this.setBlock(world, x + 13, y, z + i, quartz, 0, 2);
		}

		//Twenty-second row
		for (int i = 1; i < 9; i++)
		{
			this.setBlock(world, x + 14, y, z + i, quartz, 0, 2);
		}

		//Twenty-third row
		for (int i = 2; i < 8; i++)
		{
			this.setBlock(world, x + 15, y, z + i, quartz, 0, 2);
		}

		y++;
		//Second layer
		x -= 1;

		//First row
		this.setBlock(world, x - 6, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x - 6, y, z + 7, quartz, 1, 2);

		//Second row
		this.setBlock(world, x - 5, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x - 5, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x - 5, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x - 5, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x - 5, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x - 5, y, z + 8, quartz, 1, 2);

		//Third row
		this.setBlock(world, x - 4, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 8, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 10, quartz, 1, 2);

		//Fourth row
		this.setBlock(world, x - 3, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x - 3, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 3, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x - 3, y, z + 11, quartz, 1, 2);

		//Fifth row
		this.setBlock(world, x - 2, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x - 2, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 2, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x - 2, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x - 2, y, z + 12, quartz, 1, 2);

		//Sixth row
		this.setBlock(world, x - 1, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 12, quartz, 1, 2);

		//Seventh row
		this.setBlock(world, x, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x, y, z, quartz, 1, 2);
		this.setBlock(world, x, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x, y, z + 12, quartz, 1, 2);

		//Eighth row
		this.setBlock(world, x + 1, y, z - 4, quartz, 1, 2);
		this.setBlock(world, x + 1, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 1, y, z + 12, quartz, 1, 2);
		this.setBlock(world, x + 1, y, z + 13, quartz, 1, 2);

		//Ninth row
		this.setBlock(world, x + 2, y, z - 5, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z - 4, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 12, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 13, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 14, quartz, 1, 2);

		//Tenth row
		this.setBlock(world, x + 3, y, z - 4, quartz, 1, 2);
		this.setBlock(world, x + 3, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 3, y, z + 12, quartz, 1, 2);
		this.setBlock(world, x + 3, y, z + 13, quartz, 1, 2);

		//Eleventh row

		//Twelfth row - middle row
		this.setBlock(world, x + 5, y, z + 4, obs, 0, 2);
		this.setBlock(world, x + 5, y, z + 5, obs, 0, 2);

		//Thirteenth row

		//Fourteenth row
		this.setBlock(world, x + 7, y, z - 4, quartz, 1, 2);
		this.setBlock(world, x + 7, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 7, y, z + 12, quartz, 1, 2);
		this.setBlock(world, x + 7, y, z + 13, quartz, 1, 2);

		//Fifteenth row - 9
		this.setBlock(world, x + 8, y, z - 5, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z - 4, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z + 12, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z + 13, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z + 14, quartz, 1, 2);

		//Sixteenth row
		this.setBlock(world, x + 9, y, z - 4, quartz, 1, 2);
		this.setBlock(world, x + 9, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 9, y, z + 12, quartz, 1, 2);
		this.setBlock(world, x + 9, y, z + 13, quartz, 1, 2);

		//Seventeenth row
		this.setBlock(world, x + 10, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 12, quartz, 1, 2);

		//Eighteenth row - 6
		this.setBlock(world, x + 11, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 12, quartz, 1, 2);

		//Nineteenth row
		this.setBlock(world, x + 12, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 12, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 12, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 12, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x + 12, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x + 12, y, z + 12, quartz, 1, 2);

		//Twentieth row - 4
		this.setBlock(world, x + 13, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 13, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 13, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x + 13, y, z + 11, quartz, 1, 2);

		//Twenty-first row
		this.setBlock(world, x + 14, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 8, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 10, quartz, 1, 2);

		//Twenty-second row
		this.setBlock(world, x + 15, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x + 15, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x + 15, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x + 15, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x + 15, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x + 15, y, z + 8, quartz, 1, 2);

		//Twenty-third row
		this.setBlock(world, x + 16, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x + 16, y, z + 7, quartz, 1, 2);

		y++;

		//Third layer

		//First row

		//Second row
		this.setBlock(world, x - 5, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x - 5, y, z + 3, stone, 0, 2);
		this.setBlock(world, x - 5, y, z + 6, stone, 0, 2);
		this.setBlock(world, x - 5, y, z + 7, quartz, 1, 2);

		//Third row
		this.setBlock(world, x - 4, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 3, stone, 0, 2);
		this.setBlock(world, x - 4, y, z + 6, stone, 0, 2);
		this.setBlock(world, x - 4, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 8, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 10, quartz, 1, 2);

		//Fourth row
		this.setBlock(world, x - 3, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x - 3, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 3, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x - 3, y, z + 11, quartz, 1, 2);

		//Fifth row
		this.setBlock(world, x - 2, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 2, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x - 2, y, z + 11, quartz, 1, 2);

		//Sixth row
		this.setBlock(world, x - 1, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 12, quartz, 1, 2);

		//Seventh row
		this.setBlock(world, x, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x, y, z, quartz, 1, 2);
		this.setBlock(world, x, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x, y, z + 12, quartz, 1, 2);

		//Eighth row
		this.setBlock(world, x + 1, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 1, y, z + 12, quartz, 1, 2);

		//Ninth row
		this.setBlock(world, x + 2, y, z - 4, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 12, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 13, quartz, 1, 2);

		//Tenth row
		this.setBlock(world, x + 3, y, z - 4, stone, 0, 2);
		this.setBlock(world, x + 3, y, z - 3, stone, 0, 2);
		this.setBlock(world, x + 3, y, z + 12, stone, 0, 2);
		this.setBlock(world, x + 3, y, z + 13, stone, 0, 2);

		//Eleventh row

		//Twelfth row - middle row

		if (variant == 1)
		{
			this.setBlock(world, x + 5, y, z + 4, summon, 5, 2);
			this.setBlockToAir(world, x + 5, y, z + 5);
		}
		else if (variant == 2)
		{
			this.setBlock(world, x + 5, y, z + 4, summon, 5, 2);
			this.setBlock(world, x + 5, y, z + 5, summon, 5, 2);
		}
		else
		{
			this.setBlockToAir(world, x + 5, y, z + 4);
			this.setBlock(world, x + 5, y, z + 5, summon, 5, 2);
		}

		//Thirteenth row

		//Fourteenth row
		this.setBlock(world, x + 7, y, z - 4, stone, 0, 2);
		this.setBlock(world, x + 7, y, z - 3, stone, 0, 2);
		this.setBlock(world, x + 7, y, z + 12, stone, 0, 2);
		this.setBlock(world, x + 7, y, z + 13, stone, 0, 2);

		//Fifteenth row - 9
		this.setBlock(world, x + 8, y, z - 4, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z + 12, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z + 13, quartz, 1, 2);

		//Sixteenth row
		this.setBlock(world, x + 9, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 9, y, z + 12, quartz, 1, 2);

		//Seventeenth row
		this.setBlock(world, x + 10, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 12, quartz, 1, 2);

		//Eighteenth row - 6
		this.setBlock(world, x + 11, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 12, quartz, 1, 2);

		//Nineteenth row
		this.setBlock(world, x + 12, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 12, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 12, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x + 12, y, z + 11, quartz, 1, 2);

		//Twentieth row - 4
		this.setBlock(world, x + 13, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 13, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 13, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x + 13, y, z + 11, quartz, 1, 2);

		//Twenty-first row
		this.setBlock(world, x + 14, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 3, stone, 0, 2);
		this.setBlock(world, x + 14, y, z + 6, stone, 0, 2);
		this.setBlock(world, x + 14, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 8, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 10, quartz, 1, 2);

		//Twenty-second row
		this.setBlock(world, x + 15, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x + 15, y, z + 3, stone, 0, 2);
		this.setBlock(world, x + 15, y, z + 6, stone, 0, 2);
		this.setBlock(world, x + 15, y, z + 7, quartz, 1, 2);

		//Twenty-third row

		y++;

		//Fourth layer

		//First row

		//Second row
		this.setBlock(world, x - 5, y, z + 3, stone, 0, 2);
		this.setBlock(world, x - 5, y, z + 6, stone, 0, 2);

		//Third row
		this.setBlock(world, x - 4, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 3, stone, 0, 2);
		this.setBlock(world, x - 4, y, z + 6, stone, 0, 2);
		this.setBlock(world, x - 4, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 8, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 10, quartz, 1, 2);

		//Fourth row
		this.setBlock(world, x - 3, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 3, y, z + 10, quartz, 1, 2);

		//Fifth row
		this.setBlock(world, x - 2, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 2, y, z + 10, quartz, 1, 2);

		//Sixth row
		this.setBlock(world, x - 1, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 12, quartz, 1, 2);

		//Seventh row
		this.setBlock(world, x, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x, y, z, quartz, 1, 2);
		this.setBlock(world, x, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x, y, z + 12, quartz, 1, 2);

		//Eighth row
		this.setBlock(world, x + 1, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 1, y, z + 12, quartz, 1, 2);

		//Ninth row
		this.setBlock(world, x + 2, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 12, quartz, 1, 2);

		//Tenth row
		this.setBlock(world, x + 3, y, z - 4, stone, 0, 2);
		this.setBlock(world, x + 3, y, z - 3, stone, 0, 2);
		this.setBlock(world, x + 3, y, z + 12, stone, 0, 2);
		this.setBlock(world, x + 3, y, z + 13, stone, 0, 2);

		//Eleventh row

		//Twelfth row - middle row

		//Thirteenth row

		//Fourteenth row
		this.setBlock(world, x + 7, y, z - 4, stone, 0, 2);
		this.setBlock(world, x + 7, y, z - 3, stone, 0, 2);
		this.setBlock(world, x + 7, y, z + 12, stone, 0, 2);
		this.setBlock(world, x + 7, y, z + 13, stone, 0, 2);

		//Fifteenth row - 9
		this.setBlock(world, x + 8, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z + 12, quartz, 1, 2);

		//Sixteenth row
		this.setBlock(world, x + 9, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 9, y, z + 12, quartz, 1, 2);

		//Seventeenth row
		this.setBlock(world, x + 10, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 12, quartz, 1, 2);

		//Eighteenth row - 6
		this.setBlock(world, x + 11, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 12, quartz, 1, 2);

		//Nineteenth row
		this.setBlock(world, x + 12, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 12, y, z + 10, quartz, 1, 2);

		//Twentieth row - 4
		this.setBlock(world, x + 13, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 13, y, z + 10, quartz, 1, 2);

		//Twenty-first row
		this.setBlock(world, x + 14, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 3, stone, 0, 2);
		this.setBlock(world, x + 14, y, z + 6, stone, 0, 2);
		this.setBlock(world, x + 14, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 8, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 10, quartz, 1, 2);

		//Twenty-second row
		this.setBlock(world, x + 15, y, z + 3, stone, 0, 2);
		this.setBlock(world, x + 15, y, z + 6, stone, 0, 2);

		//Twenty-third row

		y++;

		//Fifth layer

		//First row

		//Second row
		this.setBlock(world, x - 5, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x - 5, y, z + 4, stone, 0, 2);
		this.setBlock(world, x - 5, y, z + 5, stone, 0, 2);
		this.setBlock(world, x - 5, y, z + 6, quartz, 1, 2);

		//Third row
		this.setBlock(world, x - 4, y, z, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 4, stone, 0, 2);
		this.setBlock(world, x - 4, y, z + 5, stone, 0, 2);
		this.setBlock(world, x - 4, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 8, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 9, quartz, 1, 2);

		//Fourth row
		this.setBlock(world, x - 3, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 3, y, z, quartz, 1, 2);
		this.setBlock(world, x - 3, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x - 3, y, z + 10, quartz, 1, 2);

		//Fifth row
		this.setBlock(world, x - 2, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 2, y, z + 10, quartz, 1, 2);

		//Sixth row
		this.setBlock(world, x - 1, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 11, quartz, 1, 2);

		//Seventh row
		this.setBlock(world, x, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x, y, z, quartz, 1, 2);
		this.setBlock(world, x, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x, y, z + 12, quartz, 1, 2);

		//Eighth row
		this.setBlock(world, x + 1, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 1, y, z + 12, quartz, 1, 2);

		//Ninth row
		this.setBlock(world, x + 2, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 12, quartz, 1, 2);

		//Tenth row
		this.setBlock(world, x + 3, y, z - 4, quartz, 1, 2);
		this.setBlock(world, x + 3, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 3, y, z + 12, quartz, 1, 2);
		this.setBlock(world, x + 3, y, z + 13, quartz, 1, 2);

		//Eleventh row
		this.setBlock(world, x + 4, y, z - 4, stone, 0, 2);
		this.setBlock(world, x + 4, y, z - 3, stone, 0, 2);
		this.setBlock(world, x + 4, y, z + 12, stone, 0, 2);
		this.setBlock(world, x + 4, y, z + 13, stone, 0, 2);

		//Twelfth row - middle row
		this.setBlock(world, x + 5, y, z - 4, stone, 0, 2);
		this.setBlock(world, x + 5, y, z - 3, stone, 0, 2);
		this.setBlock(world, x + 5, y, z + 12, stone, 0, 2);
		this.setBlock(world, x + 5, y, z + 13, stone, 0, 2);

		//Thirteenth row
		this.setBlock(world, x + 6, y, z - 4, stone, 0, 2);
		this.setBlock(world, x + 6, y, z - 3, stone, 0, 2);
		this.setBlock(world, x + 6, y, z + 12, stone, 0, 2);
		this.setBlock(world, x + 6, y, z + 13, stone, 0, 2);

		//Fourteenth row
		this.setBlock(world, x + 7, y, z - 4, quartz, 1, 2);
		this.setBlock(world, x + 7, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 7, y, z + 12, quartz, 1, 2);
		this.setBlock(world, x + 7, y, z + 13, quartz, 1, 2);

		//Fifteenth row - 9
		this.setBlock(world, x + 8, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z + 12, quartz, 1, 2);

		//Sixteenth row
		this.setBlock(world, x + 9, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 9, y, z + 12, quartz, 1, 2);

		//Seventeenth row
		this.setBlock(world, x + 10, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 11, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 12, quartz, 1, 2);

		//Eighteenth row - 6
		this.setBlock(world, x + 11, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 10, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 11, quartz, 1, 2);

		//Nineteenth row
		this.setBlock(world, x + 12, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 12, y, z + 10, quartz, 1, 2);

		//Twentieth row - 4
		this.setBlock(world, x + 13, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 13, y, z, quartz, 1, 2);
		this.setBlock(world, x + 13, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x + 13, y, z + 10, quartz, 1, 2);

		//Twenty-first row
		this.setBlock(world, x + 14, y, z, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 3, stone, 0, 2);
		this.setBlock(world, x + 14, y, z + 6, stone, 0, 2);
		this.setBlock(world, x + 14, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 8, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 9, quartz, 1, 2);

		//Twenty-second row
		this.setBlock(world, x + 15, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x + 15, y, z + 4, stone, 0, 2);
		this.setBlock(world, x + 15, y, z + 5, stone, 0, 2);
		this.setBlock(world, x + 15, y, z + 6, quartz, 1, 2);

		//Twenty-third row

		y++;

		//Sixth layer

		//Third row
		this.setBlock(world, x - 4, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 4, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 5, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x - 4, y, z + 8, quartz, 1, 2);

		//Fourth row
		this.setBlock(world, x - 3, y, z, quartz, 1, 2);
		this.setBlock(world, x - 3, y, z + 9, quartz, 1, 2);

		//Fifth row
		this.setBlock(world, x - 2, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 2, y, z + 10, quartz, 1, 2);

		//Sixth row
		this.setBlock(world, x - 1, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x - 1, y, z + 10, quartz, 1, 2);

		//Seventh row
		this.setBlock(world, x, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x, y, z, quartz, 1, 2);
		this.setBlock(world, x, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x, y, z + 11, quartz, 1, 2);

		//Eighth row
		this.setBlock(world, x + 1, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 1, y, z + 12, quartz, 1, 2);

		//Ninth row
		this.setBlock(world, x + 2, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 12, quartz, 1, 2);

		//Tenth row
		this.setBlock(world, x + 3, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 3, y, z + 12, quartz, 1, 2);

		//Eleventh row
		this.setBlock(world, x + 4, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 4, y, z + 12, quartz, 1, 2);

		//Twelfth row - middle row
		this.setBlock(world, x + 5, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 5, y, z + 12, quartz, 1, 2);

		//Thirteenth row
		this.setBlock(world, x + 6, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 6, y, z + 12, quartz, 1, 2);

		//Fourteenth row
		this.setBlock(world, x + 7, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 7, y, z + 12, quartz, 1, 2);

		//Fifteenth row - 9
		this.setBlock(world, x + 8, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z + 12, quartz, 1, 2);

		//Sixteenth row
		this.setBlock(world, x + 9, y, z - 3, quartz, 1, 2);
		this.setBlock(world, x + 9, y, z + 12, quartz, 1, 2);

		//Seventeenth row
		this.setBlock(world, x + 10, y, z - 2, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 9, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 11, quartz, 1, 2);

		//Eighteenth row - 6
		this.setBlock(world, x + 11, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 11, y, z + 10, quartz, 1, 2);

		//Nineteenth row
		this.setBlock(world, x + 12, y, z - 1, quartz, 1, 2);
		this.setBlock(world, x + 12, y, z + 10, quartz, 1, 2);

		//Twentieth row - 4
		this.setBlock(world, x + 13, y, z, quartz, 1, 2);
		this.setBlock(world, x + 13, y, z + 9, quartz, 1, 2);

		//Twenty-first row
		this.setBlock(world, x + 14, y, z, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 4, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 5, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 8, quartz, 1, 2);
		this.setBlock(world, x + 14, y, z + 9, quartz, 1, 2);

		y++;
		//Seventh layer

		//Seventh row
		this.setBlock(world, x, y, z, quartz, 1, 2);
		this.setBlock(world, x, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x, y, z + 4, quartz, 1, 2);
		this.setBlock(world, x, y, z + 5, quartz, 1, 2);
		this.setBlock(world, x, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x, y, z + 8, quartz, 1, 2);
		this.setBlock(world, x, y, z + 9, quartz, 1, 2);

		//Eighth row
		this.setBlock(world, x + 1, y, z, quartz, 1, 2);
		this.setBlock(world, x + 1, y, z + 9, quartz, 1, 2);

		//Ninth row
		this.setBlock(world, x + 2, y, z, quartz, 1, 2);
		this.setBlock(world, x + 2, y, z + 9, quartz, 1, 2);

		//Tenth row
		this.setBlock(world, x + 3, y, z, quartz, 1, 2);
		this.setBlock(world, x + 3, y, z + 9, quartz, 1, 2);

		//Eleventh row
		this.setBlock(world, x + 4, y, z, quartz, 1, 2);
		this.setBlock(world, x + 4, y, z + 9, quartz, 1, 2);

		//Twelfth row - middle row
		this.setBlock(world, x + 5, y, z, quartz, 1, 2);
		this.setBlock(world, x + 5, y, z + 9, quartz, 1, 2);

		//Thirteenth row
		this.setBlock(world, x + 6, y, z, quartz, 1, 2);
		this.setBlock(world, x + 6, y, z + 9, quartz, 1, 2);

		//Fourteenth row
		this.setBlock(world, x + 7, y, z, quartz, 1, 2);
		this.setBlock(world, x + 7, y, z + 9, quartz, 1, 2);

		//Fifteenth row - 9
		this.setBlock(world, x + 8, y, z, quartz, 1, 2);
		this.setBlock(world, x + 8, y, z + 9, quartz, 1, 2);

		//Sixteenth row
		this.setBlock(world, x + 9, y, z, quartz, 1, 2);
		this.setBlock(world, x + 9, y, z + 9, quartz, 1, 2);

		//Seventeenth row
		this.setBlock(world, x + 10, y, z, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 1, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 2, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 3, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 4, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 5, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 6, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 7, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 8, quartz, 1, 2);
		this.setBlock(world, x + 10, y, z + 9, quartz, 1, 2);

		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("variant", this.variant);
		return tag;
	}

	@Override
	public Schematic readFromNBT(NBTTagCompound tag) {
		this.variant = tag.getInteger("variant");
		return this;
	}
}
