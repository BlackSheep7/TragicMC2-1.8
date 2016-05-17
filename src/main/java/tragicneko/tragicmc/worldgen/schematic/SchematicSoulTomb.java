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

public class SchematicSoulTomb extends Schematic {

	public static Block[] blocks = new Block[] {TragicBlocks.DarkCobblestone, TragicBlocks.DarkenedQuartz, TragicBlocks.SmoothNetherrack,
		Blocks.stonebrick, TragicBlocks.ScorchedRock, TragicBlocks.ErodedStone};
	
	private int variant = 0;

	public SchematicSoulTomb(BlockPos pos, Structure str, World world) {
		super(pos, str, world, 10, 6, 6);
		this.variant = world.rand.nextInt(blocks.length);
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {

		for (int y1 = 0; y1 < 10; y1++)
		{
			for (int x1 = -3; x1 < 4; x1++)
			{
				for (int z1 = -3; z1 < 4; z1++)
				{
					this.setBlockToAir(world, x + x1, y + y1, z + z1);
				}
			}
		}

		Block block = blocks[variant];

		for (int x1 = -3; x1 < 4; x1++)
		{
			for (int z1 = -3; z1 < 4; z1++)
			{
				if (Math.abs(x1) != 3 && Math.abs(z1) != 3 && Math.abs(x1) != Math.abs(z1)) this.setBlock(world, x + x1, y, z + z1, block, 1, 2);
			}
		}

		this.setBlock(world, x, y, z, block, 1, 2);
		this.setBlock(world, x + 1, y, z + 1, Blocks.lava);
		this.setBlock(world, x + 1, y, z - 1, Blocks.lava);
		this.setBlock(world, x - 1, y, z + 1, Blocks.lava);
		this.setBlock(world, x - 1, y, z - 1, Blocks.lava);

		this.setBlock(world, x + 3, y + 1, z, block, 1, 2);
		this.setBlock(world, x - 3, y + 1, z, block, 1, 2);
		this.setBlock(world, x, y + 1, z + 3, block, 1, 2);
		this.setBlock(world, x, y + 1, z - 3, block, 1, 2);

		this.setBlock(world, x + 3, y + 1, z + 1, block, 1, 2);
		this.setBlock(world, x - 3, y + 1, z + 1, block, 1, 2);
		this.setBlock(world, x + 1, y + 1, z + 3, block, 1, 2);
		this.setBlock(world, x + 1, y + 1, z - 3, block, 1, 2);

		this.setBlock(world, x + 3, y + 1, z - 1, block, 1, 2);
		this.setBlock(world, x - 3, y + 1, z - 1, block, 1, 2);
		this.setBlock(world, x - 1, y + 1, z + 3, block, 1, 2);
		this.setBlock(world, x - 1, y + 1, z - 3, block, 1, 2);

		for (int y1 = 1; y1 < 8; y1++)
		{
			this.setBlock(world, x + 2, y + y1, z + 2, block, 1, 2);
			this.setBlock(world, x + 2, y + y1, z - 2, block, 1, 2);
			this.setBlock(world, x - 2, y + y1, z + 2, block, 1, 2);
			this.setBlock(world, x - 2, y + y1, z - 2, block, 1, 2);
		}

		for (int y1 = 1; y1 < 8; y1++)
		{
			this.setBlock(world, x + 4, y + y1, z, block, 1, 2);
			this.setBlock(world, x - 4, y + y1, z, block, 1, 2);
			this.setBlock(world, x, y + y1, z + 4, block, 1, 2);
			this.setBlock(world, x, y + y1, z - 4, block, 1, 2);
		}

		for (int y1 = 6; y1 < 8; y1++)
		{
			this.setBlock(world, x + 3, y + y1, z, block, 1, 2);
			this.setBlock(world, x - 3, y + y1, z, block, 1, 2);
			this.setBlock(world, x, y + y1, z + 3, block, 1, 2);
			this.setBlock(world, x, y + y1, z - 3, block, 1, 2);
		}

		for (int x1 = -3; x1 < 4; x1++)
		{
			this.setBlock(world, x + x1, y + 6, z + 3, block, 1, 2);
			this.setBlock(world, x + x1, y + 6, z - 3, block, 1, 2);
			this.setBlock(world, x + 3, y + 6, z + x1, block, 1, 2);
			this.setBlock(world, x - 3, y + 6, z + x1, block, 1, 2);
		}

		for (int x1 = -2; x1 < 3; x1++)
		{
			for (int z1 = -2; z1 < 3; z1++)
			{
				if (Math.abs(x1) != 3 && Math.abs(z1) != 3 && Math.abs(x1) != Math.abs(z1)) this.setBlock(world, x + x1, y + 7, z + z1, block, 1, 2);
			}
		}

		this.setBlock(world, x, y + 7, z, block, 1, 2);

		this.setBlock(world, x + 2, y + 8, z, block, 1, 2);
		this.setBlock(world, x - 2, y + 8, z, block, 1, 2);
		this.setBlock(world, x, y + 8, z + 2, block, 1, 2);
		this.setBlock(world, x, y + 8, z - 2, block, 1, 2);

		this.setBlock(world, x + 1, y + 9, z, block, 1, 2);
		this.setBlock(world, x - 1, y + 9, z, block, 1, 2);
		this.setBlock(world, x, y + 9, z + 1, block, 1, 2);
		this.setBlock(world, x, y + 9, z - 1, block, 1, 2);

		this.setBlock(world, x, y + 9, z, block, 1, 2);
		this.setBlock(world, x, y + 10, z, block, 1, 2);

		this.setBlock(world, x, y + 1, z, TragicBlocks.SoulChest, 0, 2, ChestHooks.uncommonHook);

		this.setBlock(world, x, y + 8, z, block, 1, 2);
		this.setBlock(world, x, y + 8, z + 1, Blocks.flowing_lava, 0, 2);
		this.setBlock(world, x, y + 8, z - 1, Blocks.flowing_lava, 0, 2);
		this.setBlock(world, x + 1, y + 8, z, Blocks.flowing_lava, 0, 2);
		this.setBlock(world, x - 1, y + 8, z, Blocks.flowing_lava, 0, 2);

		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("variant", this.variant);
		return tag;
	}

	@Override
	public Schematic readFromNBT(NBTTagCompound tag) {
		this.variant = tag.getInteger("variant");
		return this;
	}

}
