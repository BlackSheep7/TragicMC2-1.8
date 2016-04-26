package tragicneko.tragicmc.worldgen.schematic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.BlockSign;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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
	//public ArrayList<PosPreset[][]> matrix;
	public int width; //current width
	public int depth; //current depth
	public int height; //structure height, should only change for certain structures like the pillar of persistence

	public int placedBlocks = 0;

	public final BlockPos origin; //origin of this particular schematic
	public Random random; //the random that the world is using

	public final Structure structure; //the structure this schematic is associated with

	public Schematic(BlockPos origin, Structure structure, int h, int w, int d) {
		map = new HashMap<BlockPos, PosPreset>();
		this.structure = structure;
		this.height = h;
		this.width = w;
		this.depth = d;
		this.origin = origin;
	}

	public void retrogradeBuildProgress() {
		int b = 0;
		int i = 0;

		Set<BlockPos> set = map.keySet();
		Iterator<BlockPos> ite = set.iterator();
		boolean hasContinued = false;

		while (ite.hasNext())
		{
			BlockPos pos = ite.next();
			if (i++ < this.placedBlocks && !hasContinued)
			{
				if (map.get(pos) != null) map.get(pos).placed = true;
				b++;
				continue;
			}
			hasContinued = true;
			if (map.get(pos) == null || map.get(pos).placed) b++;
		}
	}

	public int getPlacedBlocks() {
		return this.placedBlocks;
	}

	public int getTotalBlocks() {
		return this.map.size(); //this.height * this.width * this.depth;
	}

	public boolean hasFinished() {
		return this.placedBlocks >= this.getTotalBlocks();
	}

	public void setMappedBlock(World world, PosPreset preset) {
		world.setBlockState(origin.add(preset.pos), preset.state);
		if (preset.tileEntity) preset.handleTileEntity(world, origin);
		preset.placed = true;
	}

	public static class PosPreset {
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
	}

	public static class SpawnerPreset extends PosPreset {
		public final String mobName;

		public SpawnerPreset(BlockPos pos, IBlockState state, String mobName) {
			super(pos, state, true);
			this.mobName = mobName;
		}

		@Override
		public void handleTileEntity(World world, BlockPos pos) {
			pos = pos.add(this.pos);
			Schematic.setSpawnerMob(world, pos, this.mobName);
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

	private static boolean setSpawnerMob(World world, BlockPos pos, String mobName)
	{
		TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(pos);
		if (spawner == null || mobName == null)
		{
			TragicMC.logWarning("Spawner setup failed. The tile entity was null or mobName was null.");
			return false;
		}
		spawner.getSpawnerBaseLogic().setEntityName(mobName);
		return true;
	}

	public void setBlockToAir(World world, int x, int y, int z)
	{
		this.setBlock(world, x, y, z, Blocks.air);
	}

	public void setBlock(World world, int x, int y, int z, Block block)
	{
		this.setBlock(world, x, y, z, block, 0, 2);
	}

	public void setBlock(World world, int x, int y, int z, Block block, int meta, int flag)
	{
		this.setBlock(world, x, y, z, block, meta, 2, new Object[] {});
	}

	public void setBlock(World world, int x, int y, int z, Block block, int meta, int flag, Object... params)
	{
		if (TragicConfig.getBoolean("allowTickBuilder"))
		{
			this.setBlockToMap(new BlockPos(x, y, z), block.getStateFromMeta(meta), params);
		}
		else 
		{//if the builder isn't used, all tile entity stuff is handled through here so we'll just call as if it was being handled by the builder
			IBlockState state = block.getStateFromMeta(meta);
			BlockPos pos = new BlockPos(x, y, z);
			world.setBlockState(pos, state, 2);
			
			if (state.getBlock() instanceof BlockSign)
			{
				if (params[0] instanceof String) new SignPreset(pos.subtract(this.origin), state, (String) params[0]).handleTileEntity(world, this.origin);
				else new SignPreset(pos.subtract(this.origin), state, (String[]) params[0]).handleTileEntity(world, this.origin);
			}
			else if (state.getBlock() instanceof BlockMobSpawner)
			{
				new SpawnerPreset(pos.subtract(this.origin), state, (String) params[0]).handleTileEntity(world, this.origin);
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
			map.put(pos, new SpawnerPreset(pos, state, (String) params[0]));
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
}
