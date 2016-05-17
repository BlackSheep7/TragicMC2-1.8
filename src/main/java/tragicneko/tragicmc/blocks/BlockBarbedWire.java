package tragicneko.tragicmc.blocks;

import java.util.Random;

import net.minecraft.block.BlockWeb;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicItems;

public class BlockBarbedWire extends BlockWeb {
	
	public BlockBarbedWire() {
		super();
		this.setHarvestLevel("pickaxe", 2);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
        entityIn.attackEntityFrom(DamageSource.cactus, 1.0F);
    }

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return TragicItems.Nekite;
    }
}
