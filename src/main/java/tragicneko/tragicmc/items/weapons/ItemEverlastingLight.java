package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;

public class ItemEverlastingLight extends Item {

	public static final String[] iconNames = new String[] {"3Quarter", "Half", "1Quarter"};

	public ItemEverlastingLight()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(250);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Infinite source of light");
		par2List.add("Must be recharged when low");
		par2List.add("To recharge, must be in brightness");
		par2List.add("and must be daytime");
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
	{
		if (!world.isRemote && world.getWorldTime() % 60L == 0 && world.isDaytime()
				&& world.getLight(WorldHelper.getBlockPos(entity)) > 0.6F)
		{
			if (itemstack.getItemDamage() <= 249)
			{
				if (itemstack.getItemDamage() - 2 >= 0)
				{
					itemstack.setItemDamage(itemstack.getItemDamage() - 2);
				}
				else
				{
					itemstack.setItemDamage(0);
				}
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote)
		{
			if (par1ItemStack.getItemDamage() >= 249)
			{
				par3EntityPlayer.addChatMessage(new ChatComponentText("The Everlasting Light must be recharged..."));
			}
			else
			{
				float f = 1.0F;
				float f1 = par3EntityPlayer.prevRotationPitch + (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch) * f;
				float f2 = par3EntityPlayer.prevRotationYaw + (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw) * f;
				double d0 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * f;
				double d1 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * f + (par2World.isRemote ? par3EntityPlayer.getEyeHeight() - par3EntityPlayer.getDefaultEyeHeight() : par3EntityPlayer.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
				double d2 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * f;
				Vec3 vec3 = new Vec3(d0, d1, d2);
				float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
				float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
				float f5 = -MathHelper.cos(-f1 * 0.017453292F);
				float f6 = MathHelper.sin(-f1 * 0.017453292F);
				float f7 = f4 * f5;
				float f8 = f3 * f5;
				double d3 = 5.0D;

				if (par3EntityPlayer instanceof EntityPlayerMP)
				{
					d3 = ((EntityPlayerMP)par3EntityPlayer).theItemInWorldManager.getBlockReachDistance() + 2.0;
				}
				Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);

				MovingObjectPosition movingobjectposition = par2World.rayTraceBlocks(vec3, vec31, true, false, false);

				if (movingobjectposition == null)
				{
					return par1ItemStack;
				}
				else
				{
					if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
					{
						BlockPos pos = movingobjectposition.getBlockPos();

						if (!par2World.canMineBlockBody(par3EntityPlayer, pos)) return par1ItemStack;
						if (!par3EntityPlayer.canPlayerEdit(pos, movingobjectposition.sideHit, par1ItemStack)) return par1ItemStack;

						par2World.setBlockState(pos.offset(movingobjectposition.sideHit), TragicBlocks.Light.getDefaultState());
						par1ItemStack.damageItem(1, par3EntityPlayer);
						return par1ItemStack;
					}
				}


			}
		}

		return par1ItemStack;
	}
}
