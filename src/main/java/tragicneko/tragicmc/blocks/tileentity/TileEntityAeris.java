package tragicneko.tragicmc.blocks.tileentity;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ITickable;
import net.minecraft.world.chunk.Chunk;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.items.ItemCorruptedEgg;
import tragicneko.tragicmc.util.WorldHelper;

public class TileEntityAeris extends TileEntity implements ITickable {

	private int corruptedTicks;

	@Override
	public void update()
	{
		if (this.blockType == null) return;
		
		IBlockState state = this.worldObj.getBlockState(this.getPos());
		if (!this.worldObj.isRemote && this.getPos().getY() <= 50 && !this.worldObj.canBlockSeeSky(this.getPos()) && this.blockType.getMetaFromState(state) < 2)
		{
			corruptedTicks++;
			Chunk chk = this.worldObj.getChunkFromBlockCoords(this.getPos());
			chk.setInhabitedTime(chk.getInhabitedTime() + 100);
			EntityPlayer player = this.worldObj.getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 16.0D);

			if (this.corruptedTicks % 300 == 0 && player != null && this.corruptedTicks < 3000)
			{
				player.addChatMessage(new ChatComponentText("Aeris is corrupting... (" + (this.corruptedTicks / 300)  + " /10)"));

				if (this.getBlockMetadata() == 1)
				{
					for (int i = 0; i < this.worldObj.rand.nextInt(3) + 1; i++)
					{
						ItemCorruptedEgg.spawnCreature(this.worldObj, this.pos.getX() + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble() * 4),
								this.pos.getY(), this.pos.getZ() + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble() * 4));
					}
				}
			}

			if (this.corruptedTicks >= 3000)
			{
				this.corruptedTicks = 0;
				this.worldObj.setBlockState(this.getPos(), this.blockType.getStateFromMeta(this.blockType.getMetaFromState(state) + 1), 2);
				String s = this.blockType.getMetaFromState(state) == 1 ? "Aeris is starting to show signs of corruption..." : "Aeris has fully corrupted!";
				if (player != null) player.addChatMessage(new ChatComponentText(s));
			}

			if (this.corruptedTicks % 20 == 0)
			{
				ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(this.worldObj, 5.0, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
				Block block;
				for (BlockPos coords : list)
				{
					block = this.worldObj.getBlockState(coords).getBlock();
					if (block != TragicBlocks.Aeris && coords.getY() >= this.getPos().getY() && block.getMaterial() != Material.air)
					{
						this.worldObj.destroyBlock(coords, true);
					}
				}
			}
		}
	}
}
