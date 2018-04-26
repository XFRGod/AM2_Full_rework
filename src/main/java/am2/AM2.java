package am2;

import am2.config.Config;
import am2.proxy.CommonProxy;
import am2.utils.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.logging.Level;
import java.util.logging.Logger;


@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, useMetadata = true)
public class AM2{
    @Instance
    public static AM2 instance;
    public static Config config = CommonProxy.config;
    public static Logger logger = Logger.getLogger(Reference.NAME);

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event){ proxy.preInit(event); }
    @Mod.EventHandler
    public static void init(FMLInitializationEvent event){
        proxy.init(event);
    }
    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
    @Mod.EventHandler
    public static void loadComplete(FMLLoadCompleteEvent event) { logger.log(Level.FINE, "Successfully loaded Ars Magica!"); }
}