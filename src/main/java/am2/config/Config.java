package am2.config;

import am2.AM2;
import am2.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;

import java.util.logging.Level;

public class Config {
    private static final String GENERAL = "general";
    private static final String GUI = "gui";
    private static final String SPELLS = "spells";

    public static boolean WitchwoodForestGen = true;


    public static boolean showHUDMinimally = false;
    public static boolean showNumerics = false;
    public static boolean showXPAlways = false;
    public static boolean showHUDBars = true;


    public static void readConfig(){
        Configuration cfg = CommonProxy.config;
        try{
            cfg.load();
            initGeneralConfig(cfg);
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
    private static void initGeneralConfig(Configuration cfg){
        cfg.addCustomCategoryComment(GENERAL, "General config");
        WitchwoodForestGen = cfg.getBoolean("GenWitchwoodForest", GENERAL, WitchwoodForestGen, "Set to false if you do not want witchwood forests to generate in your worlds");
        showHUDMinimally = cfg.getBoolean("showHUDMinimally", GENERAL, showHUDMinimally, "Set to true if you don't want the mod to have a GUI");
    }
    private static void initGUIConfig(Configuration cfg){
        cfg.addCustomCategoryComment(GUI, "GUI config");
        showNumerics = cfg.getBoolean("showNumerics", GUI, showNumerics, "Set this to true if you want to see your current mana and burnout represented by numbers");
        showXPAlways = cfg.getBoolean("showXPAlways", GUI, showXPAlways, "Set this to true if you want your AM2 XP bar to stay on-screen");
        showHUDBars = cfg.getBoolean("showHUDBars", GUI, showHUDBars, "Set this to false if you don't want to see your mana and burnout bar");

    }
    private static void initSpellConfig(Configuration cfg){
        cfg.addCustomCategoryComment(SPELLS, "Spell config");
    }
}
