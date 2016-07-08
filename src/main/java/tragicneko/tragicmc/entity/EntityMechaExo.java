package tragicneko.tragicmc.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
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
	public static final int DW_ATTACK_TIME = 23;

	public final EntityAIBase attackOnCollide = new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true);
	public final EntityAIBase moveTowardsTarget = new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F);
	public final EntityAIBase hurtByTarget = new EntityAIHurtByTarget(this, true);
	public final EntityAIBase wander = new EntityAIWander(this, 0.65D);

	public EntityMechaExo(World par1World) {
		super(par1World);
		this.setSize(2.45F, 2.45F);
		this.stepHeight = 1.5F;
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DW_TARGET_ID, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_HAS_FIRED, (byte) 0);
		this.dataWatcher.addObject(DW_THRUST_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_ATTACK_TIME, Integer.valueOf(0));
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
	
	public int getAttackTime()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_ATTACK_TIME);
	}

	public void setAttackTime(int i)
	{
		this.dataWatcher.updateObject(DW_ATTACK_TIME, i);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] mechaExoStats = TragicConfig.getMobStat("mechaExoStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(mechaExoStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(mechaExoStats[1]);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(mechaExoStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(mechaExoStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(mechaExoStats[4]);
	}

	@Override
	public int getTotalArmorValue() {
		return TragicConfig.getMobStat("mechaExoStats").getArmorValue();
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
				double d1 = entity.posY - this.posY - this.height + 0.225;
				double d2 = entity.posZ - this.posZ;

				for (byte l = 0; l < 8; l++)
				{
					double d3 = 0.123D * l + (rand.nextDouble() * 0.125D);
					this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX + d0 * d3, this.posY + d1 * d3 + this.height + 0.225, this.posZ + d2 * d3, 0.0, 0.0, 0.0);
				}
			}

			if (this.getThrustTicks() > 0)
			{
				for (byte l = 0; l < 10; l++)
				{
					this.worldObj.spawnParticle(EnumParticleTypes.CLOUD, this.posX - this.motionX, this.posY + this.height - this.motionY, this.posZ - this.motionZ, 0.0, 0.0, 0.0);
				}
				
				for (byte l = 0; l < 5; l++)
				{
					this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + this.height, this.posZ, 0.0, 0.0, 0.0);
				}
			}
			return;
		}

		if (this.hasFired() && cooldown <= 9) this.setFired(false, 0);
		if (cooldown > 0) cooldown--;
		if (this.getAttackTime() > 0) this.setAttackTime(this.getAttackTime() - 1);

		if (this.getThrustTicks() > 0 && this.riddenByEntity != null)
		{
			this.setThrustTicks(this.getThrustTicks() - 1);
			this.fallDistance = 0F; //reset fall distance as it's not technically falling while thrusting forward

			if (this.riddenByEntity instanceof EntityPlayer)
			{
				Vec3 vec = WorldHelper.getVecFromEntity(this.riddenByEntity, 1.5);
				if (vec != null)
				{
					double d0 = vec.xCoord - this.posX;
					double d2 = vec.zCoord - this.posZ;
					this.motionX = d0;
					this.motionZ = d2;
					this.motionY = 0.05D;
					this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
				}
			}
			else if (this.getAttackTarget() != null)
			{
				Vec3 vec = WorldHelper.getVecFromEntity(this, 1.5);
				if (vec != null)
				{
					double d0 = vec.xCoord - this.posX;
					double d2 = vec.zCoord - this.posZ;
					this.motionX = d0;
					this.motionZ = d2;
					this.motionY = this.motionY * 1.1D;
					this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
				}
			}

			List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(2.0, 1.0, 2.0));
			for (int i = 0; i < list.size(); i++)
			{
				EntityLivingBase e = list.get(i);
				if (e != this.riddenByEntity && e != this && this.riddenByEntity instanceof EntityLivingBase && !(e instanceof EntityRidable))
				{
					this.attackEntityAsMob(e);
				}
			}
			
			if (this.getAttackTarget() != null)
			{
				if (this.getAttackTarget().isInsideOfMaterial(Material.water) && this.getDistanceToEntity(this.getAttackTarget()) > 6.0F)
				{
					if (this.riddenByEntity instanceof EntityLiving) ((EntityLiving) this.riddenByEntity).setAttackTarget(null);
				}
			}

			this.setSprinting(true);
		}
		else if (this.getThrustTicks() <= 0)
		{
			this.setSprinting(false);
		}
		
		if (this.getAttackTarget() == this.riddenByEntity || this.getAttackTarget() != null && this.getAttackTarget().isDead) this.setAttackTarget(null);
		
		if (this.getHealth() < this.getMaxHealth() / 3 && this.ticksExisted % 20 == 0)
		{
			this.worldObj.playSoundAtEntity(this, "tragicmc:random.beep", 0.2F, 1.6F);
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
					
					if (d > 0) 
					{
						MovingObjectPosition mop = WorldHelper.getMOPFromEntity(entity, d);
						if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) break meow;
					}
					
					bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D).expand(box, box, box).offset(vec31.xCoord, vec31.yCoord + entity.getEyeHeight(), vec31.zCoord);
					List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(entity, bb);		

					if (list.isEmpty()) continue;

					for (Entity e : list)
					{			
						if (e == this || e == this.ridingEntity) continue;
						
						if (e instanceof IMultiPart)
						{

							((IMultiPart) e).getDefaultPart().attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(entity), (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
							if (TragicConfig.getBoolean("allowMobSounds")) this.worldObj.playSoundAtEntity(e, "tragicmc:boss.aegar.laser", 0.4F, 1.8F);
							this.setFired(true, e.getEntityId());
							cooldown = 10;
							break meow;
						}

						if (!(e instanceof EntityItem) && !(e instanceof EntityXPOrb) && !(e instanceof EntityArrow) && e != this && e != this.riddenByEntity)
						{

							e.attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(entity), (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
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
				if (TragicConfig.getBoolean("allowMobSounds") && !this.worldObj.isRemote) this.worldObj.playSoundAtEntity(this, "tragicmc:mob.mechaexo.thruster", 1.8F, 1.0F);
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
				rocket.posY = this.posY + this.getEyeHeight() - 0.45F;
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
				this.setThrustTicks(15);
				if (TragicConfig.getBoolean("allowMobSounds") && !this.worldObj.isRemote) this.worldObj.playSoundAtEntity(this, "tragicmc:mob.mechaexo.thruster", 1.8F, 1.0F);
			}
			else
			{
				if (this.canEntityBeSeen(target))
				{
					target.attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(this), (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
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
			this.tasks.removeTask(wander);
		}
		else if (!(this.previousRider instanceof EntityCreature) && this.riddenByEntity instanceof EntityCreature)
		{
			this.tasks.addTask(0, attackOnCollide);
			this.tasks.addTask(1, moveTowardsTarget);
			this.tasks.addTask(6, wander);
			this.targetTasks.addTask(2, hurtByTarget);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		if (this.riddenByEntity == entityIn) return false;
		if (this.getAttackTime() > 0) return false;
		if (this.riddenByEntity instanceof EntityLiving && entityIn instanceof EntityLivingBase && ((EntityLiving) this.riddenByEntity).getAttackTarget() == null) ((EntityLiving) this.riddenByEntity).setAttackTarget((EntityLivingBase) entityIn);
		if (this.getDistanceToEntity(entityIn) > 4.5) return false;
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());

		if (flag)
		{
			entityIn.motionX += this.motionX * 2;
			entityIn.motionZ += this.motionZ * 2;
			entityIn.motionY += 0.45D;
			this.setAttackTime(10);
		}
		return flag;
	}

	@Override
	public boolean attackEntityFrom(DamageSource src, float damage)
	{
		if (src == DamageSource.fall) return false;
		if (src.getEntity() != null && src.getEntity() == this.riddenByEntity) return false;
		if (src.getEntity() instanceof EntityLiving && this.riddenByEntity == null) ((EntityLiving) src.getEntity()).setAttackTarget(null);
		if (src.getEntity() != this.getAttackTarget() && rand.nextInt(8) == 0 && this.riddenByEntity instanceof EntityLiving && src.getEntity() instanceof EntityLivingBase) ((EntityLiving) this.riddenByEntity).setAttackTarget((EntityLivingBase) src.getEntity());
		return super.attackEntityFrom(src, damage);
	}
	
	@Override
	public boolean interact(EntityPlayer player) {
		if (player.getCurrentEquippedItem() != null && this.getHealth() > 0F && this.riddenByEntity == null)
		{
			Item item = player.getCurrentEquippedItem().getItem();
			if (item == TragicItems.Wrench || item.getRegistryName() != null &&
					(item.getRegistryName().contains("wrench") || item.getRegistryName().contains("Wrench")))
			{
				if (!this.worldObj.isRemote && TragicConfig.getBoolean("allowNonMobItems")) this.dropExoItem();
				else this.spawnExplosionParticle();
				this.setDead();
				return true;
			}
		}
		return super.interact(player);
		
	}

	private void dropExoItem() {
		ItemStack stack = new ItemStack(TragicItems.MechaExo, 1, MathHelper.ceiling_float_int(this.getMaxHealth() - this.getHealth()));
		EntityItem item = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, stack);
		this.worldObj.spawnEntityInWorld(item);
	}
	
	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		this.playSound(block.stepSound.getStepSound(), block.stepSound.getVolume() * 0.75F, block.stepSound.getFrequency());
	}
	
	@Override
	public String getLivingSound()
	{
		return null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.exo.wrecked" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.exo.hit" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return 0.75F + rand.nextFloat() * 0.5F;
	}

	@Override
	public float getSoundVolume()
	{
		return 0.9F + rand.nextFloat() * 0.2F;
	}
	
	@Override
	public void addPotionEffect(PotionEffect effect) {}
}
