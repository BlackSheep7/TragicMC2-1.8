package tragicneko.tragicmc.worldgen.schematic;

import static tragicneko.tragicmc.TragicBlocks.NekitePlate;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.entity.mob.EntityMechaNeko;
import tragicneko.tragicmc.entity.mob.EntityWorkerNeko;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicNekoVillage extends Schematic {

	private static final byte[] BLOCK_OFFSETS = new byte[] {-17, 0, 17};
	private static final byte[][] SMALL_BUILDING_OFFSETS = new byte[][] {{-3, -3}, {3, -3}, {-3, 3}, {3, 3}}; //top left, top right, bottom left, bottom right;
	private static final byte[][] INTERNAL_ROTATION_OFFSETS = new byte[][] {{-2, 0}, {2, 0}, {0, -2}, {0, 2}}; //west, east, north, south

	private byte[][] byteMap = new byte[3][3]; //whether each block is made of large or small buildings
	private byte[][] buildingMap = new byte[9][4]; //what each block's actual composition is, large buildings only care about the first byte in their array

	public SchematicNekoVillage(BlockPos origin, Structure structure, World world) {
		super(origin, structure, world, 15, 70, 70);
		byteMap = this.generateBlockComposition(world.rand);
		buildingMap = this.generateBuildingMap(world.rand);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {

		for (byte y1 = -1; y1 < 12; y1++)
		{
			for (byte x1 = -27; x1 < 28; x1++)
			{
				for (byte z1 = -27; z1 < 28; z1++)
				{
					this.setBlockToAir(world, x + x1, y + y1, z + z1);
				}
			}
		}

		this.generateRoads(world, rand, x, y, z);

		for (byte b = 0; b < byteMap.length; b++)
		{
			for (byte b2 = 0; b2 < byteMap[b].length; b2++)
			{
				final byte bit = byteMap[b][b2]; //type of block composition
				final int xOffset = BLOCK_OFFSETS[b2]; //offset for the x to the center of the block
				final int zOffset = BLOCK_OFFSETS[b]; //offset for the z to the center of the block
				final byte block = (byte) ((b * 3) + b2); //which block in the 3x3 grid we are placing, for building rotations

				if (bit == 0) this.placeSmallBuildings(world, rand, x + xOffset, y, z + zOffset, block);
				else if (bit == 1) this.placeLargeBuilding(world, rand, x + xOffset, y, z + zOffset, block);
			}
		}
		return this;
	}

	public void generateRoads(World world, Random rand, int x, int y, int z) 
	{
		//generates the grid of roads between each block and also the block's ground layer
		for (byte x1 = -27; x1 < 28; x1++)
		{
			for (byte z1 = -27; z1 < 28; z1++)
			{
				final int state = z1 > -27 && z1 < 27 ? (x1 >= -9 && x1 < -7 || x1 > 7 && x1 <= 9 ? 2 : (x1 == -10 || x1 == -7 || x1 == 7 || x1 == 10) ? 1 : 5) : 5;
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(state));

				if (x1 > -27 && x1 < 27 && (z1 >= -10 && z1 < -6 || z1 > 6 && z1 <= 10))
				{
					final int i = (z1 == -10 || z1 == -7 || z1 == 7 || z1 == 10) && (x1 < -10 || x1 > -7 && x1 < 7 || x1 > 10) ? 1 : 2;
					this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(i));
				}
			}
		}

		//walls around the village
		for (byte x1 = -27; x1 < -24; x1++)
		{
			for (byte z1 = -27; z1 < 28; z1++)
			{
				for (byte y1 = 0; y1 < 8; y1++)
				{
					if (y1 >= 7 && x1 == -26 || y1 >= 7 && (z1 == -26 || z1 == 26) && x1 == -25) continue;
					final int i = y1 >= 6 ? 1 : 2;
					this.setBlock(world, new BlockPos(x + x1, y + y1, z + z1), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + Math.abs(x1), y + y1, z + z1), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + z1, y + y1, z + x1), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + z1, y + y1, z + Math.abs(x1)), NekitePlate.getStateFromMeta(i));
				}
			}
		}

		//tower-like things on the corners of the walls
		for (byte x1 = -27; x1 < -24; x1++)
		{
			for (byte z1 = -27; z1 < -24; z1++)
			{
				for (byte y1 = 8; y1 < 12; y1++)
				{
					if (y1 != 10 && (x1 == -26 || z1 == -26)) continue;
					final int i = y1 == 10 && x1 == z1 && x1 == -26 ? 0 : 1;
					this.setBlock(world, new BlockPos(x + x1, y + y1, z + z1), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + Math.abs(x1), y + y1, z + z1), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + z1, y + y1, z + x1), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + z1, y + y1, z + Math.abs(x1)), NekitePlate.getStateFromMeta(i));

					this.setBlock(world, new BlockPos(x + x1, y + y1, z + Math.abs(z1)), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + Math.abs(x1), y + y1, z + Math.abs(z1)), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + Math.abs(z1), y + y1, z + x1), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + Math.abs(z1), y + y1, z + Math.abs(x1)), NekitePlate.getStateFromMeta(i));
				}
			}
		}

		//gates
		for (byte x1 = -11; x1 < -5; x1++)
		{
			for (byte z1 = -27; z1 < -24; z1++)
			{
				for (byte y1 = 0; y1 < 4; y1++)
				{
					this.setBlockToAir(world, x + x1, y + y1, z + z1);
					this.setBlockToAir(world, x + Math.abs(x1), y + y1, z + z1);
					this.setBlockToAir(world, x + z1, y + y1, z + x1);
					this.setBlockToAir(world, x + z1, y + y1, z + Math.abs(x1));

					this.setBlockToAir(world, x + x1, y + y1, z + Math.abs(z1));
					this.setBlockToAir(world, x + Math.abs(x1), y + y1, z + Math.abs(z1));
					this.setBlockToAir(world, x + Math.abs(z1), y + y1, z + x1);
					this.setBlockToAir(world, x + Math.abs(z1), y + y1, z + Math.abs(x1));

					if ((x1 == -11 || x1 == -6) || y1 == 3)
					{
						final int i = z1 == -26 ? (y1 == 3 && (x1 == -8 || x1 == -9) ? 0 : 2) : 1;
						this.setBlock(world, new BlockPos(x + x1, y + y1, z + z1), NekitePlate.getStateFromMeta(i));
						this.setBlock(world, new BlockPos(x + Math.abs(x1), y + y1, z + z1), NekitePlate.getStateFromMeta(i));
						this.setBlock(world, new BlockPos(x + z1, y + y1, z + x1), NekitePlate.getStateFromMeta(i));
						this.setBlock(world, new BlockPos(x + z1, y + y1, z + Math.abs(x1)), NekitePlate.getStateFromMeta(i));

						this.setBlock(world, new BlockPos(x + x1, y + y1, z + Math.abs(z1)), NekitePlate.getStateFromMeta(i));
						this.setBlock(world, new BlockPos(x + Math.abs(x1), y + y1, z + Math.abs(z1)), NekitePlate.getStateFromMeta(i));
						this.setBlock(world, new BlockPos(x + Math.abs(z1), y + y1, z + x1), NekitePlate.getStateFromMeta(i));
						this.setBlock(world, new BlockPos(x + Math.abs(z1), y + y1, z + Math.abs(x1)), NekitePlate.getStateFromMeta(i));
					}
				}
			}
		}
	}

	public void placeSmallBuildings(World world, Random rand, int x, int y, int z, byte block) //builds a four-set of small buildings in a quarter-block (5x5)
	{	
		for (byte b = 0; b < SMALL_BUILDING_OFFSETS.length; b++)
		{
			this.generateFiveByFive(world, rand, x + SMALL_BUILDING_OFFSETS[b][0], y, z + SMALL_BUILDING_OFFSETS[b][1], b, block);
		}

		//streetlights
		for (byte y1 = 0; y1 < 4; y1++)
		{
			final int state = y1 == 3 ? 0 : 1;
			this.setBlock(world, new BlockPos(x + 6, y + y1, z + 6), NekitePlate.getStateFromMeta(state));
			this.setBlock(world, new BlockPos(x - 6, y + y1, z + 6), NekitePlate.getStateFromMeta(state));
			this.setBlock(world, new BlockPos(x + 6, y + y1, z - 6), NekitePlate.getStateFromMeta(state));
			this.setBlock(world, new BlockPos(x - 6, y + y1, z - 6), NekitePlate.getStateFromMeta(state));
		}
	}

	public void generateFiveByFive(World world, Random rand, int x, int y, int z, byte spot, byte block) //spot is the place in that block and block is which block it's in
	{
		final byte rotation = getRotationFromBlockPosition(rand, spot, block); //generates possible exit coords based on the building's position in the schematic

		final int meow = buildingMap[block][spot];
		if (meow <= 10)
		{
			this.generateSmallNekoFlat(world, rand, x, y, z, rotation);
		}
		else if (meow <= 12)
		{
			this.generatePowerCell(world, rand, x, y, z);
		}
		else if (meow <= 13)
		{
			this.generateAntenna(world, rand, x, y, z);
		}
		else
		{
			this.generateVacantLot(world, rand, x, y, z, rotation);
		} 
	}

	public byte getRotationFromBlockPosition(Random rand, byte spot, byte block) {
		byte rotation = 0;

		if (spot == 0) rotation = rand.nextBoolean() ? 0 : (byte) 2; //spot 0 is the top left, north and west are valid
		else if (spot == 1) rotation = rand.nextBoolean() ? (byte) 1 : (byte) 2; //spot 1 is the top right, north and east are valid
		else if (spot == 2) rotation = rand.nextBoolean() ? 0 : (byte) 3; //spot 2 is the bottom left, south and west are valid
		else if (spot == 3) rotation = rand.nextBoolean() ? (byte) 1 : (byte) 3; //spot 3 is the bottom right, south and east are valid

		//special overall exceptions for rotations so that we don't face towards the village wall, we face towards the road
		if (spot == 0 && block == 0) rotation = 1;
		if (spot == 1 && block == 0) rotation = 1;
		if (spot == 2 && block == 0) rotation = 3;
		if (spot == 0 && block == 1) rotation = 0;
		if (spot == 1 && block == 1) rotation = 1;
		if (spot == 0 && block == 2) rotation = 0;
		if (spot == 1 && block == 2) rotation = 0;
		if (spot == 3 && block == 2) rotation = 3;
		if (spot == 0 && block == 3) rotation = 2;
		if (spot == 2 && block == 3) rotation = 3;
		if (spot == 1 && block == 5) rotation = 2;
		if (spot == 3 && block == 5) rotation = 3;
		if (spot == 0 && block == 6) rotation = 2;
		if (spot == 2 && block == 6) rotation = 1;
		if (spot == 3 && block == 6) rotation = 1;
		if (spot == 2 && block == 7) rotation = 0;
		if (spot == 3 && block == 7) rotation = 1;
		if (spot == 1 && block == 8) rotation = 2;
		if (spot == 2 && block == 8) rotation = 0;
		if (spot == 3 && block == 8) rotation = 0;

		return rotation;
	}

	public void generateAntenna(World world, Random rand, int x, int y, int z)
	{
		//a radio antenna
		for (byte y1 = 0; y1 < 5; y1++)
		{
			for (byte x1 = -2; x1 < 3; x1++)
			{
				for (byte z1 = -2; z1 < 3; z1++)
				{
					if (Math.abs(x1) == Math.abs(z1)) this.setBlock(world, new BlockPos(x + x1, y + y1, z + z1), Blocks.iron_bars.getStateFromMeta(0));
				}
			}
		}

		for (byte y1 = 5; y1 < 10; y1++)
		{
			for (byte x1 = -1; x1 < 2; x1++)
			{
				for (byte z1 = -1; z1 < 2; z1++)
				{
					if (Math.abs(x1) == Math.abs(z1) || y1 % 2 == 0) this.setBlock(world, new BlockPos(x + x1, y + y1, z + z1), Blocks.iron_bars.getStateFromMeta(0));
				}
			}
		}

		for (byte y1 = 0; y1 < 18; y1++)
		{
			this.setBlock(world, new BlockPos(x, y + y1, z), y1 > 10 ? Blocks.iron_bars.getStateFromMeta(0) : Blocks.iron_block.getStateFromMeta(0));
		}

		this.setBlock(world, new BlockPos(x, y + 18, z), Blocks.beacon.getStateFromMeta(0));
	}

	public void generateSmallNekoFlat(World world, Random rand, int x, int y, int z, byte rotation)
	{
		//a small, cramped home for a Neko
		for (byte y1 = 0; y1 < 9; y1++)
		{
			for (byte x1 = -2; x1 < 3; x1++)
			{
				final int i = y1 == 0 || y1 == 4 || y1 == 8 || Math.abs(x1) == 2 ? 1 : 2;
				this.setBlock(world, new BlockPos(x + x1, y + y1, z + 2), NekitePlate.getStateFromMeta(i));
				this.setBlock(world, new BlockPos(x + 2, y + y1, z + x1), NekitePlate.getStateFromMeta(i));
				this.setBlock(world, new BlockPos(x + x1, y + y1, z - 2), NekitePlate.getStateFromMeta(i));
				this.setBlock(world, new BlockPos(x - 2, y + y1, z + x1), NekitePlate.getStateFromMeta(i));
			}
		}

		for (byte x1 = -1; x1 < 2; x1++)
		{
			for (byte z1 = -1; z1 < 2; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y + 3, z + z1), NekitePlate.getStateFromMeta(2));
				this.setBlock(world, new BlockPos(x + x1, y + 7, z + z1), NekitePlate.getStateFromMeta(2));
			}
		}

		this.setBlockToAir(world, x + INTERNAL_ROTATION_OFFSETS[rotation][0], y, z + INTERNAL_ROTATION_OFFSETS[rotation][1]);
		this.setBlockToAir(world, x + INTERNAL_ROTATION_OFFSETS[rotation][0], y + 1, z + INTERNAL_ROTATION_OFFSETS[rotation][1]);

		this.setBlock(world, new BlockPos(x + 1, y, z + 1), Blocks.crafting_table.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 1, y, z - 1), Blocks.furnace.getStateFromMeta(0), SchematicNekoHouse.getFurnaceStacks(0, rand), SchematicNekoHouse.getFurnaceStacks(1, rand), SchematicNekoHouse.getFurnaceStacks(2, rand));

		for (byte y1 = 0; y1 < 4; y1++)
		{
			this.setBlock(world, new BlockPos(x - 1, y + y1, z + 1), Blocks.ladder.getStateFromMeta(0));
		}

		this.setBlock(world, new BlockPos(x, y + 3, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 1, y + 4, z - 1), Blocks.chest.getStateFromMeta(3), ChestHooks.foodHook);
		this.setBlock(world, new BlockPos(x + 1, y + 4, z), Blocks.bed.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y + 4, z - 1), Blocks.bed.getStateFromMeta(10));

		for (byte b = 0; b < INTERNAL_ROTATION_OFFSETS.length; b++)
		{
			this.setBlockToAir(world, x + INTERNAL_ROTATION_OFFSETS[b][0], y + 5, z + INTERNAL_ROTATION_OFFSETS[b][1]);
			this.setBlockToAir(world, x + INTERNAL_ROTATION_OFFSETS[b][0], y + 6, z + INTERNAL_ROTATION_OFFSETS[b][1]);
		}
		
		this.spawnEntity(world, new BlockPos(x, y, z), new EntityWorkerNeko(world));
	}

	public void generateVacantLot(World world, Random rand, int x, int y, int z, byte rotation)
	{
		//fills the space with air and puts a for sale sign
		for (byte y1 = 0; y1 < 8; y1++)
		{
			for (byte x1 = -2; x1 < 3; x1++)
			{
				for (byte z1 = -2; z1 < 3; z1++)
				{
					this.setBlockToAir(world, x + x1, y + y1, z + z1);
				}
			}
		}

		byte[] metaLookup = new byte[] {4, 12, 8, 0};

		final int meta = metaLookup[rotation];
		this.setBlock(world, new BlockPos(x + INTERNAL_ROTATION_OFFSETS[rotation][0], y, z + INTERNAL_ROTATION_OFFSETS[rotation][1]), Blocks.standing_sign.getStateFromMeta(meta), new Object[] {new String[] {"", "For sale!", "Contact Chuck!"}});
	}

	public void generatePowerCell(World world, Random rand, int x, int y, int z)
	{
		for (byte y1 = 0; y1 < 5; y1++)
		{
			for (byte x1 = -1; x1 < 2; x1++)
			{
				for (byte z1 = -1; z1 < 2; z1++)
				{
					IBlockState state = Math.abs(z1) != Math.abs(x1) && y1 < 4 ? Blocks.redstone_block.getStateFromMeta(0) : Blocks.iron_block.getStateFromMeta(0);
					this.setBlock(world, new BlockPos(x + x1, y + y1, z + z1), state);
				}
			}
		}

		for (byte b = 0; b < INTERNAL_ROTATION_OFFSETS.length; b++)
		{
			for (byte y1 = 0; y1 < 4; y1++)
			{
				this.setBlock(world, new BlockPos(x + INTERNAL_ROTATION_OFFSETS[b][0], y + y1, z + INTERNAL_ROTATION_OFFSETS[b][1]), Blocks.redstone_lamp.getStateFromMeta(0));
			}
		}

		this.setBlock(world, new BlockPos(x, y + 3, z), Blocks.redstone_block.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x, y + 4, z), Blocks.redstone_block.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x, y + 5, z), Blocks.redstone_lamp.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x, y + 1, z), Blocks.chest.getStateFromMeta(0), ChestHooks.rareHook);
	}

	public void placeLargeBuilding(World world, Random rand, int x, int y, int z, byte block) //builds a large building out of a full block (13x13) or (11x11)
	{
		final int meow = buildingMap[block][0];
		if (meow <= 6)
		{
			this.generateHousingComplex(world, rand, x, y, z, block);
		}
		else if (meow <= 9)
		{
			this.generateArmsDepot(world, rand, x, y, z, block);
		}
		else if (meow <= 10)
		{
			this.generateStorageBuilding(world, rand, x, y, z, block);
		}
		else if (meow <= 13)
		{
			this.generatePark(world, rand, x, y, z);
		}
		else
		{
			this.generatePlaza(world, rand, x, y, z);
		} 

		//streetlights
		for (byte y1 = 0; y1 < 4; y1++)
		{
			final int state = y1 == 3 ? 0 : 1;
			this.setBlock(world, new BlockPos(x + 6, y + y1, z + 6), NekitePlate.getStateFromMeta(state));
			this.setBlock(world, new BlockPos(x - 6, y + y1, z + 6), NekitePlate.getStateFromMeta(state));
			this.setBlock(world, new BlockPos(x + 6, y + y1, z - 6), NekitePlate.getStateFromMeta(state));
			this.setBlock(world, new BlockPos(x - 6, y + y1, z - 6), NekitePlate.getStateFromMeta(state));
		}
	}

	public void generateHousingComplex(World world, Random rand, int x, int y, int z, byte block)
	{
		//generate the entire block as if the entire thing rolled as 4 neko flats, since they looked similarly to a giant appartment building anyway
		for (byte b = 0; b < SMALL_BUILDING_OFFSETS.length; b++)
		{
			this.generateSmallNekoFlat(world, rand, x + SMALL_BUILDING_OFFSETS[b][0], y, z + SMALL_BUILDING_OFFSETS[b][1], this.getRotationFromBlockPosition(rand, b, block));
		}
	}

	public void generateStorageBuilding(World world, Random rand, int x, int y, int z, byte block)
	{
		//big warehouse with lots of chests full of food, weapons, random stuff, blocks
		for (byte y1 = 0; y1 < 9; y1++)
		{
			for (byte x1 = -5; x1 < 6; x1++)
			{
				final int i = y1 == 0 || y1 == 4 || y1 == 8 || Math.abs(x1) == 5 ? 1 : 2;
				this.setBlock(world, new BlockPos(x + x1, y + y1, z + 5), NekitePlate.getStateFromMeta(i));
				this.setBlock(world, new BlockPos(x + 5, y + y1, z + x1), NekitePlate.getStateFromMeta(i));
				this.setBlock(world, new BlockPos(x + x1, y + y1, z - 5), NekitePlate.getStateFromMeta(i));
				this.setBlock(world, new BlockPos(x - 5, y + y1, z + x1), NekitePlate.getStateFromMeta(i));
			}
		}

		for (byte x1 = -4; x1 < 5; x1++)
		{
			for (byte z1 = -4; z1 < 5; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y + 3, z + z1), NekitePlate.getStateFromMeta(2));
				this.setBlock(world, new BlockPos(x + x1, y + 7, z + z1), NekitePlate.getStateFromMeta(2));
			}
		}

		for (byte x1 = -1; x1 < 2; x1++)
		{
			for (byte z1 = -1; z1 < 2; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y + 3, z + z1), NekitePlate.getStateFromMeta(0));
			}
		}

		byte b = this.getRotationFromBlockPosition(rand, (byte) (block % 4), block);

		for (byte x1 = -1; x1 < 2; x1++)
		{
			for (byte z1 = -1; z1 < 2; z1++)
			{
				this.setBlockToAir(world, x + INTERNAL_ROTATION_OFFSETS[b][0] * 3 + x1, y, z + INTERNAL_ROTATION_OFFSETS[b][1] * 3 + z1);
				this.setBlockToAir(world, x + INTERNAL_ROTATION_OFFSETS[b][0] * 3 + x1, y + 1, z + INTERNAL_ROTATION_OFFSETS[b][1] * 3 + z1);
			}
		}

		for (byte y1 = 0; y1 < 4; y1++)
		{
			this.setBlock(world, new BlockPos(x - 4, y + y1, z + 4), Blocks.ladder.getStateFromMeta(0));
			if (y1 == 0)
			{
				this.setBlock(world, new BlockPos(x - 2, y + y1, z + 4), Blocks.crafting_table.getStateFromMeta(0));
				this.setBlock(world, new BlockPos(x - 3, y + y1, z + 4), Blocks.anvil.getStateFromMeta(0));
			}

			if (y1 < 3)
			{
				ChestGenHooks hook = rand.nextBoolean() ? ChestHooks.commonHook : ChestHooks.foodHook;
				this.setBlock(world, new BlockPos(x - 4, y + y1, z - 3), Blocks.chest.getStateFromMeta(5), hook);
				this.setBlock(world, new BlockPos(x - 4, y + y1, z - 2), Blocks.chest.getStateFromMeta(5), hook);
				hook = rand.nextBoolean() ? ChestHooks.commonHook : ChestHooks.foodHook;
				this.setBlock(world, new BlockPos(x + 4, y + y1, z - 3), Blocks.chest.getStateFromMeta(4), hook);
				this.setBlock(world, new BlockPos(x + 4, y + y1, z - 2), Blocks.chest.getStateFromMeta(4), hook);

				hook = rand.nextBoolean() ? ChestHooks.commonHook : ChestHooks.foodHook;
				this.setBlock(world, new BlockPos(x + 4, y + y1, z + 3), Blocks.chest.getStateFromMeta(4), hook);
				this.setBlock(world, new BlockPos(x + 4, y + y1, z + 2), Blocks.chest.getStateFromMeta(4), hook);

				hook = rand.nextBoolean() ? ChestHooks.commonHook : ChestHooks.foodHook;
				this.setBlock(world, new BlockPos(x - 3, y + y1, z - 4), Blocks.chest.getStateFromMeta(3), hook);
				this.setBlock(world, new BlockPos(x - 2, y + y1, z - 4), Blocks.chest.getStateFromMeta(3), hook);
				hook = rand.nextBoolean() ? ChestHooks.commonHook : ChestHooks.foodHook;
				this.setBlock(world, new BlockPos(x + 3, y + y1, z - 4), Blocks.chest.getStateFromMeta(3), hook);
				this.setBlock(world, new BlockPos(x + 2, y + y1, z - 4), Blocks.chest.getStateFromMeta(3), hook);

				hook = rand.nextBoolean() ? ChestHooks.commonHook : ChestHooks.foodHook;
				this.setBlock(world, new BlockPos(x + 3, y + y1, z + 4), Blocks.chest.getStateFromMeta(0), hook);
				this.setBlock(world, new BlockPos(x + 2, y + y1, z + 4), Blocks.chest.getStateFromMeta(0), hook);

				this.setBlock(world, new BlockPos(x - 4, y + y1 + 4, z - 3), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
				this.setBlock(world, new BlockPos(x - 4, y + y1 + 4, z - 2), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
				this.setBlock(world, new BlockPos(x + 4, y + y1 + 4, z - 3), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);
				this.setBlock(world, new BlockPos(x + 4, y + y1 + 4, z - 2), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);

				this.setBlock(world, new BlockPos(x + 4, y + y1 + 4, z + 3), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);
				this.setBlock(world, new BlockPos(x + 4, y + y1 + 4, z + 2), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);

				this.setBlock(world, new BlockPos(x - 3, y + y1 + 4, z - 4), Blocks.chest.getStateFromMeta(3), ChestHooks.uncommonHook);
				this.setBlock(world, new BlockPos(x - 2, y + y1 + 4, z - 4), Blocks.chest.getStateFromMeta(3), ChestHooks.uncommonHook);
				this.setBlock(world, new BlockPos(x + 3, y + y1 + 4, z - 4), Blocks.chest.getStateFromMeta(3), ChestHooks.uncommonHook);
				this.setBlock(world, new BlockPos(x + 2, y + y1 + 4, z - 4), Blocks.chest.getStateFromMeta(3), ChestHooks.uncommonHook);

				this.setBlock(world, new BlockPos(x + 3, y + y1 + 4, z + 4), Blocks.chest.getStateFromMeta(0), ChestHooks.uncommonHook);
				this.setBlock(world, new BlockPos(x + 2, y + y1 + 4, z + 4), Blocks.chest.getStateFromMeta(0), ChestHooks.uncommonHook);
			}
		}
		
		this.spawnEntity(world, new BlockPos(x + 1, y, z), new EntityWorkerNeko(world));
		this.spawnEntity(world, new BlockPos(x - 1, y, z), new EntityWorkerNeko(world));
		this.spawnEntity(world, new BlockPos(x, y, z + 1), new EntityWorkerNeko(world));
		this.spawnEntity(world, new BlockPos(x, y, z - 1), new EntityWorkerNeko(world));
	}

	public void generateArmsDepot(World world, Random rand, int x, int y, int z, byte block) 
	{
		//big warehouse-like building with lots of redstone stuff and chests full of weapons
		for (byte y1 = 0; y1 < 9; y1++)
		{
			for (byte x1 = -5; x1 < 6; x1++)
			{
				final int i = y1 == 0 || y1 == 4 || y1 == 8 || Math.abs(x1) == 5 || Math.abs(x1) == 2 ? 3 : 2;
				this.setBlock(world, new BlockPos(x + x1, y + y1, z + 5), NekitePlate.getStateFromMeta(i));
				this.setBlock(world, new BlockPos(x + 5, y + y1, z + x1), NekitePlate.getStateFromMeta(i));
				this.setBlock(world, new BlockPos(x + x1, y + y1, z - 5), NekitePlate.getStateFromMeta(i));
				this.setBlock(world, new BlockPos(x - 5, y + y1, z + x1), NekitePlate.getStateFromMeta(i));
			}
		}

		for (byte x1 = -4; x1 < 5; x1++)
		{
			for (byte z1 = -4; z1 < 5; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y + 7, z + z1), NekitePlate.getStateFromMeta(2));
			}
		}

		for (byte x1 = -2; x1 < 3; x1++)
		{
			for (byte z1 = -2; z1 < 3; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y, z + z1), NekitePlate.getStateFromMeta(1));
			}
		}

		for (byte x1 = -1; x1 < 2; x1++)
		{
			for (byte z1 = -1; z1 < 2; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y, z + z1), NekitePlate.getStateFromMeta(0));
			}
		}

		byte b = this.getRotationFromBlockPosition(rand, (byte) (block % 4), block);

		for (byte x1 = -1; x1 < 2; x1++)
		{
			for (byte z1 = -1; z1 < 2; z1++)
			{
				this.setBlockToAir(world, x + INTERNAL_ROTATION_OFFSETS[b][0] * 3 + x1, y, z + INTERNAL_ROTATION_OFFSETS[b][1] * 3 + z1);
				this.setBlockToAir(world, x + INTERNAL_ROTATION_OFFSETS[b][0] * 3 + x1, y + 1, z + INTERNAL_ROTATION_OFFSETS[b][1] * 3 + z1);
			}
		}

		for (byte y1 = 0; y1 < 3; y1++)
		{
			if (y1 == 0)
			{
				this.setBlock(world, new BlockPos(x - 2, y + y1, z + 4), Blocks.crafting_table.getStateFromMeta(0));
				this.setBlock(world, new BlockPos(x - 3, y + y1, z + 4), Blocks.anvil.getStateFromMeta(0));
			}

			this.setBlock(world, new BlockPos(x - 4, y + y1, z - 3), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x - 4, y + y1, z - 2), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 4, y + y1, z - 3), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 4, y + y1, z - 2), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);

			this.setBlock(world, new BlockPos(x - 4, y + y1, z + 3), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x - 4, y + y1, z + 2), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 4, y + y1, z + 3), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 4, y + y1, z + 2), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);

			this.setBlock(world, new BlockPos(x - 3, y + y1, z - 4), Blocks.chest.getStateFromMeta(3), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x - 2, y + y1, z - 4), Blocks.chest.getStateFromMeta(3), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 3, y + y1, z - 4), Blocks.chest.getStateFromMeta(3), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 2, y + y1, z - 4), Blocks.chest.getStateFromMeta(3), ChestHooks.uncommonHook);

			this.setBlock(world, new BlockPos(x - 3, y + y1, z + 4), Blocks.chest.getStateFromMeta(0), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x - 2, y + y1, z + 4), Blocks.chest.getStateFromMeta(0), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 3, y + y1, z + 4), Blocks.chest.getStateFromMeta(0), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 2, y + y1, z + 4), Blocks.chest.getStateFromMeta(0), ChestHooks.uncommonHook);
		}
		
		EntityMechaNeko neko = new EntityMechaNeko(world);
		neko.setPosition(x - 1.5, y, z - 1.5);
		neko.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(x - 2, y, z - 2)), null);
		this.spawnEntity(world, new BlockPos(x - 2, y, z - 2), neko);
		
		neko = new EntityMechaNeko(world);
		neko.setPosition(x + 2.5, y, z + 2.5);
		neko.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(x + 2, y, z + 2)), null);
		this.spawnEntity(world, new BlockPos(x + 2, y, z + 2), neko);
	}

	public void generatePark(World world, Random rand, int x, int y, int z) 
	{
		//has grass and trees

		//set the grass
		for (byte x1 = -6; x1 < 7; x1++)
		{
			for (byte z1 = -6; z1 < 7; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), TragicBlocks.NekoGrass.getStateFromMeta(0));
			}
		}

		//add some bushes
		for (byte b = 0; b < 16; b++)
		{
			if (rand.nextInt(4) == 0) this.setBlock(world, new BlockPos(x + rand.nextInt(7) - rand.nextInt(7), y, z + rand.nextInt(7) - rand.nextInt(7)), TragicBlocks.NekoBush.getStateFromMeta(0));
		}

		//add some flowers
		for (byte b = 0; b < 32; b++)
		{
			if (rand.nextInt(4) == 0) this.setBlock(world, new BlockPos(x + rand.nextInt(7) - rand.nextInt(7), y, z + rand.nextInt(7) - rand.nextInt(7)), TragicBlocks.TragicFlower2.getStateFromMeta(11));
		}

		//random chance for a "lake"
		for (byte b = 0; b < 3; b++)
		{
			if (rand.nextBoolean())
			{
				BlockPos pos = new BlockPos(x + rand.nextInt(5) - rand.nextInt(5), y - 1, z + rand.nextInt(5) - rand.nextInt(5));
				final int width = rand.nextInt(2) + 2;
				ArrayList<BlockPos> list = WorldHelper.getBlocksInCircularRange(world, (double) width / 2, pos);

				for (BlockPos coord : list)
				{
					this.setBlock(world, coord, Blocks.flowing_water.getStateFromMeta(0));
				}

				list = WorldHelper.getBlocksInCircularRange(world, (double) width, pos);

				for (BlockPos coord : list)
				{
					if (rand.nextInt(4) != 0) this.setBlock(world, coord, Blocks.flowing_water.getStateFromMeta(0));
				}

				break;
			}
		}

		//random chances for trees
		for (byte b = 0; b < 3; b++)
		{
			if (rand.nextBoolean())
			{
				BlockPos pos = new BlockPos(x + rand.nextInt(6) - rand.nextInt(6), y, z + rand.nextInt(6) - rand.nextInt(6));
				final int height = rand.nextInt(3) + 4;
				ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(world, (double) height / 2, pos.up(height - 1));

				for (BlockPos coord : list)
				{
					this.setBlock(world, coord, TragicBlocks.NekowoodLeaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, true).withProperty(BlockLeaves.DECAYABLE, true));
				}

				for (byte y1 = -1; y1 < height; y1++)
				{
					this.setBlock(world, pos.up(y1), TragicBlocks.Nekowood.getStateFromMeta(0));
				}
			}
		}		
	}

	public void generatePlaza(World world, Random rand, int x, int y, int z)
	{
		//has a fountain and benches
		boolean flag = rand.nextBoolean();

		for (byte x1 = -6; x1 < 7; x1++)
		{
			for (byte z1 = -6; z1 < 7; z1++)
			{
				final int i = Math.abs(z1) == Math.abs(x1) || x1 % 2 == 0 && flag || !flag && z1 % 2 == 0 ? 1 : 2;
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(i));
			}
		}

		for (byte x1 = -1; x1 < 2; x1++)
		{
			for (byte z1 = -1; z1 < 2; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y, z + z1), Blocks.flowing_water.getStateFromMeta(0));
			}
		}

		this.setBlock(world, new BlockPos(x - 1, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(1));

		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x, y + 1, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x, y + 2, z), NekitePlate.getStateFromMeta(0));

		this.setBlock(world, new BlockPos(x, y + 3, z), Blocks.flowing_water.getStateFromMeta(0));
		
		this.spawnEntity(world, new BlockPos(x + 3, y, z + 3), new EntityWorkerNeko(world));
		this.spawnEntity(world, new BlockPos(x - 3, y, z + 3), new EntityWorkerNeko(world));
		this.spawnEntity(world, new BlockPos(x + 3, y, z - 3), new EntityWorkerNeko(world));
		this.spawnEntity(world, new BlockPos(x - 3, y, z - 3), new EntityWorkerNeko(world));
	}

	public byte[][] generateBlockComposition(Random rand) { //creates a bytemap overlay for the schematic, these change what generates for each "block"
		return new byte[][]{
			{this.nextByte(rand), this.nextByte(rand), this.nextByte(rand)},
			{this.nextByte(rand), this.nextByte(rand), this.nextByte(rand)},
			{this.nextByte(rand), this.nextByte(rand), this.nextByte(rand)}
		};
	}
	
	private byte[][] generateBuildingMap(Random rand) {
		return new byte[][] {
			{this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand)},
			{this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand)},
			{this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand)},
			{this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand)},
			{this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand)},
			{this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand)},
			{this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand)},
			{this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand)},
			{this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand), this.nextByte(16, rand)}
		};
	}

	public byte nextByte(Random rand) {
		return (byte) rand.nextInt(2);
	}

	public byte nextByte(int value, Random rand) {
		return (byte) rand.nextInt(value);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setByteArray("mapValues0", byteMap[0]);
		tag.setByteArray("mapValues1", byteMap[1]);
		tag.setByteArray("mapValues2", byteMap[2]);

		tag.setByteArray("map2Values0", buildingMap[0]);
		tag.setByteArray("map2Values1", buildingMap[1]);
		tag.setByteArray("map2Values2", buildingMap[2]);
		tag.setByteArray("map2Values3", buildingMap[3]);
		tag.setByteArray("map2Values4", buildingMap[4]);
		tag.setByteArray("map2Values5", buildingMap[5]);
		tag.setByteArray("map2Values6", buildingMap[6]);
		tag.setByteArray("map2Values7", buildingMap[7]);
		tag.setByteArray("map2Values8", buildingMap[8]);
		return tag;
	}

	@Override
	public Schematic readFromNBT(NBTTagCompound tag) {
		for (byte b = 0; b < byteMap.length; b++)
		{
			byteMap[b] = tag.getByteArray("mapValues" + b);

		}

		for (byte b = 0; b < buildingMap.length; b++)
		{
			buildingMap[b] = tag.getByteArray("map2Values" + b);
		}
		
		return this;
	}
}
