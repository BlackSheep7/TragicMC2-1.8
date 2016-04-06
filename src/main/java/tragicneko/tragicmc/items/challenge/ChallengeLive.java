package tragicneko.tragicmc.items.challenge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ChallengeLive extends Challenge {

	public ChallengeLive(int id, boolean saveProgress, int requirement) {
		super(id, saveProgress, requirement);
	}

	@Override
	public void onLivingUpdate(ItemStack stack, EntityPlayer player) {
		super.onLivingUpdate(stack, player);
		
		if (player.ticksExisted % 20 == 0)
		{
			int pow = stack.getTagCompound().getInteger(CHALLENGE_PROG);

			if (this.isMobRush() && !player.worldObj.isDaytime() || !this.isMobRush())
			{
				stack.getTagCompound().setInteger(CHALLENGE_PROG, ++pow);
			}
		}
	}
}
