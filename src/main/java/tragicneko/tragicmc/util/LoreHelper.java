package tragicneko.tragicmc.util;

import static tragicneko.tragicmc.TragicMC.rand;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.TragicEnchantments;
import tragicneko.tragicmc.TragicMC;

public class LoreHelper {

	private static HashMap<ResourceLocation, LoreEntry> loreMap = new HashMap<ResourceLocation, LoreEntry>();
	private static Logger logger = LogManager.getLogger(TragicMC.MODID + "/LoreHelper");

	public static void addToLoreMap(String itemName, Lore[] lores, EnchantEntry[] enchants)
	{
		addToLoreMap(itemName, new LoreEntry(lores, enchants));
	}

	public static void addToLoreMap(String itemName, LoreEntry entry)
	{
		addToLoreMap(new ResourceLocation(itemName), entry);
	}

	public static void addToLoreMap(ResourceLocation rl, LoreEntry entry)
	{
		if (!Item.itemRegistry.containsKey(rl)) throw new IllegalArgumentException("Cannot register Lore for an invalid item! Attempted name was (" + rl.toString() + ")");
		if (loreMap.containsKey(rl)) TragicMC.logWarning("Duplicate lore entry for the item (" + rl.toString() + "), entry will be replaced.");
		loreMap.put(rl, entry);
	}

	public static LoreEntry getLoreEntry(String itemName)
	{
		ResourceLocation rl = new ResourceLocation(itemName);
		return getLoreEntry(rl);
	}

	public static LoreEntry getLoreEntry(ResourceLocation rl)
	{
		return loreMap.get(rl);
	}

	public static EnumChatFormatting getFormatForRarity(int rarity)
	{
		return rarity == 0 ? EnumChatFormatting.GRAY : (rarity == 1 ? EnumChatFormatting.GOLD : (rarity == 2 ? EnumChatFormatting.DARK_GREEN : EnumChatFormatting.DARK_RED));
	}

	public static int getRarityFromStack(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("tragicLoreRarity") ? stack.getTagCompound().getByte("tragicLoreRarity") : 0;
	}

	public static String getDescFromStack(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("tragicLoreDesc") ? stack.getTagCompound().getString("tragicLoreDesc") : null;
	}

	/**
	 * Can split any lengthy string (most likely a description) into smaller ones to fit within an item's description, it will split as often as it needs to
	 * @param lore
	 * @return
	 */
	public static List<String> splitDesc(List<String> list, final String lore, final int lineBreak, final EnumChatFormatting format)
	{
		String s = lore;

		if (lore.length() <= lineBreak)
		{
			list.add(format + lore);
		}
		else
		{

			for (int i = lineBreak; i < s.length(); i++)
			{
				if (s.substring(0, i).endsWith(" "))
				{
					list.add(format + s.substring(0, i).trim());
					s = s.substring(i).trim();
					i = lineBreak;
				}
			}

			if (s.length() > 0) list.add(format + s);
		}

		return list;
	}

	public static void registerLoreJson(File config)
	{
		File fileIn = new File(config, "TragicMC2/lores.json");

		if (fileIn.exists())
		{
			try
			{
				JsonParser parser = new JsonParser();
				JsonElement lores = null;
				lores = parser.parse(new FileReader(fileIn));

				for (JsonElement el : lores.getAsJsonArray())
				{
					JsonObject obj = el.getAsJsonObject();
					String itemName = obj.get("itemName").getAsString();

					int weight = 0;
					String desc = "";
					int rarity = 0;

					String enchantName = "";
					int enchantLevel = 0;

					try
					{
						LoreEntry entry = getLoreEntry(itemName);

						if (entry == null)
						{
							if (!Item.itemRegistry.containsKey(new ResourceLocation(itemName)))
							{
								logger.error("Item registry didn't contain the name (" + itemName + ") so the entry is being skipped.");
								continue;
							}
							addToLoreMap(itemName, new LoreEntry());
							entry = getLoreEntry(itemName);
						}

						if (obj.has("loreParent"))
						{
							String s = obj.get("loreParent").getAsString();
							LoreEntry entry2 = getLoreEntry(s);
							logger.warn("Item with name of (" + itemName + ") is attempting to inherit lores from object with name of (" + s + ")");

							if (entry2 != null)
							{
								entry.copyLoresFrom(entry2);
								logger.info("Lore inheritance successful.");
							}
							else
							{
								logger.error("Lore inheritance failed, ensure that the parent is before the registration for the child LoreEntry.");
							}
						}
						else
						{
							if (!obj.has("lores") || !obj.get("lores").isJsonArray())
							{
								logger.error("There is no lores element for item with name of (" + itemName + "), skipping...");
							}
							else
							{
								JsonArray array = obj.get("lores").getAsJsonArray();

								for (JsonElement el2 : array)
								{
									JsonObject obj2 = el2.getAsJsonObject();
									try
									{
										desc = obj2.get("desc").getAsString();
										rarity = obj2.get("rarity").getAsInt();
										entry.addLore(new Lore(desc, rarity));
									}
									catch (ClassCastException e)
									{
										logger.error("Error found while parsing item with name of (" + itemName + "), problem with an element, skipping lore...");
										continue;
									}
								}
							}
						}

						if (!obj.has("enchantEntries") || !obj.get("enchantEntries").isJsonArray())
						{
							logger.error("There is no enchantEntries array element for item with name of (" + itemName + "), skipping...");
						}
						else
						{
							JsonArray array = obj.get("enchantEntries").getAsJsonArray();

							for (JsonElement el2 : array)
							{
								JsonObject obj2 = el2.getAsJsonObject();
								try
								{
									enchantName = obj2.get("enchant").getAsString();
									enchantLevel = obj2.get("level").getAsInt();
									rarity = obj2.get("rarity").getAsInt();
									Enchantment ench = Enchantment.getEnchantmentByLocation(enchantName);
									entry.addEnchantEntry(new EnchantEntry(ench, enchantLevel, rarity));
								}
								catch (ClassCastException e)
								{
									logger.error("Error found while parsing item with name of (" + itemName + "), problem with an element, skipping lore...");
									continue;
								}
							}
						}
					}
					catch (Exception e)
					{
						logger.error("Error caught while trying to parse an element with name of (" + itemName + "), skipping element and continuing parsing", e);
						continue;
					}
				}

			}
			catch (Exception e)
			{
				logger.warn("Exception caught while loading elements of the lores json, stopping lore parsing... may cause instability!");
				return;
			}
		}
		else
		{
			logger.info("lores.json file was not found in the config/TragicMC2 directory, skipping lore parsing and loading default lores.");
			loadDefaultLores();
		}
	}

	public static void loadDefaultLores()
	{
		//Armor
		addToLoreMap("tragicmc:darkHelm", new LoreEntry(new Lore[] {new Lore("It's dark.", 1), new Lore("Dim.", 1),
				new Lore("Rather dark out!", 1), new Lore("Hold me.", 1), new Lore("I'm so alone.", 1),
				new Lore("Cold, dark and alone...", 1), new Lore("Darkness all around me.", 1),
				new Lore("It's quite dark out tonight, isn't it?", 1), new Lore("Lonely...", 1),
				new Lore("How can you see in this darkness?", 1), new Lore("Scream!", 2),
				new Lore("Screaming Bloody Mary!", 2), new Lore("Welcome to my nightmare!", 2), new Lore("Just another slasher...", 2),
				new Lore("I'll rip you a new one!", 2), new Lore("Oh yes, there will be blood.", 2),
				new Lore("Let's play a game.", 2), new Lore("Fright night!", 2), new Lore("I see dead people.", 2),
				new Lore("The Boogeyman is real and you found him!", 3), new Lore("You will die in 7 days.", 3),
				new Lore("If you don't forward this to 10 people by midnight, a psychopath will come to your house and kill you.", 3),
				new Lore("Come play with us...", 3), new Lore("We all go a little mad sometimes...", 3),
				new Lore("Victims... aren't we all?", 3), new Lore("Join us... one of us... one of us!", 3),
				new Lore("One, two, Freddy's coming for you!", 3), new Lore("Three, four, better lock your door!", 3),
				new Lore("Want to play a game?", 3), new Lore("I know what you did last summer.", 3),
				new Lore("Jeepers creepers!", 3), new Lore("It rubs the lotion on it's skin.", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2), new EnchantEntry(TragicEnchantments.DeathTouch, 3, 2),
						new EnchantEntry(Enchantment.unbreaking, 7, 3), new EnchantEntry(TragicEnchantments.DeathTouch, 5, 3), new EnchantEntry(Enchantment.respiration, 1, 3)}));

		addToLoreMap("tragicmc:darkPlate", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2),  new EnchantEntry(TragicEnchantments.DeathTouch, 3, 2),
				new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.DeathTouch, 5, 3), new EnchantEntry(TragicEnchantments.Toxicity, 3, 3),
				new EnchantEntry(Enchantment.thorns, 1, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:darkHelm")));

		addToLoreMap("tragicmc:darkLegs", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2),  new EnchantEntry(TragicEnchantments.DeathTouch, 3, 2),
				new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.DeathTouch, 5, 3), new EnchantEntry(TragicEnchantments.Toxicity, 3, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:darkHelm")));

		addToLoreMap("tragicmc:darkBoots", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2), new EnchantEntry(TragicEnchantments.DeathTouch, 3, 2),
				new EnchantEntry(Enchantment.unbreaking, 7, 3), new EnchantEntry(TragicEnchantments.DeathTouch, 5, 3), new EnchantEntry(Enchantment.featherFalling, 1, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:darkHelm")));

		addToLoreMap("tragicmc:huntersCap", new LoreEntry(new Lore[] {new Lore("Move swiftly.", 1), new Lore("Make haste.", 1),
				new Lore("Feel the wind on your face!", 1), new Lore("Fast as the wind!", 1),
				new Lore("Too fast, too furious.", 2), new Lore("Windswept.", 1), new Lore("Feverishly fast.", 1),
				new Lore("Unrelenting speed.", 1), new Lore("Used Gust! It's not very effective.", 1),
				new Lore("Watch out for windburn!", 1), new Lore("Used Fly! It's super effective!", 2), new Lore("Like a tsunami!", 2),
				new Lore("I'm like a bird.", 2), new Lore("I can go the distance!", 2),
				new Lore("In the eye of the hurricane.", 2), new Lore("Feel the full force of the unburdened wind!", 2),
				new Lore("Watch as the cold wind slices through you!", 2), new Lore("Wind chill of -40 tonight!", 2),
				new Lore("Used Sky Attack! Critical Hit! It's super effective!", 3), new Lore("Like the howling wind.", 1),
				new Lore("Used Whirlwind. The enemy fled.", 1), new Lore("All the force of a great typhoon!", 3),
				new Lore("Swift as the coursing river!", 3), new Lore("Won't you find me, free bird?", 3),
				new Lore("He crawls like a worm from a bird!", 3), new Lore("Fly like the wind, Bullseye!", 3),
				new Lore("Here comes the rooster, no he ain't gonna die!", 3), new Lore("In the eye of the storm.", 3),
				new Lore("Fly back to school now little starling, fly, fly, fly...", 3)}, 
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2), new EnchantEntry(Enchantment.projectileProtection, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 5, 3), new EnchantEntry(Enchantment.projectileProtection, 3, 3)}));

		addToLoreMap("tragicmc:huntersTunic", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2), new EnchantEntry(Enchantment.projectileProtection, 1, 2),
				new EnchantEntry(Enchantment.unbreaking, 5, 3), new EnchantEntry(Enchantment.projectileProtection, 3, 3), new EnchantEntry(TragicEnchantments.Agility, 1, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:huntersCap")));

		addToLoreMap("tragicmc:huntersLegs", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2), new EnchantEntry(Enchantment.projectileProtection, 1, 2),
				new EnchantEntry(Enchantment.unbreaking, 5, 3), new EnchantEntry(Enchantment.projectileProtection, 3, 3), new EnchantEntry(TragicEnchantments.Agility, 1, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:huntersCap")));

		addToLoreMap("tragicmc:huntersBoots", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2), new EnchantEntry(Enchantment.projectileProtection, 1, 2),
				new EnchantEntry(Enchantment.unbreaking, 5, 3), new EnchantEntry(Enchantment.projectileProtection, 3, 3), new EnchantEntry(Enchantment.featherFalling, 1, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:huntersCap")));

		addToLoreMap("tragicmc:lightHelm", new LoreEntry(new Lore[] {new Lore("Don't give up.", 1), new Lore("Overcome.", 1),
				new Lore("Rise above.", 1), new Lore("Inspire others.", 1), new Lore("Don't ever give up.", 1),
				new Lore("Brighten your day!", 1), new Lore("Be positive.", 1), new Lore("It's not that bad.", 1),
				new Lore("Get up, stand up!", 1), new Lore("The night is always darkest just before the dawn.", 3),
				new Lore("Don't stop believing!", 2), new Lore("Rise above this!", 2), new Lore("Don't worry, it gets better.", 2),
				new Lore("Live to rise!", 2), new Lore("Seize the day!", 2),
				new Lore("Carpe diem.", 2), new Lore("Keep your faith.", 1), new Lore("Never give up hope.", 1),
				new Lore("Everything in it's right place.", 2), new Lore("Everything zen.", 2), new Lore("Inspire and electrify.", 2),
				new Lore("Let your light shine down!", 3), new Lore("Beacon of hope!", 3), new Lore("Live and let die.", 3),
				new Lore("Even when your hope is gone, move along, move along just to make it through!", 3),
				new Lore("Always look on the bright side of life!", 3), new Lore("Dig me out from under what is covering!", 3),
				new Lore("It's not too late, it's never too late.", 3), new Lore("I can feel you all around me, thickening the air I'm breathing.", 3),
				new Lore("Turn around, bright eyes!", 3), new Lore("Open up my eager eyes, cuz I'm Mr. Brightside!", 3),
				new Lore("Welcome to this place, I'll show you everything with arms wide open!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2), new EnchantEntry(Enchantment.respiration, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(Enchantment.respiration, 3, 3), new EnchantEntry(Enchantment.aquaAffinity, 1, 3)}));

		addToLoreMap("tragicmc:lightPlate", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(TragicEnchantments.RuneWalker, 1, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2),
				new EnchantEntry(TragicEnchantments.RuneWalker, 3, 2), new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.RuneWalker, 5, 3),
				new EnchantEntry(TragicEnchantments.Ignition, 1, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:lightHelm")));

		addToLoreMap("tragicmc:lightLegs", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2), new EnchantEntry(TragicEnchantments.RuneWalker, 1, 2),
				new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.RuneWalker, 3, 3), new EnchantEntry(TragicEnchantments.Ignition, 3, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:lightHelm")));

		addToLoreMap("tragicmc:lightBoots", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2), new EnchantEntry(TragicEnchantments.RuneWalker, 1, 2),
				new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.RuneWalker, 3, 3), new EnchantEntry(Enchantment.featherFalling, 1, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:lightHelm")));

		addToLoreMap("tragicmc:mercuryHelm", new LoreEntry(new Lore[] {new Lore("Don't touch me.", 1), new Lore("Get away from me.", 1),
				new Lore("Don't touch!", 1), new Lore("No touchy!", 1), new Lore("Does anyone have disinfectant?", 1),
				new Lore("Germs!", 1), new Lore("Don't breathe on me!", 1), new Lore("There's germs everywhere...", 2),
				new Lore("Time for the 4th daily shower!", 2), new Lore("Anyone here ever heard of soap?", 2),
				new Lore("Just block out all of the filth...", 2), new Lore("Time for the 3rd disinfectant layer!", 2),
				new Lore("Why is it so dirty outside?", 2), new Lore("Eww, it touched me!", 2),
				new Lore("I can feel the germs crawling their way into the nape of my neck!", 3),
				new Lore("Ever heard of hygeine?", 3), new Lore("Too many things in close proximity!", 3),
				new Lore("Eww don't touch me with your germs!", 3), new Lore("Don't touch me you filthy casual!", 3),
				new Lore("Die you infectious disease!", 3), new Lore("I despise personal interaction!", 3),
				new Lore("Quick, give me a wipey!", 3), new Lore("It's a gift... and a curse.", 3),
				new Lore("Stay away from me with your disease ridden hands!", 2), new Lore("Disgusting.", 1), new Lore("Gross.", 1)}, 
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 2, 2), new EnchantEntry(Enchantment.unbreaking, 3, 3),
						new EnchantEntry(Enchantment.respiration, 1, 3)}));

		addToLoreMap("tragicmc:mercuryPlate", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 2, 2), new EnchantEntry(Enchantment.protection, 1, 2),
				new EnchantEntry(Enchantment.unbreaking, 3, 3), new EnchantEntry(Enchantment.protection, 2, 3), new EnchantEntry(TragicEnchantments.Elasticity, 2, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:mercuryHelm")));

		addToLoreMap("tragicmc:mercuryLegs", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 2, 2), new EnchantEntry(TragicEnchantments.Elasticity, 1, 2),
				new EnchantEntry(Enchantment.unbreaking, 3, 3), new EnchantEntry(TragicEnchantments.Elasticity, 2, 3), new EnchantEntry(TragicEnchantments.Paralysis, 1, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:mercuryHelm")));

		addToLoreMap("tragicmc:mercuryBoots", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking ,2, 2), new EnchantEntry(Enchantment.unbreaking, 3, 3),
				new EnchantEntry(TragicEnchantments.Elasticity, 1, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:mercuryHelm")));

		addToLoreMap("tragicmc:skullHelmet", new LoreEntry(new Lore[] {new Lore("What's that smell?", 1), new Lore("Is that smell... you?", 1),
				new Lore("Sniff, sniff...", 1), new Lore("You smell that?", 1), new Lore("Smells like rotten eggs...", 1),
				new Lore("Something smells raunchy...", 1), new Lore("The nose knows!", 1), new Lore("Are you a hobo or something?", 1),
				new Lore("Why do you smell like you live in a sewer?", 1), new Lore("Something smells fishy.", 1),
				new Lore("We don't deliver to sewers.", 2), new Lore("Anyone have an air freshener?", 2),
				new Lore("Let me guess, you're a garbage man.", 2), new Lore("At least it's a minty garbage smell now.", 2),
				new Lore("There's a faint smell of filth in the air.", 2), new Lore("Well, this stinks.", 2), new Lore("He who smelt it.", 2),
				new Lore("Smells like Nirvana.", 3), new Lore("Smells like teen spirit.", 3), new Lore("My stench strong.", 3),
				new Lore("Everyone likes their own product.", 3), new Lore("I put Oscar the Grouch to shame!", 3),
				new Lore("They call me a garbage player, how did they know?", 3), new Lore("Love is in the air, no wait that's just me.", 3)},
				new EnchantEntry[]{
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 2, 2), new EnchantEntry(Enchantment.unbreaking, 3, 3),
						new EnchantEntry(TragicEnchantments.DeathTouch, 1, 3)}));

		addToLoreMap("tragicmc:skullPlate", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(Enchantment.unbreaking, 4, 2), new EnchantEntry(TragicEnchantments.DeathTouch, 1, 2),
				new EnchantEntry(Enchantment.unbreaking, 5, 3), new EnchantEntry(TragicEnchantments.DeathTouch, 3, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:skullHelmet")));

		addToLoreMap("tragicmc:skullLegs", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 2, 2), new EnchantEntry(TragicEnchantments.DeathTouch, 1, 2),
				new EnchantEntry(Enchantment.unbreaking, 3, 3), new EnchantEntry(TragicEnchantments.DeathTouch, 5, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:skullHelmet")));

		addToLoreMap("tragicmc:skullBoots", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 2, 2), new EnchantEntry(Enchantment.unbreaking, 3, 3),
				new EnchantEntry(TragicEnchantments.DeathTouch, 1, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:skullHelmet")));

		addToLoreMap("tragicmc:tungstenHelm", new LoreEntry(new Lore[] {new Lore("Hot stuff.", 1), new Lore("Feels lukewarm.", 1),
				new Lore("Pretty hot.", 1), new Lore("Warm.", 1), new Lore("Warmer.", 1), new Lore("Hot.", 1), new Lore("Hotter.", 1),
				new Lore("It's a bit stuffy out.", 1), new Lore("In heat.", 1), new Lore("Do I look hot in this?", 1),
				new Lore("Just warming up!", 1), new Lore("I look pretty hot in this.", 1), new Lore("I'm on fire!", 1),
				new Lore("Hot stuff, coming through!", 1), new Lore("I'm pretty heated right now.", 1), new Lore("Spontaneous combustion.", 1),
				new Lore("First-degree burn.", 1), new Lore("You got burned!", 1), new Lore("Burn it all down to the ground!", 2),
				new Lore("Things are heating up quickly!", 2), new Lore("It's getting hot in here.", 2), new Lore("Burn baby, burn.", 2),
				new Lore("Too hot to handle!", 2), new Lore("Second-degree burn.", 2), new Lore("Slow burn...", 2), new Lore("Burnt to ashes.", 2),
				new Lore("Original fire.", 2), new Lore("Light my fire!", 2), new Lore("Bridges are burning now...", 2),
				new Lore("I'm hot-blooded! Check it and see, I got a fever of a hundred and three!", 3), new Lore("Third-degree burn.", 3),
				new Lore("Need some water for that burn?", 3), new Lore("I'm burning, I'm burning, I'm burning for you!", 3),
				new Lore("Caution: Contents may be hot.", 3), new Lore("Warning: Contents may explode under pressure.", 3),
				new Lore("I fell in to a burning ring of fire, I went down, down, down, and the flames went higher!", 3),
				new Lore("Through the fire and flames...", 3), new Lore("Shepherd of fire!", 3), new Lore("Scream, aim, fire!", 3),
				new Lore("We can't wait to burn it to the ground!", 3), new Lore("I don't want to set the world on fire, I just want to start a flame in your heart.", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 2, 1), new EnchantEntry(Enchantment.fireProtection, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2),
						new EnchantEntry(Enchantment.fireProtection, 2, 2), new EnchantEntry(Enchantment.unbreaking, 5, 3), new EnchantEntry(Enchantment.fireProtection, 3, 3),
						new EnchantEntry(TragicEnchantments.Ignition, 1, 3)}));

		addToLoreMap("tragicmc:tungstenPlate", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(Enchantment.fireProtection, 1, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2),
				new EnchantEntry(Enchantment.fireProtection, 3, 2), new EnchantEntry(TragicEnchantments.Ignition, 1, 2), new EnchantEntry(Enchantment.unbreaking, 7, 3),
				new EnchantEntry(Enchantment.fireProtection, 5, 3), new EnchantEntry(TragicEnchantments.Ignition, 3, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:tungstenHelm")));

		addToLoreMap("tragicmc:tungstenLegs", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 2, 1), new EnchantEntry(TragicEnchantments.Ignition, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2),
				new EnchantEntry(TragicEnchantments.Ignition, 3, 2), new EnchantEntry(Enchantment.fireProtection, 1, 2), new EnchantEntry(Enchantment.unbreaking, 4, 3),
				new EnchantEntry(TragicEnchantments.Ignition, 5, 3), new EnchantEntry(Enchantment.fireProtection, 3, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:tungstenHelm")));

		addToLoreMap("tragicmc:tungstenBoots", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 2, 1), new EnchantEntry(Enchantment.fireProtection, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2),
				new EnchantEntry(Enchantment.fireProtection, 2, 2), new EnchantEntry(TragicEnchantments.Ignition, 1, 2), new EnchantEntry(Enchantment.unbreaking, 5, 3),
				new EnchantEntry(Enchantment.fireProtection, 3, 3), new EnchantEntry(TragicEnchantments.Ignition, 3, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:tungstenHelm")));

		addToLoreMap("tragicmc:overlordHelm", new LoreEntry(new Lore[] {new Lore("And can you offer me proof of your existence? How can you, when neither modern science nor philosophy can explain what life is?", 3),
				new Lore(" If we all reacted the same way, we'd be predictable, and there's always more than one way to view a situation.", 3),
				new Lore("There's nothing sadder than a puppet without a ghost, especially the kind with red blood running through them.", 3),
				new Lore("Even a simulated experience or a dream is simultaneous reality and fantasy.", 3),
				new Lore("If you've got a problem with the world, change yourself.", 3),  new Lore("I feel confined, only free to expand myself within boundaries.", 3),
				new Lore("Your effort to remain what you are is what limits you.", 3), new Lore("I mean, who knows what's inside your head. Have you ever seen your own brain?", 2),
				new Lore("I thought what I'd do is pretend I was one of those deaf-mutes.", 2), new Lore("Stand alone complex.", 2),
				new Lore("The law doesn't protect people. People protect the law.", 3), new Lore("The time when our connections to others was the basis of ourselves is long gone.", 3),
				new Lore("The future is not a straight line. It is filled with many crossroads.", 3), new Lore("When you leave behind your body, what remains is your ghost.", 3),
				new Lore("KANEDAAAAAAAAA!", 1), new Lore("TESTSUOOOOOOOO!", 1), new Lore("Human curiosity.", 0), new Lore("Enforced criticism.", 0), new Lore("Ambiguous decline.", 0),
				new Lore("Sentiments of a dying socialite.", 1), new Lore("Space and time, dimensions beyond our imaginations.", 1), new Lore("A tragic portrait of a cynic", 1)}, 
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(Enchantment.aquaAffinity, 5, 3), new EnchantEntry(Enchantment.respiration, 5, 3), new EnchantEntry(TragicEnchantments.DeathTouch, 5, 3),
						new EnchantEntry(TragicEnchantments.Elasticity, 3, 3), new EnchantEntry(TragicEnchantments.Ignition, 5, 3), new EnchantEntry(TragicEnchantments.Paralysis, 5, 3), new EnchantEntry(TragicEnchantments.RuneWalker, 5, 3),
						new EnchantEntry(TragicEnchantments.Toxicity, 5, 3)}));

		addToLoreMap("tragicmc:overlordPlate", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(Enchantment.protection, 5, 3), new EnchantEntry(TragicEnchantments.DeathTouch, 5, 3),
				new EnchantEntry(Enchantment.thorns, 5, 3), new EnchantEntry(TragicEnchantments.Elasticity, 3, 3), new EnchantEntry(TragicEnchantments.Ignition, 5, 3),
				new EnchantEntry(TragicEnchantments.Paralysis, 5, 3), new EnchantEntry(TragicEnchantments.RuneWalker, 5, 3), new EnchantEntry(TragicEnchantments.Toxicity, 5, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:overlordHelm")));

		addToLoreMap("tragicmc:overlordLegs", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(Enchantment.protection, 5, 3), new EnchantEntry(TragicEnchantments.DeathTouch, 5, 3),
				new EnchantEntry(Enchantment.thorns, 5, 3), new EnchantEntry(TragicEnchantments.Elasticity, 3, 3), new EnchantEntry(TragicEnchantments.Ignition, 5, 3),
				new EnchantEntry(TragicEnchantments.Paralysis, 5, 3), new EnchantEntry(TragicEnchantments.RuneWalker, 5, 3), new EnchantEntry(TragicEnchantments.Toxicity, 5, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:overlordHelm")));

		addToLoreMap("tragicmc:overlordBoots", new LoreEntry(new Lore[] {}, new EnchantEntry[] {
				new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(Enchantment.protection, 5, 3), new EnchantEntry(Enchantment.featherFalling, 5, 3),
				new EnchantEntry(TragicEnchantments.DeathTouch, 5, 3), new EnchantEntry(TragicEnchantments.Elasticity, 3, 3), new EnchantEntry(TragicEnchantments.Ignition, 5, 3),
				new EnchantEntry(TragicEnchantments.Paralysis, 5, 3), new EnchantEntry(TragicEnchantments.RuneWalker, 5, 3), new EnchantEntry(TragicEnchantments.Toxicity, 5, 3)
		}).copyLoresFrom(getLoreEntry("tragicmc:overlordHelm")));

		//Tools
		addToLoreMap("tragicmc:tungstenJack", new Lore[] {new Lore("Work, work, work!", 1), new Lore("Time for lunch!", 1),
				new Lore("Work all day, sleep all night!", 2), new Lore("Off to work we go!", 2), new Lore("Can you dig it?", 1),
				new Lore("Just keep digging, digging, digging!", 2), new Lore("The finest weapons and armor!", 2),
				new Lore("Diamonds!", 3), new Lore("Ooh, emeralds!", 3), new Lore("Forged in the fires of Mount Doom!", 3),
				new Lore("The best blacksmith in Whiterun!", 3), new Lore("Can you pick up what I'm putting down?", 1)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.efficiency, 1, 1), new EnchantEntry(Enchantment.fortune, 1, 1), new EnchantEntry(Enchantment.efficiency, 3, 2),
						new EnchantEntry(Enchantment.fortune, 2, 2), new EnchantEntry(Enchantment.fireAspect, 1, 2), new EnchantEntry(Enchantment.efficiency, 5, 3),
						new EnchantEntry(Enchantment.fortune, 3, 3), new EnchantEntry(Enchantment.fireAspect, 2, 3), new EnchantEntry(TragicEnchantments.Combustion, 1, 3)});

		addToLoreMap("tragicmc:celestialJack", new Lore[] {new Lore("Enigmatic.", 0), new Lore("Quite the mystery.", 0),
				new Lore("It's a mystery to us all.", 0), new Lore("To the Mystery Machine!", 1),
				new Lore("I would've gotten away with it if it wasn't for you meddling kids!", 1), new Lore("Scooby Snax?", 1),
				new Lore("Another mystery solved!", 1), new Lore("We've got a mystery on our hands!", 2),
				new Lore("It's Old Man Withers from the Amusement Park!", 2), new Lore("Jinkies!", 2), new Lore("Whodunit?", 2),
				new Lore("Let's get out our Handy-Dandy Notebook!", 3), new Lore("Elementary, my dear Watson!", 3),
				new Lore("Once you eliminate the impossible, whatever remains, no matter how improbable, must be the truth.", 3),
				new Lore("We just found a clue!", 1), new Lore("I live by Harry's code.", 3),
				new Lore("I'm not a psychopath, I'm a high functioning sociopath, do your research.", 3),
				new Lore("Colonel Mustard in the library with a knife!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 0), new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(Enchantment.fortune, 1, 1),
						new EnchantEntry(Enchantment.unbreaking, 5, 2), new EnchantEntry(Enchantment.fortune, 3, 2), new EnchantEntry(TragicEnchantments.Veteran, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(Enchantment.fortune, 5, 3), new EnchantEntry(TragicEnchantments.Veteran, 3, 3),
						new EnchantEntry(TragicEnchantments.Luminescence, 1, 3)});

		//Normal Weapons
		addToLoreMap("tragicmc:mercuryDagger", new Lore[] {new Lore("Boring.", 1), new Lore("Nice.", 1),
				new Lore("Interesting.", 1), new Lore("Lame", 1), new Lore("Ha.", 2),
				new Lore("Awesome.", 2), new Lore("That's fascinating.", 2), new Lore("That's nice.", 2),
				new Lore("That's amazing!", 3), new Lore("Fantastic!", 3), new Lore("Okay.", 1),
				new Lore("I'm shuddering with excitement!", 3), new Lore("Ama-zuh-zing!", 3), new Lore("So awesome!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 2, 2), new EnchantEntry(Enchantment.unbreaking, 3, 3),
						new EnchantEntry(Enchantment.sharpness, 1, 3)});

		addToLoreMap("tragicmc:beastlyClaws", new Lore[] {new Lore("That's beastly.", 1), new Lore("Epic.", 1), new Lore("Knockout!", 1),
				new Lore("Roar!", 1), new Lore("Combo!", 1), new Lore("Let's fight!", 1), new Lore("Sucker punch!", 1),
				new Lore("Just getting started!", 2), new Lore("Just sharpening my claws!", 2), new Lore("One-two punch!", 2),
				new Lore("You're gonna hear me roar!", 2), new Lore("Punchout!", 2), new Lore("Fight!", 2), new Lore("TKO!", 2),
				new Lore("Hit and Run!", 3), new Lore("Falcon Punch!", 3), new Lore("Going Beastmode!", 3), new Lore("C-c-c-combo breaker!", 3),
				new Lore("Limit break!", 3), new Lore("I'll rip you to pieces!", 3), new Lore("Tear you to pieces, rip you apart!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 2, 2), new EnchantEntry(TragicEnchantments.Slay, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 3, 3), new EnchantEntry(TragicEnchantments.Slay, 2, 3), new EnchantEntry(TragicEnchantments.Consume, 1, 3)});

		addToLoreMap("tragicmc:blindingLight", new Lore[] {new Lore("You're shining!", 1), new Lore("Shine on!", 1), new Lore("Aw, you're glowing~", 1),
				new Lore("Shine bright like a diamond.", 1), new Lore("Just needs some spit shine!", 1), new Lore("Shinedown.", 1),
				new Lore("It's bright, like me!", 1), new Lore("So bright.", 2), new Lore("Like a shooting star!", 2), new Lore("Ooh, shiny!", 2),
				new Lore("Shiny, shiny, shiny!", 2), new Lore("Brilliant luster!", 2), new Lore("Heaven let your light shine on!", 3),
				new Lore("How do you get just the right amount of shiny?!", 3), new Lore("Always look on the bright side of life!", 3),
				new Lore("Turn on your love light!", 3), new Lore("Shine on you crazy diamond!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(TragicEnchantments.Absolve, 1, 1), new EnchantEntry(TragicEnchantments.Absolve, 3, 2), new EnchantEntry(Enchantment.unbreaking, 1, 2),
						new EnchantEntry(TragicEnchantments.Absolve, 5, 3), new EnchantEntry(Enchantment.unbreaking, 1, 3), new EnchantEntry(Enchantment.fireAspect, 1, 3)});

		addToLoreMap("tragicmc:celestialAegis", new Lore[] {new Lore("This is my jam!", 1), new Lore("Sounds to die for!", 1),
				new Lore("The Benny Hill theme song!", 1), new Lore("First things first, I'm the realist!", 1), new Lore("Nice tune!", 1),
				new Lore("What a lovely melody!", 1), new Lore("Death in E-Minor!", 2), new Lore("Screams in 6/4 time!", 2),
				new Lore("4/4 at 120 bpm", 2), new Lore("What a lovely death sound you make!", 2), new Lore("Such a lovely scream!", 2),
				new Lore("Shrieks of terror have a nice ambience!", 2), new Lore("Let the music take your breath away~", 2),
				new Lore("I can show you the world!", 3), new Lore("I wish I could be part of your world!", 3),
				new Lore("Be our guest!", 3), new Lore("Poor unfortunate souls!", 3), new Lore("Go! Go! Power Rangers!", 3),
				new Lore("Heroes in a half-shell, turtle power!", 3), new Lore("Flight of the Bumblebee!", 3),
				new Lore("The Blue Danube!", 3), new Lore("It's Mambo No. 5!", 3), new Lore("Guess who's back, back again!", 3),
				new Lore("Under the sea!", 3), new Lore("You know I make you wanna scream!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 0), new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(TragicEnchantments.Absolve, 1, 1),
						new EnchantEntry(Enchantment.unbreaking, 5, 2), new EnchantEntry(TragicEnchantments.Absolve, 3, 2), new EnchantEntry(TragicEnchantments.Consume, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.Absolve, 5, 3), new EnchantEntry(TragicEnchantments.Consume, 3, 3),
						new EnchantEntry(TragicEnchantments.Reach, 3, 3), new EnchantEntry(Enchantment.looting, 3, 3), new EnchantEntry(TragicEnchantments.Luminescence, 1, 3)});

		addToLoreMap("tragicmc:celestialLongbow", new Lore[] {new Lore("Like meteor showers!", 1), new Lore("Shooting stars!", 1),
				new Lore("Beautiful Starlights!", 1), new Lore("Make a Wish!", 1), new Lore("So beautiful!", 2),
				new Lore("When the sun goes down, we'll be groovin'", 3), new Lore("After the sun sets, the stars come out to play!", 3),
				new Lore("Ooh, a free starman!", 2), new Lore("Make a wish!", 2), new Lore("Time for the star festival!", 3),
				new Lore("Meteor Smash!", 3), new Lore("Time for armageddon!", 2), new Lore("Guardian of the Galaxy!", 3),
				new Lore("The Final Starman!?", 3), new Lore("Good morning Starshine, the Earth says, Hello!", 3),
				new Lore("Warm the celestial bodies!", 3), new Lore("Just a moonage daydream!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 0), new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(Enchantment.power, 1, 1),
						new EnchantEntry(Enchantment.unbreaking, 5, 2), new EnchantEntry(Enchantment.power, 3, 2), new EnchantEntry(Enchantment.looting, 3, 2),
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(Enchantment.power, 5, 3), new EnchantEntry(Enchantment.looting, 5, 3),
						new EnchantEntry(TragicEnchantments.Multiply, 1, 3), new EnchantEntry(Enchantment.infinity, 1, 3), new EnchantEntry(TragicEnchantments.Luminescence, 1, 3)});

		addToLoreMap("tragicmc:frozenLightning", new Lore[] {new Lore("Was that lightning?", 1), new Lore("Ouch, you zapped me!", 1),
				new Lore("Used Spark! It's not very effective...", 2), new Lore("Lightning crashes...", 2), new Lore("A storm is brewing!", 2),
				new Lore("You've been... THUNDERSTRUCK!", 3), new Lore("Static shock!", 2), new Lore("I feel shocked.", 3),
				new Lore("Time for a lightning round!", 3), new Lore("Used Volt Tackle! Critical hit!", 3), 
				new Lore("Used Thunder! It's super effective!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2), new EnchantEntry(TragicEnchantments.RuneBreak, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 5, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 3, 3), new EnchantEntry(TragicEnchantments.Rust, 1, 3),
						new EnchantEntry(TragicEnchantments.Luminescence, 1, 3)});

		addToLoreMap("tragicmc:gravitySpike", new Lore[] {new Lore("Time for a demonstration!", 1), new Lore("e=mc^2", 1),
				new Lore("For Science!", 1), new Lore("The next Einstein!", 2), new Lore("Isn't that a Rube Goldberg?", 2),
				new Lore("Science rules!", 2), new Lore("I like 3.14.", 2), new Lore("In SPAAAAAAAAAAAACE!", 3),
				new Lore("Reaching escape velocity!", 3), new Lore("It is a dimension as vast as space and as timeless as infinity...", 3),
				new Lore("There is a fifth dimension, beyond that which is known to man.", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2), new EnchantEntry(Enchantment.knockback, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 5, 3), new EnchantEntry(Enchantment.knockback, 3, 3), new EnchantEntry(TragicEnchantments.Distract, 1, 3)});

		addToLoreMap("tragicmc:guiltyThorn", new Lore[] {new Lore("Kill...", 1), new Lore("Your happiness kills me inside.", 1),
				new Lore("Your pain feeds me.", 1), new Lore("Your hatred fuels my soul.", 2), new Lore("I love when you hate me.", 2),
				new Lore("Die.", 1), new Lore("I'm not crazy, I'm the only one thinking clearly right now.", 2),
				new Lore("Your pain = <3", 3), new Lore("Your screams of agony sound so beautiful!", 3),
				new Lore("Some call me sadistic. I just like to have fun at other's expense.", 3),
				new Lore("Don't worry, I'll end your misery!", 3), new Lore("You sound better when you're dead!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2), new EnchantEntry(TragicEnchantments.Leech, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 5, 3), new EnchantEntry(TragicEnchantments.Leech, 3, 3), new EnchantEntry(Enchantment.sharpness, 3, 3)});

		addToLoreMap("tragicmc:harmonyBell", new Lore[] {new Lore("Ring-a-ding-ding!", 1), new Lore("Peace and Quiet.", 1),
				new Lore("Tranquility.", 1), new Lore("Need some R&R?", 2), new Lore("Ding-ding! Dinner is ready!", 2),
				new Lore("Listen to those glorious chimes!", 2), new Lore("Relax.", 2), new Lore("Hell's Bells!", 3),
				new Lore("Fahoo-Fores, Dahoo-Dores!", 3), new Lore("Ding dong, the witch is dead!", 3), new Lore("For Whom the Bell Tolls.", 3),
				new Lore("I was ringing the dinner bell!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2), new EnchantEntry(TragicEnchantments.Distract, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 5, 3), new EnchantEntry(TragicEnchantments.Distract, 3, 3), new EnchantEntry(TragicEnchantments.Absolve, 1, 3),
						new EnchantEntry(Enchantment.knockback, 1, 3)});

		addToLoreMap("tragicmc:huntersBow", new Lore[] {new Lore("On the hunt.", 1), new Lore("Run as fast as you can!", 1),
				new Lore("Tracking...", 1), new Lore("Let the hunt begin.", 2), new Lore("Time to join the hunting party!", 3),
				new Lore("Catch me if you can!", 2), new Lore("Conquest!", 2), new Lore("Don't worry, I'm an expert.", 3),
				new Lore("The Hunter becomes the Hunted.", 3), new Lore("The Most Dangerous Game", 3), new Lore("Night of the Hunter!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 2, 2), new EnchantEntry(Enchantment.punch, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 3, 3), new EnchantEntry(Enchantment.punch, 2, 3), new EnchantEntry(Enchantment.flame, 1, 3),
						new EnchantEntry(Enchantment.power, 1, 3)});

		addToLoreMap("tragicmc:ireParticleCannon", new Lore[] {new Lore("Accelerate!", 1), new Lore("Fire! Fire! Fire!", 2),
				new Lore("Kill! Kill! Kill!", 3), new Lore("You look pretty shady, dontcha?", 1), new Lore("Get away from me you creeper!", 1),
				new Lore("Take a picture, it'll last longer.", 2), new Lore("This is why I don't reply to PMs!", 2),
				new Lore("Seriously, stop following me!", 1), new Lore("Why are you stalking me?", 1),
				new Lore("Seriously, can you not do that!", 2), new Lore("You ought not to have done that!", 2),
				new Lore("Prepare to be particle accelerated!", 3), new Lore("I will give you $5 if you could not do that.", 3),
				new Lore("Way to slide into those DMs, buddy.", 3), new Lore("I'll never get that image out of my head.", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.knockback, 1, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2),
						new EnchantEntry(Enchantment.knockback, 3, 2), new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(Enchantment.knockback, 5, 3)});

		addToLoreMap("tragicmc:mourningStar", new Lore[] {new Lore("Sleep is for the weak!", 1), new Lore("Dy-no-mite!", 1),
				new Lore("Kaboom.", 1), new Lore("Nuke!", 3), new Lore("For SPARTAAAAAA!", 3), new Lore("Just die already!", 2),
				new Lore("I'm TNT, I'm dynamite!", 3), new Lore("I have an explosive temper.", 2), new Lore("Enemy airstrike inbound!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.smite, 1, 1), new EnchantEntry(Enchantment.smite, 3, 2), new EnchantEntry(TragicEnchantments.Consume, 1, 2),
						new EnchantEntry(Enchantment.smite, 5, 3), new EnchantEntry(TragicEnchantments.Consume, 3, 3), new EnchantEntry(Enchantment.looting, 1, 3)});

		addToLoreMap("tragicmc:nekoLauncher", new Lore[] {new Lore("Oops", 1), new Lore("I meant to do that.", 1),
				new Lore("That was supposed to happen!", 1), new Lore("Tell me where it hurts!", 2), new Lore("It's just a flesh wound.", 2),
				new Lore("Does it hurt when I do this?", 2), new Lore("This is why I can't have nice things!", 3),
				new Lore("Some days you just can't get rid of a bomb!", 3), new Lore("Meow~", 3), 
				new Lore("Seriously, all of these mess-ups were on purpose!", 2), new Lore("It's all going to plan, honestly!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2), new EnchantEntry(Enchantment.knockback, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(Enchantment.knockback, 3, 3), new EnchantEntry(TragicEnchantments.Distract, 1, 3)});

		addToLoreMap("tragicmc:pitchBlack", new Lore[] {new Lore("Black as my soul!", 1), new Lore("Hide in the shadows.", 1),
				new Lore("Darkness is my friend.", 1), new Lore("Perfect Dark Zero.", 1), new Lore("Paint it black!", 2),
				new Lore("Like the black in your eyes.", 2), new Lore("Pitch black!", 2), new Lore("Black Hole Sun!", 3),
				new Lore("Welcome to the Black Parade!", 3), new Lore("Blackout! Blood in your eyes!", 3),
				new Lore("I was born in the dark. Molded by it. You merely adopted it.", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(TragicEnchantments.Decay, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2),
						new EnchantEntry(TragicEnchantments.Decay, 3, 2), new EnchantEntry(TragicEnchantments.RuneBreak, 1, 2), new EnchantEntry(Enchantment.unbreaking, 5, 3),
						new EnchantEntry(TragicEnchantments.Decay, 5, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 3, 3), new EnchantEntry(Enchantment.looting, 1, 3)});

		addToLoreMap("tragicmc:reaperScythe", new Lore[] {new Lore("Bleed out.", 1), new Lore("Bleed for me.", 1),
				new Lore("Blood is flowing now!", 1), new Lore("It's raining blood.", 1), new Lore("Blood is thicker than water.", 2),
				new Lore("No matter how you discriminate we all bleed the same.", 2), new Lore("Crimson red, like the blood moon.", 2),
				new Lore("I ate his liver with some fava beans and a nice Chianti.", 3), new Lore("Digging deeper just to throw it away!", 3),
				new Lore("Let's paint this town red!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 3, 1), new EnchantEntry(TragicEnchantments.Decay, 1, 1), new EnchantEntry(Enchantment.unbreaking, 5, 2),
						new EnchantEntry(TragicEnchantments.Decay, 3, 2), new EnchantEntry(TragicEnchantments.Vampirism, 1, 2), new EnchantEntry(Enchantment.unbreaking, 10, 3),
						new EnchantEntry(TragicEnchantments.Decay, 5, 3), new EnchantEntry(TragicEnchantments.Vampirism, 3, 3)});

		addToLoreMap("tragicmc:witheringAxe", new Lore[] {new Lore("Like Paul Bunyan.", 1), new Lore("Lemme axe you a question!", 1),
				new Lore("Chop chop!", 1), new Lore("Weapon of choice.", 1), new Lore("Plaid is the new black.", 2),
				new Lore("Tree murderer.", 2), new Lore("Go ahead, axe me how my day went.", 2), new Lore("Your beard looks quite luxurious today.", 2),
				new Lore("He's a lumberjack and he's okay!", 3), new Lore("Chop Suey!?", 3), new Lore("Taking you right to the chop block!", 3),
				new Lore("Treetho's Choppa!", 3), new Lore("I'm gonna leave you on the cutting room floor!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 1, 1), new EnchantEntry(Enchantment.unbreaking, 3, 2), new EnchantEntry(Enchantment.sharpness, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 5, 3), new EnchantEntry(Enchantment.sharpness, 3, 3), new EnchantEntry(Enchantment.efficiency, 1, 3)});

		//Alpha weapons
		addToLoreMap("tragicmc:sentinel", new Lore[] {new Lore("The red pill?", 3), new Lore("White rabbit.", 3),
				new Lore("The blue pill?", 3), new Lore("How would you know the difference between the dream world and the real world?", 3),
				new Lore("Tumbling down the rabbit hole...", 3), new Lore("Is this the Matrix?", 3), new Lore("You are the one.", 3),
				new Lore("There is no spoon.", 3), new Lore("Wonder what's next.", 3), new Lore("I'm not the one.", 3),
				new Lore("You may have spent the last few years looking for me, but I have spent my entire life looking for you.", 3),
				new Lore("I don't like the idea that I'm not in control of my life.", 3),
				new Lore("What good is a phone call if you're unable to speak?", 3),
				new Lore("Mr. Anderson, you disappoint me.", 3), new Lore("Free your mind.", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.Absolve, 5, 3), new EnchantEntry(Enchantment.baneOfArthropods, 5, 3),
						new EnchantEntry(TragicEnchantments.Decay, 5, 3), new EnchantEntry(TragicEnchantments.Slay, 5, 3), new EnchantEntry(Enchantment.smite, 5, 3),
						new EnchantEntry(TragicEnchantments.Reach, 5, 3)});

		//Epic weapons
		addToLoreMap("tragicmc:butcher", new Lore[] {new Lore("Time to eat?", 0), new Lore("I'm so hungry...", 0),
				new Lore("I need food.", 0), new Lore("My stomach's grumbling...", 1), new Lore("That looks delicious!", 1),
				new Lore("My stomach won't stop growling!", 1), new Lore("Needs more salt...", 1),
				new Lore("Preheat oven to 450.", 2), new Lore("Bon apetit!", 2), new Lore("I'm having an old friend for dinner!", 2),
				new Lore("Just add a pinch of salt.", 2), new Lore("Add a splash of red wine.", 3),
				new Lore("Bake for twenty minutes or until golden brown.", 3), new Lore("Mmmm... donuts.", 3), new Lore("OMNOMNOMNOMNOM", 3),
				new Lore("Everything is edible, even me, but that would be cannibalism, children.", 3),
				new Lore("Add some olive oil and garlic then simmer.", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 3, 0), new EnchantEntry(TragicEnchantments.Reach, 3, 0), new EnchantEntry(Enchantment.unbreaking, 5, 1),
						new EnchantEntry(TragicEnchantments.Reach, 3, 1), new EnchantEntry(TragicEnchantments.Slay, 1, 1), new EnchantEntry(Enchantment.unbreaking, 7, 2),
						new EnchantEntry(TragicEnchantments.Reach, 3, 2), new EnchantEntry(TragicEnchantments.Slay, 3, 2), new EnchantEntry(Enchantment.sharpness, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.Reach, 5, 3), new EnchantEntry(TragicEnchantments.Slay, 5, 3),
						new EnchantEntry(Enchantment.sharpness, 3, 3)});

		addToLoreMap("tragicmc:dragonFang", new Lore[] {new Lore("Sasquatch!", 0), new Lore("Is that a nymph?", 0),
				new Lore("Sleeping with Sirens.", 0), new Lore("It's really happening!", 0), new Lore("Alrighty then. Picture this if you will.", 0),
				new Lore("I swear, there was a triangle of lights in the sky moving around!", 1), new Lore("Was that a jackelope?", 1),
				new Lore("It's obviously a centaur.", 1), new Lore("There's a cold spot here. Ghost?", 1),
				new Lore("I think I just saw E.T.!", 2), new Lore("Dude, I totally just saw Nessie.", 2),
				new Lore("Cartman got abducted by aliens last night!", 2), new Lore("Someone call the MIB", 2),
				new Lore("Can't remember what they said...", 2), new Lore("Return the slab or suffer my curse!", 3),
				new Lore("The man in gauze, the man in gauze. King RAAAAAMSAYYY!", 3), new Lore("They mentioned something about Group 935?", 3),
				new Lore("Aliens are nice, they apologized, gave me a nice lollipop and sent me on my way.", 3),
				new Lore("It's the chupacabra!", 3), new Lore("A Will-o-the-Wisp started that fire, I know how to cook!", 3),
				new Lore("Probed by alien. Chance of survival: minimal.", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 3, 0), new EnchantEntry(TragicEnchantments.Reach, 3, 0), new EnchantEntry(Enchantment.unbreaking, 5, 1),
						new EnchantEntry(TragicEnchantments.Reach, 3, 1), new EnchantEntry(TragicEnchantments.Slay, 1, 1), new EnchantEntry(Enchantment.unbreaking, 7, 2),
						new EnchantEntry(TragicEnchantments.Reach, 3, 2), new EnchantEntry(TragicEnchantments.Slay, 3, 2), new EnchantEntry(Enchantment.fireAspect, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.Reach, 3, 3), new EnchantEntry(TragicEnchantments.Slay, 5, 3),
						new EnchantEntry(Enchantment.fireAspect, 2, 3)});

		addToLoreMap("tragicmc:paranoia", new Lore[] {new Lore("So lonely.", 0), new Lore("Fragile and alone...", 0), new Lore("It's calm.", 0),
				new Lore("I'm so alone...", 0), new Lore("If only I had friends...", 1), new Lore("So scary.", 1), new Lore("The outside world is so scary.", 1),
				new Lore("When I wake up, I'm afraid.", 1), new Lore("Alone...", 1), new Lore("Feeling a bit paranoid right now...", 2),
				new Lore("Sensational fear of everything!", 2), new Lore("Darkness consumes me", 2), new Lore("Lost in the dark of my mind...", 3),
				new Lore("If fear is an animal, I've tamed it.", 3), new Lore("If fear is an animal, it may have just swallowed me whole...", 3), 
				new Lore("Just because you're paranoid, doesn't mean they're not after you.", 3), new Lore("Your Traveler's Light cannot reach you here!", 3),
				new Lore("They're all out to get me! Somebody help me!", 3), new Lore("Darkness consume me.", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 3, 0), new EnchantEntry(TragicEnchantments.Reach, 3, 0), new EnchantEntry(Enchantment.unbreaking, 5, 1),
						new EnchantEntry(TragicEnchantments.Reach, 3, 1), new EnchantEntry(TragicEnchantments.RuneBreak, 1, 1), new EnchantEntry(Enchantment.unbreaking, 7, 2),
						new EnchantEntry(TragicEnchantments.Reach, 3, 2), new EnchantEntry(TragicEnchantments.RuneBreak, 3, 2), new EnchantEntry(TragicEnchantments.Leech, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.Reach, 3, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 5, 3),
						new EnchantEntry(TragicEnchantments.Leech, 3, 3), new EnchantEntry(Enchantment.looting, 1, 3)});

		addToLoreMap("tragicmc:splinter", new Lore[] {new Lore("Beating around the bush...", 0), new Lore("I see.", 0),
				new Lore("I don't get it...", 0), new Lore("Fool's Gold!", 0), new Lore("Beggars can't be choosers!", 1),
				new Lore("Sorry to burst your bubble!", 1), new Lore("Easy as pie!", 1), new Lore("Piece of cake!", 1),
				new Lore("Don't cry over spilled milk!", 1), new Lore("Don't count your chickens before they hatch!", 2),
				new Lore("Jack of all trades!", 2), new Lore("Don't count your chickens before they hatch!", 2), new Lore("On cloud nine!", 2),
				new Lore("Sorry to rain on your parade!", 1), new Lore("A dime a dozen!", 3), new Lore("Curiosity killed the cat!", 1),
				new Lore("The nail that sticks out the most gets hammered down!", 3), new Lore("Read'em and weep!", 3),
				new Lore("Roll with the punches!", 3), new Lore("It's not rocket science!", 3), new Lore("A blessing in disguise!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 3, 0), new EnchantEntry(TragicEnchantments.Reach, 3, 0), new EnchantEntry(Enchantment.unbreaking, 5, 1),
						new EnchantEntry(TragicEnchantments.Reach, 3, 1), new EnchantEntry(TragicEnchantments.Consume, 1, 1), new EnchantEntry(Enchantment.unbreaking, 7, 2),
						new EnchantEntry(TragicEnchantments.Reach, 3, 2), new EnchantEntry(TragicEnchantments.Consume, 3, 2), new EnchantEntry(Enchantment.knockback, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.Reach, 3, 3), new EnchantEntry(TragicEnchantments.Consume, 5, 3), new EnchantEntry(Enchantment.knockback, 3, 3)});

		addToLoreMap("tragicmc:thardus", new Lore[] {new Lore("Power beam.", 0), new Lore("Charge beam.", 0), new Lore("Morph Ball.", 0),
				new Lore("Missle.", 0), new Lore("Energy Tank.", 0), new Lore("Better than a stun gun!", 1), new Lore("Spazer beam.", 1),
				new Lore("Grapple beam.", 1), new Lore("Zero suit.", 1), new Lore("Morph Ball bomb acquired!", 1),
				new Lore("Speed Booster acquired!", 1), new Lore("You're a girl?", 2), new Lore("Metroids. Metroids everywhere.", 2),
				new Lore("Why is there always a Space Pirate?", 2), new Lore("Remember me?", 2), new Lore("Space jump acquired!", 2),
				new Lore("Hyper Beam acquired!", 3), new Lore("Hypermode, activate!", 3), new Lore("Phazon beam acquired!", 3),
				new Lore("Plasma beam acquired!", 3), new Lore("Wave beam acquired!", 3), new Lore("Screw Attack acquired!", 3),
				new Lore("Super missle acquired!", 3), new Lore("Power Bomb acquired!", 3), new Lore("Speed Booster acquired!", 3), 
				new Lore("New area discovered: Tourian", 3), new Lore("You have arrived on planet, Tallon IV", 3),
				new Lore("SR388 has been cleared of all Metroid activity.", 3), new Lore("The last metroid is in captivity.", 3),
				new Lore("Power Suit acquired.", 1), new Lore("Varia Suit acquired!", 2), new Lore("Gravity Suit acquired!", 2),
				new Lore("Phazon Suit acquired!", 2), new Lore("Kraid has been defeated!", 1), new Lore("Ridley has been defeated!", 2),
				new Lore("Mother Brain has been defeated!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 3, 0), new EnchantEntry(TragicEnchantments.Reach, 3, 0), new EnchantEntry(Enchantment.unbreaking, 5, 1),
						new EnchantEntry(TragicEnchantments.Reach, 3, 1), new EnchantEntry(TragicEnchantments.RuneBreak, 1, 1), new EnchantEntry(Enchantment.unbreaking, 7, 2),
						new EnchantEntry(TragicEnchantments.Reach, 3, 2), new EnchantEntry(TragicEnchantments.RuneBreak, 3, 2), new EnchantEntry(TragicEnchantments.Rust, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.Reach, 3, 3), new EnchantEntry(TragicEnchantments.RuneBreak, 5, 3),
						new EnchantEntry(TragicEnchantments.Rust, 3, 3), new EnchantEntry(TragicEnchantments.Luminescence, 1, 3)});

		addToLoreMap("tragicmc:titan", new Lore[] {new Lore("Filthy mortal.", 0), new Lore("You're such a mortal.", 0),
				new Lore("Why do you have to be so... mortal?", 0), new Lore("You bore me, mortal.", 0),
				new Lore("So God-like!", 1), new Lore("It's like the Gods have blessed me!", 1), new Lore("Thank the Gods!", 1),
				new Lore("Praise the Gods for this gift!", 1), new Lore("Thank God!", 1), new Lore("A God-like aura permeates the air.", 1),
				new Lore("Such God-like abilities!", 2), new Lore("The Gods have become my equal!", 2), new Lore("I am a God!", 2),
				new Lore("Who needs a God when you have me?", 2), new Lore("You are an ant to me, mortal.", 3),
				new Lore("I am no mere mortal!", 2), new Lore("Puny God...", 3), new Lore("Poseidon has nothing on me!", 3), 
				new Lore("Faster than Hermes!", 3), new Lore("The violence of Ares!", 3), new Lore("Wiser than Athena!", 3),
				new Lore("More creative than Hephaestus!", 3), new Lore("More beautiful than Aphrodite!", 3),
				new Lore("More spectacular than Zeus!", 3)},
				new EnchantEntry[] {
						new EnchantEntry(Enchantment.unbreaking, 3, 0), new EnchantEntry(TragicEnchantments.Reach, 3, 0), new EnchantEntry(Enchantment.unbreaking, 5, 1),
						new EnchantEntry(TragicEnchantments.Reach, 3, 1), new EnchantEntry(Enchantment.looting, 1, 1), new EnchantEntry(Enchantment.unbreaking, 7, 2),
						new EnchantEntry(TragicEnchantments.Reach, 3, 2), new EnchantEntry(Enchantment.looting, 3, 2), new EnchantEntry(TragicEnchantments.Consume, 1, 2),
						new EnchantEntry(Enchantment.unbreaking, 10, 3), new EnchantEntry(TragicEnchantments.Reach, 5, 3), new EnchantEntry(Enchantment.looting, 5, 3),
						new EnchantEntry(TragicEnchantments.Consume, 3, 3)});
		/*
		//Tier 2 weapons 
		/*addToLoreMap(WeaponGrievingWidow.class, new Lore[] {new Lore(25, "Jealousy.", 1), new Lore(15, "Hate.", 1), new Lore(15, "Lovesick.", 1), new Lore(10, "Heartbreak.", 1), new Lore(5, "You can't trust any Man.", 1), new Lore(5, "You can't trust any Woman.", 1),
			new Lore(25, "Loving you is torture...", 2), new Lore(15, "My heart is in pieces.", 2), new Lore(15, "Please lie to make me feel better.", 2), new Lore(15, "I hope it kills you as much as it's killing me.", 2), new Lore(5, "Marriage is being sentenced to endless torture.", 2),
			new Lore(25, "I still love you with all the pieces of my broken heart.", 3), new Lore(10, "Seeing you with them just eats what's left of my heart.", 2), new Lore(5, "I gave my heart to you, but then you gave it to someone else...", 2), new Lore(5, "As heartless as your wretched lies...", 2),
			new Lore(15, "There's a hole where my heart used to be.", 3), new Lore(5, "Got a long list of ex-lovers, they'll tell you I'm insane.", 3), new Lore(15, "All that's left is broken pieces of my heart that you trampled all on...", 3), new Lore(10, "The color is all but faded down to this blackened heart.", 3),
			new Lore(5, "Why did you leave me in this life all alone?!", 3)},
				new EnchantEntry[][]{{new EnchantEntry(TragicEnchantments.Swiftness, 1), new EnchantEntry(TragicEnchantments.Perforate, 1)}, {new EnchantEntry(TragicEnchantments.Swiftness, 3), new EnchantEntry(TragicEnchantments.Perforate, 3), new EnchantEntry(TragicEnchantments.Consume, 1)},
			{new EnchantEntry(TragicEnchantments.Swiftness, 5), new EnchantEntry(TragicEnchantments.Perforate, 5), new EnchantEntry(TragicEnchantments.Consume, 3)},
			{new EnchantEntry(TragicEnchantments.Swiftness, 5), new EnchantEntry(TragicEnchantments.Perforate, 5), new EnchantEntry(TragicEnchantments.Consume, 5), new EnchantEntry(TragicEnchantments.DireHit, 1)}}); */
	}

	public static class LoreEntry {

		private final ArrayList<Lore> lores = new ArrayList<Lore>();
		private final ArrayList<EnchantEntry> enchants = new ArrayList<EnchantEntry>();

		public LoreEntry() {

		}

		public LoreEntry(Lore[] lores, EnchantEntry[] enchants) {
			this(Arrays.asList(lores), Arrays.asList(enchants));
		}

		public LoreEntry(Collection<Lore> lores, Collection<EnchantEntry> enchants)
		{
			this.lores.addAll(lores);
			this.enchants.addAll(enchants);
		}

		public LoreEntry copyLoresFrom(LoreEntry e) {
			this.lores.clear();
			this.lores.addAll(e.lores);
			return this;
		}

		public void addLore(Lore l)
		{
			this.lores.add(l);
		}

		public void addEnchantEntry(EnchantEntry ee) {
			this.enchants.add(ee);
		}

		/**
		 * This is used to pick a completely random lore based off of the weapon/armor's set of lores, this is for brand new weapons/armor or weapons/armor received from a mob
		 * @return
		 */
		public Lore getRandomLore()
		{
			try
			{
				final int i = rand.nextInt(100);
				int r = i < 15 ? 3 : (i < 45 ? 2 : (i < 85 ? 1 : 0)); //15% chance to be epic, 30% to be rare, 40% to be uncommon, 15% to be common
				return getLoreOfRarity(r);
			}
			catch (Exception e)
			{
				TragicMC.logError("Error getting a random Lore for a weapon", e);
				return null;
			}
		}

		/**
		 * Returns a random lore in the given rarity tier, otherwise returns an empty lore
		 * @param rarity
		 * @param id
		 * @return
		 */
		public Lore getLoreOfRarity(int rarity)
		{
			try
			{
				List<Lore> alist = new ArrayList();

				for (Lore l : this.lores)
					if (l.getRarity() == rarity) alist.add(l);

				if (alist.isEmpty()) return new Lore("[There are markings that you can't quite make out...]", rarity);

				return ((Lore) alist.get(rand.nextInt(alist.size()))).get();
			}
			catch (Exception e)
			{
				TragicMC.logError("Error retrieving a specific Lore for a weapon based on rarity", e);
				return null;
			}
		}

		public EnchantEntry[] getEnchantmentForRarity(int rarity)
		{
			try
			{
				List<EnchantEntry> alist = new ArrayList();

				for (EnchantEntry ee : this.enchants)
					if (ee.getEnchantRarity() == rarity) alist.add(ee);

				if (alist.isEmpty()) alist.add(new EnchantEntry(null, 0, rarity));

				return alist.toArray(new EnchantEntry[0]);
			}
			catch (Exception e)
			{
				TragicMC.logError("Error getting enchantments for a weapon", e);
				return null;
			}
		}
	}

	public static class EnchantEntry {

		private final Enchantment enchant;
		private final int level;
		private final int rarity;

		public EnchantEntry(Enchantment ench, int level, int rarity) {
			this.enchant = ench;
			this.level = level;
			this.rarity = MathHelper.clamp_int(rarity, 0, 3);
		}

		public EnchantEntry(EnchantEntry ee) {
			this.enchant = ee.enchant;
			this.level = ee.level;
			this.rarity = ee.rarity;
		}

		public Enchantment getEnchantment() { return this.enchant; }
		public int getEnchantLevel() { return this.level; }
		public int getEnchantRarity() { return this.rarity; }

		public EnchantEntry get() {
			return new EnchantEntry(this);
		}
	}

	public static class Lore {

		private final int rarity;
		private final String desc;

		public Lore(String desc, int rarity) {
			this.desc = desc;
			this.rarity = MathHelper.clamp_int(rarity, 0, 3);
		}

		public Lore(Lore lore)
		{
			this(lore.getDesc(), lore.getRarity());
		}

		public String getDesc() { return this.desc; }
		public int getRarity() { return this.rarity; }

		public EnumRarity getRarityEnum()
		{
			return this.rarity >= EnumRarity.values().length || this.rarity <= 0 ? EnumRarity.COMMON : EnumRarity.values()[this.rarity];
		}

		/**
		 * Returns a copy of this Lore so that it remains in the map
		 * @return
		 */
		public Lore get()
		{
			return new Lore(this);
		}
	}
}
