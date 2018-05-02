package am2.handler;

import am2.capabilities.AM2CapabilitiesProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;


public class EventHandler {
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event){
        transferCapability(AM2CapabilitiesProvider.INSTANCE, AM2CapabilitiesProvider.For(event.getOriginal()), AM2CapabilitiesProvider.For(event.getEntityPlayer()));
        System.out.println("Copied capabilities");
    }
    private <T> void transferCapability(Capability<T> capability, T original, T target) {
        capability.getStorage().readNBT(capability, target, null, capability.getStorage().writeNBT(capability, original, null));
    }
}
