package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockOverlordBarrier extends Block {

	public BlockOverlordBarrier() {
		super(Material.iron);
		this.setUnlocalizedName("tragicmc.overlordBarrier");
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
		this.setCreativeTab(TragicMC.Creative);
		this.setStepSound(soundTypeStone);
		this.setLightLevel(10.0F);
		this.setLightOpacity(0);
	}

	@Override
	public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, SpawnPlacementType type)
	{
		return false;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, BlockPos pos)
	{
		return false;
	}

	@Override
	public boolean canReplace(World world, BlockPos pos, EnumFacing facing, ItemStack stack)
	{
		return true;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		world.setBlockToAir(pos);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote) world.scheduleUpdate(pos, this, 45 + world.rand.nextInt(16));
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
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune) {}
}
