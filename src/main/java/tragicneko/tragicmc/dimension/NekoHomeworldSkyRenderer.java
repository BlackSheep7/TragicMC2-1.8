package tragicneko.tragicmc.dimension;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;

public class NekoHomeworldSkyRenderer extends IRenderHandler {

	private static final ResourceLocation skyTexture = new ResourceLocation("tragicmc:textures/environment/nekoHomeworldSky.png");

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		GlStateManager.disableFog();
		GlStateManager.disableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		RenderHelper.disableStandardItemLighting();
		GlStateManager.depthMask(false);
		mc.renderEngine.bindTexture(skyTexture);
		Tessellator tess = Tessellator.getInstance();
		WorldRenderer renderer = tess.getWorldRenderer();
		
		for (byte i = 0; i < 6; ++i)
		{
			GlStateManager.pushMatrix();
			GlStateManager.color(1.0F, 0.45F, 0.45F, 0.356F);
			if (i == 1)
			{
				GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0F);
			}
			else if (i == 2)
			{
				GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0F);
			}
			else if (i == 3)
			{
				GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0F);
			}
			else if (i == 4)
			{
				GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1F);
			}
			else if (i == 5)
			{
				GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1F);
			}

			int f = 255;
			int f2 = 32;
			renderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
			double d = 128.0D;
			renderer.pos(-d, -d, -d).tex(0.0D, 0.0D).color(f, f, f, f2).endVertex();
			renderer.pos(-d, -d, d).tex(0.0D, 16.0D).color(f, f, f, f2).endVertex();
			renderer.pos(d, -d, d).tex(16.0D, 16.0D).color(f, f, f, f2).endVertex();
			renderer.pos(d, -d, -d).tex(16.0D, 0.0D).color(f, f, f, f2).endVertex();
			tess.draw();
			GlStateManager.popMatrix();
		}
		
		GlStateManager.depthMask(true);
		GlStateManager.disableBlend();
		GlStateManager.enableTexture2D();
		GlStateManager.enableAlpha();
	}
}
