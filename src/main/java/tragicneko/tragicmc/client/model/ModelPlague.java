package tragicneko.tragicmc.client.model;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPlague extends ModelBase
{
	private ModelRenderer center;
	private ModelRenderer shape;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;
	private ModelRenderer shape6;
	private ModelRenderer shape7;
	private ModelRenderer shape8;
	private ModelRenderer shape9;
	private ModelRenderer shape10;

	private ModelRenderer[] shapeArray = new ModelRenderer[10];

	public ModelPlague()
	{
		textureWidth = 64;
		textureHeight = 32;
		
		center = new ModelRenderer(this, 0, 0);
		center.addBox(0F, 4F, 0F, 0, 0, 0);
		center.setRotationPoint(0F, 6F, 0F);

		shape = new ModelRenderer(this, 0, 0);
		shape.addBox(0F, 0F, 0F, 1, 1, 1);
		center.addChild(shape);

		shape2 = new ModelRenderer(this, 0, 0);
		shape2.addBox(0F, 5F, 5F, 1, 1, 1);
		center.addChild(shape2);

		shape3 = new ModelRenderer(this, 0, 0);
		shape3.addBox(-1F, 0F, -4F, 1, 1, 1);
		center.addChild(shape3);

		shape4 = new ModelRenderer(this, 0, 0);
		shape4.addBox(3F, -2F, 1F, 1, 1, 1);
		center.addChild(shape4);

		shape5 = new ModelRenderer(this, 0, 0);
		shape5.addBox(-5F, 5F, -6F, 1, 1, 1);
		center.addChild(shape5);

		shape6 = new ModelRenderer(this, 0, 0);
		shape6.addBox(2F, 7F, 0F, 1, 1, 1);
		center.addChild(shape6);

		shape7 = new ModelRenderer(this, 0, 0);
		shape7.addBox(-3F, 0F, 3F, 1, 1, 1);
		center.addChild(shape7);

		shape8 = new ModelRenderer(this, 0, 0);
		shape8.addBox(0F, -4F, 6F, 1, 1, 1);
		center.addChild(shape8);

		shape9 = new ModelRenderer(this, 0, 0);
		shape9.addBox(3F, 2F, -7F, 1, 1, 1);
		center.addChild(shape9);

		shape10 = new ModelRenderer(this, 0, 0);
		shape10.addBox(-6F, -2F, 0F, 1, 1, 1);
		center.addChild(shape10);

		this.shapeArray = new ModelRenderer[] {shape, shape2, shape3, shape4, shape5, shape6, shape7, shape8, shape9, shape10};
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		center.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		int j = entity.ticksExisted;

		if (j % 2 == 0)
		{
			ModelRenderer cube = this.shapeArray[entity.worldObj.rand.nextInt(this.shapeArray.length)];
			if (j % 7 == 0)
			{
				cube.offsetX += MathHelper.cos(j + entity.getEntityId()) * 0.45F;

			}
			else if (j % 13 == 0)
			{
				cube.offsetY += MathHelper.cos(j * entity.getEntityId()) * 0.45F;

			}
			else if (j % 3 == 0)
			{
				cube.offsetZ += MathHelper.cos(j - entity.getEntityId()) * 0.45F;
			}

			cube.offsetX += MathHelper.cos(j + entity.getEntityId()) * 0.25F;
			cube.offsetY += MathHelper.cos(j * entity.getEntityId()) * 0.25F;
			cube.offsetZ += MathHelper.cos(j - entity.getEntityId()) * 0.25F;

			if (Math.abs(cube.offsetX) > 0.5F) cube.offsetX = 0.0F;
			if (Math.abs(cube.offsetY) > 0.5F) cube.offsetY = 0.0F;
			if (Math.abs(cube.offsetZ) > 0.5F) cube.offsetZ = 0.0F;
		}
		
		center.rotateAngleX = this.simplifyAngle(j, 40.0F) * 0.05F;
		center.rotateAngleY = this.simplifyAngle(j + 72, 40.0F) * 36.25F;
	}

	private float simplifyAngle(float par1, float par2)
	{
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}

}
