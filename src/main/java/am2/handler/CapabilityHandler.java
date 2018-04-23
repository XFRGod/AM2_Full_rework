package am2.handler;

import am2.capabilities.AM2CapabilitiesProvider;
import am2.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {
    public static final ResourceLocation MANA_CAP = new ResourceLocation(Reference.MODID, "mana");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event){
        if (!(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(MANA_CAP, new AM2CapabilitiesProvider());
        System.out.println("Added capability: AM2Capabilities");
    }
}
