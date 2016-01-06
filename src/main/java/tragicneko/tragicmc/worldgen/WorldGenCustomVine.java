package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCustomVine extends WorldGenerator {

	private final IBlockState vineState;
	private final int maxHeight;

	public WorldGenCustomVine(IBlockState vine, int attempts)
	{
		this.vineState = vine;
		this.maxHeight = attempts;
	}

	public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        for (; pos.getY() < this.maxHeight; pos = pos.up())
        {
            if (worldIn.isAirBlock(pos))
            {
                EnumFacing[] aenumfacing = EnumFacing.Plane.HORIZONTAL.facings();
                int i = aenumfacing.length;

                for (int j = 0; j < i; ++j)
                {
                    EnumFacing enumfacing = aenumfacing[j];

                    if (Blocks.vine.canPlaceBlockOnSide(worldIn, pos, enumfacing))
                    {
                        IBlockState iblockstate = this.vineState.withProperty(BlockVine.NORTH, Boolean.valueOf(enumfacing == EnumFacing.NORTH)).withProperty(BlockVine.EAST, Boolean.valueOf(enumfacing == EnumFacing.EAST)).withProperty(BlockVine.SOUTH, Boolean.valueOf(enumfacing == EnumFacing.SOUTH)).withProperty(BlockVine.WEST, Boolean.valueOf(enumfacing == EnumFacing.WEST));
                        worldIn.setBlockState(pos, iblockstate, 2);
                        break;
                    }
                }
            }
            else
            {
                pos = pos.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
            }
        }

        return true;
    }
}
