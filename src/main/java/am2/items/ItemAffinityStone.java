package am2.items;

import am2.affinity.Affinity;
import am2.handler.RegistryHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemAffinityStone extends AM2Item{
    public ItemAffinityStone() {
        super("affinitystone");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if (!worldIn.isRemote) {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
        }
        for (Affinity affinity : RegistryHandler.GetAffinitiesToRegister()) {
            playerIn.sendMessage(new TextComponentString(affinity.getRegistryName().toString()));
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
