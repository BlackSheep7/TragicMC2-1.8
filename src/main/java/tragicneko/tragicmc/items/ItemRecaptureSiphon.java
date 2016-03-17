package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.properties.PropertyDoom;

public class ItemRecaptureSiphon extends Item {

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Right-click will release all Nekos near you!");
		par2List.add("Requires 5 Doom to use.");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
		if (doom == null || !TragicConfig.allowDoom || !TragicConfig.allowNonDoomsdayAbilities) return par1ItemStack;

		if (TragicWeapon.canUseAbility(doom, 5))
		{
			double d0 = 16.0;
			List<EntityTragicNeko> list = par2World.getEntitiesWithinAABB(EntityTragicNeko.class, par3EntityPlayer.getEntityBoundingBox().expand(d0, d0, d0));
			for (EntityTragicNeko e : list)
			{
				e.releaseNeko(par3EntityPlayer);
			}
			
			if (!par2World.isRemote && !par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-5);
		}
		return par1ItemStack;
	}
}
