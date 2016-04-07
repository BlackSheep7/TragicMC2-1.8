package tragicneko.tragicmc.items.challenge;

import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.miniboss.TragicMiniBoss;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityArchangel;
import tragicneko.tragicmc.entity.mob.EntityFusea;
import tragicneko.tragicmc.entity.mob.EntityHarvester;
import tragicneko.tragicmc.entity.mob.EntityHunter;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityPsygote;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.entity.mob.EntityRanmas;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.util.WorldHelper;

public class Challenge {

	public static final RegistryNamespacedDefaultedByKey<ResourceLocation, Challenge> challengeRegistry = new RegistryNamespacedDefaultedByKey<ResourceLocation, Challenge>(new ResourceLocation("null"));
	
	@Deprecated
	public static final Challenge[] challengeList = new Challenge[249];

	public static final Challenge nullChallenge = new Challenge(0, false, 0);
	public static final Challenge zombieKills = new ChallengeEntity(1, false, 50, EntityZombie.class, false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectWheat = new ChallengeItem(2, true, 48, new ItemStack(Items.wheat), false);
	public static final Challenge stayAlive = new ChallengeLive(3, false, 1200);
	public static final Challenge stayAlive3 = new ChallengeLive(4, false, 3600).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge survive = new ChallengeLive(5, false, 180).setDifficulty(EnumDifficulty.EASY).setMobRushChallenge();
	public static final Challenge endermanKills = new ChallengeEntity(6, false, 30, EntityEnderman.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge bossKills = new ChallengeEntity(7, false, 10, TragicBoss.class, false).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge collectLeather = new ChallengeItem(8, true, 32, new ItemStack(Items.leather), false);
	public static final Challenge stayAlive7 = new ChallengeLive(9, false, 8400).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge witherKill = new ChallengeEntity(10, false, 1, EntityWither.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findEmerald = new ChallengeItem(11, true, 1, new ItemStack(Items.emerald), false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findDiamonds = new ChallengeItem(12, true, 6, new ItemStack(Items.diamond), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectAsh = new ChallengeItem(13, true, 64, new ItemStack(TragicItems.Ash), false);
	public static final Challenge miniBossKills = new ChallengeEntity(14, false, 25, TragicMiniBoss.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge stinKills = new ChallengeEntity(15, false, 25, EntityStin.class, false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge tragicNekoKills = new ChallengeEntity(16, false, 10, EntityTragicNeko.class, false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectGoldenApples = new ChallengeItem(17, true, 16, new ItemStack(Items.golden_apple, 1, 1), false).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge findWisp = new ChallengeEntity(18, true, 1, EntityWisp.class, true);
	public static final Challenge findApis = new ChallengeEntity(19, true, 1, EntityApis.class, true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findIronGolem = new ChallengeEntity(20, true, 1, EntityIronGolem.class, true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findSnowBlock = new ChallengeBlock(21, true, 1, Blocks.snow.getDefaultState(), true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge iceBucketChallenge = new ChallengeItem(22, true, 1, new ItemStack(Items.bucket), false).setLocationBased(BiomeGenBase.icePlains).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge findRecord11 = new ChallengeItem(23, true, 1, new ItemStack(Items.record_11), false).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge collectFish = new ChallengeItem(24, true, 64, new ItemStack(Items.fish), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectGunpowder = new ChallengeItem(25, true, 64, new ItemStack(Items.gunpowder), false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge collectRedSand = new ChallengeItem(26, true, 64, new ItemStack(Blocks.sand, 1, 1), false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge bossKill = new ChallengeEntity(27, false, 1, TragicBoss.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge survive2 = new ChallengeLive(28, false, 360).setDifficulty(EnumDifficulty.NORMAL).setMobRushChallenge();
	public static final Challenge collectCooldownDefuse = new ChallengeItem(29, true, 3, new ItemStack(TragicItems.CooldownDefuse), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findStarlitCliffs = new Challenge(30, true, 0).setLocationBased(TragicBiome.StarlitCliffs).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge findMesa = new Challenge(31, true, 0).setLocationBased(BiomeGenBase.mesa).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findMushroomIsland = new Challenge(32, true, 0).setLocationBased(BiomeGenBase.mushroomIsland).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findBedrock = new ChallengeBlock(33, true, 1, Blocks.bedrock.getDefaultState(), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findWater = new ChallengeBlock(34, true, 1, Blocks.water.getDefaultState(), true);
	public static final Challenge findAshenGrass = new ChallengeBlock(35, true, 1, TragicBlocks.AshenGrass.getDefaultState(), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findRedMushroomBlock = new ChallengeBlock(36, true, 1, Blocks.red_mushroom_block.getDefaultState(), true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findSand = new ChallengeBlock(37, true, 1, Blocks.sand.getDefaultState(), false);
	public static final Challenge findMossyCobblestone = new ChallengeBlock(38, true, 1, Blocks.mossy_cobblestone.getDefaultState(), false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findIronOre = new ChallengeBlock(39, true, 1, Blocks.iron_ore.getDefaultState(), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findIceMountains = new Challenge(40, true, 0).setLocationBased(BiomeGenBase.iceMountains).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findDeepOcean = new Challenge(41, true, 0).setLocationBased(BiomeGenBase.deepOcean).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findNorVox = new ChallengeEntity(42, true, 1, EntityNorVox.class, true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge killInklings = new ChallengeEntity(43, false, 50, EntityInkling.class, false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findHorses = new ChallengeEntity(44, true, 1, EntityHorse.class, true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge killTragicMobs = new ChallengeEntity(45, false, 100, TragicMob.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findOcelots = new ChallengeEntity(46, true, 1, EntityOcelot.class, true).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findVillagers = new ChallengeEntity(47, true, 1, EntityVillager.class, true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge killGhasts = new ChallengeEntity(48, false, 15, EntityGhast.class, false);
	public static final Challenge findSlime = new ChallengeEntity(49, true, 1, EntitySlime.class, true);
	public static final Challenge killZombiePigmen = new ChallengeEntity(50, false, 100, EntityPigZombie.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge survive3 = new ChallengeLive(51, false, 720).setDifficulty(EnumDifficulty.HARD).setMobRushChallenge();
	public static final Challenge cliffDiamond = new ChallengeItem(52, true, 1, new ItemStack(Items.diamond), false).setLocationBased(BiomeGenBase.extremeHills).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge killVillagers = new ChallengeEntity(53, false, 5, EntityVillager.class, false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectEmeralds = new ChallengeItem(54, true, 16, new ItemStack(Items.emerald), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectIronBlocks = new ChallengeItem(55, true, 64, new ItemStack(Blocks.iron_block), false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge killPirah = new ChallengeEntity(56, false, 25, EntityPirah.class, false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge killSkeletons = new ChallengeEntity(57, false, 50, EntitySkeleton.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findClay = new ChallengeBlock(58, true, 1, Blocks.clay.getDefaultState(), false);
	public static final Challenge findLeaves = new ChallengeBlock(59, true, 1, Blocks.leaves.getDefaultState(), true);
	public static final Challenge killKitsune = new ChallengeEntity(60, false, 1, EntityKitsune.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge killRagr = new ChallengeEntity(61, false, 3, EntityRagr.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge killWisps = new ChallengeEntity(62, false, 10, EntityWisp.class, false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge goFishing = new ChallengeItem(63, true, 1, new ItemStack(Items.fishing_rod), true).setLocationBased(BiomeGenBase.river);
	public static final Challenge goFishing2 = new ChallengeItem(64, true, 1, new ItemStack(Items.fishing_rod), true).setLocationBased(BiomeGenBase.beach);
	public static final Challenge findLightChestplate = new ChallengeItem(65, true, 1, new ItemStack(TragicItems.LightPlate), false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findAwakeningStone = new ChallengeItem(66, true, 1, new ItemStack(TragicItems.AwakeningStone), false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findDimensionalKey = new ChallengeItem(67, true, 1, new ItemStack(TragicItems.DimensionalKey), false).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge findSavanna = new Challenge(68, true, 0).setLocationBased(BiomeGenBase.savanna);
	public static final Challenge collectPotatoes = new ChallengeItem(69, true, 64, new ItemStack(Items.potato), true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectStatues = new ChallengeItem(70, true, 10, new ItemStack(TragicItems.MobStatue), true).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge findAbomination = new ChallengeEntity(71, true, 1, EntityAbomination.class, true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findPlague = new ChallengeEntity(72, true, 1, EntityPlague.class, true);
	public static final Challenge findTimeController = new ChallengeEntity(73, true, 1, EntityTimeController.class, true).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge collectSmoothNetherrack = new ChallengeBlock(74, true, 32, TragicBlocks.SmoothNetherrack.getDefaultState(), false);
	public static final Challenge collectRedFlowers = new ChallengeItem(75, true, 32, new ItemStack(Blocks.red_flower, 1, 0), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge goFishing3 = new ChallengeItem(76, true, 1, new ItemStack(Items.fishing_rod), true).setLocationBased(BiomeGenBase.frozenRiver).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findMobStatue = new ChallengeItem(77, true, 1, new ItemStack(TragicItems.MobStatue), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectTNT = new ChallengeItem(78, true, 64, new ItemStack(Blocks.tnt), false).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge collectEnderPearls = new ChallengeItem(79, true, 16, new ItemStack(Items.ender_pearl), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findEpicLore = new ChallengeLore(80, true, 1, 3).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findRareLore = new ChallengeLore(81, true, 1, 2).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectEpicLore = new ChallengeLore(82, true, 5, 3).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge collectUncommonLore = new ChallengeLore(83, true, 10, 1).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge wearDiamondArmor = new ChallengeEquipment(84, true, 4, new ItemStack[] {new ItemStack(Items.diamond_helmet), new ItemStack(Items.diamond_chestplate),
			new ItemStack(Items.diamond_leggings), new ItemStack(Items.diamond_boots)
	}).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge wearPumpkin = new ChallengeEquipment(85, true, 1, new ItemStack[] {new ItemStack(Blocks.pumpkin)});
	public static final Challenge wearDarkArmor = new ChallengeEquipment(86, true, 4, new ItemStack[] {new ItemStack(TragicItems.DarkHelm), new ItemStack(TragicItems.DarkPlate),
			new ItemStack(TragicItems.DarkLegs), new ItemStack(TragicItems.DarkBoots)
	}).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge findEverlastingLight = new ChallengeItem(87, true, 1, new ItemStack(TragicItems.EverlastingLight), false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge collectExoticFruit = new ChallengeItem(88, true, 16, new ItemStack(TragicItems.Honeydrop), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectChallengeScrolls = new ChallengeItem(89, true, 5, new ItemStack(TragicItems.ChallengeScroll), false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findEnderChest = new ChallengeItem(90, true, 1, new ItemStack(Blocks.ender_chest), false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge killBats = new ChallengeEntity(91, false, 15, EntityBat.class, false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge longTermKilling = new ChallengeEntity(92, false, 100, EntityCreature.class, false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge killPlayer = new ChallengeEntity(93, false, 1, EntityPlayer.class, false).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge findPlayer = new ChallengeEntity(94, true, 1, EntityPlayer.class, true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge longTermKilling2 = new ChallengeEntity(95, false, 500, EntityCreature.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge longTermKilling3 = new ChallengeEntity(96, false, 1000, EntityCreature.class, false).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge killAnimals = new ChallengeEntity(97, false, 30, EntityAnimal.class, false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findWolf = new ChallengeEntity(98, true, 1, EntityWolf.class, true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectRedstone = new ChallengeItem(99, true, 64, new ItemStack(Items.redstone), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge killEnderDragon = new ChallengeEntity(100, false, 1, EntityDragon.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge travelFar = new ChallengeRange(101, false, 1000);
	public static final Challenge travelFar2 = new ChallengeRange(102, false, 5000).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge travelFar3 = new ChallengeRange(103, false, 10000).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge travelFar4 = new ChallengeRange(104, false, 100000).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge findScorchedWasteland = new Challenge(105, true, 0).setLocationBased(TragicBiome.ScorchedWastelands).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findIreNetCannon = new ChallengeItem(106, true, 1, new ItemStack(TragicItems.IreNetParticleCannon), true).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findArchangel = new ChallengeEntity(107, true, 1, EntityArchangel.class, true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findRanmas = new ChallengeEntity(108, true, 1, EntityRanmas.class, true).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge findDarkForest = new Challenge(109, true, 0).setLocationBased(TragicBiome.DarkForest);
	public static final Challenge killOverlordCore = new ChallengeEntity(110, false, 1, EntityOverlordCore.class, false).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge findSynapse = new Challenge(111, true, 0).setLocationBased(TragicBiome.Synapse).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge collectNanoBots = new ChallengeItem(112, true, 64, new ItemStack(TragicItems.NanoBots), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findWingsOfLiberation = new ChallengeItem(113, true, 1, new ItemStack(TragicItems.WingsOfLiberation), true).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge killFusea = new ChallengeEntity(114, false, 10, EntityFusea.class, false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findPermafrost = new ChallengeBlock(115, true, 1, TragicBlocks.Permafrost.getDefaultState(), true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge netherBed = new ChallengeItem(116, true, 1, new ItemStack(Blocks.bed), false).setLocationBased(BiomeGenBase.hell).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge darkAeris = new ChallengeItem(117, true, 1, new ItemStack(TragicBlocks.Aeris), false).setLocationBased(TragicBiome.DarkMarsh).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findAshenBadlands = new Challenge(118, true, 0).setLocationBased(TragicBiome.AshenBadlands).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findHallowedHills = new Challenge(119, true, 0).setLocationBased(TragicBiome.HallowedHills).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findPsygote = new ChallengeEntity(120, true, 1, EntityPsygote.class, true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge killHarvesters = new ChallengeEntity(121, false, 10, EntityHarvester.class, false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge collectConduit = new ChallengeItem(122, true, 64, new ItemStack(TragicBlocks.Conduit), false).setDifficulty(EnumDifficulty.NORMAL);
	public static final Challenge findQuicksand = new ChallengeBlock(123, true, 1, TragicBlocks.Quicksand.getDefaultState(), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge killHunters = new ChallengeEntity(124, false, 30, EntityHunter.class, true).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge findSoulChest = new ChallengeBlock(125, true, 1, TragicBlocks.SoulChest.getDefaultState(), true).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge onABoat = new ChallengeEntity(126, true, 1, EntityBoat.class, true).setLocationBased(BiomeGenBase.deepOcean).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge collectQuartz = new ChallengeItem(127, true, 64, new ItemStack(Blocks.quartz_block), false);
	public static final Challenge findGuiltyThorn = new ChallengeItem(128, true, 1, new ItemStack(TragicItems.GuiltyThorn), false).setDifficulty(EnumDifficulty.EASY);
	public static final Challenge chickenMassacre = new ChallengeEntity(129, true, 20, EntityChicken.class, false).setTimed(600).setDifficulty(EnumDifficulty.HARD);
	public static final Challenge thisIsAmazing = new ChallengeEntity(130, true, 5, EntityCreature.class, false).setTimed(200).setDifficulty(EnumDifficulty.HARD);
	
	@Deprecated
	protected final int challengeID;
	protected final boolean savesProgress;
	protected final int requirement;

	protected EnumDifficulty difficulty = EnumDifficulty.PEACEFUL; //reuse vanilla world difficulty enum, to be changed to our own custom one though to make it string serializable
	protected boolean isTimed = false;
	protected int timeLimit = 0;
	protected boolean isMobRush = false;
	protected boolean isLocationBased = false;
	protected Predicate locationPredicate = null;
	
	public static final String CHALLENGE_PROG = "challengeProgress";
	public static final String CHALLENGE_LOC = "challengeLocation";
	public static final String CHALLENGE_TIME = "challengeTime";
	@Deprecated
	public static final String CHALLENGE_ID = "challengeID";

	@Deprecated
	private static final String[] challengeNames = new String[] {"null", "zombieKills", "collectWheat", "stayAlive", "stayAlive3", "survive", "endermanKills", "bossKills",
			"collectLeather", "stayAlive7", "witherKill", "findEmerald", "findDiamonds", "collectAsh", "miniBossKills", "stinKills", "tragicNekoKills", "collectGoldenApples",
			"findWisp", "findApis", "findIronGolem", "findSnowBlock", "iceBucketChallenge", "findRecord11", "collectFish", "collectGunpowder", "collectRedSand", "bossKill",
			"survive2", "collectCooldownDefuse", "findStarlitCliffs", "findMesa", "findMushroomIsland", "findBedrock", "findWater", "findAshenGrass", "findRedMushroomBlock",
			"findSand", "findMossyCobblestone", "findIronOre", "findIceMountains", "findDeepOcean", "findNorVox", "killInklings", "findHorses", "killTragicMobs", "findOcelots",
			"findVillagers", "killGhasts", "findSlime", "killZombiePigmen", "survive3", "cliffDiamond", "killVillagers", "collectEmeralds", "collectIronBlocks", "killPirah",
			"killSkeletons", "findClay", "findLeaves", "killKitsune", "killRagr", "killWisps", "goFishing", "goFishing2", "findLightChestplate", "findAwakeningStone",
			"findDimensionalKey", "findSavanna", "collectPotatoes", "collectStatues", "findAbomination", "findPlague", "findTimeController", "collectSmoothNetherrack",
			"collectRedFlowers", "goFishing3", "findMobStatue", "collectTNT", "collectEnderPearls", "findEpicLore", "findRareLore", "collectEpicLore", "collectUncommonLore",
			"wearDiamondArmor", "wearPumpkin", "wearDarkArmor", "findEverlastingLight", "collectExoticFruit", "collectChallengeScrolls", "findEnderChest", "killBats",
			"longTermKilling", "killPlayer", "findPlayer", "longTermKilling2", "longTermKilling3", "killAnimals", "findWolf", "collectRedstone", "killEnderDragon", "travelFar",
			"travelFar2", "travelFar3", "travelFar4", "findScorchedWasteland", "findIreNetCannon", "findArchangel", "findRanmas", "findDarkForest", "killOverlordCore",
			"findSynapse", "collectNanoBots", "findWingsOfLiberation", "killFusea", "findPermafrost", "netherBed", "darkAeris", "findAshenBadlands", "findHallowedHills",
			"findPsygote", "killHarvesters", "collectConduit", "findQuicksand", "killHunters", "findSoulChest", "onABoat", "collectQuartz", "findGuiltyThorn", "chickenMassacre",
			"thisIsAmazing"
	};

	public Challenge(int id, boolean saveProgress, int requirement)
	{
		if (challengeList[id] != null) throw new IllegalArgumentException("There is already a Challenge that has that id!");
		challengeList[id] = this;
		this.challengeID = id;
		this.savesProgress = saveProgress;
		this.requirement = requirement;
	}	

	/**
	 * Called on an entity's death to update challenge progress for the dead entity
	 * @param stack
	 * @param entity
	 * @param src
	 */
	public void onLivingDeath(ItemStack stack, EntityLivingBase entity) {
		if (stack.getTagCompound().hasKey(CHALLENGE_PROG) && !this.savesProgress) stack.getTagCompound().setInteger(CHALLENGE_PROG, 0);
	}

	/**
	 * Called on an entity's death to update challenge progress for the killer
	 * @param stack
	 * @param entity
	 * @param player
	 */
	public void onLivingKill(ItemStack stack, EntityLivingBase entity, EntityPlayer player) {

	}

	/**
	 * Called on update every tick for a player to update challenge progress
	 * @param stack
	 * @param player
	 */
	public void onLivingUpdate(ItemStack stack, EntityPlayer player) {
		if (stack.getTagCompound().hasKey(CHALLENGE_PROG))
		{
			if (this.isTimed)
			{
				if (!stack.getTagCompound().hasKey(CHALLENGE_TIME) || stack.getTagCompound().hasKey(CHALLENGE_TIME) && stack.getTagCompound().getInteger(CHALLENGE_TIME) <= 0)
				{
					stack.getTagCompound().setInteger(CHALLENGE_TIME, this.timeLimit);
					stack.getTagCompound().setInteger(CHALLENGE_PROG, 0);
				}
				else
				{
					if (stack.getTagCompound().hasKey(CHALLENGE_TIME) && stack.getTagCompound().getInteger(CHALLENGE_TIME) > 0) stack.getTagCompound().setInteger(CHALLENGE_TIME, stack.getTagCompound().getInteger(CHALLENGE_TIME) - 1);
				}
			}

			if (this.isMobRush)
			{
				List<EntityMob> list = player.worldObj.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().expand(100.0, 100.0, 100.0));

				for (int j = 0; j < list.size(); j++)
				{
					list.get(j).setAttackTarget(player);
				}

				Chunk chk = player.worldObj.getChunkFromBlockCoords(WorldHelper.getBlockPos(player));
				chk.setInhabitedTime(chk.getInhabitedTime() + 10000L);
			}
			
			if (this.isLocationBased)
			{
				BiomeGenBase currentBiome = player.worldObj.getBiomeGenForCoords(WorldHelper.getBlockPos(player));
				boolean flag = this.locationPredicate != null && this.locationPredicate.apply(currentBiome);
				stack.getTagCompound().setBoolean(CHALLENGE_LOC, flag);
			}
		}
		
		this.updateStackProgress(stack, player.worldObj, player);
	}

	/**
	 * Called on update by the ItemStack to update challenge progress to that stack's NBT, this does final checks that can actually set
	 * a challenge to completed
	 * @param stack
	 * @param world
	 * @param entity
	 */
	public void updateStackProgress(ItemStack stack, World world, Entity entity) {
		if (stack.getTagCompound().hasKey(CHALLENGE_PROG))
		{
			if (stack.getTagCompound().getInteger(CHALLENGE_PROG) >= this.requirement)
			{
				if (this.isLocationBased && stack.getTagCompound().hasKey(CHALLENGE_LOC) && stack.getTagCompound().getBoolean(CHALLENGE_LOC) || !this.isLocationBased)
				{
					stack.setItemDamage(250);
					this.onCompletion(entity);
				}
			}
		}
	}

	/**
	 * Called when a challenge is completed via updateStackProgress, this can be used to trigger Achievements or other special buffs
	 * @param entity
	 */
	public void onCompletion(Entity entity) {
		
	}

	/**
	 * Set a challenge to force all nearby mobs to target the holder the duration of the Challenge
	 * @return
	 */
	public Challenge setMobRushChallenge()
	{
		this.isMobRush = true;
		return this;
	}

	/**
	 * Set the difficulty level of the Challenge, generally only affects the loot table
	 * @param i
	 * @return
	 */
	public Challenge setDifficulty(EnumDifficulty dif)
	{
		this.difficulty = dif;
		return this;
	}

	/**
	 * Set an additional requirement for a location using a predicate
	 * @param biome
	 * @return
	 */
	public Challenge setLocationBased(Predicate pred)
	{
		this.locationPredicate  = pred;
		this.isLocationBased = true;
		return this;
	}

	static class SingleBiomePred implements Predicate {

		private final BiomeGenBase biome;

		public SingleBiomePred(BiomeGenBase biome) {
			this.biome = biome;
		}

		@Override
		public boolean apply(Object input) {
			return input instanceof BiomeGenBase && canApply((BiomeGenBase) input);
		}

		public boolean canApply(BiomeGenBase b) {
			return b == this.biome;
		}

	}

	public Challenge setLocationBased(BiomeGenBase biome) {
		return setLocationBased(new SingleBiomePred(biome));
	}

	/**
	 * Whether a challenge has a timer that defines how long you have to do something before the challenge progress is reset
	 * @param limit
	 * @return
	 */
	public Challenge setTimed(int limit) {
		this.isTimed = true;
		this.timeLimit = limit;
		return this;
	}

	public int getChallengeId() {
		return this.challengeID;
	}

	public boolean getSavesProgress() {
		return this.savesProgress;
	}

	public int getRequirement() {
		return this.requirement;
	}

	public EnumDifficulty getDifficulty() {
		return this.difficulty;
	}

	public int getDifficultyId() {
		return this.difficulty.getDifficultyId();
	}

	public boolean getTimed() {
		return this.isTimed;
	}
	
	public int getTimeLimit() {
		return this.timeLimit;
	}

	public boolean isMobRush() {
		return this.isMobRush;
	}

	public boolean isLocationBased() {
		return this.isLocationBased;
	}

	public Predicate getLocationPredicate() {
		return this.locationPredicate;
	}

	public static String getNameFromID(int id)
	{
		return StatCollector.translateToLocal("challenge." + challengeNames[id] + ".name");
	}

	public static String getDesc(int id)
	{
		return StatCollector.translateToLocal("challenge." + challengeNames[id] + ".desc");
	}

	public static Challenge getChallengeFromID(int id)
	{
		return challengeList[id];
	}
}