package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityStatue;

public class ItemStatue extends Item {

	public static String[] subNames = new String[] {"apis", "kitsune", "deathReaper", "timeController", "yeti", "polaris", "jarra", "kragul", "magmox", "megaCryse", "stinKing",
		"stinQueen", "greaterStin", "voxStellarum", "enyvil", "claymation", "aegar", "overlord", "overlordCombat", "overlordCocoon"};

	public ItemStatue()
	{
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setCreativeTab(TragicMC.Survival);
		this.setUnlocalizedName("tragicmc.mobStatue");
		this.setMaxStackSize(1);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < subNames.length; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int damage = itemstack.getItemDamage();
		return damage < subNames.length ? getUnlocalizedName() + "." + subNames[damage] : getUnlocalizedName() + "." + subNames[subNames.length - 1];
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		String s = ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("textureID") && !stack.getTagCompound().hasKey("isAnimated")) return s;
		return s + " " + StatCollector.translateToLocal(this.getExtraStringName(stack)) + (this.getAnimated(stack) ? StatCollector.translateToLocal("tragicmc.mobTexture.animated") : "");
	}

	private String getExtraStringName(ItemStack stack) {
		int var = getTextureIDFromStack(stack);

		switch(var)
		{
		case 1:
			return "tragicmc.mobTexture.iron";
		case 2:
			return "tragicmc.mobTexture.gold";
		case 3:
			return "tragicmc.mobTexture.diamond";
		case 4:
			return "tragicmc.mobTexture.stone";
		case 5:
			return "tragicmc.mobTexture.wood";
		case 6:
			return "tragicmc.mobTexture.emerald";
		case 7:
			return "tragicmc.mobTexture.leaf";
		case 8:
			return "tragicmc.mobTexture.tungsten";
		case 9:
			return "tragicmc.mobTexture.ruby";
		case 10:
			return "tragicmc.mobTexture.sapphire";
		case 11:
			return "tragicmc.mobTexture.redstone";
		case 12:
			return "tragicmc.mobTexture.coal";
		case 13:
			return "tragicmc.mobTexture.lapis";
		case 14:
			return "tragicmc.mobTexture.netherrack";
		case 15:
			return "tragicmc.mobTexture.ender";
		default:
			return "";
		}
	}

	private int getTextureIDFromStack(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("textureID") ? stack.getTagCompound().getInteger("textureID") : 0;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add(EnumChatFormatting.GOLD + "It's a statue of a mob!");
		par2List.add("");
		par2List.add("Can be animated with a Synapse Crystal.");
		par2List.add("Can have it's texture changed with various blocks/items.");
		par2List.add("Can reset it's texture with Blaze Powder.");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, false);

		if (mop == null) return stack;

		BlockPos pos = mop.getBlockPos().offset(mop.sideHit);

		EntityStatue statue;

		if (World.doesBlockHaveSolidTopSurface(world, mop.getBlockPos()) && !world.isRemote)
		{
			statue = new EntityStatue(world);
			float rotation = ((MathHelper.floor_float((player.rotationYaw * 8.0F / 360.0F) + 0.5F)) * 45.0F) + 180F;

			statue.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
			statue.setRotation(rotation);
			if (!world.getCollidingBoundingBoxes(statue, statue.getEntityBoundingBox()).isEmpty() || world.isAnyLiquid(statue.getEntityBoundingBox())) return stack;
			statue.setMobID(stack.getItemDamage() % subNames.length);
			statue.setTextureID(this.getTextureIDFromStack(stack));
			statue.setAnimated(this.getAnimated(stack));
			world.spawnEntityInWorld(statue);
			if (!player.capabilities.isCreativeMode) stack.stackSize--;
		}

		return stack;
	}

	private boolean getAnimated(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("isAnimated") ? stack.getTagCompound().getInteger("isAnimated") == 1 : false;
	}
}
