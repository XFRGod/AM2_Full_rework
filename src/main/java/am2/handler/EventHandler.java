package am2.handler;

import am2.capabilities.AM2CapabilitiesProvider;
import am2.utils.LogHelper;
import am2.utils.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class EventHandler {
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event){
        transferCapability(AM2CapabilitiesProvider.INSTANCE, AM2CapabilitiesProvider.For(event.getOriginal()), AM2CapabilitiesProvider.For(event.getEntityPlayer()));
        LogHelper.info("Copied Capabilities");
    }
    private <T> void transferCapability(Capability<T> capability, T original, T target) {
        LogHelper.info(original + " / " + target);
        capability.getStorage().readNBT(capability, target, null, capability.getStorage().writeNBT(capability, original, null));
    }
}
