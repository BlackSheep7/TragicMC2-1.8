package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.IMultiPart;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.util.WorldHelper;

public class ItemNekoRayGun extends Item {

	public ItemNekoRayGun() {
		this.setMaxStackSize(1);
		this.setMaxDamage(115);
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
	{
		if (TragicWeapon.getStackCooldown(itemstack) > 0) {
			TragicWeapon.setStackCooldown(itemstack, TragicWeapon.getStackCooldown(itemstack) - 1);
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		MovingObjectPosition mop = WorldHelper.getMOPFromEntity(par3EntityPlayer, 25.0);

		if (mop == null || TragicWeapon.getStackCooldown(par1ItemStack) > 0)
		{
			return par1ItemStack;
		}
		else
		{
			if (!par2World.isRemote)
			{
				par2World.playSoundAtEntity(par3EntityPlayer, "tragicmc:boss.aegar.laser", 0.4F, 1.8F);
			}
			else
			{
				double d0 = mop.hitVec.xCoord - par3EntityPlayer.posX;
				double d1 = mop.hitVec.yCoord - par3EntityPlayer.posY - par3EntityPlayer.getEyeHeight();
				double d2 = mop.hitVec.zCoord - par3EntityPlayer.posZ;

				for (byte l = 0; l < 8; l++)
				{
					double d3 = 0.123D * l + (itemRand.nextDouble() * 0.125D);
					par2World.spawnParticle(EnumParticleTypes.FLAME, par3EntityPlayer.posX + d0 * d3, par3EntityPlayer.posY + d1 * d3 + par3EntityPlayer.getEyeHeight(), par3EntityPlayer.posZ + d2 * d3, 0.0, 0.0, 0.0);
				}
			}

			if (!par2World.isRemote)
			{
				float f1 = par3EntityPlayer.prevRotationPitch + (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch);
				float f2 = par3EntityPlayer.prevRotationYaw + (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw);
				Vec3 vec3 = new Vec3(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ);
				float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
				float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
				float f5 = -MathHelper.cos(-f1 * 0.017453292F);
				float f6 = MathHelper.sin(-f1 * 0.017453292F);
				float f7 = f4 * f5;
				float f8 = f3 * f5;
				double box = 0.435D;

				AxisAlignedBB bb;

				meow: for (double d = 0.0D; d <= 30.0; d += 0.25D)
				{
					Vec3 vec31 = vec3.addVector(f7 * d, f6 * d, f8 * d);
					
					if (d > 0) 
					{
						mop = WorldHelper.getMOPFromEntity(par3EntityPlayer, d);
						if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) break meow;
					}
					
					bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D).expand(box, box, box).offset(vec31.xCoord, vec31.yCoord + par3EntityPlayer.getEyeHeight(), vec31.zCoord);
					List<Entity> list = par2World.getEntitiesWithinAABBExcludingEntity(par3EntityPlayer, bb);		

					if (list.isEmpty()) continue;

					for (Entity e : list)
					{					
						if (e instanceof IMultiPart)
						{
							((IMultiPart) e).getDefaultPart().attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(par3EntityPlayer), 2F);
							if (TragicConfig.getBoolean("allowMobSounds")) par2World.playSoundAtEntity(e, "tragicmc:boss.aegar.laser", 0.4F, 1.8F);
						}

						if (!(e instanceof EntityItem) && !(e instanceof EntityXPOrb) && !(e instanceof EntityArrow) && e != par3EntityPlayer)
						{
							e.attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(par3EntityPlayer), 3F);
							if (TragicConfig.getBoolean("allowMobSounds")) par2World.playSoundAtEntity(e, "tragicmc:boss.aegar.laser", 0.4F, 1.8F);
						}
					}
				}
				
				if (!par1ItemStack.hasTagCompound()) par1ItemStack.setTagCompound(new NBTTagCompound());
				TragicWeapon.setStackCooldown(par1ItemStack, 10);
			}

			par1ItemStack.damageItem(1, par3EntityPlayer);
			return par1ItemStack;
		}
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
		return oldStack == null || !(oldStack.getItem() instanceof ItemNekoRayGun);
    }
}
