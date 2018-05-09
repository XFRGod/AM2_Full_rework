package am2.client.gui.container;

import am2.blocks.tileentity.TileEntityOracle;
import am2.container.ContainerOracle;
import am2.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class GuiOracle extends GuiContainer {

    TileEntityOracle te;
    InventoryPlayer player;

    public GuiOracle(TileEntityOracle te, InventoryPlayer player) {
        super(new ContainerOracle(te, player));
        this.te = te;
        this.player = player;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/inventory/oracle.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String text = "Please insert a Binded Magician Orb.";
        if (te.GetCapabilityFromOrb() != null) {
            text = te.GetCapabilityFromOrb().toString();
        }
        fontRenderer.drawString(te.getDisplayName().getUnformattedText(), 5, 5, Color.darkGray.getRGB());
        int i = 15;
        for (String s : text.split("\n")) {
            fontRenderer.drawString(s, 5, i, Color.darkGray.getRGB());
            i += 10;
        }
    }
}
