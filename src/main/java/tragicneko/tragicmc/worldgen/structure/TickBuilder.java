package tragicneko.tragicmc.worldgen.structure;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.Schematic.PosPreset;

public class TickBuilder {

	public static final Logger logger = LogManager.getLogger(TragicMC.MODID + "/Tick Builder");
	public HashMap<BlockPos, Schematic> schemas = new HashMap<BlockPos, Schematic>(); //the current builder's scheduled schematics
	public static HashMap<World, TickBuilder> builders = new HashMap<World, TickBuilder>(); //static map of all the current builders
	public final World theWorld; //the world this tick builder is building for
	private boolean shouldBuild = true; //should this builder build
	public static int BUILD_LIMIT = 1000; //the max amount of blocks to be placed per schematic per tick

	public TickBuilder(World world) {
		this.theWorld = world;
		this.builders.put(world, this);
	}

	public static TickBuilder getBuilderFor(World world) {
		return builders.get(world);
	}

	public void continueBuilding() {
		this.shouldBuild = true;
	}

	public void stopBuilding() {
		this.shouldBuild = false;
	}

	@SubscribeEvent
	public void onTick(ServerTickEvent event)
	{
		if (schemas.isEmpty() || !this.shouldBuild || this.theWorld == null) return;

		Iterator<BlockPos> ite = schemas.keySet().iterator();
		while (ite.hasNext())
		{
			final BlockPos origin = ite.next();
			final Schematic sch = schemas.get(origin);

			if (sch.hasFinished())
			{
				schemas.remove(origin);
				continue;
			}
			else
			{
				final int[] prog = sch.placedPosition;
				int placements = 0;
				meow: for (int i = prog[0]; i < sch.structureHeight; i++)
				{
					for (int j = prog[1]; j < sch.height; j++)
					{
						for (int k = prog[2]; k < sch.width; k++)
						{
							if (placements > BUILD_LIMIT) break meow;
							PosPreset preset = sch.matrix.get(i)[j][k];
							if (preset != null)
							{
								sch.setMatrixBlock(theWorld, origin, preset);
								sch.placedPosition = new int[] {i, j, k};
								placements++;
							}
						}
					}
				}

				sch.updateBuildProgress();

				if (sch.hasFinished()) schemas.remove(origin);
			}
		}
	}
}
