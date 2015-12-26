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
		if (TragicConfig.allowDecay) Decay = (new EnchantmentDamageBoost(TragicConfig.idDecay, new ResourceLocation("decay"), 8, 0));
		if (TragicConfig.allowAbsolve) Absolve = (new EnchantmentDamageBoost(TragicConfig.idAbsolve, new ResourceLocation("absolve"), 1, 1));
		if (TragicConfig.allowSlay) Slay = (new EnchantmentDamageBoost(TragicConfig.idSlay, new ResourceLocation("slay"), 8, 2));
		if (TragicConfig.allowVampirism) Vampirism = (new EnchantmentWeaponExtra(TragicConfig.idVampirism, new ResourceLocation("vampirism"), 1, 0));
		if (TragicConfig.allowLeech) Leech = (new EnchantmentWeaponExtra(TragicConfig.idLeech, new ResourceLocation("leech"), 1, 1));
		if (TragicConfig.allowConsume) Consume = (new EnchantmentWeaponExtra(TragicConfig.idConsume, new ResourceLocation("consume"), 1, 2));
		if (TragicConfig.allowDistract) Distract = (new EnchantmentWeaponExtra(TragicConfig.idDistract, new ResourceLocation("distract"), 8, 3));
		if (TragicConfig.allowCombustion) Combustion = (new EnchantmentCombustion(TragicConfig.idCombustion, new ResourceLocation("combustion"), 1, EnumEnchantmentType.DIGGER));
		if (TragicConfig.allowMultiply) Multiply = (new EnchantmentMultiply(TragicConfig.idMultiply, new ResourceLocation("multiply"), 1, EnumEnchantmentType.BOW));
		if (TragicConfig.allowRuneBreak) RuneBreak = (new EnchantmentDamageBoost(TragicConfig.idRuneBreak, new ResourceLocation("rune_break"), 4, 3));
		if (TragicConfig.allowReach) Reach = (new EnchantmentRange(TragicConfig.idReach, new ResourceLocation("reach"), 6, EnumEnchantmentType.WEAPON));
		if (TragicConfig.allowUnbreakable) Unbreakable = (new EnchantmentUnbreakable(TragicConfig.idUnbreakable, new ResourceLocation("unbreakable"), 1, EnumEnchantmentType.BREAKABLE));
		if (TragicConfig.allowRust) Rust = (new EnchantmentWeaponExtra(TragicConfig.idRust, new ResourceLocation("rust"), 6, 4));
		if (TragicConfig.allowVeteran) Veteran = (new EnchantmentVeteran(TragicConfig.idVeteran, new ResourceLocation("veteran"), 1, EnumEnchantmentType.DIGGER));

		if (TragicConfig.allowIgnition)Ignition = (new EnchantmentArmorExtra(TragicConfig.idIgnition, new ResourceLocation("ignition"), 6, 0));
		if (TragicConfig.allowParalysis)Paralysis = (new EnchantmentArmorExtra(TragicConfig.idParalysis, new ResourceLocation("paralysis"), 1, 1));
		if (TragicConfig.allowToxicity)Toxicity = (new EnchantmentArmorExtra(TragicConfig.idToxicity, new ResourceLocation("toxicity"), 4, 2));
		if (TragicConfig.allowElasticity)Elasticity = (new EnchantmentArmorExtra(TragicConfig.idElasticity, new ResourceLocation("elasticity"), 10, 3));
		if (TragicConfig.allowDeathTouch)DeathTouch = (new EnchantmentArmorExtra(TragicConfig.idDeathTouch, new ResourceLocation("death_touch"), 1, 4));
		if (TragicConfig.allowAgility) Agility = (new EnchantmentAgility(TragicConfig.idAgility, new ResourceLocation("agility"), 2, EnumEnchantmentType.ARMOR));
		if (TragicConfig.allowRuneWalker) RuneWalker = (new EnchantmentRuneWalker(TragicConfig.idRuneWalker, new ResourceLocation("rune_walker"), 2, EnumEnchantmentType.ARMOR));
		if (TragicConfig.allowLuminescence) Luminescence = (new EnchantmentLuminescence(TragicConfig.idLuminescence, new ResourceLocation("luminescence"), 1, EnumEnchantmentType.ALL));
	}
}
