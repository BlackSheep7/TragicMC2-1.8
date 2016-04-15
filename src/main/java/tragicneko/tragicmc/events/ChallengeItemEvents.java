package tragicneko.tragicmc.events;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.items.challenge.Challenge;
import tragicneko.tragicmc.items.challenge.ItemChallenge;

public class ChallengeItemEvents {

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.entityLiving.worldObj.isRemote) return;

		EntityPlayer player;
		if (event.entityLiving instanceof EntityPlayer)
		{
			player = (EntityPlayer) event.entityLiving;
			ItemStack[] inv = player.inventory.mainInventory;
			ItemStack stack;
			Challenge challenge = null;

			for (ItemStack element : inv) {
				if (element != null && element.hasTagCompound() && element.getItem() instanceof ItemChallenge && element.getItemDamage() != 0 && element.getItemDamage() != 250)
				{
					stack = element;
					if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey(Challenge.CHALLENGE_ID)) continue;
					challenge = Challenge.getChallengeFromID(stack.getTagCompound().getInteger(Challenge.CHALLENGE_ID));
					if (challenge != null) challenge.onLivingDeath(stack, player);
				}
			}
		}
		else if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer)
		{
			player = (EntityPlayer) event.source.getEntity();
			ItemStack[] inv = player.inventory.mainInventory;
			ItemStack stack;
			Challenge challenge = null;

			for (ItemStack element : inv) {
				if (element != null && element.hasTagCompound() && element.getItem() instanceof ItemChallenge && element.getItemDamage() != 0 && element.getItemDamage() != 250)
				{
					stack = element;
					if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey(Challenge.CHALLENGE_ID)) continue;
					challenge = Challenge.getChallengeFromID(stack.getTagCompound().getInteger(Challenge.CHALLENGE_ID));
					if (challenge != null) challenge.onLivingKill(stack, event.entityLiving, player);
				}
			}

			if (TragicMC.rand.nextInt(1000) <= TragicConfig.getInt("challengeScrollDropChance") && event.entityLiving instanceof EntityMob)
			{
				event.entityLiving.entityDropItem(new ItemStack(TragicItems.ChallengeScroll, 1, 0), 0.4F);
			}
		}
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving.worldObj.isRemote || !(event.entityLiving instanceof EntityPlayer)) return;
		EntityPlayer player = (EntityPlayer) event.entityLiving;
		ItemStack[] inv = player.inventory.mainInventory;
		Challenge challenge = null;

		for (ItemStack stack : inv) {
			if (stack != null && stack.getItem() instanceof ItemChallenge && stack.getItemDamage() != 0 && stack.getItemDamage() != 250)
			{
				if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
				if (!stack.getTagCompound().hasKey(Challenge.CHALLENGE_ID)) stack.getTagCompound().setInteger(Challenge.CHALLENGE_ID, stack.getItemDamage());
				if (!stack.getTagCompound().hasKey(Challenge.CHALLENGE_PROG)) stack.getTagCompound().setInteger(Challenge.CHALLENGE_PROG, 0);
				challenge = Challenge.getChallengeFromID(stack.getTagCompound().getInteger(Challenge.CHALLENGE_ID));

				if (challenge != null)
				{
					if (challenge.isLocationBased() && !stack.getTagCompound().hasKey(Challenge.CHALLENGE_LOC)) stack.getTagCompound().setBoolean(Challenge.CHALLENGE_LOC, false);
					if (challenge.getTimed() && !stack.getTagCompound().hasKey(Challenge.CHALLENGE_TIME)) stack.getTagCompound().setInteger(Challenge.CHALLENGE_TIME, challenge.getTimeLimit());
					challenge.onLivingUpdate(stack, player);
				}
			}
		}
	}
}
