package tragicneko.tragicmc.worldgen.biome;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.InvertedSpikeWorldGen;
import tragicneko.tragicmc.worldgen.PitWorldGen;
import tragicneko.tragicmc.worldgen.SurfaceWorldGen2;

public class BiomeGenScorchedWasteland extends TragicBiome {

	public final SurfaceWorldGen2 fireGen;
	public final PitWorldGen pitGen;
	public final InvertedSpikeWorldGen scarGen;

	public static final float[][] heights = new float[][] {{0.15F, 0.05F}, {0.05F, -0.45F}, {0.45F, -0.05F}};

	public BiomeGenScorchedWasteland(int par1, byte par2) {
		super(par1, par2);
		this.spawnableCreatureList.clear();
		if (TragicConfig.getBoolean("allowKindlingSpirit")) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWisp.class, TragicConfig.getInt("kindlingSpiritSpawnChance"), TragicConfig.getIntArray("kindlingSpiritGroupSize")[0], TragicConfig.getIntArray("kindlingSpiritGroupSize")[1]));
		if (TragicConfig.getBoolean("allowJabba")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJabba.class, TragicConfig.getInt("jabbaSpawnChance"), TragicConfig.getIntArray("jabbaGroupSize")[0], TragicConfig.getIntArray("jabbaGroupSize")[1]));
		if (TragicConfig.getBoolean("allowMagmox")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMagmox.class, TragicConfig.getInt("magmoxSpawnChance"), TragicConfig.getIntArray("magmoxGroupSize")[0], TragicConfig.getIntArray("magmoxGroupSize")[1]));
		if (TragicConfig.getBoolean("allowKitsunakuma")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityKitsune.class, TragicConfig.getInt("kitsunakumaSpawnChance"), 0, 0));
		this.enableSnow = false;
		this.enableRain = false;
		this.temperature = 2.0F;
		this.rainfall = 0.0F;
		this.maxHeight = heights[variant][0];
		this.minHeight = heights[variant][1];
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = -999;
		this.fillerBlock = TragicBlocks.ScorchedRock.getDefaultState();
		this.topBlock = TragicBlocks.MoltenRock.getDefaultState();
		this.fireGen = new SurfaceWorldGen2((byte) (variant == 2 ? 16 : 4), Blocks.fire, (byte) 0, (byte) 8, (byte) 4);
		this.pitGen = new PitWorldGen(Blocks.flowing_lava, (byte) 0, (byte) 12, (byte) 6, 4.0D, 3.0D);
		this.scarGen = new InvertedSpikeWorldGen((byte) 6, 1.5, 2.5, 0.90977745D, 0.48943755D);
	}
	
	@Override
	public int getFlowersFromBiomeType()
	{
		return variant == 2 ? 4 : 0;
	}

	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		super.decorate(world, rand, pos);

		int Xcoord = pos.getX() + rand.nextInt(16);
		int Zcoord = pos.getZ() + rand.nextInt(16);
		int Ycoord = world.getTopSolidOrLiquidBlock(pos).getY();

		byte mew = (byte) (variant == 2 ? 8 : 2);
		ArrayList<BlockPos> cands = new ArrayList<BlockPos>();
		Block block;
		
		byte i;

		for (i = 0; i < mew; i++)
		{
			Xcoord = pos.getX() + rand.nextInt(16);
			Zcoord = pos.getZ() + rand.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(new BlockPos(Xcoord, 0, Zcoord)).down().getY();

			block = world.getBlockState(new BlockPos(Xcoord, Ycoord, Zcoord)).getBlock();
			if (block == TragicBlocks.MoltenRock && rand.nextInt(4) == 0)
			{
				world.setBlockState(new BlockPos(Xcoord, Ycoord, Zcoord), TragicBlocks.Geyser.getDefaultState());
				world.setBlockState(new BlockPos(Xcoord, Ycoord - 1, Zcoord), Blocks.lava.getDefaultState());
			}
		}

		mew = (byte) (variant == 2 ? 10 : 5);

		for (i = 0; i < mew; i++)
		{
			Xcoord = pos.getX() + rand.nextInt(16);
			Zcoord = pos.getZ() + rand.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(pos).down().getY();

			block = world.getBlockState(new BlockPos(Xcoord, Ycoord, Zcoord)).getBlock();
			if (block == TragicBlocks.MoltenRock && rand.nextInt(4) == 0) world.setBlockState(new BlockPos(Xcoord, Ycoord, Zcoord), TragicBlocks.SteamVent.getDefaultState());
		}

		mew = (byte) (variant == 0 ? 8 : 2);

		for (i = 0; i < mew; i++)
		{
			Xcoord = pos.getX() + rand.nextInt(16);
			Zcoord = pos.getZ() + rand.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(new BlockPos(Xcoord, 0, Zcoord)).down().getY();

			block = world.getBlockState(new BlockPos(Xcoord, Ycoord, Zcoord)).getBlock();

			if (block == TragicBlocks.MoltenRock && rand.nextInt(4) == 0)
			{
				cands.clear();
				cands.addAll(WorldHelper.getBlocksInSphericalRange(world, (rand.nextDouble() * 2.25) + 1.5, Xcoord, Ycoord - 1, Zcoord));

				for (BlockPos coords : cands)
				{
					block = world.getBlockState(coords).getBlock();
					if (block.isReplaceable(world, coords)) world.setBlockState(coords, TragicBlocks.ScorchedRock.getDefaultState());
				}
			}
		}
		if (rand.nextInt(8) == 0) this.pitGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		this.fireGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
		if (variant == 2 && rand.nextInt(100) > 3 && rand.nextInt(6) != 0) this.scarGen.generate(rand, pos.getX() / 16, pos.getZ() / 16, world);
	}
}
