package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicNekoidsForwardBase extends Schematic {

	public SchematicNekoidsForwardBase(BlockPos origin, Structure structure) {
		super(origin, structure, 15, 32, 32);
	}

	@Override
	public Schematic generateStructure(int variant, World world, Random rand, int x, int y, int z) {
		this.setBlock(world, x, y, z, TragicBlocks.NekitePlate, 0, 3);
		return null;
	}

}
