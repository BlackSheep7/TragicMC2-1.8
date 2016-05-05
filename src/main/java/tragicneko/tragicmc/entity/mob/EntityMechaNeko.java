package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.EntityMechaExo;
import tragicneko.tragicmc.entity.EntityRidable;

public class EntityMechaNeko extends EntityNeko {

	private int commandBuffer = 10;

	public EntityMechaNeko(World par1World) {
		super(par1World);
		this.setSize(0.475F * 0.915F, 1.955F * 0.915F);
		this.experienceValue = 100;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] mechaNekoStats = TragicConfig.getMobStat("mechaNekoStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(mechaNekoStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(mechaNekoStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(mechaNekoStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(mechaNekoStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(mechaNekoStats[4]);
	}

	@Override
	public int getTotalArmorValue() {
		return this.ridingEntity != null && TragicConfig.getBoolean("mechaNekoRidingArmor") ? 24 : TragicConfig.getMobStat("mechaNekoStats").getArmorValue();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.getAttackTarget() == this.ridingEntity) this.setAttackTarget(null);

		if (this.ridingEntity instanceof EntityRidable && this.getAttackTarget() != null && TragicConfig.getBoolean("mechaNekoCommandExo")) 
		{
			EntityRidable er = (EntityRidable) this.ridingEntity;
			er.setAttackTarget(this.getAttackTarget());

			if (this.commandBuffer <= 0)
			{
				if (this.getDistanceToEntity(this.getAttackTarget()) >= 6 && this.canEntityBeSeen(this.getAttackTarget()))
				{
					if (er.canAttack() && TragicConfig.getBoolean("allowRidableEntityAbilitiesViaMob"))
					{
						final int i = rand.nextInt(2);
						er.useAttackViaMob(i, this.getAttackTarget());
						this.commandBuffer = i == 0 ? 80 : 20;
					}
				}
				else if (this.getDistanceToEntity(this.getAttackTarget()) < 6 && this.ticksExisted % 20 == 0 && rand.nextInt(4) == 0) //charge at them if close enough to knock them back
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
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (entity == this.ridingEntity) return false;
		return super.attackEntityAsMob(entity);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance ins, IEntityLivingData data)
	{
		if (!this.worldObj.isRemote && this.ridingEntity == null && TragicConfig.getBoolean("mechaNekoRidingExo") && TragicConfig.getBoolean("allowMechaExo"))
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
