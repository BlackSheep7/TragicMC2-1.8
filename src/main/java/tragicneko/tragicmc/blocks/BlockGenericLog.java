package tragicneko.tragicmc.blocks;

import java.util.Iterator;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
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
	}
}
