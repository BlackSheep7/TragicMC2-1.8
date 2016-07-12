package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelProfessorNekoid;

public class RenderProfessorNekoid extends RenderBoss {
	
	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/TragicNeko.png");

	public RenderProfessorNekoid(RenderManager rm) {
		super(rm, new ModelProfessorNekoid(), 0.625F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}

}
