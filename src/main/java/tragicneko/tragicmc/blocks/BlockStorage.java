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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockStorage extends Block {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockStorage.EnumVariant.class);

	public BlockStorage() {
		super(Material.iron);
		this.setUnlocalizedName("tragicmc.storageBlock");
		this.setCreativeTab(TragicMC.Survival);
		this.setHarvestLevel("pickaxe", 1);
		this.setHardness(6.0F);
		this.setResistance(10.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariant.RUBY));
	}
	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (byte i = 0; i < 5; i++)
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
		EnumVariant en;
		
		for (byte m = 0; m < 16; m++)
		{
			if (m >= EnumVariant.values().length) return 0;
			if (EnumVariant.values()[m] == comp) return m;
		}
		
		return 0;
    }
	
	public enum EnumVariant implements IStringSerializable {
		RUBY("ruby"),
		SAPPHIRE("sapphire"),
		TUNGSTEN("tungsten"),
		MERCURY("mercury"),
		QUICKSILVER("quicksilver");
		
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
