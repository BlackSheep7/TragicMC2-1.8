package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;

public class EntityScienceNeko extends EntityNeko {

	public EntityScienceNeko(World par1World) {
		super(par1World);
		this.setSize(0.675F, 1.955F);
		this.experienceValue = 10;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(65.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	@Override
	public int getTotalArmorValue() {
		return (int) 12;
	}
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		if (this.worldObj.isRemote)
		{
			return;
		}

		if (this.getAttackTarget() != null && !this.isProperDate()) //TODO set up AI to throw potions and fire a laser instead of throwing bombs and firing a missile
		{
			if (this.getFlickTime() > 0) this.setFlickTime(0);

			if ((this.getFiringTicks() == 40 || this.getFiringTicks() == 20) && this.canEntityBeSeen(this.getAttackTarget()))
			{
				if (TragicConfig.tragicNekoRockets) this.doMissleAttack();
			}
			else if (this.hasFired() && this.getFiringTicks() % 40 == 0 && this.getAttackTime() == 0 && rand.nextInt(8) == 0 && this.getDistanceToEntity(this.getAttackTarget()) <= 6.0)
			{
				this.throwRandomProjectile();
				this.setThrowingTicks(20);
			}

			if (rand.nextInt(4) == 0 && this.canFire() && this.ticksExisted % 5 == 0 && this.getAttackTime() == 0 && this.getDistanceToEntity(this.getAttackTarget()) >= 6.0F)
			{
				this.setFiringTicks(0);
			}
		}
	}
}
