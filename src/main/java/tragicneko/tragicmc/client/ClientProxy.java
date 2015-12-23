package tragicneko.tragicmc.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import org.lwjgl.input.Keyboard;

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
import tragicneko.tragicmc.client.render.RenderEpicWeapon;
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
		this.initRenders();

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

		//Weapon models
		if (TragicConfig.allowWeaponModels)
		{
			MinecraftForgeClient.registerItemRenderer(TragicItems.ReaperScythe, new RenderEpicWeapon(0, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.Butcher, new RenderEpicWeapon(1, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.DragonFang, new RenderEpicWeapon(2, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.Thardus, new RenderEpicWeapon(3, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.Splinter, new RenderEpicWeapon(4, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.Paranoia, new RenderEpicWeapon(5, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.CelestialAegis, new RenderEpicWeapon(6, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.Titan, new RenderEpicWeapon(7, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.SilentHellraiser, new RenderEpicWeapon(8, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.Sentinel, new RenderEpicWeapon(9, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.NekoLauncher, new RenderEpicWeapon(10, mc));
			MinecraftForgeClient.registerItemRenderer(TragicItems.IreNetParticleCannon, new RenderEpicWeapon(11, mc));
		}

		//Projectile renders
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowingRock.class, new RenderProjectile(rm, 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinbomb.class, new RenderProjectile(rm, 2));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargePumpkinbomb.class, new RenderProjectile(rm, 3));
		RenderingRegistry.registerEntityRenderingHandler(EntityPoisonBarb.class, new RenderProjectile(rm, 4));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoRocket.class, new RenderProjectile(rm, 5));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoStickyBomb.class, new RenderProjectile(rm, 6));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoClusterBomb.class, new RenderProjectile(rm, 7));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoMiniBomb.class, new RenderProjectile(rm, 8));
		RenderingRegistry.registerEntityRenderingHandler(EntitySolarBomb.class, new RenderProjectile(rm, 9));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiritCast.class, new RenderProjectile(rm, 10));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpore.class, new RenderProjectile(rm, 11));
		RenderingRegistry.registerEntityRenderingHandler(EntityBanana.class, new RenderProjectile(rm, 12));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargeRock.class, new RenderLargeRock(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityIcicle.class, new RenderProjectile(rm, 14));
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeBomb.class, new RenderProjectile(rm, 15));
		RenderingRegistry.registerEntityRenderingHandler(EntityStarShard.class, new RenderProjectile(rm, 16));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkLightning.class, new RenderProjectile(rm, 17));
		RenderingRegistry.registerEntityRenderingHandler(EntityPitchBlack.class, new RenderProjectile(rm, 18));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkEnergy.class, new RenderProjectile(rm, 19));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkMortor.class, new RenderProjectile(rm, 20));
		RenderingRegistry.registerEntityRenderingHandler(EntityWebBomb.class, new RenderProjectile(rm, 21));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrystalMortor.class, new RenderProjectile(rm, 22));
		RenderingRegistry.registerEntityRenderingHandler(EntityOverlordMortor.class, new RenderProjectile(rm, 23));
		RenderingRegistry.registerEntityRenderingHandler(EntityIreEnergy.class, new RenderProjectile(rm, 24));

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
		RenderingRegistry.registerEntityRenderingHandler(EntityAegar.class, new RenderAegar());
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
	
	@Override
	public void preInitRenders() {
		if (!TragicConfig.allowNonMobItems)
		{
			registerItemToBakery(TragicItems.SpawnEgg, "something", "somethingelse", "somethingmore");
			registerItemToBakery(TragicItems.Projectile, "something", "somethingelse", "somethingmore");
		}
	}

	public void initRenders() {
		//Mesher for new block/item registrations in 1.8
		Item ib; //Itemblock for block registrations
		
		if (!TragicConfig.allowNonMobItems)
		{
			registerItemToMesher(TragicItems.SpawnEgg, ZERO, "spawn_egg");
			registerItemToMesher(TragicItems.BowOfJustice, ZERO, "bow");
			registerItemToMesher(TragicItems.SwordOfJustice, ZERO, "golden_sword");
			registerItemToMesher(TragicItems.NekoNekoWand, ZERO, "neko_neko_wand");
			registerItemToMesher(TragicItems.Projectile, ZERO, "projectile");
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

	public static void registerBlockToMesher(Block block, int meta, String location)
	{
		registerItemToMesher(Item.getItemFromBlock(block), meta, location);
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
