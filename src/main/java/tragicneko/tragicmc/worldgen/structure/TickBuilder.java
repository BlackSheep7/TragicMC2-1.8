package tragicneko.tragicmc.worldgen.structure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.schematic.Schematic.PosPreset;

public class TickBuilder {

	public static final Logger logger = LogManager.getLogger(TragicMC.MODID + "/Tick Builder");
	public ConcurrentHashMap<BlockPos, Schematic> schemas = new ConcurrentHashMap<BlockPos, Schematic>(); //the current builder's scheduled schematics
	public static HashMap<World, TickBuilder> builders = new HashMap<World, TickBuilder>(); //static map of all the current builders
	public final World theWorld; //the world this tick builder is building for
	private boolean shouldBuild = true; //should this builder build
	public static int BUILD_LIMIT = TragicConfig.getInt("tickBuilderSchematicLimit"); //the max amount of blocks to be placed per schematic per tick, should be initialized after the config is set up
	public static int OVERALL_BUILD_LIMIT = TragicConfig.getInt("tickBuilderOverallLimit"); //the max amount of blocks that can be placed per tick
	public static int TICK_RATE = TragicConfig.getInt("tickBuilderTickRate"); //the rate in which the tick builder will run
	public static long tick = 0L; //the ticks we have been running
	public static final String BUILD_TAG = "TragicMC.IncompleteBuilds";
	public static final String STRUCTURE_ID_TAG = "structureID";
	public static final String STRUCTURE_ORIGIN_X_TAG = "posOriginX";
	public static final String STRUCTURE_ORIGIN_Y_TAG = "posOriginY";
	public static final String STRUCTURE_ORIGIN_Z_TAG = "posOriginZ";
	public static final String STRUCTURE_LAST_POS_TAG = "structureLastPosition";

	public TickBuilder() {
		this.theWorld = null;
	}

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
		if (builders.isEmpty()) return;
		if (tick++ % TICK_RATE != 0L) return;
		Iterator<TickBuilder> bldrs = builders.values().iterator();

		while (bldrs.hasNext())
		{
			TickBuilder tb = bldrs.next();
			if (tb.hasFinished() || !tb.shouldBuild || tb.theWorld == null) continue;
			Set<BlockPos> schemas = tb.schemas.keySet();
			Iterator<BlockPos> ite = schemas.iterator();
			int totalPlaces = 0;

			while (ite.hasNext() && totalPlaces < OVERALL_BUILD_LIMIT)
			{
				BlockPos origin = ite.next();
				Schematic sch = tb.schemas.get(origin);
				int placements = 0; //total placements done this tick on this schematic

				if (sch.hasFinished())
				{
					tb.removeSchematic(origin);
					continue;
				}
				else
				{
					final int progress = sch.placedBlocks;

					boolean hasContinued = false;
					int i = 0; //to keep track of iterations we've done through the map

					for (int j = 0; j < sch.list.size() && placements < BUILD_LIMIT && totalPlaces < OVERALL_BUILD_LIMIT; j++)
					{
						if (i++ < progress && !hasContinued) continue;
						hasContinued = true;
						PosPreset pre = sch.list.get(j);
						if (pre != null)
						{
							sch.setMappedBlock(tb.theWorld, pre);
							sch.placedBlocks++;
							placements++;
							totalPlaces++;
						}
					}
					if (sch.hasFinished()) tb.removeSchematic(origin);
				}
			}
		}
	}

	public boolean hasFinished() {
		return this.schemas.isEmpty();
	}

	public synchronized void addSchematic(BlockPos origin, Schematic sch) {
		if (this.schemas.containsKey(origin)) 
		{
			logger.warn("Builder already has a mapping for this schematic's origin, ignoring...");
			return;
		}
		else
		{
			logger.info("Added schematic with origin of " + origin + (sch.structure != null ? (" for the structure, " + sch.structure.getLocalizedName()) : ""));
		}
		this.schemas.put(origin, sch);
	}

	public synchronized void removeSchematic(BlockPos origin) {
		Schematic sch = this.schemas.get(origin);
		logger.info("Removed schematic at origin of " + origin + (sch != null && sch.structure != null ? (" for the structure, " + sch.structure.getLocalizedName()) : ""));
		this.schemas.remove(origin);
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event) {
		if (event.world.isRemote) return;
		if (TickBuilder.getBuilderFor(event.world) != null)
		{
			TickBuilder.getBuilderFor(event.world).continueBuilding();
		}
		else
		{
			new TickBuilder(event.world).continueBuilding();
		}
	}

	@SubscribeEvent
	public void onWorldUnload(WorldEvent.Unload event) {
		if (event.world.isRemote) return;
		if (TickBuilder.getBuilderFor(event.world) != null) TickBuilder.getBuilderFor(event.world).stopBuilding();
	}

	@SubscribeEvent
	public void onChunkLoad(ChunkDataEvent.Load event) {
		if (event.world.isRemote) return;
		if (TickBuilder.getBuilderFor(event.world) != null)
		{
			TickBuilder tb = TickBuilder.getBuilderFor(event.world);
			if (event.getData() != null)
			{
				NBTTagCompound tag = event.getData();
				if (tag.hasKey(BUILD_TAG))
				{
					NBTTagList tagList = tag.getTagList(BUILD_TAG, 10);
					if (tagList.hasNoTags())
					{
						tag.removeTag(BUILD_TAG);
						return;
					}

					for (int i = 0; i < tagList.tagCount(); i++)
					{
						NBTTagCompound tg = tagList.getCompoundTagAt(i);
						if (tg != null)
						{
							int id = tg.getInteger(STRUCTURE_ID_TAG);
							int x = tg.getInteger(STRUCTURE_ORIGIN_X_TAG);
							int y = tg.getInteger(STRUCTURE_ORIGIN_Y_TAG);
							int z = tg.getInteger(STRUCTURE_ORIGIN_Z_TAG);
							BlockPos pos = toPos(new int[] {x, y, z});
							int progress = tg.getInteger(STRUCTURE_LAST_POS_TAG);

							Structure str = Structure.getStructureById(id);
							Schematic sch = str.getSchematicFor(event.world, event.world.rand, pos);

							sch.generateStructure(event.world, event.world.rand, pos.getX(), pos.getY(), pos.getZ());
							sch.placedBlocks = progress;
							sch.retrogradeBuildProgress();
							tb.addSchematic(pos, sch);
						}
					}
				}
			}
		}
	}

	public static BlockPos toPos(int[] array) {
		return new BlockPos(array[0], array[1], array[2]);
	}

	@SubscribeEvent
	public void onChunkLoad(ChunkDataEvent.Save event) {
		if (event.world.isRemote) return;
		TickBuilder tb = TickBuilder.getBuilderFor(event.world);
		if (tb == null) tb = new TickBuilder(event.world);

		if (!tb.hasFinished())
		{
			Iterator<BlockPos> ite = tb.schemas.keySet().iterator();
			NBTTagList tagList = new NBTTagList();
			boolean save = false;

			while (ite.hasNext())
			{
				BlockPos pos = ite.next();
				Schematic sch = tb.schemas.get(pos);
				if (event.getChunk() == event.world.getChunkFromBlockCoords(pos))
				{
					save = true;

					NBTTagCompound tag = new NBTTagCompound();
					int[] array = toArray(pos);
					tag.setInteger(STRUCTURE_ORIGIN_X_TAG, array[0]);
					tag.setInteger(STRUCTURE_ORIGIN_Y_TAG, array[1]);
					tag.setInteger(STRUCTURE_ORIGIN_Z_TAG, array[2]);
					tag.setInteger(STRUCTURE_ID_TAG, sch.structure.getStructureId());
					tag.setInteger(STRUCTURE_LAST_POS_TAG, sch.placedBlocks);
					tagList.appendTag(tag);
					if (!event.getChunk().isLoaded()) tb.removeSchematic(pos);
				}
			}

			if (save) event.getData().setTag(BUILD_TAG, tagList);
		}

	}

	public static int[] toArray(BlockPos pos) {
		return new int[] {pos.getX(), pos.getY(), pos.getZ()};
	}
}
