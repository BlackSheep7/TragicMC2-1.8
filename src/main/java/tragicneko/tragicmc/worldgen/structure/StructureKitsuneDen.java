package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenHell;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicKitsuneDen;

public class StructureKitsuneDen extends Structure {

	public StructureKitsuneDen(int id, String name) {
		super(id, name, 12);
	}

	@Override
	public boolean isValidDimension(int dim)
	{
		return dim == -1 || dim == TragicConfig.getInt("collisionID");
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand)
	{
		if (world.provider.getDimensionId() != -1 && pos.getY() >= 62 || world.getTopSolidOrLiquidBlock(pos).getY() < pos.getY()) return false;
		BiomeGenBase biome = world.getBiomeGenForCoords(pos);
		if (biome instanceof BiomeGenHell || biome instanceof BiomeGenScorchedWasteland)
		{
			return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200);
		}
		return false;
	}
	
	@Override
	public int getStructureColor()
	{
		return 0xAF0000;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicKitsuneDen(pos, this, world);
	}
}
