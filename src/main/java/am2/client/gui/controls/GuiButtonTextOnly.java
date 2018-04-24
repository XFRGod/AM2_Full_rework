package am2.client.gui.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonTextOnly extends GuiButtonVariableDims {

	public GuiButtonTextOnly(int par1, int par2, int par3, String par4Str) {
		super(par1, par2, par3, par4Str);
	}
	
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3, float partialTicks){
		if (this.visible){
			GlStateManager.color(1, 1, 1, 1);
			boolean isMousedOver = par2 >= this.x && par3 >= this.y && par2 < this.x + this.width && par3 < this.y + this.height;
			
			int textColor = 0xFFFFFF;
			if (isMousedOver){
				textColor = 0x6600FF;
			}
			GlStateManager.disableAlpha();
			par1Minecraft.fontRenderer.drawString(this.displayString, x, y, textColor);
		}
	}

}
