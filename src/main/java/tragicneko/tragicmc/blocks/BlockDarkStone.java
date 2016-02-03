package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockDarkSandstone.EnumVariant;

public class BlockDarkStone extends Block {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockDarkStone.EnumType.class);
	public static final PropertyBool BEVELED = PropertyBool.create("beveled");

	public BlockDarkStone() {
		super(Material.rock);
		this.setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(3.0F);
		this.setHardness(1.5F);
		this.setUnlocalizedName("tragicmc.darkStone");
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumType.BLACK).withProperty(BEVELED, false));
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (byte i = 0; i < 14; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return this.getMetaFromState(state) == 0 ? TragicBlocks.DarkCobblestone.getItemDropped(TragicBlocks.DarkCobblestone.getDefaultState(), rand, fortune) : super.getItemDropped(state, rand, fortune);
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, TYPE, BEVELED);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		if (meta >= EnumType.values().length)
		{
			boolean flag = meta >= EnumType.values().length * 2;
			if (!flag)
			{
				return this.getDefaultState().withProperty(TYPE, EnumType.values()[meta / 2]).withProperty(BEVELED, true);
			}
		}
		return meta == 0 || meta >= EnumVariant.values().length ? this.getDefaultState() : this.getDefaultState().withProperty(TYPE, EnumType.values()[meta]).withProperty(BEVELED, false);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		Comparable comp = state.getValue(TYPE);

		for (byte m = 0; m < 16; m++)
		{
			boolean flag = m >= EnumType.values().length;
			if (EnumType.values()[m] == comp) return m;
			if (flag && EnumType.values()[m / 2] == comp) return m;
		}

		return 0;
	}

	public enum EnumType implements IStringSerializable {
		BLACK("black"),
		RED("red"),
		GREEN("green"),
		TEAL("teal"),
		BROWN("brown"),
		VIOLET("violet"),
		NAVY("navy");

		private final String name;

		private EnumType(String name)
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
