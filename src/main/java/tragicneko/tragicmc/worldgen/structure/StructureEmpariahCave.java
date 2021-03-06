package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicEmpariahCave;

public class StructureEmpariahCave extends Structure {

	public StructureEmpariahCave(int id, String s) {
		super(id, s, 4);
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
		if (!(world.getBiomeGenForCoords(pos) instanceof BiomeGenFrozenTundra) || pos.getY() < 36) return false;
		return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200);
	}
	
	@Override
	public int getStructureColor()
	{
		return 0xFFFFFF;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicEmpariahCave(pos, this, world);
	}
}
