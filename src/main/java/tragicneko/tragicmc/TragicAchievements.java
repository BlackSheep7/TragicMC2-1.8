package tragicneko.tragicmc;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class TragicAchievements {

	private static final int offsetX = 0; //offsets to fix achievement page glitch if you go too far up or at least not make it as likely to occur
	private static final int offsetY = 8;

	//main achievement line
	public static Achievement thanks = (Achievement) new Achievement("tragicmc.achievement.thanks", "tragicmc.thanks", offsetX, offsetY, TragicItems.NekoNekoWand, (Achievement) null).registerStat().setSpecial().initIndependentStat();
	public static Achievement mercury = new Achievement("tragicmc.achievement.mercury", "tragicmc.mercury", 2 + offsetX, offsetY, TragicItems.RedMercury, thanks).registerStat();
	public static Achievement obsidianOrb = new Achievement("tragicmc.achievement.obsidianOrb", "tragicmc.obsidianOrb", 2 + offsetX, -2 + offsetY, TragicItems.BleedingObsidianOrb, mercury).registerStat();
	public static Achievement dimensionalKey = new Achievement("tragicmc.achievement.dimensionalKey", "tragicmc.dimensionalKey", 2 + offsetX, -4 + offsetY, TragicItems.DimensionalKey, obsidianOrb).setSpecial().registerStat();
	public static Achievement collision = new Achievement("tragicmc.achievement.collision", "tragicmc.collision", 2 + offsetX, -6 + offsetY, TragicItems.LightParticles, dimensionalKey).registerStat();
	public static Achievement aeris = new Achievement("tragicmc.achievement.aeris", "tragicmc.aeris", 2 + offsetX, -8 + offsetY, TragicBlocks.Aeris, collision).registerStat();
	public static Achievement synapseLink = new Achievement("tragicmc.achievement.synapseLink", "tragicmc.synapseLink", 2 + offsetX, -10 + offsetY, TragicItems.SynapseLink, aeris).setSpecial().registerStat();
	public static Achievement synapse = new Achievement("tragicmc.achievement.synapse", "tragicmc.synapse", 2 + offsetX, -12 + offsetY, TragicBlocks.SynapseCore, synapseLink).registerStat();
	public static Achievement overlord = new Achievement("tragicmc.achievement.overlord", "tragicmc.overlord", 2 + offsetX, -14 + offsetY, TragicItems.NanoBots, synapse).registerStat();
	public static Achievement overlord2 = new Achievement("tragicmc.achievement.overlord2", "tragicmc.overlord2", 4 + offsetX, -14 + offsetY, TragicItems.SynapseCrystal, overlord).registerStat();
	public static Achievement overlord3 = new Achievement("tragicmc.achievement.overlord3", "tragicmc.overlord3", 6 + offsetX, -14 + offsetY, TragicItems.CorruptedEye, overlord2).registerStat();
	public static Achievement overlord4 = new Achievement("tragicmc.achievement.overlord4", "tragicmc.overlord4", 8 + offsetX, -14 + offsetY, TragicItems.Sentinel, overlord3).registerStat().setSpecial();

	//Neko side expansion achievements
	public static Achievement tragicNekoDevice = (Achievement) new Achievement("tragicmc.achievement.tragicNekoDevice", "tragicmc.tragicNekoDevice", -4 + offsetX, 10 + offsetY, Items.gunpowder, null).registerStat().initIndependentStat();
	public static Achievement tragicNekoRelease = (Achievement) new Achievement("tragicmc.achievement.tragicNekoRelease", "tragicmc.tragicNekoRelease", -2 + offsetX, 10 + offsetY, TragicItems.NekoMindControlDevice, tragicNekoDevice).registerStat().setSpecial();
	public static Achievement tragicNekoFile = (Achievement) new Achievement("tragicmc.achievement.tragicNekoFile", "tragicmc.tragicNekoFile", -2 + offsetX, 8 + offsetY, TragicItems.Starstruck, tragicNekoRelease).registerStat();
	public static Achievement tragicNekoAllFiles = (Achievement) new Achievement("tragicmc.achievement.tragicNekoAllFiles", "tragicmc.tragicNekoAllFiles", 0 + offsetX, 8 + offsetY, TragicItems.Archaic, tragicNekoFile).registerStat();
	public static Achievement tragicNekoSiphon = (Achievement) new Achievement("tragicmc.achievement.tragicNekoSiphon", "tragicmc.tragicNekoSiphon",-2 + offsetX, 12 + offsetY, TragicItems.RecaptureSiphon, tragicNekoRelease).registerStat();
	public static Achievement tragicNeko100Release = (Achievement) new Achievement("tragicmc.achievement.tragicNeko100Release", "tragicmc.tragicNeko100Release", -2 + offsetX, 14 + offsetY, TragicBlocks.NekitePlate, tragicNekoSiphon).registerStat();
	public static Achievement tragicNekoWarpDrive = (Achievement) new Achievement("tragicmc.achievement.tragicNekoWarpDrive", "tragicmc.tragicNekoWarpDrive", 0 + offsetX, 10 + offsetY, TragicItems.WarpDrive, tragicNekoRelease).registerStat();
	public static Achievement tragicNekoHomeworld = (Achievement) new Achievement("tragicmc.achievement.tragicNekoHomeworld", "tragicmc.tragicNekoHomeworld", 2 + offsetX, 10 + offsetY, TragicBlocks.NekoGrass, tragicNekoWarpDrive).registerStat().setSpecial();
	public static Achievement tragicNekoTrader = (Achievement) new Achievement("tragicmc.achievement.tragicNekoTrader", "tragicmc.tragicNekoTrader", 2 + offsetX, 8 + offsetY, TragicItems.Sapphire, tragicNekoHomeworld).registerStat();
	public static Achievement tragicNekoNekite = (Achievement) new Achievement("tragicmc.achievement.tragicNekoNekite", "tragicmc.tragicNekoNekite", 2 + offsetX, 12 + offsetY, TragicItems.Nekite, tragicNekoHomeworld).registerStat();
	public static Achievement tragicNekoLauncher = (Achievement) new Achievement("tragicmc.achievement.tragicNekoLauncher", "tragicmc.TragicNekoLauncher", 0 + offsetX, 12 + offsetY, TragicItems.NekoLauncher, tragicNekoNekite).registerStat();
	public static Achievement tragicNekoSwordAndGun = (Achievement) new Achievement("tragicmc.achievement.tragicNekoSwordAndGun", "tragicmc.TragicNekoSwordAndGun", 2 + offsetX, 14 + offsetY, TragicItems.NekoRayGun, tragicNekoNekite).registerStat();
	public static Achievement tragicNekoDefeatNekoid = (Achievement) new Achievement("tragicmc.achievement.tragicNekoDefeatNekoid", "tragicmc.tragicNekoDefeatNekoid", 4 + offsetX, 10 + offsetY, TragicItems.NekoidStrain, tragicNekoHomeworld).registerStat().setSpecial();
	public static Achievement tragicNekoNekoidBlaster = (Achievement) new Achievement("tragicmc.achievement.tragicNekoNekoidBlaster", "tragicmc.tragicNekoNekoidBlaster", 4 + offsetX, 12 + offsetY, TragicItems.NekoidsBlaster, tragicNekoDefeatNekoid).registerStat();
	public static Achievement tragicNekoInfluence = (Achievement) new Achievement("tragicmc.achievement.tragicNekoInfluence", "tragicmc.tragicNekoInfluence", 4 + offsetX, 8 + offsetY, TragicItems.NekoInfluencer, tragicNekoDefeatNekoid).registerStat();
	
	//first expansion achievements
	//public static Achievement wildsRelic = new Achievement("tragicmc.achievement.wildsRelic", "tragicmc.wildsRelic", 0, 0, Items.apple, (Achievement) null);
	//public static Achievement wilds = new Achievement("tragicmc.achievement.wilds", "tragicmc.wilds", 0, 0, Items.apple, (Achievement) null);
	//public static Achievement spiritCatcher = new Achievement("tragicmc.achievement.spiritCatcher", "tragicmc.spiritCatcher", 0, 0, Items.apple, (Achievement) null);
	//public static Achievement nerveCenter = new Achievement("tragicmc.achievement.nerveCenter", "tragicmc.nerveCenter", 0, 0, Items.apple, (Achievement) null);
	//public static Achievement admin = new Achievement("tragicmc.achievement.admin", "tragicmc.admin", 0, 0, Items.apple, (Achievement) null);
	//public static Achievement admin2 = new Achievement("tragicmc.achievement.admin2", "tragicmc.admin2", 0, 0, Items.apple, (Achievement) null);
	//public static Achievement admin3 = new Achievement("tragicmc.achievement.admin3", "tragicmc.admin3", 0, 0, Items.apple, (Achievement) null);
	//public static Achievement admin4 = new Achievement("tragicmc.achievement.admin4", "tragicmc.admin4", 0, 0, Items.apple, (Achievement) null);

	//amulet achievements
	public static Achievement tungsten = new Achievement("tragicmc.achievement.tungsten", "tragicmc.tungsten", 4 + offsetX, offsetY, TragicItems.Tungsten, mercury).registerStat();
	public static Achievement amulet = new Achievement("tragicmc.achievement.amulet", "tragicmc.amulet", 6 + offsetX, offsetY, TragicItems.BlacksmithAmulet, tungsten).registerStat();
	public static Achievement amuletEquip = new Achievement("tragicmc.achievement.amuletEquip", "tragicmc.amuletEquip", 6 + offsetX, -2 + offsetY, TragicItems.CreeperAmulet, amulet).registerStat();
	public static Achievement amuletRelease = new Achievement("tragicmc.achievement.amuletRelease", "tragicmc.amuletRelease", 8 + offsetX, offsetY, TragicItems.AmuletRelease, amulet).registerStat();
	public static Achievement amuletMax = new Achievement("tragicmc.achievement.amuletMax", "tragicmc.amuletMax", 10 + offsetX, offsetY, TragicItems.ApisAmulet, amuletRelease).registerStat().setSpecial();
	public static Achievement amuletSpecial = new Achievement("tragicmc.achievement.amuletSpecial", "tragicmc.amuletSpecial", 6 + offsetX, -4 + offsetY, TragicItems.KitsuneAmulet, amuletEquip).registerStat();

	//doom and doomsday achievements
	public static Achievement doom = new Achievement("tragicmc.achievement.doom", "tragicmc.doom", offsetX, -2 + offsetY, TragicItems.GuiltyThorn, thanks).registerStat();
	public static Achievement doomsday = new Achievement("tragicmc.achievement.doomsday", "tragicmc.doomsday", -2 + offsetX, -2 + offsetY, TragicItems.Titan, doom).registerStat();
	public static Achievement doomsdayCombo = new Achievement("tragicmc.achievement.doomsdayCombo", "tragicmc.doomsdayCombo", -4 + offsetX, -2 + offsetY, TragicItems.CelestialAegis, doomsday).registerStat().setSpecial();
	public static Achievement doomCritical = new Achievement("tragicmc.achievement.doomCritical", "tragicmc.doomCritical", offsetX, -4 + offsetY, TragicItems.BeastlyClaws, doom).registerStat();
	public static Achievement doomCooldown = new Achievement("tragicmc.achievement.doomCooldown", "tragicmc.doomCooldown", offsetX, -6 + offsetY, TragicItems.CooldownDefuse, doomCritical).registerStat();
	public static Achievement doomConsume = new Achievement("tragicmc.achievement.doomConsume", "tragicmc.doomConsume", -2 + offsetX, -4 + offsetY, TragicItems.DoomConsume, doomCritical).registerStat();

	//weapon/killing achievements
	public static Achievement weapon = new Achievement("tragicmc.achievement.weapon", "tragicmc.weapon", 2 + offsetX, 2 + offsetY, TragicItems.MercuryDagger, mercury).registerStat();
	public static Achievement kill = new Achievement("tragicmc.achievement.kill", "tragicmc.kill", 2 + offsetX, 4 + offsetY, TragicItems.Thorns, weapon).registerStat();
	public static Achievement killMiniBoss = new Achievement("tragicmc.achievement.killMiniBoss", "tragicmc.killMiniBoss", 4 + offsetX, 4 + offsetY, TragicItems.FrozenLightning, kill).registerStat();
	public static Achievement killBoss = new Achievement("tragicmc.achievement.killBoss", "tragicmc.killBoss", 4 + offsetX, 6 + offsetY, TragicItems.Thardus, killMiniBoss).registerStat();
	//public static Achievement upgradeWeapon; //Upgrade an eligible weapon to a new tier
	//public static Achievement killAscendent; //Kill a mob that has defense against ascendent weaponry

	//armor and basic enchantments achievements
	public static Achievement enchant = new Achievement("tragicmc.achievement.enchant", "tragicmc.enchant", offsetX, 4 + offsetY, TragicItems.Splinter, kill).registerStat();
	public static Achievement reach = new Achievement("tragicmc.achivement.reach", "tragicmc.reach", -2 + offsetX, 4 + offsetY, TragicItems.Butcher, enchant).registerStat();
	public static Achievement enchantArmor = new Achievement("tragicmc.achievement.enchantArmor", "tragicmc.enchantArmor", 2 + offsetX, 6 + offsetY, TragicItems.DarkHelm, kill).registerStat();
	public static Achievement fullSuit = new Achievement("tragicmc.achievement.fullSuit", "tragicmc.fullSuit", offsetX, 6 + offsetY, TragicItems.MercuryPlate, enchantArmor).registerStat();
	public static Achievement haxEngage = new Achievement("tragicmc.achievement.haxEngage", "tragicmc.haxEngage", -2 + offsetX, 6 + offsetY, TragicItems.OverlordHelm, fullSuit).registerStat().setSpecial();

	//jack and more enchantment achievements
	public static Achievement jack = new Achievement("tragicmc.achievement.jack", "tragicmc.jack", offsetX, 2 + offsetY, TragicItems.Jack, weapon).registerStat();
	public static Achievement combustion = new Achievement("tragicmc.achievement.combustion", "tragicmc.combustion", -2 + offsetX, 2 + offsetY, TragicItems.TungstenJack, jack).registerStat();
	public static Achievement veteran = new Achievement("tragicmc.achievement.veteran", "tragicmc.veteran", -4 + offsetX, 2 + offsetY, Items.diamond_pickaxe, combustion).registerStat();
	public static Achievement luminescence = new Achievement("tragicmc.achievement.luminescence", "tragicmc.luminescence", -6 + offsetX, 2 + offsetY, TragicItems.CelestialJack, veteran).registerStat();

	//talisman achievements
	public static Achievement talisman = new Achievement("tragicmc.achievement.talisman", "tragicmc.talisman", -2 + offsetX, offsetY, TragicItems.Talisman, thanks).registerStat();
	public static Achievement talismanSpecial = new Achievement("tragicmc.achievement.talismanSpecial", "tragicmc.talismanSpecial", -4 + offsetX, offsetY, TragicItems.MoonlightTalisman, talisman).registerStat();
	public static Achievement talismanFull = new Achievement("tragicmc.achievement.talismanFull", "tragicmc.talismanFull", -6 + offsetX, offsetY, TragicItems.SynthesisTalisman, talismanSpecial).registerStat().setSpecial();

	//random achievements
	public static Achievement goldenSushi = (Achievement) new Achievement("tragicmc.achievement.goldenSushi", "tragicmc.goldenSushi", -10 + offsetX, -14 + offsetY, TragicItems.GoldenSushi, null).registerStat().initIndependentStat();
	public static Achievement flight = (Achievement) new Achievement("tragicmc.achievement.flight", "tragicmc.flight", -8 + offsetX, -2 + offsetY, TragicItems.WingsOfLiberation, null).registerStat().initIndependentStat();
	public static Achievement disorientation = (Achievement) new Achievement("tragicmc.achievement.disorientation", "tragicmc.disorientation", -6 + offsetX, -14 + offsetY, TragicItems.Ash, null).registerStat().initIndependentStat();
	public static Achievement mobStatue = (Achievement) new Achievement("tragicmc.achievement.mobStatue", "tragicmc.mobStatue", -10 + offsetX, -2 + offsetY, TragicItems.MobStatue, null).registerStat().initIndependentStat();
	public static Achievement challengeScroll = (Achievement) new Achievement("tragicmc.achievement.challengeScroll", "tragicmc.challengeScroll", -8 + offsetX, -14 + offsetY, TragicItems.ChallengeScroll, null).registerStat().initIndependentStat();
	public static Achievement doomsdayScroll = (Achievement) new Achievement("tragicmc.achievement.doomsdayScroll", "tragicmc.doomsdayScroll", -8 + offsetX, -12 + offsetY, TragicItems.DoomsdayScroll, null).registerStat().initIndependentStat();
	public static Achievement mineXP = (Achievement) new Achievement("tragicmc.achievement.mineXP", "tragicmc.mineXP", -8 + offsetX, -10 + offsetY, Items.experience_bottle, null).registerStat().initIndependentStat();
	public static Achievement loot = (Achievement) new Achievement("tragicmc.achievement.loot", "tragicmc.loot", -8 + offsetX, -8 + offsetY, TragicItems.TungstenBoots, null).registerStat().initIndependentStat();
	public static Achievement loot2 = (Achievement) new Achievement("tragicmc.achievement.loot2", "tragicmc.loot2", -8 + offsetX, -6 + offsetY, TragicItems.LightHelm, loot).registerStat();
	public static Achievement systemCrash = (Achievement) new Achievement("tragicmc.achievement.systemCrash", "tragicmc.systemCrash", -6 + offsetX, -12 + offsetY, TragicItems.NanoBots, null).registerStat().initIndependentStat();
	public static Achievement divinity = (Achievement) new Achievement("tragicmc.achievement.divinity", "tragicmc.divinity", -8 + offsetX, -4 + offsetY, TragicItems.ArchangelFeather, null).registerStat().initIndependentStat();
	public static Achievement useOrb = (Achievement) new Achievement("tragicmc.achievement.useOrb", "tragicmc.useOrb", -10 + offsetX, -4 + offsetY, TragicItems.CryingObsidianOrb, null).registerStat().initIndependentStat();

	//mob achievements
	public static Achievement tragicNeko = (Achievement) new Achievement("tragicmc.achievement.tragicNeko", "tragicmc.tragicNeko", -14 + offsetX, -14 + offsetY, Blocks.tnt, null).registerStat().setSpecial().initIndependentStat();
	public static Achievement pumpkinhead = (Achievement) new Achievement("tragicmc.achievement.pumpkinhead", "tragicmc.pumpkinhead", -14 + offsetX, -12 + offsetY, Blocks.pumpkin, null).registerStat().initIndependentStat();
	public static Achievement norVox = (Achievement) new Achievement("tragicmc.achievement.norVox", "tragicmc.norVox", -14 + offsetX, -10 + offsetY, TragicBlocks.DarkStone, null).registerStat().initIndependentStat();
	public static Achievement psygote = (Achievement) new Achievement("tragicmc.achievement.psygote", "tragicmc.psygote", -14 + offsetX, -8 + offsetY, TragicItems.DarkParticles, null).registerStat().setSpecial().initIndependentStat();
	public static Achievement sirv = (Achievement) new Achievement("tragicmc.achievement.sirv", "tragicmc.sirv", -14 + offsetX, -6 + offsetY, Items.clay_ball, null).registerStat().initIndependentStat();
	public static Achievement ragr = (Achievement) new Achievement("tragicmc.achievement.ragr", "tragicmc.ragr", -14 + offsetX, -4 + offsetY, TragicBlocks.SmoothNetherrack, null).registerStat().initIndependentStat();
	public static Achievement kurayami = (Achievement) new Achievement("tragicmc.achievement.kurayami", "tragicmc.kurayami", -14 + offsetX, -2 + offsetY, TragicItems.KitsuneAmulet, null).registerStat().setSpecial().initIndependentStat();
	public static Achievement cantTouchThis = (Achievement) new Achievement("tragicmc.achievement.cantTouchThis", "tragicmc.cantTouchThis", -10 + offsetX, -12 + offsetY, TragicItems.AwakeningStone, null).registerStat().setSpecial().initIndependentStat();
	public static Achievement rewind = (Achievement) new Achievement("tragicmc.achievement.rewind", "tragicmc.rewind", -10 + offsetX, -10 + offsetY, TragicItems.LunarPowder, null).registerStat().initIndependentStat();
	public static Achievement aegar = (Achievement) new Achievement("tragicmc.achievement.aegar", "tragicmc.aegar", -12 + offsetX, -4 + offsetY, TragicItems.SynapseCrystal, null).registerStat().initIndependentStat();
	public static Achievement claymation2in1 = (Achievement) new Achievement("tragicmc.achievement.claymation2in1", "tragicmc.claymation2in1", -10 + offsetX, -8 + offsetY, TragicItems.ClaymationAmulet, null).registerStat().setSpecial().initIndependentStat();
	public static Achievement skultarImmune = (Achievement) new Achievement("tragicmc.achievement.skultarImmune", "tragicmc.skultarImmune", -10 + offsetX, -6 + offsetY, TragicItems.SkullHelmet, null).registerStat().initIndependentStat();
	public static Achievement minotaurSummon = (Achievement) new Achievement("tragicmc.achievement.minotaurSummon", "tragicmc.minotaurSummon", -12 + offsetX, -2 + offsetY, TragicItems.Horn, null).registerStat().setSpecial().initIndependentStat();
	public static Achievement seeker = (Achievement) new Achievement("tragicmc.achievement.seeker", "tragicmc.seeker", -12 + offsetX, -14 + offsetY, TragicItems.BlindingLight, null).registerStat().initIndependentStat();
	public static Achievement harvester = (Achievement) new Achievement("tragicmc.achievement.harvester", "tragicmc.harvester", -12 + offsetX, -12 + offsetY, Blocks.iron_block, null).registerStat().initIndependentStat();
	public static Achievement lockdown = (Achievement) new Achievement("tragicmc.achievement.lockbot", "tragicmc.lockbot", -12 + offsetX, -10 + offsetY, Blocks.anvil, null).registerStat().initIndependentStat();
	public static Achievement fusea = (Achievement) new Achievement("tragicmc.achievement.fusea", "tragicmc.fusea", -12 + offsetX, -8 + offsetY, TragicItems.UnstableIsotope, null).registerStat().initIndependentStat();
	public static Achievement ire = (Achievement) new Achievement("tragicmc.achievement.ire", "tragicmc.ire", -12 + offsetX, -6 + offsetY, TragicItems.IreNetParticleCannon, null).registerStat().initIndependentStat();
	public static Achievement avris = (Achievement) new Achievement("tragicmc.achievement.avris", "tragicmc.avris", -6 + offsetX, -10 + offsetY, TragicItems.Ruby, null).registerStat().initIndependentStat();
	public static Achievement professorNekoid = (Achievement) new Achievement("tragicmc.achievement.professorNekoid", "tragicmc.professorNekoid", -6 + offsetX, -8 + offsetY, TragicItems.MechaExo, null).registerStat().initIndependentStat();
	//public static Achievement slangLeader; //Kill a Slang Leader without it taking your item, "Beat the devil out of it!"
	//public static Achievement archangel; //Kill an Archangel while it is using it's holy beam on you, "Angel beats"
	//public static Achievement blist; //Kill a Blist without it suiciding, "Step away from that ledge, my friend"
	//public static Achievement thorg; //Kill a Thorg without being hurt by it's projectiles, "Thorg-a-sborg!"
	//public static Achievement trapper; //Kill a Trapper without it grabbing you, "Trapper no trapping!"
	//public static Achievement berserker; //Kill a Berserker after it fully transforms, "Let's go berserk!"
	//public static Achievement phoenix; //Kill all 3 phoenixes within a few seconds of each other, "3 birds, one very big stone"
	//public static Achievement lonelyShina; //Kill every wolf that Shina spawns and kill it while there are none around it, "Lone Wolf"
	//public static Achievement petrivNightmares; //Don't fail a single nightmare phase when you kill Petriv
	//public static Achievement cirelaUntouchable; //Kill C1R-314 without being hit by it

	//boss achievements
	public static Achievement apis = (Achievement) new Achievement("tragicmc.achievement.apis", "tragicmc.apis", -12 + offsetX, 2 + offsetY, TragicItems.PureLight, null).registerStat().initIndependentStat();
	public static Achievement skultar = (Achievement) new Achievement("tragicmc.achievement.skultar", "tragicmc.skultar", -12 + offsetX, 4 + offsetY, TragicItems.DeathlyHallow, null).registerStat().initIndependentStat();
	public static Achievement kitsunakuma = (Achievement) new Achievement("tragicmc.achievement.kitsunakuma", "tragicmc.kitsunakuma", -12 + offsetX, 6 + offsetY, TragicItems.KitsuneTail, null).registerStat().initIndependentStat();
	public static Achievement polaris = (Achievement) new Achievement("tragicmc.achievement.polaris", "tragicmc.polaris", -12 + offsetX, 8 + offsetY, TragicItems.StarPieces, null).registerStat().initIndependentStat();
	public static Achievement empariah = (Achievement) new Achievement("tragicmc.achievement.empariah", "tragicmc.empariah", -10 + offsetX, 2 + offsetY, TragicItems.EmpariahClaw, null).registerStat().initIndependentStat();
	public static Achievement timeController = (Achievement) new Achievement("tragicmc.achievement.timeController", "tragicmc.timeController", -10 + offsetX, 4 + offsetY, TragicItems.TimeEssence, null).registerStat().initIndependentStat();
	public static Achievement enyvil = (Achievement) new Achievement("tragicmc.achievement.enyvil", "tragicmc.enyvil", -10 + offsetX, 6 + offsetY, TragicItems.PureDarkness, null).registerStat().initIndependentStat();
	public static Achievement claymation = (Achievement) new Achievement("tragicmc.achievement.claymation", "tragicmc.claymation", -10 + offsetX, 8 + offsetY, TragicItems.LivingClay, null).registerStat().initIndependentStat();
	//public static Achievement shinaShewolf; //"Might've Shina coming!"
	//public static Achievement axyr; //"Axyr if she wants to dance."
	//public static Achievement petriv; //"Nightmares and a cold sweat"
	//public static Achievement amsheer; //"I Amsheer about it."
	//public static Achievement akhora; //"Night of the living dragon"
	//public static Achievement leviathan; //"Deep, blue sea"
	//public static Achievement polypus; //"Pulp fiction"
	//public static Achievement cirela; //"Round and round it goes"

	public static void load() {		
		AchievementPage page = new AchievementPage(TragicMC.MODID, thanks, mercury, obsidianOrb, dimensionalKey, collision, aeris, synapseLink, synapse, overlord, overlord2, overlord3, overlord4,
				tungsten, amulet, amuletEquip, amuletRelease, amuletMax, amuletSpecial, doom, doomsday, doomsdayCombo, doomCritical, doomCooldown, doomConsume, weapon, kill, killMiniBoss, killBoss,
				enchant, reach, enchantArmor, fullSuit, haxEngage, jack, combustion, veteran, luminescence, talisman, talismanSpecial, talismanFull, goldenSushi, flight, disorientation, mobStatue, challengeScroll,
				doomsdayScroll, mineXP, loot, loot2, systemCrash, divinity, useOrb, tragicNeko, pumpkinhead, norVox, psygote, sirv, ragr, kurayami, cantTouchThis, rewind, aegar, claymation2in1, skultarImmune,
				minotaurSummon, seeker, harvester, lockdown, fusea, ire, avris, tragicNekoDevice, tragicNekoRelease, tragicNekoFile, tragicNekoSiphon, tragicNekoWarpDrive, tragicNekoHomeworld,
				tragicNekoTrader, tragicNekoNekite, tragicNekoLauncher, tragicNekoSwordAndGun, tragicNekoDefeatNekoid, tragicNekoNekoidBlaster, tragicNekoInfluence, apis, skultar, kitsunakuma, polaris, empariah,
				timeController, enyvil, claymation, professorNekoid, tragicNeko100Release, tragicNekoAllFiles);
		AchievementPage.registerAchievementPage(page);
	}
}
