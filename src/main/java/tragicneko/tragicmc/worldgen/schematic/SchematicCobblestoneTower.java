package tragicneko.tragicmc.worldgen.schematic;

import static tragicneko.tragicmc.worldgen.schematic.SchematicDesertTower.sand;
import static tragicneko.tragicmc.worldgen.schematic.SchematicDesertTower.slab;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicCobblestoneTower extends SchematicDesertTower {

	private static Block cobble = Blocks.cobblestone;
	private static Block mossCob = Blocks.mossy_cobblestone;
	private static Block brick = Blocks.stonebrick;
	private static Block brickStair = Blocks.stone_brick_stairs;
	private static Block plateStone = Blocks.stone_pressure_plate;

	public SchematicCobblestoneTower(BlockPos pos, Structure str, World world) {
		super(pos, str, world);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z)
	{
		//First layer

		//First row
		this.setBlock(world, x - 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z); //Starting block of the whole schematic
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, brick, 0, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, mossCob, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, mossCob, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, cobble, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, mossCob, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, mossCob, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, cobble, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, mossCob, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, mossCob, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, brick, 0, 2);

		//Second layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, brick, 0, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, cobble, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, mossCob, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, mossCob, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, mossCob, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, cobble, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, brick, 0, 2);

		//Third layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, brick, 0, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, brick, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, brick, 1, 2);
		this.setBlock(world, x - 2, y, z, brick, 1, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 1, 2);
		this.setBlock(world, x - 2, y, z + 2, brick, 1, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, brick, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, brick, 1, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, brick, 1, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, brick, 1, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, brick, 1, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, brick, 1, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, brick, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, brick, 1, 2);
		this.setBlock(world, x + 2, y, z, brick, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 1, 2);
		this.setBlock(world, x + 2, y, z + 2, brick, 1, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, brick, 0, 2);

		//Fourth layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, brick, 0, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, mossCob, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z, brickStair, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, mossCob, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, mossCob, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, brickStair, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, brickStair, 3, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, mossCob, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, mossCob, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z, brickStair, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, brick, 0, 2);

		//Fifth layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, brick, 0, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, mossCob, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, cobble, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, cobble, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, mossCob, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, cobble, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, mossCob, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, brick, 0, 2);

		//Sixth layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, brick, 0, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, brick, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, brick, 1, 2);
		this.setBlock(world, x - 2, y, z, brick, 1, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 1, 2);
		this.setBlock(world, x - 2, y, z + 2, brick, 1, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, brick, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, brick, 1, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, brick, 1, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, brick, 1, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, brick, 1, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, brick, 1, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, brick, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, brick, 1, 2);
		this.setBlock(world, x + 2, y, z, brick, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 1, 2);
		this.setBlock(world, x + 2, y, z + 2, brick, 1, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, brick, 0, 2);

		//Seventh layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, brick, 0, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z, brickStair, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, brick, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, cobble, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, brickStair, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, brickStair, 3, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, mossCob, 0, 2);
		this.setBlock(world, x + 2, y, z, brickStair, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, brick, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, brick, 0, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, brick, 0, 2);

		//Eighth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, mossCob, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, cobble, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, cobble, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, cobble, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, cobble, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, mossCob, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, brick, 0, 2);

		//Ninth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, brick, 1, 2);
		this.setBlock(world, x - 2, y, z, brick, 1, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 1, 2);
		this.setBlock(world, x - 2, y, z + 2, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, brick, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, brick, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, brick, 1, 2);
		this.setBlock(world, x, y, z - 1, brick, 1, 2);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlock(world, x, y, z + 1, brick, 1, 2);
		this.setBlock(world, x, y, z + 2, brick, 1, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, brick, 1, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, brick, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, brick, 1, 2);
		this.setBlock(world, x + 2, y, z, brick, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 1, 2);
		this.setBlock(world, x + 2, y, z + 2, brick, 0, 2);

		//Tenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z, brickStair, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, mossCob, 0, 2);
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, cobble, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, brickStair, 2, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, brickStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, mossCob, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlockToAir(world, x + 1, y, z + 1);
		this.setBlock(world, x + 1, y, z + 2, cobble, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z, brickStair, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, brick, 0, 2);

		//Eleventh layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, mossCob, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, cobble, 0, 2);
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, cobble, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, mossCob, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlockToAir(world, x + 1, y, z + 1);
		this.setBlock(world, x + 1, y, z + 2, cobble, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, cobble, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, brick, 0, 2);

		//Twelfth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, brick, 1, 2);
		this.setBlock(world, x - 2, y, z, brick, 1, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 1, 2);
		this.setBlock(world, x - 2, y, z + 2, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, brick, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, brick, 1, 2);
		this.setBlock(world, x - 1, y, z, brick, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, brick, 1, 2);
		this.setBlock(world, x - 1, y, z + 2, brick, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, brick, 1, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, brick, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, brick, 1, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, brick, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, brick, 1, 2);
		this.setBlock(world, x + 1, y, z, brick, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, brick, 1, 2);
		this.setBlock(world, x + 1, y, z + 2, brick, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, brick, 1, 2);
		this.setBlock(world, x + 2, y, z, brick, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 1, 2);
		this.setBlock(world, x + 2, y, z + 2, brick, 0, 2);

		//Thirteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x - 2, y, z, brickStair, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, cobble, 0, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, mossCob, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, brick, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, brickStair, 2, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sand, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, brickStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 1, y, z - 1, cobble, 0, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, brick, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x + 2, y, z, brickStair, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 0, 2);

		//Fourteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x - 2, y, z, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, mossCob, 0, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, brick, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sand, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 1, y, z - 1, mossCob, 0, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, mossCob, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, brick, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x + 2, y, z, mossCob, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 0, 2);

		//Fifteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x - 2, y, z, brick, 1, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, brick, 1, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, brick, 1, 2);
		this.setBlock(world, x - 1, y, z + 2, brick, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, brick, 1, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sand, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, brick, 1, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 1, y, z - 1, brick, 1, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, brick, 1, 2);
		this.setBlock(world, x + 1, y, z + 2, brick, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x + 2, y, z, brick, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 0, 2);

		//Sixteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x - 2, y, z, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, cobble, 0, 2);
		this.setBlock(world, x - 1, y, z, cobble, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, brick, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, mossCob, 0, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sand, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, mossCob, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 1, y, z - 1, cobble, 0, 2);
		this.setBlock(world, x + 1, y, z, cobble, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, mossCob, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, brick, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x + 2, y, z, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 0, 2);

		//Seventeenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x - 2, y, z, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, cobble, 0, 2);
		this.setBlock(world, x - 1, y, z, redstone, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, brick, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, cobble, 0, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sand, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, cobble, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 1, y, z - 1, cobble, 0, 2);
		this.setBlock(world, x + 1, y, z, redstone, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, cobble, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, brick, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x + 2, y, z, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 0, 2);

		//Eighteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x - 2, y, z, brick, 1, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, plateStone, 0, 2);
		this.setBlock(world, x - 1, y, z, brick, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, plateStone, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, brick, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, brick, 1, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, trapChest, 0, 2, ChestHooks.uncommonHook);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, brick, 1, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 1, y, z - 1, plateStone, 0, 2);
		this.setBlock(world, x + 1, y, z, brick, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, plateStone, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, brick, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x + 2, y, z, brick, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 0, 2);

		//Nineteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x - 2, y, z, mossCob, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, brick, 0, 2);
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlock(world, x - 1, y, z, brick, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, brick, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, brickStair, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, brickStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, brick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlock(world, x + 1, y, z, brick, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 1);
		this.setBlock(world, x + 1, y, z + 2, brick, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x + 2, y, z, cobble, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 0, 2);

		//Twentieth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x - 2, y, z, cobble, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, brick, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, brick, 0, 2);
		this.setBlock(world, x - 1, y, z, brick, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, brick, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, brick, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, brick, 0, 2);
		this.setBlock(world, x + 1, y, z - 1, brick, 0, 2);
		this.setBlock(world, x + 1, y, z, brick, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, brick, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, brick, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, brick, 0, 2);
		this.setBlock(world, x + 2, y, z, mossCob, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, brick, 0, 2);

		//Twenty-first layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, brickStair, 2, 2);
		this.setBlock(world, x - 2, y, z, brickStair, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, brickStair, 3, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, brickStair, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, brick, 0, 2);
		this.setBlock(world, x - 1, y, z, brick, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, brick, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, brickStair, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, brickStair, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, brickStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, brickStair, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, brick, 0, 2);
		this.setBlock(world, x + 1, y, z, brick, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, brick, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, brickStair, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, brickStair, 2, 2);
		this.setBlock(world, x + 2, y, z, brickStair, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, brickStair, 3, 2);

		//Twenty-second layer
		y++;

		//Third row
		this.setBlock(world, x - 1, y, z - 1, brickStair, 0, 2);
		this.setBlock(world, x - 1, y, z, brick, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, brickStair, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 1, brickStair, 2, 2);
		this.setBlockToAir(world, x, y, z);
		this.setBlock(world, x, y, z + 1, brickStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 1, brickStair, 1, 2);
		this.setBlock(world, x + 1, y, z, brick, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, brickStair, 1, 2);

		//Twenty-third layer
		y++;

		//Third row
		this.setBlock(world, x - 1, y, z, brick, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z);

		//Fifth row
		this.setBlock(world, x + 1, y, z, brick, 0, 2);

		//Twenty-fourth layer
		y++;

		//Third row
		this.setBlock(world, x - 1, y, z, brickStair, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z, luxuryBlocks[rand.nextInt(luxuryBlocks.length)], 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z, brickStair, 1, 2);

		//Twenty-fifth layer
		y++;

		//Fourth row
		this.setBlock(world, x, y, z, slab, 5, 2);
		
		return this;
	}
}
