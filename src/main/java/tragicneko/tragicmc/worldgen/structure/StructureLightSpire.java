package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenHallowedHills;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicLightSpire;

public class StructureLightSpire extends Structure {

	public StructureLightSpire(int id, String s) {
		super(id, s, new SchematicLightSpire(BlockPos.ORIGIN, null).height);
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
		BiomeGenBase biome = world.getBiomeGenForCoords(pos);
		if (biome instanceof BiomeGenHallowedHills)
		{
			return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200);
		}

		return false;
	}

	@Override
	public int getStructureColor()
	{
		return 0xFFFCAF;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicLightSpire(pos, this);
	}
}
