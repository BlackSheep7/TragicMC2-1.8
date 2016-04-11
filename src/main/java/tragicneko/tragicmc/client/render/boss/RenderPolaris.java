package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelPolaris;
import tragicneko.tragicmc.client.render.layer.RenderLayerAnimatedTexture;
import tragicneko.tragicmc.entity.boss.EntityPolaris;

public class RenderPolaris extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Polaris2.png");

	public RenderPolaris(RenderManager rm) {
		super(rm, new ModelPolaris(), 0.335F);
		this.addLayer(new RenderLayerAnimatedTexture(this, texture) {
			@Override
			public boolean shouldTextureAnimate(EntityLivingBase entity) {
				return entity instanceof EntityPolaris && !((EntityPolaris) entity).getDaytime();
			}
		});
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return entity.worldObj.isDaytime() ? texture : empty;
	}
}
