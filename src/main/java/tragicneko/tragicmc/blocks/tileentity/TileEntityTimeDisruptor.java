package tragicneko.tragicmc.blocks.tileentity;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.storage.WorldInfo;
import tragicneko.tragicmc.TragicConfig;

public class TileEntityTimeDisruptor extends TileEntity implements ITickable {

	@Override
	public void update()
	{
		if (this.blockType != null && this.worldObj.isBlockIndirectlyGettingPowered(this.getPos()) > 0 && TragicConfig.allowItemTimeAltering)
		{
			WorldInfo info = this.worldObj.getWorldInfo();
			Random rand = this.worldObj.rand;
			info.setWorldTime(rand.nextInt(6000) + rand.nextInt(6000) + rand.nextInt(6000) + rand.nextInt(6000));
		}
	}
}
