package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.util.ChestHooks;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class SchematicSpiderNest extends Schematic {
	
	private int radius = 0;
	private int top = 5;
	private int bottom = -3;
	private final double fulcrum = 0.25466875D;

	public SchematicSpiderNest(BlockPos pos, Structure str, World world) {
		super(pos, str, world, 8, 16, 16);
		radius = world.rand.nextInt(20) + 14; //max of 23, min of 14
		top = world.rand.nextInt(3) + 5; //max of 7, min of 5
		bottom = world.rand.nextInt(3) - 3; //max of -3, min of -5
	}

	@Override
	public Schematic generateStructure(World world, Random rand, int x, int y, int z) {

		for (int x1 = -radius; x1 < radius; x1++)
		{
			double raw = Math.cos(fulcrum * x1 * 0.63) * (2 / fulcrum) + (radius * 3 / 4);
			int width = MathHelper.clamp_int(MathHelper.floor_double(raw), (int) radius / 3, radius);

			for (int z1 = -width; z1 < width; z1++)
			{
				int range = 4 + rand.nextInt(top * 5 / 8);
				int botR = bottom + rand.nextInt(3);

				for (int y1 = bottom; y1 < top; y1++)
				{
					if (y1 <= botR || y1 >= range)
					{
						if ((y1 == botR || y1 == range) && rand.nextInt(20) == 0)
						{
							if (rand.nextInt(6) == 0)
							{
								this.setBlock(world, x + x1, y + y1, z + z1, Blocks.mob_spawner, 0, 2, TragicConfig.getBoolean("allowStin") ? "TragicMC.Stin" : "Spider");
							}
							else if (rand.nextInt(16) == 0)
							{
								this.setBlock(world, x + x1, y + y1, z + z1, Blocks.chest, 0, 2, ChestHooks.uncommonHook);
							}
							else
							{
								this.setBlock(world, x + x1, y + y1, z + z1, TragicBlocks.Corsin, 8, 2);
							}
						}
						else
						{
							this.setBlock(world, x + x1, y + y1, z + z1, TragicBlocks.Corsin, rand.nextInt(4) == 0 ? (rand.nextInt(10) == 0 ? 4 : 1) : 0, 2);
						}
					}
					else
					{
						if (rand.nextInt(12) == 0)
						{
							this.setBlock(world, x + x1, y + y1, z + z1, Blocks.web, 0, 2);
						}
						else
						{
							this.setBlockToAir(world, x + x1, y + y1, z + z1);
						}
					}
				}
			}
		}

		this.setBlockToAir(world, x, y, z);
		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("radius", this.radius);
		tag.setInteger("top", this.top);
		tag.setInteger("bottom", this.bottom);
		return tag;
	}

	@Override
	public Schematic readFromNBT(NBTTagCompound tag) {
		this.radius = tag.getInteger("radius");
		this.top = tag.getInteger("top");
		this.bottom = tag.getInteger("bottom");
		return this;
	}

}
