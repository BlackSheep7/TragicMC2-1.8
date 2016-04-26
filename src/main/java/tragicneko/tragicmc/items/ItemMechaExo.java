package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityMechaExo;
import tragicneko.tragicmc.entity.EntityStatue;

public class ItemMechaExo extends Item {

	public ItemMechaExo()
	{
		this.setMaxDamage(25); //MathHelper.ceiling_double_int(TragicConfig.getMobStat("mechaExoStats").getStats()[0]));
		this.setCreativeTab(TragicMC.Survival);
		this.setUnlocalizedName("tragicmc.mechaExo");
		this.setMaxStackSize(1);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Can place your own Mecha Exo into the world!");
		par2List.add("It's health is based on the item damage value.");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, false);

		if (mop == null || stack.getMaxDamage() - stack.getItemDamage() <= 0) return stack;

		BlockPos pos = mop.getBlockPos().offset(mop.sideHit);

		EntityMechaExo exo;

		if (World.doesBlockHaveSolidTopSurface(world, mop.getBlockPos()) && !world.isRemote)
		{
			exo = new EntityMechaExo(world);

			exo.setPosition(pos.getX(), pos.getY(), pos.getZ());
			if (!world.getCollidingBoundingBoxes(exo, exo.getEntityBoundingBox()).isEmpty() || world.isAnyLiquid(exo.getEntityBoundingBox())) return stack;
			exo.setHealth(stack.getMaxDamage() - stack.getItemDamage());
			world.spawnEntityInWorld(exo);
			if (!player.capabilities.isCreativeMode) stack.stackSize--;
		}

		return stack;
	}

	private boolean getAnimated(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("isAnimated") ? stack.getTagCompound().getInteger("isAnimated") == 1 : false;
	}
}
