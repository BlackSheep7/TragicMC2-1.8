package tragicneko.tragicmc.client.render.factory;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import tragicneko.tragicmc.client.render.mob.RenderMobTransparent;

public class RenderFactoryMobTransparent extends RenderFactoryMob {

	public final float trans;
	
	public RenderFactoryMobTransparent(ModelBase model, float shadow, String resName, float scale, float trans) {
		super(model, shadow, resName, scale);
		this.trans = trans;
	}
	
	@Override
	public Render createRenderFor(RenderManager manager) {
		return new RenderMobTransparent(manager, this.model, this.shadow, this.resName, this.scale, this.trans);
	}
}
