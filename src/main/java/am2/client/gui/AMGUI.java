package am2.client.gui;

import am2.AM2;
import am2.capabilities.AM2Capabilities;
import am2.capabilities.IAM2Capabilites;
import am2.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AMGUI extends Gui {
    private final Minecraft mc;
    private float zLevel;
    private static final short MANA_BAR_FLASH_SLOT = 4;
    private static final ResourceLocation mc_gui = new ResourceLocation("textures/gui/icons.png");

    public AMGUI(){
        this.mc = Minecraft.getMinecraft();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent()
    public void renderGameOverlay(RenderGameOverlayEvent.Post event){
        if (event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE)
            return;
        boolean drawAMHud = !Config.showHUDMinimally;
        if (this.mc.currentScreen instanceof GUIHUDCustomization || this.mc.inGameHasFocus) {
            ItemStack ci = Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND);
            ScaledResolution scaledresolution = new ScaledResolution(this.mc);
            int i = scaledresolution.getScaledWidth();
            int j = scaledresolution.getScaledHeight();
            GlStateManager.pushAttrib();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.disableLighting();
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
//		if (drawAMHud)
//			RenderBuffs(i, j);
            Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            if (drawAMHud)
                this.RenderArsMagicaGUIItems(i, j, this.mc.fontRenderer);
            if (drawAMHud)
                this.RenderMagicXP(i, j);
            ItemStack item = this.mc.player.getHeldItem(EnumHand.MAIN_HAND);
            Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            GlStateManager.popAttrib();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.DST_ALPHA, GlStateManager.DestFactor.ONE_MINUS_DST_ALPHA);
            GlStateManager.enableDepth();
            GlStateManager.disableAlpha();
            GlStateManager.resetColor();
        }
    }
    private void RenderArsMagicaGUIItems(int i, int j, FontRenderer fontRenderer){
        if (AM2Capabilities.For(this.mc.player).getCurrentLevel() > 0 || this.mc.player.capabilities.isCreativeMode){
            this.RenderManaBar(i, j, fontRenderer);
        }
    }
    private void RenderManaBar(int i, int j, FontRenderer fontRenderer) {

        int barWidth = i / 8;

        AMVector2 Burnout_hud = this.getShiftedVector(ArsMagica2.config.getBurnoutHudPosition(), i, j);
        AMVector2 mana_hud = this.getShiftedVector(ArsMagica2.config.getManaHudPosition(), i, j);

        float green = 0.5f;
        float blue = 1.0f;
        float red = 0.126f;

        IAM2Capabilites props = AM2Capabilities.For(this.mc.player);

        //mana bar
        float mana = props.getCurrentMana();
        float maxMana = props.getMaxMana();

        float BurnoutBarWidth = barWidth;
        float Burnout = props.getCurrentBurnout();
        float maxBurnout = props.getMaxBurnout();

        float renderMana = mana;

        if (renderMana > maxMana)
            renderMana = maxMana;

        float progressScaled = (renderMana / (maxMana + 0.01f));
        boolean hasOverloadMana = mana > (maxMana + 1);

        if (AM2.config.showHudBars()) {
            //handle flashing of mana bar
            float flashTimer = AMGUIHelper.instance.getFlashTimer(MANA_BAR_FLASH_SLOT);
            if (flashTimer > 0) {
                green = 0.0f;
                float redShift = 1.0f - red;

                float halfFlash = AMGUIHelper.instance.flashDuration / 2;

                if (flashTimer > halfFlash) {
                    float pct = (flashTimer - halfFlash) / halfFlash;
                    red += redShift - (redShift * pct);
                } else {
                    float pct = flashTimer / halfFlash;
                    red += (redShift * pct);
                }
                GlStateManager.color(red, green, blue);
            } else if (hasBonusMana)
                GlStateManager.color(0.2f, 0.9f, 0.6f);
            else if (hasOverloadMana)
                GlStateManager.color(1f, 0.0f, 0.0f);
            ItemStack curItem = Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND);

            this.DrawPartialIconAtXY(AMGuiIcons.manaLevel, progressScaled, 1, mana_hud.iX + 16, mana_hud.iY + 1f, (int) (barWidth * 0.99F), 40, false);
            this.DrawIconAtXY(AMGuiIcons.manaBar, "items", mana_hud.iX + 15, mana_hud.iY + 3, barWidth, 50, false);

            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

            progressScaled = (Burnout / (maxBurnout + 0.01f));
            this.DrawIconAtXY(AMGuiIcons.fatigueIcon, "items", Burnout_hud.iX + barWidth, Burnout_hud.iY, false);

            this.DrawPartialIconAtXY(AMGuiIcons.fatigueLevel, progressScaled, 1, Burnout_hud.iX, Burnout_hud.iY + 3f, BurnoutBarWidth, 40, false);
            this.DrawIconAtXY(AMGuiIcons.fatigueBar, "items", Burnout_hud.iX, Burnout_hud.iY + 4, barWidth, 48, false);

            green = 0.5f;
            blue = 1.0f;
            red = 0.126f;
            //magic level
            int manaBarColor = Math.round(red * 255);
            manaBarColor = (manaBarColor << 8) + Math.round(green * 255);
            manaBarColor = (manaBarColor << 8) + Math.round(blue * 255);

            String magicLevel = (new StringBuilder()).append("").append(AM2Capabilities.For(this.mc.player).getCurrentLevel()).toString();
            AMVector2 magicLevelPos = this.getShiftedVector(ArsMagica2.config.getLevelPosition(), i, j);
            magicLevelPos.iX -= Minecraft.getMinecraft().fontRenderer.getStringWidth(magicLevel) / 2;
            fontRenderer.drawStringWithShadow(magicLevel, magicLevelPos.iX, magicLevelPos.iY, manaBarColor);

            if (flashTimer > 0) {
                GlStateManager.color(1.0f, 1.0f, 1.0f);
            }
        }
    }
}
