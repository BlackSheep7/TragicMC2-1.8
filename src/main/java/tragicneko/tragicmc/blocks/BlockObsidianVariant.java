package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.BlockObsidian;
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

public class BlockObsidianVariant extends BlockObsidian {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockObsidianVariant.EnumVariant.class);

	public BlockObsidianVariant() {
		super();
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(2500.0F);
		this.setHardness(50.0F);
		this.setUnlocalizedName("tragicmc.tragicObsidian");
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
		for (byte i = 0; i < 3; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public int getMobilityFlag()
	{
		return 2;
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
		return comp == EnumVariant.BLEEDING ? 1 : (comp == EnumVariant.DYING ? 2 : 0);
    }

	public enum EnumVariant implements IStringSerializable {
		CRYING("crying"),
		BLEEDING("bleeding"),
		DYING("dying");
		
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
