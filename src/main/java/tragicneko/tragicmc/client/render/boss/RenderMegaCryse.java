package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelMegaCryse;

public class RenderMegaCryse extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Cryse.png");
	private static final float scale = 1.5F;

	public RenderMegaCryse(RenderManager rm) {
		super(rm, new ModelMegaCryse(), 0.375F * scale);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		GL11.glScalef(RenderMegaCryse.scale, RenderMegaCryse.scale, RenderMegaCryse.scale);
	}

	@Override
	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.bindEntityTexture(par1EntityLivingBase);

		if (!par1EntityLivingBase.isInvisible() && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
		{
			GlStateManager.color(0.8F, 0.8F, 1.0F, 0.75F);
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

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return texture;
	}

}
