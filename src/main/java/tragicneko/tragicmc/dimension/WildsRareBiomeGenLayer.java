package tragicneko.tragicmc.dimension;

import tragicneko.tragicmc.TragicBiome;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WildsRareBiomeGenLayer extends GenLayer
{
	public WildsRareBiomeGenLayer(long seed, GenLayer parent)
	{
		super(seed);
		this.parent = parent;
	}

	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
	{
		int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
		int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

		for (int i1 = 0; i1 < areaHeight; ++i1)
		{
			for (int j1 = 0; j1 < areaWidth; ++j1)
			{
				this.initChunkSeed((long)(j1 + areaX), (long)(i1 + areaY));
				int k1 = aint[j1 + 1 + (i1 + 1) * (areaWidth + 2)];

				if (this.nextInt(48) == 0)
				{
					if (k1 == TragicBiome.WildPlains.biomeID)
					{
						if (this.nextInt(3) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.IriseiPlains.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.SeraleisSerenade.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildForest.biomeID)
					{
						if (this.nextInt(3) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.ImbertonForest.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.UpsidusVeld.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildDenseForest.biomeID)
					{
						aint1[j1 + i1 * areaWidth] = TragicBiome.KlahksTrove.biomeID;
					}
					else if (k1 == TragicBiome.WildValley.biomeID)
					{
						if (this.nextInt(3) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.RelicanthicValley.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.CeresteValley.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildHills.biomeID)
					{
						if (this.nextInt(3) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.KluveTerrace.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.IronveinHills.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildForestHills.biomeID)
					{
						aint1[j1 + i1 * areaWidth] = TragicBiome.HalsydeHills.biomeID;
					}
					else if (k1 == TragicBiome.WildExtremeHills.biomeID)
					{
						if (this.nextInt(3) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.ZybianHeights.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.TurbulentHeights.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildSteppes.biomeID)
					{
						if (this.nextInt(3) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.GandreaSteppes.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.TombstoneFields.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildSavanna.biomeID)
					{
						aint1[j1 + i1 * areaWidth] = TragicBiome.PrahpsPast.biomeID;
					}
					else if (k1 == TragicBiome.WildDesert.biomeID)
					{
						if (this.nextInt(5) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.DesertOfAkhora.biomeID;
						}
						else if (this.nextInt(4) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.VeneriaOasis.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.FerrierScarlands.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildRiver.biomeID)
					{
						if (this.nextInt(3) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.RiverOfSouls.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.FyxisRiver.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildLake.biomeID)
					{
						if (this.nextInt(3) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.VexinLake.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.AggroLake.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildOcean.biomeID)
					{
						if (this.nextInt(5) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.SeaOfSorrow.biomeID;
						}
						else if (this.nextInt(4) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.StelSea.biomeID;
						}
						else
						{
							aint[j1 + i1 * areaWidth] = TragicBiome.ExivSea.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildMountains.biomeID)
					{
						if (this.nextInt(5) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.LarinthianMountains.biomeID;
						}
						else if (this.nextInt(8) == 0)
						{
							aint[j1 + i1 * areaWidth] = TragicBiome.IrsalasVolcano.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.EttenDrove.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildIsland.biomeID)
					{
						if (this.nextInt(3) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.IslaDeMuerte.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.TorsianIsle.biomeID;
						}
					}
					else if (k1 == TragicBiome.WildDeepOcean.biomeID)
					{
						if (this.nextInt(3) == 0)
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.LeviaTriangle.biomeID;
						}
						else
						{
							aint1[j1 + i1 * areaWidth] = TragicBiome.DesolateDepths.biomeID;
						}
					}
					else
					{
						aint1[j1 + i1 * areaWidth] = k1;
					}
				}
				else
				{
					aint1[j1 + i1 * areaWidth] = k1;
				}
			}
		}

		return aint1;
	}
}