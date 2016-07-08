package tragicneko.tragicmc.client.render.mob;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelAssaultNeko;
import tragicneko.tragicmc.client.render.layer.RenderLayerShield;
import tragicneko.tragicmc.entity.mob.EntityAssaultNeko;

public class RenderAssaultNeko extends RenderMob
{
	private static final ResourceLocation shieldTexture = new ResourceLocation("tragicmc:textures/mobs/AssaultNeko.png");
	
	public RenderAssaultNeko(RenderManager rm) {
		super(rm, new ModelAssaultNeko(), 0.556F, "AssaultNeko", 1.145F);
		this.addLayer(new RenderLayerShield(this, new ModelAssaultNeko()) {
			@Override
			public boolean shouldShieldRender(EntityLivingBase entity) {
				return entity instanceof EntityAssaultNeko && ((EntityAssaultNeko) entity).getShieldTicks() > 0;
			}

			@Override
			public ResourceLocation getShieldTexture(EntityLivingBase entity) {
				return shieldTexture;
			}
		});
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		GL11.glScalef(1.0F, 1.0F, 1.0F);
	}
}
