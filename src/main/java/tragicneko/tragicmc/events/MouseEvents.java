package tragicneko.tragicmc.events;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.IMultiPart;
import tragicneko.tragicmc.items.amulet.ItemAmulet.AmuletModifier;
import tragicneko.tragicmc.network.MessageAttack;
import tragicneko.tragicmc.util.WorldHelper;

public class MouseEvents {

	public final Minecraft mc;
	private static final Random rand = new Random();

	public MouseEvents(Minecraft mc)
	{
		this.mc = mc;
	}

	@SubscribeEvent
	public void onMouseInput(MouseEvent event)
	{
		if (event.buttonstate && event.button == 0 && mc.inGameHasFocus)
		{
			if (mc.thePlayer == null) return;

			ItemStack stack = mc.thePlayer.getCurrentEquippedItem();
			World world = mc.theWorld;
			EntityPlayerSP player = mc.thePlayer;

			float f = 1.0F;
			float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
			float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
			double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
			double d1 = player.prevPosY + (player.posY - player.prevPosY) * f + (player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
			double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
			Vec3 vec3 = new Vec3(d0, d1, d2);
			float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
			float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
			float f5 = -MathHelper.cos(-f1 * 0.017453292F);
			float f6 = MathHelper.sin(-f1 * 0.017453292F);
			float f7 = f4 * f5;
			float f8 = f3 * f5;
			double limit = player.capabilities.isCreativeMode ? 2.5D : 1.5D;
			double enchantLimit = limit + (TragicConfig.getBoolean("allowReach") && stack != null ? EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Reach.effectId, stack) : 0);

			IAttributeInstance ins = player.getEntityAttribute(AmuletModifier.reach);
			double d3 = ins == null ? 0.0 : ins.getAttributeValue();
			enchantLimit += d3;
			double box = 0.135D;

			AxisAlignedBB bb;

			meow: for (double d = 0.0D; d <= enchantLimit; d += 0.125D)
			{
				Vec3 vec31 = vec3.addVector(f7 * d, f6 * d, f8 * d);
				if (d > 0) 
				{
					MovingObjectPosition mop = WorldHelper.getMOPFromEntity(player, d);
					if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) break meow;
				}

				bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D).expand(box, box, box).offset(vec31.xCoord, vec31.yCoord + player.getEyeHeight(), vec31.zCoord);
				List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, bb);

				if (list.size() > 0 && d <= limit) break;

				for (Entity entity : list)
				{					
					if (entity instanceof IMultiPart)
					{
						if (TragicConfig.getBoolean("allowNetwork")) TragicMC.proxy.net.sendToServer(new MessageAttack(((IMultiPart) entity).getDefaultPart()));
						break meow;
					}

					if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb) && !(entity instanceof EntityArrow) && entity != player)
					{
						if (TragicConfig.getBoolean("allowNetwork")) TragicMC.proxy.net.sendToServer(new MessageAttack(entity));
						break meow;
					}
				}
			}
		}
	}

	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void onFrozenInput(MouseEvent event)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		if (player == null) return;
		/*
		if (player.isPotionActive(TragicPotion.Frozen) && event.buttonstate)
		{
			PropertyMisc misc = PropertyMisc.get(player);
			if (misc == null) return;

			TragicMC.logInfo("Frozen input received.");

			if (misc.isFrozen)
			{
				misc.frozenInputs--;
				boolean flag = misc.frozenInputs <= 0 && misc.isFrozen;
				TragicMC.net.sendToServer(new MessageFrozenInput(flag));
				misc.isFrozen = !flag;
			}
			else
			{
				misc.isFrozen = true;
				misc.frozenInputs = 30 + (20 * player.getActivePotionEffect(TragicPotion.Frozen).getAmplifier());
			}

			TragicMC.logInfo("Frozen input left is " + misc.frozenInputs);

			if (event.isCancelable()) event.setCanceled(true);
		} */
	}
}
