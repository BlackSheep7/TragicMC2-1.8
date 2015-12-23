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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockErodedStone.EnumVariant;

public class BlockFox extends Block {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockFox.EnumVariant.class);

	public BlockFox() {
		super(Material.rock);
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(12.0F);
		this.setHardness(6.0F);
		this.setStepSound(soundTypeStone);
		this.setUnlocalizedName("tragicmc.fox");
		this.setHarvestLevel("pickaxe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariant.SMOOTH));
	}

	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos)
	{
		if (this.getMetaFromState(world.getBlockState(pos)) == 4) return 8;
		if (this.getMetaFromState(world.getBlockState(pos)) == 5) return 12;

		return super.getLightValue(world, pos);
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
	public boolean isFireSource(World world, BlockPos pos, EnumFacing facing)
	{
		return true;
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
		SMOOTH("smooth"),
		CHISELED("chiseled"),
		BEVELED("beveled"),
		SCULPTED("sculpted"),
		FOXTAIL("foxtail"),
		MOLTEN("molten");
		
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
