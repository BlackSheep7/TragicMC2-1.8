package tragicneko.tragicmc.entity.mob;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.worldgen.biome.BiomeGenAshenHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCorrodedSteppe;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDarkForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.biome.BiomeGenHallowedHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;

public class EntityWisp extends TragicMob {
	
	public static final int DW_IDLE_STATE = 20;
	public static final int DW_IDLE_TICKS = 21;
	
	public static final Predicate golemTarget = new Predicate() {
		@Override
		public boolean apply(Object input) {
			return canApply((Entity) input);
		}
		
		public boolean canApply(Entity entity) {
			return entity instanceof EntityGolem;
		}
	};

	public EntityWisp(World par1World) {
		super(par1World);
		this.setSize(0.36F, 1.36F);	
		this.experienceValue = 30;
		this.tasks.addTask(0, new EntityAIPanic(this, 1.0D));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, playerTarget, 6.0F, 1.0D, 1.6D));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityGolem.class, golemTarget, 6.0F, 1.0D, 1.6D));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.45D));
		this.stepHeight = 1.0F;
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(DW_IDLE_STATE, Integer.valueOf(0)); //modifier
		this.dataWatcher.addObject(DW_IDLE_TICKS, Integer.valueOf(0)); //ticks
	}

	public int getIdleTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_IDLE_TICKS);
	}

	public int getIdleState()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_IDLE_STATE);
	}

	private void setIdleTicks(int i)
	{
		this.dataWatcher.updateObject(DW_IDLE_TICKS, i);
	}

	private void setIdleState(int i)
	{
		this.dataWatcher.updateObject(DW_IDLE_STATE, i);
	}

	private void decrementIdleTicks()
	{
		int pow = this.getIdleTicks();
		this.setIdleTicks(--pow);
	}

	@Override
	public boolean canCorrupt()
	{
		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float p_70070_1_)
	{
		return 15728880;
	}

	@Override
	public float getBrightness(float p_70013_1_)
	{
		return 1.0F;
	}

	@Override
	public void fall(float dist, float multi) {}

	@Override
	public boolean isBurning()
	{
		return false;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] kindlingSpiritStats = TragicConfig.getMobStat("kindlingSpiritStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(kindlingSpiritStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(kindlingSpiritStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(kindlingSpiritStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(kindlingSpiritStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(kindlingSpiritStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return TragicConfig.getMobStat("kindlingSpiritStats").getArmorValue();
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			EnumParticleTypes s = EnumParticleTypes.FLAME;
			BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(new BlockPos((int) this.posX, 0, (int) this.posZ));
			if (biome instanceof BiomeGenStarlitPrarie || biome instanceof BiomeGenDarkForest) s = EnumParticleTypes.SPELL_WITCH;
			if (biome instanceof BiomeGenAshenHills) s = EnumParticleTypes.SMOKE_NORMAL;
			if (biome instanceof BiomeGenPaintedForest) s = EnumParticleTypes.CRIT_MAGIC;
			if (biome instanceof BiomeGenHallowedHills) s = EnumParticleTypes.CRIT;
			if (biome instanceof BiomeGenCorrodedSteppe) s = EnumParticleTypes.SPELL_MOB_AMBIENT;
			if (biome instanceof BiomeGenFrozenTundra) s = EnumParticleTypes.SNOW_SHOVEL;

			for (byte i = 0; i < 2 && this.getIdleTicks() == 0; i++)
			{
				this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
			}

			if (this.getIdleTicks() > 0)
			{
				switch(this.getIdleState())
				{
				default:
					if (this.ticksExisted % 10 == 0)
					{
						for (byte i = 0; i < 5; i++)
						{
							this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.155D), this.posY + 0.115D + rand.nextDouble(),
									this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.155D), rand.nextFloat() * 0.114F - rand.nextFloat() * 0.114F,
									0.155F * this.rand.nextFloat(), rand.nextFloat() * 0.114F - rand.nextFloat() * 0.114F);
						}

						this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
								this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
					}
					break;
				case 1:
					for (byte i = 0; i < 3; i++)
					{
						this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.655D), this.posY + 0.115D + rand.nextDouble(),
								this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.655D), rand.nextFloat() * 0.07F - rand.nextFloat() * 0.07F, 0.255F * this.rand.nextFloat(),
								rand.nextFloat() * 0.07F - rand.nextFloat() * 0.07F);
					}
					break;
				case 2:
					if (this.ticksExisted % 40 == 0)
					{
						for (byte i = 0; i < 2; i++)
						{
							this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.455D), this.posY + 0.115D + rand.nextDouble(),
									this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.455D), rand.nextFloat() * 0.114F - rand.nextFloat() * 0.114F, 0.155F * this.rand.nextFloat(),
									rand.nextFloat() * 0.114F - rand.nextFloat() * 0.114F);
						}
					}
					else
					{
						this.worldObj.spawnParticle(s, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
								this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
					}
					break;
				}
			}
		}
		else
		{
			if (this.getIdleTicks() > 0) this.decrementIdleTicks();

			if (this.ticksExisted % 20 == 0 && rand.nextInt(8) == 0 && this.getAttackTarget() == null && this.getIdleTicks() == 0)
			{
				int i = rand.nextInt(4);
				this.setIdleTicks(i != 1 ? 40 : 60);
				this.setIdleState(i);
			}

			if (this.isInWater() && this.ticksExisted % 10 == 0) this.attackEntityFrom(DamageSource.drown, 1.0F);
		}
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.wisp.joy" : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.wisp.fear" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.wisp.release" : null;
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
	public int getTalkInterval()
	{
		return 220;
	}
	
	@Override
	public boolean getIllumination() {
		return true;
	}
	
	@Override
	public boolean isBuffExempt() {
		return true;
	}
}
