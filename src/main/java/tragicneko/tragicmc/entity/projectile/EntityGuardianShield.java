package tragicneko.tragicmc.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityGuardianShield extends EntityProjectile {

	public boolean motionFlag;

	private double xOffset;
	private double zOffset;

	private float maxHealth = 20.0F;
	private float health = maxHealth;
	
	private static final int DW_OWNER = 5;

	public EntityGuardianShield(World world) {
		super(world);
		this.motionFlag = false;
		this.preventEntitySpawning = true;
		this.setSize(2.0F, 2.0F);
	}

	public EntityGuardianShield(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
		super(par1World, par2EntityLivingBase, par3, par5, par7);
		if (par2EntityLivingBase != null) this.setOwnerID(par2EntityLivingBase.getEntityId());
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(DW_OWNER, Integer.valueOf(0));
	}

	private void setOwnerID(int i)
	{
		this.dataWatcher.updateObject(DW_OWNER, i);
		this.updateOwner();
	}

	public int getOwnerID()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_OWNER);
	}

	@Override
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	public void setCurrentItemOrArmor(int i, ItemStack stack) {

	}

	public EntityGuardianShield setShieldMaxHealth(float f)
	{
		this.maxHealth = f;
		this.setHealth(f);
		return this;
	}

	public void setHealth(float f)
	{
		this.health = f;
	}

	public void setShieldOffsets(double offx, double offz)
	{
		this.xOffset = offx;
		this.zOffset = offz;
	}

	@Override
	public boolean handleWaterMovement()
	{
		return false;
	}

	@Override
	public void setAir(int i) {}

	@Override
	public void fall(float dist, float multi) {}

	@Override
	public void updateFallState(double par1, boolean par2, Block block, BlockPos pos) {}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);

		if (tag.hasKey("ownerID")) this.setOwnerID(tag.getInteger("ownerID"));
		if (tag.hasKey("motionFlag")) this.motionFlag = tag.getBoolean("motionFlag");
		if (tag.hasKey("offsetX")) this.xOffset = tag.getDouble("offsetX");
		if (tag.hasKey("offsetZ")) this.zOffset = tag.getDouble("offsetZ");
		if (tag.hasKey("health")) this.health = tag.getFloat("health");
		if (tag.hasKey("healthMax")) this.maxHealth = tag.getFloat("maxHealth");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		if (this.shootingEntity != null) tag.setInteger("ownerID", this.getOwnerID());
		tag.setBoolean("motionFlag", this.motionFlag);
		tag.setDouble("offsetX", this.xOffset);
		tag.setDouble("offsetZ", this.zOffset);
		tag.setFloat("health", this.health);
		tag.setFloat("healthMax", this.maxHealth);
	}

	@Override
	public boolean attackEntityFrom(DamageSource src, float dmg)
	{
		if (src == DamageSource.inWall || src.isExplosion()) return false;

		if (!this.worldObj.isRemote && src.getEntity() != null && src.getEntity() == this.shootingEntity)
		{
			this.motionFlag = true;
			Vec3 vec3 = WorldHelper.getVecFromEntity(this.shootingEntity, 2.0D);

			if (vec3 != null)
			{
				this.rotationPitch = this.shootingEntity.rotationPitch;
				this.rotationYaw = this.shootingEntity.rotationYaw;
				this.motionX = vec3.xCoord - this.shootingEntity.posX;
				this.motionY = vec3.yCoord - this.shootingEntity.posY - this.shootingEntity.getEyeHeight();
				this.motionZ = vec3.zCoord - this.shootingEntity.posZ;
				this.accelerationX = this.accelerationY = this.accelerationZ = 0;
			}

			return false;
		}

		if (!this.worldObj.isRemote)
		{
			this.health -= dmg;
			if (this.health <= 0F) this.setDead();
		}

		return super.attackEntityFrom(src, dmg);
	}

	@Override
	public void onUpdate()
	{
		if (this.worldObj.isRemote)
		{
			for (int l = 0; l < 6; ++l)
			{
				this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.855D), this.posY + rand.nextDouble() - rand.nextDouble() + 0.235D,
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.855D), 0.155F * (this.rand.nextFloat() - this.rand.nextFloat()),
						0.155F * (this.rand.nextFloat() - this.rand.nextFloat()), 0.155F * (this.rand.nextFloat() - this.rand.nextFloat()));
			}
		}
		else
		{
			this.updateOwner();
			if (this.shootingEntity == null) this.setDead();

			if (!this.motionFlag && this.shootingEntity != null)
			{
				int i = (this.ticksExisted + this.getEntityId()) % 100;
				double d0 = (Math.sin(i * 0.23 + (i * 0.123))) + 1.8; 
				Vec3 vec3 = new Vec3(this.shootingEntity.posX, this.shootingEntity.posY,
						this.shootingEntity.posZ);
				
				this.prevPosX = this.posX;
				this.prevPosY = this.posY;
				this.prevPosZ = this.posZ;
				
				this.setPosition(vec3.xCoord + this.shootingEntity.motionX + this.xOffset,
						vec3.yCoord + d0 + this.shootingEntity.motionY,
						vec3.zCoord + this.shootingEntity.motionZ + this.zOffset);
			}
			else if (this.motionFlag)
			{
				super.onUpdate();
			}

			if (this.ticksExisted > 600 && !this.worldObj.isRemote)  this.setDead();
		}
	}

	private void updateOwner() {
		Entity entity = this.worldObj.getEntityByID(this.getOwnerID());
		if (entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getHealth() > 0F)
		{
			this.shootingEntity = (EntityLivingBase) entity;
		}
		else
		{
			this.shootingEntity = null;
		}
	}

	@Override
	protected void onImpact(MovingObjectPosition var1) {
		if (this.worldObj.isRemote || var1 == null) return;

		if (this.motionFlag && var1.typeOfHit != MovingObjectType.MISS)
		{
			if (var1.entityHit != null && var1.entityHit != this.shootingEntity)
			{
				if (var1.entityHit instanceof EntityGuardianShield) return;
				var1.entityHit.attackEntityFrom(this.shootingEntity != null ? DamageHelper.causeModMagicDamageToEntity(this.shootingEntity) : DamageSource.magic , this.maxHealth / 10.0F);
			}
			this.worldObj.createExplosion(this.shootingEntity != null ? this.shootingEntity : this, this.posX, this.posY, this.posZ, (this.maxHealth / 10.0F) * rand.nextFloat() + 1.0F, WorldHelper.getMobGriefing(this.worldObj));
			this.setDead();
		}
	}
	
	@Override
	public int getLifespan()
	{
		return 1200;
	}
	
	@Override
	public float getMotionFactor()
	{
		return 0.998F;
	}
}
