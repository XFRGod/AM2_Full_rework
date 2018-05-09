package am2.items;

import am2.capabilities.AM2CapabilitiesProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemFireBall extends AM2Item {

    public ItemFireBall() {
        super("fireball");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if (!worldIn.isRemote) {  // don't execute on the server side!
            if (AM2CapabilitiesProvider.For(playerIn).decreaseMana(10F)) {
                double d1 = 4.0D;
                Vec3d vec3d = playerIn.getLook(1.0F);
                worldIn.playEvent((EntityPlayer) null, 1016, new BlockPos(playerIn), 0);
                EntityLargeFireball entitylargefireball = new EntityLargeFireball(worldIn, playerIn, vec3d.x * 5D, vec3d.y * 5D, vec3d.z * 5D);
                entitylargefireball.explosionPower = 1;
                entitylargefireball.posX = playerIn.posX;
                entitylargefireball.posY = playerIn.posY;
                entitylargefireball.posZ = playerIn.posZ;
                entitylargefireball.posY += playerIn.getEyeHeight();
                worldIn.spawnEntity(entitylargefireball);
                return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
