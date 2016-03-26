package tragicneko.tragicmc.entity.mob;

import java.util.Calendar;
import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.miniboss.EntityGreaterStin;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.miniboss.EntityMegaCryse;
import tragicneko.tragicmc.entity.miniboss.EntityStinKing;
import tragicneko.tragicmc.entity.miniboss.EntityStinQueen;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.miniboss.TragicMiniBoss;
import tragicneko.tragicmc.entity.projectile.EntityProjectile;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.util.EntityDropHelper;
import tragicneko.tragicmc.util.WorldHelper;

public abstract class TragicMob extends EntityMob
{
	protected TragicMiniBoss superiorForm;

	public static final int DW_CHANGE_STATE = 16;
	public static final int DW_CORRUPTION_TICKS = 17;
	public static final int DW_SUPPORT = 18;

	private int supportID = -1; //the potion id for the support buff applied to others
	private int supportAmp = 1; //the potion amplifier for the support buff

	public static final Predicate playerTarget = new Predicate() {
		@Override
		public boolean apply(Object input) {
			return canApply((Entity) input);
		}

		public boolean canApply(Entity entity) {
			return entity instanceof EntityPlayer;
		}
	};

	public TragicMob(World par1World) {
		super(par1World);
	}

	protected boolean canCorrupt()
	{
		return true;
	}

	protected boolean canChange()
	{
		return this.superiorForm != null && TragicConfig.allowMobTransformation;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(DW_CHANGE_STATE, Byte.valueOf((byte) 0));
		this.dataWatcher.addObject(DW_CORRUPTION_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_SUPPORT, Byte.valueOf((byte) 0));
	}

	public int getCorruptionTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_CORRUPTION_TICKS);
	}

	protected void setCorruptionTicks(int i)
	{
		this.dataWatcher.updateObject(DW_CORRUPTION_TICKS, i);
	}

	protected void incrementCorruptionTicks()
	{
		int pow = this.getCorruptionTicks();
		this.setCorruptionTicks(++pow);
	}

	public boolean isChanging()
	{
		return this.dataWatcher.getWatchableObjectByte(DW_CHANGE_STATE) == 1;
	}

	public void setChanging(boolean flag) {
		this.dataWatcher.updateObject(DW_CHANGE_STATE, flag ? (byte) 1 : (byte) 0); 
	}

	public boolean isSupport() {
		return this.dataWatcher.getWatchableObjectByte(DW_SUPPORT) == 1;
	}

	public void setSupport(boolean flag) {
		this.dataWatcher.updateObject(DW_SUPPORT, flag ? (byte) 1 : (byte) 0);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{			
			this.updateSize(); //might need to do this every tick to update the rendered hitbox to make sure they are correct
			
			if (this.isChanging())
			{
				this.spawnExplosionParticle();
			}

			if (this.isSupport() && this.worldObj.getDifficulty() == EnumDifficulty.HARD)
			{
				double d = 0.35D;
				double d2 = 0.35D;

				for (byte i = 0; i < 3; i++)
				{
					this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
							this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							this.posY + this.rand.nextDouble() * this.height,
							this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							(this.rand.nextDouble() - 0.6D) * 0.1D, this.rand.nextDouble() * 0.1D, (this.rand.nextDouble() - 0.6D) * 0.1D);

					this.worldObj.spawnParticle(EnumParticleTypes.REDSTONE,
							this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							this.posY + this.rand.nextDouble() * this.height,
							this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							0.6D, 0.2D, 0.6D);

					this.worldObj.spawnParticle(EnumParticleTypes.REDSTONE,
							this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							this.posY + this.rand.nextDouble() * this.height,
							this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							rand.nextDouble() * d + d2, rand.nextDouble() * d + d2, rand.nextDouble() * d + d2);

					this.worldObj.spawnParticle(EnumParticleTypes.REDSTONE,
							this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							this.posY + this.rand.nextDouble() * this.height,
							this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							rand.nextDouble() * d + d2, rand.nextDouble() * d + d2, rand.nextDouble() * d + d2);

					this.worldObj.spawnParticle(EnumParticleTypes.REDSTONE,
							this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							this.posY + this.rand.nextDouble() * this.height,
							this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							rand.nextDouble() * d + d2, rand.nextDouble() * d + d2, rand.nextDouble() * d + d2);
				}
			}
			
			if (this.isCorrupted())
			{
				for (byte i = 0; i < 3; i++)
				{
					this.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT,
							this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							this.posY + this.rand.nextDouble() * this.height,
							this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.25D,
							0.2, 0.2, 0.2);
				}
			}
			return;
		}		

		if (this.isChanging() && this.ticksExisted > 1)
		{
			this.change();
			return;
		}

		if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.HARD && this.isSupport() && this.ticksExisted % 30 == 0)
		{
			if (this.supportID < 0)
			{
				this.supportID = this.getRandomPotionID();
				this.supportAmp = rand.nextInt(2);
			}

			if (Potion.potionTypes[this.supportID] != null)
			{
				PotionEffect effect = new PotionEffect(this.supportID, 300, this.supportAmp);
				this.addPotionEffect(effect);

				List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(32.0, 32.0, 32.0));

				for (Entity e : list)
				{
					if (e instanceof EntityMob && this.canEntityBeSeen(e) && ((EntityMob) e).getAttackTarget() != this) ((EntityMob) e).addPotionEffect(effect);
				}
			}
		}

		if (this.getAttackTarget() != null && this.getAttackTarget().isDead) this.setAttackTarget(null);
		if (this.getAttackTarget() != null && !TragicConfig.allowMobInfighting && (this.getAttackTarget() instanceof TragicMob || this.getAttackTarget() instanceof TragicBoss)) this.setAttackTarget(null);

		if (this.getAttackTarget() == null && this.canCorrupt() && TragicConfig.allowCorruption && this.isPotionActive(TragicPotion.Corruption.id))
		{
			EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 16.0D);

			Entity result = null;

			if (entityplayer != null && this.canEntityBeSeen(entityplayer))
			{
				result = entityplayer;
			}
			else if (TragicConfig.allowMobInfighting)
			{
				List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(16.0, 16.0, 16.0));

				for (int i = 0; i < list.size(); i++)
				{
					Entity entity = list.get(i);

					if (this.canEntityBeSeen(entity) && entity != this)
					{
						if (!(entity instanceof EntityWither) && !(entity instanceof EntityDragon) && !(entity instanceof TragicBoss) && entity.getClass() != this.getClass())
						{
							if (entity instanceof TragicMob)
							{
								if (this.superiorForm != null && entity != this.superiorForm && entity.getClass() != this.getLesserForm())
								{
									result = entity;
									break;
								}
								else if (entity.getClass() != this.getLesserForm() && this.superiorForm == null)
								{
									result = entity;
									break;
								}
							}
							else if (entity instanceof EntityAnimal)
							{
								result = entity;
								break;
							}
						}
					}
				}

				this.setAttackTarget((EntityLivingBase) result);
			}
		}

		if (TragicConfig.allowCorruption)
		{
			if (this.isPotionActive(TragicPotion.Corruption))
			{
				this.incrementCorruptionTicks();
			}
			else
			{
				this.setCorruptionTicks(this.getCorruptionTicks() - 1);
			}

			if (this.canChange() && this.getCorruptionTicks() >= 400 && this.rand.nextInt(200) <= TragicConfig.mobTransformationChance && this.ticksExisted % 20 == 0 && rand.nextInt(4) == 0)
			{
				this.setChanging(true);
			}
		}
		else if (this.canChange() && this.ticksExisted >= 6000 && this.ticksExisted % 20 == 0 && this.rand.nextInt(100) <= TragicConfig.mobTransformationChance)
		{
			this.setChanging(true);
		}

		if (this.getIllumination() && TragicConfig.allowMobIllumination && this.ticksExisted % 4 == 0)
		{
			int w = MathHelper.floor_float(this.width);
			int h = MathHelper.floor_float(this.height);
			if (w < 2) w = 2;
			if (h < 2) h = 2;
			final BlockPos pos = WorldHelper.getBlockPos(this).add(rand.nextInt(w) - rand.nextInt(w), rand.nextInt(h) - rand.nextInt(h) + this.height * 2 / 3, rand.nextInt(w) - rand.nextInt(w));
			if (EntityOverlordCore.replaceableBlocks.contains(WorldHelper.getBlock(this.worldObj, pos))) this.worldObj.setBlockState(pos, TragicBlocks.Luminescence.getDefaultState());
		}
	}

	protected void change()
	{
		if (this.isChangeAllowed())
		{
			TragicMob boss = (TragicMob) this.getSuperiorForm();
			boss.copyDataFromOld(this);
			boss.copyLocationAndAnglesFrom(this);
			this.worldObj.removeEntity(this);
			this.worldObj.spawnEntityInWorld(boss);
			boss.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 2));
			boss.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 2));
			boss.setChanging(false);
			boss.playSound("tragicmc:random.change", 1.0F, 1.0F);
			boss.updateSize();
		}
	}

	protected TragicMiniBoss getSuperiorForm() {
		return this.superiorForm;
	}

	/**
	 * This needs to be overriden by each class to specify whether their superior form is allowed via the config, else it isn't even used
	 * @return
	 */
	protected abstract boolean isChangeAllowed();

	public boolean isCorrupted()
	{
		return this.getCorruptionTicks() > 0;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote || TragicConfig.allowStun && this.isPotionActive(TragicPotion.Stun)) return false;

		Boolean result = super.attackEntityAsMob(par1Entity);

		if (result && TragicConfig.allowCorruption && this.canCorrupt() && rand.nextInt(4) == 0 && this.isPotionActive(TragicPotion.Corruption))
		{
			if (par1Entity instanceof TragicMob && ((TragicMob)par1Entity).canCorrupt())
			{
				((TragicMob) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 600, 1));
			}
			else if (par1Entity instanceof EntityMob && !(par1Entity instanceof TragicMob))
			{
				((EntityMob)par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 600, 1));
			}
			else if (par1Entity instanceof EntityAnimal)
			{
				((EntityAnimal)par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 400, 1));
			}
			else if (par1Entity instanceof EntityPlayer && !((EntityPlayer) par1Entity).capabilities.isCreativeMode)
			{
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 200,1));
			}
		}

		return result;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		super.readEntityFromNBT(tag);
		if (tag.hasKey("corruptionTicks")) this.setCorruptionTicks(tag.getInteger("corruptionTicks"));
		if (tag.hasKey("changeState")) this.setChanging(tag.getByte("changeState") == 1);
		if (tag.hasKey("support")) this.setSupport(tag.getByte("support") == 1);
		if (tag.hasKey("supportID")) this.supportID = tag.getInteger("supportID");
		if (tag.hasKey("supportAmp")) this.supportAmp = tag.getInteger("supportAmp");
		this.updateSize();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("corruptionTicks", this.getCorruptionTicks());
		tag.setByte("changeState", this.isChanging() ? (byte) 1 : (byte) 0);
		tag.setByte("support", this.isSupport() ? (byte) 1 : (byte) 0);
		tag.setInteger("supportID", this.supportID);
		tag.setInteger("supportAmp", this.supportAmp);
	}

	public boolean getMobGriefing()
	{
		return this.worldObj.getGameRules().getBoolean("mobGriefing");
	}

	public boolean getAllowLoot()
	{
		return this.worldObj.getGameRules().getBoolean("doMobLoot");
	}

	@Override
	protected void onDeathUpdate()
	{
		++this.deathTime;

		if (this.deathTime == 20)
		{
			int i;

			if (!this.worldObj.isRemote && this.recentlyHit > 0)
			{
				i = this.getExperiencePoints(this.attackingPlayer);

				while (i > 0)
				{
					int j = EntityXPOrb.getXPSplit(i);
					i -= j;
					this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
				}
			}

			this.setDead();
			this.spawnExplosionParticle();
		}
	}

	@Override
	protected void dropFewItems(boolean flag, int l)
	{
		super.dropFewItems(flag, l);

		if (!this.worldObj.isRemote)
		{
			int x = this.getDropAmount() + l;
			int drops = 0;

			for (int i = 0; i < x; i++)
			{
				if (rand.nextInt(100) <= TragicConfig.commonDropRate + (x * 4))
				{
					ItemStack drop = this.isMobVariant() ? EntityDropHelper.getDropFromVariant(this.getClass(), true) : EntityDropHelper.getDropFromEntity(this.getClass(), true);
					if (drop != null) this.capturedDrops.add(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, drop));
					drops++;
				}

				if (flag && rand.nextInt(100) <= TragicConfig.rareDropRate + x)
				{
					ItemStack drop = this.isMobVariant() ? EntityDropHelper.getDropFromVariant(this.getClass(), false) : EntityDropHelper.getDropFromEntity(this.getClass(), false);
					if (drop != null) this.capturedDrops.add(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, drop));
					drops++;
				}

				if (drops > x * 2.5) break;
			}

			if (!TragicConfig.allowMobStatueDrops) return;

			int id = 0;

			if (this instanceof EntityJarra)
			{
				id = 6;
			}
			else if (this instanceof EntityKragul)
			{
				id = 7;
			}
			else if (this instanceof EntityMagmox)
			{
				id = 8;
			}
			else if (this instanceof EntityMegaCryse)
			{
				id = 9;
			}
			else if (this instanceof EntityStinKing)
			{
				id = 10;
			}
			else if (this instanceof EntityStinQueen)
			{
				id = 11;
			}
			else if (this instanceof EntityGreaterStin)
			{
				id = 12;
			}
			else if (this instanceof EntityVoxStellarum)
			{
				id = 13;
			}

			if (id != 0 && rand.nextInt(100) <= TragicConfig.mobStatueDropChance && flag)
			{
				this.capturedDrops.add(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(TragicItems.MobStatue, 1, id)));
			}
		}
	}

	@Override
	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (TragicConfig.allowAchievements && player instanceof EntityPlayerMP) 
			{
				player.triggerAchievement(TragicAchievements.kill);
				if (this instanceof TragicMiniBoss) player.triggerAchievement(TragicAchievements.killMiniBoss);
			}
		}
	}

	public boolean isMobVariant()
	{
		return false;
	}

	@Override
	public void onKillEntity(EntityLivingBase entity)
	{
		super.onKillEntity(entity);
		if (this.worldObj.isRemote) return;
		int i = (int) (entity.getMaxHealth() * 20);
		if (entity instanceof EntityPlayer) i *= 20;
		this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, i, 2));
		this.addPotionEffect(new PotionEffect(Potion.resistance.id, i, 2));
	}

	public Class<? extends TragicMob> getLesserForm()
	{
		return this.getClass();
	}

	@Override
	public boolean canAttackClass(Class par1Class)
	{
		return super.canAttackClass(par1Class) && par1Class != TragicBoss.class && this instanceof TragicMiniBoss ? par1Class != this.getLesserForm() : true;
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance ins, IEntityLivingData data)
	{
		IEntityLivingData sData = super.onInitialSpawn(ins, data);
		this.updateSize();
		if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.HARD && TragicConfig.allowRandomSupportMob) this.setSupport(rand.nextInt(100) == 0);
		if (!TragicConfig.allowGroupBuffs) return sData;
		if (sData == null)
		{
			if (rand.nextInt(200) <= TragicConfig.groupBuffChance)
			{
				int id = this.getRandomPotionID();
				PotionEffect effect = new PotionEffect(id, 99999, rand.nextInt(2));
				this.addPotionEffect(effect);
				return new GroupBuff(effect);
			}
		}
		else if (data instanceof GroupBuff)
		{
			this.addPotionEffect(((GroupBuff) data).getReducedEffect());
			return sData;
		}
		return sData;
	}

	private int getRandomPotionID() {
		int id = Potion.damageBoost.id;
		switch(rand.nextInt(12))
		{
		case 0:
		default:
			break;
		case 1:
			id = Potion.moveSpeed.id;
			break;
		case 2:
			id = Potion.invisibility.id;
			break;
		case 3:
			id = this.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD ? Potion.poison.id : Potion.regeneration.id;
			break;
		case 4:
			id = Potion.fireResistance.id;
			break;
		case 5:
			id = Potion.resistance.id;
			break;
		case 6:
			id = Potion.jump.id;
			break;
		case 7:
			id = TragicConfig.allowImmunity ? TragicPotion.Immunity.id : Potion.digSpeed.id;
			break;
		case 8:
			id = TragicConfig.allowClarity ? TragicPotion.Clarity.id : Potion.resistance.id;
			break;
		case 9:
			id = TragicConfig.allowResurrection ? TragicPotion.Resurrection.id : Potion.digSpeed.id;
			break;
		case 10:
			id = TragicConfig.allowAquaSuperiority ? TragicPotion.AquaSuperiority.id : Potion.jump.id;
			break;
		}

		return id;
	}

	/**
	 * The maximum attempts for mob drops, the looting amount during a kill is added to this amount
	 * @return
	 */
	public int getDropAmount()
	{
		return 1;
	}

	/**
	 * Utility method for firing a projectile at input target, neither can be null, dispersal is how far off target the projectile
	 * will be, setting this to 0 will negate it, on hard projectiles are automatically set to fire with target's motion taken into account
	 * @param entity
	 * @param target
	 * @param variance
	 * @param dispersal
	 * @return
	 */
	protected Entity fireProjectileAtEntity(Entity entity, Entity target, float dispersal)
	{
		entity.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
		entity.setPosition(this.posX, this.posY + (this.height * 2 / 3), this.posZ);
		if (entity instanceof EntityProjectile) ((EntityProjectile) entity).shootingEntity = this;
		else if (entity instanceof EntityFireball) ((EntityFireball) entity).shootingEntity = this;

		double d0 = target.posX - this.posX;
		double d1 = target.posY - this.posY + (target.height * 0.5D) - (this.height * 2 / 3);
		double d2 = target.posZ - this.posZ;

		float mf = entity instanceof EntityProjectile ? ((EntityProjectile) entity).getMotionFactor() : entity instanceof EntityWitherSkull 
				&& ((EntityWitherSkull) entity).isInvulnerable() ? 0.73F : 0.95F;
		float dist = this.getDistanceToEntity(this.getAttackTarget());

		if (this.worldObj.getDifficulty().getDifficultyId() > 2)
		{
			d0 += target.motionX * dist / mf;
			d1 += (target.motionY + 0.09) * dist / mf; //with a small adjustment for a player on the ground, which has a motion value of about -0.1
			d2 += target.motionZ * dist / mf;
		}

		float f = MathHelper.sqrt_float(dist) * dispersal;
		d0 += f * this.rand.nextGaussian();
		d2 += f * this.rand.nextGaussian();

		double d3 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);

		if (entity instanceof EntityProjectile)
		{
			((EntityProjectile) entity).accelerationX = d0 / d3 * 0.1D;
			((EntityProjectile) entity).accelerationY = d1 / d3 * 0.1D;
			((EntityProjectile) entity).accelerationZ = d2 / d3 * 0.1D;
		}
		else if (entity instanceof EntityFireball)
		{
			((EntityFireball) entity).accelerationX = d0 / d3 * 0.1D;
			((EntityFireball) entity).accelerationY = d1 / d3 * 0.1D;
			((EntityFireball) entity).accelerationZ = d2 / d3 * 0.1D;
		}

		this.worldObj.spawnEntityInWorld(entity);

		return entity;
	}

	@Override
	public boolean attackEntityFrom(DamageSource src, float dmg)
	{
		if (src.getEntity() instanceof EntityLivingBase) //ascension testing
		{
			if (((EntityLivingBase) src.getEntity()).getHeldItem() != null)
			{
				ItemStack stack = ((EntityLivingBase) src.getEntity()).getHeldItem();
				if (stack.getItem() instanceof TragicWeapon)
				{
					dmg += ((TragicWeapon) stack.getItem()).ascensionLevel;
				}
			}
		}
		return super.attackEntityFrom(src, dmg);
	}

	public static class GroupBuff implements IEntityLivingData {
		public final PotionEffect effect;
		public GroupBuff(PotionEffect effect)
		{
			this.effect = effect;
		}

		public PotionEffect getReducedEffect()
		{
			return new PotionEffect(effect.getPotionID(), effect.getDuration() * 3 / 4, effect.getAmplifier() / 2 * 3);
		}
	}

	@Override
	public String getName()
	{
		String s = this.isMobVariant() ? this.getVariantName() : null;
		if (s == null) return super.getName();
		return StatCollector.translateToLocal("entity." + s + ".name");
	}

	protected String getVariantName()
	{
		return EntityList.getEntityString(this);
	}

	public boolean isHalloween()
	{
		Calendar calendar = this.worldObj.getCurrentDate();

		if ((calendar.get(2) + 1 == 10 && calendar.get(5) > 29) || (calendar.get(2) + 1 == 11 || calendar.get(5) < 3))
		{
			return true;
		}

		return false;
	}

	/**
	 * Does this mob give off light via light blocks
	 * @return
	 */
	public boolean getIllumination()
	{
		return false;
	}

	protected boolean teleportRandomly()
	{
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 24.0D;
		double d1 = this.posY + (this.rand.nextInt(64) - 32);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 24.0D;
		return this.teleportTo(d0, d1, d2);
	}

	protected boolean teleportToEntity(Entity par1Entity)
	{
		Vec3 vec3 = new Vec3(this.posX - par1Entity.posX, this.getEntityBoundingBox().minY + this.height / 2.0F - par1Entity.posY + par1Entity.getEyeHeight(), this.posZ - par1Entity.posZ);
		vec3 = vec3.normalize();
		double d0 = 16.0D;
		double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
		double d2 = this.posY + (this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
		double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
		return this.teleportTo(d1, d2, d3);
	}

	protected boolean teleportTo(double par1, double par3, double par5)
	{
		double d3 = this.posX;
		double d4 = this.posY;
		double d5 = this.posZ;
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		boolean flag = false;
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);

		boolean flag2 = false;

		if (this.worldObj.getLightFor(EnumSkyBlock.BLOCK, new BlockPos(i, j, k)) <= getTeleportLight())
		{
			flag2 = true;
		}

		if (this.worldObj.isBlockLoaded(new BlockPos(i, j, k)) && flag2)
		{
			boolean flag1 = false;

			while (!flag1 && j > 0)
			{
				Block block = this.worldObj.getBlockState(new BlockPos(i, j - 1, k)).getBlock();

				if (block.getMaterial().blocksMovement())
				{
					flag1 = true;
				}
				else
				{
					--this.posY;
					--j;
				}
			}

			if (flag1)
			{
				this.setPosition(this.posX, this.posY, this.posZ);

				if (this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox()))
				{
					flag = true;
				}
			}
		}

		if (!flag)
		{
			this.setPosition(d3, d4, d5);
			return false;
		}
		else
		{
			short short1 = 128;

			for (int l = 0; l < short1; ++l)
			{
				double d6 = l / (short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
				double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				this.worldObj.spawnParticle(getTeleportParticle(), d7, d8, d9, f, f1, f2);
			}

			this.worldObj.playSoundEffect(d3, d4, d5, getTeleportSound(), 0.2F, 1.0F);
			this.playSound(getTeleportSound(), 0.2F, 1.0F);
			this.onTeleport(d3, d4, d5);
			return true;
		}
	}

	protected String getTeleportSound() {
		return "mob.endermen.portal";
	}

	protected EnumParticleTypes getTeleportParticle() {
		return EnumParticleTypes.PORTAL;
	}

	protected int getTeleportLight() {
		return 8;
	}

	/**
	 * Called on successful teleport of this entity, includes previous coordinates before teleport
	 * @param x
	 * @param y
	 * @param z
	 */
	protected void onTeleport(double x, double y, double z) {

	}

	protected boolean teleportPlayer(EntityPlayerMP mp) {
		if (mp.capabilities.isCreativeMode) return false;

		double x = mp.posX;
		double y = mp.posY;
		double z = mp.posZ;

		double x2 = this.posX;
		double y2 = this.posY;
		double z2 = this.posZ;

		if (mp.playerNetServerHandler.getNetworkManager().isChannelOpen() && this.worldObj == mp.worldObj)
		{
			if (mp.isRiding()) mp.mountEntity(null);

			mp.playerNetServerHandler.setPlayerLocation(x2, y2, z2, mp.rotationYaw, mp.rotationPitch);
			short short1 = 128;

			for (int l = 0; l < short1; ++l)
			{
				double d6 = l / (short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = x + ((x2) - x) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				double d8 = y + ((y2) - y) * d6 + this.rand.nextDouble() * this.height;
				double d9 = z + ((z2) - z) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				this.worldObj.spawnParticle(getTeleportParticle(), d7, d8, d9, f, f1, f2);
			}

			mp.fallDistance = 0.0F;
			this.worldObj.playSoundAtEntity(mp, getTeleportSound(), 0.4F, 0.4F);
			return true;
		}

		return false;
	}
	
	/**
	 * Utility method to update the entity's size (bounding box), called in {@link #onInitialSpawn(DifficultyInstance, IEntityLivingData)}
	 * , {@link #readEntityFromNBT(NBTTagCompound)} and {@link #change()}, for entities that change size based on certain data flags
	 */
	protected void updateSize() {
		
	}
}
