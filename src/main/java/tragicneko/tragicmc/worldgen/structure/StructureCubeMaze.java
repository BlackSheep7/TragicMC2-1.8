package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.SchematicCubeMaze;

public class StructureCubeMaze extends Structure {

	public StructureCubeMaze(int id, String s) {
		super(new SchematicCubeMaze(), id, s);
	}

	@Override
	public boolean isValidDimension(int dim)
	{
		return dim == TragicConfig.getInt("collisionID");
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand)
	{
		if (pos.getY() >= 48 || world.getTopSolidOrLiquidBlock(pos).down(22).getY() < pos.getY()) return false;
		return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200);
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
		return 0x000000;
	}
}
