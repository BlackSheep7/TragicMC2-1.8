package tragicneko.tragicmc.entity.mob;

import org.apache.commons.lang3.math.NumberUtils;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityJetNeko extends EntityNeko {

	public static final int DW_HOVER_TICKS = 25;

	public int hoverBuffer = 120;
	public double wayDifference = 0;
	public double prevWaypoint = 0;

	public EntityJetNeko(World par1World) {
		super(par1World);
		this.moveHelper = new EntityArchangel.FlyingMoveHelper(this);
		this.setSize(0.675F * 0.725F, 1.955F * 0.725F);
		this.experienceValue = 100;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DW_HOVER_TICKS, Integer.valueOf(0));
	}

	protected void setHoverTicks(int i)
	{
		this.dataWatcher.updateObject(DW_HOVER_TICKS, i);
	}

	public int getHoverTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_HOVER_TICKS);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] jetNekoStats = TragicConfig.getMobStat("jetNekoStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(jetNekoStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(jetNekoStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(jetNekoStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(jetNekoStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(jetNekoStats[4]);
	}

	@Override
	public int getTotalArmorValue() {
		return TragicConfig.getMobStat("jetNekoStats").getArmorValue();
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getHoverTicks() > 0) this.motionX = this.motionY = this.motionZ = 0;
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			if (this.getHoverTicks() == 0)
			{
				this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX - this.motionX, this.posY - 0.125 - this.motionY, this.posZ - this.motionZ, (rand.nextDouble() - rand.nextDouble()) * 0.125, (rand.nextDouble() - rand.nextDouble()) * 0.125, (rand.nextDouble() - rand.nextDouble()) * 0.125);
			}
			else
			{
				this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY - 0.125, this.posZ, 0.0, -0.1D - 0.25 * rand.nextDouble(), 0.0);
			}
			return;
		}

		if (this.getAttackTarget() != null && !this.isProperDate())
		{
			if (this.getFlickTime() > 0) this.setFlickTime(0);

			if ((this.getFiringTicks() == 40 || this.getFiringTicks() == 20) && this.canEntityBeSeen(this.getAttackTarget()))
			{
				if (TragicConfig.getBoolean("jetNekoRockets")) this.doMissleAttack();
			}
			else if (this.hasFired() && this.getFiringTicks() % 40 == 0 && this.getAttackTime() == 0 && rand.nextInt(8) == 0 && this.getDistanceToEntity(this.getAttackTarget()) <= 6.0)
			{
				if (this.getHoverTicks() > 0 && rand.nextInt(4) == 0 && TragicConfig.getBoolean("jetNekoHovering")) this.setHoverTicks(0);
			}

			if (rand.nextInt(4) == 0 && this.canFire() && this.ticksExisted % 5 == 0 && this.getAttackTime() == 0 && this.getDistanceToEntity(this.getAttackTarget()) >= 6.0F)
			{
				this.setFiringTicks(0);
			}
		}

		if (this.hoverBuffer > 0) this.hoverBuffer--;

		if (this.getHoverTicks() <= 0)
		{
			double d0 = this.getMoveHelper().getX() - this.posX;
			double d1 = this.getMoveHelper().getY() - this.posY;
			double d2 = this.getMoveHelper().getZ() - this.posZ;
			double d3 = d0 * d0 + d1 * d1 + d2 * d2;
			this.wayDifference = Math.abs(d3 - this.prevWaypoint);
			this.prevWaypoint = d3;

			if (d3 < 1.0D || d3 > 300.0D || this.wayDifference < 1)
			{
				if (this.getAttackTarget() != null)
				{
					d0 = this.getAttackTarget().posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
					d1 = this.getAttackTarget().posY + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
					d2 = this.getAttackTarget().posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
				}
				else
				{
					d0 = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 48.0F;
					d1 = this.posY + (this.rand.nextFloat() * 2.0F - 1.0F) * 8.0F;
					d2 = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 48.0F;

					int y = WorldHelper.getDistanceToGround(this);
					if (d1 - y >= 10) d1 -= y / 2;
				}

				this.getMoveHelper().setMoveTo(d0, d1, d2, 1.0);
			}

			double d4 = this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();

			if (this.getAttackTarget() != null && this.getAttackTarget().getDistanceToEntity(this) < d4 && this.getAttackTarget().getDistanceToEntity(this) > 4.0F && this.getHoverTicks() == 0)
			{
				if (this.canEntityBeSeen(this.getAttackTarget()) && rand.nextInt(8) == 0 && this.hoverBuffer == 0 && !this.onGround) this.setHoverTicks(150);
			}

			if (WorldHelper.getDistanceToGround(this) <= 1.5) this.motionY = 0.1D;
			if (this.onGround) this.jump();
		}
		else
		{
			this.setHoverTicks(this.getHoverTicks() - 1);
			this.hoverBuffer = 80 + (int) (120 * (this.getHealth() / this.getMaxHealth()));
			if (this.getAttackTarget() == null || this.getAttackTarget().isDead || !this.canEntityBeSeen(this.getAttackTarget()) || this.onGround) this.setHoverTicks(0);
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
		if (f2 > 1.0F) f2 = 1.0F;

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
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.jetneko.living" : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") && rand.nextInt(6) == 0 ? "tragicmc:mob.jetneko.hurt" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.jetneko.death" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return 1.0F;
	}

	@Override
	public float getSoundVolume()
	{
		return 0.65F;
	}

	@Override
	public int getTalkInterval()
	{
		return 320 + rand.nextInt(120);
	}

	@Override
	public int getDropAmount()
	{
		return 5;
	}
}
