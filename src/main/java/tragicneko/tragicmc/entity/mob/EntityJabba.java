package tragicneko.tragicmc.entity.mob;

import java.util.UUID;

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
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.miniboss.TragicMiniBoss;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;

public class EntityJabba extends TragicMob {

	public static final int DW_ANGER_TICKS = 20;
	public static final int DW_JABBA_TYPE = 21;
	public static final int DW_ATTACK_TICKS = 22;
	public static final int DW_WORM_TICKS = 23;
	
	private static AttributeModifier lowHealthDamageBoost = new AttributeModifier(UUID.fromString("8c159dc4-aacf-461f-b3e9-66dc9fbf6e99"), "jabbaLowHealthDamageBoost", TragicConfig.modifier[6], 0);

	public EntityJabba(World par1World) {
		super(par1World);
		this.stepHeight = 1.0F;
		this.experienceValue = 50;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.65D));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, playerTarget));
		if (this.superiorForm == null && !(this instanceof TragicMiniBoss)) this.superiorForm = EntityJarra.class;
	}

	@Override
	protected boolean canCorrupt()
	{
		return this.getJabbaType() == 0;
	}

	@Override
	public boolean isMobVariant()
	{
		return this.getJabbaType() == 1;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(DW_ANGER_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_JABBA_TYPE, (byte) 0);
		this.dataWatcher.addObject(DW_ATTACK_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_WORM_TICKS, Integer.valueOf(0));
	}

	protected void setAngerTicks(int i)
	{
		this.dataWatcher.updateObject(DW_ANGER_TICKS, i);
	}

	public int getAngerTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_ANGER_TICKS);
	}

	protected void incrementAngerTicks()
	{
		int pow = this.getAngerTicks();
		this.setAngerTicks(++pow);
	}

	protected void decrementAngerTicks()
	{
		int pow = this.getAngerTicks();
		this.setAngerTicks(--pow);
	}

	public byte getJabbaType()
	{
		return this.dataWatcher.getWatchableObjectByte(DW_JABBA_TYPE);
	}

	protected void setJabbaType(byte b)
	{
		this.dataWatcher.updateObject(DW_JABBA_TYPE, b);
	}

	public int getAttackTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_ATTACK_TICKS);
	}

	protected void setAttackTicks(int i)
	{
		this.dataWatcher.updateObject(DW_ATTACK_TICKS, i);
	}

	protected void decrementAttackTicks()
	{
		int pow = this.getAttackTicks();
		this.setAttackTicks(--pow);
	}

	public int getWormTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_WORM_TICKS);
	}

	protected void setWormTicks(int i)
	{
		this.dataWatcher.updateObject(DW_WORM_TICKS, i);
	}

	protected void decrementWormTicks()
	{
		int pow = this.getWormTicks();
		this.setWormTicks(--pow);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return 15728880;
	}

	@Override
	public float getBrightness(float par1)
	{
		return 1.0F;
	}

	@Override
	public boolean canRenderOnFire()
	{
		return false;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] stats = TragicConfig.getMobStat(this.getJabbaType() == 0 ? "jabbaStats" : "jannaStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(stats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(stats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(stats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(stats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(stats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return TragicConfig.getMobStat(this.getJabbaType() == 0 ? "jabbaStats" : "jannaStats").getArmorValue();
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getWormTicks() > 0) this.motionX = this.motionZ = 0.0D;

		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			this.doParticleEffects();
		}
		else
		{
			if (this.getAttackTarget() != null && this.getWormTicks() > 0) this.setWormTicks(0);
			if (this.getWormTicks() > 0) this.decrementWormTicks();
			if (this.getAttackTicks() > 0) this.decrementAttackTicks();

			if (this.getJabbaType() == 0 && TragicConfig.getBoolean("jabbaAnger"))
			{
				if (this.getAttackTarget() != null)
				{
					this.incrementAngerTicks();
					if (this.isCorrupted()) this.incrementAngerTicks();
				}
				else
				{
					if (this.getAngerTicks() > 0) this.decrementAngerTicks();
				}
			}
			else
			{
				this.setAngerTicks(0);
			}

			if (this.getAngerTicks() >= 400 && this.getAttackTarget() != null)
			{
				if (this.ticksExisted % 100 == 0) this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1.5F * rand.nextFloat(), false);
				if (this.ticksExisted % 40 == 0) this.spawnProjectiles();
				if (this.getAttackTicks() == 0) this.setAttackTicks(10);
			}

			if (this.isWet()) this.attackEntityFrom(DamageSource.drown, 5.0F);

			if (this.ticksExisted % 20 == 0 && this.rand.nextInt(3) == 0)
			{
				EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 10.0);

				if (player != null && TragicConfig.getBoolean("allowDoom") && this.canEntityBeSeen(player))
				{
					PropertyDoom doom = PropertyDoom.get(player);
					int i = this.worldObj.getDifficulty().getDifficultyId();

					if (doom != null) doom.increaseDoom(-((this.rand.nextInt(3) + 1) * i));
				}
			}

			if (this.getAttackTarget() == null && this.ticksExisted % 60 == 0 && rand.nextInt(32) == 0 && this.getAttackTicks() == 0)
			{
				this.setAttackTicks(60);
			}

			if (this.getAttackTarget() == null && this.ticksExisted % 10 == 0 && rand.nextInt(48) == 0 && this.getWormTicks() == 0)
			{
				this.setWormTicks(60);
			}

			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(lowHealthDamageBoost);
			if (this.getHealth() <= this.getMaxHealth() / 2) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(lowHealthDamageBoost);
		}
	}

	protected void doParticleEffects() {

		EnumParticleTypes s1 = this.getJabbaType() == 0 ? EnumParticleTypes.DRIP_LAVA : EnumParticleTypes.SLIME;

		for (byte k = 0; k < 3; ++k)
		{
			this.worldObj.spawnParticle(s1,
					this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 0.95D,
					this.posY + (rand.nextDouble() * 0.15D),
					this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 0.95D,
					0.0, 0.0, 0.0);
		}


		if (this.getHealth() > this.getMaxHealth() / 2) return;

		EnumParticleTypes s = this.getJabbaType() == 0 ? EnumParticleTypes.FLAME : EnumParticleTypes.SLIME;

		for (byte l = 0; l < 3; ++l)
		{
			this.worldObj.spawnParticle(s,
					this.posX + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
					this.posY + this.rand.nextDouble() * this.height,
					this.posZ + (this.rand.nextDouble() - 0.5D) * this.width * 2.5D,
					(this.rand.nextDouble() - 0.6D) * 0.1D,
					this.rand.nextDouble() * 0.1D,
					(this.rand.nextDouble() - 0.6D) * 0.1D);
		}
	}

	protected void spawnProjectiles()
	{
		if (!TragicConfig.getBoolean("jabbaProjectiles")) return;
		EntityLivingBase entity = this.getAttackTarget();
		double d0 = entity.posX - this.posX;
		double d1 = entity.getEntityBoundingBox().minY + entity.height / 2.0F - (this.posY + this.height / 2.0F);
		double d2 = entity.posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(entity)) * 0.5F;

		for (byte i = 0; i < 5; ++i)
		{
			EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
			entitysmallfireball.posY = this.posY + 0.5D;
			this.worldObj.spawnEntityInWorld(entitysmallfireball);
			if (this.getAngerTicks() >= 50) this.setAngerTicks(this.getAngerTicks() - 50);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		if (this.getHealth() <= this.getMaxHealth() / 2) par2 /= 2;

		Boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (result && this.getWormTicks() > 0) this.setWormTicks(0);

		if (this.rand.nextInt(8) == 0 && this.worldObj.getDifficulty() == EnumDifficulty.HARD && result)
		{
			if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer && !par1DamageSource.isProjectile() && !par1DamageSource.isMagicDamage() && !par1DamageSource.isFireDamage())
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

				if (player.getCurrentEquippedItem() != null && !player.capabilities.isCreativeMode && rand.nextBoolean())
				{
					player.dropOneItem(true);
				}
				else
				{
					if (this.rand.nextInt(4) == 0) player.setFire(4 + rand.nextInt(3));
				}

				if (!player.onGround) this.setAngerTicks(this.getAngerTicks() + 25);
			}
		}

		if (result || par1DamageSource == DamageSource.drown)
		{
			this.setAngerTicks(this.getAngerTicks() + 50);
			this.setAttackTicks(5);
		}

		return result;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);

		int i = MathHelper.clamp_int(this.worldObj.getDifficulty().getDifficultyId(), 1, 3);

		if (this.rand.nextInt(MathHelper.ceiling_double_int(9 / i)) == 0 && result)
		{
			if (par1Entity instanceof EntityLivingBase)
			{
				if (this.getJabbaType() == 0)
				{
					if (this.rand.nextInt(8) == 0)
					{
						par1Entity.setFire(2 * i);
					}
				}
				else
				{
					if (this.rand.nextInt(4) == 0)
					{
						((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 120 * i, i));
					}
				}

				if (par1Entity instanceof EntityPlayer && i >= 3 && rand.nextBoolean())
				{
					EntityPlayer player = (EntityPlayer) par1Entity;
					if (player.getCurrentEquippedItem() != null && !player.capabilities.isCreativeMode) player.dropOneItem(true);
				}
			}
		}

		if (result)
		{
			this.setAngerTicks(this.getAngerTicks() + 10);
			this.setAttackTicks(10);
		}

		return result;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("jabbaType")) this.setJabbaType(tag.getByte("jabbaType"));
		if (tag.hasKey("angerTicks")) this.setAngerTicks(tag.getInteger("angerTicks"));
		if (tag.hasKey("wormTicks")) this.setWormTicks(tag.getInteger("wormTicks"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setByte("jabbaType", this.getJabbaType());
		tag.setInteger("angerTicks", this.getAngerTicks());
		tag.setInteger("wormTicks", this.getWormTicks());
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance ins, IEntityLivingData data)
	{
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(new BlockPos((int) this.posX, 0, (int) this.posZ));
		this.setJabbaType(biome instanceof BiomeGenPaintedForest ? (byte) 1 : 0);
		return super.onInitialSpawn(ins, data);
	}

	@Override
	protected boolean isChangeAllowed() {
		return this.getJabbaType() == 0 && TragicConfig.getBoolean("allowJarra");
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.jabba.squish" : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.jabba.hurt" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.jabba.hurt" : null;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		if (TragicConfig.getBoolean("allowMobSounds")) this.playSound("tragicmc:mob.jabba.squish", 0.45F, 1.0F);
	}

	@Override
	public float getSoundPitch()
	{
		return this.getJabbaType() == 0 ? super.getSoundPitch() : super.getSoundPitch() + 0.4F + rand.nextFloat();
	}

	@Override
	public float getSoundVolume()
	{
		return 0.1F + rand.nextFloat() * 0.1F;
	}
	
	@Override
	public String getVariantName()
    {
        return "TragicMC.Janna";
    }
	
	@Override
	public boolean getIllumination() {
		return true;
	}
	
	@Override
	protected void updateSize() {
		if (this.getJabbaType() == 0)
		{
			this.setSize(1.425F, 0.625F);
			this.isImmuneToFire = true;
		}
		else
		{
			this.setSize(1.425F * 0.825F, 0.625F * 0.825F);
			this.isImmuneToFire = false;
			this.experienceValue = 6;
		}
	}
}
