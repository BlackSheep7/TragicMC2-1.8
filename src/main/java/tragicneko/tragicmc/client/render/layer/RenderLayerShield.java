package tragicneko.tragicmc.client.render.layer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public abstract class RenderLayerShield implements LayerRenderer {
	
	private final ModelBase model;
	private final RenderLiving renderer;
	
	public RenderLayerShield(RenderLiving render, ModelBase model) {
		this.renderer = render;
		if (model == null) this.model = renderer.getMainModel();
		else this.model = model;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entity, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
		if (!entity.isInvisible() && this.shouldShieldRender(entity))
        {
			GlStateManager.scale(1.05F, 1.05F, 1.05F);
			GlStateManager.depthMask(!entity.isInvisible());
            this.renderer.bindTexture(this.getShieldTexture(entity));
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
            this.model.setLivingAnimations(entity, f2, f3, f4);
            this.model.setModelAttributes(this.renderer.getMainModel());
            this.model.render(entity, f2, f3, f5, f6, f7, f8);
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

	public abstract boolean shouldShieldRender(EntityLivingBase entity);
	
	public abstract ResourceLocation getShieldTexture(EntityLivingBase entity);
}
