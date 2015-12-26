package tragicneko.tragicmc.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;

public class EnchantmentStomp extends Enchantment {

	protected EnchantmentStomp(int par1, ResourceLocation loc, int par2, EnumEnchantmentType par3EnumEnchantmentType) {
		super(par1, loc, par2, par3EnumEnchantmentType);
		this.setName("stomp");
	}

	@Override
	public int getMinEnchantability(int par1)
	{
		return 10 + (par1 * 4);
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 50;
	}

	@Override
	public int getMaxLevel()
	{
		return 2;
	}

	@Override
	public boolean canApplyTogether(Enchantment par1Enchantment)
	{
		return super.canApplyTogether(par1Enchantment);
	}

}
