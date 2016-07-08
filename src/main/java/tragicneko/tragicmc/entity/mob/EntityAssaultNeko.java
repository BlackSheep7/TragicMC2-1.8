package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;

public class EntityAssaultNeko extends EntityNeko {

	private static final int DW_CHARGE_TICKS = 25;
	private static final int DW_SHIELD_TICKS = 26;
	
	private int shieldBuffer = 100;
	private float shieldHealth = 100F;

	public EntityAssaultNeko(World par1World) {
		super(par1World);
		this.setSize(0.675F * 1.145F, 1.955F * 1.145F);
		this.experienceValue = 50;
		this.stepHeight = 1.0F;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DW_CHARGE_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_SHIELD_TICKS, Integer.valueOf(0));
	}
	
	public int getChargeTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_CHARGE_TICKS);
	}

	private void setChargeTicks(int i)
	{
		this.dataWatcher.updateObject(DW_CHARGE_TICKS, i);
	}
	
	public int getShieldTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_SHIELD_TICKS);
	}

	private void setShieldTicks(int i)
	{
		this.dataWatcher.updateObject(DW_SHIELD_TICKS, i);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] assaultNekoStats = TragicConfig.getMobStat("assaultNekoStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(assaultNekoStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(assaultNekoStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(assaultNekoStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(assaultNekoStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(assaultNekoStats[4]);
	}

	@Override
	public int getTotalArmorValue() {
		return TragicConfig.getMobStat("assaultNekoStats").getArmorValue();
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			return;
		}
		
		if (this.getShieldTicks() > 0)
		{
			this.setShieldTicks(this.getShieldTicks() - 1);
			this.shieldBuffer = 100;
			
			if (this.getShieldTicks() % 20 == 0 && TragicConfig.getBoolean("allowMobSounds"))
			{
				String s = shieldHealth < 40F || this.getShieldTicks() <= 40 ? "tragicmc:mob.assaultneko.shieldlow" : "tragicmc:mob.assaultneko.shield";
				this.worldObj.playSoundAtEntity(this, s, 0.6F, 1.0F);
			}
		}
		if (this.getChargeTicks() > 0) this.setChargeTicks(this.getChargeTicks() - 1);
		if (shieldBuffer > 0) --shieldBuffer;

		if (this.getAttackTarget() != null && !this.isProperDate())
		{
			if (this.getFlickTime() > 0) this.setFlickTime(0); 
			
			if (this.ticksExisted % 5 == 0 && this.getChargeTicks() == 0 && this.getDistanceToEntity(this.getAttackTarget()) > 6.0F && this.onGround && rand.nextInt(8) == 0)
			{
				this.setChargeTicks(100);
			}
			
			if (this.ticksExisted % 7 == 0 && this.getShieldTicks() == 0 && this.shieldBuffer == 0 && this.getDistanceToEntity(this.getAttackTarget()) < 16.0F && this.getHealth() < this.getMaxHealth())
			{
				this.setShieldTicks(120);
				this.shieldHealth = 100F;
			}
			
			if (this.getChargeTicks() > 0 && this.onGround)
			{
				double d0 = this.getAttackTarget().posX - this.posX;
				double d1 = this.getAttackTarget().posZ - this.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX += d0 / f2 * 0.14D * 0.1100000011920929D + this.motionX * 0.2400000298023224D;
				this.motionZ += d1 / f2 * 0.14D * 0.1100000011920929D + this.motionZ * 0.2400000298023224D;
				this.motionY = -0.1D;
			}
		}
		
		
	}

	@Override
	protected void doMissleAttack() {
		
	}

	@Override
	protected void throwRandomProjectile() {
		
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		if (this.getShieldTicks() > 0 && this.shieldHealth > 0)
		{
			this.shieldHealth -= par2;
			if (this.shieldHealth > 0)
			{
				if (TragicConfig.getBoolean("allowMobSounds")) this.worldObj.playSoundAtEntity(this, "tragicmc:mob.assaultneko.absorb", 1.0F, 1.0F);
				return false;
			}
			else
			{
				par2 += shieldHealth; //add because it'll be negative at this point
			}
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;
		
		if (this.getChargeTicks() == 0)
		{
			this.setAttackTime(10);
			float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
			return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this).setDamageBypassesArmor(), f);
		}
		else
		{
			this.setChargeTicks(0);
			float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue() * 1.5F;
			boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);
			
			if (flag)
			{
				par1Entity.motionX += this.motionX;
				par1Entity.motionZ += this.motionZ;
			}
			
			return flag;
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("shieldBuffer")) this.shieldBuffer = tag.getInteger("shieldBuffer");
		if (tag.hasKey("chargeTicks")) this.setChargeTicks(tag.getInteger("chargeTicks"));
		if (tag.hasKey("shieldTicks")) this.setShieldTicks(tag.getInteger("shieldTicks"));
		if (tag.hasKey("shieldHealth")) this.shieldHealth = tag.getFloat("shieldHealth");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("shieldBuffer", this.shieldBuffer);
		tag.setInteger("chargeTicks", this.getChargeTicks());
		tag.setInteger("shieldTicks", this.getShieldTicks());
		tag.setFloat("shieldHealth", this.shieldHealth);
	}
	
	@Override
	public String getLivingSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.assaultneko.living" : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") && rand.nextInt(5) == 0 ? "tragicmc:mob.assaultneko.hurt" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.assaultneko.death" : null;
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
}
