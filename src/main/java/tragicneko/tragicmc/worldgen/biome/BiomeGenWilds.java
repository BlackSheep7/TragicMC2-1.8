package tragicneko.tragicmc.worldgen.biome;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;

public class BiomeGenWilds extends TragicBiome {

	public BiomeGenWilds(int par1, byte par2) {
		super(par1, par2);
		this.topBlock = Blocks.dirt.getDefaultState();
		this.fillerBlock = Blocks.stone.getDefaultState();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
	}
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chk, int x, int z, double d)
	{
		this.generateWildsBiomeTerrain(worldIn, rand, chk, x, z, d);
	}

	public final void generateWildsBiomeTerrain(World worldIn, Random rand, ChunkPrimer chk, int x, int z, double d)
    {
		boolean flag = true;
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        int k = -1;
        int l = (int)(d / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int i1 = x & 15;
        int j1 = z & 15;

        for (int k1 = 255; k1 >= 0; --k1)
        {
            if (k1 <= rand.nextInt(5))
            {
                chk.setBlockState(j1, k1, i1, Blocks.air.getDefaultState());
            }
            else
            {
                IBlockState iblockstate2 = chk.getBlockState(j1, k1, i1);

                if (iblockstate2.getBlock().getMaterial() == Material.air)
                {
                    k = -1;
                }
                else if (iblockstate2.getBlock() == TragicBlocks.DarkStone)
                {
                    if (k == -1)
                    {
                        if (l <= 0)
                        {
                            iblockstate = null;
                            iblockstate1 = TragicBlocks.DarkStone.getDefaultState();
                        }
                        else if (k1 >= 59 && k1 <= 64)
                        {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        k = l;

                        if (k1 >= 62)
                        {
                            chk.setBlockState(j1, k1, i1, iblockstate);
                        }
                        else if (k1 < 56 - l)
                        {
                            iblockstate = null;
                            iblockstate1 = TragicBlocks.DarkStone.getDefaultState();
                            chk.setBlockState(j1, k1, i1, TragicBlocks.DarkStone.getDefaultState());
                        }
                        else
                        {
                            chk.setBlockState(j1, k1, i1, iblockstate1);
                        }
                    }
                    else if (k > 0)
                    {
                        --k;
                        chk.setBlockState(j1, k1, i1, iblockstate1);
                    }
                }
            }
        }
	}

}
