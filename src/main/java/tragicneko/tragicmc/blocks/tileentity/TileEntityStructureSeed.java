package tragicneko.tragicmc.blocks.tileentity;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ITickable;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockStructureSeed;
import tragicneko.tragicmc.blocks.BlockStructureSeed2;
import tragicneko.tragicmc.worldgen.structure.Structure;

public class TileEntityStructureSeed extends TileEntity implements ITickable {

	@Override
	public void update()
	{
		if (!this.worldObj.isRemote && this.worldObj.getTotalWorldTime() % 20L == 0L) this.growStructure();
	}

	public void growStructure()
	{
		IBlockState state = this.worldObj.getBlockState(this.getPos());
		Integer prop = state.getBlock() == TragicBlocks.StructureSeed ? state.getValue(BlockStructureSeed.STRUCTURE) : state.getValue(BlockStructureSeed2.STRUCTURE);
		final int id = prop.intValue();
		if (id >= Structure.structureList.length) return;

		Structure structure = Structure.structureList[id];
		if (structure == null)
		{
			EntityPlayer player = this.worldObj.getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 16.0);
			if (player != null) player.addChatMessage(new ChatComponentText("The structure you are attempting to generate is null for some reason. Try a different seed."));
			return;
		}

		if (structure.getHeight() + this.getPos().getY() > 256)
		{
			EntityPlayer player = this.worldObj.getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 16.0);
			if (player != null) player.addChatMessage(new ChatComponentText(structure.getLocalizedName() + " wasn't able to generate due to not enough height!"));
			return;
		}

		if (structure.generateStructureWithVariant(this.worldObj.rand.nextInt(structure.getVariantSize()), this.worldObj, this.worldObj.rand, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ()))
		{
			EntityPlayer player = this.worldObj.getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 16.0);
			if (player != null) player.addChatMessage(new ChatComponentText(structure.getLocalizedName() + " was generated successfully!"));
		}
		else
		{
			TragicMC.logError("Something went wrong while generating a " + structure.getLocalizedName() + " with a structure seed");
		}
	}

}
