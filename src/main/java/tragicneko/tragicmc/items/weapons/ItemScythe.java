package tragicneko.tragicmc.items.weapons;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

public class ItemScythe extends TragicTool {

	private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.tallgrass,
			Blocks.brown_mushroom_block, Blocks.cactus, Blocks.red_mushroom_block, Blocks.carrots,
			Blocks.cocoa, Blocks.deadbush, Blocks.double_plant, Blocks.hay_block, Blocks.leaves, Blocks.leaves2,
			Blocks.melon_block, Blocks.melon_stem, Blocks.nether_wart, Blocks.potatoes, Blocks.pumpkin_stem,
			Blocks.red_flower, Blocks.reeds, Blocks.sapling, Blocks.sponge, Blocks.tripwire, Blocks.vine,
			Blocks.waterlily, Blocks.web, Blocks.wheat, Blocks.yellow_flower, TragicBlocks.CarrotBlock, TragicBlocks.PotatoBlock,
			TragicBlocks.AshenTallGrass, TragicBlocks.DriedGrass, TragicBlocks.StarlitTallGrass, TragicBlocks.PaintedTallGrass,
			TragicBlocks.Glowvine, TragicBlocks.DeadBush, TragicBlocks.AshenBush, TragicBlocks.TragicFlower, TragicBlocks.TragicSapling,
			TragicBlocks.HallowedLeafTrim, TragicBlocks.HallowedLeaves, TragicBlocks.DarkLeaves, TragicBlocks.WickedVine, TragicBlocks.DarkVine});

	public ItemScythe(ToolMaterial par2Material, Doomsday dday) {
		super(3.0F, par2Material, blocksEffectiveAgainst, dday);
		this.setHarvestLevel("scythe", 3);
		this.setCreativeTab(TragicMC.Survival);
	}


	@Override
	public float getDigSpeed(ItemStack stack, IBlockState state)
	{
		Material material = state.getBlock().getMaterial();
		return material == Material.plants || material == Material.vine || material == Material.coral || material == Material.leaves || material == Material.gourd ? this.toolMaterial.getEfficiencyOnProperMaterial() : super.getDigSpeed(stack, state);
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
