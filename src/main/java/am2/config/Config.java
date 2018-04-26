package am2.config;

import am2.AM2;
import am2.math.AMVector2;
import net.minecraftforge.client.gui.ForgeGuiFactory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.IModGuiFactory;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public class Config extends Configuration{
    private static final String GENERAL = "general";
    public static final String GUI = "gui";
        public static final String ManaHUDPositionX = "ManaHUDPositionX";
        public static final String ManaHUDPositionY = "ManaHUDPositionY";
        public static final String BurnoutHUDPositionX = "BurnoutHUDPositionX";
        public static final String BurnoutHUDPositionY = "BurnoutHUDPositionY";
        public static final String levelPositionX = "levelPositionX";
        public static final String levelPositionY = "levelPositionY";
        public static final String xpBarPositionX = "xpBarPositionX";
        public static final String xpBarPositionY = "xpBarPositionY";
        public static final String manaNumericPositionX = "manaNumericPositionX";
        public static final String manaNumericPositionY = "manaNumericPositionY";
        public static final String burnoutNumericPositionX = "burnoutNumericPositionX";
        public static final String burnoutNumericPositionY = "burnoutNumericPositionY";
        public static final String xpNumericPositionX = "xpNumericPositionX";
        public static final String xpNumericPositionY = "xpNumericPositionY";
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

    public Config(File cfg){
        super(cfg);
        try{
            this.load();
            initGeneralConfig();
            initGUIConfig();
        }
        catch (Exception e1){
            AM2.logger.log(Level.WARNING, "Problem loading config file!", e1);
        }
        finally{
            if (this.hasChanged()){
                this.save();
            }
        }
    }

    private void initGeneralConfig(){
        this.addCustomCategoryComment(GENERAL, "General config");
        WitchwoodForestGen     =   this.getBoolean("GenWitchwoodForest", GENERAL, WitchwoodForestGen, "Set to false if you do not want witchwood forests to generate in your worlds");
    }
    private void initGUIConfig(){
        this.addCustomCategoryComment(GUI, "GUI config");
        showHUDMinimally       =   this.getBoolean("showHUDMinimally", GUI, showHUDMinimally, "Set to true if you don't want the mod to have a GUI");
        showNumerics           =   this.getBoolean("showNumerics",     GUI, showNumerics,     "Set this to true if you want to see your current mana and burnout represented by numbers");
        showXPAlways           =   this.getBoolean("showXPAlways",     GUI, showXPAlways,     "Set this to true if you want your AM2 XP bar to stay on-screen");
        showHUDBars            =   this.getBoolean("showHUDBars",      GUI, showHUDBars,      "Set this to false if you don't want to see your mana and burnout bar");
        manaHUDPosition        =   new AMVector2(this.get(GUI, ManaHUDPositionX,        0.71041667461395260).getDouble(0.7104166746139526),   this.get(GUI, ManaHUDPositionY,        0.9137254953384399).getDouble(0.9137254953384399));
        burnoutHUDPosition     =   new AMVector2(this.get(GUI, BurnoutHUDPositionX,     0.13333334028720856).getDouble(0.13333334028720856), this.get(GUI, BurnoutHUDPositionY,     0.9176470637321472).getDouble(0.9176470637321472));
        levelPosition          =   new AMVector2(this.get(GUI, levelPositionX,          0.49791666865348816).getDouble(0.49791666865348816), this.get(GUI, levelPositionY,          0.8117647171020508).getDouble(0.8117647171020508));
        //affinityPosition
        xpBarPosition          =   new AMVector2(this.get(GUI, xpBarPositionX,          0.31041666865348816).getDouble(0.31041666865348816), this.get(GUI, xpBarPositionY,          0.7843137383460999).getDouble(0.7843137383460999));
        manaNumericPosition    =   new AMVector2(this.get(GUI, manaNumericPositionX,    0.74374997615814210).getDouble(0.7437499761581421),   this.get(GUI, manaNumericPositionY,    0.8941176533699036).getDouble(0.8941176533699036));
        burnoutNumericPosition =   new AMVector2(this.get(GUI, burnoutNumericPositionX, 0.21041665971279144).getDouble(0.21041665971279144), this.get(GUI, burnoutNumericPositionY, 0.9058823585510254).getDouble(0.9058823585510254));
        XPNumericPosition      =   new AMVector2(this.get(GUI, xpNumericPositionX,      0.47083333134651184).getDouble(0.47083333134651184), this.get(GUI, xpNumericPositionY,      0.7450980544090271).getDouble(0.7450980544090271));
    }
    private void initSpellConfig(){
        this.addCustomCategoryComment(SPELLS, "Spell config");
    }

    public void setGuiPositions(AMVector2 manaHud, AMVector2 burnoutHud, AMVector2 levelHud, AMVector2 affinityHud, AMVector2 posBuffsHud, AMVector2 negBuffsHud, AMVector2 armorHead, AMVector2 armorChest, AMVector2 armorLegs, AMVector2 armorBoots, AMVector2 xpBar, AMVector2 contingency, AMVector2 manaNumeric, AMVector2 burnoutNumeric, AMVector2 XPNumeric, AMVector2 spellBookPos, AMVector2 manaShieldingPos, boolean showBuffs, boolean showNumerics, boolean minimalHud, boolean showArmorUI, boolean showXPAlways, boolean showHudBars){
        manaHUDPosition         = manaHud;
        burnoutHUDPosition      = burnoutHud;
        levelPosition           = levelHud;
        xpBarPosition           = xpBar;
        manaNumericPosition     = manaNumeric;
        burnoutNumericPosition  = burnoutNumeric;
        XPNumericPosition       = XPNumeric;
        showNumerics            = showNumerics;
        showHUDMinimally        = minimalHud;
        showXPAlways            = showXPAlways;
        showHUDBars             = showHudBars;
    }
    private void updateAMVector2(String keyX, String keyY, AMVector2 value){
        Property prop;
        prop = this.get(GUI, keyX, 0);
        prop.set(value.x);

        prop = this.get(GUI, keyY, 0);
        prop.set(value.y);
    }
    public void saveGuiPositions(){
        this.updateAMVector2(ManaHUDPositionX,          ManaHUDPositionY,           manaHUDPosition);
        this.updateAMVector2(BurnoutHUDPositionX,       BurnoutHUDPositionY,        burnoutHUDPosition);
        this.updateAMVector2(levelPositionX,            levelPositionY,             levelPosition);
        this.updateAMVector2(xpBarPositionX,            xpBarPositionY,             xpBarPosition);
        this.updateAMVector2(manaNumericPositionX,      manaNumericPositionY,       manaNumericPosition);
        this.updateAMVector2(burnoutNumericPositionX,   burnoutNumericPositionY,    burnoutNumericPosition);
        this.updateAMVector2(xpNumericPositionX,        xpNumericPositionY,         XPNumericPosition);

        Property numProp;
        numProp = this.get(GUI, "showNumerics", false);
        numProp.set(showNumerics);

        Property minimalProp;
        minimalProp = this.get(GUI, "showHUDMinimally", false);
        minimalProp.set(showHUDMinimally);

        Property xpShow;
        xpShow = this.get(GUI, "showXPAlways", false);
        xpShow.set(showXPAlways);

        Property barShow;
        barShow = this.get(GUI, "showHUDBars", true);
        barShow.set(showHUDBars);

        this.save();
    }

    public AMVector2 getManaHUDPosition()        { return manaHUDPosition; }
    public AMVector2 getBurnoutHUDPosition()     { return burnoutHUDPosition; }
    public AMVector2 getLevelPosition()          { return levelPosition; }
    public AMVector2 getXPBarPosition()          { return xpBarPosition; }
    public AMVector2 getManaNumericPosition()    { return manaNumericPosition; }
    public AMVector2 getBurnoutNumericPosition(){
        return burnoutNumericPosition;
    }
    public AMVector2 getXPNumericPosition()      { return XPNumericPosition; }
    public boolean getShowNumerics()     { return showNumerics; }
    public boolean getShowHUDMinimally(){
        return showHUDMinimally;
    }
    public boolean getShowXPAlways()     { return showXPAlways; }
    public boolean getShowHUDBars()      { return showHUDBars; }
}
