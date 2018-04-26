package am2.client.gui.config;

import am2.AM2;
import am2.config.Config;
import am2.utils.Reference;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGUI extends GuiConfig {

    public ConfigGUI(GuiScreen parent){
        super(parent, new ConfigElement(AM2.config.getCategory(Config.GUI)).getChildElements(), Reference.MODID, false, false, "Play Magic Beans Any Way You Want");
    }

    @Override
    public void initGui(){
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button){
        super.actionPerformed(button);
    }
}
