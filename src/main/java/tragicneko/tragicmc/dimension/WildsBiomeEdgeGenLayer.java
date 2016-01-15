package tragicneko.tragicmc.dimension;

import tragicneko.tragicmc.TragicBiome;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WildsBiomeEdgeGenLayer extends GenLayer
{
    public WildsBiomeEdgeGenLayer(long seed, GenLayer layer)
    {
        super(seed);
        this.parent = layer;
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

                if (!this.replaceBiomeEdgeIfNecessary(aint, aint1, j1, i1, areaWidth, k1, TragicBiome.WildExtremeHills.biomeID, TragicBiome.WildHills.biomeID) && !this.replaceBiomeEdge(aint, aint1, j1, i1, areaWidth, k1, TragicBiome.WildDesert.biomeID, TragicBiome.WildSteppes.biomeID) && !this.replaceBiomeEdge(aint, aint1, j1, i1, areaWidth, k1, TragicBiome.WildSavanna.biomeID, TragicBiome.WildSteppes.biomeID) && !this.replaceBiomeEdge(aint, aint1, j1, i1, areaWidth, k1, TragicBiome.WildMountains.biomeID, TragicBiome.WildExtremeHills.biomeID))
                {
                    int l1;
                    int i2;
                    int j2;
                    int k2;

                    if (k1 == TragicBiome.WildDesert.biomeID)
                    {
                        l1 = aint[j1 + 1 + (i1 + 1 - 1) * (areaWidth + 2)];
                        i2 = aint[j1 + 1 + 1 + (i1 + 1) * (areaWidth + 2)];
                        j2 = aint[j1 + 1 - 1 + (i1 + 1) * (areaWidth + 2)];
                        k2 = aint[j1 + 1 + (i1 + 1 + 1) * (areaWidth + 2)];

                        if (l1 != TragicBiome.WildExtremeHills.biomeID && i2 != TragicBiome.WildExtremeHills.biomeID && j2 != TragicBiome.WildExtremeHills.biomeID && k2 != TragicBiome.WildExtremeHills.biomeID)
                        {
                            aint1[j1 + i1 * areaWidth] = k1;
                        }
                        else
                        {
                            aint1[j1 + i1 * areaWidth] = TragicBiome.WildMountains.biomeID;
                        }
                    }
                    else if (k1 == TragicBiome.WildSteppes.biomeID)
                    {
                        l1 = aint[j1 + 1 + (i1 + 1 - 1) * (areaWidth + 2)];
                        i2 = aint[j1 + 1 + 1 + (i1 + 1) * (areaWidth + 2)];
                        j2 = aint[j1 + 1 - 1 + (i1 + 1) * (areaWidth + 2)];
                        k2 = aint[j1 + 1 + (i1 + 1 + 1) * (areaWidth + 2)];

                        if (l1 != TragicBiome.WildSteppes.biomeID && i2 != TragicBiome.WildSteppes.biomeID && j2 != TragicBiome.WildSteppes.biomeID && k2 != TragicBiome.WildSteppes.biomeID && l1 != TragicBiome.WildExtremeHills.biomeID && i2 != TragicBiome.WildExtremeHills.biomeID && j2 != TragicBiome.WildExtremeHills.biomeID && k2 != TragicBiome.WildExtremeHills.biomeID && l1 != TragicBiome.WildDenseForest.biomeID && i2 != TragicBiome.WildDenseForest.biomeID && j2 != TragicBiome.WildDenseForest.biomeID && k2 != TragicBiome.WildDenseForest.biomeID)
                        {
                            if (l1 != TragicBiome.WildMountains.biomeID && k2 != TragicBiome.WildMountains.biomeID && i2 != TragicBiome.WildMountains.biomeID && j2 != TragicBiome.WildMountains.biomeID)
                            {
                                aint1[j1 + i1 * areaWidth] = k1;
                            }
                            else
                            {
                                aint1[j1 + i1 * areaWidth] = TragicBiome.WildHills.biomeID;
                            }
                        }
                        else
                        {
                            aint1[j1 + i1 * areaWidth] = TragicBiome.WildPlains.biomeID;
                        }
                    }
                    else
                    {
                        aint1[j1 + i1 * areaWidth] = k1;
                    }
                }
            }
        }

        return aint1;
    }

    private boolean replaceBiomeEdgeIfNecessary(int[] arrayP, int[] arrayN, int areaX, int areaY, int areaWidth, int biomeOriginal, int biomeA, int biomeB)
    {
        if (!biomesEqualOrMesaPlateau(biomeOriginal, biomeA))
        {
            return false;
        }
        else
        {
            int k1 = arrayP[areaX + 1 + (areaY + 1 - 1) * (areaWidth + 2)];
            int l1 = arrayP[areaX + 1 + 1 + (areaY + 1) * (areaWidth + 2)];
            int i2 = arrayP[areaX + 1 - 1 + (areaY + 1) * (areaWidth + 2)];
            int j2 = arrayP[areaX + 1 + (areaY + 1 + 1) * (areaWidth + 2)];

            if (this.canBiomesBeNeighbors(k1, biomeA) && this.canBiomesBeNeighbors(l1, biomeA) && this.canBiomesBeNeighbors(i2, biomeA) && this.canBiomesBeNeighbors(j2, biomeA))
            {
                arrayN[areaX + areaY * areaWidth] = biomeOriginal;
            }
            else
            {
                arrayN[areaX + areaY * areaWidth] = biomeB;
            }

            return true;
        }
    }

    private boolean replaceBiomeEdge(int[] arrayP, int[] arrayN, int areaX, int areaY, int areaWidth, int biomeOriginal, int biomeA, int biomeB)
    {
        if (biomeOriginal != biomeA)
        {
            return false;
        }
        else
        {
            int k1 = arrayP[areaX + 1 + (areaY + 1 - 1) * (areaWidth + 2)];
            int l1 = arrayP[areaX + 1 + 1 + (areaY + 1) * (areaWidth + 2)];
            int i2 = arrayP[areaX + 1 - 1 + (areaY + 1) * (areaWidth + 2)];
            int j2 = arrayP[areaX + 1 + (areaY + 1 + 1) * (areaWidth + 2)];

            if (biomesEqualOrMesaPlateau(k1, biomeA) && biomesEqualOrMesaPlateau(l1, biomeA) && biomesEqualOrMesaPlateau(i2, biomeA) && biomesEqualOrMesaPlateau(j2, biomeA))
            {
                arrayN[areaX + areaY * areaWidth] = biomeOriginal;
            }
            else
            {
                arrayN[areaX + areaY * areaWidth] = biomeB;
            }

            return true;
        }
    }

    private boolean canBiomesBeNeighbors(int p_151634_1_, int p_151634_2_)
    {
        if (biomesEqualOrMesaPlateau(p_151634_1_, p_151634_2_))
        {
            return true;
        }
        else
        {
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(p_151634_1_);
            BiomeGenBase biomegenbase1 = BiomeGenBase.getBiome(p_151634_2_);

            if (biomegenbase != null && biomegenbase1 != null)
            {
                BiomeGenBase.TempCategory tempcategory = biomegenbase.getTempCategory();
                BiomeGenBase.TempCategory tempcategory1 = biomegenbase1.getTempCategory();
                return tempcategory == tempcategory1 || tempcategory == BiomeGenBase.TempCategory.MEDIUM || tempcategory1 == BiomeGenBase.TempCategory.MEDIUM;
            }
            else
            {
                return false;
            }
        }
    }
}