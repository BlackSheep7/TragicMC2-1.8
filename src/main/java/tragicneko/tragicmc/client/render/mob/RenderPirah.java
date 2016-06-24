package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.client.model.ModelPirah;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.TragicMob;

public class RenderPirah extends RenderLiving {

	private ResourceLocation[] textures = new ResourceLocation[] {new ResourceLocation("tragicmc:textures/mobs/Pirah.png"), new ResourceLocation("tragicmc:textures/mobs/Pirah2.png"),
			new ResourceLocation("tragicmc:textures/mobs/Pirah3.png"), new ResourceLocation("tragicmc:textures/mobs/Pirah4.png"),
			new ResourceLocation("tragicmc:textures/mobs/Pirah5.png"), new ResourceLocation("tragicmc:textures/mobs/Pirah6.png"),
			new ResourceLocation("tragicmc:textures/mobs/Pirah7.png"), new ResourceLocation("tragicmc:textures/mobs/Pirah8.png")};
	private ResourceLocation[] textures2 = new ResourceLocation[] {new ResourceLocation("tragicmc:textures/mobs/LavaPirah.png"), new ResourceLocation("tragicmc:textures/mobs/LavaPirah2.png"),
			new ResourceLocation("tragicmc:textures/mobs/LavaPirah3.png"), new ResourceLocation("tragicmc:textures/mobs/LavaPirah4.png"),
			new ResourceLocation("tragicmc:textures/mobs/LavaPirah5.png"), new ResourceLocation("tragicmc:textures/mobs/LavaPirah6.png"),
			new ResourceLocation("tragicmc:textures/mobs/LavaPirah7.png"), new ResourceLocation("tragicmc:textures/mobs/LavaPirah8.png")};

	public RenderPirah(RenderManager rm) {
		super(rm, new ModelPirah(), 0.255F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		EntityPirah pirah = (EntityPirah) entity;
		float scale = pirah.getTextureID() == 7 ? 1.5F : 1.0F;
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
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityPirah) entity);
	}

	protected ResourceLocation getEntityTexture(EntityPirah entity)
	{
		int i = entity.getTextureID();
		if (i > textures.length) i = 0;
		return textures[i];
	}

}
