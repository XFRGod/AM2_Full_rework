package am2.items;

import am2.capabilities.AM2CapabilitiesProvider;
import am2.handler.AM2PacketHandler;
import am2.network.messages.AM2NBTMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemLevelStick extends AM2Item {
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if (!worldIn.isRemote) {  // don't execute on the server side!
            return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
        }

        AM2PacketHandler.INSTANCE.sendToServer(new AM2NBTMessage(AM2CapabilitiesProvider.For(playerIn).IncreaseLevelWithMana(1)));
        playerIn.sendMessage(new TextComponentString("Increased Level. (" + AM2CapabilitiesProvider.For(playerIn).getCurrentLevel() + ")" + worldIn.isRemote));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    public ItemLevelStick(){
        super("levelstick");
        setMaxStackSize(1);
    }
}
