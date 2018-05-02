package am2.handler;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import am2.items.ItemLevelStick;

public class RegistryHandler {
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().register(new ItemLevelStick());
        System.out.println("Registered am2 items");
    }
}
