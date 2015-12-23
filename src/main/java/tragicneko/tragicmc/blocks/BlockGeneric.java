package tragicneko.tragicmc.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tragicneko.tragicmc.TragicMC;

public class BlockGeneric extends Block {

	public BlockGeneric(Material p_i45394_1_, String s, int level) {
		super(p_i45394_1_);
		this.setCreativeTab(TragicMC.Survival);
		this.setHarvestLevel(s, level);
	}
}
