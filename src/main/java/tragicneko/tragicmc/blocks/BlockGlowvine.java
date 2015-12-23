package tragicneko.tragicmc.blocks;

import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockGlowvine extends BlockVine {

	public BlockGlowvine()
	{
		super();
		this.setCreativeTab(TragicMC.Survival);
		this.setLightLevel(1.0F);
		this.setLightOpacity(0);
		this.setTickRandomly(true);
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
}
