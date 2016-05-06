package tragicneko.tragicmc.util;

import static tragicneko.tragicmc.TragicItems.*;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;

public class ChestHooks {

	public static ChestGenHooks rareHook, uncommonHook, commonHook, epicHook, foodHook;

	public static void load() {
		
		WeightedRandomChestContent[] uncommonChestContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(RubyCharm), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(SapphireCharm), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(EmeraldCharm), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(DiamondCharm), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(CooldownDefuse), 1, 3, TragicConfig.getInt("cooldownDefuseRarity")),
				new WeightedRandomChestContent(new ItemStack(Items.diamond, 1), 1, 3, 20),
				new WeightedRandomChestContent(new ItemStack(Blocks.diamond_block, 1), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(Items.gold_ingot, 1), 1, 3, 40),
				new WeightedRandomChestContent(new ItemStack(Blocks.gold_block, 1), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(Ruby, 1), 1, 3, 25),
				new WeightedRandomChestContent(new ItemStack(Sapphire, 1), 1, 3, 25),
				new WeightedRandomChestContent(new ItemStack(Items.emerald, 1), 1, 3, 30),
				new WeightedRandomChestContent(new ItemStack(Blocks.emerald_block, 1), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(Tungsten, 1), 1, 2, 50),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 1), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 2), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 3), 1, 2, 35),
				new WeightedRandomChestContent(new ItemStack(KitsuneAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(PiercingAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(ObsidianOrb, 1), 1, 3, 3),
				new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 1, 2, 1),
				new WeightedRandomChestContent(new ItemStack(HuntersBow, 1), 1, 2, 15),
				new WeightedRandomChestContent(new ItemStack(DarkHelm, 1), 1, 2, 7),
				new WeightedRandomChestContent(new ItemStack(DarkPlate, 1), 1, 2, 7),
				new WeightedRandomChestContent(new ItemStack(DarkLegs, 1), 1, 2, 7),
				new WeightedRandomChestContent(new ItemStack(DarkBoots, 1), 1, 2, 7),
				new WeightedRandomChestContent(new ItemStack(LightHelm, 1), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(LightPlate, 1), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(LightLegs, 1), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(LightBoots, 1), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_helmet, 1), 1, 1, 100),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_chestplate, 1), 1, 1, 100),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_pickaxe, 1), 1, 1, 100),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_leggings, 1), 1, 1, 100),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_boots, 1), 1, 1, 100),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_sword, 1), 1, 1, 100),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_axe, 1), 1, 1, 100),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_shovel, 1), 1, 1, 100),
				new WeightedRandomChestContent(new ItemStack(Items.diamond_hoe, 1), 1, 1, 100),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1), 1, 2, 15),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(Honeydrop, 1), 1, 4, 25),
				new WeightedRandomChestContent(new ItemStack(Gloopii, 1), 1, 3, 5),
				new WeightedRandomChestContent(new ItemStack(Items.cooked_beef, 3), 2, 5, 65),
				new WeightedRandomChestContent(new ItemStack(Items.cooked_porkchop, 3), 2, 5, 65),
				new WeightedRandomChestContent(new ItemStack(Items.cooked_mutton), 3, 6, 65),
				new WeightedRandomChestContent(new ItemStack(Items.cooked_rabbit), 5, 10, 65),
				new WeightedRandomChestContent(new ItemStack(Items.saddle, 1), 1, 3, 60),
				new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(ApisAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(MartyrAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(BlacksmithAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(CreeperAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(ZombieAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(SkeletonAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 1, 2, TragicConfig.getInt("amuletReleaseRarity")),
				new WeightedRandomChestContent(new ItemStack(Items.lead), 1, 2, 50),
				new WeightedRandomChestContent(new ItemStack(Items.saddle), 1, 3, 50),
				new WeightedRandomChestContent(new ItemStack(WovenSilk), 1, 2, 30),
				new WeightedRandomChestContent(new ItemStack(IceAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(SnowGolemAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(IronGolemAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(EndermanAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(GuiltyThorn, 1), 1, 1, 15),
				new WeightedRandomChestContent(new ItemStack(GravitySpike, 1), 1, 1, 15),
				new WeightedRandomChestContent(new ItemStack(FrozenLightning, 1), 1, 1, 15),
				new WeightedRandomChestContent(new ItemStack(Talisman), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(BloodSacrifice), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(NourishmentSacrifice), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(SpiderAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(StinAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(PolarisAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(LightningAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(ConsumptionAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(SupernaturalAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(UndeadAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(EnderDragonAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(FuseaAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(LuckAmulet, 1), 1, 1, TragicConfig.getInt("amuletOverallRarity"))
		};

		WeightedRandomChestContent[] scrollArray = new WeightedRandomChestContent[Doomsday.getRegistrySize()];

		for (int i = 0; i < Doomsday.getRegistrySize(); i++)
		{
			if (Doomsday.getDoomsdayFromId(i) != null) scrollArray[i] = new WeightedRandomChestContent(new ItemStack(DoomsdayScroll, 1, i), 1, 1, 3);
		}

		ArrayUtils.addAll(uncommonChestContent, scrollArray);

		WeightedRandomChestContent[] rareChestContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(RubyCharm), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(SapphireCharm), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(EmeraldCharm), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(DiamondCharm), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(CooldownDefuse), 1, 3, TragicConfig.getInt("cooldownDefuseRarity")),
				new WeightedRandomChestContent(new ItemStack(Items.diamond, 1), 1, 3, 25),
				new WeightedRandomChestContent(new ItemStack(Blocks.diamond_block, 1), 1, 3, 10),
				new WeightedRandomChestContent(new ItemStack(Ruby, 1), 1, 4, 18),
				new WeightedRandomChestContent(new ItemStack(Sapphire, 1), 1, 4, 18),
				new WeightedRandomChestContent(new ItemStack(Items.emerald, 1), 1, 3, 20),
				new WeightedRandomChestContent(new ItemStack(Blocks.emerald_block, 1), 1, 2, 8),
				new WeightedRandomChestContent(new ItemStack(Tungsten, 1), 1, 3, 70),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 1), 1, 2, 30),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 2), 1, 2, 30),
				new WeightedRandomChestContent(new ItemStack(TragicBlocks.CompactOre, 1, 3), 1, 2, 50),
				new WeightedRandomChestContent(new ItemStack(KitsuneAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(PiercingAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(ObsidianOrb, 1), 1, 3, 10),
				new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 1, 2, 5),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1), 1, 2, 35),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 1, 2, 15),
				new WeightedRandomChestContent(new ItemStack(SunkenAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(ApisAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(MartyrAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(GoldenSushi, 1), 1, 3, 5),
				new WeightedRandomChestContent(new ItemStack(BlacksmithAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(CreeperAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(ZombieAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(SkeletonAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 1, 2, TragicConfig.getInt("amuletReleaseRarity")),
				new WeightedRandomChestContent(new ItemStack(Items.lead), 1, 2, 15),
				new WeightedRandomChestContent(new ItemStack(Items.saddle), 1, 3, 15),
				new WeightedRandomChestContent(new ItemStack(IceAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(SnowGolemAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(IronGolemAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(EndermanAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(Talisman), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(BloodSacrifice), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(NourishmentSacrifice), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(SpiderAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(StinAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(PolarisAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(LightningAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(ConsumptionAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(SupernaturalAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(UndeadAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(EnderDragonAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(FuseaAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity")),
				new WeightedRandomChestContent(new ItemStack(LuckAmulet, 1), 1, 2, TragicConfig.getInt("amuletOverallRarity"))
		};

		scrollArray = new WeightedRandomChestContent[Doomsday.getRegistrySize()];

		for (int i = 0; i < Doomsday.getRegistrySize(); i++)
		{
			if (Doomsday.getDoomsdayFromId(i) != null) scrollArray[i] = new WeightedRandomChestContent(new ItemStack(DoomsdayScroll, 1, i), 1, 1, 3);
		}

		ArrayUtils.addAll(uncommonChestContent, scrollArray);

		WeightedRandomChestContent[] commonChestContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(Blocks.cobblestone), 3, 5, 50),
				new WeightedRandomChestContent(new ItemStack(Blocks.gravel), 2, 4, 50),
				new WeightedRandomChestContent(new ItemStack(Items.apple), 1, 4, 25),
				new WeightedRandomChestContent(new ItemStack(Ash), 1, 4, 25),
				new WeightedRandomChestContent(new ItemStack(Items.wheat_seeds), 2, 4, 50),
				new WeightedRandomChestContent(new ItemStack(Items.coal), 1, 4, 25),
				new WeightedRandomChestContent(new ItemStack(Items.rotten_flesh), 2, 5, 100),
				new WeightedRandomChestContent(new ItemStack(Items.string), 2, 4, 75),
				new WeightedRandomChestContent(new ItemStack(Items.bone), 1, 3, 25),
				new WeightedRandomChestContent(new ItemStack(Items.slime_ball), 1, 3, 15),
				new WeightedRandomChestContent(new ItemStack(Items.clay_ball), 1, 3, 15),
				new WeightedRandomChestContent(new ItemStack(Projectile, 1, 11), 1, 3, 10)
		};

		WeightedRandomChestContent[] epicChestContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(DoomConsume), 1, 1, TragicConfig.getInt("doomConsumeRarity")),
				new WeightedRandomChestContent(new ItemStack(CooldownDefuse), 1, 3, TragicConfig.getInt("cooldownDefuseRarity")),
				new WeightedRandomChestContent(new ItemStack(AmuletRelease, 1), 1, 2, TragicConfig.getInt("amuletReleaseRarity")),
				new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 1, 3, 35),
				new WeightedRandomChestContent(new ItemStack(GoldenSushi, 1), 1, 4, 45),
				new WeightedRandomChestContent(new ItemStack(Items.diamond), 1, 4, 50),
				new WeightedRandomChestContent(new ItemStack(Items.emerald), 1, 4, 50),
				new WeightedRandomChestContent(new ItemStack(Ruby), 1, 4, 50),
				new WeightedRandomChestContent(new ItemStack(Sapphire), 1, 4, 50),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 1, 2, 35),
				new WeightedRandomChestContent(new ItemStack(AwakeningStone, 1), 1, 2, 45),
				new WeightedRandomChestContent(new ItemStack(Items.saddle), 1, 3, 25),
				new WeightedRandomChestContent(new ItemStack(RubyCharm), 1, 2, 15),
				new WeightedRandomChestContent(new ItemStack(SapphireCharm), 1, 2, 15),
				new WeightedRandomChestContent(new ItemStack(EmeraldCharm), 1, 2, 15),
				new WeightedRandomChestContent(new ItemStack(DiamondCharm), 1, 2, 15),
				new WeightedRandomChestContent(new ItemStack(Titan), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(Splinter), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(Thardus), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(Paranoia), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(Butcher), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(DragonFang), 1, 2, 10),
				new WeightedRandomChestContent(new ItemStack(Talisman), 1, 2, 20),
				new WeightedRandomChestContent(new ItemStack(BloodSacrifice), 1, 2, 20),
				new WeightedRandomChestContent(new ItemStack(NourishmentSacrifice), 1, 2, 20)
		};

		scrollArray = new WeightedRandomChestContent[Doomsday.getRegistrySize()];

		for (int i = 0; i < Doomsday.getRegistrySize(); i++)
		{
			if (Doomsday.getDoomsdayFromId(i) != null) scrollArray[i] = new WeightedRandomChestContent(new ItemStack(DoomsdayScroll, 1, i), 1, 1, 3);
		}

		ArrayUtils.addAll(epicChestContent, scrollArray);
		
		WeightedRandomChestContent[] foodChestContent = new WeightedRandomChestContent[] {
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1), 1, 2, 3),
				new WeightedRandomChestContent(new ItemStack(Items.golden_apple, 1, 1), 1, 2, 1),
				new WeightedRandomChestContent(new ItemStack(Honeydrop, 1), 1, 4, 5),
				new WeightedRandomChestContent(new ItemStack(Gloopii, 1), 1, 3, 8),
				new WeightedRandomChestContent(new ItemStack(Items.cooked_beef, 3), 2, 5, 15),
				new WeightedRandomChestContent(new ItemStack(Items.cooked_porkchop, 3), 2, 5, 15),
				new WeightedRandomChestContent(new ItemStack(Items.cooked_chicken, 3), 2, 5, 15),
				new WeightedRandomChestContent(new ItemStack(Items.cooked_mutton), 3, 6, 15),
				new WeightedRandomChestContent(new ItemStack(Items.cooked_rabbit), 5, 10, 15),
				new WeightedRandomChestContent(new ItemStack(Tentacle, 1), 1, 4, 30),
				new WeightedRandomChestContent(new ItemStack(Items.mutton), 1, 4, 65),
				new WeightedRandomChestContent(new ItemStack(Items.beef), 1, 4, 65),
				new WeightedRandomChestContent(new ItemStack(Items.porkchop), 1, 4, 65),
				new WeightedRandomChestContent(new ItemStack(Items.chicken), 1, 4, 65),
				new WeightedRandomChestContent(new ItemStack(Items.rabbit, 3), 2, 5, 65),
				new WeightedRandomChestContent(new ItemStack(Items.apple, 3), 4, 9, 45),
				new WeightedRandomChestContent(new ItemStack(Items.carrot, 3), 2, 5, 45),
				new WeightedRandomChestContent(new ItemStack(Items.potato, 3), 2, 5, 45),
				new WeightedRandomChestContent(new ItemStack(Items.sugar, 3), 2, 5, 5),
				new WeightedRandomChestContent(new ItemStack(Items.mushroom_stew, 3), 2, 5, 3),
				new WeightedRandomChestContent(new ItemStack(Items.rabbit_stew, 3), 2, 5, 5),
				new WeightedRandomChestContent(new ItemStack(Items.bowl, 3), 1, 3, 25)
		};

		rareHook = (new ChestGenHooks("TragicMC.RareHook", Arrays.asList(rareChestContent), 1, 7));
		uncommonHook = (new ChestGenHooks("TragicMC.UncommonHook", Arrays.asList(uncommonChestContent), 2, 6));
		commonHook = (new ChestGenHooks("TragicMC.CommonHook", Arrays.asList(commonChestContent), 3, 6));
		epicHook = (new ChestGenHooks("TragicMC.EpicHook", Arrays.asList(epicChestContent), 2, 6));
		foodHook = (new ChestGenHooks("TragicMC.FoodHook", Arrays.asList(foodChestContent), 4, 9));

		if (TragicConfig.getBoolean("allowCooldownDefuse"))
		{
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.getInt("cooldownDefuseRarity")));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.getInt("cooldownDefuseRarity")));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.getInt("cooldownDefuseRarity")));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.getInt("cooldownDefuseRarity")));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.getInt("cooldownDefuseRarity")));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.getInt("cooldownDefuseRarity")));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(CooldownDefuse, 1), 1, 3, TragicConfig.getInt("cooldownDefuseRarity")));
		}
	}

	public static void loadChallengeScroll() {
		rareHook.addItem(new WeightedRandomChestContent(new ItemStack(ChallengeScroll), 1, 1, 5));
		uncommonHook.addItem(new WeightedRandomChestContent(new ItemStack(ChallengeScroll), 1, 1, 15));
		epicHook.addItem(new WeightedRandomChestContent(new ItemStack(ChallengeScroll), 1, 1, 25));
	}
}
