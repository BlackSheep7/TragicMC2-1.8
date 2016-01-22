package tragicneko.tragicmc.client.render.layer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderLayerAnimatedTexture implements LayerRenderer {
	
	private final RenderLiving renderer;
	private final ResourceLocation texture;
	
	public RenderLayerAnimatedTexture(RenderLiving render, ResourceLocation loc) {
		this.renderer = render;
		this.texture = loc;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entity, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
		if (!entity.isInvisible() && this.shouldTextureAnimate(entity))
		{
			GlStateManager.depthMask(!entity.isInvisible());
            this.renderer.bindTexture(this.texture);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f17 = (float)entity.ticksExisted + f4;
            float f18 = MathHelper.cos(f7 * 0.5F) * 0.56F;
            float f19 = f17 * 0.02F;
            GlStateManager.translate(f18, f19, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float f10 = 0.5F;
            GlStateManager.color(f10, f10, f10, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(1, 1);
            this.renderer.getMainModel().render(entity, f2, f3, f5, f6, f7, f8);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

	public boolean shouldTextureAnimate(EntityLivingBase entity) {
		return true;
	}
}
