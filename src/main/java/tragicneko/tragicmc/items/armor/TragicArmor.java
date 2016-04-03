package tragicneko.tragicmc.items.armor;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.util.LoreHelper;
import tragicneko.tragicmc.util.LoreHelper.EnchantEntry;
import tragicneko.tragicmc.util.LoreHelper.Lore;
import tragicneko.tragicmc.util.LoreHelper.LoreEntry;

public class TragicArmor extends ItemArmor {

	public final Doomsday doomsday;

	public TragicArmor(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, 0, armorType);
		this.doomsday = dday;
		this.setCreativeTab(TragicMC.Survival);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		int rarity = stack.hasTagCompound() && stack.getTagCompound().hasKey("tragicLoreRarity") ? stack.getTagCompound().getInteger("tragicLoreRarity") : 0;
		return EnumRarity.values()[rarity];
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (TragicConfig.allowRandomWeaponLore && LoreHelper.getRarityFromStack(stack) >= 0)
		{
			String lore = LoreHelper.getDescFromStack(stack);

			if (lore != null)
			{
				LoreHelper.splitDesc(par2List, lore, 32, LoreHelper.getFormatForRarity(LoreHelper.getRarityFromStack(stack)));
				par2List.add(""); //extra space
			}
		}

		if (TragicConfig.allowDoomsdays && this.doomsday != null)
		{
			par2List.add(EnumChatFormatting.WHITE + "Doomsday:");
			EnumChatFormatting format = doomsday.getDoomsdayType().getFormat();
			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());
			par2List.add(EnumChatFormatting.ITALIC + "Cost/Cooldown: " + EnumChatFormatting.GOLD + doomsday.getScaledDoomRequirement(par2EntityPlayer.worldObj) +
					EnumChatFormatting.RESET + " / " + EnumChatFormatting.DARK_AQUA +
					doomsday.getScaledCooldown(par2EntityPlayer.worldObj.getDifficulty()));
			par2List.add(""); //extra space in between
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (stack == null || stack.getItem() == null) return;
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		if (!TragicConfig.allowRandomWeaponLore) return;

		LoreEntry entry = LoreHelper.getLoreEntry(stack.getItem().getClass());
		if (entry == null) return;
		Lore lore = entry.getRandomLore();
		if (lore == null) return;

		if (!stack.getTagCompound().hasKey("tragicLoreRarity")) stack.getTagCompound().setByte("tragicLoreRarity", Byte.valueOf((byte) lore.getRarity()));
		if (!stack.getTagCompound().hasKey("tragicLoreDesc")) stack.getTagCompound().setString("tragicLoreDesc", lore.getDesc() == null ? "" : lore.getDesc());

		int rarity = stack.getTagCompound().getByte("tragicLoreRarity");
		lore = entry.getLoreOfRarity(rarity);

		if (!stack.isItemEnchanted() && lore != null)
		{
			EnchantEntry[] enchants = entry.getEnchantmentsForArmor(rarity, this.armorType);
			if (enchants == null) return;

			for (EnchantEntry e : enchants)
			{
				if (e != null && e.getEnchantment() != null) stack.addEnchantment(e.getEnchantment(), e.getEnchantLevel());
			}
		}
	}
}
