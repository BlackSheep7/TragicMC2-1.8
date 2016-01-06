package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;
import tragicneko.tragicmc.worldgen.WorldGenCustomLollipopTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomTallGrass;

public class BiomeGenStarlitPrarie extends TragicBiome {

	public CustomSpikesWorldGen crystalWorldGen;

	public static final float[][] heights = new float[][] {{0.05F, 0.65F}, {0.01F, 1.62F}, {1.55F, 0.95F}, {0.23F, -0.14F}};

	public BiomeGenStarlitPrarie(int par1, byte par2) {
		super(par1, par2);
		if (TragicConfig.allowStarCryse) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityCryse.class, TragicConfig.starCryseSC, TragicConfig.starCryseGS[0], TragicConfig.starCryseGS[1]));
		if (TragicConfig.allowStarVox) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityNorVox.class, TragicConfig.starVoxSC, TragicConfig.starVoxGS[0], TragicConfig.starVoxGS[1]));
		if (TragicConfig.allowVoxStellarum) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityVoxStellarum.class, TragicConfig.voxStellarumSC, TragicConfig.voxStellarumGS[0], TragicConfig.voxStellarumGS[1]));
		if (TragicConfig.allowPolaris) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPolaris.class, TragicConfig.polarisSC, 0, 0));
		this.enableSnow = false;
		this.temperature = 1.0F;
		this.rainfall = 0.6F;
		this.maxHeight = heights[variant][0];
		this.minHeight = heights[variant][1];
		this.fillerBlock = TragicBlocks.DeadDirt.getDefaultState();
		this.topBlock = TragicBlocks.StarlitGrass.getDefaultState();
		this.theBiomeDecorator.treesPerChunk = variant == 1 ? -999 : 2;
		this.theBiomeDecorator.grassPerChunk = variant == 2 || variant == 1 ? 5 : 12;
		byte relay = (byte) (variant == 1 ? 1 : (variant == 2 ? 6 : 3));
		double spikeSize = variant == 2 ? 1.75D : 1.10D;
		double spikeV = variant == 2 ? 1.0D : 0.35D;
		this.crystalWorldGen = new CustomSpikesWorldGen(relay, TragicBlocks.StarCrystal, (byte) 0, 0.91377745D, 0.414443755D, spikeSize, spikeV, false, true);
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
	{
		return new WorldGenCustomTallGrass(TragicBlocks.StarlitTallGrass.getDefaultState());
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand)
	{
		return new WorldGenCustomLollipopTree(TragicBlocks.BleachedWood.getDefaultState(), TragicBlocks.BleachedLeaves.getDefaultState());
	}

	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		super.decorate(world, rand, pos);
		if (rand.nextBoolean() && rand.nextInt(100) <= 12) this.crystalWorldGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
	}
}
