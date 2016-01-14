package tragicneko.tragicmc.dimension;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;

public class WildsBiomeGenLayer extends GenLayer {
	
	private ArrayList<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();

	public WildsBiomeGenLayer(long seed, GenLayer genlayer)
	{
		this(seed);
		this.parent = genlayer;
	}

	public WildsBiomeGenLayer(long seed)
	{
		super(seed);

		ArrayList<BiomeGenBase> list = new ArrayList<BiomeGenBase>();
		list.add(0, TragicBiome.WildPlains);
		list.add(1, TragicBiome.WildForest);
		list.add(2, TragicBiome.WildDenseForest);
		list.add(3, TragicBiome.WildValley);
		list.add(4, TragicBiome.WildHills);
		list.add(5, TragicBiome.WildExtremeHills);
		list.add(6, TragicBiome.WildSteppes);
		list.add(7, TragicBiome.WildSavanna);
		list.add(8, TragicBiome.WildDesert);
		list.add(9, TragicBiome.WildRiver);
		list.add(10, TragicBiome.WildOcean);
		list.add(11, TragicBiome.WildMountains);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] aint = this.parent.getInts(x, z, width, depth);
		int[] aint1 = IntCache.getIntCache(width * depth);

		for (int i1 = 0; i1 < depth; ++i1)
		{
			for (int j1 = 0; j1 < width; ++j1)
			{
				this.initChunkSeed((long)(j1 + x), (long)(i1 + z));
				int k1 = aint[j1 + i1 * width];
				int l1 = (k1 & 3840) >> 8;
				k1 &= -3841;
			
				if (k1 == TragicBiome.WildOcean.biomeID)
				{
					aint1[j1 + i1 * width] = k1;
				}
				else if (k1 == BiomeGenBase.deepOcean.biomeID)
				{
					aint[j1 + i1 * width] = TragicBiome.WildDeepOcean.biomeID;
				}
				else if (k1 == BiomeGenBase.river.biomeID)
				{
					aint[j1 + i1 + width] = TragicBiome.WildRiver.biomeID;
				}
				else if (k1 == 1 || k1 == 2)
				{
					if (l1 > 0)
					{
						aint1[j1 + i1 * width] = TragicBiome.WildPlains.biomeID; //getBiomeForTemp(BiomeGenBase.TempCategory.MEDIUM);
					}
					else
					{
						aint1[j1 + i1 * width] = TragicBiome.WildDesert.biomeID; //getBiomeForTemp(BiomeGenBase.TempCategory.WARM);
					}
				}
				else if (k1 == 3)
				{
					aint1[j1 + i1 * width] = TragicBiome.WildExtremeHills.biomeID; //getBiomeForTemp(BiomeGenBase.TempCategory.COLD);
				}
				else if (k1 == BiomeGenBase.mushroomIsland.biomeID)
				{
					aint[j1 + i1 * width] = TragicBiome.WildIsland.biomeID;
				}
				else
				{
					aint[j1 + i1 * width] = TragicBiome.WildOcean.biomeID;
				}
			}
		}

		return aint1;
	}

	public int getBiomeForTemp(BiomeGenBase.TempCategory cat)
	{
		ArrayList<BiomeGenBase> list = new ArrayList<BiomeGenBase>();
		for (BiomeGenBase b : biomes)
		{
			if (b != null && b.getTempCategory() == cat) list.add(b);
		}
		
		if (!list.isEmpty())
		{
			if (list.size() == 1) return ((BiomeGenBase) list.get(0)).biomeID;
			else
			{
				return ((BiomeGenBase) list.get(this.nextInt(list.size()))).biomeID;
			}
		}

		return TragicBiome.WildOcean.biomeID;
	}
}
