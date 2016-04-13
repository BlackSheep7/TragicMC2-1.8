package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.util.LoreHelper;

public class TragicBow extends ItemBow {

	public final Doomsday doomsday;

	public TragicBow(int dmg, Doomsday dday)
	{
		this.setFull3D();
		this.setMaxDamage(dmg);
		this.doomsday = dday;
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
		TragicWeapon.updateAsWeapon(stack, world, entity, numb, flag);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2)
	{
		if (entity instanceof EntityPlayer && !TragicConfig.allowPvP) return false;
		return super.hitEntity(stack, entity, entity2);
	}
}
