package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelYeti2;

public class RenderYeti extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Yeti2.png");

	public RenderYeti(RenderManager rm) {
		super(rm, new ModelYeti2(), 0.485F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return texture;
	}

}
