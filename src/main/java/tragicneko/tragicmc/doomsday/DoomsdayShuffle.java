package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class DoomsdayShuffle extends Doomsday implements IExtendedDoomsday {

	public DoomsdayShuffle(int id) {
		super(id);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		List<BlockPos> list = WorldHelper.getBlocksInSphericalRange(player.worldObj, crucMoment ? 8.25 : 5.25, player.posX, player.posY, player.posZ);
		List<BlockPos> cands = new ArrayList<BlockPos>();

		for (BlockPos coords : list)
		{
			Block block = player.worldObj.getBlockState(coords).getBlock();
			float f = block.getBlockHardness(player.worldObj, coords);
			if (!block.isAir(player.worldObj, coords) && !block.getMaterial().isLiquid() && f > 0F && f < 48F) cands.add(coords);
		}

		for (int i = 0; i < 16; i++)
		{
			BlockPos coords = cands.get(rand.nextInt(cands.size()));
			IBlockState block = player.worldObj.getBlockState(coords);
			BlockPos coord2 = cands.get(rand.nextInt(cands.size()));
			IBlockState block2 = player.worldObj.getBlockState(coord2);

			player.worldObj.setBlockState(coords, block2);
			player.worldObj.setBlockState(coord2, block);
			Collections.shuffle(cands);
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}

	@Override
	public Doomsday getCombination()
	{
		return Doomsday.Blink;
	}

	@Override
	public int getWaitTime() {
		return 20;
	}

	@Override
	public int getMaxIterations() {
		return 20;
	}
}
