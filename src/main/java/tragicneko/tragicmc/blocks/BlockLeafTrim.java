package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockLeafTrim extends BlockTallGrass {

	private String texturePrefix;

	public BlockLeafTrim(String s)
	{
		super();
		this.texturePrefix = s;
		this.setCreativeTab(TragicMC.Survival);
		this.setLightLevel(0.5F);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	public boolean isLeaves(IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	protected boolean canPlaceBlockOn(Block block)
	{
		return block instanceof BlockLeaves || block instanceof BlockLeavesBase || block instanceof BlockLeafTrim;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{
		list.add(new ItemStack(item, 1, 1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		return -1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(IBlockState state)
	{
		return -1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, BlockPos pos, int renderPass)
	{
		return -1;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
	{
		return false;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
	{
		return false;
	}
}
