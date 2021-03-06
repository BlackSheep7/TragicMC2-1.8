package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.inventory.InventoryAmulet;
import tragicneko.tragicmc.items.amulet.ItemAmulet;
import tragicneko.tragicmc.items.amulet.ItemAmulet.AmuletModifier;
import tragicneko.tragicmc.network.MessageAmulet;
import tragicneko.tragicmc.properties.PropertyAmulets;
import tragicneko.tragicmc.util.AmuletHelper;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.util.WorldHelper;

public class AmuletEvents {
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer)
		{
			PropertyAmulets amu = PropertyAmulets.get((EntityPlayer) event.entity);

			if (amu == null)
			{
				PropertyAmulets.register((EntityPlayer) event.entity);
			}
			else
			{
				amu.loadNBTData(new NBTTagCompound());
			}

			if (event.entity instanceof EntityPlayerMP && amu != null && TragicConfig.getBoolean("allowNetwork"))
			{
				TragicMC.proxy.net.sendTo(new MessageAmulet((EntityPlayerMP) event.entity), (EntityPlayerMP) event.entity);
			}
		}
	}

	@SubscribeEvent
	public void onLivingDeathEvent(PlayerEvent.Clone event)
	{
		if (!event.entity.worldObj.isRemote && TragicConfig.getBoolean("allowAmulets")) {
			if (PropertyAmulets.get(event.original) != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				PropertyAmulets.get(event.original).saveNBTData(tag);
				PropertyAmulets.get(event.entityPlayer).loadNBTData(tag);
			}
		}
	}

	@SubscribeEvent
	public void addAttributesToPlayer(EntityConstructing event)
	{
		if (!(event.entity instanceof EntityPlayer)) return;

		EntityPlayer player = (EntityPlayer) event.entity;
		BaseAttributeMap map = player.getAttributeMap();

		map.registerAttribute(AmuletModifier.jumpHeight);
		map.registerAttribute(AmuletModifier.reach);
		map.registerAttribute(AmuletModifier.resistance);
		map.registerAttribute(AmuletModifier.luck);
	}

	@SubscribeEvent
	public void onPlayerJump(LivingJumpEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			IAttributeInstance ins = event.entityLiving.getEntityAttribute(AmuletModifier.jumpHeight);
			double d0 = ins == null ? 0.0 : (ins.getAttributeValue() - 1.4D) * 0.25D;
			double d1 = event.entityLiving.motionY;
			event.entityLiving.motionY = d0 + d1;
		}
	}

	@SubscribeEvent
	public void onPlayerHurt(LivingHurtEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer && !event.source.isDamageAbsolute() && !event.source.isUnblockable())
		{
			IAttributeInstance ins = event.entityLiving.getEntityAttribute(AmuletModifier.resistance);
			double d0 = ins == null ? 0.0 : ins.getAttributeValue();
			event.ammount -= d0;
		}
	}

	@SubscribeEvent
	public void onAmuletTick(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entityLiving;
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;
			if (TragicConfig.getBoolean("allowNetwork")) TragicMC.proxy.net.sendTo(new MessageAmulet((EntityPlayer)event.entityLiving), (EntityPlayerMP)event.entityLiving);

			BaseAttributeMap map = mp.getAttributeMap();
			Multimap mm;

			IAttributeInstance ia;
			for (Object o : map.getAllAttributes())
			{
				if (o instanceof IAttributeInstance)
				{
					ia = (IAttributeInstance) o;
					for (UUID uuid : AmuletHelper.uuids) {
						ia.removeModifier(new AttributeModifier(uuid, ia.getAttribute().getAttributeUnlocalizedName(), 0, 0));
					}
				}
			}

			for (byte meow = 0; meow < 3; meow++)
			{
				ItemStack stack = amu.getActiveAmuletItemStack(meow);
				if (stack != null)
				{
					mm = stack.getAttributeModifiers();
					map.removeAttributeModifiers(mm);
					map.applyAttributeModifiers(mm);
				}
			}

			if (mp.ticksExisted % 2 != 0) return;

			byte[] levels = new byte[3];
			ItemAmulet[] amulets = new ItemAmulet[3];

			byte i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			int same = AmuletHelper.getSameAmulets(amulets[0], amulets[1], amulets[2]);

			if (same == 0)
			{
				for (i = 0; i < 3; i++)
				{
					if (amulets[i] != null) amulets[i].onAmuletUpdate(amu, mp, mp.worldObj, i, levels[i]);
				}
			}
			else if (same == 12)
			{
				if (amulets[0] != null) amulets[0].onAmuletUpdate(amu, mp, mp.worldObj, (byte) 0, AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[1]));
				if (amulets[2] != null) amulets[2].onAmuletUpdate(amu, mp, mp.worldObj, (byte) 2, levels[2]);
			}
			else if (same == 13)
			{
				if (amulets[0] != null) amulets[0].onAmuletUpdate(amu, mp, mp.worldObj, (byte) 0, AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[2]));
				if (amulets[1] != null) amulets[2].onAmuletUpdate(amu, mp, mp.worldObj, (byte) 1, levels[1]);
			}
			else if (same == 23)
			{
				if (amulets[1] != null) amulets[1].onAmuletUpdate(amu, mp, mp.worldObj, (byte) 1, AmuletHelper.getAmuletWithHighestLevel(levels[1], levels[2]));
				if (amulets[0] != null) amulets[0].onAmuletUpdate(amu, mp, mp.worldObj, (byte) 0, levels[0]);
			}
			else if (same == 123)
			{
				if (amulets[0] != null) amulets[0].onAmuletUpdate(amu, mp, mp.worldObj, (byte) 0, AmuletHelper.getAmuletWithHighestLevel(levels[0], levels[1], levels[2]));
			}
		}
	}

	@SubscribeEvent
	public void onHurtAmuletUse(LivingHurtEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entityLiving;
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuMartyr"); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.MartyrAmulet)
				{
					event.ammount /= 2;
					amu.damageStackInSlot(i, MathHelper.floor_float(event.ammount));
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuZombie"); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.ZombieAmulet)
				{
					if (event.source == DamageSource.starve && event.isCancelable()) event.setCanceled(true);
					amu.damageStackInSlot(i, 4 - levels[i]);
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuKitsune"); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.KitsuneAmulet)
				{
					if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase && !event.source.isProjectile())
					{
						if (rand.nextInt(100) <= 70)
						{
							event.source.getEntity().setFire(rand.nextInt(16) + 8);
							((EntityLivingBase) event.source.getEntity()).addPotionEffect(new PotionEffect(Potion.blindness.id, 160 + rand.nextInt(320)));
							amu.damageStackInSlot(i, 1);
						}

						if (event.source.damageType.equalsIgnoreCase("fireball")) event.ammount *= 1.75F;
					}
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuApis"); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.ApisAmulet)
				{
					if (event.source.isExplosion() && event.isCancelable())
					{
						event.setCanceled(true);
						amu.damageStackInSlot(i, 1);
					}
					if (event.source.isProjectile()) event.ammount *= 1.75F;
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuSunken")  && !event.source.isDamageAbsolute(); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.SunkenAmulet)
				{
					if (mp.isInWater()) event.ammount *= 1.535F;
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuPiercing") && event.source.isMagicDamage(); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.PiercingAmulet)
				{
					event.ammount *= 2.135F;
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuTime") && !event.source.isDamageAbsolute(); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.TimeAmulet)
				{
					if (mp.worldObj.getBiomeGenForCoords(WorldHelper.getBlockPos(mp)).getTempCategory() == TempCategory.COLD)
					{
						if (levels[i] >= 2 && event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase)
						{
							((EntityLivingBase) event.source.getEntity()).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100 * levels[i], levels[i]));
						}
						amu.damageStackInSlot(i, 4 - levels[i]);
						event.ammount *= 0.475F;
						break;
					}
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuWither"); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.WitherAmulet)
				{
					if (event.source == DamageSource.wither)
					{
						if (event.isCancelable()) event.setCanceled(true);
						mp.heal(event.ammount);
						break;
					}
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuPolaris") && !event.source.isDamageAbsolute(); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.PolarisAmulet)
				{
					if (event.entityLiving.worldObj.isDaytime())
					{
						event.ammount *= 1.45D;
					}
					else
					{
						event.ammount *= (1 / 1.45D);
					}
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuOverlord") && !event.source.isDamageAbsolute() && event.entityLiving.worldObj.provider.getDimensionId() == TragicConfig.getInt("synapseID"); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.OverlordAmulet)
				{
					event.ammount *= 0.65D;
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuEnderDragon") && event.source == DamageSource.inWall; i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.EnderDragonAmulet)
				{
					BlockPos pos = WorldHelper.getBlockPos(mp);
					
					Block block = mp.worldObj.getBlockState(pos).getBlock();
					if (block.isOpaqueCube() && block.getBlockHardness(mp.worldObj, pos) > 0F) mp.worldObj.setBlockToAir(pos);
					block = mp.worldObj.getBlockState(pos.up()).getBlock();
					if (block.isOpaqueCube()  && block.getBlockHardness(mp.worldObj, pos.up()) > 0F) mp.worldObj.setBlockToAir(pos.up());
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuFusea"); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.FuseaAmulet)
				{
					mp.worldObj.createExplosion(mp, mp.posX, mp.posY, mp.posZ, levels[i] * 1.5F, false);
					amu.damageStackInSlot(i, 4 - levels[i]);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onAmuletAttack(LivingAttackEvent event)
	{
		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.source.getEntity();
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuPiercing"); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.PiercingAmulet && !event.source.isUnblockable())
				{
					event.entityLiving.attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(mp), event.ammount * 0.135F + 1.0F);
					amu.damageStackInSlot(i, 1);
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuStin"); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.StinAmulet)
				{
					double d = 0.4D * levels[i];
					event.entity.motionX = event.entity.posX - mp.posX;
					event.entity.motionY = event.entity.posY - mp.posY;
					event.entity.motionZ = event.entity.posZ - mp.posZ;

					if (mp.isSprinting() && mp.motionY < 0.0 && mp.fallDistance > 0)
					{
						event.entity.motionX *= d;
						event.entity.motionY *= d;
						event.entity.motionZ *= d;
					}
					amu.damageStackInSlot(i, 4 - levels[i]);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onYetiAmuletUse(LivingFallEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entityLiving;
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			float fall = event.distance / 2;

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuYeti"); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.YetiAmulet)
				{
					if (fall > levels[i] * 1.5F)
					{
						fall = levels[i] * 1.5F;
					}

					if (fall >= 1.5F)
					{
						mp.worldObj.createExplosion(mp, mp.posX, mp.posY, mp.posZ, fall, false);
						amu.damageStackInSlot(i, 4 - levels[i]);
					}

					if (levels[i] == 3)
					{
						List<Entity> list = event.entityLiving.worldObj.getEntitiesWithinAABBExcludingEntity(mp, mp.getEntityBoundingBox().expand(6.0D, 6.0D, 6.0D));
						for (i = 0; i < list.size(); i++)
						{
							if (list.get(i) instanceof EntityLivingBase)
							{
								if (TragicConfig.getBoolean("allowSubmission")) ((EntityLivingBase) list.get(i)).addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 120 + rand.nextInt(320), rand.nextInt(4)));
								((EntityLivingBase) list.get(i)).addPotionEffect(new PotionEffect(Potion.weakness.id, 120 + rand.nextInt(320), rand.nextInt(4)));
							}
						}
					}
					break;
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuIronGolem"); i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.IronGolemAmulet && levels[i] >= 3)
				{
					if (event.isCancelable()) event.setCanceled(true);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onSkeletonAmuletUse(ArrowLooseEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP && TragicConfig.getBoolean("amuSkeleton"))
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entityLiving;
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.SkeletonAmulet && rand.nextInt(16 - (levels[i] * 5)) == 0)
				{
					event.charge += levels[i] * 5;
					amu.damageStackInSlot(i, 4 - levels[i]);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onEndermanAmuletUse(BreakEvent event)
	{
		if (TragicConfig.getBoolean("amuEnderman") && event.getPlayer() != null && event.getPlayer().getEquipmentInSlot(0) == null && event.getPlayer() instanceof EntityPlayerMP)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.getPlayer();
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.EndermanAmulet)
				{
					mp.worldObj.setBlockToAir(event.pos);
					EntityItem item = new EntityItem(mp.worldObj, event.pos.getX(), event.pos.getY(), event.pos.getZ(), new ItemStack(event.state.getBlock(), 1, event.state.getBlock().getMetaFromState(event.state)));
					mp.worldObj.spawnEntityInWorld(item);
					if (event.isCancelable()) event.setCanceled(true);
					TragicMC.logInfo("meow");
					break;
				}
			}
		}
	}
	
	public static boolean DO_LUCK_REFLECTION = true;

	@SubscribeEvent
	public void doomRechargeDeath(LivingDeathEvent event)
	{
		if (event.entityLiving.worldObj.isRemote) return;

		if (event.source.getEntity() instanceof EntityPlayerMP && (event.entityLiving instanceof TragicBoss || event.entityLiving instanceof IBossDisplayData) && TragicConfig.getBoolean("allowAmuletBossKillCharge"))
		{
			EntityPlayerMP player = (EntityPlayerMP) event.source.getEntity();
			PropertyAmulets amu = PropertyAmulets.get(player);

			if (amu == null) return;

			ItemStack[] amulets = new ItemStack[3];
			int i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmuletItemStack(i);
			}

			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i].getItemDamage() > 0)
				{
					int yo = AmuletHelper.getAmuletLevel(amulets[i]);
					int repair = (yo * 2 + (rand.nextInt(yo + 1) + rand.nextInt(yo + 1)) * 2) * (int) (10 * rand.nextDouble());
					amu.repairStackInSlot(i, MathHelper.clamp_int(repair, 1, amulets[i].getItemDamage()));
				}
			}

			for (i = 0; i < 3 && TragicConfig.getBoolean("amuLuck") && DO_LUCK_REFLECTION; i++)
			{
				if (amulets[i] != null && ((ItemAmulet) amulets[i].getItem()) == TragicItems.LuckAmulet)
				{
					try
					{
						Method m = ReflectionHelper.findMethod(EntityLivingBase.class, event.entityLiving,
								new String[] {"getExperiencePoints", "func_70693_a"}, EntityPlayer.class);
						m.setAccessible(true);
						int j = (Integer) m.invoke(event.entityLiving, player);

						while (j > 0)
						{
							int k = EntityXPOrb.getXPSplit(j);
							j -= k;
							player.worldObj.spawnEntityInWorld(new EntityXPOrb(player.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, k));
						}
					}
					catch (Exception e)
					{
						TragicMC.logError("Error caught while reflecting experience value from a mob for the Luck Amulet effect", e);
						DO_LUCK_REFLECTION = false;
						break;
					}
					break;
				}
			}
		}

		if (event.source.getEntity() instanceof EntityPlayerMP && event.entityLiving instanceof EntityLiving && TragicConfig.getBoolean("allowAmuletModifiers") && DO_LUCK_REFLECTION)
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.source.getEntity();
			IAttributeInstance ins = mp.getEntityAttribute(AmuletModifier.luck);
			double d0 = ins == null ? 0.0 : ins.getAttributeValue();
			try
			{
				Method m = ReflectionHelper.findMethod(EntityLivingBase.class, event.entityLiving,
						new String[] {"getExperiencePoints", "func_70693_a"}, EntityPlayer.class);
				m.setAccessible(true);
				int j = (Integer) m.invoke((EntityLiving) event.entityLiving, mp);
				j *= d0;

				while (j > 0)
				{
					int k = EntityXPOrb.getXPSplit(j);
					j -= k;
					mp.worldObj.spawnEntityInWorld(new EntityXPOrb(mp.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, k));
				}
			}
			catch (Exception e)
			{
				TragicMC.logError("Error caught while reflecting experience value from a mob for the Luck attribute.", e);
				DO_LUCK_REFLECTION = false;
				return;
			}
		}
	}
	
	@SubscribeEvent
	public void onAmuletDrop(PlayerDropsEvent event) {
		if (event.entityPlayer instanceof EntityPlayerMP && TragicConfig.getBoolean("allowAmuletDeathDrops") && !event.entityPlayer.worldObj.getGameRules().getBoolean("keepInventory"))
		{
			EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;
			PropertyAmulets amu = PropertyAmulets.get(player);
			if (amu == null) return;

			InventoryAmulet inv = amu.inventory;
			inv.dropAllAmulets(event.drops);
			inv.markDirty();
		}
	}

	@SubscribeEvent
	public void onLightningAmuletUse(EntityStruckByLightningEvent event)
	{
		if (event.entity instanceof EntityPlayerMP && TragicConfig.getBoolean("amuLightning"))
		{
			EntityPlayerMP mp = (EntityPlayerMP) event.entity;
			PropertyAmulets amu = PropertyAmulets.get(mp);

			if (amu == null) return;

			int[] levels = new int[3];
			ItemAmulet[] amulets = new ItemAmulet[3];
			byte i;

			for (i = 0; i < 3; i++)
			{
				amulets[i] = amu.getActiveAmulet(i);
				levels[i] = AmuletHelper.getAmuletLevel(amu.getActiveAmuletItemStack(i));
			}

			for (i = 0; i < 3; i++)
			{
				if (amulets[i] != null && amulets[i] == TragicItems.LightningAmulet)
				{
					if (event.isCancelable()) event.setCanceled(true);
					mp.getFoodStats().addStats(20, 1.0F);
					mp.extinguish();
					mp.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 100, 0));
					break;
				}
			}
		}
	}
}
