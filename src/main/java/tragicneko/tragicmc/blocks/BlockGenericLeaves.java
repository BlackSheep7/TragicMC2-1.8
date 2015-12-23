package tragicneko.tragicmc.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.actors.threadpool.Arrays;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockGenericLeaves extends BlockLeaves {

	public BlockGenericLeaves()
	{
		super();
		this.setCreativeTab(TragicMC.Survival);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this == TragicBlocks.PaintedLeaves ? 0 : (this == TragicBlocks.BleachedLeaves ? 1 : (this == TragicBlocks.AshenLeaves ? 2 : (this == TragicBlocks.HallowedLeaves ? 3 : 4)));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(TragicBlocks.TragicSapling);
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
	public boolean canBeReplacedByLeaves(IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return new ArrayList(Arrays.asList(new ItemStack[] {new ItemStack(this, 1, 0)}));
	}

	@Override
	public EnumType getWoodType(int meta) {
		return EnumType.OAK;
	}
}
