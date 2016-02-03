package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;

public class BlockTragicFlower extends BlockBush implements IGrowable {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockTragicFlower.EnumVariant.class);

	public BlockTragicFlower() {
		super();
		this.setCreativeTab(TragicMC.Survival);
		this.setUnlocalizedName("tragicmc.flower");
		this.setStepSound(soundTypeGrass);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariant.BLUE_SPIRANTHES));
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		return this.canPlaceBlockOn(world.getBlockState(pos.down()).getBlock());
	}

	@Override
	protected boolean canPlaceBlockOn(Block block)
	{
		return block instanceof BlockGrass || block == Blocks.dirt || block.getMaterial() == Material.ground || block.getMaterial() == Material.grass || block == TragicBlocks.MoltenRock || block == TragicBlocks.ScorchedRock;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (byte i = 0; i < 16; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean bool) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		if (rand.nextInt(4) != 0) return;

		int meta = this.getMetaFromState(state);
		if (meta == 14 && rand.nextInt(4) != 0) return;

		this.dropBlockAsItem(world, pos, state, 0);
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, VARIANT);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return meta == 0 || meta >= EnumVariant.values().length ? this.getDefaultState() : this.getDefaultState().withProperty(VARIANT, EnumVariant.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		Comparable comp = state.getValue(VARIANT);

		for (byte m = 0; m < 16; m++)
		{

			if (m >= EnumVariant.values().length) return 0;
			if (EnumVariant.values()[m] == comp) return m;
		}

		return 0;
	}
	
	@SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XYZ;
    }

	public enum EnumVariant implements IStringSerializable {
		BLUE_SPIRANTHES("blue_spiranthes"),
		PINK_SPIRANTHES("pink_spiranthes"),
		RED_SPIRANTHES("red_spiranthes"),
		WHITE_SPIRANTHES("white_spiranthes"),
		BLUE_CORAL("blue_coral"),
		RED_CORAL("red_coral"),
		PINK_GINGER("pink_ginger"),
		RED_GINGER("red_ginger"),
		BLUEBONNET("bluebonnet"),
		VIOLET_SAGE("violet_sage"),
		PINK_SAGE("pink_sage"),
		WHITE_SAGE("white_sage"),
		BIRD_OF_PARADISE("bird_of_paradise"),
		JUNIPER_BUSH("juniper_bush"),
		STAPELIA("stapelia"),
		THISTLE("thistle");

		private final String name;

		private EnumVariant(String name)
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
