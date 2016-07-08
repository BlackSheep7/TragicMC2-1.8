package tragicneko.tragicmc.worldgen.schematic;

import static tragicneko.tragicmc.TragicBlocks.NekitePlate;
import static tragicneko.tragicmc.TragicBlocks.NekowoodPlanks;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicNekoHouse extends Schematic {

	public SchematicNekoHouse(BlockPos origin, Structure structure, World world) {
		super(origin, structure, world, 5, 10, 10);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {
		
		for (byte b = 0; b < 6; b++)
		{
			for (byte b2 = -5; b2 < 5; b2++)
			{
				for (byte b3 = -5; b3 < 5; b3++)
				{
					this.setBlockToAir(world, x + b3, y + b, z + b2);
				}
			}
		}

		for (byte x1 = -3; x1 < 5; x1++)
		{
			for (byte z1 = -3; z1 < 5; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekowoodPlanks.getStateFromMeta(0));
			}
		}
		
		this.setBlock(world, new BlockPos(x, y - 1, z + 5), NekowoodPlanks.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x + 1, y - 1, z + 5), NekowoodPlanks.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x, y - 1, z + 6), NekowoodPlanks.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x + 1, y - 1, z + 6), NekowoodPlanks.getStateFromMeta(0));

		this.setBlock(world, new BlockPos(x + 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 3, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 5, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 2, y, z + 3), Blocks.jukebox.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), Blocks.mob_spawner.getStateFromMeta(0), rand.nextInt(16) != 0 ? "TragicMC.WorkerNeko" : "TragicMC.TraderNeko");
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 5, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 5, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 4, y, z + 1), Blocks.bed.getStateFromMeta(11));
		this.setBlock(world, new BlockPos(x + 3, y, z + 1), Blocks.bed.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), Blocks.furnace.getStateFromMeta(5), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));
		this.setBlock(world, new BlockPos(x - 4, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 5, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 4, y, z), Blocks.bed.getStateFromMeta(11));
		this.setBlock(world, new BlockPos(x + 3, y, z), Blocks.bed.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x - 3, y, z), Blocks.furnace.getStateFromMeta(5), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));
		this.setBlock(world, new BlockPos(x - 4, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 5, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 5, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 2, y, z - 2), Blocks.chest.getStateFromMeta(3), ChestHooks.foodHook);
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), Blocks.chest.getStateFromMeta(3), ChestHooks.uncommonHook);
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 3, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), Blocks.anvil.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x, y, z - 3), Blocks.crafting_table.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 1, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z - 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		
		//meow
		++y;
		this.setBlock(world, new BlockPos(x + 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 5), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 3, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 5, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 5, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), Blocks.furnace.getStateFromMeta(5), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));
		this.setBlock(world, new BlockPos(x - 4, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 5, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), Blocks.furnace.getStateFromMeta(5), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));
		this.setBlock(world, new BlockPos(x - 4, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 5, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 3, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z - 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 4), NekitePlate.getStateFromMeta(1));
		
		++y;
		this.setBlock(world, new BlockPos(x + 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 5), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 3, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 3), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x + 4, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 3, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 5, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 5, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 5, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 5, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 4, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x + 4, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 3, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 3, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z - 4), NekitePlate.getStateFromMeta(1));
		
		++y;
		this.setBlock(world, new BlockPos(x + 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 5), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 3, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 5, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 5, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z - 4), NekitePlate.getStateFromMeta(1));
		
		++y;
		this.setBlock(world, new BlockPos(x + 2, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(1));
		
		++y;
		this.setBlock(world, new BlockPos(x + 3, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(1));		
		
		return this;
	}

	public static ItemStack getFurnaceStacks(int slot, Random rand) {
		ItemStack stack = null;
		if (slot == 1) stack = rand.nextInt(16) == 0 ? new ItemStack(TragicBlocks.Nekowood) : (rand.nextInt(4) == 0 ? new ItemStack(TragicBlocks.NekiteOre) : (rand.nextInt(16) == 0 ? new ItemStack(TragicItems.RedMercury) : null));
		if (slot == 0) stack = rand.nextInt(32) == 0 ? new ItemStack(Blocks.coal_block) : (rand.nextInt(4) == 0 ? new ItemStack(Items.coal) : (rand.nextInt(16) == 0 ? new ItemStack(TragicBlocks.Nekowood) : null));
		if (slot == 2) stack = rand.nextInt(16) == 0 ? new ItemStack(Items.coal, 1, 1) : (rand.nextInt(4) == 0 ? new ItemStack(TragicItems.Quicksilver) : (rand.nextInt(16) == 0 ? new ItemStack(TragicItems.Nekite) : (rand.nextInt(12) == 0 ? new ItemStack(Items.diamond) : null)));
		return stack;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		return tag;
	}

	@Override
	public Schematic readFromNBT(NBTTagCompound tag) {
		return this;
	}
}
