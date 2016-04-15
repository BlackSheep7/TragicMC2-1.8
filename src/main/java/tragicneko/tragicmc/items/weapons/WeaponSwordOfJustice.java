package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicMC;

public class WeaponSwordOfJustice extends ItemSword {

	public WeaponSwordOfJustice(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setMaxDamage(1);
		this.setCreativeTab(TragicMC.Creative);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2)
	{
		if (entity instanceof EntityPlayer && !TragicConfig.getBoolean("allowPvP")) return false;
		if (entity2 instanceof EntityPlayer && !entity.worldObj.isRemote && !((EntityPlayer) entity2).capabilities.isCreativeMode) stack.stackSize--;
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack)
	{
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (world.isRemote) return;

		if (!stack.isItemEnchanted())
		{
			if (TragicConfig.getBoolean("allowReach")) stack.addEnchantment(TragicEnchantments.Reach, 10);
			stack.addEnchantment(Enchantment.looting, 10);
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par2World.isRemote) return par1ItemStack;

		if (par3EntityPlayer.capabilities.isCreativeMode && par3EntityPlayer.canCommandSenderUseCommand(2, ""))
		{
			List<Entity> list = par2World.getEntitiesWithinAABBExcludingEntity(par3EntityPlayer, par3EntityPlayer.getEntityBoundingBox().expand(128.0, 128.0, 128.0));
			int count = 0;
			for (Entity e : list)
			{
				if (e instanceof EntityLivingBase && !e.isDead && ((EntityLivingBase) e).getHealth() > 0F)
				{
					if (e instanceof EntityPlayer && !TragicConfig.getBoolean("allowPvP")) continue;
					if (!e.attackEntityFrom(DamageSource.outOfWorld, Float.MAX_VALUE)) e.setDead();
					count++;
				}
			}
			if (count > 0) par3EntityPlayer.addChatMessage(new ChatComponentText(par3EntityPlayer.getName() + " killed all nearby entities (" + count + " entities)!"));
		}

		return par1ItemStack;
	}
}
