package am2.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemNBTReader extends AM2Item {
    public ItemNBTReader() {
        super("nbtreader");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.sendMessage(new TextComponentString(playerIn.getHeldItem(handIn).getTagCompound().toString()));
        return ActionResult.newResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}
