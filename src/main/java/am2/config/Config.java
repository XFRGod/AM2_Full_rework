package am2.config;

import am2.AM2;
import am2.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;

import java.util.logging.Level;

public class Config {
    private static final String GENERAL = "general";
    private static final String SPELLS = "spells";

    public static boolean WitchwoodForestGen = true;

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
    }
    private static void initSpellConfig(Configuration cfg){
        cfg.addCustomCategoryComment(SPELLS, "Spell config");
    }
}
