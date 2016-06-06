package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.WorldGenCustomCanopyTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomHugeJungleTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomLollipopTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomOakTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomSavannaTree;

public class BlockTragicSapling extends Block implements IGrowable, IPlantable {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockTragicSapling.EnumVariant.class);

	public BlockTragicSapling()
	{
		super(Material.plants);
		this.setUnlocalizedName("tragicmc.sapling");
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setCreativeTab(TragicMC.Survival);
		this.setStepSound(soundTypeGrass);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariant.PAINTED));
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (byte i = 0; i < EnumVariant.values().length; i++)
			par3.add(new ItemStack(par1, 1, i));
	}
	
	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		if (rand.nextBoolean()) return;
		int meta = this.getMetaFromState(state);
		Object object = null;

		switch(meta)
		{
		case 0:
			if (rand.nextBoolean())
			{
				object = new WorldGenCustomOakTree(false, 4, TragicBlocks.PaintedWood.getDefaultState(), TragicBlocks.PaintedLeaves.getDefaultState());
			}
			else
			{
				object = new WorldGenCustomHugeJungleTree(false, rand.nextInt(3) + 4, 10, TragicBlocks.PaintedLeaves.getDefaultState(), TragicBlocks.PaintedWood.getDefaultState(), TragicBlocks.Glowvine.getDefaultState());
			}
			break;
		case 1:
			object = new WorldGenCustomLollipopTree(TragicBlocks.BleachedWood.getDefaultState(), TragicBlocks.BleachedLeaves.getDefaultState());
			break;
		case 2:
			object = new WorldGenCustomSavannaTree(true, TragicBlocks.AshenWood.getDefaultState(), TragicBlocks.AshenLeaves.getDefaultState());
			break;
		case 3:
			object = new WorldGenCustomOakTree(true, 4, TragicBlocks.HallowedWood.getDefaultState(), TragicBlocks.HallowedLeaves.getDefaultState());
			break;
		case 4:
			object = new WorldGenCustomCanopyTree(TragicBlocks.Darkwood.getDefaultState(), TragicBlocks.DarkLeaves.getDefaultState());
			break;
		case 5:
			object = new WorldGenCustomOakTree(false, 4, TragicBlocks.Nekowood.getDefaultState(), TragicBlocks.NekowoodLeaves.getDefaultState());
		default:
			return;
		}

		world.setBlockToAir(pos);

		if (object instanceof WorldGenerator)
		{
			WorldGenerator worldGen = (WorldGenerator) object;
			if (!worldGen.generate(world, rand, pos.down()))
			{
				world.setBlockState(pos, this.getStateFromMeta(meta), 4);
			}
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return world.getBlockState(pos);
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
	
	public enum EnumVariant implements IStringSerializable {
		PAINTED("painted"),
		BLEACHED("bleached"),
		ASHEN("ashen"),
		HALLOWED("hallowed"),
		DARKWOOD("darkwood"),
		NEKOWOOD("nekowood");
		
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
