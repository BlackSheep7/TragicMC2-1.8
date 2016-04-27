package tragicneko.tragicmc.items;

import java.lang.reflect.Method;
import java.util.List;

import net.minecraft.entity.Entity;
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
			Class oclass = Class.forName(par1ItemStack.getTagCompound().getString("extrapolatedEntity"));
			EntityMob entity = (EntityMob) oclass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par2EntityPlayer.worldObj});
			s2 = entity.getName();
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
			stack.getTagCompound().setString("extrapolatedEntity", entity.getClass().getCanonicalName());
			player.addChatMessage(new ChatComponentText("Sound extrapolated from " + entity.getName()));
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
				oclass = Class.forName(par1ItemStack.getTagCompound().getString("extrapolatedEntity"));
				EntityMob entity = (EntityMob) oclass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par2World});

				String s = type == 1 ? "getHurtSound" : (type == 2 ? "getDeathSound" : "getLivingSound");
				Method m = type == 0 ? EntityLiving.class.getDeclaredMethod(s) : EntityMob.class.getDeclaredMethod(s);
				m.setAccessible(true);
				sound = (String) m.invoke(entity, (Object[]) null);
				if (sound == null || sound.isEmpty()) return par1ItemStack;
				m = EntityLivingBase.class.getDeclaredMethod("getSoundPitch", (Class<?>[]) null);
				m.setAccessible(true);
				pitch = (Float) m.invoke(entity, (Object[]) null);
				m = EntityLivingBase.class.getDeclaredMethod("getSoundVolume", (Class<?>[]) null);
				m.setAccessible(true);
				volume = (Float) m.invoke(entity, (Object[]) null);
				if (par2World.isRemote) par3EntityPlayer.playSound(sound, volume, pitch);

				entity = null;

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
			++type;
			if (type > 2) type = 0;
			par1ItemStack.getTagCompound().setByte("soundType", type);
		}
		return par1ItemStack;
	}
}
