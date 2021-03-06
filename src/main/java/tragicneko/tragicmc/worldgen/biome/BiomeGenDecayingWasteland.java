package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.mob.EntitySirv;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;
import tragicneko.tragicmc.worldgen.RuggedTerrainWorldGen;
import tragicneko.tragicmc.worldgen.VoidPitWorldGen;

public class BiomeGenDecayingWasteland extends TragicBiome {

	public final VoidPitWorldGen voidPitGen;
	public final RuggedTerrainWorldGen mixedDirtGen;

	public static final float[][] heights = new float[][] {{0.35F, 0.22F}, {0.05F, -0.35F}, {0.18F, 0.15F}, {0.75F, 0.35F}};

	public BiomeGenDecayingWasteland(int par1, byte par2) {
		super(par1, par2);
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 50, 3, 5));
		if (TragicConfig.getBoolean("allowSirv")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySirv.class, TragicConfig.getInt("sirvSpawnChance"), TragicConfig.getIntArray("sirvGroupSize")[0], TragicConfig.getIntArray("sirvGroupSize")[1]));
		if (TragicConfig.getBoolean("allowSkultar")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityDeathReaper.class, TragicConfig.getInt("skultarSpawnChance"), 0, 0));
		this.enableRain = false;
		this.enableSnow = false;
		this.temperature = 0.6F;
		this.rainfall = 0F;
		this.maxHeight = heights[variant][0];
		this.minHeight = heights[variant][1];
		this.theBiomeDecorator.mushroomsPerChunk = variant == 0 || variant == 3 ? 2 : 8;
		this.fillerBlock = TragicBlocks.DarkStone.getDefaultState();
		this.topBlock = TragicBlocks.DeadDirt.getDefaultState();
		this.theBiomeDecorator.mushroomsPerChunk = 16;
		this.voidPitGen = new VoidPitWorldGen(3.5D, 3.5D);
		this.mixedDirtGen = new RuggedTerrainWorldGen(TragicBlocks.DeadDirt, (byte) 2, TragicBlocks.DeadDirt, (byte) 6, 3.0D, 4.0D, false, (byte) 8);
	}

	@Override
	public int getBushesFromBiomeType()
	{
		return variant == 0 ? 8 : 4;
	}
	
	@Override
	public int getFlowersFromBiomeType()
	{
		return variant == 1 ? 12 : 4;
	}

	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		super.decorate(world, rand, pos);
		new CustomSpikesWorldGen((byte) (variant == 3 ? 6 : 2), TragicBlocks.BoneBlock, (byte) rand.nextInt(2), 0.8977735D, 0.441114525D, 1.0D, 0.35D, false, false).generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		this.mixedDirtGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		if (TragicConfig.getBoolean("allowVoidPitGen") && rand.nextInt(200) >= 5 && rand.nextInt(6) == 0) this.voidPitGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
	}
}
