package tragicneko.tragicmc.items.weapons;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponBeastlyClaws extends TragicWeapon {

	private static final AttributeModifier mod = new AttributeModifier(UUID.fromString("b4d7a819-5dd1-4383-b1ac-66ef8ea9e40f"), "beastlyClawsComboModifier", 0, 0);

	public WeaponBeastlyClaws(ToolMaterial p_i45356_1_, Doomsday dday) {
		super(p_i45356_1_, dday);
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
	{
		player.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(mod);
		return super.onDroppedByPlayer(item, player);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		PropertyDoom doom = PropertyDoom.get(player);

		if (canUseAbility(doom, TragicConfig.doomAbilityCost[0]) && TragicConfig.doomAbility[0])
		{
			if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && stack.hasTagCompound())
			{
				if (!stack.getTagCompound().hasKey("comboCooldown")) stack.getTagCompound().setInteger("comboCooldown", 0);
				if (!stack.getTagCompound().hasKey("comboCount")) stack.getTagCompound().setInteger("comboCount", 0);

				int cooldown = stack.getTagCompound().getInteger("comboCooldown");
				int combo = stack.getTagCompound().getInteger("comboCount");
				double damage = combo * 2.0;

				if (cooldown > 0)
				{
					if (combo < 5)
					{
						stack.getTagCompound().setInteger("comboCount", combo + 1);
						stack.getTagCompound().setDouble("comboDamage", damage + 2.0);
					}

					if (cooldown > 5 && cooldown < 20) stack.getTagCompound().setInteger("comboCooldown", cooldown + 5);
					if (!player.capabilities.isCreativeMode) doom.increaseDoom(-TragicConfig.doomAbilityCost[0]);

				}
				else
				{
					stack.getTagCompound().setInteger("comboCount", 0);
					stack.getTagCompound().setInteger("comboCooldown", 20);
				}
			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(stack, world, entity, par4, par5);

		if (world.isRemote) return;

		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("comboCooldown")) stack.getTagCompound().setInteger("comboCooldown", 0);
		if (!stack.getTagCompound().hasKey("comboCount")) stack.getTagCompound().setInteger("comboCount", 0);

		int cooldown = stack.getTagCompound().getInteger("comboCooldown");
		int combo = stack.getTagCompound().getInteger("comboCount");

		if (cooldown > 0) stack.getTagCompound().setInteger("comboCooldown", cooldown - 1);
		if (cooldown == 0) stack.getTagCompound().setInteger("comboCount", 0);
		double damage = combo * 2.0D;

		AttributeModifier mod2 = new AttributeModifier(mod.getID(), "beastlyClawsComboModifier", damage, 0);

		if (entity instanceof EntityPlayer)
		{
			((EntityPlayer) entity).getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(mod2);
			if (par5) ((EntityPlayer) entity).getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(mod2);
		}
	}

}
