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
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockCircuit.EnumState;

public class BlockCorsin extends Block {
	
	private String[] oreNames = new String[] {"", "Faded", "Brick", "FadedBrick", "Circle", "Celled", "Scarred", "Crystal", "CrystalWrap"};
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockCorsin.EnumVariant.class);
	
	public BlockCorsin() {
		super(Material.rock);
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(15.0F);
		this.setHardness(3.5F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariant.NORMAL));
	}
	
	@Override
	public int getLightValue(IBlockAccess access, BlockPos pos)
	{
		int meta = this.getMetaFromState(access.getBlockState(pos));
		switch(meta)
		{
		default:
			return 0;
		case 7:
			return 15;
		case 8:
			return 8;
		}
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (int i = 0; i < 9; i++)
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
		FADED("faded"),
		BRICK("brick"),
		FADED_BRICK("faded_brick"),
		CIRCLE("circle"),
		CELLED("celled"),
		SCARRED("scarred"),
		CRYSTAL("crystal"),
		WRAPPED_CRYSTAL("wrapped_crystal");
		
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
