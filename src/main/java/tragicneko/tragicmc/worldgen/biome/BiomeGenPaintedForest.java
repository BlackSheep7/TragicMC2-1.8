package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.worldgen.WorldGenCustomHugeJungleTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomOakTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomShrubs;
import tragicneko.tragicmc.worldgen.WorldGenCustomTallGrass;

public class BiomeGenPaintedForest extends TragicBiome {

	public static final float[][] heights = new float[][] {{0.15F, 0.15F}, {0.05F, 0.01F}, {0.35F, 0.18F}, {0.01F, -0.12F}};

	public BiomeGenPaintedForest(int par1, byte par2) {
		super(par1, par2);
		if (TragicConfig.getBoolean("allowPox")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTox.class, TragicConfig.getInt("poxSpawnChance"), TragicConfig.getIntArray("poxGroupSize")[0], TragicConfig.getIntArray("poxGroupSize")[1]));
		if (TragicConfig.getBoolean("allowJanna")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJabba.class, TragicConfig.getInt("jannaSpawnChance"), TragicConfig.getIntArray("jannaGroupSize")[0], TragicConfig.getIntArray("jannaGroupSize")[1]));
		this.fillerBlock = TragicBlocks.DeadDirt.getDefaultState();
		this.topBlock = TragicBlocks.BrushedGrass.getDefaultState();
		this.temperature = 1.2F;
		this.rainfall = 1.5F;
		this.maxHeight = heights[variant][0];
		this.minHeight = heights[variant][1];
		this.theBiomeDecorator.treesPerChunk = variant == 1 ? 2 : (variant == 3 ? -999 : 12);
		this.theBiomeDecorator.mushroomsPerChunk = 4;
		this.theBiomeDecorator.grassPerChunk = variant == 1 || variant == 3 ? 4 : 2;
	}

	@Override
	public int getFlowersFromBiomeType()
	{
		return variant == 3 ? 24 : 6;
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_)
	{
		return new WorldGenCustomTallGrass(TragicBlocks.PaintedTallGrass.getDefaultState());
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand)
	{
		IBlockState wood = TragicBlocks.PaintedWood.getDefaultState();
		IBlockState leaf = TragicBlocks.PaintedLeaves.getDefaultState();
		IBlockState vine = TragicBlocks.Glowvine.getDefaultState();
		WorldGenCustomShrubs shrubs = new WorldGenCustomShrubs(wood, leaf);
		if (variant == 1 || variant == 3)
		{
			if (rand.nextInt(4) != 0)
			{
				return shrubs;
			}
			else
			{
				return new WorldGenCustomOakTree(false, 4, wood, leaf);
			}
		}
		else
		{
			if (rand.nextInt(8) != 0)
			{
				return new WorldGenCustomHugeJungleTree(false, rand.nextInt(3) + 4, 10, leaf, wood, vine);
			}
			else if (rand.nextInt(8) == 0)
			{
				return shrubs;
			}
		}

		return new WorldGenCustomOakTree(false, 6, wood, leaf);
	}
}
