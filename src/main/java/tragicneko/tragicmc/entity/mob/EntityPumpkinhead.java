package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.pumpkinheadStats;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityPumpkinhead extends TragicMob {
	
	public static final int DW_MOD_VALUE = 20;
	public static final int DW_HOME_COORD_X = 21;
	public static final int DW_HOME_COORD_Y = 22;
	public static final int DW_HOME_COORD_Z = 23;
	public static final int DW_ANGER_TICKS = 24;

	public EntityPumpkinhead(World par1World) {
		super(par1World);
		this.setSize(0.675F, 2.215F);
		this.experienceValue = 5;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIFleeSun(this, 1.2D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.65D));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
	}

	@Override
	public boolean canCorrupt()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(DW_MOD_VALUE, Float.valueOf(2.0F));
		this.dataWatcher.addObject(DW_HOME_COORD_X, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_HOME_COORD_Y, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_HOME_COORD_Z, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_ANGER_TICKS, Integer.valueOf(0));
	}

	public float getModValue()
	{
		return this.dataWatcher.getWatchableObjectFloat(DW_MOD_VALUE);
	}

	private void setModValue(float f)
	{
		this.dataWatcher.updateObject(DW_MOD_VALUE, f);
	}

	private void resetModValue()
	{
		this.dataWatcher.updateObject(DW_MOD_VALUE, 2.0F);
	}

	public BlockPos getHomeCoordinates()
	{
		return new BlockPos(this.dataWatcher.getWatchableObjectInt(DW_HOME_COORD_X), this.dataWatcher.getWatchableObjectInt(DW_HOME_COORD_Y), this.dataWatcher.getWatchableObjectInt(DW_HOME_COORD_Z));
	}

	private void setHomeCoordinates(BlockPos pos)
	{
		this.dataWatcher.updateObject(DW_HOME_COORD_X, pos.getX());
		this.dataWatcher.updateObject(DW_HOME_COORD_Y, pos.getY());
		this.dataWatcher.updateObject(DW_HOME_COORD_Z, pos.getZ());
		this.func_175449_a(pos, 12);
	}

	public boolean hasHomePumpkin()
	{
		BlockPos coords = this.getHomeCoordinates();
		return this.worldObj.getBlockState(coords).getBlock() == Blocks.pumpkin || this.worldObj.getBlockState(coords).getBlock() == Blocks.lit_pumpkin;
	}

	public int getAngerTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_ANGER_TICKS);
	}

	private void setAngerTicks(int i)
	{
		this.dataWatcher.updateObject(DW_ANGER_TICKS, i);
	}

	private void incrementAngerTicks()
	{
		int pow = this.getAngerTicks();
		this.setAngerTicks(++pow);
	}

	public boolean isAngry()
	{
		return this.getAngerTicks() > 0;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(pumpkinheadStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(pumpkinheadStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(pumpkinheadStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(pumpkinheadStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(pumpkinheadStats[4]);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (this.worldObj.isRemote)
		{
			if (this.hasHomePumpkin() && rand.nextBoolean())
			{
				if (rand.nextBoolean())
				{
					double d0 = this.getHomeCoordinates().getX() - this.posX + 0.5D;
					double d1 = this.getHomeCoordinates().getY() - this.posY - 0.5D;
					double d2 = this.getHomeCoordinates().getZ() - this.posZ + 0.5D;

					for (int i = 0; i < 4; i++)
					{
						double d3 = 0.23D * i + (rand.nextDouble() * 0.25D);
						this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX + d0 * d3, this.posY + d1 * d3 + 1.45D, this.posZ + d2 * d3, 0.0, 0.0, 0.0);
					}
				}

				for (int i = 0; i < 2; i++)
				{
					this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX + rand.nextDouble() - rand.nextDouble(), this.posY, this.posZ + rand.nextDouble() - rand.nextDouble(),
							0.0, rand.nextDouble() * 0.175D, 0.0);
				}
			}
			return;
		}

		if (!this.hasHomePumpkin() && this.ticksExisted % 120 == 0)
		{
			if (this.isPumpkinNearby()) this.setHomeCoordinates(getNearbyPumpkin());
		}

		if (this.isBurning())
		{
			this.attackEntityFrom(DamageSource.onFire, 1.0F);
		}
		else if (this.ticksExisted % 60 == 0 && this.getHealth() < this.getMaxHealth())
		{
			this.heal(3.0F);
		}

		if (this.getAttackTarget() != null)
		{
			this.incrementAngerTicks();
		}
		else
		{
			this.setAngerTicks(0);
			this.resetModValue();
		}

		if (!this.hasHomePumpkin())
		{
			this.detachHome();
			this.setAngerTicks(0);
			this.resetModValue();
		}

		if (TragicConfig.pumpkinheadHaste)
		{
			AttributeModifier mod = new AttributeModifier(UUID.fromString("2042ddcd-b29a-474f-acda-00ec1a2b4a2e"), "pumpkinheadHaste", this.getModValue(), 0);
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(mod);
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(mod);

			if (this.getAngerTicks() % 100 == 0 && this.isAngry() && this.hasHomePumpkin())
			{
				this.setModValue(this.getModValue() + 2.0F);
			}
		}

		if (this.getHealth() <= this.getMaxHealth() / 4 && this.hasHomePumpkin() && rand.nextInt(4) == 0 && this.ticksExisted % 10 == 0 && TragicConfig.pumpkinheadPumpkinbombs)
		{
			for (byte x = 0; x < 6; x++)
			{
				EntityPumpkinbomb bomb = new EntityPumpkinbomb(this.worldObj, this);
				bomb.motionX = bomb.motionZ = rand.nextDouble() - rand.nextDouble();
				bomb.motionY = rand.nextDouble() * 1.15D;
				bomb.setPosition(this.posX + bomb.motionX * 0.115D, this.posY + 1.5 + bomb.motionY * 0.115D, this.posZ + bomb.motionZ * 0.115D);
				this.worldObj.spawnEntityInWorld(bomb);
			}
		}
	}

	@Override
	public int getTotalArmorValue()
	{
		return this.hasHomePumpkin() ? (int) pumpkinheadStats[5] : MathHelper.floor_double(pumpkinheadStats[5] / 3);
	}

	public boolean isPumpkinNearby()
	{
		ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(worldObj, 6.0D, this.posX, this.posY, this.posZ);
		BlockPos coords;

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			if (this.worldObj.getBlockState(coords).getBlock() == Blocks.pumpkin || this.worldObj.getBlockState(coords).getBlock() == Blocks.lit_pumpkin) return true;
		}
		return false;
	}

	public BlockPos getNearbyPumpkin()
	{
		ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(worldObj, 6.0D, this.posX, this.posY, this.posZ);
		BlockPos coords;

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			if (this.worldObj.getBlockState(coords).getBlock() == Blocks.pumpkin || this.worldObj.getBlockState(coords).getBlock() == Blocks.lit_pumpkin) return coords;
		}

		return null;
	}

	public void createHomePumpkin()
	{
		if (!TragicConfig.pumpkinheadPumpkinSpawn) return;
		ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(worldObj, 6.0D, this.posX, this.posY, this.posZ);
		BlockPos coords;
		Block block;

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			block = this.worldObj.getBlockState(coords).getBlock();
			if (block.canBeReplacedByLeaves(worldObj, coords) && World.doesBlockHaveSolidTopSurface(worldObj, coords.down()))
			{
				this.worldObj.setBlockState(coords, Blocks.lit_pumpkin.getDefaultState());
				this.setHomeCoordinates(coords);
				break;
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (result && par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase && rand.nextBoolean() && !par1DamageSource.isMagicDamage())
		{
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(12.0, 12.0, 12.0));
			EntityPumpkinhead mob;

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityPumpkinhead)
				{
					mob = (EntityPumpkinhead) list.get(i);
					if (mob.getAttackTarget() == null && this.getAttackTarget() != null) mob.setAttackTarget(this.getAttackTarget());
					if (mob.getHomeCoordinates() == this.getHomeCoordinates() && mob.hasHomePumpkin() && this.hasHomePumpkin()) mob.attackEntityFrom(DamageSource.magic, 1.0F);
				}
			}
		}

		return result;
	}

	@Override
	public IEntityLivingData func_180482_a(DifficultyInstance ins, IEntityLivingData data)
	{
		if (!this.isPumpkinNearby())
		{
			if (rand.nextInt(32) == 0 && this.getMobGriefing()) this.createHomePumpkin();
		}
		else
		{
			this.setHomeCoordinates(getNearbyPumpkin());
		}

		return super.func_180482_a(ins, data);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("homeCoords")) this.setHomeCoordinates(convertArrayToPos(tag.getIntArray("homeCoords")));
		if (tag.hasKey("angerTicks")) this.setAngerTicks(tag.getInteger("angerTicks"));
		if (tag.hasKey("modValue")) this.setModValue(tag.getFloat("modValue"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		if (this.hasHomePumpkin()) tag.setIntArray("homeCoords", convertPosToArray(this.getHomeCoordinates()));
		tag.setInteger("angerTicks", this.getAngerTicks());
		tag.setFloat("modValue", this.getModValue());
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.allowMobSounds ? (this.isAngry() ? "tragicmc:mob.pumpkinhead.angry" : "tragicmc:mob.pumpkinhead.living") : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.allowMobSounds ? "tragicmc:mob.pumpkinhead.hiss" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.allowMobSounds ? "tragicmc:mob.pumpkinhead.death" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return 0.8F;
	}

	@Override
	public float getSoundVolume()
	{
		return 0.4F + rand.nextFloat() * 0.2F;
	}

	@Override
	public int getTalkInterval()
	{
		return this.isAngry() ? 40 : 320;
	}
	
	public static int[] convertPosToArray(BlockPos pos) {
		return new int[] {pos.getX(), pos.getY(), pos.getZ()};
	}
	
	public static BlockPos convertArrayToPos(int[] array) {
		return new BlockPos(array[0], array[1], array[2]);
	}
}
