package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;

public class EntityTraderNeko extends EntityNeko {

	public EntityTraderNeko(World par1World) {
		super(par1World);
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] traderNekoStats = TragicConfig.getMobStat("traderNekoStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(traderNekoStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(traderNekoStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(traderNekoStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(traderNekoStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(traderNekoStats[4]);
	}

	@Override
	public int getTotalArmorValue() {
		return TragicConfig.getMobStat("traderNekoStats").getArmorValue();
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
