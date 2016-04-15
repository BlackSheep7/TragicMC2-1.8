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
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicConfig;

public class NekoHomeworldChunkManager extends WorldChunkManager
{
	private GenLayer genLayerBiomes;
	private GenLayer genLayerBiomeIndex;
	private BiomeCache biomeCache;
	private List<BiomeGenBase> spawnBiomes;

	public NekoHomeworldChunkManager(long seed, WorldType worldType)
	{
		this.biomeCache = new BiomeCache(this);
		this.spawnBiomes = new ArrayList<BiomeGenBase>();
		this.addBiomes(this.spawnBiomes);

		GenLayer[] genLayers = getGenLayers(seed, worldType);
		this.genLayerBiomes = genLayers[0];
		this.genLayerBiomeIndex = genLayers[1];
	}

	public NekoHomeworldChunkManager(World world)
	{
		this(world.getSeed(), world.getWorldInfo().getTerrainType());
	}

	public void addBiomes(List<BiomeGenBase> list)
	{
		list.add(TragicBiome.NekoBarrens);
		list.add(TragicBiome.NekoForest);
		list.add(TragicBiome.NekoHeights);
	}

	public static GenLayer[] getGenLayers(long seed, WorldType worldType)
	{
		GenLayer biomes = new NekoHomeworldBiomeGenLayer(seed);
		biomes = GenLayerZoom.magnify(100L, biomes, TragicConfig.getInt("nekoHomeworldBiomeSize"));
		GenLayer zoom = new GenLayerVoronoiZoom(10L, biomes);

		biomes.initWorldGenSeed(seed);
		zoom.initWorldGenSeed(seed);

		return new GenLayer[] {biomes, zoom};
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