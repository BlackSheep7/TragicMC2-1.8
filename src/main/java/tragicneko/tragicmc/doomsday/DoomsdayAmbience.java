package tragicneko.tragicmc.doomsday;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.doomsday.Doomsday.IExtendedDoomsday;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayAmbience extends Doomsday implements IExtendedDoomsday {

	public DoomsdayAmbience(int id) {
		super(id);
	}

	@Override
	public void useDoomsday(DoomsdayEffect effect, PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		int x = (int) (player.posX + rand.nextInt(2) - rand.nextInt(2));
		int y = (int) (player.posY + rand.nextInt(2) - rand.nextInt(2));
		int z = (int) (player.posZ + rand.nextInt(2) - rand.nextInt(2));
		if (EntityOverlordCore.replaceableBlocks.contains(player.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock()))
		{
			player.worldObj.setBlockState(new BlockPos(x, y, z), TragicBlocks.Luminescence.getDefaultState());
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.blindness.id, 120, 0));
	}

	@Override
	public Doomsday getCombination() {
		return Doomsday.Decay;
	}

	@Override
	public int getWaitTime() {
		return 1;
	}

	@Override
	public int getMaxIterations() {
		return 600;
	}
}
