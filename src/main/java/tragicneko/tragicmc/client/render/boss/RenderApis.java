package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelApis;
import tragicneko.tragicmc.client.render.layer.RenderLayerApisShield;

public class RenderApis extends RenderBoss
{
	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/ApisCombat2.png");

	public RenderApis(RenderManager rm) {
		super(rm, new ModelApis(), 0.556F);
		this.addLayer(new RenderLayerApisShield(this, new ModelApis()));
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		GL11.glScalef(1.0F, 1.0F, 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
