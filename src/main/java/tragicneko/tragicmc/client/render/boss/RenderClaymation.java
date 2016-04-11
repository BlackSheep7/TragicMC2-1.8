package tragicneko.tragicmc.client.render.boss;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tragicneko.tragicmc.client.model.ModelApis;
import tragicneko.tragicmc.client.model.ModelClaymation;
import tragicneko.tragicmc.client.model.ModelCustomGolem;
import tragicneko.tragicmc.client.model.ModelDeathReaper;
import tragicneko.tragicmc.client.model.ModelJabba;
import tragicneko.tragicmc.client.model.ModelKitsune2;
import tragicneko.tragicmc.client.model.ModelMinotaur;
import tragicneko.tragicmc.client.model.ModelNorVox;
import tragicneko.tragicmc.client.model.ModelRagr;
import tragicneko.tragicmc.client.model.ModelStinKing;
import tragicneko.tragicmc.client.render.layer.RenderLayerAnimatedTexture;
import tragicneko.tragicmc.entity.boss.EntityClaymation;

public class RenderClaymation extends RenderBoss {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Claymation.png");

	private static final ModelBase[] models = new ModelBase[] {new ModelClaymation(), new ModelMinotaur(), new ModelApis(), new ModelStinKing(), new ModelNorVox(), new ModelJabba(),
		new ModelRagr(), new ModelDeathReaper(), new ModelKitsune2(), new ModelCustomGolem()};

	public RenderClaymation(RenderManager rm) {
		super(rm, models[0], 0.556F);
		this.addLayer(new RenderLayerAnimatedTexture(this, texture));
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float par2)
	{
		if (this.mainModel != this.getModelFromClaymationForm((EntityClaymation) entity)) this.mainModel = this.getModelFromClaymationForm((EntityClaymation) entity);
		if (this.scale != this.getScaleFromClaymationForm((EntityClaymation) entity)) this.scale = this.getScaleFromClaymationForm((EntityClaymation) entity);
		GL11.glScalef(scale, scale, scale);
	}

	private float getScaleFromClaymationForm(EntityClaymation clay) {
		switch(clay.getEntityForm())
		{
		case 3:
			return 1.625F;
		case 0:
		case 4:
			return 1.445F;
		default:
			return 1.0F;
		}
	}

	private ModelBase getModelFromClaymationForm(EntityClaymation clay) {
		return models[clay.getEntityForm()];
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return empty;
	}

}
