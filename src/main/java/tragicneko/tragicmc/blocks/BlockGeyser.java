package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockErodedStone.EnumVariant;

public class BlockGeyser extends Block {

	public static final PropertyBool STEAMING = PropertyBool.create("steaming");

	public BlockGeyser() {
		super(Material.rock);
		this.setCreativeTab(TragicMC.Survival);
		this.setUnlocalizedName("tragicmc.geyser");
		this.setTickRandomly(true);
		this.setHarvestLevel("pickaxe", 1);
		this.setResistance(10.0F);
		this.setHardness(1.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(STEAMING, false));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		boolean isSteaming = ((Boolean) state.getValue(STEAMING)).booleanValue();
		int i = 4 + (isSteaming ? 4 : 0);
		if (isSteaming && !world.getBlockState(pos.up()).getBlock().isOpaqueCube() && !world.getBlockState(pos.up(2)).getBlock().isOpaqueCube())
		{
			for (int j = 0; j < 16; j++)
			{
				world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + rand.nextDouble() - rand.nextDouble() + 0.5, pos.getY() + 0.25, pos.getZ() + rand.nextDouble() - rand.nextDouble() + 0.5,
						(rand.nextFloat() - rand.nextFloat()) * 0.1F, 0.5F + rand.nextFloat(), (rand.nextFloat() - rand.nextFloat()) * 0.1F);
			}
		}
		
		if (rand.nextInt(i) == 0)
		{
			world.setBlockState(pos, this.getDefaultState().withProperty(STEAMING, !isSteaming));
		}
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (((Boolean) state.getValue(STEAMING)).booleanValue())
		{
			entity.motionY += 1.4 * world.rand.nextDouble();
			entity.velocityChanged = true;
			if (!entity.isImmuneToFire() && world.rand.nextInt(16) == 0) entity.setFire(8 + world.rand.nextInt(4));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (((Boolean) state.getValue(STEAMING)).booleanValue() && !world.getBlockState(pos.up()).getBlock().isOpaqueCube() && !world.getBlockState(pos.up(2)).getBlock().isOpaqueCube())
		{
			for (int i = 0; i < 32; i++)
			{
				world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + ((rand.nextDouble() - rand.nextDouble()) * 0.5) + 0.5, pos.getY() + 0.25, pos.getZ() + ((rand.nextDouble() - rand.nextDouble()) * 0.5) + 0.5,
						(rand.nextFloat() - rand.nextFloat()) * 0.1F, 1.0F + rand.nextFloat(), (rand.nextFloat() - rand.nextFloat()) * 0.1F);
			}
		}
	}

	@Override
	public int tickRate(World world)
	{
		return 5;
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, STEAMING);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return this.getDefaultState().withProperty(STEAMING, meta == 1);
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		boolean comp = ((Boolean) state.getValue(STEAMING)).booleanValue();
		return comp ? 1 : 0;
    }
}
