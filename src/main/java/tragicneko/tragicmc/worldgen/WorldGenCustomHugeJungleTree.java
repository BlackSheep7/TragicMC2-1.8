package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.BlockVine;
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
                    this.func_175903_a(worldIn, new BlockPos(k, j - 3 + i1 / 2, l), this.logState);
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
                    this.func_175903_a(worldIn, blockpos1, this.logState);

                    if (i2 > 0)
                    {
                        this.func_175932_b(worldIn, rand, blockpos1.west(), BlockVine.EAST_FLAG);
                        this.func_175932_b(worldIn, rand, blockpos1.north(), BlockVine.SOUTH_FLAG);
                    }
                }

                if (i2 < i - 1)
                {
                    BlockPos blockpos2 = blockpos1.east();

                    if (this.isAirLeaves(worldIn, blockpos2))
                    {
                        this.func_175903_a(worldIn, blockpos2, this.logState);

                        if (i2 > 0)
                        {
                            this.func_175932_b(worldIn, rand, blockpos2.east(), BlockVine.WEST_FLAG);
                            this.func_175932_b(worldIn, rand, blockpos2.north(), BlockVine.SOUTH_FLAG);
                        }
                    }

                    BlockPos blockpos3 = blockpos1.south().east();

                    if (this.isAirLeaves(worldIn, blockpos3))
                    {
                        this.func_175903_a(worldIn, blockpos3, this.logState);

                        if (i2 > 0)
                        {
                            this.func_175932_b(worldIn, rand, blockpos3.east(), BlockVine.WEST_FLAG);
                            this.func_175932_b(worldIn, rand, blockpos3.south(), BlockVine.NORTH_FLAG);
                        }
                    }

                    BlockPos blockpos4 = blockpos1.south();

                    if (this.isAirLeaves(worldIn, blockpos4))
                    {
                        this.func_175903_a(worldIn, blockpos4, this.logState);

                        if (i2 > 0)
                        {
                            this.func_175932_b(worldIn, rand, blockpos4.west(), BlockVine.EAST_FLAG);
                            this.func_175932_b(worldIn, rand, blockpos4.south(), BlockVine.NORTH_FLAG);
                        }
                    }
                }
            }

            return true;
        }
    }

    private void func_175932_b(World worldIn, Random rand, BlockPos pos, int meta)
    {
        if (rand.nextInt(3) > 0 && worldIn.isAirBlock(pos))
        {
            this.func_175903_a(worldIn, pos, this.vineState.getBlock().getStateFromMeta(meta));
        }
    }

    private void func_175930_c(World worldIn, BlockPos pos, int offset)
    {
        byte b0 = 2;

        for (int j = -b0; j <= 0; ++j)
        {
            this.func_175925_a(worldIn, pos.up(j), offset + 1 - j);
        }
    }

    private boolean isAirLeaves(World world, BlockPos pos)
    {
        net.minecraft.block.Block block = world.getBlockState(pos).getBlock();
        return block.isAir(world, pos) || block.isLeaves(world, pos);
    }
}