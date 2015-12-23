package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;

public class BlockGenericOre extends Block {

	private boolean dropsSelf;

	public BlockGenericOre(int level, boolean dropsSelf) {
		super(Material.rock);
		this.dropsSelf = dropsSelf;
		this.setHarvestLevel("pickaxe", level);
		this.setStepSound(soundTypeStone);
		this.setCreativeTab(TragicMC.Survival);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int par3)
	{
		if (this.dropsSelf) return Item.getItemFromBlock(this);
		return this == TragicBlocks.RubyOre ? TragicItems.Ruby : TragicItems.Sapphire;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 1;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(state, rand, fortune) && !this.dropsSelf)
		{
			int j = rand.nextInt(fortune + 2) - 1;
			if (j < 0) j = 0;

			return this.quantityDropped(rand) * (j + 1);
		}
		else
		{
			return this.quantityDropped(rand);
		}
	}

	@Override
	public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune)
	{
		if (this.getItemDropped(world.getBlockState(pos), RANDOM, fortune) != Item.getItemFromBlock(this) && !this.dropsSelf)
		{
			int j1 = MathHelper.getRandomIntegerInRange(RANDOM, 2, 5);
			return j1;
		}
		return 0;
	}

}
