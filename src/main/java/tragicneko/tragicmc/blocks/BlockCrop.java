package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;

public class BlockCrop extends BlockReed implements IGrowable {

	public BlockCrop()
	{
		super();
		this.setCreativeTab(TragicMC.Creative);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (world.getBlockState(pos.down()).getBlock() == this || this.checkForDrop(world, pos, state))
		{
			if (world.isAirBlock(pos.up()))
            {
                int i;

                for (i = 1; world.getBlockState(pos.down(i)).getBlock() == this; ++i)
                {
                    ;
                }

                if (i < 5)
                {
                    int j = ((Integer)state.getValue(AGE)).intValue();

                    if (j == 15)
                    {
                        world.setBlockState(pos.up(), this.getDefaultState());
                    }
                    else
                    {
                        world.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(j + 1)), 4);
                    }
                }
            }
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		return super.canPlaceBlockAt(world, pos) || this == TragicBlocks.Deathglow && world.getBlockState(pos.down()).getBlock() == TragicBlocks.DarkCobblestone || world.getBlockState(pos.down()).getBlock() instanceof BlockGenericGrass;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, BlockPos pos)
	{
		return this == TragicBlocks.Deathglow ? TragicItems.Deathglow : TragicItems.Honeydrop;
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		return -1;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
	{
		return EnumPlantType.Plains;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		boolean flag = this == TragicBlocks.Deathglow;
		return ((Integer) state.getValue(AGE)).intValue() < 8 ? (flag ? TragicItems.DeathglowSeeds : TragicItems.HoneydropSeeds) : (flag ? TragicItems.Deathglow : TragicItems.Honeydrop);
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing facing, IPlantable plantable)
	{
		Block plant = plantable.getPlant(world, pos.up()).getBlock();
		return plant instanceof BlockCrop && plant == this || super.canSustainPlant(world, pos, facing, plantable);
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		return this.getMetaFromState(state) <= 15 && world.getBlockState(pos.up()).getBlock().isAir(world, pos.up());
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return this.getMetaFromState(state) <= 15 && world.getBlockState(pos.up()).getBlock().isAir(world, pos.up());
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		int m = this.getMetaFromState(state);
		int r = rand.nextInt(4) + 1;
		if (m < 15 && m + r <= 15)
		{
			world.setBlockState(pos, this.getStateFromMeta(m + r), 4);
		}
		else if (m == 15)
		{
			this.updateTick(world, pos, state, rand);
		}
	}
}
