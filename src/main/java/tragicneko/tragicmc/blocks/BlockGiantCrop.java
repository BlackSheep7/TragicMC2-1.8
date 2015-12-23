package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockGiantCrop extends Block {

	public BlockGiantCrop() {
		super(Material.plants);
		this.setCreativeTab(TragicMC.Survival);
		this.setHardness(0.5F);
		this.setResistance(1.0F);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return this == TragicBlocks.CarrotBlock ? Items.carrot : Items.potato;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 2 + rand.nextInt(3);
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(state, rand, fortune))
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

}
