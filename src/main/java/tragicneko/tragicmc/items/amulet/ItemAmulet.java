package tragicneko.tragicmc.items.amulet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.properties.PropertyAmulets;
import tragicneko.tragicmc.proxy.ClientProxy;
import tragicneko.tragicmc.util.AmuletHelper;

public class ItemAmulet extends Item {

	public static final int COLOR_WHITE = 0xFFFFFF;
	public static final int COLOR_BLACK = 0x000000;
	
	public static Set<ItemAmulet> cursedAmulets = new HashSet<ItemAmulet>();
	public static Set<ItemAmulet> epicAmulets = new HashSet<ItemAmulet>();
	
	private final int chainColor;
	private final int amuletColor;
	
	public final EnumAmuletType amuletType;
	public final String amuletName;

	public ItemAmulet(String name, EnumAmuletType type, int chain, int amulet)
	{
		this.amuletName = name;
		this.amuletType = type;
		this.chainColor = chain;
		this.amuletColor = amulet;
		this.setUnlocalizedName("tragicmc.amulet" + name);
		this.setCreativeTab(TragicMC.Survival);
		this.setMaxStackSize(1);
		
		if (type == EnumAmuletType.CURSED) cursedAmulets.add(this);
		else if (type == EnumAmuletType.EPIC) epicAmulets.add(this);
	}
	
	public ItemAmulet(String name, EnumAmuletType type)
	{
		this(name, type, COLOR_WHITE, COLOR_BLACK);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		if (this.amuletType == EnumAmuletType.CURSED || this.amuletType == EnumAmuletType.EPIC) return true;
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") && stack.getTagCompound().getByte("amuletLevel") >= 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
		if (this.amuletType == EnumAmuletType.CURSED || this.amuletType == EnumAmuletType.EPIC) return EnumRarity.EPIC;
		byte i = stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") ? stack.getTagCompound().getByte("amuletLevel") : getDefaultLevels(this.amuletType);

		switch (i)
		{
		case 1:
			return EnumRarity.COMMON;
		case 2:
			return EnumRarity.UNCOMMON;
		case 3:
			return EnumRarity.RARE;
		}
		return EnumRarity.COMMON;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		byte i = stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") ? stack.getTagCompound().getByte("amuletLevel") : getDefaultLevels(this.amuletType);
		String specialName = StatCollector.translateToLocal(this.amuletName.toLowerCase() + i + ".special");
		par2List.add(EnumChatFormatting.RED + "\"" + specialName + "\"");

		String s = "Amulet Level: ";
		String s1 = getFormatFromLevel(i) + s + (this.amuletType == EnumAmuletType.EPIC ? "Epic" : (this.amuletType == EnumAmuletType.CURSED ? "Cursed" : i));
		if (s1 != null) par2List.add(s1);
		
		par2List.add("");
		par2List.add("To equip this, either right-click with it in your hand");
		par2List.add("or hit " + Keyboard.getKeyName(ClientProxy.openAmuletGui.getKeyCode()) + " to open the Amulet Gui");
	}

	public EnumChatFormatting getFormatFromLevel(final byte i)
	{
		if (this.amuletType == EnumAmuletType.CURSED) return EnumChatFormatting.DARK_RED;
		if (this.amuletType == EnumAmuletType.EPIC) return EnumChatFormatting.GOLD; 
		return i == 1 ? EnumChatFormatting.AQUA : (i == 2 ? EnumChatFormatting.BLUE : (i == 3 ? EnumChatFormatting.DARK_BLUE : EnumChatFormatting.WHITE));
	}

	@Override
	public int getMaxDamage()
	{
		return 300;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int par2)
	{
		if (par2 == 1)
		{
			return this.amuletColor;
		}
		else
		{
			return this.chainColor;
		}
	}

	public byte getAmuletLevel(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("amuletLevel") ? stack.getTagCompound().getByte("amuletLevel") : 0;
	}

	public String getAmuletName()
	{
		return this.amuletName;
	}

	public static byte getDefaultLevels(EnumAmuletType type)
	{
		return type == EnumAmuletType.CURSED || type == EnumAmuletType.EPIC ? (byte) 3 : 1;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (world.isRemote || !(entity instanceof EntityPlayer)) return;

		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		if (!stack.getTagCompound().hasKey("amuletLevel")) stack.getTagCompound().setByte("amuletLevel", getDefaultLevels(this.amuletType));
		if (TragicConfig.getBoolean("allowAmuletModifiers") && !stack.getTagCompound().hasKey("AttributeModifiers", 9)) applyModifiersToItemStack(stack);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		PropertyAmulets amu = PropertyAmulets.get(player);

		for (int i = 0; i < amu.inventory.getSizeInventory(); i++)
		{
			if (amu.inventory.getStackInSlot(i) == null)
			{
				if (amu.getSlotsOpen() == 1 && (i == 1 || i == 2)) continue;
				if (amu.getSlotsOpen() == 2 && i == 2) continue;
				if (!world.isRemote) amu.inventory.setInventorySlotContents(i, stack.copy());
				stack.stackSize--;
				break;
			}
		}
		return stack;
	}

	public static void applyModifiersToItemStack(ItemStack stack)
	{
		NBTTagList taglist = new NBTTagList();
		NBTTagCompound tag;

		IAttribute atr = AmuletHelper.getRandomAttribute();
		AttributeModifier mod = AmuletHelper.getRandomModifier(atr);
		if (stack == null || !(stack.getItem() instanceof ItemAmulet)) return;
		ItemAmulet amulet = (ItemAmulet) stack.getItem();

		if (itemRand.nextInt(100) <= TragicConfig.getInt("amuletModChance") || amulet.amuletType == EnumAmuletType.CURSED)
		{
			stack.getTagCompound().setTag("AttributeModifiers", taglist);
			return;
		}

		if (mod != null)
		{
			tag = AmuletHelper.writeAttributeModifierToNBT(atr.getAttributeUnlocalizedName(), mod);
			taglist.appendTag(tag);
		}

		atr = AmuletHelper.getRandomAttribute();
		mod = AmuletHelper.getRandomModifier(atr);

		if (itemRand.nextInt(100) <= TragicConfig.getInt("amuletModChance2"))
		{
			stack.getTagCompound().setTag("AttributeModifiers", taglist);
			return;
		}

		if (mod != null)
		{
			tag = AmuletHelper.writeAttributeModifierToNBT(atr.getAttributeUnlocalizedName(), mod);
			taglist.appendTag(tag);
		}

		atr = AmuletHelper.getRandomAttribute();
		mod = AmuletHelper.getRandomModifier(atr);

		if (itemRand.nextInt(100) <= TragicConfig.getInt("amuletModChance3"))
		{
			stack.getTagCompound().setTag("AttributeModifiers", taglist);
			return;
		}

		if (mod != null)
		{
			tag = AmuletHelper.writeAttributeModifierToNBT(atr.getAttributeUnlocalizedName(), mod);
			taglist.appendTag(tag);
		}

		stack.getTagCompound().setTag("AttributeModifiers", taglist);
	}
	
	/**
	 * To be used by the Amulet Events class to call each Amulet on update, may or may not be used by each Amulet
	 * @param amu
	 * @param player
	 * @param world
	 * @param slot
	 * @param level
	 */
	public void onAmuletUpdate(final PropertyAmulets amu, final EntityPlayer player, final World world, final byte slot, final byte level) {}
	
	/**
	 * Shortcut method to damage an Amulet
	 * @param amu
	 * @param slot
	 * @param level
	 */
	protected final void damageAmulet(final PropertyAmulets amu, final byte slot, final byte level)
	{
		amu.damageStackInSlot(slot, 4 - level);
	}

	public static class AmuletModifier extends SharedMonsterAttributes {
		public static final IAttribute reach = (new RangedAttribute(null, "tragicmc.reach", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Reach").setShouldWatch(true);
		public static final IAttribute jumpHeight = (new RangedAttribute(null, "tragicmc.jumpHeight", 1.4, 0.0, Double.MAX_VALUE)).setDescription("Jump Height").setShouldWatch(true);
		public static final IAttribute resistance = (new RangedAttribute(null, "tragicmc.resistance", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Resistance").setShouldWatch(true);
		public static final IAttribute luck = (new RangedAttribute(null, "tragicmc.luck", 0.0, -Double.MAX_VALUE, Double.MAX_VALUE)).setDescription("Luck").setShouldWatch(true);
	}
	
	public static enum EnumAmuletType {
		NORMAL,
		CURSED,
		EPIC;
	}
}
