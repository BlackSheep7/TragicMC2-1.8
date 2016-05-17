package tragicneko.tragicmc.blocks.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ITickable;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockStructureSeed;
import tragicneko.tragicmc.blocks.BlockStructureSeed2;
import tragicneko.tragicmc.worldgen.schematic.Schematic;
import tragicneko.tragicmc.worldgen.structure.Structure;
import tragicneko.tragicmc.worldgen.structure.TickBuilder;

public class TileEntityStructureSeed extends TileEntity implements ITickable {

	public boolean warned = false;

	@Override
	public void update()
	{
		if (!this.worldObj.isRemote && this.worldObj.getTotalWorldTime() % 5L == 0L && !warned) this.growStructure();
	}

	public void growStructure()
	{
		IBlockState state = this.worldObj.getBlockState(this.getPos());
		Integer prop = state.getBlock() == TragicBlocks.StructureSeed ? state.getValue(BlockStructureSeed.STRUCTURE) : state.getValue(BlockStructureSeed2.STRUCTURE);
		final int id = prop.intValue();
		if (id >= Structure.getRegistrySize()) return;

		Structure structure = Structure.getStructureById(id);
		if (structure == null)
		{
			EntityPlayer player = this.worldObj.getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 16.0);
			if (player != null) player.addChatMessage(new ChatComponentText("The structure you are attempting to generate is null for some reason. Try a different seed."));
			warned = true;
			return;
		}

		if (structure.getHeight() + this.getPos().getY() > this.worldObj.getActualHeight())
		{
			EntityPlayer player = this.worldObj.getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 16.0);
			if (player != null) player.addChatMessage(new ChatComponentText(structure.getLocalizedName() + " wasn't able to generate due to not enough height!"));
			warned = true;
			return;
		}

		Schematic sch = structure.generate(this.worldObj, this.worldObj.rand, this.getPos());
		if (sch != null)
		{
			boolean flag = false;
			if (TragicConfig.getBoolean("allowTickBuilder") && TickBuilder.getBuilderFor(this.worldObj) != null)
			{
				flag = TickBuilder.getBuilderFor(this.worldObj).addSchematic(pos, sch);
			}
			EntityPlayer player = this.worldObj.getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 16.0);
			if (player != null)
			{
				ChatComponentText text = new ChatComponentText(structure.getLocalizedName() + (TragicConfig.getBoolean("allowTickBuilder") ? (flag ? " had it's schematic generated successfully, building..." : " had it's schematic generated, however, there is a schematic being generated at that coordinate already.") : " was generated successfully!"));
				player.addChatMessage(text);
			}
			this.worldObj.setBlockState(this.pos, Blocks.air.getDefaultState());
		}
		else
		{
			TragicMC.logError("Something went wrong while generating a " + structure.getLocalizedName() + " with a structure seed");
			warned = true;
		}
	}

}
