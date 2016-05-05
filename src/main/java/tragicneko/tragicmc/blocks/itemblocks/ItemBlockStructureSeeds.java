package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class ItemBlockStructureSeeds extends TragicItemBlock {

	public ItemBlockStructureSeeds(Block p_i45326_1_) {
		super(p_i45326_1_, null);
		this.setUnlocalizedName("tragicmc.structureSeed");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		int damage = par1ItemStack.getItemDamage();
		return Structure.getStructureById(damage) != null ? Structure.getStructureById(damage).getStructureColor() : 0;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int damage = itemstack.getItemDamage();

		if (damage >= Structure.getRegistrySize())
		{
			damage = Structure.getRegistrySize() - 1;
		}
		
		if (Structure.getStructureById(damage) == null)
		{
			damage = 0;
		}
		return getUnlocalizedName() + "." + Structure.getStructureById(damage).getUnlocalizedName();
	}
}
