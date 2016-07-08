package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;

public class EntityWorkerNeko extends EntityNeko {
	
	public static final int DW_TEXTURE = 25;

	public EntityWorkerNeko(World par1World) {
		super(par1World);
		this.setSize(0.475F * 0.915F, 1.955F * 0.915F);
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] workerNekoStats = TragicConfig.getMobStat("workerNekoStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(workerNekoStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(workerNekoStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(workerNekoStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(workerNekoStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(workerNekoStats[4]);
	}

	@Override
	public int getTotalArmorValue() {
		return TragicConfig.getMobStat("workerNekoStats").getArmorValue();
	}

	@Override
	protected void updateNekoTasks() {
		this.tasks.removeTask(attackOnCollide);
		this.tasks.removeTask(moveTowardsTarget);
		this.targetTasks.removeTask(hurtByNekos);
		this.targetTasks.removeTask(targetPlayers);
		this.targetTasks.removeTask(targetUnreleasedNekos);
	}
	
	@Override
	public int getDropAmount() {
		return 1;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DW_TEXTURE, 0);
	}
	
	public int getTextureId() {
		return this.dataWatcher.getWatchableObjectInt(DW_TEXTURE);
	}
	
	private void setTextureId(int i) {
		this.dataWatcher.updateObject(DW_TEXTURE, i);
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance ins, IEntityLivingData data) {
		if (!this.worldObj.isRemote) this.setTextureId(rand.nextInt(7));
		return super.onInitialSpawn(ins, data);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setInteger("textureID", this.getTextureId());
    }

	@Override
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        super.readEntityFromNBT(tagCompound);
        if (tagCompound.hasKey("textureID")) this.setTextureId(tagCompound.getInteger("textureID"));
    }
}
