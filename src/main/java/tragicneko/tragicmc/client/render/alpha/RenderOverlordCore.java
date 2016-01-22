package tragicneko.tragicmc.client.render.alpha;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelOverlordCore;
import tragicneko.tragicmc.client.render.boss.RenderBoss;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;

public class RenderOverlordCore extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/OverlordCore.png");
	private static final ResourceLocation damagedTexture = new ResourceLocation("tragicmc:textures/mobs/OverlordCoreDamaged.png");
	private static final ResourceLocation enderDragonExplodingTextures = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");

	public RenderOverlordCore(RenderManager rm) {
		super(rm, new ModelOverlordCore(), 0.756F, 2.25F);
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
		GL11.glTranslatef(0F, 0.5F, 0F);
	}

	@Override
	protected void renderModel(EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		EntityOverlordCore core = (EntityOverlordCore) entity;

		this.bindEntityTexture(core);

		if (!core.isInvisible() && core.getVulnerableTicks() == 0 && core.getTransformationTicks() <= 60 && core.getTransformationTicks() >= 20)
		{
			this.mainModel.render(core, par2, par3, par4, par5, par6, par7);
		}
		else if (!core.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
		{
			float f = 1.0F;
			if (core.deathTime > 0)
			{
				f = MathHelper.cos(core.ticksExisted * 0.2F) * 0.8F + 0.2F;
				GlStateManager.translate(entity.getRNG().nextGaussian() * 0.3, entity.getRNG().nextGaussian() * 0.3, entity.getRNG().nextGaussian() * 0.3);
			}
			GlStateManager.color(f, f, f, 1.0F);
			GlStateManager.enableNormalize();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(770, 771);
			this.mainModel.render(entity, par2, par3, par4, par5, par6, par7);
			GlStateManager.disableBlend();
			GlStateManager.disableNormalize();
		}
		else
		{
			this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, core);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityOverlordCore) entity);
	}

	public ResourceLocation getEntityTexture(EntityOverlordCore core)
	{
		return core.getVulnerableTicks() > 0 && core.deathTime == 0 || core.getTransformationTicks() <= 60 && core.getTransformationTicks() >= 20 ? damagedTexture : texture;
	}

}
