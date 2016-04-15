package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.util.WorldHelper;

public class BlockExplosiveGas extends BlockGas {

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		for (byte i = 0; i < 5; i++)
		{
			world.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX() + rand.nextDouble() - rand.nextDouble(), pos.getY() + (rand.nextDouble() * 0.725), pos.getZ() + rand.nextDouble() - rand.nextDouble(),
					0.6F, 0.6F, 0.6F);
			world.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX() + rand.nextDouble() - rand.nextDouble(), pos.getY() + (rand.nextDouble() * 0.725), pos.getZ() + rand.nextDouble() - rand.nextDouble(),
					0.8F, 0.8F, 0.8F);
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		Block[] block = new Block[] {world.getBlockState(pos).getBlock(), world.getBlockState(pos.east()).getBlock(), world.getBlockState(pos.west()).getBlock(),
				world.getBlockState(pos.north()).getBlock(), world.getBlockState(pos.south()).getBlock(), world.getBlockState(pos.up()).getBlock(), world.getBlockState(pos.down()).getBlock()};
		for (Block b : block)
		{
			if (b instanceof BlockFire || b.getMaterial() == Material.lava) world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F + rand.nextFloat(), WorldHelper.getMobGriefing(world));
		}
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosion)
	{
		if (!world.isRemote)  world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F + world.rand.nextFloat(), WorldHelper.getMobGriefing(world));
	}
}
