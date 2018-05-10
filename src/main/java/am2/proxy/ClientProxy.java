package am2.proxy;

import am2.client.gui.AMGUI;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.logging.Level;

public class ClientProxy extends CommonProxy{
    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(new AMGUI());
    }

}
