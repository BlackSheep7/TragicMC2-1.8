package tragicneko.tragicmc.entity.mob;

import com.google.common.base.Predicate;

import net.minecraft.block.material.Material;
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
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;

public class EntityPirah extends TragicMob {
	
	public static final int DW_AIR_TICKS = 20;
	public static final int DW_TEXTURE_ID = 21;
	
	public static final Predicate nonSpeciesTarget = new Predicate() {
		@Override
		public boolean apply(Object input) {
			return canApply((Entity) input);
		}
		
		public boolean canApply(Entity entity) {
			return !(entity instanceof EntityPirah);
		}
	};

	public EntityPirah(World par1World) {
		super(par1World);
		this.experienceValue = 40;
		this.tasks.addTask(4, new EntityAIWander(this, 0.7D));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, false, nonSpeciesTarget));
	}

	@Override
	public boolean isMobVariant()
	{
		return this.getTextureID() == 7;
	}

	@Override
	public boolean canCorrupt()
	{
		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Beast;
	}

	@Override
	public boolean handleWaterMovement()
	{
		if (this.isBurning()) this.extinguish();
		return false;
	}

	@Override
	public void setAir(int i){}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		boolean flag = this.getTextureID() == 7 && TragicConfig.getBoolean("pirahGolden");
		double[] stats = TragicConfig.getMobStat(flag ? "goldenPirahStats" : "pirahStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(stats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(stats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(stats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(stats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(stats[4]);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(DW_AIR_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_TEXTURE_ID, (byte) 0);
	}

	protected void setAirTicks(int i)
	{
		this.dataWatcher.updateObject(DW_AIR_TICKS, i);
	}

	public int getAirTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_AIR_TICKS);
	}

	protected void incrementAirTicks()
	{
		int pow = this.getAirTicks();
		this.setAirTicks(++pow);
	}

	protected void decrementAirTicks()
	{
		int pow = this.getAirTicks();
		this.setAirTicks(--pow);
	}

	protected void setTextureID(byte b)
	{
		this.dataWatcher.updateObject(DW_TEXTURE_ID, b);
		if (b == 7 && TragicConfig.getBoolean("pirahGolden")) this.experienceValue = 12;
	}

	public byte getTextureID()
	{
		return this.dataWatcher.getWatchableObjectByte(DW_TEXTURE_ID);
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (this.worldObj.isRemote)
		{
			if (this.isInsideOfMaterial(getMaterial()))
			{
				//spawn bubble particles
			}
			else
			{
				//spawn splash particles
			}

			
			
		}
		else
		{
			if (this.isInsideOfMaterial(getMaterial()))
			{
				if (this.getAttackTarget() != null && !this.getAttackTarget().isInsideOfMaterial(getMaterial())) this.setAttackTarget(null);
				this.fallDistance = 0.0F;

				if (this.ticksExisted % 5 == 0)
				{
					this.motionY += ((rand.nextDouble() - rand.nextDouble()) + 0.625D) * 0.725D;
					this.motionX += ((rand.nextDouble() - rand.nextDouble())) * 0.825D;
					this.motionZ += ((rand.nextDouble() - rand.nextDouble())) * 0.825D;

					if (this.getAttackTarget() == null)
					{
						if (this.ticksExisted % 10 == 0)
						{
							this.motionX = (rand.nextDouble() - rand.nextDouble()) * 0.625D;
							this.motionY = (rand.nextDouble() - rand.nextDouble()) * 0.435D;
							this.motionZ = (rand.nextDouble() - rand.nextDouble()) * 0.625D;
						}
					}
					else
					{
						double d0 = this.getAttackTarget().posX - this.posX;
						double d1 = this.getAttackTarget().posY - this.posY;
						double d2 = this.getAttackTarget().posZ - this.posZ;
						float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
						this.motionX = d0 / f2 * 0.75D * 0.150000011920929D + this.motionX * 0.20000000298023224D;
						this.motionY = d1 * 0.10000000298023224D;
						this.motionZ = d2 / f2 * 0.75D * 0.150000011920929D + this.motionZ * 0.20000000298023224D;

						if (this.getAttackTarget().isDead) this.setAttackTarget(null);
					}
				}

				if (this.getAirTicks() < 100) this.incrementAirTicks();
			}
			else
			{
				this.setAttackTarget(null);
				if (this.getAirTicks() > 0) this.decrementAirTicks();

				if (this.ticksExisted % 20 == 0 && this.getAirTicks() == 0) this.attackEntityFrom(DamageSource.drown, 1.0F);
				if (this.ticksExisted % 4 == 0 && this.motionY <= 0.0D &&
						World.doesBlockHaveSolidTopSurface(this.worldObj, new BlockPos((int) this.posX, MathHelper.ceiling_double_int(this.posY) - 1, (int) this.posZ))) this.motionY = 0.35D + rand.nextDouble() * 0.25D;
				this.motionX = (rand.nextDouble() - rand.nextDouble());
				this.motionZ = (rand.nextDouble() - rand.nextDouble());
			}
		}
	}

	@Override
	public void moveEntity(double d0, double d1, double d2)
	{
		if (this.isInsideOfMaterial(getMaterial()))
		{
			super.moveEntity(d0, d1, d2);
		}
		else
		{
			super.moveEntity(d0 * 0.315D, d1, d2 * 0.315D);
		}
	}

	@Override
	public int getTotalArmorValue()
	{
		final boolean flag = this.getTextureID() == 7 && TragicConfig.getBoolean("pirahGolden");
		return TragicConfig.getMobStat(flag ? "goldenPirahStats" : "pirahStats").getArmorValue();
	}

	protected Material getMaterial() {
		return Material.water;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return this.posY > 35.0D && this.posY < 65.0D && this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox()) && this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && this.isInsideOfMaterial(this.getMaterial());
	}

	@Override
	public void fall(float dist, float multi) {
		if (this.worldObj.isRemote) return;
		if (!this.isInsideOfMaterial(getMaterial()))
		{
			this.motionX = rand.nextDouble() - rand.nextDouble();
			this.motionZ = rand.nextDouble() - rand.nextDouble();
			this.motionY = rand.nextDouble();
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("airTicks")) this.setAirTicks(tag.getInteger("airTicks"));
		if (tag.hasKey("textureID")) this.setTextureID(tag.getByte("textureID"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("airTicks", this.getAirTicks());
		tag.setByte("textureID", this.getTextureID());
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance ins, IEntityLivingData data)
	{
		if (!this.worldObj.isRemote)
		{
			byte i = (byte) rand.nextInt(8);
			if (i == 7) i = (byte) rand.nextInt(8); //make the 7 id less common
			this.setTextureID(i);
		}
		return super.onInitialSpawn(ins, data);
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public String getVariantName()
    {
        return "TragicMC.GoldenPirah";
    }
	
	@Override
	protected void updateSize() {
		float w = 0.325F;
		float h = 0.515F;

		if (this.getTextureID() == 7 && TragicConfig.getBoolean("pirahGolden"))
		{
			w *= 1.5F;
			h *= 1.5F;
		}
		
		this.setSize(w, h);
	}
}
