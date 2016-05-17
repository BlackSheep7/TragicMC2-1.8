package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.biome.BiomeGenSnow;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCorrodedSteppe;
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicDesertTower extends Schematic {

	public static Block sand = Blocks.sand;
	private static Block sandstone = Blocks.sandstone;
	public static Block tnt = Blocks.tnt;
	public static Block plate = TragicBlocks.SandstonePressurePlate;
	public static Block ladder = Blocks.ladder;
	public static Block lava = Blocks.lava;
	private static Block sandStair = Blocks.sandstone_stairs;
	public static Block redstone = Blocks.redstone_wire;
	public static Block trapChest = Blocks.trapped_chest;
	public static Block slab = Blocks.stone_slab;

	//9 luxury blocks currently
	public static Block[] luxuryBlocks = new Block[] {Blocks.diamond_block, Blocks.gold_block, Blocks.emerald_block, TragicBlocks.CompactOre, Blocks.iron_block, Blocks.lapis_block};

	public SchematicDesertTower(BlockPos pos, Structure str, World world) {
		super(pos, str, world, 25, 9, 9);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {
		//First layer

		//First row
		this.setBlock(world, x - 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z); //Starting block of the whole schematic
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, sandstone, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, sandstone, 1, 2);

		//Second layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, sandstone, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, sandstone, 1, 2);

		//Third layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, sandstone, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 2, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 2, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, sandstone, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, sandstone, 2, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, sandstone, 2, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 2, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 2, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, sandstone, 1, 2);

		//Fourth layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, sandstone, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z, sandStair, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, sandStair, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, sandStair, 3, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z, sandStair, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, sandstone, 1, 2);

		//Fifth layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, sandstone, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, sandstone, 1, 2);

		//Sixth layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, sandstone, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 2, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 2, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, sandstone, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, sandstone, 2, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, sandstone, 2, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 2, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 2, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, sandstone, 1, 2);

		//Seventh layer
		y++;

		//First row
		this.setBlock(world, x - 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x - 3, y, z - 2);
		this.setBlockToAir(world, x - 3, y, z - 1);
		this.setBlockToAir(world, x - 3, y, z);
		this.setBlockToAir(world, x - 3, y, z + 1);
		this.setBlockToAir(world, x - 3, y, z + 2);
		this.setBlock(world, x - 3, y, z + 3, sandstone, 1, 2);

		//Second row
		this.setBlockToAir(world, x - 2, y, z - 3);
		this.setBlock(world, x - 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z, sandStair, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 1, 2);
		this.setBlockToAir(world, x - 2, y, z + 3);

		//Third row
		this.setBlockToAir(world, x - 1, y, z - 3);
		this.setBlock(world, x - 1, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x - 1, y, z + 3);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 3);
		this.setBlock(world, x, y, z - 2, sandStair, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, sandStair, 3, 2);
		this.setBlockToAir(world, x, y, z + 3);

		//Fifth row
		this.setBlockToAir(world, x + 1, y, z - 3);
		this.setBlock(world, x + 1, y, z - 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z + 3);

		//Sixth row
		this.setBlockToAir(world, x + 2, y, z - 3);
		this.setBlock(world, x + 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z, sandStair, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 1, 2);
		this.setBlockToAir(world, x + 2, y, z + 3);

		//Seventh row
		this.setBlock(world, x + 3, y, z - 3, sandstone, 1, 2);
		this.setBlockToAir(world, x + 3, y, z - 2);
		this.setBlockToAir(world, x + 3, y, z - 1);
		this.setBlockToAir(world, x + 3, y, z);
		this.setBlockToAir(world, x + 3, y, z + 1);
		this.setBlockToAir(world, x + 3, y, z + 2);
		this.setBlock(world, x + 3, y, z + 3, sandstone, 1, 2);

		//Eighth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 1, 2);

		//Ninth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, ladder, 3, 2);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 2, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlock(world, x, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x, y, z + 2, sandstone, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 2, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlock(world, x + 1, y, z + 1, ladder, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 2, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 1, 2);

		//Tenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z, sandStair, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 0, 2);
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, sandStair, 2, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, sandStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlockToAir(world, x + 1, y, z + 1);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z, sandStair, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 1, 2);

		//Eleventh layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 0, 2);
		this.setBlockToAir(world, x - 2, y, z);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 0, 2);
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlockToAir(world, x - 1, y, z);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 0, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 0, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlockToAir(world, x + 1, y, z);
		this.setBlockToAir(world, x + 1, y, z + 1);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 0, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 0, 2);
		this.setBlockToAir(world, x + 2, y, z);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 1, 2);

		//Twelfth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z + 2, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x - 1, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x - 1, y, z, sandstone, 2, 2);
		this.setBlock(world, x - 1, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 2, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sandstone, 1, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, sandstone, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x + 1, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x + 1, y, z, sandstone, 2, 2);
		this.setBlock(world, x + 1, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 2, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z + 2, sandstone, 1, 2);

		//Thirteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z, sandStair, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, sandStair, 2, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sand, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, sandStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z, sandStair, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 1, 2);

		//Fourteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 1, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sand, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 1, 2);

		//Fifteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x - 1, y, z, tnt, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, sandstone, 2, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sand, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, sandstone, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, sandstone, 2, 2);
		this.setBlock(world, x + 1, y, z, tnt, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, sandstone, 2, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 1, 2);

		//Sixteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sand, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, sandstone, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x + 1, y, z, sandstone, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 1, 2);

		//Seventeenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z, redstone, 0, 2);
		this.setBlock(world, x - 1, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, sandstone, 0, 2);
		this.setBlock(world, x, y, z - 1, ladder, 2, 2);
		this.setBlock(world, x, y, z, sand, 0, 2);
		this.setBlock(world, x, y, z + 1, ladder, 3, 2);
		this.setBlock(world, x, y, z + 2, sandstone, 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, sandstone, 0, 2);
		this.setBlock(world, x + 1, y, z, redstone, 0, 2);
		this.setBlock(world, x + 1, y, z + 1, sandstone, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 1, 2);

		//Eighteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, plate, 0, 2);
		this.setBlock(world, x - 1, y, z, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, plate, 0, 2);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, sandstone, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlock(world, x, y, z, trapChest, 0, 2, ChestHooks.uncommonHook);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, sandstone, 2, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, plate, 0, 2);
		this.setBlock(world, x + 1, y, z, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, plate, 0, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z, sandstone, 2, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 1, 2);

		//Nineteenth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 1, 2);
		this.setBlockToAir(world, x - 1, y, z - 1);
		this.setBlock(world, x - 1, y, z, sandstone, 1, 2);
		this.setBlockToAir(world, x - 1, y, z + 1);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 1, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, sandStair, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, sandStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 1, 2);
		this.setBlockToAir(world, x + 1, y, z - 1);
		this.setBlock(world, x + 1, y, z, sandstone, 1, 2);
		this.setBlockToAir(world, x + 1, y, z + 1);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 1, 2);

		//Twentieth layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x - 2, y, z, sandstone, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, sandstone, 1, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z + 2, sandstone, 1, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z - 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlockToAir(world, x, y, z + 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z + 2, sandstone, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x + 2, y, z, sandstone, 0, 2);
		this.setBlock(world, x + 2, y, z + 1, sandstone, 1, 2);

		//Twenty-first layer
		y++;

		//Second row
		this.setBlock(world, x - 2, y, z - 1, sandStair, 2, 2);
		this.setBlock(world, x - 2, y, z, sandStair, 0, 2);
		this.setBlock(world, x - 2, y, z + 1, sandStair, 3, 2);

		//Third row
		this.setBlock(world, x - 1, y, z - 2, sandStair, 0, 2);
		this.setBlock(world, x - 1, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z + 2, sandStair, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 2, sandStair, 2, 2);
		this.setBlockToAir(world, x, y, z - 1);
		this.setBlockToAir(world, x, y, z);
		this.setBlockToAir(world, x, y, z + 1);
		this.setBlock(world, x, y, z + 2, sandStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 2, sandStair, 1, 2);
		this.setBlock(world, x + 1, y, z - 1, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z + 2, sandStair, 1, 2);

		//Sixth row
		this.setBlock(world, x + 2, y, z - 1, sandStair, 2, 2);
		this.setBlock(world, x + 2, y, z, sandStair, 1, 2);
		this.setBlock(world, x + 2, y, z + 1, sandStair, 3, 2);

		//Twenty-second layer
		y++;

		//Third row
		this.setBlock(world, x - 1, y, z - 1, sandStair, 0, 2);
		this.setBlock(world, x - 1, y, z, sandstone, 1, 2);
		this.setBlock(world, x - 1, y, z + 1, sandStair, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z - 1, sandStair, 2, 2);
		this.setBlockToAir(world, x, y, z);
		this.setBlock(world, x, y, z + 1, sandStair, 3, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z - 1, sandStair, 1, 2);
		this.setBlock(world, x + 1, y, z, sandstone, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, sandStair, 1, 2);

		//Twenty-third layer
		y++;

		//Third row
		this.setBlock(world, x - 1, y, z, sandstone, 1, 2);

		//Fourth row
		this.setBlockToAir(world, x, y, z);

		//Fifth row
		this.setBlock(world, x + 1, y, z, sandstone, 1, 2);

		//Twenty-fourth layer
		y++;

		//Third row
		this.setBlock(world, x - 1, y, z, sandStair, 0, 2);

		//Fourth row
		this.setBlock(world, x, y, z, luxuryBlocks[rand.nextInt(luxuryBlocks.length)], 0, 2);

		//Fifth row
		this.setBlock(world, x + 1, y, z, sandStair, 1, 2);

		//Twenty-fifth layer
		y++;

		//Fourth row
		this.setBlock(world, x, y, z, slab, 1, 2);
		
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
