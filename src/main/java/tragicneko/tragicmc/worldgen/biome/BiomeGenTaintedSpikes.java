package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityPsygote;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;
import tragicneko.tragicmc.worldgen.InvertedSpikeWorldGen;
import tragicneko.tragicmc.worldgen.IsleWorldGen;
import tragicneko.tragicmc.worldgen.RuggedTerrainWorldGen;

public class BiomeGenTaintedSpikes extends TragicBiome {

	private final CustomSpikesWorldGen spikeWorldGen;
	private final InvertedSpikeWorldGen scarGen;
	private final RuggedTerrainWorldGen ruggedGen;
	private final IsleWorldGen isleGen;

	public static final float[][] heights = new float[][] {{0.45F, 0.05F}, {0.35F, -0.95F}, {1.85F, 0.45F}, {0.05F, 0.65F}, {0.25F, -0.65F}};

	public BiomeGenTaintedSpikes(int par1, byte par2) {
		super(par1, par2);
		if (TragicConfig.getBoolean("allowPsygote")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPsygote.class, TragicConfig.getInt("psygoteSpawnChance"), TragicConfig.getIntArray("psygoteGroupSize")[0], TragicConfig.getIntArray("psygoteGroupSize")[1]));
		if (TragicConfig.getBoolean("allowStin")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityStin.class, TragicConfig.getInt("stinSpawnChance"), TragicConfig.getIntArray("stinGroupSize")[0], TragicConfig.getIntArray("stinGroupSize")[1]));
		if (TragicConfig.getBoolean("allowGreaterStin")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityStin.class, TragicConfig.getInt("greaterStinSpawnChance"), TragicConfig.getIntArray("greaterStinGroupSize")[0], TragicConfig.getIntArray("greaterStinGroupSize")[1]));
		this.enableSnow = false;
		this.temperature = 1.8F;
		this.rainfall = 2.0F;
		this.theBiomeDecorator.mushroomsPerChunk = variant == 1 ? 16 : 2;
		this.maxHeight = heights[variant][0];
		this.minHeight = heights[variant][1];
		this.fillerBlock = TragicBlocks.DarkStone.getDefaultState();
		this.topBlock = TragicBlocks.ErodedStone.getDefaultState();
		this.spikeWorldGen = new CustomSpikesWorldGen((byte) (variant == 0 ? 3 : 1), TragicBlocks.Spike, (byte) 0, 0.93477745D, 0.42943755D, 1.5D, 1.0D);
		this.scarGen = new InvertedSpikeWorldGen((byte) 4, 1.5, 2.5, 0.91977745D, 0.48943755D);
		this.ruggedGen = new RuggedTerrainWorldGen(TragicBlocks.ErodedStone, (byte) 2, TragicBlocks.ErodedStone, (byte) 3, 2.0D, 2.5D, true, (byte) 8);
		this.isleGen = new IsleWorldGen();
	}

	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		super.decorate(world, rand, pos);
		if (this.variant == 4 && rand.nextInt(3) == 0) this.isleGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		if (this.variant < 3 && rand.nextInt(100) >= 3 && rand.nextBoolean()) this.spikeWorldGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		if (this.variant == 3)
		{
			if (rand.nextInt(100) > 5 && rand.nextInt(3) != 0) this.scarGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
			this.ruggedGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		}
	}
}
