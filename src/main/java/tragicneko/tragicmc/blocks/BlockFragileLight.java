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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockFox.EnumVariant;

public class BlockFragileLight extends Block {

	public static final PropertyBool VISIBLE = PropertyBool.create("visible");

	public BlockFragileLight() {
		super(Material.circuits);
		this.setHarvestLevel("pickaxe", 0);
		this.setUnlocalizedName("tragicmc.fragileLight");
		this.setHardness(0.5F);
		this.setResistance(0.5F);
		this.setStepSound(Block.soundTypeGlass);
		this.setLightOpacity(0);
		this.setTickRandomly(true);
		this.setCreativeTab(TragicMC.Survival);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VISIBLE, true));
	}
	
	@Override
	public int getLightValue(IBlockAccess access, BlockPos pos)
	{
		IBlockState state = access.getBlockState(pos);
		
		return (Boolean) state.getValue(VISIBLE) ? 12 : 4;
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
		
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, VISIBLE);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return this.getDefaultState().withProperty(VISIBLE, meta == 0);
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		return ((Boolean) state.getValue(VISIBLE)).booleanValue() ? 0 : 1;
    }
}
