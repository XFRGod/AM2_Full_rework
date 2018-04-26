package am2.client.gui;

import am2.AM2;
import am2.math.AMVector2;
import am2.client.gui.controls.GuiButtonVariableDims;
import am2.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.HashMap;

public class GUIHUDCustomization extends GuiScreen{
    private GuiButtonVariableDims manaButton;
    private GuiButtonVariableDims burnoutButton;
    private GuiButtonVariableDims levelButton;
    private GuiButtonVariableDims affinityButton;
    private GuiButtonVariableDims xpBar;

    private GuiButtonVariableDims manaNumeric;
    private GuiButtonVariableDims burnoutNumeric;
    private GuiButtonVariableDims XPNumeric;


    private GuiButtonVariableDims options;
    private GuiButtonVariableDims showNumerics;
    private GuiButtonVariableDims showHudMinimally;
    private GuiButtonVariableDims showXPAlways;
    private GuiButtonVariableDims showHudBars;

    private boolean doShowBuffs;
    private boolean doShowNumerics;
    private boolean doShowHudMinimally;
    private boolean doShowArmor;
    private boolean doShowXPAlways;
    private boolean doShowBars;
    private boolean showOptions;

    private GuiButtonVariableDims dragTarget;
    private AMVector2 dragOffset;

    private final HashMap<Integer, Integer> snapData;

    int screenWidth = 1;
    int screenHeight = 1;

    public GUIHUDCustomization(){
        snapData = new HashMap<Integer, Integer>();
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        screenWidth = scaledresolution.getScaledWidth();
        screenHeight = scaledresolution.getScaledHeight();
    }

    @Override
    public void initGui(){
        super.initGui();

        doShowHudMinimally = AM2.config.getShowHUDMinimally();
        doShowNumerics = AM2.config.getShowNumerics();
        doShowXPAlways = AM2.config.getShowXPAlways();
        doShowBars = AM2.config.getShowHUDBars();

        int barWidth = (width / 8) + 16;

        manaButton = new GuiButtonVariableDims(0, 0, 0, "").setDimensions(barWidth, 14).setBorderOnly(true);
        burnoutButton = new GuiButtonVariableDims(1, 0, 0, "").setDimensions(barWidth, 14).setBorderOnly(true);
        levelButton = new GuiButtonVariableDims(2, 0, 0, "").setDimensions(10, 10).setBorderOnly(true);
        affinityButton = new GuiButtonVariableDims(3, 0, 0, "").setDimensions(10, 20).setBorderOnly(true).setPopupText(I18n.format("am2.gui.affinity"));
        xpBar = new GuiButtonVariableDims(10, 0, 0, "").setDimensions(182, 5).setPopupText(I18n.format("am2.gui.xpBar")).setBorderOnly(true);
        showNumerics = new GuiButtonVariableDims(12, width / 2 - 90, height - 66, I18n.format("am2.gui.numericValues")).setDimensions(180, 20);
        options = new GuiButtonVariableDims(13, width / 2 - 90, height - 22, I18n.format("am2.gui.options")).setDimensions(180, 20);
        showHudMinimally = new GuiButtonVariableDims(14, width / 2 - 90, height - 110, I18n.format("am2.gui.minimalHud")).setDimensions(180, 20).setPopupText(I18n.format("am2.gui.minimalHudDesc"));
        showXPAlways = new GuiButtonVariableDims(16, width / 2 - 90, height - 154, I18n.format("am2.gui.xpAlways")).setDimensions(180, 20);
        showHudBars = new GuiButtonVariableDims(17, width / 2 - 90, height - 176, I18n.format("am2.gui.hudBars")).setDimensions(180, 20);

        manaNumeric = new GuiButtonVariableDims(18, 0, 0, "").setDimensions(25, 10).setPopupText(I18n.format("am2.gui.manaNumeric")).setBorderOnly(true);
        burnoutNumeric = new GuiButtonVariableDims(19, 0, 0, "").setDimensions(25, 10).setPopupText(I18n.format("am2.gui.burnoutNumeric")).setBorderOnly(true);
        XPNumeric = new GuiButtonVariableDims(20, 0, 0, "").setDimensions(25, 10).setPopupText(I18n.format("am2.gui.XPNumeric")).setBorderOnly(true);

        showNumerics.displayString = I18n.format("am2.gui.numericValues") + ": " + ((doShowNumerics) ? I18n.format("am2.gui.yes") : I18n.format("am2.gui.no"));
        showHudMinimally.displayString = I18n.format("am2.gui.minimalHud") + ": " + ((doShowHudMinimally) ? I18n.format("am2.gui.yes") : I18n.format("am2.gui.no"));
        showXPAlways.displayString = I18n.format("am2.gui.xpAlways") + ": " + ((doShowXPAlways) ? I18n.format("am2.gui.yes") : I18n.format("am2.gui.no"));
        showHudBars.displayString = I18n.format("am2.gui.hudBars") + ": " + ((doShowBars) ? I18n.format("am2.gui.yes") : I18n.format("am2.gui.no"));

        manaNumeric.enabled = doShowNumerics;
        burnoutNumeric.enabled = doShowNumerics;
        XPNumeric.enabled = doShowNumerics;

        manaButton.enabled = doShowBars;
        burnoutButton.enabled = doShowBars;

        initButtonAndSnapData(manaButton, AM2.config.getManaHUDPosition());
        initButtonAndSnapData(burnoutButton, AM2.config.getBurnoutHUDPosition()); //new AMVector2(0.5, 0.5)
        initButtonAndSnapData(levelButton, AM2.config.getLevelPosition());
        initButtonAndSnapData(xpBar, AM2.config.getXPBarPosition());
        initButtonAndSnapData(manaNumeric, AM2.config.getManaNumericPosition());
        initButtonAndSnapData(burnoutNumeric, AM2.config.getBurnoutNumericPosition());
        initButtonAndSnapData(XPNumeric, AM2.config.getXPNumericPosition());

        setOptionsVisibility(false);

        this.buttonList.add(showNumerics);
        this.buttonList.add(options);
        this.buttonList.add(showHudMinimally);
        this.buttonList.add(showXPAlways);
        this.buttonList.add(showHudBars);
    }

    private void setOptionsVisibility(boolean visible){
        showNumerics.visible = visible;
        showHudMinimally.visible = visible;
        showXPAlways.visible = visible;
        showHudBars.visible = visible;
    }

    private void initButtonAndSnapData(GuiButtonVariableDims button, AMVector2 position){
        int xPos = (int)(position.x * screenWidth);
        int yPos = (int)(screenHeight * position.y);
        int snap = 0;

        if (xPos < width / 2)
            snap |= 0x1;

        if (yPos < height / 2)
            snap |= 0x2;

        button.setPosition(xPos, yPos);
        this.buttonList.add(button);
        snapData.put(button.id, snap);
    }

    private int getSnapData(GuiButtonVariableDims button){
        Integer i = snapData.get(button.id);
        return i != null ? i : 0;
    }

    private AMVector2 getSnapVector(GuiButtonVariableDims button){
        Integer snap = snapData.get(button.id);
        if (snap == null) snap = 0;
        float xPos = (float)((button.getPosition().x) / width);
        float yPos = (float)((button.getPosition().y) / height);

        return new AMVector2(xPos, yPos);
    }

    private void updateButtonPosition(GuiButtonVariableDims button, int newX, int newY){
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;
        int snap = getSnapData(button);
        if (newX < centerX){
            snap |= 0x1;
        }else{
            snap &= ~0x1;
        }

        if (newY < centerY){
            snap |= 0x2;
        }else{
            snap &= ~0x2;
        }
        snapData.put(button.id, snap);
        button.setPosition(newX, newY);
    }

    @Override
    protected void mouseReleased(int par1, int par2, int par3){
        super.mouseReleased(par1, par2, par3);
        if (par3 == 1 || par3 == 0)
            dragTarget = null;
    }

    @Override
    protected void mouseClickMove(int par1, int par2, int par3, long par4){
        super.mouseClickMove(par1, par2, par3, par4);

        if (dragTarget != null){
            int newX = par1 - dragOffset.iX;
            int newY = par2 - dragOffset.iY;

            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
                newX = (int)(Math.round(newX / 5.0f) * 5.0f);
                newY = (int)(Math.round(newY / 5.0f) * 5.0f);
            }

            updateButtonPosition(dragTarget, newX, newY);
            storeGuiPositions();
        }else{
            dragTarget = null;
        }
    }

    private void storeGuiPositions(){
        AM2.config.setGuiPositions(
                getSnapVector(manaButton),
                getSnapVector(burnoutButton),
                getSnapVector(levelButton),
                getSnapVector(affinityButton),
                getSnapVector(xpBar),
                getSnapVector(manaNumeric),
                getSnapVector(burnoutNumeric),
                getSnapVector(XPNumeric),
                doShowNumerics,
                doShowHudMinimally,
                doShowXPAlways,
                doShowBars);
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) throws IOException{
        super.mouseClicked(par1, par2, par3);

        for (Object button : this.buttonList){
            if (button instanceof GuiButtonVariableDims){
                if (((GuiButtonVariableDims)button).mousePressed(mc, par1, par2)){
                    if (button == showNumerics){
                        doShowNumerics = !doShowNumerics;
                        showNumerics.displayString = I18n.format("am2.gui.numericValues") + ": " + ((doShowNumerics) ? I18n.format("am2.gui.yes") : I18n.format("am2.gui.no"));
                        manaNumeric.enabled = doShowNumerics;
                        burnoutNumeric.enabled = doShowNumerics;
                        XPNumeric.enabled = doShowNumerics;
                        storeGuiPositions();
                    }else if (button == showHudMinimally){
                        doShowHudMinimally = !doShowHudMinimally;
                        showHudMinimally.displayString = I18n.format("am2.gui.minimalHud") + ": " + ((doShowHudMinimally) ? I18n.format("am2.gui.yes") : I18n.format("am2.gui.no"));
                        storeGuiPositions();
                    }else if (button == options){
                        showOptions = !showOptions;
                        setOptionsVisibility(showOptions);
                    }else if (button == showXPAlways){
                        doShowXPAlways = !doShowXPAlways;
                        showXPAlways.displayString = I18n.format("am2.gui.xpAlways") + ": " + ((doShowXPAlways) ? I18n.format("am2.gui.yes") : I18n.format("am2.gui.no"));
                        storeGuiPositions();
                    }else if (button == showHudBars){
                        doShowBars = !doShowBars;
                        showHudBars.displayString = I18n.format("am2.gui.hudBars") + ": " + ((doShowBars) ? I18n.format("am2.gui.yes") : I18n.format("am2.gui.no"));
                        manaButton.enabled = doShowBars;
                        burnoutButton.enabled = doShowBars;
                        storeGuiPositions();
                    }else if (!showOptions){
                        dragTarget = (GuiButtonVariableDims)button;
                        AMVector2 buttonPos = ((GuiButtonVariableDims)button).getPosition();
                        AMVector2 mousePos = new AMVector2(par1, par2);
                        dragOffset = mousePos.subtract(buttonPos);
                    }
                }
            }
        }
    }

    @Override
    protected void keyTyped(char par1, int par2) throws IOException{
        if (par2 == Keyboard.KEY_ESCAPE){
            AM2.config.saveGuiPositions();
            if (showOptions){
                showOptions = false;
                setOptionsVisibility(false);
                return;
            }
        }
        super.keyTyped(par1, par2);
    }

    @Override
    public boolean doesGuiPauseGame(){
        return true;
    }

    @Override
    public void drawScreen(int par1, int par2, float par3){
        super.drawScreen(par1, par2, par3);
        if (dragTarget != null){
            int snap = getSnapData(dragTarget);
            //x
            if ((snap & 0x1) == 0x1){
                drawSnapLeft(dragTarget);
            }else{
                drawSnapRight(dragTarget);
            }

            //y
            if ((snap & 0x2) == 0x2){
                drawSnapTop(dragTarget);
            }else{
                drawSnapBottom(dragTarget);
            }
        }
    }

    private void drawSnapLeft(GuiButtonVariableDims button){
        GL11.glColor3f(0, 1, 0);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glLineWidth(4f);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(0, 0, this.zLevel);
        GL11.glVertex3f(0, screenHeight, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(button.x, button.y + button.getDimensions().iY / 2, this.zLevel);
        GL11.glVertex3f(0, button.y + button.getDimensions().iY / 2, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(0, button.y + button.getDimensions().iY / 2, this.zLevel);
        GL11.glVertex3f(5, button.y + button.getDimensions().iY / 2 - 5, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(0, button.y + button.getDimensions().iY / 2, this.zLevel);
        GL11.glVertex3f(5, button.y + button.getDimensions().iY / 2 + 5, this.zLevel);
        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    private void drawSnapRight(GuiButtonVariableDims button){
        GL11.glColor3f(0, 1, 0);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glLineWidth(4f);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(screenWidth, 0, this.zLevel);
        GL11.glVertex3f(screenWidth, screenHeight, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(button.x + button.getDimensions().iX, button.y + button.getDimensions().iY / 2, this.zLevel);
        GL11.glVertex3f(screenWidth, button.y + button.getDimensions().iY / 2, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(screenWidth, button.y + button.getDimensions().iY / 2, this.zLevel);
        GL11.glVertex3f(screenWidth - 5, button.y + button.getDimensions().iY / 2 - 5, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(screenWidth, button.y + button.getDimensions().iY / 2, this.zLevel);
        GL11.glVertex3f(screenWidth - 5, button.y + button.getDimensions().iY / 2 + 5, this.zLevel);
        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    private void drawSnapTop(GuiButtonVariableDims button){
        GL11.glColor3f(0, 1, 0);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glLineWidth(4f);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(0, 0, this.zLevel);
        GL11.glVertex3f(screenWidth, 0, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2, button.y, this.zLevel);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2, 0, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2, 0, this.zLevel);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2 + 5, 5, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2, 0, this.zLevel);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2 - 5, 5, this.zLevel);
        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    private void drawSnapBottom(GuiButtonVariableDims button){
        GL11.glColor3f(0, 1, 0);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glLineWidth(4f);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(0, screenHeight, this.zLevel);
        GL11.glVertex3f(screenWidth, screenHeight, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2, button.y + button.getDimensions().iY, this.zLevel);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2, screenHeight, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2, screenHeight, this.zLevel);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2 + 5, screenHeight - 5, this.zLevel);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2, screenHeight, this.zLevel);
        GL11.glVertex3f(button.x + button.getDimensions().iX / 2 - 5, screenHeight - 5, this.zLevel);
        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) throws IOException{
        super.actionPerformed(par1GuiButton);
    }

    @Override
    public void onGuiClosed(){
        AM2.config.saveGuiPositions();
        super.onGuiClosed();
    }
}