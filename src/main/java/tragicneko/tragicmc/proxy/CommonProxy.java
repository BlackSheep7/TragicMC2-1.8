package tragicneko.tragicmc.proxy;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBiome;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.TragicRecipes;
import tragicneko.tragicmc.client.gui.GuiAmuletInventory;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.DoomsdayManager;
import tragicneko.tragicmc.events.AchievementEvents;
import tragicneko.tragicmc.events.AmuletEvents;
import tragicneko.tragicmc.events.ChallengeItemEvents;
import tragicneko.tragicmc.events.DoomEvents;
import tragicneko.tragicmc.events.DropEvents;
import tragicneko.tragicmc.events.EnchantmentEvents;
import tragicneko.tragicmc.events.MiscEvents;
import tragicneko.tragicmc.events.PotionEvents;
import tragicneko.tragicmc.events.RespawnDoomEvents;
import tragicneko.tragicmc.events.ServerTickEvents;
import tragicneko.tragicmc.events.VanillaChangingEvents;
import tragicneko.tragicmc.inventory.ContainerAmulet;
import tragicneko.tragicmc.network.MessageAmulet;
import tragicneko.tragicmc.network.MessageAttack;
import tragicneko.tragicmc.network.MessageDoom;
import tragicneko.tragicmc.network.MessageFlight;
import tragicneko.tragicmc.network.MessageFrozenInput;
import tragicneko.tragicmc.network.MessageGui;
import tragicneko.tragicmc.network.MessageHandlerAmulet;
import tragicneko.tragicmc.network.MessageHandlerAttack;
import tragicneko.tragicmc.network.MessageHandlerDoom;
import tragicneko.tragicmc.network.MessageHandlerFlight;
import tragicneko.tragicmc.network.MessageHandlerFrozenInput;
import tragicneko.tragicmc.network.MessageHandlerGui;
import tragicneko.tragicmc.network.MessageHandlerSpawnParticle;
import tragicneko.tragicmc.network.MessageHandlerUseDoomsday;
import tragicneko.tragicmc.network.MessageHandlerUseRidable;
import tragicneko.tragicmc.network.MessageParticle;
import tragicneko.tragicmc.network.MessageUseDoomsday;
import tragicneko.tragicmc.network.MessageUseRidable;
import tragicneko.tragicmc.properties.PropertyAmulets;
import tragicneko.tragicmc.worldgen.FlowerWorldGen;
import tragicneko.tragicmc.worldgen.structure.TickBuilder;

public class CommonProxy implements IGuiHandler {

	public static SimpleNetworkWrapper net;
	private static final Logger logger = LogManager.getLogger(TragicMC.MODID);

	public static final int AMULET_GUI_ID = 0;
	public static File configDir = null;

	public void init(FMLInitializationEvent event){ }

	public void postInit(FMLPostInitializationEvent event) {
		if (TragicConfig.getBoolean("allowRandomWeaponLore") && TragicConfig.getBoolean("allowNonMobItems") && TragicConfig.getBoolean("allowRandomWeaponLore")) tragicneko.tragicmc.util.LoreHelper.registerLoreJson(configDir);
	}

	public void preInit(FMLPreInitializationEvent event) {
		TragicConfig.doConfigProcess(event);
		configDir = event.getModConfigurationDirectory();

		if (TragicConfig.getBoolean("allowPotions"))
		{
			TragicPotion.load();
			registerEvent(new PotionEvents());
		}

		if (TragicConfig.getBoolean("allowEnchantments")) 
		{
			TragicEnchantments.load();
			registerEvent(new EnchantmentEvents());
		}

		if (TragicConfig.getBoolean("allowSurvivalTab"))
		{
			TragicMC.Survival = (new CreativeTabs("tragicMCSurvival") {
				@Override
				public Item getTabIconItem() {
					return TragicItems.AwakeningStone;
				}
			});
		}

		TragicMC.Creative = (new CreativeTabs("tragicMCCreative") {
			@Override
			public Item getTabIconItem() {
				return TragicItems.NekoNekoWand;
			}
		});
		
		Doomsday.load();
		TragicBlocks.load();
		TragicItems.load();

		if (TragicConfig.getBoolean("allowPotions") && TragicConfig.getBoolean("allowNonMobBlocks") && TragicConfig.getBoolean("allowNonMobItems")) TragicPotion.setPotionIcons();
		if (TragicConfig.getBoolean("allowRecipes")) TragicRecipes.load();
		if (TragicConfig.getBoolean("allowAmulets")) registerEvent(new AmuletEvents());

		registerEvent(new MiscEvents());

		if (TragicConfig.getBoolean("allowDoom"))
		{
			registerEvent(new DoomEvents());
			registerEvent(new RespawnDoomEvents());
		}

		if (TragicConfig.getBoolean("allowDimensions"))
		{
			if (TragicConfig.getBoolean("allowCollision"))
			{
				if (DimensionManager.isDimensionRegistered(TragicConfig.getInt("collisionID")))
				{
					final int id = DimensionManager.getNextFreeDimId();
					TragicConfig.overrideObject("collisionID", id);
					TragicConfig.overrideObject("collisionProviderID", id);
				}

				DimensionManager.registerProviderType(TragicConfig.getInt("collisionProviderID"), tragicneko.tragicmc.dimension.TragicWorldProvider.class, TragicConfig.getBoolean("keepCollisionLoaded"));
				DimensionManager.registerDimension(TragicConfig.getInt("collisionID"), TragicConfig.getInt("collisionProviderID"));
				TragicMC.logInfo("Dimension (The Collision) was registered with an ID of " + TragicConfig.getInt("collisionID"));
			}

			if (TragicConfig.getBoolean("allowSynapse"))
			{
				if (DimensionManager.isDimensionRegistered(TragicConfig.getInt("synapseID")))
				{
					final int id = DimensionManager.getNextFreeDimId();
					TragicConfig.overrideObject("synapseID", id);
					TragicConfig.overrideObject("synapseProviderID", id);
				}

				DimensionManager.registerProviderType(TragicConfig.getInt("synapseProviderID"), tragicneko.tragicmc.dimension.SynapseWorldProvider.class, TragicConfig.getBoolean("keepSynapseLoaded"));
				DimensionManager.registerDimension(TragicConfig.getInt("synapseID"), TragicConfig.getInt("synapseProviderID"));
				TragicMC.logInfo("Dimension (Synapse) was registered with an ID of " + TragicConfig.getInt("synapseID"));
			}

			if (TragicConfig.getBoolean("allowNekoHomeworld"))
			{
				if (DimensionManager.isDimensionRegistered(TragicConfig.getInt("nekoHomeworldID")))
				{
					final int id = DimensionManager.getNextFreeDimId();
					TragicConfig.overrideObject("nekoHomeworldID", id);
					TragicConfig.overrideObject("nekoHomeworldProviderID", id);
				}
				DimensionManager.registerProviderType(TragicConfig.getInt("nekoHomeworldProviderID"), tragicneko.tragicmc.dimension.NekoHomeworldProvider.class, TragicConfig.getBoolean("keepNekoHomeworldLoaded"));
				DimensionManager.registerDimension(TragicConfig.getInt("nekoHomeworldID"), TragicConfig.getInt("nekoHomeworldProviderID"));
				TragicMC.logInfo("Dimension (Neko Homeworld) was registered with an ID of " + TragicConfig.getInt("nekoHomeworldID"));
			}
			/* 
			DimensionManager.registerProviderType(5, tragicneko.tragicmc.dimension.WildsWorldProvider.class, false);
			DimensionManager.registerDimension(5, 5);
			TragicMC.logInfo("Dimension (The Wilds) was registered with an ID of " + 5); */

			TragicBiome.load();
			MinecraftForge.ORE_GEN_BUS.register(new tragicneko.tragicmc.events.MiscEvents());
		}

		TragicEntities.load();
		if (TragicConfig.getBoolean("allowMobs"))
		{
			tragicneko.tragicmc.util.EntityDropHelper.fill();
			if (TragicConfig.getBoolean("allowIre")) registerEvent(new ServerTickEvents());
		}

		if (TragicConfig.getBoolean("allowChallengeScrolls") && TragicConfig.getBoolean("allowNonMobItems") && TragicConfig.getBoolean("allowNonMobBlocks") && TragicConfig.getBoolean("allowMobs") && TragicConfig.getBoolean("allowDimensions"))
		{
			TragicItems.initializeChallengeItem();
			registerEvent(new ChallengeItemEvents());
		}
		else if (TragicConfig.getBoolean("allowChallengeScrolls"))
		{
			TragicMC.logWarning("Challenge Scrolls are enabled in config but are disabled due to certain things being disabled. This is to prevent game crashes from ocurring.");
			TragicConfig.overrideObject("allowChallengeScrolls", false);
		}

		if (TragicConfig.getBoolean("allowNonMobItems") && TragicConfig.getBoolean("allowNonMobBlocks")) registerEvent(new DropEvents());

		NetworkRegistry.INSTANCE.registerGuiHandler(TragicMC.getInstance(), this);
		if (TragicConfig.getBoolean("allowDoomsdays")) registerEvent(new DoomsdayManager());
		DoomsdayManager.clearRegistry();

		if (TragicConfig.getBoolean("allowVanillaChanges")) registerEvent(new VanillaChangingEvents());
		if (TragicConfig.getBoolean("allowOverworldOreGen")) GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.OverworldOreWorldGen(), 1);
		if (TragicConfig.getBoolean("allowNetherOreGen")) GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.NetherOreWorldGen(), 2);
		if (TragicConfig.getBoolean("allowFlowerGen")) GameRegistry.registerWorldGenerator(new FlowerWorldGen(), 3);

		if (TragicConfig.getBoolean("allowDimensions") && TragicConfig.getBoolean("allowFlowerGen"))
		{
			FlowerWorldGen.allowedBiomes.add(TragicBiome.PaintedClearing);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.PaintedForest);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.PaintedHills);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.PaintedPlains);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.AshenBadlands);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.AshenHills);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.AshenMountains);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.DarkForest);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.DarkForestHills);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.DarkMarsh);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.HallowedHills);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.HallowedForest);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.HallowedCliffs);
			FlowerWorldGen.allowedBiomes.add(TragicBiome.HallowedPrarie);
		}

		if (TragicConfig.getBoolean("allowStructureGen"))
		{
			GameRegistry.registerWorldGenerator(new tragicneko.tragicmc.worldgen.StructureWorldGen(), 10);
			if (TragicConfig.getBoolean("allowTickBuilder")) registerEvent(new TickBuilder(null));
		}

		if (TragicConfig.getBoolean("allowNetwork"))
		{
			net = new SimpleNetworkWrapper(TragicMC.MODID);
			net.registerMessage(MessageHandlerDoom.class, MessageDoom.class, 0, Side.CLIENT);
			net.registerMessage(MessageHandlerAmulet.class, MessageAmulet.class, 1, Side.CLIENT);
			net.registerMessage(MessageHandlerGui.class, MessageGui.class, 2, Side.SERVER);
			net.registerMessage(MessageHandlerUseDoomsday.class, MessageUseDoomsday.class, 3, Side.SERVER);
			net.registerMessage(MessageHandlerFlight.class, MessageFlight.class, 4, Side.CLIENT);
			net.registerMessage(MessageHandlerAttack.class, MessageAttack.class, 5, Side.SERVER);
			net.registerMessage(MessageHandlerSpawnParticle.class, MessageParticle.class, 6, Side.CLIENT);
			net.registerMessage(MessageHandlerUseRidable.class, MessageUseRidable.class, 7, Side.SERVER);
			net.registerMessage(MessageHandlerFrozenInput.class, MessageFrozenInput.class, 8, Side.SERVER);
		}

		if (TragicConfig.getBoolean("allowAchievements") && TragicConfig.getBoolean("allowNonMobItems") && TragicConfig.getBoolean("allowNonMobBlocks") && TragicConfig.getBoolean("allowChallengeScrolls") && TragicConfig.getBoolean("allowAmulets") && TragicConfig.getBoolean("allowDoom") && TragicConfig.getBoolean("allowDoomsdays") && TragicConfig.getBoolean("allowDimensions")) //register achievements after everything else is processed
		{
			TragicAchievements.load(); 
			registerEvent(new AchievementEvents());
		}
		else if (TragicConfig.getBoolean("allowAchievements"))
		{
			TragicMC.logWarning("Achievements are enabled in config but are disabled due to certain blocks and items being disabled. This is to prevent game crashes from ocurring.");
			TragicConfig.overrideObject("allowAchievements", false);
		}
	}

	@Override
	public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z)
	{
		if (guiId == AMULET_GUI_ID && PropertyAmulets.get(player) != null)
		{
			return new ContainerAmulet(player, player.inventory, PropertyAmulets.get(player).inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z)
	{
		if (guiId == AMULET_GUI_ID && PropertyAmulets.get(player) != null)
		{
			return new GuiAmuletInventory(player, player.inventory, PropertyAmulets.get(player).inventory);
		}
		return null;
	}

	public EntityPlayer getPlayerFromMessageCtx(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}

	public static void registerEvent(Object o)
	{
		MinecraftForge.EVENT_BUS.register(o);
	}

	public Logger getLogger() {
		return logger;
	}
}
