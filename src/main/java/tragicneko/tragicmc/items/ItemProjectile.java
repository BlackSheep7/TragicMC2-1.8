package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.projectile.EntityBanana;
import tragicneko.tragicmc.entity.projectile.EntityCrystalMortor;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.entity.projectile.EntityDarkLightning;
import tragicneko.tragicmc.entity.projectile.EntityDarkMortor;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntityIreEnergy;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.entity.projectile.EntityOverlordMortor;
import tragicneko.tragicmc.entity.projectile.EntityPitchBlack;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.entity.projectile.EntityProjectile;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.entity.projectile.EntitySpore;
import tragicneko.tragicmc.entity.projectile.EntityStarShard;
import tragicneko.tragicmc.entity.projectile.EntityThrowingRock;
import tragicneko.tragicmc.entity.projectile.EntityTimeBomb;
import tragicneko.tragicmc.entity.projectile.EntityWebBomb;
import tragicneko.tragicmc.util.WorldHelper;

public class ItemProjectile extends Item {

	private static final String[] subNames = new String[] {"rock", "lavaRock", "pumpkinbomb", "largePumpkinbomb", "poisonBarb", "nekoRocket", "nekoStickyBomb", "nekoClusterBomb",
		"nekoMiniBomb", "solarBomb", "spiritCast", "spore", "banana", "largeRock", "icicle", "timeBomb", "starShard", "darkLightning", "pitchBlack", "darkEnergy",
		"darkMortor", "webBomb", "crystalMortor", "overlordMortor", "ireEnergy"};

	private static final String[] textureNames = new String[] {"Rock", "LavaRock", "Pumpkinbomb", "LargePumpkinbomb", "PoisonBarb", "NekoRocket", "NekoStickyBomb", "NekoClusterBomb",
		"NekoMiniBomb", "SolarBomb", "SpiritCast", "Spore", "Banana", "LargeRock", "Icicle", "TimeBomb", "StarShard", "DarkLightning", "PitchBlack", "DarkEnergy",
		"DarkMortor", "WebBomb", "CrystalMortor", "OverlordMortor", "IreEnergy"};

	public ItemProjectile()
	{
		super();
		this.setCreativeTab(TragicMC.Creative);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("tragicmc.projectile");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (world.isRemote) return stack;

		final int i = stack.getItemDamage();

		Vec3 vec = WorldHelper.getVecFromEntity(player);
		if (vec == null) return stack;

		double x = vec.xCoord - player.posX;
		double y = vec.yCoord - (player.posY + player.height / 2.0F);
		double z = vec.zCoord - player.posZ;
		float f9 = 0.12F * 6.0F * 0.9275F;

		Entity entity = null;

		switch(i)
		{
		case 0:
		case 1:
			entity = new EntityThrowingRock(world, player, i == 1);
			break;
		case 2:
			entity = new EntityPumpkinbomb(world, player);
			break;
		case 3:
			entity = new EntityLargePumpkinbomb(world, player);
			break;
		case 4:
			entity = new EntityPoisonBarb(world, player, x, y, z);
			break;
		case 5:
			entity = new EntityNekoRocket(world, player, x, y, z);
			break;
		case 6:
			entity = new EntityNekoStickyBomb(world, player);
			break;
		case 7:
			entity = new EntityNekoClusterBomb(world, player);
			break;
		case 8:
			entity = new EntityNekoMiniBomb(world, player);
			break;
		case 9:
			entity = new EntitySolarBomb(world, player, x, y, z);
			break;
		case 10:
			entity = new EntitySpiritCast(world, player, x, y, z);
			break;
		case 11:
			entity = new EntitySpore(world, player, x, y, z);
			break;
		case 12:
			entity = new EntityBanana(world, player);
			break;
		case 13:
			entity = new EntityLargeRock(world, player, x, y, z);
			entity.motionY += f9;
			break;
		case 14:
			entity = new EntityIcicle(world, player, x, y, z);
			break;
		case 15:
			entity = new EntityTimeBomb(world, player, x, y, z);
			break;
		case 16:
			entity = new EntityStarShard(world, player, x, y, z);
			break;
		case 17:
			entity = new EntityDarkLightning(world, player, x, y, z);
			break;
		case 18:
			entity = new EntityPitchBlack(world, player);
			break;
		case 19:
			entity = new EntityDarkEnergy(world, player, x, y, z);
			break;
		case 20:
			entity = new EntityDarkMortor(world, player, x, y, z);
			entity.motionY += f9;
			break;
		case 21:
			entity = new EntityWebBomb(world, player, x, y, z);
			entity.motionY += f9;
			break;
		case 22:
			entity = new EntityCrystalMortor(world, player, x, y, z);
			entity.motionY += f9;
			break;
		case 23:
			entity = new EntityOverlordMortor(world, player, x, y, z);
			entity.motionX += itemRand.nextFloat() - itemRand.nextFloat();
			entity.motionY += itemRand.nextFloat() - itemRand.nextFloat();
			entity.motionZ += itemRand.nextFloat() - itemRand.nextFloat();
			break;
		case 24:
			entity = new EntityIreEnergy(world, player, x, y, z);
			break;
		}

		if (entity != null)
		{
			if (entity instanceof EntityProjectile) entity.posY += player.getEyeHeight();
			world.spawnEntityInWorld(entity);
		}

		if (!player.capabilities.isCreativeMode)  --stack.stackSize;

		return stack;
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
		if (damage >= subNames.length) damage = subNames.length - 1;

		return getUnlocalizedName() + "." + subNames[damage];
	}
}
