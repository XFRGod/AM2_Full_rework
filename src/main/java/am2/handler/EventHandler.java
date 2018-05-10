package am2.handler;

import am2.affinity.Affinity;
import am2.capabilities.AM2CapabilitiesProvider;
import am2.network.messages.AM2NBTMessage;
import am2.utils.LogHelper;
import am2.utils.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        transferCapability(AM2CapabilitiesProvider.INSTANCE, AM2CapabilitiesProvider.For(event.getOriginal()), AM2CapabilitiesProvider.For(event.getEntityPlayer()));
        System.out.println("Copied capabilities");
    }
    private static <T> void transferCapability(Capability<T> capability, T original, T target) {
        NBTBase nbt = capability.getStorage().writeNBT(capability, original, null);
        capability.getStorage().readNBT(capability, target, null, nbt);

    }

    @SubscribeEvent
    public static void entityTick (LivingEvent.LivingUpdateEvent event) {
        if (!event.getEntity().world.isRemote) { //Do on server
            if (event.getEntityLiving() instanceof EntityPlayer) {
                if (AM2CapabilitiesProvider.For((EntityPlayer)event.getEntityLiving()).getDoUpdate()) {
                    AM2PacketHandler.INSTANCE.sendTo(new AM2NBTMessage(AM2CapabilitiesProvider.For((EntityPlayer) event.getEntityLiving())), (EntityPlayerMP) event.getEntity());
                    AM2CapabilitiesProvider.For((EntityPlayer) event.getEntityLiving()).update();
                }
            }
        }
    }

    @SubscribeEvent
    public static void addRegistry(RegistryEvent.NewRegistry event){
        LogHelper.info("Adding Registry");
        RegistryBuilder affinity = new RegistryBuilder<Affinity>();
        affinity.setName(new ResourceLocation(Reference.MODID, "affinities"));
        affinity.setType(Affinity.class).create();
        affinity.setIDRange(0, 64);
        affinity.create();
    }
}
