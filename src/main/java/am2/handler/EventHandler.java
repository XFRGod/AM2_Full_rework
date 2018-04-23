package am2.handler;

import am2.capabilities.IMana;
import am2.capabilities.ManaProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventHandler {
    @SubscribeEvent
    public void onPlayerLogsIn(PlayerEvent.PlayerLoggedInEvent event){
        EntityPlayer player = event.player;
        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
        String message = String.format("Hello, you have §7%d§r mana left.", (long) mana.getCurrentMana());
        player.sendMessage(new TextComponentString(message));
    }
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        EntityPlayer player = event.player;
        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
        if (mana.getMaxMana() > mana.getCurrentMana()) mana.setCurrentMana(mana.getCurrentMana()+1);
    }
}
