package tragicneko.tragicmc.worldgen.schematic;

import static tragicneko.tragicmc.TragicBlocks.NekitePlate;

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

public class SchematicNekoWarehouse extends Schematic {
	
	private static final byte[][] BIT_MASK = new byte[][] {
		{1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
		{0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0},
		{0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0},
		{1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
		{0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0},
		{0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},
		{0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},
		{0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0},
		{1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
		{0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0},
		{0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0},
		{1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1}
	}; //bitMask for the weird design on the top of the building

	public SchematicNekoWarehouse(BlockPos origin, Structure structure, World world) {
		super(origin, structure, world, 10, 15, 15);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {

		for (byte b = 0; b < 10; b++)
		{
			for (byte b2 = -8; b2 < 10; b2++)
			{
				for (byte b3 = -8; b3 < 9; b3++)
				{
					this.setBlockToAir(world, x + b3, y + b, z + b2);
				}
			}
		}

		this.setBlock(world, new BlockPos(x, y, z), Blocks.mob_spawner.getStateFromMeta(0), "TragicMC.MechaNeko");
		this.setBlock(world, new BlockPos(x, y, z - 1), Blocks.mob_spawner.getStateFromMeta(0), "TragicMC.MechaNeko");

		for (byte b = -7; b < 8; b++)
		{
			for (byte b2 = -7; b2 < 7; b2++)
			{
				this.setBlock(world, new BlockPos(x + b, y - 1, z + b2), NekitePlate.getStateFromMeta(5));
			}
		}

		for (byte b = -7; b < 7; b++)
		{
			if (b > -8)
			{
				this.setBlock(world, new BlockPos(x + b, y - 1, z - 8), NekitePlate.getStateFromMeta(3));
				this.setBlock(world, new BlockPos(x + b, y - 1, z + 7), NekitePlate.getStateFromMeta(3));
			}

			if (b > -7 && b < 7)
			{
				this.setBlock(world, new BlockPos(x + 7, y - 1, z + b), NekitePlate.getStateFromMeta(3));
				this.setBlock(world, new BlockPos(x - 7, y - 1, z + b), NekitePlate.getStateFromMeta(3));
			}
		}

		for (byte b = -4; b < -1; b++)
		{
			this.setBlock(world, new BlockPos(x, y - 1, z + b), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x - 2, y - 1, z + b), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x - 3, y - 1, z + b), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + 2, y - 1, z + b), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 3, y - 1, z + b), NekitePlate.getStateFromMeta(2));
		}

		for (byte b = 1; b < 4; b++)
		{
			this.setBlock(world, new BlockPos(x, y - 1, z + b), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x - 2, y - 1, z + b), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x - 3, y - 1, z + b), NekitePlate.getStateFromMeta(2));

			this.setBlock(world, new BlockPos(x + 2, y - 1, z + b), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 3, y - 1, z + b), NekitePlate.getStateFromMeta(2));
		}

		for (byte y1 = 0; y1 < 8; y1++)
		{
			this.setBlock(world, new BlockPos(x + 2, y + y1, z - 8), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x - 2, y + y1, z - 8), NekitePlate.getStateFromMeta(3));

			this.setBlock(world, new BlockPos(x + 2, y + y1, z + 7), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x - 2, y + y1, z + 7), NekitePlate.getStateFromMeta(3));

			this.setBlock(world, new BlockPos(x + 2, y + y1, z - 6), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x - 2, y + y1, z - 6), NekitePlate.getStateFromMeta(3));

			this.setBlock(world, new BlockPos(x + 1, y + y1, z + 5), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x - 1, y + y1, z + 5), NekitePlate.getStateFromMeta(3));

			this.setBlock(world, new BlockPos(x + 7, y + y1, z - 3), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x + 7, y + y1, z + 2), NekitePlate.getStateFromMeta(3));

			this.setBlock(world, new BlockPos(x - 7, y + y1, z - 3), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x - 7, y + y1, z + 2), NekitePlate.getStateFromMeta(3));

			this.setBlock(world, new BlockPos(x + 5, y + y1, z - 1), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x + 5, y + y1, z), NekitePlate.getStateFromMeta(3));

			this.setBlock(world, new BlockPos(x - 5, y + y1, z - 1), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x - 5, y + y1, z), NekitePlate.getStateFromMeta(3));

			for (byte b = -7; b < 8; b++)
			{
				if (b > -7 && b < 7)
				{
					if (y1 > 2 || b < -1 || b > 1) this.setBlock(world, new BlockPos(x + b, y + y1, z - 7), NekitePlate.getStateFromMeta(2));
					this.setBlock(world, new BlockPos(x + b, y + y1, z + 6), NekitePlate.getStateFromMeta(2));
				}

				if (b < 6)
				{
					this.setBlock(world, new BlockPos(x + 6, y + y1, z + b), NekitePlate.getStateFromMeta(2));
					this.setBlock(world, new BlockPos(x - 6, y + y1, z + b), NekitePlate.getStateFromMeta(2));
				}
			}

			this.setBlock(world, new BlockPos(x + 7, y + y1, z + 7), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x - 7, y + y1, z + 7), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x + 7, y + y1, z - 8), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x - 7, y + y1, z - 8), NekitePlate.getStateFromMeta(3));

			this.setBlock(world, new BlockPos(x + 5, y + y1, z + 5), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x - 5, y + y1, z + 5), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x + 5, y + y1, z - 6), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x - 5, y + y1, z - 6), NekitePlate.getStateFromMeta(3));

			if (y1 == 2 || y1 == 3 || y1 == 6)
			{
				for (byte b = -8; b < 8; b++)
				{
					if (b > -7)
					{
						if (y1 == 2 && (b > -2 && b < 2)) this.setBlock(world, new BlockPos(x + b, y + y1 + 2, z - 8), NekitePlate.getStateFromMeta(3));
						else this.setBlock(world, new BlockPos(x + b, y + y1, z - 8), NekitePlate.getStateFromMeta(3));
						this.setBlock(world, new BlockPos(x + b, y + y1, z + 7), NekitePlate.getStateFromMeta(3));
					}

					if (b > -8 && b < 7)
					{
						this.setBlock(world, new BlockPos(x + 7, y + y1, z + b), NekitePlate.getStateFromMeta(3));
						this.setBlock(world, new BlockPos(x - 7, y + y1, z + b), NekitePlate.getStateFromMeta(3));

						if (y1 == 6)
						{
							if (b > -7 && b < 6)
							{
								this.setBlock(world, new BlockPos(x + 2, y + y1 - 1, z + b), NekitePlate.getStateFromMeta(3));
								this.setBlock(world, new BlockPos(x + 3, y + y1 - 1, z + b), NekitePlate.getStateFromMeta(3));

								this.setBlock(world, new BlockPos(x - 2, y + y1 - 1, z + b), NekitePlate.getStateFromMeta(3));
								this.setBlock(world, new BlockPos(x - 3, y + y1 - 1, z + b), NekitePlate.getStateFromMeta(3));
							}

							if (b == 2 || b == 3 || b == -3 || b == -4)
							{
								this.setBlock(world, new BlockPos(x + 2, y + y1, z + b), NekitePlate.getStateFromMeta(0));
								this.setBlock(world, new BlockPos(x + 3, y + y1, z + b), NekitePlate.getStateFromMeta(0));

								this.setBlock(world, new BlockPos(x - 2, y + y1, z + b), NekitePlate.getStateFromMeta(0));
								this.setBlock(world, new BlockPos(x - 3, y + y1, z + b), NekitePlate.getStateFromMeta(0));
							}
						}
					}
				}
			}

			if (y1 == 2 || y1 == 3)
			{
				for (byte b = -6; b < 6; b++)
				{
					if (b > -6)
					{
						if (y1 == 3) this.setBlock(world, new BlockPos(x + b, y + y1, z - 6), NekitePlate.getStateFromMeta(3));
						if (y1 == 2) this.setBlock(world, new BlockPos(x + b, y + y1, z + 5), NekitePlate.getStateFromMeta(3));
					}

					if (b > -6 && b < 5 && y1 == 3)
					{
						this.setBlock(world, new BlockPos(x + 5, y + y1, z + b), NekitePlate.getStateFromMeta(3));
						this.setBlock(world, new BlockPos(x - 5, y + y1, z + b), NekitePlate.getStateFromMeta(3));
					}
				}
			}

			if (y1 == 0 || y1 == 3)
			{
				this.setBlock(world, new BlockPos(x, y + y1, z + 5), NekitePlate.getStateFromMeta(3));
			}
			else if (y1 == 1)
			{
				this.setBlock(world, new BlockPos(x, y + y1, z + 5), NekitePlate.getStateFromMeta(4));
			}
			else if (y1 == 2)
			{
				this.setBlock(world, new BlockPos(x, y + y1, z + 5), NekitePlate.getStateFromMeta(0));
			}

			if (y1 == 3)
			{
				this.setBlock(world, new BlockPos(x, y + y1, z - 8), NekitePlate.getStateFromMeta(4));
			}
		}

		for (byte b = -5; b < 6; b++)
		{
			for (byte b2 = -6; b2 < 6; b2++)
			{
				this.setBlock(world, new BlockPos(x + b, y + 8, z + b2), NekitePlate.getStateFromMeta(2));
			}
		}

		this.setBlock(world, new BlockPos(x + 2, y + 8, z - 7), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x - 2, y + 8, z - 7), NekitePlate.getStateFromMeta(3));

		this.setBlock(world, new BlockPos(x + 2, y + 8, z + 6), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x - 2, y + 8, z + 6), NekitePlate.getStateFromMeta(3));

		this.setBlock(world, new BlockPos(x + 6, y + 8, z - 7), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x - 6, y + 8, z - 7), NekitePlate.getStateFromMeta(3));

		this.setBlock(world, new BlockPos(x + 6, y + 8, z + 6), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x - 6, y + 8, z + 6), NekitePlate.getStateFromMeta(3));

		this.setBlock(world, new BlockPos(x + 6, y + 8, z + 2), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x - 6, y + 8, z + 2), NekitePlate.getStateFromMeta(3));

		this.setBlock(world, new BlockPos(x + 6, y + 8, z - 3), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x - 6, y + 8, z - 3), NekitePlate.getStateFromMeta(3));
		
		for (byte b = 0; b < BIT_MASK.length; b++)
		{
			for (byte b0 = 0; b0 < BIT_MASK[b].length; b0++)
			{
				if (BIT_MASK[b][b0] == 1) this.setBlock(world, new BlockPos(x + b0 - 5, y + 9, z + b - 6), NekitePlate.getStateFromMeta(3));
			}
		}

		for (byte y1 = 0; y1 < 3; y1++)
		{
			this.setBlock(world, new BlockPos(x + 3, y + y1, z - 6), Blocks.chest.getStateFromMeta(3), ChestHooks.commonHook);
			this.setBlock(world, new BlockPos(x + 4, y + y1, z - 6), Blocks.chest.getStateFromMeta(3), ChestHooks.commonHook);

			this.setBlock(world, new BlockPos(x - 3, y + y1, z - 6), Blocks.chest.getStateFromMeta(3), ChestHooks.commonHook);
			this.setBlock(world, new BlockPos(x - 4, y + y1, z - 6), Blocks.chest.getStateFromMeta(3), ChestHooks.commonHook);

			this.setBlock(world, new BlockPos(x - 5, y + y1, z - 2), Blocks.furnace.getStateFromMeta(5), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));
			this.setBlock(world, new BlockPos(x - 5, y + y1, z - 3), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x - 5, y + y1, z - 4), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x - 5, y + y1, z - 5), Blocks.furnace.getStateFromMeta(5), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));

			this.setBlock(world, new BlockPos(x - 5, y + y1, z + 1), Blocks.furnace.getStateFromMeta(5), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));
			this.setBlock(world, new BlockPos(x - 5, y + y1, z + 2), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x - 5, y + y1, z + 3), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x - 5, y + y1, z + 4), Blocks.furnace.getStateFromMeta(5), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));

			this.setBlock(world, new BlockPos(x + 5, y + y1, z - 2), Blocks.furnace.getStateFromMeta(4), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));
			this.setBlock(world, new BlockPos(x + 5, y + y1, z - 3), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 5, y + y1, z - 4), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 5, y + y1, z - 5), Blocks.furnace.getStateFromMeta(4), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));

			this.setBlock(world, new BlockPos(x + 5, y + y1, z + 1), Blocks.furnace.getStateFromMeta(4), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));
			this.setBlock(world, new BlockPos(x + 5, y + y1, z + 2), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 5, y + y1, z + 3), Blocks.chest.getStateFromMeta(4), ChestHooks.uncommonHook);
			this.setBlock(world, new BlockPos(x + 5, y + y1, z + 4), Blocks.furnace.getStateFromMeta(4), getFurnaceStacks(0, rand), getFurnaceStacks(1, rand), getFurnaceStacks(2, rand));

			if (y1 < 2)
			{
				this.setBlock(world, new BlockPos(x + 3, y + y1, z + 5), Blocks.chest.getStateFromMeta(0), ChestHooks.commonHook);
				this.setBlock(world, new BlockPos(x + 4, y + y1, z + 5), Blocks.chest.getStateFromMeta(0), ChestHooks.commonHook);

				this.setBlock(world, new BlockPos(x - 3, y + y1, z + 5), Blocks.chest.getStateFromMeta(0), ChestHooks.commonHook);
				this.setBlock(world, new BlockPos(x - 4, y + y1, z + 5), Blocks.chest.getStateFromMeta(0), ChestHooks.commonHook);

				if (y1 == 0)
				{
					this.setBlock(world, new BlockPos(x + 2, y + y1, z + 5), Blocks.crafting_table.getStateFromMeta(0));
					this.setBlock(world, new BlockPos(x - 2, y + y1, z + 5), Blocks.crafting_table.getStateFromMeta(0));
				}
				else if (y1 == 1)
				{
					this.setBlock(world, new BlockPos(x + 2, y + y1, z + 5), Blocks.anvil.getStateFromMeta(0));
					this.setBlock(world, new BlockPos(x - 2, y + y1, z + 5), Blocks.anvil.getStateFromMeta(0));
				}
			}
		}

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
