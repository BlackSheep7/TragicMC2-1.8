package tragicneko.tragicmc.worldgen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class StructureWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.isRemote || !world.getWorldInfo().isMapFeaturesEnabled() || random.nextInt(500) > TragicConfig.structureOverallRarity ||
				(world.provider.getDimensionId() == TragicConfig.collisionID || world.provider.getDimensionId() == TragicConfig.synapseID) && !TragicConfig.allowDimension) return;

		int x = (chunkX * 16) + random.nextInt(16);
		int y = random.nextInt(118) + random.nextInt(118) + 10;
		int z = (chunkZ * 16) + random.nextInt(16);

		int top = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
		ArrayList<Structure> cands = new ArrayList<Structure>();

		for (Structure s : Structure.structureList)
		{
			if (s != null && s.isValidDimension(world.provider.getDimensionId()))
			{
				if (s.isSurfaceStructure() && s.areCoordsValidForGeneration(world, new BlockPos(x, top, z), random) || s.areCoordsValidForGeneration(world, new BlockPos(x, y, z), random))
				{
					cands.add(s);
				}
			}
		}

		if (cands.isEmpty()) return;

		Collections.shuffle(cands, random);

		for (Structure s : cands)
		{
			if (s.generate(world, random, new BlockPos(x, s.isSurfaceStructure() ? top : y, z)))
			{
				TragicMC.logInfo(s.getLocalizedName() + " succesfully generated at " + x + ", " + (s.isSurfaceStructure() ? top : y) + ", " + z);
				break;
			}
		}
	}
}
