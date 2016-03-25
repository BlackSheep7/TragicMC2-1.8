package tragicneko.tragicmc.items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.WorldGenCustomHugeJungleTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomLollipopTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomOakTree;
import tragicneko.tragicmc.worldgen.WorldGenCustomSavannaTree;

public class ItemGenerator extends Item {

	private String[] subNames = new String[] {"VoidPitGenerator", "SpikeGenerator", "StarCrystalGenerator", "SphereGenerator", "SphereEraser", "LiquidRemover", "TreeGenerator",
			"LightningSummoner", "ExplosionGenerator", "IsleGenerator", "DirectedLightningSummoner", "PitGenerator"};

	private String[] textureNames = new String[] {"voidPitGenerator", "spikeGenerator", "starCrystalGenerator", "sphereGenerator", "sphereEraser", "liquidRemover", "treeGenerator",
			"lightningSummoner", "explosionGenerator", "isleGenerator", "directedLightningSummoner", "pitGenerator"};
	
	private static final IBlockState jungleLog = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState jungleLeaves = Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));

	public ItemGenerator()
	{
		super();
		this.setCreativeTab(TragicMC.Creative);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("tragicmc.worldGen");
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Generate some of the WorldGen features!");
		par2List.add("Some of this is really CPU intensive.");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (world.isRemote) return stack;
		Random random = world.rand;

		double size;
		int meta = stack.getItemDamage();

		BlockPos coords;
		ArrayList<BlockPos> list;

		String[] names;
		Set<Block> filter;

		Vec3 vec = WorldHelper.getVecFromEntity(player, 100.0);

		int Xcoord = MathHelper.floor_double(vec.xCoord);
		int Ycoord = MathHelper.floor_double(vec.yCoord);
		int Zcoord = MathHelper.floor_double(vec.zCoord);

		switch(meta)
		{
		case 0:
			names = TragicConfig.voidPitFilter;
			filter = new HashSet<Block>();

			for (String s : names)
			{
				Block block = (Block) Block.blockRegistry.getObject(new ResourceLocation(s));
				if (block != null) filter.add(block);
			}

			if (!TragicConfig.voidPitUsesFilter) filter.clear();

			size = TragicConfig.voidPitSize;

			for (int pow = 0; pow + Ycoord >= 0 && pow + Ycoord <= 256; --pow)
			{
				if (size >= 5.5D)
				{
					list = WorldHelper.getBlocksInCircularRange(world, size * 0.31773D, Xcoord, Ycoord + pow, Zcoord); //makes sure the middle of the pit is clear

					for (int mapping = 0; mapping < list.size(); mapping++)
					{
						coords = list.get(mapping);
						if (random.nextInt(2) != 0 && !filter.contains(world.getBlockState(coords).getBlock())) world.setBlockToAir(coords);
					}
				}

				list = WorldHelper.getBlocksInCircularRange(world, size * 0.64773D, Xcoord, Ycoord + pow, Zcoord); //gives the pit more of a gradual feel

				for (int mapping = 0; mapping < list.size(); mapping++)
				{
					coords = list.get(mapping);
					if (random.nextInt(2) != 0 && !filter.contains(world.getBlockState(coords).getBlock())) world.setBlockToAir(coords);
				}

				list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + pow, Zcoord); //outer part that has the most scattered blocks

				for (int mapping = 0; mapping < list.size(); mapping++)
				{
					coords = list.get(mapping);
					if (random.nextInt(2) != 0 && !filter.contains(world.getBlockState(coords).getBlock())) world.setBlockToAir(coords);
				}
			}

			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Void pit generated with size of " + size));
			break;
		case 3:
			names = TragicConfig.sphereFilter;
			filter = new HashSet<Block>();

			for (String s : names)
			{
				Block block = (Block) Block.blockRegistry.getObject(new ResourceLocation(s));
				if (block != Blocks.air) filter.add(block);
			}

			size = TragicConfig.sphereSize;
			Block ablock;
			list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord, Zcoord);

			ablock = TragicConfig.sphereGenUsesFilter && filter.size() > 0 ? (Block) filter.toArray()[itemRand.nextInt(filter.size())] : Block.getBlockById(random.nextInt(4096));
			byte attempts = 0;			

			if (!TragicConfig.sphereGenUsesFilter)
			{
				while (!ablock.isOpaqueCube() && !(ablock instanceof BlockBreakable) || ablock.hasTileEntity(ablock.getDefaultState()) || ablock instanceof BlockFalling)
				{
					ablock = Block.getBlockById(random.nextInt(4096));
					attempts++;
					if (attempts > 40) break;
				}
				
				if (!ablock.isOpaqueCube() && !(ablock instanceof BlockBreakable)|| ablock.hasTileEntity(ablock.getDefaultState()) || ablock instanceof BlockFalling) ablock = Blocks.tnt;
			}
			
			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				world.setBlockState(coords, ablock.getDefaultState());
			}

			if (!list.isEmpty() && ablock != null)
			{
				String s = ablock.getUnlocalizedName();
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Sphere generated with size of " + size + " made of " + StatCollector.translateToLocal(s + ".name")));
			}
			break;
		case 4:
			names = TragicConfig.eraserFilter;
			filter = new HashSet<Block>();

			for (String s : names)
			{
				Block block = (Block) Block.blockRegistry.getObject(new ResourceLocation(s));
				if (block != Blocks.air) filter.add(block);
			}

			list = WorldHelper.getBlocksInSphericalRange(world, TragicConfig.eraserSize, Xcoord, Ycoord, Zcoord);

			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				if (!TragicConfig.eraserUsesFilter || !filter.contains(world.getBlockState(coords).getBlock())) world.setBlockToAir(coords);
			}

			if (!list.isEmpty())
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Spherical area erased."));
			}
			break;
		case 5:
			list = WorldHelper.getBlocksInSphericalRange(world, TragicConfig.eraserSize, Xcoord, Ycoord, Zcoord);

			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);

				if (world.getBlockState(coords).getBlock() instanceof BlockLiquid)
				{
					world.setBlockToAir(coords);
				}
			}

			if (!list.isEmpty()) player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Spherical area of liquid removed."));

			break;
		case 6:
			WorldGenerator object;

			switch (random.nextInt(14))
			{
			default:
				object = new WorldGenForest(true, false);
				break;
			case 0:
				object = new WorldGenBigTree(true);
				break;
			case 1:
				object = new WorldGenSavannaTree(true);
				break;
			case 2:
				object = new WorldGenCanopyTree(true);
				break;
			case 3:
				object = new WorldGenMegaJungle(true, 10, 20, jungleLog, jungleLeaves);
				break;
			case 4:
				object = new WorldGenMegaPineTree(false, random.nextBoolean());
				break;
			case 5:
				object = new WorldGenTaiga2(true);
				break;
			case 6:
				object = new WorldGenTrees(true);
				break;
			case 7:
				object = new WorldGenCustomLollipopTree(TragicBlocks.BleachedWood.getDefaultState(), TragicBlocks.BleachedLeaves.getDefaultState());
				break;
			case 8:
				object = new WorldGenCustomSavannaTree(random.nextBoolean(), TragicBlocks.AshenWood.getDefaultState(), TragicBlocks.AshenLeaves.getDefaultState());
				break;
			case 9:
				object = new WorldGenCustomHugeJungleTree(true, random.nextInt(3) + 4, 10, TragicBlocks.PaintedLeaves.getDefaultState(), TragicBlocks.PaintedWood.getDefaultState(), TragicBlocks.Glowvine.getDefaultState());
				break;
			case 10:
				object = new WorldGenCustomOakTree(true, 6, TragicBlocks.PaintedWood.getDefaultState(), TragicBlocks.PaintedLeaves.getDefaultState());
				break;
			}

			for (int y1 = 1; y1 < 4; y1++)
			{
				for (int z1 = -1; z1 < 2; z1++)
				{
					for (int x1 = -1; x1 < 2; x1++)
					{
						world.setBlockToAir(new BlockPos(Xcoord + x1, Ycoord + y1, Zcoord + z1));
					}
				}
			}

			if (object.generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord)))
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Tree generated successfully"));
			}
			else
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Tree generation failed."));
			}
			break;
		case 1:
			names = TragicConfig.spikeFilter;
			filter = new HashSet<Block>();

			for (String s : names)
			{
				Block block = (Block) Block.blockRegistry.getObject(new ResourceLocation(s));
				if (block != Blocks.air) filter.add(block);
			}

			if (!TragicConfig.spikeGenUsesFilter) filter.clear();

			size = TragicConfig.spikeSize;
			Block spike = filter.isEmpty() ? TragicBlocks.DarkStone : (Block) filter.toArray()[itemRand.nextInt(filter.size())];
			int blockMeta = TragicConfig.spikeGenUsesFilter && !filter.isEmpty() ? 0 : 14;
			int spikeType = random.nextInt(2);
			boolean flag = false;
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Spike of type " + spikeType + " and size of " + size + " generated."));

			for (int y1 = 0; y1 < 256; y1++)
			{
				if (random.nextBoolean())
				{
					size *= TragicConfig.spikeRegression; //reduce the radius of the spike randomly, give at least 2 levels at max radius

					if (random.nextInt(3) == 0 && size >= 0.4888233D) //randomly apply offset to the spike, this sometimes gives it a cool spiral effect
					{
						Xcoord += random.nextInt(2) - random.nextInt(2);
						Zcoord += random.nextInt(2) - random.nextInt(2);
					}

					if (spikeType == 1 && !flag && y1 >= 35 && y1 <= 70 && random.nextBoolean() && size <= 0.774446314D) //Type 1 has chance to thicken at a random spot once
					{
						size *= 2.86333567D;
						flag = true;
					}
				}

				if (size < TragicConfig.spikeCutoff || Ycoord + y1 > 256) break;

				list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1, Zcoord);

				for (int j = 0; j < list.size(); j++)
				{
					coords = list.get(j);
					world.setBlockState(coords, spike.getStateFromMeta(blockMeta), 2);
				}
			}
			break;
		case 2:
			size = 0.35D * random.nextDouble() + 0.75D;
			int blockMeta2 = random.nextInt(16);
			Block block;

			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Star Crystal with size of " + size + " generated."));

			for (int y1 = 0; y1 < 12; y1++)
			{
				size *= 0.91377745D;

				if (size < 0.444443755D || Ycoord + y1 > 256) break;

				list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1 + (size * 0.5D), Zcoord);

				for (int j = 0; j < list.size(); j++)
				{
					coords = list.get(j);
					world.setBlockState(coords, TragicBlocks.StarCrystal.getStateFromMeta(blockMeta2), 2);
				}
			}
			break;
		case 7:
			world.addWeatherEffect(new EntityLightningBolt(world, Xcoord, Ycoord, Zcoord));
			player.addChatMessage(new ChatComponentText("Lightning created."));
			break;
		case 8:
			float f = (float) TragicConfig.explosionBaseSize + ((float) TragicConfig.explosionSizeVariation * itemRand.nextFloat());
			world.createExplosion(player, Xcoord, Ycoord, Zcoord, f, WorldHelper.getMobGriefing(world));
			player.addChatMessage(new ChatComponentText("Explosion created with size of " + f));
			break;
		case 9:
			double regression = 0.88977745D;
			double cutoff = 0.48943755D;
			ArrayList<BlockPos> cands = new ArrayList<BlockPos>();

			size = random.nextDouble() * 3.5D + 1.5D;
			Xcoord += random.nextInt(8) - random.nextInt(8);
			Zcoord += random.nextInt(8) - random.nextInt(8);
			Ycoord = world.getTopSolidOrLiquidBlock(new BlockPos(Xcoord, 0, Zcoord)).getY() + 1 + random.nextInt(18) + 10;
			int yMax = Ycoord;

			for (int y1 = 0; y1 > -32; y1--)
			{
				if (size < cutoff) break;
				size *= regression; //reduce the radius of the spike randomly

				if (random.nextBoolean())
				{
					if (random.nextInt(6) == 0) //randomly apply offset to the spike, this sometimes gives it a cool spiral effect
					{
						Xcoord += random.nextInt(2) - random.nextInt(2);
						Zcoord += random.nextInt(2) - random.nextInt(2);
					}
				}

				list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + y1, Zcoord);

				for (BlockPos coords2 : list)
				{
					block = world.getBlockState(coords2).getBlock();
					if (!cands.contains(coords2))
					{
						if (yMax < coords2.getY()) yMax = coords2.getY();
						cands.add(coords2);
					}
				}
			}

			int rand = random.nextInt(4) + 2;

			for (BlockPos coords2 : cands)
			{
				if (coords2.getY() >= yMax)
				{
					world.setBlockState(coords2, TragicBlocks.ErodedStone.getDefaultState(), 2);
				}
				else if (coords2.getY() >= yMax - rand - random.nextInt(2))
				{
					world.setBlockState(coords2, TragicBlocks.DeadDirt.getDefaultState(), 2);
				}
				else
				{
					world.setBlockState(coords2, TragicBlocks.DarkStone.getDefaultState(), 2);
				}
			}

			break;
		case 10:
			EntityDirectedLightning lightning = new EntityDirectedLightning(world, Xcoord, Ycoord, Zcoord, player);
			lightning.setPosition(Xcoord, Ycoord, Zcoord);
			world.spawnEntityInWorld(lightning);
			player.addChatMessage(new ChatComponentText("Directed Lightning created!"));
			break;
		case 11:
			int depth = Ycoord - 10 - random.nextInt(10);
			size = 3.0D * random.nextDouble() + 3.0D;
			cands = new ArrayList<BlockPos>();

			for (int pow = 0; pow + Ycoord >= depth && pow + Ycoord >= 0 && pow + Ycoord <= 256; --pow)
			{
				if (size >= 5.5D)
				{
					list = WorldHelper.getBlocksInCircularRange(world, size * 0.31773D, Xcoord, Ycoord + pow, Zcoord); //makes sure the middle of the pit is clear

					for (int mapping = 0; mapping < list.size(); mapping++)
					{
						coords = list.get(mapping);
						if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
					}
				}

				list = WorldHelper.getBlocksInCircularRange(world, size * 0.64773D, Xcoord, Ycoord + pow, Zcoord); //gives the pit more of a gradual feel

				for (int mapping = 0; mapping < list.size(); mapping++)
				{
					coords = list.get(mapping);
					if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
				}

				list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + pow, Zcoord); //outer part that has the most scattered blocks

				for (int mapping = 0; mapping < list.size(); mapping++)
				{
					coords = list.get(mapping);
					if (random.nextBoolean() && !cands.contains(coords)) cands.add(coords);
				}

				if (size >= 3.0D && random.nextInt(4) == 0) size *= 0.987425D; //reduces size of the void pit randomly, similarly to spikes, but this is to reduce lag
			}

			block = random.nextBoolean() ? TragicBlocks.IceSpike : (random.nextBoolean() ? Blocks.flowing_lava : (random.nextBoolean() ? TragicBlocks.RadiatedGas : TragicBlocks.Quicksand));
			int m = block == TragicBlocks.Quicksand && random.nextBoolean() ? 3 : 0;

			for (BlockPos coords2 : cands)
			{
				if (coords2.getY() > depth + 1) world.setBlockToAir(coords2);
				else world.setBlockState(coords2, block.getStateFromMeta(m), 3);
			}
			player.addChatMessage(new ChatComponentText("Pit generated using " + block.getUnlocalizedName() + " as the bottom."));
			break;
		}

		return stack;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < subNames.length; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int damage = itemstack.getItemDamage();
		if (damage >= subNames.length) damage = subNames.length - 1;

		return getUnlocalizedName() + "." + textureNames[damage];
	}
}
