package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicNetherTower extends SchematicDesertTower {
	
	private static Block netherBrick = Blocks.nether_brick;
	private static Block nether = Blocks.netherrack;
	private static Block netherStair = Blocks.nether_brick_stairs;
	private static Block plateNether = TragicBlocks.NetherBrickPressurePlate;
	private static Block netherFence = Blocks.nether_brick_fence;

	public SchematicNetherTower(BlockPos pos, Structure str, World world) {
		super(pos, str, world);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {
		//First layer

		//First row
		this.setBlock(world, x - 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z); //Starting block of the whole schematic
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, netherBrick, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, netherBrick, 1, 2);

		//Second layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, netherBrick, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, netherBrick, 1, 2);

		//Third layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, netherBrick, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, netherBrick, 1, 2);

		//Fourth layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, netherBrick, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z, netherFence, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, netherFence, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, netherFence, 3, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z, netherFence, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, netherBrick, 1, 2);

		//Fifth layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, netherBrick, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z, netherFence, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, netherFence, 0, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, netherFence, 0, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z, netherFence, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, netherBrick, 1, 2);

		//Sixth layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, netherBrick, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, netherBrick, 1, 2);

		//Seventh layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, netherBrick, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z, netherFence, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 1, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, netherFence, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, netherFence, 3, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z, netherFence, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 1, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, netherBrick, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, netherBrick, 1, 2);

		//Eighth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z, netherFence, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherFence, 0, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, netherFence, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z, netherFence, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 1, 2);

		//Ninth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 2, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlock(world, x, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x, y, z + 2, netherBrick, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 2, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 1, 2);

		//Tenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z, netherFence, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherFence, 0, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, netherFence, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlockToAir(world, x + 1, y, z + 1);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z, netherFence, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 1, 2);

		//Eleventh layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z, netherFence, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherFence, 0, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, netherFence, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlockToAir(world, x + 1, y, z + 1);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z, netherFence, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 1, 2);

		//Twelfth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x - 1, y, z, netherBrick, 2, 2);
		this.setBlock(world, x - 1, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 2, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, netherBrick, 1, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, netherBrick, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x + 1, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x + 1, y, z, netherBrick, 2, 2);
		this.setBlock(world, x + 1, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 2, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, netherBrick, 1, 2);

		//Thirteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z, lava, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherFence, 2, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, nether, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, netherFence, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x + 1, y, z, lava, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 1, 2);

		//Fourteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z, lava, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherFence, 0, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, nether, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, netherFence, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x + 1, y, z, lava, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 1, 2);

		//Fifteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x - 1, y, z, lava, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherBrick, 2, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, nether, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, netherBrick, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, netherBrick, 2, 2);
		this.setBlock(world, x + 1, y, z, lava, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, netherBrick, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 1, 2);

		//Sixteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, nether, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, netherBrick, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 1, 2);

		//Seventeenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherBrick, 0, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, nether, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, netherBrick, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, netherBrick, 0, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, netherBrick, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 1, 2);

		//Eighteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, plateNether, 0, 2);
		this.setBlock(world, x - 1, y, z, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, plateNether, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherBrick, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, trapChest, 0, 2, ChestHooks.uncommonHook);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, netherBrick, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, plateNether, 0, 2);
		this.setBlock(world, x + 1, y, z, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, plateNether, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 1, 2);

		//Nineteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 1, 2);
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlock(world, x - 1, y, z, netherBrick, 1, 2);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherFence, 0, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, netherFence, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 1, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlock(world, x + 1, y, z, netherBrick, 1, 2);
		this.setBlockToAir(world, x + 1, y, z + 1);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 1, 2);

		//Twentieth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x - 2, y, z, netherBrick, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherBrick, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z + 2, netherBrick, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherFence, 0, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, netherFence, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z + 2, netherBrick, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x + 2, y, z, netherBrick, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, netherBrick, 1, 2);

		//Twenty-first layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, netherStair, 2, 2);
		this.setBlock(world, x - 2, y, z, netherStair, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, netherStair, 3, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, netherStair, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z + 2, netherStair, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, netherStair, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, netherStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, netherStair, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z + 2, netherStair, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, netherStair, 2, 2);
		this.setBlock(world, x + 2, y, z, netherStair, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, netherStair, 3, 2);

		//Twenty-second layer
		y++;

		//Third row
		this.setBlock(world, x - 1, y, z - 1, netherStair, 0, 2);
		this.setBlock(world, x - 1, y, z, netherBrick, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, netherStair, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 1, netherStair, 2, 2);
		this.setBlockToAir(world, x, y, z);
		this.setBlock(world, x, y, z + 1, netherStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 1, netherStair, 1, 2);
		this.setBlock(world, x + 1, y, z, netherBrick, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, netherStair, 1, 2);

		//Twenty-third layer
		y++;

		//Third row
		this.setBlock(world, x - 1, y, z, netherBrick, 1, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z);

		//Fifth row
		this.setBlock(world, x + 1, y, z, netherBrick, 1, 2);

		//Twenty-fourth layer
		y++;

		//Third row
		this.setBlock(world, x - 1, y, z, netherStair, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z, luxuryBlocks[rand.nextInt(luxuryBlocks.length)], 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z, netherStair, 1, 2);

		//Twenty-fifth layer
		y++;

		//Fourth row
		this.setBlock(world, x, y, z, slab, 6, 2);
		
		return this;
	}
}
