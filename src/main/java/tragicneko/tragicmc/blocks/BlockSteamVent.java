package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockSteamVent extends Block {

	public BlockSteamVent() {
		super(Material.rock);
		this.setCreativeTab(TragicMC.Survival);
		this.setUnlocalizedName("tragicmc.steamVent");
		this.setHarvestLevel("pickaxe", 1);
		this.setTickRandomly(true);
		this.setResistance(10.0F);
		this.setHardness(1.0F);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		entity.motionY += 0.6 * world.rand.nextDouble();
		entity.velocityChanged = true;
		if (!entity.isImmuneToFire() && world.rand.nextInt(256) == 0) entity.setFire(8 + world.rand.nextInt(4));
	}

	@Override
	public int tickRate(World world)
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!world.getBlockState(pos.up()).getBlock().isOpaqueCube() && !world.getBlockState(pos.up(2)).getBlock().isOpaqueCube())
		{
			for (byte i = 0; i < 16; i++)
			{
				world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + ((rand.nextDouble() - rand.nextDouble()) * 0.5) + 0.5, pos.getY() + 0.25, pos.getZ() + ((rand.nextDouble() - rand.nextDouble()) * 0.5) + 0.5,
						(rand.nextFloat() - rand.nextFloat()) * 0.1F, rand.nextFloat() * 0.4F, (rand.nextFloat() - rand.nextFloat()) * 0.1F);
			}
		}
	}
}
