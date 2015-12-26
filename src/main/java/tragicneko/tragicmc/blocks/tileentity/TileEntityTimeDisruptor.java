package tragicneko.tragicmc.blocks.tileentity;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.storage.WorldInfo;
import tragicneko.tragicmc.TragicConfig;

public class TileEntityTimeDisruptor extends TileEntity {

	@Override
	public void updateContainingBlockInfo()
	{
		if (this.worldObj.isBlockIndirectlyGettingPowered(this.getPos()) > 0 && TragicConfig.allowItemTimeAltering)
		{
			WorldInfo info = this.worldObj.getWorldInfo();
			Random rand = this.worldObj.rand;
			info.setWorldTime(rand.nextInt(6000) + rand.nextInt(6000) + rand.nextInt(6000) + rand.nextInt(6000));
		}
	}
}
