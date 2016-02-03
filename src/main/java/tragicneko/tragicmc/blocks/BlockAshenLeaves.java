package tragicneko.tragicmc.blocks;

import java.util.Random;

import tragicneko.tragicmc.TragicMC;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockAshenLeaves extends BlockGenericLeaves {

	public static final PropertyBool ASHEN = PropertyBool.create("ashen");

	public BlockAshenLeaves() {
		super();
		this.setUnlocalizedName("tragicmc.ashenLeaves");
		this.setDefaultState(this.getDefaultState().withProperty(ASHEN, false));
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {CHECK_DECAY, DECAYABLE, ASHEN});
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(CHECK_DECAY, Boolean.valueOf(meta % 2 == 0)).withProperty(DECAYABLE, Boolean.valueOf(meta > 1)).withProperty(ASHEN, Boolean.valueOf(meta > 3));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		boolean check = (Boolean) state.getValue(CHECK_DECAY);
		boolean decay = (Boolean) state.getValue(DECAYABLE);
		boolean ashen = (Boolean) state.getValue(ASHEN);

		if (!ashen)
		{
			if (check && decay) return 2;
			else if (check && !decay) return 1;
			else if (!check && decay) return 3;
			else if (!check && !decay) return 0;
		}
		else
		{
			if (check && decay) return 6;
			else if (check && !decay) return 5;
			else if (!check && decay) return 7;
			else if (!check && !decay) return 4;
		}

		return 0;
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		super.updateTick(worldIn, pos, state, rand);

		Block block = worldIn.getBlockState(pos.up()).getBlock();
		boolean flag = block.getMaterial().blocksMovement();
		worldIn.setBlockState(pos, state.withProperty(ASHEN, !flag));
    }
}
