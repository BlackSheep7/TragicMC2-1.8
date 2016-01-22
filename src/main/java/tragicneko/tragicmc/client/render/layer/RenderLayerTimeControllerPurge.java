package tragicneko.tragicmc.client.render.layer;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.entity.boss.EntityTimeController;

public class RenderLayerTimeControllerPurge extends RenderLayerShield {
	
	private final ResourceLocation texture;

	public RenderLayerTimeControllerPurge(RenderLiving render, ResourceLocation loc) {
		super(render, null);
		this.texture = loc;
	}

	@Override
	public boolean shouldShieldRender(EntityLivingBase entity) {
		return entity instanceof EntityTimeController && ((EntityTimeController) entity).getPurgeTicks() > 0;
	}

	@Override
	public ResourceLocation getShieldTexture(EntityLivingBase entity) {
		return texture;
	}

}
