package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import tragicneko.tragicmc.entity.mob.EntityJetNeko;
import tragicneko.tragicmc.entity.mob.EntityNeko;
import tragicneko.tragicmc.entity.mob.EntityScienceNeko;

public class ModelProfessorNekoid extends ModelBiped
{
	public ModelProfessorNekoid()
	{
		textureWidth = 64;
		textureHeight = 64;

		//Head and Ears
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-3F, -6F, -4F, 6, 6, 6);
		bipedHead.setRotationPoint(0F, 0F, 0F);
		bipedHeadwear = new ModelRenderer(this, 0, 0);
		bipedHeadwear.addBox(0F, 0F, 0F, 0, 0, 0);

		//Body and Tail
		bipedBody = new ModelRenderer(this, 16, 16);
		bipedBody.addBox(-3F, 0F, -2F, 6, 12, 3);
		bipedBody.setRotationPoint(0F, 0F, 0F);

		//Right Arm
		bipedRightArm = new ModelRenderer(this, 40, 16);
		bipedRightArm.addBox(-2F, -2F, -2F, 2, 10, 2);
		bipedRightArm.setRotationPoint(-3F, 2F, 0F);

		//Left Arm
		bipedLeftArm = new ModelRenderer(this, 40, 16);
		bipedLeftArm.addBox(0F, -2F, -2F, 2, 10, 2);
		bipedLeftArm.setRotationPoint(3F, 2F, 0F);

		//Right Leg
		bipedRightLeg = new ModelRenderer(this, 0, 16);
		bipedRightLeg.addBox(-1.5F, 0F, -2F, 2, 12, 2);
		bipedRightLeg.setRotationPoint(-1F, 12F, 0F);

		//Left Leg
		bipedLeftLeg = new ModelRenderer(this, 0, 16);
		bipedLeftLeg.addBox(-1.5F, 0F, -2F, 2, 12, 2);
		bipedLeftLeg.setRotationPoint(2F, 12F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.isRiding = entity.isRiding();
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		this.bipedHead.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.bipedHead.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.bipedLeftLeg.rotateAngleX = -1.15F * this.simplifyAngle(f, 15.0F) * f1;
		this.bipedRightLeg.rotateAngleX = 1.15F * this.simplifyAngle(f, 15.0F) * f1;

		bipedRightArm.rotateAngleX = (-0.2F + 1.5F * this.simplifyAngle(f, 13.0F)) * f1;
		bipedLeftArm.rotateAngleX = (-0.2F - 1.5F * this.simplifyAngle(f, 13.0F)) * f1;

		bipedLeftArm.rotateAngleZ = -0.2361433F;
		bipedRightArm.rotateAngleZ = 0.2361433F;

		if (this.isRiding)
		{
			bipedRightArm.rotateAngleX = -1.11F;
			bipedLeftArm.rotateAngleX = -1.11F;
			bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
			bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
			bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
			bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
		}
		else
		{
			bipedRightLeg.rotateAngleY = 0F;
			bipedLeftLeg.rotateAngleY = 0F;
		}
	}

	private float simplifyAngle(float par1, float par2)
	{
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}
}
