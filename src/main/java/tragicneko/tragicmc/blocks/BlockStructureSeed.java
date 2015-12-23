package tragicneko.tragicmc.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntityStructureSeed;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class BlockStructureSeed extends BlockContainer {
	
	public static final PropertyInteger STRUCTURE = PropertyInteger.create("structure_id", 0, Structure.structureList.length);

	public BlockStructureSeed() {
		super(Material.gourd);
		this.setResistance(100.0F);
		this.setHardness(10.0F);
		this.setCreativeTab(TragicMC.Creative);
		this.setUnlocalizedName("tragicmc.structureSeed");
		this.setDefaultState(this.blockState.getBaseState().withProperty(STRUCTURE, 0));
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityStructureSeed();
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (int i = 0; i < Structure.structureList.length && Structure.structureList[i] != null; i++)
			par3.add(new ItemStack(par1, 1, i));
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, STRUCTURE);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return this.getDefaultState().withProperty(STRUCTURE, meta);
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		return ((Integer) state.getValue(STRUCTURE)).intValue();
    }
}
