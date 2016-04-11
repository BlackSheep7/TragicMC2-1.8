package tragicneko.tragicmc.dimension;

import java.util.ArrayList;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicMC;

public class NekoHomeworldBiomeGenLayer extends GenLayer {

	private ArrayList<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();

	public NekoHomeworldBiomeGenLayer(long seed) {
		super(seed);
		biomeList.add(TragicBiome.NekoBarrens); //barrens
		biomeList.add(TragicBiome.NekoForest); //forest
		biomeList.add(TragicBiome.NekoHeights); //heights
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++)
		{
			for (int dx = 0; dx < width; dx++)
			{
				this.initChunkSeed(dx + x, dz + z);
				try
				{
					dest[dx + dz * width] = biomeList.get(this.nextInt(biomeList.size())).biomeID;
				}
				catch (Exception e)
				{
					TragicMC.logError("Error adding a biome entry to the gen layer, defaulting biome to Decaying Wasteland", e);
					dest[dx + dz * width] = TragicBiome.DecayingWasteland.biomeID;
					continue;
				}
			}
		}

		return dest;
	}
}
