package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.inklingStats;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityAIBurn;

public class EntityInkling extends TragicMob {
	
	public static final int DW_VISIBLE_TICKS = 20;

	public EntityInkling(World par1World) {
		super(par1World);
		this.setSize(0.3F, 1.4F);
		this.experienceValue = this.getEntityId() % 7 == 0 || this.getEntityId() % 3 == 0 ? 6 : 5;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIFleeSun(this, 1.2D));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.8D, 32.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(8, new EntityAIBurn(this, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, playerTarget));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(DW_VISIBLE_TICKS, Integer.valueOf(0));
	}

	public int getVisibleTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_VISIBLE_TICKS);
	}

	private void setVisibleTicks(int i)
	{
		this.dataWatcher.updateObject(DW_VISIBLE_TICKS, i);
	}

	private void decrementVisibleTicks()
	{
		int pow = this.getVisibleTicks();
		this.setVisibleTicks(--pow);
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(inklingStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(inklingStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(inklingStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(inklingStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(inklingStats[4]);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote && this.getVisibleTicks() == 0 && rand.nextBoolean())
		{
			this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
					this.posX + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
					this.posY + this.rand.nextDouble() * this.height - 0.5D,
					this.posZ + (this.rand.nextDouble() - rand.nextDouble()) * this.width * 1.5D,
					(this.rand.nextDouble() - 0.6D) * 0.1D,
					this.rand.nextDouble() * 0.1D,
					(this.rand.nextDouble() - 0.6D) * 0.1D);
		}

		if (this.worldObj.isRemote) return;

		if (this.isBurning()) this.setVisibleTicks(20);

		if (this.getVisibleTicks() > 0 || !TragicConfig.inklingInvisibility)
		{
			this.decrementVisibleTicks();
			this.setInvisible(false);
		}
		else
		{
			this.setInvisible(true);
		}

		if (this.isBurning() && rand.nextInt(32) == 0)
		{
			this.teleportRandomly();
		}

		if (this.worldObj.getLightFor(EnumSkyBlock.BLOCK, this.getPosition()) >= 8)
		{
			this.setVisibleTicks(20);
			if (this.getAttackTarget() != null) this.setAttackTarget(null);
			if (TragicConfig.inklingTeleport && rand.nextBoolean()) this.teleportRandomly();
		}

		if (this.ticksExisted % 20 == 0 && rand.nextInt(8) == 0 && this.getAttackTarget() != null
				&& this.worldObj.getLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.getAttackTarget().posX, (int)this.getAttackTarget().posY + 1, (int)this.getAttackTarget().posZ)) <= 8 &&
				this.getDistanceToEntity(this.getAttackTarget()) >= 3.0F && this.canEntityBeSeen(this.getAttackTarget()) && TragicConfig.inklingTeleport)
		{
			this.teleportToEntity(this.getAttackTarget());
		}

		if (this.ticksExisted % 60 == 0 && this.getMobGriefing() && rand.nextBoolean() && TragicConfig.inklingTorchBreaking)
		{
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;

			for (int y1 = -4; y1 < 5; y1++)
			{
				for (int z1 = -4; z1 < 5; z1++)
				{
					for (int x1 = -4; x1 < 5; x1++)
					{
						Block block = worldObj.getBlockState(new BlockPos(x + x1, y + y1, z + z1)).getBlock();
						if (block instanceof BlockTorch)
						{
							this.worldObj.destroyBlock(new BlockPos(x + x1, y + y1, z + z1), true);
							return;
						}
					}
				}
			}
		}

		if (this.getAttackTarget() == null)
		{
			if (this.getVisibleTicks() == 0) this.setVisibleTicks(10);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		if (par1DamageSource.isFireDamage() && rand.nextInt(4) == 0 && TragicConfig.inklingTeleport) this.teleportRandomly();
		this.setVisibleTicks(10 + rand.nextInt(10));

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);
		if (result)
		{
			this.setVisibleTicks(10 + rand.nextInt(10));
			if (TragicConfig.allowMobSounds) this.playSound("tragicmc:mob.inkling.hey", this.getSoundVolume(), this.getSoundPitch());
		}

		return result;
	}

	@Override
	public int getTotalArmorValue()
	{
		if (this.isBurning()) return 0;
		return (int) inklingStats[5];
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("visibleTicks")) this.setVisibleTicks(this.getVisibleTicks());
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("visibleTicks", this.getVisibleTicks());
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.allowMobSounds ? (this.isInvisible() ? "tragicmc:mob.inkling.giggle" : "tragicmc:mob.inkling.hey") : null;
	}

	@Override
	public String getHurtSound()
	{
		return "tragicmc:mob.inkling.hurt";
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.allowMobSounds ? (this.getEntityId() % 7 == 0 || this.getEntityId() % 3 == 0 ? "tragicmc:mob.inkling.death" : "tragicmc:mob.inkling.hurt") : super.getHurtSound();
	}

	@Override
	public float getSoundPitch()
	{
		return 1.0F;
	}

	@Override
	public float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	public int getTalkInterval()
	{
		return 320;
	}
	
	@Override
	protected EnumParticleTypes getTeleportParticle() {
		return EnumParticleTypes.SMOKE_NORMAL;
	}
}
