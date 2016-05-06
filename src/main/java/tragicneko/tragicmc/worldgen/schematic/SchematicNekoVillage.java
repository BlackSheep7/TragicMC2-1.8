package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicNekoVillage extends Schematic {

	public SchematicNekoVillage(BlockPos origin, Structure structure) {
		super(origin, structure, 30, 70, 70);
	}

	@Override
	public Schematic generateStructure(int variant, World world, Random rand, int x, int y, int z) {
		this.setBlock(world, new BlockPos(x, y, z), TragicBlocks.NekitePlate.getStateFromMeta(0));
		byte[][] byteMap = this.generateBlockComposition(rand);
		return this;
	}
	
	public void placeSmallBuilding(World world, Random rand, int x, int y, int z) //builds a small building in a quarter-block (5x5)
	{
		//small home
		//small home - variant
		//small home - variant 2
		//small tower/antenna
		//vacant lot
		//container/storage unit - could be the rare structure that has a good chest inside of it
	}
	
	public void placeLargeBuilding(World world, Random rand, int x, int y, int z) //builds a large building out of a full block (13x13) or (11x11)
	{
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
