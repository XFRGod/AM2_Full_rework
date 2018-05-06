package am2.client.network.handlers;

import am2.capabilities.AM2Capabilities;
import am2.capabilities.AM2CapabilitiesProvider;
import am2.network.messages.AM2NBTMessage;
import am2.utils.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AM2NBTClientMessageHandler implements IMessageHandler<AM2NBTMessage, IMessage> {

    @Override
    public IMessage onMessage(AM2NBTMessage message, MessageContext ctx) {

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
        AM2Capabilities cap = AM2CapabilitiesProvider.For(player);
        LogHelper.info("[" + player.isDead +"]Receiving message. Remote level: "+cap.getCurrentLevel()+" Message level: " + message.getCurrLevel());
        cap.fromAM2NBTMessage(message);
        return null;
    }
}
