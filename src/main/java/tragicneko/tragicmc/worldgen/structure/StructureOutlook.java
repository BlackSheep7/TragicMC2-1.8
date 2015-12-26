package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.SchematicOutlook;

public class StructureOutlook extends Structure {

	public StructureOutlook(int id, String s) {
		super(new SchematicOutlook(), id, s);
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
		return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200) && pos.getY() < 130;
	}

	@Override
	public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z)
	{
		if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
		return this.schematic.generateStructure(world, rand, x, y, z);
	}
	
	@Override
	public int getStructureColor()
	{
		return 0x000099;
	}

}
