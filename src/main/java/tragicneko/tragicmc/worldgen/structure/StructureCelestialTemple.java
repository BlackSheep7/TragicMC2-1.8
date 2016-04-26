package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicCelestialTemple;

public class StructureCelestialTemple extends StructureBoss {

	public StructureCelestialTemple(int id, String name) {
		super(id, name, new SchematicCelestialTemple(BlockPos.ORIGIN, null).height);
	}

	@Override
	public int getVariantSize()
	{
		return 3;
	}

	@Override
	public boolean isValidDimension(int dim)
	{
		return dim == TragicConfig.getInt("collisionID");
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand)
	{
		if (pos.getY() < 128) return false;
		return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200);
	}
	
	@Override
	public int getStructureColor()
	{
		return 0xAA23AA;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicCelestialTemple(pos, this);
	}
}
