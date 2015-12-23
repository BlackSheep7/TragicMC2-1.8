package tragicneko.tragicmc.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;

public class BlockMoltenRock extends Block {

	public BlockMoltenRock() {
		super(Material.rock);
		this.setCreativeTab(TragicMC.Survival);
		this.setUnlocalizedName("tragicmc.moltenRock");
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(0.8F);
		this.setResistance(5.0F);
	}

	@Override
	public boolean isFireSource(World world, BlockPos pos, EnumFacing facing)
	{
		return true;
	}
}
