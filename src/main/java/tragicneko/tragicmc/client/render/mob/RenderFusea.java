package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelFusea;
import tragicneko.tragicmc.entity.mob.EntityFusea;

public class RenderFusea extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Fusea.png");
	private static final ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/VolatileFusea.png");
	private final int fuseaType;

	public RenderFusea(RenderManager rm, int i) {
		super(rm, new ModelFusea(), 0.335F);
		this.fuseaType = i;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		float scale = this.fuseaType > 0 ? 1.185F : 0.6F;
		float s = (scale * (entity.getHealth() / entity.getMaxHealth())) + 0.4F;
		GL11.glScalef(s, s, s);
	}

	@Override
	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.bindEntityTexture(par1EntityLivingBase);

		if (!par1EntityLivingBase.isInvisible() && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
		{
			float trans = 0.65F;

			GlStateManager.color(1.0F, 1.0F, 1.0F, trans);
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
		return getEntityTexture((EntityFusea) var1);
	}

	private ResourceLocation getEntityTexture(EntityFusea fusea)
	{
		return this.fuseaType == 0 ? texture : texture2;
	}
}
