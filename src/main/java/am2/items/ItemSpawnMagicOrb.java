package am2.items;

import am2.AM2;
import am2.entities.EntityMagicOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemSpawnMagicOrb extends AM2Item {
    public ItemSpawnMagicOrb() {
        super("spawnMagicOrb");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        worldIn.spawnEntity(new EntityMagicOrb(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, 1200));
        return ActionResult.newResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}