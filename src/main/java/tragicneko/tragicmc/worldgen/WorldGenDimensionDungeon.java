package tragicneko.tragicmc.worldgen;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.DungeonHooks;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;

public class WorldGenDimensionDungeon extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        boolean flag = true;
        int i = rand.nextInt(2) + 2;
        int j = -i - 1;
        int k = i + 1;
        boolean flag1 = true;
        boolean flag2 = true;
        int l = rand.nextInt(2) + 2;
        int i1 = -l - 1;
        int j1 = l + 1;
        int k1 = 0;
        int l1;
        int i2;
        int j2;
        BlockPos blockpos1;

        for (l1 = j; l1 <= k; ++l1)
        {
            for (i2 = -1; i2 <= 4; ++i2)
            {
                for (j2 = i1; j2 <= j1; ++j2)
                {
                    blockpos1 = pos.add(l1, i2, j2);
                    Material material = worldIn.getBlockState(blockpos1).getBlock().getMaterial();
                    boolean flag3 = material.isSolid();

                    if (i2 == -1 && !flag3)
                    {
                        return false;
                    }

                    if (i2 == 4 && !flag3)
                    {
                        return false;
                    }

                    if ((l1 == j || l1 == k || j2 == i1 || j2 == j1) && i2 == 0 && worldIn.isAirBlock(blockpos1) && worldIn.isAirBlock(blockpos1.up()))
                    {
                        ++k1;
                    }
                }
            }
        }

        if (k1 >= 1 && k1 <= 5)
        {
            for (l1 = j; l1 <= k; ++l1)
            {
                for (i2 = 3; i2 >= -1; --i2)
                {
                    for (j2 = i1; j2 <= j1; ++j2)
                    {
                        blockpos1 = pos.add(l1, i2, j2);

                        if (l1 != j && i2 != -1 && j2 != i1 && l1 != k && i2 != 4 && j2 != j1)
                        {
                            if (worldIn.getBlockState(blockpos1).getBlock() != Blocks.chest)
                            {
                                worldIn.setBlockToAir(blockpos1);
                            }
                        }
                        else if (blockpos1.getY() >= 0 && !worldIn.getBlockState(blockpos1.down()).getBlock().getMaterial().isSolid())
                        {
                            worldIn.setBlockToAir(blockpos1);
                        }
                        else if (worldIn.getBlockState(blockpos1).getBlock().getMaterial().isSolid() && worldIn.getBlockState(blockpos1).getBlock() != Blocks.chest)
                        {
                            if (i2 == -1 && rand.nextInt(4) != 0)
                            {
                                worldIn.setBlockState(blockpos1, TragicBlocks.DarkCobblestone.getStateFromMeta(1), 2);
                            }
                            else
                            {
                                worldIn.setBlockState(blockpos1, TragicBlocks.DarkCobblestone.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            l1 = 0;

            while (l1 < 2)
            {
                i2 = 0;

                while (true)
                {
                    if (i2 < 3)
                    {
                        label197:
                        {
                            j2 = pos.getX() + rand.nextInt(i * 2 + 1) - i;
                            int l2 = pos.getY();
                            int i3 = pos.getZ() + rand.nextInt(l * 2 + 1) - l;
                            BlockPos blockpos2 = new BlockPos(j2, l2, i3);

                            if (worldIn.isAirBlock(blockpos2))
                            {
                                int k2 = 0;
                                Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

                                while (iterator.hasNext())
                                {
                                    EnumFacing enumfacing = (EnumFacing)iterator.next();

                                    if (worldIn.getBlockState(blockpos2.offset(enumfacing)).getBlock().getMaterial().isSolid())
                                    {
                                        ++k2;
                                    }
                                }

                                if (k2 == 1)
                                {
                                    worldIn.setBlockState(blockpos2, ((BlockChest) TragicBlocks.SoulChest).correctFacing(worldIn, blockpos2, TragicBlocks.SoulChest.getDefaultState()), 2);
                                    TileEntity tileentity1 = worldIn.getTileEntity(blockpos2);

                                    if (tileentity1 instanceof TileEntityChest)
                                    {
                                        WeightedRandomChestContent.generateChestContents(rand, Arrays.asList(TragicItems.NetherStructureHook), (TileEntityChest)tileentity1, TragicItems.NetherStructureHook.getCount(rand));
                                    }

                                    break label197;
                                }
                            }

                            ++i2;
                            continue;
                        }
                    }

                    ++l1;
                    break;
                }
            }

            worldIn.setBlockState(pos, Blocks.mob_spawner.getDefaultState(), 2);
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityMobSpawner)
            {
                ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(rand));
            }
            else
            {
                TragicMC.logError("Failed to fetch mob spawner entity at (" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")");
            }

            return true;
        }
        else
        {
            return false;
        }
    }

	private String pickMobSpawner(Random rand)
	{
		return DungeonHooks.getRandomDungeonMob(rand);
	}
}
