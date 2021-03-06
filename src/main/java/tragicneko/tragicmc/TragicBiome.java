package tragicneko.tragicmc;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.mob.EntityAvris;
import tragicneko.tragicmc.entity.mob.EntityErkel;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.worldgen.biome.BiomeGenAshenHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCorrodedSteppe;
import tragicneko.tragicmc.worldgen.biome.BiomeGenCrystal;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDarkForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenFrozenTundra;
import tragicneko.tragicmc.worldgen.biome.BiomeGenHallowedHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenNekoBarrens;
import tragicneko.tragicmc.worldgen.biome.BiomeGenPaintedForest;
import tragicneko.tragicmc.worldgen.biome.BiomeGenScorchedWasteland;
import tragicneko.tragicmc.worldgen.biome.BiomeGenStarlitPrarie;
import tragicneko.tragicmc.worldgen.biome.BiomeGenSynapse;
import tragicneko.tragicmc.worldgen.biome.BiomeGenTaintedSpikes;
import tragicneko.tragicmc.worldgen.biome.BiomeGenWilds;

public class TragicBiome extends BiomeGenBase {

	protected final byte variant;

	//Collision biomes
	public static BiomeGenBase DecayingHills, DecayingValley, DecayingWasteland, DecayingMountains;
	public static BiomeGenBase PaintedForest, PaintedPlains, PaintedHills, PaintedClearing;
	public static BiomeGenBase AshenMountains, AshenHills, AshenBadlands;
	public static BiomeGenBase StarlitPrarie, StarlitPlateaus, StarlitCliffs, StarlitLowlands;
	public static BiomeGenBase TaintedSpikes, TaintedLowlands, TaintedRises, TaintedScarlands, TaintedIsles;
	public static BiomeGenBase HallowedHills, HallowedForest, HallowedPrarie, HallowedCliffs;
	public static BiomeGenBase ScorchedWastelands, ScorchedValley, ScorchedScarlands;
	public static BiomeGenBase CorrodedSteppe, CorrodedHeights, CorrodedVeld, CorrodedRunoff, CorrodedFallout;
	public static BiomeGenBase FrozenTundra,FrozenHills,FrozenDepths;
	public static BiomeGenBase Crystal;
	public static BiomeGenBase DarkForest, DarkForestHills, DarkMarsh;
	
	//Synapse biomes
	public static BiomeGenBase Synapse, SynapseDead, SynapseCorrupt;
	
	//Neko Homeworld biomes
	public static BiomeGenBase NekoBarrens, NekoHeights, NekoForest;
	
	//Wilds biomes
	public static BiomeGenBase WildPlains, IriseiPlains, SeraleisSerenade;
	public static BiomeGenBase WildForest, WildDenseForest, ImbertonForest, KlahksTrove, UpsidusVeld;
	public static BiomeGenBase WildValley, CeresteValley, RelicanthicValley;
	public static BiomeGenBase WildHills, WildForestHills, WildExtremeHills, KluveTerrace, ZybianHeights, TurbulentHeights, HalsydeHills, IronveinHills;
	public static BiomeGenBase WildSteppes, WildSavanna, GandreaSteppes, TombstoneFields, PrahpsPast;
	public static BiomeGenBase WildDesert, FerrierScarlands, DesertOfAkhora, VeneriaOasis;
	public static BiomeGenBase WildRiver, RiverOfSouls, FyxisRiver;
	public static BiomeGenBase WildLake, WildOcean, SeaOfSorrow, VexinLake, StelSea, ExivSea, AggroLake;
	public static BiomeGenBase WildMountains, LarinthianMountains, EttenDrove, IrsalasVolcano;
	public static BiomeGenBase WildIsland, TorsianIsle, IslaDeMuerte;
	public static BiomeGenBase WildDeepOcean, LeviaTriangle, DesolateDepths;
	public static BiomeGenBase WildBeach;
	
	//Nerve Center biome
	public static BiomeGenBase NerveCenter;

	public TragicBiome(int par1, byte par2) {
		super(par1, true);
		this.variant = par2;
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.topBlock = TragicBlocks.DarkStone.getDefaultState();
		this.topBlock = TragicBlocks.DeadDirt.getDefaultState();
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = -999;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.bigMushroomsPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.maxHeight = 0F;
		this.minHeight = 0F;
		if (TragicConfig.getBoolean("allowPlague")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPlague.class, TragicConfig.getInt("plagueSpawnChance"), TragicConfig.getIntArray("plagueGroupSize")[0], TragicConfig.getIntArray("plagueGroupSize")[1]));
		if (TragicConfig.getBoolean("allowTragicNeko")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTragicNeko.class, TragicConfig.getInt("tragicNekoSpawnChance"), TragicConfig.getIntArray("tragicNekoGroupSize")[0], TragicConfig.getIntArray("tragicNekoGroupSize")[1]));
		if (TragicConfig.getBoolean("allowTimeController")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTimeController.class, TragicConfig.getInt("timeControllerSpawnChance"), 0, 0));
		if (TragicConfig.getBoolean("allowErkel")) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityErkel.class, TragicConfig.getInt("erkelSpawnChance"), TragicConfig.getIntArray("erkelGroupSize")[0], TragicConfig.getIntArray("erkelGroupSize")[1]));
		if (TragicConfig.getBoolean("allowKindlingSpirit")) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWisp.class, TragicConfig.getInt("kindlingSpiritSpawnChance"), TragicConfig.getIntArray("kindlingSpiritGroupSize")[0], TragicConfig.getIntArray("kindlingSpiritGroupSize")[1]));
		if (TragicConfig.getBoolean("allowAvris")) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityAvris.class, TragicConfig.getInt("avrisSpawnChance"), TragicConfig.getIntArray("avrisGroupSize")[0], TragicConfig.getIntArray("avrisGroupSize")[1])); 
	}

	@Override
	public BiomeGenBase setBiomeName(String s)
	{
		this.biomeName = StatCollector.translateToLocal(s);
		return this;
	}

	/**
	 * Returns the flowers per chunk based on specific biome, this is for my custom flower world gen that uses my own flowers
	 * @return
	 */
	public int getFlowersFromBiomeType()
	{
		return 0;
	}

	/**
	 * Returns the bushes per chunk based on specific biome, this is for my custom bush worldgen within the flower worldgen
	 * @return
	 */
	public int getBushesFromBiomeType()
	{
		return 0;
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chk, int x, int z, double d)
	{
		this.generateTragicBiomeTerrain(worldIn, rand, chk, x, z, d);
	}

	public final void generateTragicBiomeTerrain(World worldIn, Random rand, ChunkPrimer chk, int x, int z, double d)
    {
		boolean flag = true;
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.topBlock;
        int k = -1;
        int l = (int)(d / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int i1 = x & 15;
        int j1 = z & 15;

        for (int k1 = 255; k1 >= 0; --k1)
        {
            if (k1 <= rand.nextInt(5))
            {
                chk.setBlockState(j1, k1, i1, Blocks.air.getDefaultState());
            }
            else
            {
                IBlockState iblockstate2 = chk.getBlockState(j1, k1, i1);

                if (iblockstate2.getBlock().getMaterial() == Material.air)
                {
                    k = -1;
                }
                else if (iblockstate2.getBlock() == TragicBlocks.DarkStone)
                {
                    if (k == -1)
                    {
                        if (l <= 0)
                        {
                            iblockstate = null;
                            iblockstate1 = TragicBlocks.DarkStone.getDefaultState();
                        }
                        else if (k1 >= 59 && k1 <= 64)
                        {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.topBlock;
                        }

                        k = l;

                        if (k1 >= 62)
                        {
                            chk.setBlockState(j1, k1, i1, iblockstate);
                        }
                        else if (k1 < 56 - l)
                        {
                            iblockstate = null;
                            iblockstate1 = TragicBlocks.DarkStone.getDefaultState();
                            chk.setBlockState(j1, k1, i1, TragicBlocks.DarkStone.getDefaultState());
                        }
                        else
                        {
                            chk.setBlockState(j1, k1, i1, iblockstate1);
                        }
                    }
                    else if (k > 0)
                    {
                        --k;
                        chk.setBlockState(j1, k1, i1, iblockstate1);
                    }
                }
            }
        }
	}

	public static void load()
	{
		//Collision/Synapse biomes
		DecayingHills = new BiomeGenDecayingWasteland(TragicConfig.getInt("decayingHillsID"), (byte) 0).setBiomeName("tragicmc.decayingHills");
		DecayingValley = new BiomeGenDecayingWasteland(TragicConfig.getInt("decayingValleyID"), (byte) 1).setBiomeName("tragicmc.decayingValley");
		DecayingWasteland = new BiomeGenDecayingWasteland(TragicConfig.getInt("decayingWastelandID"), (byte) 2).setBiomeName("tragicmc.decayingWasteland");
		DecayingMountains = new BiomeGenDecayingWasteland(TragicConfig.getInt("decayingMountainsID"), (byte) 3).setBiomeName("tragicmc.decayingMountains");

		PaintedForest = new BiomeGenPaintedForest(TragicConfig.getInt("paintedForestID"), (byte) 0).setBiomeName("tragicmc.paintedForest");
		PaintedPlains = new BiomeGenPaintedForest(TragicConfig.getInt("paintedPlainsID"), (byte) 1).setBiomeName("tragicmc.paintedPlains");
		PaintedHills = new BiomeGenPaintedForest(TragicConfig.getInt("paintedHillsID"), (byte) 2).setBiomeName("tragicmc.paintedHills");
		PaintedClearing = new BiomeGenPaintedForest(TragicConfig.getInt("paintedClearingID"), (byte) 3).setBiomeName("tragicmc.paintedClearing");

		AshenMountains = new BiomeGenAshenHills(TragicConfig.getInt("ashenMountainsID"), (byte) 0).setBiomeName("tragicmc.ashenMountains");
		AshenHills = new BiomeGenAshenHills(TragicConfig.getInt("ashenHillsID"), (byte) 1).setBiomeName("tragicmc.ashenHills");
		AshenBadlands = new BiomeGenAshenHills(TragicConfig.getInt("ashenBadlandsID"), (byte) 2).setBiomeName("tragicmc.ashenBadlands");

		StarlitPrarie = new BiomeGenStarlitPrarie(TragicConfig.getInt("starlitPrarieID"), (byte) 0).setBiomeName("tragicmc.starlitPrarie");
		StarlitPlateaus = new BiomeGenStarlitPrarie(TragicConfig.getInt("starlitPlateausID"), (byte) 1).setBiomeName("tragicmc.starlitPlateaus");
		StarlitCliffs = new BiomeGenStarlitPrarie(TragicConfig.getInt("starlitCliffsID"), (byte) 2).setBiomeName("tragicmc.starlitCliffs");
		StarlitLowlands = new BiomeGenStarlitPrarie(TragicConfig.getInt("starlitLowlandsID"), (byte) 3).setBiomeName("tragicmc.starlitLowlands");

		TaintedSpikes = new BiomeGenTaintedSpikes(TragicConfig.getInt("taintedSpikesID"), (byte) 0).setBiomeName("tragicmc.taintedSpikes");
		TaintedLowlands = new BiomeGenTaintedSpikes(TragicConfig.getInt("taintedLowlandsID"), (byte) 1).setBiomeName("tragicmc.taintedLowlands");
		TaintedRises = new BiomeGenTaintedSpikes(TragicConfig.getInt("taintedRisesID"), (byte) 2).setBiomeName("tragicmc.taintedRises");
		TaintedScarlands = new BiomeGenTaintedSpikes(TragicConfig.getInt("taintedScarlandsID"), (byte) 3).setBiomeName("tragicmc.taintedScarlands");
		TaintedIsles = new BiomeGenTaintedSpikes(TragicConfig.getInt("taintedIslesID"), (byte) 4).setBiomeName("tragicmc.taintedIsles");

		Synapse = new BiomeGenSynapse(TragicConfig.getInt("synapseBiomeID")).setBiomeName("tragicmc.synapse");
		SynapseDead = new BiomeGenSynapse(TragicConfig.getInt("synapseDeadID")).setBiomeName("tragicmc.synapseDead");
		SynapseCorrupt = new BiomeGenSynapse(TragicConfig.getInt("synapseCorruptID")).setBiomeName("tragicmc.synapseCorrupt");

		HallowedHills = new BiomeGenHallowedHills(TragicConfig.getInt("hallowedHillsID"), (byte) 0).setBiomeName("tragicmc.hallowedHills");
		HallowedForest = new BiomeGenHallowedHills(TragicConfig.getInt("hallowedForestID"), (byte) 1).setBiomeName("tragicmc.hallowedForest");
		HallowedPrarie = new BiomeGenHallowedHills(TragicConfig.getInt("hallowedPrarieID"), (byte) 2).setBiomeName("tragicmc.hallowedPrarie");
		HallowedCliffs = new BiomeGenHallowedHills(TragicConfig.getInt("hallowedCliffsID"), (byte) 3).setBiomeName("tragicmc.hallowedCliffs");

		ScorchedWastelands = new BiomeGenScorchedWasteland(TragicConfig.getInt("scorchedWastelandsID"), (byte) 0).setBiomeName("tragicmc.scorchedWastelands");
		ScorchedValley = new BiomeGenScorchedWasteland(TragicConfig.getInt("scorchedValleyID"), (byte) 1).setBiomeName("tragicmc.scorchedValley");
		ScorchedScarlands = new BiomeGenScorchedWasteland(TragicConfig.getInt("scorchedScarlandsID"), (byte) 2).setBiomeName("tragicmc.scorchedScarlands");

		CorrodedSteppe = new BiomeGenCorrodedSteppe(TragicConfig.getInt("corrodedSteppeID"), (byte) 0).setBiomeName("tragicmc.corrodedSteppe");
		CorrodedHeights = new BiomeGenCorrodedSteppe(TragicConfig.getInt("corrodedHeightsID"), (byte) 1).setBiomeName("tragicmc.corrodedHeights");
		CorrodedVeld = new BiomeGenCorrodedSteppe(TragicConfig.getInt("corrodedVeldID"), (byte) 2).setBiomeName("tragicmc.corrodedVeld");
		CorrodedRunoff = new BiomeGenCorrodedSteppe(TragicConfig.getInt("corrodedRunoffID"), (byte) 3).setBiomeName("tragicmc.corrodedRunoff");
		CorrodedFallout = new BiomeGenCorrodedSteppe(TragicConfig.getInt("corrodedFalloutID"), (byte) 4).setBiomeName("tragicmc.corrodedFallout");

		FrozenTundra = new BiomeGenFrozenTundra(TragicConfig.getInt("frozenTundraID"), (byte) 0).setBiomeName("tragicmc.frozenTundra");
		FrozenHills = new BiomeGenFrozenTundra(TragicConfig.getInt("frozenHillsID"), (byte) 1).setBiomeName("tragicmc.frozenHills");
		FrozenDepths = new BiomeGenFrozenTundra(TragicConfig.getInt("frozenDepthsID"), (byte) 2).setBiomeName("tragicmc.frozenDepths");

		Crystal = new BiomeGenCrystal(TragicConfig.getInt("crystalID")).setBiomeName("tragicmc.crystal");

		DarkForest = new BiomeGenDarkForest(TragicConfig.getInt("darkForestID"), (byte) 0).setBiomeName("tragicmc.darkForest");
		DarkForestHills = new BiomeGenDarkForest(TragicConfig.getInt("darkForestHillsID"), (byte) 1).setBiomeName("tragicmc.darkForestHills");
		DarkMarsh = new BiomeGenDarkForest(TragicConfig.getInt("darkMarshID"), (byte) 2).setBiomeName("tragicmc.darkMarsh");
		
		//Neko-related biomes
		NekoBarrens = new BiomeGenNekoBarrens(TragicConfig.getInt("nekoBarrensID"), (byte) 0).setBiomeName("tragicmc.nekoBarrens");
		NekoForest = new BiomeGenNekoBarrens(TragicConfig.getInt("nekoForestID"), (byte) 1).setBiomeName("tragicmc.nekoForest");
		NekoHeights = new BiomeGenNekoBarrens(TragicConfig.getInt("nekoHeightsID"), (byte) 2).setBiomeName("tragicmc.nekoHeights");
		
		/*
		//Wilds/Nerve Center biomes
		WildPlains = new BiomeGenWilds(200, (byte) 0).setBiomeName("tragicmc.wildPlains");
		IriseiPlains = new BiomeGenWilds(201, (byte) 1).setBiomeName("tragicmc.iriseiPlains");
		IriseiPlains.topBlock = Blocks.emerald_block.getDefaultState();
		SeraleisSerenade = new BiomeGenWilds(202, (byte) 2).setBiomeName("tragicmc.seraleisSerenade");
		SeraleisSerenade.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildForest = new BiomeGenWilds(203, (byte) 0).setBiomeName("tragicmc.wildForest");
		ImbertonForest = new BiomeGenWilds(204, (byte) 1).setBiomeName("tragicmc.imbertonForest");
		ImbertonForest.topBlock = Blocks.emerald_block.getDefaultState();
		UpsidusVeld = new BiomeGenWilds(205, (byte) 2).setBiomeName("tragicmc.upsidusVeld");
		UpsidusVeld.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildDenseForest = new BiomeGenWilds(206, (byte) 0).setBiomeName("tragicmc.wildDenseForest");
		KlahksTrove = new BiomeGenWilds(207, (byte) 1).setBiomeName("tragicmc.klahksTrove");
		KlahksTrove.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildValley = new BiomeGenWilds(208, (byte) 0).setBiomeName("tragicmc.wildValley");
		CeresteValley = new BiomeGenWilds(209, (byte) 1).setBiomeName("tragicmc.ceresteValley");
		CeresteValley.topBlock = Blocks.emerald_block.getDefaultState();
		RelicanthicValley = new BiomeGenWilds(210, (byte) 2).setBiomeName("tragicmc.relicanthicValley");
		RelicanthicValley.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildHills = new BiomeGenWilds(211, (byte) 0).setBiomeName("tragicmc.wildHills");
		KluveTerrace = new BiomeGenWilds(212, (byte) 1).setBiomeName("tragicmc.kluveTerrace");
		KluveTerrace.topBlock = Blocks.emerald_block.getDefaultState();
		IronveinHills = new BiomeGenWilds(213, (byte) 2).setBiomeName("tragicmc.ironveinHills");
		IronveinHills.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildForestHills = new BiomeGenWilds(214, (byte) 0).setBiomeName("tragicmc.wildForestHills");
		HalsydeHills = new BiomeGenWilds(215, (byte) 1).setBiomeName("tragicmc.halsydeHills");
		HalsydeHills.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildExtremeHills = new BiomeGenWilds(216, (byte) 0).setBiomeName("tragicmc.wildExtremeHills");
		ZybianHeights = new BiomeGenWilds(217, (byte) 0).setBiomeName("tragicmc.zybianHeights");
		ZybianHeights.topBlock = Blocks.emerald_block.getDefaultState();
		TurbulentHeights = new BiomeGenWilds(218, (byte) 0).setBiomeName("tragicmc.turbulentHeights");
		TurbulentHeights.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildSteppes = new BiomeGenWilds(219, (byte) 0).setBiomeName("tragicmc.wildSteppes");
		GandreaSteppes = new BiomeGenWilds(220, (byte) 1).setBiomeName("tragicmc.gandreaSteppes");
		GandreaSteppes.topBlock = Blocks.emerald_block.getDefaultState();
		TombstoneFields = new BiomeGenWilds(221, (byte) 2).setBiomeName("tragicmc.tombstoneFields");
		TombstoneFields.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildSavanna = new BiomeGenWilds(222, (byte) 0).setBiomeName("tragicmc.wildSavanna");
		PrahpsPast = new BiomeGenWilds(223, (byte) 1).setBiomeName("tragicmc.prahpsPast");
		PrahpsPast.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildDesert = new BiomeGenWilds(224, (byte) 0).setBiomeName("tragicmc.wildDesert");
		FerrierScarlands = new BiomeGenWilds(225, (byte) 1).setBiomeName("tragicmc.ferrierScarlands");
		FerrierScarlands.topBlock = Blocks.emerald_block.getDefaultState();
		DesertOfAkhora = new BiomeGenWilds(226, (byte) 2).setBiomeName("tragicmc.desertOfAkhora");
		DesertOfAkhora.topBlock = Blocks.emerald_block.getDefaultState();
		VeneriaOasis = new BiomeGenWilds(227, (byte) 3).setBiomeName("tragicmc.veneriaOasis");
		VeneriaOasis.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildRiver = new BiomeGenWilds(228, (byte) 0).setBiomeName("tragicmc.wildRiver");
		WildRiver.topBlock = Blocks.bedrock.getDefaultState();
		RiverOfSouls = new BiomeGenWilds(229, (byte) 1).setBiomeName("tragicmc.riverOfSouls");
		RiverOfSouls.topBlock = Blocks.lapis_block.getDefaultState();
		FyxisRiver = new BiomeGenWilds(230, (byte) 2).setBiomeName("tragicmc.fyxisRiver");
		FyxisRiver.topBlock = Blocks.lapis_block.getDefaultState();
		
		WildLake = new BiomeGenWilds(231, (byte) 0).setBiomeName("tragicmc.wildLake");
		WildLake.topBlock = Blocks.bedrock.getDefaultState();
		VexinLake = new BiomeGenWilds(232, (byte) 1).setBiomeName("tragicmc.vexinLake");
		VexinLake.topBlock = Blocks.lapis_block.getDefaultState();
		AggroLake = new BiomeGenWilds(233, (byte) 2).setBiomeName("tragicmc.aggroLake");
		AggroLake.topBlock = Blocks.lapis_block.getDefaultState();
		
		WildOcean = new BiomeGenWilds(234, (byte) 0).setBiomeName("tragicmc.wildOcean");
		WildOcean.topBlock = Blocks.bedrock.getDefaultState();
		SeaOfSorrow = new BiomeGenWilds(235, (byte) 1).setBiomeName("tragicmc.seaOfSorrow");
		SeaOfSorrow.topBlock = Blocks.lapis_block.getDefaultState();
		StelSea = new BiomeGenWilds(236, (byte) 2).setBiomeName("tragicmc.stelSea");
		StelSea.topBlock = Blocks.lapis_block.getDefaultState();
		ExivSea = new BiomeGenWilds(237, (byte) 3).setBiomeName("tragicmc.exivSea");
		ExivSea.topBlock = Blocks.lapis_block.getDefaultState();
		
		WildMountains = new BiomeGenWilds(238, (byte) 0).setBiomeName("tragicmc.wildMountains");
		LarinthianMountains = new BiomeGenWilds(239, (byte) 1).setBiomeName("tragicmc.larinthianMountains");
		LarinthianMountains.topBlock = Blocks.emerald_block.getDefaultState();
		EttenDrove = new BiomeGenWilds(240, (byte) 2).setBiomeName("tragicmc.ettenDrove");
		EttenDrove.topBlock = Blocks.emerald_block.getDefaultState();
		IrsalasVolcano = new BiomeGenWilds(241, (byte) 3).setBiomeName("tragicmc.irsalasVolcano");
		IrsalasVolcano.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildIsland = new BiomeGenWilds(242, (byte) 0).setBiomeName("tragicmc.wildIsland");
		TorsianIsle = new BiomeGenWilds(243, (byte) 1).setBiomeName("tragicmc.torsianIsle");
		TorsianIsle.topBlock = Blocks.emerald_block.getDefaultState();
		IslaDeMuerte = new BiomeGenWilds(244, (byte) 2).setBiomeName("tragicmc.islaDeMuerte");
		IslaDeMuerte.topBlock = Blocks.emerald_block.getDefaultState();
		
		WildDeepOcean = new BiomeGenWilds(245, (byte) 0).setBiomeName("tragicmc.wildDeepOcean");
		WildDeepOcean.topBlock = Blocks.bedrock.getDefaultState();
		LeviaTriangle = new BiomeGenWilds(246, (byte) 1).setBiomeName("tragicmc.leviaTriangle");
		LeviaTriangle.topBlock = Blocks.bedrock.getDefaultState();
		DesolateDepths = new BiomeGenWilds(247, (byte) 2).setBiomeName("tragicmc.desolateDepths");
		DesolateDepths.topBlock = Blocks.bedrock.getDefaultState();
		
		NerveCenter = new BiomeGenWilds(249, (byte) 0).setBiomeName("tragicmc.nerveCenter"); */
	}
}
