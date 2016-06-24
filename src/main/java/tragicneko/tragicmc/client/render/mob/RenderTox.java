package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.client.model.ModelTox;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.TragicMob;

public class RenderTox extends RenderMob {

	private ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Tox.png");
	private ResourceLocation texture2 = new ResourceLocation("tragicmc:textures/mobs/Pox.png");

	public RenderTox(RenderManager rm) {
		super(rm, new ModelTox(), 0.855F, "");
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		EntityTox tox = (EntityTox) entity;
		float scale = tox.getToxType() == 0 ? 1.0F : 0.635F;
		GL11.glScalef(scale, scale, scale);
		
		if (entity instanceof TragicMob && ((TragicMob) entity).getCorruptionTicks() > 0 && TragicConfig.getBoolean("allowCorruptionMobRender"))
		{
			float f = (float) ((TragicMob) entity).getCorruptionTicks();
			if (f > 400.0F) f = 400.0F;
			final float f2 = 1.0F - (f / 400.0F);
			GlStateManager.color(f2, f2, f2);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityTox) entity);
	}

	protected ResourceLocation getEntityTexture(EntityTox entity)
	{
		return entity.getToxType() == 0 ? texture : texture2;
	}
}
