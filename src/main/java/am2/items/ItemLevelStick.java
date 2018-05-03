package am2.items;

import am2.capabilities.AM2CapabilitiesProvider;
import am2.utils.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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
        AM2CapabilitiesProvider.For(playerIn).setCurrentLevel(AM2CapabilitiesProvider.For(playerIn).getCurrentLevel()+1);
        playerIn.sendMessage(new TextComponentString("Increased Level. (" + AM2CapabilitiesProvider.For(playerIn).getCurrentLevel() + ")"));
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    public ItemLevelStick(){
        super("levelstick");
        setMaxStackSize(1);
    }
}
