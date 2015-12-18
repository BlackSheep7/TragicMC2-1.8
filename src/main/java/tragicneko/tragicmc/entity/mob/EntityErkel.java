package tragicneko.tragicmc.entity.mob;

import static tragicneko.tragicmc.TragicConfig.erkelStats;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.worldgen.biome.BiomeGenAshenHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.biome.BiomeGenHallowedHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;
import tragicneko.tragicmc.worldgen.biome.BiomeGenTaintedSpikes;

import com.google.common.base.Predicate;

public class EntityErkel extends TragicMob {
	
	public static final Predicate bossTarget = new Predicate() {
		@Override
		public boolean apply(Object o) {
			return canApply((Entity) o);
		}
		
		public boolean canApply(Entity entity) {
			return entity instanceof TragicBoss;
		}
	};

	public EntityErkel(World par1World) {
		super(par1World);
		this.setSize(0.56F, 1.06F);
		this.experienceValue = 2;
		this.tasks.addTask(0, new EntityAIPanic(this, 0.65D));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, bossTarget, 12.0F, 0.4D, 0.6D));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIWander(this, 0.45D));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.getDataWatcher().addObject(16, (byte) 0);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(erkelStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(erkelStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(erkelStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(erkelStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(erkelStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return (int) erkelStats[5];
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		if (source.getEntity() != null && source.getEntity() instanceof EntityLivingBase && !source.isProjectile() && rand.nextBoolean() && !this.worldObj.isRemote && this.getDistanceToEntity(source.getEntity()) <= 2.0F)
		{
			((EntityLivingBase) source.getEntity()).addPotionEffect(new PotionEffect(Potion.poison.id, 120, 0));
		}

		if (this.worldObj.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				this.worldObj.spawnParticle(EnumParticleTypes.SPELL_WITCH, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
			}
		}
		return super.attackEntityFrom(source, damage);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				this.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT, this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
			}
		}
		else
		{
			if (this.rand.nextInt(32) == 0 && this.onGround && this.getMobGriefing() && this.ticksExisted % 20 == 0 && TragicConfig.erkelMushroomSpawning)
			{
				int x = (int) (this.posX + rand.nextInt(2) - rand.nextInt(2));
				int y = (int) (this.posY + rand.nextInt(2) - rand.nextInt(2));
				int z = (int) (this.posZ + rand.nextInt(2) - rand.nextInt(2));

				if (this.worldObj.isAirBlock(new BlockPos(x, y, z)) || this.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof BlockTallGrass)
				{
					if (Blocks.brown_mushroom.canBlockStay(this.worldObj, new BlockPos(x, y, z), Blocks.brown_mushroom.getDefaultState())) this.worldObj.setBlockState(new BlockPos(x, y, z), rand.nextBoolean() ? Blocks.brown_mushroom.getDefaultState() : Blocks.red_mushroom.getDefaultState());
				}
			}
		}
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}

	@Override
	public IEntityLivingData func_180482_a(DifficultyInstance ins, IEntityLivingData data)
	{
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(new BlockPos((int) this.posX, 0, (int) this.posZ));
		byte b = 0;
		if (biome instanceof BiomeGenAshenHills || biome instanceof BiomeGenTaintedSpikes)
		{
			b = 1;
		}
		else if (biome instanceof BiomeGenPaintedForest)
		{
			b = 2;
		}
		else if (biome instanceof BiomeGenStarlitPrarie)
		{
			b = 3;
		}
		else if (biome instanceof BiomeGenDecayingWasteland)
		{
			b = 4;
		}
		else if (biome instanceof BiomeGenHallowedHills)
		{
			b = 5;
		}
		else if (biome instanceof BiomeGenFrozenTundra)
		{
			b = 6;
		}

		this.setTextureId(b);
		return super.func_180482_a(ins, data);
	}

	private void setTextureId(byte b)
	{
		this.getDataWatcher().updateObject(16, b);
	}

	public byte getTextureId()
	{
		return this.getDataWatcher().getWatchableObjectByte(16);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("texture")) this.setTextureId(tag.getByte("texture"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setByte("texture", this.getTextureId());
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.allowMobSounds ? "tragicmc:mob.tox.living" : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.allowMobSounds ? "tragicmc:mob.tox.hurt" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.allowMobSounds ? "tragicmc:mob.tox.hurt" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return 1.9F;
	}

	@Override
	public float getSoundVolume()
	{
		return 0.2F + rand.nextFloat() * 0.1F;
	}

	@Override
	public int getTalkInterval()
	{
		return super.getTalkInterval();
	}
}
