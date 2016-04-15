package tragicneko.tragicmc.worldgen;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;

import com.google.common.base.Predicate;

public class NetherOreWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		if (world.provider.getDimensionId() == -1)
		{
			int Xcoord, Ycoord, Zcoord;
			byte i;
			Predicate pred = BlockHelper.forBlock(Blocks.netherrack);
			
			for (i = 0; i < TragicConfig.getInt("rubyOreGenRate"); i++)
			{
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Ycoord = random.nextInt(30) + 15;
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicBlocks.RubyOre.getStateFromMeta(0), TragicConfig.getInt("rubyOreVeinSize"), pred).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}

			for (i = 0; i < TragicConfig.getInt("sapphireOreGenRate"); i++)
			{
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Ycoord = random.nextInt(20) + 95;
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicBlocks.SapphireOre.getStateFromMeta(0), TragicConfig.getInt("sapphireOreVeinSize"), pred).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}

			for (i = 0; i < TragicConfig.getInt("drudgeGenRate") && TragicConfig.getBoolean("allowDrudgeGen"); i++)
			{
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Ycoord = random.nextInt(60) + 30;
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicBlocks.Quicksand.getStateFromMeta(2), TragicConfig.getInt("drudgeVeinSize"), pred).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		}
	}

}
