package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockCandle extends BlockTorch {

	public BlockCandle() {
		super();
		this.setLightLevel(0.49F);
		this.setLightOpacity(0);
		this.setCreativeTab(TragicMC.Survival);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		int l = this.getMetaFromState(state);
		double d0 = pos.getX() + 0.5F;
		double d1 = pos.getY() + 0.75F;
		double d2 = pos.getZ() + 0.5F;
		double d3 = 0.2399999988079071D;
		double d4 = 0.27000001072883606D;

		if (l == 1)
		{
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d4 + 0.05, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, d0 - d4 + 0.05, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		}
		else if (l == 2)
		{
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4 - 0.05, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4 - 0.05, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		}
		else if (l == 3)
		{
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1 + d3, d2 - d4 + 0.05, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, d0, d1 + d3, d2 - d4 + 0.05, 0.0D, 0.0D, 0.0D);
		}
		else if (l == 4)
		{
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1 + d3, d2 + d4 - 0.05, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, d0, d1 + d3, d2 + d4 - 0.05, 0.0D, 0.0D, 0.0D);
		}
		else
		{
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}
}
