package tragicneko.tragicmc.worldgen;

import static net.minecraft.init.Blocks.dirt;
import static tragicneko.tragicmc.TragicBlocks.BleachedLeaves;
import static tragicneko.tragicmc.TragicBlocks.BleachedWood;
import static tragicneko.tragicmc.TragicBlocks.DeadDirt;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import tragicneko.tragicmc.blocks.BlockGenericGrass;
import tragicneko.tragicmc.util.WorldHelper;

public class WorldGenCustomLollipopTree extends WorldGenAbstractTree {

	private final IBlockState logState;
	private final IBlockState leafState;

	public WorldGenCustomLollipopTree(IBlockState log, IBlockState leaf) {
		super(false);
		this.logState = log;
		this.leafState = leaf;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		BlockPos coords;
		Block block;

		block = world.getBlockState(pos).getBlock();
		if (!(block instanceof BlockGenericGrass) && !(block instanceof BlockGrass) && block.getMaterial() != Material.ground && block.getMaterial() != Material.grass && !(block instanceof BlockBush)) return false;

		double scale = rand.nextDouble() * 3.25D + 1.15D;
		if (scale < 2.0) return false;

		ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(world, scale * 0.5D, pos.getX(), pos.getY() + 1 + scale * 3.0 / 4.0, pos.getZ());

		for (int y1 = 0; y1 < 1 + scale * 3.0 / 4.0; y1++)
		{
			block = world.getBlockState(pos.up(y1)).getBlock();
			if (block.canBeReplacedByLeaves(world, pos.up(y1))) world.setBlockState(pos.up(y1), logState);
		}

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			block = world.getBlockState(coords).getBlock();

			if (block.canBeReplacedByLeaves(world, coords) && block.getMaterial() != Material.wood) world.setBlockState(coords, leafState);
		}

		return true;
	}

}
