package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import tragicneko.tragicmc.entity.mob.EntityJetNeko;
import tragicneko.tragicmc.entity.mob.EntityNeko;
import tragicneko.tragicmc.entity.mob.EntityScienceNeko;

public class ModelScienceNeko extends ModelBiped
{
	private ModelRenderer leftEar;
	private ModelRenderer rightEar;
	private ModelRenderer tail;
	private ModelRenderer rayGun;

	public ModelScienceNeko()
	{
		textureWidth = 64;
		textureHeight = 64;

		//Head and Ears
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-3F, -6F, -4F, 6, 6, 6);
		bipedHead.setRotationPoint(0F, 0F, 0F);
		leftEar = new ModelRenderer(this, 9, 38);
		leftEar.addBox(-1F, -7.5F, -4F, 3, 3, 1);
		leftEar.rotateAngleX = -0.3346075F;
		leftEar.rotateAngleZ = 0.4461433F;
		bipedHead.addChild(leftEar);
		rightEar = new ModelRenderer(this, 0, 38);
		rightEar.addBox(-2.5F, -7.5F, -4F, 3, 3, 1);
		rightEar.rotateAngleX = -0.3346075F;
		rightEar.rotateAngleZ = -0.4461433F;
		bipedHead.addChild(rightEar);
		bipedHeadwear = new ModelRenderer(this, 0, 0);
		bipedHeadwear.addBox(0F, 0F, 0F, 0, 0, 0);

		//Body and Tail
		bipedBody = new ModelRenderer(this, 16, 16);
		bipedBody.addBox(-3F, 0F, -2F, 6, 12, 3);
		bipedBody.setRotationPoint(0F, 0F, 0F);

		tail = new ModelRenderer(this, 40, 0);
		tail.addBox(-1F, -1F, 7F, 2, 8, 2);
		tail.rotateAngleX = -0.7063936F;
		bipedBody.addChild(tail);
		ModelRenderer tailTip = new ModelRenderer(this, 40, 0);
		tailTip.addBox(-1F, -6F, 6.5F, 2, 8, 2);
		tailTip.rotateAngleX = 0.3717861F;
		tail.addChild(tailTip);

		//Right Arm
		bipedRightArm = new ModelRenderer(this, 40, 16);
		bipedRightArm.addBox(-2F, -2F, -2F, 2, 10, 2);
		bipedRightArm.setRotationPoint(-3F, 2F, 0F);
		rayGun = new ModelRenderer(this, 36, 52);
		rayGun.addBox(-1.5F, 7F, -2.5F, 1, 1, 4);
		bipedRightArm.addChild(rayGun);
		ModelRenderer rayGunBody = new ModelRenderer(this, 36, 40);
		rayGunBody.addBox(-2.5F, 5F, -5.5F, 3, 6, 3);
		rayGun.addChild(rayGunBody);
		ModelRenderer rayGunSight = new ModelRenderer(this, 36, 52);
		rayGunSight.addBox(-2F, 7.5F, -7.5F, 2, 1, 2);
		rayGun.addChild(rayGunSight);
		ModelRenderer rayGunNeedle = new ModelRenderer(this, 36, 52);
		rayGunNeedle.addBox(-1.5F, 11F, -4.5F, 1, 3, 1);
		rayGun.addChild(rayGunNeedle);
		ModelRenderer rayGunTip = new ModelRenderer(this, 36, 40);
		rayGunTip.addBox(-2F, 14F, -5F, 2, 2, 2);
		rayGun.addChild(rayGunTip);

		//Left Arm
		bipedLeftArm = new ModelRenderer(this, 40, 16);
		bipedLeftArm.addBox(0F, -2F, -2F, 2, 10, 2);
		bipedLeftArm.setRotationPoint(3F, 2F, 0F);
		bipedLeftArm.rotateAngleZ = -0.4461433F;

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
		if (entity instanceof EntityNeko) rayGun.showModel = !((EntityNeko) entity).isProperDate();
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		this.bipedHead.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.bipedHead.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.bipedLeftLeg.rotateAngleX = -1.15F * this.simplifyAngle(f, 15.0F) * f1;
		this.bipedRightLeg.rotateAngleX = 1.15F * this.simplifyAngle(f, 15.0F) * f1;

		EntityNeko neko = (EntityNeko) entity;
		this.tail.rotateAngleY = 0.275F * this.simplifyAngle(entity.ticksExisted, 15.0F);

		if (neko instanceof EntityScienceNeko && neko.isAboutToFire() && ((EntityScienceNeko) neko).getTargetID() > 0)
		{
			bipedRightArm.rotateAngleX = -1.175F;
		}
		else
		{
			bipedRightArm.rotateAngleX = (-0.2F + 1.5F * this.simplifyAngle(f, 13.0F)) * f1;
			bipedLeftArm.rotateAngleX = (-0.2F - 1.5F * this.simplifyAngle(f, 13.0F)) * f1;
		}

		bipedLeftArm.rotateAngleZ = -0.2361433F;
		bipedRightArm.rotateAngleZ = 0.2361433F;

		if (neko.getFlickTime() > 0)
		{
			this.rightEar.rotateAngleZ = -0.446F + 0.02875F * this.simplifyAngle(entity.ticksExisted, 5.0F);
			this.leftEar.rotateAngleZ = 0.446F + -0.02875F * this.simplifyAngle(entity.ticksExisted, 5.0F);
			this.rightEar.offsetY = 0.0175F + -0.0175F * this.simplifyAngle(entity.ticksExisted, 5.0F);
			this.leftEar.offsetY = 0.0175F + -0.0175F * this.simplifyAngle(entity.ticksExisted, 5.0F);
		}
		else
		{
			this.rightEar.rotateAngleZ = -0.4461433F;
			this.leftEar.rotateAngleZ = 0.4461433F;
			this.rightEar.offsetY = 0.0F;
			this.rightEar.offsetY = 0.0F;
		}

		if (!neko.isProperDate())
		{
			if (neko.getThrowingTicks() > 0)
			{
				this.bipedLeftArm.rotateAngleX = -0.45F + -1.65F * this.simplifyAngle(neko.getThrowingTicks(), 30.0F);
				this.bipedLeftArm.rotateAngleZ = -0.45F + 0.35F * this.simplifyAngle(neko.getThrowingTicks(), 30.0F);
			}
			else if (neko.getAttackTime() > 0)
			{
				bipedLeftArm.rotateAngleX = -0.2F + 1.8F * this.simplifyAngle(neko.getAttackTime(), 10.0F);
			}
		}
		else
		{
			bipedLeftArm.rotateAngleZ = -0.2361433F;
			bipedRightArm.rotateAngleZ = 0.2361433F;

			bipedRightArm.rotateAngleX = (-0.2F + 1.5F * this.simplifyAngle(f, 13.0F)) * f1;
			bipedLeftArm.rotateAngleX = (-0.2F - 1.5F * this.simplifyAngle(f, 13.0F)) * f1;
		}

		if (this.isRiding)
		{
			bipedRightArm.rotateAngleX = -1.11F;
			bipedLeftArm.rotateAngleX = -1.11F;
			bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
			bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
			bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
			bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
		}
	}

	private float simplifyAngle(float par1, float par2)
	{
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}
}
