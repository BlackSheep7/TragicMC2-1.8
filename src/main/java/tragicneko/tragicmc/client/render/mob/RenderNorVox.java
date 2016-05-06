package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.client.model.ModelNorVox;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.entity.mob.TragicMob;

public class RenderNorVox extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/NorVox.png");
	private static final ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/StarVox.png");

	public RenderNorVox(RenderManager rm) {
		super(rm, new ModelNorVox(), 0.835F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		EntityNorVox vox = (EntityNorVox) entity;
		float scale = vox.getNorVoxType() == 0 ? 1.455F : 1.0F;
		GlStateManager.scale(scale, scale, scale);
		
		if (entity instanceof TragicMob && ((TragicMob) entity).getCorruptionTicks() > 0 && TragicConfig.getBoolean("allowMobCorruptionRender"))
		{
			float f = (float) ((TragicMob) entity).getCorruptionTicks();
			if (f > 400.0F) f = 400.0F;
			final float f2 = 1.0F - (f / 400.0F);
			GlStateManager.color(f2, f2, f2);
		}
	}

	@Override
	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.bindEntityTexture(par1EntityLivingBase);

		if (!par1EntityLivingBase.isInvisible() && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
		{
			EntityNorVox vox = (EntityNorVox) par1EntityLivingBase;
			float[] rgb = vox.getNorVoxType() == 0 ? new float[] {1.0F, 1.0F, 1.0F} : getRGBThroughTextureID(vox.getTextureID());
			float trans = vox.getNorVoxType() == 0 ? 1.0F : 0.85F;

			GlStateManager.color(rgb[0], rgb[1], rgb[2], trans);
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
	protected ResourceLocation getEntityTexture(Entity var1) {
		return getEntityTexture((EntityNorVox) var1);
	}

	private ResourceLocation getEntityTexture(EntityNorVox vox)
	{
		return vox.getNorVoxType() == 0 ? texture : texture2;
	}

}
