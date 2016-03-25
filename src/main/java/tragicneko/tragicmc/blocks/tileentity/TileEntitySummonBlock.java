package tragicneko.tragicmc.blocks.tileentity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ITickable;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.boss.EntityAegar;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public class TileEntitySummonBlock extends TileEntity implements ITickable {

	private static final String[] taunts = new String[] {"The choice is made, the Traveller has come!", "They're here...", "Ready? ... FIGHT!", "Mortal Kombat!",
		"Begin.", "Let's get ready to RUMBLE!", "Come now, make up and hug it out", "Come play with us, forever and ever and ever and ever...", "Oh no!", "Kissy kissy~",
		"Run away!", "Retreat!", "Requesting permission to GTFO of here!", "Come on, it only wants a hug!", "It doesn't bite! ... much...",
		"It's just trying to show you some affection!", "Just pretend it's Dinnerbone and hug!", "I think you should get that checked out by a Doctor", "Is that the TARDIS I hear?",
		"Knock knock", "Did you see how he turned the Summon Block?", "I'm distracting you!", "I am Groot", "We are Groot"};

	@Override
	public void update()
	{
		if (this.worldObj.isRemote) return;

		if (this.worldObj.getTotalWorldTime() % 20L == 0L) this.updateState();
		if (this.worldObj.isBlockIndirectlyGettingPowered(this.getPos()) > 0 && !this.worldObj.isRemote)
		{
			this.spawnBoss(null);
		}
	}

	/**
	 * Checks for nearby players, if there is at least 1 that is not in creative mode, summons a boss and removes the block and tile entity
	 */
	private void updateState()
	{
		double d0 = 12.0;
		IBlockState state = this.worldObj.getBlockState(this.getPos());
		AxisAlignedBB axisalignedbb = state.getBlock().getCollisionBoundingBox(this.worldObj, this.getPos(), state).expand(d0, d0, d0);
		List<EntityPlayer> list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);

		if (!list.isEmpty())
		{
			for (int i = 0; i < list.size(); i++)
			{
				EntityPlayer player = list.get(i);

				if (!player.isPotionActive(Potion.invisibility) && !player.capabilities.isCreativeMode && !this.worldObj.isRemote)
				{
					this.spawnBoss(player);
					break;
				}
			}
		}
	}

	private void spawnBoss(EntityPlayer player)
	{
		IBlockState state = this.worldObj.getBlockState(this.getPos());
		int meta = state.getBlock().getMetaFromState(state);
		EntityLivingBase boss = null;

		if (meta == 2 && TragicConfig.allowApis)
		{
			boss = new EntityApis(this.worldObj);
		}
		else if (meta == 0)
		{
			boss = new EntityWither(this.worldObj);
		}
		else if (meta == 1)
		{
			boss = new EntityDragon(this.worldObj);
		}
		else if (meta == 3 && TragicConfig.allowSkultar)
		{
			boss = new EntityDeathReaper(this.worldObj);
		}
		else if (meta == 4 && TragicConfig.allowKitsunakuma)
		{
			boss = new EntityKitsune(this.worldObj);
		}
		else if (meta == 5 && TragicConfig.allowPolaris)
		{
			boss = new EntityPolaris(this.worldObj);
		}
		else if (meta == 6 && TragicConfig.allowEmpariah)
		{
			boss = new EntityYeti(this.worldObj);
		}
		else if (meta == 7 && TragicConfig.allowTimeController)
		{
			boss = new EntityTimeController(this.worldObj);
		}
		else if (meta == 8 && TragicConfig.allowEnyvil)
		{
			boss =  new EntityEnyvil(this.worldObj);
		}
		else if (meta == 9 && TragicConfig.allowClaymation)
		{
			boss = new EntityClaymation(this.worldObj);
		}
		else if (meta == 10 && TragicConfig.allowAegar)
		{
			boss = new EntityAegar(this.worldObj);
		}

		if (boss instanceof TragicBoss && this.worldObj.getDifficulty().getDifficultyId() < 2 && !TragicConfig.allowEasyBosses && player != null)
		{
			player.addChatMessage(new ChatComponentText("Difficulty needs to be raised to spawn this boss."));
			return;
		}

		if (boss == null) return;

		boss.setLocationAndAngles(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), this.worldObj.rand.nextFloat(), this.worldObj.rand.nextFloat());
		if (boss instanceof EntityLiving) ((EntityLiving) boss).onInitialSpawn(this.worldObj.getDifficultyForLocation(this.getPos()), null);
		this.worldObj.setBlockToAir(this.getPos());
		this.worldObj.removeTileEntity(this.getPos());
		this.worldObj.spawnEntityInWorld(boss);

		if (player != null)
		{
			((EntityLiving) boss).setAttackTarget(player);
			this.tauntPlayer(player, this.worldObj.rand);
		}
	}

	private void tauntPlayer(EntityPlayer player, Random rand) {

		EnumChatFormatting format = EnumChatFormatting.DARK_RED;
		ChatComponentText chat = null;

		if (rand.nextInt(8) == 0)
		{
			chat = new ChatComponentText(format + "A Boss has been summoned!");
		}
		else
		{
			chat = new ChatComponentText(format + taunts[rand.nextInt(taunts.length)]);
		}

		player.addChatMessage(chat);
	}
}
