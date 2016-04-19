package tragicneko.tragicmc.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.IMultiPart;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityMechaExo extends EntityRidable {

	private int cooldown = 100;
	public static final int DW_TARGET_ID = 20;
	public static final int DW_HAS_FIRED = 21;
	public static final int DW_THRUST_TICKS = 22;

	public final EntityAIBase attackOnCollide = new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true);
	public final EntityAIBase moveTowardsTarget = new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F);
	public final EntityAIBase hurtByTarget = new EntityAIHurtByTarget(this, true);

	public EntityMechaExo(World par1World) {
		super(par1World);
		this.setSize(2.25F, 2.4F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DW_TARGET_ID, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_HAS_FIRED, (byte) 0);
		this.dataWatcher.addObject(DW_THRUST_TICKS, Integer.valueOf(0));
	}

	public int getTargetID()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_TARGET_ID);
	}

	private void setTargetID(int i)
	{
		this.dataWatcher.updateObject(DW_TARGET_ID, i);
	}

	public boolean hasFired()
	{
		return this.dataWatcher.getWatchableObjectByte(DW_HAS_FIRED) == 1;
	}

	private void setFired(boolean flag, int i)
	{
		this.dataWatcher.updateObject(DW_HAS_FIRED, flag ? (byte) 1 : (byte) 0);
		this.setTargetID(flag ? i : 0);
	}

	public int getThrustTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_THRUST_TICKS);
	}

	public void setThrustTicks(int i)
	{
		this.dataWatcher.updateObject(DW_THRUST_TICKS, i);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.19);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	@Override
	public int getTotalArmorValue() {
		return 24;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			Entity entity = this.worldObj.getEntityByID(this.getTargetID());
			if (entity != null && this.hasFired())
			{
				double d0 = entity.posX - this.posX;
				double d1 = entity.posY - this.posY;
				double d2 = entity.posZ - this.posZ;

				for (byte l = 0; l < 8; l++)
				{
					double d3 = 0.123D * l + (rand.nextDouble() * 0.125D);
					this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX + d0 * d3, this.posY + d1 * d3 + 1.25D, this.posZ + d2 * d3, 0.0, 0.0, 0.0);
				}
			}
			return;
		}

		if (this.hasFired() && cooldown <= 9) this.setFired(false, 0);
		if (cooldown > 0) cooldown--;

		if (this.ticksExisted % 20 == 0)
		{
			TragicMC.logInfo("exo health is " + this.getHealth() + "/ " + this.getMaxHealth());
			TragicMC.logInfo("cooldown is " + this.cooldown);
		}

		if (this.getThrustTicks() > 0 && this.riddenByEntity != null)
		{
			this.setThrustTicks(this.getThrustTicks() - 1);
			TragicMC.logInfo("thrusttttttt");
			if (this.getThrustTicks() % 5 == 0 && this.getThrustTicks() > 0) this.worldObj.playSoundAtEntity(this, "tragicmc:random.rocketflying", 0.8F, 0.115F);

			if (this.riddenByEntity instanceof EntityPlayer)
			{
				Vec3 vec = WorldHelper.getVecFromEntity(this.riddenByEntity, 1.5);
				double d0 = vec.xCoord - this.posX;
				double d2 = vec.zCoord - this.posZ;
				this.motionX = d0;
				this.motionZ = d2;
				this.motionY = this.motionY * 1.1D;
				this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
			}
			else if (this.getAttackTarget() != null)
			{
				double d0 = this.getAttackTarget().posX - this.posX;
				double d2 = this.getAttackTarget().posZ - this.posZ;
				this.motionX = d0;
				this.motionZ = d2;
				this.motionY = this.motionY * 1.1D;
				this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
			}

			List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(2.0, 1.0, 2.0));
			for (int i = 0; i < list.size(); i++)
			{
				EntityLivingBase e = list.get(i);
				if (e != this.riddenByEntity && e != this && this.riddenByEntity instanceof EntityLivingBase)
				{
					this.attackEntityAsMob(e);
				}
			}
		}
	}

	@Override
	public void useAttack(int attackType) {
		if (this.riddenByEntity == null) return;

		Vec3 vec = WorldHelper.getVecFromEntity(this.riddenByEntity, 30.0);
		if (vec == null) return;		

		if (this.riddenByEntity instanceof EntityLivingBase)
		{
			EntityLivingBase entity = (EntityLivingBase) this.riddenByEntity;
			double d4 = vec.xCoord - entity.posX;
			double d5 = vec.yCoord - (entity.posY + entity.height / 2.0F);
			double d6 = vec.zCoord - entity.posZ;

			if (attackType == 0)
			{
				EntityNekoRocket rocket = new EntityNekoRocket(this.worldObj, entity, d4, d5, d6);
				rocket.posY = entity.posY + entity.getEyeHeight() - 0.45F;
				rocket.posX += d4 * 0.315;
				rocket.posZ += d6 * 0.315;
				if (!this.worldObj.isRemote)
				{
					this.cooldown = 20;
					this.worldObj.spawnEntityInWorld(rocket);
					this.worldObj.playSoundAtEntity(this, "tragicmc:random.rocketflying", 0.8F, 1.6F);
					this.worldObj.playSoundAtEntity(this, "tragicmc:random.tinktink", 1.8F, 1.0F);
				}
			}
			else if (attackType == 1)
			{
				float f1 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch);
				float f2 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw);
				Vec3 vec3 = new Vec3(entity.posX, entity.posY, entity.posZ);
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
					bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D).expand(box, box, box).offset(vec31.xCoord, vec31.yCoord + entity.getEyeHeight(), vec31.zCoord);
					List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(entity, bb);		

					List<BlockPos> list2 = WorldHelper.getBlocksInSphericalRange(this.worldObj, 0.45, vec31.xCoord, vec31.yCoord + entity.getEyeHeight(), vec31.zCoord);
					Block block;
					AxisAlignedBB bb2;

					for (BlockPos coords : list2)
					{
						block = this.worldObj.getBlockState(coords).getBlock();

						bb2 = block.getCollisionBoundingBox(this.worldObj, coords, this.worldObj.getBlockState(coords));
						if (bb2 != null && bb2.isVecInside(vec31)) break meow;
					}

					if (list.isEmpty() || d < 2.5)
					{
						continue;
					}	

					for (Entity e : list)
					{					
						if (e instanceof IMultiPart)
						{

							((IMultiPart) e).getDefaultPart().attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(entity), 5.0F);
							if (TragicConfig.getBoolean("allowMobSounds")) this.worldObj.playSoundAtEntity(e, "tragicmc:boss.aegar.laser", 0.4F, 1.8F);
							this.setFired(true, e.getEntityId());
							cooldown = 10;
							break meow;
						}

						if (!(e instanceof EntityItem) && !(e instanceof EntityXPOrb) && !(e instanceof EntityArrow) && e != this && e != this.riddenByEntity)
						{

							e.attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(entity), 5.0F);
							if (TragicConfig.getBoolean("allowMobSounds")) this.worldObj.playSoundAtEntity(e, "tragicmc:boss.aegar.laser", 0.4F, 1.8F);
							this.setFired(true, e.getEntityId());
							cooldown = 10;
							break meow;
						}
					}
				}

				if (TragicConfig.getBoolean("allowMobSounds") && !this.worldObj.isRemote) this.worldObj.playSoundAtEntity(this, "tragicmc:boss.aegar.laser", 0.4F, 1.8F);
			}
			else
			{
				this.setThrustTicks(30);
			}
		}
	}

	@Override
	public void useAttackViaMob(int attackType, EntityLivingBase target) {
		if (this.riddenByEntity instanceof EntityLivingBase)
		{
			EntityLivingBase entity = (EntityLivingBase) this.riddenByEntity;
			double d4 = target.posX - entity.posX;
			double d5 = target.posY - (entity.posY + entity.height / 2.0F);
			double d6 = target.posZ - entity.posZ;

			if (attackType == 0)
			{
				EntityNekoRocket rocket = new EntityNekoRocket(this.worldObj, entity, d4, d5, d6);
				rocket.posY = entity.posY + entity.getEyeHeight() - 0.45F;
				rocket.posX += d4 * 0.315;
				rocket.posZ += d6 * 0.315;
				if (!this.worldObj.isRemote)
				{
					this.cooldown = 20;
					this.worldObj.spawnEntityInWorld(rocket);
					this.worldObj.playSoundAtEntity(this, "tragicmc:random.rocketflying", 0.8F, 1.6F);
					this.worldObj.playSoundAtEntity(this, "tragicmc:random.tinktink", 1.8F, 1.0F);
				}
			}
			else if (attackType == 2)
			{
				this.setThrustTicks(10);
			}
			else
			{
				if (this.canEntityBeSeen(target))
				{
					target.attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(this), 5.0F);
					this.setFired(true, target.getEntityId());
					this.cooldown = 10;
				}
				if (TragicConfig.getBoolean("allowMobSounds") && !this.worldObj.isRemote) this.worldObj.playSoundAtEntity(this, "tragicmc:boss.aegar.laser", 0.4F, 1.8F);
			}
		}
	}

	@Override
	public boolean canAttack() {
		return !this.isDead && this.cooldown == 0 && this.getThrustTicks() == 0;
	}

	@Override
	public void onRiderChange() {

		if (this.previousRider instanceof EntityCreature && !(this.riddenByEntity instanceof EntityCreature))
		{
			this.tasks.removeTask(attackOnCollide);
			this.tasks.removeTask(moveTowardsTarget);
			this.targetTasks.removeTask(hurtByTarget);
			TragicMC.logInfo("removed tasks");
		}
		else if (!(this.previousRider instanceof EntityCreature) && this.riddenByEntity instanceof EntityCreature)
		{
			this.tasks.addTask(0, attackOnCollide);
			this.tasks.addTask(1, moveTowardsTarget);
			this.targetTasks.addTask(2, hurtByTarget);
			TragicMC.logInfo("added tasks");
		}
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn)
    {
		super.attackEntityAsMob(entityIn);
		if (this.riddenByEntity instanceof EntityLiving && entityIn instanceof EntityLivingBase) ((EntityLiving) this.riddenByEntity).setAttackTarget((EntityLivingBase) entityIn);
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F);
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource src, float damage)
	{
		if (src.getEntity() != null && src.getEntity() == this.ridingEntity) return false;
		return super.attackEntityFrom(src, damage);
		
	}

}
