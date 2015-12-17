package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.SchematicObsidianCavern;

public class StructureObsidianCavern extends Structure {

	public StructureObsidianCavern(int id, String name) {
		super(new SchematicObsidianCavern(), id, name);
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
		return dim == TragicConfig.collisionID;
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand)
	{
		if (pos.getY() > 80) return false; //To prevent huge lagspikes from it generating from a high y value
		return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200);
	}

	@Override
	public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z)
	{
		if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
		return this.schematic.generateStructure(variant, world, rand, x, y, z);
	}
	
	@Override
	public int getStructureColor()
	{
		return 0xAAAAAA;
	}
}
