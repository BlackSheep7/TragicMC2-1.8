package tragicneko.tragicmc.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;

public class EntityAIBurn extends EntityAIBase {
	
	private final boolean brightLightBurn;
	private EntityLiving theEntity;
	
	public EntityAIBurn(EntityLiving entity, boolean brightLightInclusive)
	{
		this.brightLightBurn = brightLightInclusive;
		this.theEntity = entity;
	}

	@Override
	public boolean shouldExecute() {
		return this.theEntity.worldObj.getLightFor(EnumSkyBlock.BLOCK, this.theEntity.getPosition().up()) >= 8 && this.brightLightBurn || this.theEntity.worldObj.canBlockSeeSky(this.theEntity.getPosition()) && this.theEntity.worldObj.isDaytime();
	}

	@Override
	public boolean continueExecuting()
	{
		return this.theEntity.isEntityAlive() && !this.theEntity.isBurning() && super.continueExecuting();
	}
	
	@Override
	public void startExecuting()
	{
		this.theEntity.setFire(4);
	}
	
	@Override
	public boolean isInterruptible()
    {
		return false;
    }
}
