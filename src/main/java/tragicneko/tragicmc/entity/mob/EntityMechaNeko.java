package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.EntityMechaExo;
import tragicneko.tragicmc.entity.EntityRidable;

public class EntityMechaNeko extends EntityNeko {

	private int commandBuffer = 10;

	public EntityMechaNeko(World par1World) {
		super(par1World);
		this.setSize(0.475F, 1.955F);
		this.experienceValue = 100;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(75.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.27);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	@Override
	public int getTotalArmorValue() {
		return 6;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.getAttackTarget() == this.ridingEntity) this.setAttackTarget(null);

		if (this.ridingEntity instanceof EntityRidable && this.getAttackTarget() != null) 
		{
			EntityRidable er = (EntityRidable) this.ridingEntity;
			er.setAttackTarget(this.getAttackTarget());

			if (this.commandBuffer <= 0)
			{
				if (this.getDistanceToEntity(this.getAttackTarget()) >= 6 && this.canEntityBeSeen(this.getAttackTarget()))
				{
					if (er.canAttack())
					{
						final int i = rand.nextInt(2);
						er.useAttackViaMob(i, this.getAttackTarget());
						this.commandBuffer = i == 0 ? 80 : 20;
					}
				}
				else if (this.getDistanceToEntity(this.getAttackTarget()) < 5 && this.ticksExisted % 20 == 0) //charge at them if close enough to knock them back
				{
					if (er instanceof EntityMechaExo && ((EntityMechaExo) er).getThrustTicks() == 0)
					{
						((EntityMechaExo) er).setThrustTicks(10);
						this.commandBuffer = 100;
					}
				}
			}
			else
			{
				this.commandBuffer--;
			}
		}
		else if (this.getAttackTarget() != null && this.getAttackTarget() instanceof EntityLiving)
		{
			((EntityLiving) this.getAttackTarget()).setAttackTarget(null);
			this.setAttackTarget(null);
		}
	}

	@Override
	public void moveEntityWithHeading(float strafe, float forward) {
		super.moveEntityWithHeading(strafe, forward);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		boolean flag = super.attackEntityAsMob(entity);

		if (flag && this.ridingEntity != null)
		{
			entity.motionX += this.motionX;
			entity.motionZ += this.motionZ;
			entity.motionY += 1.4D;
		}

		return flag;
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance ins, IEntityLivingData data)
	{
		if (!this.worldObj.isRemote)
		{
			EntityMechaExo exo = new EntityMechaExo(this.worldObj);
			exo.setPositionAndUpdate(this.posX, this.posY, this.posZ);
			this.worldObj.spawnEntityInWorld(exo);
			this.mountEntity(exo);
		}
		return super.onInitialSpawn(ins, data);
	}
	
	@Override
	public boolean getCanSpawnHere() {
		EntityMechaExo exo = new EntityMechaExo(this.worldObj);
		exo.setPosition(this.posX, this.posY, this.posZ);
		if (this.worldObj.checkNoEntityCollision(exo.getEntityBoundingBox()) &&
				this.worldObj.getCollidingBoundingBoxes(exo, exo.getEntityBoundingBox()).isEmpty() &&
				!this.worldObj.isAnyLiquid(exo.getEntityBoundingBox()))
		{
			return super.getCanSpawnHere();
		}
		return false;
	}
}
