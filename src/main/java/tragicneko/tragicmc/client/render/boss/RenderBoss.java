package tragicneko.tragicmc.client.render.boss;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.entity.boss.TragicBoss;

public abstract class RenderBoss extends RenderLiving {

	protected static final ResourceLocation empty = new ResourceLocation("tragicmc:textures/blocks/Transparency.png");
	protected float scale;

	public RenderBoss(RenderManager rm, ModelBase model, float shadowSize, float scale) {
		super(rm, model, shadowSize);
		this.scale = scale;
	}

	public RenderBoss(RenderManager rm, ModelBase model, float shadowSize) {
		this(rm, model, shadowSize, 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		GL11.glScalef(scale, scale, scale);
	}

	public void doRender(TragicBoss boss, double par2, double par4, double par6, float par8, float par9)
	{
		if (boss.deathTime > 15) return;
		BossStatus.setBossStatus(boss, true);
		super.doRender(boss, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.doRender((TragicBoss)entity, par2, par4, par6, par8, par9);
	}

}
