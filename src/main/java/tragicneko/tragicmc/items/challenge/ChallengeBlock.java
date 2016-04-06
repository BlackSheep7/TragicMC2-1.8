package tragicneko.tragicmc.items.challenge;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import tragicneko.tragicmc.util.WorldHelper;

public class ChallengeBlock extends Challenge {
	
	private final IBlockState challengeBlockState;
	private final boolean ignoresState;

	public ChallengeBlock(int id, boolean saveProgress, int requirement, IBlockState state, boolean ignoresState)
	{
		super(id, saveProgress, requirement);
		if (state == null) throw new IllegalArgumentException("IBlockState cannot be null!");
		this.challengeBlockState = state;
		this.ignoresState = ignoresState;
	}

	public IBlockState getChallengeBlockState() {
		return this.challengeBlockState;
	}

	public Block getChallengeBlock() {
		return this.challengeBlockState.getBlock();
	}

	public boolean getIgnoresState() {
		return this.ignoresState;
	}
	
	@Override
	public void onLivingUpdate(ItemStack stack, EntityPlayer player)
	{
		super.onLivingUpdate(stack, player);
		
		ArrayList<BlockPos> list = WorldHelper.getBlocksInCircularRange(player.worldObj, 1.5, player.posX, player.getEntityBoundingBox().minY - 1.5, player.posZ);
		IBlockState block;
		for (BlockPos coords : list)
		{
			block = player.worldObj.getBlockState(coords);
			if (block == this.challengeBlockState && !this.ignoresState || block.getBlock() == this.challengeBlockState.getBlock() && this.ignoresState)
			{
				stack.getTagCompound().setInteger(CHALLENGE_PROG, 1);
				break;
			}
		}
	}
}
