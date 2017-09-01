package tragicneko.tragicmc.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockCocoa;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;

public class BlockFruit extends BlockCocoa {
	
	public BlockFruit()
	{
		super();
		this.setCreativeTab(TragicMC.Creative);
		this.setStepSound(soundTypeGrass);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
		return !(worldIn.getBlockState(pos).getBlock() instanceof BlockFruit) ? new AxisAlignedBB((double)pos.getX() + this.minX, (double)pos.getY() + this.minY, (double)pos.getZ() + this.minZ, (double)pos.getX() + this.maxX, (double)pos.getY() + this.maxY, (double)pos.getZ() + this.maxZ) : super.getCollisionBoundingBox(worldIn, pos, state);
    }
	
	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        pos = pos.offset((EnumFacing)state.getValue(FACING));
        IBlockState iblockstate1 = worldIn.getBlockState(pos);
        return iblockstate1.getBlock() == TragicBlocks.PaintedWood;
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return ((Integer) state.getValue(AGE)).intValue() > 3 ? TragicItems.SkyFruit : TragicItems.SkyFruitSeeds;
	}
	
	@Override
    public java.util.List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        java.util.List<ItemStack> dropped = super.getDrops(world, pos, state, fortune);
        dropped.clear();
        int j = ((Integer)state.getValue(AGE)).intValue();
        byte b0 = 1;
        if (j >= 2) b0 = 3;

        for (int k = 0; k < b0; ++k)
            dropped.add(new ItemStack(this.getItemDropped(state, this.RANDOM, fortune), 1, 0));
        
        return dropped;
    }
	
	@SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
		return ((Integer) worldIn.getBlockState(pos).getValue(AGE)).intValue() > 3 ? TragicItems.SkyFruit : TragicItems.SkyFruitSeeds;
    }
}
