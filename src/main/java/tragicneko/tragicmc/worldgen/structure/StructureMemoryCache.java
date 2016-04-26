package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenSynapse;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicMemoryCache;


public class StructureMemoryCache extends Structure {

	public StructureMemoryCache(int id, String s) {
		super(id, s, new SchematicMemoryCache(BlockPos.ORIGIN, null).height);
	}
	
	@Override
	public boolean isValidDimension(int dim)
	{
		return dim == TragicConfig.getInt("synapseID");
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand)
	{
		return world.getBiomeGenForCoords(pos) instanceof BiomeGenSynapse && pos.getY() > 24 && pos.getY() < 112 && this.getRarity(200);
	}
	
	@Override
	public int getStructureColor()
	{
		return 0x484848;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicMemoryCache(pos, this);
	}
}
