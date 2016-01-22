package tragicneko.tragicmc.client.render.alpha;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelOverlordCombat;
import tragicneko.tragicmc.client.render.boss.RenderBoss;
import tragicneko.tragicmc.client.render.layer.RenderLayerOverlordReflection;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCombat;

public class RenderOverlordCombat extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/OverlordCombat.png");

	public RenderOverlordCombat(RenderManager rm) {
		super(rm, new ModelOverlordCombat(), 0.756F, 2.25F);
		this.addLayer(new RenderLayerOverlordReflection(this));
	}

	@Override
	protected void rotateCorpse(EntityLivingBase entity, float par1, float par2, float par3)
	{
		if (entity.deathTime > 0) return;
		super.rotateCorpse(entity, par1, par2, par3);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityOverlordCombat) entity);
	}

	public ResourceLocation getEntityTexture(EntityOverlordCombat core)
	{
		return texture;
	}

}
