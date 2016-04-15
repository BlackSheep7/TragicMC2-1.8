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

public class ItemCryingObsidianOrb extends Item {

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("When Right-Clicked, will set your");
		par2List.add("spawn point, only affects the dimension");
		par2List.add("you're currently in");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote)
		{
			BlockPos pos = par2World.getSpawnPoint();
			int x = (int) par3EntityPlayer.posX;
			int z = (int) par3EntityPlayer.posZ;
			int y = (int) par3EntityPlayer.posY;

			BlockPos newCC = new BlockPos(x, y, z);

			int dim = par2World.provider.getDimensionId();

			par3EntityPlayer.setSpawnChunk(newCC, true, dim);
			par3EntityPlayer.addChatMessage(new ChatComponentText("Spawn set to " + x + ", " + y + ", " + z + " for dimension with id of " + par2World.provider.getDimensionId()));
			
			if (par3EntityPlayer instanceof EntityPlayerMP && TragicConfig.getBoolean("allowAchievements")) ((EntityPlayerMP) par3EntityPlayer).triggerAchievement(TragicAchievements.useOrb);
			par1ItemStack.stackSize--;
		}
		return par1ItemStack;
	}
}
