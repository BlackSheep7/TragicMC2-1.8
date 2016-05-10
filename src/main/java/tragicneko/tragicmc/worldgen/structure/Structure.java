package tragicneko.tragicmc.worldgen.structure;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.schematic.Schematic;

public abstract class Structure {

	protected final int height;
	
	public static final RegistryNamespacedDefaultedByKey<ResourceLocation, Structure> structureRegistry = new RegistryNamespacedDefaultedByKey<ResourceLocation, Structure>(new ResourceLocation("null"));

	public static Structure apisTemple = new StructureApisTemple(0, "apisTemple");
	public static Structure towerStructure = new StructureTower(1, "tower");
	public static Structure deathCircle = new StructureDeathCircle(2, "deathCircle");
	public static Structure obsidianCavern = new StructureObsidianCavern(3, "obsidianCavern");
	public static Structure kitsuneDen = new StructureKitsuneDen(4, "kitsuneDen");
	public static Structure celestialTemple = new StructureCelestialTemple(5, "celestialTemple");
	public static Structure timeAltar = new StructureTimeAltar(6, "timeAltar");
	public static Structure soulTomb = new StructureSoulTomb(7, "soulTomb");
	public static Structure corruptedSpire = new StructureCorruptedSpire(8, "corruptedSpire");
	public static Structure empariahCave = new StructureEmpariahCave(9, "empariahCave");
	public static Structure claymationRuin = new StructureClaymationRuin(10, "claymationRuin");
	public static Structure darkHut = new StructureDarkHut(11, "darkHut");
	public static Structure spiderNest = new StructureSpiderNest(12, "spiderNest"); 
	public static Structure memoryCache = new StructureMemoryCache(13, "memoryCache");
	public static Structure lightSpire = new StructureLightSpire(14, "lightSpire");
	public static Structure hackerNet = new StructureHackerNet(15, "hackerNet");
	public static Structure cubeMaze = new StructureCubeMaze(16, "cubeMaze");
	public static Structure outlook = new StructureOutlook(17, "outlook");
	public static Structure nekoBarracks = new StructureNekoBarracks(18, "nekoBarracks");
	public static Structure nekoWarehouse = new StructureNekoWarehouse(19, "nekoWarehouse");
	public static Structure nekoGuardTower = new StructureNekoTower(20, "nekoGuardTower");
	public static Structure nekoCottage = new StructureNekoHouse(21, "nekoCottage");
	public static Structure nekoVillage = new StructureNekoVillage(22, "nekoVillage");
	public static Structure nekoidsForwardBase = new StructureNekoidsForwardBase(23, "nekoidsForwardBase");

	public Structure(int id, String s, int height)
	{
		ResourceLocation rl = new ResourceLocation("tragicmc:" + s);
		if (structureRegistry.containsKey(rl)) throw new IllegalArgumentException("There is a Structure using that name (" + rl + ") already!");
		structureRegistry.register(id, rl, this);
		this.height = height;
	}

	/**
	 * Override to set a variant amount for a particular structure
	 * @return
	 */
	public int getVariantSize()
	{
		return 1;
	}

	/**
	 * Whether or not this particular structure should only generate on a solid surface
	 * @return
	 */
	public boolean isSurfaceStructure()
	{
		return false;
	}

	/**
	 * Check if the structure is in the correct dimension
	 * @param dim
	 * @return
	 */
	public boolean isValidDimension(int dim)
	{
		return true;
	}

	/**
	 * Check if the starting coords for the structure are valid, may check a larger area as well
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param rand
	 * @return
	 */
	public boolean areCoordsValidForGeneration(World world, BlockPos pos, Random rand)
	{
		return World.doesBlockHaveSolidTopSurface(world, pos.down()) && pos.getY() + this.height < world.provider.getActualHeight();
	}

	/**
	 * Specific checks for particular structures, this should be where to check things like if the structure is allowed in the config or if a valid variant
	 * id is set, etc.
	 * @param rand
	 * @param variantID
	 * @return
	 */
	public boolean canGenerate()
	{
		return TragicConfig.structureAllow[this.getStructureId()];
	}
	
	public int getStructureId() {
		return structureRegistry.getIDForObject(this);
	}
	
	/**
	 * Returns whether a structure can generate based on the configured rarity value out of the integer value to compare to
	 * @param compare
	 * @return
	 */
	public boolean getRarity(final int compare)
	{
		return TragicMC.rand.nextInt(compare) <= TragicConfig.structureRarity[this.getStructureId()];
	}

	public int getHeight()
	{
		return this.height;
	}

	public Schematic generate(World world, Random rand, BlockPos pos)
	{
		return generateStructureWithVariant(rand.nextInt(this.getVariantSize()), world, rand, pos.getX(), pos.getY(), pos.getZ());
	}

	/**
	 * Must be overridden by each structure to actually generate their schematics, this just does generic preliminary checks, also note that
	 * this is how structure seeds generate structures
	 * @param variant
	 * @param world
	 * @param rand
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Schematic generateStructureWithVariant(int variant, World world, Random rand, int x, int y, int z)
	{
		return !world.isRemote && this.canGenerate() ? this.getSchematicFor(world, rand, new BlockPos(x, y, z)).generateStructure(variant, world, rand, x, y, z).sortIntoList() : null;
	}

	public String getLocalizedName()
	{
		return StatCollector.translateToLocal("tile.tragicmc.structureSeed." + this.getUnlocalizedName() + ".name");
	}

	public String getUnlocalizedName() 
	{
		return structureRegistry.getNameForObject(this).getResourcePath();
	}
	
	public int getStructureColor()
	{
		return 0x000000;
	}
	
	public static Structure getStructureById(int i) {
		return structureRegistry.getObjectById(i);
	}
	
	public static int getRegistrySize() {
		return structureRegistry.getKeys().size();
	}
	
	public abstract Schematic getSchematicFor(World world, Random rand, BlockPos pos);
}
