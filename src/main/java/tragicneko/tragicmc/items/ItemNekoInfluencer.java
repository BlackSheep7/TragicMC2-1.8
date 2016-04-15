package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityPart;
import tragicneko.tragicmc.entity.mob.EntityNeko;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.properties.PropertyDoom;

public class ItemNekoInfluencer extends Item {

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("R-Click (cost 5 Doom) will blare a siren, causing");
		par2List.add("all of the unreleased Nekos around to attack");
		par2List.add("L-click a released Neko to choose it's target");
		par2List.add("then you must L-click it's attack target");
		par2List.add("Automatically releases Nekos near you.");
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote) return false;
		if (getStackCooldown(stack) > 0) return true;
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("entityID")) stack.getTagCompound().setInteger("entityID", 0);

		EnumChatFormatting reset = EnumChatFormatting.RESET;
		EnumChatFormatting aqua = EnumChatFormatting.AQUA;
		EnumChatFormatting red = EnumChatFormatting.RED;

		if (entity instanceof EntityPart)
		{
			entity = (Entity) ((EntityPart) entity).main;
		}

		if (stack.getTagCompound().getInteger("entityID") == 0)
		{
			if (entity instanceof EntityNeko && ((EntityNeko) entity).isReleased())
			{
				stack.getTagCompound().setInteger("entityID", entity.getEntityId());
				player.addChatMessage(new ChatComponentText("Please choose a target for " + aqua + entity.getName() + reset + "."));
				setStackCooldown(stack, 10);
				return true;
			}
		}
		else
		{
			EntityCreature ent = (EntityCreature) entity.worldObj.getEntityByID(stack.getTagCompound().getInteger("entityID"));

			if (entity instanceof EntityCreature && ent != null && !ent.equals(entity))
			{
				ent.getNavigator().clearPathEntity();
				ent.setAttackTarget(null);
				ent.targetTasks.addTask(0, new EntityAINearestAttackableTarget(ent, entity.getClass(), 0, true, false, null));
				ent.setAttackTarget((EntityLivingBase) entity);
				player.addChatMessage(new ChatComponentText(aqua + "" +  ent.getName() + reset + " has been set to attack " + red + entity.getName() + reset + "!"));
				setStackCooldown(stack, 10);
				stack.getTagCompound().setInteger("entityID", 0);
				return true;
			}
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
		if (doom == null || !TragicConfig.getBoolean("allowDoom") || !TragicConfig.getBoolean("allowNonDoomsdayAbilities")) return par1ItemStack;

		if (TragicWeapon.canUseAbility(doom, 5))
		{
			double d0 = 128.0;
			List<EntityNeko> list = par2World.getEntitiesWithinAABB(EntityNeko.class, par3EntityPlayer.getEntityBoundingBox().expand(d0, d0, d0));
			for (EntityNeko e : list)
			{
				if (!e.isReleased()) e.setAttackTarget(par3EntityPlayer);
			}

			if (!par2World.isRemote && !par3EntityPlayer.capabilities.isCreativeMode) doom.increaseDoom(-5);
			if (!par2World.isRemote) par2World.playSoundAtEntity(par3EntityPlayer, "tragicmc:random.siren", 1.0F, 1.0F);
		}
		return par1ItemStack;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		if (!world.isRemote && getStackCooldown(stack) > 0) setStackCooldown(stack, getStackCooldown(stack) - 1);

		if (entity instanceof EntityPlayer)
		{
			double d0 = 16.0;
			List<EntityNeko> list = world.getEntitiesWithinAABB(EntityNeko.class, entity.getEntityBoundingBox().expand(d0, d0, d0));
			for (EntityNeko e : list)
			{
				if (!e.isReleased()) e.releaseNeko(((EntityPlayer) entity));
			}
		}
	}

	public static void setStackCooldown(ItemStack stack, int i)
	{
		if (!stack.hasTagCompound()) return;
		stack.getTagCompound().setInteger("cooldown", i);
	}

	public static int getStackCooldown(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("cooldown") ? stack.getTagCompound().getInteger("cooldown") : 0;
	}
}
