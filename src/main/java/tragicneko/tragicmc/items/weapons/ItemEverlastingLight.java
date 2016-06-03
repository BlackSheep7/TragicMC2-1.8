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
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.WorldHelper;

public class ItemEverlastingLight extends Item {

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
				&& world.getLightFor(EnumSkyBlock.BLOCK, WorldHelper.getBlockPos(entity)) > 6)
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
				MovingObjectPosition mop = WorldHelper.getMOPFromEntity(par3EntityPlayer, 5.0);

				if (mop == null)
				{
					return par1ItemStack;
				}
				else
				{
					if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
					{
						BlockPos pos = mop.getBlockPos();

						if (!par2World.canMineBlockBody(par3EntityPlayer, pos)) return par1ItemStack;
						if (!par3EntityPlayer.canPlayerEdit(pos, mop.sideHit, par1ItemStack)) return par1ItemStack;

						par2World.setBlockState(pos.offset(mop.sideHit), TragicBlocks.Light.getDefaultState());
						par1ItemStack.damageItem(1, par3EntityPlayer);
						return par1ItemStack;
					}
				}


			}
		}

		return par1ItemStack;
	}
}
