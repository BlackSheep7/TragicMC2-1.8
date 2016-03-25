package tragicneko.tragicmc.blocks;

import static net.minecraft.init.Blocks.air;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntityTimeDisruptor;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class BlockTimeDisruptor extends BlockContainer {

	public BlockTimeDisruptor() {
		super(Material.iron);
		this.setCreativeTab(TragicMC.Survival);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityTimeDisruptor();
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		super.onBlockAdded(world, pos, state);

		Block block = null;
		Block block1 = world.getBlockState(pos.down()).getBlock();
		Block block2 = world.getBlockState(pos.down(2)).getBlock();
		if (block1 == Blocks.quartz_block && block2 == Blocks.quartz_block) block = Blocks.quartz_block;
		else if (block1 == TragicBlocks.DarkenedQuartz && block2 == TragicBlocks.DarkenedQuartz) block = TragicBlocks.DarkenedQuartz;
		else if (block1 == Blocks.hardened_clay && block2 == Blocks.hardened_clay) block = Blocks.hardened_clay;
		else if (block1 == Blocks.sandstone && block2 == Blocks.sandstone) block = Blocks.sandstone;
		else if (block1 == Blocks.ice && block2 == Blocks.ice) block = Blocks.ice;
		else if (block1 == TragicBlocks.StarCrystal && block2 == TragicBlocks.StarCrystal) block = TragicBlocks.StarCrystal;
		else if (block1 == TragicBlocks.BoneBlock && block2 == TragicBlocks.BoneBlock) block = TragicBlocks.BoneBlock;
		else if (block1 == Blocks.nether_brick && block2 == Blocks.nether_brick) block = Blocks.nether_brick;

		if (block == null) return;
		boolean flag = world.getBlockState(pos.down().east()).getBlock() == block && world.getBlockState(pos.down().west()).getBlock() == block && world.getBlockState(pos.down().north()).getBlock() == block && world.getBlockState(pos.down().south()).getBlock() == block;

		if (flag)
		{
			TragicBoss boss = null;
			if (block == Blocks.quartz_block && TragicConfig.allowTimeController) boss = new EntityTimeController(world);
			else if (block == TragicBlocks.DarkenedQuartz && TragicConfig.allowEnyvil) boss = new EntityEnyvil(world);
			else if (block == Blocks.hardened_clay && TragicConfig.allowClaymation) boss = new EntityClaymation(world);
			else if (block == Blocks.sandstone && TragicConfig.allowApis) boss = new EntityApis(world);
			else if (block == Blocks.ice && TragicConfig.allowEmpariah) boss = new EntityYeti(world);
			else if (block == TragicBlocks.StarCrystal && TragicConfig.allowPolaris) boss = new EntityPolaris(world);
			else if (block == TragicBlocks.BoneBlock && TragicConfig.allowSkultar) boss = new EntityDeathReaper(world);
			else if (block == Blocks.nether_brick && TragicConfig.allowKitsunakuma) boss = new EntityKitsune(world);

			if (boss == null) return;

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
			boss.onInitialSpawn(world.getDifficultyForLocation(pos), null);
			world.spawnEntityInWorld(boss);

			for (int l = 0; l < 120; ++l)
				world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, pos.getX() + world.rand.nextDouble(), pos.getY() - 2 + world.rand.nextDouble() * 3.9D, pos.getZ() + world.rand.nextDouble(),
						0.0D, 0.0D, 0.0D);
		}
	}
	
	@Override
	public int getRenderType()
    {
		return 3;
    }
}
