package tragicneko.tragicmc.dimension;

import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicMC;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WildsRiverMixGenLayer extends GenLayer
{
	private GenLayer biomePatternGeneratorChain;
	private GenLayer riverPatternGeneratorChain;

	public WildsRiverMixGenLayer(long seed, GenLayer biomeLayer, GenLayer riverLayer)
	{
		super(seed);
		this.biomePatternGeneratorChain = biomeLayer;
		this.riverPatternGeneratorChain = riverLayer;
	}

	public void initWorldGenSeed(long seed)
	{
		this.biomePatternGeneratorChain.initWorldGenSeed(seed);
		this.riverPatternGeneratorChain.initWorldGenSeed(seed);
		super.initWorldGenSeed(seed);
	}

	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
	{
		int[] aint = this.biomePatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
		int[] aint1 = this.riverPatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
		int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);

		for (int i1 = 0; i1 < areaWidth * areaHeight; ++i1)
		{
			if (aint[i1] != TragicBiome.WildOcean.biomeID && aint[i1] != TragicBiome.WildDeepOcean.biomeID && aint[i1] != TragicBiome.SeaOfSorrow.biomeID && aint[i1] != TragicBiome.StelSea.biomeID && aint[i1] != TragicBiome.ExivSea.biomeID && aint[i1] != TragicBiome.LeviaTriangle.biomeID && aint[i1] != TragicBiome.DesolateDepths.biomeID)
			{
				if (aint1[i1] == TragicBiome.WildRiver.biomeID || aint1[i1] == BiomeGenBase.river.biomeID)
				{
					aint2[i1] = TragicBiome.WildRiver.biomeID; //add chance for a rare river variant at some point, for now just leave as a normal river
				}
				else //layer corrections for various bugs
				{
					if (aint[i1] < 128)
					{
						TragicMC.logInfo("Vanilla biome creeped into river mix gen layer (" + (aint[i1]) + ")");
						if (aint[i1] == BiomeGenBase.ocean.biomeID) aint2[i1] = TragicBiome.WildOcean.biomeID;
						else if (aint[i1] == BiomeGenBase.plains.biomeID) aint2[i1] = TragicBiome.WildPlains.biomeID;
						else if (aint[i1] == BiomeGenBase.desert.biomeID) aint2[i1] = TragicBiome.WildDesert.biomeID;
						else if (aint[i1] == BiomeGenBase.forest.biomeID) aint2[i1] = TragicBiome.WildForest.biomeID;
						else
						{
							aint2[i1] = aint[i1];
						}
					}
					else if (aint[i1] > 255)
					{
						TragicMC.logInfo("Biome ID was chosen too high for river mix gen layer (" + (aint[i1]) + "), defaulting to Wild Ocean");
						aint2[i1] = TragicBiome.WildOcean.biomeID;
					}
					else
					{
						aint2[i1] = aint[i1];
					}
				}
			}
			else
			{
				aint2[i1] = aint[i1];
			}
		}

		return aint2;
	}
}