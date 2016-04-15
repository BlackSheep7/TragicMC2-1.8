package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;

public class ItemDyingObsidianOrb extends Item {

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("When Right-Clicked, will teleport you");
		par2List.add("to your last spawn point in the overworld");
		par2List.add("no matter what dimension you're in");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote)
		{
			int dim = par2World.provider.getDimensionId();

			if (dim != 0)
			{
				par3EntityPlayer.travelToDimension(0);
			}

			BlockPos cc = par3EntityPlayer.getBedLocation(0);

			if (cc != null)
			{
				par3EntityPlayer.setPositionAndUpdate(cc.getX(), cc.getY(), cc.getZ());
				par3EntityPlayer.addChatMessage(new ChatComponentText("Teleported to " + cc.getX() + ", " + cc.getY() + ", " + cc.getZ()));
			}
			else
			{
				BlockPos cc2 = par2World.getSpawnPoint();
				par3EntityPlayer.setPositionAndUpdate(cc2.getX(), par2World.getTopSolidOrLiquidBlock(cc2).getY(), cc2.getZ());
				par3EntityPlayer.addChatMessage(new ChatComponentText("Teleported to " + cc2.getX() + ", " + par2World.getTopSolidOrLiquidBlock(cc2).getY() + ", " + cc2.getZ()));
			}

			if (par3EntityPlayer instanceof EntityPlayerMP && TragicConfig.getBoolean("allowAchievements")) ((EntityPlayerMP) par3EntityPlayer).triggerAchievement(TragicAchievements.useOrb);
			par1ItemStack.stackSize--;
		}
		return par1ItemStack;
	}
}
