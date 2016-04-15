package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRadiatedGas extends BlockGas {

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
	{
		if (!world.isRemote && entity instanceof EntityLivingBase)
		{
			entity.attackEntityFrom(DamageSource.cactus, 1.0F);
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 200, 0));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		for (byte i = 0; i < 5; i++)
		{
			world.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX() + rand.nextDouble() - rand.nextDouble(), pos.getY() + (rand.nextDouble() * 0.725), pos.getZ() + rand.nextDouble() - rand.nextDouble(),
					0.4F, 1.0F, 0.4F);
			world.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX() + rand.nextDouble() - rand.nextDouble(), pos.getY() + (rand.nextDouble() * 0.725), pos.getZ() + rand.nextDouble() - rand.nextDouble(),
					0.1F, 1.0F, 0.1F);
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {}
}
