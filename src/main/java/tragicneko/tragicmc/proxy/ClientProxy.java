package tragicneko.tragicmc.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.tileentity.TileEntitySoulChest;
import tragicneko.tragicmc.client.gui.GuiAmuletStatus;
import tragicneko.tragicmc.client.gui.GuiDoom;
import tragicneko.tragicmc.client.model.ModelAbomination;
import tragicneko.tragicmc.client.model.ModelArchangel;
import tragicneko.tragicmc.client.model.ModelAvris;
import tragicneko.tragicmc.client.model.ModelGragul;
import tragicneko.tragicmc.client.model.ModelGreaterStin;
import tragicneko.tragicmc.client.model.ModelHarvester;
import tragicneko.tragicmc.client.model.ModelHunter;
import tragicneko.tragicmc.client.model.ModelInkling;
import tragicneko.tragicmc.client.model.ModelIre;
import tragicneko.tragicmc.client.model.ModelJarra;
import tragicneko.tragicmc.client.model.ModelJetNeko;
import tragicneko.tragicmc.client.model.ModelKragul;
import tragicneko.tragicmc.client.model.ModelKurayami;
import tragicneko.tragicmc.client.model.ModelLockbot;
import tragicneko.tragicmc.client.model.ModelMinotaur;
import tragicneko.tragicmc.client.model.ModelNanoSwarm;
import tragicneko.tragicmc.client.model.ModelPlague;
import tragicneko.tragicmc.client.model.ModelPsygote;
import tragicneko.tragicmc.client.model.ModelRanmas;
import tragicneko.tragicmc.client.model.ModelSeeker;
import tragicneko.tragicmc.client.model.ModelSirv;
import tragicneko.tragicmc.client.model.ModelStinKing;
import tragicneko.tragicmc.client.model.ModelStinQueen;
import tragicneko.tragicmc.client.model.ModelTox;
import tragicneko.tragicmc.client.model.ModelTragicNeko;
import tragicneko.tragicmc.client.model.armor.ModelDarkArmor;
import tragicneko.tragicmc.client.model.armor.ModelLightArmor;
import tragicneko.tragicmc.client.model.armor.ModelOverlordArmor;
import tragicneko.tragicmc.client.render.RenderDarkCrystal;
import tragicneko.tragicmc.client.render.RenderDimensionalAnomaly;
import tragicneko.tragicmc.client.render.RenderDirectedLightning;
import tragicneko.tragicmc.client.render.RenderGuardianShield;
import tragicneko.tragicmc.client.render.RenderLargeRock;
import tragicneko.tragicmc.client.render.RenderLock;
import tragicneko.tragicmc.client.render.RenderNuke;
import tragicneko.tragicmc.client.render.RenderSoulChest;
import tragicneko.tragicmc.client.render.RenderStatue;
import tragicneko.tragicmc.client.render.RenderTimeDisruption;
import tragicneko.tragicmc.client.render.alpha.RenderOverlordCocoon;
import tragicneko.tragicmc.client.render.alpha.RenderOverlordCombat;
import tragicneko.tragicmc.client.render.alpha.RenderOverlordCore;
import tragicneko.tragicmc.client.render.boss.RenderAegar;
import tragicneko.tragicmc.client.render.boss.RenderApis;
import tragicneko.tragicmc.client.render.boss.RenderClaymation;
import tragicneko.tragicmc.client.render.boss.RenderDeathReaper;
import tragicneko.tragicmc.client.render.boss.RenderEnyvil;
import tragicneko.tragicmc.client.render.boss.RenderKitsune;
import tragicneko.tragicmc.client.render.boss.RenderMegaCryse;
import tragicneko.tragicmc.client.render.boss.RenderPolaris;
import tragicneko.tragicmc.client.render.boss.RenderTimeController;
import tragicneko.tragicmc.client.render.boss.RenderVoxStellarum;
import tragicneko.tragicmc.client.render.boss.RenderYeti;
import tragicneko.tragicmc.client.render.factory.RenderFactoryMob;
import tragicneko.tragicmc.client.render.factory.RenderFactoryMobTransparent;
import tragicneko.tragicmc.client.render.factory.RenderFactoryProjectile;
import tragicneko.tragicmc.client.render.mob.RenderCryse;
import tragicneko.tragicmc.client.render.mob.RenderErkel;
import tragicneko.tragicmc.client.render.mob.RenderFusea;
import tragicneko.tragicmc.client.render.mob.RenderJabba;
import tragicneko.tragicmc.client.render.mob.RenderNorVox;
import tragicneko.tragicmc.client.render.mob.RenderPirah;
import tragicneko.tragicmc.client.render.mob.RenderPumpkinhead;
import tragicneko.tragicmc.client.render.mob.RenderRagr;
import tragicneko.tragicmc.client.render.mob.RenderStin;
import tragicneko.tragicmc.client.render.mob.RenderTox;
import tragicneko.tragicmc.client.render.mob.RenderWisp;
import tragicneko.tragicmc.dimension.SynapseSkyRenderer;
import tragicneko.tragicmc.dimension.TragicSkyRenderer;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.EntityDarkCrystal;
import tragicneko.tragicmc.entity.EntityDimensionalAnomaly;
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.entity.EntityKurayami;
import tragicneko.tragicmc.entity.EntityLock;
import tragicneko.tragicmc.entity.EntityNuke;
import tragicneko.tragicmc.entity.EntityStatue;
import tragicneko.tragicmc.entity.EntityTimeDisruption;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCocoon;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;
import tragicneko.tragicmc.entity.boss.EntityAegar;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.miniboss.EntityGreaterStin;
import tragicneko.tragicmc.entity.miniboss.EntityJarra;
import tragicneko.tragicmc.entity.miniboss.EntityKragul;
import tragicneko.tragicmc.entity.miniboss.EntityMagmox;
import tragicneko.tragicmc.entity.miniboss.EntityMegaCryse;
import tragicneko.tragicmc.entity.miniboss.EntityStinKing;
import tragicneko.tragicmc.entity.miniboss.EntityStinQueen;
import tragicneko.tragicmc.entity.miniboss.EntityVolatileFusea;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityArchangel;
import tragicneko.tragicmc.entity.mob.EntityAvris;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityErkel;
import tragicneko.tragicmc.entity.mob.EntityFusea;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityHarvester;
import tragicneko.tragicmc.entity.mob.EntityHunter;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityIre;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityJetNeko;
import tragicneko.tragicmc.entity.mob.EntityLockbot;
import tragicneko.tragicmc.entity.mob.EntityMinotaur;
import tragicneko.tragicmc.entity.mob.EntityNanoSwarm;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.entity.mob.EntityParasmite;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityPsygote;
import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.entity.mob.EntityRanmas;
import tragicneko.tragicmc.entity.mob.EntitySeeker;
import tragicneko.tragicmc.entity.mob.EntitySirv;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.entity.projectile.EntityBanana;
import tragicneko.tragicmc.entity.projectile.EntityCrystalMortor;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.entity.projectile.EntityDarkLightning;
import tragicneko.tragicmc.entity.projectile.EntityDarkMortor;
import tragicneko.tragicmc.entity.projectile.EntityGuardianShield;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntityIreEnergy;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.entity.projectile.EntityOverlordMortor;
import tragicneko.tragicmc.entity.projectile.EntityPitchBlack;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.entity.projectile.EntitySpore;
import tragicneko.tragicmc.entity.projectile.EntityStarShard;
import tragicneko.tragicmc.entity.projectile.EntityThrowingRock;
import tragicneko.tragicmc.entity.projectile.EntityTimeBomb;
import tragicneko.tragicmc.entity.projectile.EntityWebBomb;
import tragicneko.tragicmc.events.ClientEvents;
import tragicneko.tragicmc.events.MouseEvents;
import tragicneko.tragicmc.events.SoundsEvents;
import tragicneko.tragicmc.items.amulet.ItemAmulet;
import tragicneko.tragicmc.items.amulet.ItemAmulet.EnumAmuletType;
import tragicneko.tragicmc.util.TragicEntityList;
import tragicneko.tragicmc.util.TragicEntityList.EntityEggInfo;
import tragicneko.tragicmc.util.TragicEntityList.EnumEggType;

public class ClientProxy extends CommonProxy {

	public static final String DOMAIN = "tragicmc:";

	public static KeyBinding useSpecial = new KeyBinding("Special Use", Keyboard.KEY_R, TragicMC.MODNAME);
	public static KeyBinding openAmuletGui = new KeyBinding("Open Amulet Gui", Keyboard.KEY_Y, TragicMC.MODNAME);

	public static final ModelOverlordArmor[] modelsOverlord = new ModelOverlordArmor[] {new ModelOverlordArmor(0), new ModelOverlordArmor(1),
			new ModelOverlordArmor(2), new ModelOverlordArmor(3)};
	public static final ModelLightArmor[] modelsLight = new ModelLightArmor[] {new ModelLightArmor(0), new ModelLightArmor(1),
			new ModelLightArmor(2), new ModelLightArmor(3)};
	public static final ModelDarkArmor[] modelsDark = new  ModelDarkArmor[] {new ModelDarkArmor(0), new ModelDarkArmor(1),
			new ModelDarkArmor(2), new ModelDarkArmor(3)};

	public static final IRenderHandler collisionSkyRenderer = new TragicSkyRenderer();
	public static final IRenderHandler synapseSkyRenderer = new SynapseSkyRenderer();

	private static final int ZERO = 0;

	@Override
	public void initRenders()
	{
		Minecraft mc = Minecraft.getMinecraft();

		//Gui event registration
		if (TragicConfig.showDoomGui) TragicMC.registerEvent(new GuiDoom(mc));
		if (TragicConfig.showAmuletStatusGui) TragicMC.registerEvent(new GuiAmuletStatus(mc));

		//Keybinding registrations
		ClientRegistry.registerKeyBinding(useSpecial);
		ClientRegistry.registerKeyBinding(openAmuletGui);

		//Client-side event registration
		TragicMC.registerEvent(new ClientEvents());
		TragicMC.registerEvent(new MouseEvents(mc));

		//Music
		TragicMC.registerEvent(new SoundsEvents(mc));

		//Tile Entity render registration (shouldn't be used too often)
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulChest.class, new RenderSoulChest());
	}

	private static final String[] compactOre = new String[] {"rubyBlock", "sapphireBlock", "tungstenBlock", "mercuryBlock", "quicksilverBlock"};
	private static final String[] summonBlock = new String[] {"witherSummon", "enderDragonSummon", "apisSummon", "deathReaperSummon", "kitsuneSummon", "polarisSummon", "yetiSummon", "timeControllerSummon",
			"enyvilSummon", "claymationSummon", "aegarSummon"};
	private static final String[] quicksand = new String[] {"quicksand", "mud", "drudge", "sludge"};
	private static final String[] darkStone = new String[] {"darkStoneBlack", "darkStoneRed", "darkStoneGreen", "darkStoneTeal", "darkStoneBrown", "darkStoneViolet", "darkStoneNavy",
			"darkStoneBlackBeveled", "darkStoneRedBeveled", "darkStoneGreenBeveled", "darkStoneTealBeveled", "darkStoneBrownBeveled", "darkStoneVioletBeveled", "darkStoneNavyBeveled"};
	private static final String[] darkCobble = new String[] {"darkCobblestone", "darkCobblestoneHot", "darkCobblestoneToxic", "darkCobblestoneAshen"};
	private static final String[] lightCobble = new String[] {"lightCobblestone", "lightCobblestoneFrozen", "lightCobblestoneGlowing"};
	private static final String[] obsidian = new String[] {"cryingObsidian", "bleedingObsidian", "dyingObsidian"};
	private static final String[] deadDirt = new String[] {"deadDirt", "deadDirtRugged", "deadDirtMixed"};
	private static final String[] darkSandstone = new String[] {"darkSandstone", "darkSandstoneSmooth", "darkSandstoneBricked", "darkSandstoneChiseled", "darkSandstoneGridded",
			"darkSandstoneCarved"};
	private static final String[] ores = new String[] {"oreMercury", "oreTungsten", "oreRuby", "oreSapphire", "oreLapis", "oreDiamond", "oreEmerald", "oreGold",
			"oreIron", "oreCoal", "oreXP"};
	private static final String[] boneBlock = new String[] {"boneBlock", "boneBlockRotten"};
	private static final String[] smoothNetherrack = new String[] {"smoothNetherrack", "smoothNetherrackChiseled", "smoothNetherrackBeveled", "smoothNetherrackSculpted", "smoothNetherrackFoxtail",
			"smoothNetherrackMolten"};
	private static final String[] saplings = new String[] {"saplingPainted", "saplingBleached", "saplingAshen", "saplingHallowed", "saplingDarkwood"};
	private static final String[] flowers = new String[] {"blueSpiranthes", "pinkSpiranthes", "redSpiranthes", "whiteSpiranthes", "blueCoral", "redCoral", "pinkGinger", "redGinger",
			"bluebonnet", "violetSage", "pinkSage", "whiteSage", "birdOfParadise", "juniperBush", "stapelia", "thistle"};
	private static final String[] flowers2 = new String[] {"bramble", "tangleweed", "deathClaw", "fusche", "osiris", "thusk", "podtail", "fanbrush", "torchweed",
			"halon", "rizaphora", "blackSpot", "nannon", "barbedWire", "kern", "flahgrass"};
	private static String[] colors = new String[] {"white", "orange", "magenta", "lightBlue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown",
			"green", "red", "black"};
	private static String[] erodedStone = new String[] {"erodedStone", "erodedStoneCarved", "erodedStoneScattered"};
	private static String[] darkenedQuartz = new String[] {"darkenedQuartz", "darkenedQuartzChiseled", "darkenedQuartzPillared"};
	private static String[] circuitBlock = new String[] {"circuitLive", "circuitDamaged", "circuitVeryDamaged", "circuitAged", "circuitDead"};
	private static String[] aeris = new String[] {"aerisPure", "aerisCorrupting", "aerisCorrupt"};
	private static String[] permafrost = new String[] {"permafrost", "permafrostCracked"};
	private static String[] corsin = new String[] {"corsin", "corsinFaded", "corsinBrick", "corsinFadedBrick", "corsinCircle", "corsinCelled", "corsinScarred", "corsinCrystal", "corsinCrystalWrap"};

	private static final String[] spawnEggs = new String[] {"spawnEgg", "spawnEgg2", "spawnEgg3", "spawnEgg4", "spawnEgg5"};
	private static final String[] huntersBow = new String[] {"huntersBow", "huntersBow1", "huntersBow2", "huntersBow3"};
	private static final String[] celestialLongbow = new String[] {"celestialLongbow", "celestialLongbow1", "celestialLongbow2", "celestialLongbow3"};
	private static final String[] everlastingLight = new String[] {"everlastingLight", "everlastingLight1", "everlastingLight2", "everlastingLight3"};
	private static final String[] challengeScroll = new String[] {"challengeScroll", "challengeScrollInProgress", "challengeScrollComplete"};
	private static final String[] amulets = new String[] {"amulet", "amulet2", "amulet3", "amulet4"}; 
	private static final String[] projectile = new String[] {"rock", "lavaRock", "pumpkinbomb", "largePumpkinbomb",
			"poisonBarb", "nekoRocket", "nekoStickyBomb", "nekoClusterBomb", "nekoMiniBomb", "solarBomb",
			"spiritCast", "spore", "banana", "largeRock", "icicle", "timeBomb", "starShard", "darkLightning",
			"pitchBlack", "darkEnergy", "darkMortor", "webBomb", "crystalMortor", "overlordMortor", "ireEnergy"};
	private static final String[] generator = new String[] {"voidPitGenerator", "spikeGenerator", "starCrystalGenerator", "sphereGenerator",
			"sphereEraser", "liquidRemover", "treeGenerator", "lightningSummoner", "explosionGenerator", "isleGenerator", "directedLightningSummoner",
			"pitGenerator"};
	private static final String[] statue = new String[] {"apis", "kitsune", "deathReaper", "timeController", "yeti", "polaris", "jarra", "kragul",
			"magmox", "megaCryse", "stinKing", "stinQueen", "greaterStin", "voxStellarum", "enyvil", "claymation", "aegar", "overlord", "overlordCombat",
			"overlordCocoon"};

	@Override
	public void preInitRenders() {

		//Projectile renders
		final Item item = TragicItems.Projectile;
		registerRender(EntityThrowingRock.class, new RenderFactoryProjectile(item, 0, 1F));
		registerRender(EntityPumpkinbomb.class, new RenderFactoryProjectile(item, 2, 1F));
		registerRender(EntityLargePumpkinbomb.class, new RenderFactoryProjectile(item, 3, 1F));
		registerRender(EntityPoisonBarb.class, new RenderFactoryProjectile(item, 4, 1F));
		registerRender(EntityNekoRocket.class, new RenderFactoryProjectile(item, 5, 1F));
		registerRender(EntityNekoStickyBomb.class, new RenderFactoryProjectile(item, 6, 1F));
		registerRender(EntityNekoClusterBomb.class, new RenderFactoryProjectile(item, 7, 1F));
		registerRender(EntityNekoMiniBomb.class, new RenderFactoryProjectile(item, 8, 1F));
		registerRender(EntitySolarBomb.class, new RenderFactoryProjectile(item, 9, 1F));
		registerRender(EntitySpiritCast.class, new RenderFactoryProjectile(item, 10, 1F));
		registerRender(EntitySpore.class, new RenderFactoryProjectile(item, 11, 1F));
		registerRender(EntityBanana.class, new RenderFactoryProjectile(item, 12, 1F));
		registerRender(EntityLargeRock.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderLargeRock(manager); }});
		registerRender(EntityIcicle.class, new RenderFactoryProjectile(item, 14, 1F));
		registerRender(EntityTimeBomb.class, new RenderFactoryProjectile(item, 15, 1F));
		registerRender(EntityStarShard.class, new RenderFactoryProjectile(item, 16, 1F));
		registerRender(EntityDarkLightning.class, new RenderFactoryProjectile(item, 17, 1F));
		registerRender(EntityPitchBlack.class, new RenderFactoryProjectile(item, 18, 1F));
		registerRender(EntityDarkEnergy.class, new RenderFactoryProjectile(item, 19, 1F));
		registerRender(EntityDarkMortor.class, new RenderFactoryProjectile(item, 20, 1F));
		registerRender(EntityWebBomb.class, new RenderFactoryProjectile(item, 21, 1F));
		registerRender(EntityCrystalMortor.class, new RenderFactoryProjectile(item, 22, 1F));
		registerRender(EntityOverlordMortor.class, new RenderFactoryProjectile(item, 23, 1F));
		registerRender(EntityIreEnergy.class, new RenderFactoryProjectile(item, 24, 1F));

		//Non projectile renders
		registerRender(EntityStatue.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderStatue(manager); }});
		registerRender(EntityTimeDisruption.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderTimeDisruption(manager); }});
		registerRender(EntityDarkCrystal.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderDarkCrystal(manager); }});
		registerRender(EntityGuardianShield.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderGuardianShield(manager); }});
		registerRender(EntityDimensionalAnomaly.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderDimensionalAnomaly(manager); }});
		registerRender(EntityLock.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderLock(manager); }});
		registerRender(EntityDirectedLightning.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderDirectedLightning(manager); }});
		registerRender(EntityNuke.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderNuke(manager); }});

		//Mob renders
		registerRender(EntityJabba.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderJabba(manager); }});
		registerRender(EntityJarra.class, new RenderFactoryMob(new ModelJarra(), 0.655F, "Jarra", 1.585F));
		registerRender(EntityPlague.class, new RenderFactoryMob(new ModelPlague(), 0.115F, "Plague"));
		registerRender(EntityGragul.class, new RenderFactoryMob(new ModelGragul(), 0.115F, "Gragul"));
		registerRender(EntityKragul.class, new RenderFactoryMob(new ModelKragul(), 0.115F, "Kragul", 2.115F));
		registerRender(EntityMinotaur.class, new RenderFactoryMob(new ModelMinotaur(), 0.337F, "Minotaur"));
		registerRender(EntityRagr.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderRagr(manager); }});
		registerRender(EntityInkling.class, new RenderFactoryMob(new ModelInkling(), 0.175F, "Inkling"));
		registerRender(EntityPumpkinhead.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderPumpkinhead(manager); }});
		registerRender(EntityTragicNeko.class, new RenderFactoryMob(new ModelTragicNeko(), 0.295F, "TragicNeko"));
		registerRender(EntityTox.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderTox(manager); }});
		registerRender(EntityMagmox.class, new RenderFactoryMob(new ModelTox(), 0.565F, "Magmox2", 1.625F));
		registerRender(EntityCryse.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderCryse(manager); }});
		registerRender(EntityMegaCryse.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderMegaCryse(manager); }});
		registerRender(EntityNorVox.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderNorVox(manager); }});
		registerRender(EntityVoxStellarum.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderVoxStellarum(manager); }});
		registerRender(EntityPirah.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderPirah(manager); }});
		registerRender(EntityStin.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderStin(manager); }});
		registerRender(EntityGreaterStin.class, new RenderFactoryMob(new ModelGreaterStin(), 0.675F, "GreaterStin"));
		registerRender(EntityStinKing.class, new RenderFactoryMob(new ModelStinKing(), 0.675F, "StinKing", 1.625F));
		registerRender(EntityStinQueen.class, new RenderFactoryMob(new ModelStinQueen(), 0.675F, "StinQueen", 1.225F));
		registerRender(EntityWisp.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderWisp(manager); }});
		registerRender(EntityAbomination.class, new RenderFactoryMob(new ModelAbomination(), 0.475F, "Abomination"));
		registerRender(EntityErkel.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderErkel(manager); }});
		registerRender(EntitySirv.class, new RenderFactoryMob(new ModelSirv(), 0.245F, "Sirv"));
		registerRender(EntityPsygote.class, new RenderFactoryMob(new ModelPsygote(), 0.565F, "Psygote"));
		registerRender(EntityNanoSwarm.class, new RenderFactoryMob(new ModelNanoSwarm(), 0.215F, "NanoSwarm", 1.545F));
		registerRender(EntityAegar.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderAegar(manager); }});
		registerRender(EntityHunter.class, new RenderFactoryMob(new ModelHunter(), 0.565F, "Hunter", 1.4F));
		registerRender(EntityHarvester.class, new RenderFactoryMob(new ModelHarvester(), 0.785F, "Harvester", 1.555F));
		registerRender(EntityLockbot.class, new RenderFactoryMob(new ModelLockbot(), 0.335F, "Lockbot"));
		registerRender(EntitySeeker.class, new RenderFactoryMob(new ModelSeeker(), 0.475F, "Seeker"));
		registerRender(EntityIre.class, new RenderFactoryMobTransparent(new ModelIre(), 0.335F, "Ire", 1.0F, 0.65F));
		registerRender(EntityArchangel.class, new RenderFactoryMobTransparent(new ModelArchangel(), 0.355F, "Archangel", 1.0F, 0.625F));
		registerRender(EntityFusea.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderFusea(manager, 0); }});
		registerRender(EntityVolatileFusea.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderFusea(manager, 1); }});
		registerRender(EntityRanmas.class, new RenderFactoryMob(new ModelRanmas(), 0.775F, "Ranmas", 1.25F));
		registerRender(EntityParasmite.class, new RenderFactoryMob(new ModelHunter(), 0.565F, "Parasmite", 1.355F));
		registerRender(EntityKurayami.class, new RenderFactoryMob(new ModelKurayami(), 0.645F, "Kurayami", 0.825F));
		registerRender(EntityAvris.class, new RenderFactoryMob(new ModelAvris(), 0.645F, "Avris"));
		registerRender(EntityJetNeko.class, new RenderFactoryMob(new ModelJetNeko(), 0.295F, "JetNeko"));

		//Boss renders
		registerRender(EntityApis.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderApis(manager); }});
		registerRender(EntityDeathReaper.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderDeathReaper(manager); }});
		registerRender(EntityKitsune.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderKitsune(manager); }});
		registerRender(EntityPolaris.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderPolaris(manager); }});
		registerRender(EntityYeti.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderYeti(manager); }});
		registerRender(EntityTimeController.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderTimeController(manager); }});
		registerRender(EntityEnyvil.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderEnyvil(manager); }});
		registerRender(EntityClaymation.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderClaymation(manager); }});

		//Alpha renders
		registerRender(EntityOverlordCocoon.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderOverlordCocoon(manager); }});
		registerRender(EntityOverlordCombat.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderOverlordCombat(manager); }});
		registerRender(EntityOverlordCore.class, new IRenderFactory() {
			@Override public Render createRenderFor(RenderManager manager) { return new RenderOverlordCore(manager); }});

		registerBlockToBakery(TragicBlocks.SummonBlock, getPrefixedArray(summonBlock));

		if (TragicConfig.allowNonMobBlocks)
		{
			registerBlockToBakery(TragicBlocks.CompactOre, getPrefixedArray(compactOre));
			registerBlockToBakery(TragicBlocks.Quicksand, getPrefixedArray(quicksand));
			registerBlockToBakery(TragicBlocks.DarkStone, getPrefixedArray(darkStone));
			registerBlockToBakery(TragicBlocks.DarkCobblestone, getPrefixedArray(darkCobble));
			registerBlockToBakery(TragicBlocks.LightCobblestone, getPrefixedArray(lightCobble));
			registerBlockToBakery(TragicBlocks.TragicObsidian, getPrefixedArray(obsidian));
			registerBlockToBakery(TragicBlocks.DeadDirt, getPrefixedArray(deadDirt));
			registerBlockToBakery(TragicBlocks.DarkSandstone, getPrefixedArray(darkSandstone));
			registerBlockToBakery(TragicBlocks.TragicOres, getPrefixedArray(ores));
			registerBlockToBakery(TragicBlocks.BoneBlock, getPrefixedArray(boneBlock));
			registerBlockToBakery(TragicBlocks.SmoothNetherrack, getPrefixedArray(smoothNetherrack));

			registerBlockToBakery(TragicBlocks.TragicSapling, getPrefixedArray(saplings));
			registerBlockToBakery(TragicBlocks.TragicFlower, getPrefixedArray(flowers));
			registerBlockToBakery(TragicBlocks.TragicFlower2, getPrefixedArray(flowers2));

			registerBlockToBakery(TragicBlocks.StarCrystal, getPrefixedArray(capitalizeArray("starCrystal", colors)));
			registerBlockToBakery(TragicBlocks.ErodedStone, getPrefixedArray(erodedStone));
			registerBlockToBakery(TragicBlocks.DarkenedQuartz, getPrefixedArray(darkenedQuartz));
			registerBlockToBakery(TragicBlocks.CircuitBlock, getPrefixedArray(circuitBlock));
			registerBlockToBakery(TragicBlocks.CelledLamp, getPrefixedArray(capitalizeArray("celledLamp", colors)));

			registerBlockToBakery(TragicBlocks.WitheringGas, DOMAIN + "luminescence");
			registerBlockToBakery(TragicBlocks.CorruptedGas, DOMAIN + "luminescence");

			registerBlockToBakery(TragicBlocks.Aeris, getPrefixedArray(aeris));

			registerBlockToBakery(TragicBlocks.RadiatedGas, DOMAIN + "luminescence");
			registerBlockToBakery(TragicBlocks.ExplosiveGas, DOMAIN + "luminescence");

			registerBlockToBakery(TragicBlocks.Permafrost, getPrefixedArray(permafrost));

			registerBlockToBakery(TragicBlocks.DarkGas, DOMAIN + "luminescence");
			registerBlockToBakery(TragicBlocks.SepticGas, DOMAIN + "luminescence");

			registerBlockToBakery(TragicBlocks.Corsin, getPrefixedArray(corsin));
		}

		registerItemToBakery(TragicItems.Projectile, getPrefixedArray(projectile));

		if (TragicConfig.allowMobs) registerItemToBakery(TragicItems.SpawnEgg, getPrefixedArray(spawnEggs));

		if (TragicConfig.allowNonMobItems)
		{			
			registerArmorToBakery(TragicItems.SkullHelmet, "skullHelmet");
			registerArmorToBakery(TragicItems.SkullPlate, "skullPlate");
			registerArmorToBakery(TragicItems.SkullLegs, "skullLegs");
			registerArmorToBakery(TragicItems.SkullBoots, "skullBoots");

			registerArmorToBakery(TragicItems.HuntersCap, "huntersCap");
			registerArmorToBakery(TragicItems.HuntersTunic, "huntersTunic");
			registerArmorToBakery(TragicItems.HuntersLegs, "huntersLegs");
			registerArmorToBakery(TragicItems.HuntersBoots, "huntersBoots");

			registerArmorToBakery(TragicItems.MercuryHelm, "mercuryHelm");
			registerArmorToBakery(TragicItems.MercuryPlate, "mercuryPlate");
			registerArmorToBakery(TragicItems.MercuryLegs, "mercuryLegs");
			registerArmorToBakery(TragicItems.MercuryBoots, "mercuryBoots");

			registerArmorToBakery(TragicItems.TungstenHelm, "tungstenHelm");
			registerArmorToBakery(TragicItems.TungstenPlate, "tungstenPlate");
			registerArmorToBakery(TragicItems.TungstenLegs, "tungstenLegs");
			registerArmorToBakery(TragicItems.TungstenBoots, "tungstenBoots");

			registerArmorToBakery(TragicItems.LightHelm, "lightHelm");
			registerArmorToBakery(TragicItems.LightPlate, "lightPlate");
			registerArmorToBakery(TragicItems.LightLegs, "lightLegs");
			registerArmorToBakery(TragicItems.LightBoots, "lightBoots");

			registerArmorToBakery(TragicItems.DarkHelm, "darkHelm");
			registerArmorToBakery(TragicItems.DarkPlate, "darkPlate");
			registerArmorToBakery(TragicItems.DarkLegs, "darkLegs");
			registerArmorToBakery(TragicItems.DarkBoots, "darkBoots");

			registerArmorToBakery(TragicItems.OverlordHelm, "overlordHelm");
			registerArmorToBakery(TragicItems.OverlordPlate, "overlordPlate");
			registerArmorToBakery(TragicItems.OverlordLegs, "overlordLegs");
			registerArmorToBakery(TragicItems.OverlordBoots, "overlordBoots");

			registerWeaponToBakery(TragicItems.BeastlyClaws, "beastlyClaws");
			registerWeaponToBakery(TragicItems.NekoLauncher, "nekoLauncher");
			registerWeaponToBakery(TragicItems.ReaperScythe, "reaperScythe");
			registerWeaponToBakery(TragicItems.CelestialAegis, "celestialAegis");

			registerWeaponToBakery(TragicItems.Titan, "titan");
			registerWeaponToBakery(TragicItems.Butcher, "butcher");
			registerWeaponToBakery(TragicItems.Thardus, "thardus");
			registerWeaponToBakery(TragicItems.Paranoia, "paranoia");
			registerWeaponToBakery(TragicItems.Splinter, "splinter");
			registerWeaponToBakery(TragicItems.DragonFang, "dragonFang");

			registerWeaponToBakery(TragicItems.Sentinel, "sentinel");

			registerItemToBakery(TragicItems.HuntersBow, getPrefixedArray(huntersBow));
			registerItemToBakery(TragicItems.CelestialLongbow, getPrefixedArray(celestialLongbow));
			registerItemToBakery(TragicItems.EverlastingLight, getPrefixedArray(everlastingLight));

			registerWeaponToBakery(TragicItems.IreNetParticleCannon, "ireParticleCannon");

			registerItemToBakery(TragicItems.Starstruck, DOMAIN + "record");
			registerItemToBakery(TragicItems.Faultless, DOMAIN + "record");
			registerItemToBakery(TragicItems.Transmissions, DOMAIN + "record");
			registerItemToBakery(TragicItems.Atrophy, DOMAIN + "record");
			registerItemToBakery(TragicItems.Archaic, DOMAIN + "record");
			registerItemToBakery(TragicItems.System, DOMAIN + "record");
			registerItemToBakery(TragicItems.Mirrors, DOMAIN + "record");
			registerItemToBakery(TragicItems.Untitled, DOMAIN + "record");
			registerItemToBakery(TragicItems.Untitled2, DOMAIN + "record");

			if (TragicConfig.allowGeneratorItems) registerItemToBakery(TragicItems.Generator, getPrefixedArray(generator));
			registerItemToBakery(TragicItems.MobStatue, getPrefixedArray(statue));
			if (TragicConfig.allowAmulets)
			{
				registerAmuletToBakery(TragicItems.KitsuneAmulet);
				registerAmuletToBakery(TragicItems.PeaceAmulet);
				registerAmuletToBakery(TragicItems.YetiAmulet);
				registerAmuletToBakery(TragicItems.ClaymationAmulet);
				registerAmuletToBakery(TragicItems.ChickenAmulet);
				registerAmuletToBakery(TragicItems.MartyrAmulet);
				registerAmuletToBakery(TragicItems.PiercingAmulet);
				registerAmuletToBakery(TragicItems.BlacksmithAmulet);
				registerAmuletToBakery(TragicItems.ApisAmulet);
				registerAmuletToBakery(TragicItems.CreeperAmulet);
				registerAmuletToBakery(TragicItems.ZombieAmulet);
				registerAmuletToBakery(TragicItems.SkeletonAmulet);
				registerAmuletToBakery(TragicItems.SunkenAmulet);
				registerAmuletToBakery(TragicItems.TimeAmulet);
				registerAmuletToBakery(TragicItems.IceAmulet);
				registerAmuletToBakery(TragicItems.SnowGolemAmulet);
				registerAmuletToBakery(TragicItems.IronGolemAmulet);
				registerAmuletToBakery(TragicItems.EndermanAmulet);
				registerAmuletToBakery(TragicItems.WitherAmulet);
				registerAmuletToBakery(TragicItems.SpiderAmulet);
				registerAmuletToBakery(TragicItems.StinAmulet);
				registerAmuletToBakery(TragicItems.PolarisAmulet);
				registerAmuletToBakery(TragicItems.OverlordAmulet);
				registerAmuletToBakery(TragicItems.LightningAmulet);
				registerAmuletToBakery(TragicItems.ConsumptionAmulet);
				registerAmuletToBakery(TragicItems.SupernaturalAmulet);
				registerAmuletToBakery(TragicItems.UndeadAmulet);
				registerAmuletToBakery(TragicItems.EnderDragonAmulet);
				registerAmuletToBakery(TragicItems.FuseaAmulet);
				registerAmuletToBakery(TragicItems.EnyvilAmulet);
				registerAmuletToBakery(TragicItems.LuckAmulet);
			}
			if (TragicConfig.allowChallengeScrolls) registerItemToBakery(TragicItems.ChallengeScroll, getPrefixedArray(challengeScroll));
		}

		int i; //for loops

		for (i = 0; i < summonBlock.length; i++) registerBlockToMesher(TragicBlocks.SummonBlock, i, summonBlock[i]);

		registerBlockToMesher(TragicBlocks.Luminescence, ZERO, "luminescence");
		if (TragicConfig.allowOverlord) registerBlockToMesher(TragicBlocks.OverlordBarrier, ZERO, "overlordBarrier");

		if (TragicConfig.allowNonMobBlocks)
		{
			registerBlockToMesher(TragicBlocks.MercuryOre, ZERO, "mercuryOre");
			registerBlockToMesher(TragicBlocks.TungstenOre, ZERO, "tungstenOre");
			registerBlockToMesher(TragicBlocks.RubyOre, ZERO, "rubyOre");
			registerBlockToMesher(TragicBlocks.SapphireOre, ZERO, "sapphireOre");
			for (i = 0; i < compactOre.length; i++) registerBlockToMesher(TragicBlocks.CompactOre, i, compactOre[i]);

			registerBlockToMesher(TragicBlocks.Wax, ZERO, "wax");
			registerBlockToMesher(TragicBlocks.Light, ZERO, "light");
			registerBlockToMesher(TragicBlocks.Candle, ZERO, "candle");
			registerBlockToMesher(TragicBlocks.PotatoBlock, ZERO, "potatoBlock");
			registerBlockToMesher(TragicBlocks.CarrotBlock, ZERO, "carrotBlock");
			registerBlockToMesher(TragicBlocks.SandstonePressurePlate, ZERO, "sandstonePressurePlate");
			registerBlockToMesher(TragicBlocks.NetherBrickPressurePlate, ZERO, "netherBrickPressurePlate");

			for (i = 0; i < 16; i++) registerBlockToMesher(TragicBlocks.StructureSeed, i, "structureSeed");
			for (i = 0; i < quicksand.length; i++) registerBlockToMesher(TragicBlocks.Quicksand, i, quicksand[i]);

			for (i = 0; i < darkStone.length; i++) registerBlockToMesher(TragicBlocks.DarkStone, i, darkStone[i]);
			for (i = 0; i < darkCobble.length; i++) registerBlockToMesher(TragicBlocks.DarkCobblestone, i, darkCobble[i]);
			for (i = 0; i < lightCobble.length; i++) registerBlockToMesher(TragicBlocks.LightCobblestone, i, lightCobble[i]);
			registerBlockToMesher(TragicBlocks.LightStone, ZERO, "lightStone");
			registerBlockToMesher(TragicBlocks.Spike, ZERO, "spike");

			for (i = 0; i < obsidian.length; i++) registerBlockToMesher(TragicBlocks.TragicObsidian, i, obsidian[i]);
			for (i = 0; i < deadDirt.length; i++) registerBlockToMesher(TragicBlocks.DeadDirt, i, deadDirt[i]);
			registerBlockToMesher(TragicBlocks.DarkSand, ZERO, "darkSand");
			for (i = 0; i < darkSandstone.length; i++) registerBlockToMesher(TragicBlocks.DarkSandstone, i, darkSandstone[i]);
			registerBlockToMesher(TragicBlocks.TimeDisruptionCube, ZERO, "timeDisruptor");
			for (i = 0; i < ores.length; i++) registerBlockToMesher(TragicBlocks.TragicOres, i, ores[i]);
			for (i = 0; i < boneBlock.length; i++) registerBlockToMesher(TragicBlocks.BoneBlock, i, boneBlock[i]);
			for (i = 0; i < smoothNetherrack.length; i++) registerBlockToMesher(TragicBlocks.SmoothNetherrack, i, smoothNetherrack[i]);

			registerBlockToMesher(TragicBlocks.BrushedGrass, ZERO, "brushedGrass");
			registerBlockToMesher(TragicBlocks.PaintedWood, ZERO, "paintedWood");
			registerBlockToMesher(TragicBlocks.PaintedLeaves, ZERO, "paintedLeaves");
			registerBlockToMesher(TragicBlocks.PaintedPlanks, ZERO, "paintedPlanks");
			registerBlockToMesher(TragicBlocks.Glowvine, ZERO, "glowvine");
			registerBlockToMesher(TragicBlocks.PaintedTallGrass, ZERO, "paintedTallGrass");
			registerBlockToMesher(TragicBlocks.AshenGrass, ZERO, "ashenGrass");
			registerBlockToMesher(TragicBlocks.AshenWood, ZERO, "ashenWood");
			registerBlockToMesher(TragicBlocks.AshenLeaves, ZERO, "ashenLeaves");
			registerBlockToMesher(TragicBlocks.AshenPlanks, ZERO, "ashenPlanks");
			registerBlockToMesher(TragicBlocks.BleachedWood, ZERO, "bleachedWood");
			registerBlockToMesher(TragicBlocks.BleachedLeaves, ZERO, "bleachedLeaves");
			registerBlockToMesher(TragicBlocks.BleachedPlanks, ZERO, "bleachedPlanks");

			for (i = 0; i < saplings.length; i++) registerBlockToMesher(TragicBlocks.TragicSapling, i, saplings[i]);
			for (i = 0; i < flowers.length; i++) registerBlockToMesher(TragicBlocks.TragicFlower, i, flowers[i]);
			for (i = 0; i < flowers2.length; i++) registerBlockToMesher(TragicBlocks.TragicFlower2, i, flowers2[i]);

			registerBlockToMesher(TragicBlocks.AshenBush, ZERO, "ashenBush");
			registerBlockToMesher(TragicBlocks.DeadBush, ZERO, "deadBush");
			registerBlockToMesher(TragicBlocks.DriedTallGrass, ZERO, "driedTallGrass");
			registerBlockToMesher(TragicBlocks.AshenTallGrass, ZERO, "ashenTallGrass");
			registerBlockToMesher(TragicBlocks.StarlitGrass, ZERO, "starlitGrass");
			for (i = 0; i < colors.length; i++) registerBlockToMesher(TragicBlocks.StarCrystal, i, "starCrystal" + capitalize(colors[i]));
			registerBlockToMesher(TragicBlocks.StarlitTallGrass, ZERO, "starlitTallGrass");
			for (i = 0; i < erodedStone.length; i++) registerBlockToMesher(TragicBlocks.ErodedStone, i, erodedStone[i]);
			for (i = 0; i < darkenedQuartz.length; i++) registerBlockToMesher(TragicBlocks.DarkenedQuartz, i, darkenedQuartz[i]);

			for (i = 0; i < circuitBlock.length; i++) registerBlockToMesher(TragicBlocks.CircuitBlock, i, circuitBlock[i]);
			registerBlockToMesher(TragicBlocks.CelledBlock, ZERO, "celled");
			for (i = 0; i < colors.length; i++) registerBlockToMesher(TragicBlocks.CelledLamp, i, "celledLamp" + capitalize(colors[i]));
			registerBlockToMesher(TragicBlocks.SynapseCore, ZERO, "synapseCore");
			registerBlockToMesher(TragicBlocks.WitheringGas, ZERO, "luminescence");
			registerBlockToMesher(TragicBlocks.CorruptedGas, ZERO, "luminescence");
			registerBlockToMesher(TragicBlocks.Conduit, ZERO, "conduit");
			registerBlockToMesher(TragicBlocks.DigitalSea, ZERO, "digitalSea");

			registerBlockToMesher(TragicBlocks.FrozenNetherrack, ZERO, "frozenNetherrack");
			for (i = 0; i < aeris.length; i++) registerBlockToMesher(TragicBlocks.Aeris, i, aeris[i]);
			registerBlockToMesher(TragicBlocks.MoltenRock, ZERO, "moltenRock");
			registerBlockToMesher(TragicBlocks.ScorchedRock, ZERO, "scorchedRock");
			registerBlockToMesher(TragicBlocks.Geyser, ZERO, "geyser");
			registerBlockToMesher(TragicBlocks.SteamVent, ZERO, "steamVent");
			registerBlockToMesher(TragicBlocks.HallowedGrass, ZERO, "hallowedGrass");
			registerBlockToMesher(TragicBlocks.StringLight, ZERO, "stringLight");
			registerBlockToMesher(TragicBlocks.FragileLight, ZERO, "fragileLight");
			registerBlockToMesher(TragicBlocks.HallowedLeaves, ZERO, "hallowedLeaves");
			registerBlockToMesher(TragicBlocks.HallowedLeafTrim, ZERO, "hallowedLeafTrim");
			registerBlockToMesher(TragicBlocks.HallowedPlanks, ZERO, "hallowedPlanks");
			registerBlockToMesher(TragicBlocks.HallowedWood, ZERO, "hallowedWood");
			registerBlockToMesher(TragicBlocks.WickedVine, ZERO, "wickedVine");

			registerBlockToMesher(TragicBlocks.RadiatedGas, ZERO, "luminescence");
			registerBlockToMesher(TragicBlocks.ExplosiveGas, ZERO, "luminescence");
			registerBlockToMesher(TragicBlocks.SoulChest, ZERO, "soulChest");

			registerBlockToMesher(TragicBlocks.IcedDirt, ZERO, "icedDirt");
			for (i = 0; i < permafrost.length; i++) registerBlockToMesher(TragicBlocks.Permafrost, i, permafrost[i]);
			registerBlockToMesher(TragicBlocks.Moss, ZERO, "moss");
			registerBlockToMesher(TragicBlocks.Lichen, ZERO, "lichen");
			registerBlockToMesher(TragicBlocks.IceSpike, ZERO, "iceSpike");
			registerBlockToMesher(TragicBlocks.Crystal, ZERO, "crystal");
			registerBlockToMesher(TragicBlocks.DarkGrass, ZERO, "darkGrass");
			registerBlockToMesher(TragicBlocks.DarkLeaves, ZERO, "darkLeaves");
			registerBlockToMesher(TragicBlocks.Darkwood, ZERO, "darkwood");
			registerBlockToMesher(TragicBlocks.DarkwoodPlanks, ZERO, "darkwoodPlanks");
			registerBlockToMesher(TragicBlocks.DarkVine, ZERO, "darkVine");
			registerBlockToMesher(TragicBlocks.DarkGas, ZERO, "luminescence");
			registerBlockToMesher(TragicBlocks.DarkTallGrass, ZERO, "darkTallGrass");
			registerBlockToMesher(TragicBlocks.SepticGas, ZERO, "luminescence");
			registerBlockToMesher(TragicBlocks.SkyFruit, ZERO, "skyFruitPod");
			registerBlockToMesher(TragicBlocks.Deathglow, ZERO, "deathglowPlant");
			registerBlockToMesher(TragicBlocks.Honeydrop, ZERO, "honeydropPlant");
			for (i = 0; i < corsin.length; i++) registerBlockToMesher(TragicBlocks.Corsin, i, corsin[i]);
		}

		registerItemWithCustomDefinition(TragicItems.BowOfJustice, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				EntityPlayer player = Minecraft.getMinecraft().thePlayer;
				if (player == null || player.getItemInUse() != stack) return new ModelResourceLocation("bow", "inventory");

				int amt = player.getItemInUseDuration();

				if (amt > 8)
				{
					return new ModelResourceLocation("bow_pulling_2", "inventory");
				}
				else if (amt > 4)
				{
					return new ModelResourceLocation("bow_pulling_1", "inventory");
				}
				else if (amt > 0)
				{
					return new ModelResourceLocation("bow_pulling_0", "inventory");
				}
				else
				{
					return new ModelResourceLocation("bow", "inventory");
				}
			}
		});
		registerItemToMesher(TragicItems.SwordOfJustice, ZERO, "swordOfJustice");
		registerItemToMesher(TragicItems.NekoNekoWand, ZERO, "nekoNekoWand");

		if (TragicConfig.allowMobs)
		{
			for (i = 0; i < TragicEntityList.idToClassMapping.size(); i++)
			{
				if (TragicEntityList.idToClassMapping.containsKey(i))
				{
					EntityEggInfo info = (EntityEggInfo) TragicEntityList.entityEggs.get(i);
					String s = info.eggType == EnumEggType.NORMAL ? "spawnEgg2" : (info.eggType == EnumEggType.MINIBOSS ? "spawnEgg4" : (info.eggType == EnumEggType.BOSS ? "spawnEgg" : (info.eggType == EnumEggType.ALPHA ? "spawnEgg5" : "spawnEgg3")));
					registerItemToMesher(TragicItems.SpawnEgg, i, s);
				}
				else
				{
					registerItemToMesher(TragicItems.SpawnEgg, i, "spawnEgg2");
				}
			}
		}

		for (i = 0; i < projectile.length; i++)
			registerItemToMesher(TragicItems.Projectile, i, projectile[i]);

		if (TragicConfig.allowNonMobItems)
		{
			registerItemToMesher(TragicItems.RedMercury, ZERO, "redMercury");
			registerItemToMesher(TragicItems.Quicksilver, ZERO, "quicksilver");
			registerItemToMesher(TragicItems.QuicksilverIngot, ZERO, "quicksilverIngot");
			registerItemToMesher(TragicItems.Tungsten, ZERO, "tungsten");
			registerItemToMesher(TragicItems.Ruby, ZERO, "ruby");
			registerItemToMesher(TragicItems.Sapphire, ZERO, "sapphire");

			registerArmorToMesher(TragicItems.SkullHelmet, "skullHelmet");
			registerArmorToMesher(TragicItems.SkullPlate, "skullPlate");
			registerArmorToMesher(TragicItems.SkullLegs, "skullLegs");
			registerArmorToMesher(TragicItems.SkullBoots, "skullBoots");

			registerArmorToMesher(TragicItems.HuntersCap, "huntersCap");
			registerArmorToMesher(TragicItems.HuntersTunic, "huntersTunic");
			registerArmorToMesher(TragicItems.HuntersLegs, "huntersLegs");
			registerArmorToMesher(TragicItems.HuntersBoots, "huntersBoots");

			registerArmorToMesher(TragicItems.MercuryHelm, "mercuryHelm");
			registerArmorToMesher(TragicItems.MercuryPlate, "mercuryPlate");
			registerArmorToMesher(TragicItems.MercuryLegs, "mercuryLegs");
			registerArmorToMesher(TragicItems.MercuryBoots, "mercuryBoots");

			registerArmorToMesher(TragicItems.TungstenHelm, "tungstenHelm");
			registerArmorToMesher(TragicItems.TungstenPlate, "tungstenPlate");
			registerArmorToMesher(TragicItems.TungstenLegs, "tungstenLegs");
			registerArmorToMesher(TragicItems.TungstenBoots, "tungstenBoots");

			registerArmorToMesher(TragicItems.LightHelm, "lightHelm");
			registerArmorToMesher(TragicItems.LightPlate,"lightPlate");
			registerArmorToMesher(TragicItems.LightLegs, "lightLegs");
			registerArmorToMesher(TragicItems.LightBoots, "lightBoots");

			registerArmorToMesher(TragicItems.DarkHelm, "darkHelm");
			registerArmorToMesher(TragicItems.DarkPlate, "darkPlate");
			registerArmorToMesher(TragicItems.DarkLegs, "darkLegs");
			registerArmorToMesher(TragicItems.DarkBoots, "darkBoots");

			registerArmorToMesher(TragicItems.OverlordHelm, "overlordHelm");
			registerArmorToMesher(TragicItems.OverlordPlate, "overlordPlate");
			registerArmorToMesher(TragicItems.OverlordLegs, "overlordLegs");
			registerArmorToMesher(TragicItems.OverlordBoots, "overlordBoots");

			registerItemToMesher(TragicItems.MercuryDagger, ZERO, "mercuryDagger");
			registerItemWithCustomDefinition(TragicItems.HuntersBow, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					EntityPlayer player = Minecraft.getMinecraft().thePlayer;
					if (player == null || player.getItemInUse() != stack) return new ModelResourceLocation(DOMAIN + "huntersBow", "inventory");

					int amt = player.getItemInUseDuration();

					if (amt > 14)
					{
						return new ModelResourceLocation(DOMAIN + "huntersBow3", "inventory");
					}
					else if (amt > 8)
					{
						return new ModelResourceLocation(DOMAIN + "huntersBow2", "inventory");
					}
					else if (amt > 2)
					{
						return new ModelResourceLocation(DOMAIN + "huntersBow1", "inventory");
					}
					else
					{
						return new ModelResourceLocation(DOMAIN + "huntersBow", "inventory");
					}
				}
			});
			registerItemToMesher(TragicItems.PitchBlack, ZERO, "pitchBlack");
			registerItemToMesher(TragicItems.BlindingLight, ZERO, "blindingLight");
			registerItemToMesher(TragicItems.GravitySpike, ZERO, "gravitySpike");
			registerItemToMesher(TragicItems.HarmonyBell, ZERO, "harmonyBell");
			registerItemToMesher(TragicItems.MourningStar, ZERO, "mourningStar");
			registerItemWithCustomDefinition(TragicItems.BeastlyClaws, new ItemMeshDefinition() {

				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "beastlyClaws" : "beastlyClawsInventory"), "inventory");
				}
			});
			registerItemToMesher(TragicItems.GuiltyThorn, ZERO, "guiltyThorn");
			registerItemWithCustomDefinition(TragicItems.NekoLauncher, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "nekoLauncher" : "nekoLauncherInventory"), "inventory");
				}
			});
			registerItemWithCustomDefinition(TragicItems.ReaperScythe, new ItemMeshDefinition() {

				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "reaperScythe" : "reaperScytheInventory"), "inventory");
				}
			});
			registerItemToMesher(TragicItems.WitheringAxe, ZERO, "witheringAxe");
			registerItemToMesher(TragicItems.FrozenLightning, ZERO, "frozenLightning");
			registerItemWithCustomDefinition(TragicItems.CelestialAegis, new ItemMeshDefinition() {

				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "celestialAegis" : "celestialAegisInventory"), "inventory");
				}
			});

			registerItemWithCustomDefinition(TragicItems.CelestialLongbow, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					EntityPlayer player = Minecraft.getMinecraft().thePlayer;
					if (player == null || player.getItemInUse() != stack) return new ModelResourceLocation(DOMAIN + "celestialLongbow", "inventory");

					int amt = player.getItemInUseDuration();

					if (amt > 48)
					{
						return new ModelResourceLocation(DOMAIN + "celestialLongbow3", "inventory");
					}
					else if (amt > 24)
					{
						return new ModelResourceLocation(DOMAIN + "celestialLongbow2", "inventory");
					}
					else if (amt > 8)
					{
						return new ModelResourceLocation(DOMAIN + "celestialLongbow1", "inventory");
					}
					else
					{
						return new ModelResourceLocation(DOMAIN + "celestialLongbow", "inventory");
					}
				}
			});
			registerItemToMesher(TragicItems.SilentHellraiser, ZERO, "silentHellraiser");

			registerItemWithCustomDefinition(TragicItems.Titan, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "titan" : "titanInventory"), "inventory");
				}
			});
			registerItemWithCustomDefinition(TragicItems.Splinter, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "splinter" : "splinterInventory"), "inventory");
				}
			});
			registerItemWithCustomDefinition(TragicItems.Butcher, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "butcher" : "butcherInventory"), "inventory");
				}
			});
			registerItemWithCustomDefinition(TragicItems.Thardus, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "thardus" : "thardusInventory"), "inventory");
				}
			});
			registerItemWithCustomDefinition(TragicItems.Paranoia, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "paranoia" : "paranoiaInventory"), "inventory");
				}
			});
			registerItemWithCustomDefinition(TragicItems.DragonFang, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "dragonFang" : "dragonFangInventory"), "inventory");
				}
			});

			registerItemWithCustomDefinition(TragicItems.Sentinel, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "sentinel" : "sentinelInventory"), "inventory");
				}
			});

			registerItemToMesher(TragicItems.Scythe, ZERO, "scythe");
			registerItemWithCustomDefinition(TragicItems.EverlastingLight, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {

					int amt = stack.getItemDamage();

					if (amt > 248)
					{
						return new ModelResourceLocation(DOMAIN + "everlastingLight3", "inventory");
					}
					else if (amt > 185)
					{
						return new ModelResourceLocation(DOMAIN + "everlastingLight2", "inventory");
					}
					else if (amt > 124)
					{
						return new ModelResourceLocation(DOMAIN + "everlastingLight1", "inventory");
					}
					else
					{
						return new ModelResourceLocation(DOMAIN + "everlastingLight", "inventory");
					}
				}
			});
			registerItemToMesher(TragicItems.Jack, ZERO, "jack");
			registerItemToMesher(TragicItems.TungstenJack, ZERO, "tungstenJack");
			registerItemToMesher(TragicItems.CelestialJack, ZERO, "celestialJack");

			registerItemToMesher(TragicItems.Ectoplasm, ZERO, "ectoplasm");
			registerItemToMesher(TragicItems.Ash, ZERO, "ash");
			registerItemToMesher(TragicItems.EnchantedTears, ZERO, "enchantedTears");
			registerItemToMesher(TragicItems.ToughLeather, ZERO, "toughLeather");
			registerItemToMesher(TragicItems.WovenSilk, ZERO, "wovenSilk");
			registerItemToMesher(TragicItems.CrushedIce, ZERO, "crushedIce");
			registerItemToMesher(TragicItems.LightParticles, ZERO, "lightParticles");
			registerItemToMesher(TragicItems.DarkParticles, ZERO, "darkParticles");
			registerItemToMesher(TragicItems.IceOrb, ZERO, "iceOrb");
			registerItemToMesher(TragicItems.GravityOrb, ZERO, "gravityOrb");
			registerItemToMesher(TragicItems.FireOrb, ZERO, "fireOrb");
			registerItemToMesher(TragicItems.LightningOrb, ZERO, "lightningOrb");
			registerItemToMesher(TragicItems.AquaOrb, ZERO, "aquaOrb");
			registerItemToMesher(TragicItems.Thorns, ZERO, "thorns");
			registerItemToMesher(TragicItems.Horn, ZERO, "horn");
			registerItemToMesher(TragicItems.BoneMarrow, ZERO, "boneMarrow");
			registerItemToMesher(TragicItems.LightIngot, ZERO, "lightIngot");
			registerItemToMesher(TragicItems.DarkIngot, ZERO, "darkIngot");
			registerItemToMesher(TragicItems.KitsuneTail, ZERO, "kitsuneTail");
			registerItemToMesher(TragicItems.DeathlyHallow, ZERO, "deathlyHallow");
			registerItemToMesher(TragicItems.EmpariahClaw, ZERO, "empariahClaw");
			registerItemToMesher(TragicItems.StarPieces, ZERO, "starPieces");
			registerItemToMesher(TragicItems.TimeEssence, ZERO, "timeEssence");
			registerItemToMesher(TragicItems.PureLight, ZERO, "pureLight");
			registerItemToMesher(TragicItems.LunarPowder, ZERO, "lunarPowder");
			registerItemToMesher(TragicItems.CelestialDiamond, ZERO, "celestialDiamond");
			registerItemToMesher(TragicItems.StinHorn, ZERO, "stinHorn");
			registerItemToMesher(TragicItems.WispParticles, ZERO, "wispParticles");
			registerItemToMesher(TragicItems.IcyFur, ZERO, "icyFur");
			registerItemToMesher(TragicItems.PureDarkness, ZERO, "pureDarkness");
			registerItemToMesher(TragicItems.LivingClay, ZERO, "livingClay");
			registerItemToMesher(TragicItems.CelestialSteel, ZERO, "celestialSteel");
			registerItemToMesher(TragicItems.SynapseCrystal, ZERO, "synapseCrystal");
			registerItemToMesher(TragicItems.CorruptedEye, ZERO, "corruptedEye");
			registerItemToMesher(TragicItems.CorruptedEssence, ZERO, "corruptedEssence");
			registerItemToMesher(TragicItems.CorruptedEgg, ZERO, "corruptedEgg");
			registerItemToMesher(TragicItems.NanoBots, ZERO, "nanoBots");
			registerItemToMesher(TragicItems.UnstableIsotope, ZERO, "unstableIsotope");
			registerItemToMesher(TragicItems.ArchangelFeather, ZERO, "archangelFeather");
			registerItemToMesher(TragicItems.WingsOfLiberation, ZERO, "wingsOfLiberation");
			registerItemToMesher(TragicItems.IreNode, ZERO, "ireNode");
			registerItemWithCustomDefinition(TragicItems.IreNetParticleCannon, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return  new ModelResourceLocation(DOMAIN + (TragicConfig.allowWeaponModels ? "ireParticleCannon" : "ireParticleCannonInventory"), "inventory");
				}
			});
			registerItemToMesher(TragicItems.CatalyticCompound, ZERO, "catalyticCompound");
			registerItemToMesher(TragicItems.InterestingResin, ZERO, "interestingResin");
			registerItemToMesher(TragicItems.Chitin, ZERO, "chitin");
			registerItemToMesher(TragicItems.SoulExcess, ZERO, "soulExcess");
			registerItemToMesher(TragicItems.EtherealDistortion, ZERO, "etherealDistortion");

			registerItemToMesher(TragicItems.NekoMindControlDevice, ZERO, "nekoMindControlDevice");
			registerItemToMesher(TragicItems.RecaptureSiphon, ZERO, "recaptureSiphon");
			registerItemToMesher(TragicItems.NekoInfluencer, ZERO, "nekoInfluencer");

			registerItemToMesher(TragicItems.Starstruck, ZERO, "record");
			registerItemToMesher(TragicItems.Faultless, ZERO, "record");
			registerItemToMesher(TragicItems.Transmissions, ZERO, "record");
			registerItemToMesher(TragicItems.Atrophy, ZERO, "record");
			registerItemToMesher(TragicItems.Archaic, ZERO, "record");
			registerItemToMesher(TragicItems.System, ZERO, "record");
			registerItemToMesher(TragicItems.Mirrors, ZERO, "record");
			registerItemToMesher(TragicItems.Untitled, ZERO, "record");
			registerItemToMesher(TragicItems.Untitled2, ZERO, "record");

			registerItemToMesher(TragicItems.ToxicAmalgation, ZERO, "toxicAmalgation");
			registerItemToMesher(TragicItems.ParadoxicalFormula, ZERO, "paradoxicalFormula");
			registerItemToMesher(TragicItems.RadiatedInfusion, ZERO, "radiatedInfusion");
			registerItemToMesher(TragicItems.ImpossibleReaction, ZERO, "impossibleReaction");
			registerItemToMesher(TragicItems.InfallibleMetal, ZERO, "infallibleMetal");
			registerItemToMesher(TragicItems.ComplexCircuitry, ZERO, "complexCircuitry");
			registerItemToMesher(TragicItems.NauseatingConcoction, ZERO, "nauseatingConcoction");
			registerItemToMesher(TragicItems.CreepyIdol, ZERO, "creepyIdol");
			registerItemToMesher(TragicItems.PurifiedEnergy, ZERO, "purifiedEnergy");
			registerItemToMesher(TragicItems.Shadowskin, ZERO, "shadowskin");

			registerItemToMesher(TragicItems.IceCream, ZERO, "iceCream");
			registerItemToMesher(TragicItems.Honeydrop, ZERO, "honeydrop");
			registerItemToMesher(TragicItems.Gloopii, ZERO, "gloopii");
			registerItemToMesher(TragicItems.Deathglow, ZERO, "deathglow");
			registerItemToMesher(TragicItems.Rice, ZERO, "rice");
			registerItemToMesher(TragicItems.Sushi, ZERO, "sushi");
			registerItemToMesher(TragicItems.GoldenSushi, ZERO, "goldenSushi");
			registerItemToMesher(TragicItems.Banana, ZERO, "banana");
			registerItemToMesher(TragicItems.BananaSplit, ZERO, "bananaSplit");
			registerItemToMesher(TragicItems.SkyFruit, ZERO, "skyFruit");
			registerItemToMesher(TragicItems.Tentacle, ZERO, "tentacle");
			registerItemToMesher(TragicItems.HoneydropSeeds, ZERO, "honeydropSeeds");
			registerItemToMesher(TragicItems.DeathglowSeeds, ZERO, "deathglowSeeds");
			registerItemToMesher(TragicItems.SkyFruitSeeds, ZERO, "skyFruitSeeds");

			registerItemToMesher(TragicItems.RubyCharm, ZERO, "rubyCharm");
			registerItemToMesher(TragicItems.SapphireCharm, ZERO, "sapphireCharm");
			registerItemToMesher(TragicItems.DiamondCharm, ZERO, "diamondCharm");
			registerItemToMesher(TragicItems.EmeraldCharm, ZERO, "emeraldCharm");
			registerItemToMesher(TragicItems.AwakeningStone, ZERO, "awakeningStone");
			registerItemToMesher(TragicItems.ObsidianOrb, ZERO, "obsidianOrb");
			registerItemToMesher(TragicItems.CryingObsidianOrb, ZERO, "cryingObsidianOrb");
			registerItemToMesher(TragicItems.BleedingObsidianOrb, ZERO, "bleedingObsidianOrb");
			registerItemToMesher(TragicItems.DyingObsidianOrb, ZERO, "dyingObsidianOrb");

			registerItemToMesher(TragicItems.Talisman, ZERO, "talisman");
			registerItemToMesher(TragicItems.RainDanceTalisman, ZERO, "rainDanceTalisman");
			registerItemToMesher(TragicItems.SunnyDayTalisman, ZERO, "sunnyDayTalisman");
			registerItemToMesher(TragicItems.ThunderstormTalisman, ZERO, "thunderstormTalisman");
			registerItemToMesher(TragicItems.TimeManipulatorTalisman, ZERO, "timeManipulatorTalisman");
			registerItemToMesher(TragicItems.MoonlightTalisman, ZERO, "moonlightTalisman");
			registerItemToMesher(TragicItems.SynthesisTalisman, ZERO, "synthesisTalisman");
			registerItemToMesher(TragicItems.HydrationTalisman, ZERO, "hydrationTalisman");
			registerItemToMesher(TragicItems.LightningRodTalisman, ZERO, "lightningRodTalisman");

			if (TragicConfig.allowAmulets)
			{
				registerAmuletToMesher(TragicItems.KitsuneAmulet);
				registerAmuletToMesher(TragicItems.PeaceAmulet);
				registerAmuletToMesher(TragicItems.YetiAmulet);
				registerAmuletToMesher(TragicItems.ClaymationAmulet);
				registerAmuletToMesher(TragicItems.ChickenAmulet);
				registerAmuletToMesher(TragicItems.MartyrAmulet);
				registerAmuletToMesher(TragicItems.PiercingAmulet);
				registerAmuletToMesher(TragicItems.BlacksmithAmulet);
				registerAmuletToMesher(TragicItems.ApisAmulet);
				registerAmuletToMesher(TragicItems.CreeperAmulet);
				registerAmuletToMesher(TragicItems.ZombieAmulet);
				registerAmuletToMesher(TragicItems.SkeletonAmulet);
				registerAmuletToMesher(TragicItems.SunkenAmulet);
				registerAmuletToMesher(TragicItems.TimeAmulet);
				registerAmuletToMesher(TragicItems.IceAmulet);
				registerAmuletToMesher(TragicItems.SnowGolemAmulet);
				registerAmuletToMesher(TragicItems.IronGolemAmulet);
				registerAmuletToMesher(TragicItems.EndermanAmulet);
				registerAmuletToMesher(TragicItems.WitherAmulet);
				registerAmuletToMesher(TragicItems.SpiderAmulet);
				registerAmuletToMesher(TragicItems.StinAmulet);
				registerAmuletToMesher(TragicItems.PolarisAmulet);
				registerAmuletToMesher(TragicItems.OverlordAmulet);
				registerAmuletToMesher(TragicItems.LightningAmulet);
				registerAmuletToMesher(TragicItems.ConsumptionAmulet);
				registerAmuletToMesher(TragicItems.SupernaturalAmulet);
				registerAmuletToMesher(TragicItems.UndeadAmulet);
				registerAmuletToMesher(TragicItems.EnderDragonAmulet);
				registerAmuletToMesher(TragicItems.FuseaAmulet);
				registerAmuletToMesher(TragicItems.EnyvilAmulet);
				registerAmuletToMesher(TragicItems.LuckAmulet);

				registerItemToMesher(TragicItems.AmuletRelease, ZERO, "amuletRelease");
			}

			if (TragicConfig.allowDoom)
			{
				registerItemToMesher(TragicItems.DoomConsume, ZERO, "doomConsume");
				registerItemToMesher(TragicItems.CooldownDefuse, ZERO, "cooldownDefuse");

				registerItemToMesher(TragicItems.BloodSacrifice, ZERO, "bloodSacrifice");
				registerItemToMesher(TragicItems.NourishmentSacrifice, ZERO, "nourishmentSacrifice");
			}

			for (i = 0; i < statue.length; i++)
				registerItemToMesher(TragicItems.MobStatue, i, statue[i]);

			if (TragicConfig.allowDimension)
			{
				registerItemToMesher(TragicItems.DimensionalKey, ZERO, "dimensionalKey");
				registerItemToMesher(TragicItems.DimensionalKeyEnd, ZERO, "dimensionalKeyEnd");
				registerItemToMesher(TragicItems.DimensionalKeyNether, ZERO, "dimensionalKeyNether");
				registerItemToMesher(TragicItems.DimensionalKeySynapse, ZERO, "dimensionalKeySynapse");
				//registerItemToMesher(TragicItems.DimensionalKeyWilds, ZERO, "dimensionalKeyWilds");
				registerItemToMesher(TragicItems.SynapseLink, ZERO, "synapseLink");
			}

			if (TragicConfig.allowDoomsdays)
			{
				for (i = 1; i < Doomsday.doomsdayList.length; i++)
				{
					if (Doomsday.doomsdayList[i] != null && i - 1 >= 0)
						registerItemToMesher(TragicItems.DoomsdayScroll, i - 1, "doomsdayScroll");
				}
			}

			registerItemToMesher(TragicItems.SoundExtrapolator, ZERO, "soundExtrapolator");

			if (TragicConfig.allowGeneratorItems)
			{
				for (i = 0; i < generator.length; i++)
					registerItemToMesher(TragicItems.Generator, i, generator[i]);
			}

			if (TragicConfig.allowChallengeScrolls)
			{
				for (i = 0; i < 251; i++)
				{
					if (i == 0) registerItemToMesher(TragicItems.ChallengeScroll, ZERO, "challengeScroll");
					else if (i == 250) registerItemToMesher(TragicItems.ChallengeScroll, i, "challengeScrollComplete");
					else registerItemToMesher(TragicItems.ChallengeScroll, i, "challengeScrollInProgress");
				}
			}
		}
	}

	@Override
	public EntityPlayer getPlayerFromMessageCtx(MessageContext ctx)
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	private static void registerItemToMesher(Item item, int meta, String location)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(DOMAIN + location, "inventory"));
	}

	private static void registerItemWithCustomDefinition(Item item, ItemMeshDefinition def)
	{
		ModelLoader.setCustomMeshDefinition(item, def);
	}

	private static void registerAmuletToBakery(Item item)
	{
		ModelBakery.registerItemVariants(item, convertToResource(getPrefixedArray(amulets)));
	}

	private static void registerAmuletToMesher(Item item)
	{
		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				String def = ((ItemAmulet) stack.getItem()).amuletType == EnumAmuletType.NORMAL ? DOMAIN + amulets[0] : DOMAIN + amulets[3];
				if (!stack.hasTagCompound()) return new ModelResourceLocation(def, "inventory");

				if (stack.getTagCompound().hasKey("amuletLevel"))
				{
					byte level = stack.getTagCompound().getByte("amuletLevel");
					return new ModelResourceLocation(DOMAIN + amulets[level], "inventory");
				}
				return new ModelResourceLocation(def, "inventory");
			}
		});
	}

	private static void registerArmorToBakery(Item item, String s)
	{
		ModelBakery.registerItemVariants(item, convertToResource(getPrefixedArray(new String[] {s, s + "2"})));
	}

	private static void registerArmorToMesher(Item item, final String s)
	{
		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {

				String s2 = stack.getItemDamage() >= stack.getMaxDamage() * 2 / 3 ? DOMAIN + s + "2" : DOMAIN + s;
				return new ModelResourceLocation(s2, "inventory");
			}
		});
	}

	private static void registerBlockToMesher(Block block, int meta, String location)
	{
		registerItemToMesher(Item.getItemFromBlock(block), meta, location);
	}

	private static void registerBlockToBakery(Block block, String... names)
	{
		registerItemToBakery(Item.getItemFromBlock(block), names);
	}

	private static void registerItemToBakery(Item item, String... names)
	{
		ModelBakery.registerItemVariants(item, convertToResource(names));
	}

	private static void registerWeaponToBakery(Item item, String name)
	{
		ModelBakery.registerItemVariants(item, new ResourceLocation(DOMAIN + name), new ResourceLocation(DOMAIN + name + "Inventory"));
	}

	private static ResourceLocation[] convertToResource(String[] names)
	{
		ResourceLocation[] rls = new ResourceLocation[names.length];
		for (int i = 0; i < names.length; i++)
		{
			rls[i] = new ResourceLocation(names[i]);
		}

		return rls;
	}

	private static String[] getPrefixedArray(String[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++)
		{
			newArray[i] = DOMAIN + array[i];
		}

		return newArray;
	}

	private static String capitalize(String s)
	{
		char[] str = s.toCharArray();
		str[0] = Character.toTitleCase(str[0]);
		s = new String(str);
		return s;
	}

	private static String[] capitalizeArray(String s, String[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++)
		{
			newArray[i] = s + capitalize(array[i]);
		}

		return newArray;
	}

	private static void registerRender(Class oclass, IRenderFactory factory)
	{
		RenderingRegistry.registerEntityRenderingHandler(oclass, factory);
	}
}
