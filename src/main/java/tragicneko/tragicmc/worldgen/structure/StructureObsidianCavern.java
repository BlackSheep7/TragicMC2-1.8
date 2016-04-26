package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicObsidianCavern;

public class StructureObsidianCavern extends Structure {

	public StructureObsidianCavern(int id, String name) {
		super(id, name, 6);
	}

	@Override
	public int getVariantSize()
	{
		return 10;
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
		if (pos.getY() > 80) return false; //To prevent huge lagspikes from it generating from a high y value
		return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200);
	}
	
	@Override
	public int getStructureColor()
	{
		return 0xAAAAAA;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicObsidianCavern(pos, this);
	}
}
