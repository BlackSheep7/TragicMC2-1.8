package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelBlock;

public class RenderWisp extends RenderMob {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/blocks/Transparency.png");

	public RenderWisp(RenderManager rm) {
		super(rm, new ModelBlock(), 0.275F, "");
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}

}
