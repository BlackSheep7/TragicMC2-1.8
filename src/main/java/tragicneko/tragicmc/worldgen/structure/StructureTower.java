package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.biome.BiomeGenSnow;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCorrodedSteppe;
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.SchematicDesertTower;

public class StructureTower extends Structure {

	public StructureTower(int id, String name) {
		super(id, name, new SchematicDesertTower(BlockPos.ORIGIN, null).height);
	}
	
	@Override
	public int getVariantSize()
	{
		return 5;
	}

	@Override
	public boolean isSurfaceStructure()
	{
		return true;
	}

	@Override
	public boolean isValidDimension(int dim)
	{
		return dim != -1 && dim != TragicConfig.getInt("synapseID") && dim != 1;
	}

	@Override
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand)
	{
		return super.areCoordsValidForGeneration(world, pos, rand) && this.getRarity(200) && rand.nextInt(4) == 0;
	}

	@Override
	public Schematic generate(World world, Random rand, BlockPos pos)
	{
		return generateStructureWithVariant(this.getVariantFromBiome(world.getBiomeGenForCoords(pos)), world, rand, pos.getX(), pos.getY(), pos.getZ());
	}

	public int getVariantFromBiome(BiomeGenBase biome)
	{
		if (biome instanceof BiomeGenMesa) return 1; //clay tower
		else if (biome instanceof BiomeGenDesert) return 0; //sandstone tower
		else if (biome instanceof BiomeGenHell || biome instanceof BiomeGenScorchedWasteland || biome instanceof BiomeGenCorrodedSteppe) return 3; //netherbrick tower
		else if (biome == BiomeGenBase.coldTaiga || biome == BiomeGenBase.coldTaigaHills || biome == BiomeGenBase.coldBeach ||
				biome instanceof BiomeGenSnow || biome instanceof BiomeGenFrozenTundra) return 4; //ice tower
		return 2; //stone tower
	}

	@Override
	public int getStructureColor()
	{
		return 0xC3E799;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		return new SchematicDesertTower(pos, this);
	}
}
