package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.blocks.BlockTragicFlower2;
import tragicneko.tragicmc.worldgen.biome.BiomeGenAshenHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDarkForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.biome.BiomeGenHallowedHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenNekoBarrens;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;

public class FlowerWorldGen2 implements IWorldGen {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world) {
		
		if (!TragicConfig.getBoolean("allowFlowerGen")) return;
		int Xcoord = (chunkX * 16);
		int Zcoord = (chunkZ * 16);
		int Ycoord = world.getTopSolidOrLiquidBlock(new BlockPos(Xcoord, 0, Zcoord)).getY();
		BiomeGenBase biome = world.getBiomeGenForCoords(new BlockPos(Xcoord, 0, Zcoord));
		BlockTragicFlower2 flower = (BlockTragicFlower2) TragicBlocks.TragicFlower2;
		byte meta;
		
		if (!(biome instanceof TragicBiome)) return;
		TragicBiome trBiome = (TragicBiome) biome;

		if (biome instanceof BiomeGenDarkForest) meta = (byte) (random.nextBoolean() ? 3 : (random.nextBoolean() ? 13 : 14));
		else if (biome instanceof BiomeGenPaintedForest) meta = (byte) (random.nextBoolean() ? 6 : 7);
		else if (biome instanceof BiomeGenDecayingWasteland) meta = 0;
		else if (biome instanceof BiomeGenStarlitPrarie) meta = 1;
		else if (biome instanceof BiomeGenAshenHills) meta = 2;
		else if (biome instanceof BiomeGenHallowedHills) meta = (byte) (random.nextBoolean() ? 4 : 9);
		else if (biome instanceof BiomeGenFrozenTundra) meta = 5;
		else if (biome instanceof BiomeGenScorchedWasteland) meta = (byte) (random.nextBoolean() ? 10 : (random.nextBoolean() ? 8 : 15));
		else if (biome instanceof BiomeGenNekoBarrens) meta = 11;
		else return;
		
		if (random.nextInt(50) == 0) meta = 11; //black spot is rare in the Collision
		if (random.nextInt(100) == 0) meta = 12; //nannon is even more rare

		for (byte i = 0; i < trBiome.getFlowersFromBiomeType(); i++)
		{
			Xcoord += random.nextInt(8) - random.nextInt(8);
			Zcoord += random.nextInt(8) - random.nextInt(8);
			Ycoord += random.nextInt(2) - random.nextInt(2);

			if (world.isAirBlock(new BlockPos(Xcoord, Ycoord, Zcoord)) &&  Ycoord < 255 && flower.canBlockStay(world, new BlockPos(Xcoord, Ycoord, Zcoord), flower.getStateFromMeta(meta)))
			{
				world.setBlockState(new BlockPos(Xcoord, Ycoord, Zcoord), flower.getStateFromMeta(meta), 2);
			}
		}
	}

}
