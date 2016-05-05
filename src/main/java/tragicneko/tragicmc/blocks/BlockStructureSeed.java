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
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntityStructureSeed;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class BlockStructureSeed extends BlockContainer {
	
	public static final PropertyInteger STRUCTURE = PropertyInteger.create("structure_id", 0, 15);

	public BlockStructureSeed() {
		super(Material.plants);
		this.setResistance(100.0F);
		this.setHardness(10.0F);
		this.setCreativeTab(TragicMC.Creative);
		this.setUnlocalizedName("tragicmc.structureSeed");
		this.setDefaultState(this.blockState.getBaseState().withProperty(STRUCTURE, 0));
	}

	@Override
	public boolean isFullBlock()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
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
		for (int i = 0; i < Structure.getRegistrySize() && Structure.getStructureById(i) != null && i < 16; i++)
			par3.add(new ItemStack(par1, 1, i));
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, STRUCTURE);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
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
	
	@Override
	public int getRenderType()
    {
		return 3;
    }
}
