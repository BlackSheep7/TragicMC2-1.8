package tragicneko.tragicmc.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockGenericLeaves extends BlockLeaves {

	public BlockGenericLeaves()
	{
		super();
		this.setCreativeTab(TragicMC.Survival);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
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
	
	@Override
	protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return this.getDefaultState().withProperty(CHECK_DECAY, Boolean.valueOf(meta % 2 == 0)).withProperty(DECAYABLE, Boolean.valueOf(meta > 1));
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		boolean check = (Boolean) state.getValue(CHECK_DECAY);
		boolean decay = (Boolean) state.getValue(DECAYABLE);
		
		if (check && decay) return 2;
		else if (check && !decay) return 1;
		else if (!check && decay) return 3;
		else if (!check && !decay) return 0;
		
		return 0;
    }
	
	@Override
	protected ItemStack createStackedBlock(IBlockState state)
    {
		return new ItemStack(Item.getItemFromBlock(this), 1, 0);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if (worldIn.getBlockState(pos.offset(side.getOpposite())) != iblockstate)
		{
			return true;
		}

		if (block == this)
		{
			return false;
		}

		return super.shouldSideBeRendered(worldIn, pos, side);
	}
}
