package tragicneko.tragicmc.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday;

public class ArmorOverlord extends TragicArmor {

	private static final String texture = "tragicmc:textures/armor/OverlordArmor.png";

	public ArmorOverlord(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if (world.isRemote) return;

		Boolean flag1 = false;
		Boolean flag2 = false;
		Boolean flag3 = false;
		Boolean flag4 = false;

		for (int a = 1; a < 5; a++)
		{
			if (player.getEquipmentInSlot(a) != null)
			{
				Item armor = player.getEquipmentInSlot(a).getItem();

				if (armor == TragicItems.OverlordHelm) flag1 = true;
				if (armor == TragicItems.OverlordPlate) flag2 = true;
				if (armor == TragicItems.OverlordLegs) flag3 = true;
				if (armor == TragicItems.OverlordBoots) flag4 = true;
			}
		}

		if (flag1 && flag2 && flag3 && flag4)
		{
			if (TragicConfig.getBoolean("allowCripple") && player.isPotionActive(TragicPotion.Cripple)) player.removePotionEffect(TragicPotion.Cripple.id);
			if (player.isPotionActive(Potion.weakness)) player.removePotionEffect(Potion.weakness.id);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, int slot, net.minecraft.client.model.ModelBiped model)
	{
		net.minecraft.client.model.ModelBiped model2 = tragicneko.tragicmc.proxy.ClientProxy.modelsOverlord[4 - slot];
		model2.setModelAttributes(model);
		return TragicConfig.getBoolean("allowArmorModels") ? model2 : null;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return TragicConfig.getBoolean("allowArmorModels") ? texture : (slot == 2 ? "tragicmc:textures/armor/Overlord2.png" : "tragicmc:textures/armor/Overlord1.png");
	}
}
