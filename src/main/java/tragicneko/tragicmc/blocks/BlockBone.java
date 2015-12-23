package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.TragicMC;

public class BlockBone extends Block {

	public static final PropertyBool VARIANT = PropertyBool.create("rotten");

	public BlockBone() {
		super(Material.gourd);
		this.setCreativeTab(TragicMC.Survival);
		this.setHardness(0.5F);
		this.setResistance(1.0F);
		this.setStepSound(soundTypeStone);
		this.setUnlocalizedName("tragicmc.boneBlock");
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, false));
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (byte i = 0; i < 2; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.dye;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 15;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(state, rand, fortune))
		{
			int j = rand.nextInt(fortune + 2) - 1;
			if (j < 1) j = 1;

			return (2 + rand.nextInt(3)) * j;
		}
		else
		{
			return super.quantityDropped(state, fortune, rand);
		}
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, VARIANT);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return meta == 0 ? this.getDefaultState() : this.getDefaultState().withProperty(VARIANT, true);
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		return (Boolean) state.getValue(VARIANT) ? 1 : 0;
    }
}
