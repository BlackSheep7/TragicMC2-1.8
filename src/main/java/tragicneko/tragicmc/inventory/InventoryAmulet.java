package tragicneko.tragicmc.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import tragicneko.tragicmc.items.amulet.ItemAmulet;

public class InventoryAmulet extends InventoryBasic {

	private static final String invName = "Amulet Inventory";
	private static final String tagName = "InvAmulet";

	public static final int invSize = 28;
	public final EntityPlayer player;

	public InventoryAmulet(EntityPlayer player)
	{
		super(invName, false, invSize);
		this.player = player;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		NBTTagCompound tag = new NBTTagCompound();
		readFromNBT(tag);
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return stack != null && stack.getItem() instanceof ItemAmulet;
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				getStackInSlot(i).writeToNBT(item);
				items.appendTag(item);
			}
		}

		compound.setTag(tagName, items);
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		if (compound == null) return;

		NBTTagList items = compound.getTagList(tagName, compound.getId());

		if (items == null) return;

		for (int i = 0; i < items.tagCount(); ++i)
		{
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			if (slot >= 0 && slot < getSizeInventory())
			{
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}

	public void dropAllAmulets() {
		int i;

		for (i = 0; i < this.getSizeInventory(); ++i)
		{
			if (this.getStackInSlot(i) != null)
			{
				this.player.dropItem(this.getStackInSlot(i), true, false);
				this.setInventorySlotContents(i, null);;
			}
		}
	}
}
