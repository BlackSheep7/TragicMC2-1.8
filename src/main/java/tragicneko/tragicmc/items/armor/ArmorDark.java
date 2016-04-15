package tragicneko.tragicmc.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.TragicPotion;
import tragicneko.tragicmc.doomsday.Doomsday;

public class ArmorDark extends TragicArmor {

	private static final String texture = "tragicmc:textures/armor/DarkArmor.png";

	public ArmorDark(ArmorMaterial material, int armorType, Doomsday dday) {
		super(material, armorType, dday);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if (!world.isRemote)
		{
			Boolean flag1 = false;
			Boolean flag2 = false;
			Boolean flag3 = false;
			Boolean flag4 = false;

			for (int a = 1; a < 5; a++)
			{
				if (player.getEquipmentInSlot(a) != null)
				{
					Item armor = player.getEquipmentInSlot(a).getItem();

					if (armor == TragicItems.DarkHelm) flag1 = true;
					if (armor == TragicItems.DarkPlate) flag2 = true;
					if (armor == TragicItems.DarkLegs) flag3 = true;
					if (armor == TragicItems.DarkBoots) flag4 = true;
				}
			}

			if (flag1 && flag2 && flag3 && flag4)
			{
				if (TragicConfig.getBoolean("allowImmunity") && player.ticksExisted % 60 == 0) player.addPotionEffect(new PotionEffect(TragicPotion.Immunity.id, 600));
				if (TragicConfig.getBoolean("allowFear") && player.isPotionActive(TragicPotion.Fear)) player.removePotionEffect(TragicPotion.Fear.id);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, int slot, net.minecraft.client.model.ModelBiped model)
	{
		net.minecraft.client.model.ModelBiped model2 = tragicneko.tragicmc.proxy.ClientProxy.modelsDark[4 - slot];
		model2.setModelAttributes(model);
		return TragicConfig.getBoolean("allowArmorModels") ? model2 : null;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return TragicConfig.getBoolean("allowArmorModels") ? texture : (slot == 2 ? "tragicmc:textures/armor/Dark2.png" : "tragicmc:textures/armor/Dark1.png");
	}
}
