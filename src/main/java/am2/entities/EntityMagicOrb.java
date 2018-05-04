package am2.entities;

import am2.capabilities.AM2CapabilitiesProvider;
import am2.handler.RegistryHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.List;

public class EntityMagicOrb extends EntityXPOrb {
    public EntityMagicOrb(World worldIn, double x, double y, double z, int expValue) {
        super(worldIn, x, y, z, expValue);
        this.setSize(0.5f, 0.5f);
    }

    public EntityMagicOrb(World worldIn) {
        super(worldIn, 0, 0, 0, 1);
        this.setSize(0.5f, 0.5f);
    }

    /*@Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if (!this.world.isRemote) {
            //AM2CapabilitiesProvider.For(entityIn).IncreaseXP(this.xpValue);
            this.setDead();
        }
    }*/
    @Override
    public void onUpdate() {
        super.onUpdate();
        if(!world.isRemote && this.xpValue == 0) {
            this.setDead();
            return;
        }
        if(this.delayBeforeCanPickup > 0) {
            --this.delayBeforeCanPickup;
        }


        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if(!this.hasNoGravity()) {
            this.motionY -= 0.029999999329447746D;
        }

        float f = 0.98F;

        if(this.onGround) {
            f = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.98F;
        }

        this.motionX *= (double) f;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= (double) f;

        if(this.onGround) {
            this.motionY *= -0.8999999761581421D;
        }
        ++this.xpColor;
        ++this.xpOrbAge;

        if(this.xpOrbAge >= 6000) {
            this.setDead();
        }
        if(world.getTotalWorldTime()%5 ==0) {
            List<EntityMagicOrb> orbs = world.getEntitiesWithinAABB(EntityMagicOrb.class, new AxisAlignedBB(posX - 2, posY - 2, posZ - 2, posX + 2, posY + 2, posZ + 2));
            int newSize = 0;
            if(orbs.size() > 0) {
                EntityMagicOrb orb = orbs.get(world.rand.nextInt(orbs.size()));
                if(!orb.getUniqueID().equals(this.getUniqueID()) && orb.xpValue < this.xpValue) {
                    newSize += orb.getXpValue() + xpValue;
                    orb.setDead();
                }
                if(newSize > xpValue) {
                    if(!world.isRemote) {
                        EntityMagicOrb norb = new EntityMagicOrb(world, posX, posY, posZ, newSize);
                        world.spawnEntity(norb);
                        setDead();
                    }
                }
                orbs.clear();
            }
        }
    }

}
