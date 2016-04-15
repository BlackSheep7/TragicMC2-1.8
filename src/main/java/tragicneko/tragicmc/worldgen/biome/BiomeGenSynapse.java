package tragicneko.tragicmc.worldgen.biome;

import net.minecraft.world.biome.BiomeGenBase;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.EntityHarvester;
import tragicneko.tragicmc.entity.mob.EntityHunter;
import tragicneko.tragicmc.entity.mob.EntityLockbot;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;

public class BiomeGenSynapse extends TragicBiome {

	public BiomeGenSynapse(int par1) {
		super(par1, (byte) 0);
		this.enableSnow = false;
		this.enableRain = false;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		if (TragicConfig.getBoolean("allowNanoSwarm")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityNanoSwarm.class, TragicConfig.getInt("nanoSwarmSpawnChance"), TragicConfig.getIntArray("nanoSwarmGroupSize")[0], TragicConfig.getIntArray("nanoSwarmGroupSize")[1]));
		if (TragicConfig.getBoolean("allowHunter")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityHunter.class, TragicConfig.getInt("hunterSpawnChance"), TragicConfig.getIntArray("hunterGroupSize")[0], TragicConfig.getIntArray("hunterGroupSize")[1]));
		if (TragicConfig.getBoolean("allowLockbot")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityLockbot.class, TragicConfig.getInt("lockbotSpawnChance"), TragicConfig.getIntArray("lockbotGroupSize")[0], TragicConfig.getIntArray("lockbotGroupSize")[1]));
		if (TragicConfig.getBoolean("allowHarvester")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityHarvester.class, TragicConfig.getInt("harvesterSpawnChance"), TragicConfig.getIntArray("harvesterGroupSize")[0], TragicConfig.getIntArray("harvesterGroupSize")[1]));
		this.fillerBlock = TragicBlocks.CircuitBlock.getDefaultState();
		this.topBlock = TragicBlocks.CircuitBlock.getDefaultState();
	}
}
