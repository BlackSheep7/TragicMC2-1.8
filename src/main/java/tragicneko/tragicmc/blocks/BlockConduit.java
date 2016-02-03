package tragicneko.tragicmc.blocks;

import tragicneko.tragicmc.TragicMC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;

public class BlockConduit extends BlockFence {

	public BlockConduit() {
		super(Material.iron);
		this.setCreativeTab(TragicMC.Survival);
		this.setHarvestLevel("pickaxe", 1);
	}

	@Override
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos).getBlock();
        return block == this || block.getMaterial().blocksMovement() && block.isFullCube();
    }
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
}
