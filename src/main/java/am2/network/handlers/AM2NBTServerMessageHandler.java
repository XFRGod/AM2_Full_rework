package am2.network.handlers;

import am2.capabilities.AM2Capabilities;
import am2.capabilities.AM2CapabilitiesProvider;
import am2.network.messages.AM2NBTMessage;
import am2.utils.LogHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class AM2NBTServerMessageHandler implements IMessageHandler<AM2NBTMessage, IMessage> {

    @Override
    public IMessage onMessage(AM2NBTMessage message, MessageContext ctx) {

        final EntityPlayerMP player = ctx.getServerHandler().player;

        if (player == null) {
            LogHelper.error("Player was null.");
            return null;
        }

        AM2Capabilities cap = AM2CapabilitiesProvider.For(ctx.getServerHandler().player);
        LogHelper.info("Receiving message. Remote level: "+cap.getCurrentLevel()+" Message level: " + message.getCurrLevel());
        cap.fromAM2NBTMessage(message);
        return new AM2NBTMessage(cap);
    }
}
