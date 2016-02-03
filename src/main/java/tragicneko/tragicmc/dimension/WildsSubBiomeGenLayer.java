package tragicneko.tragicmc.dimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.subbiome.TragicSubBiome;

public class WildsSubBiomeGenLayer extends GenLayer {

	private List<TragicSubBiome> biomeList = new ArrayList<TragicSubBiome>();

	public WildsSubBiomeGenLayer(long seed, GenLayer genlayer)
	{
		this(seed);
		this.parent = genlayer;
	}

	public WildsSubBiomeGenLayer(long seed)
	{
		super(seed);
		List<TragicSubBiome> list = Arrays.asList(TragicSubBiome.subBiomes);
		for (TragicSubBiome sb : list)
		{
			biomeList.add(sb);
		}
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);
		final int l = biomeList.size();

		for (int dz = 0; dz < depth; dz++)
		{
			for (int dx = 0; dx < width; dx++)
			{
				this.initChunkSeed(dx + x, dz + z);
				try
				{
					dest[dx + dz * width] = biomeList.get(this.nextInt(l)).subID;
				}
				catch (Exception e)
				{
					dest[dx + dz * width] = TragicSubBiome.ferris.subID;
					continue;
				}
			}
		}

		return dest;
	}
}
