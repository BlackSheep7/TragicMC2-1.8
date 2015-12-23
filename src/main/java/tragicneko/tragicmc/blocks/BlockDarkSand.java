package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.BlockFalling;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.TragicMC;

public class BlockDarkSand extends BlockFalling {

	public BlockDarkSand() {
		super();
		this.setCreativeTab(TragicMC.Survival);
		this.setStepSound(soundTypeSand);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item));
	}
}
