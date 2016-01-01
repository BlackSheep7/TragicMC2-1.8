package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayFlash extends Doomsday {

	public DoomsdayFlash(int id) {
		super(id, EnumDoomType.WORLDSHAPER);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double d0 = crucMoment ? 8.25D : 4.0D;
		List<BlockPos> list = WorldHelper.getBlocksInSphericalRange(player.worldObj, d0, player.posX, player.posY, player.posZ);

		for (int i = 0; i < list.size(); i++)
		{
			BlockPos coord = list.get(i);
			Block block = player.worldObj.getBlockState(coord).getBlock();
			if (block == Blocks.air || block == TragicBlocks.Luminescence) player.worldObj.setBlockState(coord, TragicBlocks.Luminescence.getDefaultState());
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}
}
