package tragicneko.tragicmc.entity.mob;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityAssaultNeko extends EntityNeko {

	private static final int DW_ATTACK_MODE = 25;
	private static final int DW_CHARGE_TICKS = 26;

	public EntityAssaultNeko(World par1World) {
		super(par1World);
		this.setSize(0.675F * 0.825F, 1.955F * 0.825F);
		this.experienceValue = 50;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DW_ATTACK_MODE, (byte) 0); //target mode 0 is laser sword, target mode 1 is battering ram, target mode 3 is shield
		this.dataWatcher.addObject(DW_CHARGE_TICKS, Integer.valueOf(0));
	}

	public int getTargetMode()
	{
		return this.dataWatcher.getWatchableObjectByte(DW_ATTACK_MODE);
	}

	private void setTargetMode(byte b)
	{
		this.dataWatcher.updateObject(DW_ATTACK_MODE, b);
	}
	
	public int getChargeTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_CHARGE_TICKS);
	}

	private void setChargeTicks(int i)
	{
		this.dataWatcher.updateObject(DW_CHARGE_TICKS, i);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(45.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.26);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
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
			return;
		}

		if (this.getAttackTarget() != null && !this.isProperDate())
		{
			if (this.getFlickTime() > 0) this.setFlickTime(0);
			if (this.getChargeTicks() == 0 && this.getDistanceToEntity(this.getAttackTarget()) <= 6.0F && this.getTargetMode() != 1) this.setTargetMode((byte) 1); 
			else this.setTargetMode((byte) 0);
		}
		else if (this.getTargetMode() == 0)
		{
			
		}
	}

	@Override
	protected void doMissleAttack() {
		
	}

	@Override
	protected void throwRandomProjectile() {
		
	}
	
	@Override
	public String getLivingSound()
	{
		return null;
	}

	@Override
	public String getHurtSound()
	{
		return super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return null;
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
