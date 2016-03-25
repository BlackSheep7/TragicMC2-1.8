package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import tragicneko.tragicmc.TragicMC;

public class BlockCircuit extends Block {

	public static final PropertyEnum STATE = PropertyEnum.create("state", BlockCircuit.EnumState.class);

	public BlockCircuit() {
		super(Material.rock);
		this.setCreativeTab(TragicMC.Survival);
		this.setHarvestLevel("pickaxe", 1);
		this.setResistance(27.0F);
		this.setHardness(3.6F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(STATE, EnumState.LIVE));
	}

	@Override
	public int getLightValue(IBlockAccess access, BlockPos pos)
	{
		int meta = this.getMetaFromState(access.getBlockState(pos));
		switch(meta)
		{
		default:
			return 0;
		case 0:
			return 6;
		case 1:
			return 4;
		case 2:
			return 2;
		}
	}

	@Override
	public int getStrongPower(IBlockAccess access, BlockPos pos, IBlockState state, EnumFacing facing)
	{
		int meta = this.getMetaFromState(state);
		return meta == 0 ? 12 : (meta == 1 ? 8 : (meta == 2 ? 4 : 0));
	}


	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (int i = 0; i < 5; i++) 
			par3.add(new ItemStack(par1, 1, i));
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, STATE);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return meta == 0 || meta >= EnumState.values().length ? this.getDefaultState() : this.getDefaultState().withProperty(STATE, EnumState.values()[meta]);
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		Comparable comp = state.getValue(STATE);
		return comp == EnumState.DAMAGED ? 1 : (comp == EnumState.VERY_DAMAGED ? 2 : (comp == EnumState.AGED ? 3 : (comp == EnumState.DEAD ? 4 : 0)));
    }
	
	public enum EnumState implements IStringSerializable {
		LIVE("live"),
		DAMAGED("damaged"),
		VERY_DAMAGED("very_damaged"),
		AGED("aged"),
		DEAD("dead");
		
		private final String name;
		
		private EnumState(String name)
		{
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.name;
		}

		@Override
		public String getName() {
			return this.name;
		}
	}
}
