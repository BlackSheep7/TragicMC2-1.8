package tragicneko.tragicmc.entity.projectile;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.EntityRidable;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityNekoRocket extends EntityProjectile {

	public EntityLivingBase target = null;
	private int ticksWithTarget;

	public EntityNekoRocket(World par1World) {
		super(par1World);
		this.setSize(1.0F, 1.0F);
	}

	public EntityNekoRocket(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7)
	{
		super(par1World, par2EntityLivingBase, par3, par5, par7);
	}

	@Override
	public float getMotionFactor()
	{
		return 0.872F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (this.worldObj.isRemote) return;

		if (mop.entityHit != null)
		{
			if (mop.entityHit == this.shootingEntity || mop.entityHit instanceof EntityProjectile) return;
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 5.0F);
		}

		boolean flag = this.worldObj.getGameRules().getBoolean("mobGriefing");
		this.worldObj.createExplosion(this.shootingEntity, this.posX, this.posY, this.posZ, rand.nextFloat() * 0.5F + 2.5F, flag);

		this.setDead();
	}

	@Override
	public void onUpdate()
	{
		if (this.target != null && (this.target.getHealth() == 0 || this.target.isDead)) this.target = null;
		if (this.target != null && this.ticksInAir > 2) this.ticksWithTarget++;

		if (this.ticksWithTarget > 70 || this.ticksInAir > 100 || this.target != null && this.getDistanceToEntity(this.target) <= 2.5)
		{
			boolean flag = this.worldObj.getGameRules().getBoolean("mobGriefing");

			if (!this.worldObj.isRemote)
			{
				this.worldObj.createExplosion(this.shootingEntity, this.posX, this.posY, this.posZ, rand.nextFloat() * 0.5F + 2.5F, flag);
				this.setDead();
			}
		}

		super.onUpdate();

		if (this.target == null && this.ticksInAir > 3)
		{
			if (this.shootingEntity != null && this.shootingEntity instanceof EntityLiving)
			{
				this.target = this.shootingEntity.getAITarget();
			}
			else
			{
				List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(16.0, 16.0, 16.0));

				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i) != this.shootingEntity && list.get(i) instanceof EntityLivingBase && this.shootingEntity != null && this.shootingEntity.canEntityBeSeen(list.get(i)) &&
							list.get(i) != this.shootingEntity.ridingEntity && list.get(i) != this.shootingEntity.riddenByEntity && !(list.get(i) instanceof EntityRidable) &&
							!(list.get(i) instanceof EntityPlayer && ((EntityPlayer) list.get(i)).capabilities.isCreativeMode) && !list.get(i).isDead)
					{
						if (!((EntityLivingBase) list.get(i)).canEntityBeSeen(this)) continue;
						if (this.target == null || this.getDistanceToEntity(list.get(i)) < this.getDistanceToEntity(this.target)) this.target = (EntityLivingBase) list.get(i);
					}
				}
			}
		}

		if (this.target != null && this.ticksInAir > 8 && this.ticksExisted % 2 == 0)
		{
			boolean flag = true;
			float f = this.getDistanceToEntity(this.target);
			if ((this.ticksWithTarget > 30 || this.ticksWithTarget > f * 10) && f > 3.0) flag = false;

			if (flag)
			{				
				double limit = 0.23;
				double mx = (target.posX - this.posX + target.width / 2) * 0.068;
				boolean mxf = Math.abs(mx) > 0 && Math.abs(mx) > limit;
				this.motionX += mxf ? (mx > 0 ? limit : -limit) : mx;

				double my = (target.posY - this.posY + (target.height * 2 / 3)) * 0.068;
				boolean myf = Math.abs(my) > 0 && Math.abs(my) > limit;
				this.motionY += myf ? (my > 0 ? limit : -limit) : my;

				double mz = (target.posZ - this.posZ + target.width / 2) * 0.068;
				boolean mzf = Math.abs(mz) > 0 && Math.abs(mz) > limit;
				this.motionZ += mzf ? (mz > 0 ? limit : -limit) : mz;
			}
		}

		if (this.isInWater())
		{
			if (this.worldObj.isRemote)
			{
				for (byte l = 0; l < 5; ++l) {
					worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
				}
				this.worldObj.playSoundAtEntity(this, "random.fizz", 0.3F, 0.4F);
			}
			else
			{
				this.setDead();
			}
		}
		else
		{
			if (!this.worldObj.isRemote && this.ticksExisted % 5 == 1)
			{
				this.worldObj.playSoundAtEntity(this, "tragicmc:random.rocketflying", 0.4F, 1.6F);
			}
		}
	}

}
