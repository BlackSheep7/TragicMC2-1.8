package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicOutlook extends Schematic {

	public SchematicOutlook(BlockPos pos, Structure str) {
		super(pos, str, 48, 4, 4);
	}

	@Override
	public boolean generateStructure(int variant, World world, Random rand, int x, int y, int z) {
		
		byte x1, z1;
		int y1;
		for (y1 = 0; y1 + y < 230; y1++)
		{
			if ((y + y1) % 50 == 0 && y1 > 40 && y + y1 < 200)
			{
				for (x1 = -5; x1 < 6; x1++)
				{
					for (z1 = -5; z1 < 6; z1++)
					{
						this.setBlock(world, x + x1, y + y1, z + z1, TragicBlocks.ErodedStone, 1, 2);
					}
				}
				
				for (x1 = -4; x1 < 5; x1++)
				{
					for (z1 = -6; z1 < 7; z1++)
					{
						this.setBlock(world, x + x1, y + y1, z + z1, TragicBlocks.ErodedStone, 1, 2);
						this.setBlock(world, x + z1, y + y1, z + x1, TragicBlocks.ErodedStone, 1, 2);
					}
				}
				
				byte b = 4;
				this.setBlock(world, x + b, y + y1 + 1, z + b, Blocks.mob_spawner, 0, 2, "Skeleton");
				this.setBlock(world, x + b, y + y1 + 1, z - b, Blocks.mob_spawner, 0, 2, "Skeleton");
				this.setBlock(world, x - b, y + y1 + 1, z + b, Blocks.mob_spawner, 0, 2, "Skeleton");
				this.setBlock(world, x - b, y + y1 + 1, z - b, Blocks.mob_spawner, 0, 2, "Skeleton");
			}
			this.setBlock(world, x, y + y1, z, TragicBlocks.ErodedStone, 1, 2);
			this.setBlock(world, x - 1, y + y1, z, Blocks.ladder, 4, 2);
		}
		
		for (x1 = -6; x1 < 7; x1++)
		{
			for (z1 = -6; z1 < 7; z1++)
			{
				this.setBlock(world, x + x1, y + y1, z + z1, TragicBlocks.ErodedStone, 1, 2);
			}
		}
		
		for (x1 = -4; x1 < 5; x1++)
		{
			for (z1 = -7; z1 < 8; z1++)
			{
				this.setBlock(world, x + x1, y + y1, z + z1, TragicBlocks.ErodedStone, 1, 2);
				this.setBlock(world, x + z1, y + y1, z + x1, TragicBlocks.ErodedStone, 1, 2);
			}
		}
		this.setBlock(world, x + 1, y + y1 + 1, z + 1, TragicBlocks.SoulChest, 0, 2, TragicItems.BossStructureHook);
		this.setBlock(world, x + 1, y + y1 + 1, z + 2, TragicBlocks.SoulChest, 0, 2, TragicItems.BossStructureHook);
		
		this.setBlock(world, x, y + y1, z, TragicBlocks.ErodedStone, 1, 2);
		this.setBlock(world, x - 1, y + y1, z, Blocks.ladder, 4, 2);
		
		byte b = 5;
		this.setBlock(world, x + b, y + y1 + 1, z + b, Blocks.mob_spawner, 0, 2, "Enderman");
		this.setBlock(world, x + b, y + y1 + 1, z - b, Blocks.mob_spawner, 0, 2, "Enderman");
		this.setBlock(world, x - b, y + y1 + 1, z + b, Blocks.mob_spawner, 0, 2, "Enderman");
		this.setBlock(world, x - b, y + y1 + 1, z - b, Blocks.mob_spawner, 0, 2, "Enderman");
		
		return true;
	}

}
