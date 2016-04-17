package tragicneko.tragicmc.items.armor;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
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
		if (TragicConfig.getBoolean("allowRandomWeaponLore") && LoreHelper.getRarityFromStack(stack) >= 0)
		{
			String lore = LoreHelper.getDescFromStack(stack);

			if (lore != null)
			{
				LoreHelper.splitDesc(par2List, lore, 32, LoreHelper.getFormatForRarity(LoreHelper.getRarityFromStack(stack)));
				par2List.add(""); //extra space
			}
		}

		if (TragicConfig.getBoolean("allowDoomsdays") && this.doomsday != null)
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
		TragicWeapon.updateAsWeapon(stack, world, entity, numb, flag);
	}
}
