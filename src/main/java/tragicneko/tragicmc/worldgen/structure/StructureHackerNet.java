package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenSynapse;
import tragicneko.tragicmc.worldgen.schematic.SchematicHackerNet;

public class StructureHackerNet extends Structure {

	public StructureHackerNet(int id, String s) {
		super(new SchematicHackerNet(), id, s);
	}

	@Override
	public boolean isValidDimension(int dim)
	{
		return dim == TragicConfig.synapseID;
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand)
	{
		return world.getBiomeGenForCoords(pos) instanceof BiomeGenSynapse && pos.getY() > 24 && pos.getY() < 112 && this.getRarity(200);
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
		return 0x777777;
	}
}
