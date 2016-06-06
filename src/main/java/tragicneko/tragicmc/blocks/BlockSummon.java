package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntitySummonBlock;

public class BlockSummon extends BlockContainer {

	public static final PropertyEnum BOSS = PropertyEnum.create("boss", BlockSummon.EnumBoss.class);

	public BlockSummon() {
		super(Material.iron);
		this.setCreativeTab(TragicMC.Creative);
		this.setResistance(100.0F);
		this.setHardness(150.0F);
		this.setStepSound(soundTypeStone);
		this.setUnlocalizedName("tragicmc.summonBlock");
		this.setDefaultState(this.blockState.getBaseState().withProperty(BOSS, EnumBoss.WITHER));
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntitySummonBlock();
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		int j = EnumBoss.values().length;
		for (int i = 0; i < j; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune)
	{
		return 48 + TragicMC.rand.nextInt(48);
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, BOSS);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return meta == 0 || meta >= EnumBoss.values().length ? this.getDefaultState() : this.getDefaultState().withProperty(BOSS, EnumBoss.values()[meta]);
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		Comparable comp = state.getValue(BOSS);
		
		for (byte m = 0; m < 16; m++)
		{
			if (m >= EnumBoss.values().length) return 0;
			if (EnumBoss.values()[m] == comp) return m;
		}
		
		return 0;
    }
	
	@Override
	public int getRenderType()
    {
		return 3;
    }
	
	public enum EnumBoss implements IStringSerializable {
		WITHER("wither"),
		ENDER_DRAGON("ender_dragon"),
		APIS("apis"),
		SKULTAR("skultar"),
		KITSUNAKUMA("kitsunakuma"),
		POLARIS("polaris"),
		EMPARIAH("empariah"),
		TIME_CONTROLLER("time_controller"),
		ENYVIL("enyvil"),
		CLAYMATION("claymation"),
		AEGAR("aegar"),
		NEKOID("professor_nekoid");
		
		private final String name;
		
		private EnumBoss(String name)
		{
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.name;
		}

		@Override
		public String getName() {
			return this.name;
		}
	}
}
