package am2.proxy;

import am2.capabilities.IAM2Capabilites;
import am2.capabilities.AM2CapabilitiesFactory;
import am2.capabilities.AM2CapabilitiesStorage;
import am2.definitions.BlockDefinitions;
import am2.definitions.ItemDefinitions;
import am2.handler.*;
import am2.network.handlers.AM2NBTServerMessageHandler;
import am2.network.messages.AM2NBTMessage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new RegistryHandler());
        MinecraftForge.EVENT_BUS.register(new NetworkHandler());

        ItemDefinitions.INSTANCE.Load();
        BlockDefinitions.INSTANCE.Load();

        AM2PacketHandler.INSTANCE.registerMessage(AM2NBTServerMessageHandler.class, AM2NBTMessage.class, 0, Side.SERVER);
        CapabilityManager.INSTANCE.register(IAM2Capabilites.class, new AM2CapabilitiesStorage(),new AM2CapabilitiesFactory());
    }

    public void init(FMLInitializationEvent event){}

    public void postInit(FMLPostInitializationEvent event){
    }
}
