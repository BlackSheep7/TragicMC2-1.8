package tragicneko.tragicmc.client.gui;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.armor.TragicArmor;
import tragicneko.tragicmc.items.weapons.TragicBow;
import tragicneko.tragicmc.items.weapons.TragicTool;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.properties.PropertyAmulets;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.proxy.ClientProxy;

@SideOnly(Side.CLIENT)
public class GuiDoom extends Gui
{
	private final Minecraft mc;
	private static int buffer;
	private static int width;
	private static int prevDoom;
	private static int difference = 0;
	private static int difTick = 0;
	private static boolean cdFlag = false;

	private static final ResourceLocation newTexture = new ResourceLocation("tragicmc:textures/gui/new_doom_bar.png");
	private static final ResourceLocation newTextureStatus = new ResourceLocation("tragicmc:textures/gui/new_amulet_status.png");

	public GuiDoom(Minecraft mc) {
		super();
		this.mc = mc;
	}

	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onRenderNewOverlay(RenderGameOverlayEvent event)
	{
		if (event.isCancelable() || event.type != ElementType.HOTBAR || Minecraft.getMinecraft().gameSettings.showDebugInfo) return;

		int xPos = TragicConfig.getInt("guiX") + 2;
		int yPos = TragicConfig.getInt("guiY") + 4;
		this.mc.getTextureManager().bindTexture(newTexture);

		GlStateManager.enableBlend();
		GlStateManager.disableDepth();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.disableAlpha();

		boolean shiftAmuStatus = false;
		boolean isDoomDisplayed = true;
		
		final float trans = ((float) TragicConfig.getInt("guiTransparency") / 100.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, trans);

		PropertyDoom props = PropertyDoom.get(this.mc.thePlayer);
		if (props != null && props.getMaxDoom() > 0)
		{
			if (TragicConfig.getBoolean("allowAnimatedGui"))
			{
				width++;
				if (width > 30) width = 0;
			}
			else
			{
				width = 0;
			}

			int color = 0x000000;
			String s = null;

			if (props.getCurrentCooldown() > 0)
			{
				cdFlag = true;

				GlStateManager.pushMatrix();
				double scale = 0.265D;
				GlStateManager.scale(scale, scale, scale);
				drawTexturedModalRect(xPos + 28, yPos + 32, 0, 96, 200, 20); //the bar
				drawTexturedModalRect(xPos, yPos, 0, 0, 240, 60); //the entire gui
				//drawTexturedModalRect(xPos, yPos, 40, 160, 36, 36); //for coloring the circle in the top corner
				GlStateManager.popMatrix();

				s = "Cooling Down... " + props.getCurrentCooldown() ;
				color = 0x27B8DC;
			}
			else
			{
				if (cdFlag)
				{
					mc.thePlayer.playSound("tragicmc:random.cooldowndone", 1.0F, 1.0F);
					cdFlag = false;
				}

				buffer++;
				if (!TragicConfig.getBoolean("allowAnimatedGui")) buffer = 0;
				final int barFill = (int)(((float) props.getCurrentDoom() / props.getMaxDoom()) * 200);
				Doomsday doomsday = null;

				GlStateManager.pushMatrix();
				double scale = 0.265D;
				GlStateManager.scale(scale, scale, scale);
				drawTexturedModalRect(xPos + 28, yPos + 32, 0, 72, barFill, 20); //the bar
				drawTexturedModalRect(xPos, yPos, 0, 0, 240, 60); //the entire gui
				drawTexturedModalRect(xPos, yPos, 40, 160, 36, 36); //for coloring the circle in the top corner
				GlStateManager.popMatrix();

				if (difTick == 0)
				{
					difference = props.getCurrentDoom() - prevDoom;
					if (prevDoom != props.getMaxDoom() && props.getCurrentDoom() == props.getMaxDoom()) mc.thePlayer.playSound("tragicmc:random.doommaxed", 1.0F, 1.0F);
				}

				if (difference != 0)
				{
					difTick++;
					if (difTick > 20) difTick = 0;
				}
				else
				{
					difTick = 0;
				}

				prevDoom = props.getCurrentDoom();
				s = "Doom: " + props.getCurrentDoom() + "/" + props.getMaxDoom();
				color = 0xFF3636;
				boolean flag = false;
				boolean sneakFlag = false;

				if (this.mc.thePlayer.getCurrentEquippedItem() != null)
				{
					ItemStack stack = this.mc.thePlayer.getCurrentEquippedItem();

					if (stack.getItem() instanceof TragicBow)
					{
						if (((TragicBow)stack.getItem()).doomsday.doesCurrentDoomMeetRequirement(props))
						{
							doomsday = ((TragicBow)stack.getItem()).doomsday;
						}
					}
					else if (stack.getItem() instanceof TragicWeapon)
					{
						TragicWeapon weapon = (TragicWeapon) stack.getItem();
						if (weapon.getDoomsday() != null && weapon.getDoomsday().doesCurrentDoomMeetRequirement(props) || weapon.getSecondaryDoomsday() != null && weapon.getSecondaryDoomsday().doesCurrentDoomMeetRequirement(props))
						{
							if (weapon.getDoomsday() != null && weapon.getDoomsday().doesCurrentDoomMeetRequirement(props)) doomsday = weapon.getDoomsday();

							if (!mc.thePlayer.isSneaking())
							{
								if (weapon.getSecondaryDoomsday() != null && weapon.getSecondaryDoomsday().doesCurrentDoomMeetRequirement(props)) doomsday = weapon.getSecondaryDoomsday();
							}
							else
							{
								if (weapon.getSecondaryDoomsday() != null && weapon.getSecondaryDoomsday().doesCurrentDoomMeetRequirement(props) && doomsday != null) sneakFlag = true;
							}
						}
					}
					else if (stack.getItem() instanceof TragicTool)
					{
						if (((TragicTool)stack.getItem()).getDoomsday() != null && ((TragicTool)stack.getItem()).getDoomsday().doesCurrentDoomMeetRequirement(props))
						{
							doomsday = ((TragicTool)stack.getItem()).getDoomsday();
						}
					}

					if (!(stack.getItem() instanceof ItemArmor) && stack.hasTagCompound() && stack.getTagCompound().hasKey("doomsdayID") && TragicConfig.getBoolean("allowDoomScrollImbue")) doomsday = Doomsday.getDoomsdayFromId(stack.getTagCompound().getInteger("doomsdayID"));
					if (doomsday != null) flag = true;
				}
				else
				{
					flag = false;
					boolean flag2 = false;
					Doomsday[] doomsdays = new Doomsday[4];
					ItemStack stack;
					EntityPlayer player = mc.thePlayer;

					for (byte i = 0; i < 4; i++)
					{
						stack = player.getCurrentArmor(i);

						if (stack != null && stack.getItem() instanceof TragicArmor)
						{
							doomsdays[i] = ((TragicArmor)stack.getItem()).doomsday;
						}

						if (stack != null && stack.hasTagCompound() && stack.getTagCompound().hasKey("doomsdayID") && TragicConfig.getBoolean("allowDoomScrollImbue"))
						{
							doomsdays[i] = Doomsday.getDoomsdayFromId(stack.getTagCompound().getInteger("doomsdayID"));
						}

						if (doomsday == null && doomsdays[i] != null) doomsday = doomsdays[i];
					}

					for (byte i = 0; i < 4; i++)
					{
						if (doomsdays[i] == null)
						{
							flag2 = false;
							break;
						}
						else if (doomsdays[i] == doomsday)
						{
							flag2 = true;
						}
						else
						{
							flag2 = false;
							break;
						}
					}

					if (flag2 && doomsday != null && doomsday.doesCurrentDoomMeetRequirement(props)) flag = true;
				}

				if (flag)
				{
					if (buffer > 10 && buffer <= 20)
					{
						color = 0xFFF368;
					}
					else
					{
						color = 0xFFB868;
					}

					if (TragicConfig.getBoolean("allowExtraDoomsdayInfoInGui"))
					{
						shiftAmuStatus = true;
						GlStateManager.pushMatrix();
						GlStateManager.scale(0.475, 0.475, 0.475);
						EnumDifficulty dif = mc.theWorld.getDifficulty();
						String s3 = "Current Doomsday: " + doomsday.getLocalizedName() + (mc.thePlayer.isSneaking() && sneakFlag ? " (Sneak)" : "");
						drawRect(xPos + 2, yPos + 32, mc.fontRendererObj.getStringWidth(s3) + 15, yPos + 33 + mc.fontRendererObj.FONT_HEIGHT * 2, 0x77333333);
						this.mc.fontRendererObj.drawString(s3, xPos + 4, yPos + 33, 0xDDDDDD);
						s3 = "Cost/Cooldown: " + doomsday.getScaledDoomRequirement(dif) + " / " + doomsday.getScaledCooldown(dif);
						this.mc.fontRendererObj.drawString(s3, xPos + 4, yPos + 43, 0xDDDDDD);
						
						GlStateManager.scale(0.745, 0.745, 0.745);
						String s4 = "Press " + Keyboard.getKeyName(ClientProxy.useSpecial.getKeyCode()) + " to activate!";
						this.mc.fontRendererObj.drawString(s4, xPos + 48, yPos + 25, color);
						GlStateManager.popMatrix();
					}
				}

				if (buffer > 20) buffer = 0;

				if (difference != 0 && difTick > 0)
				{
					boolean flg = difference > 0;
					String s2 = (flg ? "+" : "") + difference;
					int y = yPos + (TragicConfig.getBoolean("allowAnimatedGui") ? (difTick / 2) : 0);
					this.mc.fontRendererObj.drawString(s2, xPos + 70, y, flg ? 0x00FF00 : 0xFF0000);
				}
			}

			GlStateManager.pushMatrix();
			GlStateManager.scale(0.875, 0.875, 0.875);
			this.mc.fontRendererObj.drawString(s, xPos + 11, yPos - 3, color);
			GlStateManager.popMatrix();
		}
		else
		{
			isDoomDisplayed = false;
		}

		PropertyAmulets amu = PropertyAmulets.get(this.mc.thePlayer);
		if (amu != null && amu.getSlotsOpen() > 0 && TragicConfig.getBoolean("showAmuletStatusGui"))
		{
			this.mc.getTextureManager().bindTexture(newTextureStatus);
			int length = 40;
			if (amu.getSlotsOpen() == 2) length = 120;
			if (amu.getSlotsOpen() == 3) length = 200;
			int yShift = 36;
			if (shiftAmuStatus) yShift += 24;
			if (!isDoomDisplayed) yShift = 2;
			GlStateManager.pushMatrix();
			double scale = 0.375D;
			GlStateManager.scale(scale, scale, scale);
			drawTexturedModalRect(xPos + 10, yPos + yShift, 0, 0, length, 60);
			GlStateManager.popMatrix();
		}

		GlStateManager.enableAlpha();
		GlStateManager.enableDepth();
		GlStateManager.disableBlend();

		ItemStack stack;
		int yShift = 16;
		if (shiftAmuStatus) yShift += 10;
		if (!isDoomDisplayed) yShift = 1;

		for (byte i = 0; i < 3 && TragicConfig.getBoolean("showAmuletStatusGui") && amu != null; i++)
		{
			stack = amu.inventory.getStackInSlot(i);
			if (stack != null)
			{
				mc.getRenderItem().renderItemAndEffectIntoGUI(stack, xPos - 2 + (24 * i), yPos + yShift);
				mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, stack, xPos - 3 + (24 * i), yPos + yShift, "");
				GlStateManager.disableLighting();
			}
			else if (amu.getSlotsOpen() < i + 1 && TragicConfig.getInt("amuletMaxSlots") >= i + 1)
			{
				this.mc.getTextureManager().bindTexture(newTextureStatus);
				GlStateManager.pushMatrix();
				GlStateManager.color(1F, 1F, 1F, trans);
				drawTexturedModalRect(xPos - 6 + (24 * i), yPos + yShift - 1, 0, 64, 16, 16);
				GlStateManager.popMatrix();
			}
		}
	}
}
