package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.mob.EntityHunter;
import tragicneko.tragicmc.entity.mob.EntityParasmite;
import tragicneko.tragicmc.entity.mob.EntityPlague;

public class BlockQuicksand extends BlockFalling
{
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockQuicksand.EnumVariant.class);

	public BlockQuicksand()
	{
		super(Material.sand);
		this.setCreativeTab(TragicMC.Survival);
		this.setUnlocalizedName("tragicmc.quicksand");
		this.setHardness(25.0F);
		this.setResistance(10.0F);
		this.setHarvestLevel("shovel", 3);
		this.setStepSound(soundTypeSand);
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (byte i = 0; i < 4; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		if (entity instanceof EntityBlaze || entity instanceof EntityGhast || entity instanceof EntityPlague || entity instanceof EntityHunter || entity instanceof EntityParasmite) return;
		entity.motionX *= 0.0015;
		entity.motionZ *= 0.0015;
		entity.motionY *= entity instanceof EntityHorse ? 0.925 : 0.125;
		if (entity.motionY < 0.1) entity.motionY = -0.1;
		entity.velocityChanged = true;
		entity.fallDistance = 0.0F;
		if (!(entity instanceof EntityFallingBlock)) entity.onGround = true;
		if (world.getBlockState(pos).getValue(VARIANT) == EnumVariant.SLUDGE && entity instanceof EntityLivingBase && world.rand.nextInt(16) == 0) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 60 + world.rand.nextInt(40)));
	}
/*
	@Override //TODO change method call
	public void onEntityWalking(World world, BlockPos pos, Entity entity)
	{
		if (entity instanceof EntityBlaze || entity instanceof EntityGhast || entity instanceof EntityPlague || entity instanceof EntityHunter || entity instanceof EntityParasmite) return;
		entity.motionX *= 0.0015;
		entity.motionZ *= 0.0015;
		entity.motionY = -0.5;
		entity.velocityChanged = true;
	} */

	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float distance)
	{
		if (entity instanceof EntityBlaze || entity instanceof EntityGhast || entity instanceof EntityPlague || entity instanceof EntityHunter || entity instanceof EntityParasmite) return;
		entity.motionX *= 0.0015;
		entity.motionZ *= 0.0015;
		entity.motionY = -0.5;
		entity.fallDistance = 0.0F;
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
	{
		return true;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public int getRenderType()
	{
		return 0;
	}


	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return super.getItemDropped(state, rand, fortune);
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}

	@Override
	public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, SpawnPlacementType type)
	{
		return false;
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, VARIANT);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return meta == 0 || meta >= EnumVariant.values().length ? this.getDefaultState() : this.getDefaultState().withProperty(VARIANT, EnumVariant.values()[meta]);
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		Comparable comp = state.getValue(VARIANT);
		return comp == EnumVariant.MUD ? 1 : (comp == EnumVariant.DRUDGE ? 2 : (comp == EnumVariant.SLUDGE ? 3 : 0));
    }
	
	public enum EnumVariant implements IStringSerializable {
		NORMAL("normal"),
		MUD("mud"),
		DRUDGE("drudge"),
		SLUDGE("sludge");
		
		private final String name;
		
		private EnumVariant(String name)
		{
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.name;
		}

		@Override
		public String getName() {
			return this.name;
		}
	}
}