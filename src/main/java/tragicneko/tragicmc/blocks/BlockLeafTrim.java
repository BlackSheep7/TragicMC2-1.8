package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockLeafTrim extends BlockGenericTallGrass {

	public BlockLeafTrim()
	{
		super();
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
}
