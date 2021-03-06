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
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.structure.Structure;
import tragicneko.tragicmc.worldgen.structure.TickBuilder;

public class StructureWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.isRemote || !world.getWorldInfo().isMapFeaturesEnabled() || random.nextInt(500) > TragicConfig.getInt("structureOverallRarity") ||
				(world.provider.getDimensionId() == TragicConfig.getInt("collisionID") || world.provider.getDimensionId() == TragicConfig.getInt("synapseID") ||
				world.provider.getDimensionId() == TragicConfig.getInt("nekoHomeworldID")) && !TragicConfig.getBoolean("allowDimensions")) return;

		final int x = (chunkX * 16) + random.nextInt(16);
		final int y = random.nextInt(118) + random.nextInt(118) + 10;
		final int z = (chunkZ * 16) + random.nextInt(16);

		final int top = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
		ArrayList<Structure> cands = new ArrayList<Structure>();

		for (int i = 0; i < Structure.getRegistrySize(); i++)
		{
			Structure s = Structure.getStructureById(i);
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
			BlockPos pos = new BlockPos(x, s.isSurfaceStructure() ? top : y, z);
			Schematic sch = s.generate(world, random, pos);
			if (sch != null)
			{
				String str = " successfully generated at " + pos;
				if (TragicConfig.getBoolean("allowTickBuilder"))
				{
					if (TickBuilder.getBuilderFor(world) != null)
					{
						if (TickBuilder.getBuilderFor(world).addSchematic(pos, sch))
						{
							str = " was added to the TickBuilder with origin of " + pos;
						}
						else
						{
							str = " could not be added to the TickBuilder since there is a structure being built at that coordinate already";
						}
					}
					else
					{
						str = " failed to generate due to an error with the TickBuilder, report this.";
					}

				}
				TragicMC.logInfo(s.getLocalizedName() + str);
				break;
			}
		}
	}
}
