package tragicneko.tragicmc.items.challenge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ChallengeLore extends Challenge {
	
	private final int challengeRarity;

	public ChallengeLore(int id, boolean saveProgress, int requirement, int loreRarity) {
		super(id, saveProgress, requirement);
		this.challengeRarity = loreRarity;
	}

	public int getChallengeRarity() {
		return this.challengeRarity;
	}
	
	@Override
	public void onLivingUpdate(ItemStack stack, EntityPlayer player) {
		super.onLivingUpdate(stack, player);
		
		ItemStack[] inv = player.inventory.mainInventory;
		int amt = 0;

		for (ItemStack loreStack : inv) {
			if (loreStack != null && loreStack.hasTagCompound() && loreStack.getTagCompound().hasKey("tragicLoreRarity"))
			{
				if (loreStack.getTagCompound().getInteger("tragicLoreRarity") <= this.challengeRarity) amt++;
			}
		}
		stack.getTagCompound().setInteger(CHALLENGE_PROG, amt);
	}
}
