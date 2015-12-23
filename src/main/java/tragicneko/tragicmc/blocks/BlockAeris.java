package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntityAeris;

public class BlockAeris extends BlockBush implements ITileEntityProvider
{
	public static final PropertyEnum STATE = PropertyEnum.create("state", BlockAeris.EnumState.class);
	
	public BlockAeris()
	{
		super();
		this.setUnlocalizedName("tragicmc.aeris");
		this.setCreativeTab(TragicMC.Survival);
		this.setTickRandomly(true);
		this.setStepSound(soundTypeGrass);
		this.setDefaultState(this.blockState.getBaseState().withProperty(STATE, BlockAeris.EnumState.PURE));
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (byte i = 0; i < 3; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityAeris();
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		super.breakBlock(world, pos, state);
		world.removeTileEntity(pos);
	}

	@Override
	public boolean onBlockEventReceived(World world, BlockPos pos, IBlockState state, int id, int type)
	{
		super.onBlockEventReceived(world, pos, state, id, type);
		TileEntity tileentity = world.getTileEntity(pos);
		return tileentity != null ? tileentity.receiveClientEvent(id, type) : false;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (pos.getY() <= 50 && world.provider.getDimensionId() == TragicConfig.collisionID && !world.canBlockSeeSky(pos)|| this.getMetaFromState(state) >= 2)
		{
			int pow = this.getMetaFromState(state) >= 2 ? 8 : 3;

			for (int l = 0; l < pow; ++l)
			{
				double d1 = pos.getY() + rand.nextFloat();
				double d3 = 0.0D;
				double d4 = 0.0D;
				double d5 = 0.0D;
				int i1 = rand.nextInt(2) * 2 - 1;
				int j1 = rand.nextInt(2) * 2 - 1;
				d3 = (rand.nextFloat() - 0.5D) * 0.125D;
				d4 = (rand.nextFloat() - 0.5D) * 0.125D;
				d5 = (rand.nextFloat() - 0.5D) * 0.125D;
				double d2 = pos.getZ() + 0.5D + 0.25D * j1;
				d5 = rand.nextFloat() * 1.0F * j1;
				double d0 = pos.getX() + 0.5D + 0.25D * i1;
				d3 = rand.nextFloat() * 1.0F * i1;
				world.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
			}

			if (rand.nextInt(16) == 0 || this.getMetaFromState(state) >= 2)
			{
				for (int i = 0; i < 3; i++)
				{
					world.spawnParticle(EnumParticleTypes.SPELL_WITCH, pos.getX() + (rand.nextDouble() - rand.nextDouble()) * 0.25 + 0.5,
							pos.getY() + rand.nextDouble() * 0.35 + 0.25,
							pos.getZ() + (rand.nextDouble() - rand.nextDouble()) * 0.25 + 0.5,
							0, 0, 0);
				}
			}
		}
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, STATE);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return meta == 1 ? this.getDefaultState().withProperty(STATE, EnumState.CORRUPTING) : (meta == 2 ? this.getDefaultState().withProperty(STATE, EnumState.CORRUPT) : this.getDefaultState());
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
		Comparable comp = state.getValue(STATE);
		return comp == EnumState.CORRUPTING ? 1 : (comp == EnumState.CORRUPT ? 2 : 0);
    }
	
	public static enum EnumState implements IStringSerializable {
		PURE("pure"),
		CORRUPTING("corrupting"),
		CORRUPT("corrupt");
		
		private final String name;
		
		private EnumState(String name)
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
