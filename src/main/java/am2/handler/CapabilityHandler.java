package am2.handler;

import am2.capabilities.AM2CapabilitiesProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event){
        if (!(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(AM2CapabilitiesProvider.AM2_CAPABILITY_RL, new AM2CapabilitiesProvider());
        System.out.println("Added capability: AM2Capabilities");
    }
}
