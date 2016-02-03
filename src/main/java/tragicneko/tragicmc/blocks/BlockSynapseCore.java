package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCocoon;

public class BlockSynapseCore extends Block {

	public BlockSynapseCore() {
		super(Material.iron);
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(TragicMC.Survival);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		super.onBlockAdded(world, pos, state);

		Block block = TragicBlocks.CircuitBlock;
		boolean flag = world.getBlockState(pos.down()).getBlock() == block && world.getBlockState(pos.down(2)).getBlock() == block &&
				world.getBlockState(pos.down().east()).getBlock() == block && world.getBlockState(pos.down().west()).getBlock() == block &&
				world.getBlockState(pos.down().north()).getBlock() == block && world.getBlockState(pos.down().south()).getBlock() == block;

		if (flag)
		{
			if (world.provider.getDimensionId() != TragicConfig.synapseID && !world.isRemote)
			{
				List<EntityPlayerMP> list = world.getEntitiesWithinAABB(EntityPlayerMP.class, new AxisAlignedBB(0, 0, 0, 0, 0, 0).expand(4.0, 4.0, 4.0).offset(pos.getX(), pos.getY(), pos.getZ()));
				for (EntityPlayerMP mp : list)
				{
					mp.addChatMessage(new ChatComponentText("Not the right dimension to summon the Overlord!"));
				}
				return;
			}
			EntityOverlordCocoon boss = new EntityOverlordCocoon(world);

			world.setBlockToAir(pos);
			world.setBlockToAir(pos.down());
			world.setBlockToAir(pos.down(2));
			world.setBlockToAir(pos.down().east());
			world.setBlockToAir(pos.down().west());
			world.setBlockToAir(pos.down().north());
			world.setBlockToAir(pos.down().south());

			boss.setLocationAndAngles(pos.getX() + 0.5D, pos.getY() - 1.95D, pos.getZ() + 0.5D, 0.0F, 0.0F);
			EntityPlayer player = boss.worldObj.getClosestPlayerToEntity(boss, 16.0D);
			if (player != null) boss.setAttackTarget(player);
			boss.func_180482_a(world.getDifficultyForLocation(pos), null);
			world.spawnEntityInWorld(boss);

			for (int l = 0; l < 120; ++l)
				world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, pos.getX() + world.rand.nextDouble(), pos.getY() - 2 + world.rand.nextDouble() * 3.9D, pos.getZ() + world.rand.nextDouble(),
						0.0D, 0.0D, 0.0D);

			
			List<EntityPlayerMP> list = world.getEntitiesWithinAABB(EntityPlayerMP.class, new AxisAlignedBB(0, 0, 0, 0, 0, 0).expand(4.0, 4.0, 4.0).offset(pos.getX(), pos.getY(), pos.getZ()));
			for (EntityPlayerMP mp : list)
			{
				mp.triggerAchievement(TragicAchievements.overlord);
			}
		}
	}
}
