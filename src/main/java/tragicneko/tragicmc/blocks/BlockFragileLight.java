package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;

public class BlockFragileLight extends Block {

	public static final PropertyBool VISIBLE = PropertyBool.create("visible");

	public BlockFragileLight(boolean flag) {
		super(Material.glass);
		this.setHarvestLevel("pickaxe", 0);
		this.setUnlocalizedName("tragicmc.fragileLight");
		this.setHardness(0.5F);
		this.setResistance(0.5F);
		this.setStepSound(Block.soundTypeGlass);
		this.setLightOpacity(0);
		this.setLightLevel(!flag ? 0.2F : 0.6F);
		this.setTickRandomly(true);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(TragicBlocks.FragileLight);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!((Boolean) state.getValue(VISIBLE))) world.setBlockState(pos, state.withProperty(VISIBLE, true));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (!world.isRemote && entity instanceof EntityLivingBase && ((Boolean) state.getValue(VISIBLE)))
		{
			world.setBlockState(pos, this.getDefaultState().withProperty(VISIBLE, true));
			world.scheduleUpdate(pos, this, 10);
		}
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
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return 1;
	}
}
