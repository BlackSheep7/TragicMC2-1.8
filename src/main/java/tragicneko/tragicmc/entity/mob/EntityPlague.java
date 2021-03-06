package tragicneko.tragicmc.entity.mob;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityPlague extends TragicMob {

	public EntityPlague(World par1World) {
		super(par1World);
		this.setSize(0.925F, 0.925F);
		this.stepHeight = 1.0F;
		this.experienceValue = 0;
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, playerTarget));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] plagueStats = TragicConfig.getMobStat("plagueStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(plagueStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(plagueStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(plagueStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(plagueStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(plagueStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return TragicConfig.getMobStat("plagueStats").getArmorValue();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return 0;
	}

	@Override
	public float getBrightness(float par1)
	{
		return 0.0F;
	}

	@Override
	public boolean canRenderOnFire()
	{
		return false;
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			for (byte l = 0; l < 2; ++l)
			{
				this.worldObj.spawnParticle(EnumParticleTypes.SPELL_WITCH,
						this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
						this.posY + this.rand.nextDouble() * this.height,
						this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
						(this.rand.nextDouble() - 0.6D) * 0.1D,
						this.rand.nextDouble() * 0.1D,
						(this.rand.nextDouble() - 0.6D) * 0.1D);
			}
		}
		else
		{
			if (this.ticksExisted % 120 == 0 && TragicConfig.getBoolean("allowCorruption"))
			{
				this.addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 200, 0));
			}

			if (this.ticksExisted % 5 == 0)
			{
				this.motionY = -rand.nextDouble() + 0.2;

				if (rand.nextInt(4) == 0 && WorldHelper.getDistanceToGround(this) < 10) this.motionY += rand.nextDouble() + 0.8;
				this.motionX = rand.nextDouble() * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
				this.motionZ = rand.nextDouble() * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
			}

			if (TragicConfig.getBoolean("allowCorruption") && this.ticksExisted % 60 == 0 && TragicConfig.getBoolean("plagueCorruption"))
			{
				int dif = this.worldObj.getDifficulty().getDifficultyId();
				double d0 = dif == 2 ? 6.0 : (dif == 3 ? 24.0 : 12.0);
				List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(d0, d0, d0));

				for (int i = 0; i < list.size(); i++)
				{
					Entity entity = list.get(i);

					if (!this.canEntityBeSeen(entity) || !(entity instanceof EntityLivingBase) || entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode) continue;

					if (entity instanceof TragicMob && ((TragicMob) entity).canCorrupt() || entity instanceof EntityGolem)
					{
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 800));
					}
					else if (entity instanceof EntityAnimal)
					{
						((EntityCreature) entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 400));
					}
					else if (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.isCreativeMode)
					{
						((EntityPlayer) entity).addPotionEffect(new PotionEffect(TragicPotion.Corruption.id, 200));
					}
				}
			}

			if (this.ticksExisted >= 600 && !this.hasCustomName())
			{
				this.attackEntityFrom(DamageSource.outOfWorld, Float.MAX_VALUE);
			}

			if (this.ticksExisted % 20 == 0 && TragicConfig.getBoolean("allowMobSounds"))
			{
				this.worldObj.playSoundAtEntity(this,"tragicmc:mob.plague.chirp", 0.3F, 1.0F);
			}
		}
	}

	@Override
	public void fall(float dist, float multi){}

	@Override
	public void updateFallState(double par1, boolean par2, Block block, BlockPos pos) {}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public String getLivingSound()
	{
		return null;
	}

	@Override
	public String getHurtSound()
	{
		return super.getHurtSound(); //"tragicmc:mob.plague.chirp";
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.plague.death" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return 1.0F;
	}

	@Override
	public float getSoundVolume()
	{
		return 0.4F + rand.nextFloat() * 0.2F;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		
	}

	@Override
	public int getTalkInterval()
	{
		return super.getTalkInterval();
	}
	
	@Override
	public boolean isBuffExempt() {
		return true;
	}
}
