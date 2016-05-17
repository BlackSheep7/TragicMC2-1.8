package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicOutlook;

public class StructureOutlook extends Structure {

	public StructureOutlook(int id, String s) {
		super(id, s, 100);
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
		return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200) && pos.getY() < 130;
	}
	
	@Override
	public int getStructureColor()
	{
		return 0x000099;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicOutlook(pos, this, world);
	}

}
