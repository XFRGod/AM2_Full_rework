package am2.proxy;

import am2.capabilities.IMana;
import am2.capabilities.Mana;
import am2.capabilities.ManaStorage;
import am2.config.Config;
import am2.handler.CapabilityHandler;
import am2.handler.EventHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

@Mod.EventBusSubscriber
public class CommonProxy {
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent event){
        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "am2.cfg"));
        Config.readConfig();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana.class);
    }

    public void init(FMLInitializationEvent event){}

    public void postInit(FMLPostInitializationEvent event){
        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){}
}
