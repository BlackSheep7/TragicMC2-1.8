package tragicneko.tragicmc;

import java.util.ArrayList;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import tragicneko.tragicmc.entity.EntityDarkCrystal;
import tragicneko.tragicmc.entity.EntityDimensionalAnomaly;
import tragicneko.tragicmc.entity.EntityDirectedLightning;
import tragicneko.tragicmc.entity.EntityKurayami;
import tragicneko.tragicmc.entity.EntityLock;
import tragicneko.tragicmc.entity.EntityMechaExo;
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
import tragicneko.tragicmc.entity.boss.EntityProfessorNekoid;
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
import tragicneko.tragicmc.entity.mob.EntityAssaultNeko;
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
import tragicneko.tragicmc.entity.mob.EntityMechaNeko;
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
import tragicneko.tragicmc.entity.mob.EntityScienceNeko;
import tragicneko.tragicmc.entity.mob.EntitySeeker;
import tragicneko.tragicmc.entity.mob.EntitySirv;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.EntityTraderNeko;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.entity.mob.EntityWorkerNeko;
import tragicneko.tragicmc.entity.projectile.EntityBanana;
import tragicneko.tragicmc.entity.projectile.EntityCrystalMortor;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.entity.projectile.EntityDarkLightning;
import tragicneko.tragicmc.entity.projectile.EntityDarkMortor;
import tragicneko.tragicmc.entity.projectile.EntityEnergyCharge;
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
import tragicneko.tragicmc.util.TragicEntityList;
import tragicneko.tragicmc.util.TragicEntityList.EnumEggType;

public class TragicEntities {

	public static final EnumCreatureAttribute Natural = EnumHelper.addCreatureAttribute("NATURAL");
	public static final EnumCreatureAttribute Beast = EnumHelper.addCreatureAttribute("BEAST");
	public static final EnumCreatureAttribute Synapse = EnumHelper.addCreatureAttribute("SYNAPSE");

	public static void load()
	{
		int id = 0;
		int listid = 0;

		BiomeGenBase[] biomes = BiomeGenBase.getBiomeGenArray();
		ArrayList<BiomeGenBase> list = new ArrayList<BiomeGenBase>();
		BiomeGenBase[] spawns = new BiomeGenBase[1];
		boolean allowVanillaSpawns = TragicConfig.getBoolean("allowNonDimensionMobSpawns");

		if (TragicConfig.getBoolean("allowJabba"))
		{
			EntityRegistry.registerModEntity(EntityJabba.class, "Jabba", listid++, TragicMC.getInstance(), 80, 1, true);

			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("jabbaSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("jabbaSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Jabba.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityJabba.class, TragicConfig.getInt("jabbaSpawnChance"), TragicConfig.getIntArray("jabbaGroupSize")[0], TragicConfig.getIntArray("jabbaGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityJabba.class, TragicConfig.getInt("jabbaSpawnChance"), TragicConfig.getIntArray("jabbaGroupSize")[0], TragicConfig.getIntArray("jabbaGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.hell,
							BiomeGenBase.desert,
							BiomeGenBase.desertHills,
							BiomeGenBase.mesa,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F
							);
				}
			}
			TragicEntityList.addMapping(EntityJabba.class, "TragicMC.Jabba", id++, 0xDA3600, 0xFF961D);
		}

		if (TragicConfig.getBoolean("allowPlague"))
		{
			EntityRegistry.registerModEntity(EntityPlague.class, "Plague", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("plagueSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("plagueSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Plague.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityPlague.class, TragicConfig.getInt("plagueSpawnChance"), TragicConfig.getIntArray("plagueGroupSize")[0], TragicConfig.getIntArray("plagueGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityPlague.class, TragicConfig.getInt("plagueSpawnChance"), TragicConfig.getIntArray("plagueGroupSize")[0], TragicConfig.getIntArray("plagueGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.beach,
							BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills,
							BiomeGenBase.coldBeach,
							BiomeGenBase.coldTaiga,
							BiomeGenBase.coldTaigaHills,
							BiomeGenBase.deepOcean,
							BiomeGenBase.desert,
							BiomeGenBase.desertHills,
							BiomeGenBase.extremeHills,
							BiomeGenBase.extremeHillsEdge,
							BiomeGenBase.extremeHillsPlus,
							BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.frozenOcean,
							BiomeGenBase.frozenRiver,
							BiomeGenBase.hell,
							BiomeGenBase.iceMountains,
							BiomeGenBase.icePlains,
							BiomeGenBase.jungle,
							BiomeGenBase.jungleEdge,
							BiomeGenBase.jungleHills,
							BiomeGenBase.megaTaiga,
							BiomeGenBase.megaTaigaHills,
							BiomeGenBase.mesa,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F,
							BiomeGenBase.mushroomIsland,
							BiomeGenBase.mushroomIslandShore,
							BiomeGenBase.ocean,
							BiomeGenBase.plains,
							BiomeGenBase.river,
							BiomeGenBase.roofedForest,
							BiomeGenBase.savanna,
							BiomeGenBase.savannaPlateau,
							BiomeGenBase.sky,
							BiomeGenBase.stoneBeach,
							BiomeGenBase.swampland,
							BiomeGenBase.taiga,
							BiomeGenBase.taigaHills
							);
				}
			}
			TragicEntityList.addMapping(EntityPlague.class, "TragicMC.Plague", id++, 0x121212, 0x121212);
		}

		if (TragicConfig.getBoolean("allowGragul"))
		{
			EntityRegistry.registerModEntity(EntityGragul.class, "Gragul", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("gragulSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("gragulSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Gragul.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityGragul.class, TragicConfig.getInt("gragulSpawnChance"), TragicConfig.getIntArray("gragulGroupSize")[0], TragicConfig.getIntArray("gragulGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityGragul.class, TragicConfig.getInt("gragulSpawnChance"), TragicConfig.getIntArray("gragulGroupSize")[0], TragicConfig.getIntArray("gragulGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.desertHills,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F,
							BiomeGenBase.extremeHills,
							BiomeGenBase.extremeHillsPlus,
							BiomeGenBase.megaTaiga,
							BiomeGenBase.megaTaigaHills,
							BiomeGenBase.roofedForest,
							BiomeGenBase.swampland,
							BiomeGenBase.mushroomIsland,
							BiomeGenBase.mushroomIslandShore
							);
				}
			}
			TragicEntityList.addMapping(EntityGragul.class, "TragicMC.Gragul", id++, 0x777777, 0xAAAAAA);
		}

		if (TragicConfig.getBoolean("allowMinotaur"))
		{
			EntityRegistry.registerModEntity(EntityMinotaur.class, "Minotaur", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("minotaurSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("minotaurSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Minotaur.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityMinotaur.class, TragicConfig.getInt("minotaurSpawnChance"), TragicConfig.getIntArray("minotaurGroupSize")[0], TragicConfig.getIntArray("minotaurGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityMinotaur.class, TragicConfig.getInt("minotaurSpawnChance"), TragicConfig.getIntArray("minotaurGroupSize")[0], TragicConfig.getIntArray("minotaurGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.plains,
							BiomeGenBase.savanna,
							BiomeGenBase.savannaPlateau,
							BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills,
							BiomeGenBase.mesa,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F,
							BiomeGenBase.extremeHills,
							BiomeGenBase.extremeHillsEdge,
							BiomeGenBase.extremeHillsPlus
							);
				}
			}
			TragicEntityList.addMapping(EntityMinotaur.class, "TragicMC.Minotaur", id++, 0x683C1F, 0x351F10);
			DungeonHooks.addDungeonMob("Minotaur", 50);
		}

		if (TragicConfig.getBoolean("allowInkling"))
		{
			EntityRegistry.registerModEntity(EntityInkling.class, "Inkling", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("inklingSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("inklingSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Jabba.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityInkling.class, TragicConfig.getInt("inklingSpawnChance"), TragicConfig.getIntArray("inklingGroupSize")[0], TragicConfig.getIntArray("inklingGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityInkling.class, TragicConfig.getInt("inklingSpawnChance"), TragicConfig.getIntArray("inklingGroupSize")[0], TragicConfig.getIntArray("inklingGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.beach,
							BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills,
							BiomeGenBase.coldBeach,
							BiomeGenBase.coldTaiga,
							BiomeGenBase.coldTaigaHills,
							BiomeGenBase.deepOcean,
							BiomeGenBase.desert,
							BiomeGenBase.desertHills,
							BiomeGenBase.extremeHills,
							BiomeGenBase.extremeHillsEdge,
							BiomeGenBase.extremeHillsPlus,
							BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.frozenOcean,
							BiomeGenBase.frozenRiver,
							BiomeGenBase.iceMountains,
							BiomeGenBase.icePlains,
							BiomeGenBase.jungle,
							BiomeGenBase.jungleEdge,
							BiomeGenBase.jungleHills,
							BiomeGenBase.megaTaiga,
							BiomeGenBase.megaTaigaHills,
							BiomeGenBase.mesa,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F,
							BiomeGenBase.mushroomIsland,
							BiomeGenBase.mushroomIslandShore,
							BiomeGenBase.ocean,
							BiomeGenBase.plains,
							BiomeGenBase.river,
							BiomeGenBase.roofedForest,
							BiomeGenBase.savanna,
							BiomeGenBase.savannaPlateau,
							BiomeGenBase.stoneBeach,
							BiomeGenBase.swampland,
							BiomeGenBase.taiga,
							BiomeGenBase.taigaHills
							);
				}
			}
			TragicEntityList.addMapping(EntityInkling.class, "TragicMC.Inkling", id++, 0x222222, 0x333333);
		}

		if (TragicConfig.getBoolean("allowRagr"))
		{
			EntityRegistry.registerModEntity(EntityRagr.class, "Ragr", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("ragrSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("ragrSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Ragr.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityRagr.class, TragicConfig.getInt("ragrSpawnChance"), TragicConfig.getIntArray("ragrGroupSize")[0], TragicConfig.getIntArray("ragrGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityRagr.class, TragicConfig.getInt("ragrSpawnChance"), TragicConfig.getIntArray("ragrGroupSize")[0], TragicConfig.getIntArray("ragrGroupSize")[1], EnumCreatureType.MONSTER,
							BiomeGenBase.taiga,
							BiomeGenBase.taigaHills,
							BiomeGenBase.coldTaiga,
							BiomeGenBase.coldTaigaHills,
							BiomeGenBase.icePlains,
							BiomeGenBase.iceMountains
							);
				}
			}
			TragicEntityList.addMapping(EntityRagr.class, "TragicMC.Ragr", id++, 0x94C3D9, 0x406779);
			DungeonHooks.addDungeonMob("Ragr", 25);
		}

		if (TragicConfig.getBoolean("allowPumpkinhead"))
		{
			EntityRegistry.registerModEntity(EntityPumpkinhead.class, "Pumpkinhead", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("pumpkinheadSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("pumpkinheadSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Pumpkinhead.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityPumpkinhead.class, TragicConfig.getInt("pumpkinheadSpawnChance"), TragicConfig.getIntArray("pumpkinheadGroupSize")[0], TragicConfig.getIntArray("pumpkinheadGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityPumpkinhead.class, TragicConfig.getInt("pumpkinheadSpawnChance"), TragicConfig.getIntArray("pumpkinheadGroupSize")[0], TragicConfig.getIntArray("pumpkinheadGroupSize")[1], EnumCreatureType.MONSTER,
							BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills,
							BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.megaTaiga,
							BiomeGenBase.megaTaigaHills,
							BiomeGenBase.mushroomIsland,
							BiomeGenBase.mushroomIslandShore,
							BiomeGenBase.plains,
							BiomeGenBase.roofedForest,
							BiomeGenBase.savanna,
							BiomeGenBase.savannaPlateau,
							BiomeGenBase.taiga,
							BiomeGenBase.taigaHills
							);
				}
			}
			TragicEntityList.addMapping(EntityPumpkinhead.class, "TragicMC.Pumpkinhead", id++, 0xFD9229, 0x333333);
		}

		if (TragicConfig.getBoolean("allowTragicNeko"))
		{
			EntityRegistry.registerModEntity(EntityTragicNeko.class, "TragicNeko", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityTragicNeko.class, "TragicMC.TragicNeko", id++, 0x373535, 0x853B3B);

			if (allowVanillaSpawns && TragicConfig.getBoolean("tragicNekoSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("tragicNekoSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Tragic Neko.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityTragicNeko.class, TragicConfig.getInt("tragicNekoSpawnChance"), TragicConfig.getIntArray("tragicNekoGroupSize")[0], TragicConfig.getIntArray("tragicNekoGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowTox"))
		{
			EntityRegistry.registerModEntity(EntityTox.class, "Tox", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("toxSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("toxSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Tox.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityTox.class, TragicConfig.getInt("toxSpawnChance"), TragicConfig.getIntArray("toxGroupSize")[0], TragicConfig.getIntArray("toxGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityTox.class, TragicConfig.getInt("toxSpawnChance"), TragicConfig.getIntArray("toxGroupSize")[0], TragicConfig.getIntArray("toxGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.roofedForest,
							BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills,
							BiomeGenBase.jungle,
							BiomeGenBase.jungleHills
							);
				}
			}
			TragicEntityList.addMapping(EntityTox.class, "TragicMC.Tox", id++, 0xDACF18, 0x15A915);
		}

		if (TragicConfig.getBoolean("allowCryse"))
		{
			EntityRegistry.registerModEntity(EntityCryse.class, "Cryse", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("cryseSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("cryseSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Cryse.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityCryse.class, TragicConfig.getInt("cryseSpawnChance"), TragicConfig.getIntArray("cryseGroupSize")[0], TragicConfig.getIntArray("cryseGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityCryse.class, TragicConfig.getInt("cryseSpawnChance"), TragicConfig.getIntArray("cryseGroupSize")[0], TragicConfig.getIntArray("cryseGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.icePlains,
							BiomeGenBase.iceMountains,
							BiomeGenBase.coldTaiga,
							BiomeGenBase.coldTaigaHills
							);
				}
			}
			TragicEntityList.addMapping(EntityCryse.class, "TragicMC.Cryse", id++, 0xCEE3E3, 0xFFFFFF);
		}

		if (TragicConfig.getBoolean("allowNorVox"))
		{
			EntityRegistry.registerModEntity(EntityNorVox.class, "NorVox", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("norVoxSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("norVoxSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Nor-Vox.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityNorVox.class, TragicConfig.getInt("norVoxSpawnChance"), TragicConfig.getIntArray("norVoxGroupSize")[0], TragicConfig.getIntArray("norVoxGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityNorVox.class, TragicConfig.getInt("norVoxSpawnChance"), TragicConfig.getIntArray("norVoxGroupSize")[0], TragicConfig.getIntArray("norVoxGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills,
							BiomeGenBase.deepOcean,
							BiomeGenBase.extremeHills,
							BiomeGenBase.extremeHillsEdge,
							BiomeGenBase.extremeHillsPlus,
							BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.jungle,
							BiomeGenBase.jungleEdge,
							BiomeGenBase.jungleHills,
							BiomeGenBase.megaTaiga,
							BiomeGenBase.megaTaigaHills,
							BiomeGenBase.mesa,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F,
							BiomeGenBase.mushroomIsland,
							BiomeGenBase.mushroomIslandShore,
							BiomeGenBase.ocean,
							BiomeGenBase.plains,
							BiomeGenBase.river,
							BiomeGenBase.roofedForest,
							BiomeGenBase.savanna,
							BiomeGenBase.savannaPlateau,
							BiomeGenBase.stoneBeach,
							BiomeGenBase.swampland,
							BiomeGenBase.taiga,
							BiomeGenBase.taigaHills
							);
				}
			}
			TragicEntityList.addMapping(EntityNorVox.class, "TragicMC.NorVox", id++, 0x000000, 0x565656);
		}

		if (TragicConfig.getBoolean("allowPirah"))
		{
			EntityRegistry.registerModEntity(EntityPirah.class, "Pirah", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("pirahSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("pirahSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Pirah.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityPirah.class, TragicConfig.getInt("pirahSpawnChance"), TragicConfig.getIntArray("pirahGroupSize")[0], TragicConfig.getIntArray("pirahGroupSize")[1], EnumCreatureType.WATER_CREATURE, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityPirah.class, TragicConfig.getInt("pirahSpawnChance"), TragicConfig.getIntArray("pirahGroupSize")[0], TragicConfig.getIntArray("pirahGroupSize")[1], EnumCreatureType.WATER_CREATURE, BiomeGenBase.deepOcean,
							BiomeGenBase.ocean,
							BiomeGenBase.river
							);
				}
			}
			TragicEntityList.addMapping(EntityPirah.class, "TragicMC.Pirah", id++, 0x69A2FF, 0xFF6666);
		}

		if (TragicConfig.getBoolean("allowStin"))
		{
			EntityRegistry.registerModEntity(EntityStin.class, "Stin", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityStin.class, "TragicMC.Stin", id++, 0x676767, 0x454545);

			if (allowVanillaSpawns && TragicConfig.getBoolean("stinSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("stinSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Stin.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityStin.class, TragicConfig.getInt("stinSpawnChance"), TragicConfig.getIntArray("stinGroupSize")[0], TragicConfig.getIntArray("stinGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowKindlingSpirit"))
		{
			EntityRegistry.registerModEntity(EntityWisp.class, "Wisp", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("kindlingSpiritSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("kindlingSpiritSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Kindling Spirit.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityWisp.class, TragicConfig.getInt("kindlingSpiritSpawnChance"), TragicConfig.getIntArray("kindlingSpiritGroupSize")[0], TragicConfig.getIntArray("kindlingSpiritGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityWisp.class, TragicConfig.getInt("kindlingSpiritSpawnChance"), TragicConfig.getIntArray("kindlingSpiritGroupSize")[0], TragicConfig.getIntArray("kindlingSpiritGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.roofedForest,
							BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills,
							BiomeGenBase.jungle,
							BiomeGenBase.jungleHills,
							BiomeGenBase.desert,
							BiomeGenBase.desertHills,
							BiomeGenBase.mesa
							);
				}
			}
			TragicEntityList.addMapping(EntityWisp.class, "TragicMC.Wisp", id++, 0xFF2323, 0xCB6B4B);
		}

		if (TragicConfig.getBoolean("allowAbomination"))
		{
			EntityRegistry.registerModEntity(EntityAbomination.class, "Abomination", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("abominationSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("abominationSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Abomination.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityAbomination.class, TragicConfig.getInt("abominationSpawnChance"), TragicConfig.getIntArray("abominationGroupSize")[0], TragicConfig.getIntArray("abominationGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityAbomination.class, TragicConfig.getInt("abominationSpawnChance"), TragicConfig.getIntArray("abominationGroupSize")[0], TragicConfig.getIntArray("abominationGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.icePlains,
							BiomeGenBase.iceMountains,
							BiomeGenBase.frozenOcean,
							BiomeGenBase.frozenRiver,
							BiomeGenBase.coldBeach,
							BiomeGenBase.coldTaiga,
							BiomeGenBase.coldTaigaHills
							);
				}
			}
			TragicEntityList.addMapping(EntityAbomination.class, "TragicMC.Abomination", id++, 0xCDCDCD, 0xA9AFB7);
		}

		if (TragicConfig.getBoolean("allowErkel"))
		{
			EntityRegistry.registerModEntity(EntityErkel.class, "Erkel", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityErkel.class, "TragicMC.Erkel", id++, 0x43BD43, 0x30663D);

			if (allowVanillaSpawns && TragicConfig.getBoolean("erkelSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("erkelSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Erkel.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityErkel.class, TragicConfig.getInt("erkelSpawnChance"), TragicConfig.getIntArray("erkelGroupSize")[0], TragicConfig.getIntArray("erkelGroupSize")[1], EnumCreatureType.MONSTER, spawns);
				}
			}
		}

		if (TragicConfig.getBoolean("allowSirv"))
		{
			EntityRegistry.registerModEntity(EntitySirv.class, "Sirv", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntitySirv.class, "TragicMC.Sirv", id++, 0xADADAD, 0xBDBDBD);

			if (allowVanillaSpawns && TragicConfig.getBoolean("sirvSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("sirvSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Sirv.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntitySirv.class, TragicConfig.getInt("sirvSpawnChance"), TragicConfig.getIntArray("sirvGroupSize")[0], TragicConfig.getIntArray("sirvGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowPsygote"))
		{
			EntityRegistry.registerModEntity(EntityPsygote.class, "Psygote", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityPsygote.class, "TragicMC.Psygote", id++, 0x8965A4, 0x000000);

			if (allowVanillaSpawns && TragicConfig.getBoolean("psygoteSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("psygoteSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Psygote.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityPsygote.class, TragicConfig.getInt("psygoteSpawnChance"), TragicConfig.getIntArray("psygoteGroupSize")[0], TragicConfig.getIntArray("psygoteGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowLockbot"))
		{
			EntityRegistry.registerModEntity(EntityLockbot.class, "Lockbot", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityLockbot.class, "TragicMC.Lockbot", id++, 0x121212, 0x60D6D7);

			if (allowVanillaSpawns && TragicConfig.getBoolean("lockbotSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("lockbotSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Lockbot.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntitySirv.class, TragicConfig.getInt("lockbotSpawnChance"), TragicConfig.getIntArray("lockbotGroupSize")[0], TragicConfig.getIntArray("lockbotGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowNanoSwarm"))
		{
			EntityRegistry.registerModEntity(EntityNanoSwarm.class, "NanoSwarm", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityNanoSwarm.class, "TragicMC.NanoSwarm", id++, 0xFFFFFF, 0xAAAAAA);

			if (allowVanillaSpawns && TragicConfig.getBoolean("nanoSwarmSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("nanoSwarmSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Nano Swarm.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityNanoSwarm.class, TragicConfig.getInt("nanoSwarmSpawnChance"), TragicConfig.getIntArray("nanoSwarmGroupSize")[0], TragicConfig.getIntArray("nanoSwarmGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowHunter"))
		{
			EntityRegistry.registerModEntity(EntityHunter.class, "Hunter", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityHunter.class, "TragicMC.Hunter", id++, 0x60D6D7, 0x888888);

			if (allowVanillaSpawns && TragicConfig.getBoolean("hunterSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("hunterSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Jabba.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityHunter.class, TragicConfig.getInt("hunterSpawnChance"), TragicConfig.getIntArray("hunterGroupSize")[0], TragicConfig.getIntArray("hunterGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowHarvester"))
		{
			EntityRegistry.registerModEntity(EntityHarvester.class, "Harvester", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityHarvester.class, "TragicMC.Harvester", id++, 0x555555, 0x53BBBC);

			if (allowVanillaSpawns && TragicConfig.getBoolean("harvesterSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("harvesterSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Harvester.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityHarvester.class, TragicConfig.getInt("harvesterSpawnChance"), TragicConfig.getIntArray("harvesterGroupSize")[0], TragicConfig.getIntArray("harvesterGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowSeeker") || TragicConfig.getBoolean("allowOverlord")) //So that you can separately have the Seeker's own spawns, otherwise it's required for the Overlord encounter
		{
			EntityRegistry.registerModEntity(EntitySeeker.class, "Seeker", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntitySeeker.class, "TragicMC.Seeker", id++, 0x53BBBC, 0x464646);

			if (allowVanillaSpawns && TragicConfig.getBoolean("seekerSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("seekerSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Seeker.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntitySeeker.class, TragicConfig.getInt("seekerSpawnChance"), TragicConfig.getIntArray("seekerGroupSize")[0], TragicConfig.getIntArray("seekerGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowArchangel"))
		{
			EntityRegistry.registerModEntity(EntityArchangel.class, "Archangel", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityArchangel.class, "TragicMC.Archangel", id++, 0xFFFCA0, 0xFFFCA0);

			if (allowVanillaSpawns && TragicConfig.getBoolean("archangelSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("archangelSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Archangel.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityArchangel.class, TragicConfig.getInt("archangelSpawnChance"), TragicConfig.getIntArray("archangelGroupSize")[0], TragicConfig.getIntArray("archangelGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowIre"))
		{
			EntityRegistry.registerModEntity(EntityIre.class, "Ire", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityIre.class, "TragicMC.Ire", id++, 0xFFFFC3, 0xFFFFC3);

			if (allowVanillaSpawns && TragicConfig.getBoolean("ireSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("ireSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Ire.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityIre.class, TragicConfig.getInt("ireSpawnChance"), TragicConfig.getIntArray("ireGroupSize")[0], TragicConfig.getIntArray("ireGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowFusea"))
		{
			EntityRegistry.registerModEntity(EntityFusea.class, "Fusea", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityFusea.class, "TragicMC.Fusea", id++, 0xE4B1E0, 0xA0E39D);

			if (allowVanillaSpawns && TragicConfig.getBoolean("fuseaSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("fuseaSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Fusea.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityFusea.class, TragicConfig.getInt("fuseaSpawnChance"), TragicConfig.getIntArray("fuseaGroupSize")[0], TragicConfig.getIntArray("fuseaGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowRanmas"))
		{
			EntityRegistry.registerModEntity(EntityRanmas.class, "Ranmas", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityRanmas.class, "TragicMC.Ranmas", id++, 0xDCDCDC, 0xCCCCCC);

			if (allowVanillaSpawns && TragicConfig.getBoolean("ranmasSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("ranmasSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Ranmas.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityRanmas.class, TragicConfig.getInt("ranmasSpawnChance"), TragicConfig.getIntArray("ranmasGroupSize")[0], TragicConfig.getIntArray("ranmasGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowParasmite"))
		{
			EntityRegistry.registerModEntity(EntityParasmite.class, "Parasmite", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityParasmite.class, "TragicMC.Parasmite", id++, 0xAF00A6, 0x581354);

			if (allowVanillaSpawns && TragicConfig.getBoolean("parasmiteSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("parasmiteSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Parasmite.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityParasmite.class, TragicConfig.getInt("parasmiteSpawnChance"), TragicConfig.getIntArray("parasmiteGroupSize")[0], TragicConfig.getIntArray("parasmiteGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowAvris"))
		{
			EntityRegistry.registerModEntity(EntityAvris.class, "Avris", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityAvris.class, "TragicMC.Avris", id++, 0xB81B1B, 0x761E1E);

			if (allowVanillaSpawns && TragicConfig.getBoolean("avrisSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("avrisSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Avris.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityAvris.class, TragicConfig.getInt("avrisSpawnChance"), TragicConfig.getIntArray("avrisGroupSize")[0], TragicConfig.getIntArray("avrisGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowJetNeko"))
		{
			EntityRegistry.registerModEntity(EntityJetNeko.class, "JetNeko", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityJetNeko.class, "TragicMC.JetNeko", id++, 0x308FAE, 0x363636);

			if (allowVanillaSpawns && TragicConfig.getBoolean("jetNekoSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("jetNekoSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Jet Neko.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityJetNeko.class, TragicConfig.getInt("jetNekoSpawnChance"), TragicConfig.getIntArray("jetNekoGroupSize")[0], TragicConfig.getIntArray("jetNekoGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowScienceNeko"))
		{
			EntityRegistry.registerModEntity(EntityScienceNeko.class, "ScienceNeko", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityScienceNeko.class, "TragicMC.ScienceNeko", id++, 0xDDDDDD, 0xE0E0E0);

			if (allowVanillaSpawns && TragicConfig.getBoolean("scienceNekoSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("scienceNekoSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Science Neko.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityScienceNeko.class, TragicConfig.getInt("scienceNekoSpawnChance"), TragicConfig.getIntArray("scienceNekoGroupSize")[0], TragicConfig.getIntArray("scienceNekoGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowMechaNeko"))
		{
			EntityRegistry.registerModEntity(EntityMechaNeko.class, "MechaNeko", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityMechaNeko.class, "TragicMC.MechaNeko", id++, 0xF6B252, 0xA2673C);

			if (allowVanillaSpawns && TragicConfig.getBoolean("mechaNekoSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("mechaNekoSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Mecha Neko.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityMechaNeko.class, TragicConfig.getInt("mechaNekoSpawnChance"), TragicConfig.getIntArray("mechaNekoGroupSize")[0], TragicConfig.getIntArray("mechaNekoGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}
		
		if (TragicConfig.getBoolean("allowAssaultNeko"))
		{
			EntityRegistry.registerModEntity(EntityAssaultNeko.class, "AssaultNeko", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityAssaultNeko.class, "TragicMC.AssaultNeko", id++, 0x789654, 0xD5C385);

			if (allowVanillaSpawns && TragicConfig.getBoolean("assaultNekoSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("assaultNekoSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Assault Neko.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityAssaultNeko.class, TragicConfig.getInt("assaultNekoSpawnChance"), TragicConfig.getIntArray("assaultNekoGroupSize")[0], TragicConfig.getIntArray("assaultNekoGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}
		
		if (TragicConfig.getBoolean("allowWorkerNeko"))
		{
			EntityRegistry.registerModEntity(EntityWorkerNeko.class, "WorkerNeko", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityWorkerNeko.class, "TragicMC.WorkerNeko", id++, 0x000000, 0x000000);

			if (allowVanillaSpawns && TragicConfig.getBoolean("workerNekoSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("workerNekoSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Worker Neko.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityWorkerNeko.class, TragicConfig.getInt("workerNekoSpawnChance"), TragicConfig.getIntArray("workerNekoGroupSize")[0], TragicConfig.getIntArray("workerNekoGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}
		
		if (TragicConfig.getBoolean("allowTraderNeko"))
		{
			EntityRegistry.registerModEntity(EntityTraderNeko.class, "TraderNeko", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityTraderNeko.class, "TragicMC.TraderNeko", id++, 0x3F1414, 0xA10000);

			if (allowVanillaSpawns && TragicConfig.getBoolean("traderNekoSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("traderNekoSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Trader Neko.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityTraderNeko.class, TragicConfig.getInt("traderNekoSpawnChance"), TragicConfig.getIntArray("traderNekoGroupSize")[0], TragicConfig.getIntArray("traderNekoGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}
		
		/*
		EntityRegistry.registerModEntity(EntityBlist.class, "Blist", listid++, TragicMC.getInstance(), 80, 1, true);
		TragicEntityList.addMapping(EntityBlist.class, "TragicMC.Blist", id++, 0x000000, 0x000000);

		EntityRegistry.registerModEntity(EntityThorg.class, "Thorg", listid++, TragicMC.getInstance(), 80, 1, true);
		TragicEntityList.addMapping(EntityThorg.class, "TragicMC.Thorg", id++, 0x000000, 0x000000);

		EntityRegistry.registerModEntity(EntityGirsh.class, "Girsh", listid++, TragicMC.getInstance(), 80, 1, true);
		TragicEntityList.addMapping(EntityGirsh.class, "TragicMC.Girsh", id++, 0x000000, 0x000000);

		EntityRegistry.registerModEntity(EntitySlang.class, "Slang", listid++, TragicMC.getInstance(), 80, 1, true);
		TragicEntityList.addMapping(EntitySlang.class, "TragicMC.Slang", id++, 0x000000, 0x000000); */

		//Giant Zombie
		TragicEntityList.addMapping(EntityGiantZombie.class, "TragicMC.GiantZombie", id++, 0x43BD98, 0x53DCBC);

		//Iron Golem
		TragicEntityList.addMapping(EntityIronGolem.class, "TragicMC.IronGolem", id++, 0xDBCDC1, 0x8B7260, EnumEggType.PET);

		//Added snow golem to ice biomes
		if (TragicConfig.getBoolean("allowSnowGolem"))
		{
			if (TragicConfig.getBoolean("snowGolemSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("snowGolemSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Snow Golem.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntitySnowman.class, TragicConfig.getInt("snowGolemSpawnChance"), TragicConfig.getIntArray("snowGolemGroupSize")[0], TragicConfig.getIntArray("snowGolemGroupSize")[1], EnumCreatureType.CREATURE, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
			else
			{
				EntityRegistry.addSpawn(EntitySnowman.class, TragicConfig.getInt("snowGolemSpawnChance"), TragicConfig.getIntArray("snowGolemGroupSize")[0], TragicConfig.getIntArray("snowGolemGroupSize")[1], EnumCreatureType.CREATURE, BiomeGenBase.icePlains, BiomeGenBase.iceMountains,
						BiomeGenBase.frozenOcean,
						BiomeGenBase.frozenRiver,
						BiomeGenBase.coldBeach,
						BiomeGenBase.coldTaiga,
						BiomeGenBase.coldTaigaHills
						);
			}
		}
		TragicEntityList.addMapping(EntitySnowman.class, "TragicMC.SnowGolem", id++, 0xFFFDF1, 0xABA290, EnumEggType.PET);

		//Kurayami
		if (TragicConfig.getBoolean("allowKurayami"))
		{
			EntityRegistry.registerModEntity(EntityKurayami.class, "Kurayami", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityKurayami.class, "TragicMC.Kurayami", id++, 0x2222AA, 0x8888FF, EnumEggType.PET);
		}

		if (TragicConfig.getBoolean("allowMechaExo"))
		{
			EntityRegistry.registerModEntity(EntityMechaExo.class, "MechaExo", listid++, TragicMC.getInstance(), 80, 3, true);
			TragicEntityList.addMapping(EntityMechaExo.class, "TragicMC.MechaExo", id++, 0x3F0000, 0x6A0000, EnumEggType.PET);
		}

		//Mini-Bosses
		if (TragicConfig.getBoolean("allowJarra"))
		{
			EntityRegistry.registerModEntity(EntityJarra.class, "Jarra", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("jarraSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("jarraSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Jarra.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityJarra.class, TragicConfig.getInt("jarraSpawnChance"), TragicConfig.getIntArray("jarraGroupSize")[0], TragicConfig.getIntArray("jarraGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityJarra.class, TragicConfig.getInt("jarraSpawnChance"), TragicConfig.getIntArray("jarraGroupSize")[0], TragicConfig.getIntArray("jarraGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.hell,
							BiomeGenBase.desert,
							BiomeGenBase.desertHills,
							BiomeGenBase.mesa,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F
							);
				}
			}
			TragicEntityList.addMapping(EntityJarra.class, "TragicMC.Jarra", id++, 0x77329B, 0xC457FD, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.getBoolean("allowKragul"))
		{
			EntityRegistry.registerModEntity(EntityKragul.class, "Kragul", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("kragulSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("kragulSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Kragul.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityKragul.class, TragicConfig.getInt("kragulSpawnChance"), TragicConfig.getIntArray("kragulGroupSize")[0], TragicConfig.getIntArray("kragulGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityKragul.class, TragicConfig.getInt("kragulSpawnChance"), TragicConfig.getIntArray("kragulGroupSize")[0], TragicConfig.getIntArray("kragulGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.desertHills,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F,
							BiomeGenBase.extremeHills,
							BiomeGenBase.extremeHillsPlus,
							BiomeGenBase.megaTaiga,
							BiomeGenBase.megaTaigaHills,
							BiomeGenBase.roofedForest,
							BiomeGenBase.swampland,
							BiomeGenBase.mushroomIsland,
							BiomeGenBase.mushroomIslandShore
							);
				}
			}
			TragicEntityList.addMapping(EntityKragul.class, "TragicMC.Kragul", id++, 0xDE3C31, 0x747474, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.getBoolean("allowMagmox"))
		{
			EntityRegistry.registerModEntity(EntityMagmox.class, "Magmox", listid++, TragicMC.getInstance(), 80, 1, true);

			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("magmoxSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("magmoxSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Magmox.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityMagmox.class, TragicConfig.getInt("magmoxSpawnChance"), TragicConfig.getIntArray("magmoxGroupSize")[0], TragicConfig.getIntArray("magmoxGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityMagmox.class, TragicConfig.getInt("magmoxSpawnChance"), TragicConfig.getIntArray("magmoxGroupSize")[0], TragicConfig.getIntArray("magmoxGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.hell);
				}
			}
			TragicEntityList.addMapping(EntityMagmox.class, "TragicMC.Magmox", id++, 0xC20000, 0x550000, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.getBoolean("allowMegaCryse"))
		{
			EntityRegistry.registerModEntity(EntityMegaCryse.class, "MegaCryse", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("megaCryseSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("megaCryseSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Mega Cryse.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityMegaCryse.class, TragicConfig.getInt("megaCryseSpawnChance"), TragicConfig.getIntArray("megaCryseGroupSize")[0], TragicConfig.getIntArray("megaCryseGroupSize")[1], EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityMegaCryse.class, TragicConfig.getInt("megaCryseSpawnChance"), TragicConfig.getIntArray("megaCryseGroupSize")[0], TragicConfig.getIntArray("megaCryseGroupSize")[1], EnumCreatureType.MONSTER, BiomeGenBase.icePlains,
							BiomeGenBase.iceMountains,
							BiomeGenBase.coldTaiga,
							BiomeGenBase.coldTaigaHills
							);
				}
			}
			TragicEntityList.addMapping(EntityMegaCryse.class, "TragicMC.MegaCryse", id++, 0xDADADA, 0xB9BFC7, EnumEggType.MINIBOSS);
		}

		if (TragicConfig.getBoolean("allowGreaterStin"))
		{
			EntityRegistry.registerModEntity(EntityGreaterStin.class, "GreaterStin", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityGreaterStin.class, "TragicMC.GreaterStin", id++, 0x454545, 0x383838, EnumEggType.MINIBOSS);

			if (allowVanillaSpawns && TragicConfig.getBoolean("greaterStinSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("greaterStinSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Greater Stin.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityGreaterStin.class, TragicConfig.getInt("greaterStinSpawnChance"), TragicConfig.getIntArray("greaterStinGroupSize")[0], TragicConfig.getIntArray("greaterStinGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowStinKing"))
		{
			EntityRegistry.registerModEntity(EntityStinKing.class, "StinKing", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityStinKing.class, "TragicMC.StinKing", id++, 0x754545, 0x483838, EnumEggType.MINIBOSS);

			if (allowVanillaSpawns && TragicConfig.getBoolean("stinKingSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("stinKingSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Stin King.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityStinKing.class, TragicConfig.getInt("stinKingSpawnChance"), TragicConfig.getIntArray("stinKingGroupSize")[0], TragicConfig.getIntArray("stinKingGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowStinQueen"))
		{
			EntityRegistry.registerModEntity(EntityStinQueen.class, "StinQueen", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityStinQueen.class, "TragicMC.StinQueen", id++, 0x232323, 0x767676, EnumEggType.MINIBOSS);

			if (allowVanillaSpawns && TragicConfig.getBoolean("stinQueenSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("stinQueenSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Stin Queen.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityStinQueen.class, TragicConfig.getInt("stinQueenSpawnChance"), TragicConfig.getIntArray("stinQueenGroupSize")[0], TragicConfig.getIntArray("stinQueenGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowVoxStellarum"))
		{
			EntityRegistry.registerModEntity(EntityVoxStellarum.class, "VoxStellarum", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityVoxStellarum.class, "TragicMC.VoxStellarum", id++, 0xFDC169, 0xFD3C69, EnumEggType.MINIBOSS);

			if (allowVanillaSpawns && TragicConfig.getBoolean("voxStellarumSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("voxStellarumSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Vox Stellarum.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityStin.class, TragicConfig.getInt("voxStellarumSpawnChance"), TragicConfig.getIntArray("voxStellarumGroupSize")[0], TragicConfig.getIntArray("voxStellarumGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowAegar"))
		{
			EntityRegistry.registerModEntity(EntityAegar.class, "Aegar", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityAegar.class, "TragicMC.Aegar", id++, 0x45C0CB, 0xCEFBFF, EnumEggType.MINIBOSS);

			if (allowVanillaSpawns && TragicConfig.getBoolean("aegarSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("aegarSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Aegar.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityAegar.class, TragicConfig.getInt("aegarSpawnChance"), TragicConfig.getIntArray("aegarGroupSize")[0], TragicConfig.getIntArray("aegarGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		if (TragicConfig.getBoolean("allowVolatileFusea"))
		{
			EntityRegistry.registerModEntity(EntityVolatileFusea.class, "VolatileFusea", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityVolatileFusea.class, "TragicMC.VolatileFusea", id++, 0xE7E69B, 0xB3ADE3, EnumEggType.MINIBOSS);

			if (allowVanillaSpawns && TragicConfig.getBoolean("volatileFuseaSpawnOverride"))
			{
				list.clear();

				for (BiomeGenBase b : TragicConfig.getBiomeArray("volatileFuseaSpawnBiomes"))
				{
					if (b != null)
					{
						TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Volatile Fusea.");
						list.add(b);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityVolatileFusea.class, TragicConfig.getInt("volatileFuseaSpawnChance"), TragicConfig.getIntArray("volatileFuseaGroupSize")[0], TragicConfig.getIntArray("volatileFuseaGroupSize")[1], EnumCreatureType.MONSTER, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}
		/*
		if (TragicConfig.allowAggro)
		{
			EntityRegistry.registerModEntity(EntityAggro.class, "Aggro", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityAggro.class, "TragicMC.Aggro", id++, 0x000000, 0x000000, EnumEggType.MINIBOSS);

			if (allowVanillaSpawns && TragicConfig.aggroSOV)
			{
				list.clear();

				for (int i : TragicConfig.aggroSpawns)
				{
					if (i >= biomes.length || i < 0) continue;
					if (biomes[i] != null)
					{
						TragicMC.logInfo(biomes[i].biomeName + " was added to list of possible spawns for Aggro.");
						list.add(biomes[i]);
					}
				}

				if (!list.isEmpty())
				{
					spawns = (BiomeGenBase[]) list.toArray(spawns);
					EntityRegistry.addSpawn(EntityAggro.class, TragicConfig.aggroSC, TragicConfig.aggroGS[0], TragicConfig.aggroGS[1], EnumCreatureType.monster, spawns);
					spawns = new BiomeGenBase[1];
				}
			}
		}

		EntityRegistry.registerModEntity(EntitySlangLeader.class, "SlangLeader", listid++, TragicMC.getInstance(), 80, 1, true);
		TragicEntityList.addMapping(EntitySlangLeader.class, "TragicMC.SlangLeader", id++, 0x000000, 0x000000, EnumEggType.MINIBOSS);

		EntityRegistry.registerModEntity(EntityWarden.class, "Warden", listid++, TragicMC.getInstance(), 80, 1, true);
		TragicEntityList.addMapping(EntityWarden.class, "TragicMC.Warden", id++, 0x000000, 0x000000, EnumEggType.MINIBOSS); */

		//Bosses
		allowVanillaSpawns = TragicConfig.getBoolean("allowBossOverworldSpawns");

		//Ender Dragon
		TragicEntityList.addMapping(EntityDragon.class, "TragicMC.EnderDragon", id++, 0x1A1A1A, 0xCC00FA, EnumEggType.BOSS);

		//Wither
		TragicEntityList.addMapping(EntityWither.class, "TragicMC.Wither", id++, 0x1C1C1C, 0x252525, EnumEggType.BOSS);

		if (TragicConfig.getBoolean("allowApis"))
		{
			EntityRegistry.registerModEntity(EntityApis.class, "Apis", listid++, TragicMC.getInstance(), 80, 1, true);

			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("apisSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("apisSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Apis.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityApis.class, TragicConfig.getInt("apisSpawnChance"), 0, 0, EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityApis.class, TragicConfig.getInt("apisSpawnChance"), 0, 0, EnumCreatureType.MONSTER, BiomeGenBase.plains,
							BiomeGenBase.savanna,
							BiomeGenBase.savannaPlateau,
							BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills,
							BiomeGenBase.mesa,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F,
							BiomeGenBase.extremeHills,
							BiomeGenBase.extremeHillsEdge,
							BiomeGenBase.extremeHillsPlus
							);
				}
			}
			TragicEntityList.addMapping(EntityApis.class, "TragicMC.Apis", id++, 0xFFFF82, 0xFFCD82, EnumEggType.BOSS);
		}

		if (TragicConfig.getBoolean("allowSkultar"))
		{
			EntityRegistry.registerModEntity(EntityDeathReaper.class, "DeathReaper", listid++, TragicMC.getInstance(), 80, 1, true);

			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("skultarSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("skultarSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Skultar.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityDeathReaper.class, TragicConfig.getInt("skultarSpawnChance"), 0, 0, EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityDeathReaper.class, TragicConfig.getInt("skultarSpawnChance"), 0, 0, EnumCreatureType.MONSTER, BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills
							);
				}
			}
			TragicEntityList.addMapping(EntityDeathReaper.class, "TragicMC.DeathReaper", id++, 0xCFCCB4, 0x553131, EnumEggType.BOSS);
		}

		if (TragicConfig.getBoolean("allowKitsunakuma"))
		{
			EntityRegistry.registerModEntity(EntityKitsune.class, "Kitsune", listid++, TragicMC.getInstance(), 80, 1, true);
			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("kitsunakumaSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("kitsunakumaSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Kitsunakuma.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityKitsune.class, TragicConfig.getInt("kitsunakumaSpawnChance"), 0, 0, EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityKitsune.class, TragicConfig.getInt("kitsunakumaSpawnChance"), 0, 0, EnumCreatureType.MONSTER, BiomeGenBase.hell);
				}
			}
			TragicEntityList.addMapping(EntityKitsune.class, "TragicMC.Kitsune", id++, 0xFF0000, 0xFFD087, EnumEggType.BOSS);
		}

		if (TragicConfig.getBoolean("allowPolaris"))
		{
			EntityRegistry.registerModEntity(EntityPolaris.class, "Polaris", listid++, TragicMC.getInstance(), 80, 1, true);

			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("polarisSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("polarisSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Polaris.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityPolaris.class, TragicConfig.getInt("polarisSpawnChance"), 0, 0, EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityPolaris.class, TragicConfig.getInt("polarisSpawnChance"), 0, 0, EnumCreatureType.MONSTER, BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills,
							BiomeGenBase.deepOcean,
							BiomeGenBase.extremeHills,
							BiomeGenBase.extremeHillsEdge,
							BiomeGenBase.extremeHillsPlus,
							BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.jungle,
							BiomeGenBase.jungleEdge,
							BiomeGenBase.jungleHills,
							BiomeGenBase.megaTaiga,
							BiomeGenBase.megaTaigaHills,
							BiomeGenBase.mesa,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F,
							BiomeGenBase.mushroomIsland,
							BiomeGenBase.mushroomIslandShore,
							BiomeGenBase.ocean,
							BiomeGenBase.plains,
							BiomeGenBase.river,
							BiomeGenBase.roofedForest,
							BiomeGenBase.savanna,
							BiomeGenBase.savannaPlateau,
							BiomeGenBase.stoneBeach,
							BiomeGenBase.swampland,
							BiomeGenBase.taiga,
							BiomeGenBase.taigaHills
							);
				}
			}
			TragicEntityList.addMapping(EntityPolaris.class, "TragicMC.Polaris", id++, 0x4A00BA, 0x000000, EnumEggType.BOSS);
		}

		if (TragicConfig.getBoolean("allowEmpariah"))
		{
			EntityRegistry.registerModEntity(EntityYeti.class, "Yeti", listid++, TragicMC.getInstance(), 80, 1, true);

			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("empariahSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("empariahSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Empariah.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityYeti.class, TragicConfig.getInt("empariahSpawnChance"), 0, 0, EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityYeti.class, TragicConfig.getInt("empariahSpawnChance"), 0, 0, EnumCreatureType.MONSTER, BiomeGenBase.icePlains,
							BiomeGenBase.iceMountains,
							BiomeGenBase.frozenOcean,
							BiomeGenBase.frozenRiver,
							BiomeGenBase.coldBeach,
							BiomeGenBase.coldTaiga,
							BiomeGenBase.coldTaigaHills
							);
				}
			}
			TragicEntityList.addMapping(EntityYeti.class, "TragicMC.Yeti", id++, 0xDADADA, 0xB9BFC7, EnumEggType.BOSS);
		}

		if (TragicConfig.getBoolean("allowTimeController"))
		{
			EntityRegistry.registerModEntity(EntityTimeController.class, "TimeController", listid++, TragicMC.getInstance(), 80, 1, true);

			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("timeControllerSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("timeControllerSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Time Controller.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityTimeController.class, TragicConfig.getInt("timeControllerSpawnChance"), 0, 0, EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityTimeController.class, TragicConfig.getInt("timeControllerSpawnChance"), 0, 0, EnumCreatureType.MONSTER, BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills,
							BiomeGenBase.deepOcean,
							BiomeGenBase.extremeHills,
							BiomeGenBase.extremeHillsEdge,
							BiomeGenBase.extremeHillsPlus,
							BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.jungle,
							BiomeGenBase.jungleEdge,
							BiomeGenBase.jungleHills,
							BiomeGenBase.megaTaiga,
							BiomeGenBase.megaTaigaHills,
							BiomeGenBase.mesa,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F,
							BiomeGenBase.mushroomIsland,
							BiomeGenBase.mushroomIslandShore,
							BiomeGenBase.ocean,
							BiomeGenBase.plains,
							BiomeGenBase.river,
							BiomeGenBase.roofedForest,
							BiomeGenBase.savanna,
							BiomeGenBase.savannaPlateau,
							BiomeGenBase.stoneBeach,
							BiomeGenBase.swampland,
							BiomeGenBase.taiga,
							BiomeGenBase.taigaHills
							);
				}
			}
			TragicEntityList.addMapping(EntityTimeController.class, "TragicMC.TimeController", id++, 0x94FFA3, 0xEA92E9, EnumEggType.BOSS);
		}

		if (TragicConfig.getBoolean("allowEnyvil"))
		{
			EntityRegistry.registerModEntity(EntityEnyvil.class, "Enyvil", listid++, TragicMC.getInstance(), 80, 1, true);

			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("enyvilSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("enyvilSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Enyvil.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityEnyvil.class, TragicConfig.getInt("enyvilSpawnChance"), 0, 0, EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityEnyvil.class, TragicConfig.getInt("enyvilSpawnChance"), 0, 0, EnumCreatureType.MONSTER, BiomeGenBase.birchForest,
							BiomeGenBase.birchForestHills,
							BiomeGenBase.deepOcean,
							BiomeGenBase.extremeHills,
							BiomeGenBase.extremeHillsEdge,
							BiomeGenBase.extremeHillsPlus,
							BiomeGenBase.forest,
							BiomeGenBase.forestHills,
							BiomeGenBase.jungle,
							BiomeGenBase.jungleEdge,
							BiomeGenBase.jungleHills,
							BiomeGenBase.megaTaiga,
							BiomeGenBase.megaTaigaHills,
							BiomeGenBase.mesa,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F,
							BiomeGenBase.mushroomIsland,
							BiomeGenBase.mushroomIslandShore,
							BiomeGenBase.ocean,
							BiomeGenBase.plains,
							BiomeGenBase.river,
							BiomeGenBase.roofedForest,
							BiomeGenBase.savanna,
							BiomeGenBase.savannaPlateau,
							BiomeGenBase.stoneBeach,
							BiomeGenBase.swampland,
							BiomeGenBase.taiga,
							BiomeGenBase.taigaHills
							);
				}
			}
			TragicEntityList.addMapping(EntityEnyvil.class, "TragicMC.Enyvil", id++, 0x5D1543, 0xFF6FFF, EnumEggType.BOSS);
		}

		if (TragicConfig.getBoolean("allowClaymation"))
		{
			EntityRegistry.registerModEntity(EntityClaymation.class, "Claymation", listid++, TragicMC.getInstance(), 80, 1, true);

			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("claymationSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("claymationSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Claymation.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityClaymation.class, TragicConfig.getInt("claymationSpawnChance"), 0, 0, EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
				else
				{
					EntityRegistry.addSpawn(EntityClaymation.class, TragicConfig.getInt("claymationSpawnChance"), 0, 0, EnumCreatureType.MONSTER, BiomeGenBase.desert,
							BiomeGenBase.desertHills,
							BiomeGenBase.mesa,
							BiomeGenBase.mesaPlateau,
							BiomeGenBase.mesaPlateau_F
							);
				}
			}
			TragicEntityList.addMapping(EntityClaymation.class, "TragicMC.Claymation", id++, 0xFF8100, 0xFFB800, EnumEggType.BOSS);
		}
		
		if (TragicConfig.getBoolean("allowProfessorNekoid"))
		{
			EntityRegistry.registerModEntity(EntityProfessorNekoid.class, "ProfessorNekoid", listid++, TragicMC.getInstance(), 80, 1, true);

			if (allowVanillaSpawns)
			{
				if (TragicConfig.getBoolean("professorNekoidSpawnOverride"))
				{
					list.clear();

					for (BiomeGenBase b : TragicConfig.getBiomeArray("professorNekoidSpawnBiomes"))
					{
						if (b != null)
						{
							TragicMC.logInfo(b.biomeName + " was added to list of possible spawns for Professor Nekoid.");
							list.add(b);
						}
					}

					if (!list.isEmpty())
					{
						spawns = (BiomeGenBase[]) list.toArray(spawns);
						EntityRegistry.addSpawn(EntityProfessorNekoid.class, TragicConfig.getInt("professorNekoidSpawnChance"), 0, 0, EnumCreatureType.MONSTER, spawns);
						spawns = new BiomeGenBase[1];
					}
				}
			}
			TragicEntityList.addMapping(EntityProfessorNekoid.class, "TragicMC.ProfessorNekoid", id++, 0xFF8100, 0xFFB800, EnumEggType.BOSS);
		}

		//Alphas

		if (TragicConfig.getBoolean("allowOverlord"))
		{
			EntityRegistry.registerModEntity(EntityOverlordCocoon.class, "OverlordCocoon", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityOverlordCocoon.class, "TragicMC.OverlordCocoon", id++, 0x335548, 0x787878, EnumEggType.ALPHA);

			EntityRegistry.registerModEntity(EntityOverlordCombat.class, "OverlordCombat", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityOverlordCombat.class, "TragicMC.OverlordCombat", id++, 0x64A28A, 0x555555, EnumEggType.ALPHA);

			EntityRegistry.registerModEntity(EntityOverlordCore.class, "OverlordCore", listid++, TragicMC.getInstance(), 80, 1, true);
			TragicEntityList.addMapping(EntityOverlordCore.class, "TragicMC.OverlordCore", id++, 0x92F9D1, 0x212121, EnumEggType.ALPHA);
		}

		//Other entities

		EntityRegistry.registerModEntity(EntityThrowingRock.class, "ThrowingRock", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityPumpkinbomb.class, "Pumpkinbomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityLargePumpkinbomb.class, "LargePumpkinbomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityPoisonBarb.class, "PoisonBarb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityNekoRocket.class, "NekoRocket", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityNekoStickyBomb.class, "NekoStickyBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityNekoClusterBomb.class, "NekoClusterBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityNekoMiniBomb.class, "NekoMiniBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntitySolarBomb.class, "SolarBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntitySpiritCast.class, "SpiritCast", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntitySpore.class, "Spore", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityBanana.class, "Banana", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityTimeBomb.class, "TimeBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityTimeDisruption.class, "TimeDisruption", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityLargeRock.class, "LargeRock", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityIcicle.class, "Icicle", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityStatue.class, "Statue", listid++, TragicMC.getInstance(), 80, 3, false);
		EntityRegistry.registerModEntity(EntityStarShard.class, "StarShard", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDarkLightning.class, "DarkLightning", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityPitchBlack.class, "PitchBlack", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDarkEnergy.class, "DarkEnergy", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDarkCrystal.class, "DarkCrystal", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDarkMortor.class, "DarkMortor", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityWebBomb.class, "WebBomb", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityCrystalMortor.class, "CrystalMortor", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityGuardianShield.class, "GuardianShield", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityOverlordMortor.class, "OverlordMortor", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDimensionalAnomaly.class, "DimensionalAnomaly", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityLock.class, "Lock", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityDirectedLightning.class, "DirectedLightning", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityIreEnergy.class, "IreEnergy", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityNuke.class, "Nuke", listid++, TragicMC.getInstance(), 80, 3, true);
		EntityRegistry.registerModEntity(EntityEnergyCharge.class, "EnergyCharge", listid++, TragicMC.getInstance(), 80, 3, true);
	}
}
