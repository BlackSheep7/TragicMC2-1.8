package tragicneko.tragicmc.items.weapons;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.util.LoreHelper;

public class TragicTool extends ItemTool {

	protected final Doomsday doomsday;

	protected TragicTool(float dmg, ToolMaterial mat, Set set, Doomsday dday) {
		super(dmg, mat, set);
		this.doomsday = dday;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		int rarity = stack.hasTagCompound() && stack.getTagCompound().hasKey("tragicLoreRarity") ? stack.getTagCompound().getInteger("tragicLoreRarity") : 0;
		return EnumRarity.values()[rarity];
	}

	public Doomsday getDoomsday()
	{
		return this.doomsday;
	}

	public EnumDoomType doomsdayType()
	{
		return this.doomsday != null ? this.doomsday.doomsdayType : EnumDoomType.INFLUENCE;
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
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
    {
        return true;
    }
}
