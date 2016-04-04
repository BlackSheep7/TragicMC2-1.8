package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityScienceNeko extends EntityNeko {
	
	private static final int DW_TARGET_ID = 25;

	public EntityScienceNeko(World par1World) {
		super(par1World);
		this.setSize(0.675F * 0.825F, 1.955F * 0.825F);
		this.experienceValue = 50;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DW_TARGET_ID, Integer.valueOf(0));
	}
	
	public int getTargetID()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_TARGET_ID);
	}

	private void setTargetID(int i)
	{
		this.dataWatcher.updateObject(DW_TARGET_ID, i);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(45.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.26);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0);
	}

	@Override
	public int getTotalArmorValue() {
		return (int) 0;
	}
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		if (this.worldObj.isRemote)
		{
			if (this.getFiringTicks() == 40)
			{
				Entity entity = this.worldObj.getEntityByID(this.getTargetID());
				if (entity != null && this.getDistanceToEntity(entity) > 4.0)
				{
					double d0 = entity.posX - this.posX;
					double d1 = entity.posY - this.posY;
					double d2 = entity.posZ - this.posZ;

					for (byte l = 0; l < 8; l++)
					{
						double d3 = 0.123D * l + (rand.nextDouble() * 0.125D);
						this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX + d0 * d3, this.posY + d1 * d3 + 1.25D, this.posZ + d2 * d3, 0.0, 0.0, 0.0);
					}
				}
			}
			return;
		}

		if (this.getAttackTarget() != null && !this.isProperDate())
		{
			if (this.getFlickTime() > 0) this.setFlickTime(0);
			this.setTargetID(this.getAttackTarget().getEntityId());

			if (this.getFiringTicks() == 40 && this.canEntityBeSeen(this.getAttackTarget()) && this.getDistanceToEntity(this.getAttackTarget()) <= 16.0 && this.getDistanceToEntity(this.getAttackTarget()) >= 4.0)
			{
				if (TragicConfig.tragicNekoRockets) this.doMissleAttack();
			}
			else if (this.hasFired() && this.getFiringTicks() % 40 == 0 && this.getAttackTime() == 0 && rand.nextBoolean() && this.getDistanceToEntity(this.getAttackTarget()) >= 5.0 && this.getDistanceToEntity(this.getAttackTarget()) <= 12.0)
			{
				this.throwRandomProjectile();
				this.setThrowingTicks(20);
			}

			if (rand.nextInt(16) == 0 && this.canFire() && this.ticksExisted % 10 == 0 && this.getAttackTime() == 0 && this.getDistanceToEntity(this.getAttackTarget()) >= 3.0F)
			{
				this.setFiringTicks(0);
			}
		}
		
		if (this.getAttackTarget() == null) this.setTargetID(0);
	}
	
	@Override
	protected void doMissleAttack() {
		if (TragicConfig.allowMobSounds) 
		{
			this.worldObj.playSoundAtEntity(this, "tragicmc:boss.aegar.laser", 1.0F, 1.0F);
			this.worldObj.playSoundAtEntity(this.getAttackTarget(), "tragicmc:boss.aegar.laser", 1.0F, 1.0F);
		}
		this.getAttackTarget().attackEntityFrom(DamageHelper.causeArmorPiercingDamageToEntity(this), 3.0F);
	}
	
	@Override
	protected void throwRandomProjectile() {
		int i = rand.nextInt(32) == 0 ? 28 : (rand.nextInt(16) == 0 ? 4 : 56);
		EntityThrowable theProjectile = new EntityPotion(this.worldObj, this, new ItemStack(Items.potionitem, 1, i));
		theProjectile.motionX = (this.getAttackTarget().posX - this.posX) * 0.335D;
		theProjectile.motionZ = (this.getAttackTarget().posZ - this.posZ) * 0.335D;
		theProjectile.motionY = (this.getAttackTarget().posY - this.posY) * 0.335D;
		this.worldObj.spawnEntityInWorld(theProjectile);
	}
}
