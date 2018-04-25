package am2.config;

import am2.AM2;
import am2.math.AMVector2;
import am2.proxy.CommonProxy;
import com.sun.istack.internal.NotNull;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.util.logging.Level;

public class Config {
    private static final String GENERAL = "general";
    private static final String GUI = "gui";
        private static final String ManaHUDPositionX = "ManaHUDPositionX";
        private static final String ManaHUDPositionY = "ManaHUDPositionY";
        private static final String BurnoutHUDPositionX = "BurnoutHUDPositionX";
        private static final String BurnoutHUDPositionY = "BurnoutHUDPositionY";
        private static final String levelPositionX = "levelPositionX";
        private static final String levelPositionY = "levelPositionY";
        private static final String xpBarPositionX = "xpBarPositionX";
        private static final String xpBarPositionY = "xpBarPositionY";
        private static final String manaNumericPositionX = "manaNumericPositionX";
        private static final String manaNumericPositionY = "manaNumericPositionY";
        private static final String burnoutNumericPositionX = "burnoutNumericPositionX";
        private static final String burnoutNumericPositionY = "burnoutNumericPositionY";
        private static final String xpNumericPositionX = "xpNumericPositionX";
        private static final String xpNumericPositionY = "xpNumericPositionY";
    private static final String SPELLS = "spells";

    private static boolean WitchwoodForestGen = true;

    private static AMVector2 manaHUDPosition;
    private static AMVector2 burnoutHUDPosition;
    private static AMVector2 levelPosition;
    private static AMVector2 xpBarPosition;
    private static AMVector2 manaNumericPosition;
    private static AMVector2 burnoutNumericPosition;
    private static AMVector2 XPNumericPosition;

    private static boolean showHUDMinimally = false;
    private static boolean showNumerics     = false;
    private static boolean showXPAlways     = false;
    private static boolean showHUDBars      = true;

    public static void readConfig(){
        Configuration cfg = CommonProxy.config;
        try{
            cfg.load();
            initGeneralConfig();
            initGUIConfig();
        }
        catch (Exception e1){
            AM2.logger.log(Level.WARNING, "Problem loading config file!", e1);
        }
        finally{
            if (cfg.hasChanged()){
                cfg.save();
            }
        }
    }
    private static void initGeneralConfig(){
        Configuration cfg = CommonProxy.config;
        cfg.addCustomCategoryComment(GENERAL, "General config");
        WitchwoodForestGen = cfg.getBoolean("GenWitchwoodForest", GENERAL, WitchwoodForestGen, "Set to false if you do not want witchwood forests to generate in your worlds");
    }
    private static void initGUIConfig(){
        Configuration cfg = CommonProxy.config;
        cfg.addCustomCategoryComment(GUI, "GUI config");
        showHUDMinimally = cfg.getBoolean("showHUDMinimally", GENERAL, showHUDMinimally, "Set to true if you don't want the mod to have a GUI");
        showNumerics = cfg.getBoolean("showNumerics", GUI, showNumerics, "Set this to true if you want to see your current mana and burnout represented by numbers");
        showXPAlways = cfg.getBoolean("showXPAlways", GUI, showXPAlways, "Set this to true if you want your AM2 XP bar to stay on-screen");
        showHUDBars = cfg.getBoolean("showHUDBars", GUI, showHUDBars, "Set this to false if you don't want to see your mana and burnout bar");
        manaHUDPosition = new AMVector2(cfg.get(GUI, ManaHUDPositionX, 0.7104166746139526).getDouble(0.7104166746139526), cfg.get(GUI, ManaHUDPositionY, 0.9137254953384399).getDouble(0.9137254953384399));
        burnoutHUDPosition = new AMVector2(cfg.get(GUI, BurnoutHUDPositionX, 0.13333334028720856).getDouble(0.13333334028720856), cfg.get(GUI, BurnoutHUDPositionY, 0.9176470637321472).getDouble(0.9176470637321472));
        levelPosition = new AMVector2(cfg.get(GUI, levelPositionX, 0.49791666865348816).getDouble(0.49791666865348816), cfg.get(GUI, levelPositionY, 0.8117647171020508).getDouble(0.8117647171020508));
        //affinityPosition
        xpBarPosition = new AMVector2(cfg.get(GUI, xpBarPositionX, 0.31041666865348816).getDouble(0.31041666865348816), cfg.get(GUI, xpBarPositionY, 0.7843137383460999).getDouble(0.7843137383460999));
        manaNumericPosition = new AMVector2(cfg.get(GUI, manaNumericPositionX, 0.7437499761581421).getDouble(0.7437499761581421), cfg.get(GUI, manaNumericPositionY, 0.8941176533699036).getDouble(0.8941176533699036));
        burnoutNumericPosition = new AMVector2(cfg.get(GUI, burnoutNumericPositionX, 0.21041665971279144).getDouble(0.21041665971279144), cfg.get(GUI, burnoutNumericPositionY, 0.9058823585510254).getDouble(0.9058823585510254));
        XPNumericPosition = new AMVector2(cfg.get(GUI, xpNumericPositionX, 0.47083333134651184).getDouble(0.47083333134651184), cfg.get(GUI, xpNumericPositionY, 0.7450980544090271).getDouble(0.7450980544090271));
    }
    private void initSpellConfig(){
        Configuration cfg = CommonProxy.config;
        cfg.addCustomCategoryComment(SPELLS, "Spell config");
    }

    public void setGuiPositions(AMVector2 manaHud, AMVector2 burnoutHud, AMVector2 levelHud, AMVector2 affinityHud, AMVector2 posBuffsHud, AMVector2 negBuffsHud, AMVector2 armorHead, AMVector2 armorChest, AMVector2 armorLegs, AMVector2 armorBoots, AMVector2 xpBar, AMVector2 contingency, AMVector2 manaNumeric, AMVector2 burnoutNumeric, AMVector2 XPNumeric, AMVector2 spellBookPos, AMVector2 manaShieldingPos, boolean showBuffs, boolean showNumerics, boolean minimalHud, boolean showArmorUI, boolean showXPAlways, boolean showHudBars){
        this.manaHUDPosition = manaHud;
        this.burnoutHUDPosition = burnoutHud;
        this.levelPosition = levelHud;
        this.xpBarPosition = xpBar;
        this.manaNumericPosition = manaNumeric;
        this.burnoutNumericPosition = burnoutNumeric;
        this.XPNumericPosition = XPNumeric;
        this.showNumerics = showNumerics;
        this.showHUDMinimally = minimalHud;
        this.showXPAlways = showXPAlways;
        this.showHUDBars = showHudBars;
    }
    private void updateAMVector2(String keyX, String keyY, AMVector2 value){
        Configuration cfg = CommonProxy.config;
        Property prop;
        prop = cfg.get(GUI, keyX, 0);
        prop.set(value.x);

        prop = cfg.get(GUI, keyY, 0);
        prop.set(value.y);
    }
    public void saveGuiPositions(){
        Configuration cfg = CommonProxy.config;
        this.updateAMVector2(ManaHUDPositionX, ManaHUDPositionY, this.manaHUDPosition);
        this.updateAMVector2(BurnoutHUDPositionX, BurnoutHUDPositionY, this.burnoutHUDPosition);
        this.updateAMVector2(levelPositionX, levelPositionY, this.levelPosition);
        this.updateAMVector2(xpBarPositionX, xpBarPositionY, xpBarPosition);
        this.updateAMVector2(manaNumericPositionX, manaNumericPositionY, this.manaNumericPosition);
        this.updateAMVector2(burnoutNumericPositionX, burnoutNumericPositionY, this.burnoutNumericPosition);
        this.updateAMVector2(xpNumericPositionX, xpNumericPositionY, this.XPNumericPosition);

        Property numProp;
        numProp = cfg.get(GUI, "showNumerics", false);
        numProp.set(this.showNumerics);

        Property minimalProp;
        minimalProp = cfg.get(GUI, "showHUDMinimally", false);
        minimalProp.set(showHUDMinimally);

        Property xpShow;
        xpShow = cfg.get(GUI, "showXPAlways", false);
        xpShow.set(showXPAlways);

        Property barShow;
        barShow = cfg.get(GUI, "showHUDBars", true);
        barShow.set(showHUDBars);

        cfg.save();
    }

    public AMVector2 getManaHUDPosition(){
        return this.manaHUDPosition;
    }

    public AMVector2 getBurnoutHUDPosition(){
        return this.burnoutHUDPosition;
    }
    public AMVector2 getLevelPosition(){
        return this.levelPosition;
    }
    public AMVector2 getXPBarPosition(){
        return this.xpBarPosition;
    }
    public AMVector2 getManaNumericPosition(){
        return this.manaNumericPosition;
    }

    public AMVector2 getBurnoutNumericPosition(){
        return this.burnoutNumericPosition;
    }

    public AMVector2 getXPNumericPosition(){
        return this.XPNumericPosition;
    }
    public boolean getShowNumerics(){
        return showNumerics;
    }
    public boolean getShowHUDMinimally(){
        return showHUDMinimally;
    }
    public boolean showXPAlways(){
        return showXPAlways;
    }

    public boolean showHUDBars(){
        return showHUDBars;
    }
}
