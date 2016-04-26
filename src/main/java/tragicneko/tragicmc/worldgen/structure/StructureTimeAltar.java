package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicTimeAltar;

public class StructureTimeAltar extends StructureBoss {

	public StructureTimeAltar(int id, String name) {
		super(id, name, new SchematicTimeAltar(BlockPos.ORIGIN, null).height);
	}

	@Override
	public int getVariantSize()
	{
		return 16;
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
		return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200);
	}
	
	@Override
	public int getStructureColor()
	{
		return 0x23FF23;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicTimeAltar(pos, this);
	}
}
