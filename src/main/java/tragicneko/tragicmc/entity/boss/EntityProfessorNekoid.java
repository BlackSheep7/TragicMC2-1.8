package tragicneko.tragicmc.entity.boss;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.EntityMechaExo;
import tragicneko.tragicmc.entity.mob.EntityAssaultNeko;
import tragicneko.tragicmc.entity.mob.EntityJetNeko;
import tragicneko.tragicmc.entity.mob.EntityNeko;
import tragicneko.tragicmc.entity.mob.EntityScienceNeko;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityProfessorNekoid extends TragicBoss {

	public boolean hasLostMecha = false;
	public int mechaUseTicks = 100;
	public int blasterUseTicks = 60;

	public EntityProfessorNekoid(World par1World) {
		super(par1World);
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] professorNekoidStats = TragicConfig.getMobStat("professorNekoidStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(professorNekoidStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(professorNekoidStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(professorNekoidStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(professorNekoidStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(professorNekoidStats[4]);
	}

	@Override
	public int getTotalArmorValue() {
		return TragicConfig.getMobStat("professorNekoidStats").getArmorValue();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			return;
		}

		this.mechaUseTicks--;
		this.blasterUseTicks--;

		if (this.hasLostMecha && this.ridingEntity == null)
		{
			List<EntityMechaExo> list = this.worldObj.getEntitiesWithinAABB(EntityMechaExo.class, this.getEntityBoundingBox().expand(4.0, 4.0, 4.0));
			for (EntityMechaExo e: list)
			{
				if (e.riddenByEntity != null) continue;
				this.mountEntity(e);
				break;
			}
		}

		if (!this.hasLostMecha && (this.ridingEntity == null || this.ridingEntity.isDead && this.ridingEntity instanceof EntityMechaExo))
		{
			this.hasLostMecha = true;
		}

		if (this.ridingEntity != null && this.ridingEntity instanceof EntityMechaExo && this.getAttackTarget() != null && this.mechaUseTicks == 0)
		{
			EntityMechaExo exo = (EntityMechaExo) this.ridingEntity;
			final boolean flag = rand.nextBoolean();
			exo.useAttackViaMob(flag ? 1 : 0, this.getAttackTarget());
			this.mechaUseTicks = flag ? 40 : 80;
		}

		if (this.blasterUseTicks == 0 && this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) <= 8.0F)
		{
			MovingObjectPosition mop = WorldHelper.getMOPFromEntity(this, 16.0);

			float f1 = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch);
			float f2 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw);
			Vec3 vec3 = new Vec3(this.posX, this.posY, this.posZ);
			float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
			float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
			float f5 = -MathHelper.cos(-f1 * 0.017453292F);
			float f6 = MathHelper.sin(-f1 * 0.017453292F);
			float f7 = f4 * f5;
			float f8 = f3 * f5;
			double box = 6.0D;

			AxisAlignedBB bb;

			meow: for (double d = 0.0D; d <= 15.0; d += 0.5D)
			{
				Vec3 vec31 = vec3.addVector(f7 * d, f6 * d, f8 * d);

				if (d > 0) 
				{
					mop = WorldHelper.getMOPFromEntity(this, d);
					if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) break meow;
				}

				bb = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D).expand(box, box, box).offset(vec31.xCoord, vec31.yCoord + this.getEyeHeight(), vec31.zCoord);
				List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, bb);		

				if (list.isEmpty()) continue;

				boolean flag = false;

				for (Entity e : list)
				{		
					if (!(e instanceof EntityItem) && !(e instanceof EntityXPOrb) && !(e instanceof EntityArrow) && e != this && e != this.ridingEntity)
					{
						flag = true;
						float f = this.getDistanceToEntity(e) / 23.0F;
						if (f <= 0) f = 0.1F;
						MovingObjectPosition mop2 = WorldHelper.getMOPFromEntity(this, 1 / f);

						double x = (mop2.hitVec.xCoord - this.posX);
						double y = (mop2.hitVec.yCoord - this.posY - this.getEyeHeight());
						double z = (mop2.hitVec.zCoord - this.posZ);
						e.addVelocity(x, y, z);
						e.attackEntityFrom(DamageSource.fall, 1.0F);
					}
				}

				if (flag) break;
			}

			this.blasterUseTicks = 120;
		}

		if (this.ticksExisted % 20 == 0 && this.getAttackTarget() != null)
		{
			double d = 16.0 + ((1.0 / (this.getHealth() / this.getMaxHealth())) * 48.0);
			List<EntityNeko> list = this.worldObj.getEntitiesWithinAABB(EntityNeko.class, this.getEntityBoundingBox().expand(d, d, d));

			for (EntityNeko e : list)
			{
				if (e.isReleased()) continue;
				if (e.getAttackTarget() == null) e.setAttackTarget(this.getAttackTarget());
			}

			if (list.size() < 12 && this.getHealth() < this.getMaxHealth() / 2.0F && this.ticksExisted % 60 == 0)
			{
				EntityNeko neko = new EntityTragicNeko(this.worldObj);
				if (rand.nextInt(4) == 0)
				{
					if (rand.nextInt(4) == 0)
					{
						neko = new EntityJetNeko(this.worldObj);
					}
					else if (rand.nextBoolean())
					{
						neko = new EntityScienceNeko(this.worldObj);
					}
					else
					{
						neko = new EntityAssaultNeko(this.worldObj);
					}
				}
				neko.copyLocationAndAnglesFrom(this);
				EntityLivingBase entitylivingbase = this.getAttackTarget();

				meow: for (int y1 = -4; y1 < 5; y1++)
				{
					for (int z1 = -3; z1 < 4; z1++)
					{
						for (int x1 = -3; x1 < 4; x1++)
						{
							if (World.doesBlockHaveSolidTopSurface(this.worldObj, new BlockPos((int) this.posX + x1, (int) this.posY + y1 - 1, (int) this.posZ + z1)) && rand.nextBoolean())
							{
								neko.setPosition(this.posX + x1, this.posY + y1, this.posZ + z1);

								if (this.worldObj.checkNoEntityCollision(neko.getEntityBoundingBox()) &&
										this.worldObj.getCollidingBoundingBoxes(neko, neko.getEntityBoundingBox()).isEmpty() &&
										!this.worldObj.isAnyLiquid(neko.getEntityBoundingBox()))
								{
									this.worldObj.spawnEntityInWorld(neko);
									neko.onInitialSpawn(this.worldObj.getDifficultyForLocation(new BlockPos(this.posX + x1, this.posY + y1, this.posZ + z1)), null);
									if (entitylivingbase != null) neko.setAttackTarget(entitylivingbase);
									break meow;
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (entity == this.ridingEntity) return false;
		return super.attackEntityAsMob(entity);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance ins, IEntityLivingData data)
	{
		if (!this.worldObj.isRemote && this.ridingEntity == null && TragicConfig.getBoolean("allowMechaExo"))
		{
			EntityMechaExo exo = new EntityMechaExo(this.worldObj);
			exo.setPositionAndUpdate(this.posX, this.posY, this.posZ);
			this.worldObj.spawnEntityInWorld(exo);
			this.mountEntity(exo);
		}
		return super.onInitialSpawn(ins, data);
	}

	@Override
	public void onDeath(DamageSource src)
	{
		super.onDeath(src);

		if (this.worldObj.isRemote) return;
		
		EntityPlayer player = (EntityPlayer) (src.getEntity() instanceof EntityPlayer ? src.getEntity() : null);

		double d = 64.0;
		List<EntityNeko> list = this.worldObj.getEntitiesWithinAABB(EntityNeko.class, this.getEntityBoundingBox().expand(d, d, d));

		for (EntityNeko e : list) {
			if (!e.isReleased()) e.releaseNeko(player);
		}
	}
}
