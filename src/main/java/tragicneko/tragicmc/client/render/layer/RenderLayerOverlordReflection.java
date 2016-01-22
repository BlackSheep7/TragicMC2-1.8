package tragicneko.tragicmc.client.render.layer;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;

public class RenderLayerOverlordReflection extends RenderLayerShield {
	
	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/OverlordCombatNoise.png");

	public RenderLayerOverlordReflection(RenderLiving render) {
		super(render, null);
	}

	@Override
	public boolean shouldShieldRender(EntityLivingBase entity) {
		return entity instanceof EntityOverlordCombat && ((EntityOverlordCombat) entity).getReflectionTicks() > 0;
	}

	@Override
	public ResourceLocation getShieldTexture(EntityLivingBase entity) {
		return texture;
	}

}
