package tragicneko.tragicmc.blocks;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import tragicneko.tragicmc.TragicMC;

public class BlockDarkenedQuartz extends BlockRotatedPillar {

	public BlockDarkenedQuartz() {
		super(Material.rock);
		this.setCreativeTab(TragicMC.Survival);
		this.setHardness(0.8F);
		this.setUnlocalizedName("tragicmc.darkenedQuartz");
		this.setHarvestLevel("pickaxe", 0);
	}
}
