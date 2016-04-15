package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;

public class EntityTragicNeko extends EntityNeko {

	public EntityTragicNeko(World par1World) {
		super(par1World);
		this.setSize(0.475F, 1.955F);
		this.experienceValue = 70;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] tragicNekoStats = TragicConfig.getMobStat("tragicNekoStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(tragicNekoStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(tragicNekoStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(tragicNekoStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(tragicNekoStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(tragicNekoStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return TragicConfig.getMobStat("tragicNekoStats").getArmorValue();
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.getAttackTarget() != null && !this.isProperDate())
		{
			if (this.getFlickTime() > 0) this.setFlickTime(0);

			if (this.getFiringTicks() == 40 && this.canEntityBeSeen(this.getAttackTarget()))
			{
				if (TragicConfig.getBoolean("tragicNekoRockets")) this.doMissleAttack();
			}
			else if (this.hasFired() && rand.nextInt(8) == 0 && this.getFiringTicks() % 40 == 0 && this.getAttackTime() == 0)
			{
				this.throwRandomProjectile();
				this.setThrowingTicks(20);
			}

			if (rand.nextInt(8) == 0 && this.canFire() && this.ticksExisted % 10 == 0 && this.getThrowingTicks() == 0 && this.getAttackTime() == 0
					&& this.getDistanceToEntity(this.getAttackTarget()) >= 2.0F)
			{
				this.setFiringTicks(0);
			}
		}
	}

	@Override
	public void onDeathUpdate()
	{
		super.onDeathUpdate();

		if (this.worldObj.isRemote) return;

		if (this.deathTime == 20 && rand.nextInt(8) == 0 && !this.isProperDate() && TragicConfig.getBoolean("tragicNekoDeathBomb") && !this.isReleased())
		{
			this.worldObj.playSoundAtEntity(this, "creeper.primed", 1.7F, 1.0F);
			byte x = (byte) (rand.nextInt(2) + 2);

			for (byte i = 0; i < x; i++)
			{
				EntityNekoClusterBomb bomb = new EntityNekoClusterBomb(this.worldObj);

				bomb.posY = this.posY + 0.15;
				bomb.posX = this.posX + rand.nextDouble() - rand.nextDouble();
				bomb.posZ = this.posZ + rand.nextDouble() - rand.nextDouble();
				bomb.motionX = (rand.nextDouble() - rand.nextDouble()) * 0.25;
				bomb.motionY = -0.325;
				bomb.motionZ = (rand.nextDouble() - rand.nextDouble()) * 0.25;

				this.worldObj.spawnEntityInWorld(bomb);
			}
		}
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.tragicneko.living" : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") && rand.nextInt(6) == 0 ? "tragicmc:mob.tragicneko.hurt" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.tragicneko.death" : null;
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
