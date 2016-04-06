package tragicneko.tragicmc.items.challenge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ChallengeEquipment extends Challenge {
	
	private final ItemStack[] challengeStacks;

	public ChallengeEquipment(int id, boolean saveProgress, int requirement, ItemStack[] stacks) {
		super(id, saveProgress, requirement);
		this.challengeStacks = stacks;
	}

	public ItemStack[] getChallengeEquipment() {
		return this.challengeStacks;
	}
	
	@Override
	public void onLivingUpdate(ItemStack stack, EntityPlayer player) {
		super.onLivingUpdate(stack, player);
		
		int amt = 0;
		ItemStack[] armorInv = player.inventory.armorInventory;

		for (ItemStack armorStack : armorInv) {
			if (armorStack != null)
			{
				for (ItemStack cStack : this.challengeStacks) {
					if (cStack != null && armorStack.getItem() == cStack.getItem()) amt++;
				}
			}
		}
		stack.getTagCompound().setInteger(CHALLENGE_PROG, amt);
	}
}
