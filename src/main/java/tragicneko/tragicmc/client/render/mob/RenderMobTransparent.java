package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

public class RenderMobTransparent extends RenderMob {

	private final float maxTransparency;

	public RenderMobTransparent(RenderManager rm, ModelBase model, float shadowSize, String path, float scale, float trans) {
		super(rm, model, shadowSize, path, scale);
		this.maxTransparency = trans;
	}

	@Override
	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.bindEntityTexture(par1EntityLivingBase);

		if (!par1EntityLivingBase.isInvisible() && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
		{			
			GlStateManager.color(1.0F, 1.0F, 1.0F, this.maxTransparency);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
		}
		else
		{
			this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityLivingBase);
		}
	}
}
