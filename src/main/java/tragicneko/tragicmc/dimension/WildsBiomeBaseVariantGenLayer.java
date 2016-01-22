package tragicneko.tragicmc.dimension;

import tragicneko.tragicmc.TragicBiome;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WildsBiomeBaseVariantGenLayer extends GenLayer
{
    private GenLayer blendLayer;

    public WildsBiomeBaseVariantGenLayer(long seed, GenLayer parent, GenLayer blend)
    {
        super(seed);
        this.parent = parent;
        this.blendLayer = blend;
    }

    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = this.blendLayer.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i1 = 0; i1 < areaHeight; ++i1)
        {
            for (int j1 = 0; j1 < areaWidth; ++j1)
            {
                this.initChunkSeed((long)(j1 + areaX), (long)(i1 + areaY));
                int k1 = aint[j1 + 1 + (i1 + 1) * (areaWidth + 2)];
                int l1 = aint1[j1 + 1 + (i1 + 1) * (areaWidth + 2)];
                boolean flag = (l1 - 2) % 29 == 0;

                if (this.nextInt(3) != 0 && !flag)
                {
                    aint2[j1 + i1 * areaWidth] = k1; //random chance to not change it to a variant
                }
                else
                {
                    int i2 = k1;
                    int j2;

                    if (k1 == TragicBiome.WildForest.biomeID)
                    {
                    	if (this.nextInt(3) == 0)
                        {
                            i2 = TragicBiome.WildDenseForest.biomeID;
                        }
                        else
                        {
                            i2 = TragicBiome.WildForestHills.biomeID;
                        }
                    }
                    else if (k1 == TragicBiome.WildPlains.biomeID)
                    {
                    	if (this.nextInt(3) == 0)
                        {
                            i2 = TragicBiome.WildValley.biomeID;
                        }
                        else
                        {
                            i2 = TragicBiome.WildLake.biomeID;
                        }
                    }
                    else if (k1 == TragicBiome.WildHills.biomeID)
                    {
                    	if (this.nextInt(4) == 0)
                        {
                            i2 = TragicBiome.WildMountains.biomeID;
                        }
                        else
                        {
                            i2 = TragicBiome.WildExtremeHills.biomeID;
                        }
                    }
                    else if (k1 == TragicBiome.WildSteppes.biomeID)
                    {
                        i2 = TragicBiome.WildSavanna.biomeID;
                    }
                    else if (k1 == TragicBiome.WildOcean.biomeID)
                    {
                        i2 = TragicBiome.WildDeepOcean.biomeID;
                    }
                    else if (k1 == TragicBiome.WildDeepOcean.biomeID)
                    {
                        j2 = this.nextInt(4);

                        if (j2 == 0)
                        {
                            i2 = TragicBiome.WildIsland.biomeID;
                        }
                        else
                        {
                            i2 = TragicBiome.WildPlains.biomeID;
                        }
                    }

                    if (i2 == k1)
                    {
                        aint2[j1 + i1 * areaWidth] = k1;
                    }
                    else
                    {
                        j2 = aint[j1 + 1 + (i1 + 1 - 1) * (areaWidth + 2)];
                        int k2 = aint[j1 + 1 + 1 + (i1 + 1) * (areaWidth + 2)];
                        int l2 = aint[j1 + 1 - 1 + (i1 + 1) * (areaWidth + 2)];
                        int i3 = aint[j1 + 1 + (i1 + 1 + 1) * (areaWidth + 2)];
                        int j3 = 0;

                        if (biomesEqualOrMesaPlateau(j2, k1))
                        {
                            ++j3;
                        }

                        if (biomesEqualOrMesaPlateau(k2, k1))
                        {
                            ++j3;
                        }

                        if (biomesEqualOrMesaPlateau(l2, k1))
                        {
                            ++j3;
                        }

                        if (biomesEqualOrMesaPlateau(i3, k1))
                        {
                            ++j3;
                        }

                        if (j3 >= 3) //if the biome is bordered by enough similar biomes or mesa plateaus then it uses the hill variant to break up monotony
                        {
                            aint2[j1 + i1 * areaWidth] = i2;
                        }
                        else //otherwise it uses the biome that was there already
                        {
                            aint2[j1 + i1 * areaWidth] = k1;
                        }
                    }
                }
            }
        }

        return aint2;
    }
}