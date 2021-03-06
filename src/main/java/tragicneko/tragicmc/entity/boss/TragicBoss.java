package tragicneko.tragicmc.entity.boss;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.util.EntityDropHelper;
import tragicneko.tragicmc.util.WorldHelper;

public abstract class TragicBoss extends EntityMob implements IBossDisplayData
{
	private boolean hasDamagedEntity = false; //for achievement can't touch this, kill a boss without being hurt by it
	private int xpAmount = 0;

	public TragicBoss(World par1World) {
		super(par1World);
		this.experienceValue = 1000;
	}

	@Override
	protected void dropFewItems(boolean flag, int l)
	{
		super.dropFewItems(flag, l);

		int x = 3 + l;
		int amt = 0;

		if (TragicConfig.getBoolean("allowExtraBossLoot"))
		{
			int amount = rand.nextInt(6) + 4 * x;

			for (int i = 0; i < amount; i++)
			{
				if (rand.nextBoolean())
				{
					ItemStack luxuryDrop = EntityDropHelper.getLuxuryDropForBoss();
					if (luxuryDrop != null) this.capturedDrops.add(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, luxuryDrop));
					amt++;
				}

				if (amt >= 6 + x) break;
			}
		}

		int total = 0;

		for (int i = 0; i < x + 3; i++)
		{
			if (rand.nextInt(100) <= TragicConfig.getInt("commonMobDropChance") + (x * 4))
			{
				ItemStack drop = EntityDropHelper.getDropFromEntity(this.getClass(), true);
				if (drop != null) this.capturedDrops.add(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, drop));
				total += 1;
			}

			if (flag && rand.nextInt(25) <= TragicConfig.getInt("rareMobDropChance") + x)
			{
				ItemStack drop = EntityDropHelper.getDropFromEntity(this.getClass(), false);
				if (drop != null) this.capturedDrops.add(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, drop));
				total += 1;
			}

			if (total >= x * 2.5) break;
		}

		if (flag)
		{
			ItemStack drop = EntityDropHelper.getDropFromEntity(this.getClass(), false);
			if (drop != null) this.capturedDrops.add(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, drop));

			if (TragicConfig.getBoolean("allowNonMobItems"))
			{
				drop = new ItemStack(TragicItems.EtherealDistortion);
				this.capturedDrops.add(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, drop));
			}
		}
	}

	@Override
	public void onDeath(DamageSource par1)
	{
		super.onDeath(par1);

		if (this.worldObj.isRemote) return;

		ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(this.worldObj, 4.0D, this.posX, this.posY, this.posZ);
		BlockPos coords;

		for (int i = 0; i < list.size(); i++)
		{
			coords= list.get(i);
			if (this.worldObj.getBlockState(coords).getBlock().getMaterial() == Material.fire) this.worldObj.setBlockToAir(coords);
		}

		if (par1.getEntity() != null && par1.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1.getEntity();

			if (TragicConfig.getBoolean("allowAchievements") && player instanceof EntityPlayerMP) player.triggerAchievement(TragicAchievements.killBoss);

			if (!this.hasDamagedEntity && TragicConfig.getBoolean("allowAchievements") && player instanceof EntityPlayerMP)
			{
				player.triggerAchievement(TragicAchievements.cantTouchThis);
			}
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("hasDamagedEntity")) this.hasDamagedEntity = tag.getBoolean("hasDamagedEntity"); 
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setBoolean("hasDamagedEntity", this.hasDamagedEntity);
	}

	@Override
	public void onLivingUpdate()
	{
		if (TragicConfig.getBoolean("allowCorruption") && this.isPotionActive(TragicPotion.Corruption)) this.removePotionEffect(TragicPotion.Corruption.id);
		super.onLivingUpdate();
		if (this.getAttackTarget() != null && this.getAttackTarget().isDead) this.setAttackTarget(null);
		if (this.getAttackTarget() != null && !TragicConfig.getBoolean("allowMobInfighting") && (this.getAttackTarget() instanceof TragicMob || this.getAttackTarget() instanceof TragicBoss)) this.setAttackTarget(null);

		if (this.worldObj.getDifficulty() == EnumDifficulty.EASY && !TragicConfig.getBoolean("allowEasyBosses") || this.posY <= -30 || this.posY > 280) this.setDead();

		if (!this.worldObj.isRemote && this.getIllumination() && TragicConfig.getBoolean("allowMobIllumination") && this.ticksExisted % 4 == 0)
		{
			int w = MathHelper.floor_float(this.width);
			int h = MathHelper.floor_float(this.height);
			if (w < 2) w = 2;
			if (h < 2) h = 2;
			final BlockPos pos = WorldHelper.getBlockPos(this).add(rand.nextInt(w) - rand.nextInt(w), rand.nextInt(h) - rand.nextInt(h) + this.height * 2 / 3, rand.nextInt(w) - rand.nextInt(w));
			if (EntityOverlordCore.replaceableBlocks.contains(WorldHelper.getBlock(this.worldObj, pos))) this.worldObj.setBlockState(pos, TragicBlocks.Luminescence.getDefaultState());
		}

		if (!this.worldObj.isRemote && TragicConfig.getBoolean("bossesDenyFlight"))
		{
			List<EntityPlayerMP> list = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, new AxisAlignedBB(0, 0, 0, 1, 1, 1).offset(this.posX, this.posY, this.posZ).expand(64.0, 64.0, 64.0));

			for (EntityPlayerMP mp : list)
			{
				if (!mp.capabilities.isCreativeMode) mp.capabilities.allowFlying = false;
				if (TragicConfig.getBoolean("allowFlight") && mp.isPotionActive(TragicPotion.Flight)) mp.removePotionEffect(TragicPotion.Flight.id);
			}
		}
	}

	@Override
	public boolean getCanSpawnHere()
	{
		if (rand.nextInt(10) != 0) return false;

		if (this.posY <= 63)
		{
			switch (this.worldObj.provider.getDimensionId())
			{
			case 0:
				return false;
			case 1:
				return rand.nextInt(4) == 0 ? false : super.getCanSpawnHere();
			case -1:
				return false;
			default:
				if (this.worldObj.provider instanceof TragicWorldProvider)
				{
					return super.getCanSpawnHere();
				}
				else
				{
					return false;
				}
			}
		}
		return super.getCanSpawnHere();
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (TragicConfig.getBoolean("allowStun") && this.isPotionActive(TragicPotion.Stun)) return false;

		boolean flag = super.attackEntityAsMob(par1Entity);

		if (flag) this.hasDamagedEntity = true;
		return flag;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.worldObj.isRemote) return false;

		if (par1DamageSource.getEntity() != null)
		{
			if (par1DamageSource.getEntity() instanceof EntityPlayer && par2 >= TragicConfig.getInt("bossDamageCap"))
			{
				EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();
				boolean flag = player.getCurrentEquippedItem() == null ? false : (player.getCurrentEquippedItem().getItem() == TragicItems.BowOfJustice || player.getCurrentEquippedItem().getItem() == TragicItems.SwordOfJustice);

				if (!player.capabilities.isCreativeMode || !flag) par2 = MathHelper.clamp_float(par2, 0.0F, (float) TragicConfig.getInt("bossDamageCap"));
			}

			if (rand.nextBoolean() && this.getAttackTarget() != null && par1DamageSource.getEntity() instanceof EntityLivingBase && this.getAttackTarget() != par1DamageSource.getEntity()) this.setAttackTarget((EntityLivingBase) par1DamageSource.getEntity());
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	public boolean getMobGriefing()
	{
		return this.worldObj.getGameRules().getBoolean("mobGriefing");
	}

	public boolean getAllowLoot()
	{
		return this.worldObj.getGameRules().getBoolean("doMobLoot");
	}

	public boolean isEntityInRange(Entity entity, float min, float max)
	{
		float f = this.getDistanceToEntity(entity);
		return f >= min && f <= max;
	}

	public int getIntegerInRange(int min, int max)
	{
		int cand = MathHelper.getRandomIntegerInRange(rand, min, max);
		return rand.nextBoolean() ? cand : -cand;
	}

	public int getPlayersNearby() {
		return this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(0, 0, 0, 1, 1, 1).offset(this.posX, this.posY, this.posZ).expand(64.0, 64.0, 64.0)).size();
	}

	public int getPlayersNearby(int min, int max)
	{
		return MathHelper.clamp_int(getPlayersNearby(), min, max);
	}

	public int getPlayersNearby(double range)
	{
		return this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(0, 0, 0, 1, 1, 1).offset(this.posX, this.posY, this.posZ).expand(range, range, range)).size();
	}

	public int getPlayersNearby(double range, int min, int max)
	{
		return MathHelper.clamp_int(getPlayersNearby(range), min, max);
	}

	public void healByFactor(float factor)
	{
		int i = this.getPlayersNearby();
		this.heal(factor * i);
	}

	public void healByFactorRanged(float factor, float min, float max)
	{
		int i = this.getPlayersNearby();
		float f = MathHelper.clamp_float(factor * i, min, max);
		this.heal(f);
	}

	public int getHighestSolidBlock(int posX, int posY, int posZ) {
		while(this.worldObj.getBlockState(new BlockPos(posX, posY, posZ)).getBlock().getMaterial() == Material.air && posY > 0)
		{
			--posY;
		}
		return posY;
	}

	public boolean isHalloween()
	{
		Calendar calendar = this.worldObj.getCurrentDate();

		if ((calendar.get(2) + 1 == 10 && calendar.get(5) > 29) || (calendar.get(2) + 1 == 11 || calendar.get(5) < 3))
		{
			return true;
		}

		return false;
	}

	public boolean getIllumination()
	{
		return false;
	}

	protected boolean teleportRandomly()
	{
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 24.0D;
		double d1 = this.posY + (this.rand.nextInt(64) - 32);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 24.0D;
		return this.teleportTo(d0, d1, d2);
	}

	protected boolean teleportToEntity(Entity par1Entity)
	{
		Vec3 vec3 = new Vec3(this.posX - par1Entity.posX, this.getEntityBoundingBox().minY + this.height / 2.0F - par1Entity.posY + par1Entity.getEyeHeight(), this.posZ - par1Entity.posZ);
		vec3 = vec3.normalize();
		double d0 = 16.0D;
		double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
		double d2 = this.posY + (this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
		double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
		return this.teleportTo(d1, d2, d3);
	}

	protected boolean teleportTo(double par1, double par3, double par5)
	{
		if (this.isDead) return false;
		double d3 = this.posX;
		double d4 = this.posY;
		double d5 = this.posZ;
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		boolean flag = false;
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);

		boolean flag2 = false;

		if (this.worldObj.getLightFor(EnumSkyBlock.BLOCK, new BlockPos(i, j, k)) <= getTeleportLight())
		{
			flag2 = true;
		}

		if (this.worldObj.isBlockLoaded(new BlockPos(i, j, k)) && flag2)
		{
			boolean flag1 = false;

			while (!flag1 && j > 0)
			{
				Block block = this.worldObj.getBlockState(new BlockPos(i, j - 1, k)).getBlock();

				if (block.getMaterial().blocksMovement())
				{
					flag1 = true;
				}
				else
				{
					--this.posY;
					--j;
				}
			}

			if (flag1)
			{
				this.setPosition(this.posX, this.posY, this.posZ);

				if (this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox()))
				{
					flag = true;
				}
			}
		}

		if (!flag)
		{
			this.setPosition(d3, d4, d5);
			return false;
		}
		else
		{
			short short1 = 128;

			for (int l = 0; l < short1; ++l)
			{
				double d6 = l / (short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
				double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				this.worldObj.spawnParticle(getTeleportParticle(), d7, d8, d9, f, f1, f2);
			}

			this.worldObj.playSoundEffect(d3, d4, d5, getTeleportSound(), 0.2F, 1.0F);
			this.playSound(getTeleportSound(), 0.2F, 1.0F);
			this.onTeleport(d3, d4, d5);
			return true;
		}
	}

	protected String getTeleportSound() {
		return "mob.endermen.portal";
	}

	protected EnumParticleTypes getTeleportParticle() {
		return EnumParticleTypes.PORTAL;
	}

	protected int getTeleportLight() {
		return 8;
	}

	/**
	 * Called on successful teleport of this entity, includes previous coordinates before teleport
	 * @param x
	 * @param y
	 * @param z
	 */
	protected void onTeleport(double x, double y, double z) {

	}

	protected boolean teleportPlayer(EntityPlayerMP mp) {
		if (mp.capabilities.isCreativeMode) return false;

		double x = mp.posX;
		double y = mp.posY;
		double z = mp.posZ;

		double x2 = this.posX;
		double y2 = this.posY;
		double z2 = this.posZ;

		if (mp.playerNetServerHandler.getNetworkManager().isChannelOpen() && this.worldObj == mp.worldObj)
		{
			if (mp.isRiding()) mp.mountEntity(null);

			mp.playerNetServerHandler.setPlayerLocation(x2, y2, z2, mp.rotationYaw, mp.rotationPitch);
			short short1 = 128;

			for (int l = 0; l < short1; ++l)
			{
				double d6 = l / (short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = x + ((x2) - x) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				double d8 = y + ((y2) - y) * d6 + this.rand.nextDouble() * this.height;
				double d9 = z + ((z2) - z) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
				this.worldObj.spawnParticle(getTeleportParticle(), d7, d8, d9, f, f1, f2);
			}

			mp.fallDistance = 0.0F;
			this.worldObj.playSoundAtEntity(mp, getTeleportSound(), 0.4F, 0.4F);
			return true;
		}

		return false;
	}

	@Override
	protected void onDeathUpdate()
	{
		if (!TragicConfig.getBoolean("allowCustomBossDeathUpdate")) 
		{
			super.onDeathUpdate();
			return;
		}
		
		++this.deathTime;

		if (this.deathTime == 1)
		{
			if (!this.worldObj.isRemote && this.recentlyHit > 0)
			{
				this.xpAmount = this.getExperiencePoints(this.attackingPlayer);
			}
		}
		else if (this.deathTime < 60)
		{
			if (!this.worldObj.isRemote && this.recentlyHit > 0 && this.xpAmount > 0)
			{
				int m = rand.nextInt(32) + 1;
				if (m > this.xpAmount) m = this.xpAmount;
				this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, m));
				this.xpAmount -= m;
			}

			for (byte i = 0; i < 3; ++i)
			{
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				double d2 = this.rand.nextGaussian() * 0.02D;
				double d3 = 10.0D;
				this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d0 * d3, this.posY + (double)(this.rand.nextFloat() * this.height) - d1 * d3, this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d2 * d3, d0, d1, d2, new int[0]);
			}
		}

		if (this.deathTime == 60)
		{
			if (!this.worldObj.isRemote && this.recentlyHit > 0)
			{
				int i = this.xpAmount;

				while (i > 0)
				{
					int j = EntityXPOrb.getXPSplit(i);
					i -= j;
					this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
				}
			}

			this.setDead();
			this.spawnExplosionParticle();
		}
	}
	
	protected boolean spawnEntityNearby(EntityLiving entity, final int hRange, final int vRange, boolean withTarget)
	{
		for (int y1 = -vRange; y1 < vRange + 1; y1++)
		{
			for (int z1 = -hRange; z1 < hRange + 1; z1++)
			{
				for (int x1 = -vRange; x1 < hRange + 1; x1++)
				{
					if (World.doesBlockHaveSolidTopSurface(this.worldObj, new BlockPos((int) this.posX + x1, (int) this.posY + y1 - 1, (int) this.posZ + z1)) && rand.nextBoolean())
					{
						entity.setPosition(this.posX + x1, this.posY + y1, this.posZ + z1);

						if (this.worldObj.checkNoEntityCollision(entity.getEntityBoundingBox()) &&
								this.worldObj.getCollidingBoundingBoxes(entity, entity.getEntityBoundingBox()).isEmpty() &&
								!this.worldObj.isAnyLiquid(entity.getEntityBoundingBox()))
						{
							this.worldObj.spawnEntityInWorld(entity);
							entity.onInitialSpawn(this.worldObj.getDifficultyForLocation(new BlockPos(this.posX + x1, this.posY + y1, this.posZ + z1)), null);
							if (withTarget && this.getAttackTarget() != null) entity.setAttackTarget(this.getAttackTarget());
							this.worldObj.playSoundAtEntity(this, "tragicmc:random.siren", 1.0F, 1.0F);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
