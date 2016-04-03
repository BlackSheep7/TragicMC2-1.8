package tragicneko.tragicmc.client.render.factory;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import tragicneko.tragicmc.client.render.RenderProjectile;

public class RenderFactoryProjectile implements IRenderFactory {
	
	public final Item item;
	public final int meta;
	public final float scale;
	
	public RenderFactoryProjectile(Item item, int meta, float scale) {
		this.item = item;
		this.meta = meta;
		this.scale = scale;
	}

	@Override
	public Render createRenderFor(RenderManager manager) {
		return new RenderProjectile(manager, this.item, this.meta, this.scale);
	}
}
