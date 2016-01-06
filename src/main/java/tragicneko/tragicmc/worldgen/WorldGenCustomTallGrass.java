package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCustomTallGrass extends WorldGenerator {
	
	private final IBlockState grassState;
	
	public WorldGenCustomTallGrass(IBlockState grass)
	{
		super();
		this.grassState = grass;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        Block block;

        do
        {
            block = worldIn.getBlockState(pos).getBlock();
            if (!block.isAir(worldIn, pos) && !block.isLeaves(worldIn, pos)) break;
            pos = pos.down();
        } while (pos.getY() > 0);

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos1) && this.grassState.getBlock().canPlaceBlockAt(worldIn, blockpos1))
            {
                worldIn.setBlockState(blockpos1, this.grassState, 2);
            }
        }

        return true;
    }

}
