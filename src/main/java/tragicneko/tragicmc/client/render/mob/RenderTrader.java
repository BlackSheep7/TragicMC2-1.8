package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelSimpleNeko;
import tragicneko.tragicmc.entity.mob.EntityTraderNeko;

public class RenderTrader extends RenderMob {

	private static final String cons = "tragicmc:textures/mobs/TraderNeko";
	private static final ResourceLocation texture = new ResourceLocation(cons + ".png");
	private static final ResourceLocation texture2 = new ResourceLocation(cons + "2.png");
	private static final ResourceLocation texture3 = new ResourceLocation(cons + "3.png");
	private static final ResourceLocation texture4 = new ResourceLocation(cons + "4.png");
	private static final ResourceLocation texture5 = new ResourceLocation(cons + "5.png");
	private static final ResourceLocation texture6 = new ResourceLocation(cons + "6.png");
	private static final ResourceLocation texture7 = new ResourceLocation(cons + "7.png");

	public RenderTrader(RenderManager rm) {
		super(rm, new ModelSimpleNeko(), 0.245F, "");
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityTraderNeko) entity);
	}

	private ResourceLocation getEntityTexture(EntityTraderNeko entity) {
		switch(entity.getTextureId())
		{
		default:
		case 0:
			return texture;
		case 1:
			return texture2;
		case 2:
			return texture3;
		case 3:
			return texture4;
		case 4:
			return texture5; 
		case 5:
			return texture6;
		case 6:
			return texture7;
		}
	}
}
