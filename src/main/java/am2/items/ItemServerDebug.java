package am2.items;

import am2.capabilities.AM2CapabilitiesProvider;
import am2.network.messages.AM2NBTMessage;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemServerDebug extends AM2Item {
    public ItemServerDebug() {
        super("serverdebug");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
            playerIn.sendMessage(new TextComponentString("[" + worldIn.isRemote + "] " + AM2CapabilitiesProvider.For(playerIn).toString()));
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

}
