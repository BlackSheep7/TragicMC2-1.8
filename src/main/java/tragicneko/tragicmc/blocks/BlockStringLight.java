package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;

public class BlockStringLight extends Block {

	public BlockStringLight() {
		super(Material.glass);
		this.setLightLevel(0.81F);
		this.setLightOpacity(0);
		this.setResistance(0.1F);
		this.setHardness(0.15F);
		this.setStepSound(soundTypeGlass);
		this.setCreativeTab(TragicMC.Survival);
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
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}
}
