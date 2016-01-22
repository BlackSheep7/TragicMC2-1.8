package tragicneko.tragicmc.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import org.lwjgl.input.Keyboard;

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
import tragicneko.tragicmc.client.render.RenderProjectile;
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
import tragicneko.tragicmc.client.render.mob.RenderCryse;
import tragicneko.tragicmc.client.render.mob.RenderErkel;
import tragicneko.tragicmc.client.render.mob.RenderFusea;
import tragicneko.tragicmc.client.render.mob.RenderJabba;
import tragicneko.tragicmc.client.render.mob.RenderMob;
import tragicneko.tragicmc.client.render.mob.RenderMobTransparent;
import tragicneko.tragicmc.client.render.mob.RenderNorVox;
import tragicneko.tragicmc.client.render.mob.RenderPirah;
import tragicneko.tragicmc.client.render.mob.RenderPumpkinhead;
import tragicneko.tragicmc.client.render.mob.RenderRagr;
import tragicneko.tragicmc.client.render.mob.RenderStin;
import tragicneko.tragicmc.client.render.mob.RenderTox;
import tragicneko.tragicmc.client.render.mob.RenderWisp;
import tragicneko.tragicmc.dimension.SynapseSkyRenderer;
import tragicneko.tragicmc.dimension.TragicSkyRenderer;
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
import tragicneko.tragicmc.worldgen.structure.Structure;

public class ClientProxy extends CommonProxy {

	public static final String moddir = "tragicmc:";

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

	public static TragicMusicTicker musicTicker;
	private static final int ZERO = 0;

	@Override
	public void registerRenders()
	{
		Minecraft mc = Minecraft.getMinecraft();
		this.initRenders(); //1.8 block and item renders

		//Render Manager
		RenderManager rm = mc.getRenderManager();

		//Gui event registration
		if (TragicConfig.showDoomGui) MinecraftForge.EVENT_BUS.register(new GuiDoom(mc));
		if (TragicConfig.showAmuletStatusGui) MinecraftForge.EVENT_BUS.register(new GuiAmuletStatus(mc));

		//Keybinding registrations
		ClientRegistry.registerKeyBinding(useSpecial);
		ClientRegistry.registerKeyBinding(openAmuletGui);

		//Client-side event registration
		FMLCommonHandler.instance().bus().register(new ClientEvents());
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
		MinecraftForge.EVENT_BUS.register(new MouseEvents(mc));

		//Music
		musicTicker = new TragicMusicTicker(mc);
		MinecraftForge.EVENT_BUS.register(new TragicMusicTicker(mc));

		//Tile Entity render registration (shouldn't be used too often)
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulChest.class, new RenderSoulChest());

		//Projectile renders
		Item item = TragicItems.Projectile;
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowingRock.class, new RenderProjectile(rm, item, 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinbomb.class, new RenderProjectile(rm, item, 2));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargePumpkinbomb.class, new RenderProjectile(rm, item, 3));
		RenderingRegistry.registerEntityRenderingHandler(EntityPoisonBarb.class, new RenderProjectile(rm, item, 4));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoRocket.class, new RenderProjectile(rm, item, 5));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoStickyBomb.class, new RenderProjectile(rm, item, 6));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoClusterBomb.class, new RenderProjectile(rm, item, 7));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoMiniBomb.class, new RenderProjectile(rm, item, 8));
		RenderingRegistry.registerEntityRenderingHandler(EntitySolarBomb.class, new RenderProjectile(rm, item, 9));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiritCast.class, new RenderProjectile(rm, item, 10));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpore.class, new RenderProjectile(rm, item, 11));
		RenderingRegistry.registerEntityRenderingHandler(EntityBanana.class, new RenderProjectile(rm, item, 12));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargeRock.class, new RenderLargeRock(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityIcicle.class, new RenderProjectile(rm, item, 14));
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeBomb.class, new RenderProjectile(rm, item, 15));
		RenderingRegistry.registerEntityRenderingHandler(EntityStarShard.class, new RenderProjectile(rm, item, 16));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkLightning.class, new RenderProjectile(rm, item, 17));
		RenderingRegistry.registerEntityRenderingHandler(EntityPitchBlack.class, new RenderProjectile(rm, item, 18));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkEnergy.class, new RenderProjectile(rm, item, 19));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkMortor.class, new RenderProjectile(rm, item, 20));
		RenderingRegistry.registerEntityRenderingHandler(EntityWebBomb.class, new RenderProjectile(rm, item, 21));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrystalMortor.class, new RenderProjectile(rm, item, 22));
		RenderingRegistry.registerEntityRenderingHandler(EntityOverlordMortor.class, new RenderProjectile(rm, item, 23));
		RenderingRegistry.registerEntityRenderingHandler(EntityIreEnergy.class, new RenderProjectile(rm, item, 24));

		//Non projectile renders
		RenderingRegistry.registerEntityRenderingHandler(EntityStatue.class, new RenderStatue(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeDisruption.class, new RenderTimeDisruption(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkCrystal.class, new RenderDarkCrystal(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityGuardianShield.class, new RenderGuardianShield(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityDimensionalAnomaly.class, new RenderDimensionalAnomaly(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityLock.class, new RenderLock(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityDirectedLightning.class, new RenderDirectedLightning(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityNuke.class, new RenderNuke(rm));

		//Mob renders
		RenderingRegistry.registerEntityRenderingHandler(EntityJabba.class, new RenderJabba(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityJarra.class, new RenderMob(rm, new ModelJarra(), 0.655F, "Jarra", 1.585F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlague.class, new RenderMob(rm, new ModelPlague(), 0.115F, "Plague"));
		RenderingRegistry.registerEntityRenderingHandler(EntityGragul.class, new RenderMob(rm, new ModelGragul(), 0.115F, "Gragul"));
		RenderingRegistry.registerEntityRenderingHandler(EntityKragul.class, new RenderMob(rm, new ModelKragul(), 0.115F, "Kragul", 2.115F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMinotaur.class, new RenderMob(rm, new ModelMinotaur(), 0.337F, "Minotaur"));
		RenderingRegistry.registerEntityRenderingHandler(EntityRagr.class, new RenderRagr(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityInkling.class, new RenderMob(rm, new ModelInkling(), 0.175F, "Inkling"));
		RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinhead.class, new RenderPumpkinhead(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityTragicNeko.class, new RenderMob(rm, new ModelTragicNeko(), 0.295F, "TragicNeko"));
		RenderingRegistry.registerEntityRenderingHandler(EntityTox.class, new RenderTox(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityMagmox.class, new RenderMob(rm, new ModelTox(), 0.565F, "Magmox2", 1.625F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCryse.class, new RenderCryse(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityMegaCryse.class, new RenderMegaCryse(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityNorVox.class, new RenderNorVox(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityVoxStellarum.class, new RenderVoxStellarum(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPirah.class, new RenderPirah(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityStin.class, new RenderStin(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityGreaterStin.class, new RenderMob(rm, new ModelGreaterStin(), 0.675F, "GreaterStin"));
		RenderingRegistry.registerEntityRenderingHandler(EntityStinKing.class, new RenderMob(rm, new ModelStinKing(), 0.675F, "StinKing", 1.625F));
		RenderingRegistry.registerEntityRenderingHandler(EntityStinQueen.class, new RenderMob(rm, new ModelStinQueen(), 0.675F, "StinQueen", 1.225F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWisp.class, new RenderWisp(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityAbomination.class, new RenderMob(rm, new ModelAbomination(), 0.475F, "Abomination"));
		RenderingRegistry.registerEntityRenderingHandler(EntityErkel.class, new RenderErkel(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntitySirv.class, new RenderMob(rm, new ModelSirv(), 0.245F, "Sirv"));
		RenderingRegistry.registerEntityRenderingHandler(EntityPsygote.class, new RenderMob(rm, new ModelPsygote(), 0.565F, "Psygote"));
		RenderingRegistry.registerEntityRenderingHandler(EntityNanoSwarm.class, new RenderMob(rm, new ModelNanoSwarm(), 0.215F, "NanoSwarm", 1.545F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAegar.class, new RenderAegar(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityHunter.class, new RenderMob(rm, new ModelHunter(), 0.565F, "Hunter"));
		RenderingRegistry.registerEntityRenderingHandler(EntityHarvester.class, new RenderMob(rm, new ModelHarvester(), 0.785F, "Harvester", 1.555F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLockbot.class, new RenderMob(rm, new ModelLockbot(), 0.335F, "Lockbot"));
		RenderingRegistry.registerEntityRenderingHandler(EntitySeeker.class, new RenderMob(rm, new ModelSeeker(), 0.475F, "Seeker"));
		RenderingRegistry.registerEntityRenderingHandler(EntityIre.class, new RenderMobTransparent(rm, new ModelIre(), 0.335F, "Ire", 1.0F, 0.65F));
		RenderingRegistry.registerEntityRenderingHandler(EntityArchangel.class, new RenderMobTransparent(rm, new ModelArchangel(), 0.355F, "Archangel", 1.0F, 0.625F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFusea.class, new RenderFusea(rm, 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityVolatileFusea.class, new RenderFusea(rm, 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityRanmas.class, new RenderMob(rm, new ModelRanmas(), 0.775F, "Ranmas", 1.25F));
		RenderingRegistry.registerEntityRenderingHandler(EntityParasmite.class, new RenderMob(rm, new ModelHunter(), 0.565F, "Parasmite", 1.355F));
		RenderingRegistry.registerEntityRenderingHandler(EntityKurayami.class, new RenderMob(rm, new ModelKurayami(), 0.645F, "Kurayami", 0.825F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAvris.class, new RenderMob(rm, new ModelAvris(), 0.645F, "Avris"));

		//Boss renders
		RenderingRegistry.registerEntityRenderingHandler(EntityApis.class, new RenderApis(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathReaper.class, new RenderDeathReaper(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityKitsune.class, new RenderKitsune(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPolaris.class, new RenderPolaris(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityYeti.class, new RenderYeti(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeController.class, new RenderTimeController(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityEnyvil.class, new RenderEnyvil(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityClaymation.class, new RenderClaymation(rm));

		//Alpha renders
		RenderingRegistry.registerEntityRenderingHandler(EntityOverlordCocoon.class, new RenderOverlordCocoon(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityOverlordCombat.class, new RenderOverlordCombat(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityOverlordCore.class, new RenderOverlordCore(rm));
	}

	private static final String[] projectileItems = new String[] {"rock", "lava_rock", "pumpkinbomb", "large_pumpkinbomb",
		"poison_barb", "neko_rocket", "neko_sticky_bomb", "neko_cluster_bomb", "neko_mini_bomb", "solar_bomb",
		"spirit_cast", "spore", "banana", "large_rock", "icicle", "time_bomb", "star_shard", "dark_lightning",
		"pitch_black", "dark_energy", "dark_mortor", "web_bomb", "crystal_mortor", "overlord_mortor", "ire_energy"};

	private static final String[] compactOreItems = new String[] {"ruby", "sapphire", "tungsten", "mercury", "quicksilver"};
	private static final String[] quicksandItems = new String[] {"quicksand", "mud", "dredge", "sludge"};

	@Override
	public void preInitRenders() {
		if (!TragicConfig.allowNonMobBlocks)
		{

		}
		else
		{
			registerBlockToBakery(TragicBlocks.CompactOre, compactOreItems);
		}

		if (!TragicConfig.allowNonMobItems)
		{
			registerItemToBakery(TragicItems.Projectile, projectileItems);
		}
		else
		{

		}
	}

	public void initRenders() {
		//Mesher for new block/item registrations in 1.8
		Item ib; //Itemblock for block registrations
		int i; //for loops

		if (!TragicConfig.allowNonMobBlocks)
		{
			registerBlockToMesherIgnoreMeta(TragicBlocks.SummonBlock);
			registerBlockToMesher(TragicBlocks.Luminescence, ZERO, "luminescence");
			registerBlockToMesher(TragicBlocks.OverlordBarrier, ZERO, "overlord_barrier");
		}
		else
		{
			registerBlockToMesher(TragicBlocks.MercuryOre, ZERO, "red_mercury_ore");
			registerBlockToMesher(TragicBlocks.TungstenOre, ZERO, "tungsten_ore");
			registerBlockToMesher(TragicBlocks.RubyOre, ZERO, "ruby_ore");
			registerBlockToMesher(TragicBlocks.SapphireOre, ZERO, "sapphire_ore");
			for (i = 0; i < compactOreItems.length; i++) registerBlockToMesher(TragicBlocks.CompactOre, i, compactOreItems[i]);

			registerBlockToMesher(TragicBlocks.Wax, ZERO, "wax");
			registerBlockToMesher(TragicBlocks.Light, ZERO, "light");
			registerBlockToMesher(TragicBlocks.Candle, ZERO, "candle");
			registerBlockToMesher(TragicBlocks.PotatoBlock, ZERO, "potato_block");
			registerBlockToMesher(TragicBlocks.CarrotBlock, ZERO, "carrot_block");
			registerBlockToMesher(TragicBlocks.SandstonePressurePlate, ZERO, "sandstone_pressure_plate");
			registerBlockToMesher(TragicBlocks.NetherBrickPressurePlate, ZERO, "nether_brick_pressure_plate");
			registerBlockToMesher(TragicBlocks.SummonBlock, ZERO, "summon_block");
			for (i = 0; i < Structure.structureList.length; i++)
			{
				if (Structure.structureList[i] != null)
					registerBlockToMesher(TragicBlocks.StructureSeed, i, Structure.structureList[i].getUnlocalizedName()); //TODO change to get mesher name
			}
			for (i = 0; i < quicksandItems.length; i++) registerBlockToMesher(TragicBlocks.Quicksand, i, quicksandItems[i]);
		}

		if (!TragicConfig.allowNonMobItems)
		{
			registerItemToMesherIgnoreMeta(TragicItems.SpawnEgg);
			registerItemToMesher(TragicItems.BowOfJustice, ZERO, "bow_of_justice");
			registerItemToMesher(TragicItems.SwordOfJustice, ZERO, "sword_of_justice");
			registerItemToMesher(TragicItems.NekoNekoWand, ZERO, "neko_neko_wand");
			for (i = 0; i < projectileItems.length; i++) registerItemToMesher(TragicItems.Projectile, i, projectileItems[i]);
		}
		else
		{
			registerItemToMesher(TragicItems.RedMercury, ZERO, "red_mercury");
			registerItemToMesher(TragicItems.Quicksilver, ZERO, "quicksilver");
			registerItemToMesher(TragicItems.QuicksilverIngot, ZERO, "quicksilver_ingot");
			registerItemToMesher(TragicItems.Tungsten, ZERO, "tungsten");
			registerItemToMesher(TragicItems.Ruby, ZERO, "ruby");
			registerItemToMesher(TragicItems.Sapphire, ZERO, "sapphire");

			registerItemToMesher(TragicItems.SkullHelmet, ZERO, "skull_helmet");
			registerItemToMesher(TragicItems.SkullPlate, ZERO, "skull_plate");
			registerItemToMesher(TragicItems.SkullLegs, ZERO, "skull_legs");
			registerItemToMesher(TragicItems.SkullBoots, ZERO, "skull_boots");

			registerItemToMesher(TragicItems.HuntersCap, ZERO, "hunters_cap");
			registerItemToMesher(TragicItems.HuntersTunic, ZERO, "hunters_tunic");
			registerItemToMesher(TragicItems.HuntersLegs, ZERO, "hunters_legs");
			registerItemToMesher(TragicItems.HuntersBoots, ZERO, "hunters_boots");

			registerItemToMesher(TragicItems.MercuryHelm, ZERO, "mercury_helm");
			registerItemToMesher(TragicItems.MercuryPlate, ZERO, "mercury_plate");
			registerItemToMesher(TragicItems.MercuryLegs, ZERO, "mercury_legs");
			registerItemToMesher(TragicItems.MercuryBoots, ZERO, "mercury_boots");

			registerItemToMesher(TragicItems.TungstenHelm, ZERO, "tungsten_helm");
			registerItemToMesher(TragicItems.TungstenPlate, ZERO, "tungsten_plate");
			registerItemToMesher(TragicItems.TungstenLegs, ZERO, "tungsten_legs");
			registerItemToMesher(TragicItems.TungstenBoots, ZERO, "tungsten_boots");

			registerItemToMesher(TragicItems.LightHelm, ZERO, "light_helm");
			registerItemToMesher(TragicItems.LightPlate, ZERO, "light_plate");
			registerItemToMesher(TragicItems.LightLegs, ZERO, "light_legs");
			registerItemToMesher(TragicItems.LightBoots, ZERO, "light_boots");

			registerItemToMesher(TragicItems.DarkHelm, ZERO, "dark_helm");
			registerItemToMesher(TragicItems.DarkPlate, ZERO, "dark_plate");
			registerItemToMesher(TragicItems.DarkLegs, ZERO, "dark_legs");
			registerItemToMesher(TragicItems.DarkBoots, ZERO, "dark_boots");

			registerItemToMesher(TragicItems.OverlordHelm, ZERO, "overlord_helm");
			registerItemToMesher(TragicItems.OverlordPlate, ZERO, "overlord_plate");
			registerItemToMesher(TragicItems.OverlordLegs, ZERO, "overlord_legs");
			registerItemToMesher(TragicItems.OverlordBoots, ZERO, "overlord_boots");

			registerItemToMesher(TragicItems.MercuryDagger, ZERO, "mercury_dagger");
			registerItemToMesher(TragicItems.HuntersBow, ZERO, "hunters_bow");
			registerItemToMesher(TragicItems.PitchBlack, ZERO, "pitch_black");
			registerItemToMesher(TragicItems.BlindingLight, ZERO, "blinding_light");
			registerItemToMesher(TragicItems.GravitySpike, ZERO, "gravity_spike");
			registerItemToMesher(TragicItems.HarmonyBell, ZERO, "harmony_bell");
			registerItemToMesher(TragicItems.MourningStar, ZERO, "mourning_star");
			registerItemToMesher(TragicItems.BeastlyClaws, ZERO, "beastly_claws");
			registerItemToMesher(TragicItems.GuiltyThorn, ZERO, "guilty_thorn");
			registerItemToMesher(TragicItems.NekoLauncher, ZERO, "neko_launcher");
			registerItemToMesher(TragicItems.ReaperScythe, ZERO, "reaper_scythe");
			registerItemToMesher(TragicItems.WitheringAxe, ZERO, "withering_axe");
			registerItemToMesher(TragicItems.FrozenLightning, ZERO, "frozen_lightning");
			registerItemToMesher(TragicItems.CelestialAegis, ZERO, "celestial_aegis");
			registerItemToMesher(TragicItems.CelestialLongbow, ZERO, "celestial_longbow");
			registerItemToMesher(TragicItems.SilentHellraiser, ZERO, "silent_hellraiser");

			registerItemToMesher(TragicItems.Titan, ZERO, "titan");
			registerItemToMesher(TragicItems.Splinter, ZERO, "splinter");
			registerItemToMesher(TragicItems.Butcher, ZERO, "butcher");
			registerItemToMesher(TragicItems.Thardus, ZERO, "thardus");
			registerItemToMesher(TragicItems.Paranoia, ZERO, "paranoia");
			registerItemToMesher(TragicItems.DragonFang, ZERO, "dragon_fang");

			registerItemToMesher(TragicItems.Sentinel, ZERO, "sentinel");

			registerItemToMesher(TragicItems.Scythe, ZERO, "scythe");
			registerItemToMesher(TragicItems.EverlastingLight, ZERO, "everlasting_light");
			registerItemToMesher(TragicItems.Jack, ZERO, "jack");
			registerItemToMesher(TragicItems.TungstenJack, ZERO, "tungsten_jack");
			registerItemToMesher(TragicItems.CelestialJack, ZERO, "celestial_jack");

			registerItemToMesher(TragicItems.Ectoplasm, ZERO, "ectoplasm");
			registerItemToMesher(TragicItems.ToughLeather, ZERO, "tough_leather");
			registerItemToMesher(TragicItems.WovenSilk, ZERO, "woven_silk");
			registerItemToMesher(TragicItems.CrushedIce, ZERO, "crushed_ice");
			registerItemToMesher(TragicItems.LightParticles, ZERO, "light_particles");
			registerItemToMesher(TragicItems.DarkParticles, ZERO, "dark_particles");
			registerItemToMesher(TragicItems.IceOrb, ZERO, "ice_orb");
			registerItemToMesher(TragicItems.GravityOrb, ZERO, "gravity_orb");
			registerItemToMesher(TragicItems.FireOrb, ZERO, "fire_orb");
			registerItemToMesher(TragicItems.LightningOrb, ZERO, "lightning_orb");
			registerItemToMesher(TragicItems.AquaOrb, ZERO, "aqua_orb");
			registerItemToMesher(TragicItems.Thorns, ZERO, "thorns");
			registerItemToMesher(TragicItems.Horn, ZERO, "horn");
			registerItemToMesher(TragicItems.BoneMarrow, ZERO, "bone_marrow");
			registerItemToMesher(TragicItems.LightIngot, ZERO, "light_ingot");
			registerItemToMesher(TragicItems.DarkIngot, ZERO, "dark_ingot");
			registerItemToMesher(TragicItems.KitsuneTail, ZERO, "kitsune_tail");
			registerItemToMesher(TragicItems.DeathlyHallow, ZERO, "deathly_hallow");
			registerItemToMesher(TragicItems.EmpariahClaw, ZERO, "empariah_claw");
			registerItemToMesher(TragicItems.StarPieces, ZERO, "star_pieces");
			registerItemToMesher(TragicItems.TimeEssence, ZERO, "time_essence");
			registerItemToMesher(TragicItems.PureLight, ZERO, "pure_light");
			registerItemToMesher(TragicItems.LunarPowder, ZERO, "lunar_powder");
			registerItemToMesher(TragicItems.CelestialDiamond, ZERO, "celestial_diamond");
			registerItemToMesher(TragicItems.StinHorn, ZERO, "stin_horn");
			registerItemToMesher(TragicItems.WispParticles, ZERO, "wisp_particles");
			registerItemToMesher(TragicItems.IcyFur, ZERO, "icy_fur");
			registerItemToMesher(TragicItems.PureDarkness, ZERO, "pure_darkness");
			registerItemToMesher(TragicItems.LivingClay, ZERO, "living_clay");
			registerItemToMesher(TragicItems.CelestialSteel, ZERO, "celestial_steel");
			registerItemToMesher(TragicItems.SynapseCrystal, ZERO, "synapse_crystal");
			registerItemToMesher(TragicItems.CorruptedEye, ZERO, "corrupted_eye");
			registerItemToMesher(TragicItems.CorruptedEssence, ZERO, "corrupted_essence");
			registerItemToMesher(TragicItems.CorruptedEgg, ZERO, "corrupted_egg");
			registerItemToMesher(TragicItems.UnstableIsotope, ZERO, "unstable_isotope");
			registerItemToMesher(TragicItems.ArchangelFeather, ZERO, "archangel_feather");
			registerItemToMesher(TragicItems.WingsOfLiberation, ZERO, "wings_of_liberation");
			registerItemToMesher(TragicItems.IreNode, ZERO, "ire_node");
			registerItemToMesher(TragicItems.IreNetParticleCannon, ZERO, "irenet_particle_cannon");
			registerItemToMesher(TragicItems.CatalyticCompound, ZERO, "catalytic_compound");
			registerItemToMesher(TragicItems.InterestingResin, ZERO, "interesting_resin");
			registerItemToMesher(TragicItems.Chitin, ZERO, "chitin");
			registerItemToMesher(TragicItems.SoulExcess, ZERO, "soul_excess");
			registerItemToMesher(TragicItems.EtherealDistortion, ZERO, "ethereal_distortion");

			registerItemToMesher(TragicItems.ToxicAmalgation, ZERO, "toxic_amalgation");
			registerItemToMesher(TragicItems.ParadoxicalFormula, ZERO, "paradoxical_formula");
			registerItemToMesher(TragicItems.RadiatedInfusion, ZERO, "radiated_infusion");
			registerItemToMesher(TragicItems.ImpossibleReaction, ZERO, "impossible_reaction");
			registerItemToMesher(TragicItems.InfallibleMetal, ZERO, "infallible_metal");
			registerItemToMesher(TragicItems.ComplexCircuitry, ZERO, "complex_circuitry");
			registerItemToMesher(TragicItems.NauseatingConcoction, ZERO, "nauseating_concoction");
			registerItemToMesher(TragicItems.CreepyIdol, ZERO, "creepy_idol");
			registerItemToMesher(TragicItems.PurifiedEnergy, ZERO, "purified_energy");
			registerItemToMesher(TragicItems.Shadowskin, ZERO, "shadowskin");

			registerItemToMesher(TragicItems.IceCream, ZERO, "ice_cream");
			registerItemToMesher(TragicItems.Honeydrop, ZERO, "honeydrop");
			registerItemToMesher(TragicItems.Gloopii, ZERO, "gloopii");
			registerItemToMesher(TragicItems.Deathglow, ZERO, "deathglow");
			registerItemToMesher(TragicItems.Rice, ZERO, "rice");
			registerItemToMesher(TragicItems.Sushi, ZERO, "sushi");
			registerItemToMesher(TragicItems.GoldenSushi, ZERO, "golden_sushi");
			registerItemToMesher(TragicItems.Banana, ZERO, "banana");
			registerItemToMesher(TragicItems.BananaSplit, ZERO, "banana_split");
			registerItemToMesher(TragicItems.SkyFruit, ZERO, "skyfruit");
			registerItemToMesher(TragicItems.Tentacle, ZERO, "tentacle");
			registerItemToMesher(TragicItems.HoneydropSeeds, ZERO, "honeydrop_seeds");
			registerItemToMesher(TragicItems.DeathglowSeeds, ZERO, "deathglow_seeds");
			registerItemToMesher(TragicItems.SkyFruitSeeds, ZERO, "skyfruit_seeds");

			registerItemToMesher(TragicItems.RubyCharm, ZERO, "ruby_charm");
			registerItemToMesher(TragicItems.SapphireCharm, ZERO, "sapphire_charm");
			registerItemToMesher(TragicItems.DiamondCharm, ZERO, "diamond_charm");
			registerItemToMesher(TragicItems.EmeraldCharm, ZERO, "emerald_charm");
			registerItemToMesher(TragicItems.AwakeningStone, ZERO, "awakening_stone");
			registerItemToMesher(TragicItems.ObsidianOrb, ZERO, "obsidian_orb");
			registerItemToMesher(TragicItems.CryingObsidianOrb, ZERO, "crying_obsidian_orb");
			registerItemToMesher(TragicItems.BleedingObsidianOrb, ZERO, "zero_obsidian_orb");
			registerItemToMesher(TragicItems.DyingObsidianOrb, ZERO, "dying_obsidian_orb");

			registerItemToMesher(TragicItems.Talisman, ZERO, "talisman");
			registerItemToMesher(TragicItems.RainDanceTalisman, ZERO, "rain_dance_talisman");
			registerItemToMesher(TragicItems.SunnyDayTalisman, ZERO, "sunny_day_talisman");
			registerItemToMesher(TragicItems.ThunderstormTalisman, ZERO, "thunderstorm_talisman");
			registerItemToMesher(TragicItems.TimeManipulatorTalisman, ZERO, "time_manipulator_talisman");
			registerItemToMesher(TragicItems.MoonlightTalisman, ZERO, "moonlight_talisman");
			registerItemToMesher(TragicItems.SynthesisTalisman, ZERO, "synthesis_talisman");
			registerItemToMesher(TragicItems.HydrationTalisman, ZERO, "hydration_talisman");
			registerItemToMesher(TragicItems.LightningRodTalisman, ZERO, "lightning_rod_talisman");

			if (TragicConfig.allowAmulets)
			{
				registerItemToMesher(TragicItems.KitsuneAmulet, ZERO, "kitsune_amulet");
				registerItemToMesher(TragicItems.PeaceAmulet, ZERO, "peace_amulet");
				registerItemToMesher(TragicItems.YetiAmulet, ZERO, "yeti_amulet");
				registerItemToMesher(TragicItems.ClaymationAmulet, ZERO, "claymation_amulet");
				registerItemToMesher(TragicItems.ChickenAmulet, ZERO, "chicken_amulet");
				registerItemToMesher(TragicItems.MartyrAmulet, ZERO, "martyr_amulet");
				registerItemToMesher(TragicItems.PiercingAmulet, ZERO, "piercing_amulet");
				registerItemToMesher(TragicItems.BlacksmithAmulet, ZERO, "blacksmith_amulet");
				registerItemToMesher(TragicItems.ApisAmulet, ZERO, "apis_amulet");
				registerItemToMesher(TragicItems.CreeperAmulet, ZERO, "creeper_amulet");
				registerItemToMesher(TragicItems.ZombieAmulet, ZERO, "zombie_amulet");
				registerItemToMesher(TragicItems.SkeletonAmulet, ZERO, "skeleton_amulet");
				registerItemToMesher(TragicItems.SunkenAmulet, ZERO, "sunken_amulet");
				registerItemToMesher(TragicItems.TimeAmulet, ZERO, "time_amulet");
				registerItemToMesher(TragicItems.IceAmulet, ZERO, "ice_amulet");
				registerItemToMesher(TragicItems.SnowGolemAmulet, ZERO, "snow_golem_amulet");
				registerItemToMesher(TragicItems.IronGolemAmulet, ZERO, "iron_golem_amulet");
				registerItemToMesher(TragicItems.EndermanAmulet, ZERO, "enderman_amulet");
				registerItemToMesher(TragicItems.WitherAmulet, ZERO, "wither_amulet");
				registerItemToMesher(TragicItems.SpiderAmulet, ZERO, "spider_amulet");
				registerItemToMesher(TragicItems.StinAmulet, ZERO, "stin_amulet");
				registerItemToMesher(TragicItems.PolarisAmulet, ZERO, "polaris_amulet");
				registerItemToMesher(TragicItems.OverlordAmulet, ZERO, "overlord_amulet");
				registerItemToMesher(TragicItems.LightningAmulet, ZERO, "lightning_amulet");
				registerItemToMesher(TragicItems.ConsumptionAmulet, ZERO, "consumption_amulet");
				registerItemToMesher(TragicItems.SupernaturalAmulet, ZERO, "supernatural_amulet");
				registerItemToMesher(TragicItems.UndeadAmulet, ZERO, "undead_amulet");
				registerItemToMesher(TragicItems.EnderDragonAmulet, ZERO, "ender_dragon_amulet");
				registerItemToMesher(TragicItems.FuseaAmulet, ZERO, "fusea_amulet");
				registerItemToMesher(TragicItems.EnyvilAmulet, ZERO, "enyvil_amulet");
				registerItemToMesher(TragicItems.LuckAmulet, ZERO, "luck_amulet");

				registerItemToMesher(TragicItems.AmuletRelease, ZERO, "amulet_release");
			}



			if (TragicConfig.allowDoom)
			{
				registerItemToMesher(TragicItems.DoomConsume, ZERO, "doom_consume");
				registerItemToMesher(TragicItems.CooldownDefuse, ZERO, "cooldown_defuse");

				registerItemToMesher(TragicItems.BloodSacrifice, ZERO, "blood_sacrifice");
				registerItemToMesher(TragicItems.NourishmentSacrifice, ZERO, "nourishment_sacrifice");
			}

			registerItemToMesher(TragicItems.MobStatue, ZERO, "mob_statue");

			if (TragicConfig.allowDimension)
			{
				registerItemToMesher(TragicItems.DimensionalKey, ZERO, "dimensional_key");
				registerItemToMesher(TragicItems.DimensionalKeyEnd, ZERO, "dimensional_key_end");
				registerItemToMesher(TragicItems.DimensionalKeyNether, ZERO, "dimensional_key_nether");
				registerItemToMesher(TragicItems.DimensionalKeySynapse, ZERO, "dimensional_key_synapse");
				registerItemToMesher(TragicItems.DimensionalKeyWilds, ZERO, "dimensional_key_wilds");
				registerItemToMesher(TragicItems.SynapseLink, ZERO, "synapse_link");
			}

			if (TragicConfig.allowDoomsdays)
			{
				registerItemToMesher(TragicItems.DoomsdayScroll, ZERO, "doomsday_scroll");
			}

			registerItemToMesher(TragicItems.BowOfJustice, ZERO, "bow_of_justice");
			registerItemToMesher(TragicItems.SwordOfJustice, ZERO, "sword_of_justice");

			if (TragicConfig.allowGeneratorItems)
			{
				registerItemToMesher(TragicItems.Generator, ZERO, "generator_item");
			}

			registerItemToMesher(TragicItems.NekoNekoWand, ZERO, "neko_neko_wand");
			registerItemToMesher(TragicItems.SoundExtrapolator, ZERO, "sound_extrapolator");

			if (TragicConfig.allowMobs)
			{
				registerItemToMesherIgnoreMeta(TragicItems.SpawnEgg);
			}

			for (i = 0; i < projectileItems.length; i++)
				registerItemToMesher(TragicItems.Projectile, i, projectileItems[i]);
		}
	}

	@Override
	public EntityPlayer getPlayerFromMessageCtx(MessageContext ctx)
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	public static void registerItemToMesher(Item item, int meta, String location)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(moddir + location, "inventory"));
	}

	public static void registerItemToMesherIgnoreMeta(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, ZERO, new ModelResourceLocation("spawn_egg", "inventory"));
	}

	public static void registerBlockToMesher(Block block, int meta, String location)
	{
		registerItemToMesher(Item.getItemFromBlock(block), meta, location);
	}

	public static void registerBlockToMesherIgnoreMeta(Block block)
	{
		registerItemToMesherIgnoreMeta(Item.getItemFromBlock(block));
	}

	public static void registerBlockToBakery(Block block, String... names)
	{
		registerItemToBakery(Item.getItemFromBlock(block), names);
	}

	public static void registerItemToBakery(Item item, String... names)
	{
		ModelBakery.addVariantName(item, names);
	}
}
