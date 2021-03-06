package tragicneko.tragicmc.worldgen.biome;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.mob.EntityFusea;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.PitWorldGen;
import tragicneko.tragicmc.worldgen.RuggedTerrainWorldGen;
import tragicneko.tragicmc.worldgen.SurfacePlantWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen2;
import tragicneko.tragicmc.worldgen.VoidPitWorldGen;
import tragicneko.tragicmc.worldgen.WorldGenCustomVine;

public class BiomeGenCorrodedSteppe extends TragicBiome {

	public final SurfaceWorldGen sludgeGen;
	public final RuggedTerrainWorldGen toxicCobbleGen;
	public final SurfaceWorldGen2 gasGen;
	public final VoidPitWorldGen voidPitGen;
	public final WorldGenCustomVine vineGen;
	public final SurfacePlantWorldGen deathglowGen;

	public static final float[][] heights = new float[][] {{0.05F, 0.03F}, {0.35F, 0.83F}, {0.01F, -0.53F}, {0.75F, -0.73F}, {0.01F, -1.25F}};

	public BiomeGenCorrodedSteppe(int par1, byte par2) {
		super(par1, par2);
		this.enableSnow = false;
		this.enableRain = false;
		this.temperature = 1.0F;
		this.rainfall = 0.0F;
		this.maxHeight = heights[variant][0];
		this.minHeight = heights[variant][1];
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = 16;
		this.fillerBlock = TragicBlocks.DarkCobblestone.getDefaultState();
		this.topBlock = TragicBlocks.DarkCobblestone.getDefaultState();
		if (TragicConfig.getBoolean("allowJarra")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJarra.class, TragicConfig.getInt("jarraSpawnChance"), TragicConfig.getIntArray("jarraGroupSize")[0], TragicConfig.getIntArray("jarraGroupSize")[1]));
		if (TragicConfig.getBoolean("allowTox")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTox.class, TragicConfig.getInt("toxSpawnChance"), TragicConfig.getIntArray("toxGroupSize")[0], TragicConfig.getIntArray("toxGroupSize")[1]));
		if (TragicConfig.getBoolean("allowFusea")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityFusea.class, TragicConfig.getInt("fuseaSpawnChance"), TragicConfig.getIntArray("fuseaGroupSize")[0], TragicConfig.getIntArray("fuseaGroupSize")[1]));
		this.sludgeGen = new SurfaceWorldGen(3.0D, 1.5D, true, (byte) 10, TragicBlocks.Quicksand, (byte) 3, TragicBlocks.DarkCobblestone, true, true);
		this.toxicCobbleGen = new RuggedTerrainWorldGen(TragicBlocks.DarkCobblestone, (byte) 2, TragicBlocks.DarkCobblestone, (byte) 6, 4.0D, 3.0D, false, (byte) 8);
		this.gasGen = new SurfaceWorldGen2((byte) (variant == 0 || variant == 4 ? 14 : (variant >= 3 ? 7 : 2)), TragicBlocks.RadiatedGas, (byte) 0, (byte) 4, (byte) 8);
		this.voidPitGen = new VoidPitWorldGen(2.5D, 2.0D);
		this.vineGen = new WorldGenCustomVine(TragicBlocks.WickedVine.getDefaultState(), 84);
		this.deathglowGen = new SurfacePlantWorldGen((byte) 2, TragicBlocks.Deathglow, (byte) 14, (byte) 4, (byte) 4, (byte) 2);
	}

	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		super.decorate(world, rand, pos);

		byte mew = (byte) (variant > 2 ? 4 : (variant == 2 ? 16 : 8));
		int k;
		int l;

		for (byte a = 0; a < mew; ++a)
		{
			k = pos.getX() + rand.nextInt(16) - 8;
			l = pos.getZ() + rand.nextInt(16) - 8;
			this.vineGen.generate(world, rand, new BlockPos(k, rand.nextInt(64) + 42, l));
		}

		int Xcoord = pos.getX() + rand.nextInt(16);
		int Zcoord = pos.getZ() + rand.nextInt(16);
		int Ycoord = rand.nextInt(236) + 10;
		ArrayList<BlockPos> cands;
		cands = WorldHelper.getBlocksInSphericalRange(world, 3.75, Xcoord, Ycoord, Zcoord);
		Block block;
		boolean flag = true;

		for (BlockPos coords : cands)
		{
			block = world.getBlockState(coords).getBlock();
			if (!block.isOpaqueCube() || block.getMaterial() == Material.air || block.getMaterial().isLiquid())
			{
				flag = false;
				break;
			}
		}

		if (flag)
		{
			cands = WorldHelper.getBlocksInSphericalRange(world, 2.75, Xcoord, Ycoord, Zcoord);
			for (BlockPos coords : cands) world.setBlockState(coords, TragicBlocks.ExplosiveGas.getDefaultState());
		}

		this.gasGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		if (variant > 2) this.sludgeGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		this.toxicCobbleGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);

		if (rand.nextInt(8) == 0)
		{
			boolean flag2 = rand.nextBoolean();
			new PitWorldGen(flag2 ? TragicBlocks.Quicksand : TragicBlocks.RadiatedGas, (byte) (flag2 ? 3 : 0), (byte) 12, (byte) 6, 4.0D, 3.0D).generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		}
		if (TragicConfig.getBoolean("allowVoidPitGen") && variant == 4 && rand.nextInt(200) >= 5) this.voidPitGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		if (rand.nextInt(8) == 0) this.deathglowGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
	}

}
