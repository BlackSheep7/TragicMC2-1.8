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
import tragicneko.tragicmc.properties.PropertyAmulets;

public class ItemAmuletRelease extends Item {

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.EPIC;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Unlocks an Amulet slot for use");
		par2List.add("Can also be used to restore an");
		par2List.add("Amulet's charge to max.");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (TragicConfig.getBoolean("requireAmuletSlotUnlock"))
		{
			PropertyAmulets amulets = PropertyAmulets.get(par3EntityPlayer);

			if (amulets.getSlotsOpen() < TragicConfig.getInt("amuletMaxSlots"))
			{
				if (!par2World.isRemote)
				{
					amulets.openAmuletSlot();
					par3EntityPlayer.addChatMessage(new ChatComponentText("Unlocked a new amulet slot!"));
				}
				else
				{
					par3EntityPlayer.playSound("tragicmc:random.amuletrelease", 1.0F, 1.0F);
				}
				
				if (TragicConfig.getBoolean("allowAchievements") && par3EntityPlayer instanceof EntityPlayerMP) par3EntityPlayer.triggerAchievement(TragicAchievements.amuletRelease);
			}
			else
			{
				return par1ItemStack;
			}

			if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.stackSize--;

		}
		return par1ItemStack;
	}
}
