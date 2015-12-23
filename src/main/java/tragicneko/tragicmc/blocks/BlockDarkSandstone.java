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

public class BlockDarkSandstone extends Block {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockDarkSandstone.EnumVariant.class);

	public BlockDarkSandstone() {
		super(Material.rock);
		this.setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(3.0F);
		this.setHardness(1.5F);
		this.setUnlocalizedName("tragicmc.darkSandstone");
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
		for (byte i = 0; i < 6; i++)
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
		
		for (byte m = 0; m < 16; m++)
		{
			if (m >= EnumVariant.values().length) return 0;
			if (EnumVariant.values()[m] == comp) return m;
		}
		
		return 0;
    }

	public enum EnumVariant implements IStringSerializable {
		NORMAL("normal"),
		SMOOTH("smooth"),
		BRICKED("bricked"),
		CHISELED("chiseled"),
		GRIDDED("gridded"),
		CARVED("carved");
		
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
