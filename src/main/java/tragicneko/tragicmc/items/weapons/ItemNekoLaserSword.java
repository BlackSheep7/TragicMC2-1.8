package tragicneko.tragicmc.items.weapons;

import net.minecraft.item.Item.ToolMaterial;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemNekoLaserSword extends ItemSword {

	public ItemNekoLaserSword(ToolMaterial material) {
		super(material);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (!player.worldObj.isRemote && TragicConfig.getBoolean("allowMobSounds"))
		{
			player.worldObj.playSoundAtEntity(player, "tragicmc:boss.overlordcombat.shink", 1.9F, 1.9F);
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
}
