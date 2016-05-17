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
import tragicneko.tragicmc.worldgen.schematic.SchematicClayTower;
import tragicneko.tragicmc.worldgen.schematic.SchematicCobblestoneTower;
import tragicneko.tragicmc.worldgen.schematic.SchematicDesertTower;
import tragicneko.tragicmc.worldgen.schematic.SchematicIceTower;
import tragicneko.tragicmc.worldgen.schematic.SchematicNetherTower;

public class StructureTower extends Structure {

	public StructureTower(int id, String name) {
		super(id, name, 25);
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
	public int getStructureColor()
	{
		return 0xC3E799;
	}

	@Override
	public Schematic getSchematicFor(World world, Random rand, BlockPos pos) {
		
		return getSchematicFromBiome(world.getBiomeGenForCoords(pos), world, pos);
	}
	
	public Schematic getSchematicFromBiome(BiomeGenBase biome, World world, BlockPos pos) {
		if (biome instanceof BiomeGenMesa) return new SchematicClayTower(pos, this, world);
		else if (biome instanceof BiomeGenDesert) return new SchematicDesertTower(pos, this, world);
		else if (biome instanceof BiomeGenHell || biome instanceof BiomeGenScorchedWasteland || biome instanceof BiomeGenCorrodedSteppe) return new SchematicNetherTower(pos, this, world);
		else if (biome.getTempCategory() == BiomeGenBase.TempCategory.COLD) return new SchematicIceTower(pos, this, world);
		return new SchematicCobblestoneTower(pos, this, world);
	}
}
