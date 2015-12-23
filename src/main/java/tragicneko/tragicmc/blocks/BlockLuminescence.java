package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;

public class BlockLuminescence extends Block {

	public BlockLuminescence() {
		super(Material.circuits);
		this.setCreativeTab(TragicMC.Creative);
		this.setLightLevel(0.55F);
		this.setLightOpacity(0);
		this.setResistance(0.0F);
		this.setHardness(0.0F);
		this.setTickRandomly(true);
	}

	@Override
	public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, SpawnPlacementType type)
	{
		return false;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean canReplace(World world, BlockPos pos, EnumFacing facing, ItemStack stack)
	{
		return true;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!world.isRemote) world.setBlockToAir(pos);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote) world.scheduleUpdate(pos, this, 5);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitLiquid)
	{
		return false;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune) {}
}
