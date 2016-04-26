package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicSoulTomb;

public class StructureSoulTomb extends Structure {

	public StructureSoulTomb(int id, String s) {
		super(id, s, new SchematicSoulTomb(BlockPos.ORIGIN, null).height);
	}

	@Override
	public int getVariantSize()
	{
		return SchematicSoulTomb.blocks.length;
	}

	@Override
	public boolean isValidDimension(int dim)
	{
		return dim == TragicConfig.getInt("collisionID");
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand)
	{
		if (pos.getY() >= 62 || rand.nextInt(8) != 0 || world.getTopSolidOrLiquidBlock(pos).getY() < pos.getY()) return false;
		return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200);
	}

	@Override
	public boolean generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z)
	{
		if (!super.generateStructureWithVariant(variant, world, rand, x, y, z)) return false;
		return this.getSchematicFor(world, rand, new BlockPos(x, y, z)).generateStructure(variant, world, rand, x, y, z);
	}

	@Override
	public int getStructureColor()
	{
		return 0x333333;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicSoulTomb(pos, this);
	}
}
