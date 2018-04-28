package am2.client.gui;

import am2.AM2;
import am2.capabilities.AM2Capabilities;
import am2.capabilities.IAM2Capabilites;
import am2.config.Config;
import am2.math.AMVector2;
import am2.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AMGUI extends Gui {
    private final Minecraft mc;
    public static final Config  c = CommonProxy.config;
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
        boolean drawAMHud = !c.getShowHUDMinimally();
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

        AMVector2 Burnout_hud = this.getShiftedVector(AM2.config.getBurnoutHUDPosition(), i, j);
        AMVector2 mana_hud = this.getShiftedVector(AM2.config.getManaHUDPosition(), i, j);

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

        if (AM2.config.getShowHUDBars()) {
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
            }
            ItemStack curItem = Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND);

            this.DrawPartialIconAtXY(AMGUIIcons.manaLevel, progressScaled, 1, mana_hud.iX + 16, mana_hud.iY + 1f, (int) (barWidth * 0.99F), 40, false);
            this.DrawIconAtXY(AMGUIIcons.manaBar, "items", mana_hud.iX + 15, mana_hud.iY + 3, barWidth, 50, false);

            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

            progressScaled = (Burnout / (maxBurnout + 0.01f));
            this.DrawIconAtXY(AMGUIIcons.fatigueIcon, "items", Burnout_hud.iX + barWidth, Burnout_hud.iY, false);

            this.DrawPartialIconAtXY(AMGUIIcons.fatigueLevel, progressScaled, 1, Burnout_hud.iX, Burnout_hud.iY + 3f, BurnoutBarWidth, 40, false);
            this.DrawIconAtXY(AMGUIIcons.fatigueBar, "items", Burnout_hud.iX, Burnout_hud.iY + 4, barWidth, 48, false);

            green = 0.5f;
            blue = 1.0f;
            red = 0.126f;
            //magic level
            int manaBarColor = Math.round(red * 255);
            manaBarColor = (manaBarColor << 8) + Math.round(green * 255);
            manaBarColor = (manaBarColor << 8) + Math.round(blue * 255);

            String magicLevel = (new StringBuilder()).append("").append(AM2Capabilities.For(this.mc.player).getCurrentLevel()).toString();
            AMVector2 magicLevelPos = this.getShiftedVector(AM2.config.getLevelPosition(), i, j);
            magicLevelPos.iX -= Minecraft.getMinecraft().fontRenderer.getStringWidth(magicLevel) / 2;
            fontRenderer.drawStringWithShadow(magicLevel, magicLevelPos.iX, magicLevelPos.iY, manaBarColor);

            if (flashTimer > 0) {
                GlStateManager.color(1.0f, 1.0f, 1.0f);
            }
        }
    }
    public void RenderMagicXP(int i, int j){
        AM2Capabilities props = AM2Capabilities.For(Minecraft.getMinecraft().player);
        if (props.getCurrentLevel() > 0){
            GlStateManager.enableBlend();
            AMVector2 position = this.getShiftedVector(AM2.config.getXPBarPosition(), i, j);
            AMVector2 dimensions = new AMVector2(182, 5);
            Minecraft.getMinecraft().renderEngine.bindTexture(mc_gui);
            GlStateManager.color(0.5f, 0.5f, 1.0f, AM2.config.getShowXPAlways() ? 1.0f : AMGUIHelper.instance.getMagicXPBarAlpha());

            //base XP bar
            this.drawTexturedModalRect_Classic(position.iX, position.iY, 0, 64, dimensions.iX, dimensions.iY, dimensions.iX, dimensions.iY);

            if (props.getCurrentXP() > 0){
                float pctXP = props.getCurrentXP() / props.getMaxXP();
                if (pctXP > 1)
                    pctXP = 1;
                int width = (int)((dimensions.iX + 1) * pctXP);
                this.drawTexturedModalRect_Classic(position.iX, position.iY, 0, 69, width, dimensions.iY, width, dimensions.iY);
            }

            if (AM2.config.getShowNumerics() && (AM2.config.getShowXPAlways() || AMGUIHelper.instance.getMagicXPBarAlpha() > 0)){
                String xpStr = I18n.format("am2.gui.xp") + ": " + +(int)(props.getCurrentXP() * 100) + "/" + (int)(props.getMaxXP() * 100);
                AMVector2 numericPos = this.getShiftedVector(AM2.config.getXPNumericPosition(), i, j);
                Minecraft.getMinecraft().fontRenderer.drawString(xpStr, numericPos.iX, numericPos.iY, 0x999999);
            }
        }
    }

    private AMVector2 getShiftedVector(AMVector2 configVec, int screenWidth, int screenHeight){
        int x = (int)Math.round(configVec.x * screenWidth);
        int y = (int)Math.round(configVec.y * screenHeight);

        return new AMVector2(x, y);
    }

    public void drawTexturedModalRect_Classic(int par1, int par2, int par3, int par4, int par5, int par6){
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;

        Tessellator var9 = Tessellator.getInstance();
        var9.getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
        var9.getBuffer().pos(par1 + 0, par2 + par6, this.zLevel).tex((par3 + 0) * var7, (par4 + par6) * var8).endVertex();
        var9.getBuffer().pos(par1 + par5, par2 + par6, this.zLevel).tex((par3 + par5) * var7, (par4 + par6) * var8).endVertex();
        var9.getBuffer().pos(par1 + par5, par2 + 0, this.zLevel).tex((par3 + par5) * var7, (par4 + 0) * var8).endVertex();
        var9.getBuffer().pos(par1 + 0, par2 + 0, this.zLevel).tex((par3 + 0) * var7, (par4 + 0) * var8).endVertex();
        var9.draw();
    }

    /**
     * Draw a section of the currently bound texture to the screen.
     *
     * @param dst_x      The x coordinate on the screen to draw to
     * @param dst_y      The y coordinate on the screen to draw to
     * @param src_x      The x coordinate on the texture to pull from
     * @param src_y      The y coordinate on the texture to pull from
     * @param dst_width  The width on screen to draw
     * @param dst_height The height on screen to draw
     * @param src_width  The width of the texture section
     * @param src_height The height of the texture section
     */
    public void drawTexturedModalRect_Classic(float dst_x, float dst_y, float src_x, float src_y, float dst_width, float dst_height, float src_width, float src_height){
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;

        Tessellator var9 = Tessellator.getInstance();
        var9.getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
        var9.getBuffer().pos(dst_x + 0, dst_y + dst_height, this.zLevel).tex((src_x + 0) * var7, (src_y + src_height) * var8).endVertex();
        var9.getBuffer().pos(dst_x + dst_width, dst_y + dst_height, this.zLevel).tex((src_x + src_width) * var7, (src_y + src_height) * var8).endVertex();
        var9.getBuffer().pos(dst_x + dst_width, dst_y + 0, this.zLevel).tex((src_x + src_width) * var7, (src_y + 0) * var8).endVertex();
        var9.getBuffer().pos(dst_x + 0, dst_y + 0, this.zLevel).tex((src_x + 0) * var7, (src_y + 0) * var8).endVertex();
        var9.draw();
    }

    @Override
    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6){
        float f = 1f/256f;
        float var9 = par3 * f;
        float var10 = (par3 + par5) * f;
        float var11 = par4 * f;
        float var12 = (par4 + par5) * f;

        Tessellator var8 = Tessellator.getInstance();
        //GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
        var8.getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        var8.getBuffer().pos(par1 + 0, par2 + par6, this.zLevel).tex(var9, var12).normal(0.0F, 1.0F, 0.0F).endVertex();
        var8.getBuffer().pos(par1 + par5, par2 + par6, this.zLevel).tex(var10, var12).normal(0.0F, 1.0F, 0.0F).endVertex();
        var8.getBuffer().pos(par1 + par5, par2 + 0, this.zLevel).tex(var10, var11).normal(0.0F, 1.0F, 0.0F).endVertex();
        var8.getBuffer().pos(par1 + 0, par2 + 0, this.zLevel).tex(var9, var11).normal(0.0F, 1.0F, 0.0F).endVertex();
        var8.draw();
    }

    private void DrawIconAtXY(TextureAtlasSprite icon, String base, float x, float y, boolean semitransparent){
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        this.DrawIconAtXY(icon, base, x, y, 16, 16, semitransparent);
    }

    private void DrawIconAtXY(TextureAtlasSprite IIcon, String base, float x, float y, int w, int h, boolean semitransparent){
        if (IIcon == null) return;
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
        tessellator.getBuffer().pos(x, y + h, this.zLevel).tex(IIcon.getMinU(), IIcon.getMaxV()).endVertex();
        tessellator.getBuffer().pos(x + w, y + h, this.zLevel).tex(IIcon.getMaxU(), IIcon.getMaxV()).endVertex();
        tessellator.getBuffer().pos(x + w, y, this.zLevel).tex(IIcon.getMaxU(), IIcon.getMinV()).endVertex();
        tessellator.getBuffer().pos(x, y, this.zLevel).tex(IIcon.getMinU(), IIcon.getMinV()).endVertex();
        tessellator.draw();
    }

    private void DrawPartialIconAtXY(TextureAtlasSprite IIcon, float pct_x, float pct_y, float x, float y, float w, float h, boolean semitransparent){
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        if (IIcon == null) return;

        Tessellator tessellator = Tessellator.getInstance();
        tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);

        tessellator.getBuffer().pos(x, y + (h * pct_y), this.zLevel).tex( IIcon.getMinU(), IIcon.getMaxV()).endVertex();
        tessellator.getBuffer().pos(x + (w * pct_x), y + (h * pct_y), this.zLevel).tex( IIcon.getMaxU(), IIcon.getMaxV()).endVertex();
        tessellator.getBuffer().pos(x + (w * pct_x), y, this.zLevel).tex(IIcon.getMaxU(), IIcon.getMinV()).endVertex();
        tessellator.getBuffer().pos(x, y, this.zLevel).tex(IIcon.getMinU(), IIcon.getMinV()).endVertex();

        tessellator.draw();
    }

}
