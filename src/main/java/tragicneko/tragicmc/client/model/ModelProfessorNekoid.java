package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import tragicneko.tragicmc.entity.boss.EntityProfessorNekoid;

public class ModelProfessorNekoid extends ModelBiped
{
	private ModelRenderer rayGun;
	
	public ModelProfessorNekoid()
	{
		textureWidth = 64;
		textureHeight = 64;
		
		bipedHeadwear = new ModelRenderer(this, 0, 0);
		bipedHeadwear.addBox(0F, 0F, 0F, 0, 0, 0);

		//Head and hat
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-3F, -6F, -4F, 6, 6, 6);
		bipedHead.setRotationPoint(0F, 0F, 0F);
		
		ModelRenderer headphone = new ModelRenderer(this, 24, 36);
		headphone.addBox(-4F, -4F, -2.5F, 8, 2, 2);
		bipedHead.addChild(headphone);
		
		ModelRenderer headphone2 = new ModelRenderer(this, 24, 36);
		headphone2.addBox(-3.5F, -6.5F, -2F, 7, 3, 1);
		bipedHead.addChild(headphone2);

		//Body
		bipedBody = new ModelRenderer(this, 16, 16);
		bipedBody.addBox(-3F, 0F, -2F, 6, 12, 3);
		bipedBody.setRotationPoint(0F, 0F, 0F);

		//Right Arm
		bipedRightArm = new ModelRenderer(this, 40, 16);
		bipedRightArm.addBox(-2F, -2F, -2F, 2, 10, 2);
		bipedRightArm.setRotationPoint(-3F, 2F, 0F);
		
		ModelRenderer rightPauldron = new ModelRenderer(this, 24, 36);
		rightPauldron.addBox(-5F, -2F, -2.5F, 4, 2, 3);
		bipedRightArm.addChild(rightPauldron);
		
		rayGun = new ModelRenderer(this, 24, 46);
		rayGun.addBox(-1.5F, 6F, -2.5F, 1, 1, 4);
		bipedRightArm.addChild(rayGun);
		ModelRenderer rayGunSight = new ModelRenderer(this, 24, 36);
		rayGunSight.addBox(-2F, 7.5F, -7.5F, 2, 1, 2);
		rayGun.addChild(rayGunSight);
		ModelRenderer rayGunBody = new ModelRenderer(this, 24, 46);
		rayGunBody.addBox(-2.5F, 5F, -5.5F, 3, 7, 3);
		rayGun.addChild(rayGunBody);
		ModelRenderer rayGunNeedle = new ModelRenderer(this, 24, 36);
		rayGunNeedle.addBox(-3F, 8F, -4.5F, 4, 6, 3);
		rayGun.addChild(rayGunNeedle);
		ModelRenderer rayGunTip = new ModelRenderer(this, 24, 46);
		rayGunTip.addBox(-2F, 13F, -5F, 2, 5, 2);
		rayGun.addChild(rayGunTip);
		ModelRenderer rayGunTip2 = new ModelRenderer(this, 24, 36);
		rayGunTip2.addBox(-3.5F, 18F, -6.5F, 5, 2, 5);
		rayGun.addChild(rayGunTip2);

		//Left Arm
		bipedLeftArm = new ModelRenderer(this, 40, 16);
		bipedLeftArm.addBox(0F, -2F, -2F, 2, 10, 2);
		bipedLeftArm.setRotationPoint(3F, 2F, 0F);
		
		ModelRenderer leftPauldron = new ModelRenderer(this, 24, 36);
		leftPauldron.addBox(1F, -2F, -2.5F, 4, 2, 3);
		bipedLeftArm.addChild(leftPauldron);

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
		this.rayGun.isHidden = !(entity instanceof EntityProfessorNekoid && ((EntityProfessorNekoid) entity).getBlasterTicks() == 0);
	
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
		
		if (entity instanceof EntityProfessorNekoid)
		{
			EntityProfessorNekoid nekoid = (EntityProfessorNekoid) entity;
			
			if (this.isRiding)
			{
				bipedRightArm.rotateAngleX = -0.86F;
				bipedLeftArm.rotateAngleX = -0.86F;
				
				bipedRightLeg.rotateAngleX = -1.3F;
				bipedLeftLeg.rotateAngleX = -1.3F;
				
				if (nekoid.getCommandTicks() <= 20)
				{
					bipedLeftArm.rotateAngleX = -1.5F + this.simplifyAngle(nekoid.ticksExisted, 20.0F) * -0.3F;
				}
			}
			else
			{
				if (nekoid.getTitanfallTicks() <= 60)
				{
					bipedRightArm.rotateAngleX = -2.6F;
					bipedRightArm.rotateAngleZ = -0.4F;
					
					bipedLeftArm.rotateAngleX = -2.6F;
					bipedLeftArm.rotateAngleZ = 0.4F;
				}
			}
			
			if (nekoid.getBlasterTicks() == 0)
			{
				bipedRightArm.rotateAngleX = -1.54F;
			}
		}
	}

	private float simplifyAngle(float par1, float par2)
	{
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}
}
