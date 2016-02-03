package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockTragicFlower.EnumVariant;

public class BlockTragicOres extends Block {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockTragicOres.EnumVariant.class);
	private int[] digLevels = new int[] {1, 2, 3, 3, 1, 2, 2, 2, 1, 0, 3};

	public BlockTragicOres() {
		super(Material.rock);
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(10.0F);
		this.setHardness(6.0F);
		this.setStepSound(soundTypeStone);
		this.setUnlocalizedName("tragicmc.tragicOres");
		this.setHarvestLevels();
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariant.MERCURY));
	}

	private void setHarvestLevels() {
		for (byte i = 0; i < 11; i++)
			this.setHarvestLevel("pickaxe", digLevels[i], this.getStateFromMeta(i));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortuneLevel)
	{
		switch(this.getMetaFromState(state))
		{
		case 2:
			return TragicItems.Ruby;
		case 3:
			return TragicItems.Sapphire;
		case 5:
			return Items.diamond;
		case 6:
			return Items.emerald;
		case 9:
			return Items.coal;
		case 4:
			return Items.dye;
		case 10:
			return null;
		default:
			return super.getItemDropped(state, rand, fortuneLevel);
		}
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
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

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (byte i = 0; i < 11; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune)
	{
		Random rand = TragicMC.rand;
		IBlockState state = world.getBlockState(pos);
		
		if (this.getItemDropped(state, rand, fortune) == null)
		{
			return 48 + rand.nextInt(48);
		}
		else if (this.getItemDropped(state, rand, fortune) == Item.getItemFromBlock(this))
		{
			return 0;
		}
		else if (this.getItemDropped(state, rand, fortune) == Items.diamond || this.getItemDropped(state, rand, fortune) == Items.emerald ||
				this.getItemDropped(state, rand, fortune) == TragicItems.Ruby || this.getItemDropped(state, rand, fortune) == TragicItems.Sapphire)
		{
			return MathHelper.getRandomIntegerInRange(rand, 3, 7);
		}
		else if (this.getItemDropped(state, rand, fortune) == Items.dye)
		{
			return MathHelper.getRandomIntegerInRange(rand, 4, 8);
		}
		else if (this.getItemDropped(state, rand, fortune) == Items.coal)
		{
			return MathHelper.getRandomIntegerInRange(rand, 0, 3);
		}

		return MathHelper.getRandomIntegerInRange(rand, 0, 3);
	}

	public enum EnumVariant implements IStringSerializable {
		MERCURY("mercury"),
		TUNGSTEN("tungsten"),
		RUBY("ruby"),
		SAPPHIRE("sapphire"),
		LAPIS("lapis"),
		DIAMOND("diamond"),
		EMERALD("emerald"),
		GOLD("gold"),
		IRON("iron"),
		COAL("coal"),
		XP("xp");
		
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
