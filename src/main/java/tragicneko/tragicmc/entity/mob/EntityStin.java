package tragicneko.tragicmc.entity.mob;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.miniboss.EntityGreaterStin;
import tragicneko.tragicmc.entity.miniboss.TragicMiniBoss;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityStin extends TragicMob {
	
	public static final int DW_AGE_TICKS = 20;
	public static final int DW_CLIMBABLE = 21;
	public static final int DW_CLIMB_DIRECTION = 22;
	public static final int DW_GALLOP_TICKS = 23;

	protected EntityAIBase targetPlayer = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, playerTarget);

	public EntityStin(World par1World) {
		super(par1World);
		this.stepHeight = 0.5F;
		this.experienceValue = 40;
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.85D));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 16.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 16.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true, false, null));
		this.targetTasks.addTask(3, targetPlayer);
		if (this.superiorForm == null && !(this instanceof TragicMiniBoss)) this.superiorForm = EntityGreaterStin.class;
	}

	@Override
	public boolean isMobVariant()
	{
		return !this.isAdult();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(DW_AGE_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_CLIMBABLE, (byte) 0);
		this.dataWatcher.addObject(DW_CLIMB_DIRECTION, (byte) 0);
		this.dataWatcher.addObject(DW_GALLOP_TICKS, Integer.valueOf(0));
	}

	public int getAgeTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_AGE_TICKS);
	}

	protected void setAgeTicks(int i)
	{
		this.dataWatcher.updateObject(DW_AGE_TICKS, i);
	}

	protected void incrementAgeTicks()
	{
		int pow = this.getAgeTicks();
		this.setAgeTicks(++pow);
		if (pow == 1) this.growUp();
	}

	public boolean isAdult()
	{
		return this.getAgeTicks() > 0;
	}

	public void setChild()
	{
		this.setAgeTicks(-2400 - rand.nextInt(1000));
		this.experienceValue = 3;
	}

	protected void growUp()
	{
		this.reapplyEntityAttributes();
		this.heal(this.getMaxHealth());
		this.updateSize();
		this.targetTasks.removeTask(targetPlayer);
	}

	protected void setCanClimb(boolean flag)
	{
		this.dataWatcher.updateObject(DW_CLIMBABLE, flag ? (byte) 1 : (byte) 0);
	}

	protected boolean canClimb()
	{
		return this.dataWatcher.getWatchableObjectByte(DW_CLIMBABLE) == 1;
	}

	@Override
	public boolean isOnLadder()
	{
		return this.canClimb() && !this.isAdult();
	}

	/**
	 * 0 is normal, 1 is upside down, 2 is sideways left, 3 is sideways right
	 * @return
	 */
	public byte getClimbDirection()
	{
		return this.dataWatcher.getWatchableObjectByte(DW_CLIMB_DIRECTION);
	}

	/**
	 * 0 is normal, 1 is upside down, 2 is horizontal left, 3 is horizontal right, if needed, 4 is vertical up, 5 is vertical down
	 * @param
	 */
	protected void setClimbDirection(byte b)
	{
		this.dataWatcher.updateObject(DW_CLIMB_DIRECTION, b);
	}

	public int getGallopTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_GALLOP_TICKS);
	}

	protected void setGallopTicks(int i)
	{
		this.dataWatcher.updateObject(DW_GALLOP_TICKS, i);
	}

	public boolean isGalloping()
	{
		return this.getGallopTicks() > 0;
	}

	protected void decrementGallopTicks()
	{
		int pow = this.getGallopTicks();
		this.setGallopTicks(--pow);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.reapplyEntityAttributes();
	}

	protected void reapplyEntityAttributes()
	{
		double[] stats = TragicConfig.getMobStat(this.isAdult() ? "stinStats" : "stinBabyStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(stats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(stats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(stats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(stats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(stats[4]);
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.isGalloping() && this.onGround) this.motionX = this.motionZ = 0.0D;
		if (this.isGalloping()) this.motionY = -0.1D;

		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			return;
		}

		if (this.getAgeTicks() <= 700) this.incrementAgeTicks();
		if (this.getGallopTicks() > 0) this.decrementGallopTicks();
		if (this.isAdult() && this.canClimb()) this.setCanClimb(false);

		if (!this.isAdult())
		{
			this.setCanClimb(this.isCollidedHorizontally);
			ArrayList<BlockPos> list;
			BlockPos coords;
			Block block;
			if (this.isOnLadder())
			{
				list = WorldHelper.getBlocksInCircularRange(this.worldObj, this.width, this.posX, this.posY, this.posZ);
				for (int i = 0; i < list.size(); i++)
				{
					coords = list.get(i);
					block = worldObj.getBlockState(coords).getBlock();
					if (block.isOpaqueCube())
					{
						double d0 = Math.abs(this.posX) - Math.abs(coords.getX());
						double d2 = Math.abs(this.posZ) - Math.abs(coords.getZ());

						if (d0 >= 0 && d2 >= 0)
						{
							this.setClimbDirection((byte) 2);
						}
						else if (d0 < 0 && d2 < 0)
						{
							this.setClimbDirection((byte) 3);
						}
						else if (d0 >= 0 && d2 < 0)
						{
							this.setClimbDirection((byte) 4);
						}
						else
						{
							this.setClimbDirection((byte) 5);
						}
					}
				}
			}
			else
			{
				this.setClimbDirection((byte) 0);
			}
		}

		if (this.isWet() && this.ticksExisted % 5 == 0) this.attackEntityFrom(DamageSource.drown, 1.0F);

		if (this.getAttackTarget() != null && !this.isAdult() && this.getAttackTarget().posY + this.stepHeight > this.posY && this.getDistanceToEntity(this.getAttackTarget()) <= 16.0F)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().posZ - this.posZ;
			float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
			this.motionX = d0 / f2 * 0.23D * 0.200000011920929D + this.motionX * 0.10000000298023224D;
			this.motionZ = d1 / f2 * 0.23D * 0.200000011920929D + this.motionZ * 0.10000000298023224D;
			if (this.isCollidedHorizontally) this.motionY = 0.15D;
		}

		if (this.getAttackTarget() == null && this.ticksExisted % 20 == 0 && rand.nextInt(32) == 0 && !this.isGalloping() && this.isAdult() && this.onGround) this.setGallopTicks(30);
	}

	@Override
	public int getTotalArmorValue()
	{
		return TragicConfig.getMobStat(this.isAdult() ? "stinStats" : "stinBabyStats").getArmorValue();
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;
		return super.attackEntityAsMob(par1Entity);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		boolean flag = super.attackEntityFrom(par1DamageSource, par2);

		if (flag && TragicConfig.getBoolean("stinTeleport"))
		{
			if (this.getGallopTicks() == 0) this.setGallopTicks(15);
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(8.0, 8.0, 8.0));
			TragicMob mob;

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof TragicMob)
				{
					mob = (TragicMob) list.get(i);

					if (mob instanceof EntityStin)
					{
						if (mob.getAttackTarget() == null) mob.setAttackTarget(this.getAttackTarget());
					}
				}
			}

			if (this.isAdult() && par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase &&
					!par1DamageSource.isProjectile() && rand.nextInt(16) == 0)
			{
				return this.teleportEnemyAway((EntityLivingBase) par1DamageSource.getEntity(), flag);
			}
		}

		return flag;
	}

	protected boolean teleportEnemyAway(EntityLivingBase entity, boolean flag)
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
									this.worldObj.spawnParticle(getTeleportParticle(), d7, d8, d9, f, f1, f2);
								}

								this.worldObj.playSoundAtEntity(entity, getTeleportSound(), 0.4F, 0.4F);
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

	@Override
	public void fall(float dist, float multi)
	{
		super.fall(dist, multi);
		if (!this.isGalloping()) this.setGallopTicks(15);
	}

	@Override
	public void setInWeb() {}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("ageTicks")) this.setAgeTicks(tag.getInteger("ageTicks"));
		if (tag.hasKey("canClimb")) this.setCanClimb(tag.getBoolean("canClimb"));
		if (tag.hasKey("climbDirection")) this.setClimbDirection(tag.getByte("climbDirection"));
		if (tag.hasKey("gallopTicks")) this.setGallopTicks(tag.getInteger("gallopTicks"));
		if (this.isAdult()) this.targetTasks.removeTask(targetPlayer);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("ageTicks", this.getAgeTicks());
		tag.setBoolean("canClimb", this.canClimb());
		tag.setByte("climbDirection", this.getClimbDirection());
		tag.setInteger("gallopTicks", this.getGallopTicks());
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance ins, IEntityLivingData data)
	{
		if (!this.worldObj.isRemote)
		{
			if (TragicConfig.getBoolean("allowStinBaby") && rand.nextInt(6) == 0) this.setChild();
		}
		return super.onInitialSpawn(ins, data);
	}

	@Override
	protected boolean isChangeAllowed() {
		return TragicConfig.getBoolean("allowGreaterStin") && this.getAgeTicks() >= 600;
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.stin.living" : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.stin.hurt" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.stin.hurt" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return this.isAdult() ? 1.0F : 1.6F;
	}

	@Override
	public float getSoundVolume()
	{
		return 0.6F + rand.nextFloat() * 0.2F;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		this.playSound(this.isAdult() && TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.stin.step" : "mob.spider.step", 0.45F, this.isAdult() ? 1.9F : 0.2F);
	}

	@Override
	public int getTalkInterval()
	{
		return 60;
	}
	
	@Override
	public String getVariantName()
    {
        return "TragicMC.StinBaby";
    }
	
	@Override
	protected String getTeleportSound() {
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.stin.teleport" : super.getTeleportSound();
	}
	
	@Override
	protected EnumParticleTypes getTeleportParticle() {
		return EnumParticleTypes.SMOKE_NORMAL;
	}
	
	@Override
	protected void updateSize() {
		if (this.isAdult())
		{
			this.setSize(0.98F, 2.65F);
			this.stepHeight = 1.0F;
		}
		else
		{
			this.setSize(0.65F, 0.65F);
			this.stepHeight = 0.5F;
		}
	}
}
