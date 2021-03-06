package tragicneko.tragicmc.config;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicConfig.MobStat;

public class ConfigDefault extends TragicConfig {

	public static void load(Configuration config)
	{
		config.load();

		ConfigCategory cat; //The category currently being loaded from the config
		Property prop; //The value currently being parsed
		byte m; //a byte mapping to make it easier for my array hypervisors to work
		String s = ""; //what will be used by each property to keep the config string and the registration string the same

		registerObject("allowNonMobItems", true);
		registerObject("allowNonMobBlocks", true);
		registerObject("allowNetwork", true);
		registerObject("allowRecipes", true);
		registerObject("allowSurvivalTab", true);

		cat = config.getCategory(CAT_BLANKET);
		cat.setComment("These disable all options beneath them if set to false.");

		s = "allowAchievements";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Are Achievements and Achievement options allowed?";
		registerObject(s, prop.getBoolean(true));

		s = "allowAmulets";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Are Amulets, Amulet Modifiers, Amulet Guis and Amulet recipes allowed?";
		registerObject(s, prop.getBoolean(true));

		s = "allowDimensions";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Are the mod-exclusive Dimensions and Biomes allowed?";
		registerObject(s, prop.getBoolean(true));

		s = "allowDoom";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Are Doom, Doomsdays and non-Doomsday Weapon/Armor abilities allowed?";
		registerObject(s, prop.getBoolean(true));

		s = "allowEnchantments";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Are mod-exclusive Weapon and Armor Enchantments allowed?";
		registerObject(s, prop.getBoolean(true));

		s = "allowMobs";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Are mod-exclusive Mobs and Bosses allowed?";
		registerObject(s, prop.getBoolean(true));

		s = "allowPotions";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Are mod-exclusive Potion Effects allowed and can they be used by the mod's Mobs and various effects?";
		registerObject(s, prop.getBoolean(true));

		s = "allowVanillaChanges";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Are changes to Vanilla like increasing Vanilla mob health and giving extra abilities to Vanilla mobs allowed?";
		registerObject(s, prop.getBoolean(true));

		s = "allowWorldGen";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Is the mod able to execute any of it's non-Ore WorldGen?";
		registerObject(s, prop.getBoolean(true));

		cat = config.getCategory(CAT_AMULET);
		cat.setComment("These allow you to toggle various aspects of Amulets.");

		prop = config.get(cat.getName(), "allowAmuletLeveling", true);
		prop.comment = "Are amulets able to be leveled up by combining them in a crafting recipe?";
		amuletConfig[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowAmuletCrafting", true);
		prop.comment = "Can amulets be crafted via raw materials?";
		amuletConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "requireAmuletSlotUnlock", true);
		prop.comment = "Do you need to use an Amulet Release to unlock a new Amulet slot?";
		amuletConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowAmuletBossKillCharge", true);
		prop.comment = "Will your Amulets repair some durability after killing a Boss?";
		amuletConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowAmuletModifiers", true);
		prop.comment = "Will Amulets have random modifiers that affect their user while equipped?";
		amuletConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowAmuletDeathDrops", true);
		prop.comment = "Do Amulets drop off of you on death?";
		amuletConfig[++m] = prop.getBoolean(true);

		s = "amuletMaxSlots";
		prop = config.get(cat.getName(), s, 3);
		prop.comment = "The maximum amount of slots you can have unlocked.";
		registerObject(s, clamp(prop.getInt(3), 0, 3));

		s = "amuletOverallRarity";
		prop = config.get(cat.getName(), s, 5);
		prop.comment = "The chance of you getting an Amulet in a chest, higher value is higher chance.";
		registerObject(s, clamp(prop.getInt(5), 1, 250));

		s = "amuletReleaseRarity";
		prop = config.get(cat.getName(), s, 5);
		prop.comment = "The chance of you getting an Amulet Release in a chest, higher value is higher chance.";
		registerObject(s, clamp(prop.getInt(5), 1, 250));

		s = "amuletModChance";
		prop = config.get(cat.getName(), s, 54);
		prop.comment = "Affects the chance of getting at least one Modifier on an Amulet. Lower value is higher chance.";
		registerObject(s, clamp(prop.getInt(54), 1, 100));

		s = "amuletModChance2";
		prop = config.get(cat.getName(), s, 79);
		prop.comment = "Affects the chance of getting a second Modifier on an Amulet. Lower value is higher chance.";
		registerObject(s, clamp(prop.getInt(79), 1, 100));

		s = "amuletModChance3";
		prop = config.get(cat.getName(), s, 89);
		prop.comment = "Affects the chance of getting a third Modifier on an Amulet. Lower value is higher chance.";
		registerObject(s, clamp(prop.getInt(89), 1, 100));

		s = "amuletStartSlots";
		prop = config.get(cat.getName(), s, 1);
		prop.comment = "The amount of slots you have unlocked at the start.";
		registerObject(s, clamp(prop.getInt(1), 0, 3));

		cat = config.getCategory(CAT_AMUEFFECT);
		cat.setComment("Disable or enable certain Amulet Effects.");

		prop = config.get(cat.getName(), "peace", true);
		amuletEffects[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "yeti", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "claymation", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "chicken", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "blacksmith", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "creeper", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "zombie", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "skeleton", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "ice", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "snowGolem", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "ironGolem", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "spider", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "stin", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "supernatural", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "fusea", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "luck", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "kitsune", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "martyr", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "piercing", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "apis", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "sunken", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "enderman", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "polaris", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "lightning", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "consumption", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "undead", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "enderDragon", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "time", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "wither", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "overlord", true);
		amuletEffects[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "enyvil", true);
		amuletEffects[++m] = prop.getBoolean(true);

		cat = config.getCategory(CAT_CREATIVE);
		cat.setComment("Change various aspects of the Creative mode only items in the mod.");

		s = "sphereGenUsesFilter";
		prop = config.get(cat.getName(), s, false);
		prop.comment = "Does the Sphere Generator use your custom block filter?";
		registerObject(s, prop.getBoolean(false));

		s = "sphereFilter";
		prop = config.get(cat.getName(), s, new String[] {"tnt", "stone", "cobblestone", "TragicMC:darkStone"});
		prop.comment = "The blocks that can be used in the Sphere Generator, must be the Vanilla blockname or must be appended with the mod id for use of modded blocks. This is not meta-sensitive.";
		registerObject(s, prop.getStringList());

		s = "sphereSize";
		prop = config.get(cat.getName(), s, 6.5);
		prop.comment = "The radius of the sphere that the Sphere Generator uses.";
		registerObject(s, prop.getDouble(6.5D));

		s = "eraserUsesFilter";
		prop = config.get(cat.getName(), s, false);
		prop.comment = "Does the Sphere Eraser use your custom block filter?";
		registerObject(s, prop.getBoolean(false));

		s = "eraserFilter";
		prop = config.get(cat.getName(), s, new String[] {"air"});
		prop.comment = "The blocks that are ignored by the Sphere Eraser, must be the Vanilla blockname or must be appended with the mod id for use of modded blocks. This is not meta-sensitive.";
		registerObject(s, prop.getStringList());

		s = "eraserSize";
		prop = config.get(cat.getName(), s, 6.5D);
		prop.comment = "The radius of the sphere that the Sphere Eraser uses.";
		registerObject(s, prop.getDouble(6.5D));

		s = "spikeGenUsesFilter";
		prop = config.get(cat.getName(), s, false);
		prop.comment = "Does the Spike Generator use your custom block filter?";
		registerObject(s, prop.getBoolean(false));

		s = "spikeFilter";
		prop = config.get(cat.getName(), s, new String[] {"TragicMC:darkStone"});
		prop.comment = "The blocks that spikes can be generated out of in the Spike Generator, must be Vanilla blockname or must be appended with mod id for use of modded blocks. This is not meta-sensitive.";
		registerObject(s, prop.getStringList());

		s = "spikeSize";
		prop = config.get(cat.getName(), s, 2.5D);
		prop.comment = "The starting radius of a spike that the Spike Generator uses.";
		registerObject(s, prop.getDouble(2.5D));

		s = "spikeRegression";
		prop = config.get(cat.getName(), s, 0.96977745D);
		prop.comment = "The rate the spike will regress as it gets generated higher up into the air, used by the Spike Generator.";
		registerObject(s, prop.getDouble(0.96977745D));

		s = "spikeCutoff";
		prop = config.get(cat.getName(), s, 0.36943755D);
		prop.comment = "The minimum radius that the spike will stop generating at, used by the Spike Generator.";
		registerObject(s, prop.getDouble(0.36943755D));

		s = "voidPitUsesFilter";
		prop = config.get(cat.getName(), s, false);
		prop.comment = "Does the Void Pit Generator ignore any blocks when generated?";
		registerObject(s, prop.getBoolean(false));

		s = "voidPitFilter";
		prop = config.get(cat.getName(), s, new String[] {"air"});
		prop.comment = "The blocks that void pits can delete during generation via the Void Pit Generator, must be Vanilla blockname or must be appended with mod id for use of modded blocks. This is not meta-sensitive.";
		registerObject(s, prop.getStringList());

		s = "voidPitSize";
		prop = config.get(cat.getName(), s, 12.5D);
		prop.comment = "The radius that the Void Pit Generator will use.";
		registerObject(s, prop.getDouble(12.5D));

		s = "explosionBaseSize";
		prop = config.get(cat.getName(), s, 3.5D);
		prop.comment = "The base size of the explosions used by the Explosion Generator";
		registerObject(s, prop.getDouble(3.5D));

		s = "explosionSizeVariation";
		prop = config.get(cat.getName(), s, 5.0D);
		prop.comment = "The variation applied to the base size of explosions created via the Explosion Generator";
		registerObject(s, prop.getDouble(5.0F));

		cat = config.getCategory(CAT_DIMENSION);
		cat.setComment("Change and toggle aspects of the mod-exclusive Dimensions.");

		prop = config.get(cat.getName(), "allowSynapse", true);
		prop.comment = "Is the Synapse Dimension allowed?";
		dimensionConfig[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowCollision", true);
		prop.comment = "Is the Collision Dimension allowed?";
		dimensionConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowCollisionRespawn", true);
		prop.comment = "Can you respawn in the Collision or will you be forced back to the Overworld?";
		dimensionConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowSynapseRespawn", true);
		prop.comment = "Can you respawn in the Synapse or will you be forced back to the Overworld?";
		dimensionConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "keepCollisionLoaded", false);
		prop.comment = "Will the Collision Dimension remain loaded when no one is there?";
		dimensionConfig[++m] = prop.getBoolean(false);

		prop = config.get(cat.getName(), "keepSynapseLoaded", false);
		prop.comment = "Will the Synapse Dimension remain loaded when no one is there?";
		dimensionConfig[++m] = prop.getBoolean(false);

		prop = config.get(cat.getName(), "allowSynapseVariants", true);
		prop.comment = "Can the Synapse generate with mini-Biomes?";
		dimensionConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowNekoHomeworld", true);
		prop.comment = "Is the Neko Homeworld Dimension allowed?";
		dimensionConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowNekoHomeworldRespawn", true);
		prop.comment = "Can you respawn in the Neko Homeworld or will you be forced back to the Overworld?";
		dimensionConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "keepNekoHomeworldLoaded", false);
		prop.comment = "Will the Neko Homeworld Dimension remain loaded when no one is there?";
		dimensionConfig[++m] = prop.getBoolean(false);

		s = "collisionID";
		prop = config.get(cat.getName(), s, 2);
		prop.comment = "The ID that the Collision Dimension will use.";
		registerObject(s, prop.getInt(2));

		s = "collisionProviderID";
		prop = config.get(cat.getName(), s, 2);
		prop.comment = "The ID that the Collision's provider will use, if you don't know what this is, keep it the same as the Collision's Dimension ID.";
		registerObject(s, prop.getInt(2));

		s = "synapseID";
		prop = config.get(cat.getName(), s, 3);
		prop.comment = "The ID that the Synapse Dimension will use.";
		registerObject(s, prop.getInt(3));

		s = "synapseProviderID";
		prop = config.get(cat.getName(), s, 3);
		prop.comment = "The ID that the Synapse's provider will use, if you don't know what this is, keep it the same as the Synapse's Dimension ID.";
		registerObject(s, prop.getInt(3));

		s = "collisionBiomeSize";
		prop = config.get(cat.getName(), s, 6);
		prop.comment = "How large the Collision's biomes generate.";
		registerObject(s, prop.getInt(6));

		s = "synapseVariantChance";
		prop = config.get(cat.getName(), s, 128);
		prop.comment = "The chance that a Synapse mini-biome will generate, higher number is lower chance to generate.";
		registerObject(s, prop.getInt(128));

		s = "nekoHomeworldID";
		prop = config.get(cat.getName(), s, 4);
		prop.comment = "The ID that the Neko Homeworld Dimension will use.";
		registerObject(s, prop.getInt(4));

		s = "nekoHomeworldProviderID";
		prop = config.get(cat.getName(), s, 4);
		prop.comment = "The ID that the Neko Homeworld's provider will use, if you don't know what this is, keep it the same as the Neko Homeworld's Dimension ID.";
		registerObject(s, prop.getInt(4));

		s = "nekoHomeworldBiomeSize";
		prop = config.get(cat.getName(), s, 8);
		prop.comment = "How large the Neko Homeworld's biomes generate.";
		registerObject(s, prop.getInt(8));

		cat = config.getCategory(CAT_BIOME);
		cat.setComment("Set biome ids and generation weights, higher weight is greater chance to generate out of that biome group. Setting a weight to 0 effectively removes a biome from the possible biomes to generate.");

		s = "decayingHillsID";
		prop = config.get(cat.getName(), s, findBiomeID(90));
		registerObject(s, prop.getInt(findBiomeID(90)));

		String s2 = s; s = "decayingValleyID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "decayingWastelandID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "decayingMountainsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "paintedForestID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "paintedPlainsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "paintedHillsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "paintedClearingID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "ashenMountainsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "ashenHillsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "ashenBadlandsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "starlitPrarieID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "starlitPlateausID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "starlitCliffsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "starlitLowlandsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "taintedSpikesID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "taintedLowlandsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "taintedRisesID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "taintedScarlandsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "taintedIslesID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "synapseBiomeID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "hallowedHillsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "hallowedForestID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "hallowedPrarieID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "hallowedCliffsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "scorchedWastelandsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "scorchedValleyID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "scorchedScarlandsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "corrodedSteppeID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "corrodedHeightsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "corrodedVeldID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "corrodedRunoffID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "corrodedFalloutID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "frozenTundraID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "frozenHillsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "frozenDepthsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "crystalID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "darkForestID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "darkForestHillsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "darkMarshID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "synapseDeadID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "synapseCorruptID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "nekoBarrensID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "nekoForestID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s2 = s; s = "nekoHeightsID";
		prop = config.get(cat.getName(), s, findBiomeID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findBiomeID(getInt(s2) + 1)));

		s = "decayingHillsWeight";
		prop = config.get(cat.getName(), s, 20);
		registerObject(s, clamp(prop.getInt(20), 0, 250));

		s = "decayingValleyWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 0, 250));

		s = "decayingWastelandWeight";
		prop = config.get(cat.getName(), s, 20);
		registerObject(s, clamp(prop.getInt(20), 0, 250));

		s = "decayingMountainsWeight";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, clamp(prop.getInt(10), 0, 250));

		s = "paintedForestWeight";
		prop = config.get(cat.getName(), s, 30);
		registerObject(s, clamp(prop.getInt(30), 0, 250));

		s = "paintedPlainsWeight";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, clamp(prop.getInt(10), 0, 250));

		s = "paintedHillsWeight";
		prop = config.get(cat.getName(), s, 20);
		registerObject(s, clamp(prop.getInt(20), 0, 250));

		s = "paintedClearingWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 0, 250));

		s = "ashenMountainsWeight";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, clamp(prop.getInt(10), 0, 250));

		s = "ashenHillsWeight";
		prop = config.get(cat.getName(), s, 20);
		registerObject(s, clamp(prop.getInt(20), 0, 250));

		s = "ashenBadlandsWeight";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 0, 250));

		s = "starlitPrarieWeight";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 0, 250));

		s = "starlitPlateausWeight";
		prop = config.get(cat.getName(), s, 15);
		registerObject(s, clamp(prop.getInt(15), 0, 250));

		s = "starlitCliffsWeight";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, clamp(prop.getInt(10), 0, 250));

		s = "starlitLowlandsWeight";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 0, 250));

		s = "taintedSpikesWeight";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 0, 250));

		s = "taintedLowlandsWeight";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, clamp(prop.getInt(10), 0, 250));

		s = "taintedRisesWeight";
		prop = config.get(cat.getName(), s, 15);
		registerObject(s, clamp(prop.getInt(15), 0, 250));

		s = "taintedScarlandsWeight";
		prop = config.get(cat.getName(), s, 15);
		registerObject(s, clamp(prop.getInt(15), 0, 250));

		s = "taintedIslesWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 0, 250));

		s = "hallowedHillsWeight";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, clamp(prop.getInt(10), 0, 250));

		s = "hallowedForestWeight";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 0, 250));

		s = "hallowedPrarieWeight";
		prop = config.get(cat.getName(), s, 20);
		registerObject(s, clamp(prop.getInt(20), 0, 250));

		s = "hallowedCliffsWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 0, 250));

		s = "scorchedWastelandsWeight";
		prop = config.get(cat.getName(), s, 15);
		registerObject(s, clamp(prop.getInt(15), 0, 250));

		s = "scorchedValleyWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 0, 250));

		s = "scorchedScarlandsWeight";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, clamp(prop.getInt(10), 0, 250));

		s = "corrodedSteppeWeight";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 0, 250));

		s = "corrodedHeightsWeight";
		prop = config.get(cat.getName(), s, 15);
		registerObject(s, clamp(prop.getInt(15), 0, 250));

		s = "corrodedVeldWeight";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, clamp(prop.getInt(10), 0, 250));

		s = "corrodedRunoffWeight";
		prop = config.get(cat.getName(), s, 20);
		registerObject(s, clamp(prop.getInt(10), 0, 250));

		s = "corrodedFalloutWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 0, 250));

		s = "frozenTundraWeight";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 0, 250));

		s = "frozenHillsWeight";
		prop = config.get(cat.getName(), s, 15);
		registerObject(s, clamp(prop.getInt(15), 0, 250));

		s = "frozenDepthsWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 0, 250));

		s = "crystalWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 0, 250));

		s = "darkForestWeight";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 0, 250));

		s = "darkForestHillsWeight";
		prop = config.get(cat.getName(), s, 15);
		registerObject(s, clamp(prop.getInt(15), 0, 250));

		s = "darkMarshWeight";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, clamp(prop.getInt(10), 0, 250));

		cat = config.getCategory(CAT_DOOM);
		cat.setComment("Modify aspects of Doom.");
		cat.setRequiresMcRestart(true);

		prop = config.get(cat.getName(), "allowDoomsdays", true);
		prop.comment = "Are Doomsdays able to be used?";
		doomConfig[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowInfluenceDoomsdays", true);
		prop.comment = "Can Influence Doomsdays be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowOverflowDoomsdays", true);
		prop.comment = "Can Overflow Doomsdays be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowCrisisDoomsdays", true);
		prop.comment = "Can Crisis Doomsdays be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowWorldShaperDoomsdays", true);
		prop.comment = "Can World Shaper Doomsdays be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowCombinationDoomsdays", true);
		prop.comment = "Can Combination Doomsdays be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowNonDoomsdayAbilities", true);
		prop.comment = "Can non-Doomsday Weapon and Armor abilities be used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowDoomIncrease", true);
		prop.comment = "Can the maximum Doom amount be increased by a Doom Consume?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowConsumeRefill", true);
		prop.comment = "Should Doom Consumes refill your Doom when used?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowDoomPainCharge", true);
		prop.comment = "Should your Doom increase when you take or inflict damage?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowDoomNaturalCharge", true);
		prop.comment = "Should your Doom recharge naturally?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowCrucialMoments", true);
		prop.comment = "Can Doomsdays have a chance to have extra effectiveness?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowBacklash", true);
		prop.comment = "Can Doomsdays have a chance to fail?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowCooldown", true);
		prop.comment = "Should Doomsdays inflict Global cooldown after use?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowDoomKillCharge", true);
		prop.comment = "Should killing enemies recharge your Doom?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowCooldownDefuse", true);
		prop.comment = "Can Cooldown Defuses be used to remove your Global cooldown?";
		doomConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowPartnerDoomsdays", false);
		prop.comment = "Can two people activate a Doomsday combination while near each other? (This hasn't been tested and you should report your results from use)";
		doomConfig[++m] = prop.getBoolean(false);

		prop = config.get(cat.getName(), "canCombineDoomsdayScrollsWithItems", false);
		prop.comment = "Can you combine a Doomsday scroll with a non-TragicMC weapon or armor piece to imbue it with a Doomsday?";
		doomConfig[++m] = prop.getBoolean(false);

		s = "maxDoomAmount";
		prop = config.get(cat.getName(), s, 500);
		prop.comment = "The highest Doom amount you can have.";
		registerObject(s, clampPositive(prop.getInt(500)));

		s = "doomRechargeRate";
		prop = config.get(cat.getName(), s, 1);
		prop.comment = "The speed at which you naturally recharge Doom. Maxes out at 20, which would be one recharge per tick essentially.";
		registerObject(s, clamp(prop.getInt(1), 1, 20));

		s = "doomConsumeRarity";
		prop = config.get(cat.getName(), s, 3);
		prop.comment = "The chance of getting a Doom Consume out of a chest, higher value is higher chance.";
		registerObject(s, clamp(prop.getInt(3), 1, 250));

		s = "cooldownDefuseRarity";
		prop = config.get(cat.getName(), s, 5);
		prop.comment = "The chance of getting a Cooldown Defuse out of a chest, higher value is higher chance.";
		registerObject(s, clamp(prop.getInt(5), 1, 250));

		s = "doomConsumeRefillAmount";
		prop = config.get(cat.getName(), s, 50);
		prop.comment = "The percentage of Doom that you'll refill upon use of a Doom Consume.";
		registerObject(s, clamp(prop.getInt(50), 1, 100));

		s = "cooldownDefuseRefillAmount";
		prop = config.get(cat.getName(), s, 30);
		prop.comment = "The amount of cooldown that you'll remove upon use of a Cooldown Defuse.";
		registerObject(s, clampPositive(prop.getInt(30)));

		s = "backlashChance";
		prop = config.get(cat.getName(), s, 5);
		prop.comment = "The chance that Backlash will occur, higher value is higher chance.";
		registerObject(s, clamp(prop.getInt(5), 1, 100));

		s = "crucialMomentChance";
		prop = config.get(cat.getName(), s, 5);
		prop.comment = "The chance that a Crucial Moment will occur, higher value is higher chance.";
		registerObject(s, clamp(prop.getInt(5), 1, 100));

		s = "doomConsumeIncreaseAmount";
		prop = config.get(cat.getName(), s, 100);
		prop.comment = "The amount of Max Doom you gain per Doom Consume use.";
		registerObject(s, clampPositive(prop.getInt(100)));

		s = "maxDoomStartAmount";
		prop = config.get(cat.getName(), s, 100);
		prop.comment = "The default amount of Max Doom that you start with.";
		registerObject(s, clampPositive(prop.getInt(100)));

		s = "doomRechargeAmount";
		prop = config.get(cat.getName(), s, 1);
		prop.comment = "The amount you recharge per recharge tick, also used by the Doom kill charge.";
		registerObject(s, clampPositive(prop.getInt(1)));

		s = "partnerDoomsdayDistance";
		prop = config.get(cat.getName(), s, 12);
		prop.comment = "How far away you can be from someone to activate a Partner Combination Doomsday.";
		registerObject(s, clampPositive(prop.getInt(1)));

		cat = config.getCategory(CAT_DOOMSDAYS);
		cat.setComment("Set whether each Doomsday is allowed, as well as set their cooldown and their Doom cost.");
		cat.setRequiresWorldRestart(false);

		prop = config.get(cat.getName(), "decayAllow", true);
		doomsdayAllow[m = 1] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "huntersInstinctAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "toxicityAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "berserkerAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "piercingLightAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "natureDrainAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "poisonBreakAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "snipeAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "rapidFireAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "pulseAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "lightShoveAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "fearAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "harmonizerAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "ravageAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "tormentAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "beastlyImpulsesAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "huntersInstinctAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "suicicalTendenciesAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "reaperLaughAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "realityAlterAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "skullCrusherAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "minerSkillsAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "freezeAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "moonlightSonataAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "flightOfTheValkyriesAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "titanfallAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "bloodlustAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "permafrostAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "purgeAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "lightningRushAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "marionetteAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "mindcrackAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "growthSpurtAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "blizzardAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "asphyxiateAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "fireRainAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "dragonsRoarAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "firestormAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "shotgunAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "guardiansCallAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "hardenAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "sharpenAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "flashAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "septicsAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "kurayamiAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "lifeShareAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "deathMarkAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "paradigmShiftAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "adrenalineAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "escapeAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "giftOfTheGodsAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "gamblerAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "soulstealerAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "parasiteAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "symbiosisAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "timeCollapseAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "magnetizerAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "ambienceAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "dimentiaAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "deleteAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "laserCutterAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "radiantLightAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "dangerZoneAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "supportAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "purifyAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "recallAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "shuffleAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "blinkAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "evacuateAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "medicAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "resurgenceAllow", true);
		doomsdayAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "decayCost", 40);
		doomsdayCost[m = 1] = clampPositive(prop.getInt(40));

		prop = config.get(cat.getName(), "huntersInstinctCost", 60);
		doomsdayCost[++m] = clampPositive(prop.getInt(60));

		prop = config.get(cat.getName(), "toxicityCost", 40);
		doomsdayCost[++m] = clampPositive(prop.getInt(40));

		prop = config.get(cat.getName(), "berserkerCost", 50);
		doomsdayCost[++m] = clampPositive(prop.getInt(50));

		prop = config.get(cat.getName(), "piercingLightCost", 60);
		doomsdayCost[++m] = clampPositive(prop.getInt(60));

		prop = config.get(cat.getName(), "natureDrainCost", 12);
		doomsdayCost[++m] = clampPositive(prop.getInt(12));

		prop = config.get(cat.getName(), "poisonBreakCost", 30);
		doomsdayCost[++m] = clampPositive(prop.getInt(30));

		prop = config.get(cat.getName(), "snipeCost", 90);
		doomsdayCost[++m] = clampPositive(prop.getInt(90));

		prop = config.get(cat.getName(), "rapidFireCost", 8);
		doomsdayCost[++m] = clampPositive(prop.getInt(8));

		prop = config.get(cat.getName(), "pulseCost", 10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "lightShoveCost", 3);
		doomsdayCost[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "fearCost", 30);
		doomsdayCost[++m] = clampPositive(prop.getInt(30));

		prop = config.get(cat.getName(), "harmonizerCost", 40);
		doomsdayCost[++m] = clampPositive(prop.getInt(40));

		prop = config.get(cat.getName(), "ravageCost", 55);
		doomsdayCost[++m] = clampPositive(prop.getInt(55));

		prop = config.get(cat.getName(), "tormentCost", 45);
		doomsdayCost[++m] = clampPositive(prop.getInt(45));

		prop = config.get(cat.getName(), "beastlyImpulsesCost", 60);
		doomsdayCost[++m] = clampPositive(prop.getInt(60));

		prop = config.get(cat.getName(), "suicidalTendenciesCost", 12);
		doomsdayCost[++m] = clampPositive(prop.getInt(12));

		prop = config.get(cat.getName(), "reaperLaughCost", 16);
		doomsdayCost[++m] = clampPositive(prop.getInt(16));

		prop = config.get(cat.getName(), "realityAlterCost", 40);
		doomsdayCost[++m] = clampPositive(prop.getInt(40));

		prop = config.get(cat.getName(), "skullCrusherCost", 50);
		doomsdayCost[++m] = clampPositive(prop.getInt(50));

		prop = config.get(cat.getName(), "minerSkillsCost", 30);
		doomsdayCost[++m] = clampPositive(prop.getInt(30));

		prop = config.get(cat.getName(), "freezeCost", 30);
		doomsdayCost[++m] = clampPositive(prop.getInt(30));

		prop = config.get(cat.getName(), "moonlightSonataCost", 1);
		doomsdayCost[++m] = clampPositive(prop.getInt(1));

		prop = config.get(cat.getName(), "flightOfTheValkyriesCost", 10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "titanfallCost", 5);
		doomsdayCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "bloodlustCost", 80);
		doomsdayCost[++m] = clampPositive(prop.getInt(80));

		prop = config.get(cat.getName(), "permafrostCost", 6);
		doomsdayCost[++m] = clampPositive(prop.getInt(6));

		prop = config.get(cat.getName(), "purgeCost", 5);
		doomsdayCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "lightningRushCost", 8);
		doomsdayCost[++m] = clampPositive(prop.getInt(8));

		prop = config.get(cat.getName(), "marionetteCost", 3);
		doomsdayCost[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "mindcrackCost", 45);
		doomsdayCost[++m] = clampPositive(prop.getInt(45));

		prop = config.get(cat.getName(), "growthSpurtCost", 50);
		doomsdayCost[++m] = clampPositive(prop.getInt(50));

		prop = config.get(cat.getName(), "blizzardCost", 10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "asphyxiateCost", 3);
		doomsdayCost[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "fireRainCost", 8);
		doomsdayCost[++m] = clampPositive(prop.getInt(8));

		prop = config.get(cat.getName(), "dragonsRoarCost", 25);
		doomsdayCost[++m] = clampPositive(prop.getInt(25));

		prop = config.get(cat.getName(), "firestormCost", 10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "shotgunCost", 10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "guardiansCallCost", 75);
		doomsdayCost[++m] = clampPositive(prop.getInt(75));

		prop = config.get(cat.getName(), "hardenCost", 60);
		doomsdayCost[++m] = clampPositive(prop.getInt(60));

		prop = config.get(cat.getName(), "sharpenCost", 75);
		doomsdayCost[++m] = clampPositive(prop.getInt(75));

		prop = config.get(cat.getName(), "flashCost", 10);
		doomsdayCost[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "septicsCost", 8);
		doomsdayCost[++m] = clampPositive(prop.getInt(8));

		prop = config.get(cat.getName(), "kurayamiCost", 115);
		doomsdayCost[++m] = clampPositive(prop.getInt(115));

		prop = config.get(cat.getName(), "lifeShareCost", 65);
		doomsdayCost[++m] = clampPositive(prop.getInt(65));

		prop = config.get(cat.getName(), "deathMarkCost", 12);
		doomsdayCost[++m] = clampPositive(prop.getInt(12));

		prop = config.get(cat.getName(), "paradigmShiftCost", 50);
		doomsdayCost[++m] = clampPositive(prop.getInt(50));

		prop = config.get(cat.getName(), "adrenalineCost", 33);
		doomsdayCost[++m] = clampPositive(prop.getInt(33));

		prop = config.get(cat.getName(), "escapeCost", 12);
		doomsdayCost[++m] = clampPositive(prop.getInt(12));

		prop = config.get(cat.getName(), "giftOfTheGodsCost", 115);
		doomsdayCost[++m] = clampPositive(prop.getInt(115));

		prop = config.get(cat.getName(), "gamblerCost", 15);
		doomsdayCost[++m] = clampPositive(prop.getInt(15));

		prop = config.get(cat.getName(), "soulstealerCost", 12);
		doomsdayCost[++m] = clampPositive(prop.getInt(12));

		prop = config.get(cat.getName(), "parasiteCost", 16);
		doomsdayCost[++m] = clampPositive(prop.getInt(16));

		prop = config.get(cat.getName(), "symbiosisCost", 18);
		doomsdayCost[++m] = clampPositive(prop.getInt(18));

		prop = config.get(cat.getName(), "timeCollapseCost", 3);
		doomsdayCost[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "magnetizerCost", 95);
		doomsdayCost[++m] = clampPositive(prop.getInt(95));

		prop = config.get(cat.getName(), "ambienceCost", 1);
		doomsdayCost[++m] = clampPositive(prop.getInt(1));

		prop = config.get(cat.getName(), "dimentiaCost", 99);
		doomsdayCost[++m] = clampPositive(prop.getInt(99));

		prop = config.get(cat.getName(), "deleteCost", 135);
		doomsdayCost[++m] = clampPositive(prop.getInt(135));

		prop = config.get(cat.getName(), "laserCutterCost", 16);
		doomsdayCost[++m] = clampPositive(prop.getInt(16));

		prop = config.get(cat.getName(), "radiantLightCost", 62);
		doomsdayCost[++m] = clampPositive(prop.getInt(62));

		prop = config.get(cat.getName(), "dangerZoneCost", 22);
		doomsdayCost[++m] = clampPositive(prop.getInt(22));

		prop = config.get(cat.getName(), "supportCost", 60);
		doomsdayCost[++m] = clampPositive(prop.getInt(60));

		prop = config.get(cat.getName(), "purifyCost", 42);
		doomsdayCost[++m] = clampPositive(prop.getInt(42));

		prop = config.get(cat.getName(), "recallCost", 82);
		doomsdayCost[++m] = clampPositive(prop.getInt(82));

		prop = config.get(cat.getName(), "shuffleCost", 18);
		doomsdayCost[++m] = clampPositive(prop.getInt(18));

		prop = config.get(cat.getName(), "blinkCost", 15);
		doomsdayCost[++m] = clampPositive(prop.getInt(15));

		prop = config.get(cat.getName(), "evacuateCost", 56);
		doomsdayCost[++m] = clampPositive(prop.getInt(56));

		prop = config.get(cat.getName(), "medicCost", 9);
		doomsdayCost[++m] = clampPositive(prop.getInt(9));

		prop = config.get(cat.getName(), "resurgenceCost", 135);
		doomsdayCost[++m] = clampPositive(prop.getInt(135));

		prop = config.get(cat.getName(), "decayCooldown", 20);
		doomsdayCooldown[m = 1] = clampPositive(prop.getInt(20));

		prop = config.get(cat.getName(), "huntersInstinctCooldown", 25);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(25));

		prop = config.get(cat.getName(), "toxicityCooldown", 15);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

		prop = config.get(cat.getName(), "berserkerCooldown", 15);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

		prop = config.get(cat.getName(), "piercingLightCooldown", 30);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

		prop = config.get(cat.getName(), "natureDrainCooldown", 6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = config.get(cat.getName(), "poisonBreakCooldown", 10);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "snipeCooldown", 55);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(55));

		prop = config.get(cat.getName(), "rapidFireCooldown", 3);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "pulseCooldown", 6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = config.get(cat.getName(), "lightShoveCooldown", 1);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(1));

		prop = config.get(cat.getName(), "fearCooldown", 20);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

		prop = config.get(cat.getName(), "harmonizerCooldown", 30);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

		prop = config.get(cat.getName(), "ravageCooldown", 35);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(35));

		prop = config.get(cat.getName(), "tormentCooldown", 20);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

		prop = config.get(cat.getName(), "beastlyImpulsesCooldown", 50);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(50));

		prop = config.get(cat.getName(), "suicidalTendenciesCooldown", 4);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(4));

		prop = config.get(cat.getName(), "reaperLaughCooldown", 3);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "realityAlterCooldown", 12);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(12));

		prop = config.get(cat.getName(), "skullCrusherCooldown", 15);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

		prop = config.get(cat.getName(), "minerSkillsCooldown", 20);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

		prop = config.get(cat.getName(), "freezeCooldown", 30);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

		prop = config.get(cat.getName(), "moonlightSonataCooldown", 60);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(60));

		prop = config.get(cat.getName(), "flightOfTheValkyriesCooldown", 10);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "titanfallCooldown", 10);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "bloodlustCooldown", 30);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(30));

		prop = config.get(cat.getName(), "permafrostCooldown", 5);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "purgeCooldown", 4);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(4));

		prop = config.get(cat.getName(), "lightningRushCooldown", 6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = config.get(cat.getName(), "marionetteCooldown", 3);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "mindcrackCooldown", 60);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(60));

		prop = config.get(cat.getName(), "growthSpurtCooldown", 10);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "blizzardCooldown", 6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = config.get(cat.getName(), "asphyxiateCooldown", 3);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "fireRainCooldown", 5);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "dragonsRoarCooldown", 15);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

		prop = config.get(cat.getName(), "firestormCooldown", 8);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(8));

		prop = config.get(cat.getName(), "shotgunCooldown", 5);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "guardiansCallCooldown", 50);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(50));

		prop = config.get(cat.getName(), "hardenCooldown", 4);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(4));

		prop = config.get(cat.getName(), "sharpenCooldown", 6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = config.get(cat.getName(), "flashCooldown", 5);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "septicsCooldown", 6);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(6));

		prop = config.get(cat.getName(), "kurayamiCooldown", 80);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(80));

		prop = config.get(cat.getName(), "lifeShareCooldown", 25);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(25));

		prop = config.get(cat.getName(), "deathMarkCooldown", 10);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "paradigmShiftCooldown", 0);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(0));

		prop = config.get(cat.getName(), "adrenalineCooldown", 21);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(21));

		prop = config.get(cat.getName(), "escapeCooldown", 38);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(38));

		prop = config.get(cat.getName(), "giftOfTheGodsCooldown", 100);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(100));

		prop = config.get(cat.getName(), "gamblerCooldown", 20);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(20));

		prop = config.get(cat.getName(), "soulstealerCooldown", 8);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(8));

		prop = config.get(cat.getName(), "parasiteCooldown", 12);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(12));

		prop = config.get(cat.getName(), "symbiosisCooldown", 14);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(14));

		prop = config.get(cat.getName(), "timeCollapseCooldown", 2);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(2));

		prop = config.get(cat.getName(), "magnetizerCooldown", 65);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(65));

		prop = config.get(cat.getName(), "ambienceCooldown", 1);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(1));

		prop = config.get(cat.getName(), "dimentiaCooldown", 77);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(77));

		prop = config.get(cat.getName(), "deleteCooldown", 125);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(125));

		prop = config.get(cat.getName(), "laserCutterCooldown", 15);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(15));

		prop = config.get(cat.getName(), "radiantLightCooldown", 65);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(65));

		prop = config.get(cat.getName(), "dangerZoneCooldown", 24);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(24));

		prop = config.get(cat.getName(), "supportCooldown", 16);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(16));

		prop = config.get(cat.getName(), "purifyCooldown", 42);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(42));

		prop = config.get(cat.getName(), "recallCooldown", 22);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(22));

		prop = config.get(cat.getName(), "shuffleCooldown", 8);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(8));

		prop = config.get(cat.getName(), "blinkCooldown", 1);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(1));

		prop = config.get(cat.getName(), "evacuateCooldown", 77);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(77));

		prop = config.get(cat.getName(), "medicCooldown", 9);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(9));

		prop = config.get(cat.getName(), "resurgenceCooldown", 55);
		doomsdayCooldown[++m] = clampPositive(prop.getInt(55));

		cat = config.getCategory(CAT_WEAPON);
		cat.setComment("Modify non-Doomsday abilities of weapons, setting whether they are allowed and their cost.");
		cat.setRequiresWorldRestart(false);

		prop = config.get(cat.getName(), "beastlyClawsComboAllow", true);
		doomAbility[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "blindingLightSolarBombAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "blindingLightBurnAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "blindingLightProjectileDeflectAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "butcherCritKnockbackAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "butcherKnockbackResistanceBuffAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "butcherWeaknessDebuffAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "celestialAegisDamageReductionAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "celestialLongbowTeleportAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "dragonFangBurnAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "dragonFangLargeFireballAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "dragonFangExtinguishAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "frozenLightningSlownessDebuffAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "frozenLightningLightningStrikeAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "frozenLightningIcicleAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "gravitySpikeLaunchAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "guiltyThornPoisonStunDebuffAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "harmonyBellHarmonyDebuffAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "harmonyBellHealingAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "mourningStarSelfDestructAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "mourningStarLookExplosionAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "paranoiaFearSubmissionDebuffAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "paranoiaDarkEnergySprayAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "paranoiaSingleDarkEnergyAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "pitchBlackThrowAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "reaperScytheSmallPumpkinbombAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "reaperScytheLargePumpkinbombAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "splinterRandomDirectonHitAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "splinterGroupRandomDirectionHitAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "thardusSlownessDebuffAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "thardusIcicleAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "titanLightningHitAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "titanLightningStrikesAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "titanLightningAbsorbAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "witheringAxeWitherDebuffAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "witherAxeWitherSkullAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "witheringAxeBlueWitherSkullAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "dragonFangFlamethrowerAllow", true);
		doomAbility[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "beastlyClawsComboCost", 0);
		doomAbilityCost[m = 0] = clampPositive(prop.getInt(0));

		prop = config.get(cat.getName(), "blindingLightSolarBombCost", 15);
		doomAbilityCost[++m] = clampPositive(prop.getInt(15));

		prop = config.get(cat.getName(), "blindingLightBurnCost", 10);
		doomAbilityCost[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "blindingLightProjectileDeflectCost", 5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "butcherCritKnockbackCost", 1);
		doomAbilityCost[++m] = clampPositive(prop.getInt(1));

		prop = config.get(cat.getName(), "butcherKnockbackResistanceCost", 0);
		doomAbilityCost[++m] = clampPositive(prop.getInt(0));

		prop = config.get(cat.getName(), "butcherWeaknessDebuffCost", 0);
		doomAbilityCost[++m] = clampPositive(prop.getInt(0));

		prop = config.get(cat.getName(), "celestialAegisDamageReductionCost", 0);
		doomAbilityCost[++m] = clampPositive(prop.getInt(0));

		prop = config.get(cat.getName(), "celestialLongbowTeleportCost", 0);
		doomAbilityCost[++m] = clampPositive(prop.getInt(0));

		prop = config.get(cat.getName(), "dragonFangBurnCost", 1);
		doomAbilityCost[++m] = clampPositive(prop.getInt(1));

		prop = config.get(cat.getName(), "dragonFangLargeFireballCost", 10);
		doomAbilityCost[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "dragonFangExtinguishCost", 1);
		doomAbilityCost[++m] = clampPositive(prop.getInt(1));

		prop = config.get(cat.getName(), "frozenLightningSlownessDebuffCost", 3);
		doomAbilityCost[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "frozenLightningLightningStrikeCost", 20);
		doomAbilityCost[++m] = clampPositive(prop.getInt(20));

		prop = config.get(cat.getName(), "frozenLightningIcicleCost", 3);
		doomAbilityCost[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "gravitySpikeLaunchCost", 7);
		doomAbilityCost[++m] = clampPositive(prop.getInt(7));

		prop = config.get(cat.getName(), "guiltyThornPoisonStunDebuffCost", 5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "harmonyBellHarmonyDebuffCost", 3);
		doomAbilityCost[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "harmonyBellHealingCost", 1);
		doomAbilityCost[++m] = clampPositive(prop.getInt(1));

		prop = config.get(cat.getName(), "mourningStarSelfDestructCost", 25);
		doomAbilityCost[++m] = clampPositive(prop.getInt(25));

		prop = config.get(cat.getName(), "mourningStarLookExplosionCost", 30);
		doomAbilityCost[++m] = clampPositive(prop.getInt(30));

		prop = config.get(cat.getName(), "paranoiaFearSubmissionDebuffCost", 10);
		doomAbilityCost[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "paranoiaDarkEnergySprayCost", 15);
		doomAbilityCost[++m] = clampPositive(prop.getInt(15));

		prop = config.get(cat.getName(), "paranoiaSingleDarkEnergyCost", 5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "pitchBlackThrowCost", 5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "reaperScytheSmallPumpkinbombCost", 5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "reaperScytheLargePumpkinbombCost", 15);
		doomAbilityCost[++m] = clampPositive(prop.getInt(15));

		prop = config.get(cat.getName(), "splinterRandomDirectionHitCost", 3);
		doomAbilityCost[++m] = clampPositive(prop.getInt(3));

		prop = config.get(cat.getName(), "splinterGroupRandomDirectionHitCost", 10);
		doomAbilityCost[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "thardusSlownessDebuffCost", 5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "thardusIcicleCost", 5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "titanLightningHitCost", 10);
		doomAbilityCost[++m] = clampPositive(prop.getInt(10));

		prop = config.get(cat.getName(), "titanLightningStrikesCost", 20);
		doomAbilityCost[++m] = clampPositive(prop.getInt(20));

		prop = config.get(cat.getName(), "titanLightningAbsorbCost", 5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "witheringAxeWitherDebuffCost", 5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "witheringAxeWitherSkullCost", 5);
		doomAbilityCost[++m] = clampPositive(prop.getInt(5));

		prop = config.get(cat.getName(), "witheringAxeBlueWitherSkullCost", 15);
		doomAbilityCost[++m] = clampPositive(prop.getInt(15));

		prop = config.get(cat.getName(), "dragonFangFlamethrowerCost", 3);
		doomAbilityCost[++m] = clampPositive(prop.getInt(3));

		cat = config.getCategory(CAT_ENCHANT);
		cat.setComment("Disable certain enchantments and set their ids, also set their weights.");
		cat.setRequiresMcRestart(true);

		prop = config.get(cat.getName(), "decayAllow", true);
		enchantAllow[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "slayAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "absolveAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "vampirismAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "leechAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "consumeAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "distractAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "multiplyAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "combustionAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "runeBreakAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "reachAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "unbreakableAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "rustAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "veteranAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "deathTouchAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "ignitionAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "toxicityAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "paralysisAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "elasticityAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "agilityAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "runeWalkerAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "luminescenceAllow", true);
		enchantAllow[++m] = prop.getBoolean(true);

		s2 = s; s = "decayID";
		prop = config.get(cat.getName(), s, findEnchantID(128));
		registerObject(s, prop.getInt(findEnchantID(128)));

		s2 = s; s = "slayID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "absolveID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "vampirismID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "leechID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "consumeID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "distractID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "multiplyID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "combustionID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "runeBreakID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "reachID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "unbreakableID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "rustID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "veteranID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "deathTouchID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "ignitionID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "toxicityID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "paralysisID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "elasticityID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "agilityID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "runeWalkerID";
		prop = config.get(cat.getName(), s, findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s2 = s; s = "luminescenceID";
		prop = config.get(cat.getName(), "luminescenceID", findEnchantID(getInt(s2) + 1));
		registerObject(s, prop.getInt(findEnchantID(getInt(s2) + 1)));

		s = "decayWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, prop.getInt(5));

		s = "slayWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, prop.getInt(5));

		s = "absolveWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, prop.getInt(5));

		s = "vampirismWeight";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, prop.getInt(1));

		s = "leechWeight";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, prop.getInt(1));

		s = "consumeWeight";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, prop.getInt(1));

		s = "distractWeight";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, prop.getInt(1));

		s = "multiplyWeight";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, prop.getInt(1));

		s = "combustionWeight";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, prop.getInt(1));

		s = "runeBreakWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, prop.getInt(5));

		s = "reachWeight";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, prop.getInt(5));

		s = "unbreakableWeight";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, prop.getInt(1));

		s = "rustWeight";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, prop.getInt(1));

		s = "veteranWeight";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, prop.getInt(1));

		s = "deathTouchWeight";
		prop = config.get(cat.getName(), s, 2);
		registerObject(s, prop.getInt(2));

		s = "ignitionWeight";
		prop = config.get(cat.getName(), s, 2);
		registerObject(s, prop.getInt(2));

		s = "toxicityWeight";
		prop = config.get(cat.getName(), s, 2);
		registerObject(s, prop.getInt(2));

		s = "paralysisWeight";
		prop = config.get(cat.getName(), s, 2);
		registerObject(s, prop.getInt(2));

		s = "elasticityWeight";
		prop = config.get(cat.getName(), s, 4);
		registerObject(s, prop.getInt(4));

		s = "agilityWeight";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, prop.getInt(2));

		s = "runeWalkerWeight";
		prop = config.get(cat.getName(), s, 2);
		registerObject(s, prop.getInt(2));

		s = "luminescenceWeight";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, prop.getInt(1));

		cat = config.getCategory(CAT_MOBS);
		cat.setComment("Set various aspects of Mobs.");

		prop = config.get(cat.getName(), "allowNormalMobs", true);
		prop.comment = "Are all of the normal mod-exclusive mobs allowed?";
		mobConfig[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowMiniBosses", true);
		prop.comment = "Are all of the Mini-Boss variants of some of the mobs allowed?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowBosses", true);
		prop.comment = "Are all of the Bosses allowed? (Disbling the Overlord disables all of it's forms and disables the Seeker mob)";
		mobConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowBossOverworldSpawns", true);
		prop.comment = "Are Bosses able to spawn in the Overworld?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowExtraBossLoot", true);
		prop.comment = "Can Bosses drop a lot of extra normal loot in addition to their specific drops?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowMobTransformation", true);
		prop.comment = "Can normal mobs transform into their Mini-Boss variants?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowNonDimensionMobSpawns", true);
		prop.comment = "Are the mod-exclusive mobs able to spawn naturally outside of the mod's Dimensions?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowMobGroupBuffs", true);
		prop.comment = "Are the mod-exclusive mobs able to randomly spawn with a potion effect when they spawn naturally in a group?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowEasyBosses", true);
		prop.comment = "Can Bosses be fought on Easy difficulty?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowCustomMobSounds", true);
		prop.comment = "Can the mod-exclusive mobs make their custom sounds? (they will still play the Vanilla sounds if disabled)";
		mobConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "bossesDenyFlight", false);
		prop.comment = "When being near Bosses, do they cancel Flight for the player?";
		mobConfig[++m] = prop.getBoolean(false);

		prop = config.get(cat.getName(), "allowMobInfighting", true);
		prop.comment = "Can mobs from the mod target other mobs from the mod?";
		mobConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowMobIllumination", false);
		prop.comment = "Can mobs glow via a Luminescence block?";
		mobConfig[++m] = prop.getBoolean(false);

		prop = config.get(cat.getName(), "allowRandomSupportMob", false);
		prop.comment = "Can Support mobs sometimes spawn and continuously buff other nearby mobs?";
		mobConfig[++m] = prop.getBoolean(false);
		
		prop = config.get(cat.getName(), "allowCustomBossDeathUpdate", false);
		prop.comment = "On death, should Bosses do a custom death effect?";
		mobConfig[++m] = prop.getBoolean(false);
		
		prop = config.get(cat.getName(), "allowRidableEntities", true);
		prop.comment = "Should Ridable entities be allowed?";
		mobConfig[++m] = prop.getBoolean(true);
		
		prop = config.get(cat.getName(), "allowRidableEntityAbilities", true);
		prop.comment = "Should Ridable entities be able to use their unique abilities when ridden by a player?";
		mobConfig[++m] = prop.getBoolean(true);
		
		prop = config.get(cat.getName(), "allowRidableEntityAbilitiesViaMob", true);
		prop.comment = "Should Ridable entities be able to use their unique abilities when ridden by other mobs?";
		mobConfig[++m] = prop.getBoolean(true);

		s = "commonMobDropChance";
		prop = config.get(cat.getName(), s, 25);
		prop.comment = "Affects the chances of getting common mob drops from the looting amount you killed mobs with, only affects mod-exclusive entities.";
		registerObject(s, clamp(prop.getInt(25), 1, 200));

		s = "rareMobDropChance";
		prop = config.get(cat.getName(), s, 5);
		prop.comment = "Affects the chances of getting rare mob drops from the looting amount you killed mobs with, only affects mod-exclusive entities.";
		registerObject(s, clamp(prop.getInt(5), 1, 100));

		s = "mobTransformationChance";
		prop = config.get(cat.getName(), s, 3);
		registerObject(s, clamp(prop.getInt(3), 1, 100));

		s = "bossDamageCap";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clampPositive(prop.getInt(25)));

		s = "groupBuffChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(15), 1, 200));

		cat = config.getCategory(CAT_MOBSTATS);
		cat.setComment("Change mob stats and allowances.");

		prop = config.get(cat.getName(), "jabbaAllow", true);
		mobAllow[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "jannaAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "plagueAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "gragulAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "minotaurAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "inklingAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "ragrAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "pumpkinheadAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "tragicNekoAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "toxAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "poxAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "cryseAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "starCryseAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "norVoxAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "starVoxAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "pirahAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "stinAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "stinBabyAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "kindlingSpiritAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "abominationAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "erkelAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "sirvAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "psygoteAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "lockbotAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "nanoSwarmAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "snowGolemAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "hunterAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "harvesterAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "seekerAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "archangelAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "ireAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "fuseaAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "ranmasAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "parasmiteAllow", true);
		mobAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "avrisAllow", true);
		mobAllow[++m] = prop.getBoolean(true);
		
		prop = config.get(cat.getName(), "kurayamiAllow", true);
		mobAllow[++m] = prop.getBoolean(true);
		
		prop = config.get(cat.getName(), "jetNekoAllow", true);
		mobAllow[++m] = prop.getBoolean(true);
		
		prop = config.get(cat.getName(), "scienceNekoAllow", true);
		mobAllow[++m] = prop.getBoolean(true);
		
		prop = config.get(cat.getName(), "mechaNekoAllow", true);
		mobAllow[++m] = prop.getBoolean(true);
		
		prop = config.get(cat.getName(), "assaultNekoAllow", true);
		mobAllow[++m] = prop.getBoolean(true);
		
		prop = config.get(cat.getName(), "commonNekoAllow", true);
		mobAllow[++m] = prop.getBoolean(true);
		
		prop = config.get(cat.getName(), "traderNekoAllow", true);
		mobAllow[++m] = prop.getBoolean(true);
		
		prop = config.get(cat.getName(), "mechaExoAllow", true);
		ridableAllow[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "jarraAllow", true);
		miniBossAllow[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "kragulAllow", true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "magmoxAllow", true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "megaCryseAllow", true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "voxStellarumAllow", true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "greaterStinAllow", true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "stinKingAllow", true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "stinQueenAllow", true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "aegarAllow", true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "volatileFuseaAllow", true);
		miniBossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "apisAllow", true);
		bossAllow[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "skultarAllow", true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "kitsunakumaAllow", true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "empariahAllow", true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "timeControllerAllow", true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "polarisAllow", true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "enyvilAllow", true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "claymationAllow", true);
		bossAllow[++m] = prop.getBoolean(true);
		
		prop = config.get(cat.getName(), "professorNekoidAllow", true);
		bossAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "overlordAllow", true);
		bossAllow[++m] = prop.getBoolean(true);

		s = "jabbaStats";
		prop = config.get(cat.getName(), s, new double[] {40.0, 0.275, 5.5, 32.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "jannaStats";
		prop = config.get(cat.getName(), s, new double[] {20.0, 0.325, 4.5, 32.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "jarraStats";
		prop = config.get(cat.getName(), s, new double[] {70.0, 0.360, 6.5, 64.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "plagueStats";
		prop = config.get(cat.getName(), s, new double[] {4.0, 0.235, 1.0, 16.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "gragulStats";
		prop = config.get(cat.getName(), s, new double[] {5.0, 0.350, 5.0, 32.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "kragulStats";
		prop = config.get(cat.getName(), s, new double[] {8.0, 0.380, 5.0, 5.0, 32.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "minotaurStats";
		prop = config.get(cat.getName(), s, new double[] {32.0, 0.350, 7.0, 32.0, 0.5, 6});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "inklingStats";
		prop = config.get(cat.getName(), s, new double[] {16.0, 0.230, 1.0, 32.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "ragrStats";
		prop = config.get(cat.getName(), s, new double[] {65.0, 0.380, 7.0, 32.0, 1.0, 10});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "pumpkinheadStats";
		prop = config.get(cat.getName(), s, new double[] {60.0, 0.275, 6.0, 32.0, 0.0, 18});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "tragicNekoStats";
		prop = config.get(cat.getName(), s, new double[] {60.0, 0.335, 4.0, 32.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "toxStats";
		prop = config.get(cat.getName(), s, new double[] {40.0, 0.050, 8.0, 64.0, 1.0, 16});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "poxStats";
		prop = config.get(cat.getName(), s, new double[] {30.0, 0.050, 4.0, 64.0, 0.7, 10});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "magmoxStats";
		prop = config.get(cat.getName(), s, new double[] {75.0, 0.050, 15.0, 64.0, 1.0, 20});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "cryseStats";
		prop = config.get(cat.getName(), s, new double[] {35.0, 0.285, 4.0, 48.0, 0.0, 4});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "starCryseStats";
		prop = config.get(cat.getName(), s, new double[] {55.0, 0.315, 4.0, 48.0, 0.0, 4});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "megaCryseStats";
		prop = config.get(cat.getName(), s, new double[] {50.0, 0.310, 6.0, 48.0, 1.0, 18});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "norVoxStats";
		prop = config.get(cat.getName(), s, new double[] {30.0, 0.390, 4.0, 32.0, 0.25, 8});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "starVoxStats";
		prop = config.get(cat.getName(), s, new double[] {40.0, 0.390, 4.0, 32.0, 0.25, 16});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "voxStellarumStats";
		prop = config.get(cat.getName(), s, new double[] {150.0, 0.460, 4.0, 64.0, 0.2, 16});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "pirahStats";
		prop = config.get(cat.getName(), s, new double[] {10.0, 0.450, 3.0, 16.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "goldenPirahStats";
		prop = config.get(cat.getName(), s, new double[] {25.0, 0.450, 7.5, 16.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "stinStats";
		prop = config.get(cat.getName(), s, new double[] {40.0, 0.246, 10.0, 32.0, 0.5, 6});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "stinBabyStats";
		prop = config.get(cat.getName(), s, new double[] {16.0, 0.346, 6.0, 32.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "greaterStinStats";
		prop = config.get(cat.getName(), s, new double[] {80.0, 0.276, 14.0, 24.0, 1.0, 12});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "stinKingStats";
		prop = config.get(cat.getName(), s, new double[] {100.0, 0.226, 20.0, 32.0, 2.0, 20});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "stinQueenStats";
		prop = config.get(cat.getName(), s, new double[] {160.0, 0.186, 12.0, 24, 2.0, 10});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "kindlingSpiritStats";
		prop = config.get(cat.getName(), s, new double[] {8.0, 0.476, 1.0, 16.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "abominationStats";
		prop = config.get(cat.getName(), s, new double[] {45.0, 0.276, 7.0, 32.0, 0.5, 4});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "erkelStats";
		prop = config.get(cat.getName(), s, new double[] {16.0, 0.476, 1.0, 16.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "sirvStats";
		prop = config.get(cat.getName(), s, new double[] {8.0, 0.375, 14.0, 64.0, 0.5, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "psygoteStats";
		prop = config.get(cat.getName(), s, new double[] {52.0, 0.290, 8.0, 32.0, 0.65, 10});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "lockbotStats";
		prop = config.get(cat.getName(), s, new double[] {22.0, 0.0, 1.0, 8.0, 100.0, 8});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "nanoSwarmStats";
		prop = config.get(cat.getName(), s, new double[] {6.0, 0.335, 2.0, 64.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "hunterStats";
		prop = config.get(cat.getName(), s, new double[] {16.0, 0.236, 4.0, 32.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "harvesterStats";
		prop = config.get(cat.getName(), s, new double[] {56.0, 0.145, 0.0, 16.0, 100.0, 20});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "seekerStats";
		prop = config.get(cat.getName(), s, new double[] {30.0, 0.0, 1.0, 48.0, 100.0, 24});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "archangelStats";
		prop = config.get(cat.getName(), s, new double[] {45.0, 0.0, 1.0, 32.0, 0.5, 12});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "ireStats";
		prop = config.get(cat.getName(), s, new double[] {25.0, 0.0, 1.0, 16.0, 100.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "fuseaStats";
		prop = config.get(cat.getName(), s, new double[] {10.0, 0.0, 0.0, 16.0, 100.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "volatileFuseaStats";
		prop = config.get(cat.getName(), s, new double[] {18.0, 0.0, 0.0, 32.0, 100.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "ranmasStats";
		prop = config.get(cat.getName(), s, new double[] {50.0, 0.0, 1.0, 32.0, 100.0, 24});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "parasmiteStats";
		prop = config.get(cat.getName(), s, new double[] {10.0, 0.0, 1.0, 16.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "kurayamiStats";
		prop = config.get(cat.getName(), s, new double[] {120.0, 0.420, 12.0, 64.0, 0.4, 10});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "avrisStats";
		prop = config.get(cat.getName(), s, new double[] {75.0, 0.312, 2.0, 64.0, 0.6, 16});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));
		
		s = "jetNekoStats";
		prop = config.get(cat.getName(), s, new double[] {45.0, 0.23, 1.0, 32.0, 1.0, 6});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));
		
		s = "scienceNekoStats";
		prop = config.get(cat.getName(), s, new double[] {30.0, 0.225, 2.0, 32.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));
		
		s = "mechaNekoStats";
		prop = config.get(cat.getName(), s, new double[] {15.0, 0.27, 4.0, 32.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));
		
		s = "assaultNekoStats";
		prop = config.get(cat.getName(), s, new double[] {42.0, 0.33, 4.0, 32.0, 0.5, 8});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));
		
		s = "commonNekoStats";
		prop = config.get(cat.getName(), s, new double[] {20.0, 0.245, 2.0, 32.0, 0.0, 4});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));
		
		s = "traderNekoStats";
		prop = config.get(cat.getName(), s, new double[] {30.0, 0.215, 1.0, 16.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));
		
		s = "mechaExoStats"; 
		prop = config.get(cat.getName(), s, new double[] {25.0, 0.19, 5.0, 32.0, 1.0, 24});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "aegarStats";
		prop = config.get(cat.getName(), s, new double[] {150.0, 0.185, 26.0, 32.0, 2.5, 24});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "apisStats";
		prop = config.get(cat.getName(), s, new double[] {160.0, 0.375, 12.0, 32.0, 1.0, 16});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "skultarStats";
		prop = config.get(cat.getName(), s, new double[] {220.0, 0.350, 16.0, 32.0, 1.0, 20});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "kitsunakumaStats";
		prop = config.get(cat.getName(), s, new double[] {80.0, 0.420, 6.0, 64.0, 0.0, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "polarisStats";
		prop = config.get(cat.getName(), s, new double[] {120.0, 0.440, 5.0, 64.0, 0.0, 14});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "empariahStats";
		prop = config.get(cat.getName(), s, new double[] {140.0, 0.326, 16.0, 48.0, 2.0, 22});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "timeControllerStats";
		prop = config.get(cat.getName(), s, new double[] {350.0, 0.366, 6.0, 64.0, 0.5, 18});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "enyvilStats";
		prop = config.get(cat.getName(), s, new double[] {450.0, 0.276, 24.0, 48.0, 1.0, 4});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "claymationStats";
		prop = config.get(cat.getName(), s, new double[] {150.0, 0.320, 12.0, 32.0, 1.0, 18});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));
		
		s = "professorNekoidStats";
		prop = config.get(cat.getName(), s, new double[] {200.0, 0.25, 6.0, 32.0, 0.0, 12});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "overlordCocoonStats";
		prop = config.get(cat.getName(), s, new double[] {500.0, 0.226, 24.0, 64.0, 4.5, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "overlordCombatStats";
		prop = config.get(cat.getName(), s, new double[] {500.0, 0.326, 24.0, 64.0, 4.5, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		s = "overlordCoreStats";
		prop = config.get(cat.getName(), s, new double[] {1000.0, 0.326, 24.0, 64.0, 4.5, 0});
		registerObject(s, MobStat.verifyMobStat(new MobStat(prop.getDoubleList())));

		cat = config.getCategory(CAT_MOBSPAWNS);
		cat.setComment("Set spawn chances and group sizes for each mob, can also override the Vanilla biome spawns.");

		s = "jabbaSpawnChance";
		prop = config.get(cat.getName(), s, 75);
		registerObject(s, clamp(prop.getInt(75), 1, 1000));

		s = "jannaSpawnChance";
		prop = config.get(cat.getName(), s, 50);
		registerObject(s, clamp(prop.getInt(50), 1, 1000));

		s = "jarraSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "plagueSpawnChance";
		prop = config.get(cat.getName(), s, 50);
		registerObject(s, clamp(prop.getInt(50), 1, 1000));

		s = "gragulSpawnChance";
		prop = config.get(cat.getName(), "gragulSpawnChance", 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "kragulSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "minotaurSpawnChance";
		prop = config.get(cat.getName(), s, 75);
		registerObject(s, clamp(prop.getInt(75), 1, 1000));

		s = "inklingSpawnChance";
		prop = config.get(cat.getName(), s, 75);
		registerObject(s, clamp(prop.getInt(75), 1, 1000));

		s = "ragrSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "pumpkinheadSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "tragicNekoSpawnChance";
		prop = config.get(cat.getName(), s, 50);
		registerObject(s, clamp(prop.getInt(50), 1, 1000));

		s = "toxSpawnChance";
		prop = config.get(cat.getName(), s, 50);
		registerObject(s, clamp(prop.getInt(50), 1, 1000));

		s = "poxSpawnChance";
		prop = config.get(cat.getName(), s, 50);
		registerObject(s, clamp(prop.getInt(50), 1, 1000));

		s = "magmoxSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "cryseSpawnChance";
		prop = config.get(cat.getName(), s, 75);
		registerObject(s, clamp(prop.getInt(75), 1, 1000));

		s = "starCryseSpawnChance";
		prop = config.get(cat.getName(), s, 75);
		registerObject(s, clamp(prop.getInt(75), 1, 1000));

		s = "megaCryseSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "norVoxSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "starVoxSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "voxStellarumSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "pirahSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "stinSpawnChance";
		prop = config.get(cat.getName(), s, 50);
		registerObject(s, clamp(prop.getInt(50), 1, 1000));

		s = "greaterStinSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "stinKingSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "stinQueenSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "kindlingSpiritSpawnChance";
		prop = config.get(cat.getName(), s, 15);
		registerObject(s, clamp(prop.getInt(15), 1, 1000));

		s = "abominationSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "erkelSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "sirvSpawnChance";
		prop = config.get(cat.getName(), s, 50);
		registerObject(s, clamp(prop.getInt(50), 1, 1000));

		s = "psygoteSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "lockbotSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "nanoSwarmSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "snowGolemSpawnChance";
		prop = config.get(cat.getName(), s, 20);
		registerObject(s, clamp(prop.getInt(20), 1, 1000));

		s = "hunterSpawnChance";
		prop = config.get(cat.getName(), s, 15);
		registerObject(s, clamp(prop.getInt(15), 1, 1000));

		s = "harvesterSpawnChance";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, clamp(prop.getInt(10), 1, 1000));

		s = "seekerSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "archangelSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "ireSpawnChance";
		prop = config.get(cat.getName(), s, 45);
		registerObject(s, clamp(prop.getInt(45), 1, 1000));

		s = "fuseaSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "volatileFuseaSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "ranmasSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "parasmiteSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));

		s = "avrisSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));
		
		s = "jetNekoSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));
		
		s = "scienceNekoSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));
		
		s = "mechaNekoSpawnChance";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, clamp(prop.getInt(1), 1, 1000));
		
		s = "assaultNekoSpawnChance";
		prop = config.get(cat.getName(), s, 25);
		registerObject(s, clamp(prop.getInt(25), 1, 1000));
		
		s = "commonNekoSpawnChance";
		prop = config.get(cat.getName(), s, 45);
		registerObject(s, clamp(prop.getInt(45), 1, 1000));
		
		s = "traderNekoSpawnChance";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, clamp(prop.getInt(1), 1, 1000));

		s = "aegarSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "apisSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "skultarSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "kitsunakumaSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "empariahSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "timeControllerSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "polarisSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "enyvilSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));

		s = "claymationSpawnChance";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, clamp(prop.getInt(5), 1, 1000));
		
		s = "professorNekoidSpawnChance";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, clamp(prop.getInt(1), 1, 1000));

		s = "jabbaGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 2});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "jannaGroupSize";
		prop = config.get(cat.getName(),s, new int[] {0, 2});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "plagueGroupSize";
		prop = config.get(cat.getName(), s, new int[] {2, 3});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "gragulGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "minotaurGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "inklingGroupSize";
		prop = config.get(cat.getName(), s, new int[] {2, 5});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "ragrGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "pumpkinheadGroupSize";
		prop = config.get(cat.getName(), s, new int[] {2, 4});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "tragicNekoGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "toxGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "poxGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "cryseGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 2});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "starCryseGroupSize";
		prop = config.get(cat.getName(), s, new int[] {1, 3});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "norVoxGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "starVoxGroupSize";
		prop = config.get(cat.getName(), s, new int[] {2, 5});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "pirahGroupSize";
		prop = config.get(cat.getName(), s, new int[] {4, 6});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "stinGroupSize";
		prop = config.get(cat.getName(), s, new int[] {1, 2});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "kindlingSpiritGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "abominationGroupSize";
		prop = config.get(cat.getName(), s, new int[] {2, 5});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "erkelGroupSize";
		prop = config.get(cat.getName(), s, new int[] {1, 2});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "sirvGroupSize";
		prop = config.get(cat.getName(), s, new int[] {4, 6});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "psygoteGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "lockbotGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "nanoSwarmGroupSize";
		prop = config.get(cat.getName(), s, new int[] {2, 4});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "snowGolemGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "hunterGroupSize";
		prop = config.get(cat.getName(), s, new int[] {4, 6});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "harvesterGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 2});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "seekerGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "archangelGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "ireGroupSize";
		prop = config.get(cat.getName(), s, new int[] {2, 4});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "fuseaGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 2});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "ranmasGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "parasmiteGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "avrisGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));
		
		s = "jetNekoGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 0});
		registerObject(s, verifyGS(prop.getIntList()));
		
		s = "scienceNekoGroupSize";
		prop = config.get(cat.getName(), s, new int[] {1, 2});
		registerObject(s, verifyGS(prop.getIntList()));
		
		s = "mechaNekoGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 0});
		registerObject(s, verifyGS(prop.getIntList()));
		
		s = "assaultNekoGroupSize";
		prop = config.get(cat.getName(), s, new int[] {2, 4});
		registerObject(s, verifyGS(prop.getIntList()));
		
		s = "commonNekoGroupSize";
		prop = config.get(cat.getName(), s, new int[] {2, 5});
		registerObject(s, verifyGS(prop.getIntList()));
		
		s = "traderNekoGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 0});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "jarraGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "kragulGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "magmoxGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "megaCryseGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "voxStellarumGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "greaterStinGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "stinKingGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "stinQueenGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "volatileFuseaGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "aegarGroupSize";
		prop = config.get(cat.getName(), s, new int[] {0, 1});
		registerObject(s, verifyGS(prop.getIntList()));

		s = "jabbaSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "jabbaSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.desert.biomeID, BiomeGenBase.desertHills.biomeID, BiomeGenBase.mesa.biomeID,
				BiomeGenBase.hell.biomeID, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "plagueSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "plagueSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.beach.biomeID, BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID,
				BiomeGenBase.coldBeach.biomeID, BiomeGenBase.coldTaiga.biomeID, BiomeGenBase.coldTaigaHills.biomeID, BiomeGenBase.deepOcean.biomeID, BiomeGenBase.desert.biomeID,
				BiomeGenBase.desertHills.biomeID, BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID, BiomeGenBase.forest.biomeID,
				BiomeGenBase.forestHills.biomeID, BiomeGenBase.frozenOcean.biomeID, BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.hell.biomeID, BiomeGenBase.iceMountains.biomeID,
				BiomeGenBase.icePlains.biomeID, BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID, BiomeGenBase.megaTaiga.biomeID,
				BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mushroomIsland.biomeID,
				BiomeGenBase.mushroomIslandShore.biomeID, BiomeGenBase.ocean.biomeID, BiomeGenBase.plains.biomeID, BiomeGenBase.river.biomeID, BiomeGenBase.roofedForest.biomeID,
				BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID, BiomeGenBase.sky.biomeID, BiomeGenBase.stoneBeach.biomeID, BiomeGenBase.swampland.biomeID,
				BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "gragulSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "gragulSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.desertHills.biomeID, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID,
				BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsPlus.biomeID, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.megaTaigaHills.biomeID,
				BiomeGenBase.roofedForest.biomeID, BiomeGenBase.swampland.biomeID, BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "minotaurSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "minotaurSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.plains.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID,
				BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.mesa.biomeID,
				BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID,
				BiomeGenBase.extremeHillsPlus.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "inklingSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "inklingSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.beach.biomeID, BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID,
				BiomeGenBase.coldBeach.biomeID, BiomeGenBase.coldTaiga.biomeID, BiomeGenBase.coldTaigaHills.biomeID, BiomeGenBase.deepOcean.biomeID, BiomeGenBase.desert.biomeID, 
				BiomeGenBase.desertHills.biomeID, BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID, BiomeGenBase.forest.biomeID,
				BiomeGenBase.forestHills.biomeID, BiomeGenBase.frozenOcean.biomeID, BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.iceMountains.biomeID, BiomeGenBase.icePlains.biomeID,
				BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.megaTaigaHills.biomeID,
				BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID,
				BiomeGenBase.ocean.biomeID, BiomeGenBase.plains.biomeID, BiomeGenBase.river.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID,
				BiomeGenBase.stoneBeach.biomeID, BiomeGenBase.swampland.biomeID, BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "ragrSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "ragrSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID, BiomeGenBase.coldTaiga.biomeID,
				BiomeGenBase.coldTaigaHills.biomeID, BiomeGenBase.icePlains.biomeID, BiomeGenBase.iceMountains.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "pumpkinheadSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "pumpkinheadSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.forest.biomeID,
				BiomeGenBase.forestHills.biomeID, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID,
				BiomeGenBase.plains.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID, BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "tragicNekoSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "tragicNekoSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "toxSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "toxSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.roofedForest.biomeID, BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID,
				BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "cryseSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "cryseSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.icePlains.biomeID, BiomeGenBase.iceMountains.biomeID, BiomeGenBase.coldTaiga.biomeID,
				BiomeGenBase.coldTaigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "norVoxSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "norVoxSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.deepOcean.biomeID,
				BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID, BiomeGenBase.forest.biomeID,
				BiomeGenBase.forestHills.biomeID, BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID, BiomeGenBase.megaTaiga.biomeID,
				BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID,
				BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID, BiomeGenBase.ocean.biomeID, BiomeGenBase.plains.biomeID,
				BiomeGenBase.river.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID,
				BiomeGenBase.stoneBeach.biomeID, BiomeGenBase.swampland.biomeID, BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "pirahSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "pirahSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.deepOcean.biomeID, BiomeGenBase.ocean.biomeID, BiomeGenBase.river.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "stinSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "stinSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "kindlingSpiritSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "kindlingSpiritSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.roofedForest.biomeID, BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID,
				BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleHills.biomeID, BiomeGenBase.desert.biomeID,
				BiomeGenBase.desertHills.biomeID, BiomeGenBase.mesa.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "abominationSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "abominationSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.icePlains.biomeID, BiomeGenBase.iceMountains.biomeID, BiomeGenBase.frozenOcean.biomeID,
				BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.coldBeach.biomeID, BiomeGenBase.coldTaiga.biomeID, BiomeGenBase.coldTaigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "erkelSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "erkelSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "sirvSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "sirvSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "psygoteSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "psygoteSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "lockbotSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "lockbotSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "nanoSwarmSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "nanoSwarmSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "hunterSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "hunterSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "harvesterSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "harvesterSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "seekerSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "seekerSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "archangelSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "archangelSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "ireSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "ireSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "fuseaSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "fuseaSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "ranmasSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "ranmasSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "parasmiteSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "parasmiteSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "avrisSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "avrisSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));
		
		s = "jetNekoSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));
		
		s = "jetNekoSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));
		
		s = "scienceNekoSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));
		
		s = "scienceNekoSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));
		
		s = "mechaNekoSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));
		
		s = "mechaNekoSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));
		
		s = "assaultNekoSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));
		
		s = "assaultNekoSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));
		
		s = "commonNekoSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));
		
		s = "commonNekoSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));
		
		s = "traderNekoSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));
		
		s = "traderNekoSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "snowGolemSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "snowGolemSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.icePlains.biomeID, BiomeGenBase.iceMountains.biomeID, BiomeGenBase.frozenOcean.biomeID,
				BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.coldBeach.biomeID, BiomeGenBase.coldTaiga.biomeID, BiomeGenBase.coldTaigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "jarraSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "jarraSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.hell.biomeID, BiomeGenBase.desert.biomeID, BiomeGenBase.desertHills.biomeID, BiomeGenBase.mesa.biomeID,
				BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "kragulSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "kragulSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.desertHills.biomeID, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID,
				BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsPlus.biomeID, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.roofedForest.biomeID,
				BiomeGenBase.swampland.biomeID, BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "magmoxSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "magmoxSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.hell.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "megaCryseSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "megaCryseSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.icePlains.biomeID, BiomeGenBase.iceMountains.biomeID, BiomeGenBase.coldTaiga.biomeID,
				BiomeGenBase.coldTaigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "greaterStinSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "greaterStinSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "stinKingSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "stinKingSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "stinQueenSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "stinQueenSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "voxStellarumSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "voxStellarumSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "volatileFuseaSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "volatileFuseaSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "aegarSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "aegarSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {0});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "apisSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "apisSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.plains.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID,
				BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.mesa.biomeID,
				BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID, 
				BiomeGenBase.extremeHillsPlus.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "skultarSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "skultarSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.birchForest.biomeID,
				BiomeGenBase.birchForestHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "kitsunakumaSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "kitsunakumaSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.hell.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "empariahSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "empariahSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.icePlains.biomeID, BiomeGenBase.iceMountains.biomeID, BiomeGenBase.frozenOcean.biomeID,
				BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.coldBeach.biomeID, BiomeGenBase.coldTaiga.biomeID, BiomeGenBase.coldTaigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "timeControllerSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "timeControllerSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.deepOcean.biomeID,
				BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID, BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID,
				BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.megaTaigaHills.biomeID,
				BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID,
				BiomeGenBase.ocean.biomeID, BiomeGenBase.plains.biomeID, BiomeGenBase.river.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID,
				BiomeGenBase.stoneBeach.biomeID, BiomeGenBase.swampland.biomeID, BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "polarisSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "polarisSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.deepOcean.biomeID,
				BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID, BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID,
				BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.megaTaigaHills.biomeID, BiomeGenBase.mesa.biomeID,
				BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID, BiomeGenBase.ocean.biomeID,
				BiomeGenBase.plains.biomeID, BiomeGenBase.river.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID, BiomeGenBase.stoneBeach.biomeID,
				BiomeGenBase.swampland.biomeID, BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "enyvilSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "enyvilSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.birchForest.biomeID, BiomeGenBase.birchForestHills.biomeID, BiomeGenBase.deepOcean.biomeID,
				BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID, BiomeGenBase.extremeHillsPlus.biomeID, BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID,
				BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.megaTaigaHills.biomeID,
				BiomeGenBase.mesa.biomeID, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mushroomIsland.biomeID, BiomeGenBase.mushroomIslandShore.biomeID,
				BiomeGenBase.ocean.biomeID, BiomeGenBase.plains.biomeID, BiomeGenBase.river.biomeID, BiomeGenBase.roofedForest.biomeID, BiomeGenBase.savanna.biomeID, BiomeGenBase.savannaPlateau.biomeID,
				BiomeGenBase.stoneBeach.biomeID, BiomeGenBase.swampland.biomeID, BiomeGenBase.taiga.biomeID, BiomeGenBase.taigaHills.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		s = "claymationSpawnOverride";
		prop = config.get(cat.getName(), s, false);
		registerObject(s, prop.getBoolean(false));

		s = "claymationSpawnBiomes";
		prop = config.get(cat.getName(), s, new int[] {BiomeGenBase.desert.biomeID, BiomeGenBase.desertHills.biomeID, BiomeGenBase.mesa.biomeID,
				BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesaPlateau_F.biomeID});
		registerObject(s, getIntsAsBiome(prop.getIntList()));

		cat = config.getCategory(CAT_POTION);
		cat.setComment("Modify various aspects of Potions.");

		prop = config.get(cat.getName(), "flightAllow", true);
		potionAllow[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "aquaSuperiorityAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "immunityAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "resurrectionAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "harmonyAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "invulnerabilityAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "clarityAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "convergenceAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "divinityAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "corruptionAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "disorientationAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "stunAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "fearAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "malnourishAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "crippleAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "submissionAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "inhibitAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "leadFootAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "hackedAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "burnedAllow", true);
		potionAllow[++m] = prop.getBoolean(true);

		cat = config.getCategory(CAT_VANILLA);
		cat.setComment("These toggle the various changes the mod does that explicitly affects Vanilla Minecraft. Ore rate and vein size only affects those ores generated in Vanilla Dimensions.");

		prop = config.get(cat.getName(), "allowMobBuffs", true);
		prop.comment = "Will various Vanilla Mobs gain a Health, Attack Damage, Knockback Resistance or Speed buff?";
		vanillaConfig[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowExtraMobEffects", true);
		prop.comment = "Will some mobs gain Potion Effect debuffs and other abilities along with their normal attacks?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowAnimalRetribution", true);
		prop.comment = "Can slaying animals sometimes trigger a Lightning strike?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowMobModdedArmor", true);
		prop.comment = "Can Vanilla mobs sometimes spawn in with Armor from the mod?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowRespawnPunishment", true);
		prop.comment = "Will you get inflicted with negative effects upon respawning after a death?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowExtraExplosiveEffects", true);
		prop.comment = "Do explosions inflict extra negative effects on you when hit?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowMobBlindnessDebuff", true);
		prop.comment = "Does Blindness reduce the follow range of mobs?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowExtraOverworldFlowers", true);
		prop.comment = "Can some of the mod-exclusive flowers generate in Vanilla biomes?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowOverworldSilverfishGen", true);
		prop.comment = "Will Silverfish stone generate in lower y-levels in the Overworld?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowNetherOreGen", true);
		prop.comment = "Can mod-exclusive ores generate in the Nether?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowOverworldOreGen", true);
		prop.comment = "Can mod-exclusive ores generate in the Overworld?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowDrudgeGen", true);
		prop.comment = "Can Drudge generate in the Nether?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowAnimalAndGolemCorruption", true);
		prop.comment = "Can Animals and Golems become Corrupted?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowCowMinotaurCreation", true);
		prop.comment = "Will striking a Cow with Lightning turn it into a Minotaur?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowIronGolemCooldown", true);
		prop.comment = "Should Iron Golems have an enforced hit cooldown?";
		vanillaConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowNauseaRandomMiss", false);
		prop.comment = "Should Nausea (Confusion) have a random chance to make you miss a non-projectile, non-magic hit?";
		vanillaConfig[++m] = prop.getBoolean(false);

		prop = config.get(cat.getName(), "allowBlindnessReachDebuff", false);
		prop.comment = "Should Blindness debuff your Reach?";
		vanillaConfig[++m] = prop.getBoolean(false);

		prop = config.get(cat.getName(), "allowCripplingFall", false);
		prop.comment = "Should a fall that damages you inflict Cripple?";
		vanillaConfig[++m] = prop.getBoolean(false);

		s = "rubyOreGenRate";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, prop.getInt(10));

		s = "rubyOreVeinSize";
		prop = config.get(cat.getName(), s, 3);
		registerObject(s, prop.getInt(3));

		s = "sapphireOreGenRate";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, prop.getInt(10));

		s = "sapphireOreVeinSize";
		prop = config.get(cat.getName(), s, 3);
		registerObject(s, prop.getInt(3));

		s = "mercuryOreGenRate";
		prop = config.get(cat.getName(), s, 20);
		registerObject(s, prop.getInt(20));

		s = "mercuryOreVeinSize";
		prop = config.get(cat.getName(), s, 4);
		registerObject(s, prop.getInt(4));

		s = "tungstenOreGenRate";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, prop.getInt(10));

		s = "tungstenOreVeinSize";
		prop = config.get(cat.getName(), s, 3);
		registerObject(s, prop.getInt(3));

		s = "drudgeGenRate";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, prop.getInt(10));

		s = "drudgeVeinSize";
		prop = config.get(cat.getName(), s, 10);
		registerObject(s, prop.getInt(10));

		s = "silverfishStoneGenRate";
		prop = config.get(cat.getName(), s, 12);
		registerObject(s, prop.getInt(12));

		s = "silverfishStoneVeinSize";
		prop = config.get(cat.getName(), s, 3);
		registerObject(s, prop.getInt(3));

		s = "aerisRarity";
		prop = config.get(cat.getName(), s, 5);
		registerObject(s, prop.getInt(5));

		s = "nauseaMissChance";
		prop = config.get(cat.getName(), s, 8);
		registerObject(s, clamp(prop.getInt(8), 1, 100));

		s = "blindnessReachDebuffAmount";
		prop = config.get(cat.getName(), s, 0.25);
		registerObject(s, prop.getDouble(0.25));

		cat = config.getCategory(CAT_WORLDGEN);
		cat.setComment("Change things related to the mod-exclusive Dimensional World Generation.");

		prop = config.get(cat.getName(), "allowVoidPits", true);
		prop.comment = "Should void pits be allowed to generate?";
		worldGenConfig[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowSpikes", true);
		prop.comment = "Should large spikes, ice spikes, star crystals and crystal spikes be allowed to generate?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowScatteredSurfaceWorldGen", true);
		prop.comment = "Should scattered surface features like mixed dirt, light orbs and cracked permafrost be allowed to generate?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowStringLights", true);
		prop.comment = "Should String Lights be allowed to generate?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowDarkStoneVariantGen", true);
		prop.comment = "Should Dark Stone have layers generate as a colored variant?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowStructureGen", true);
		prop.comment = "Should mod-exclusive Structures be allowed to generate?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowRoughTerrainGen", true);
		prop.comment = "Should rough terrain like in the Tainted or Scorched Scarlands be generated?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowDigitalSeaGen", true);
		prop.comment = "Should Digital Sea generate in the Synapse?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowFruitGen", true);
		prop.comment = "Should Honeydrop, Deathglow and Sky Fruit generate naturally?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowIsleGen", true);
		prop.comment = "Should Isles be generated in the Tainted Isles biome?";
		worldGenConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowFlowerGen", true);
		prop.comment = "Should flowers generate in the mod's Dimensions?";
		worldGenConfig[++m] = prop.getBoolean(true);

		cat = config.getCategory(CAT_STRUCTURE);
		cat.setComment("Toggle specific structures and change their rarities. Higher number is higher chance to generate.");

		s = "structureOverallRarity";
		prop = config.get(cat.getName(), s, 15);
		registerObject(s, clamp(prop.getInt(15), 1, 500));

		s = "tickBuilderSchematicLimit";
		prop = config.get(cat.getName(), s, 250);
		prop.comment = "The maximum blocks the tick builder will set per structure schematic per tick";
		registerObject(s, clampPositive(prop.getInt(100)));

		s = "tickBuilderOverallLimit";
		prop = config.get(cat.getName(), s, 500);
		prop.comment = "The maximum total blocks the tick builder will place per tick.";
		registerObject(s, clampPositive(prop.getInt(200)));
		
		s = "tickBuilderTickRate";
		prop = config.get(cat.getName(), s, 4);
		prop.comment = "The tick rate that the Builders will work at, higher value is slower building";
		registerObject(s, clampPositive(prop.getInt(4)));

		s = "allowTickBuilder";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should structures generate using a tick builder, which spreads out the placement of blocks over several ticks instead of generating the structure all at once in one tick?";
		registerObject(s, prop.getBoolean(true));
		
		s = "tickBuilderIgnoresAir";
		prop = config.get(cat.getName(), s, false);
		prop.comment = "Will air factor into the total blocks placed by the TickBuilder?";
		registerObject(s, prop.getBoolean(false));

		prop = config.get(cat.getName(), "apisTempleAllow", true);
		structureAllow[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "randomTowerAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "deathCircleAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "obsidianCavernAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "kitsunakumaDenAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "celestialTempleAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "timeAltarAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "soulTombAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "corruptedSpireAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "empariahCaveAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "claymationRuinAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "darkHutAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "spiderNestAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "memoryCacheAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "lightSpireAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "hackerNetAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "cubeMazeAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "outlookAllow", true);
		structureAllow[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "apisTempleRarity", 5);
		structureRarity[m = 0] = clamp(prop.getInt(5), 1, 200);

		prop = config.get(cat.getName(), "randomTowerRarity", 15);
		structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

		prop = config.get(cat.getName(), "deathCircleRarity", 5);
		structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

		prop = config.get(cat.getName(), "obsidianCavernRarity", 10);
		structureRarity[++m] = clamp(prop.getInt(10), 1, 200);

		prop = config.get(cat.getName(), "kitsunakumaDenRarity", 5);
		structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

		prop = config.get(cat.getName(), "celestialTempleRarity", 10);
		structureRarity[++m] = clamp(prop.getInt(10), 1, 200);

		prop = config.get(cat.getName(), "timeAltarRarity", 3);
		structureRarity[++m] = clamp(prop.getInt(3), 1, 200);

		prop = config.get(cat.getName(), "soulTombRarity", 15);
		structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

		prop = config.get(cat.getName(), "corruptedSpireRarity", 0);
		structureRarity[++m] = clamp(prop.getInt(0), 1, 200);

		prop = config.get(cat.getName(), "empariahCaveRarity", 5);
		structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

		prop = config.get(cat.getName(), "claymationRuinRarity", 5);
		structureRarity[++m] = clamp(prop.getInt(5), 1, 200);

		prop = config.get(cat.getName(), "darkHutRarity", 15);
		structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

		prop = config.get(cat.getName(), "spiderNestRarity", 15);
		structureRarity[++m] = clamp(prop.getInt(15), 1, 200);

		prop = config.get(cat.getName(), "memoryCacheRarity", 3);
		structureRarity[++m] = clamp(prop.getInt(3), 1, 200);

		prop = config.get(cat.getName(), "lightSpireRarity", 10);
		structureRarity[++m] = clamp(prop.getInt(10), 1, 200);

		prop = config.get(cat.getName(), "hackerNetRarity", 3);
		structureRarity[++m] = clamp(prop.getInt(3), 1, 200);

		prop = config.get(cat.getName(), "cubeMazeRarity", 25);
		structureRarity[++m] = clamp(prop.getInt(25), 1, 200);

		prop = config.get(cat.getName(), "outlookRarity", 3);
		structureRarity[++m] = clamp(prop.getInt(3), 1, 200);
		
		prop = config.get(cat.getName(), "nekoBarracksRarity", 10);
		structureRarity[++m] = clamp(prop.getInt(10), 1, 200);
		
		prop = config.get(cat.getName(), "nekoWarehouseRarity", 5);
		structureRarity[++m] = clamp(prop.getInt(5), 1, 200);
		
		prop = config.get(cat.getName(), "nekoGuardTowerRarity", 25);
		structureRarity[++m] = clamp(prop.getInt(25), 1, 200);
		
		prop = config.get(cat.getName(), "nekoShelterRarity", 50);
		structureRarity[++m] = clamp(prop.getInt(50), 1, 200);
		
		prop = config.get(cat.getName(), "nekoWalledCityRarity", 3);
		structureRarity[++m] = clamp(prop.getInt(3), 1, 200);
		
		prop = config.get(cat.getName(), "nekoidsFOBRarity", 1);
		structureRarity[++m] = clamp(prop.getInt(1), 1, 200);
		
		prop = config.get(cat.getName(), "nekoidsMansionRarity", 0);
		structureRarity[++m] = clamp(prop.getInt(0), 1, 200);

		cat = config.getCategory(CAT_MISC);
		cat.setComment("Random other options that don't quite fit into other categories.");

		s = "allowRandomWeaponLore";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should mod-exclusive weapons and armor come with randomized Lore?";
		registerObject(s, prop.getBoolean(true));

		s = "allowChallengeScrolls";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Are Challenge Scrolls enabled?";
		registerObject(s, prop.getBoolean(true));

		s = "allowMobStatueDrops";
		prop = config.get(cat.getName(), "allowMobStatueDrops", true);
		prop.comment = "Can certain mobs drop statues?";
		registerObject(s, prop.getBoolean(true));

		s = "allowGeneratorItems";
		prop = config.get(cat.getName(), "allowGeneratorItems", true);
		prop.comment = "Will Creative mode-only generator items be enabled?";
		registerObject(s, prop.getBoolean(true));

		s = "allowItemTimeAltering";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Certain items and blocks may alter in-game time, should this be allowed?";
		registerObject(s, prop.getBoolean(true));

		s = "allowPvP";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Doomsdays and Weapons be able to be used against other players?";
		registerObject(s, prop.getBoolean(true));

		s = "allowDefaultLores";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Lores bundled with the mod be used? Perhaps disable them if you wish to only use your own custom Lore.";
		registerObject(s, prop.getBoolean(true));

		s = "allowCorruptionTransfer";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Corruption transfer between entities?";
		registerObject(s, prop.getBoolean(true));

		s = "challengeScrollDropChance";
		prop = config.get(cat.getName(), s, 5);
		prop.comment = "Chance for Challenge Scrolls to drop off of any mob that you kill.";
		registerObject(s, clamp(prop.getInt(5), 1, 100));

		s = "mobStatueDropChance";
		prop = config.get(cat.getName(), s, 100);
		prop.comment = "Chance for a mob to drop it's corresponding statue. Only certain mobs have these.";
		registerObject(s, clamp(prop.getInt(100), 1, 100));

		cat = config.getCategory(CAT_CLIENT);
		cat.setComment("These are client-side only options, they affect nothing on the server-side.");

		s = "allowAnimatedGui";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Whether or not the Doom GUI is animated.";
		registerObject(s, prop.getBoolean(true));

		s = "allowArmorModels";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Whether or not custom armor models are rendered for armor that has it";
		registerObject(s, prop.getBoolean(true));

		s = "allowWeaponModels";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Whether or not custom weapon models are used for weapons that have it";
		registerObject(s, prop.getBoolean(true));

		s = "allowDivinityColorChange";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Whether the Divinity potion effect has a rainbow-like render overlay";
		registerObject(s, prop.getBoolean(true));

		s = "showDoomGui";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Whether the Doom GUI is rendered";
		registerObject(s, prop.getBoolean(true));

		s = "showAmuletStatusGui";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Whether the Amulet Status GUI is rendered";
		registerObject(s, prop.getBoolean(true));

		s = "allowDimensionalMusic";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Whether Dimension-specific music is played";
		registerObject(s, prop.getBoolean(true));

		s = "allowPotionEffectOverlays";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Whether certain potion effects have an overlay rendered while they are active";
		registerObject(s, prop.getBoolean(true));

		s = "allowExtraDoomsdayInfoInGui";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "If you can activate a Doomsday, displays that Doomsday's information in the Gui";
		registerObject(s, prop.getBoolean(true));
		
		s = "allowCorruptionMobRender";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should mobs render darker the longer they are corrupted? Only affects this mod's mobs.";
		registerObject(s, prop.getBoolean(true));

		s = "guiTransparency";
		prop = config.get(cat.getName(), s, 100);
		registerObject(s, clamp(prop.getInt(100), 1, 100));

		s = "guiTextureSkinID";
		prop = config.get(cat.getName(), s, 0);
		registerObject(s, clamp(prop.getInt(0), 0, 100));

		s = "guiX";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, clampPositive(prop.getInt(1)));

		s = "guiY";
		prop = config.get(cat.getName(), s, 1);
		registerObject(s, clampPositive(prop.getInt(1)));

		cat = config.getCategory(CAT_GRIEF);
		cat.setComment("Toggle whether specific Weapon abilities or Doomsdays damage the terrain.");

		prop = config.get(cat.getName(), "allowNatureDrainDestruction", true);
		griefConfig[m = 0] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowRavageDestruction", true);
		griefConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowFrozenLightningDestruction", true);
		griefConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowMourningStarDestruction", true);
		griefConfig[++m] = prop.getBoolean(true);

		prop = config.get(cat.getName(), "allowTitanDestruction", true);
		griefConfig[++m] = prop.getBoolean(true);

		cat = config.getCategory(CAT_MOBAI);
		cat.setComment("Toggle aspects of each mob's AI and also set specific griefing instances.");

		s = "jabbaAnger";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Jabba species become angry after being in combat for a while and gain new abilities?";
		registerObject(s, prop.getBoolean(true));

		s = "jabbaProjectiles";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Jabba species shoot projectiles at targets when angered?";
		registerObject(s, prop.getBoolean(true));

		s = "plagueCorruption";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Plagues corrupt entities around it?";
		registerObject(s, prop.getBoolean(true));

		s = "gragulDamageReduction";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Gragul species take partial health damage instead of normal damage?";
		registerObject(s, prop.getBoolean(true));

		s = "gragulPercentageDamage";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Gragul species inflict partial health damage instead of normal damage? (They will still do armor piercing damage)";
		registerObject(s, prop.getBoolean(true));

		s = "minotaurCharge";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Minotaurs charge at their target?";
		registerObject(s, prop.getBoolean(true));

		s = "inklingInvisibility";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Inklings become invisible when threatened?";
		registerObject(s, prop.getBoolean(true));

		s = "inklingTorchBreaking";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Inklings randomly destroy torches when near them?";
		registerObject(s, prop.getBoolean(true));

		s = "inklingTeleport";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Inklings teleport?";
		registerObject(s, prop.getBoolean(true));

		s = "ragrExplosions";
		prop = config.get(cat.getName(), s, false);
		prop.comment = "Should Ragrs create explosions when landing?";
		registerObject(s, prop.getBoolean(false));

		s = "ragrBlockCrushing";
		prop = config.get(cat.getName(), s, false);
		prop.comment = "Should Ragrs crush blocks when landing?";
		registerObject(s, prop.getBoolean(false));

		s = "pumpkinheadPumpkinSpawn";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Pumpkinheads create a Home Pumpkin upon spawning?";
		registerObject(s, prop.getBoolean(true));

		s = "pumpkinheadHaste";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Pumpkinheads gain increased stats while they have a Home Pumpkin and are being threatened?";
		registerObject(s, prop.getBoolean(true));

		s = "pumpkinheadPumpkinbombs";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Pumpkinheads fire Pumpkinbombs when they are low on health and have a Home Pumpkin?";
		registerObject(s, prop.getBoolean(true));

		s = "tragicNekoRockets";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Tragic Nekos fire rockets at people's faces?";
		registerObject(s, prop.getBoolean(true));

		s = "tragicNekoStickyBombs";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Tragic Nekos throw Sticky Bombs into people's faces?";
		registerObject(s, prop.getBoolean(true));

		s = "tragicNekoClusterBomb";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Tragic Nekos throw Cluster Bombs at people's faces?";
		registerObject(s, prop.getBoolean(true));

		s = "tragicNekoDeathBomb";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Tragic Nekos have a chance to drop various bombs on death?";
		registerObject(s, prop.getBoolean(true));

		s = "tragicNekoCelebration";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Tragic Nekos celebrate their birthday?";
		registerObject(s, prop.getBoolean(true));

		s = "toxProjectiles";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Tox species fire projectiles? (They still attack on contact)";
		registerObject(s, prop.getBoolean(true));

		s = "cryseReflection";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Cryse species reflect damage?";
		registerObject(s, prop.getBoolean(true));

		s = "norVoxProjectiles";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Nor-Vox species fire projectiles?";
		registerObject(s, prop.getBoolean(true));

		s = "norVoxRegeneration";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Nor-Vox species regenerate health naturally?";
		registerObject(s, prop.getBoolean(true));

		s = "pirahGolden";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Pirah be randomly spawned as a Golden stronger version?";
		registerObject(s, prop.getBoolean(true));

		s = "stinTeleport";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Stin species randomly teleport you away when you attack them?";
		registerObject(s, prop.getBoolean(true));

		s = "abominationHelpCall";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Abominations call for help when they are attacked?";
		registerObject(s, prop.getBoolean(true));

		s = "erkelMushroomSpawning";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Erkels randomly place Mushrooms?";
		registerObject(s, prop.getBoolean(true));

		s = "sirvHelpCall";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Sirvs call for help when they are attacked?";
		registerObject(s, prop.getBoolean(true));

		s = "psygoteSwapTeleport";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Psygotes attempt to swap places with their attacker to confuse them?";
		registerObject(s, prop.getBoolean(true));

		s = "psygoteProjectiles";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Psygotes fire Dark Mortors at their attacker?";
		registerObject(s, prop.getBoolean(true));

		s = "psygoteRegeneration";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Psygotes regenerate health naturally?";
		registerObject(s, prop.getBoolean(true));

		s = "lockbotLockdown";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Lockbots lock down their target?";
		registerObject(s, prop.getBoolean(true));

		s = "harvesterBuffDebuffEntities";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Harvesters buff Synapse mobs and debuff everything else?";
		registerObject(s, prop.getBoolean(true));

		s = "harvesterNanoSwarms";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Harvesters spawn Nano Swarms in to defend them?";
		registerObject(s, prop.getBoolean(true));

		s = "seekerKillbeam";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Seekers kill you with their gaze?";
		registerObject(s, prop.getBoolean(true));

		s = "archangelHolybeam";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Archangels kill you with their Holy Beam of death?";
		registerObject(s, prop.getBoolean(true));

		s = "ireEnergyBurst";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Ires shoot an Ire Energy burst at their target?";
		registerObject(s, prop.getBoolean(true));

		s = "fuseaExplosiveDamage";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Fusea species explode when they take damage?";
		registerObject(s, prop.getBoolean(true));

		s = "fuseaExplosiveAttack";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Fusea species explode when they attack something?";
		registerObject(s, prop.getBoolean(true));

		s = "fuseaExplosiveLayers";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Fusea species only take damage when they explode?";
		registerObject(s, prop.getBoolean(true));

		s = "ranmasCharge";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Ranmas hurl themselves at you?";
		registerObject(s, prop.getBoolean(true));

		s = "ranmasImpactExplosions";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Ranmas create explosions if they impact something at high enough velocity";
		registerObject(s, prop.getBoolean(true));

		s = "parasmiteLeech";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Parasmites attempt to latch onto entities to simultaneously buff and hurt them?";
		registerObject(s, prop.getBoolean(true));

		s = "avrisAnnouncements";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Avris' announce when they spawn, die or elude attackers?";
		registerObject(s, prop.getBoolean(true));

		s = "avrisDespawnTime";
		prop = config.get(cat.getName(), "avrisDespawnTime", true);
		prop.comment = "Should Avris' despawn after a preset amount of time?";
		registerObject(s, prop.getBoolean(true));
		
		s = "jetNekoRockets";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Jet Nekos fire rockets at their target?";
		registerObject(s, prop.getBoolean(true));
		
		s = "jetNekoHovering";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Jet Nekos stop and hover occasionally?";
		registerObject(s, prop.getBoolean(true));
		
		s = "scienceNekoLaser";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Science Nekos fire their ray guns at their target?";
		registerObject(s, prop.getBoolean(true));
		
		s = "scienceNekoPotions";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Science Nekos toss potions at their target?";
		registerObject(s, prop.getBoolean(true));
		
		s = "scienceNekoTargetsUndead";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Science Nekos seek out Undead creatures to kill?";
		registerObject(s, prop.getBoolean(true));
		
		s = "mechaNekoCommandExo";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Mecha Nekos command the Exo they are riding to use abilities?";
		registerObject(s, prop.getBoolean(true)); //should the mecha neko 'command' the Exo to use abilities
		
		s = "mechaNekoRidingExo";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Mecha Nekos spawn in riding a Mecha Exo?";
		registerObject(s, prop.getBoolean(true));
		
		s = "mechaNekoRidingArmor";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Mecha Nekos gain increased armor when riding another entity?";
		registerObject(s, prop.getBoolean(true));
		
		s = "assaultNekoChargeAttack";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Assault Nekos charge towards their target when far away?";
		registerObject(s, prop.getBoolean(true));
		
		s = "assaultNekoShield";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Assault Nekos sometimes get a temporary shield that absorbs damage?";
		registerObject(s, prop.getBoolean(true));
		
		s = "assaultNekoLaserSword";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Assault Nekos do armor piercing damage?";
		registerObject(s, prop.getBoolean(true));
		
		s = "traderNekoTrading";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Trader Nekos be allowed to trade?";
		registerObject(s, prop.getBoolean(true));
		
		s = "traderNekoReleaseTrading";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Trader Nekos only be allowed to trade when they are released?";
		registerObject(s, prop.getBoolean(true));

		s = "kragulSpiritCasts";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Kraguls fire Spirit Casts at their target?";
		registerObject(s, prop.getBoolean(true));

		s = "magmoxLargeFireballs";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Magmox fire Large Fireballs in addition to the smaller projectiles at their target?";
		registerObject(s, prop.getBoolean(true));

		s = "megaCryseShields";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Mega Cryse have shields that block all damage (except Magic)?";
		registerObject(s, prop.getBoolean(true));

		s = "greaterStinCharge";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Greater Stins charge towards their targets?";
		registerObject(s, prop.getBoolean(true));

		s = "stinKingMortors";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Stin Kings fire Dark Mortors at their target?";
		registerObject(s, prop.getBoolean(true));

		s = "stinQueenWebBombs";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Stin Queens fire Web Bombs at their target?";
		registerObject(s, prop.getBoolean(true));

		s = "stinQueenBabies";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Stin Queens spawn in babies to attack their target?";
		registerObject(s, prop.getBoolean(true));

		s = "stinQueenWebs";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Stin Queens trap attackers in webs?";
		registerObject(s, prop.getBoolean(true));

		s = "voxStellarumSpinAttack";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Vox Stellarums do a spinning attack?";
		registerObject(s, prop.getBoolean(true));

		s = "voxStellarumHealing";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Vox Stellarums go into a healing phase to regenerate their health quickly?";
		registerObject(s, prop.getBoolean(true));

		s = "aegarHypermode";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Aegars enter Hypermode after their crystal is damaged and gain increased stats?";
		registerObject(s, prop.getBoolean(true));

		s = "aegarShockwave";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Aegars do a ground shockwave attack?";
		registerObject(s, prop.getBoolean(true));

		s = "aegarLasers";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Aegars fire a laser at their target?";
		registerObject(s, prop.getBoolean(true));

		s = "aegarMortors";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Aegars fire Crystal Mortors at their target?";
		registerObject(s, prop.getBoolean(true));

		s = "volatileFuseaElementalChange";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Volatile Fuseas change elements based on their surroundings? (they gain projectile attacks from this)";
		registerObject(s, prop.getBoolean(true));

		s = "apisSolarbombs";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Apis fire Solar Bombs at it's target?";
		registerObject(s, prop.getBoolean(true));

		s = "apisChargeAttack";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Apis charge towards it's target?";
		registerObject(s, prop.getBoolean(true));

		s = "apisExplosiveCharge";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Apis create an explosion when it charges?";
		registerObject(s, prop.getBoolean(true));

		s = "apisSuperStomp";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Apis do a super stomp attack on nearby targets?";
		registerObject(s, prop.getBoolean(true));

		s = "apisReflection";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Apis be able to reflect attacks temporarily?";
		registerObject(s, prop.getBoolean(true));

		s = "skultarDemeanor";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Skultar use demeanor to change it's attack state?";
		registerObject(s, prop.getBoolean(true));

		s = "skultarProjectiles";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Skultar fire projectiles at it's target?";
		registerObject(s, prop.getBoolean(true));

		s = "skultarClone";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Skultar create clones when it's health is low to confuse attackers?";
		registerObject(s, prop.getBoolean(true));

		s = "skultarWitheringGas";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Skultar create Withering Gas when it's health is low?";
		registerObject(s, prop.getBoolean(true));

		s = "skultarRegeneration";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Skultar regenerate health naturally?";
		registerObject(s, prop.getBoolean(true));

		s = "kitsunakumaFireballExempt";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Kitsunakuma be immune to all damage but Fireball damage?";
		registerObject(s, prop.getBoolean(true));

		s = "kitsunakumaFireballs";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Kitsunakuma shoot fireballs?";
		registerObject(s, prop.getBoolean(true));

		s = "kitsunakumaTeleport";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Kitsunakuma teleport?";
		registerObject(s, prop.getBoolean(true));

		s = "kitsunakumaTaunt";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Kitsunakuma taunt?";
		registerObject(s, prop.getBoolean(true));

		s = "polarisTeleport";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Polaris teleport?";
		registerObject(s, prop.getBoolean(true));

		s = "polarisInvisibility";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Polaris go invisible?";
		registerObject(s, prop.getBoolean(true));

		s = "polarisAfterImage";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Polaris sometimes leave an after-image (clone) when teleporting?";
		registerObject(s, prop.getBoolean(true));

		s = "polarisNighttimeSet";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Polaris automatically set the world to night?";
		registerObject(s, prop.getBoolean(true));

		s = "polarisFearGolems";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Polaris run away from Golems?";
		registerObject(s, prop.getBoolean(true));

		s = "polarisRegeneration";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Polaris regenerate health naturally?";
		registerObject(s, prop.getBoolean(true));

		s = "empariahDemeanor";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Empariah use demeanor to determine it's attack patterns?";
		registerObject(s, prop.getBoolean(true));

		s = "empariahCharge";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Empariah charge towards it's target?";
		registerObject(s, prop.getBoolean(true));

		s = "empariahFrostBreath";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Empariah use a Frost breath attack?";
		registerObject(s, prop.getBoolean(true));

		s = "empariahRoar";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Empariah use a Roar attack?";
		registerObject(s, prop.getBoolean(true));

		s = "empariahRockThrowing";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Empariah throw rocks at it's target?";
		registerObject(s, prop.getBoolean(true));

		s = "empariahSummonAbomination";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Empariah summon Abominations to help it?";
		registerObject(s, prop.getBoolean(true));

		s = "empariahCallHelp";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Empariah call for help of nearby Abominations?";
		registerObject(s, prop.getBoolean(true));

		s = "empariahRegeneration";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Empariah regenerate health naturally?";
		registerObject(s, prop.getBoolean(true));

		s = "timeControllerRegeneration";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Time Controller generate health naturally?";
		registerObject(s, prop.getBoolean(true));

		s = "timeControllerQuantumLeap";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Time Controller use a Quantum Leap attack that reverts entities to their position from a few seconds ago?";
		registerObject(s, prop.getBoolean(true));

		s = "timeControllerFlux";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Time Controller use a Flux attack that pulls entities towards it and absorbs damage?";
		registerObject(s, prop.getBoolean(true));

		s = "timeControllerPurge";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Time Controller use a Purge attack that deflects projectiles and hits entities near it?";
		registerObject(s, prop.getBoolean(true));

		s = "timeControllerSpaz";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Time Controller spaz out when it's health is low and teleport around?";
		registerObject(s, prop.getBoolean(true));

		s = "timeControllerTimeBombs";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Time Controller spawn in Time Bombs that affect entities near them?";
		registerObject(s, prop.getBoolean(true));

		s = "timeControllerTimeAltering";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Time Controller be able to change the world time?";
		registerObject(s, prop.getBoolean(true));

		s = "enyvilDarkCrystals";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Enyvil need Dark Crystals to use it's abilities?";
		registerObject(s, prop.getBoolean(true));

		s = "enyvilDarkLightning";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Enyvil shoot Dark Lightning projectiles?";
		registerObject(s, prop.getBoolean(true));

		s = "enyvilCrystalLaser";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Enyvil be able to use it's Dark Crystals to fire a laser at it's target?";
		registerObject(s, prop.getBoolean(true));

		s = "enyvilDarkEnergySpray";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Enyvil be able to fire Dark Energy at it's target?";
		registerObject(s, prop.getBoolean(true));

		s = "enyvilTractorBeam";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Enyvil be able to Tractor beam far away targets to it?";
		registerObject(s, prop.getBoolean(true));

		s = "enyvilThunderstorm";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Enyvil be able to summon Lightning down around it?";
		registerObject(s, prop.getBoolean(true));

		s = "enyvilLightningExplosions";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Enyvil create explosions when it strikes something with Lightning?";
		registerObject(s, prop.getBoolean(true));

		s = "enyvilSlam";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Enyvil have a Slam attack?";
		registerObject(s, prop.getBoolean(true));

		s = "enyvilDestroyBlocks";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Enyvil destroy blocks if it gets stuck?";
		registerObject(s, prop.getBoolean(true));

		s = "enyvilRegeneration";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Enyvil regenerate health naturally while it has a Dark Crystal?";
		registerObject(s, prop.getBoolean(true));

		s = "claymationTransformation";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Claymation transform into other mobs and inherit their abilities?";
		registerObject(s, prop.getBoolean(true));

		s = "claymationPotionReflection";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Claymation reflect bad potion effects at it's target?";
		registerObject(s, prop.getBoolean(true));
		
		s = "professorNekoidBlaster";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Professor Nekoid use an Aero-Blaster to knock enemies away?";
		registerObject(s, prop.getBoolean(true));
		
		s = "professorNekoidMechaSpawn";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Professor Nekoid spawn in riding a Mecha Exo?";
		registerObject(s, prop.getBoolean(true));
		
		s = "professorNekoidMechaArmor";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Professor Nekoid gain increased armor from riding a Mecha Exo?";
		registerObject(s, prop.getBoolean(true));
		
		s = "professorNekoidUseMecha";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Professor Nekoid use it's ridden Mecha Exo's abilities?";
		registerObject(s, prop.getBoolean(true));
		
		s = "professorNekoidReinforcements";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Professor Nekoid summon reinforcements when it's out of it's mech or it's health is low?";
		registerObject(s, prop.getBoolean(true));
		
		s = "professorNekoidTitanfall";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Professor Nekoid call in a replacement Mecha Exo if it's previous one was destroyed?";
		registerObject(s, prop.getBoolean(true));
		
		s = "professorNekoidDeathRelease";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should Professor Nekoid send out a release signal upon death that releases Nekos in a huge radius around it?";
		registerObject(s, prop.getBoolean(true));

		//all overlord forms
		s = "overlordDivineWeakness";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord species be immune to all damage unless Divinity is active on the attacker?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordBarriers";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord species place their specialized barrier blocks in specific situations?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordGas";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord species place Corrupting gas in specific situations?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordClearSpace";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord species clear space around them to avoid suffocation?";
		registerObject(s, prop.getBoolean(true)); //if the overlord should clear space to avoid suffocation

		s = "overlordTransformation";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord species transform into it's next form upon death?";
		registerObject(s, prop.getBoolean(true)); //if the overlord should transform into it's next form upon death

		s = "overlordTransformationAesthetics";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord species do the transformation idle stuff upon spawning?";
		registerObject(s, prop.getBoolean(true)); //if the overlord should do it's 'transforming' stuff when it spawns in

		s = "overlordNanoSwarms";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord species spawn in Nano Swarms for protection?";
		registerObject(s, prop.getBoolean(true)); //if the overlord should spawn in nano swarms to help it

		//cocoon form
		s = "overlordPhases";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Cocoon instance be in phases, alternating Seekers and damage cycles? If this is disabled you won't need Divinity to damage it.";
		registerObject(s, prop.getBoolean(true));

		s = "overlordSeekers";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Cocoon use Seekers in it's instance? If this is set to false and phases are true, it'll always be in damage cycle.";
		registerObject(s, prop.getBoolean(true));

		//combat form
		s = "overlordUnstableAnomalies";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Combat become unstable and spawn anomalies? If this is disabled, you won't need Divinity to damage it.";
		registerObject(s, prop.getBoolean(true));

		s = "overlordMegaLeap";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Combat leap into the air and do a massive slam onto the ground?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordSlashAttack";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Combat have an Area of Effect on it's normal attack?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordChargeAttack";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Combat turtle up and charge towards it's target?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordGrappleAttack";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Combat pull targets towards it with an attracting laser beam?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordReflection";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Combat cocoon up and reflect all incoming damage?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordHunters";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Combat spawn Hunters to attack it's target?";
		registerObject(s, prop.getBoolean(true));

		//core form
		s = "overlordDeleteBlocks";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Core delete blocks it passes through?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordMortors";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Core hover and fire mortors to create anomalies? If this is disabled, you won't need Divinity to damage it.";
		registerObject(s, prop.getBoolean(true));

		s = "overlordVulnerable";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Core become vulnerable and flee when it takes damage?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordGrabAttack";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Core grab nearby targets and focus attacks on them while carrying them high up into the sky?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordRegeneration";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Core regenerate health naturally?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordPlatform";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Core generate a platform to catch it's drops upon death?";
		registerObject(s, prop.getBoolean(true));

		s = "overlordSentinelDrop";
		prop = config.get(cat.getName(), s, true);
		prop.comment = "Should the Overlord Core drop a Sentinel upon death every time?";
		registerObject(s, prop.getBoolean(true));

		cat = config.getCategory(CAT_MODIFIERS);
		cat.setComment("Set each mob's modifier's actual amount, these can also be used to disable them by setting them to 0 in most cases.");

		prop = config.get(cat.getName(), "claymationSpeedDebuff", -1.0);
		prop.comment = "For any of the Claymation's forms that require it to have it's speed debuffed.";
		modifier[m = 0] = prop.getDouble(-1.0);

		prop = config.get(cat.getName(), "kitsunakumaSpeedDebuff", -0.5);
		prop.comment = "For when the Kitsunakuma is preparing to fire a fireball or taunt.";
		modifier[++m] = prop.getDouble(-0.5);

		prop = config.get(cat.getName(), "timeControllerSpeedBuff", 0.5);
		prop.comment = "For when the Time Controller is in Purge mode.";
		modifier[++m] = prop.getDouble(0.55);

		prop = config.get(cat.getName(), "empariahSpeedDebuff", -0.5);
		prop.comment = "For when the Empariah is about to throw a rock or use it's Frost Breath.";
		modifier[++m] = prop.getDouble(-0.5);

		prop = config.get(cat.getName(), "aegarSpeedBuff", 0.156);
		prop.comment= "For when the Aegar has it's Core destroyed and it is in Hypermode.";
		modifier[++m] = prop.getDouble(0.156);

		prop = config.get(cat.getName(), "megaCryseAttackBuff", 2.0);
		prop.comment = "For when the Mega Cryse has all of it's shields destroyed.";
		modifier[++m] = prop.getDouble(2.0);

		prop = config.get(cat.getName(), "jabbaAttackBuff", 2.5);
		prop.comment = "For when Jabba, Janna or Jarra is below half health.";
		modifier[++m] = prop.getDouble(2.5);

		prop = config.get(cat.getName(), "norVoxSpeedDebuff", -0.5);
		prop.comment = "For when the Nor-Vox, Star-Vox or Vox Stellarum is about to fire a projectile.";
		modifier[++m] = prop.getDouble(-0.5);

		prop = config.get(cat.getName(), "psygoteSpeedDebuff", -0.5);
		prop.comment = "For when the Psygote is about to teleport or fire a projectile.";
		modifier[++m] = prop.getDouble(-0.5);

		prop = config.get(cat.getName(), "tragicNekoSpeedDebuff", -0.5);
		prop.comment = "For when the Tragic Neko is about to fire or throw a projectile.";
		modifier[++m] = prop.getDouble(-0.5);

		prop = config.get(cat.getName(), "dynamicMobHealthBuff", 20.0);
		prop.comment = "Health buff for mod-exclusive mobs on Hard difficulty. Only affects mobs that have over double of this value for their base Max Health value.";
		modifier[++m] = prop.getDouble(20.0);

		prop = config.get(cat.getName(), "dynamicMobHealthDebuff", -20.0);
		prop.comment = "Health debuff for mod-exclusive mobs on Easy difficulty. Only affects mobs that have over double of this value for their base Max Health.";
		modifier[++m] = prop.getDouble(-20.0);

		prop = config.get(cat.getName(), "dynamicBossHealthBuff", 50.0);
		prop.comment = "Health buff for mod-exclusive Bosses on Hard difficulty. Only affects mobs that have over 25% of this value for their base Max Health.";
		modifier[++m] = prop.getDouble(50.0);

		prop = config.get(cat.getName(), "apisSpeedDebuff", -0.5);
		prop.comment = "For when Apis is using it's Stomp attack.";
		modifier[++m] = prop.getDouble(-0.5);

		prop = config.get(cat.getName(), "ghastHealthBuff", 30.0);
		prop.comment = "Vanilla mob buff for Ghasts.";
		modifier[++m] = prop.getDouble(30.0);

		prop = config.get(cat.getName(), "zombieSkeletonCreeperHealthBuff", 10.0);
		prop.comment = "Vanilla mob buff for Skeletons, Creepers and Zombies.";
		modifier[++m] = prop.getDouble(10.0);

		prop = config.get(cat.getName(), "endermanHealthBuff", 20.0);
		prop.comment = "Vanilla mob buff for Endermen.";
		modifier[++m] = prop.getDouble(20.0);

		prop = config.get(cat.getName(), "spiderHealthBuff", 8.0);
		prop.comment = "Vanilla mob buff for Spiders.";
		modifier[++m] = prop.getDouble(8.0);

		prop = config.get(cat.getName(), "mobFollowRangeDebuff", -16.0);
		prop.comment = "Overall mob debuff for when a mob has Blindness.";
		modifier[++m] = prop.getDouble(-16.0);

		prop = config.get(cat.getName(), "hydrationKnockbackResistanceBuff", 1.0);
		prop.comment = "The Knockback Resistance buff that the Hydration Talisman gives you when you're in water or it's raining.";
		modifier[++m] = prop.getDouble(1.0);

		prop = config.get(cat.getName(), "lightningRodAttackBuff", 5.0);
		prop.comment = "The Attack Damage buff that the Lightning Rod Talisman gives you when it's thundering.";
		modifier[++m] = prop.getDouble(5.0);

		prop = config.get(cat.getName(), "moonlightHealthBuff", 10.0);
		prop.comment = "The Health buff that the Moonlight Talisman gives you when it's nighttime and it's not raining or thundering.";
		modifier[++m] = prop.getDouble(10.0);

		prop = config.get(cat.getName(), "synthesisHealthBuff", 10.0);
		prop.comment = "The Health buff that the Synthesis Talisman gives you when it's daytime and not raining or thundering.";
		modifier[++m] = prop.getDouble(10.0);

		prop = config.get(cat.getName(), "butcherKnockbackResistanceBuff", 1.0);
		prop.comment = "The Knockback Resistance buff you gain from holding the Butcher.";
		modifier[++m] = prop.getDouble(1.0);

		prop = config.get(cat.getName(), "overlordArmorHealthBuff", 5.0);
		prop.comment = "The Health buff you gain from wearing any of the Overlord Armor set.";
		modifier[++m] = prop.getDouble(5.0);

		if (config.hasChanged()) config.save();
	}
}
