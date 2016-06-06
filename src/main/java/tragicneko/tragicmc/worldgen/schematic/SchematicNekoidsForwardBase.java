package tragicneko.tragicmc.worldgen.schematic;

import static tragicneko.tragicmc.TragicBlocks.NekiteWire;
import static tragicneko.tragicmc.TragicBlocks.NekitePlate;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicNekoidsForwardBase extends Schematic {

	public SchematicNekoWarehouse[] whSchematics = new SchematicNekoWarehouse[2];
	public SchematicNekoBarracks[] brSchematics = new SchematicNekoBarracks[6];
	public SchematicNekoidsMansion nmSchematic;
	
	private static final BlockPos WAREHOUSE_OFFSET = new BlockPos(-11, 0, -4);
	private static final BlockPos WAREHOUSE2_OFFSET = new BlockPos(-11, 0, 20);
	
	private static final BlockPos[] BARRACKS_OFFSETS = new BlockPos[] {
			new BlockPos(6, 0, -8),
			new BlockPos(6, 0, -24),
			new BlockPos(6, 0, -40),
			new BlockPos(19, 0, -8),
			new BlockPos(19, 0, -24),
			new BlockPos(19, 0, -40)
	};
	
	private static final BlockPos MANSION_OFFSET = new BlockPos(13, 0, 23); //TODO ensure offsets are correct

	public SchematicNekoidsForwardBase(BlockPos origin, Structure structure, World world) {
		super(origin, structure, world, 25, 90, 50);
		
		whSchematics[0] = new SchematicNekoWarehouse(origin.add(WAREHOUSE_OFFSET), structure, world);
		this.setChild(whSchematics[0]);
		whSchematics[1] = new SchematicNekoWarehouse(origin.add(WAREHOUSE2_OFFSET), structure, world);
		this.setChild(whSchematics[1]);

		brSchematics[0] = new SchematicNekoBarracks(origin.add(BARRACKS_OFFSETS[0]), structure, world);
		this.setChild(brSchematics[0]);
		brSchematics[1] = new SchematicNekoBarracks(origin.add(BARRACKS_OFFSETS[1]), structure, world);
		this.setChild(brSchematics[1]);
		brSchematics[2] = new SchematicNekoBarracks(origin.add(BARRACKS_OFFSETS[2]), structure, world);
		this.setChild(brSchematics[2]);
		brSchematics[3] = new SchematicNekoBarracks(origin.add(BARRACKS_OFFSETS[3]), structure, world);
		this.setChild(brSchematics[3]);
		brSchematics[4] = new SchematicNekoBarracks(origin.add(BARRACKS_OFFSETS[4]), structure, world);
		this.setChild(brSchematics[4]);
		brSchematics[5] = new SchematicNekoBarracks(origin.add(BARRACKS_OFFSETS[5]), structure, world);
		this.setChild(brSchematics[5]);
		
		nmSchematic = new SchematicNekoidsMansion(origin.add(MANSION_OFFSET), structure, world);
		this.setChild(nmSchematic);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {
		
		for (byte y1 = -1; y1 < 10; y1++)
		{
			for (byte z1 = -17; z1 < 39; z1++)
			{
				for (byte x1 = -20; x1 < 28; x1++)
				{
					this.setBlockToAir(world, x + x1, y + y1, z + z1);
				}
			}

			for (byte z1 = -48; z1 < -17; z1++)
			{
				for (byte x1 = -4; x1 < 28; x1++)
				{
					this.setBlockToAir(world, x + x1, y + y1, z + z1);
				}
			}
		}

		for (byte z1 = -17; z1 < 39; z1++) //ground layers
		{
			for (byte x1 = -20; x1 < 28; x1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(5));
			}
		}

		for (byte z1 = -48; z1 < -17; z1++) //ground layers
		{
			for (byte x1 = -4; x1 < 28; x1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(5));
			}
		}
		
		for (byte x1 = -2; x1 < 1; x1++) //main road, north-south
		{
			for (byte z1 = -45; z1 < 36; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(2));
			}
		}
		
		for (byte x1 = -20; x1 < 1; x1++) //main road, east-west
		{
			for (byte z1 = 7; z1 < 10; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(2));
			}
		}
		
		for (byte x1 = 0; x1 < 25; x1++) //small roads, east-west
		{
			for (byte z1 = -1; z1 < 2; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(2));
			}
			
			for (byte z1 = -17; z1 < -15; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(2));
			}
			
			for (byte z1 = -33; z1 < -31; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(2));
			}
		}
		
		for (byte x1 = -18; x1 < 1; x1++) //small roads, east-west
		{
			for (byte z1 = -15; z1 < -13; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(2));
			}
		}
		
		for (byte x1 = 12; x1 < 14; x1++) //smaller main road, north-south
		{
			for (byte z1 = -48; z1 < 0; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(2));
			}
		}

		//z + 38 is the south wall
		//z - 17 is the close north wall
		//z - 48 is the far north wall
		//x - 20 is the far west wall
		//x -  4 is the close west wall
		//x + 27 is the east wall

		for (byte y1 = 0; y1 < 4; y1++) //walls
		{
			final IBlockState state = y1 < 2 ? NekitePlate.getStateFromMeta(2) : (y1 != 3 ? NekitePlate.getStateFromMeta(1) : NekiteWire.getStateFromMeta(0));
			for (byte z1 = -17; z1 < 39; z1++)
			{
				this.setBlock(world, new BlockPos(x - 20, y + y1, z + z1), state);
			}

			for (byte z1 = -48; z1 < -16; z1++)
			{
				this.setBlock(world, new BlockPos(x - 4, y + y1, z + z1), state);
			}

			for (byte z1 = -48; z1 < 39; z1++)
			{
				this.setBlock(world, new BlockPos(x + 27, y + y1, z + z1), state);
			}

			for (byte x1 = -20; x1 < 28; x1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y + y1, z + 38), state);
			}

			for (byte x1 = -20; x1 < -3; x1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y + y1, z - 17), state);
			}

			for (byte x1 = -4; x1 < 28; x1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y + y1, z - 48), state);
			}
		}
		
		for (byte b = 0; b < brSchematics.length; b++) //use the pre-created schematics for the various buildings around the base
		{
			BlockPos pos = BARRACKS_OFFSETS[b];
			brSchematics[b].generateStructure(world, rand, x + pos.getX(), y + pos.getY(), z + pos.getZ());
		}
		
		whSchematics[0].generateStructure(world, rand, x + WAREHOUSE_OFFSET.getX(), y + WAREHOUSE_OFFSET.getY(), z + WAREHOUSE_OFFSET.getZ());
		whSchematics[1].generateStructure(world, rand, x + WAREHOUSE2_OFFSET.getX(), y + WAREHOUSE2_OFFSET.getY(), z + WAREHOUSE2_OFFSET.getZ());
		
		nmSchematic.generateStructure(world, rand, x + MANSION_OFFSET.getX(), y + MANSION_OFFSET.getY(), z + MANSION_OFFSET.getZ());

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
