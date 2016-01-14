package tragicneko.tragicmc.dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicConfig;

public class WildsChunkManager extends WorldChunkManager
{
	private GenLayer genLayerBiomes;
	private GenLayer genLayerBiomeIndex;
	private GenLayer genLayerSubBiomes;
	private GenLayer genLayerSubBiomeIndex;
	private BiomeCache biomeCache;
	private List<BiomeGenBase> spawnBiomes;

	public WildsChunkManager(long seed, WorldType worldType)
	{
		this.biomeCache = new BiomeCache(this);
		this.spawnBiomes = new ArrayList<BiomeGenBase>();
		this.addBiomes(this.spawnBiomes);

		GenLayer[] genLayers = getGenLayers(seed, worldType);
		this.genLayerBiomes = genLayers[0];
		this.genLayerBiomeIndex = genLayers[1];
		this.genLayerSubBiomes = genLayers[2];
		this.genLayerSubBiomeIndex = genLayers[3];
	}

	public WildsChunkManager(World world)
	{
		this(world.getSeed(), world.getWorldInfo().getTerrainType());
	}

	public void addBiomes(List<BiomeGenBase> list)
	{
		list.add(TragicBiome.WildPlains);
		list.add(TragicBiome.WildForest);
		list.add(TragicBiome.WildDenseForest);
		list.add(TragicBiome.WildValley);
		list.add(TragicBiome.WildHills);
		list.add(TragicBiome.WildExtremeHills);
		list.add(TragicBiome.WildSteppes);
		list.add(TragicBiome.WildSavanna);
		list.add(TragicBiome.WildDesert);
		list.add(TragicBiome.WildMountains);
		list.add(TragicBiome.WildIsland);
	}

	public static GenLayer[] getGenLayers(long seed, WorldType worldType)
	{
		GenLayerIsland genlayerisland = new GenLayerIsland(1L);
        GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
        GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayerfuzzyzoom);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(50L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(70L, genlayeraddisland);
        GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland);
        GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
        genlayeraddisland = new GenLayerAddIsland(3L, genlayeraddsnow);
        GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland, GenLayerEdge.Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
        genlayerzoom = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom = new GenLayerZoom(2003L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(4L, genlayerzoom);
        GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
        GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
        GenLayer genlayer2 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
        
        GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer2, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
        
		GenLayer biomes = new WildsBiomeGenLayer(seed, genlayer2);
		biomes = GenLayerZoom.magnify(100L, biomes, 6);
		//biomes = new WildsBiomeEdgeGenLayer(seed, biomes); //Add biome transitions when necessary, so that an icy biome won't be right next to a desert biome
		GenLayer zoom = new GenLayerVoronoiZoom(10L, biomes);
		
		GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        //GenLayerHills genlayerhills = new GenLayerHills(1000L, biomes, genlayer1); //Add base variants (i.e. Dense Forest, Deep Ocean, Forest hills)
        genlayer = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer = GenLayerZoom.magnify(1000L, genlayer, 6);
        GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        //Object object = new GenLayerRareBiome(1001L, genlayerhills); //Add rare biomes, one in 100 chance for each plausible one to generate from a base biome
        /*
        for (int l = 0; l < 6; ++l)
        {
            object = new GenLayerZoom((long)(1000 + l), (GenLayer)object);

            if (l == 0)
            {
                object = new GenLayerAddIsland(3L, (GenLayer)object); //break up huge masses of land or ocean by setting random areas to the opposite
            }
			else if (l == 1)
            {
                object = new GenLayerShore(1000L, (GenLayer)object); //Add shores for biomes that border an ocean or are islands
            }
        } */
        
        //GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer)object); //smooths out the islands and zoom done previously
        //GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth); //generate rivers based on current and previous gen layer
        //GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix); //define the biome border more cleanly
        //genlayerrivermix.initWorldGenSeed(seed);
        //genlayervoronoizoom.initWorldGenSeed(seed);
        
		biomes.initWorldGenSeed(seed);
		zoom.initWorldGenSeed(seed);
		
		GenLayer subBiomes = new WildsSubBiomeGenLayer(seed);
		subBiomes = GenLayerZoom.magnify(110L, subBiomes, 9);
		GenLayer zoom2 = new GenLayerVoronoiZoom(10L, subBiomes);
		
		subBiomes.initWorldGenSeed(seed);
		zoom2.initWorldGenSeed(seed);

		return new GenLayer[] {biomes, zoom, subBiomes, zoom2};
	}

	@Override
	public final List getBiomesToSpawnIn()
	{
		return this.spawnBiomes;
	}

	@Override
	public BiomeGenBase getBiomeGenerator(BlockPos pos)
    {
        return this.getBiomeGenerator(pos, (BiomeGenBase)null);
    }

    public BiomeGenBase getBiomeGenerator(BlockPos pos, BiomeGenBase biome)
    {
        return this.biomeCache.func_180284_a(pos.getX(), pos.getZ(), biome);
    }

	@Override
	public float[] getRainfall(float[] store, int x, int z, int width, int height)
	{
		IntCache.resetIntCache();

		int len = width * height;

		if (store == null || store.length < len)
		{
			store = new float[len];
		}

		int[] ints = this.genLayerBiomeIndex.getInts(x, z, width, height);

		for (int i = 0; i < len; ++i)
		{
			try
			{
				float f = BiomeGenBase.getBiome(ints[i]).getIntRainfall() / 65536.0F;

				if (f > 1.0F)
				{
					f = 1.0F;
				}

				store[i] = f;
			}
			catch (Throwable throwable)
			{
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("DownfallBlock");
				crashreportcategory.addCrashSection("biome id", Integer.valueOf(i));
				crashreportcategory.addCrashSection("downfalls[] size", Integer.valueOf(store.length));
				crashreportcategory.addCrashSection("x", Integer.valueOf(x));
				crashreportcategory.addCrashSection("z", Integer.valueOf(z));
				crashreportcategory.addCrashSection("w", Integer.valueOf(width));
				crashreportcategory.addCrashSection("h", Integer.valueOf(height));
				throw new ReportedException(crashreport);
			}
		}

		return store;
	}

	@Override
	public float getTemperatureAtHeight(float temperature, int y)
	{
		return temperature;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] store, int x, int z, int width, int height)
	{
		IntCache.resetIntCache();

		int len = width * height;

		if (store == null || store.length < len)
		{
			store = new BiomeGenBase[len];
		}

		int[] ints = this.genLayerBiomes.getInts(x, z, width, height);
		for (int i = 0; i < len; ++i)
		{
			try
			{
				store[i] = BiomeGenBase.getBiome(ints[i]);
			}
			catch (Throwable throwable)
			{
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
				crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(store.length));
				crashreportcategory.addCrashSection("x", Integer.valueOf(x));
				crashreportcategory.addCrashSection("z", Integer.valueOf(z));
				crashreportcategory.addCrashSection("w", Integer.valueOf(width));
				crashreportcategory.addCrashSection("h", Integer.valueOf(height));
				throw new ReportedException(crashreport);
			}
		}

		return store;
	}

	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] store, int x, int z, int width, int height)
	{
		return this.getBiomeGenAt(store, x, z, width, height, true);
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] store, int x, int z, int width, int height, boolean cache)
	{
		IntCache.resetIntCache();

		int len = width * height;

		if (store == null || store.length < len)
		{
			store = new BiomeGenBase[len];
		}

		if (cache && width == 16 && height == 16 && (x & 0xF) == 0 && (z & 0xF) == 0)
		{
			BiomeGenBase[] biomes = this.biomeCache.getCachedBiomes(x, z);
			System.arraycopy(biomes, 0, store, 0, len);
			return store;
		}

		int[] ints = this.genLayerBiomeIndex.getInts(x, z, width, height);

		for (int i = 0; i < len; ++i)
		{
			store[i] = BiomeGenBase.getBiome(ints[i]);
		}

		return store;
	}

	@Override
	public boolean areBiomesViable(int x, int z, int range, List biomes)
	{
		IntCache.resetIntCache();
		int x1 = x - range >> 2;
		int z1 = z - range >> 2;
		int x2 = x + range >> 2;
		int z2 = z + range >> 2;
		int x3 = x2 - x1 + 1;
		int z3 = z2 - z1 + 1;
		int len = x3 * z3;
		int[] ints = this.genLayerBiomes.getInts(x1, z1, x3, z3);

		try
		{
			for (int i = 0; i < len; ++i)
			{
				BiomeGenBase biomegenbase = BiomeGenBase.getBiome(ints[i]);
				if (!biomes.contains(biomegenbase) || !(biomegenbase instanceof TragicBiome)) return false;
			}

			return true;
		}
		catch (Throwable throwable)
		{
			CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
			CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
			crashreportcategory.addCrashSection("Layer", this.genLayerBiomes.toString());
			crashreportcategory.addCrashSection("x", Integer.valueOf(x));
			crashreportcategory.addCrashSection("z", Integer.valueOf(z));
			crashreportcategory.addCrashSection("radius", Integer.valueOf(range));
			crashreportcategory.addCrashSection("allowed", biomes);
			throw new ReportedException(crashreport);
		}
	}

	public BlockPos findBiomePosition(int x, int z, int range, List biomes, Random random)
    {
        IntCache.resetIntCache();
        int l = x - range >> 2;
        int i1 = z - range >> 2;
        int j1 = x + range >> 2;
        int k1 = z + range >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genLayerBiomes.getInts(l, i1, l1, i2);
        BlockPos blockpos = null;
        int j2 = 0;

        for (int k2 = 0; k2 < l1 * i2; ++k2)
        {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k2]);

            if (biomes.contains(biomegenbase) && (blockpos == null || random.nextInt(j2 + 1) == 0))
            {
                blockpos = new BlockPos(l2, 0, i3);
                ++j2;
            }
        }

        return blockpos;
    }

	@Override
	public final void cleanupCache()
	{
		this.biomeCache.cleanupCache();
	}
}