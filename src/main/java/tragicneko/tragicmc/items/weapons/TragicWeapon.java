package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.LoreHelper;
import tragicneko.tragicmc.util.LoreHelper.EnchantEntry;
import tragicneko.tragicmc.util.LoreHelper.Lore;
import tragicneko.tragicmc.util.LoreHelper.LoreEntry;

public class TragicWeapon extends ItemSword {

	protected final Doomsday doomsday;
	protected Doomsday doomsday2;
	public Item.ToolMaterial material;
	public float ascensionLevel;

	public TragicWeapon(ToolMaterial material, Doomsday dday) {
		super(material);
		this.doomsday = dday;
		this.setCreativeTab(TragicMC.Survival);
		this.material = material;
	}

	public TragicWeapon(ToolMaterial material, Doomsday dday, Doomsday dday2)
	{
		this(material, dday);
		this.doomsday2 = dday2;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		int rarity = stack.hasTagCompound() && stack.getTagCompound().hasKey("tragicLoreRarity") ? stack.getTagCompound().getInteger("tragicLoreRarity") : 0;
		return EnumRarity.values()[rarity];
	}

	public Doomsday getDoomsday()
	{
		return this.doomsday;
	}

	public EnumDoomType doomsdayType()
	{
		return this.doomsday != null ? this.doomsday.doomsdayType : EnumDoomType.INFLUENCE;
	}
	
	public TragicWeapon setAscensionLevel(float f)
	{
		this.ascensionLevel = f;
		return this;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (TragicConfig.getBoolean("allowRandomWeaponLore") && LoreHelper.getRarityFromStack(stack) >= 0)
		{
			String lore = LoreHelper.getDescFromStack(stack);

			if (lore != null)
			{
				LoreHelper.splitDesc(par2List, lore, 32, LoreHelper.getFormatForRarity(LoreHelper.getRarityFromStack(stack)));
				par2List.add(""); //extra space
			}
		}

		if (TragicConfig.getBoolean("allowDoomsdays") && this.doomsday != null)
		{
			EnumChatFormatting format;
			String d = this.doomsday2 != null ? "Doomsdays:" : "Doomsday:";
			par2List.add(EnumChatFormatting.WHITE + d);

			if (this.doomsday2 != null)
			{
				format = doomsday2.getDoomsdayType().getFormat();
				par2List.add(format + doomsday2.getLocalizedType() + ": " + doomsday2.getLocalizedName());
				par2List.add(EnumChatFormatting.ITALIC + "Cost/Cooldown: " + EnumChatFormatting.GOLD + doomsday2.getScaledDoomRequirement(par2EntityPlayer.worldObj) +
						EnumChatFormatting.RESET + " / " + EnumChatFormatting.DARK_AQUA +
						doomsday2.getScaledCooldown(par2EntityPlayer.worldObj.getDifficulty()));
				par2List.add(""); //extra space in between
			}

			format = doomsday.getDoomsdayType().getFormat();
			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());
			par2List.add(EnumChatFormatting.ITALIC + "Cost/Cooldown: " + EnumChatFormatting.GOLD + doomsday.getScaledDoomRequirement(par2EntityPlayer.worldObj) +
					EnumChatFormatting.RESET + " / " + EnumChatFormatting.DARK_AQUA +
					doomsday.getScaledCooldown(par2EntityPlayer.worldObj.getDifficulty()));
			par2List.add(""); //extra space in between
		}
		
		if (this.ascensionLevel > 0F)
		{
			par2List.add(EnumChatFormatting.LIGHT_PURPLE + "Ascension Level: " + this.ascensionLevel);
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (world.isRemote || !(entity instanceof EntityPlayer)) return;
		updateAsWeapon(stack, world, entity, numb, flag);
	}

	/**
	 * Allow any item class to update as a weapon and not require extending the main TragicWeapon class, this applies and decrements cooldown and sets up a weapon's lore
	 * @param stack
	 * @param world
	 * @param entity
	 * @param numb
	 * @param flag
	 */
	public static void updateAsWeapon(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (world.isRemote || stack == null) return;
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());

		if (!stack.getTagCompound().hasKey("cooldown")) stack.getTagCompound().setInteger("cooldown", 0);
		if (getStackCooldown(stack) > 0) setStackCooldown(stack, getStackCooldown(stack) - 1);

		if (!TragicConfig.getBoolean("allowRandomWeaponLore") || stack.getItem() == null) return;

		ResourceLocation rl = Item.itemRegistry.getNameForObject(stack.getItem());
		LoreEntry entry = LoreHelper.getLoreEntry(rl.toString());
		if (entry == null) return;
		Lore lore = entry.getRandomLore();
		if (lore == null) return;

		if (!stack.getTagCompound().hasKey("tragicLoreRarity")) stack.getTagCompound().setByte("tragicLoreRarity", Byte.valueOf((byte) lore.getRarity()));
		if (!stack.getTagCompound().hasKey("tragicLoreDesc")) stack.getTagCompound().setString("tragicLoreDesc", lore.getDesc());

		int rarity = stack.getTagCompound().getByte("tragicLoreRarity");
		lore = entry.getLoreOfRarity(rarity);

		if (!stack.isItemEnchanted() && lore != null)
		{
			EnchantEntry[] enchants = entry.getEnchantmentForRarity(rarity);
			if (enchants == null) return;

			for (EnchantEntry e : enchants)
			{
				if (e != null && e.getEnchantment() != null && e.getEnchantLevel() > 0) stack.addEnchantment(e.getEnchantment(), e.getEnchantLevel());
			}
		}
	}
	
	/**
	 * Allows any generic itemstack to update on tick to apply lore and enchantments from it's lore entry, if any
	 * @param stack
	 */
	public static void updateGenericallyAsWeapon(ItemStack stack) {
		if (!TragicConfig.getBoolean("allowRandomWeaponLore") || stack.getItem() == null) return;

		ResourceLocation rl = Item.itemRegistry.getNameForObject(stack.getItem());
		LoreEntry entry = LoreHelper.getLoreEntry(rl.toString());
		if (entry == null) return;
		Lore lore = entry.getRandomLore();
		if (lore == null) return;
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());

		if (!stack.getTagCompound().hasKey("tragicLoreRarity")) stack.getTagCompound().setByte("tragicLoreRarity", Byte.valueOf((byte) lore.getRarity()));
		if (!stack.getTagCompound().hasKey("tragicLoreDesc")) stack.getTagCompound().setString("tragicLoreDesc", lore.getDesc());

		int rarity = stack.getTagCompound().getByte("tragicLoreRarity");
		lore = entry.getLoreOfRarity(rarity);

		if (!stack.isItemEnchanted() && lore != null)
		{
			EnchantEntry[] enchants = entry.getEnchantmentForRarity(rarity);
			if (enchants == null) return;

			for (EnchantEntry e : enchants)
			{
				if (e != null && e.getEnchantment() != null && e.getEnchantLevel() > 0) stack.addEnchantment(e.getEnchantment(), e.getEnchantLevel());
			}
		}
	}

	public static boolean canUseAbility(PropertyDoom doom, int rq)
	{
		return doom != null && TragicConfig.getBoolean("allowNonDoomsdayAbilities") && doom.getCurrentCooldown() == 0 && doom.getCurrentDoom() >= rq;
	}

	public static void setStackCooldown(ItemStack stack, int i)
	{
		if (!stack.hasTagCompound()) return;
		stack.getTagCompound().setInteger("cooldown", i);
	}

	public static int getStackCooldown(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("cooldown") ? stack.getTagCompound().getInteger("cooldown") : 0;
	}

	public Doomsday getSecondaryDoomsday()
	{
		return this.doomsday2;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2)
	{
		if (entity instanceof EntityPlayer && !TragicConfig.getBoolean("allowPvP")) return false;
		return super.hitEntity(stack, entity, entity2);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
    {
        return true;
    }
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
		return oldStack == null || !(oldStack.getItem() instanceof TragicWeapon);
    }
}
