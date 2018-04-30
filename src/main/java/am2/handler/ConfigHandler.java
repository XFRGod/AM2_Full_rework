package am2.handler;

import am2.math.AMVector2;
import am2.utils.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config.LangKey("am2.config.title")
@Config(modid = Reference.MODID, name = Reference.NAME)
public class ConfigHandler {
    @Config.LangKey("config.am2:client.hud")
    public static HUD hud = new HUD();

    public static class HUD{

        @Config.Name("ShowHUDMinimally")
        @Config.LangKey("config.am2:client.hud.minimalhud")
        @Config.Comment("Set it to true if you want the minimal HUD setting")
        public boolean ShowHUDMinimally = false;

        @Config.Name("ShowNumerics")
        @Config.LangKey("config.am2:client.hud.numerics")
        @Config.Comment("Set it to true if you want to see your mana and burnout represented by numbers")
        public boolean ShowNumerics = false;

        @Config.Name("ShowXPAlways")
        @Config.LangKey("config.am2:client.persistentxp")
        @Config.Comment("Set it to true if you always want to see your magic XP")
        public boolean ShowXPAlways = false;

        @Config.Name("ShowHUDBars")
        @Config.LangKey("config.am2:client.hudbars")
        @Config.Comment("Set it to false if you don't want to see your mana and burnout bar")
        public boolean ShowHUDBars = true;


        @Config.Name("ManaHUDPosition")
        @Config.LangKey("config.am2:client.manabarposition")
        public AMVector2 ManaHUDPosition = new AMVector2(0.71041667461395260, 0.9137254953384399);

        @Config.Name("BurnoutHUDPosition")
        @Config.LangKey("config.am2:client.burnoutbarposition")
        public AMVector2 BurnoutHUDPosition = new AMVector2(0.13333334028720856, 0.9176470637321472);

        @Config.Name("XPBarHUDPosition")
        @Config.LangKey("config.am2:client.magicxpbarposition")
        public AMVector2 XPBarPosition = new AMVector2(0.31041666865348816, 0.7843137383460999);

        @Config.Name("LevelHUDPosition")
        @Config.LangKey("config.am2:client.magiclevelposition")
        public AMVector2 LevelPosition = new AMVector2(0.49791666865348816, 0.8117647171020508);

        @Config.Name("ManaNumericPosition")
        @Config.LangKey("config.am2:client.mananumericposition")
        public AMVector2 ManaNumericPosition = new AMVector2(0.74374997615814210, 0.8941176533699036);

        @Config.Name("BurnoutNumericPosition")
        @Config.LangKey("config.am2:client.burnoutnumericposition")
        public AMVector2 BurnoutNumericPosition = new AMVector2(0.21041665971279144, 0.9058823585510254);

        @Config.Name("XPNumericPosition")
        @Config.LangKey("config.am2:client.magicxpnumericposition")
        public AMVector2 XPNumericPosition = new AMVector2(0.47083333134651184, 0.7450980544090271);
    }

    @Mod.EventBusSubscriber(modid = Reference.MODID)
    private static class EventHandler{
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event){
            if (event.getModID().equals(Reference.MODID)){
                ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
            }
        }
    }

}
