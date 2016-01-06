package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class WorldGenCustomShrubs extends WorldGenTrees
{
    private final IBlockState logState;
    private final IBlockState leafState;

    public WorldGenCustomShrubs(IBlockState log, IBlockState leaf)
    {
        super(false);
        this.logState = log;
        this.leafState = leaf;
    }

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        Block block;

        do
        {
            block = worldIn.getBlockState(pos).getBlock();
            if (!block.isAir(worldIn, pos) && !block.isLeaves(worldIn, pos)) break;
            pos = pos.down();
        } while (pos.getY() > 0);

        Block block1 = worldIn.getBlockState(pos).getBlock();

        if (block1.canSustainPlant(worldIn, pos, net.minecraft.util.EnumFacing.UP, ((net.minecraft.block.BlockSapling)Blocks.sapling)))
        {
            pos = pos.up();
            this.func_175903_a(worldIn, pos, this.logState);

            for (int i = pos.getY(); i <= pos.getY() + 2; ++i)
            {
                int j = i - pos.getY();
                int k = 2 - j;

                for (int l = pos.getX() - k; l <= pos.getX() + k; ++l)
                {
                    int i1 = l - pos.getX();

                    for (int j1 = pos.getZ() - k; j1 <= pos.getZ() + k; ++j1)
                    {
                        int k1 = j1 - pos.getZ();

                        if (Math.abs(i1) != k || Math.abs(k1) != k || rand.nextInt(2) != 0)
                        {
                            BlockPos blockpos1 = new BlockPos(l, i, j1);

                            if (worldIn.getBlockState(blockpos1).getBlock().canBeReplacedByLeaves(worldIn, blockpos1))
                            {
                                this.func_175903_a(worldIn, blockpos1, this.leafState);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}