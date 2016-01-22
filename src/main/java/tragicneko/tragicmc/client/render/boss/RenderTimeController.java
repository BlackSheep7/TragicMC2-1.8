package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelTimeController;
import tragicneko.tragicmc.client.render.layer.RenderLayerTimeControllerPurge;
import tragicneko.tragicmc.entity.boss.EntityTimeController;

public class RenderTimeController extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/TimeController.png");

	public RenderTimeController(RenderManager rm) {
		super(rm, new ModelTimeController(), 0.415F);
		this.addLayer(new RenderLayerTimeControllerPurge(this, texture));
	}

	@Override
	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.bindEntityTexture(par1EntityLivingBase);

		if (!par1EntityLivingBase.isInvisible() && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
		{
			GL11.glPushMatrix();
			float f2 = 1.8F;
			EntityTimeController ctrl = (EntityTimeController) par1EntityLivingBase;
			if (ctrl.getLeapTicks() > 0) f2 = 1.0F + MathHelper.cos(ctrl.ticksExisted * 0.4F) * 0.876F;

			GlStateManager.color(f2 - 0.4F, f2, f2 - 0.4F, 1.0F);
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
