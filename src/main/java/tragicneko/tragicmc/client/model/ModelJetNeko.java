package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import tragicneko.tragicmc.entity.mob.EntityJetNeko;
import tragicneko.tragicmc.entity.mob.EntityNeko;

public class ModelJetNeko extends ModelBiped
{
	private ModelRenderer leftEar;
	private ModelRenderer rightEar;
	private ModelRenderer tail;
	private ModelRenderer rocketLauncher;
	private ModelRenderer rocketLauncher2;

	public ModelJetNeko()
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
		bipedRightArm.rotateAngleX = -0.2974289F;
		bipedRightArm.rotateAngleZ = 0.4461433F;
		rocketLauncher = new ModelRenderer(this, 24, 30);
		rocketLauncher.addBox(-2F, 6F, -7F, 5, 4, 15);
		rocketLauncher.rotateAngleX = 0.2974289F;
		bipedRightArm.addChild(rocketLauncher);
		ModelRenderer rocketLauncherBody = new ModelRenderer(this, 0, 44);
		rocketLauncherBody.addBox(-3F, 5F, -2F, 7, 6, 7);
		rocketLauncher.addChild(rocketLauncherBody);

		//Left Arm
		bipedLeftArm = new ModelRenderer(this, 40, 16);
		bipedLeftArm.addBox(0F, -2F, -2F, 2, 10, 2);
		bipedLeftArm.setRotationPoint(3F, 2F, 0F);
		bipedLeftArm.rotateAngleX = -0.2974289F;
		bipedLeftArm.rotateAngleZ = -0.4461433F;
		rocketLauncher2 = new ModelRenderer(this, 24, 30);
		rocketLauncher2.addBox(-2F, 6F, -7F, 5, 4, 15);
		rocketLauncher2.rotateAngleX = 0.2974289F;
		bipedLeftArm.addChild(rocketLauncher2);
		ModelRenderer rocketLauncherBody2 = new ModelRenderer(this, 0, 44);
		rocketLauncherBody2.addBox(-3F, 5F, -2F, 7, 6, 7);
		rocketLauncher2.addChild(rocketLauncherBody2);

		//Right Leg
		bipedRightLeg = new ModelRenderer(this, 0, 16);
		bipedRightLeg.addBox(-1.5F, 0F, -2F, 2, 12, 2);
		bipedRightLeg.setRotationPoint(-1F, 12F, 0F);
		ModelRenderer rightJet = new ModelRenderer(this, 36, 52);
		rightJet.addBox(-2F, 8.5F, -1.5F, 3, 4, 3);
		bipedRightLeg.addChild(rightJet);
		ModelRenderer rightJetBit = new ModelRenderer(this, 36, 52);
		rightJetBit.addBox(-2F, 12.5F, -1.5F, 2, 2, 2);
		rightJet.addChild(rightJetBit);

		//Left Leg
		bipedLeftLeg = new ModelRenderer(this, 0, 16);
		bipedLeftLeg.addBox(-1.5F, 0F, -2F, 2, 12, 2);
		bipedLeftLeg.setRotationPoint(2F, 12F, 0F);
		ModelRenderer leftJet = new ModelRenderer(this, 36, 52);
		leftJet.addBox(-2F, 8.5F, -1.5F, 3, 4, 3);
		bipedLeftLeg.addChild(leftJet);
		ModelRenderer leftJetBit = new ModelRenderer(this, 36, 52);
		leftJetBit.addBox(-2F, 12.5F, -1.5F, 2, 2, 2);
		leftJet.addChild(leftJetBit);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.isRiding = entity.isRiding();
		if (entity instanceof EntityNeko)
		{
			rocketLauncher.showModel = !((EntityNeko) entity).isProperDate();
			rocketLauncher2.showModel = !((EntityNeko) entity).isProperDate();
		}
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
		
		if (neko instanceof EntityJetNeko && ((EntityJetNeko) entity).getHoverTicks() > 0)
		{
			this.bipedLeftLeg.rotateAngleX = 0F;
			this.bipedRightLeg.rotateAngleX = 0F;
		}

		this.tail.rotateAngleY = 0.275F * this.simplifyAngle(entity.ticksExisted, 15.0F);

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
			else
			{
				bipedLeftArm.rotateAngleX = -0.2974289F;
				bipedLeftArm.rotateAngleZ = -0.4461433F;

				if (neko.getAttackTime() > 0)
				{
					this.bipedRightArm.rotateAngleX = 0.45F + -1.65F * this.simplifyAngle(neko.getThrowingTicks(), 30.0F);
					this.bipedRightArm.rotateAngleZ = 0.45F + 0.35F * this.simplifyAngle(neko.getThrowingTicks(), 30.0F);
				}
				else
				{
					bipedRightArm.rotateAngleX = -0.4833219F;
					bipedRightArm.rotateAngleZ = 0.4461433F;
				}
			}
		}
		else
		{
			bipedRightArm.rotateAngleZ = bipedLeftArm.rotateAngleZ = 0F;
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
