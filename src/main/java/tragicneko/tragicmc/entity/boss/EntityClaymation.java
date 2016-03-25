package tragicneko.tragicmc.entity.boss;

import static tragicneko.tragicmc.TragicConfig.apisStats;
import static tragicneko.tragicmc.TragicConfig.claymationStats;
import static tragicneko.tragicmc.TragicConfig.jabbaStats;
import static tragicneko.tragicmc.TragicConfig.kitsunakumaStats;
import static tragicneko.tragicmc.TragicConfig.minotaurStats;
import static tragicneko.tragicmc.TragicConfig.norVoxStats;
import static tragicneko.tragicmc.TragicConfig.ragrStats;
import static tragicneko.tragicmc.TragicConfig.skultarStats;
import static tragicneko.tragicmc.TragicConfig.stinKingStats;
import static tragicneko.tragicmc.entity.mob.EntityRagr.crushableBlocks;
import static tragicneko.tragicmc.events.AmuletEvents.badPotions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;
import tragicneko.tragicmc.entity.projectile.EntityDarkMortor;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityClaymation extends TragicBoss {
	
	public static final int DW_ACTUAL_HEALTH = 20;
	public static final int DW_FORM = 21;
	public static final int DW_UTILITY = 22;
	public static final int DW_UTILITY_2 = 23;
	public static final int DW_UTILITY_3 = 24;

	private double[][] formValues = new double[][] {{claymationStats[0], claymationStats[1], claymationStats[2], claymationStats[3], claymationStats[4]}, //claymation
			{minotaurStats[0], minotaurStats[1], minotaurStats[2], minotaurStats[3], minotaurStats[4]}, //minotaur
			{apisStats[0], apisStats[1], apisStats[2], apisStats[3], apisStats[4]}, //apis
			{stinKingStats[0], stinKingStats[1], stinKingStats[2], stinKingStats[3], stinKingStats[4]}, //stin king
			{norVoxStats[0], norVoxStats[1], norVoxStats[2], norVoxStats[3], norVoxStats[4]}, //nor-vox
			{jabbaStats[0], jabbaStats[1], jabbaStats[2], jabbaStats[3], jabbaStats[4]}, //jabba
			{ragrStats[0], ragrStats[1], ragrStats[2], ragrStats[3], ragrStats[4]}, //ragr
			{skultarStats[0], skultarStats[1], skultarStats[2], skultarStats[3], skultarStats[4]}, //skultar
			{kitsunakumaStats[0], kitsunakumaStats[1], kitsunakumaStats[2], kitsunakumaStats[3], kitsunakumaStats[4]}, //kitsune
			{100.0D, 0.25D, 12.0D, 32.0D, 0.5D}}; //iron golem

	private float[][] formSizes = new float[][] {{1.375F, 2.575F}, {0.725F, 2.575F}, {1.385F, 3.325F}, {1.7835F, 5.15F}, {1.135F, 1.575F}, {0.825F, 0.725F}, {1.335F, 2.675F},
			{0.7F, 2.1F}, {0.745F, 1.745F}, {1.4F, 2.9F}};

	private int formTicks;
	private AttributeModifier mod = new AttributeModifier(UUID.fromString("8b42b35e-f870-40ca-ae74-95a38879bed0"), "claymationUtilitySpeedDebuff", TragicConfig.modifier[0], 0);

	public EntityClaymation(World par1World) {
		super(par1World);
		this.setSize(1.375F, 2.575F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchTarget(this, 32.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.isImmuneToFire = true;
		this.stepHeight = 1.5F;
		this.formTicks = 0;
		this.experienceValue = 50;
	}

	@Override
	public boolean canRenderOnFire()
	{
		return false;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(claymationStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(TragicConfig.claymationStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(TragicConfig.claymationStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(TragicConfig.claymationStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(TragicConfig.claymationStats[4]);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		float f = (float) claymationStats[0];
		this.dataWatcher.addObject(DW_ACTUAL_HEALTH, Float.valueOf(f)); //Claymation's actual health
		this.dataWatcher.addObject(DW_FORM, Integer.valueOf(0)); //current form
		this.dataWatcher.addObject(DW_UTILITY, Integer.valueOf(0)); //utility form integer
		this.dataWatcher.addObject(DW_UTILITY_2, Integer.valueOf(0)); //utility form integer 2
		this.dataWatcher.addObject(DW_UTILITY_3, Integer.valueOf(0)); //utility form integer 3
	}

	private void updateHealth(float f)
	{
		this.dataWatcher.updateObject(DW_ACTUAL_HEALTH, f);
	}

	public float getActualHealth()
	{
		return this.dataWatcher.getWatchableObjectFloat(DW_ACTUAL_HEALTH);
	}

	private void setEntityForm(int i)
	{
		this.dataWatcher.updateObject(DW_FORM, i);
		this.formTicks = 0;

		this.setFormAttributes();
		this.setFormSize();
		this.resetUtilityIntegers();
	}

	private void setFormAttributes()
	{
		int i = this.getEntityForm();
		double health = formValues[i][0];
		double speed = formValues[i][1];
		double attack = formValues[i][2];
		double follow = formValues[i][3];
		double knockback = formValues[i][4];

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(health);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attack);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(follow);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(knockback);

		if (i == 0)
		{
			this.setHealth(this.getActualHealth());
			this.updateHealth(this.getHealth());
		}
		else
		{
			this.setHealth(this.getMaxHealth());
		}
	}

	private void setFormSize()
	{
		int i = this.getEntityForm();

		float width = this.formSizes[i][0];
		float height = this.formSizes[i][1];
		this.setSize(width, height);
	}

	/**
	 * 0 is the normal form, 1 is the Minotaur, 2 is the Apis, 3 is the Stin King, 4 is Nor-Vox, 5 is Jabba, 6 is Ragr, 7 is Skultar, 8 is Kitsunakuma, 9 is Iron Golem
	 * @return
	 */
	public int getEntityForm()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_FORM);
	}

	private void resetUtilityIntegers()
	{
		this.dataWatcher.updateObject(DW_UTILITY, 0);
		this.dataWatcher.updateObject(DW_UTILITY_2, 0);
		this.dataWatcher.updateObject(DW_UTILITY_3, 0);
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getActualHealth() > 0F && this.getEntityForm() != 0) this.deathTime = 0;
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			if (rand.nextBoolean())
			{
				for (byte i = 0; i < 2; i++)
				{
					this.worldObj.spawnParticle(EnumParticleTypes.DRIP_LAVA,
							this.posX + (rand.nextDouble() - rand.nextDouble()) * 0.556,
							this.posY + rand.nextDouble() * this.height - 0.1D,
							this.posZ + (rand.nextDouble() - rand.nextDouble()) * 0.556,
							0.0, rand.nextFloat() * -1.2556F, 0.0);
				}
			}

			this.setFormSize();
			return;
		}

		this.formTicks++;
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);

		if (this.formTicks >= 150 && this.getEntityForm() == 0 && TragicConfig.claymationTransformation)
		{
			this.updateHealth(this.getHealth());
			if (this.getAttackTarget() != null)
			{
				this.setEntityForm(rand.nextInt(9) + 1);
			}
			else
			{
				this.setUtilityInt(50);
				if (this.getHealth() < this.getMaxHealth())
				{
					this.heal(12.0F);
					this.updateHealth(this.getHealth());
				}
			}
		}
		else if (this.formTicks >= 600 && this.getEntityForm() != 0)
		{
			if (this.getAttackTarget() != null && TragicConfig.claymationTransformation)
			{
				this.setEntityForm(rand.nextInt(9) + 1);
			}
			else
			{
				this.setEntityForm(0);
			}
		}

		if (this.getAttackTarget() != null)
		{
			this.updateAsForm();
		}
		else
		{
			this.resetUtilityIntegers();
		}
	}

	private void updateAsForm()
	{
		switch(this.getEntityForm())
		{
		default:
		case 0:
			this.updateAsClaymation();
			break;
		case 1:
			this.updateAsMinotaur();
			break;
		case 2:
			this.updateAsApis();
			break;
		case 3:
			this.updateAsStinKing();
			break;
		case 4:
			this.updateAsNorVox();
			break;
		case 5:
			this.updateAsJabba();
			break;
		case 6:
			this.updateAsRagr();
			break;
		case 7:
			this.updateAsSkultar();
			break;
		case 8:
			this.updateAsKitsunakuma();
			break;
		case 9:
			this.updateAsIronGolem();
			break;
		}
	}

	private void updateAsClaymation()
	{
		if (this.getUtilityInt() > 0) this.decrementUtilityInt();
		if (this.getUtilityInt2() > 0) this.decrementUtilityInt2();
		if (this.getUtilityInt3() > 0) this.decrementUtilityInt3();

		if (this.isEntityInRange(this.getAttackTarget(), 2.0F, 8.0F) && this.onGround && rand.nextInt(32) == 0 && this.canEntityBeSeen(this.getAttackTarget()))
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			double d2 = this.getAttackTarget().posY - this.posY;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
			this.motionX = d0 / f2 * 2.5D * 0.700000011920929D + this.motionX * 0.40000000298023224D;
			this.motionZ = d1 / f2 * 2.5D * 0.700000011920929D + this.motionZ * 0.40000000298023224D;
			this.motionY = d1 / f2 * 1.1D * 0.200000011920929D + this.motionY * 0.20000000298023224D;
			this.setUtilityInt(10);
			this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
		}

		this.reflectPotionEffects();
		this.updateHealth(this.getHealth());
	}

	private void reflectPotionEffects() {
		if (!TragicConfig.claymationPotionReflection) return;
		PotionEffect[] effects = new PotionEffect[16];
		PotionEffect temp;
		int a = 0;

		for (int i = 0; i < Potion.potionTypes.length; i++)
		{
			if (Potion.potionTypes[i] != null)
			{
				Potion potion = Potion.potionTypes[i];

				if (this.isPotionActive(potion) && badPotions.contains(potion) && a < effects.length)
				{
					temp = this.getActivePotionEffect(potion);
					effects[a++] = new PotionEffect(potion.id, temp.getDuration() * 2, temp.getAmplifier() * 2);
					this.removePotionEffect(i);
				}
			}
		}

		EntityLivingBase entity = this.getAttackTarget();

		for (PotionEffect effect : effects) {
			if (effect != null)
			{
				entity.addPotionEffect(effect);
			}
		}
	}

	private void updateAsMinotaur()
	{
		if (this.getUtilityInt() > 0)
		{
			this.decrementUtilityInt();
			this.setSprinting(true);
		}
		else
		{
			this.setSprinting(false);
		}

		if (this.isEntityInRange(this.getAttackTarget(), 2.0F, 8.0F) && this.onGround && rand.nextInt(16) == 0 && this.getUtilityInt() == 0 && this.canEntityBeSeen(this.getAttackTarget()))
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			double d2 = this.getAttackTarget().posY - this.posY;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
			this.motionX = d0 / f2 * 2.5D * 0.700000011920929D + this.motionX * 0.40000000298023224D;
			this.motionZ = d1 / f2 * 2.5D * 0.700000011920929D + this.motionZ * 0.40000000298023224D;
			this.motionY = d1 / f2 * 1.1D * 0.200000011920929D + this.motionY * 0.20000000298023224D;
			this.setUtilityInt(20);
			this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
		}
	}

	private void updateAsApis()
	{
		if (this.getUtilityInt2() > 0)
		{
			this.decrementUtilityInt2();
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
			if (this.getUtilityInt() > 0) this.setUtilityInt(0);
			if (this.getUtilityInt3() > 0) this.setUtilityInt3(0);
			this.setSprinting(true);
		}
		else
		{
			this.setSprinting(false);
		}

		if (this.getUtilityInt() > 0) this.decrementUtilityInt();
		if (this.getUtilityInt3() > 0) this.decrementUtilityInt3();

		if (this.isEntityInRange(this.getAttackTarget(), 2.0F, 8.0F) && this.onGround && rand.nextInt(32) == 0 && this.getUtilityInt() == 0 && this.getUtilityInt2() == 0 && this.canEntityBeSeen(this.getAttackTarget()))
		{
			if (rand.nextInt(3) == 0)
			{
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 0.5F, false);
			}

			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			double d2 = this.getAttackTarget().posY - this.posY;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
			this.motionX = d0 / f2 * 2.5D * 0.700000011920929D + this.motionX * 0.40000000298023224D;
			this.motionZ = d1 / f2 * 2.5D * 0.700000011920929D + this.motionZ * 0.40000000298023224D;
			this.motionY = d1 / f2 * 1.1D * 0.200000011920929D + this.motionY * 0.20000000298023224D;
			this.setUtilityInt(10);
			this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
		}
		else if (this.isEntityInRange(this.getAttackTarget(), 6.0F, 12.0F)
				&& this.onGround && rand.nextInt(48) == 0 && this.getUtilityInt() == 0 && this.getUtilityInt2() == 0)
		{
			if (rand.nextInt(3) == 0)
			{
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat(), false);
			}

			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			double d2 = this.getAttackTarget().posY - this.posY;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
			this.motionX = d0 / f2 * 2.5D * 0.700000011920929D + this.motionX * 0.40000000298023224D;
			this.motionZ = d1 / f2 * 2.5D * 0.700000011920929D + this.motionZ * 0.40000000298023224D;
			this.motionY = d1 / f2 * 1.1D * 0.200000011920929D + this.motionY * 0.20000000298023224D;
			this.setUtilityInt(10);
			this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
		}
		else if (this.getDistanceToEntity(this.getAttackTarget()) >= 12.0F && this.onGround && rand.nextInt(48) == 0 && this.getUtilityInt() == 0 && this.getUtilityInt2() == 0)
		{
			if (rand.nextInt(3) == 0)
			{
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() + 0.5F, false);
			}

			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / f2 * 3.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
			this.motionZ = d1 / f2 * 3.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
			this.motionY = 0.45;
			this.setUtilityInt(10);
			this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
		}
		else if (this.onGround && rand.nextInt(48) == 0 && this.getUtilityInt() == 0 && this.getUtilityInt2() == 0 && this.getDistanceToEntity(this.getAttackTarget()) <= 6.0F)
		{
			this.setUtilityInt2(40);
		}

		if (this.getUtilityInt2() == 1)
		{
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(6.0D, 6.0D, 6.0D));
			Entity entity;

			for (int i = 0; i < list.size(); i++)
			{
				entity = list.get(i);
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F - this.getDistanceToEntity(entity));
			}

			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 1.225F + 4.0F, this.getMobGriefing());
		}

		if (this.getDistanceToEntity(this.getAttackTarget()) >= 12.0F && rand.nextInt(8) == 0 && this.getUtilityInt() == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().getEntityBoundingBox().minY + this.getAttackTarget().height / 3.0F - (this.posY + this.height / 2.0F);
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

			switch(rand.nextInt(6))
			{
			case 0:
				EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
				fireball.posY = this.posY + this.height;
				this.worldObj.spawnEntityInWorld(fireball);
				break;
			case 1:
				EntitySolarBomb solarBomb = new EntitySolarBomb(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
				solarBomb.posY = this.posY + this.height;
				this.worldObj.spawnEntityInWorld(solarBomb);
				break;
			case 2:
				EntitySolarBomb solarBomb2 = new EntitySolarBomb(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
				solarBomb2.posY = this.posY + this.height;
				this.worldObj.spawnEntityInWorld(solarBomb2);
				break;
			default:
				for (int i = 0; i < 3; ++i)
				{
					EntitySmallFireball fireball2 = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
					fireball2.posY = this.posY + this.height;
					this.worldObj.spawnEntityInWorld(fireball2);
				}
				break;
			}
		}
	}

	private void updateAsStinKing()
	{
		if (this.getUtilityInt2() > 0)
		{
			this.setSprinting(true);
			if (this.getUtilityInt() > 0) this.setUtilityInt(0);
			this.decrementUtilityInt2();
			if (this.getAttackTarget().posY >= this.posY + 4.5D) this.setUtilityInt2(0);
		}
		else
		{
			this.setSprinting(false);
		}

		if (this.getUtilityInt2() == 0 && this.getUtilityInt() == 0 && this.isEntityInRange(this.getAttackTarget(), 3.0F, 12.0F) &&
				this.ticksExisted % 10 == 0 && rand.nextInt(12) == 0 && this.getAttackTarget().onGround && this.onGround)
		{
			this.setUtilityInt2(200);
		}

		if (this.getUtilityInt2() > 0)
		{
			if (this.getUtilityInt2() <= 170)
			{
				float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 30.0F;

				if (this.isCollidedHorizontally && f >= 2.0F)
				{
					this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (f / 2) * rand.nextFloat() + (f / 2), this.getMobGriefing());
					this.setUtilityInt2(0);
				}
				else
				{
					double d0 = this.getAttackTarget().posX - this.posX;
					double d1 = this.getAttackTarget().posZ - this.posZ;
					float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
					this.motionX += d0 / f2 * 0.13D * 0.1100000011920929D + this.motionX * 0.1000000298023224D;
					this.motionZ += d1 / f2 * 0.13D * 0.1100000011920929D + this.motionZ * 0.1000000298023224D;
					this.motionY = -0.1D;
				}
			}
			else
			{
				this.motionY = -0.1D;
			}
		}

		if (this.getUtilityInt3() > 0)
		{
			this.decrementUtilityInt3();
			if (this.getUtilityInt2() > 0) this.setUtilityInt2(0);
			if (this.getUtilityInt() > 0) this.setUtilityInt(0);
		}

		if (this.ticksExisted % 10 == 0 && rand.nextInt(256) == 0 && TragicConfig.allowFear) this.getAttackTarget().addPotionEffect(new PotionEffect(TragicPotion.Fear.id, 60 + rand.nextInt(160), rand.nextInt(4)));


		if (this.getUtilityInt2() == 0 && this.getUtilityInt3() == 0 && this.getDistanceToEntity(this.getAttackTarget()) >= 6.0F &&
				rand.nextInt(12) == 0 && this.ticksExisted % 10 == 0)
		{
			this.setUtilityInt3(80);
		}

		if (this.getUtilityInt3() > 0 && this.getUtilityInt3() % 10 == 0)
		{
			this.doMortorFire();
		}
	}

	private void doMortorFire() {

		double d0 = this.getAttackTarget().posX - this.posX + rand.nextInt(5) - rand.nextInt(5);
		double d1 = this.getAttackTarget().getEntityBoundingBox().minY + this.getAttackTarget().height / 3.0F - (this.posY + this.height / 2.0F);
		double d2 = this.getAttackTarget().posZ - this.posZ + rand.nextInt(5) - rand.nextInt(5);
		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.875F;

		EntityDarkMortor mortor = new EntityDarkMortor(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
		mortor.posY = this.posY + this.height + 0.5D;
		mortor.posX += d0 * 0.04335D;
		mortor.posZ += d2 * 0.04335D;
		mortor.motionY += 0.66D * f1;
		this.worldObj.spawnEntityInWorld(mortor);
	}

	private void updateAsNorVox()
	{
		if (this.getUtilityInt() > 0) this.decrementUtilityInt();
		if (this.getUtilityInt2() > 0) this.decrementUtilityInt2();

		if (this.getUtilityInt() >= 60) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);

		if (this.ticksExisted % 20 == 0 && this.getDistanceToEntity(this.getAttackTarget()) > 1.0F && rand.nextInt(8) == 0 && this.getUtilityInt() == 0) this.setUtilityInt(120);
		if (this.getUtilityInt() >= 60 && this.ticksExisted % 20 == 0 && this.getAttackTarget() != null) this.shootVoxProjectiles();

		if (this.ticksExisted % 120 == 0 && this.getHealth() < this.getMaxHealth()) this.heal(6.0F);
	}

	private void shootVoxProjectiles()
	{
		double d0 = this.getAttackTarget().posX - this.posX;
		double d1 = this.getAttackTarget().posY - this.posY;
		double d2 = this.getAttackTarget().posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;

		for (int i = 0; i < 2 + rand.nextInt(2); i++)
		{
			EntityWitherSkull skull = new EntityWitherSkull(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
			skull.posX = this.posX + 0.115D * d0;
			skull.posY = this.posY + (this.height * 2 / 3);
			skull.posZ = this.posZ + 0.115D * d2;
			this.worldObj.spawnEntityInWorld(skull);
		}
	}

	private void updateAsJabba()
	{
		if (this.getUtilityInt2() > 0) this.decrementUtilityInt2();
		this.incrementUtilityInt();

		if (this.getUtilityInt() >= 400)
		{
			if (this.ticksExisted % 100 == 0) this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1.5F * rand.nextFloat(), false);
			if (this.ticksExisted % 40 == 0) this.shootJabbaProjectiles();
			if (this.getUtilityInt2() == 0) this.setUtilityInt2(10);
		}

		if (this.isWet()) this.attackEntityFrom(DamageSource.drown, 5.0F);

		if (this.ticksExisted % 20 == 0 && this.rand.nextInt(3) == 0)
		{
			EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 10.0);

			if (player != null && TragicConfig.allowDoom && this.canEntityBeSeen(player))
			{
				PropertyDoom doom = PropertyDoom.get(player);
				int i = this.worldObj.getDifficulty().getDifficultyId();

				if (doom != null) doom.increaseDoom(-((this.rand.nextInt(3) + 1) * i));
			}
		}
	}

	private void shootJabbaProjectiles()
	{
		EntityLivingBase entity = this.getAttackTarget();
		double d0 = entity.posX - this.posX;
		double d1 = entity.getEntityBoundingBox().minY + entity.height / 2.0F - (this.posY + this.height / 2.0F);
		double d2 = entity.posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(entity)) * 0.5F;

		for (int i = 0; i < 5; ++i)
		{
			EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
			entitysmallfireball.posY = this.posY + 0.5D;
			this.worldObj.spawnEntityInWorld(entitysmallfireball);
			if (this.getUtilityInt() >= 50) this.setUtilityInt(this.getUtilityInt() - 50);
		}
	}

	private void updateAsRagr()
	{
		this.incrementUtilityInt();

		if (this.onGround && this.ticksExisted % 10 == 0 && this.rand.nextInt(32) == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
			this.motionZ = d1 / f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
			this.motionY = 0.745D;
		}
		else if (this.getUtilityInt() >= 600)
		{
			if (this.getUtilityInt() % 50 == 0 && this.onGround)
			{
				double d0 = this.getAttackTarget().posX - this.posX;
				double d1 = this.getAttackTarget().posZ - this.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX = d0 / f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
				this.motionZ = d1 / f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
				this.motionY = (rand.nextDouble() * 1.055) + 0.445;
			}

			if (this.getUtilityInt() >= 800) this.setUtilityInt(400);
		}
		else if (this.onGround && rand.nextBoolean())
		{
			double d0 = rand.nextDouble() * 1.45D - rand.nextDouble() * 1.45D;
			double d1 = rand.nextDouble() * 1.45D - rand.nextDouble() * 1.45D;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / f2 * 1.5D * 0.800000011920929D + this.motionX * 0.60000000298023224D;
			this.motionZ = d1 / f2 * 1.5D * 0.800000011920929D + this.motionZ * 0.60000000298023224D;
			this.motionY = 0.545D;
		}
	}

	private void fallAsRagr(float par1)
	{
		boolean flag = this.getMobGriefing();

		if (par1 >= 8.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 3.0F + 2.0F, flag);
		}
		else if (par1 >= 4.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 2.0F + 1.0F, flag);
		}
		else if (par1 >= 2.0F)
		{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 2.0F, flag);
		}

		if (!flag) return;

		int x = (int) this.posX;
		int y = (int) this.posY;
		int z = (int) this.posZ;
		par1 = MathHelper.clamp_float(par1 / 2.0F, 1.0F, 4.0F);
		ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(worldObj, par1, x, y, z);
		BlockPos coords;
		Block block;

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			block = this.worldObj.getBlockState(coords).getBlock();

			if (crushableBlocks.contains(block))
			{
				this.worldObj.setBlockToAir(coords);
			}
			else if (block == Blocks.grass)
			{
				this.worldObj.setBlockState(coords, Blocks.dirt.getDefaultState());
			}
			else if (block == Blocks.stone)
			{
				this.worldObj.setBlockState(coords, Blocks.cobblestone.getDefaultState());
			}
			else if (block == Blocks.stonebrick)
			{
				this.worldObj.setBlockState(coords, Blocks.stonebrick.getStateFromMeta(2), 2);
			}
			else if (block == Blocks.cobblestone)
			{
				this.worldObj.setBlockState(coords, Blocks.gravel.getDefaultState());
			}
		}
	}

	private void updateAsSkultar()
	{
		if (this.rand.nextInt(524) == 0 && this.getUtilityInt() <= 0 && this.getAttackTarget() != null || this.getUtilityInt3() >= 100 && this.getAttackTarget() != null) this.setUtilityInt(5);
		if (this.getUtilityInt2() > 0) this.decrementUtilityInt2();

		if (this.ticksExisted % 60 == 0 && this.getHealth() < this.getMaxHealth()) this.heal(6.0F);

		if (this.getAttackTarget().getHealth() <= this.getAttackTarget().getMaxHealth() / 4) this.setUtilityInt(5);

		int z = this.getHealth() <= this.getMaxHealth() / 2 ? 2 : 1;

		if (this.canEntityBeSeen(this.getAttackTarget()) && this.rand.nextInt(96 / z) == 0)
		{
			EntityLivingBase entity = this.getAttackTarget();

			if (rand.nextInt(72) == 0)
			{
				entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 300 + rand.nextInt(320), 0));
			}

			if (rand.nextInt(256) == 0)
			{
				entity.addPotionEffect(new PotionEffect(Potion.wither.id, 300 + rand.nextInt(320), 0));
			}

			if (rand.nextInt(128) == 0 && TragicConfig.allowInhibit)
			{
				entity.addPotionEffect(new PotionEffect(TragicPotion.Inhibit.id, 300 + rand.nextInt(320), 0));
			}

			if (rand.nextInt(72) == 0 && TragicConfig.allowCripple)
			{
				entity.addPotionEffect(new PotionEffect(TragicPotion.Cripple.id, 300 + rand.nextInt(320), rand.nextInt(3)));
			}

			if (rand.nextInt(72) == 0 && TragicConfig.allowDisorientation)
			{
				entity.addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, 300 + rand.nextInt(320), rand.nextInt(3)));
			}

			if (rand.nextInt(72) == 0 && TragicConfig.allowMalnourish)
			{
				entity.addPotionEffect(new PotionEffect(TragicPotion.Malnourish.id, 300 + rand.nextInt(320), rand.nextInt(3)));
			}
		}

		if (this.isEntityInRange(this.getAttackTarget(), 2.0F, 6.0F)  && this.onGround && rand.nextInt(16) == 0 && this.getUtilityInt2() == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			double d2 = this.getAttackTarget().posY - this.posY;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);

			if (this.getUtilityInt() > 0)
			{
				this.motionX = d0 / f2 * 2.45D * 0.800000011920929D + this.motionX * 0.80000000298023224D;
				this.motionZ = d1 / f2 * 2.45D * 0.800000011920929D + this.motionZ * 0.80000000298023224D;
				this.motionY = d2 / f2 * 2.45D * 0.800000011920929D + this.motionY * 0.80000000298023224D;
			}
			else
			{
				this.motionX = -d0 / f2 * 2.45D * 0.800000011920929D + this.motionX * 0.80000000298023224D;
				this.motionZ = -d1 / f2 * 2.45D * 0.800000011920929D + this.motionZ * 0.80000000298023224D;
				this.motionY = d2 / f2 * 2.45D * 0.800000011920929D + this.motionY * 0.80000000298023224D;
			}
		}
		else if (this.getAttackTarget() != null && this.isEntityInRange(this.getAttackTarget(), 1.0F, 12.0F)  && this.getUtilityInt2() == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			double d2 = this.getAttackTarget().posY - this.posY;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);

			if (this.getUtilityInt() <= 0)
			{
				this.motionX = -d0 / f2 * 1.25D * 0.200000011920929D + this.motionX * 0.10000000298023224D;
				this.motionZ = -d1 / f2 * 1.25D * 0.200000011920929D + this.motionZ * 0.10000000298023224D;
				this.motionY = d2 / f2 * 1.25D * 0.200000011920929D + this.motionY * 0.10000000298023224D;
			}
		}

		int x = this.getHealth() <= this.getMaxHealth() / 2 ? 4 : 2;

		if (this.getDistanceToEntity(this.getAttackTarget()) > 4.0F && rand.nextInt(64 / x) == 0 && this.canEntityBeSeen(this.getAttackTarget()) && this.getUtilityInt2() == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().getEntityBoundingBox().minY + this.getAttackTarget().height / 3.0F - (this.posY + this.height / 2.0F);
			double d2 = this.getAttackTarget().posZ - this.posZ;

			if (this.getUtilityInt() > 0)
			{
				EntityLargePumpkinbomb bomb = new EntityLargePumpkinbomb(this.worldObj, this);
				bomb.posY = this.posY + (this.height * 2.0 / 3.0);
				this.worldObj.spawnEntityInWorld(bomb);
			}
			else
			{
				EntityWitherSkull skull = new EntityWitherSkull(this.worldObj, this, d0, d1, d2);
				skull.posY = this.posY + (this.height * 2.0 / 3.0);
				this.worldObj.spawnEntityInWorld(skull);
			}

			if (rand.nextInt(4) == 0 && this.getUtilityInt() < 10) this.incrementUtilityInt();
		}

		if (this.getDistanceToEntity(this.getAttackTarget()) <= 3.0F && this.getHealth() <= this.getMaxHealth() / 2 && this.getUtilityInt2() == 0 && this.getUtilityInt() > 0 && rand.nextInt(32) == 0) this.setUtilityInt2(20);

		if (this.getDistanceToEntity(this.getAttackTarget()) <= 3.0F && this.getHealth() <= this.getMaxHealth() / 2 && this.getUtilityInt2() == 1)
		{
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(4.0D, 4.0D, 4.0D));
			EntityLivingBase target;

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityLivingBase)
				{
					target = (EntityLivingBase) list.get(i);

					if (this.getDistanceToEntity(target) <= 3.0F)
					{
						boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), target instanceof EntityPlayer ? 10.0F : 20.0F);

						if (flag)
						{
							target.addPotionEffect(new PotionEffect(Potion.wither.id, rand.nextInt(160) + 120, 3));
							target.motionX *= 2.25D;
							target.motionZ *= 2.25D;
						}
					}
				}

			}
		}
	}

	private void updateAsKitsunakuma()
	{
		if (this.getUtilityInt2() > 0) this.decrementUtilityInt2();
		if (this.getUtilityInt() > 0) this.decrementUtilityInt();
		if (this.getUtilityInt3() > 0) this.decrementUtilityInt3();

		if (this.getUtilityInt2() == 1) this.teleportRandomly();

		if (this.getUtilityInt2() == 5 && this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) <= 5.0F) this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue()); //double swipe

		if (this.getUtilityInt() > 0) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);

		if (this.getUtilityInt() > 0 && (this.getDistanceToEntity(this.getAttackTarget()) < 4.0F || this.getDistanceToEntity(this.getAttackTarget()) >= 14.0F))
		{
			this.setUtilityInt(0);
		}
		else if (this.onGround && this.getDistanceToEntity(this.getAttackTarget()) < 4.0F && rand.nextInt(32) == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			double d2 = this.getAttackTarget().posY - this.posY;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
			this.motionX = d0 / f2 * 1.05D * 0.500000011920929D + this.motionX * 0.40000000298023224D;
			this.motionZ = d1 / f2 * 1.05D * 0.500000011920929D + this.motionZ * 0.40000000298023224D;
			this.motionY = d1 / f2 * 1.1D * 0.200000011920929D + this.motionY * 0.20000000298023224D;
		}

		if (this.canEntityBeSeen(this.getAttackTarget()))
		{
			if (this.rand.nextInt(48) == 0)
			{
				EntityLivingBase entity = this.getAttackTarget();

				if (rand.nextInt(72) == 0)
				{
					entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 300 + rand.nextInt(320), 0));
				}

				if (rand.nextInt(72) == 0 && TragicConfig.allowDisorientation)
				{
					entity.addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, 300 + rand.nextInt(320), rand.nextInt(3)));
				}

				if (rand.nextInt(72) == 0)
				{
					entity.addPotionEffect(new PotionEffect(Potion.confusion.id, 300 + rand.nextInt(320), 0));
				}

				if (this.ticksExisted % 120 == 0 && this.rand.nextInt(16) == 0)
				{
					this.teleportRandomly();
				}
			}

			if (this.getUtilityInt3() % 20 == 0 && this.getUtilityInt3() > 0 && this.getDistanceToEntity(this.getAttackTarget()) > 4.0F)
			{
				double d0 = this.getAttackTarget().posX - this.posX;
				double d1 = this.getAttackTarget().getEntityBoundingBox().minY + this.getAttackTarget().height / 3.0F - (this.posY + this.height / 2.0F);
				double d2 = this.getAttackTarget().posZ - this.posZ;

				float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.175F;

				for (int i = 0; i < 3; i++)
				{
					EntitySmallFireball fireball = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
					fireball.posY = this.posY + (this.height * 2 / 3);
					this.worldObj.spawnEntityInWorld(fireball);
				}
			}
		}
		else
		{
			if (this.rand.nextInt(56) == 0 || this.getUtilityInt3() > 0 && this.getUtilityInt3() % 20 == 0 && this.getDistanceToEntity(this.getAttackTarget()) > 4.0F || this.getDistanceToEntity(this.getAttackTarget()) >= 14.0F && rand.nextInt(4) == 0)
			{
				this.teleportToEntity(this.getAttackTarget());
			}
		}

		if (this.isEntityInRange(this.getAttackTarget(), 6.0F, 16.0F) && rand.nextInt(4) == 0 && this.getUtilityInt() == 0 && this.canEntityBeSeen(this.getAttackTarget()) && this.ticksExisted % 5 == 0)
		{
			this.setUtilityInt(40);
		}

		if (this.isEntityInRange(this.getAttackTarget(), 4.0F, 16.0F) && this.canEntityBeSeen(this.getAttackTarget()) && this.getUtilityInt() > 0 && this.getUtilityInt() % 25 == 0)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().getEntityBoundingBox().minY + this.getAttackTarget().height / 3.0F - (this.posY + this.height / 2.0F);
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.375F;

			EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
			fireball.posY = this.posY + (this.height * 2 / 3);
			this.worldObj.spawnEntityInWorld(fireball);
		}

		if (this.getDistanceToEntity(this.getAttackTarget()) >= 12.0F && rand.nextInt(36) == 0 && this.getUtilityInt() == 0)
		{
			boolean flag = this.teleportToEntity(this.getAttackTarget());
			if (!flag) this.teleportRandomly();
		}
	}

	private void updateAsIronGolem()
	{
		//Nobody's home!
	}

	public int getUtilityInt()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_UTILITY);
	}

	private void setUtilityInt(int i)
	{
		this.dataWatcher.updateObject(DW_UTILITY, i);
	}

	private void decrementUtilityInt()
	{
		int pow = this.getUtilityInt();
		this.setUtilityInt(--pow);
	}

	private void incrementUtilityInt()
	{
		int pow = this.getUtilityInt();
		this.setUtilityInt(++pow);
	}

	public int getUtilityInt2()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_UTILITY_2);
	}

	private void setUtilityInt2(int i)
	{
		this.dataWatcher.updateObject(DW_UTILITY_2, i);
	}

	private void decrementUtilityInt2()
	{
		int pow = this.getUtilityInt2();
		this.setUtilityInt2(--pow);
	}

	public int getUtilityInt3()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_UTILITY_3);
	}

	private void setUtilityInt3(int i)
	{
		this.dataWatcher.updateObject(DW_UTILITY_3, i);
	}

	private void decrementUtilityInt3()
	{
		int pow = this.getUtilityInt3();
		this.setUtilityInt3(--pow);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		if (this.worldObj.isRemote) return false;

		if (this.getActualHealth() <= 0.0F) this.setEntityForm(0);

		if (this.getEntityForm() == 0 && source.isProjectile() && source.getSourceOfDamage() != null &&
				!(source.getSourceOfDamage() instanceof EntitySnowball) && !(source.getSourceOfDamage() instanceof EntityIcicle))
		{
			return false;
		}

		if (this.getEntityForm() != 0)
		{
			switch(this.getEntityForm())
			{
			default:
			case 0:
				break;
			case 1:
				damage = this.getMinotaurAttackResponse(source, damage);
				break;
			case 2:
				damage = this.getApisAttackResponse(source, damage);
				break;
			case 3:
				damage = this.getStinKingAttackResponse(source, damage);
				break;
			case 4:
				damage = this.getNorVoxAttackResponse(source, damage);
				break;
			case 5:
				damage = this.getJabbaAttackResponse(source, damage);
				break;
			case 6:
				damage = this.getRagrAttackResponse(source, damage);
				break;
			case 7:
				damage = this.getSkultarAttackResponse(source, damage);
				break;
			case 8:
				damage = this.getKitsunakumaAttackResponse(source, damage);
				break;
			case 9:
				damage = this.getIronGolemAttackResponse(source, damage);
				break;
			}

			if (this.getHealth() - MathHelper.clamp_float(damage - this.getTotalArmorValue(), 0F, TragicConfig.bossDamageCap) <= 0.0F)
			{
				if (this.getEntityForm() == 2 || this.getEntityForm() == 8 || this.getEntityForm() == 7) 
				{
					if (TragicConfig.allowAchievements && source.getEntity() instanceof EntityPlayerMP)
					{
						((EntityPlayerMP) source.getEntity()).triggerAchievement(TragicAchievements.claymation2in1);
					}
				}
				this.setEntityForm(0);
				return true;
			}
		}
		else
		{
			if (this.getUtilityInt2() == 0) this.setUtilityInt2(10);
		}

		return super.attackEntityFrom(source, damage);
	}

	private float getMinotaurAttackResponse(DamageSource source, float damage)
	{
		if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer && !source.isProjectile() && !source.isMagicDamage())
		{
			EntityPlayer player = (EntityPlayer) source.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				if (!(player.getCurrentEquippedItem().getItem() instanceof ItemBow))
				{
					player.getCurrentEquippedItem().damageItem(rand.nextInt(2) + 1, player);
				}
			}
			else
			{
				player.attackEntityFrom(DamageSource.causeMobDamage(this), 0.5F);
			}
		}

		if (!source.isProjectile() && !source.isExplosion() && !source.isMagicDamage()) damage *= 0.65F;
		if (this.getUtilityInt() > 0) damage *= 0.45F;
		return damage;
	}

	private float getApisAttackResponse(DamageSource source, float damage)
	{
		if (source.isExplosion()) return 0.0F;

		if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) source.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				if (!(player.getCurrentEquippedItem().getItem() instanceof ItemBow))
				{
					player.getCurrentEquippedItem().damageItem(rand.nextInt(2) + 1, player);
				}
			}
			else
			{
				if (this.getHealth() <= this.getMaxHealth() / 2)
				{
					player.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F);
					player.setFire(rand.nextInt(8) + 4);
				}
				else
				{
					player.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
				}
			}
		}

		if (!source.isProjectile()) damage *= 0.425F;
		if (this.getUtilityInt3() == 0 && this.getUtilityInt() == 0 && this.getUtilityInt2() == 0) this.setUtilityInt3(10);

		return damage;
	}

	private float getStinKingAttackResponse(DamageSource source, float damage)
	{
		if (rand.nextInt(32) == 0) return 0.0F;

		if (this.getUtilityInt() == 0) this.setUtilityInt(15);

		if (source.getEntity() != null && source.getEntity() instanceof EntityLivingBase &&
				!source.isProjectile() && rand.nextInt(16) == 0)
		{
			this.teleportEnemyAway((EntityLivingBase) source.getEntity(), true);
			return 0.0F;
		}

		if (this.getUtilityInt2() > 170) this.setUtilityInt2(0);
		if (this.getUtilityInt2() > 0 && this.getUtilityInt2() <= 170) damage /= 2;
		return damage;
	}

	private boolean teleportEnemyAway(EntityLivingBase entity, boolean flag)
	{
		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;

		for (int y1 = 0; y1 < 24; y1++)
		{
			for (int z1 = -8; z1 < 9; z1++)
			{
				for (int x1 = -8; x1 < 9; x1++)
				{
					if (World.doesBlockHaveSolidTopSurface(this.worldObj, new BlockPos((int)this.posX + x1, (int)this.posY + y1 - 1, (int)this.posZ + z1)) && rand.nextBoolean())
					{
						if (entity instanceof EntityPlayerMP)
						{
							boolean flag2 = this.teleportPlayer((EntityPlayerMP) entity);
							if (flag2) entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 0));
							return flag2;
						}
						else
						{
							entity.setPosition(x + x1, y + y1, z + z1);

							if (this.worldObj.checkNoEntityCollision(entity.getEntityBoundingBox()) &&
									this.worldObj.getCollidingBoundingBoxes(entity, entity.getEntityBoundingBox()).isEmpty() &&
									!this.worldObj.isAnyLiquid(entity.getEntityBoundingBox()))
							{
								short short1 = 128;

								for (int l = 0; l < short1; ++l)
								{
									double d6 = l / (short1 - 1.0D);
									float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
									float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
									float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
									double d7 = x + ((x + x1) - x) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
									double d8 = y + ((y + y1) - y) * d6 + this.rand.nextDouble() * this.height;
									double d9 = z + ((z + z1) - z) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
									this.worldObj.spawnParticle(this.getTeleportParticle(), d7, d8, d9, f, f1, f2);
								}

								this.worldObj.playSoundAtEntity(entity, this.getTeleportSound(), 0.4F, 0.4F);
								entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 0));
								return flag;
							}
							else
							{
								entity.setPosition(x, y, z);
							}
						}
					}
				}
			}
		}

		return flag;
	}

	private float getNorVoxAttackResponse(DamageSource source, float damage)
	{
		if (source.isExplosion() || source == DamageSource.wither) return 0.0F;

		if (source.isProjectile()) damage /= 4;

		this.setUtilityInt2(10);
		if (source.getEntity() != null && rand.nextInt(8) == 0 && this.getUtilityInt() >= 40)
		{
			this.setUtilityInt(0);
		}

		return damage;
	}

	private float getJabbaAttackResponse(DamageSource source, float damage)
	{
		if (this.getHealth() <= this.getMaxHealth() / 2) damage /= 2;

		if (this.rand.nextInt(8) == 0 && this.worldObj.getDifficulty() == EnumDifficulty.HARD)
		{
			if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer && !source.isProjectile() && !source.isMagicDamage() && !source.isFireDamage())
			{
				EntityPlayer player = (EntityPlayer) source.getEntity();

				if (player.getCurrentEquippedItem() != null && !player.capabilities.isCreativeMode && rand.nextBoolean())
				{
					player.dropOneItem(true);
				}
				else
				{
					if (this.rand.nextInt(4) == 0) player.setFire(4 + rand.nextInt(3));
				}

				if (!player.onGround) this.setUtilityInt(this.getUtilityInt() + 25);
			}
		}
		if (source == DamageSource.drown)
		{
			this.setUtilityInt(this.getUtilityInt() + 50);
		}
		if (this.getUtilityInt2() == 0) this.setUtilityInt2(5);

		return damage;
	}

	private float getRagrAttackResponse(DamageSource source, float damage)
	{
		if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) source.getEntity();

			if (player.getCurrentEquippedItem() != null)
			{
				if (!(player.getCurrentEquippedItem().getItem() instanceof ItemBow))
				{
					player.getCurrentEquippedItem().damageItem(rand.nextInt(4) + 1, player);
				}
			}
			else
			{
				player.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
			}
		}

		if (source.isProjectile()) damage /= 2;

		return damage;
	}

	private float getSkultarAttackResponse(DamageSource source, float damage)
	{
		this.trackHitType(source.getDamageType());
		this.setUtilityInt3(0);
		return damage;
	}

	private void trackHitType(String damageType)
	{
		String hitType = null;
		boolean flag = false;

		if (damageType.equals("arrow"))
		{
			hitType = "projectile";
		}
		else if (damageType.equals("fireball"))
		{
			hitType = "projectile";
		}
		else if (damageType.equals("indirectMagic"))
		{
			hitType = "normal";
		}
		else if (damageType.equals("player"))
		{
			hitType = "normal";
			flag = true;
		}
		else if (damageType.equals("generic"))
		{
			hitType = "normal";
		}
		else if (damageType.equals("mob"))
		{
			hitType = "normal";
		}

		if (hitType == null)
		{
			return;
		}
		else if (hitType == "projectile" && this.getUtilityInt() < 10)
		{
			this.incrementUtilityInt();
			if (flag && this.getUtilityInt() < 10) this.incrementUtilityInt();
		}
		else if (this.getUtilityInt() > -10)
		{
			this.decrementUtilityInt();
			if (flag && this.getUtilityInt() > -10) this.decrementUtilityInt();
		}
	}

	private float getKitsunakumaAttackResponse(DamageSource source, float damage)
	{
		if (this.getUtilityInt3() > 0) return 0.0F;

		boolean flag = false;

		if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) source.getEntity();
			flag = player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem().getItem() == TragicItems.SwordOfJustice || player.getCurrentEquippedItem().getItem() == TragicItems.BowOfJustice);
		}

		if (!source.getDamageType().equals("fireball") && !flag)
		{
			return 0.0F;
		}
		else
		{
			if (this.getUtilityInt3() == 0 && !flag) this.setUtilityInt3(100);
			damage = flag ? Float.MAX_VALUE : (this.getUtilityInt() > 0 && this.getUtilityInt() % 20 >= 15 ? 20 : 10);
			if (!flag && source.getEntity() != null) this.teleportToEntity(source.getEntity());
		}

		return damage;
	}

	private float getIronGolemAttackResponse(DamageSource source, float damage)
	{
		//Out to Lunch!
		return damage;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;

		boolean flag = super.attackEntityAsMob(par1Entity);

		if (!flag) return flag;

		if (this.getEntityForm() != 0)
		{
			switch(this.getEntityForm())
			{
			default:
			case 0:
				break;
			case 1:
				this.attackEntityAsMinotaur(par1Entity);
				break;
			case 2:
				this.attackEntityAsApis(par1Entity);
				break;
			case 3:
				this.attackEntityAsStinKing(par1Entity);
				break;
			case 4:
				this.attackEntityAsNorVox(par1Entity);
				break;
			case 5:
				this.attackEntityAsJabba(par1Entity);
				break;
			case 6:
				this.attackEntityAsRagr(par1Entity);
				break;
			case 7:
				this.attackEntityAsSkultar(par1Entity);
				break;
			case 8:
				this.attackEntityAsKitsunakuma(par1Entity);
				break;
			case 9:
				this.attackEntityAsIronGolem(par1Entity);
				break;
			}
		}
		else
		{
			if (rand.nextInt(4) == 0) par1Entity.setFire(8 + rand.nextInt(4));
			if (this.getUtilityInt3() == 0) this.setUtilityInt3(10);
		}
		return flag;
	}

	private void attackEntityAsMinotaur(Entity par1Entity)
	{
		if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0)
		{
			switch(rand.nextInt(3))
			{
			default:
			case 0:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200)));
				break;
			case 1:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200)));
				break;
			case 2:
				if (TragicConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(200)));
				break;
			}
		}

		if (this.getUtilityInt() > 0)
		{
			par1Entity.motionX *= 1.2000000059604645D;
			par1Entity.motionZ *= 1.2D;
			par1Entity.motionY += 0.3D;
		}
	}

	private void attackEntityAsApis(Entity par1Entity)
	{
		if (this.getHealth() <= this.getMaxHealth() / 2) par1Entity.setFire(8 + rand.nextInt(12));

		if (this.getUtilityInt() > 0)
		{
			par1Entity.motionX *= 2.2000000059604645D;
			par1Entity.motionZ *= 2.2D;
			par1Entity.motionY += 0.56D;
		}
		else
		{
			if (this.getUtilityInt3() == 0) this.setUtilityInt3(10);
		}
	}

	private void attackEntityAsStinKing(Entity par1Entity)
	{
		if (this.getUtilityInt2() > 0 && this.getUtilityInt2() <= 170)
		{
			if (rand.nextBoolean() && par1Entity instanceof EntityLivingBase)
			{
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 300, 0));
				if (TragicConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Submission.id, 300, 1 + rand.nextInt(3)));
			}

			par1Entity.motionY += 1.222543D;
			par1Entity.motionX *= 1.65D;
			par1Entity.motionZ *= 1.65D;
		}
	}

	private void attackEntityAsNorVox(Entity par1Entity)
	{
		if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0 && TragicConfig.allowSubmission)
		{
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(120) + 60, rand.nextInt(2)));
		}
	}

	private void attackEntityAsJabba(Entity par1Entity)
	{
		int i = MathHelper.clamp_int(this.worldObj.getDifficulty().getDifficultyId(), 1, 3);

		if (this.rand.nextInt(MathHelper.ceiling_double_int(9 / i)) == 0)
		{
			if (this.rand.nextInt(8) == 0) par1Entity.setFire(2 * i);

			if (par1Entity instanceof EntityPlayer && i >= 3 && rand.nextBoolean())
			{
				EntityPlayer player = (EntityPlayer) par1Entity;
				if (player.getCurrentEquippedItem() != null && !player.capabilities.isCreativeMode) player.dropOneItem(true);
			}
		}

		this.setUtilityInt(this.getUtilityInt() + 10);
		if (this.getUtilityInt2() == 0) this.setUtilityInt2(10);
	}

	private void attackEntityAsRagr(Entity par1Entity)
	{
		if (par1Entity instanceof EntityLivingBase && rand.nextInt(4) == 0)
		{
			switch(rand.nextInt(3))
			{
			case 0:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200)));
				break;
			case 1:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200)));
				break;
			case 2:
				if (TragicConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(200)));
				break;
			}
		}

		if (!this.onGround)
		{
			par1Entity.motionX *= 1.8000000059604645D;
			par1Entity.motionZ *= 1.8D;
			par1Entity.motionY += 0.6D;
		}
	}

	private void attackEntityAsSkultar(Entity par1Entity)
	{
		if (par1Entity instanceof EntityLivingBase && rand.nextInt(8) == 0)
		{
			switch(rand.nextInt(8))
			{
			case 0:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200) + 320));
				break;
			case 1:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, rand.nextInt(200) + 320));
				break;
			case 2:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, rand.nextInt(200) + 320));
				break;
			case 3:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, rand.nextInt(200) + 320));
				break;
			case 4:
				if (TragicConfig.allowDisorientation) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, rand.nextInt(200) + 320));
				break;
			case 5:
				if (TragicConfig.allowFear) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Fear.id, rand.nextInt(200) + 320));
				break;
			default:
				if (TragicConfig.allowSubmission) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Submission.id, rand.nextInt(200) + 320, rand.nextInt(2) + 1));
				break;
			}
		}

		if (this.getHealth() <= this.getMaxHealth() / 2) par1Entity.setFire(4 + rand.nextInt(12));
		par1Entity.motionX *= 1.4D;
		par1Entity.motionZ *= 1.4D;
		par1Entity.motionY += 0.3D;

		if (this.getUtilityInt2() == 0) this.setUtilityInt2(10);
	}

	private void attackEntityAsKitsunakuma(Entity par1Entity)
	{
		if (this.getUtilityInt() > 0) this.setUtilityInt(0);
		if (this.getUtilityInt2() == 0) this.setUtilityInt2(10);

		if (par1Entity instanceof EntityLivingBase && rand.nextBoolean())
		{
			switch(rand.nextInt(8))
			{
			default:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, rand.nextInt(200) + 320));
				break;
			case 1:
				((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, rand.nextInt(200) + 320));
				break;
			case 2:
				if (TragicConfig.allowDisorientation)
				{
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotion.Disorientation.id, rand.nextInt(200) + 320));
				}
				break;
			}
		}

		if (this.rand.nextInt(4) == 0) par1Entity.setFire(4 + rand.nextInt(8));
	}

	private void attackEntityAsIronGolem(Entity par1Entity)
	{
		par1Entity.motionY += 0.4000000059604645D;
		if (this.getUtilityInt() == 0) this.setUtilityInt(10);
	}

	@Override
	public int getTotalArmorValue()
	{
		return this.getEntityForm() == 0 ? (int) claymationStats[5] : MathHelper.floor_double(claymationStats[5] / 2);
	}

	@Override
	public void fall(float dist, float multi)
	{
		if (this.getEntityForm() == 6) this.fallAsRagr(dist);
		else super.fall(dist, multi);
	}

	@Override
	public void setInWeb() {}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("clayForm")) this.setEntityForm(tag.getInteger("clayForm"));
		if (tag.hasKey("actualHealth"))
		{
			float f = tag.getFloat("actualHealth");
			if (this.getEntityForm() == 0) this.setHealth(f);
			this.updateHealth(f);
		}
		if (tag.hasKey("utility")) this.setUtilityInt(tag.getInteger("utility"));
		if (tag.hasKey("utility2")) this.setUtilityInt2(tag.getInteger("utility2"));
		if (tag.hasKey("utility3")) this.setUtilityInt3(tag.getInteger("utility3"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setFloat("actualHealth", this.getActualHealth());
		tag.setInteger("clayForm", this.getEntityForm());
		tag.setInteger("utility", this.getUtilityInt());
		tag.setInteger("utility2", this.getUtilityInt2());
		tag.setInteger("utility3", this.getUtilityInt3());
	}
	
	@Override
	public void onDeath(DamageSource src)
	{
		super.onDeath(src);
		if (src.getEntity() instanceof EntityPlayerMP && TragicConfig.allowAchievements) ((EntityPlayerMP) src.getEntity()).triggerAchievement(TragicAchievements.claymation);
	}
	
	@Override
	protected void dropFewItems(boolean flag, int l)
	{
		super.dropFewItems(flag, l);
		if (!this.worldObj.isRemote && TragicConfig.allowMobStatueDrops && rand.nextInt(100) <= TragicConfig.mobStatueDropChance && this.getAllowLoot()) this.capturedDrops.add(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(TragicItems.MobStatue, 1, 15)));
	}
	
	@Override
	protected EnumParticleTypes getTeleportParticle() {
		return this.getEntityForm() == 8 ? EnumParticleTypes.FLAME : (this.getEntityForm() == 3 ? EnumParticleTypes.SMOKE_NORMAL : super.getTeleportParticle());
	}
	
	@Override
	protected String getTeleportSound() {
		return this.getEntityForm() == 3 ? "tragicmc:mob.stin.teleport" : super.getTeleportSound();
	}
	
	@Override
	public String getLivingSound()
	{
		return TragicConfig.allowMobSounds ? (this.getEntityForm() == 0 ? "tragicmc:boss.claymation.line" : "tragicmc:boss.claymation.sop") : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.allowMobSounds ? "tragicmc:boss.claymation.sop" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.allowMobSounds ? "tragicmc:boss.claymation.sop" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return 1.0F;
	}

	@Override
	public float getSoundVolume()
	{
		return 1.0F;
	}

	@Override
	public int getTalkInterval()
	{
		return 120;
	}
}
