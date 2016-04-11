package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.entity.mob.EntityJetNeko;
import tragicneko.tragicmc.entity.mob.EntityScienceNeko;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.worldgen.RuggedTerrainWorldGen;
import tragicneko.tragicmc.worldgen.WorldGenCustomOakTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomTallGrass;

public class BiomeGenNekoBarrens extends TragicBiome {

	public final RuggedTerrainWorldGen ruggedGen;
	
	public static final float[][] heights = new float[][] {{0.01F, 0.05F}, {0.12F, 0.06F}, {0.85F, 0.15F}};
	
	public BiomeGenNekoBarrens(int par1, byte par2) {
		super(par1, par2);
		this.enableSnow = false;
		this.temperature = 1.0F;
		this.rainfall = 1.0F;
		this.maxHeight = heights[variant][0];
		this.minHeight = heights[variant][1];
		this.theBiomeDecorator.treesPerChunk = variant == 0 ? 0 : (variant == 1 ? 8 : 2);
		this.theBiomeDecorator.flowersPerChunk = variant == 0 || variant == 3 ? 2 : 12;
		this.theBiomeDecorator.grassPerChunk = 4;
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTragicNeko.class, 25, 0, 1));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJetNeko.class, 3, 0, 0));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityScienceNeko.class, 15, 0, 1));
		this.fillerBlock = TragicBlocks.DeadDirt.getDefaultState();
		this.topBlock = TragicBlocks.NekoGrass.getDefaultState();
		this.ruggedGen = new RuggedTerrainWorldGen(TragicBlocks.DeadDirt, (byte) 0, TragicBlocks.NekoGrass, (byte) 4, 3.0D, 2.0D, false, (byte) 8);
	}
	
	@Override
	public int getFlowersFromBiomeType()
	{
		return 14;
	}
	
	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		return new WorldGenCustomOakTree(false, 4, TragicBlocks.Nekowood.getDefaultState(), TragicBlocks.NekowoodLeaves.getDefaultState());
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return new WorldGenCustomTallGrass(TragicBlocks.NekoBush.getDefaultState());
	}
	
	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		super.decorate(world, rand, pos);
		if (variant == 0) this.ruggedGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
	}
}
