package tragicneko.tragicmc.entity.mob;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityMechaExo;
import tragicneko.tragicmc.entity.EntityRidable;

public class EntityMechaNeko extends EntityNeko {

	private int commandBuffer = 10;
	public static final Predicate golemTarget = new Predicate() {
		@Override
		public boolean apply(Object input) {
			return canApply((Entity) input);
		}

		public boolean canApply(Entity entity) {
			return entity instanceof EntityIronGolem;
		}
	};
	
	public EntityAIBase targetGolems = new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true, false, golemTarget);
	private boolean hasGrieved = false;

	public EntityMechaNeko(World par1World) {
		super(par1World);
		this.setSize(0.475F * 0.915F, 1.895F * 0.915F);
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

		this.onGround = true;
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
			
			if (this.getAttackTarget().isDead)
			{
				er.setAttackTarget(null);
				this.setAttackTarget(null);
			}
		}
		
		if (this.ridingEntity != null && !this.ridingEntity.isEntityAlive() && !this.hasGrieved)
		{
			this.hasGrieved = true;
			if (TragicConfig.getBoolean("allowMobSounds")) this.worldObj.playSoundAtEntity(this, "tragicmc:mob.mechaneko.grieve", this.getSoundVolume(), this.getSoundPitch());
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
	
	@Override
	protected void updateNekoTasks() {
		super.updateNekoTasks();
		
		if (this.ridingEntity instanceof EntityMechaExo && !this.isProperDate())
		{
			if (!this.targetTasks.taskEntries.contains(targetGolems)) this.targetTasks.addTask(4, targetGolems);
		}
		else
		{
			this.targetTasks.removeTask(targetGolems);
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("commandBuffer")) this.commandBuffer = tag.getInteger("commandBuffer");
		if (tag.hasKey("hasGrieved")) this.hasGrieved = tag.getBoolean("hasGrieved");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("commandBuffer", this.commandBuffer);
		tag.setBoolean("hasGrieved", this.hasGrieved);
	}
	
	@Override
	public String getLivingSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? (this.ridingEntity instanceof EntityMechaExo ? "tragicmc:mob.mechaneko.livingexo" : "tragicmc:mob.mechaneko.living") : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") && rand.nextInt(5) == 0 ? (this.ridingEntity instanceof EntityMechaExo ? "tragicmc:mob.mechaneko.hurtexo" : "tragicmc:mob.mechaneko.hurt") : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? (this.ridingEntity instanceof EntityMechaExo ? "tragicmc:mob.mechaneko.deathexo" : "tragicmc:mob.mechaneko.death") : null;
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
		return 320;
	}
}
