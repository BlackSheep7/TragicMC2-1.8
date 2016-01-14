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
	
	//Nerve Center biome
	public static BiomeGenBase NerveCenter;

	public TragicBiome(int par1, byte par2) {
		super(par1, true);
		this.variant = par2;
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.fillerBlock = TragicBlocks.DarkStone.getDefaultState();
		this.topBlock = TragicBlocks.DeadDirt.getDefaultState();
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = -999;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.bigMushroomsPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.maxHeight = 0F;
		this.minHeight = 0F;
		if (TragicConfig.allowPlague) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPlague.class, TragicConfig.plagueSC, TragicConfig.plagueGS[0], TragicConfig.plagueGS[1]));
		if (TragicConfig.allowTragicNeko) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTragicNeko.class, TragicConfig.tragicNekoSC, TragicConfig.tragicNekoGS[0], TragicConfig.tragicNekoGS[1]));
		if (TragicConfig.allowTimeController) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityTimeController.class, TragicConfig.timeControllerSC, 0, 0));
		if (TragicConfig.allowErkel) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityErkel.class, TragicConfig.erkelSC, TragicConfig.erkelGS[0], TragicConfig.erkelGS[1]));
		if (TragicConfig.allowKindlingSpirit) this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWisp.class, TragicConfig.kindlingSpiritSC, TragicConfig.kindlingSpiritGS[0], TragicConfig.kindlingSpiritGS[1]));
		if (TragicConfig.allowAvris) this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityAvris.class, TragicConfig.avrisSC, TragicConfig.avrisGS[0], TragicConfig.avrisGS[1])); 
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
        IBlockState iblockstate1 = this.fillerBlock;
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
                            iblockstate1 = this.fillerBlock;
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
		DecayingHills = new BiomeGenDecayingWasteland(TragicConfig.idDecayingHills, (byte) 0).setBiomeName("tragicmc.decayingHills");
		DecayingValley = new BiomeGenDecayingWasteland(TragicConfig.idDecayingValley, (byte) 1).setBiomeName("tragicmc.decayingValley");
		DecayingWasteland = new BiomeGenDecayingWasteland(TragicConfig.idDecayingWasteland, (byte) 2).setBiomeName("tragicmc.decayingWasteland");
		DecayingMountains = new BiomeGenDecayingWasteland(TragicConfig.idDecayingMountains, (byte) 3).setBiomeName("tragicmc.decayingMountains");

		PaintedForest = new BiomeGenPaintedForest(TragicConfig.idPaintedForest, (byte) 0).setBiomeName("tragicmc.paintedForest");
		PaintedPlains = new BiomeGenPaintedForest(TragicConfig.idPaintedPlains, (byte) 1).setBiomeName("tragicmc.paintedPlains");
		PaintedHills = new BiomeGenPaintedForest(TragicConfig.idPaintedHills, (byte) 2).setBiomeName("tragicmc.paintedHills");
		PaintedClearing = new BiomeGenPaintedForest(TragicConfig.idPaintedClearing, (byte) 3).setBiomeName("tragicmc.paintedClearing");

		AshenMountains = new BiomeGenAshenHills(TragicConfig.idAshenMountains, (byte) 0).setBiomeName("tragicmc.ashenMountains");
		AshenHills = new BiomeGenAshenHills(TragicConfig.idAshenHills, (byte) 1).setBiomeName("tragicmc.ashenHills");
		AshenBadlands = new BiomeGenAshenHills(TragicConfig.idAshenBadlands, (byte) 2).setBiomeName("tragicmc.ashenBadlands");

		StarlitPrarie = new BiomeGenStarlitPrarie(TragicConfig.idStarlitPrarie, (byte) 0).setBiomeName("tragicmc.starlitPrarie");
		StarlitPlateaus = new BiomeGenStarlitPrarie(TragicConfig.idStarlitPlateaus, (byte) 1).setBiomeName("tragicmc.starlitPlateaus");
		StarlitCliffs = new BiomeGenStarlitPrarie(TragicConfig.idStarlitCliffs, (byte) 2).setBiomeName("tragicmc.starlitCliffs");
		StarlitLowlands = new BiomeGenStarlitPrarie(TragicConfig.idStarlitLowlands, (byte) 3).setBiomeName("tragicmc.starlitLowlands");

		TaintedSpikes = new BiomeGenTaintedSpikes(TragicConfig.idTaintedSpikes, (byte) 0).setBiomeName("tragicmc.taintedSpikes");
		TaintedLowlands = new BiomeGenTaintedSpikes(TragicConfig.idTaintedLowlands, (byte) 1).setBiomeName("tragicmc.taintedLowlands");
		TaintedRises = new BiomeGenTaintedSpikes(TragicConfig.idTaintedRises, (byte) 2).setBiomeName("tragicmc.taintedRises");
		TaintedScarlands = new BiomeGenTaintedSpikes(TragicConfig.idTaintedScarlands, (byte) 3).setBiomeName("tragicmc.taintedScarlands");
		TaintedIsles = new BiomeGenTaintedSpikes(TragicConfig.idTaintedIsles, (byte) 4).setBiomeName("tragicmc.taintedIsles");

		Synapse = new BiomeGenSynapse(TragicConfig.idSynapse).setBiomeName("tragicmc.synapse");
		SynapseDead = new BiomeGenSynapse(TragicConfig.idSynapseDead).setBiomeName("tragicmc.synapseDead");
		SynapseCorrupt = new BiomeGenSynapse(TragicConfig.idSynapseCorrupt).setBiomeName("tragicmc.synapseCorrupt");

		HallowedHills = new BiomeGenHallowedHills(TragicConfig.idHallowedHills, (byte) 0).setBiomeName("tragicmc.hallowedHills");
		HallowedForest = new BiomeGenHallowedHills(TragicConfig.idHallowedForest, (byte) 1).setBiomeName("tragicmc.hallowedForest");
		HallowedPrarie = new BiomeGenHallowedHills(TragicConfig.idHallowedPrarie, (byte) 2).setBiomeName("tragicmc.hallowedPrarie");
		HallowedCliffs = new BiomeGenHallowedHills(TragicConfig.idHallowedCliffs, (byte) 3).setBiomeName("tragicmc.hallowedCliffs");

		ScorchedWastelands = new BiomeGenScorchedWasteland(TragicConfig.idScorchedWastelands, (byte) 0).setBiomeName("tragicmc.scorchedWastelands");
		ScorchedValley = new BiomeGenScorchedWasteland(TragicConfig.idScorchedValley, (byte) 1).setBiomeName("tragicmc.scorchedValley");
		ScorchedScarlands = new BiomeGenScorchedWasteland(TragicConfig.idScorchedScarlands, (byte) 2).setBiomeName("tragicmc.scorchedScarlands");

		CorrodedSteppe = new BiomeGenCorrodedSteppe(TragicConfig.idCorrodedSteppe, (byte) 0).setBiomeName("tragicmc.corrodedSteppe");
		CorrodedHeights = new BiomeGenCorrodedSteppe(TragicConfig.idCorrodedHeights, (byte) 1).setBiomeName("tragicmc.corrodedHeights");
		CorrodedVeld = new BiomeGenCorrodedSteppe(TragicConfig.idCorrodedVeld, (byte) 2).setBiomeName("tragicmc.corrodedVeld");
		CorrodedRunoff = new BiomeGenCorrodedSteppe(TragicConfig.idCorrodedRunoff, (byte) 3).setBiomeName("tragicmc.corrodedRunoff");
		CorrodedFallout = new BiomeGenCorrodedSteppe(TragicConfig.idCorrodedFallout, (byte) 4).setBiomeName("tragicmc.corrodedFallout");

		FrozenTundra = new BiomeGenFrozenTundra(TragicConfig.idFrozenTundra, (byte) 0).setBiomeName("tragicmc.frozenTundra");
		FrozenHills = new BiomeGenFrozenTundra(TragicConfig.idFrozenHills, (byte) 1).setBiomeName("tragicmc.frozenHills");
		FrozenDepths = new BiomeGenFrozenTundra(TragicConfig.idFrozenDepths, (byte) 2).setBiomeName("tragicmc.frozenDepths");

		Crystal = new BiomeGenCrystal(TragicConfig.idCrystal).setBiomeName("tragicmc.crystal");

		DarkForest = new BiomeGenDarkForest(TragicConfig.idDarkForest, (byte) 0).setBiomeName("tragicmc.darkForest");
		DarkForestHills = new BiomeGenDarkForest(TragicConfig.idDarkForestHills, (byte) 1).setBiomeName("tragicmc.darkForestHills");
		DarkMarsh = new BiomeGenDarkForest(TragicConfig.idDarkMarsh, (byte) 2).setBiomeName("tragicmc.darkMarsh");
		
		//Wilds/Nerve Center biomes
		WildPlains = new BiomeGenWilds(200, (byte) 0).setBiomeName("tragicmc.wildPlains");
		IriseiPlains = new BiomeGenWilds(201, (byte) 1).setBiomeName("tragicmc.iriseiPlains");
		SeraleisSerenade = new BiomeGenWilds(202, (byte) 2).setBiomeName("tragicmc.seraleisSerenade");
		
		WildForest = new BiomeGenWilds(203, (byte) 0).setBiomeName("tragicmc.wildForest");
		ImbertonForest = new BiomeGenWilds(204, (byte) 1).setBiomeName("tragicmc.imbertonForest");
		UpsidusVeld = new BiomeGenWilds(205, (byte) 2).setBiomeName("tragicmc.upsidusVeld");
		
		WildDenseForest = new BiomeGenWilds(206, (byte) 0).setBiomeName("tragicmc.wildDenseForest");
		KlahksTrove = new BiomeGenWilds(207, (byte) 1).setBiomeName("tragicmc.klahksTrove");
		
		WildValley = new BiomeGenWilds(208, (byte) 0).setBiomeName("tragicmc.wildValley");
		CeresteValley = new BiomeGenWilds(209, (byte) 1).setBiomeName("tragicmc.ceresteValley");
		RelicanthicValley = new BiomeGenWilds(210, (byte) 2).setBiomeName("tragicmc.relicanthicValley");
		
		WildHills = new BiomeGenWilds(211, (byte) 0).setBiomeName("tragicmc.wildHills");
		KluveTerrace = new BiomeGenWilds(212, (byte) 1).setBiomeName("tragicmc.kluveTerrace");
		IronveinHills = new BiomeGenWilds(213, (byte) 2).setBiomeName("tragicmc.ironveinHills");
		
		WildForestHills = new BiomeGenWilds(214, (byte) 0).setBiomeName("tragicmc.wildForestHills");
		HalsydeHills = new BiomeGenWilds(215, (byte) 1).setBiomeName("tragicmc.halsydeHills");
		
		WildExtremeHills = new BiomeGenWilds(216, (byte) 0).setBiomeName("tragicmc.wildExtremeHills");
		ZybianHeights = new BiomeGenWilds(217, (byte) 0).setBiomeName("tragicmc.zybianHeights");
		TurbulentHeights = new BiomeGenWilds(218, (byte) 0).setBiomeName("tragicmc.turbulentHeights");
		
		WildSteppes = new BiomeGenWilds(219, (byte) 0).setBiomeName("tragicmc.wildSteppes");
		GandreaSteppes = new BiomeGenWilds(220, (byte) 1).setBiomeName("tragicmc.gandreaSteppes");
		TombstoneFields = new BiomeGenWilds(221, (byte) 2).setBiomeName("tragicmc.tombstoneFields");
		
		WildSavanna = new BiomeGenWilds(222, (byte) 0).setBiomeName("tragicmc.wildSavanna");
		PrahpsPast = new BiomeGenWilds(223, (byte) 1).setBiomeName("tragicmc.prahpsPast");
		
		WildDesert = new BiomeGenWilds(224, (byte) 0).setBiomeName("tragicmc.wildDesert");
		FerrierScarlands = new BiomeGenWilds(225, (byte) 1).setBiomeName("tragicmc.ferrierScarlands");
		DesertOfAkhora = new BiomeGenWilds(226, (byte) 2).setBiomeName("tragicmc.desertOfAkhora");
		VeneriaOasis = new BiomeGenWilds(227, (byte) 3).setBiomeName("tragicmc.veneriaOasis");
		
		WildRiver = new BiomeGenWilds(228, (byte) 0).setBiomeName("tragicmc.wildRiver");
		RiverOfSouls = new BiomeGenWilds(229, (byte) 1).setBiomeName("tragicmc.riverOfSouls");
		FyxisRiver = new BiomeGenWilds(230, (byte) 2).setBiomeName("tragicmc.fyxisRiver");
		
		WildLake = new BiomeGenWilds(231, (byte) 0).setBiomeName("tragicmc.wildLake");
		VexinLake = new BiomeGenWilds(232, (byte) 1).setBiomeName("tragicmc.vexinLake");
		AggroLake = new BiomeGenWilds(233, (byte) 2).setBiomeName("tragicmc.aggroLake");
		
		WildOcean = new BiomeGenWilds(234, (byte) 0).setBiomeName("tragicmc.wildOcean");
		SeaOfSorrow = new BiomeGenWilds(235, (byte) 1).setBiomeName("tragicmc.seaOfSorrow");
		VexinLake = new BiomeGenWilds(236, (byte) 2).setBiomeName("tragicmc.vexinLake");
		StelSea = new BiomeGenWilds(237, (byte) 3).setBiomeName("tragicmc.stelSea");
		ExivSea = new BiomeGenWilds(238, (byte) 4).setBiomeName("tragicmc.exivSea");
		
		WildMountains = new BiomeGenWilds(239, (byte) 0).setBiomeName("tragicmc.wildMountains");
		LarinthianMountains = new BiomeGenWilds(240, (byte) 1).setBiomeName("tragicmc.larinthianMountains");
		EttenDrove = new BiomeGenWilds(241, (byte) 2).setBiomeName("tragicmc.ettenDrove");
		IrsalasVolcano = new BiomeGenWilds(242, (byte) 3).setBiomeName("tragicmc.irsalasVolcano");
		
		WildIsland = new BiomeGenWilds(243, (byte) 0).setBiomeName("tragicmc.wildIsland");
		TorsianIsle = new BiomeGenWilds(244, (byte) 1).setBiomeName("tragicmc.torsianIsle");
		IslaDeMuerte = new BiomeGenWilds(245, (byte) 2).setBiomeName("tragicmc.islaDeMuerte");
		
		WildDeepOcean = new BiomeGenWilds(246, (byte) 0).setBiomeName("tragicmc.wildDeepOcean");
		LeviaTriangle = new BiomeGenWilds(247, (byte) 1).setBiomeName("tragicmc.leviaTriangle");
		DesolateDepths = new BiomeGenWilds(248, (byte) 2).setBiomeName("tragicmc.desolateDepths");
		
		NerveCenter = new BiomeGenWilds(249, (byte) 0).setBiomeName("tragicmc.nerveCenter");
	}
}
