package tragicneko.tragicmc.entity.mob;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.entity.projectile.EntityDarkMortor;

public class EntityPsygote extends TragicMob {

	public static final int DW_FIRING_TICKS = 20;
	public static final int DW_SWITCH_TICKS = 21;
	public static final int DW_HURT_TIME = 22;
	
	private AttributeModifier mod = new AttributeModifier(UUID.fromString("1e8bc939-443c-46b6-8158-0d53513a47e6"), "psygoteSpeedDebuff", TragicConfig.modifier[8], 0);
	private boolean hasTeleported = false; //keep track for achievement

	public EntityPsygote(World par1World) {
		super(par1World);
		this.setSize(0.72F, 1.45F);
		this.stepHeight = 2.0F;
		this.experienceValue = 100;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 16.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.tasks.addTask(0, new EntityAIAvoidEntity(this, EntityPlayer.class, playerTarget, 8.0F, 1.6D, 2.2D));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, playerTarget));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(DW_FIRING_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_SWITCH_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_HURT_TIME, Integer.valueOf(0));
	}

	public int getFiringTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_FIRING_TICKS);
	}

	private void setFiringTicks(int i)
	{
		this.dataWatcher.updateObject(DW_FIRING_TICKS, i);
	}

	private void decrementFiringTicks()
	{
		this.setFiringTicks(this.getFiringTicks() - 1);
	}

	public int getSwitchTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_SWITCH_TICKS);
	}

	private void setSwitchTicks(int i)
	{
		this.dataWatcher.updateObject(DW_SWITCH_TICKS, i);
	}

	private void decrementSwitchTicks()
	{
		this.setSwitchTicks(this.getSwitchTicks() - 1);
	}

	public int getHurtTime()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_HURT_TIME);
	}

	private void setHurtTime(int i)
	{
		this.dataWatcher.updateObject(DW_HURT_TIME, i);
	}

	private void decrementHurtTime()
	{
		this.setHurtTime(this.getHurtTime() - 1);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] psygoteStats = TragicConfig.getMobStat("psygoteStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(psygoteStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(psygoteStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(psygoteStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(psygoteStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(psygoteStats[4]);
	}

	@Override
	public void onLivingUpdate()
	{
		if (!this.worldObj.isRemote && this.isPotionActive(Potion.wither.id)) this.removePotionEffect(Potion.wither.id);

		super.onLivingUpdate();

		if (!this.onGround && this.motionY < 0.0D) this.motionY *= 0.58D;
		this.fallDistance = 0.0F;

		if (this.worldObj.isRemote)
		{
			for (int l = 0; l < 2; ++l)
			{
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
						this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
						this.posY + this.rand.nextDouble() * this.height,
						this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
						(this.rand.nextDouble() - 0.6D) * 0.1D,
						this.rand.nextDouble() * 0.1D,
						(this.rand.nextDouble() - 0.6D) * 0.1D);

				if (this.getSwitchTicks() > 0)
				{
					this.worldObj.spawnParticle(EnumParticleTypes.PORTAL,
							this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
							this.posY + this.rand.nextDouble() + this.height,
							this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
							(this.rand.nextDouble() - 0.6D) * 0.1D,
							this.rand.nextDouble() * -0.8D - 2.4D,
							(this.rand.nextDouble() - 0.6D) * 0.1D);

					if (this.getSwitchTicks() == 3) this.playSound("tragicmc:mob.psygote.coo", 0.6F, 1.0F);
				}
			}
			return;
		}

		if (this.getFiringTicks() > 0) this.decrementFiringTicks();
		if (this.getSwitchTicks() > 0) this.decrementSwitchTicks();
		if (this.getHurtTime() > 0) this.decrementHurtTime();

		if (this.getAttackTarget() == null || this.getDistanceToEntity(this.getAttackTarget()) <= 8.0)
		{
			if (this.getFiringTicks() > 0) this.setFiringTicks(0);
			if (this.getSwitchTicks() > 0) this.setSwitchTicks(0);

			if (this.getAttackTarget() != null && this.ticksExisted % 10 == 0 && TragicConfig.getBoolean("psygoteProjectiles"))
			{
				this.fireOneMortor();
			}
		}

		if (this.getSwitchTicks() > 0) this.setFiringTicks(0);

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
		if (this.getFiringTicks() >= 40 || this.getSwitchTicks() > 0) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) >= 6.0F && rand.nextInt(48) == 0 && this.getFiringTicks() == 0 && this.getSwitchTicks() == 0)
		{
			this.setFiringTicks(100);
		}

		if (this.getFiringTicks() == 0 && this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) >= 8.0F && this.getFiringTicks() == 0 && this.getSwitchTicks() == 0 && rand.nextInt(48) == 0) this.setSwitchTicks(60);

		if (this.getFiringTicks() >= 60 && this.ticksExisted % 5 == 0 && this.getAttackTarget() != null && TragicConfig.getBoolean("psygoteProjectiles"))
		{
			this.shootProjectiles();
		}
		if (this.getSwitchTicks() == 2 && this.getAttackTarget() != null && TragicConfig.getBoolean("psygoteSwapTeleport")) this.switchPlaces();

		if (this.ticksExisted % 20 == 0 && this.getHealth() < this.getMaxHealth() && TragicConfig.getBoolean("psygoteRegeneration")) this.heal(3.0F);

		if (this.ticksExisted % 5 == 0 && this.getAttackTarget() != null && rand.nextInt(128) == 0 && TragicConfig.getBoolean("allowInhibit") && this.canEntityBeSeen(this.getAttackTarget())) this.getAttackTarget().addPotionEffect(new PotionEffect(TragicPotion.Inhibit.id, 120));
		if (this.ticksExisted % 5 == 0 && this.getAttackTarget() != null && rand.nextInt(32) == 0 && this.getDistanceToEntity(this.getAttackTarget()) <= 8.0F && this.canEntityBeSeen(this.getAttackTarget())) this.getAttackTarget().addPotionEffect(new PotionEffect(Potion.blindness.id, 120));
	}

	private void switchPlaces() {
		double x = this.getAttackTarget().posX;
		double y = this.getAttackTarget().posY;
		double z = this.getAttackTarget().posZ;

		double x2 = this.posX;
		double y2 = this.posY;
		double z2 = this.posZ;

		if (this.getAttackTarget() instanceof EntityPlayerMP)
		{
			if (this.teleportPlayer((EntityPlayerMP) this.getAttackTarget()))
			{
				this.getAttackTarget().addPotionEffect(new PotionEffect(Potion.blindness.id, 200, 0));
			}
		}
		else
		{
			this.getAttackTarget().setPosition(x2, y2, z2);

			short short1 = 128;

			for (int l = 0; l < short1; ++l)
			{
				double d6 = l / (short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = x + ((x2) - x) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				double d8 = y + ((y2) - y) * d6 + this.rand.nextDouble() * this.height;
				double d9 = z + ((z2) - z) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				this.worldObj.spawnParticle(getTeleportParticle(), d7, d8, d9, f, f1, f2);
			}

			this.worldObj.playSoundAtEntity(this.getAttackTarget(), getTeleportSound(), 0.4F, 0.4F);
			this.getAttackTarget().addPotionEffect(new PotionEffect(Potion.blindness.id, 60, 0));
		}

		this.setPosition(x, y, z);
		this.hasTeleported = true;
	}

	private void shootProjectiles() {
		double d0 = rand.nextInt(2) - rand.nextInt(2);
		double d1 = rand.nextInt(4);
		double d2 = rand.nextInt(2) - rand.nextInt(2);
		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.975F;

		EntityDarkMortor mortor = new EntityDarkMortor(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
		mortor.posY = this.posY + this.height + 0.5D;
		mortor.posX += d0 * 0.04335D;
		mortor.posZ += d2 * 0.04335D;
		mortor.motionY += 0.36D * f1;
		this.worldObj.spawnEntityInWorld(mortor);
	}

	private void fireOneMortor() {
		double d0 = this.getAttackTarget().posX - this.posX;
		double d1 = this.getAttackTarget().posY - this.posY;
		double d2 = this.getAttackTarget().posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.25F;

		EntityDarkMortor mortor = new EntityDarkMortor(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
		mortor.posX = this.posX + 0.115D * d0;
		mortor.posY = this.posY + (this.height * 2 / 3);
		mortor.posZ = this.posZ + 0.115D * d2;
		this.worldObj.spawnEntityInWorld(mortor);
	}

	@Override
	public void fall(float dist, float multi) {}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;
		if (this.getHurtTime() == 0) this.setHurtTime(10);
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public int getTotalArmorValue()
	{
		return TragicConfig.getMobStat("psygoteStats").getArmorValue();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("hurtTime")) this.setHurtTime(tag.getInteger("hurtTime"));;
		if (tag.hasKey("firingTicks")) this.setFiringTicks(tag.getInteger("firingTicks"));
		if (tag.hasKey("switchTicks")) this.setSwitchTicks(tag.getInteger("switchTicks"));
		if (tag.hasKey("hasTeleported")) this.hasTeleported = tag.getBoolean("hasTeleported");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("hurtTime", this.getHurtTime());
		tag.setInteger("firingTicks", this.getFiringTicks());
		tag.setInteger("switchTicks", this.getSwitchTicks());
		tag.setBoolean("hasTeleported", this.hasTeleported);
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? (this.getAttackTarget() == null ? "tragicmc:mob.psygote.coo" : "tragicmc:mob.psygote.cry") : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.psygote.shriek" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.psygote.shriek" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return 1.0F;
	}

	@Override
	public float getSoundVolume()
	{
		return 0.6F + rand.nextFloat() * 0.2F;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		
	}
	
	@Override
	public void onDeath(DamageSource src) {
		super.onDeath(src);
		if (src.getEntity() instanceof EntityPlayerMP && TragicConfig.getBoolean("allowAchievements") && !this.hasTeleported)
		{
			((EntityPlayerMP) src.getEntity()).triggerAchievement(TragicAchievements.psygote);
		}
	}
	
	@Override
	protected EnumParticleTypes getTeleportParticle() {
		return EnumParticleTypes.SMOKE_NORMAL;
	}
}
