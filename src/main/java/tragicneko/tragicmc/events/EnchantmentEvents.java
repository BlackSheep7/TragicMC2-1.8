package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class EnchantmentEvents {

	private Set replaceableBlocks = Sets.newHashSet(new Block[] {Blocks.air, TragicBlocks.Luminescence});

	@SubscribeEvent
	public void onLuminescence(LivingUpdateEvent event)
	{
		if (!event.entityLiving.worldObj.isRemote && TragicConfig.getBoolean("allowLuminescence") && event.entityLiving.ticksExisted % 4 == 0)
		{
			boolean flag = false;

			for (int i = 0; i < 5 && !flag; i++)
			{
				ItemStack stack = event.entityLiving.getEquipmentInSlot(i);
				if (stack != null && stack.getItem() != Items.enchanted_book && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Luminescence.effectId, stack) > 0) flag = true;
			}
			if (!flag) return;

			int x = (int) (event.entityLiving.posX + rand.nextInt(2) - rand.nextInt(2));
			int y = (int) (event.entityLiving.posY + rand.nextInt(2) - rand.nextInt(2));
			int z = (int) (event.entityLiving.posZ + rand.nextInt(2) - rand.nextInt(2));
			if (EntityOverlordCore.replaceableBlocks.contains(event.entityLiving.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock()))
			{
				event.entityLiving.worldObj.setBlockState(new BlockPos(x, y, z), TragicBlocks.Luminescence.getDefaultState());
			}
		}
	}

	@SubscribeEvent
	public void onCombustion(HarvestDropsEvent event)
	{
		if (event.harvester != null && !event.isSilkTouching && !event.world.isRemote)
		{
			if (event.harvester.getEquipmentInSlot(0) != null)
			{
				ItemStack tool = event.harvester.getEquipmentInSlot(0);

				if (tool.getItem() instanceof ItemTool && TragicConfig.getBoolean("allowCombustion") && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Combustion.effectId, tool) > 0)
				{
					if (tool.getItem().getDigSpeed(tool, event.state) > 1.0F)
					{
						final int z = EnchantmentHelper.getFortuneModifier(event.harvester);
						ItemStack input = new ItemStack(event.state.getBlock().getItem(event.world, event.pos), 1, event.state.getBlock().damageDropped(event.state));
						ItemStack result = FurnaceRecipes.instance().getSmeltingList().get(input);						
						
						if (result != null)
						{
							result.stackSize = z > 0 ? rand.nextInt(z) + 1 : 1;
							tool.attemptDamageItem(1, rand);
							event.drops.clear();
							event.drops.add(result.copy());
							event.world.playSoundAtEntity(event.harvester, "random.fire.hiss", 0.4F, 1.0F);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onBreak(BreakEvent event)
	{
		if (event.getPlayer() != null && !event.isCanceled())
		{
			if (event.getPlayer().getEquipmentInSlot(0) != null)
			{
				ItemStack tool = event.getPlayer().getEquipmentInSlot(0);
				int i = TragicConfig.getBoolean("allowVeteran") ? EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Veteran.effectId, tool) * 2 + 1 : 1;

				if (tool.getItemDamage() >= tool.getMaxDamage() - i && TragicConfig.getBoolean("allowUnbreakable") && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Unbreakable.effectId, tool) > 0)
				{
					if (event.isCancelable()) event.setCanceled(true);
					return;
				}

				if (tool.getItem() instanceof ItemTool && canMineWithTool(event.world, tool, event.pos) && TragicConfig.getBoolean("allowVeteran") && !event.world.isRemote)
				{
					int e = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Veteran.effectId, tool);
					if (e < 1) return;
					MovingObjectPosition mop = WorldHelper.getMOPFromEntity(event.getPlayer(), event.getPlayer().capabilities.isCreativeMode ? 4.5 : 3.5);

					if (mop != null)
					{
						if (mop.sideHit == EnumFacing.DOWN || mop.sideHit == EnumFacing.UP)
						{
							event.pos.east(1);
							if (canMineWithTool(event.world, tool, event.pos.north())) event.world.destroyBlock(event.pos.north(), true);
							if (canMineWithTool(event.world, tool, event.pos.south())) event.world.destroyBlock(event.pos.south(), true);
							if (canMineWithTool(event.world, tool, event.pos.east())) event.world.destroyBlock(event.pos.east(), true);
							if (canMineWithTool(event.world, tool, event.pos.west())) event.world.destroyBlock(event.pos.west(), true);

							if (e >= 2)
							{
								if (canMineWithTool(event.world, tool, event.pos.north().east())) event.world.destroyBlock(event.pos.north().east(), true);
								if (canMineWithTool(event.world, tool, event.pos.north().west())) event.world.destroyBlock(event.pos.north().west(), true);
								if (canMineWithTool(event.world, tool, event.pos.south().east())) event.world.destroyBlock(event.pos.south().east(), true);
								if (canMineWithTool(event.world, tool, event.pos.south().west())) event.world.destroyBlock(event.pos.south().west(), true);

								if (e >= 3)
								{
									if (canMineWithTool(event.world, tool, event.pos.north(2))) event.world.destroyBlock(event.pos.north(2), true);
									if (canMineWithTool(event.world, tool, event.pos.south(2))) event.world.destroyBlock(event.pos.south(2), true);
									if (canMineWithTool(event.world, tool, event.pos.east(2))) event.world.destroyBlock(event.pos.east(2), true);
									if (canMineWithTool(event.world, tool, event.pos.west(2))) event.world.destroyBlock(event.pos.west(2), true);
								}
							}
						}
						else
						{
							if (canMineWithTool(event.world, tool, event.pos.up())) event.world.destroyBlock(event.pos.up(), true);
							if (canMineWithTool(event.world, tool, event.pos.down())) event.world.destroyBlock(event.pos.down(), true);

							if (e >= 3)
							{
								if (canMineWithTool(event.world, tool, event.pos.up(2))) event.world.destroyBlock(event.pos.up(2), true);
								if (canMineWithTool(event.world, tool, event.pos.down(2))) event.world.destroyBlock(event.pos.down(2), true);
							}

							if (mop.sideHit == EnumFacing.SOUTH || mop.sideHit == EnumFacing.NORTH)
							{
								if (canMineWithTool(event.world, tool, event.pos.east())) event.world.destroyBlock(event.pos.east(), true);
								if (canMineWithTool(event.world, tool, event.pos.west())) event.world.destroyBlock(event.pos.west(), true);

								if (e >= 2)
								{
									if (canMineWithTool(event.world, tool, event.pos.east().up())) event.world.destroyBlock(event.pos.east().up(), true);
									if (canMineWithTool(event.world, tool, event.pos.west().up())) event.world.destroyBlock(event.pos.west().up(), true);
									if (canMineWithTool(event.world, tool, event.pos.east().down())) event.world.destroyBlock(event.pos.east().down(), true);
									if (canMineWithTool(event.world, tool, event.pos.west().down())) event.world.destroyBlock(event.pos.west().down(), true);

									if (e >= 3)
									{
										if (canMineWithTool(event.world, tool, event.pos.east(2))) event.world.destroyBlock(event.pos.east(2), true);
										if (canMineWithTool(event.world, tool, event.pos.west(2))) event.world.destroyBlock(event.pos.west(2), true);
									}
								}
							}
							else
							{
								if (canMineWithTool(event.world, tool, event.pos.north())) event.world.destroyBlock(event.pos.north(), true);
								if (canMineWithTool(event.world, tool, event.pos.south())) event.world.destroyBlock(event.pos.south(), true);

								if (e >= 2)
								{
									if (canMineWithTool(event.world, tool, event.pos.south().down())) event.world.destroyBlock(event.pos.south().down(), true);
									if (canMineWithTool(event.world, tool, event.pos.north().down())) event.world.destroyBlock(event.pos.north().down(), true);
									if (canMineWithTool(event.world, tool, event.pos.south().up())) event.world.destroyBlock(event.pos.south().up(), true);
									if (canMineWithTool(event.world, tool, event.pos.north().up())) event.world.destroyBlock(event.pos.north().up(), true);

									if (e >= 3)
									{
										if (canMineWithTool(event.world, tool, event.pos.north(2))) event.world.destroyBlock(event.pos.north(2), true);
										if (canMineWithTool(event.world, tool, event.pos.south(2))) event.world.destroyBlock(event.pos.south(2), true);
									}
								}
							}
						}

						if (!event.getPlayer().capabilities.isCreativeMode) tool.attemptDamageItem(e + rand.nextInt(e), rand);
					}
				}
			}
		}
	}

	public static boolean canMineWithTool(World world, ItemStack stack, BlockPos pos)
	{
		ItemTool tool = (ItemTool) stack.getItem();
		IBlockState block = world.getBlockState(pos);
		if (tool.getDigSpeed(stack, block) > 1.0F && block.getBlock().getBlockHardness(world, pos) > 0) return true;
		return false;
	}

	@SubscribeEvent
	public void multiplyArrow(ArrowLooseEvent event)
	{
		if (TragicConfig.getBoolean("allowMultiply"))
		{
			EntityPlayer player = event.entityPlayer;
			World world = player.worldObj;

			float f = event.charge / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if (f < 0.5D)
			{
				return;
			}

			if (f > 1.0F)
			{
				f = 1.0F;
			}

			if (EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Multiply.effectId, event.bow) > 0)
			{
				EntityArrow entityarrow = new EntityArrow(world, player, f * 1.5F);
				entityarrow.motionX *= 0.95;
				entityarrow.motionZ *= 0.95;
				EntityArrow entityarrow2 = new EntityArrow(world, player, f * 1.5F);
				entityarrow2.motionX *= 1.3;
				entityarrow2.motionZ *= 1.3;

				if (f == 1.0F)
				{
					entityarrow.setIsCritical(true);
					entityarrow2.setIsCritical(true);
				}

				int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, event.bow);

				if (k > 0)
				{
					entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D);
					entityarrow2.setDamage(entityarrow.getDamage() + k * 0.5D);
				}

				int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, event.bow);

				if (l > 0)
				{
					entityarrow.setKnockbackStrength(0);
					entityarrow2.setKnockbackStrength(0);
				}

				if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, event.bow) > 0)
				{
					entityarrow.setFire(100);
					entityarrow2.setFire(100);
				}

				entityarrow.canBePickedUp = 2;
				entityarrow2.canBePickedUp = 2;

				if (!world.isRemote)
				{
					world.spawnEntityInWorld(entityarrow);
					world.spawnEntityInWorld(entityarrow2);
				}
			}
		}
	}

	@SubscribeEvent
	public void withAgility(LivingHurtEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer && TragicConfig.getBoolean("allowAgility") && !event.source.isMagicDamage())
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (!event.source.canHarmInCreative())
			{
				for (int x = 1; x < 5; x++)
				{
					if (player.getEquipmentInSlot(x) != null)
					{
						ItemStack armor = player.getEquipmentInSlot(x);

						if (EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Agility.effectId, armor) > 0)
						{
							int l = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Agility.effectId, armor);

							if (rand.nextInt(400) <= 10 * l)
							{
								if (event.isCancelable())
								{
									event.setCanceled(true);
								}
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onExtraWeaponEnchantUse(LivingHurtEvent event)
	{
		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer && TragicConfig.getBoolean("allowEnchantments"))
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				float f = event.ammount / 10;

				if (TragicConfig.getBoolean("allowVampirism"))
				{
					int vamp = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Vampirism.effectId, player.getCurrentEquippedItem());

					if (vamp > 0 && rand.nextInt(10 - (vamp * 2)) == 0 && rand.nextInt(4) == 0)
					{
						player.heal(f * vamp);

						if (TragicConfig.getBoolean("allowCripple"))
						{
							event.entityLiving.addPotionEffect(new PotionEffect(TragicPotion.Cripple.id, 60 * vamp, vamp * 2));
						}

						if (rand.nextInt(3) == 0)
						{
							player.addExhaustion(f);
						}
					}
				}

				if (TragicConfig.getBoolean("allowLeech"))
				{
					int leech = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Leech.effectId, player.getCurrentEquippedItem());

					if (leech > 0 && rand.nextInt(10 - (leech * 2)) == 0 && rand.nextInt(2) == 0)
					{
						int food = player.getFoodStats().getFoodLevel();

						if (food + leech <= 20)
						{
							player.getFoodStats().addStats(leech, 1.0F);
						}

						if (event.entityLiving instanceof EntityPlayer)
						{
							((EntityPlayer) event.entityLiving).addExhaustion(f);
						}

						if (rand.nextInt(3) == 0)
						{
							PropertyDoom property = PropertyDoom.get(player);
							property.increaseDoom(-leech);
						}
					}
				}

				if (TragicConfig.getBoolean("allowConsume"))
				{
					int consume = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Consume.effectId, player.getCurrentEquippedItem());

					if (consume > 0 && rand.nextInt(10 - (consume * 2)) == 0 && rand.nextInt(3) == 0)
					{
						PropertyDoom property1 = PropertyDoom.get(player);

						if (property1 != null && TragicConfig.getBoolean("allowDoom"))
						{
							property1.increaseDoom(consume);
							if (event.entityLiving instanceof EntityPlayer)
							{
								PropertyDoom property2 = PropertyDoom.get((EntityPlayer) event.entityLiving);

								property2.increaseDoom(-consume);
							}

							if (rand.nextInt(3) == 0)
							{
								player.attackEntityFrom(DamageSource.generic, consume);
							}
						}
					}
				}

				if (TragicConfig.getBoolean("allowRuneBreak"))
				{
					int runeBreak = EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.RuneBreak.effectId, player.getCurrentEquippedItem());

					if (runeBreak > 0 && event.source.isMagicDamage())
					{
						for (int i = 0; i < runeBreak; i++)
						{
							event.ammount *= 1.125F;
						}
						if (player instanceof EntityPlayerMP) ((EntityPlayerMP) player).triggerAchievement(TragicAchievements.enchant);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onUnbreakableUse(LivingAttackEvent event)
	{
		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			if (player.getCurrentEquippedItem() != null)
			{
				ItemStack stack = player.getCurrentEquippedItem();

				if (stack.getItemDamage() >= stack.getMaxDamage() - 1 && TragicConfig.getBoolean("allowUnbreakable") && EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Unbreakable.effectId, stack) > 0)
				{
					if (event.isCancelable()) event.setCanceled(true);
				}
			}
		}
	}
}
