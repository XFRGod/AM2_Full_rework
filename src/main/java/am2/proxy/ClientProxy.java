package am2.proxy;

import am2.AM2;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.logging.Level;

public class ClientProxy extends CommonProxy{
    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
        //AM2.logger.log(Level.INFO, "registering items");
    }
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event){}
}
