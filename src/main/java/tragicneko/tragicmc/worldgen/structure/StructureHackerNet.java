package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenSynapse;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicHackerNet;

public class StructureHackerNet extends Structure {

	public StructureHackerNet(int id, String s) {
		super(id, s, 8);
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
		return 0x777777;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicHackerNet(pos, this, world);
	}
}
