package tragicneko.tragicmc.worldgen.schematic;

import static tragicneko.tragicmc.TragicBlocks.NekitePlate;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicNekoBarracks extends Schematic {

	public SchematicNekoBarracks(BlockPos origin, Structure structure, World world) {
		super(origin, structure, world, 8, 10, 10);
	}

	@Override
	public Schematic generateStructure( World world, Random rand, int x, int y, int z) {
		
		for (byte b = 0; b < 8; b++)
		{
			for (byte b2 = -5; b2 < 6; b2++)
			{
				for (byte b3 = -5; b3 < 6; b3++)
				{
					this.setBlockToAir(world, x + b3, y + b, z + b2);
				}
			}
		}

		for (byte b = -2; b < 3; b++)
		{
			this.setBlock(world, new BlockPos(x + b, y - 1, z - 5), NekitePlate.getStateFromMeta(1));
		}

		for (byte b = -4; b < -2; b++)
		{
			this.setBlock(world, new BlockPos(x - 2, y - 1, z + b), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x - 1, y - 1, z + b), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x, y - 1, z + b), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 1, y - 1, z + b), NekitePlate.getStateFromMeta(5));
			this.setBlock(world, new BlockPos(x + 2, y - 1, z + b), NekitePlate.getStateFromMeta(1));
		}

		for (byte b = -2; b < 5; b++)
		{
			for (byte b2 = -3; b2 < 4; b2++)
			{
				this.setBlock(world, new BlockPos(x + b2, y - 1, z + b), NekitePlate.getStateFromMeta(5));
			}
		}

		for (byte b = -4; b < 5; b++)
		{
			if (b < -1 || b > 1) this.setBlock(world, new BlockPos(x + b, y, z - 3), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + b, y, z + 5), NekitePlate.getStateFromMeta(1));
		}

		for (byte b = -3; b < 6; b++)
		{
			this.setBlock(world, new BlockPos(x - 4, y, z + b), NekitePlate.getStateFromMeta(1));
			this.setBlock(world, new BlockPos(x + 4, y, z + b), NekitePlate.getStateFromMeta(1));
		}

		for (byte b = -3; b < 4; b++)
		{
			this.setBlock(world, new BlockPos(x + b, y, z + 4), NekitePlate.getStateFromMeta(3));
		}

		for (byte b = -2; b < 5; b++)
		{
			this.setBlock(world, new BlockPos(x - 3, y, z + b), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x + 3, y, z + b), NekitePlate.getStateFromMeta(3));
		}
		
		ChestGenHooks hook = ChestHooks.uncommonHook;
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), Blocks.chest.getStateFromMeta(5), hook);
		this.setBlock(world, new BlockPos(x - 2, y, z), Blocks.chest.getStateFromMeta(5), hook);
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), Blocks.chest.getStateFromMeta(5), hook);
		this.setBlock(world, new BlockPos(x + 2, y, z - 2), Blocks.chest.getStateFromMeta(4), hook);
		this.setBlock(world, new BlockPos(x + 2, y, z), Blocks.chest.getStateFromMeta(4), hook);
		this.setBlock(world, new BlockPos(x + 2, y, z + 2), Blocks.chest.getStateFromMeta(4), hook);

		this.setBlock(world, new BlockPos(x, y, z + 3), Blocks.mob_spawner.getDefaultState(), "TragicMC.TragicNeko");

		y++;

		for (byte b = -3; b < 4; b++)
		{
			this.setBlock(world, new BlockPos(x + b, y, z + 4), NekitePlate.getStateFromMeta(2));
		}

		for (byte b = -2; b < 5; b++)
		{
			this.setBlock(world, new BlockPos(x - 3, y, z + b), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 3, y, z + b), NekitePlate.getStateFromMeta(2));
		}

		this.setBlock(world, new BlockPos(x - 3, y, z - 2), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x - 3, y, z + 4), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x + 3, y, z + 4), NekitePlate.getStateFromMeta(3));

		this.setBlock(world, new BlockPos(x + 2, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(1));

		y++;

		for (byte b = -3; b < 4; b++)
		{
			this.setBlock(world, new BlockPos(x + b, y, z + 4), NekitePlate.getStateFromMeta(2));
		}

		for (byte b = -2; b < 5; b++)
		{
			this.setBlock(world, new BlockPos(x - 3, y, z + b), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 3, y, z + b), NekitePlate.getStateFromMeta(2));
		}

		this.setBlock(world, new BlockPos(x - 3, y, z - 2), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x - 3, y, z + 4), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x + 3, y, z + 4), NekitePlate.getStateFromMeta(3));

		for (byte b = -2; b < 3; b++)
		{
			this.setBlock(world, new BlockPos(x + b, y, z - 3), NekitePlate.getStateFromMeta(1));
		}
		
		y++;
		
		for (byte b = -2; b < 5; b++)
		{
			this.setBlock(world, new BlockPos(x - 3, y, z + b), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x - 2, y, z + b), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x - 1, y, z + b), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x + 1, y, z + b), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x + 2, y, z + b), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 3, y, z + b), NekitePlate.getStateFromMeta(3));
		}
		
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x + 2, y, z - 2), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x + 2, y, z + 4), NekitePlate.getStateFromMeta(3));
		
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(4));
		this.setBlock(world, new BlockPos(x, y, z - 1), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x, y, z + 4), NekitePlate.getStateFromMeta(2));
		
		y++;
		
		for (byte b = -2; b < 5; b++)
		{
			this.setBlock(world, new BlockPos(x - 1, y, z + b), NekitePlate.getStateFromMeta(3));
			this.setBlock(world, new BlockPos(x, y, z + b), NekitePlate.getStateFromMeta(2));
			this.setBlock(world, new BlockPos(x + 1, y, z + b), NekitePlate.getStateFromMeta(3));
		}
		
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x, y, z + 4), NekitePlate.getStateFromMeta(3));
		
		y -= 4;

		this.setBlock(world, new BlockPos(x + 1, y, z - 1), Blocks.bed.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x + 1, y, z + 1), Blocks.bed.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x + 1, y, z + 3), Blocks.bed.getStateFromMeta(3));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), Blocks.bed.getStateFromMeta(11));
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), Blocks.bed.getStateFromMeta(11));
		this.setBlock(world, new BlockPos(x + 2, y, z + 3), Blocks.bed.getStateFromMeta(11));
		
		this.setBlock(world, new BlockPos(x - 1, y, z - 1), Blocks.bed.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), Blocks.bed.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), Blocks.bed.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), Blocks.bed.getStateFromMeta(9));
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), Blocks.bed.getStateFromMeta(9));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), Blocks.bed.getStateFromMeta(9));
		
		return this;
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
