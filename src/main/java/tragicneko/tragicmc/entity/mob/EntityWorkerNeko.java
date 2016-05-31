package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;

public class EntityWorkerNeko extends EntityNeko {

	public EntityWorkerNeko(World par1World) {
		super(par1World);
		this.setSize(0.475F * 0.655F, 1.955F * 0.655F);
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
}
