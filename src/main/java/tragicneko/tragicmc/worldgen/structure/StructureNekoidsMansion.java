package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenNekoBarrens;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicNekoidsMansion;

public class StructureNekoidsMansion extends Structure {

	public StructureNekoidsMansion(int id, String s) {
		super(id, s, 32);
	}

	@Override
	public boolean isSurfaceStructure()
	{
		return true;
	}

	@Override
	public boolean isValidDimension(int dim)
	{
		return dim == TragicConfig.getInt("nekoHomeworldID");
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand) {
		return false; //doesn't generate naturally as it is meant specifically for testing purposes
	}
	
	@Override
	public int getStructureColor()
	{
		return 0x552323;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicNekoidsMansion(pos, this, world);
	}
}
