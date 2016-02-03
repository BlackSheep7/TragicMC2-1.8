package tragicneko.tragicmc.blocks.itemblocks;

import net.minecraft.block.Block;

public class ItemBlockCelledLamp extends TragicItemBlock {

	private static String[] subNames = new String[] {"white", "orange", "magenta", "lightBlue", "yellow", "lime", "pink", "darkGray", "lightGray", "cyan", "purple", "blue", "brown",
		"green", "red", "black"};

	public ItemBlockCelledLamp(Block p_i45328_1_) {
		super(p_i45328_1_, subNames);
		this.setUnlocalizedName("tragicmc.celledLamp");
	}
}
