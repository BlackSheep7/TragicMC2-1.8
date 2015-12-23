package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockMoss extends BlockVine {

	public BlockMoss()
	{
		super();
		this.setCreativeTab(TragicMC.Survival);
		this.setLightOpacity(0);
		this.setTickRandomly(true);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(world, pos, state, rand);
		if (world.getBlockState(pos.down()).getBlock() == TragicBlocks.Permafrost && this.getMetaFromState(state) != 2) world.setBlockState(pos, TragicBlocks.Permafrost.getDefaultState()); //.withProperty(VARIANT, BlockPermafrost.MOSSY));
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
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, BlockPos pos, int renderPass)
	{
		return -1;
	}

	@Override
	public boolean isLadder(IBlockAccess world, BlockPos pos, EntityLivingBase entity)
	{
		return false;
	}
}
