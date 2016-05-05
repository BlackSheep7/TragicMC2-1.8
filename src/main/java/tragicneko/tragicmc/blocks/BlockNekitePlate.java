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

public class BlockNekitePlate extends Block {
	
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockNekitePlate.EnumVariant.class);
	
	public BlockNekitePlate() {
		super(Material.iron);
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(45.0F);
		this.setHardness(17.5F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 3);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariant.COMPRESSED));
	}
	
	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos)
	{
		return this.getMetaFromState(world.getBlockState(pos)) == 0 ? 10 : super.getLightValue(world, pos);
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
		COMPRESSED("compressed"),
		NORMAL("normal"),
		SMOOTH("smooth"),
		CROSS("cross"),
		MARKED("marked"),
		GRATED("grated");
		
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
