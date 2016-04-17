package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelVoxStellarum;
import tragicneko.tragicmc.client.render.layer.RenderLayerShield;
import tragicneko.tragicmc.entity.miniboss.EntityVoxStellarum;

public class RenderVoxStellarum extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/VoxStellarum.png");
	private static final float scale = 2.25F;

	public RenderVoxStellarum(RenderManager rm) {
		super(rm, new ModelVoxStellarum(), 0.75F * scale);
		this.addLayer(new RenderLayerShield(this, new ModelVoxStellarum()) {

			@Override
			public boolean shouldShieldRender(EntityLivingBase entity) {
				return entity instanceof EntityVoxStellarum && ((EntityVoxStellarum) entity).getHealTicks() >= 100;
			}

			@Override
			public ResourceLocation getShieldTexture(EntityLivingBase entity) {
				return texture;
			}
		});
	}

	@Override
	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.renderModel((EntityVoxStellarum) par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
	}

	private void renderModel(EntityVoxStellarum par1EntityLivingBase, float par2, 	float par3, float par4, float par5, float par6, float par7)
	{
		this.bindEntityTexture(par1EntityLivingBase);

		if (!par1EntityLivingBase.isInvisible() && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
		{
			float[] rgb = this.getRGBThroughTextureID(par1EntityLivingBase.getTextureID());

			GlStateManager.color(rgb[0], rgb[1], rgb[2], 0.85F);
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

	private float[] getRGBThroughTextureID(int textureID) {
		switch(textureID)
		{
		default:
			return new float[] {0.75F, 0.75F, 0.75F};
		case 1:
			return new float[] {0.75F, 0.25F, 0.25F};
		case 2:
			return new float[] {0.25F, 0.75F, 0.25F};
		case 3:
			return new float[] {0.25F, 0.25F, 0.75F};
		case 4:
			return new float[] {0.25F, 0.75F, 0.75F};
		case 5:
			return new float[] {0.75F, 0.75F, 0.25F};
		case 6:
			return new float[] {0.75F, 0.25F, 0.75F};
		case 7:
			return new float[] {0.25F, 0.25F, 0.25F};
		}
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		GL11.glScalef(RenderVoxStellarum.scale, RenderVoxStellarum.scale, RenderVoxStellarum.scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}
}
