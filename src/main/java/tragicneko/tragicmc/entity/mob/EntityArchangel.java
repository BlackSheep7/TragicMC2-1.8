package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.archangelStats;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityArchangel extends TragicMob {
	
	public static final int DW_HOVER_TICKS = 20;
	public static final int DW_TARGET_ID = 21;

	public int hoverBuffer = 120;
	public double wayDifference = 0;
	public double prevWaypoint = 0;

	public EntityArchangel(World par1World) {
		super(par1World);
		this.setSize(1.725F, 1.625F);
		this.moveHelper = new EntityArchangel.FlyingMoveHelper(this);
		this.isImmuneToFire = true;
		this.experienceValue = 100;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, false, EntityIre.nonLightEntityTarget));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(archangelStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(archangelStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(archangelStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(archangelStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(archangelStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return (int) archangelStats[5];
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	@Override
	protected boolean canCorrupt()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return 15728880;
	}

	@Override
	public float getBrightness(float par1)
	{
		return 1.0F;
	}

	@Override
	public boolean canRenderOnFire()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(DW_HOVER_TICKS, Integer.valueOf(0)); //hover/laser ticks
		this.dataWatcher.addObject(DW_TARGET_ID, Integer.valueOf(0)); //target id
	}

	private void setHoverTicks(int i)
	{
		this.dataWatcher.updateObject(DW_HOVER_TICKS, i);
	}

	public int getHoverTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_HOVER_TICKS);
	}

	private void setTargetId(int i)
	{
		this.dataWatcher.updateObject(DW_TARGET_ID, i);
	}

	public int getTargetId()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_TARGET_ID);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (result)
		{
			if (this.getHoverTicks() > 0)
			{
				this.setHoverTicks(0);
				this.hoverBuffer = 120;
			}
		}

		return result;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (!this.worldObj.isRemote) par1Entity.setFire(4 + rand.nextInt(4));
		return super.attackEntityAsMob(par1Entity);
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getHoverTicks() > 0) this.motionX = this.motionY = this.motionZ = 0;

		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			Entity entity = this.worldObj.getEntityByID(this.getTargetId());
			if (entity != null && this.getHoverTicks() > 10)
			{
				double d0 = entity.posX - this.posX;
				double d1 = entity.posY - this.posY;
				double d2 = entity.posZ - this.posZ;

				for (byte l = 0; l < 4; l++)
				{
					double d3 = 0.23D * l + (rand.nextDouble() * 0.25D);
					this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX + d0 * d3, this.posY + d1 * d3 + 0.75D, this.posZ + d2 * d3, 0.0, 0.0, 0.0);
					if (this.getHoverTicks() <= 120) this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX + d0 * d3, this.posY + d1 * d3 + 0.75D, this.posZ + d2 * d3, 0.0, 0.0, 0.0);
				}

				if (this.getHoverTicks() <= 120)
				{
					for (byte i = 0; i < 4; i++)
					{
						this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX + rand.nextDouble() - rand.nextDouble(),
								this.posY + 0.75 + rand.nextDouble() * 0.5, this.posZ + rand.nextDouble() - rand.nextDouble(), 0.0, 0.0, 0.0);
					}
				}
			}

			if (this.ticksExisted % 5 == 0 && rand.nextBoolean())
			{
				for (byte l = 0; l < 2; ++l)
				{
					this.worldObj.spawnParticle(EnumParticleTypes.CRIT,
							this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
							this.posY + this.rand.nextDouble() * this.height,
							this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
							(this.rand.nextDouble() - 0.6D) * 0.1D,
							this.rand.nextDouble() * 0.1D,
							(this.rand.nextDouble() - 0.6D) * 0.1D);
				}
			}

			return;
		}

		if (this.hoverBuffer > 0) this.hoverBuffer--;

		if (this.getHoverTicks() <= 0)
		{
			double d0 = this.getMoveHelper().getX() - this.posX;
			double d1 = this.getMoveHelper().getY() - this.posY;
			double d2 = this.getMoveHelper().getZ() - this.posZ;
			double d3 = d0 * d0 + d1 * d1 + d2 * d2;

			if (!Double.isNaN(d3))
			{
				this.wayDifference = Math.abs(d3 - this.prevWaypoint);
				this.prevWaypoint = d3;
			}

			if (d3 < 1.0D || d3 > 400.0D || this.wayDifference < 10)
			{
				if (this.getAttackTarget() != null)
				{
					d0 = this.getAttackTarget().posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 8.0F;
					d1 = this.getAttackTarget().posY + (this.rand.nextFloat() * 2.0F - 1.0F) * 8.0F;
					d2 = this.getAttackTarget().posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 8.0F;
				}
				else
				{
					d0 = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
					d1 = this.posY + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
					d2 = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;

					int y = WorldHelper.getDistanceToGround(this);
					if (d1 - y >= 20) d1 -= y / 2;
				}
				
				this.getMoveHelper().setMoveTo(d0, d1, d2, 1.0);
			}

			double d4 = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();

			if (this.getAttackTarget() != null && this.getAttackTarget().getDistanceSqToEntity(this) < d4 * d4 && this.getAttackTarget().getDistanceToEntity(this) > 6.0F && this.getHoverTicks() == 0 && rand.nextInt(16) == 0)
			{
				if (this.canEntityBeSeen(this.getAttackTarget()) && this.hoverBuffer == 0) this.setHoverTicks(250);
			}
			
			if (WorldHelper.getDistanceToGround(this) <= 1.5) this.motionY = 0.1D;
		}
		else
		{
			this.setHoverTicks(this.getHoverTicks() - 1);
			this.hoverBuffer = 80 + (int) (120 * (this.getHealth() / this.getMaxHealth()));

			if (this.getHoverTicks() > 10 && this.getHoverTicks() <= 120 && this.getAttackTarget() != null && this.canEntityBeSeen(this.getAttackTarget()))
			{
				this.getAttackTarget().attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(this), (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
			}

			if (this.getHoverTicks() % 15 == 0 && TragicConfig.allowMobSounds)
			{
				this.playSound("tragicmc:mob.archangel.low", 0.4F, 1.0F);
				if (this.getAttackTarget() != null) this.worldObj.playSoundAtEntity(this.getAttackTarget(), "tragicmc:mob.archangel.low", 0.4F, 1.0F);
			}

			if (this.getAttackTarget() == null || this.getAttackTarget().isDead || !this.canEntityBeSeen(this.getAttackTarget())) this.setHoverTicks(0);
		}

		if (this.getAttackTarget() != null && this.getAttackTarget().isDead) this.setAttackTarget(null);

		if (this.getAttackTarget() != null)
		{
			this.setTargetId(this.getAttackTarget().getEntityId());
		}
		else
		{
			this.setTargetId(0);
		}
		
		if (this.getAttackTarget() != null)
		{
			double d5 = this.getAttackTarget().posX - this.posX;
			double d7 = this.getAttackTarget().posZ - this.posZ;
			this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0F / (float)Math.PI;
		}
		else
		{
			this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
		}
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public void fall(float dist, float multi) {}

	@Override
	public void updateFallState(double par1, boolean par2, Block block, BlockPos pos) {}
	
	@Override
	public void moveEntityWithHeading(float strafe, float forward)
    {
        if (this.isInWater())
        {
            this.moveFlying(strafe, forward, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
        else if (this.isInLava())
        {
            this.moveFlying(strafe, forward, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }
        else
        {
            float f = 0.91F;

            if (this.onGround)
            {
                f = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ))).getBlock().slipperiness * 0.91F;
            }

            float f1 = 0.16277136F / (f * f * f);
            this.moveFlying(strafe, forward, this.onGround ? 0.1F * f1 : 0.02F);
            f = 0.91F;

            if (this.onGround)
            {
                f = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ))).getBlock().slipperiness * 0.91F;
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)f;
            this.motionY *= (double)f;
            this.motionZ *= (double)f;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d1 = this.posX - this.prevPosX;
        double d0 = this.posZ - this.prevPosZ;
        float f2 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

	@Override
	public boolean isOnLadder()
	{
		return false;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("hoverBuffer")) this.hoverBuffer = tag.getInteger("hoverBuffer");
		if (tag.hasKey("hoverTicks")) this.setHoverTicks(tag.getInteger("hoverTicks"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("hoverBuffer", this.hoverBuffer);
		tag.setInteger("hoverTicks", this.getHoverTicks());
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.allowMobSounds ? "tragicmc:mob.archangel.choir" : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.allowMobSounds ? "tragicmc:mob.archangel.vibrato" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.allowMobSounds ? "tragicmc:mob.archangel.triple" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return 1.0F;
	}

	@Override
	public float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		
	}
	
	@Override
	public boolean getIllumination() {
		return true;
	}
	
	public static class FlyingMoveHelper extends EntityMoveHelper
    {
        private EntityLiving parentEntity;
        private int courseChangeCooldown;

        public FlyingMoveHelper(EntityLiving parent)
        {
            super(parent);
            this.parentEntity = parent;
        }

        public void onUpdateMoveHelper()
        {
            if (this.update)
            {
                if (this.courseChangeCooldown-- <= 0)
                {
                	double d0 = this.posX - this.parentEntity.posX;
                    double d1 = this.posY - this.parentEntity.posY;
                    double d2 = this.posZ - this.parentEntity.posZ;
                    double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                    this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                    d3 = (double)MathHelper.sqrt_double(d3);

                    if (this.isNotColliding(this.posX, this.posY, this.posZ, d3))
                    {
                        this.parentEntity.motionX += d0 / d3 * 0.1D;
                        this.parentEntity.motionY += d1 / d3 * 0.1D;
                        this.parentEntity.motionZ += d2 / d3 * 0.1D;
                    }
                    else
                    {
                        this.update = false;
                    }
                }
            }
        }
        
        private boolean isNotColliding(double wayX, double wayY, double wayZ, double dist)
        {
            double d0 = (wayX - this.parentEntity.posX) / dist;
            double d1 = (wayY - this.parentEntity.posY) / dist;
            double d2 = (wayZ - this.parentEntity.posZ) / dist;
            AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();

            for (int i = 1; (double)i < dist; ++i)
            {
                axisalignedbb = axisalignedbb.offset(d0, d1, d2);

                if (!this.parentEntity.worldObj.getCollidingBoundingBoxes(this.parentEntity, axisalignedbb).isEmpty())
                {
                    return false;
                }
            }

            return true;
        }
    }
}
