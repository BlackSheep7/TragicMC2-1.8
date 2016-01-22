package tragicneko.tragicmc.client.render.layer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.entity.boss.EntityApis;

public class RenderLayerApisShield extends RenderLayerShield {
	
	private static final ResourceLocation combatTexture = new ResourceLocation("tragicmc:textures/mobs/ApisCombat3.png");

	public RenderLayerApisShield(RenderLiving render, ModelBase model) {
		super(render, model);
	}

	@Override
	public boolean shouldShieldRender(EntityLivingBase entity) {
		return entity instanceof EntityApis && ((EntityApis) entity).isReflecting();
	}

	@Override
	public ResourceLocation getShieldTexture(EntityLivingBase entity) {
		return combatTexture;
	}

}
