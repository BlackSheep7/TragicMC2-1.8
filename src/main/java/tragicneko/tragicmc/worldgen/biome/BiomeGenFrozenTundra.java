package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;
import tragicneko.tragicmc.worldgen.PitWorldGen;
import tragicneko.tragicmc.worldgen.RuggedTerrainWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen2;
import tragicneko.tragicmc.worldgen.WorldGenCustomTallGrass;
import tragicneko.tragicmc.worldgen.WorldGenCustomVine;

public class BiomeGenFrozenTundra extends TragicBiome {

	public final RuggedTerrainWorldGen permafrostGen;
	public final SurfaceWorldGen2 iceSpikeGen;
	public final PitWorldGen pitGen;
	public final WorldGenCustomVine vineGen;

	public final static float[][] heights = new float[][] {{0.01F, 0.12F}, {0.35F, 0.22F}, {0.65F, -1.15F}};

	public BiomeGenFrozenTundra(int par1, byte par2) {
		super(par1, par2);
		this.enableSnow = true;
		this.enableRain = false;
		this.temperature = 0.1F;
		this.rainfall = 0.1F;
		this.maxHeight = heights[variant][0];
		this.minHeight = heights[variant][1];
		this.fillerBlock = TragicBlocks.IcedDirt.getDefaultState();
		this.topBlock = TragicBlocks.Permafrost.getDefaultState();
		if (TragicConfig.getBoolean("allowCryse")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityCryse.class, TragicConfig.getInt("cryseSpawnChance"), TragicConfig.getIntArray("cryseGroupSize")[0], TragicConfig.getIntArray("cryseGroupSize")[1]));
		if (TragicConfig.getBoolean("allowRagr")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityRagr.class, TragicConfig.getInt("ragrSpawnChance"), TragicConfig.getIntArray("ragrGroupSize")[0], TragicConfig.getIntArray("ragrGroupSize")[1]));
		if (TragicConfig.getBoolean("allowAbomination")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityAbomination.class, TragicConfig.getInt("abominationSpawnChance"), TragicConfig.getIntArray("abominationGroupSize")[0], TragicConfig.getIntArray("abominationGroupSize")[1]));
		if (TragicConfig.getBoolean("allowEmpariah")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityYeti.class, TragicConfig.getInt("empariahSpawnChance"), 0, 0));
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = 16;
		this.theBiomeDecorator.grassPerChunk = variant == 1 ? 12 : (variant == 2 ? 6 : 2);
		this.permafrostGen = new RuggedTerrainWorldGen(TragicBlocks.Permafrost, (byte) 1, TragicBlocks.Permafrost, (byte) 2, 2.0D, 3.0D, false, (byte) 8);
		this.iceSpikeGen = new SurfaceWorldGen2((byte) (variant == 2 ? 12 : 4), TragicBlocks.IceSpike, (byte) 0, (byte) 4, (byte) 8);
		this.pitGen = new PitWorldGen(TragicBlocks.IceSpike, (byte) 0, (byte) 12, (byte) 6, 4.0D, 3.0D);
		this.vineGen = new WorldGenCustomVine(TragicBlocks.Moss.getDefaultState(), 84);
	}

	@Override
	public int getFlowersFromBiomeType()
	{
		return variant == 0 ? 6 : 2;
	}

	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		super.decorate(world, rand, pos);

		byte mew = (byte) (variant > 0 ? 18 : 6);
		int k;
		int l;

		for (byte a = 0; a < mew; ++a)
		{
			k = pos.getX() + rand.nextInt(16) - 8;
			l = pos.getZ() + rand.nextInt(16) - 8;
			this.vineGen.generate(world, rand, new BlockPos(k, rand.nextInt(64) + 36, l));
		}

		this.permafrostGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		if (variant != 1) this.iceSpikeGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		new CustomSpikesWorldGen((byte) (variant == 2 ? 6 : (variant == 0 ? 2 : 4)), Blocks.packed_ice, (byte) 0, 0.89477735D, 0.441114525D, 1.0D, 0.35D, false, false).generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		if (rand.nextInt(8) == 0) this.pitGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
	{
		return new WorldGenCustomTallGrass(TragicBlocks.Lichen.getDefaultState());
	}

}
