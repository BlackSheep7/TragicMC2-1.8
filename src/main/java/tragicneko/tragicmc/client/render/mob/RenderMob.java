package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.TragicMob;

public class RenderMob extends RenderLiving {

	private final ResourceLocation texture;
	private final float scale;
	private String filePath = "tragicmc:textures/mobs/";
	private String extension = ".png";

	public RenderMob(RenderManager rm, ModelBase model, float shadowSize, String path, float scale) {
		super(rm, model, scale * shadowSize);
		this.texture = new ResourceLocation(this.filePath + path + this.extension);
		this.scale = scale;
	}

	public RenderMob(RenderManager rm, ModelBase model, float shadowSize, String path) {
		this(rm, model, shadowSize, path, 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
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
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}

}
