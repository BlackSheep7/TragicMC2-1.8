package tragicneko.tragicmc.worldgen.schematic;

import static tragicneko.tragicmc.TragicBlocks.NekitePlate;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicNekoVillage extends Schematic {

	public SchematicNekoVillage(BlockPos origin, Structure structure) {
		super(origin, structure, 30, 70, 70);
	}

	@Override
	public Schematic generateStructure(int variant, World world, Random rand, int x, int y, int z) {
		this.generateRoads(world, rand, x, y, z);
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(0));
		byte[][] byteMap = this.generateBlockComposition(rand);
		int[] offsets = new int[] {-17, 0, 17};

		for (byte b = 0; b < byteMap.length; b++)
		{
			for (byte b2 = 0; b2 < byteMap[b].length; b2++)
			{
				final byte bit = byteMap[b][b2]; //type of block composition
				final int xOffset = offsets[(b % 3)]; //offset for the x to the center of the block
				final int zOffset = offsets[b2]; //offset for the z to the center of the block

				this.placeSmallBuildings(world, rand, x + xOffset, y, z + zOffset);
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
					if (y1 == 7 && x1 == -25) continue;
					final int i = y1 >= 6 ? 1 : 2;
					this.setBlock(world, new BlockPos(x + x1, y + y1, z + z1), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + Math.abs(x1), y + y1, z + z1), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + z1, y + y1, z + x1), NekitePlate.getStateFromMeta(i));
					this.setBlock(world, new BlockPos(x + z1, y + y1, z + Math.abs(x1)), NekitePlate.getStateFromMeta(i));
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

	public void placeSmallBuildings(World world, Random rand, int x, int y, int z) //builds a small building in a quarter-block (5x5)
	{
		byte[][] offsets = new byte[][] {{-3, 3}, {3, 3}, {-3, -3}, {3, -3}}; //top left, top right, bottom left, bottom right
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x, y + 1, z), NekitePlate.getStateFromMeta(0));
		
		//streetlights
		for (byte y1 = 0; y1 < 4; y1++)
		{
			final int state = y1 == 3 ? 0 : 1;
			this.setBlock(world, new BlockPos(x + 6, y + y1, z + 6), NekitePlate.getStateFromMeta(state));
			this.setBlock(world, new BlockPos(x - 6, y + y1, z + 6), NekitePlate.getStateFromMeta(state));
			this.setBlock(world, new BlockPos(x + 6, y + y1, z - 6), NekitePlate.getStateFromMeta(state));
			this.setBlock(world, new BlockPos(x - 6, y + y1, z - 6), NekitePlate.getStateFromMeta(state));
		}

		for (byte b = 0; b < offsets.length; b++)
		{
			this.setBlock(world, new BlockPos(x + offsets[b][0], y, z + offsets[b][1]), NekitePlate.getStateFromMeta(0));

			this.setBlock(world, new BlockPos(x + offsets[b][0] + 2, y, z + offsets[b][1]), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + offsets[b][0] - 2, y, z + offsets[b][1]), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + offsets[b][0], y, z + offsets[b][1] + 2), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + offsets[b][0], y, z + offsets[b][1] - 2), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + offsets[b][0] + 2, y, z + offsets[b][1] + 2), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + offsets[b][0] - 2, y, z + offsets[b][1] - 2), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + offsets[b][0] - 2, y, z + offsets[b][1] + 2), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + offsets[b][0] + 2, y, z + offsets[b][1] - 2), NekitePlate.getStateFromMeta(2));
		}
		//small home
		//small home - variant
		//small home - variant 2
		//small tower/antenna
		//vacant lot
		//container/storage unit - could be the rare structure that has a good chest inside of it
	}

	public void placeLargeBuilding(World world, Random rand, int x, int y, int z) //builds a large building out of a full block (13x13) or (11x11)
	{
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x, y + 1, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x, y + 2, z), NekitePlate.getStateFromMeta(0));
		//fountain
		//kitchen - has food chests in it
		//blacksmith - has decent chests in it
		//plaza
		//housing complex
	}

	public byte[][] generateBlockComposition(Random rand) { //creates a bytemap overlay for the schematic, these change what generates for each "block"
		return new byte[][]{
			{0, 0, 0},
			{0, 0, 0},
			{0, 0, 0}
		};
	}

}
