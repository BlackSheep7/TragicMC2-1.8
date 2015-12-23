package tragicneko.tragicmc.blocks;

import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockWickedVine extends BlockVine {

	public BlockWickedVine()
	{
		super();
		this.setCreativeTab(TragicMC.Survival);
		this.setLightLevel(0.4F);
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
	public int colorMultiplier(IBlockAccess p_149720_1_, BlockPos pos, int renderPass)
	{
		return -1;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		if (!world.isRemote && entity instanceof EntityLivingBase) entity.attackEntityFrom(DamageSource.cactus, 1.0F);
	}
}
