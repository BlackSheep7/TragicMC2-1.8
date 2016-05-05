package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicNekoHouse extends Schematic {

	public SchematicNekoHouse(BlockPos origin, Structure structure) {
		super(origin, structure, 10, 10, 10);
	}

	@Override
	public Schematic generateStructure(int variant, World world, Random rand, int x, int y, int z) {
		this.setBlock(world, new BlockPos(x, y, z), TragicBlocks.NekitePlate.getStateFromMeta(0));
		return this;
	}

}
