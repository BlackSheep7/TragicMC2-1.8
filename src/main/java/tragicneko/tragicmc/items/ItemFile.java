package tragicneko.tragicmc.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.LoreHelper;

public class ItemFile extends ItemRecord {
	
	private final int recordColor;

	public ItemFile(String name, int color) {
		super(name);
		this.recordColor = color;
		this.setCreativeTab(TragicMC.Survival);
		this.setUnlocalizedName("tragicmc." + name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
    {
		LoreHelper.splitDesc(tooltip, this.getRecordNameDescLocal(), 28, EnumChatFormatting.GRAY);
		tooltip.add(EnumChatFormatting.ITALIC + "Use this like a record.");
    }

	@Override
    @SideOnly(Side.CLIENT)
    public String getRecordNameLocal()
    {
        return StatCollector.translateToLocal("item.record." + this.recordName + ".name");
    }
	
	@SideOnly(Side.CLIENT)
    public String getRecordNameDescLocal()
    {
        return StatCollector.translateToLocal("item.record." + this.recordName + ".desc");
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int par2)
	{
		return this.recordColor;
	}

	@Override
	public net.minecraft.util.ResourceLocation getRecordResource(String name)
    {
		return new net.minecraft.util.ResourceLocation("tragicmc:" + name);
    }
}
