package tragicneko.tragicmc.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.doomsday.Doomsday;

public class WeaponSilentHellraiser extends TragicWeapon {

	public WeaponSilentHellraiser(ToolMaterial material, Doomsday dday) {
		super(material, dday);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par, boolean flag)
	{
		super.onUpdate(stack, world, entity, par, flag);
		if (stack.hasTagCompound()) stack.getTagCompound().setTag("ench", new NBTTagList());
	}
}
