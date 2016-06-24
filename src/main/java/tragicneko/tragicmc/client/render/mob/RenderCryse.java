package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.client.model.ModelCryse;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.TragicMob;

public class RenderCryse extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Cryse.png");
	private static final ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/StarCryse.png");

	public RenderCryse(RenderManager rm) {
		super(rm, new ModelCryse(), 0.335F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		EntityCryse cryse = (EntityCryse) entity;
		float scale = cryse.getCryseType() == 0 ? 1.0F : 0.625F;
		GlStateManager.scale(scale, scale, scale);
		
		if (entity instanceof TragicMob && ((TragicMob) entity).getCorruptionTicks() > 0 && TragicConfig.getBoolean("allowCorruptionMobRender"))
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
			EntityCryse cryse = (EntityCryse) par1EntityLivingBase;
			float[] rgb = cryse.getCryseType() == 0 ? new float[] {1.0F, 1.0F, 1.0F} : getRGBThroughTextureID(cryse.getTextureID());
			float trans = cryse.getCryseType() == 0 ? 0.35F : 0.75F;

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
		return getEntityTexture((EntityCryse) var1);
	}

	private ResourceLocation getEntityTexture(EntityCryse cryse)
	{
		return cryse.getCryseType() == 0 ? texture : texture2;
	}
}
