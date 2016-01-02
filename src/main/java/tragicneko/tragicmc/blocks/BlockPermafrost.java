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
import net.minecraft.util.IStringSerializable;
import tragicneko.tragicmc.TragicMC;

public class BlockPermafrost extends Block {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockPermafrost.EnumVariant.class);

	public BlockPermafrost() {
		super(Material.ground);
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(5.0F);
		this.setHardness(1.5F);
		this.slipperiness = 0.72F;
		this.setStepSound(soundTypeGravel);
		this.setHarvestLevel("shovel", 1);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariant.NORMAL));
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (int i = 0; i < 3; i++)
			par3.add(new ItemStack(par1, 1, i));
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, VARIANT);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return meta == 0 || meta >= EnumVariant.values().length ? this.getDefaultState() : this.getDefaultState().withProperty(VARIANT, EnumVariant.values()[meta]);
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		Comparable comp = state.getValue(VARIANT);
		return comp == EnumVariant.CRACKED ? 1 : (comp == EnumVariant.MOSSY ? 2 : 0);
    }
	
	public enum EnumVariant implements IStringSerializable {
		NORMAL("normal"),
		CRACKED("cracked"),
		MOSSY("mossy");
		
		private final String name;
		
		private EnumVariant(String name)
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
