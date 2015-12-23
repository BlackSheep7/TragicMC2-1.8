package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockGas extends Block {

	public BlockGas() {
		super(Material.circuits);
		this.setCreativeTab(TragicMC.Creative);
		this.setLightLevel(0.0F);
		this.setLightOpacity(0);
		this.setResistance(0.0F);
		this.setHardness(0.0F);
		this.setTickRandomly(true);
	}

	@Override
	public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, SpawnPlacementType type)
	{
		return false;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean canReplace(World world, BlockPos pos, EnumFacing facing, ItemStack stack)
	{
		return true;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		world.setBlockToAir(pos);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote) world.scheduleUpdate(pos, this, 300 + world.rand.nextInt(120));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return -1;
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

	/**
	 * Returns whether this block is collideable based on the arguments passed in n@param par1 block metaData n@param
	 * par2 whether the player right-clicked while holding a boat
	 */
	@Override
	public boolean canCollideCheck(IBlockState state, boolean withBoat)
	{
		return false;
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	@Override
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune) {}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		if (!world.isRemote && entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD)
		{
			entity.attackEntityFrom(DamageSource.wither, 1.0F);
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200, 0));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		for (int i = 0; i < 10; i++)
		{
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + rand.nextDouble() - rand.nextDouble(), pos.getY() + (rand.nextDouble() * 0.725), pos.getZ() + rand.nextDouble() - rand.nextDouble(),
					0.0F, 0.0F, 0.0F);
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + rand.nextDouble() - rand.nextDouble(), pos.getY() + (rand.nextDouble() * 0.725), pos.getZ() + rand.nextDouble() - rand.nextDouble(),
					0.0F, 0.0F, 0.0F);
			world.spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT, pos.getX() + rand.nextDouble() - rand.nextDouble(), pos.getY() + (rand.nextDouble() * 0.725), pos.getZ() + rand.nextDouble() - rand.nextDouble(),
					0.0F, 0.0F, 0.0F);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public boolean isAir(IBlockAccess world, BlockPos pos)
	{
		return true;
	}
}
