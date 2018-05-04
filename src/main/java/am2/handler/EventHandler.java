package am2.handler;

import am2.capabilities.AM2CapabilitiesProvider;
import am2.network.messages.AM2NBTMessage;
import am2.utils.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
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
        AM2PacketHandler.INSTANCE.sendToServer(new AM2NBTMessage(AM2CapabilitiesProvider.For(event.getEntityPlayer())));
    }
    private static <T> void transferCapability(Capability<T> capability, T original, T target) {
        NBTBase nbt = capability.getStorage().writeNBT(capability, original, null);
        capability.getStorage().readNBT(capability, target, null, nbt);

    }
}
