package tragicneko.tragicmc.items.challenge;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.network.MessageSound;
import tragicneko.tragicmc.util.EntityDropHelper.EntityDrop;
import tragicneko.tragicmc.util.LoreHelper;
import tragicneko.tragicmc.util.TragicEntityList;

public class ItemChallenge extends Item {

	public static EntityDrop[] badRewards = new EntityDrop[] {new EntityDrop(15, Items.coal), new EntityDrop(5, Items.flint_and_steel), new EntityDrop(5, Items.string), new EntityDrop(5, Items.stick),
		new EntityDrop(5, Items.bone), new EntityDrop(15, Items.bread), new EntityDrop(5, Items.book), new EntityDrop(5, Items.bowl), new EntityDrop(5, TragicItems.Ash), new EntityDrop(15, TragicItems.Banana),
		new EntityDrop(10, TragicItems.Sushi), new EntityDrop(5, TragicItems.BoneMarrow), new EntityDrop(30, TragicItems.ChallengeScroll)};

	public static EntityDrop[] cheapRewards = new EntityDrop[] {new EntityDrop(1, Items.diamond), new EntityDrop(1, Items.emerald), new EntityDrop(5, Items.apple), new EntityDrop(10, Items.iron_ingot),
		new EntityDrop(5, Items.gold_ingot), new EntityDrop(15, TragicItems.Tungsten), new EntityDrop(25, TragicItems.BloodSacrifice), new EntityDrop(25, TragicItems.NourishmentSacrifice),
		new EntityDrop(15, TragicItems.RedMercury), new EntityDrop(20, TragicItems.Quicksilver), new EntityDrop(5, TragicItems.QuicksilverIngot), new EntityDrop(5, Blocks.obsidian),
		new EntityDrop(15, TragicItems.Deathglow), new EntityDrop(15, TragicItems.Honeydrop), new EntityDrop(10, TragicItems.SkyFruit), new EntityDrop(5, TragicItems.Gloopii),
		new EntityDrop(1, getSpawnEggsMiniBoss()), new EntityDrop(15, TragicItems.ChallengeScroll)};

	public static EntityDrop[] rewards = new EntityDrop[] {new EntityDrop(10, Items.diamond), new EntityDrop(5, Items.emerald),
		new EntityDrop(15, TragicItems.AwakeningStone), new EntityDrop(30, TragicItems.AmuletRelease), new EntityDrop(25, TragicItems.DoomConsume),
		new EntityDrop(20, TragicItems.CooldownDefuse), new EntityDrop(25, TragicItems.Ruby), new EntityDrop(5, Items.iron_ingot),
		new EntityDrop(25, TragicItems.Sapphire), new EntityDrop(5, Items.gold_ingot), new EntityDrop(20, new ItemStack(Items.golden_apple, 1, 0), new ItemStack(Items.golden_apple, 1, 1)),
		new EntityDrop(5, TragicItems.CryingObsidianOrb), new EntityDrop(5, TragicItems.BleedingObsidianOrb), new EntityDrop(5, TragicItems.DyingObsidianOrb), new EntityDrop(5, TragicItems.ObsidianOrb),
		new EntityDrop(15, TragicItems.GoldenSushi),  new EntityDrop(10, TragicItems.Talisman), new EntityDrop(5, TragicItems.BloodSacrifice), new EntityDrop(5, TragicItems.NourishmentSacrifice),
		new EntityDrop(15, TragicItems.Deathglow), new EntityDrop(35, TragicItems.Honeydrop), new EntityDrop(20, TragicItems.SkyFruit), new EntityDrop(10, TragicItems.Gloopii),
		new EntityDrop(1, getSpawnEggsBoss()), new EntityDrop(3, getSpawnEggsMiniBoss()), new EntityDrop(3, getEpicWeapons()), new EntityDrop(3, getEpicWeapons())};

	private static String[] subNames = new String[] {"inactive", "inProgress", "complete"};

	public ItemChallenge()
	{
		this.setCreativeTab(TragicMC.Survival);
		this.setMaxDamage(0);
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setUnlocalizedName("tragicmc.challengeItem");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		if (stack.getItemDamage() == 0) return EnumRarity.COMMON;
		if (stack.getItemDamage() == 250) return EnumRarity.EPIC;
		if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey(Challenge.CHALLENGE_ID) || Challenge.getChallengeFromID(stack.getTagCompound().getInteger("challengeID")) == null) return EnumRarity.COMMON;
		switch(Challenge.getChallengeFromID(stack.getTagCompound().getInteger(Challenge.CHALLENGE_ID)).getDifficultyId())
		{
		case 3:
			return EnumRarity.EPIC;
		case 2:
			return EnumRarity.RARE;
		case 1:
			return EnumRarity.UNCOMMON;
		case 0:
		default:
			return EnumRarity.COMMON;
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int var = itemstack.getItemDamage() == 250 ? 2 : (itemstack.getItemDamage() != 0 ? 1 : 0);
		return getUnlocalizedName() + "." + subNames[var];
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (world.isRemote) return stack;

		if (stack.getItemDamage() == 0)
		{
			try
			{
				Challenge challenge = Challenge.getChallengeFromID(itemRand.nextInt(Challenge.challengeList.length - 1) + 1);
				while (challenge == null)
				{
					challenge = Challenge.getChallengeFromID(itemRand.nextInt(Challenge.challengeList.length - 1) + 1);
				}
				stack.setItemDamage(challenge.getChallengeId());
				if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setInteger(Challenge.CHALLENGE_ID, challenge.getChallengeId());
				player.addChatMessage(new ChatComponentText("Challenge accepted!"));
				if (player instanceof EntityPlayerMP) TragicMC.net.sendTo(new MessageSound("tragicmc:random.challengestart", 0.4F, 1.0F), (EntityPlayerMP) player);
				if (TragicConfig.allowAchievements && player instanceof EntityPlayerMP) player.triggerAchievement(TragicAchievements.challengeScroll);
			}
			catch (Exception e)
			{
				TragicMC.logError("Challenge item errored while retreiving a Challenge. Report this! ItemStack was " + stack, e);
				return stack;
			}
		}
		else if (stack.getItemDamage() == 250)
		{
			player.addChatMessage(new ChatComponentText("Challenge completed, have a reward!"));
			Challenge challenge = Challenge.getChallengeFromID(stack.getTagCompound().getInteger(Challenge.CHALLENGE_ID));
			final int extra = itemRand.nextInt((challenge.getDifficultyId() + 1) * 2) + 1;
			ItemStack reward = null;

			for (int i = 0; i < extra && i < 5; i++)
			{
				EntityItem item = new EntityItem(world);

				try
				{
					reward = challenge.getDifficultyId() > 2 ? ((EntityDrop) WeightedRandom.getRandomItem(itemRand, Arrays.asList(rewards))).getStack():
						(challenge.getDifficultyId() > 0 ? ((EntityDrop) WeightedRandom.getRandomItem(itemRand, Arrays.asList(cheapRewards))).getStack() :
							((EntityDrop) WeightedRandom.getRandomItem(itemRand, Arrays.asList(badRewards))).getStack());
					item.setEntityItemStack(reward.copy());
					item.setPosition(player.posX + itemRand.nextDouble() - itemRand.nextDouble(), player.posY + 0.6D, player.posZ  + itemRand.nextDouble() - itemRand.nextDouble());
					world.spawnEntityInWorld(item);
				}
				catch (Exception e)
				{
					TragicMC.logError("Challenge errored while rewarding, silently catching error and continuing", e);
					continue;
				}
				if (i > 3 && itemRand.nextInt(4) == 0) break;
			}
			stack.stackSize--;
		}
		else
		{
			if (stack.hasTagCompound()) player.addChatMessage(new ChatComponentText(Challenge.getNameFromID(stack.getTagCompound().getInteger(Challenge.CHALLENGE_ID))+ " is in progress!"));
		}

		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (stack.getItemDamage() == 250)
		{
			if (stack.hasTagCompound() && stack.getTagCompound().hasKey(Challenge.CHALLENGE_ID))
			{
				Challenge challenge = Challenge.getChallengeFromID(stack.getTagCompound().getInteger(Challenge.CHALLENGE_ID));
				if (challenge == null) return;
				EnumChatFormatting format = challenge.getDifficultyId() == 1 ? EnumChatFormatting.AQUA : (challenge.getDifficultyId() == 2 ? EnumChatFormatting.BLUE : (challenge.getDifficultyId() == 3 ? EnumChatFormatting.GOLD : EnumChatFormatting.WHITE));
				par2List.add("Challenge: " + format + Challenge.getNameFromID(challenge.getChallengeId()));
				String diff = challenge.getDifficultyId() == 0 ? "Easy" : (challenge.getDifficultyId() == 1 ? "Medium" : (challenge.getDifficultyId() == 2 ? "Hard" : "Harsh"));
				par2List.add("Difficulty: " + format + diff);
			}
			par2List.add(EnumChatFormatting.GOLD + "Challenge complete!");
			par2List.add(EnumChatFormatting.WHITE + "What are you doing reading this, get your reward!");
		}
		else if (stack.getItemDamage() == 0)
		{
			par2List.add(EnumChatFormatting.WHITE + "An inactive Challenge Scroll.");
			par2List.add(EnumChatFormatting.RESET + "Right-Click to start a Challenge!");
		}
		else if (stack.hasTagCompound() && stack.getTagCompound().hasKey(Challenge.CHALLENGE_ID))
		{
			Challenge challenge = Challenge.getChallengeFromID(stack.getTagCompound().getInteger(Challenge.CHALLENGE_ID));
			if (challenge == null) return;
			EnumChatFormatting format = challenge.getDifficultyId() == 1 ? EnumChatFormatting.AQUA : (challenge.getDifficultyId() == 2 ? EnumChatFormatting.BLUE : (challenge.getDifficultyId() == 3 ? EnumChatFormatting.GOLD : EnumChatFormatting.WHITE));
			par2List.add("Challenge: " + format + Challenge.getNameFromID(challenge.getChallengeId()));
			String diff = challenge.getDifficultyId() == 0 ? "Easy" : (challenge.getDifficultyId() == 1 ? "Medium" : (challenge.getDifficultyId() == 2 ? "Hard" : "Harsh"));
			par2List.add("Difficulty: " + format + diff);
			LoreHelper.splitDesc(par2List, Challenge.getDesc(challenge.getChallengeId()), 32, EnumChatFormatting.WHITE);
			par2List.add("Progress: " + stack.getTagCompound().getInteger(Challenge.CHALLENGE_PROG) + "/ " + challenge.getRequirement());
			if (challenge.isLocationBased() && stack.getTagCompound().hasKey(Challenge.CHALLENGE_LOC)) par2List.add("Proper location: " + (stack.getTagCompound().getBoolean(Challenge.CHALLENGE_LOC) ? "Yes" : "No"));
			if (challenge.isTimed && stack.getTagCompound().hasKey(Challenge.CHALLENGE_TIME)) par2List.add("Time remaining: " + (stack.getTagCompound().getInteger(Challenge.CHALLENGE_TIME)));
		}
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
		return oldStack == null || oldStack.getItemDamage() != newStack.getItemDamage();
    }

	public static ItemStack[] getSpawnEggsMiniBoss()
	{
		ItemStack[] stacks = new ItemStack[] {
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.Jarra") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.Jarra")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.Magmox") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.Magmox")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.Kragul") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.Kragul")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.MegaCryse") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.MegaCryse")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.StinKing") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.StinKing")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.GreaterStin") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.GreaterStin")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.StinQueen") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.StinQueen")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.VoxStellarum") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.VoxStellarum")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.VolatileFusea") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.VolatileFusea"))};
		return stacks;
	}

	public static ItemStack[] getSpawnEggsBoss()
	{
		ItemStack[] stacks = new ItemStack[] {
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.Apis") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.Apis")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.Polaris") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.Polaris")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.Yeti") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.Yeti")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.TimeController") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.TimeController")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.Kitsune") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.Kitsune")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.DeathReaper") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.Enyvil")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.Enyvil") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.Kitsune")),
				new ItemStack(TragicItems.SpawnEgg, 1, TragicEntityList.getIDFromString("TragicMC.Claymation") == -1 ? 0 : TragicEntityList.getIDFromString("TragicMC.Claymation"))};
		return stacks;
	}

	public static ItemStack[] getAmulets()
	{
		ItemStack[] stacks = new ItemStack[] {
				new ItemStack(TragicItems.ApisAmulet), new ItemStack(TragicItems.CreeperAmulet), new ItemStack(TragicItems.BlacksmithAmulet), new ItemStack(TragicItems.KitsuneAmulet),
				new ItemStack(TragicItems.ZombieAmulet), new ItemStack(TragicItems.SkeletonAmulet), new ItemStack(TragicItems.SunkenAmulet), new ItemStack(TragicItems.PeaceAmulet),
				new ItemStack(TragicItems.ChickenAmulet), new ItemStack(TragicItems.ClaymationAmulet), new ItemStack(TragicItems.YetiAmulet), new ItemStack(TragicItems.MartyrAmulet),
				new ItemStack(TragicItems.EndermanAmulet)};
		return stacks;
	}

	public static ItemStack[] getEpicWeapons()
	{
		ItemStack[] stacks = new ItemStack[] {
				new ItemStack(TragicItems.Titan), new ItemStack(TragicItems.Paranoia), new ItemStack(TragicItems.Butcher),
				new ItemStack(TragicItems.Thardus), new ItemStack(TragicItems.DragonFang), new ItemStack(TragicItems.Splinter),
				new ItemStack(TragicItems.SilentHellraiser)};
		return stacks;
	}
}
