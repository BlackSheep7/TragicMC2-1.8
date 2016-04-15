package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.worldgen.RuggedTerrainWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen;
import tragicneko.tragicmc.worldgen.WorldGenCustomSavannaTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomShrubs;
import tragicneko.tragicmc.worldgen.WorldGenCustomTallGrass;

public class BiomeGenAshenHills extends TragicBiome {

	public final SurfaceWorldGen shieldGen;
	public final RuggedTerrainWorldGen ruggedGen;

	public static final float[][] heights = new float[][] {{0.85F, 0.45F}, {0.45F, 0.25F}, {0.05F, 0.05F}};

	public BiomeGenAshenHills(int par1, byte par2) {
		super(par1, par2);
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 50, 3, 5));
		if (TragicConfig.getBoolean("allowInkling")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityInkling.class, TragicConfig.getInt("inklingSpawnChance"), TragicConfig.getIntArray("inklingGroupSize")[0], TragicConfig.getIntArray("inklingGroupSize")[1]));
		if (TragicConfig.getBoolean("allowGragul")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGragul.class, TragicConfig.getInt("gragulSpawnChance"), TragicConfig.getIntArray("gragulGroupSize")[0], TragicConfig.getIntArray("gragulGroupSize")[1]));
		if (TragicConfig.getBoolean("allowKragul")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityKragul.class, TragicConfig.getInt("kragulSpawnChance"), TragicConfig.getIntArray("kragulGroupSize")[0], TragicConfig.getIntArray("kragulGroupSize")[1]));
		if (TragicConfig.getBoolean("allowNorVox")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityNorVox.class, TragicConfig.getInt("norVoxSpawnChance"), TragicConfig.getIntArray("norVoxGroupSize")[0], TragicConfig.getIntArray("norVoxGroupSize")[1]));
		this.enableRain = false;
		this.enableSnow = false;
		this.temperature = 1.6F;
		this.rainfall = 0F;
		this.maxHeight = heights[variant][0];
		this.minHeight = heights[variant][1];
		this.fillerBlock = TragicBlocks.DarkStone.getDefaultState();
		this.topBlock = TragicBlocks.AshenGrass.getDefaultState();
		this.theBiomeDecorator.treesPerChunk = variant == 2 ? 1 : (variant == 1 ? 2 : 4);
		this.theBiomeDecorator.grassPerChunk = variant == 2 ? 3 : 1;
		this.shieldGen = new SurfaceWorldGen(3.0D, 4.0D, false, (byte) 0, TragicBlocks.DeadDirt, (byte) 1, TragicBlocks.AshenGrass, true, false);
		this.ruggedGen = new RuggedTerrainWorldGen(TragicBlocks.DeadDirt, (byte) 1, TragicBlocks.AshenGrass, (byte) 3, 3.0D, 2.0D, false, (byte) 8);
	}

	@Override
	public int getBushesFromBiomeType()
	{
		return 12;
	}
	
	@Override
	public int getFlowersFromBiomeType()
	{
		return variant == 2 ? 8 : 2;
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand)
	{
		if (rand.nextInt(16) == 0)
		{
			return new WorldGenCustomShrubs(TragicBlocks.AshenWood.getDefaultState(), TragicBlocks.AshenLeaves.getDefaultState());
		}
		return new WorldGenCustomSavannaTree(false, TragicBlocks.AshenWood.getDefaultState(), TragicBlocks.AshenLeaves.getDefaultState());
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
	{
		return new WorldGenCustomTallGrass(rand.nextInt(16) == 0 ? TragicBlocks.DriedTallGrass.getDefaultState() : TragicBlocks.AshenTallGrass.getDefaultState());
	}

	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		super.decorate(world, rand, pos);
		if (variant == 2)
		{
			this.shieldGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
			this.ruggedGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		}
	}
}
