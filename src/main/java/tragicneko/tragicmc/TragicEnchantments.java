package tragicneko.tragicmc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.enchantment.EnchantmentAgility;
import tragicneko.tragicmc.enchantment.EnchantmentArmorExtra;
import tragicneko.tragicmc.enchantment.EnchantmentCombustion;
import tragicneko.tragicmc.enchantment.EnchantmentDamageBoost;
import tragicneko.tragicmc.enchantment.EnchantmentLuminescence;
import tragicneko.tragicmc.enchantment.EnchantmentMultiply;
import tragicneko.tragicmc.enchantment.EnchantmentRange;
import tragicneko.tragicmc.enchantment.EnchantmentRuneWalker;
import tragicneko.tragicmc.enchantment.EnchantmentUnbreakable;
import tragicneko.tragicmc.enchantment.EnchantmentVeteran;
import tragicneko.tragicmc.enchantment.EnchantmentWeaponExtra;

public class TragicEnchantments {

	public static Enchantment Decay, Absolve, Slay, Vampirism, Leech, Consume, Distract, Combustion, Multiply, RuneBreak, Reach, Unbreakable, Rust, Veteran;
	public static Enchantment Ignition, Paralysis, Toxicity, Elasticity, DeathTouch, Agility, RuneWalker, Luminescence;

	public static void load()
	{
		if (TragicConfig.getBoolean("allowDecay")) Decay = (new EnchantmentDamageBoost(TragicConfig.getInt("decayID"), new ResourceLocation("decay"), TragicConfig.getInt("decayWeight"), 0));
		if (TragicConfig.getBoolean("allowAbsolve")) Absolve = (new EnchantmentDamageBoost(TragicConfig.getInt("absolveID"), new ResourceLocation("absolve"), TragicConfig.getInt("absolveWeight"), 1));
		if (TragicConfig.getBoolean("allowSlay")) Slay = (new EnchantmentDamageBoost(TragicConfig.getInt("slayID"), new ResourceLocation("slay"), TragicConfig.getInt("slayWeight"), 2));
		if (TragicConfig.getBoolean("allowVampirism")) Vampirism = (new EnchantmentWeaponExtra(TragicConfig.getInt("vampirismID"), new ResourceLocation("vampirism"), TragicConfig.getInt("vampirismWeight"), 0));
		if (TragicConfig.getBoolean("allowLeech")) Leech = (new EnchantmentWeaponExtra(TragicConfig.getInt("leechID"), new ResourceLocation("leech"), TragicConfig.getInt("leechWeight"), 1));
		if (TragicConfig.getBoolean("allowConsume")) Consume = (new EnchantmentWeaponExtra(TragicConfig.getInt("consumeID"), new ResourceLocation("consume"), TragicConfig.getInt("consumeWeight"), 2));
		if (TragicConfig.getBoolean("allowDistract")) Distract = (new EnchantmentWeaponExtra(TragicConfig.getInt("distractID"), new ResourceLocation("distract"), TragicConfig.getInt("distractWeight"), 3));
		if (TragicConfig.getBoolean("allowCombustion")) Combustion = (new EnchantmentCombustion(TragicConfig.getInt("combustionID"), new ResourceLocation("combustion"), TragicConfig.getInt("combustionWeight"), EnumEnchantmentType.DIGGER));
		if (TragicConfig.getBoolean("allowMultiply")) Multiply = (new EnchantmentMultiply(TragicConfig.getInt("multiplyID"), new ResourceLocation("multiply"), TragicConfig.getInt("multiplyWeight"), EnumEnchantmentType.BOW));
		if (TragicConfig.getBoolean("allowRuneBreak")) RuneBreak = (new EnchantmentDamageBoost(TragicConfig.getInt("runeBreakID"), new ResourceLocation("runeBreak"), TragicConfig.getInt("runeBreakWeight"), 3));
		if (TragicConfig.getBoolean("allowReach")) Reach = (new EnchantmentRange(TragicConfig.getInt("reachID"), new ResourceLocation("reach"), TragicConfig.getInt("reachWeight"), EnumEnchantmentType.WEAPON));
		if (TragicConfig.getBoolean("allowUnbreakable")) Unbreakable = (new EnchantmentUnbreakable(TragicConfig.getInt("unbreakableID"), new ResourceLocation("unbreakable"), TragicConfig.getInt("unbreakableWeight"), EnumEnchantmentType.BREAKABLE));
		if (TragicConfig.getBoolean("allowRust")) Rust = (new EnchantmentWeaponExtra(TragicConfig.getInt("rustID"), new ResourceLocation("rust"), TragicConfig.getInt("rustWeight"), 4));
		if (TragicConfig.getBoolean("allowVeteran")) Veteran = (new EnchantmentVeteran(TragicConfig.getInt("veteranID"), new ResourceLocation("veteran"), TragicConfig.getInt("veteranWeight"), EnumEnchantmentType.DIGGER));

		if (TragicConfig.getBoolean("allowIgnition"))Ignition = (new EnchantmentArmorExtra(TragicConfig.getInt("ignitionID"), new ResourceLocation("ignition"), TragicConfig.getInt("ignitionWeight"), 0));
		if (TragicConfig.getBoolean("allowParalysis"))Paralysis = (new EnchantmentArmorExtra(TragicConfig.getInt("paralysisID"), new ResourceLocation("paralysis"), TragicConfig.getInt("paralysisWeight"), 1));
		if (TragicConfig.getBoolean("allowToxicity"))Toxicity = (new EnchantmentArmorExtra(TragicConfig.getInt("toxicityID"), new ResourceLocation("toxicity"), TragicConfig.getInt("toxicityWeight"), 2));
		if (TragicConfig.getBoolean("allowElasticity"))Elasticity = (new EnchantmentArmorExtra(TragicConfig.getInt("elasticityID"), new ResourceLocation("elasticity"), TragicConfig.getInt("elasticityWeight"), 3));
		if (TragicConfig.getBoolean("allowDeathTouch"))DeathTouch = (new EnchantmentArmorExtra(TragicConfig.getInt("deathTouchID"), new ResourceLocation("deathTouch"), TragicConfig.getInt("deathTouchWeight"), 4));
		if (TragicConfig.getBoolean("allowAgility")) Agility = (new EnchantmentAgility(TragicConfig.getInt("agilityID"), new ResourceLocation("agility"), TragicConfig.getInt("agilityWeight"), EnumEnchantmentType.ARMOR));
		if (TragicConfig.getBoolean("allowRuneWalker")) RuneWalker = (new EnchantmentRuneWalker(TragicConfig.getInt("runeWalkerID"), new ResourceLocation("runeWalker"), TragicConfig.getInt("runeWalkerID"), EnumEnchantmentType.ARMOR));
		if (TragicConfig.getBoolean("allowLuminescence")) Luminescence = (new EnchantmentLuminescence(TragicConfig.getInt("luminescenceID"), new ResourceLocation("luminescence"), TragicConfig.getInt("luminescenceID"), EnumEnchantmentType.ALL));
	}
}
