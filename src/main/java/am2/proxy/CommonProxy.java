package am2.proxy;

import am2.capabilities.IAM2Capabilites;
import am2.capabilities.AM2CapabilitiesFactory;
import am2.capabilities.AM2CapabilitiesStorage;
import am2.handler.CapabilityHandler;
import am2.handler.ConfigHandler;
import am2.handler.EventHandler;
import am2.handler.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new RegistryHandler());

        CapabilityManager.INSTANCE.register(IAM2Capabilites.class, new AM2CapabilitiesStorage(),new AM2CapabilitiesFactory());
    }

    public void init(FMLInitializationEvent event){}

    public void postInit(FMLPostInitializationEvent event){
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){}
}
