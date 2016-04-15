package tragicneko.tragicmc.items.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicPotion;

public class ItemEnchantedSushi extends ItemFood {

	public ItemEnchantedSushi(int p_i45340_1_, boolean p_i45340_2_) {
		super(p_i45340_1_, p_i45340_2_);
		if (TragicConfig.getBoolean("allowImmunity")) this.setPotionEffect(TragicPotion.Immunity.id, 120, 0, 1.0F);
		this.setAlwaysEdible();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity.EPIC;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 16;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player)
	{
		player.addPotionEffect(new PotionEffect(Potion.healthBoost.id, 2400, 4));
		player.addPotionEffect(new PotionEffect(Potion.saturation.id, 2400, 1));
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2400, 2));
		player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 2400, 0));
		if (TragicConfig.getBoolean("allowClarity")) player.addPotionEffect(new PotionEffect(TragicPotion.Clarity.id, 2400, 1));
		if (TragicConfig.getBoolean("allowInvulnerability")) player.addPotionEffect(new PotionEffect(TragicPotion.Invulnerability.id, 60));
		if (TragicConfig.getBoolean("allowAchievements") && player instanceof EntityPlayerMP) player.triggerAchievement(TragicAchievements.goldenSushi);

		return super.onItemUseFinish(stack, world, player);
	}

}
