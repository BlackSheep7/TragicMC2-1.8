package tragicneko.tragicmc.client.render;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLLog;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderSoulChest extends TileEntitySpecialRenderer
{
	private static final ResourceLocation doubleTexture = new ResourceLocation("tragicmc:textures/entities/SoulChestDouble.png");
	private static final ResourceLocation singleTexture = new ResourceLocation("tragicmc:textures/entities/SoulChestSingle.png");
	private ModelChest simpleChest = new ModelChest();
	private ModelChest doubleChest = new ModelLargeChest();

	public void renderTileEntityAt(TileEntityChest te, double x, double y, double z, float partialTick, int renderPass)
    {
		int j;

        if (!te.hasWorldObj())
        {
            j = 0;
        }
        else
        {
            Block block = te.getBlockType();
            j = te.getBlockMetadata();

            if (block instanceof BlockChest && j == 0)
            {
                ((BlockChest)block).checkForSurroundingChests(te.getWorld(), te.getPos(), te.getWorld().getBlockState(te.getPos()));
                j = te.getBlockMetadata();
            }

            te.checkForAdjacentChests();
        }

        if (te.adjacentChestZNeg == null && te.adjacentChestXNeg == null)
        {
            ModelChest modelchest;

            if (te.adjacentChestXPos == null && te.adjacentChestZPos == null)
            {
                modelchest = this.simpleChest;

                if (renderPass >= 0)
                {
                    this.bindTexture(DESTROY_STAGES[renderPass]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(4.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                }
                else
                {
                    this.bindTexture(singleTexture);
                }
            }
            else
            {
                modelchest = this.doubleChest;

                if (renderPass >= 0)
                {
                    this.bindTexture(DESTROY_STAGES[renderPass]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(8.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                }
                else
                {
                    this.bindTexture(doubleTexture);
                }
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            if (renderPass < 0)
            {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            GlStateManager.translate(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if (j == 2)
            {
                short1 = 180;
            }

            if (j == 3)
            {
                short1 = 0;
            }

            if (j == 4)
            {
                short1 = 90;
            }

            if (j == 5)
            {
                short1 = -90;
            }

            if (j == 2 && te.adjacentChestXPos != null)
            {
                GlStateManager.translate(1.0F, 0.0F, 0.0F);
            }

            if (j == 5 && te.adjacentChestZPos != null)
            {
                GlStateManager.translate(0.0F, 0.0F, -1.0F);
            }

            GlStateManager.rotate((float)short1, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            float f1 = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTick;
            float f2;

            if (te.adjacentChestZNeg != null)
            {
                f2 = te.adjacentChestZNeg.prevLidAngle + (te.adjacentChestZNeg.lidAngle - te.adjacentChestZNeg.prevLidAngle) * partialTick;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            if (te.adjacentChestXNeg != null)
            {
                f2 = te.adjacentChestXNeg.prevLidAngle + (te.adjacentChestXNeg.lidAngle - te.adjacentChestXNeg.prevLidAngle) * partialTick;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
            modelchest.renderAll();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            if (renderPass >= 0)
            {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }
        }
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick, int renderPass)
	{
		this.renderTileEntityAt((TileEntityChest)te, x, y, z, partialTick, renderPass);
	}
}