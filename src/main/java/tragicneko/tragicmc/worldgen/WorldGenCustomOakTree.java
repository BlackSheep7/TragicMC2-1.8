package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCustomOakTree extends WorldGenAbstractTree
{
	private final int minTreeHeight;
	private final IBlockState logState;
	private final IBlockState leafState;
	private final boolean growsVines;
	private final IBlockState vineState;
	private IBlockState trimState = null;
	
	public WorldGenCustomOakTree(boolean largeTree, int treeMin, IBlockState log, IBlockState leaf)
	{
		this(largeTree, treeMin, log, leaf, false, null);
	}
	
	public WorldGenCustomOakTree(boolean largeTree, int treeMin, IBlockState log, IBlockState leaf, boolean growVine, IBlockState vine)
	{
		super(largeTree);
		this.minTreeHeight = treeMin;
		this.logState = log;
		this.leafState = leaf;
		this.growsVines = growVine;
		this.vineState = vine;
	}
	
	public WorldGenCustomOakTree setTrim(IBlockState state) {
		this.trimState = state;
		return this;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256)
        {
            byte b0;
            int l;

            for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j)
            {
                b0 = 1;

                if (j == pos.getY())
                {
                    b0 = 0;
                }

                if (j >= pos.getY() + 1 + i - 2)
                {
                    b0 = 2;
                }

                for (int k = pos.getX() - b0; k <= pos.getX() + b0 && flag; ++k)
                {
                    for (l = pos.getZ() - b0; l <= pos.getZ() + b0 && flag; ++l)
                    {
                        if (j >= 0 && j < 256)
                        {
                            if (!this.isReplaceable(worldIn, new BlockPos(k, j, l)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                BlockPos down = pos.down();
                Block block1 = worldIn.getBlockState(down).getBlock();
                boolean isSoil = block1.canSustainPlant(worldIn, down, net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling)Blocks.sapling);

                if (isSoil && pos.getY() < 256 - i - 1)
                {
                    block1.onPlantGrow(worldIn, down, pos);
                    b0 = 3;
                    byte b1 = 0;
                    int i1;
                    int j1;
                    int k1;
                    int l1;
                    BlockPos blockpos1;

                    for (l = pos.getY() - b0 + i; l <= pos.getY() + i; ++l)
                    {
                        i1 = l - (pos.getY() + i);
                        j1 = b1 + 1 - i1 / 2;

                        for (k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1)
                        {
                            l1 = k1 - pos.getX();

                            for (int i2 = pos.getZ() - j1; i2 <= pos.getZ() + j1; ++i2)
                            {
                                int j2 = i2 - pos.getZ();

                                if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i1 != 0)
                                {
                                    blockpos1 = new BlockPos(k1, l, i2);
                                    Block block = worldIn.getBlockState(blockpos1).getBlock();

                                    if (block.isAir(worldIn, blockpos1) || block.isLeaves(worldIn, blockpos1) || block.getMaterial() == Material.vine)
                                    {
                                        this.func_175903_a(worldIn, blockpos1, leafState);
                                        
                                        if (this.trimState != null && (block.isAir(worldIn, blockpos1.up()) || block.isLeaves(worldIn, blockpos1.up())))
                                        {
                                        	this.func_175903_a(worldIn, blockpos1.up(), this.trimState);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for (l = 0; l < i; ++l)
                    {
                        BlockPos upN = pos.up(l);
                        Block block2 = worldIn.getBlockState(upN).getBlock();

                        if (block2.isAir(worldIn, upN) || block2.isLeaves(worldIn, upN) || block2.getMaterial() == Material.vine)
                        {
                            this.func_175903_a(worldIn, pos.up(l), logState);

                            if (this.growsVines && l > 0)
                            {
                                if (rand.nextInt(3) > 0 && worldIn.isAirBlock(pos.add(-1, l, 0)))
                                {
                                    this.func_175903_a(worldIn, pos.add(-1, l, 0), vineState.getBlock().getStateFromMeta(BlockVine.EAST_FLAG));
                                }

                                if (rand.nextInt(3) > 0 && worldIn.isAirBlock(pos.add(1, l, 0)))
                                {
                                    this.func_175903_a(worldIn, pos.add(1, l, 0), vineState.getBlock().getStateFromMeta(BlockVine.WEST_FLAG));
                                }

                                if (rand.nextInt(3) > 0 && worldIn.isAirBlock(pos.add(0, l, -1)))
                                {
                                    this.func_175903_a(worldIn, pos.add(0, l, -1), vineState.getBlock().getStateFromMeta(BlockVine.SOUTH_FLAG));
                                }

                                if (rand.nextInt(3) > 0 && worldIn.isAirBlock(pos.add(0, l, 1)))
                                {
                                    this.func_175903_a(worldIn, pos.add(0, l, 1), vineState.getBlock().getStateFromMeta(BlockVine.NORTH_FLAG));
                                }
                            }
                        }
                    }

                    if (this.growsVines)
                    {
                        for (l = pos.getY() - 3 + i; l <= pos.getY() + i; ++l)
                        {
                            i1 = l - (pos.getY() + i);
                            j1 = 2 - i1 / 2;

                            for (k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1)
                            {
                                for (l1 = pos.getZ() - j1; l1 <= pos.getZ() + j1; ++l1)
                                {
                                    BlockPos blockpos3 = new BlockPos(k1, l, l1);

                                    if (worldIn.getBlockState(blockpos3).getBlock().isLeaves(worldIn, blockpos3))
                                    {
                                        BlockPos blockpos4 = blockpos3.west();
                                        blockpos1 = blockpos3.east();
                                        BlockPos blockpos5 = blockpos3.north();
                                        BlockPos blockpos2 = blockpos3.south();

                                        if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos4).getBlock().isAir(worldIn, blockpos4))
                                        {
                                            this.placeHangingVines(worldIn, blockpos4, BlockVine.EAST_FLAG);
                                        }

                                        if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos1).getBlock().isAir(worldIn, blockpos1))
                                        {
                                            this.placeHangingVines(worldIn, blockpos1, BlockVine.WEST_FLAG);
                                        }

                                        if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos5).getBlock().isAir(worldIn, blockpos5))
                                        {
                                            this.placeHangingVines(worldIn, blockpos5, BlockVine.SOUTH_FLAG);
                                        }

                                        if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos2).getBlock().isAir(worldIn, blockpos2))
                                        {
                                            this.placeHangingVines(worldIn, blockpos2, BlockVine.NORTH_FLAG);
                                        }
                                    }
                                }
                            }
                        }

                        if (rand.nextInt(5) == 0 && i > 5)
                        {
                            for (l = 0; l < 2; ++l)
                            {
                                for (i1 = 0; i1 < 4; ++i1)
                                {
                                    if (rand.nextInt(4 - l) == 0)
                                    {
                                        j1 = rand.nextInt(3);
                                        EnumFacing enumfacing = EnumFacing.getHorizontal(i1).getOpposite();
                                        this.func_175905_a(worldIn, pos.add(enumfacing.getFrontOffsetX(), i - 5 + l, enumfacing.getFrontOffsetZ()), Blocks.cocoa, j1 << 2 | EnumFacing.getHorizontal(i1).getHorizontalIndex());
                                    }
                                }
                            }
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    private void placeHangingVines(World worldIn, BlockPos pos, int meta)
    {
        this.func_175903_a(worldIn, pos, vineState.getBlock().getStateFromMeta(meta));
        int j = 4;

        for (pos = pos.down(); worldIn.getBlockState(pos).getBlock().isAir(worldIn, pos) && j > 0; --j)
        {
            this.func_175903_a(worldIn, pos, vineState.getBlock().getStateFromMeta(meta));
            pos = pos.down();
        }
    }
}