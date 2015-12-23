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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;

public class BlockDarkCobble extends Block {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockDarkCobble.EnumVariant.class);

	public BlockDarkCobble() {
		super(Material.rock);
		this.setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(1.0F);
		this.setHardness(1.0F);
		this.setUnlocalizedName("tragicmc.darkCobblestone");
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariant.NORMAL));
	}

	@Override
	public boolean isFireSource(World world, BlockPos pos, EnumFacing facing)
	{
		return facing == EnumFacing.UP && world.getBlockState(pos).getValue(VARIANT) == EnumVariant.HOT;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (int i = 0; i < 4; i++)
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
		return comp == EnumVariant.HOT ? 1 : (comp == EnumVariant.TOXIC ? 2 : (comp == EnumVariant.TOXIC ? 3 : 0));
    }
	
	public enum EnumVariant implements IStringSerializable {
		NORMAL("normal"),
		HOT("hot"),
		TOXIC("toxic"),
		ASHEN("ashen");
		
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
