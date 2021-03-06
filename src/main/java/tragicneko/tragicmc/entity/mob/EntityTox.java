package tragicneko.tragicmc.entity.mob;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.miniboss.TragicMiniBoss;
import tragicneko.tragicmc.entity.projectile.EntitySpore;
import tragicneko.tragicmc.items.weapons.ItemScythe;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;

public class EntityTox extends TragicMob {
	
	public static final int DW_TOX_TYPE = 20;
	public static final int DW_FIRING_TICKS = 21;
	public static final int DW_ATTACK_TIME = 22;
	public static final int DW_WIGGLE_TIME = 23;

	public EntityTox(World par1World) {
		super(par1World);
		this.experienceValue = 60;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 0.05D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 64.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, playerTarget));
		if (this.superiorForm == null && !(this instanceof TragicMiniBoss)) this.superiorForm = EntityMagmox.class;
	}

	@Override
	public boolean isMobVariant()
	{
		return this.getToxType() == 1;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(DW_TOX_TYPE, (byte) 0);
		this.dataWatcher.addObject(DW_FIRING_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_ATTACK_TIME, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_WIGGLE_TIME, Integer.valueOf(0));
	}

	public byte getToxType()
	{
		return this.dataWatcher.getWatchableObjectByte(DW_TOX_TYPE);
	}

	protected void setToxType(byte b)
	{
		this.dataWatcher.updateObject(DW_TOX_TYPE, b);
	}

	public int getFiringTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_FIRING_TICKS);
	}

	protected void setFiringTicks(int i)
	{
		this.dataWatcher.updateObject(DW_FIRING_TICKS, i);
	}

	protected void decrementFiringTicks()
	{
		int pow = this.getFiringTicks();
		this.setFiringTicks(--pow);
	}

	public boolean isFiring()
	{
		return this.getFiringTicks() > 0;
	}

	public int getAttackTime()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_ATTACK_TIME);
	}

	protected void setAttackTime(int i)
	{
		this.dataWatcher.updateObject(DW_ATTACK_TIME, i);
	}

	protected void decrementAttackTime()
	{
		int pow = this.getAttackTime();
		this.setAttackTime(--pow);
	}

	public int getWiggleTime()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_WIGGLE_TIME);
	}

	protected void setWiggleTime(int i)
	{
		this.dataWatcher.updateObject(DW_WIGGLE_TIME, i);
	}

	protected void decrementWiggleTime()
	{
		int pow = this.getWiggleTime();
		this.setWiggleTime(--pow);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return this.getToxType() == 1 ? 15728880 : super.getBrightnessForRender(par1);
	}

	@Override
	public float getBrightness(float par1)
	{
		return this.getToxType() == 1 ? 1.0F : super.getBrightness(par1);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] stats = TragicConfig.getMobStat(this.getToxType() == 0 ? "toxStats" : "poxStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(stats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(stats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(stats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(stats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(stats[4]);
	}

	@Override
	public void onLivingUpdate()
	{
		if (!this.worldObj.isRemote)
		{
			if (this.motionY > 0.0D) this.motionY = -0.1D;
			if (Math.abs(this.motionX) > 0.25D) this.motionX *= 0.225D;
			if (Math.abs(this.motionZ) > 0.25D) this.motionZ *= 0.225D;
			if (this.isPotionActive(Potion.poison)) this.removePotionEffect(Potion.poison.id);
		}

		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			if (this.getToxType() == 0)
			{
				if ((this.getWiggleTime() > 0 || this.isFiring() || this.getAttackTime() > 0) && !this.isImmuneToFire())
				{
					this.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT,
							this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
							this.posY + this.rand.nextDouble() * this.height,
							this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
							(this.rand.nextDouble() - 0.6D) * 0.1D,
							this.rand.nextDouble() * 0.1D,
							(this.rand.nextDouble() - 0.6D) * 0.1D);
				}
			}
			else
			{
				if (this.getWiggleTime() > 0 || this.isFiring() || this.getAttackTime() > 0)
				{
					this.worldObj.spawnParticle(EnumParticleTypes.SLIME,
							this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
							this.posY + this.rand.nextDouble() * this.height,
							this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
							(this.rand.nextDouble() - 0.6D) * 0.1D,
							this.rand.nextDouble() * 0.1D,
							(this.rand.nextDouble() - 0.6D) * 0.1D);
				}
			}
		}
		else
		{
			if (this.getWiggleTime() > 0)
			{
				this.decrementWiggleTime();
				if (this.getAttackTarget() != null) this.setWiggleTime(0);
			}

			if (this.isFiring()) this.decrementFiringTicks();
			if (this.getAttackTime() > 0) this.decrementAttackTime();

			if (this.ticksExisted % 60 == 0 && rand.nextInt(4) == 0 && this.getAttackTarget() == null) this.setWiggleTime(20);

			if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) >= 2.0F && this.ticksExisted % 20 == 0 && rand.nextInt(4) == 0 && !this.isFiring())
			{
				this.setFiringTicks(120);
			}

			int rate = this.getToxType() == 0 ? 10 : 5;
			if (this.getFiringTicks() >= 40 && this.ticksExisted % rate == 0 && this.getAttackTarget() != null && this.canEntityBeSeen(this.getAttackTarget()) &&
					this.getDistanceToEntity(this.getAttackTarget()) >= 2.0F)
			{
				this.shootProjectiles();
			}

			if (this.ticksExisted % 60 == 0 && this.getHealth() < this.getMaxHealth()) this.heal(3.0F);
		}
	}

	protected void shootProjectiles()
	{
		this.fireProjectileAtEntity(new EntitySpore(this.worldObj), this.getAttackTarget(), 0F);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		if (this.isFiring()) this.setFiringTicks(19);
		if (this.getWiggleTime() > 0) this.setWiggleTime(0);

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (player.getCurrentEquippedItem() != null &&
					(player.getCurrentEquippedItem().getItem() instanceof ItemScythe || player.getCurrentEquippedItem().getItem() instanceof ItemAxe))
			{
				par2 *= 1.425;
			}
		}

		if (par1DamageSource.isFireDamage()) par2 *= 1.625;

		boolean result = super.attackEntityFrom(par1DamageSource, par2);
		if (result) this.setAttackTime(5);

		return result;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;

		boolean result = super.attackEntityAsMob(par1Entity);

		if (result && par1Entity instanceof EntityLivingBase && rand.nextInt(16) == 0 && !this.isImmuneToFire())
		{
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 600, 1));
		}

		return result;
	}

	@Override
	public int getTotalArmorValue()
	{
		
		return this.isFiring() ? 0 : TragicConfig.getMobStat(this.getToxType() == 0 ? "toxStats" : "poxStats").getArmorValue();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("toxType")) this.setToxType(tag.getByte("toxType"));
		if (tag.hasKey("firingTicks")) this.setFiringTicks(tag.getInteger("firingTicks"));
		if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
		if (tag.hasKey("wiggleTime")) this.setWiggleTime(tag.getInteger("wiggleTime"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setByte("toxType", this.getToxType());
		tag.setInteger("firingTicks", this.getFiringTicks());
		tag.setInteger("attackTime", this.getAttackTime());
		tag.setInteger("wiggleTime", this.getWiggleTime());
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance ins, IEntityLivingData data)
	{
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(new BlockPos((int) this.posX, 0, (int) this.posZ));
		this.setToxType(biome instanceof BiomeGenPaintedForest ? (byte) 1 : (byte) 0);
		return super.onInitialSpawn(ins, data);
	}

	@Override
	protected boolean isChangeAllowed() {
		return this.getToxType() == 0 && TragicConfig.getBoolean("allowMagmox");
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.tox.living" : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.tox.hurt" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.tox.hurt" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return this.getToxType() == 1 ? 1.4F : 1.0F;
	}

	@Override
	public float getSoundVolume()
	{
		return 0.9F + rand.nextFloat();
	}

	@Override
	public int getTalkInterval()
	{
		return super.getTalkInterval();
	}
	
	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		
	}
	
	@Override
	public String getVariantName()
    {
        return "TragicMC.Pox";
    }
	
	@Override
	protected void updateSize() {
		if (this.getToxType() == 0)
		{
			this.setSize(0.625F, 1.965F);
		}
		else
		{
			this.experienceValue = 6;
			this.setSize(0.625F * 0.635F, 1.965F * 0.635F);
		}
	}
}
