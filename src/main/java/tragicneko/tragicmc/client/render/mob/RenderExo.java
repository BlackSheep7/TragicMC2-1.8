package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelMechaExo;
import tragicneko.tragicmc.entity.EntityMechaExo;

public class RenderExo extends RenderMob {
	
	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/MechaExo.png");
	private static final ResourceLocation varTexture = new ResourceLocation("tragicmc:textures/mobs/MechaExoVariant.png");

	public RenderExo(RenderManager rm) {
		super(rm, new ModelMechaExo(), 0.625F, "", 1.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityMechaExo) entity);
	}
	
	protected ResourceLocation getEntityTexture(EntityMechaExo exo) {
		return exo.isVariant() ? varTexture : texture;
	}
}
