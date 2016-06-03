package tragicneko.tragicmc.worldgen.schematic;

import static tragicneko.tragicmc.TragicBlocks.NekitePlate;
import static tragicneko.tragicmc.TragicBlocks.NekiteWire;

import java.util.HashSet;
import java.util.Random;

import com.google.common.collect.Sets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicNekoidsMansion extends Schematic {

	//for the lights in the walls, this is used for negative and positive as it is symmetrical 
	private static final byte[] BIT_MASK_X = new byte[] { 
			1, 0, 0,
			1, 0, 0,
			1, 0, 0,
			1, 0, 0
	};
	
	//for the lights in the walls, this offsets the actual coordinate offsets so that they will all be positive since it is asymmetric
	private static final byte[] BIT_MASK_Z = new byte[] {
			0, 0, 1, 0, 0,
			1, 0, 0, 1, 0,
			0, 1, 0, 0, 1,
			0, 1, 0, 0, 1,
			0, 0, 1, 0, 0,
			1, 0, 0, 1, 0,
			0
	};

	public SchematicNekoidsMansion(BlockPos origin, Structure structure, World world) {
		super(origin, structure, world, 16, 24, 32);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {

		for (byte b = 0; b < 16; b++)
		{
			for (byte b2 = -19; b2 < 12; b2++)
			{
				for (byte b3 = -11; b3 < 12; b3++)
				{
					this.setBlockToAir(world, x + b3, y + b, z + b2);
				}
			}
		}

		for (byte z1 = -19; z1 < 12; z1++) //grass layer
		{
			for (byte x1 = -11; x1 < 12; x1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), TragicBlocks.NekoGrass.getStateFromMeta(0));
			}
		}

		for (byte z1 = -8; z1 < 9; z1++) //nekite ground layer
		{
			for (byte x1 = -8; x1 < 9; x1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(2));
			}
		}

		for (byte x1 = -1; x1 < 2; x1++) //pathway
		{
			final IBlockState state = NekitePlate.getStateFromMeta(x1 == 0 ? 2 : 1);
			for (byte z1 = -19; z1 < -8; z1++) //grass layer
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), state);
			}
		}

		for (byte y1 = 0; y1 < 6; y1++) //walls around main building premises
		{
			final IBlockState state = y1 < 4 ? NekitePlate.getStateFromMeta(2) : (y1 != 5 ? NekitePlate.getStateFromMeta(1) : NekiteWire.getStateFromMeta(0));

			for (byte z1 = -19; z1 < 12; z1++)
			{
				final IBlockState state2 = y1 == 0 && BIT_MASK_Z[z1 + 19] == 1 ? NekitePlate.getStateFromMeta(0) : state;
				this.setBlock(world, new BlockPos(x + 11, y + y1, z + z1), state2);
				this.setBlock(world, new BlockPos(x - 11, y + y1, z + z1), state2);
			}

			for (byte x1 = -11; x1 < 12; x1++)
			{
				final IBlockState state2 = y1 == 0 && BIT_MASK_X[Math.abs(x1)] == 1 ? NekitePlate.getStateFromMeta(0) : state;
				this.setBlock(world, new BlockPos(x + x1, y + y1, z + 11), state2);
				this.setBlock(world, new BlockPos(x + x1, y + y1, z - 19), state2);
			}

			for (byte x1 = -2; y1 < 3 && x1 < 3; x1++) //entrance
			{
				if (Math.abs(x1) == 2)
				{
					this.setBlock(world, new BlockPos(x + x1, y + y1, z - 19), NekitePlate.getStateFromMeta(1));
				}
				else
				{
					this.setBlockToAir(world, x + x1, y + y1, z - 19);
				}
			}

			for (byte x1 = -2; y1 == 3 && x1 < 3; x1++) //upper entrance border
			{
				this.setBlock(world, new BlockPos(x + x1, y + y1, z - 19), NekitePlate.getStateFromMeta(1));
			}
		}

		for (byte z1 = -8; z1 < 9; z1++) //building external wall border layer
		{
			this.setBlock(world, new BlockPos(x - 8, y, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 8, y, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y, z + 8), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y, z - 8), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = -6; z1 < 7; z1++) //building internal wall border layer
		{
			this.setBlock(world, new BlockPos(x - 6, y, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 6, y, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y, z + 6), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y, z - 6), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = -9; z1 < -4; z1++) //nekite border corner layer
		{
			this.setBlock(world, new BlockPos(x - 9, y, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 9, y, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y, z + 9), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y, z - 9), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = 5; z1 < 10; z1++) //nekite border corner layer
		{
			this.setBlock(world, new BlockPos(x - 9, y, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 9, y, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y, z + 9), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y, z - 9), NekitePlate.getStateFromMeta(1));
		}

		this.setBlockToAir(world, x, y, z - 8); //building entrance
		this.setBlockToAir(world, x, y, z - 6);

		for (byte z1 = -5; z1 < -3; z1++) //nekite internal corner border layer
		{
			this.setBlock(world, new BlockPos(x - 5, y, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 5, y, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y, z + 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y, z - 5), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = 4; z1 < 6; z1++) //nekite internal corner border layer
		{
			this.setBlock(world, new BlockPos(x - 5, y, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 5, y, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y, z + 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y, z - 5), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = -1; z1 < 2; z1++) //floor design
		{
			for (byte x1 = -1; x1 < 2; x1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(5));
			}
		}

		//floor design
		this.setBlock(world, new BlockPos(x + 2, y - 1, z), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x - 2, y - 1, z), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x, y - 1, z + 2), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x, y - 1, z - 2), NekitePlate.getStateFromMeta(5));

		this.setBlock(world, new BlockPos(x + 2, y - 1, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y - 1, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y - 1, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y - 1, z - 1), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 1, y - 1, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y - 1, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y - 1, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y - 1, z - 2), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 3, y - 1, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y - 1, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y - 1, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y - 1, z - 3), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x, y - 1, z), NekitePlate.getStateFromMeta(0));

		//first internal wall steps
		this.setBlock(world, new BlockPos(x + 3, y, z - 7), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 7), NekitePlate.getStateFromMeta(2));

		this.setBlock(world, new BlockPos(x + 4, y, z + 4), Blocks.mob_spawner.getStateFromMeta(0), "TragicMC.TragicNeko");
		this.setBlock(world, new BlockPos(x - 4, y, z + 4), Blocks.mob_spawner.getStateFromMeta(0), "TragicMC.TragicNeko");
		this.setBlock(world, new BlockPos(x + 4, y, z - 4), Blocks.mob_spawner.getStateFromMeta(0), "TragicMC.TragicNeko");
		this.setBlock(world, new BlockPos(x - 4, y, z - 4), Blocks.mob_spawner.getStateFromMeta(0), "TragicMC.TragicNeko");

		//this.setBlock(world, new BlockPos(x, y, z), TragicBlocks.SoulChest.getStateFromMeta(0), ChestHooks.rareHook); //TODO uncomment this

		//second layer start
		for (byte z1 = -8; z1 < 9; z1++) //building external wall border layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 1, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 8, y + 1, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 1, z + 8), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 1, z - 8), NekitePlate.getStateFromMeta(5));
		}

		for (byte z1 = -6; z1 < 7; z1++) //building internal wall border layer
		{
			this.setBlock(world, new BlockPos(x - 6, y + 1, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 6, y + 1, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 1, z + 6), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 1, z - 6), NekitePlate.getStateFromMeta(5));
		}

		for (byte z1 = -8; z1 < -5; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 1, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 1, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 1, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 1, z - 8), NekitePlate.getStateFromMeta(2));
		}

		for (byte z1 = 6; z1 < 9; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 1, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 1, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 1, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 1, z - 8), NekitePlate.getStateFromMeta(2));
		}

		this.setBlockToAir(world, x, y + 1, z - 8); //building entrance
		this.setBlockToAir(world, x, y + 1, z - 6);

		//entrance border
		this.setBlock(world, new BlockPos(x + 1, y + 1, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y + 1, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y + 1, z - 6), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y + 1, z - 6), NekitePlate.getStateFromMeta(1));

		//tower borders
		this.setBlock(world, new BlockPos(x + 5, y + 1, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 1, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 1, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 1, z - 8), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 8, y + 1, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 1, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 8, y + 1, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 1, z - 5), NekitePlate.getStateFromMeta(1));

		//internal corners
		this.setBlock(world, new BlockPos(x + 5, y + 1, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 1, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 1, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 1, z - 5), NekitePlate.getStateFromMeta(1));

		//tower external corners
		this.setBlock(world, new BlockPos(x + 9, y + 1, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 1, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 1, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 1, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 1, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 1, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 1, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 1, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 9, y + 1, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 1, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 1, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 1, z - 5), NekitePlate.getStateFromMeta(1));

		//second internal wall steps
		this.setBlock(world, new BlockPos(x + 4, y + 1, z - 7), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y + 1, z - 7), NekitePlate.getStateFromMeta(2));

		//third layer start
		for (byte z1 = -8; z1 < 9; z1++) //building external wall border layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 2, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 8, y + 2, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 2, z + 8), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 2, z - 8), NekitePlate.getStateFromMeta(5));
		}

		for (byte z1 = -6; z1 < 7; z1++) //building internal wall border layer
		{
			this.setBlock(world, new BlockPos(x - 6, y + 2, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 6, y + 2, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 2, z + 6), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 2, z - 6), NekitePlate.getStateFromMeta(5));
		}

		for (byte z1 = -8; z1 < -5; z1++) //nekite smooth corner layer
		{
			final int i = Math.abs(z1) == 7 ? 0 : 2;
			this.setBlock(world, new BlockPos(x - 8, y + 2, z + z1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 8, y + 2, z + z1), NekitePlate.getStateFromMeta(i));

			this.setBlock(world, new BlockPos(x + z1, y + 2, z + 8), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + z1, y + 2, z - 8), NekitePlate.getStateFromMeta(i));
		}

		for (byte z1 = 6; z1 < 9; z1++) //nekite smooth corner layer
		{
			final int i = Math.abs(z1) == 7 ? 0 : 2;
			this.setBlock(world, new BlockPos(x - 8, y + 2, z + z1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 8, y + 2, z + z1), NekitePlate.getStateFromMeta(i));

			this.setBlock(world, new BlockPos(x + z1, y + 2, z + 8), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + z1, y + 2, z - 8), NekitePlate.getStateFromMeta(i));
		}

		//entrance border
		this.setBlock(world, new BlockPos(x + 1, y + 2, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y + 2, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y + 2, z - 6), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y + 2, z - 6), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y + 2, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y + 2, z - 6), NekitePlate.getStateFromMeta(1));

		//tower borders
		this.setBlock(world, new BlockPos(x + 5, y + 2, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 2, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 2, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 2, z - 8), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 8, y + 2, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 2, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 8, y + 2, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 2, z - 5), NekitePlate.getStateFromMeta(1));

		//internal corners
		this.setBlock(world, new BlockPos(x + 5, y + 2, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 2, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 2, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 2, z - 5), NekitePlate.getStateFromMeta(1));

		//tower external corners
		this.setBlock(world, new BlockPos(x + 9, y + 2, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 2, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 2, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 2, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 2, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 2, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 2, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 2, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 9, y + 2, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 2, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 2, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 2, z - 5), NekitePlate.getStateFromMeta(1));

		//third internal wall steps
		this.setBlock(world, new BlockPos(x + 5, y + 2, z - 7), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y + 2, z - 7), NekitePlate.getStateFromMeta(2));

		//internal wall stuff
		this.setBlock(world, new BlockPos(x + 1, y + 2, z - 7), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y + 2, z - 7), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 1, y + 2, z - 7), NekitePlate.getStateFromMeta(1));

		//fourth layer
		for (byte z1 = -8; z1 < 9; z1++) //building external wall border layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 3, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 8, y + 3, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 3, z + 8), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 3, z - 8), NekitePlate.getStateFromMeta(5));
		}

		for (byte z1 = -6; z1 < 7; z1++) //building internal wall border layer
		{
			this.setBlock(world, new BlockPos(x - 6, y + 3, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 6, y + 3, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 3, z + 6), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 3, z - 6), NekitePlate.getStateFromMeta(5));
		}

		for (byte z1 = -8; z1 < -5; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 3, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 3, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 3, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 3, z - 8), NekitePlate.getStateFromMeta(2));
		}

		for (byte z1 = 6; z1 < 9; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 3, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 3, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 3, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 3, z - 8), NekitePlate.getStateFromMeta(2));
		}

		//tower borders
		this.setBlock(world, new BlockPos(x + 5, y + 3, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 3, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 3, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 3, z - 8), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 8, y + 3, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 3, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 8, y + 3, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 3, z - 5), NekitePlate.getStateFromMeta(1));

		//internal corners
		this.setBlock(world, new BlockPos(x + 5, y + 3, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 3, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 3, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 3, z - 5), NekitePlate.getStateFromMeta(1));

		//tower external corners
		this.setBlock(world, new BlockPos(x + 9, y + 3, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 3, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 3, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 3, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 3, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 3, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 3, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 3, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 9, y + 3, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 3, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 3, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 3, z - 5), NekitePlate.getStateFromMeta(1));

		//corner wall
		this.setBlock(world, new BlockPos(x + 6, y + 3, z - 7), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 6, y + 3, z - 7), NekitePlate.getStateFromMeta(2)); 
		this.setBlock(world, new BlockPos(x + 6, y + 3, z + 7), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 6, y + 3, z + 7), NekitePlate.getStateFromMeta(2));

		this.setBlock(world, new BlockPos(x + 7, y + 3, z - 6), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 7, y + 3, z - 6), NekitePlate.getStateFromMeta(2)); 
		this.setBlock(world, new BlockPos(x + 7, y + 3, z + 6), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 7, y + 3, z + 6), NekitePlate.getStateFromMeta(2)); 

		this.setBlock(world, new BlockPos(x + 7, y + 3, z - 7), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 7, y + 3, z - 7), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 7, y + 3, z + 7), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 7, y + 3, z + 7), NekitePlate.getStateFromMeta(2));

		//internal wall stuff
		for (byte x1 = -2; x1 < 3; x1++)
		{
			this.setBlock(world, new BlockPos(x + x1, y + 3, z - 7), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 3, z - 8), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + x1, y + 3, z + 7), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 3, z + 8), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + 7, y + 3, z + x1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 8, y + 3, z + x1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x - 7, y + 3, z + x1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 8, y + 3, z + x1), NekitePlate.getStateFromMeta(1));
		}
		//marked block
		this.setBlock(world, new BlockPos(x, y + 3, z - 8), NekitePlate.getStateFromMeta(4));

		for (byte z1 = -5; z1 < 6; z1++) //nekite smooth internal wall floor
		{
			this.setBlock(world, new BlockPos(x - 7, y + 3, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 7, y + 3, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 3, z + 7), NekitePlate.getStateFromMeta(5));
		}

		//tower internal corners
		this.setBlock(world, new BlockPos(x + 6, y + 3, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 6, y + 3, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 6, y + 3, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 6, y + 3, z - 5), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 3, z + 6), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 3, z + 6), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 3, z - 6), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 3, z - 6), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 6, y + 3, z + 6), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 6, y + 3, z + 6), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 6, y + 3, z - 6), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 6, y + 3, z - 6), NekitePlate.getStateFromMeta(2));

		for (byte z1 = -5; z1 < 6; z1++) //nekite internal corner border layer
		{
			this.setBlock(world, new BlockPos(x - 5, y + 3, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 5, y + 3, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 3, z + 5), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 3, z - 5), NekitePlate.getStateFromMeta(5));
		}

		for (byte z1 = -5; z1 < -3; z1++) //nekite internal corner border layer
		{
			this.setBlock(world, new BlockPos(x - 5, y + 3, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 5, y + 3, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 3, z + 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 3, z - 5), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = 4; z1 < 6; z1++) //nekite internal corner border layer
		{
			this.setBlock(world, new BlockPos(x - 5, y + 3, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 5, y + 3, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 3, z + 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 3, z - 5), NekitePlate.getStateFromMeta(1));
		}

		//fifth layer start
		for (byte z1 = -9; z1 < -4; z1++) //nekite border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 4, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 9, y + 4, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 4, z + 9), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 4, z - 9), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = 5; z1 < 10; z1++) //nekite border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 4, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 9, y + 4, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 4, z + 9), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 4, z - 9), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = -5; z1 < -1; z1++) //nekite border external layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 4, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 8, y + 4, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 4, z + 8), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 4, z - 8), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = 2; z1 < 6; z1++) //nekite border external layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 4, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 8, y + 4, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 4, z + 8), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 4, z - 8), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = -4; z1 < 5; z1++) //nekite internal border layer
		{
			this.setBlock(world, new BlockPos(x - 4, y + 4, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 4, y + 4, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 4, z + 4), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 4, z - 4), NekitePlate.getStateFromMeta(1));
		}

		//internal corners
		this.setBlock(world, new BlockPos(x + 5, y + 4, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 4, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 4, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 4, z - 5), NekitePlate.getStateFromMeta(1));

		for (byte x1 = -2; x1 < 3; x1++) //inset layer
		{
			final int i = Math.abs(x1) < 2 ? 2 : 1;
			this.setBlock(world, new BlockPos(x + x1, y + 4, z - 7), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + x1, y + 4, z + 7), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 7, y + 4, z + x1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x - 7, y + 4, z + x1), NekitePlate.getStateFromMeta(i));
		}

		for (byte z1 = -8; z1 < -5; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 4, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 4, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 4, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 4, z - 8), NekitePlate.getStateFromMeta(2));
		}

		for (byte z1 = 6; z1 < 9; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 4, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 4, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 4, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 4, z - 8), NekitePlate.getStateFromMeta(2));
		}

		//steps
		this.setBlock(world, new BlockPos(x + 7, y + 4, z + 7), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 7, y + 4, z + 7), NekitePlate.getStateFromMeta(2));

		//sixth layer start
		for (byte z1 = -5; z1 < -1; z1++) //nekite border external layer
		{
			final int i = z1 == -5 || z1 == -2 ? 1 : 5;
			this.setBlock(world, new BlockPos(x - 8, y + 5, z + z1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 8, y + 5, z + z1), NekitePlate.getStateFromMeta(i));

			this.setBlock(world, new BlockPos(x + z1, y + 5, z + 8), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + z1, y + 5, z - 8), NekitePlate.getStateFromMeta(i));
		}

		for (byte z1 = 2; z1 < 6; z1++) //nekite border external layer
		{
			final int i = z1 == 2 || z1 == 5 ? 1 : 5;
			this.setBlock(world, new BlockPos(x - 8, y + 5, z + z1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 8, y + 5, z + z1), NekitePlate.getStateFromMeta(i));

			this.setBlock(world, new BlockPos(x + z1, y + 5, z + 8), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + z1, y + 5, z - 8), NekitePlate.getStateFromMeta(i));
		}

		//internal corners
		this.setBlock(world, new BlockPos(x + 5, y + 5, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 5, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 5, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 5, z - 5), NekitePlate.getStateFromMeta(1));

		for (byte x1 = -2; x1 < 3; x1++) //inset layer
		{
			final int i = Math.abs(x1) < 2 ? (x1 == 0 ? 0 : 2) : 1;
			this.setBlock(world, new BlockPos(x + x1, y + 5, z - 7), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + x1, y + 5, z + 7), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 7, y + 5, z + x1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x - 7, y + 5, z + x1), NekitePlate.getStateFromMeta(i));
		}

		for (byte z1 = -8; z1 < -5; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 5, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 5, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 5, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 5, z - 8), NekitePlate.getStateFromMeta(2));
		}

		for (byte z1 = 6; z1 < 9; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 5, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 5, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 5, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 5, z - 8), NekitePlate.getStateFromMeta(2));
		}

		//tower external corners
		this.setBlock(world, new BlockPos(x + 9, y + 5, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 5, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 5, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 5, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 5, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 5, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 5, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 5, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 9, y + 5, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 5, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 5, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 5, z - 5), NekitePlate.getStateFromMeta(1));

		//steps
		this.setBlock(world, new BlockPos(x + 6, y + 5, z + 7), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 6, y + 5, z + 7), NekitePlate.getStateFromMeta(2));

		//seventh layer start
		for (byte z1 = -5; z1 < -1; z1++) //nekite border external layer
		{
			final int i = z1 == -5 || z1 == -2 ? 1 : 5;
			this.setBlock(world, new BlockPos(x - 8, y + 6, z + z1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 8, y + 6, z + z1), NekitePlate.getStateFromMeta(i));

			this.setBlock(world, new BlockPos(x + z1, y + 6, z + 8), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + z1, y + 6, z - 8), NekitePlate.getStateFromMeta(i));
		}

		for (byte z1 = 2; z1 < 6; z1++) //nekite border external layer
		{
			final int i = z1 == 2 || z1 == 5 ? 1 : 5;
			this.setBlock(world, new BlockPos(x - 8, y + 6, z + z1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 8, y + 6, z + z1), NekitePlate.getStateFromMeta(i));

			this.setBlock(world, new BlockPos(x + z1, y + 6, z + 8), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + z1, y + 6, z - 8), NekitePlate.getStateFromMeta(i));
		}

		//internal corners
		this.setBlock(world, new BlockPos(x + 5, y + 6, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 6, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 6, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 6, z - 5), NekitePlate.getStateFromMeta(1));

		for (byte x1 = -2; x1 < 3; x1++) //inset layer
		{
			final int i = Math.abs(x1) < 2 ? (x1 == 0 ? 0 : 2) : 1;
			this.setBlock(world, new BlockPos(x + x1, y + 6, z - 7), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + x1, y + 6, z + 7), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 7, y + 6, z + x1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x - 7, y + 6, z + x1), NekitePlate.getStateFromMeta(i));
		}

		for (byte z1 = -8; z1 < -5; z1++) //nekite smooth corner layer
		{
			final int i = z1 == -7 ? 0 : 2;
			this.setBlock(world, new BlockPos(x - 8, y + 6, z + z1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 8, y + 6, z + z1), NekitePlate.getStateFromMeta(i));

			this.setBlock(world, new BlockPos(x + z1, y + 6, z + 8), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + z1, y + 6, z - 8), NekitePlate.getStateFromMeta(i));
		}

		for (byte z1 = 6; z1 < 9; z1++) //nekite smooth corner layer
		{
			final int i = z1 == 7 ? 0 : 2;
			this.setBlock(world, new BlockPos(x - 8, y + 6, z + z1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 8, y + 6, z + z1), NekitePlate.getStateFromMeta(i));

			this.setBlock(world, new BlockPos(x + z1, y + 6, z + 8), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + z1, y + 6, z - 8), NekitePlate.getStateFromMeta(i));
		}

		//tower external corners
		this.setBlock(world, new BlockPos(x + 9, y + 6, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 6, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 6, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 6, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 6, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 6, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 6, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 6, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 9, y + 6, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 6, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 6, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 6, z - 5), NekitePlate.getStateFromMeta(1));

		//steps
		this.setBlock(world, new BlockPos(x + 6, y + 6, z + 6), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 6, y + 6, z + 6), NekitePlate.getStateFromMeta(2));

		//eighth layer
		for (byte z1 = -5; z1 < -1; z1++) //nekite border external layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 7, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 8, y + 7, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 7, z + 8), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z - 8), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = 2; z1 < 6; z1++) //nekite border external layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 7, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 8, y + 7, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 7, z + 8), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z - 8), NekitePlate.getStateFromMeta(1));
		}

		for (byte x1 = -2; x1 < 3; x1++) //inset layer
		{
			final int i = Math.abs(x1) < 2 ? 2 : 1;
			this.setBlock(world, new BlockPos(x + x1, y + 7, z - 7), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + x1, y + 7, z + 7), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x + 7, y + 7, z + x1), NekitePlate.getStateFromMeta(i));
			this.setBlock(world, new BlockPos(x - 7, y + 7, z + x1), NekitePlate.getStateFromMeta(i));
		}

		for (byte z1 = -8; z1 < -5; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 7, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 7, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 7, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z - 8), NekitePlate.getStateFromMeta(2));
		}

		for (byte z1 = 6; z1 < 9; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 7, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 7, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 7, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z - 8), NekitePlate.getStateFromMeta(2));
		}

		//tower external corners
		this.setBlock(world, new BlockPos(x + 9, y + 7, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 7, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 7, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 7, z - 9), NekitePlate.getStateFromMeta(1));

		//fill holes
		for (byte z1 = -7; z1 < -2; z1++)
		{
			this.setBlock(world, new BlockPos(x + 6, y + 7, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 7, y + 7, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x - 6, y + 7, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x - 7, y + 7, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 7, z + 6), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z + 7), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 7, z - 6), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z - 7), NekitePlate.getStateFromMeta(2));
		}

		//fill holes
		for (byte z1 = 3; z1 < 8; z1++)
		{
			this.setBlock(world, new BlockPos(x + 6, y + 7, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 7, y + 7, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x - 6, y + 7, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x - 7, y + 7, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 7, z + 6), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z + 7), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 7, z - 6), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z - 7), NekitePlate.getStateFromMeta(2));
		}

		//long borders
		for (byte z1 = -9; z1 < -1; z1++)
		{
			this.setBlock(world, new BlockPos(x + 5, y + 7, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 5, y + 7, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z + 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z - 5), NekitePlate.getStateFromMeta(1));
		}	

		for (byte z1 = 2; z1 < 10; z1++)
		{
			this.setBlock(world, new BlockPos(x + 5, y + 7, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 5, y + 7, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z + 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 7, z - 5), NekitePlate.getStateFromMeta(1));
		}	

		//finish borders
		this.setBlock(world, new BlockPos(x + 6, y + 7, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 6, y + 7, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 6, y + 7, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 6, y + 7, z + 2), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 2, y + 7, z - 6), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y + 7, z - 6), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y + 7, z + 6), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y + 7, z + 6), NekitePlate.getStateFromMeta(1));

		//steps
		this.setBlockToAir(world, x + 7, y + 7, z + 7);
		this.setBlockToAir(world, x - 7, y + 7, z + 7);
		this.setBlockToAir(world, x + 6, y + 7, z + 7);
		this.setBlockToAir(world, x - 6, y + 7, z + 7);
		this.setBlockToAir(world, x + 6, y + 7, z + 6);
		this.setBlockToAir(world, x - 6, y + 7, z + 6);

		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		return tag;
	}

	@Override
	public Schematic readFromNBT(NBTTagCompound tag) {
		return this;
	}

}
