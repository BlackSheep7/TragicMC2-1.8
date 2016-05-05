package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenNekoBarrens;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicNekoHouse;

public class StructureNekoHouse extends Structure {

	public StructureNekoHouse(int id, String s) {
		super(id, s, 10);
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
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand)
	{
		BiomeGenBase biome = world.getBiomeGenForCoords(pos);
		if (biome instanceof BiomeGenNekoBarrens)
		{
			return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200);
		}

		return false;
	}
	
	@Override
	public int getStructureColor()
	{
		return 0x000000;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicNekoHouse(pos, this);
	}
}
