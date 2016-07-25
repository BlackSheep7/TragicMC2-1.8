package tragicneko.tragicmc.entity.boss;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.entity.EntityAIWatchTarget;

public class EntityPolaris extends TragicBoss {

	public static final int DW_ATTACK_TIME = 20;
	public static final int DW_DAYTIME = 21;
	public static final int DW_CLONE = 22;

	public static final Predicate golemTarget = new Predicate() {
		@Override
		public boolean apply(Object input) {
			return canApply((Entity) input);
		}

		public boolean canApply(Entity entity) {
			return entity instanceof EntityGolem;
		}
	};
	private EntityAIBase fearGolems = new EntityAIAvoidEntity(this, EntityGolem.class, golemTarget, 6.0F, 1.0D, 1.2D);

	public EntityPolaris(World par1World) {
		super(par1World);
		this.setSize(0.5F, 2.075F);
		this.stepHeight = 2.0F;
		this.experienceValue = 600;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchTarget(this, 48.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.75D, 32.0F));
		if (TragicConfig.getBoolean("polarisFearGolems")) this.tasks.addTask(1, fearGolems);
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, null));
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	@Override
	public boolean canRenderOnFire()
	{
		return super.canRenderOnFire() && !this.isInvisible();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return !getDaytime() ? 15728880 : super.getBrightnessForRender(par1);
	}

	@Override
	public float getBrightness(float par1)
	{
		return !getDaytime() ? 1.0F : super.getBrightness(par1);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] polarisStats = TragicConfig.getMobStat("polarisStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(polarisStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(polarisStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(polarisStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(polarisStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(polarisStats[4]);
	}

	@Override
	public void onDeath(DamageSource src)
	{
		super.onDeath(src);
		if (src.getEntity() instanceof EntityPlayerMP && TragicConfig.getBoolean("allowAchievements")) ((EntityPlayerMP) src.getEntity()).triggerAchievement(TragicAchievements.polaris);
	}

	@Override
	protected void dropFewItems(boolean flag, int l)
	{
		super.dropFewItems(flag, l);
		if (!this.worldObj.isRemote && TragicConfig.getBoolean("allowMobStatueDrops") && rand.nextInt(100) <= TragicConfig.getInt("mobStatueDropChance") && this.getAllowLoot()) this.capturedDrops.add(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(TragicItems.MobStatue, 1, 5)));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(DW_ATTACK_TIME, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_DAYTIME, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_CLONE, Integer.valueOf(0));
	}

	public int getAttackTime()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_ATTACK_TIME);
	}

	private void setAttackTime(int i)
	{
		this.dataWatcher.updateObject(DW_ATTACK_TIME, i);
	}

	private void decrementAttackTime()
	{
		int pow = this.getAttackTime();
		this.setAttackTime(--pow);
	}

	/**
	 * Returns true if it is currently day time
	 * @return
	 */
	public boolean getDaytime()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_DAYTIME) == 0;
	}

	private void setDaytime(boolean flag)
	{
		this.dataWatcher.updateObject(DW_DAYTIME, flag ? 0 : 1);
	}

	public boolean isClone()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_CLONE) == 1;
	}

	public void setClone()
	{
		this.dataWatcher.updateObject(DW_CLONE, 1);
	}

	@Override
	public void onLivingUpdate()
	{
		if (!this.worldObj.isRemote)
		{
			if (this.worldObj.isDaytime() && !this.getDaytime())
			{
				this.setDaytime(true);
			}
			else if (!this.worldObj.isDaytime() && this.getDaytime())
			{
				this.setDaytime(false);
			}
		}

		super.onLivingUpdate();

		if (this.worldObj.isRemote)	return;
		if (this.getAttackTime() > 0) this.decrementAttackTime();
		if (this.isClone() && this.tasks.taskEntries.contains(fearGolems)) this.tasks.taskEntries.remove(fearGolems);
		if (this.isInWater()) this.teleportRandomly();

		if (this.ticksExisted % 240 == 0 && TragicConfig.getBoolean("polarisRegeneration")) this.heal(3.0F);

		if (this.getAttackTarget() != null && !this.isClone())
		{
			if (TragicConfig.getBoolean("polarisNighttimeSet")) this.worldObj.getWorldInfo().setWorldTime(18000);

			if (this.isEntityInRange(this.getAttackTarget(), 3.0F, 16.0F) && TragicConfig.getBoolean("polarisInvisibility"))
			{
				this.setInvisible(true);
			}
			else
			{
				this.setInvisible(false);
			}

			if (this.getDistanceToEntity(this.getAttackTarget()) > 16.0F && rand.nextInt(48) == 0 && TragicConfig.getBoolean("polarisTeleport")) this.teleportToEntity(this.getAttackTarget());
			if (this.ticksExisted % 10 == 0 && rand.nextInt(4) == 0 && this.getDistanceToEntity(this.getAttackTarget()) <= 12.0F && TragicConfig.getBoolean("polarisTeleport")) this.teleportRandomly();
		}
		else
		{
			this.setInvisible(false);
		}

		if (this.isClone() && this.ticksExisted >= 100) this.setDead();
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		if (this.isClone())
		{
			this.setBeenAttacked();
			this.setDead();
			return true;
		}

		if (par1DamageSource.getDamageType().equals("arrow") || this.isInvisible()) par2 *= 1.15;

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase)
		{
			if (this.getHealth() - par2 > 0.0F && TragicConfig.getBoolean("polarisTeleport")) this.teleportRandomly();

			if (par1DamageSource.getEntity() instanceof EntityPlayer && !par1DamageSource.isProjectile())
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

				if (player.experienceTotal > 20)
				{
					player.experienceTotal -= 20;
				}
				else
				{
					player.experienceTotal = 0;
				}
			}
		}

		if (this.getAttackTime() == 0) this.setAttackTime(5);

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;

		boolean flag = super.attackEntityAsMob(par1Entity);

		if (flag)
		{
			if (rand.nextBoolean() && par1Entity instanceof EntityLivingBase) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 30));
			if (TragicConfig.getBoolean("polarisTeleport")) this.teleportRandomly();

			ArrayList<EntityPolaris> list = (ArrayList<EntityPolaris>) this.worldObj.getEntitiesWithinAABB(EntityPolaris.class, this.getEntityBoundingBox().expand(32.0D, 32.0D, 32.0D));
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) != this && !this.isClone() && list.get(i).isClone())
				{
					list.get(i).setDead();
				}
			}

			if (this.isClone()) this.setDead();
		}

		return flag;
	}

	@Override
	public int getTotalArmorValue()
	{
		return this.worldObj.isDaytime() ? 0 : TragicConfig.getMobStat("polarisStats").getArmorValue();
	}

	@Override
	public void fall(float dist, float multi){}

	@Override
	protected void onTeleport(double d3, double d4, double d5)
	{
		if (rand.nextBoolean() && this.getHealth() <= this.getMaxHealth() / 2 && TragicConfig.getBoolean("polarisAfterImage"))
		{
			List<EntityPolaris> list = this.worldObj.getEntitiesWithinAABB(EntityPolaris.class, new AxisAlignedBB(0, 0, 0, 1, 1, 1).offset(this.posX, this.posY, this.posZ).expand(32.0D, 32.0D, 32.0D));
			for (int mow = 0; mow < list.size(); mow++)
			{
				if (list.get(mow) == this) list.remove(mow);
			}

			if (list.size() <= 3)
			{
				EntityPolaris polar = new EntityPolaris(this.worldObj);
				polar.copyLocationAndAnglesFrom(this);
				polar.setPosition(d3, d4, d5);
				polar.setClone();
				this.worldObj.spawnEntityInWorld(polar);
				if (this.getAttackTarget() != null) polar.setAttackTarget(this.getAttackTarget());
				polar.onInitialSpawn(this.worldObj.getDifficultyForLocation(new BlockPos(d3, d4, d5)), null);
			}
		}
	}

	@Override
	public void setInWeb() {}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance ins, IEntityLivingData data)
	{
		if (!this.worldObj.isRemote && !this.worldObj.isDaytime() && this.getDaytime()) this.setDaytime(false);
		return super.onInitialSpawn(ins, data);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
		if (tag.hasKey("isClone") && tag.getBoolean("isClone")) this.setClone();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("attackTime", this.getAttackTime());
		tag.setBoolean("isClone", this.isClone());
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:boss.polaris.sonar" : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:boss.polaris.ding" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:boss.polaris.death" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return 0.8F + rand.nextFloat() * 0.2F;
	}

	@Override
	public float getSoundVolume()
	{
		return 1.0F;
	}

	@Override
	public int getTalkInterval()
	{
		return super.getTalkInterval();
	}
	
	@Override
	protected String getTeleportSound() {
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:boss.polaris.clone" : super.getTeleportSound();
	}
	
	@Override
	protected int getTeleportLight() {
		return 15;
	}
}
