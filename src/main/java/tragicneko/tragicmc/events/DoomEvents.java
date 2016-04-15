package tragicneko.tragicmc.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.items.ItemDoomsdayScroll;
import tragicneko.tragicmc.items.armor.TragicArmor;
import tragicneko.tragicmc.items.weapons.TragicBow;
import tragicneko.tragicmc.items.weapons.TragicTool;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.network.MessageDoom;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomEvents {

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer)
		{
			PropertyDoom doom = PropertyDoom.get((EntityPlayer) event.entity);

			if (doom == null)
			{
				PropertyDoom.register((EntityPlayer) event.entity);
			}
			else
			{
				doom.loadNBTData(new NBTTagCompound());
			}

			if (event.entity instanceof EntityPlayerMP && doom != null && TragicConfig.getBoolean("allowNetwork"))
			{
				TragicMC.proxy.net.sendTo(new MessageDoom((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
			}
		}
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer && !event.entity.worldObj.isRemote)
		{
			PropertyDoom doom = PropertyDoom.get((EntityPlayer) event.entityLiving);

			if (doom != null)
			{
				doom.onUpdate();
				if (event.entityLiving instanceof EntityPlayerMP && TragicConfig.getBoolean("allowNetwork")) TragicMC.proxy.net.sendTo(new MessageDoom((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
			}
		}
	}

	@SubscribeEvent
	public void onLivingDeathEvent(PlayerEvent.Clone event)
	{
		if (!event.entity.worldObj.isRemote && TragicConfig.getBoolean("allowDoom")) {
			if (PropertyDoom.get(event.original) != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				PropertyDoom.get(event.original).saveNBTData(tag);
				PropertyDoom.get(event.entityPlayer).loadNBTData(tag);
			}
		}
	}

	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void onAttack(LivingHurtEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer && TragicConfig.getBoolean("allowDoomPainRecharge"))
		{
			if (!event.entityLiving.worldObj.isRemote)
			{
				PropertyDoom properties = PropertyDoom.get((EntityPlayer)event.entityLiving);
				if (properties != null)
					properties.applyDoomPainRecharge(event.ammount);
			}
		}

		if (event.entityLiving instanceof EntityMob && TragicConfig.getBoolean("allowDoomPainRecharge"))
		{
			if (event.source.getEntity() instanceof EntityLivingBase && event.source.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) event.source.getEntity();

				PropertyDoom properties = PropertyDoom.get(player);
				if (properties != null)
					properties.applyDoomPainRecharge(event.ammount);
			}
		}
	}

	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void onDeath(Clone event)
	{
		if (event.entityLiving instanceof EntityPlayerMP && !event.wasDeath)
		{
			EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;
			if (TragicConfig.getBoolean("allowNetwork")) TragicMC.proxy.net.sendTo(new MessageDoom(player), player);
		}
	}

	@SubscribeEvent
	public void onDeath(LivingDeathEvent event)
	{
		if (event.entityLiving instanceof EntityMob && TragicConfig.getBoolean("allowDoomKillRecharge"))
		{
			if (event.source.getEntity() instanceof EntityLivingBase && event.source.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) event.source.getEntity();

				PropertyDoom properties = PropertyDoom.get(player);
				properties.increaseDoom(TragicConfig.getInt("doomRechargeAmount"));
			}
		}
	}
	
	@SubscribeEvent
	public void onAnvilUse(AnvilUpdateEvent event) {
		if (TragicConfig.getBoolean("allowDoomScrollImbue") && canApplyScroll(event.left) && event.right != null && event.right.getItem() instanceof ItemDoomsdayScroll)
		{
			event.output = ItemStack.copyItemStack(event.left);
			NBTTagCompound tag = event.output.hasTagCompound() ? event.output.getTagCompound() : new NBTTagCompound();
			Doomsday dday = Doomsday.getDoomsdayFromId(event.right.getItemDamage() + 1);
			if (dday != null) tag.setInteger("doomsdayID", dday.getDoomId());
			if (!event.output.hasTagCompound()) event.output.setTagCompound(tag);
			event.cost += 15 + (dday != null && dday.getDoomsdayType() == EnumDoomType.COMBINATION ? 15 : 0);
		}
	}
	
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event)
	{
		if (TragicConfig.getBoolean("allowDoomScrollImbue") && event.itemStack != null && event.itemStack.hasTagCompound() && event.itemStack.getTagCompound().hasKey("doomsdayID"))
		{
			Doomsday dday = Doomsday.getDoomsdayFromId(event.itemStack.getTagCompound().getInteger("doomsdayID"));
			if (dday == null) return;
			event.toolTip.add(EnumChatFormatting.WHITE + "Doomsday:");
			EnumChatFormatting format = dday.getDoomsdayType().getFormat();
			event.toolTip.add(format + dday.getLocalizedType() + ": " + dday.getLocalizedName());
			event.toolTip.add(EnumChatFormatting.ITALIC + "Cost/Cooldown: " + EnumChatFormatting.GOLD + dday.getScaledDoomRequirement(event.entityPlayer.worldObj) +
					EnumChatFormatting.RESET + " / " + EnumChatFormatting.DARK_AQUA +
					dday.getScaledCooldown(event.entityPlayer.worldObj.getDifficulty()));
			event.toolTip.add(""); //extra space in between
			if (event.itemStack.getItem() instanceof ItemArmor)
			{
				event.toolTip.add(EnumChatFormatting.ITALIC + "This item is considered an armor piece");
				event.toolTip.add(EnumChatFormatting.ITALIC + "you must wear a full set of the same");
				event.toolTip.add(EnumChatFormatting.ITALIC + "Doomsday to use it.");
				event.toolTip.add(""); //space at the end
			}
		}
	}
	
	public static boolean canApplyScroll(ItemStack stack) {
		if (stack == null || stack.getItem() instanceof ItemDoomsdayScroll || stack.getItem() instanceof TragicWeapon || stack.getItem() instanceof TragicTool || stack.getItem() instanceof TragicBow || stack.getItem() instanceof TragicArmor) return false;
		return true;
	}
}
