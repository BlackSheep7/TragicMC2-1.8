package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.client.model.ModelJabba;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.TragicMob;

public class RenderJabba extends RenderMob {

	private ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Jabba.png");
	private ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/Janna.png");

	public RenderJabba(RenderManager rm) {
		super(rm, new ModelJabba(), 0.655F, "");
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		EntityJabba jab = (EntityJabba) entity;
		float scale = jab.getJabbaType() == 0 ? 1.0F : 0.825F;
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
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityJabba) entity);
	}

	protected ResourceLocation getEntityTexture(EntityJabba entity)
	{
		return entity.getJabbaType() == 0 ? texture : texture2;
	}
}
