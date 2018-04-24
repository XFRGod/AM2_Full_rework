package am2.handler;

import am2.capabilities.IAM2Capabilites;
import am2.capabilities.AM2CapabilitiesProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventHandler {
    @SubscribeEvent
    public void onPlayerLogsIn(PlayerEvent.PlayerLoggedInEvent event){
        EntityPlayer player = event.player;
        IAM2Capabilites mana = player.getCapability(AM2CapabilitiesProvider.AM2_CAPABILITY, null);
        String message = String.format("Hello, you have §7%f§r mana left.", mana.getCurrentMana());
        player.sendMessage(new TextComponentString(message));
    }
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        EntityPlayer player = event.player;
        IAM2Capabilites mana = player.getCapability(AM2CapabilitiesProvider.AM2_CAPABILITY, null);
        if (mana.getMaxMana() > mana.getCurrentMana()) mana.setCurrentMana(mana.getCurrentMana()+1);
        String message = String.format("You have §7%f§r mana left.", mana.getCurrentMana());
        player.sendMessage(new TextComponentString(message));
    }
}
