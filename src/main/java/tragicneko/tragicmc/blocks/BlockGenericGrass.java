package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockGenericGrass extends BlockGrass {

	private String texturePrefix;

	public BlockGenericGrass(String s) {
		super();
		this.texturePrefix = s;
		this.setHardness(0.6F);
		this.setHarvestLevel("shovel", 0);
		this.setCreativeTab(TragicMC.Survival);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing facing, IPlantable plant)
	{
		return (plant instanceof BlockDeadBush || plant instanceof BlockSapling || plant instanceof BlockMushroom || plant instanceof BlockLog || plant instanceof BlockTallGrass || plant instanceof BlockTragicSapling || plant instanceof BlockGenericBush || plant instanceof BlockGenericTallGrass) && facing == EnumFacing.UP;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(TragicBlocks.DeadDirt);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!world.isRemote && this != TragicBlocks.AshenGrass)
		{
			if (world.getLight(pos.up()) < 4 && world.getBlockLightOpacity(pos.up()) > 2)
			{
				world.setBlockState(pos, TragicBlocks.DeadDirt.getDefaultState());
			}
			else if (world.getLight(pos.up()) >= 6)
			{
				BlockPos pos2;
				for (byte l = 0; l < 4; ++l)
				{
					pos2 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
					Block block = world.getBlockState(pos2).getBlock();

					if (block == TragicBlocks.DeadDirt  && world.getLight(pos2.up()) >= 4 && world.getBlockLightOpacity(pos.up()) <= 2)
					{
						world.setBlockState(pos2, this.getDefaultState(), 4);
					}
				}
			}
		}
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state)
	{
		BlockPos blockpos1 = pos.up();
        int i = 0;

        while (i < 128)
        {
            BlockPos blockpos2 = blockpos1;
            int j = 0;

            while (true)
            {
                if (j < i / 16)
                {
                    blockpos2 = blockpos2.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                    if (world.getBlockState(blockpos2.down()).getBlock().getMaterial() == Material.grass && !world.getBlockState(blockpos2).getBlock().isNormalCube())
                    {
                        ++j;
                        continue;
                    }
                }
                else if (world.isAirBlock(blockpos2))
                {
                    if (rand.nextInt(8) == 0)
                    {
                        world.getBiomeGenForCoords(blockpos2).plantFlower(world, rand, blockpos2);
                    }
                    else
                    {
                        IBlockState iblockstate2 = this.getTallGrass().getDefaultState();

                        if (((BlockBush) this.getTallGrass()).canBlockStay(world, blockpos2, iblockstate2))
                        {
                            world.setBlockState(blockpos2, iblockstate2, 3);
                        }
                    }
                }

                ++i;
                break;
            }
        }
	}

	private Block getTallGrass() {
		if (this == TragicBlocks.BrushedGrass) return TragicBlocks.PaintedTallGrass;
		if (this == TragicBlocks.StarlitGrass) return TragicBlocks.StarlitTallGrass;
		if (this == TragicBlocks.AshenGrass && TragicMC.rand.nextInt(16) == 0) return TragicMC.rand.nextBoolean() ? TragicBlocks.AshenTallGrass : TragicBlocks.DriedTallGrass;
		return TragicBlocks.DeadBush;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		return -1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(IBlockState state)
	{
		return -1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, BlockPos pos, int renderPass)
	{
		return -1;
	}
}
