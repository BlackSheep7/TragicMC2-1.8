package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class ItemCooldownDefuse extends Item {

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.RARE;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Removes some global Doomsday cooldown");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote && !TragicConfig.getBoolean("allowCooldownDefuse")) return par1ItemStack;

		PropertyDoom property = PropertyDoom.get(par3EntityPlayer);
		if (property == null) return par1ItemStack;

		if (property.getCurrentCooldown() > 0)
		{
			if (par2World.isRemote)
			{
				par3EntityPlayer.playSound("tragicmc:random.cooldowndefuse", 1.0F, 1.0F);
			}
			else
			{
				if (TragicConfig.getInt("cooldownDefuseRefillAmount") >= property.getCurrentCooldown())
				{
					property.setCooldown(0);
					par3EntityPlayer.addChatMessage(new ChatComponentText("Cooldown was removed!"));
				}
				else
				{
					int cooldown = TragicConfig.getInt("cooldownDefuseRefillAmount");

					if (cooldown < property.getCurrentCooldown())
					{
						property.setCooldown(property.getCurrentCooldown() - cooldown);
						par3EntityPlayer.addChatMessage(new ChatComponentText("Cooldown was reduced!"));
					}
				}

				if (TragicConfig.getBoolean("allowAchievements") && par3EntityPlayer instanceof EntityPlayerMP) par3EntityPlayer.triggerAchievement(TragicAchievements.doomCooldown);
				if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;
			}
		}

		return par1ItemStack;
	}
}
