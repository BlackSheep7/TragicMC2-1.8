package tragicneko.tragicmc.items.challenge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ChallengeItem extends Challenge {
	
	private final ItemStack challengeStack;
	private final boolean ignoresMeta;

	public ChallengeItem(int id, boolean saveProgress, int requirement, ItemStack stack, boolean ignoresMeta)
	{
		super(id, saveProgress, requirement);
		if (stack == null) throw new IllegalArgumentException("ItemStack cannot be null!");
		this.challengeStack = stack;
		this.ignoresMeta = ignoresMeta;
	}

	public ItemStack getChallengeStack() {
		return this.challengeStack;
	}

	public boolean getIgnoresMeta() {
		return this.ignoresMeta;
	}

	@Override
	public void onLivingUpdate(ItemStack stack, EntityPlayer player) {
		super.onLivingUpdate(stack, player);
		
		int amt = 0;
		ItemStack[] inv = player.inventory.mainInventory;
		for (ItemStack invStack : inv) {
			if (invStack != null && this.challengeStack != null)
			{
				boolean flag = !this.ignoresMeta && invStack.getItemDamage() == this.challengeStack.getItemDamage() || this.ignoresMeta;
				if (invStack.getItem() == this.challengeStack.getItem() && flag)
				{
					amt += invStack.stackSize;
				}
			}
		}

		stack.getTagCompound().setInteger(CHALLENGE_PROG, amt);
	}

}
