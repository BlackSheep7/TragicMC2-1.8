package tragicneko.tragicmc.client.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.properties.PropertyAmulets;

@SideOnly(Side.CLIENT)
public class GuiAmuletStatus extends Gui
{
	private final Minecraft mc;
	private final RenderItem itemRender;

	private static final ResourceLocation texturepath = new ResourceLocation("tragicmc:textures/gui/amulet_status_minimal.png");
	private static final ResourceLocation texturepath2 = new ResourceLocation("tragicmc:textures/gui/amulet_status_pink.png");
	private static final ResourceLocation texturepath3 = new ResourceLocation("tragicmc:textures/gui/amulet_status_tentacle.png");
	private static final ResourceLocation texturepath4 = new ResourceLocation("tragicmc:textures/gui/amulet_status_pokemon.png");

	public GuiAmuletStatus(Minecraft mc) {
		super();
		this.mc = mc;
		this.itemRender = mc.getRenderItem();
	}

	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onRenderOverlay(RenderGameOverlayEvent event)
	{
		if (event.isCancelable() || event.type != ElementType.PORTAL || Minecraft.getMinecraft().gameSettings.showDebugInfo) return;

		PropertyAmulets amu = PropertyAmulets.get(this.mc.thePlayer);
		if (amu == null || amu.getSlotsOpen() <= 0) return;

		int xPos = TragicConfig.guiX + 1;
		int yPos = TragicConfig.guiY + 11;
		this.mc.renderEngine.bindTexture(getTextureFromConfig());
		int length = 0;
		if (amu.getSlotsOpen() == 1) length = 20;
		if (amu.getSlotsOpen() == 2) length = 40;
		if (amu.getSlotsOpen() == 3) length = 60;

		GlStateManager.enableBlend();
		GlStateManager.disableDepth();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.disableAlpha();
		
		float trans = ((float) TragicConfig.guiTransparency / 100.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, trans);
		
		drawTexturedModalRect(xPos, yPos, 0, 0, length, 20);

		GlStateManager.enableAlpha();
		GlStateManager.enableDepth();
		GlStateManager.disableBlend();
		
		ItemStack stack;

		for (byte i = 0; i < 3; i++)
		{
			if (amu.inventory.getStackInSlot(i) != null)
			{
				stack = amu.inventory.getStackInSlot(i);

				itemRender.renderItemAndEffectIntoGUI(stack, xPos + 3 + (20 * i), yPos + 2);
				itemRender.renderItemOverlayIntoGUI(mc.fontRendererObj, stack, xPos + 2 + (20 * i), yPos + 4, stack.getDisplayName());
				GlStateManager.disableLighting();
			}
			else if (amu.getSlotsOpen() < i + 1 && TragicConfig.amuletMaxSlots >= i + 1)
			{
				String s = "X";
				Color color = new Color(0x23, 0x23, 0x23);
				this.mc.fontRendererObj.drawString(s.trim(), xPos + 8 + (20 * i), yPos + 6, color.getRGB());
			}
		}
	}

	public static ResourceLocation getTextureFromConfig()
	{
		switch(TragicConfig.guiTexture)
		{
		case 0:
			return texturepath3;
		case 1:
			return texturepath2;
		case 2:
			return texturepath;
		default:
			return texturepath4;
		}
	}
}
