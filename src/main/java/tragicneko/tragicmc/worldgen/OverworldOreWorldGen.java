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

public class OverworldOreWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimensionId() == 0)
		{
			int Xcoord;
			int Ycoord;
			int Zcoord;
			byte i;
			Predicate pred = BlockHelper.forBlock(Blocks.stone);

			for (i = 0; i < TragicConfig.getInt("mercuryOreGenRate"); i++)
			{
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Ycoord = random.nextInt(48) + 5;
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicBlocks.MercuryOre.getStateFromMeta(0), TragicConfig.getInt("mercuryOreVeinSize"), pred).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}

			for (i = 0; i < TragicConfig.getInt("tungstenOreGenRate"); i++)
			{
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Ycoord = random.nextInt(24) + 5;
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(TragicBlocks.TungstenOre.getStateFromMeta(0), TragicConfig.getInt("tungstenOreVeinSize"), pred).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}

			for (i = 0; i < TragicConfig.getInt("silverfishStoneGenRate") && TragicConfig.getBoolean("allowOverworldSilverfishGen"); i++)
			{
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Ycoord = random.nextInt(6);
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(Blocks.monster_egg.getStateFromMeta(0), TragicConfig.getInt("silverfishStoneVeinSize"), pred).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));

				Xcoord = (chunkX * 16) + random.nextInt(16);
				Ycoord = random.nextInt(16) + 5;
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				new WorldGenMinable(Blocks.monster_egg.getStateFromMeta(0), TragicConfig.getInt("silverfishStoneVeinSize"), pred).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}

			if (random.nextInt(4) != 0) return;

			for (i = 0; i < 16 && TragicConfig.getBoolean("allowDimensions"); i++)
			{
				Xcoord = (chunkX * 16) + random.nextInt(16);
				Zcoord = (chunkZ * 16) + random.nextInt(16);
				Ycoord = world.getTopSolidOrLiquidBlock(new BlockPos(Xcoord, 0, Zcoord)).getY();
				if (Ycoord < 100) continue;
				if (world.getBlockState(new BlockPos(Xcoord, Ycoord, Zcoord)).getBlock().isReplaceable(world, new BlockPos(Xcoord, Ycoord, Zcoord)) && world.getBlockState(new BlockPos(Xcoord, Ycoord - 1, Zcoord)).getBlock().isOpaqueCube() && random.nextInt(200) <= TragicConfig.getInt("aerisRarity"))
				{
					world.setBlockState(new BlockPos(Xcoord, Ycoord, Zcoord), TragicBlocks.Aeris.getStateFromMeta(0), 2);
					break;
				}
			}
		}

	}

}
