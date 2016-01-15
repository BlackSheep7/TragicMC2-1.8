package tragicneko.tragicmc.dimension;

import tragicneko.tragicmc.TragicBiome;
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
            { //ensure it's not an ocean or any of the ocean variants
                if (aint1[i1] == TragicBiome.WildRiver.biomeID || aint[i1] == BiomeGenBase.river.biomeID) //ensure that it is a river that we're messing with
                {
                    if (this.nextInt(57) == 0) //chance to have a rare river variant
                    {
                    	if (this.nextInt(5) == 0)
                    	{
                    		aint2[i1] = TragicBiome.RiverOfSouls.biomeID;
                    	}
                    	else
                    	{
                    		aint2[i1] = TragicBiome.FyxisRiver.biomeID;
                    	}
                    }
                    else
                    {
                        aint2[i1] = TragicBiome.WildRiver.biomeID;
                    }
                }
                else if (aint[i1] > 255)
                {
                	aint2[i1] = TragicBiome.WildOcean.biomeID;
                }
                else
                {
                    aint2[i1] = aint[i1];
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