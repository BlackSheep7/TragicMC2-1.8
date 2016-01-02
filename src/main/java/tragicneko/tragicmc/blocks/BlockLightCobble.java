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
import tragicneko.tragicmc.blocks.BlockObsidianVariant.EnumVariant;

public class BlockLightCobble extends Block {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockLightCobble.EnumVariant.class);

	public BlockLightCobble() {
		super(Material.rock);
		this.setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(1.0F);
		this.setHardness(1.0F);
		this.setUnlocalizedName("tragicmc.lightCobblestone");
		this.lightValue = 5;
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
		for (byte i = 0; i < 3; i++)
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
		return comp == EnumVariant.FROZEN ? 1 : (comp == EnumVariant.GLOWING ? 2 : 0);
    }

	public enum EnumVariant implements IStringSerializable {
		NORMAL("normal"),
		FROZEN("frozen"),
		GLOWING("glowing");
		
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
