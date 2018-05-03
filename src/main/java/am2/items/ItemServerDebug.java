package am2.items;

import am2.capabilities.AM2CapabilitiesProvider;
import am2.network.handlers.AM2PacketHandler;
import am2.network.messages.AM2NBTMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemServerDebug extends AM2Item {
    public ItemServerDebug() {
        super("serverdebug");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {  // don't execute on the server side!
            playerIn.sendMessage(new TextComponentString("" + AM2CapabilitiesProvider.For(playerIn).getCurrentLevel()));
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}
