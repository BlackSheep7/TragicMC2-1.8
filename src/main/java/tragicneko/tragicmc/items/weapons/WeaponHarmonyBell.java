package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponHarmonyBell extends TragicWeapon {

	public WeaponHarmonyBell(ToolMaterial material, Doomsday dday) {
		super(material, dday);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par2World.isRemote) return par1ItemStack;

		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (canUseAbility(doom, TragicConfig.doomAbilityCost[17]) && getStackCooldown(par1ItemStack) == 0 && TragicConfig.doomAbility[17])
		{
			List<EntityLivingBase> list = par2World.getEntitiesWithinAABB(EntityLivingBase.class, par3EntityPlayer.getEntityBoundingBox().expand(16.0, 16.0, 16.0));

			for (int i = 0; i < list.size(); i++)
			{
				EntityLivingBase e = list.get(i);
				if (e != par3EntityPlayer && par3EntityPlayer.canEntityBeSeen(e))
				{
					e.addPotionEffect(new PotionEffect(TragicPotion.Harmony.id, 60, 0));
				}
			}
			
			if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[17]);
			setStackCooldown(par1ItemStack, 5);
		}
		return par1ItemStack;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(stack, world, entity, par4, par5);

		if (entity instanceof EntityLivingBase)
		{
			if (par5 && entity.ticksExisted % 120 == 0 && ((EntityLivingBase) entity).getHealth() < ((EntityLivingBase) entity).getMaxHealth() && TragicConfig.doomAbility[18])
			{
				if (entity instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer) entity;
					PropertyDoom doom = PropertyDoom.get(player);

					if (doom != null && canUseAbility(doom, TragicConfig.doomAbilityCost[18]))
					{
						if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[18]);
						player.heal(1.0F);
					}
				}
			}
		}
	}
}
