package tragicneko.tragicmc.dimension;

import java.util.List;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.LongHashMap;
import tragicneko.tragicmc.worldgen.subbiome.TragicSubBiome;

import com.google.common.collect.Lists;

public class SubBiomeCache
{
    private final WildsChunkManager chunkManager;
    private long lastCleanupTime;
    private LongHashMap cacheMap = new LongHashMap();
    private List cache = Lists.newArrayList();

    public SubBiomeCache(WildsChunkManager chunkManagerIn)
    {
        this.chunkManager = chunkManagerIn;
    }

    public SubBiomeCache.Block getBiomeCacheBlock(int x, int z)
    {
        x >>= 4;
        z >>= 4;
        long k = (long)x & 4294967295L | ((long)z & 4294967295L) << 32;
        SubBiomeCache.Block block = (SubBiomeCache.Block)this.cacheMap.getValueByKey(k);

        if (block == null)
        {
            block = new SubBiomeCache.Block(x, z);
            this.cacheMap.add(k, block);
            this.cache.add(block);
        }

        block.lastAccessTime = MinecraftServer.getCurrentTimeMillis();
        return block;
    }

    public TragicSubBiome func_180284_a(int x, int z, TragicSubBiome biome)
    {
        TragicSubBiome biomegenbase1 = this.getBiomeCacheBlock(x, z).getBiomeGenAt(x, z);
        return biomegenbase1 == null ? biome : biomegenbase1;
    }

    public void cleanupCache()
    {
        long i = MinecraftServer.getCurrentTimeMillis();
        long j = i - this.lastCleanupTime;

        if (j > 7500L || j < 0L)
        {
            this.lastCleanupTime = i;

            for (int k = 0; k < this.cache.size(); ++k)
            {
                SubBiomeCache.Block block = (SubBiomeCache.Block)this.cache.get(k);
                long l = i - block.lastAccessTime;

                if (l > 30000L || l < 0L)
                {
                    this.cache.remove(k--);
                    long i1 = (long)block.xPosition & 4294967295L | ((long)block.zPosition & 4294967295L) << 32;
                    this.cacheMap.remove(i1);
                }
            }
        }
    }

    public TragicSubBiome[] getCachedBiomes(int x, int z)
    {
        return this.getBiomeCacheBlock(x, z).biomes;
    }

    public class Block
    {
        public float[] rainfallValues = new float[256];
        public TragicSubBiome[] biomes = new TragicSubBiome[256];
        public int xPosition;
        public int zPosition;
        public long lastAccessTime;

        public Block(int x, int z)
        {
            this.xPosition = x;
            this.zPosition = z;
            SubBiomeCache.this.chunkManager.getRainfall(this.rainfallValues, x << 4, z << 4, 16, 16);
            SubBiomeCache.this.chunkManager.getBiomeGenAt(this.biomes, x << 4, z << 4, 16, 16, false);
        }

        public TragicSubBiome getBiomeGenAt(int x, int z)
        {
            return this.biomes[x & 15 | (z & 15) << 4];
        }
    }
}