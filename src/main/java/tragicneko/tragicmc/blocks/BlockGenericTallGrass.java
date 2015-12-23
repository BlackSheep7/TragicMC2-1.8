package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockGenericTallGrass extends BlockTallGrass {

	private String texturePrefix;

	public BlockGenericTallGrass(String s)
	{
		super();
		this.texturePrefix = s;
		this.setCreativeTab(TragicMC.Survival);
		this.setLightLevel(0.5F);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	protected boolean canPlaceBlockOn(Block block)
	{
		return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == TragicBlocks.BrushedGrass || block == TragicBlocks.DeadDirt || block == TragicBlocks.AshenGrass || block == TragicBlocks.StarlitGrass;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
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
	public boolean canBeReplacedByLeaves(IBlockAccess world, BlockPos pos)
	{
		return true;
	}
}
