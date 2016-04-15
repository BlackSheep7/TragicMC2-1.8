package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityRanmas;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;

public class BiomeGenCrystal extends TragicBiome {

	public CustomSpikesWorldGen crystalGen;

	public BiomeGenCrystal(int par1) {
		super(par1, (byte) 0);
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		if (TragicConfig.getBoolean("allowRanmas")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityRanmas.class, TragicConfig.getInt("ranmasSpawnChance"), TragicConfig.getIntArray("ranmasGroupSize")[0], TragicConfig.getIntArray("ranmasGroupSize")[1]));
		this.enableRain = false;
		this.enableSnow = false;
		this.rainfall = 0F;
		this.temperature = 1F;
		this.maxHeight = 1.25F;
		this.minHeight = 1.04F;
		this.theBiomeDecorator.flowersPerChunk = 0;
		this.theBiomeDecorator.treesPerChunk = 0;
		this.fillerBlock = TragicBlocks.Crystal.getDefaultState();
		this.topBlock = TragicBlocks.Crystal.getDefaultState();
		this.crystalGen = new CustomSpikesWorldGen((byte) 8, TragicBlocks.Crystal, (byte) 0, 0.90477735D, 0.421114525D, 1.15D, 1.15D, false, false);
	}

	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		super.decorate(world, rand, pos);
		this.crystalGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
	}
}
