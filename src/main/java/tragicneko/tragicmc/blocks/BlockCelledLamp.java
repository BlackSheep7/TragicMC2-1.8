package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.TragicMC;

public class BlockCelledLamp extends BlockColored {

	public BlockCelledLamp() {
		super(Material.circuits);
		this.setCreativeTab(TragicMC.Survival);
		this.setHarvestLevel("pickaxe", 1);
		this.setResistance(30.0F);
		this.setHardness(7.6F);
		this.setLightLevel(0.8F);
		this.setStepSound(soundTypeMetal);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (byte i = 0; i < 16; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

}
