package tragicneko.tragicmc.items;

import java.lang.reflect.Method;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.EntityPart;

public class ItemSoundExtrapolator extends Item {

	public ItemSoundExtrapolator()
	{
		this.setMaxDamage(256);
		this.setMaxStackSize(1);
		this.setCreativeTab(TragicMC.Creative);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add(EnumChatFormatting.WHITE + "Extrapolate sounds from living creatures!");
		par2List.add("Hit a mob to extrapolate the sound from it.");
		par2List.add("R-Click to play a sound from it!");
		par2List.add("Shift+R-Click to cycle between sound types.");

		if (!par1ItemStack.hasTagCompound()) return;
		par2List.add("");
		String s2 = null;
		try
		{
			Entity entity = EntityList.createEntityByName(par1ItemStack.getTagCompound().getString("extrapolatedEntity"), par2EntityPlayer.worldObj);
			if (entity == null) return;
			s2 = EntityList.getEntityString(entity);
		}
		catch (Exception e)
		{
			TragicMC.logError("Error getting entity name for extrapolated entity.", e);
			s2 = null;
		}

		if (s2 != null) par2List.add("Current extrapolated entity: " + s2);
		byte type = par1ItemStack.getTagCompound().hasKey("soundType") ? par1ItemStack.getTagCompound().getByte("soundType") : 0;
		String s = type == 1 ? "Hurt" : (type == 2 ? "Death" : "Living");
		par2List.add("Current sound type: " + s + " sound");
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		if (entity != null && entity instanceof EntityPart)
		{
			entity = (Entity) ((EntityPart)entity).main;
		}

		if (entity != null && entity instanceof EntityMob && !entity.worldObj.isRemote)
		{
			String s = EntityList.getEntityString(entity);
			stack.getTagCompound().setString("extrapolatedEntity", s);
			player.addChatMessage(new ChatComponentText("Sound extrapolated from " + s));
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("extrapolatedEntity") && !par3EntityPlayer.isSneaking())
		{
			Class oclass;
			String sound = null;
			float pitch = 1F;
			float volume = 1F;
			byte type = par1ItemStack.getTagCompound().hasKey("soundType") ? par1ItemStack.getTagCompound().getByte("soundType") : 0;
			try
			{
				Entity entity = EntityList.createEntityByName(par1ItemStack.getTagCompound().getString("extrapolatedEntity"), par2World);
				boolean flag = type == 0;
				Method m;

				if (flag)
				{
					m = ReflectionHelper.findMethod(EntityLiving.class, (EntityLiving) entity, new String[] {"getLivingSound", "func_70639_aQ"}, (Class[]) null);
					m.setAccessible(true);
					sound = (String) m.invoke(entity, (Object[]) null);
				}
				else
				{
					String[] methodNames = type == 1 ? new String[] {"getHurtSound", "func_70621_aR"} : new String[] {"getDeathSound", "func_70673_aS"};
					m = ReflectionHelper.findMethod(EntityMob.class, (EntityMob) entity, methodNames, (Class[]) null);
					m.setAccessible(true);
					sound = (String) m.invoke(entity, (Object[]) null);
				}

				if (sound == null || sound.isEmpty()) return par1ItemStack;
				m = ReflectionHelper.findMethod(EntityLivingBase.class, (EntityLivingBase) entity, new String[] {"getSoundPitch", "func_70647_i"}, (Class[]) null); 
				m.setAccessible(true);
				pitch = (Float) m.invoke(entity, (Object[]) null);
				m = ReflectionHelper.findMethod(EntityLivingBase.class, (EntityLivingBase) entity, new String[] {"getSoundVolume", "func_70599_aP"}, (Class[]) null);
				m.setAccessible(true);
				volume = (Float) m.invoke(entity, (Object[]) null);
				if (!par2World.isRemote) par2World.playSoundAtEntity(par3EntityPlayer, sound, volume, pitch);
				if (!par3EntityPlayer.capabilities.isCreativeMode) par1ItemStack.damageItem(1, par3EntityPlayer);
			}
			catch (Exception e) {
				TragicMC.logError("Error caught from Sound Extrapolator", e);
				return par1ItemStack;
			}

		}
		else if (par1ItemStack.hasTagCompound() && par3EntityPlayer.isSneaking())
		{
			byte type = 0;
			if (par1ItemStack.getTagCompound().hasKey("soundType")) type = par1ItemStack.getTagCompound().getByte("soundType");
			if (++type > 2) type = 0;
			par1ItemStack.getTagCompound().setByte("soundType", type);
		}
		return par1ItemStack;
	}
}
