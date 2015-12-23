package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;

public class BlockStarCrystal extends BlockColored {

	public BlockStarCrystal() {
		super(Material.glass);
		this.setCreativeTab(TragicMC.Survival);
		this.setResistance(10.0F);
		this.setHardness(2.0F);
		this.setStepSound(soundTypeGlass);
		this.setUnlocalizedName("tragicmc.starCrystal");
		this.lightValue = 15;
		this.lightOpacity = 2;
		this.setHarvestLevel("pickaxe", 0);
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random rand)
	{
		if (fortune > 0 && this.getItemDropped(state, rand, fortune) == Items.dye)
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
	public int quantityDropped(Random rand)
	{
		return 1 + rand.nextInt(4);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int level)
	{
		return Items.dye;
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
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing facing)
	{
		Block block = world.getBlockState(pos).getBlock();
		return block == this ? false : super.shouldSideBeRendered(world, pos, facing);
	}

}
