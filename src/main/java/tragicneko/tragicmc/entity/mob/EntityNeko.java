package tragicneko.tragicmc.entity.mob;

import java.util.Calendar;
import java.util.UUID;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;

public abstract class EntityNeko extends TragicMob {

	public static final int DW_FIRING_TICKS = 20;
	public static final int DW_THROWING_TICKS = 21;
	public static final int DW_ATTACK_TIME = 22;
	public static final int DW_FLICK_TIME = 23;
	public static final int DW_RELEASED = 24;

	private AttributeModifier mod = new AttributeModifier(UUID.fromString("ef7bc471-3df8-4d0d-8aa6-8f52ae0a6045"), "tragicNekoSpeedDebuff", TragicConfig.modifier[9], 0);

	public static final Predicate nekoTarget = new Predicate() {
		@Override
		public boolean apply(Object input) {
			return canApply((Entity) input);
		}

		public boolean canApply(Entity entity) {
			return entity instanceof EntityNeko && !((EntityNeko) entity).isReleased();
		}
	};

	public final EntityAIBase attackOnCollide = new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true);
	public final EntityAIBase hurtByNekos = new EntityAIHurtByTarget(this, true, EntityNeko.class);
	public final EntityAIBase moveTowardsTarget = new EntityAIMoveTowardsTarget(this, 0.85D, 32.0F);
	public final EntityAIBase targetPlayers = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, playerTarget);
	public final EntityAIBase targetUnreleasedNekos = new EntityAINearestAttackableTarget(this, EntityNeko.class, 0, true, false, nekoTarget);

	public EntityNeko(World par1World) {
		super(par1World);
		this.setSize(0.475F, 1.955F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		if (!this.isProperDate()) this.tasks.addTask(0, attackOnCollide);
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.65D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		if (!this.isProperDate()) this.tasks.addTask(1, moveTowardsTarget);
		if (!this.isReleased()) this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		else this.targetTasks.addTask(2, hurtByNekos);
		if (!this.isProperDate() && !this.isReleased()) this.targetTasks.addTask(3, targetPlayers);
		if (this.isReleased()) this.targetTasks.addTask(3, targetUnreleasedNekos);
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
		this.dataWatcher.addObject(DW_FIRING_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_THROWING_TICKS, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_ATTACK_TIME, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_FLICK_TIME, Integer.valueOf(0));
		this.dataWatcher.addObject(DW_RELEASED, (byte) 0);
	}

	public int getFiringTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_FIRING_TICKS);
	}

	protected void setFiringTicks(int i)
	{
		this.dataWatcher.updateObject(DW_FIRING_TICKS, i);
	}

	protected void incrementFiringTicks()
	{
		int pow = this.getFiringTicks();
		this.setFiringTicks(++pow);
	}

	public boolean isAboutToFire()
	{
		return this.getFiringTicks() > 0 && this.getFiringTicks() <= 40;
	}

	public boolean hasFired()
	{
		return this.getFiringTicks() > 40;
	}

	public boolean canFire()
	{
		return this.getFiringTicks() >= 80;
	}

	public int getThrowingTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_THROWING_TICKS);
	}

	protected void setThrowingTicks(int i)
	{
		this.dataWatcher.updateObject(DW_THROWING_TICKS, i);
	}

	protected void decrementThrowingTicks()
	{
		int pow = this.getThrowingTicks();
		this.setThrowingTicks(--pow);
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

	public int getFlickTime()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_FLICK_TIME);
	}

	protected void setFlickTime(int i)
	{
		this.dataWatcher.updateObject(DW_FLICK_TIME, i);
	}

	protected void decrementFlickTime()
	{
		int pow = this.getFlickTime();
		this.setFlickTime(--pow);
	}

	public boolean isReleased() {
		return this.dataWatcher.getWatchableObjectByte(DW_RELEASED) == 1;
	}

	protected void setReleased(boolean flag) {
		this.dataWatcher.updateObject(DW_RELEASED, flag ? (byte) 1 : (byte) 0);
	}

	public void releaseNeko(EntityPlayer player) {
		if (this.isReleased()) return;

		if (this.worldObj.isRemote)
		{
			for (byte i = 0; i < 8; i++) this.worldObj.spawnParticle(EnumParticleTypes.HEART, this.posX + rand.nextDouble() - rand.nextDouble(), this.posY + rand.nextDouble() * this.height,
					this.posZ + rand.nextDouble() - rand.nextDouble(), 1.0, 1.0, 1.0);
		}
		else
		{
			player.addChatComponentMessage(new ChatComponentText("Thanks for releasing me from that mind control~! Have a copy of one of my favorite songs!"));

			if (this.getAllowLoot())
			{
				final Item[] items = new Item[] {TragicItems.Starstruck, TragicItems.Faultless, TragicItems.Transmissions,
						TragicItems.Atrophy, TragicItems.Archaic, TragicItems.System, TragicItems.Mirrors,
						TragicItems.Untitled, TragicItems.Untitled2};
				this.entityDropItem(new ItemStack(items[rand.nextInt(items.length)]), 0.4F);
			}
		}
		this.setAttackTarget(null);
		this.setReleased(true);
		this.updateNekoTasks();

		if (TragicConfig.allowAchievements && player instanceof EntityPlayerMP) player.triggerAchievement(TragicAchievements.tragicNekoRelease);
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	@Override
	public void setInWeb() {}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			if ((this.isReleased() || this.isProperDate()) && this.ticksExisted % 10 == 0)
			{
				for (byte i = 0; i < 2; i++) this.worldObj.spawnParticle(EnumParticleTypes.HEART, this.posX + rand.nextDouble() - rand.nextDouble(), this.posY + rand.nextDouble() * this.height,
						this.posZ + rand.nextDouble() - rand.nextDouble(), 1.0, 1.0, 1.0);
			}
			return;
		}

		if (this.getAttackTarget() != null) this.incrementFiringTicks();
		if (this.getThrowingTicks() > 0) this.decrementThrowingTicks();
		if (this.getAttackTime() > 0) this.decrementAttackTime();
		if (this.getFlickTime() > 0) this.decrementFlickTime();

		if (this.isReleased() && this.getAttackTarget() != null && this.getAttackTarget() instanceof EntityNeko && ((EntityNeko) this.getAttackTarget()).isReleased())
		{
			this.setAttackTarget(null);
		}

		if (this.isReleased() && this.getAttackTarget() != null && this.getAttackTarget() instanceof EntityPlayer)
		{
			this.setAttackTarget(null);
		}
		
		if (this.getAttackTarget() == null)
		{
			if (this.ticksExisted % 20 == 0 && rand.nextInt(4) == 0) this.setFlickTime(10);
			this.setFiringTicks(35);
		}

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
		if (this.isAboutToFire() || this.getThrowingTicks() > 0) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
	}

	protected void updateNekoTasks() {
		if (this.isProperDate())
		{
			this.tasks.removeTask(moveTowardsTarget);
			this.targetTasks.removeTask(hurtByNekos);
			this.targetTasks.removeTask(targetPlayers);
			this.targetTasks.removeTask(targetUnreleasedNekos);
		}

		if (this.isReleased())
		{
			this.targetTasks.removeTask(targetPlayers);
			if (!this.targetTasks.taskEntries.contains(targetUnreleasedNekos)) this.targetTasks.addTask(3, targetUnreleasedNekos);
			if (!this.targetTasks.taskEntries.contains(hurtByNekos)) this.targetTasks.addTask(2, hurtByNekos);
		}
	}

	@Override
	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		if (!this.worldObj.isRemote) this.setThrowingTicks(20);
	}

	protected void doMissleAttack()
	{
		EntityLivingBase entity = this.getAttackTarget();
		double d0 = entity.posX - this.posX;
		double d1 = entity.getEntityBoundingBox().minY + entity.height / 2.0 - this.posY - 0.65;
		double d2 = entity.posZ - this.posZ;
		EntityNekoRocket rocket = new EntityNekoRocket(this.worldObj, this, d0, d1, d2);
		rocket.shootingEntity = this;
		rocket.target = entity;
		rocket.posY = this.posY + (0.65D);
		rocket.posX = this.posX - (Math.sin(this.rotationYaw * (float)Math.PI / 180.0F) * 0.025D);
		rocket.posZ = this.posZ - (Math.cos(this.rotationYaw * (float)Math.PI / 180.0F) * 0.025D);
		this.worldObj.spawnEntityInWorld(rocket);

		this.worldObj.playSoundAtEntity(this, "tragicmc:random.rocketlaunch", 1.0F, 1.0F);
		this.worldObj.playSoundAtEntity(this, "tragicmc:random.rocketflying", 1.0F, 1.0F);
	}

	protected void throwRandomProjectile()
	{
		EntityThrowable theProjectile = null;

		switch (rand.nextInt(5))
		{
		case 1:
		case 4:
			if (TragicConfig.tragicNekoStickyBombs) theProjectile = new EntityNekoStickyBomb(this.worldObj, this);
			break;
		case 2:
			if (TragicConfig.tragicNekoClusterBombs) theProjectile = new EntityNekoClusterBomb(this.worldObj, this);
			break;
		default:
			if (TragicConfig.tragicNekoClusterBombs) theProjectile = new EntityNekoMiniBomb(this.worldObj, this);
			break;
		}

		if (theProjectile == null) return;

		theProjectile.motionX = (this.getAttackTarget().posX - this.posX) * 0.335D;
		theProjectile.motionZ = (this.getAttackTarget().posZ - this.posZ) * 0.335D;
		theProjectile.motionY = (this.getAttackTarget().posY - this.posY) * 0.335D;
		this.worldObj.spawnEntityInWorld(theProjectile);
	}

	@Override
	public boolean getCanSpawnHere()
	{
		if (MathHelper.floor_double(this.getEntityBoundingBox().minY) <= this.worldObj.provider.getAverageGroundLevel())
		{
			return false;
		}
		else
		{
			return super.getCanSpawnHere() || this.isProperDate();
		}
	}

	public boolean isProperDate()
	{
		if (!TragicConfig.tragicNekoCelebration) return false;
		Calendar calendar = this.worldObj.getCurrentDate();

		if ((calendar.get(2) + 1 == 8 && calendar.get(5) > 29) || (calendar.get(2) + 1 == 9 || calendar.get(5) < 3))
		{
			return true;
		}

		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (par1DamageSource.isExplosion() || this.worldObj.isRemote) return false;

		if (this.getFiringTicks() < 60) this.setFiringTicks(61);
		boolean result = super.attackEntityFrom(par1DamageSource, par2);
		if (result) this.setAttackTime(10);

		return result;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.getThrowingTicks() > 0) return false;
		if (this.getFiringTicks() < 60) this.setFiringTicks(61);
		boolean result = super.attackEntityAsMob(par1Entity);
		if (result) this.setAttackTime(10);
		return result;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
		if (tag.hasKey("firingTicks")) this.setFiringTicks(tag.getInteger("firingTicks"));
		if (tag.hasKey("throwingTicks")) this.setThrowingTicks(tag.getInteger("throwingTicks"));
		if (tag.hasKey("flickTime")) this.setFlickTime(tag.getInteger("flickTime"));
		if (tag.hasKey("isReleased")) this.setReleased(tag.getBoolean("isReleased"));
		this.updateNekoTasks();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("attackTime", this.getAttackTime());
		tag.setInteger("firingTicks", this.getFiringTicks());
		tag.setInteger("throwingTicks", this.getThrowingTicks());
		tag.setInteger("flickTime", this.getFlickTime());
		tag.setBoolean("isReleased", this.isReleased());
	}

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
		return 0.8F;
	}

	@Override
	public int getTalkInterval()
	{
		return 320 + rand.nextInt(120);
	}

	@Override
	public int getDropAmount()
	{
		return 5;
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		if (player.getCurrentEquippedItem() != null)
		{
			ItemStack item = player.getCurrentEquippedItem();
			if (this.isProperDate() && (item.getItem() == Items.cake || item.getItem() == Item.getItemFromBlock(Blocks.tnt)))
			{
				if (this.worldObj.isRemote)
				{
					for (byte i = 0; i < 16; i++) this.worldObj.spawnParticle(EnumParticleTypes.HEART, this.posX + rand.nextDouble() - rand.nextDouble(), this.posY + rand.nextDouble() * this.height,
							this.posZ + rand.nextDouble() - rand.nextDouble(), 1.0, 1.0, 1.0);
				}
				else
				{
					ItemStack stack = new ItemStack(Items.fireworks, 1, 0);
					NBTTagCompound tag = new NBTTagCompound();
					NBTTagCompound newTag = new NBTTagCompound();
					newTag.setByte("Flight", (byte) 0);
					NBTTagList tagList = new NBTTagList();

					NBTTagCompound explosion = new NBTTagCompound();
					explosion.setByte("Type", (byte) 2);
					explosion.setByte("Fade", (byte) 1);
					explosion.setByte("Flicker", (byte)1 );
					explosion.setIntArray("Colors", new int[] {0xFF0000, 0x00FF00, 0x2378FF});
					explosion.setIntArray("FadeColors", new int[] {0xFFFFFF, 0xFFFFFF, 0xFFFFFF});
					tagList.appendTag(explosion);

					NBTTagCompound explosion2 = new NBTTagCompound();
					explosion2.setByte("Type", (byte) 4);
					explosion2.setByte("Trail", (byte) 1);
					explosion2.setIntArray("Colors", new int[] {0x00FF00, 0x4488FF, 0x45FF54});
					explosion2.setIntArray("FadeColors", new int[] {0x00FF00, 0x4488FF, 0x45FF54});
					tagList.appendTag(explosion2);

					NBTTagCompound explosion3 = new NBTTagCompound();
					explosion3.setByte("Type", (byte) 3);
					explosion3.setIntArray("Colors", new int[] {0xFF0023, 0x004400, 0x2378FF});
					explosion3.setIntArray("FadeColors", new int[] {0x00FF00, 0x00FF00, 0x00FF00});
					tagList.appendTag(explosion3);

					NBTTagCompound explosion4 = new NBTTagCompound();
					explosion4.setByte("Type", (byte) 0);
					explosion4.setByte("Trail", (byte) 1);
					explosion4.setIntArray("Colors", new int[] {0x000000, 0x000000, 0x000000});
					explosion4.setIntArray("FadeColors", new int[] {0xFF0000, 0xFF0000, 0xFF0000});
					tagList.appendTag(explosion4);

					NBTTagCompound explosion5 = new NBTTagCompound();
					explosion5.setByte("Type", (byte) 1);
					explosion5.setIntArray("Colors", new int[] {0xFF0023, 0xFF4400, 0xFF7844});
					explosion5.setIntArray("FadeColors", new int[] {0x46A0F3, 0xAF12EE, 0x4675FF});
					tagList.appendTag(explosion5);

					NBTTagCompound explosion6 = new NBTTagCompound();
					explosion6.setByte("Type", (byte) 0);
					explosion6.setIntArray("Colors", new int[] {0x4436FF, 0x2246FF, 0x3311FF});
					explosion6.setIntArray("FadeColors", new int[] {0x00FF00, 0x0036FF, 0x0000FF});
					tagList.appendTag(explosion6);

					newTag.setTag("Explosions", tagList);
					tag.setTag("Fireworks", newTag);
					stack.setTagCompound(tag);

					for (byte i = 0; i < 3; i++)
						this.worldObj.spawnEntityInWorld(new EntityFireworkRocket(this.worldObj, this.posX + rand.nextDouble() - rand.nextDouble(), this.posY + rand.nextDouble() * this.height,
								this.posZ + rand.nextDouble() - rand.nextDouble(), stack));
					player.addChatComponentMessage(new ChatComponentText("Thanks for celebrating my birthday with me~!"));
				}

				if (TragicConfig.allowAchievements && player instanceof EntityPlayerMP) player.triggerAchievement(TragicAchievements.tragicNeko);
				if (!player.capabilities.isCreativeMode) player.getCurrentEquippedItem().stackSize--;
				return true;
			}
			else if (item.getItem() == TragicItems.NekoMindControlDevice)
			{
				this.releaseNeko(player);
				if (!player.capabilities.isCreativeMode) player.getCurrentEquippedItem().stackSize--;
				return true;
			}
		}
		return false;
	}
}
