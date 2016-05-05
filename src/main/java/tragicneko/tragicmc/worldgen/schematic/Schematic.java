package tragicneko.tragicmc.worldgen.schematic;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.BlockSign;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.structure.Structure;

public abstract class Schematic {

	public HashMap<BlockPos, PosPreset> map;
	public LinkedList<PosPreset> list;
	//public LinkedList<PosPreset> matrix;
	public int width; //current width
	public int depth; //current depth
	public int height; //structure height, should only change for certain structures like the pillar of persistence

	public int placedBlocks = 0;

	public final BlockPos origin; //origin of this particular schematic
	public Random random; //the random that the world is using

	public final Structure structure; //the structure this schematic is associated with

	public Schematic(BlockPos origin, Structure structure, int h, int w, int d) {
		map = new LinkedHashMap<BlockPos, PosPreset>();
		this.structure = structure;
		this.height = h;
		this.width = w;
		this.depth = d;
		this.origin = origin;
	}

	public void retrogradeBuildProgress() {
		int b = 0;
		int i = 0;

		boolean hasContinued = false;

		for (int j = 0; j < list.size(); j++)
		{
			PosPreset pos = list.get(j);
			if (i++ < this.placedBlocks && !hasContinued)
			{
				pos.placed = true;
				b++;
				continue;
			}
			hasContinued = true;
			if (pos.placed) b++;
		}
	}

	public int getPlacedBlocks() {
		return this.placedBlocks;
	}

	public int getTotalBlocks() {
		return this.list.size(); //this.height * this.width * this.depth;
	}

	public boolean hasFinished() {
		return this.placedBlocks >= this.getTotalBlocks();
	}

	public void setMappedBlock(World world, PosPreset preset) {
		world.setBlockState(origin.add(preset.pos), preset.state, 3);
		if (preset.tileEntity) preset.handleTileEntity(world, origin);
		preset.placed = true;
	}

	public static class PosComparator implements Comparator<PosPreset> {
		@Override
		public int compare(PosPreset o1, PosPreset o2) {
			if (o1 == o2) return 0;
			if (o1 == null) return -1;
			if (o2 == null) return 1;
			if (o1.equals(o2)) return 0;
			final int comp = o1.compareTo(o2);
			if (comp != 0) return comp;
			return o1.hashCode() - o2.hashCode();
		}
	}

	public static class PosPreset implements Comparable {
		public final BlockPos pos;
		public final boolean tileEntity;
		public final IBlockState state;
		public boolean placed = false;

		public PosPreset(BlockPos pos, IBlockState state, boolean isTileEntity) {
			this.pos = pos;
			this.state = state;
			this.tileEntity = isTileEntity;
		}

		public PosPreset(BlockPos pos, IBlockState state) {
			this(pos, state, false);
		}

		public int getX() { return this.pos.getX(); }
		public int getY() { return this.pos.getY(); }
		public int getZ() { return this.pos.getZ(); }

		/**
		 * Allows a preset to set it's own tile entity data
		 * @param world current world we are in
		 * @param origin origin of the current schematic
		 */
		public void handleTileEntity(World world, BlockPos origin) {

		}

		@Override
		public String toString() {
			return new String("preset:[" + this.pos + "," + this.state + ",tileentity=" + this.tileEntity + "]");
		}

		@Override
		public int compareTo(Object o) {
			return compare((PosPreset) o);
		}

		public int compare(PosPreset pre) { 
			if (this.tileEntity || this.state.getBlock() instanceof BlockLadder)
			{
				if (this.tileEntity && pre.state.getBlock() instanceof BlockLadder) //we are a tile entity comparing against a ladder
				{
					return 1;
				}
				else if (pre.tileEntity && this.state.getBlock() instanceof BlockLadder) //if we are a ladder comparing against a tile entity
				{
					return -1;
				}
				else if (this.tileEntity && pre.tileEntity || this.state.getBlock() instanceof BlockLadder && pre.state.getBlock() instanceof BlockLadder) //if we are both tile entities or ladders, then do normal coordinate comparison
				{
					if (this.getY() >= pre.getY()) //if this preset is at a greater y value than the input one
					{
						if (this.getX() >= pre.getX()) //if this preset has a greater X value than the input one
						{
							if (this.getZ() > pre.getZ()) //if this preset has a greater Z value than the input one
							{
								return 1;
							}
							else if (this.getZ() == pre.getZ()) //if they are the same then we are completely equal at this point
							{
								return 0;
							}
							else //we are less than it as far as we are concerned
							{
								return -1;
							}
						}
						else //if we are less than it in the x direction, than we are less than it as far as we are concerned
						{
							return -1;
						}
					}
					else //if this preset y value is less than the input one, then we are less than it as far as we are concerned
					{
						return -1;
					}
				}
				else //we are a ladder or tile entity comparing against a non-ladder and non-tile entity
				{
					return -1;
				}
			}
			else if (pre.tileEntity || pre.state.getBlock() instanceof BlockLadder) //if we aren't a tile entity or ladder and we're comparing against one, we take priority
			{
				return 1;
			}

			if (this.getY() >= pre.getY()) //if this preset is at a greater y value than the input one
			{
				if (this.getX() >= pre.getX()) //if this preset has a greater X value than the input one
				{
					if (this.getZ() > pre.getZ()) //if this preset has a greater Z value than the input one
					{
						return 1;
					}
					else if (this.getZ() == pre.getZ()) //if they are the same then we are completely equal at this point
					{
						return 0;
					}
					else //we are less than it as far as we are concerned
					{
						return -1;
					}
				}
				else //if we are less than it in the x direction, than we are less than it as far as we are concerned
				{
					return -1;
				}
			}
			else //if this preset y value is less than the input one, then we are less than it as far as we are concerned
			{
				return -1;
			}
		}
	}

	public static class SpawnerPreset extends PosPreset {
		public final String mobName;
		public final boolean limitSpawnRate;

		public SpawnerPreset(BlockPos pos, IBlockState state, String mobName, boolean limitSpawnRate) {
			super(pos, state, true);
			this.mobName = mobName;
			this.limitSpawnRate = limitSpawnRate;
		}

		@Override
		public void handleTileEntity(World world, BlockPos pos) {
			pos = pos.add(this.pos);
			Schematic.setSpawnerMob(world, pos, this.mobName, this.limitSpawnRate);
		}
	}

	public static class SignPreset extends PosPreset {
		public final String[] signContents;

		public SignPreset(BlockPos pos, IBlockState state, String signContent) {
			this(pos, state, new String[] {signContent});
		}

		public SignPreset(BlockPos pos, IBlockState state, String[] signContent) {
			super(pos, state, true);
			this.signContents = signContent;
		}

		@Override
		public void handleTileEntity(World world, BlockPos pos) {
			pos = pos.add(this.pos);
			for (int i = 0; i < this.signContents.length; i++)
				Schematic.addSignContents(world, pos, i, this.signContents[i]);
		}
	}

	public static class ChestPreset extends PosPreset {
		public final ChestGenHooks hook;

		public ChestPreset(BlockPos pos, IBlockState state, ChestGenHooks hook) {
			super(pos, state, true);
			this.hook = hook;
		}

		@Override
		public void handleTileEntity(World world, BlockPos pos) {
			pos = pos.add(this.pos);
			Schematic.applyChestContents(world, world.rand, pos, hook);
		}
	}

	/*
	public void invertMatrix(PosPreset[][] presets)
	{
		for (int k = 0; k < this.height; k++)
		{
			invertRow(presets, k);
		}
	}

	public void invertRow(PosPreset[][] presets, int row)
	{
		PosPreset[] temp = presets[row];
		PosPreset block;
		for (int i = 0; i < this.width; i++)
		{
			block = temp[i];
			presets[row][this.width - i] = block;
		}
	}

	public void rotateMatrix(PosPreset[][] presets, int rotations)
	{
		for (int k = 0; k < this.height; k++)
		{
			rotateRow(presets, k, rotations);
		}
	}

	public void rotateRow(PosPreset[][] presets, int row, int rotations)
	{
		PosPreset[] temp = presets[row];
		PosPreset block;
		for (int i = 0; i < this.width; i++)
		{
			block = temp[i];
			presets[row][i + rotations >= this.width ? (i + rotations - this.width) : (i + rotations)] = block;
		}
	}

	public void rotateEntireMatrix()
	{
		final int h = this.height;
		final int w = this.width;

		for (int m = 0; m < matrix.size(); m++)
		{
			PosPreset[][] matrice = matrix.get(m);
			PosPreset[][] newMatrix = new PosPreset[h][w];
			PosPreset preset;

			for (int i = 0; i < h; i++)
			{
				for (int k = 0; k < w; k++)
				{
					newMatrix[k][i] = matrice[i][k];
					preset = newMatrix[k][i];
					//if (preset.state.getBlock() instanceof net.minecraft.block.BlockStairs) preset.meta = preset.meta < 4 ? (preset.meta + 1) % 4 : (preset.meta + 1 % 4) + 4;
				}
			}

			this.invertMatrix(newMatrix);
			matrix.set(m, newMatrix);
		}
		//invert width and height parameters since these are used to keep track of the matrices assumed width and height
		this.height = w;
		this.width = h;
	} */

	/**
	 * Main method to generate the particular structure, variants should be decided upon before this method is called, this may split off variants
	 * into their own methods at the schematic's discretion
	 */
	public abstract Schematic generateStructure(int variant, World world, Random rand, int x, int y, int z);

	/**
	 * This tells the schematic to generate as the basic version
	 */
	public Schematic generateStructure(World world, Random rand, int x, int y, int z)
	{
		return this.generateStructure(0, world, rand, x, y, z);
	}

	/**
	 * This tells the schematic to generate as a random variant, use the main generation method if you want to pick a particular variant,
	 * variantSize should be greater than 0, otherwise errors will ensue
	 */
	public Schematic generateWithRandomVariant(int variantSize, World world, Random rand, int x, int y, int z)
	{
		return this.generateStructure(rand.nextInt(variantSize), world, rand, x, y, z);
	}

	private static boolean applyChestContents(World world, Random rand, BlockPos pos, ChestGenHooks hook)
	{
		if (world.isRemote || pos.getY() <= 0 || pos.getY() >= 256) return false;

		TileEntityChest tileentity = (TileEntityChest)world.getTileEntity(pos);
		if (tileentity != null)
		{
			WeightedRandomChestContent.generateChestContents(rand, hook.getItems(rand), tileentity, hook.getCount(rand));
			return true;
		}
		else
		{
			TragicMC.logWarning("Chest generation failed. The tile entity was null.");
			return false;
		}
	}

	private static boolean addSignContents(World world, BlockPos pos, int line, String text) {

		TileEntitySign sign = (TileEntitySign) world.getTileEntity(pos);
		if (sign == null || line > 4)
		{
			TragicMC.logWarning("Sign text setup failed. The tile entity was null or an improper text line was chosen.");
			return false;
		}
		sign.signText[line] = new ChatComponentText(text);
		return true;
	}

	private static boolean setSpawnerMob(World world, BlockPos pos, String mobName, boolean limit)
	{
		TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(pos);
		if (spawner == null || mobName == null)
		{
			TragicMC.logWarning("Spawner setup failed. The tile entity was null or mobName was null.");
			return false;
		}

		if (limit)
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("EntityId", mobName); //the id of the mob we are spawning
			tag.setShort("Delay", (short) 120); //initial delay to give the schematic time to finish
			tag.setShort("MinSpawnDelay", (short) 600); //minimum follow-up delay
			tag.setShort("MaxSpawnDelay", (short) 1600); //maximum follow-up delay
			tag.setShort("SpawnCount", (short) 2); //max spawn count per activation
			tag.setShort("MaxNearbyEntities", (short) 4); //entity limit for an activation, if there is this many or more around, then it doesn't activate
			tag.setShort("RequiredPlayerRange", (short) 8); //the required range for a player to trigger an activation
			spawner.getSpawnerBaseLogic().readFromNBT(tag);
		}
		else
		{
			spawner.getSpawnerBaseLogic().setEntityName(mobName);
		}
		return true;
	}

	public void setBlockToAir(World world, int x, int y, int z)
	{
		this.setBlock(world, x, y, z, Blocks.air);
	}

	public void setBlock(World world, int x, int y, int z, Block block)
	{
		this.setBlock(world, x, y, z, block, 0, 3);
	}

	public void setBlock(World world, int x, int y, int z, Block block, int meta, int flag)
	{
		this.setBlock(world, x, y, z, block, meta, 3, new Object[] {});
	}
	
	public void setBlock(World world, int x, int y, int z, Block block, int meta, int flag, Object... params) {
		this.setBlock(world, new BlockPos(x, y, z), block.getStateFromMeta(meta), params);
	}
	
	public void setBlock(World world, BlockPos pos, IBlockState state) {
		this.setBlock(world, pos, state, new Object[] {});
	}
	
	public void setBlock(World world, BlockPos pos, IBlockState state, Object... params) {
		if (TragicConfig.getBoolean("allowTickBuilder"))
		{
			this.setBlockToMap(pos, state, params);
		}
		else 
		{//if the builder isn't used, all tile entity stuff is handled through here so we'll just call as if it was being handled by the builder
			world.setBlockState(pos, state, 3);

			if (state.getBlock() instanceof BlockSign)
			{
				if (params[0] instanceof String) new SignPreset(pos.subtract(this.origin), state, (String) params[0]).handleTileEntity(world, this.origin);
				else new SignPreset(pos.subtract(this.origin), state, (String[]) params[0]).handleTileEntity(world, this.origin);
			}
			else if (state.getBlock() instanceof BlockMobSpawner)
			{
				new SpawnerPreset(pos.subtract(this.origin), state, (String) params[0], this.shouldLimitSpawnerRate()).handleTileEntity(world, this.origin);
			}
			else if (state.getBlock() instanceof BlockChest)
			{
				new ChestPreset(pos.subtract(this.origin), state, (ChestGenHooks) params[0]).handleTileEntity(world, this.origin);
			} 
		}
	}

	public void setBlockToMap(BlockPos pos, IBlockState state, Object[] params) 
	{
		pos = pos.subtract(this.origin);

		if (state.getBlock() instanceof BlockSign)
		{
			if (params[0] instanceof String) map.put(pos, new SignPreset(pos, state, (String) params[0]));
			else map.put(pos, new SignPreset(pos, state, (String[]) params[0]));
		}
		else if (state.getBlock() instanceof BlockMobSpawner)
		{
			map.put(pos, new SpawnerPreset(pos, state, (String) params[0], this.shouldLimitSpawnerRate()));
		}
		else if (state.getBlock() instanceof BlockChest)
		{
			map.put(pos, new ChestPreset(pos, state, (ChestGenHooks) params[0]));
		}
		else
		{ 
			map.put(pos, new PosPreset(pos, state));
		}
	}
	
	public boolean shouldLimitSpawnerRate() {
		return false;
	}
	
	public Schematic sortIntoList() {
		this.list = new LinkedList<PosPreset>(); 
		for (Entry<BlockPos, PosPreset> entry : this.map.entrySet())
		{
			this.list.add(entry.getValue());
		}/*
		try
		{
		Collections.sort(this.list, new PosComparator());
		}
		catch (Exception e)
		{
			TragicMC.logError("Error caught trying to sort a map into a list", e);
		} 
		for (int i = 0; i < this.list.size(); i++)
		{
			//TragicMC.logInfo("mapping is " + list.get(i));
		} */
		//TragicMC.logInfo("Map size is " + this.map.size());
		this.map.clear();
		return this;
	}
}
