package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.BlockVine;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenCustomHugeJungleTree extends WorldGenCustomHugeTree
{
    private final IBlockState logState;
    private final IBlockState vineState;

    public WorldGenCustomHugeJungleTree(boolean isLargeTree, int baseH, int heightV, IBlockState leaf, IBlockState log, IBlockState vine)
    {
        super(isLargeTree, baseH, heightV, leaf);
        this.logState = log;
        this.vineState = vine;
    }

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        int i = this.func_150533_a(rand);

        if (!this.func_175929_a(worldIn, rand, pos, i))
        {
            return false;
        }
        else
        {
            this.func_175930_c(worldIn, pos.up(i), 2);

            for (int j = pos.getY() + i - 2 - rand.nextInt(4); j > pos.getY() + i / 2; j -= 2 + rand.nextInt(4))
            {
                float f = rand.nextFloat() * (float)Math.PI * 2.0F;
                int k = pos.getX() + (int)(0.5F + MathHelper.cos(f) * 4.0F);
                int l = pos.getZ() + (int)(0.5F + MathHelper.sin(f) * 4.0F);
                int i1;

                for (i1 = 0; i1 < 5; ++i1)
                {
                    k = pos.getX() + (int)(1.5F + MathHelper.cos(f) * (float)i1);
                    l = pos.getZ() + (int)(1.5F + MathHelper.sin(f) * (float)i1);
                    this.setBlockAndNotifyAdequately(worldIn, new BlockPos(k, j - 3 + i1 / 2, l), this.logState);
                }

                i1 = 1 + rand.nextInt(2);
                int j1 = j;

                for (int k1 = j - i1; k1 <= j1; ++k1)
                {
                    int l1 = k1 - j1;
                    this.func_175928_b(worldIn, new BlockPos(k, k1, l), 1 - l1);
                }
            }

            for (int i2 = 0; i2 < i; ++i2)
            {
                BlockPos blockpos1 = pos.up(i2);

                if (this.isAirLeaves(worldIn, blockpos1))
                {
                    this.setBlockAndNotifyAdequately(worldIn, blockpos1, this.logState);

                    if (i2 > 0)
                    {
                        this.func_181632_a(worldIn, rand, blockpos1.west(), BlockVine.EAST);
                        this.func_181632_a(worldIn, rand, blockpos1.north(), BlockVine.SOUTH);
                    }
                }

                if (i2 < i - 1)
                {
                    BlockPos blockpos2 = blockpos1.east();

                    if (this.isAirLeaves(worldIn, blockpos2))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, blockpos2, this.logState);

                        if (i2 > 0)
                        {
                            this.func_181632_a(worldIn, rand, blockpos2.east(), BlockVine.WEST);
                            this.func_181632_a(worldIn, rand, blockpos2.north(), BlockVine.SOUTH);
                        }
                    }

                    BlockPos blockpos3 = blockpos1.south().east();

                    if (this.isAirLeaves(worldIn, blockpos3))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, blockpos3, this.logState);

                        if (i2 > 0)
                        {
                            this.func_181632_a(worldIn, rand, blockpos3.east(), BlockVine.WEST);
                            this.func_181632_a(worldIn, rand, blockpos3.south(), BlockVine.NORTH);
                        }
                    }

                    BlockPos blockpos4 = blockpos1.south();

                    if (this.isAirLeaves(worldIn, blockpos4))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, blockpos4, this.logState);

                        if (i2 > 0)
                        {
                            this.func_181632_a(worldIn, rand, blockpos4.west(), BlockVine.EAST);
                            this.func_181632_a(worldIn, rand, blockpos4.south(), BlockVine.NORTH);
                        }
                    }
                }
            }

            return true;
        }
    }

    private void func_181632_a(World p_181632_1_, Random p_181632_2_, BlockPos p_181632_3_, PropertyBool p_181632_4_)
    {
        if (p_181632_2_.nextInt(3) > 0 && p_181632_1_.isAirBlock(p_181632_3_))
        {
            this.setBlockAndNotifyAdequately(p_181632_1_, p_181632_3_, this.vineState.withProperty(p_181632_4_, Boolean.valueOf(true)));
        }
    }

    private void func_175930_c(World worldIn, BlockPos p_175930_2_, int p_175930_3_)
    {
        int i = 2;

        for (int j = -i; j <= 0; ++j)
        {
            this.func_175925_a(worldIn, p_175930_2_.up(j), p_175930_3_ + 1 - j);
        }
    }

    private boolean isAirLeaves(World world, BlockPos pos)
    {
        net.minecraft.block.Block block = world.getBlockState(pos).getBlock();
        return block.isAir(world, pos) || block.isLeaves(world, pos);
    }
}