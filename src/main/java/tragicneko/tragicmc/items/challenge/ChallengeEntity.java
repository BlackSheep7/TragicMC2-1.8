package tragicneko.tragicmc.items.challenge;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ChallengeEntity extends Challenge {

	private final Class targetClass;
	private final boolean isSearchChallenge;

	public ChallengeEntity(int id, boolean saveProgress, int requirement, Class oclass, boolean searchChallenge)
	{
		super(id, saveProgress, requirement);
		this.targetClass = oclass;
		this.isSearchChallenge = searchChallenge;
	}

	public Class getTargetClass() {
		return this.targetClass;
	}

	public boolean getIsSearchChallenge() {
		return this.isSearchChallenge;
	}

	@Override
	public void onLivingKill(ItemStack stack, EntityLivingBase entity, EntityPlayer player) {
		super.onLivingKill(stack, entity, player);
		
		if (stack.getTagCompound().hasKey(CHALLENGE_PROG) && this.targetClass != null && !this.isSearchChallenge)
		{
			Class cls2 = entity.getClass();
			boolean flag = this.targetClass == cls2;

			for (Class cl : cls2.getInterfaces())
			{
				if (flag || cl == this.targetClass)
				{
					flag = true;
					break;
				}
			}

			while (!flag)
			{
				if (this.targetClass == cls2)
				{
					flag = true;
					break;
				}

				if (cls2.getSuperclass() == null) break;
				cls2 = cls2.getSuperclass();
			}

			if (flag)
			{
				int pow = stack.getTagCompound().getInteger(CHALLENGE_PROG);
				stack.getTagCompound().setInteger(CHALLENGE_PROG, ++pow);
			}
		}
	}
	
	@Override
	public void onLivingUpdate(ItemStack stack, EntityPlayer player) {
		super.onLivingUpdate(stack, player);
		
		if (stack.getTagCompound().hasKey(CHALLENGE_PROG) &&  this.targetClass != null && this.isSearchChallenge)
		{
			List<Entity> list = player.worldObj.getEntitiesWithinAABB(this.targetClass, player.getEntityBoundingBox().expand(6.0, 6.0, 6.0));
			int amt = 0;

			for (int j = 0; j < list.size(); j++)
			{
				Class cls2 = list.get(j).getClass();
				boolean flag = this.targetClass == cls2;

				for (Class cl : cls2.getInterfaces())
				{
					if (cl == this.targetClass)
					{
						flag = true;
						break;
					}
				}

				while (!flag)
				{
					if (this.targetClass == cls2)
					{
						flag = true;
						break;
					}

					if (cls2.getSuperclass() == null) break;
					cls2 = cls2.getSuperclass();
				}
			}
			
			stack.getTagCompound().setInteger(CHALLENGE_PROG, amt);
		}
	}
}
