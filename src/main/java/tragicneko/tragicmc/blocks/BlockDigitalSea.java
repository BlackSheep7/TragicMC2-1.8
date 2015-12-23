package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockDigitalSea extends Block {

	public BlockDigitalSea()
	{
		super(Material.circuits);
		this.setUnlocalizedName("tragicmc.digitalSea");
		this.setCreativeTab(TragicMC.Creative);
		this.setLightLevel(0.0F);
		this.setLightOpacity(0);
		this.setResistance(0.0F);
		this.setHardness(0.0F);
		this.setTickRandomly(false);
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
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
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

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing facing)
	{
		if (world.getBlockState(pos).getBlock() == this) return false;
		return super.shouldSideBeRendered(world, pos, facing);
	}
}
