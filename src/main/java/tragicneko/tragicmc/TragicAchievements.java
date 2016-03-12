package tragicneko.tragicmc;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class TragicAchievements {
	
	private static final int offsetX = 0; //offsets to fix achievement page glitch if you go too far up or at least not make it as likely to occur
	private static final int offsetZ = 8;

	//main achievement line
	public static Achievement thanks = (Achievement) new Achievement("tragicmc.achievement.thanks", "tragicmc.thanks", offsetX, offsetZ, TragicItems.NekoNekoWand, (Achievement) null).func_180788_c().setSpecial().initIndependentStat();
	public static Achievement mercury = new Achievement("tragicmc.achievement.mercury", "tragicmc.mercury", 2 + offsetX, offsetZ, TragicItems.RedMercury, thanks).func_180788_c();
	public static Achievement obsidianOrb = new Achievement("tragicmc.achievement.obsidianOrb", "tragicmc.obsidianOrb", 2 + offsetX, -2 + offsetZ, TragicItems.BleedingObsidianOrb, mercury).func_180788_c();
	public static Achievement dimensionalKey = new Achievement("tragicmc.achievement.dimensionalKey", "tragicmc.dimensionalKey", 2 + offsetX, -4 + offsetZ, TragicItems.DimensionalKey, obsidianOrb).setSpecial().func_180788_c();
	public static Achievement collision = new Achievement("tragicmc.achievement.collision", "tragicmc.collision", 2 + offsetX, -6 + offsetZ, TragicItems.LightParticles, dimensionalKey).func_180788_c();
	public static Achievement aeris = new Achievement("tragicmc.achievement.aeris", "tragicmc.aeris", 2 + offsetX, -8 + offsetZ, TragicBlocks.Aeris, collision).func_180788_c();
	public static Achievement synapseLink = new Achievement("tragicmc.achievement.synapseLink", "tragicmc.synapseLink", 2 + offsetX, -10 + offsetZ, TragicItems.SynapseLink, aeris).setSpecial().func_180788_c();
	public static Achievement synapse = new Achievement("tragicmc.achievement.synapse", "tragicmc.synapse", 2 + offsetX, -12 + offsetZ, TragicBlocks.SynapseCore, synapseLink).func_180788_c();
	public static Achievement overlord = new Achievement("tragicmc.achievement.overlord", "tragicmc.overlord", 2 + offsetX, -14 + offsetZ, TragicItems.NanoBots, synapse).func_180788_c();
	public static Achievement overlord2 = new Achievement("tragicmc.achievement.overlord2", "tragicmc.overlord2", 4 + offsetX, -14 + offsetZ, TragicItems.SynapseCrystal, overlord).func_180788_c();
	public static Achievement overlord3 = new Achievement("tragicmc.achievement.overlord3", "tragicmc.overlord3", 6 + offsetX, -14 + offsetZ, TragicItems.CorruptedEye, overlord2).func_180788_c();
	public static Achievement overlord4 = new Achievement("tragicmc.achievement.overlord4", "tragicmc.overlord4", 8 + offsetX, -14 + offsetZ, TragicItems.Sentinel, overlord3).func_180788_c().setSpecial();
	
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
	public static Achievement tungsten = new Achievement("tragicmc.achievement.tungsten", "tragicmc.tungsten", 4 + offsetX, offsetZ, TragicItems.Tungsten, mercury).func_180788_c();
	public static Achievement amulet = new Achievement("tragicmc.achievement.amulet", "tragicmc.amulet", 6 + offsetX, offsetZ, TragicItems.BlacksmithAmulet, tungsten).func_180788_c();
	public static Achievement amuletEquip = new Achievement("tragicmc.achievement.amuletEquip", "tragicmc.amuletEquip", 6 + offsetX, -2 + offsetZ, TragicItems.CreeperAmulet, amulet).func_180788_c();
	public static Achievement amuletRelease = new Achievement("tragicmc.achievement.amuletRelease", "tragicmc.amuletRelease", 8 + offsetX, offsetZ, TragicItems.AmuletRelease, amulet).func_180788_c();
	public static Achievement amuletMax = new Achievement("tragicmc.achievement.amuletMax", "tragicmc.amuletMax", 10 + offsetX, offsetZ, TragicItems.ApisAmulet, amuletRelease).func_180788_c().setSpecial();
	public static Achievement amuletSpecial = new Achievement("tragicmc.achievement.amuletSpecial", "tragicmc.amuletSpecial", 6 + offsetX, -4 + offsetZ, TragicItems.KitsuneAmulet, amuletEquip).func_180788_c();
	
	//doom and doomsday achievements
	public static Achievement doom = new Achievement("tragicmc.achievement.doom", "tragicmc.doom", offsetX, -2 + offsetZ, TragicItems.GuiltyThorn, thanks).func_180788_c();
	public static Achievement doomsday = new Achievement("tragicmc.achievement.doomsday", "tragicmc.doomsday", -2 + offsetX, -2 + offsetZ, TragicItems.Titan, doom).func_180788_c();
	public static Achievement doomsdayCombo = new Achievement("tragicmc.achievement.doomsdayCombo", "tragicmc.doomsdayCombo", -4 + offsetX, -2 + offsetZ, TragicItems.CelestialAegis, doomsday).func_180788_c().setSpecial();
	public static Achievement doomCritical = new Achievement("tragicmc.achievement.doomCritical", "tragicmc.doomCritical", offsetX, -4 + offsetZ, TragicItems.BeastlyClaws, doom).func_180788_c();
	public static Achievement doomCooldown = new Achievement("tragicmc.achievement.doomCooldown", "tragicmc.doomCooldown", offsetX, -6 + offsetZ, TragicItems.CooldownDefuse, doomCritical).func_180788_c();
	public static Achievement doomConsume = new Achievement("tragicmc.achievement.doomConsume", "tragicmc.doomConsume", -2 + offsetX, -4 + offsetZ, TragicItems.DoomConsume, doomCritical).func_180788_c();
	
	//weapon/killing achievements
	public static Achievement weapon = new Achievement("tragicmc.achievement.weapon", "tragicmc.weapon", 2 + offsetX, 2 + offsetZ, TragicItems.MercuryDagger, mercury).func_180788_c();
	public static Achievement kill = new Achievement("tragicmc.achievement.kill", "tragicmc.kill", 2 + offsetX, 4 + offsetZ, TragicItems.Thorns, weapon).func_180788_c();
	public static Achievement killMiniBoss = new Achievement("tragicmc.achievement.killMiniBoss", "tragicmc.killMiniBoss", 4 + offsetX, 4 + offsetZ, TragicItems.FrozenLightning, kill).func_180788_c();
	public static Achievement killBoss = new Achievement("tragicmc.achievement.killBoss", "tragicmc.killBoss", 4 + offsetX, 6 + offsetZ, TragicItems.Thardus, killMiniBoss).func_180788_c();
	//public static Achievement upgradeWeapon; //Upgrade an eligible weapon to a new tier
	//public static Achievement killAscendent; //Kill a mob that has defense against ascendent weaponry
	
	//armor and basic enchantments achievements
	public static Achievement enchant = new Achievement("tragicmc.achievement.enchant", "tragicmc.enchant", offsetX, 4 + offsetZ, TragicItems.Splinter, kill).func_180788_c();
	public static Achievement reach = new Achievement("tragicmc.achivement.reach", "tragicmc.reach", -2 + offsetX, 4 + offsetZ, TragicItems.Butcher, enchant).func_180788_c();
	public static Achievement enchantArmor = new Achievement("tragicmc.achievement.enchantArmor", "tragicmc.enchantArmor", 2 + offsetX, 6 + offsetZ, TragicItems.DarkHelm, kill).func_180788_c();
	public static Achievement fullSuit = new Achievement("tragicmc.achievement.fullSuit", "tragicmc.fullSuit", offsetX, 6 + offsetZ, TragicItems.MercuryPlate, enchantArmor).func_180788_c();
	public static Achievement haxEngage = new Achievement("tragicmc.achievement.haxEngage", "tragicmc.haxEngage", -2 + offsetX, 6 + offsetZ, TragicItems.OverlordHelm, fullSuit).func_180788_c().setSpecial();
	
	//jack and more enchantment achievements
	public static Achievement jack = new Achievement("tragicmc.achievement.jack", "tragicmc.jack", offsetX, 2 + offsetZ, TragicItems.Jack, weapon).func_180788_c();
	public static Achievement combustion = new Achievement("tragicmc.achievement.combustion", "tragicmc.combustion", -2 + offsetX, 2 + offsetZ, TragicItems.TungstenJack, jack).func_180788_c();
	public static Achievement veteran = new Achievement("tragicmc.achievement.veteran", "tragicmc.veteran", -4 + offsetX, 2 + offsetZ, Items.diamond_pickaxe, combustion).func_180788_c();
	public static Achievement luminescence = new Achievement("tragicmc.achievement.luminescence", "tragicmc.luminescence", -6 + offsetX, 2 + offsetZ, TragicItems.CelestialJack, veteran).func_180788_c();
	
	//talisman achievements
	public static Achievement talisman = new Achievement("tragicmc.achievement.talisman", "tragicmc.talisman", -2 + offsetX, offsetZ, TragicItems.Talisman, thanks).func_180788_c();
	public static Achievement talismanSpecial = new Achievement("tragicmc.achievement.talismanSpecial", "tragicmc.talismanSpecial", -4 + offsetX, offsetZ, TragicItems.MoonlightTalisman, talisman).func_180788_c();
	public static Achievement talismanFull = new Achievement("tragicmc.achievement.talismanFull", "tragicmc.talismanFull", -6 + offsetX, offsetZ, TragicItems.SynthesisTalisman, talismanSpecial).func_180788_c().setSpecial();
	
	//random achievements
	public static Achievement goldenSushi = (Achievement) new Achievement("tragicmc.achievement.goldenSushi", "tragicmc.goldenSushi", -10 + offsetX, -14 + offsetZ, TragicItems.GoldenSushi, null).func_180788_c().initIndependentStat();
	public static Achievement flight = (Achievement) new Achievement("tragicmc.achievement.flight", "tragicmc.flight", -8 + offsetX, -2 + offsetZ, TragicItems.WingsOfLiberation, null).func_180788_c().initIndependentStat();
	public static Achievement disorientation = (Achievement) new Achievement("tragicmc.achievement.disorientation", "tragicmc.disorientation", -6 + offsetX, -14 + offsetZ, TragicItems.Ash, null).func_180788_c().initIndependentStat();
	public static Achievement mobStatue = (Achievement) new Achievement("tragicmc.achievement.mobStatue", "tragicmc.mobStatue", -10 + offsetX, -2 + offsetZ, TragicItems.MobStatue, null).func_180788_c().initIndependentStat();
	public static Achievement challengeScroll = (Achievement) new Achievement("tragicmc.achievement.challengeScroll", "tragicmc.challengeScroll", -8 + offsetX, -14 + offsetZ, TragicItems.ChallengeScroll, null).func_180788_c().initIndependentStat();
	public static Achievement doomsdayScroll = (Achievement) new Achievement("tragicmc.achievement.doomsdayScroll", "tragicmc.doomsdayScroll", -8 + offsetX, -12 + offsetZ, TragicItems.DoomsdayScroll, null).func_180788_c().initIndependentStat();
	public static Achievement mineXP = (Achievement) new Achievement("tragicmc.achievement.mineXP", "tragicmc.mineXP", -8 + offsetX, -10 + offsetZ, Items.experience_bottle, null).func_180788_c().initIndependentStat();
	public static Achievement loot = (Achievement) new Achievement("tragicmc.achievement.loot", "tragicmc.loot", -8 + offsetX, -8 + offsetZ, TragicItems.TungstenBoots, null).func_180788_c().initIndependentStat();
	public static Achievement loot2 = (Achievement) new Achievement("tragicmc.achievement.loot2", "tragicmc.loot2", -8 + offsetX, -6 + offsetZ, TragicItems.LightHelm, loot).func_180788_c();
	public static Achievement systemCrash = (Achievement) new Achievement("tragicmc.achievement.systemCrash", "tragicmc.systemCrash", -6 + offsetX, -12 + offsetZ, TragicItems.NanoBots, null).func_180788_c().initIndependentStat();
	public static Achievement divinity = (Achievement) new Achievement("tragicmc.achievement.divinity", "tragicmc.divinity", -8 + offsetX, -4 + offsetZ, TragicItems.ArchangelFeather, null).func_180788_c().initIndependentStat();
	public static Achievement useOrb = (Achievement) new Achievement("tragicmc.achievement.useOrb", "tragicmc.useOrb", -10 + offsetX, -4 + offsetZ, TragicItems.CryingObsidianOrb, null).func_180788_c().initIndependentStat();
	
	//mob achievements
	public static Achievement tragicNeko = (Achievement) new Achievement("tragicmc.achievement.tragicNeko", "tragicmc.tragicNeko", -14 + offsetX, -14 + offsetZ, Blocks.tnt, null).func_180788_c().setSpecial().initIndependentStat();
	public static Achievement pumpkinhead = (Achievement) new Achievement("tragicmc.achievement.pumpkinhead", "tragicmc.pumpkinhead", -14 + offsetX, -12 + offsetZ, Blocks.pumpkin, null).func_180788_c().initIndependentStat();
	public static Achievement norVox = (Achievement) new Achievement("tragicmc.achievement.norVox", "tragicmc.norVox", -14 + offsetX, -10 + offsetZ, TragicBlocks.DarkStone, null).func_180788_c().initIndependentStat();
	public static Achievement psygote = (Achievement) new Achievement("tragicmc.achievement.psygote", "tragicmc.psygote", -14 + offsetX, -8 + offsetZ, TragicItems.DarkParticles, null).func_180788_c().setSpecial().initIndependentStat();
	public static Achievement sirv = (Achievement) new Achievement("tragicmc.achievement.sirv", "tragicmc.sirv", -14 + offsetX, -6 + offsetZ, Items.clay_ball, null).func_180788_c().initIndependentStat();
	public static Achievement ragr = (Achievement) new Achievement("tragicmc.achievement.ragr", "tragicmc.ragr", -14 + offsetX, -4 + offsetZ, TragicBlocks.SmoothNetherrack, null).func_180788_c().initIndependentStat();
	public static Achievement kurayami = (Achievement) new Achievement("tragicmc.achievement.kurayami", "tragicmc.kurayami", -14 + offsetX, -2 + offsetZ, TragicItems.KitsuneAmulet, null).func_180788_c().setSpecial().initIndependentStat();
	public static Achievement cantTouchThis = (Achievement) new Achievement("tragicmc.achievement.cantTouchThis", "tragicmc.cantTouchThis", -10 + offsetX, -12 + offsetZ, TragicItems.AwakeningStone, null).func_180788_c().setSpecial().initIndependentStat();
	public static Achievement rewind = (Achievement) new Achievement("tragicmc.achievement.rewind", "tragicmc.rewind", -10 + offsetX, -10 + offsetZ, TragicItems.LunarPowder, null).func_180788_c().initIndependentStat();
	public static Achievement aegar = (Achievement) new Achievement("tragicmc.achievement.aegar", "tragicmc.aegar", -12 + offsetX, -4 + offsetZ, TragicItems.SynapseCrystal, null).func_180788_c().initIndependentStat();
	public static Achievement claymation2in1 = (Achievement) new Achievement("tragicmc.achievement.claymation2in1", "tragicmc.claymation2in1", -10 + offsetX, -8 + offsetZ, TragicItems.ClaymationAmulet, null).func_180788_c().setSpecial().initIndependentStat();
	public static Achievement skultarImmune = (Achievement) new Achievement("tragicmc.achievement.skultarImmune", "tragicmc.skultarImmune", -10 + offsetX, -6 + offsetZ, TragicItems.SkullHelmet, null).func_180788_c().initIndependentStat();
	public static Achievement minotaurSummon = (Achievement) new Achievement("tragicmc.achievement.minotaurSummon", "tragicmc.minotaurSummon", -12 + offsetX, -2 + offsetZ, TragicItems.Horn, null).func_180788_c().setSpecial().initIndependentStat();
	public static Achievement seeker = (Achievement) new Achievement("tragicmc.achievement.seeker", "tragicmc.seeker", -12 + offsetX, -14 + offsetZ, TragicItems.BlindingLight, null).func_180788_c().initIndependentStat();
	public static Achievement harvester = (Achievement) new Achievement("tragicmc.achievement.harvester", "tragicmc.harvester", -12 + offsetX, -12 + offsetZ, Blocks.iron_block, null).func_180788_c().initIndependentStat();
	public static Achievement lockdown = (Achievement) new Achievement("tragicmc.achievement.lockbot", "tragicmc.lockbot", -12 + offsetX, -10 + offsetZ, Blocks.anvil, null).func_180788_c().initIndependentStat();
	public static Achievement fusea = (Achievement) new Achievement("tragicmc.achievement.fusea", "tragicmc.fusea", -12 + offsetX, -8 + offsetZ, TragicItems.UnstableIsotope, null).func_180788_c().initIndependentStat();
	public static Achievement ire = (Achievement) new Achievement("tragicmc.achievement.ire", "tragicmc.ire", -12 + offsetX, -6 + offsetZ, TragicItems.IreNetParticleCannon, null).func_180788_c().initIndependentStat();
	public static Achievement avris = (Achievement) new Achievement("tragicmc.achievement.avris", "tragicmc.avris", -6 + offsetX, -10 + offsetZ, TragicItems.Ruby, null).func_180788_c().initIndependentStat();
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
	
	//Neko side expansion achievements
	public static Achievement tragicNekoDevice = (Achievement) new Achievement("tragicmc.achievement.tragicNekoDevice", "tragicmc.tragicNekoDevice", -4 + offsetX, 10 + offsetZ, TragicItems.NekoMindControlDevice, null).func_180788_c().initIndependentStat();
	public static Achievement tragicNekoRelease = (Achievement) new Achievement("tragicmc.achievement.tragicNekoRelease", "tragicmc.tragicNekoRelease", -2 + offsetX, 10 + offsetZ, TragicItems.RecaptureSiphon, tragicNekoDevice).func_180788_c().setSpecial();
	public static Achievement tragicNekoFile = (Achievement) new Achievement("tragicmc.achievement.tragicNekoFile", "tragicmc.tragicNekoFile",-2 + offsetX, 8 + offsetZ, TragicItems.Starstruck, tragicNekoRelease).func_180788_c();
	//public static Achievement tragicNekoRecapture = (Achievement) new Achievement("tragicmc.achievement.tragicNekoRecapture", "tragicmc.tragicNekoRecapture",-2 + offsetX, 12 + offsetZ, TragicItems.ComplexCircuitry, tragicNekoRelease).func_180788_c();
	
	//boss achievements
	public static Achievement apis = (Achievement) new Achievement("tragicmc.achievement.apis", "tragicmc.apis", -12 + offsetX, 2 + offsetZ, TragicItems.PureLight, null).func_180788_c().initIndependentStat();
	public static Achievement skultar = (Achievement) new Achievement("tragicmc.achievement.skultar", "tragicmc.skultar", -12 + offsetX, 4 + offsetZ, TragicItems.DeathlyHallow, null).func_180788_c().initIndependentStat();
	public static Achievement kitsunakuma = (Achievement) new Achievement("tragicmc.achievement.kitsunakuma", "tragicmc.kitsunakuma", -12 + offsetX, 6 + offsetZ, TragicItems.KitsuneTail, null).func_180788_c().initIndependentStat();
	public static Achievement polaris = (Achievement) new Achievement("tragicmc.achievement.polaris", "tragicmc.polaris", -12 + offsetX, 8 + offsetZ, TragicItems.StarPieces, null).func_180788_c().initIndependentStat();
	public static Achievement empariah = (Achievement) new Achievement("tragicmc.achievement.empariah", "tragicmc.empariah", -10 + offsetX, 2 + offsetZ, TragicItems.EmpariahClaw, null).func_180788_c().initIndependentStat();
	public static Achievement timeController = (Achievement) new Achievement("tragicmc.achievement.timeController", "tragicmc.timeController", -10 + offsetX, 4 + offsetZ, TragicItems.TimeEssence, null).func_180788_c().initIndependentStat();
	public static Achievement enyvil = (Achievement) new Achievement("tragicmc.achievement.enyvil", "tragicmc.enyvil", -10 + offsetX, 6 + offsetZ, TragicItems.PureDarkness, null).func_180788_c().initIndependentStat();
	public static Achievement claymation = (Achievement) new Achievement("tragicmc.achievement.claymation", "tragicmc.claymation", -10 + offsetX, 8 + offsetZ, TragicItems.LivingClay, null).func_180788_c().initIndependentStat();
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
				minotaurSummon, seeker, harvester, lockdown, fusea, ire, avris, tragicNekoDevice, tragicNekoRelease, tragicNekoFile, apis, skultar, kitsunakuma, polaris, empariah, timeController, enyvil, claymation);
		AchievementPage.registerAchievementPage(page);
	}
}
