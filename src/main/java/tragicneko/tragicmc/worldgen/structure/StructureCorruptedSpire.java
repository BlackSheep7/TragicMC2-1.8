package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicCorruptedSpire;

public class StructureCorruptedSpire extends Structure {

	public StructureCorruptedSpire(int id, String s) {
		super(id, s, new SchematicCorruptedSpire(BlockPos.ORIGIN, null).height);
	}

	@Override
	public boolean isSurfaceStructure()
	{
		return true;
	}

	@Override
	public boolean isValidDimension(int dim)
	{
		return dim == TragicConfig.getInt("collisionID");
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand)
	{
		return false; //should not generate naturally
	}
	
	@Override
	public int getStructureColor()
	{
		return 0xDDFFFF;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicCorruptedSpire(pos, this);
	}
}
