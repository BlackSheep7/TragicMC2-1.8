package tragicneko.tragicmc.entity.miniboss;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.entity.mob.EntityFusea;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityVolatileFusea extends EntityFusea implements TragicMiniBoss {

	private int volatype;

	public EntityVolatileFusea(World par1World) {
		super(par1World);
		this.experienceValue = 150;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] volatileFuseaStats = TragicConfig.getMobStat("volatileFuseaStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(volatileFuseaStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(volatileFuseaStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(volatileFuseaStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(volatileFuseaStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(volatileFuseaStats[4]);
	}

	@Override
	public int getTotalArmorValue()
	{
		return TragicConfig.getMobStat("volatileFuseaStats").getArmorValue();
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getHealth() < this.getMaxHealth())
		{
			for (int i = 0; i < this.getMaxHealth() - this.getHealth(); i++)
			{
				this.motionX *= 1.015;
				this.motionZ *= 1.015;
			}
		}

		super.onLivingUpdate();

		if (!this.worldObj.isRemote && this.ticksExisted % 5 == 0)
		{
			if (TragicConfig.getBoolean("volatileFuseaElementalChange"))
			{
				ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(this.worldObj, 5.25, this.posX, this.posY, this.posZ);
				Block block;

				for (BlockPos coords : list)
				{
					block = this.worldObj.getBlockState(coords).getBlock();

					if (block.getMaterial() == Material.water || block instanceof BlockIce)
					{
						this.volatype = 2;
						break;
					}
					else if (block.getStrongPower(this.worldObj, coords, this.worldObj.getBlockState(coords), EnumFacing.UP) > 0)
					{
						this.volatype = 3;
						break;
					}
					else if (block.getMaterial() == Material.fire || block.getMaterial() == Material.lava)
					{
						this.volatype = 1;
						break;
					}
					else
					{
						this.volatype = 0;
					}
				}
			}

			EntityLivingBase entity = this.getAttackTarget();
			double d0;
			double d1;
			double d2;

			if (entity != null)
			{
				d0 = entity.posX - this.posX;
				d1 = entity.getEntityBoundingBox().minY + entity.height / 2.0F - (this.posY + this.height / 2.0F);
				d2 = entity.posZ - this.posZ;
			}
			else
			{
				d0 = rand.nextInt(4) - rand.nextInt(4);
				d1 = rand.nextInt(4) - rand.nextInt(4);
				d2 = rand.nextInt(4) - rand.nextInt(4);
			}

			switch(this.volatype)
			{
			default:
			case 0:
				break;
			case 1:
				if (this.isBurning()) this.extinguish();
				for (int i = 0; i < 3; ++i)
				{
					if (entity == null)
					{
						d0 = rand.nextInt(4) - rand.nextInt(4);
						d1 = rand.nextInt(4) - rand.nextInt(4);
						d2 = rand.nextInt(4) - rand.nextInt(4);
					}

					EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.worldObj, this, d0, d1, d2);
					this.worldObj.spawnEntityInWorld(entitysmallfireball);
				}
				break;
			case 2:
				for (int i = 0; i < 3; ++i)
				{
					if (entity == null)
					{
						d0 = rand.nextInt(4) - rand.nextInt(4);
						d1 = rand.nextInt(4) - rand.nextInt(4);
						d2 = rand.nextInt(4) - rand.nextInt(4);
					}

					EntityIcicle icicle = new EntityIcicle(this.worldObj, this, d0, d1, d2);
					this.worldObj.spawnEntityInWorld(icicle);
				}
				break;
			case 3:
				for (int i = 0; i < 3; ++i)
				{
					if (entity == null)
					{
						d0 = rand.nextInt(4) - rand.nextInt(4) + this.posX;
						d1 = rand.nextInt(4) - rand.nextInt(4) + this.posY;
						d2 = rand.nextInt(4) - rand.nextInt(4) + this.posZ;
					}

					this.worldObj.spawnEntityInWorld(new EntityDirectedLightning(this.worldObj, d0, d1, d2, this));
				}
				break;
			}
		}
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public Class getLesserForm() {
		return EntityFusea.class;
	}

	@Override
	public float getSoundPitch()
	{
		return 0.4F;
	}

	@Override
	public int getDropAmount()
	{
		return 5;
	}
	
	@Override
	protected void updateSize() {
		this.setSize(1.5F * 1.585F, 1.5F * 1.585F);
	}
}
