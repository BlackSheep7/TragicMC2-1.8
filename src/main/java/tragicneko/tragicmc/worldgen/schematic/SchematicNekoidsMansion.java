package tragicneko.tragicmc.worldgen.schematic;

import static tragicneko.tragicmc.TragicBlocks.NekitePlate;
import static tragicneko.tragicmc.TragicBlocks.NekiteWire;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.util.WorldHelper;
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
		
		int xTree = rand.nextInt(6) + 3;
		xTree *= rand.nextBoolean() ? 1 : -1;
		final int zTree = rand.nextInt(6) - 16;
		final int yTree = rand.nextInt(3) + 4;
		
		int xTree2 = rand.nextInt(6) + 3;
		xTree2 *= xTree < 0 ? 1 : -1;
		final int zTree2 = rand.nextInt(6) - 16;
		final int yTree2 = rand.nextInt(3) + 4;		
		
		ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(world, 0.25 + yTree / 3.0, new BlockPos(x + xTree + 0.5, y + yTree - 1, z + zTree + 0.5));
		for (BlockPos pos : list)
		{
			this.setBlock(world, pos, TragicBlocks.NekowoodLeaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, true).withProperty(BlockLeaves.DECAYABLE, true));
		}
		
		list = WorldHelper.getBlocksInSphericalRange(world, 0.25 + yTree2 / 3.0, new BlockPos(x + xTree2 + 0.5, y + yTree2 - 1, z + zTree2 + 0.5));
		for (BlockPos pos : list)
		{
			this.setBlock(world, pos, TragicBlocks.NekowoodLeaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, true).withProperty(BlockLeaves.DECAYABLE, true));
		}
		
		for (byte y1 = 0; y1 < yTree; y1++)
		{
			this.setBlock(world, new BlockPos(x + xTree, y + y1, z + zTree), TragicBlocks.Nekowood.getStateFromMeta(0));
		}
		
		for (byte y1 = 0; y1 < yTree2; y1++)
		{
			this.setBlock(world, new BlockPos(x + xTree2, y + y1, z + zTree2), TragicBlocks.Nekowood.getStateFromMeta(0));
		}
		
		for (byte meow = 0; meow < 12; meow++)
		{
			final int randX = (rand.nextInt(7) + 3) * (rand.nextBoolean() ? 1 : -1);
			final int randZ = rand.nextInt(7) - 16;
			
			if ((randX != xTree && randZ != zTree) && (randX != xTree2 && randZ != zTree2)) {
				this.setBlock(world, new BlockPos(x + randX, y, z + randZ), TragicBlocks.NekoBush.getDefaultState());
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

		this.setBlock(world, new BlockPos(x, y, z), TragicBlocks.SoulChest.getStateFromMeta(0), ChestHooks.rareHook);

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

		//ninth layer
		for (byte z1 = -9; z1 < -4; z1++) //nekite border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 8, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 9, y + 8, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 8, z + 9), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 8, z - 9), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = 5; z1 < 10; z1++) //nekite border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 8, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 9, y + 8, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 8, z + 9), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 8, z - 9), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = -8; z1 < -5; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 8, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 8, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 8, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 8, z - 8), NekitePlate.getStateFromMeta(2));
		}

		for (byte z1 = 6; z1 < 9; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 8, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 8, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 8, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 8, z - 8), NekitePlate.getStateFromMeta(2));
		}

		//internal wall stuff
		for (byte x1 = -2; x1 < 3; x1++)
		{
			this.setBlock(world, new BlockPos(x + x1, y + 8, z - 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 8, z - 8), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + x1, y + 8, z + 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 8, z + 8), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + 5, y + 8, z + x1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 8, y + 8, z + x1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x - 5, y + 8, z + x1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 8, y + 8, z + x1), NekitePlate.getStateFromMeta(1));
		}

		//internal tower border corner
		this.setBlock(world, new BlockPos(x + 5, y + 8, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 8, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 8, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 8, z - 5), NekitePlate.getStateFromMeta(1));

		//internal tower border corner, inset
		this.setBlock(world, new BlockPos(x + 8, y + 8, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 8, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 8, y + 8, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 8, z - 5), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 8, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 8, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 8, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 8, z - 8), NekitePlate.getStateFromMeta(1));

		for (byte x1 = -5; x1 < 6; x1++) //internal wall smooth layers
		{
			this.setBlock(world, new BlockPos(x + x1, y + 8, z - 6), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + x1, y + 8, z - 7), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x - 6, y + 8, z + x1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x - 7, y + 8, z + x1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + x1, y + 8, z + 6), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + x1, y + 8, z + 7), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + 6, y + 8, z + x1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 7, y + 8, z + x1), NekitePlate.getStateFromMeta(2));
		}

		//tenth layer
		for (byte z1 = -9; z1 < -4; z1++) //grated border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 9, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 9, y + 9, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 9, z + 9), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 9, z - 9), NekitePlate.getStateFromMeta(5));
		}

		for (byte z1 = 5; z1 < 10; z1++) //grated border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 9, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 9, y + 9, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 9, z + 9), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 9, z - 9), NekitePlate.getStateFromMeta(5));
		}

		//tower external corners
		this.setBlock(world, new BlockPos(x + 9, y + 9, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 9, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 9, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 9, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 9, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 9, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 9, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 9, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 9, y + 9, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 9, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 9, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 9, z - 5), NekitePlate.getStateFromMeta(1));

		for (byte z1 = -8; z1 < -5; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 9, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 9, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 9, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 9, z - 8), NekitePlate.getStateFromMeta(2));
		}

		for (byte z1 = 6; z1 < 9; z1++) //nekite smooth corner layer
		{
			this.setBlock(world, new BlockPos(x - 8, y + 9, z + z1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 8, y + 9, z + z1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + z1, y + 9, z + 8), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + z1, y + 9, z - 8), NekitePlate.getStateFromMeta(2));
		}

		//internal wall stuff
		for (byte x1 = -2; x1 < 3; x1++)
		{
			this.setBlock(world, new BlockPos(x + x1, y + 9, z - 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 9, z + 5), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + 5, y + 9, z + x1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 5, y + 9, z + x1), NekitePlate.getStateFromMeta(1));
		}

		//internal tower border corner
		this.setBlock(world, new BlockPos(x + 5, y + 9, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 9, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 9, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 9, z - 5), NekitePlate.getStateFromMeta(1));

		//internal tower border corner, inset
		this.setBlock(world, new BlockPos(x + 8, y + 9, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 9, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 8, y + 9, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 9, z - 5), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 9, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 9, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 9, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 9, z - 8), NekitePlate.getStateFromMeta(1));

		//internal tower border corner, inset compressed
		this.setBlock(world, new BlockPos(x + 8, y + 9, z + 4), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 8, y + 9, z + 4), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x + 8, y + 9, z - 4), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 8, y + 9, z - 4), NekitePlate.getStateFromMeta(0));

		this.setBlock(world, new BlockPos(x + 4, y + 9, z + 8), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 4, y + 9, z + 8), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x + 4, y + 9, z - 8), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 4, y + 9, z - 8), NekitePlate.getStateFromMeta(0));

		for (byte x1 = -7; x1 < 8; x1++) //internal wall smooth layers
		{
			this.setBlock(world, new BlockPos(x + x1, y + 9, z - 6), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + x1, y + 9, z - 7), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x - 6, y + 9, z + x1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x - 7, y + 9, z + x1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + x1, y + 9, z + 6), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + x1, y + 9, z + 7), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + 6, y + 9, z + x1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 7, y + 9, z + x1), NekitePlate.getStateFromMeta(2));
		}

		//tower floor corner lights
		this.setBlock(world, new BlockPos(x + 7, y + 9, z + 7), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 7, y + 9, z + 7), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x + 7, y + 9, z - 7), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 7, y + 9, z - 7), NekitePlate.getStateFromMeta(0));

		for (byte x1 = -2; x1 < 3; x1++) //ceiling layer
		{
			for (byte z1 = -4; z1 < 5; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y + 9, z + z1), NekitePlate.getStateFromMeta(2));
				this.setBlock(world, new BlockPos(x + z1, y + 9, z + x1), NekitePlate.getStateFromMeta(2));
			}
		}

		//ceiling corner lights
		this.setBlock(world, new BlockPos(x + 3, y + 9, z + 3), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 3, y + 9, z + 3), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x + 3, y + 9, z - 3), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 3, y + 9, z - 3), NekitePlate.getStateFromMeta(0));

		//make room to step up to this floor
		this.setBlockToAir(world, x + 6, y + 9, z + 6);
		this.setBlockToAir(world, x + 7, y + 9, z + 6);
		this.setBlockToAir(world, x + 8, y + 9, z + 6);

		this.setBlockToAir(world, x - 6, y + 9, z + 6);
		this.setBlockToAir(world, x - 7, y + 9, z + 6);
		this.setBlockToAir(world, x - 8, y + 9, z + 6);

		//floor design
		this.setBlock(world, new BlockPos(x, y + 9, z), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x + 1, y + 9, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 1, y + 9, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x + 2, y + 9, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y + 9, z), NekitePlate.getStateFromMeta(1));

		for (byte x1 = -1; x1 < 2; x1++)
		{
			this.setBlock(world, new BlockPos(x + x1, y + 9, z + 1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 9, z - 1), NekitePlate.getStateFromMeta(1));
		}

		//eleventh layer
		for (byte z1 = -9; z1 < -4; z1++) //grated border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 10, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 9, y + 10, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 10, z + 9), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 10, z - 9), NekitePlate.getStateFromMeta(5));
		}

		for (byte z1 = 5; z1 < 10; z1++) //grated border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 10, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 9, y + 10, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 10, z + 9), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 10, z - 9), NekitePlate.getStateFromMeta(5));
		}

		//grated mark design
		this.setBlock(world, new BlockPos(x + 9, y + 10, z + 7), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x - 9, y + 10, z + 7), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x + 9, y + 10, z - 7), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x - 9, y + 10, z - 7), NekitePlate.getStateFromMeta(4));

		this.setBlock(world, new BlockPos(x + 7, y + 10, z + 9), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x - 7, y + 10, z + 9), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x + 7, y + 10, z - 9), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x - 7, y + 10, z - 9), NekitePlate.getStateFromMeta(4));

		//tower external corners
		this.setBlock(world, new BlockPos(x + 9, y + 10, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 10, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 10, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 10, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 10, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 10, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 10, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 10, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 9, y + 10, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 10, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 10, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 10, z - 5), NekitePlate.getStateFromMeta(1));

		//internal wall stuff
		for (byte x1 = -2; x1 < 3; x1++)
		{
			this.setBlock(world, new BlockPos(x + x1, y + 10, z - 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 10, z + 5), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + x1, y + 10, z - 8), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 10, z + 8), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + 5, y + 10, z + x1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 5, y + 10, z + x1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + 8, y + 10, z + x1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 8, y + 10, z + x1), NekitePlate.getStateFromMeta(1));
		}

		for (byte x1 = -5; x1 < -2; x1++) //grated floor corners
		{
			for (byte z1 = -5; z1 < -2; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y + 10, z + z1), NekitePlate.getStateFromMeta(5));
				this.setBlock(world, new BlockPos(x - x1, y + 10, z + z1), NekitePlate.getStateFromMeta(5));
				this.setBlock(world, new BlockPos(x + x1, y + 10, z - z1), NekitePlate.getStateFromMeta(5));
				this.setBlock(world, new BlockPos(x - x1, y + 10, z - z1), NekitePlate.getStateFromMeta(5));
			}
		}

		//internal tower border corner
		this.setBlock(world, new BlockPos(x + 5, y + 10, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 10, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 10, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 10, z - 5), NekitePlate.getStateFromMeta(1));

		//internal tower border corner, inset
		this.setBlock(world, new BlockPos(x + 8, y + 10, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 10, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 8, y + 10, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 10, z - 5), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 10, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 10, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 10, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 10, z - 8), NekitePlate.getStateFromMeta(1));

		for (byte x1 = -4; x1 < 5; x1++) //internal wall smooth layers
		{
			this.setBlock(world, new BlockPos(x + x1, y + 10, z - 6), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + x1, y + 10, z - 7), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x - 6, y + 10, z + x1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x - 7, y + 10, z + x1), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + x1, y + 10, z + 6), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + x1, y + 10, z + 7), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + 6, y + 10, z + x1), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 7, y + 10, z + x1), NekitePlate.getStateFromMeta(2));
		}

		for (byte x1 = -1; x1 < 2; x1++) //internal wall compressed layers
		{
			this.setBlock(world, new BlockPos(x + x1, y + 10, z - 6), NekitePlate.getStateFromMeta(0));
			this.setBlock(world, new BlockPos(x + x1, y + 10, z - 7), NekitePlate.getStateFromMeta(0));

			this.setBlock(world, new BlockPos(x - 6, y + 10, z + x1), NekitePlate.getStateFromMeta(0));
			this.setBlock(world, new BlockPos(x - 7, y + 10, z + x1), NekitePlate.getStateFromMeta(0));

			this.setBlock(world, new BlockPos(x + x1, y + 10, z + 6), NekitePlate.getStateFromMeta(0));
			this.setBlock(world, new BlockPos(x + x1, y + 10, z + 7), NekitePlate.getStateFromMeta(0));

			this.setBlock(world, new BlockPos(x + 6, y + 10, z + x1), NekitePlate.getStateFromMeta(0));
			this.setBlock(world, new BlockPos(x + 7, y + 10, z + x1), NekitePlate.getStateFromMeta(0));
		}

		//summon block
		this.setBlock(world, new BlockPos(x, y + 10, z), TragicBlocks.SummonBlock.getStateFromMeta(11));

		//twelfth layer
		for (byte z1 = -9; z1 < -4; z1++) //grated border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 11, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 9, y + 11, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 11, z + 9), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 11, z - 9), NekitePlate.getStateFromMeta(5));
		}

		for (byte z1 = 5; z1 < 10; z1++) //grated border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 11, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 9, y + 11, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 11, z + 9), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 11, z - 9), NekitePlate.getStateFromMeta(5));
		}

		//grated mark design
		this.setBlock(world, new BlockPos(x + 9, y + 11, z + 7), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x - 9, y + 11, z + 7), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x + 9, y + 11, z - 7), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x - 9, y + 11, z - 7), NekitePlate.getStateFromMeta(4));

		this.setBlock(world, new BlockPos(x + 7, y + 11, z + 9), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x - 7, y + 11, z + 9), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x + 7, y + 11, z - 9), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x - 7, y + 11, z - 9), NekitePlate.getStateFromMeta(4));

		//tower external corners
		this.setBlock(world, new BlockPos(x + 9, y + 11, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 11, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 11, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 11, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 11, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 11, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 11, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 11, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 9, y + 11, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 11, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 11, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 11, z - 5), NekitePlate.getStateFromMeta(1));

		//internal wall stuff
		for (byte x1 = -5; x1 < -1; x1++)
		{
			this.setBlock(world, new BlockPos(x + x1, y + 11, z - 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 11, z + 5), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + x1, y + 11, z - 8), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 11, z + 8), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + 5, y + 11, z + x1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 5, y + 11, z + x1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + 8, y + 11, z + x1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 8, y + 11, z + x1), NekitePlate.getStateFromMeta(1));
		}

		//internal wall stuff
		for (byte x1 = 2; x1 < 6; x1++)
		{
			this.setBlock(world, new BlockPos(x + x1, y + 11, z - 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 11, z + 5), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + x1, y + 11, z - 8), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + x1, y + 11, z + 8), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + 5, y + 11, z + x1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 5, y + 11, z + x1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + 8, y + 11, z + x1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 8, y + 11, z + x1), NekitePlate.getStateFromMeta(1));
		}

		//thirteenth layer
		for (byte z1 = -9; z1 < -4; z1++) //grated border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 12, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 9, y + 12, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 12, z + 9), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 12, z - 9), NekitePlate.getStateFromMeta(5));
		}

		for (byte z1 = 5; z1 < 10; z1++) //grated border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 12, z + z1), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 9, y + 12, z + z1), NekitePlate.getStateFromMeta(5));

			this.setBlock(world, new BlockPos(x + z1, y + 12, z + 9), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + z1, y + 12, z - 9), NekitePlate.getStateFromMeta(5));
		}

		//tower external corners
		this.setBlock(world, new BlockPos(x + 9, y + 12, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 12, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 12, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 12, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 12, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 12, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 12, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 12, z - 9), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 9, y + 12, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 12, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 12, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 12, z - 5), NekitePlate.getStateFromMeta(1));

		//internal tower border corner
		this.setBlock(world, new BlockPos(x + 5, y + 12, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 12, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 12, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 12, z - 5), NekitePlate.getStateFromMeta(1));

		//internal tower border corner, inset
		this.setBlock(world, new BlockPos(x + 8, y + 12, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 12, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 8, y + 12, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 8, y + 12, z - 5), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 5, y + 12, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 12, z + 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 12, z - 8), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 12, z - 8), NekitePlate.getStateFromMeta(1));

		//fourteenth layer
		for (byte z1 = -9; z1 < -4; z1++) //nekite border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 13, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 9, y + 13, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 13, z + 9), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 13, z - 9), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x - 5, y + 13, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 5, y + 13, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 13, z + 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 13, z - 5), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = 5; z1 < 10; z1++) //nekite border tower layer
		{
			this.setBlock(world, new BlockPos(x - 9, y + 13, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 9, y + 13, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 13, z + 9), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 13, z - 9), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x - 5, y + 13, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 5, y + 13, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 13, z + 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 13, z - 5), NekitePlate.getStateFromMeta(1));
		}

		for (byte x1 = -8; x1 < -5; x1++) //smooth tower internal bit
		{
			for (byte z1 = -8; z1 < -5; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y + 13, z + z1), NekitePlate.getStateFromMeta(2));
				this.setBlock(world, new BlockPos(x - x1, y + 13, z + z1), NekitePlate.getStateFromMeta(2));
				this.setBlock(world, new BlockPos(x + x1, y + 13, z - z1), NekitePlate.getStateFromMeta(2));
				this.setBlock(world, new BlockPos(x - x1, y + 13, z - z1), NekitePlate.getStateFromMeta(2));
			}
		}

		//fifteenth layer
		for (byte z1 = -9; z1 < -4; z1++) //nekite border tower layer
		{
			if (z1 == -7) continue;
			this.setBlock(world, new BlockPos(x - 9, y + 14, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 9, y + 14, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 14, z + 9), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 14, z - 9), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x - 5, y + 14, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 5, y + 14, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 14, z + 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 14, z - 5), NekitePlate.getStateFromMeta(1));
		}

		for (byte z1 = 5; z1 < 10; z1++) //nekite border tower layer
		{
			if (z1 == 7) continue;
			this.setBlock(world, new BlockPos(x - 9, y + 14, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 9, y + 14, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 14, z + 9), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 14, z - 9), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x - 5, y + 14, z + z1), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 5, y + 14, z + z1), NekitePlate.getStateFromMeta(1));

			this.setBlock(world, new BlockPos(x + z1, y + 14, z + 5), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + z1, y + 14, z - 5), NekitePlate.getStateFromMeta(1));
		}
		
		//randomly generate 4 chests, with up to 4 and a chance of 0
		if (rand.nextBoolean())
		{
			this.setBlock(world , new BlockPos(x + 8, y + 14, z + 8), Blocks.chest.getStateFromMeta(0), ChestHooks.epicHook);
		}
		
		if (rand.nextBoolean())
		{
			this.setBlock(world , new BlockPos(x - 8, y + 14, z + 8), Blocks.chest.getStateFromMeta(0), ChestHooks.epicHook);
		}
		
		if (rand.nextBoolean())
		{
			this.setBlock(world , new BlockPos(x + 8, y + 14, z - 8), Blocks.chest.getStateFromMeta(0), ChestHooks.epicHook);
		}
		
		if (rand.nextBoolean())
		{
			this.setBlock(world , new BlockPos(x - 8, y + 14, z - 8), Blocks.chest.getStateFromMeta(0), ChestHooks.epicHook);
		}
		
		//sixteenth layer
		this.setBlock(world, new BlockPos(x + 9, y + 15, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 15, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 15, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 15, z - 9), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 5, y + 15, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 15, z + 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 15, z - 9), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 15, z - 9), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 9, y + 15, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 15, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 9, y + 15, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 9, y + 15, z - 5), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 5, y + 15, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 15, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 5, y + 15, z - 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y + 15, z - 5), NekitePlate.getStateFromMeta(1));
		
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
