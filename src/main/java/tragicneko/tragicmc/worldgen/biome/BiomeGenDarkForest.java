package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityParasmite;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen2;
import tragicneko.tragicmc.worldgen.WorldGenCustomCanopyTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomTallGrass;

public class BiomeGenDarkForest extends TragicBiome {

	public final SurfaceWorldGen drudgeGen;
	public final SurfaceWorldGen2 gasGen;

	public static final float[][] heights = new float[][] {{0.05F, 0.15F}, {0.32F, 0.32F}, {0.35F, -0.35F}};

	public BiomeGenDarkForest(int par1, byte par2) {
		super(par1, par2);
		this.spawnableMonsterList.clear();
		if (TragicConfig.getBoolean("allowPlague")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPlague.class, TragicConfig.getInt("plagueSpawnChance"), TragicConfig.getIntArray("plagueGroupSize")[0], TragicConfig.getIntArray("plagueGroupSize")[1]));
		if (TragicConfig.getBoolean("allowInkling")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityInkling.class, TragicConfig.getInt("inklingSpawnChance"), TragicConfig.getIntArray("inklingGroupSize")[0], TragicConfig.getIntArray("inklingGroupSize")[1]));
		if (TragicConfig.getBoolean("allowParasmite")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityParasmite.class, TragicConfig.getInt("parasmiteSpawnChance"), TragicConfig.getIntArray("parasmiteGroupSize")[0], TragicConfig.getIntArray("parasmiteGroupSize")[1]));
		if (TragicConfig.getBoolean("allowEnyvil")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityEnyvil.class, TragicConfig.getInt("enyvilSpawnChance"), 0, 0));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 15, 2, 4));
		this.fillerBlock = TragicBlocks.DeadDirt.getDefaultState();
		this.topBlock = TragicBlocks.DarkGrass.getDefaultState();
		this.temperature = 1.2F;
		this.rainfall = 1.5F;
		this.theBiomeDecorator.treesPerChunk = variant == 1 ? 8 : (variant == 2 ? 4 : 12);
		this.theBiomeDecorator.mushroomsPerChunk = variant == 2 ? 12 : 2;
		this.theBiomeDecorator.grassPerChunk = variant == 1 ? 4 : 8;
		this.drudgeGen = new SurfaceWorldGen(3.0D, 4.0D, true, (byte) 24, TragicBlocks.Quicksand, (byte) 2, TragicBlocks.DarkGrass, true, true);
		this.gasGen = new SurfaceWorldGen2((byte) 8, TragicBlocks.DarkGas, (byte) 0, (byte) 4, (byte) 4);
	}
	
	@Override
	public int getFlowersFromBiomeType()
	{
		return variant == 2 ? 2 : 8;
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_)
	{
		return new WorldGenCustomTallGrass(TragicBlocks.DarkTallGrass.getDefaultState());
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand)
	{
		return new WorldGenCustomCanopyTree(TragicBlocks.Darkwood.getDefaultState(), TragicBlocks.DarkLeaves.getDefaultState());
	}

	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		super.decorate(world, rand, pos);

		this.gasGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		if (this.variant == 2) this.drudgeGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
	}

}
