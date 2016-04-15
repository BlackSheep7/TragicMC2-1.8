package tragicneko.tragicmc.dimension;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;

public class TragicBiomeGenLayer extends GenLayer {

	private ArrayList<BiomeEntry>[] biomeList;

	public TragicBiomeGenLayer(long seed)
	{
		super(seed);
		biomeList = new ArrayList[10];

		ArrayList<BiomeEntry> decayingBiomes = new ArrayList<BiomeEntry>();
		decayingBiomes.add(new BiomeEntry(TragicBiome.DecayingWasteland, TragicConfig.getInt("decayingWastelandWeight")));
		decayingBiomes.add(new BiomeEntry(TragicBiome.DecayingHills, TragicConfig.getInt("decayingHillsWeight")));
		decayingBiomes.add(new BiomeEntry(TragicBiome.DecayingMountains, TragicConfig.getInt("decayingMountainsWeight")));
		decayingBiomes.add(new BiomeEntry(TragicBiome.DecayingValley, TragicConfig.getInt("decayingValleyWeight")));

		ArrayList<BiomeEntry> paintedBiomes = new ArrayList<BiomeEntry>();
		paintedBiomes.add(new BiomeEntry(TragicBiome.PaintedForest, TragicConfig.getInt("paintedForestWeight")));
		paintedBiomes.add(new BiomeEntry(TragicBiome.PaintedHills, TragicConfig.getInt("paintedHillsWeight")));
		paintedBiomes.add(new BiomeEntry(TragicBiome.PaintedPlains, TragicConfig.getInt("paintedPlainsWeight")));
		paintedBiomes.add(new BiomeEntry(TragicBiome.PaintedClearing, TragicConfig.getInt("paintedClearingWeight")));

		ArrayList<BiomeEntry> ashenBiomes = new ArrayList<BiomeEntry>();
		ashenBiomes.add(new BiomeEntry(TragicBiome.AshenBadlands, TragicConfig.getInt("ashenBadlandsWeight")));
		ashenBiomes.add(new BiomeEntry(TragicBiome.AshenHills, TragicConfig.getInt("ashenHillsWeight")));
		ashenBiomes.add(new BiomeEntry(TragicBiome.AshenMountains, TragicConfig.getInt("ashenMountainsWeight")));

		ArrayList<BiomeEntry> starlitBiomes = new ArrayList<BiomeEntry>();
		starlitBiomes.add(new BiomeEntry(TragicBiome.StarlitPrarie, TragicConfig.getInt("starlitPrarieWeight")));
		starlitBiomes.add(new BiomeEntry(TragicBiome.StarlitPlateaus, TragicConfig.getInt("starlitPlateausWeight")));
		starlitBiomes.add(new BiomeEntry(TragicBiome.StarlitLowlands, TragicConfig.getInt("starlitLowlandsWeight")));
		starlitBiomes.add(new BiomeEntry(TragicBiome.StarlitCliffs, TragicConfig.getInt("starlitCliffsWeight")));

		ArrayList<BiomeEntry> taintedBiomes = new ArrayList<BiomeEntry>();
		taintedBiomes.add(new BiomeEntry(TragicBiome.TaintedLowlands, TragicConfig.getInt("taintedLowlandsWeight")));
		taintedBiomes.add(new BiomeEntry(TragicBiome.TaintedSpikes, TragicConfig.getInt("taintedSpikesWeight")));
		taintedBiomes.add(new BiomeEntry(TragicBiome.TaintedIsles, TragicConfig.getInt("taintedIslesWeight")));
		taintedBiomes.add(new BiomeEntry(TragicBiome.TaintedRises, TragicConfig.getInt("taintedRisesWeight")));
		taintedBiomes.add(new BiomeEntry(TragicBiome.TaintedScarlands, TragicConfig.getInt("taintedScarlandsWeight")));

		ArrayList<BiomeEntry> hallowedBiomes = new ArrayList<BiomeEntry>();
		hallowedBiomes.add(new BiomeEntry(TragicBiome.HallowedHills, TragicConfig.getInt("hallowedHillsWeight")));
		hallowedBiomes.add(new BiomeEntry(TragicBiome.HallowedForest, TragicConfig.getInt("hallowedForestWeight")));
		hallowedBiomes.add(new BiomeEntry(TragicBiome.HallowedPrarie, TragicConfig.getInt("hallowedPrarieWeight")));
		hallowedBiomes.add(new BiomeEntry(TragicBiome.HallowedCliffs, TragicConfig.getInt("hallowedCliffsWeight")));

		ArrayList<BiomeEntry> scorchedBiomes = new ArrayList<BiomeEntry>();
		scorchedBiomes.add(new BiomeEntry(TragicBiome.ScorchedWastelands, TragicConfig.getInt("scorchedWastelandsWeight")));
		scorchedBiomes.add(new BiomeEntry(TragicBiome.ScorchedValley, TragicConfig.getInt("scorchedValleyWeight")));
		scorchedBiomes.add(new BiomeEntry(TragicBiome.ScorchedScarlands, TragicConfig.getInt("scorchedScarlandsWeight")));

		ArrayList<BiomeEntry> corrodedBiomes = new ArrayList<BiomeEntry>();
		corrodedBiomes.add(new BiomeEntry(TragicBiome.CorrodedSteppe, TragicConfig.getInt("corrodedSteppeWeight")));
		corrodedBiomes.add(new BiomeEntry(TragicBiome.CorrodedHeights, TragicConfig.getInt("corrodedHeightsWeight")));
		corrodedBiomes.add(new BiomeEntry(TragicBiome.CorrodedVeld, TragicConfig.getInt("corrodedVeldWeight")));
		corrodedBiomes.add(new BiomeEntry(TragicBiome.CorrodedRunoff, TragicConfig.getInt("corrodedRunoffWeight")));
		corrodedBiomes.add(new BiomeEntry(TragicBiome.CorrodedFallout, TragicConfig.getInt("corrodedFalloutWeight")));

		ArrayList<BiomeEntry> frozenBiomes = new ArrayList<BiomeEntry>();
		frozenBiomes.add(new BiomeEntry(TragicBiome.FrozenTundra, TragicConfig.getInt("frozenTundraWeight")));
		frozenBiomes.add(new BiomeEntry(TragicBiome.FrozenHills, TragicConfig.getInt("frozenHillsWeight")));
		frozenBiomes.add(new BiomeEntry(TragicBiome.FrozenDepths, TragicConfig.getInt("frozenDepthsWeight")));

		ArrayList<BiomeEntry> darkForestBiomes = new ArrayList<BiomeEntry>();
		darkForestBiomes.add(new BiomeEntry(TragicBiome.DarkForest, TragicConfig.getInt("darkForestWeight")));
		darkForestBiomes.add(new BiomeEntry(TragicBiome.DarkForestHills, TragicConfig.getInt("darkForestHillsWeight")));
		darkForestBiomes.add(new BiomeEntry(TragicBiome.DarkMarsh, TragicConfig.getInt("darkMarshWeight")));

		biomeList[0] = decayingBiomes;
		biomeList[1] = paintedBiomes;
		biomeList[2] = ashenBiomes;
		biomeList[3] = starlitBiomes;
		biomeList[4] = taintedBiomes;
		biomeList[5] = hallowedBiomes;
		biomeList[6] = scorchedBiomes;
		biomeList[7] = corrodedBiomes;
		biomeList[8] = frozenBiomes;
		biomeList[9] = darkForestBiomes;
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++)
		{
			for (int dx = 0; dx < width; dx++)
			{
				this.initChunkSeed(dx + x, dz + z);
				try
				{
					List<BiomeEntry> biomes = biomeList[this.nextInt(biomeList.length)];
					dest[dx + dz * width] = this.nextInt(1000) <= TragicConfig.getInt("crystalWeight") ? TragicBiome.Crystal.biomeID : WeightedRandom.getRandomItem(biomes, this.nextInt(WeightedRandom.getTotalWeight(biomes))).biome.biomeID;
				}
				catch (Exception e)
				{
					TragicMC.logError("Error adding a biome entry to the gen layer, defaulting biome to Decaying Wasteland", e);
					dest[dx + dz * width] = TragicBiome.DecayingWasteland.biomeID;
					continue;
				}
			}
		}

		return dest;
	}
}
