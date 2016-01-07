package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockIceSpike extends Block {

	public static final PropertyBool FLIP = PropertyBool.create("flip");

	public BlockIceSpike(boolean flag) {
		super(Material.coral);
		this.setCreativeTab(TragicMC.Survival);
		this.setUnlocalizedName("tragicmc.iceSpike");
		this.setHardness(25.0F);
		this.setResistance(10.0F);
		this.setHarvestLevel("pickaxe", 1);
		this.setStepSound(soundTypeStone);
		this.setLightOpacity(0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FLIP, false));
	}

	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float distance)
	{
		IBlockState state = world.getBlockState(pos);
		if (!((Boolean) state.getValue(FLIP))) entity.attackEntityFrom(DamageSource.cactus, distance * 1.5F);
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int level)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing facing)
	{
		Block block = world.getBlockState(pos).getBlock();
		return block == this ? false : super.shouldSideBeRendered(world, pos, facing);
	}
	
	@Override 
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		super.onEntityCollidedWithBlock(world, pos, entity);
		if (world.rand.nextInt(4) == 0 && entity instanceof EntityLivingBase) entity.attackEntityFrom(new DamageSource("iceSpike").setDamageBypassesArmor(), 1F);
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, FLIP);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return this.getDefaultState().withProperty(FLIP, meta == 1);
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		boolean comp = ((Boolean) state.getValue(FLIP)).booleanValue();
		return comp ? 1 : 0;
    }
}
