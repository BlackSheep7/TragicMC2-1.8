package tragicneko.tragicmc.dimension;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import tragicneko.tragicmc.TragicBiome;

public class WildsBiomeGenLayer extends GenLayer {

	public WildsBiomeGenLayer(long seed, GenLayer genlayer)
	{
		super(seed);
		this.parent = genlayer;
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
					if (this.nextInt(3) == 0)
					{
						aint1[j1 + i1 * width] = TragicBiome.WildPlains.biomeID;
					}
					else if (this.nextInt(5) == 0)
					{
						aint[j1 + i1 * width] = TragicBiome.WildSteppes.biomeID;
					}
					else
					{
						aint[j1 + i1 * width] = TragicBiome.WildForest.biomeID;
					}
				}
				else
				{
					aint1[j1 + i1 * width] = TragicBiome.WildDesert.biomeID;
				}
			}
			else if (k1 == 3)
			{

				aint[j1 + i1 * width] = TragicBiome.WildHills.biomeID;

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
}
