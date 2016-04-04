package tragicneko.tragicmc.dimension;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;
import tragicneko.tragicmc.TragicMC;

import org.lwjgl.opengl.GL11;

public class TragicSkyRenderer extends IRenderHandler {

	private static final ResourceLocation skyTexture = new ResourceLocation("tragicmc:textures/environment/collisionSky2.png");

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

			float f = 0.06F;
			float f2 = 0.12F;
			renderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
			renderer.pos(-64.0D, -64.0D, -64.0D).tex(0.0D, 0.0D).color(f, f, f, f2).endVertex();
			renderer.pos(-64.0D, -64.0D, 64.0D).tex(0.0D, 16.0D).color(f, f, f, f2).endVertex();
			renderer.pos(64.0D, -64.0D, 64.0D).tex(16.0D, 16.0D).color(f, f, f, f2).endVertex();
			renderer.pos(64.0D, -64.0D, -64.0D).tex(16.0D, 0.0D).color(f, f, f, f2).endVertex();
			tess.draw();
			GlStateManager.popMatrix();
		}

		GlStateManager.depthMask(true);
		GlStateManager.disableBlend();
		GlStateManager.enableTexture2D();
		GlStateManager.enableAlpha();
	}

}
