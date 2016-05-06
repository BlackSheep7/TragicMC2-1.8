package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.client.model.ModelStin;
import tragicneko.tragicmc.client.model.ModelStinBaby;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.TragicMob;

public class RenderStin extends RenderMob {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Stin.png");

	public RenderStin(RenderManager rm) {
		super(rm, new ModelStin(), 0.755F, "");
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		EntityStin stin = (EntityStin) entity;

		if (!stin.isAdult() && !(this.mainModel instanceof ModelStinBaby))
		{
			this.mainModel = new ModelStinBaby();
		}
		else if (stin.isAdult() && this.mainModel instanceof ModelStinBaby)
		{
			this.mainModel = new ModelStin();
		}
		
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
		return texture;
	}

}
