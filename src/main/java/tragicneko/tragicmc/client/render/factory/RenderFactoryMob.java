package tragicneko.tragicmc.client.render.factory;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import tragicneko.tragicmc.client.render.mob.RenderMob;
import tragicneko.tragicmc.client.render.mob.RenderMobTransparent;

public class RenderFactoryMob implements IRenderFactory {
	
	public final ModelBase model;
	public final float shadow;
	public final String resName;
	public final float scale;
	
	public RenderFactoryMob(ModelBase model, float shadow, String resName, float scale) {
		this.model = model;
		this.shadow = shadow;
		this.resName = resName;
		this.scale = scale;
	}
	
	public RenderFactoryMob(ModelBase model, float shadow, String resName) {
		this(model, shadow, resName, 1.0F);
	}

	@Override
	public Render createRenderFor(RenderManager manager) {
		return new RenderMob(manager, this.model, this.shadow, this.resName, this.scale);
	}
}
