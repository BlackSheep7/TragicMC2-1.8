package tragicneko.tragicmc.blocks;

import net.minecraft.block.BlockChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntitySoulChest;

public class BlockSoulChest extends BlockChest {

	public BlockSoulChest(int open) {
		super(open);
		this.setCreativeTab(TragicMC.Creative);
	}

	@Override
	public int getRenderType()
	{
		return 42;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		TileEntitySoulChest tileentitychest = new TileEntitySoulChest(30);
		return tileentitychest;
	}
}
