package tragicneko.tragicmc.worldgen.schematic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
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
import tragicneko.tragicmc.TragicMC;

public abstract class Schematic {

	public ArrayList<PosPreset[][]> matrix;
	public int width;
	public int height;
	public int structureHeight;
	
	public int placedBlocks = 0;
	public int[] placedPosition = new int[] {0, 0, 0}; //height, width, depth dimensions for the location of the last placed block position

	public Schematic(int structureHeight, int w, int d)
	{
		matrix = new ArrayList<PosPreset[][]>(structureHeight);
		for (int i = 0; i < structureHeight; i++) matrix.add(new PosPreset[w][d]);
		this.width = w;
		this.height = d;
		this.structureHeight = structureHeight;
		//this.fillMatrices();
	}
	
	public void updateBuildProgress() {
		int b = 0;
		for (int i = 0; i < this.structureHeight; i++)
		{
			for (int j = 0; j < this.height; j++)
			{
				for (int k = 0; k < this.width; k++)
				{
					if (this.matrix.get(this.structureHeight)[this.width][this.height].placed) b++;
				}
			}
		}
		
		this.placedBlocks = b;
	}
	
	public int getPlacedBlocks() {
		return this.placedBlocks;
	}
	
	public int getTotalBlocks() {
		return this.structureHeight * this.width * this.height;
	}
	
	public boolean hasFinished() {
		return this.placedBlocks >= this.getTotalBlocks();
	}
	
	public void setMatrixBlock(World world, BlockPos origin, PosPreset preset) {
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
		 * @param world
		 * @param origin of the current schematic
		 */
		public void handleTileEntity(World world, final BlockPos origin) {
			
		}
	}

	//public abstract void fillMatrices();

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
	}

	/**
	 * Main method to generate the particular structure, variants should be decided upon before this method is called, this may split off variants
	 * into their own methods at the schematic's discretion
	 */
	public abstract boolean generateStructure(int variant, World world, Random rand, int x, int y, int z);

	/**
	 * This tells the schematic to generate as the basic version
	 */
	public boolean generateStructure(World world, Random rand, int x, int y, int z)
	{
		return this.generateStructure(0, world, rand, x, y, z);
	}

	/**
	 * This tells the schematic to generate as a random variant, use the main generation method if you want to pick a particular variant,
	 * variantSize should be greater than 0, otherwise errors will ensue
	 */
	public boolean generateWithRandomVariant(int variantSize, World world, Random rand, int x, int y, int z)
	{
		return this.generateStructure(rand.nextInt(variantSize), world, rand, x, y, z);
	}

	/**
	 * Use this to apply chest contents to generated chests
	 */
	public boolean applyChestContents(World world, Random rand, int x, int y, int z, ChestGenHooks hook)
	{
		if (world.isRemote || y <= 0 || y >= 256) return false;

		TileEntityChest tileentity = (TileEntityChest)world.getTileEntity(new BlockPos(x, y, z));
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

	public boolean addSignContents(World world, int x, int y, int z, int line, String text) {
		TileEntitySign sign = (TileEntitySign) world.getTileEntity(new BlockPos(x, y, z));
		if (sign == null || line > 4)
		{
			TragicMC.logWarning("Sign text setup failed. The tile entity was null or an improper text line was chosen.");
			return false;
		}
		sign.signText[line] = new ChatComponentText(text);
		return true;
	}

	public boolean setSpawnerMob(World world, int x, int y, int z, String mobName)
	{
		TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(new BlockPos(x, y, z));
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
		this.setBlock(world, x, y, z, block, 0, 3);
	}
	
	public void setBlock(World world, int x, int y, int z, Block block, int meta, int flag)
	{
		world.setBlockState(new BlockPos(x, y, z), block.getStateFromMeta(meta), 3);
	}
}
