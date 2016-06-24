package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import tragicneko.tragicmc.entity.EntityMechaExo;

public class ModelMechaExo extends ModelBase
{
	private ModelRenderer aimAssist;
	private ModelRenderer body;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer rightShoulder;
	private ModelRenderer rightForearm;
	private ModelRenderer leftShoulder;
	private ModelRenderer leftForearm;

	public ModelMechaExo()
	{
		textureWidth = 64;
		textureHeight = 64;

		//Body
		body = new ModelRenderer(this, 0, 18);
		body.addBox(-4F, -1F, -1F, 8, 8, 4);
		ModelRenderer seat = new ModelRenderer(this, 0, 0);
		seat.addBox(-6F, -7F, 2F, 12, 8, 5);
		body.addChild(seat);
		ModelRenderer packLeft = new ModelRenderer(this, 40, 48);
		packLeft.addBox(-5F, -6F, 7F, 3, 3, 3);
		body.addChild(packLeft);
		ModelRenderer packRight = new ModelRenderer(this, 40, 48);
		packRight.addBox(1F, -6F, 7F, 3, 3, 3);
		body.addChild(packRight);

		//Aim stuff
		ModelRenderer aimBar = new ModelRenderer(this, 40, 48); //aimbar will only rotate with the body, aimAssist thing will rotate independently from that point
		aimBar.addBox(-1F, -1F, -5F, 2, 1, 4);		
		aimAssist = new ModelRenderer(this, 40, 48);
		aimAssist.addBox(-4F, -6F, -6F, 8, 6, 1);
		aimAssist.setRotationPoint(0F, 0F, 0F);
		ModelRenderer laser = new ModelRenderer(this, 40, 48);
		laser.addBox(-3F, -5F, -8F, 6, 4, 2);
		aimAssist.addChild(laser);
		ModelRenderer laser2 = new ModelRenderer(this, 40, 48);
		laser2.addBox(-1.5F, -6F, -13F, 3, 2, 7);
		aimAssist.addChild(laser2);
		//ModelRenderer laser3 = new ModelRenderer(this, 0, 32); //this will be semi-transparent
		//laser3.addBox(-2F, -10F, -8F, 4, 4, 1);
		//aimAssist.addChild(laser3);
		ModelRenderer laser4 = new ModelRenderer(this, 40, 48);
		laser4.addBox(-3F, -5F, -10F, 2, 2, 2);
		aimAssist.addChild(laser4);
		ModelRenderer laser5 = new ModelRenderer(this, 30, 36);
		laser5.addBox(-1F, -5F, -18F, 2, 2, 12);
		aimAssist.addChild(laser5);
		aimBar.addChild(aimAssist);
		body.addChild(aimBar);

		//Right Leg
		rightLeg = new ModelRenderer(this, 16, 32);
		rightLeg.addBox(-2F, -2F, -1F, 3, 7, 3);
		rightLeg.setRotationPoint(-3F, 7F, 0F);
		ModelRenderer rightLowerLeg = new ModelRenderer(this, 42, 0);
		rightLowerLeg.addBox(-2.5F, 5F, -1F, 4, 12, 4);
		rightLeg.addChild(rightLowerLeg);
		body.addChild(rightLeg);

		//Left Leg
		leftLeg = new ModelRenderer(this, 16, 32);
		leftLeg.addBox(-1F, -2F, -1F, 3, 7, 3);
		leftLeg.setRotationPoint(3F, 7F, 0F);
		ModelRenderer leftLowerLeg = new ModelRenderer(this, 42, 0);
		leftLowerLeg.addBox(-1.5F, 5F, -1F, 4, 12, 4);
		leftLeg.addChild(leftLowerLeg);
		body.addChild(leftLeg);

		//Right Shoulder, Upper and Fore Arm, and Fist
		rightShoulder = new ModelRenderer(this, 28, 18);
		rightShoulder.addBox(-7F, -3F, -3F, 8, 8, 8);
		rightShoulder.setRotationPoint(-7F, -7F, 0F);
		ModelRenderer rightUpperArm = new ModelRenderer(this, 42, 0);
		rightUpperArm.addBox(-5F, 5F, -1F, 4, 8, 4);
		rightShoulder.addChild(rightUpperArm);
		rightForearm = new ModelRenderer(this, 34, 42);
		rightForearm.addBox(-6F, 13F, -2F, 6, 12, 6);
		rightShoulder.addChild(rightForearm);
		ModelRenderer rightFist = new ModelRenderer(this, 0, 46);
		rightFist.addBox(-7F, 25F, -3F, 8, 6, 8);
		rightForearm.addChild(rightFist);
		body.addChild(rightShoulder);

		//Left Shoulder, Upper and Fore Arm, and Fist
		leftShoulder = new ModelRenderer(this, 28, 18);
		leftShoulder.addBox(-1F, -3F, -3F, 8, 8, 8);
		leftShoulder.setRotationPoint(7F, -7F, 0F);
		ModelRenderer leftUpperArm = new ModelRenderer(this, 42, 0);
		leftUpperArm.addBox(1F, 5F, -1F, 4, 8, 4);
		leftShoulder.addChild(leftUpperArm);
		leftForearm = new ModelRenderer(this, 34, 42);
		leftForearm.addBox(0F, 13F, -2F, 6, 12, 6);
		leftShoulder.addChild(leftForearm);
		ModelRenderer leftFist = new ModelRenderer(this, 0, 46);
		leftFist.addBox(-1F, 25F, -3F, 8, 6, 8);
		leftForearm.addChild(leftFist);
		body.addChild(leftShoulder);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		body.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		this.leftLeg.rotateAngleX = -0.75F * this.simplifyAngle(entity.ticksExisted, 20.0F) * f1;
		this.leftLeg.rotateAngleZ = 0F;
		this.rightLeg.rotateAngleX = 0.75F * this.simplifyAngle(entity.ticksExisted, 20.0F) * f1;
		this.rightLeg.rotateAngleZ = 0F;

		this.leftShoulder.rotateAngleX = -1.25F * this.simplifyAngle(entity.ticksExisted, 20.0F) * f1;
		this.leftShoulder.rotateAngleZ = 0F;
		this.rightShoulder.rotateAngleX = 1.25F * this.simplifyAngle(entity.ticksExisted, 20.0F) * f1;
		this.rightShoulder.rotateAngleZ = 0F;

		this.body.rotateAngleY = 0.35F * this.simplifyAngle(entity.ticksExisted, 20.0F) * f1;
		this.body.rotateAngleX = 0F;

		this.aimAssist.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.aimAssist.rotateAngleX = f4 / (180F / (float)Math.PI);

		if (entity instanceof EntityMechaExo)
		{
			EntityMechaExo exo = (EntityMechaExo) entity;

			if (exo.getThrustTicks() > 0)
			{
				body.rotateAngleX = 0.25F;
				leftShoulder.rotateAngleX = -1.45F + (exo.worldObj.rand.nextFloat() - exo.worldObj.rand.nextFloat()) * 0.05F;
				leftShoulder.rotateAngleZ = (exo.worldObj.rand.nextFloat() - exo.worldObj.rand.nextFloat()) * 0.05F;
				rightShoulder.rotateAngleX = -1.45F + (exo.worldObj.rand.nextFloat() - exo.worldObj.rand.nextFloat()) * 0.05F;
				rightShoulder.rotateAngleZ = (exo.worldObj.rand.nextFloat() - exo.worldObj.rand.nextFloat()) * 0.05F;

				rightLeg.rotateAngleX = -0.55F;
				leftLeg.rotateAngleX = 0.15F;
			}
			else if (exo.getAttackTime() > 0)
			{
				body.rotateAngleY = 0.85F * this.simplifyAngle(exo.getAttackTime(), 10.0F);
				rightShoulder.rotateAngleX = -0.85F + 1.25F * this.simplifyAngle(exo.getAttackTime(), 10.0F);
				leftShoulder.rotateAngleX = 0F;
			}
		}
	}

	private float simplifyAngle(float par1, float par2)
	{
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}

}
