package tragicneko.tragicmc.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.TragicMC;

public class BlockGenericLog extends BlockLog {

	public BlockGenericLog(int level) {
		super();
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(5.0F);
		this.setHardness(1.0F);
		this.setStepSound(soundTypeWood);
		this.setHarvestLevel("axe", level);
		this.setLightLevel(0.5F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
	}
	
	protected BlockState createBlockState()
    {
		return new BlockState(this, LOG_AXIS);
    }
	
	protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, 0);
    }
	
	public int damageDropped(IBlockState state)
    {
		return 0;
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return meta == 0 || meta >= EnumAxis.values().length ? this.getDefaultState() : this.getDefaultState().withProperty(LOG_AXIS, EnumAxis.values()[meta]);
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		Comparable comp = state.getValue(LOG_AXIS);
		return comp == EnumAxis.Y ? 1 : (comp == EnumAxis.Z ? 2 : (comp == EnumAxis.NONE ? 3 : 0));
    }
}
