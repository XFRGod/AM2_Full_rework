package am2.handler;

import am2.capabilities.AM2CapabilitiesProvider;
import am2.utils.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        transferCapability(AM2CapabilitiesProvider.INSTANCE, AM2CapabilitiesProvider.For(event.getOriginal()), AM2CapabilitiesProvider.For(event.getEntityPlayer()));
        System.out.println("Copied capabilities");
    }
    private static <T> void transferCapability(Capability<T> capability, T original, T target) {
        capability.getStorage().readNBT(capability, original, null, capability.getStorage().writeNBT(capability, original, null));
    }
}
