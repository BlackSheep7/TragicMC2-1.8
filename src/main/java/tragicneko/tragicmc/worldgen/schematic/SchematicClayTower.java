package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicClayTower extends SchematicDesertTower {
	
	private static Block hardClay = Blocks.hardened_clay;

	public SchematicClayTower(BlockPos origin, Structure structure, World world) {
		super(origin, structure, world);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {
		//First layer

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 0, 2);

		//Second layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 0, 2);

		//Third layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 2, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 2, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, hardClay, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, hardClay, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 2, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 2, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 2, 2);

		//Fourth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 0, 2);

		//Fifth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 0, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 0, 2);

		//Sixth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 2, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 2, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, hardClay, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, hardClay, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 2, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 2, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 2, 2);

		//Seventh layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 1, 2);

		//Eighth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 1, 2);

		//Ninth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 2, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlock(world, x, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x, y, z + 2, hardClay, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 2, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 2, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 1, 2);

		//Tenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 0, 2);
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlockToAir(world, x + 1, y, z + 1);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 1, 2);

		//Eleventh layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 0, 2);
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlockToAir(world, x + 1, y, z + 1);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 1, 2);

		//Twelfth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x - 1, y, z, hardClay, 2, 2);
		this.setBlock(world, x - 1, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 2, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, hardClay, 1, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, hardClay, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x + 1, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x + 1, y, z, hardClay, 2, 2);
		this.setBlock(world, x + 1, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 2, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, hardClay, 1, 2);

		//Thirteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 1, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, hardClay, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 1, 2);

		//Fourteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 1, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, hardClay, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 1, 2);

		//Fifteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, hardClay, 2, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, hardClay, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, hardClay, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, hardClay, 2, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, hardClay, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 1, 2);

		//Sixteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 1, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, hardClay, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 1, 2);

		//Seventeenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 1, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, hardClay, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 1, 2);

		//Eighteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, hardClay, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, trapChest, 0, 2, ChestHooks.uncommonHook);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, hardClay, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 1, 2);

		//Nineteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, hardClay, 0, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, hardClay, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, hardClay, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 1, 2);

		//Twentieth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x - 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, hardClay, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z + 2, hardClay, 1, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z + 2, hardClay, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x + 2, y, z, hardClay, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, hardClay, 1, 2);

		//Twenty-first layer
		y++;

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 1);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlockToAir(world, x - 2, y, z + 1);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 2);
		this.setBlock(world, x - 1, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z, hardClay, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, hardClay, 1, 2);
		this.setBlockToAir(world, x - 1, y, z + 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 2);
		this.setBlock(world, x + 1, y, z - 1, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z, hardClay, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, hardClay, 1, 2);
		this.setBlockToAir(world, x + 1, y, z + 2);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 1);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlockToAir(world, x + 2, y, z + 1);

		//Twenty-second layer
		y++;

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlock(world, x - 1, y, z, hardClay, 1, 2);
		this.setBlockToAir(world, x - 1, y, z + 1);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, luxuryBlocks[rand.nextInt(luxuryBlocks.length)], 0, 2);
		this.setBlockToAir(world, x, y, z + 1);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlock(world, x + 1, y, z, hardClay, 1, 2);
		this.setBlockToAir(world, x + 1, y, z + 1);

		//Twentiy-third layer
		y++;

		//Fourth row
		this.setBlock(world, x, y, z, hardClay, 0, 2);

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
