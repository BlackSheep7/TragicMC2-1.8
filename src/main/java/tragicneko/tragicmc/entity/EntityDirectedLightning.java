package tragicneko.tragicmc.entity;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDirectedLightning extends EntityWeatherEffect
{
	public static final int DW_USER_ID = 5;
	
	private int lightningState;
	public long boltVertex;
	private int boltLivingTime;
	private Entity user;

	public EntityDirectedLightning(World world)
	{
		super(world);
		this.lightningState = 2;
		this.boltVertex = this.rand.nextLong();
	}

	public EntityDirectedLightning(World world, double x, double y, double z, Entity user)
	{
		super(world);
		this.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
		this.lightningState = 2;
		this.boltVertex = this.rand.nextLong();
		this.boltLivingTime = this.rand.nextInt(3) + 1;
		this.user = user;
		if (!world.isRemote) this.setUserID(user);

		if (!world.isRemote && world.getGameRules().getBoolean("doFireTick") && world.getDifficulty().getDifficultyId() >= 2 && world.isAreaLoaded(new BlockPos(MathHelper.floor_double(x), MathHelper.floor_double(y), MathHelper.floor_double(z)), 10))
		{
			int i = MathHelper.floor_double(x);
			int j = MathHelper.floor_double(y);
			int k = MathHelper.floor_double(z);

			if (world.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(world, new BlockPos(i, j, k)))
			{
				world.setBlockState(new BlockPos(i, j, k), Blocks.fire.getDefaultState());
			}

			for (i = 0; i < 4; ++i)
			{
				j = MathHelper.floor_double(x) + this.rand.nextInt(3) - 1;
				k = MathHelper.floor_double(y) + this.rand.nextInt(3) - 1;
				int l = MathHelper.floor_double(z) + this.rand.nextInt(3) - 1;

				if (world.getBlockState(new BlockPos(j, k, l)).getBlock().getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(world, new BlockPos(j, k, l)))
				{
					world.setBlockState(new BlockPos(j, k, l), Blocks.fire.getDefaultState());
				}
			}
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.lightningState == 2)
		{
			if (this.user != null) this.worldObj.playSoundAtEntity(this.user, "tragicmc:random.directedlightning", 0.3F, rand.nextFloat() * 0.5F);
			this.worldObj.playSoundAtEntity(this, "tragicmc:random.directedlightning", 0.4F, rand.nextFloat() * 0.3F);
			this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 0.1F + rand.nextFloat(), 0.1F + this.rand.nextFloat() * 0.2F);
		}

		--this.lightningState;

		if (this.lightningState < 0)
		{
			if (this.boltLivingTime == 0)
			{
				this.setDead();
			}
			else if (this.lightningState < -this.rand.nextInt(10))
			{
				--this.boltLivingTime;
				this.lightningState = 1;
				this.boltVertex = this.rand.nextLong();

				if (!this.worldObj.isRemote && this.worldObj.getGameRules().getBoolean("doFireTick") && this.worldObj.isAreaLoaded(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)), 10))
				{
					int i = MathHelper.floor_double(this.posX);
					int j = MathHelper.floor_double(this.posY);
					int k = MathHelper.floor_double(this.posZ);

					if (this.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(this.worldObj, new BlockPos(i, j, k)))
					{
						this.worldObj.setBlockState(new BlockPos(i, j, k), Blocks.fire.getDefaultState());
					}
				}
			}
		}

		if (this.lightningState >= 0)
		{
			if (!this.worldObj.isRemote)
			{
				double d0 = 3.0D;
				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(d0, d0, d0));

				for (int l = 0; l < list.size(); ++l)
				{
					Entity entity = (Entity)list.get(l);
					if (this.user != entity && !net.minecraftforge.event.ForgeEventFactory.onEntityStruckByLightning(entity, null)) entity.onStruckByLightning(null);
				}
			}
		}
	}

	public int getUserID()
	{
		return this.dataWatcher.getWatchableObjectInt(DW_USER_ID);
	}

	private void setUserID(Entity entity)
	{
		if (entity == null) return;
		this.dataWatcher.updateObject(DW_USER_ID, entity.getEntityId());
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(DW_USER_ID, Integer.valueOf(0));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {}
	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {}
}