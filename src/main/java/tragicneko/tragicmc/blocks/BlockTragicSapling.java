package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.worldgen.WorldGenAshenTree;
import tragicneko.tragicmc.worldgen.WorldGenBleachedOakTree;
import tragicneko.tragicmc.worldgen.WorldGenDarkForestTree;
import tragicneko.tragicmc.worldgen.WorldGenHallowedTree;
import tragicneko.tragicmc.worldgen.WorldGenLargePaintedTree;
import tragicneko.tragicmc.worldgen.WorldGenPaintedTree;

public class BlockTragicSapling extends Block implements IGrowable, IPlantable {

	private String[] treeNames = new String[] {"Painted", "Bleached", "Ashen", "Hallowed", "Darkwood"};

	public BlockTragicSapling()
	{
		super(Material.plants);
		this.setUnlocalizedName("tragicmc.sapling");
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setCreativeTab(TragicMC.Survival);
		this.setStepSound(soundTypeGrass);
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
	public int getRenderType()
	{
		return 1;
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
		for (byte i = 0; i < 5; i++)
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
				object = new WorldGenPaintedTree(true, rand.nextBoolean());
			}
			else
			{
				object = new WorldGenLargePaintedTree(true, rand.nextInt(3) + 4, 10);
			}
			break;
		case 1:
			object = new WorldGenBleachedOakTree(true, true);
			break;
		case 2:
			object = new WorldGenAshenTree(true);
			break;
		case 3:
			object = new WorldGenHallowedTree(true);
			break;
		case 4:
			object = new WorldGenDarkForestTree();
			break;
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
}
