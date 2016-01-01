package tragicneko.tragicmc.items.weapons;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;

import com.google.common.collect.Sets;

public class ItemJack extends TragicTool {

	private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone,
			Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore,
			Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail,
			Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail, Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow,
			Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium, Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin,
			Blocks.lit_pumpkin, Blocks.tallgrass, Blocks.brown_mushroom_block, Blocks.cactus, Blocks.red_mushroom_block, Blocks.carrots, Blocks.cocoa, Blocks.deadbush,
			Blocks.double_plant, Blocks.hay_block, Blocks.leaves, Blocks.leaves2, Blocks.melon_block, Blocks.melon_stem, Blocks.nether_wart, Blocks.potatoes, Blocks.pumpkin_stem,
			Blocks.red_flower, Blocks.reeds, Blocks.sapling, Blocks.sponge, Blocks.tripwire, Blocks.vine, Blocks.waterlily, Blocks.web, Blocks.wheat, Blocks.yellow_flower,
			TragicBlocks.CarrotBlock, TragicBlocks.PotatoBlock, TragicBlocks.Wax, TragicBlocks.LightCobblestone, TragicBlocks.DarkCobblestone, TragicBlocks.Quicksand,
			TragicBlocks.DeadDirt, TragicBlocks.DarkSand, TragicBlocks.MercuryOre, TragicBlocks.TungstenOre, TragicBlocks.RubyOre,
			TragicBlocks.SapphireOre, Blocks.wool, Blocks.beacon, Blocks.obsidian, TragicBlocks.DarkStone, TragicBlocks.TragicOres, TragicBlocks.SmoothNetherrack,
			TragicBlocks.TragicObsidian, TragicBlocks.AshenGrass, TragicBlocks.AshenLeaves, TragicBlocks.AshenPlanks, TragicBlocks.AshenWood, TragicBlocks.BleachedLeaves,
			TragicBlocks.BleachedPlanks, TragicBlocks.BleachedWood, TragicBlocks.BrushedGrass, TragicBlocks.PaintedLeaves, TragicBlocks.PaintedPlanks, TragicBlocks.PaintedWood,
			TragicBlocks.DarkenedQuartz, TragicBlocks.BoneBlock, TragicBlocks.ErodedStone, TragicBlocks.SandstonePressurePlate, TragicBlocks.NetherBrickPressurePlate,
			Blocks.wooden_button, Blocks.stone_button, Blocks.oak_door, Blocks.acacia_door, Blocks.wooden_slab, TragicBlocks.SummonBlock});

	public ItemJack(ToolMaterial material, Doomsday dday) {
		super(1.0F, material, blocksEffectiveAgainst, dday);
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(TragicMC.Survival);
	}

	@Override
	public float getDigSpeed(ItemStack stack, IBlockState state)
	{
		return this.toolMaterial.getEfficiencyOnProperMaterial();
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f1, float f2)
	{
		if (!player.canPlayerEdit(pos, facing, stack))
		{
			return false;
		}
		else
		{
			UseHoeEvent event = new UseHoeEvent(player, stack, world, pos);
			if (MinecraftForge.EVENT_BUS.post(event))
			{
				return false;
			}

			if (event.getResult() == Result.ALLOW)
			{
				stack.damageItem(1, player);
				return true;
			}

			Block block = world.getBlockState(pos).getBlock();

			if (facing != EnumFacing.DOWN && world.getBlockState(pos.up()).getBlock().isAir(world, pos.up()) && (block == Blocks.grass || block == Blocks.dirt))
			{
				Block block1 = Blocks.farmland;
				world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, block1.stepSound.getStepSound(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getFrequency() * 0.8F);

				if (world.isRemote)
				{
					return true;
				}
				else
				{
					world.setBlockState(pos, block1.getDefaultState());
					stack.damageItem(1, player);
					return true;
				}
			}
			else
			{
				return false;
			}
		}
	}
}
