package tragicneko.tragicmc.worldgen.schematic;

import static tragicneko.tragicmc.TragicBlocks.NekitePlate;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicNekoTower extends Schematic {

	public SchematicNekoTower(BlockPos origin, Structure structure, World world) {
		super(origin, structure, world, 25, 10, 10);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {
		
		for (byte b = 0; b < 25; b++)
		{
			for (byte b2 = -5; b2 < 6; b2++)
			{
				for (byte b3 = -5; b3 < 5; b3++)
				{
					this.setBlockToAir(world, x + b3, y + b, z + b2);
				}
			}
		}
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(0));
		
		for (byte x1 = -4; x1 < 4; x1++)
		{
			for (byte z1 = -3; z1 < 5; z1++)
			{
				this.setBlock(world, new BlockPos(x + x1, y - 1, z + z1), NekitePlate.getStateFromMeta(5));
			}
		}
		
		this.setBlock(world, new BlockPos(x, y - 1, z - 4), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x - 1, y - 1, z - 4), NekitePlate.getStateFromMeta(5));
		
		this.setBlock(world, new BlockPos(x + 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z - 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z - 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z + 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 5), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 5), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z + 5), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x - 2, y, z), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x - 1, y, z + 2), Blocks.chest.getStateFromMeta(3), ChestHooks.uncommonHook);
		
		y++;
		this.setBlock(world, new BlockPos(x + 1, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 5), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 5), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(5));
		
		y++;
		this.setBlock(world, new BlockPos(x + 1, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 5), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 5), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x - 1, y, z + 2), NekitePlate.getStateFromMeta(5));
		
		y++;
		this.setBlock(world, new BlockPos(x + 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z - 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z - 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 5, y, z + 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z + 5), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z + 2), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(5));
		
		y++;
		this.setBlock(world, new BlockPos(x + 1, y, z - 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 4, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 5, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 4), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 4, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 5), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 5), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 1), NekitePlate.getStateFromMeta(5));
		
		y++;		
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(5));
		
		y++;		
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z - 1), NekitePlate.getStateFromMeta(5));
		
		y++;		
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x - 1, y, z - 1), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(5));
		
		y++;		
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x - 2, y, z), NekitePlate.getStateFromMeta(5));
		
		y++;		
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(5));
		
		y++;		
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), Blocks.mob_spawner.getStateFromMeta(0), rand.nextBoolean() ? "TragicMC.TragicNeko" : "TragicMC.WorkerNeko");
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x - 1, y, z + 2), NekitePlate.getStateFromMeta(5));
		this.setBlock(world, new BlockPos(x, y, z + 2), NekitePlate.getStateFromMeta(5));
		
		y++;		
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		y++;		
		this.setBlock(world, new BlockPos(x + 1, y, z - 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 3, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 4, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 3, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 2, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 3, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 4, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 4), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 4), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		
		y++;				
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		
		y++;				
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		y++;				
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x + 1, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		y++;						
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(0));
		
		y++;						
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x - 1, y, z), NekitePlate.getStateFromMeta(1));
		
		y++;						
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x - 1, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		y++;						
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 1, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		y++;						
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z - 1), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 1, y, z - 1), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 2, y, z), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x + 1, y, z + 1), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 2), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 1, y, z + 2), NekitePlate.getStateFromMeta(0));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(2));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(2));
		
		this.setBlock(world, new BlockPos(x, y, z), NekitePlate.getStateFromMeta(1));
		
		y++;						
		this.setBlock(world, new BlockPos(x - 1, y, z - 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z - 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
		this.setBlock(world, new BlockPos(x - 3, y, z), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 2, y, z + 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 1), Blocks.chest.getStateFromMeta(5), ChestHooks.uncommonHook);
		this.setBlock(world, new BlockPos(x - 3, y, z + 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x - 1, y, z + 3), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x, y, z + 3), NekitePlate.getStateFromMeta(1));
		
		y++;								
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(1));
		
		y++;								
		this.setBlock(world, new BlockPos(x + 1, y, z - 1), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z - 1), NekitePlate.getStateFromMeta(1));
		
		this.setBlock(world, new BlockPos(x + 1, y, z + 2), NekitePlate.getStateFromMeta(1));
		this.setBlock(world, new BlockPos(x - 2, y, z + 2), NekitePlate.getStateFromMeta(1));
		
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
