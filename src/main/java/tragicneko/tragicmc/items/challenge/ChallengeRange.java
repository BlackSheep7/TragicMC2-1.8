package tragicneko.tragicmc.items.challenge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ChallengeRange extends Challenge {

	public ChallengeRange(int id, boolean saveProgress, int requirement) {
		super(id, saveProgress, requirement);
	}
	
	@Override
	public void onLivingUpdate(ItemStack stack, EntityPlayer player) {
		super.onLivingUpdate(stack, player);
		
		double x = Math.abs(player.posX);
		double z = Math.abs(player.posZ);
		int i = MathHelper.floor_double(MathHelper.sqrt_double(x * x + z * z));
		stack.getTagCompound().setInteger(CHALLENGE_PROG, i);
	}
}
